package com.nms.corba.ninterface.impl.service.proxy;

import flowDomainFragment.MatrixFlowDomainFragmentList_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import flowDomainFragment.MatrixFlowDomainFragment_THolder;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.tool.CorbaMFDFRConvertTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.service.impl.dispatch.PortDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 单网元以太网业务代理类
 * 
 * @author kk
 * 
 */
public class CorbaMFDFRProxy {

	private ICorbaSession session;

	public CorbaMFDFRProxy(ICorbaSession session) {
		this.session = session;
	}

	/**
	 * @author zk function:创建单点业务
	 * @param createData
	 *            创建业务的数据
	 * @param resultData
	 *            返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	public void createMFDFr(MatrixFlowDomainFragment_T createData, MatrixFlowDomainFragment_THolder resultData) throws ProcessingFailureException {

		CorbaMFDFRConvertTool corbaMFDFRConvertTool = new CorbaMFDFRConvertTool();
		List<ServiceInfo> serviceInfoList = null;
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.session);
		Map<Integer, AcPortInfo> acMap = null;
		String result = null;
		DispatchInterface dispatchInterface = null;
		VerifyNameUtil verifyNameUtil = new VerifyNameUtil();
		int siteId = 0;
		try {
			// 把创建对象转换成serviceInfo
			serviceInfoList = corbaMFDFRConvertTool.convertServiceInfo(createData, this.session);

			// 如果没有转换，返回异常
			if (null == serviceInfoList || serviceInfoList.size() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "createData is error");
			}

			siteId = serviceInfoList.get(0).getaSiteId() == 0 ? serviceInfoList.get(0).getzSiteId() : serviceInfoList.get(0).getaSiteId();

			// 验证名称是否重复。
			if (verifyNameUtil.verifyNameBySingle(EServiceType.valueOf(createData.mfdfrType).getValue(), serviceInfoList.get(0).getName(), null, siteId)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
			}

			// 验证pw是否可用
			if (!this.checkPwIsUsable(serviceInfoList)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "PW is not available");
			}
			
			//修改端口信息
			this.updatePort(serviceInfoList, createData.additionalInfo,true);

			// 创建AC
			acMap = corbaFlowDomainProxy.getCreateAc(serviceInfoList);
			result = corbaFlowDomainProxy.createAcBath(acMap);

			// AC创建失败，返回异常
			if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createMFDFr.");
			} else {
				// AC创建成功后，把serviceinfo中的aAcId和zAcId赋值
				corbaFlowDomainProxy.setAcId(acMap, serviceInfoList);

				// 获取调度接口
				dispatchInterface = this.getDispatchInterface(EServiceType.from(createData.mfdfrType));
				// 创建serviceinfo对象
				if (createData.mfdfrType.equals(EServiceType.ELINE.toString())) {
					result = dispatchInterface.excuteInsert(serviceInfoList.get(0));
				} else {
					result = dispatchInterface.excuteInsert(serviceInfoList);
				}
				if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createMFDFr.");
				}
				resultData.value = createData;
			}

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createMFDFr.");
		}

	}

	/**
	 * @author zk function:激活业务
	 * @param nameAndStringValue_T
	 *            所需激活的业务的数据 eg;arg0[1][4] arg0[0][0]: name=EMS value=”DATAX” arg0[0][1: name=site value=“siteId” arg0[0][2]: name=”dbID” value=”id” arg0[0][3]: name=”serviceType” value=”CES/ETREE/ELINE/ELAN”
	 * 
	 * @throws ProcessingFailureException
	 */
	public void activateMFDFr(NameAndStringValue_T[][] nameAndStringValue) throws ProcessingFailureException {
		try {
			this.setServiceActiveStatus(nameAndStringValue, EActiveStatus.ACTIVITY.getValue());
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * @author guoqc 去激活业务
	 * @param nameAndStringValue
	 *            厂商信息/网元id/业务类型/主键id
	 * @throws ProcessingFailureException
	 */
	public void deactivateMFDFr(NameAndStringValue_T[][] nameAndStringValue) throws ProcessingFailureException {
		try {
			this.setServiceActiveStatus(nameAndStringValue, EActiveStatus.UNACTIVITY.getValue());
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}


	}

	/**
	 * @author fucntion:删除业务
	 * @param nameAndStringValue
	 *            网元对象以及业务名称
	 * @throws Exception
	 * @throws Exception
	 */
	public void deleteMFDFr(NameAndStringValue_T[][] nameAndStringValue) throws ProcessingFailureException {
		int serviceInfoId = 0;// 业务的业务ID
		List<ServiceInfo> serviceInfoList = null;
		DispatchInterface dispatchInterface = null;
		String result = null;
		CorbaFlowDomainProxy corbaFlowDomainProxy = new CorbaFlowDomainProxy(this.session);
		try {
			for (int i = 0; i < nameAndStringValue.length; i++) {
				// 验证名称格式
				this.checkingName(nameAndStringValue[i]);

				serviceInfoId = Integer.parseInt(nameAndStringValue[i][2].value);

				int serviceType = this.getServiceType(serviceInfoId);

				// 根据以太网对象列表
				serviceInfoList = this.selectServiceInfo(serviceType, serviceInfoId);

				// 获取操作接口
				dispatchInterface = this.getDispatchInterface(serviceType);
				result = dispatchInterface.excuteDelete(serviceInfoList);

				// 如果执行结果为成功，那么删除AC对象
				if (ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)) {
					corbaFlowDomainProxy.deleteAcBach(serviceInfoList);
				}
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 查询某网元某业务
	 * 
	 * @param meName
	 *            :网元名称，业务类型
	 * @param mfdfrList
	 *            :业务数据
	 * @throws ProcessingFailureException
	 */
	public void getMeMFDFrs(NameAndStringValue_T[] meName, MatrixFlowDomainFragmentList_THolder mfdfrList) throws ProcessingFailureException {
		int siteId = 0;
		CorbaMFDFRConvertTool corbaMFDFRConvertTool = new CorbaMFDFRConvertTool();
		MatrixFlowDomainFragment_T[] elineArray = null;
		MatrixFlowDomainFragment_T[] etreeArray = null;
		MatrixFlowDomainFragment_T[] elanArray = null;
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		try {
			// 验证网元名称的name是否正确
			if (!CheckParameterUtil.checkMeName(meName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "meName is error");
			}
			// 获取网元id
			siteId = Integer.parseInt(corbaMFDFRConvertTool.getValueByKey(meName, corbaMFDFRConvertTool.MANAGEELEMENT));

			// 查询所有单点eline
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			List<ElineInfo> elineInfoList = elineService.selectBySite_node(siteId);
			elineArray = corbaMFDFRConvertTool.convertMFDFr_eline(siteId, elineInfoList);

			// 查询所有单点etree
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			Map<Integer, List<EtreeInfo>> etreeInfoList = etreeService.selectBySite_node(siteId);
			etreeArray = corbaMFDFRConvertTool.convertMFDFr_etree(siteId, etreeInfoList);
			
			// 查询所有单点elan
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			Map<Integer, List<ElanInfo>> elanInfoList = elanInfoService.selectBySite_node(siteId);
			elanArray = corbaMFDFRConvertTool.convertMFDFr_elan(siteId, elanInfoList);
			
			// 把查到的eline、etree、elan组合起来
			mfdfrList.value = corbaMFDFRConvertTool.concatAll(elineArray, etreeArray, elanArray);

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally{
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elanInfoService);
		}
	}

	/**
	 * 根据标识查询一条以太网业务
	 * 
	 * @param mfdfrName
	 *            以太网标识
	 * @param mfdfr
	 *            出参
	 * @throws ProcessingFailureException
	 */
	public void getMFDFr(NameAndStringValue_T[] mfdfrName, MatrixFlowDomainFragment_THolder mfdfr) throws ProcessingFailureException {
		int serviceInfoId = 0;
		List<ServiceInfo> serviceInfoList = null;
		CorbaMFDFRConvertTool corbaMFDFRConvertTool = new CorbaMFDFRConvertTool();
		try {
			// 验证名称格式
			this.checkingName(mfdfrName);

			serviceInfoId = Integer.parseInt(mfdfrName[2].value);

			int serviceType = this.getServiceType(serviceInfoId);

			// 根据类型和主键查询业务列表
			serviceInfoList = this.selectServiceInfo(serviceType, serviceInfoId);

			if (null != serviceInfoList && serviceInfoList.size() > 0) {
				if (serviceType == EServiceType.ELINE.getValue()) {
					if (serviceInfoList.size() == 1) {
						mfdfr.value = corbaMFDFRConvertTool.convertMFDFr_eline((ElineInfo) serviceInfoList.get(0));
					}
				} else if (serviceType == EServiceType.ETREE.getValue()) {
					mfdfr.value = corbaMFDFRConvertTool.convertMFDFr_etree(serviceInfoList);
				} else if (serviceType == EServiceType.ELAN.getValue()) {
					mfdfr.value = corbaMFDFRConvertTool.convertMFDFr_elan(serviceInfoList);
				}
			}

			if (null == mfdfr.value) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "meName is error");
			}

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 根绝以太网业务类型和主键查询以太网信息
	 * 
	 * @param serviceType
	 *            以太网类型，对应EServiceType
	 * @param serviceId
	 *            主键
	 * @return
	 * @throws ProcessingFailureException
	 */
	private List<ServiceInfo> selectServiceInfo(int serviceType, int serviceId) throws ProcessingFailureException {

		ElineInfoService_MB elineService = null;
		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		try {

			if (serviceType == EServiceType.ELINE.getValue()) {
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				ElineInfo elineInfo = new ElineInfo();
				elineInfo.setId(serviceId);
				serviceInfoList.addAll(elineService.selectByCondition(elineInfo));
			} else if (serviceType == EServiceType.ETREE.getValue()) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				serviceInfoList.addAll(etreeService.selectByServiceId(serviceId));
			} else if (serviceType == EServiceType.ELAN.getValue()) {
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				serviceInfoList.addAll(elanInfoService.selectByServiceId(serviceId));
			}

			// 如果没有查到，说明传入的mfdfrName错误
			if (serviceInfoList.size() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "mfdfrName is error");
			}

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elanInfoService);
		}

		return serviceInfoList;
	}

	/**
	 * 根绝以太网业务类型获取操作的接口
	 * 
	 * @param serviceType
	 *            以太网类型，对应EServiceType
	 * @return
	 * @throws ProcessingFailureException
	 */
	private DispatchInterface getDispatchInterface(int serviceType) throws ProcessingFailureException {

		DispatchInterface dispatchInterface = null;
		try {

			if (serviceType == EServiceType.ELINE.getValue()) {
				dispatchInterface = new ElineDispatch();
			} else if (serviceType == EServiceType.ETREE.getValue()) {
				dispatchInterface = new EtreeDispatch();
			} else if (serviceType == EServiceType.ELAN.getValue()) {
				dispatchInterface = new ElanDispatch();
			}

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return dispatchInterface;
	}

	/**
	 * 设置业务的激活状态
	 * 
	 * @param serviceInfoList
	 * @param activeStatus
	 */
	private void setActiveStatus(List<ServiceInfo> serviceInfoList, int activeStatus) {
		for (ServiceInfo serviceInfo : serviceInfoList) {
			serviceInfo.setActiveStatus(activeStatus);
		}
	}

	/**
	 * 设置业务的激活状态，去激活和激活操作都调用此方法
	 * 
	 * @param nameAndStringValue
	 * @param activeStatus
	 * @throws ProcessingFailureException
	 */
	private void setServiceActiveStatus(NameAndStringValue_T[][] nameAndStringValue, int activeStatus) throws ProcessingFailureException {
		int serviceInfoId = 0;
		List<ServiceInfo> serviceInfoList = null;
		DispatchInterface dispatchInterface = null;
		try {
			for (int i = 0; i < nameAndStringValue.length; i++) {

				// 验证名称格式
				this.checkingName(nameAndStringValue[i]);

				serviceInfoId = Integer.parseInt(nameAndStringValue[i][2].value);

				int serviceType = this.getServiceType(serviceInfoId);

				// 根据以太网对象列表
				serviceInfoList = this.selectServiceInfo(serviceType, serviceInfoId);

				// 设置激活状态
				this.setActiveStatus(serviceInfoList, activeStatus);

				// 获取操作接口
				dispatchInterface = this.getDispatchInterface(serviceType);
				dispatchInterface.excuteUpdate(serviceInfoList);

			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 验证mfdfr名称是否正确
	 * 
	 * @param mfdfrName
	 * @throws ProcessingFailureException
	 */
	private void checkingName(NameAndStringValue_T[] mfdfrName) throws ProcessingFailureException {
		// 验证名称内容
		if (!CheckParameterUtil.checkMFDFrName(mfdfrName)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "mfdfrName is error");
		}
	}

	/**
	 * 根据业务ID获取业务类型
	 * 
	 * @param mfdfrName
	 * @return
	 * @throws ProcessingFailureException
	 */
	private int getServiceType(int serviceInfoId) throws ProcessingFailureException {
		// 根据主键查询业务类型
		ElineInfoService_MB serviceInfoService = null;
		try {
			serviceInfoService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			int serviceType = serviceInfoService.getServiceTypeByServiceId(serviceInfoId);
			return serviceType;
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally{
			UiUtil.closeService_MB(serviceInfoService);
		}

	}

	/**
	 * 验证pw的可用性
	 * 
	 * @return true 可用 false不可用
	 * @throws ProcessingFailureException
	 */
	private boolean checkPwIsUsable(List<ServiceInfo> serviceInfoList) throws ProcessingFailureException {
		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;
		boolean flag = true;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);

			for (ServiceInfo serviceInfo : serviceInfoList) {

				pwInfo = new PwInfo();
				pwInfo.setPwId(serviceInfo.getPwId());
				pwInfo = pwInfoService.queryByPwId(pwInfo);

				// 验证关联的业务主键是否大于0，大于0说明已经关联，不能被使用
				if (null != pwInfo && pwInfo.getRelatedServiceId() > 0) {
					flag = false;
					break;
				}
			}

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}

		return flag;
	}

	/**
	 * 修改业务名称
	 * 
	 * @param objectName
	 *            标识
	 * @param userLabel
	 *            修改后的名称
	 * @param enforceUniqueness
	 *            是否唯一
	 * @throws ProcessingFailureException
	 */
	public void setUserLabel(NameAndStringValue_T[] objectName, String userLabel, boolean enforceUniqueness) throws ProcessingFailureException {
		int serviceInfoId = 0;
		List<ServiceInfo> serviceInfoList = null;
		VerifyNameUtil verifyNameUtil = new VerifyNameUtil();
		DispatchInterface dispatchInterface = null;
		try {
			// 验证名称是否合法
			this.checkingName(objectName);
			serviceInfoId = Integer.parseInt(objectName[2].value);

			// 获取类型
			int serviceType = this.getServiceType(serviceInfoId);

			// 根据以太网对象列表
			serviceInfoList = this.selectServiceInfo(serviceType, serviceInfoId);

			// 验证名称
			if (verifyNameUtil.verifyNameBySingle(serviceType, userLabel, serviceInfoList.get(0).getName(), Integer.parseInt(objectName[1].value))) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
			}

			this.setUserLabel(serviceInfoList, userLabel);

			// 获取操作接口
			dispatchInterface = this.getDispatchInterface(serviceType);
			dispatchInterface.excuteUpdate(serviceInfoList);

		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 给serviceInfo设置userLabel 修改用
	 * 
	 * @param serviceInfoList
	 * @param userLabel
	 */
	private void setUserLabel(List<ServiceInfo> serviceInfoList, String userLabel) {
		for (ServiceInfo serviceInfo : serviceInfoList) {
			serviceInfo.setName(userLabel);
		}
	}

	/**
	 * 修改附加信息
	 * @param objectName
	 * @param additionalInfo
	 * @throws ProcessingFailureException
	 */
	public void setAdditionalInfo(NameAndStringValue_T[] objectName, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		int serviceInfoId=0;
		List<ServiceInfo> serviceInfoList=null;
		try {
			// 验证名称是否合法
			this.checkingName(objectName);
			serviceInfoId = Integer.parseInt(objectName[2].value);
			
			// 获取类型
			int serviceType = this.getServiceType(serviceInfoId);

			// 根据以太网对象列表
			serviceInfoList = this.selectServiceInfo(serviceType, serviceInfoId);
			
			this.updatePort(serviceInfoList, additionalInfo.value,false);
			
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}
	
	/**
	 * 修改端口信息、―	使能广播/组播/未知单播报文抑制使能状态；（*）
	 * @param serviceInfoList
	 * @param nameAndStringValueArray 附加信息列表
	 * @throws ProcessingFailureException
	 */
	private void updatePort(List<ServiceInfo> serviceInfoList,NameAndStringValue_T[] nameAndStringValueArray,boolean isAdd) throws ProcessingFailureException{
		PortInst portInst=null;
		CorbaMFDFRConvertTool corbaMFDFRConvertTool = new CorbaMFDFRConvertTool();
		PortDispatch portDispatch=new PortDispatch();
		String result=null;
		try {
			portInst=this.getPort(serviceInfoList.get(0),isAdd);
			
			if(!portInst.getPortType().equals("UNI")){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "portType is error");
			}
			
			corbaMFDFRConvertTool.convertPort(portInst, nameAndStringValueArray);
			result = portDispatch.excuteUpdate(portInst);
			
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "update port is error : "+result);
			}
			
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

		
	}
	
	/**
	 * 根据seriveInfo获取port对象
	 * @param serviceInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private PortInst getPort(ServiceInfo serviceInfo,boolean isAdd) throws ProcessingFailureException{
		
		int acId=0;
		AcPortInfoService_MB acInfoService=null;
		PortService_MB portService=null;
		PortInst portinst=new PortInst();
		int portId=0;
		try {
			if(isAdd){
				if(null!=serviceInfo.getCreateAc_a()){
					portId=serviceInfo.getCreateAc_a().getPortId();
				}else{
					portId=serviceInfo.getCreateAc_z().getPortId();
				}
				
			}else{
				if(serviceInfo.getaAcId()>0){
					acId=serviceInfo.getaAcId();
				}else{
					acId=serviceInfo.getzAcId();
				}
				//根据主键查询ac
				acInfoService=(AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				AcPortInfo acPortInfo=acInfoService.selectById(acId);
				portId=acPortInfo.getPortId();
			}
			
			
			//根据AC中的portid查询端口
			portService=(PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portinst.setPortId(portId);
			List<PortInst> portList=portService.select(portinst);
			
			if(null==portList || portList.size()!=1){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}else{
				portinst=portList.get(0);
			}
			
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(acInfoService);
		}

		return portinst;
	}
}
