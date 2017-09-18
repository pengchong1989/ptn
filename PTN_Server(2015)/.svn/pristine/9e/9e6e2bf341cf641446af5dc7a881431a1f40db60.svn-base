package com.nms.corba.ninterface.impl.service.tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import performance.AdministrativeState_T;
import subnetworkConnection.SNCState_T;
import subnetworkConnection.TPData_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.UiUtil;

import flowDomain.ConnectivityState_T;
import flowDomain.FlowDomain_T;
import flowDomainFragment.FDFrCreateData_T;
import flowDomainFragment.FlowDomainFragment_T;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import globaldefs.ConnectionDirection_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * corba部分以太网业务转化你对象工具类
 * 
 * @author kk
 * 
 */
public class CorbaFlowDomainConvertTools extends CorbaConvertBase {

	private final String A2ZVLANID = "A2ZvlanId";// A端 VlanID
	private final String Z2AVLANID = "Z2AvlanId";// z端 VlanID
	private final String A2ZEXITRULE = "A2ZexitRule";// A端 出口规则，关联code表
	private final String Z2AEXITRULE = "Z2AexitRule";// z端 出口规则，关联code表
	private final String A2ZVLANPRI = "A2Zvlanpri";
	private final String Z2AVLANPRI = "Z2Avlanpri";
	private final String A2ZPORTMODEL = "A2ZportModel";// A端 端口模式，关联code表
	private final String Z2APORTMODEL = "Z2AportModel";// Z端 端口模式，关联code表
	private final String FD="FD";//FlowDomain_T的name名称  第2层名

	/**
	 * 把eline转换成FDFr对象
	 * 
	 * @param elineInfoList
	 *            eline集合
	 * @param flowDomainFragmentArray
	 *            FDFr数组
	 */
	public List<FlowDomainFragment_T> convertElineToFDFr(List<ElineInfo> elineInfoList, FlowDomainFragment_T[] flowDomainFragmentArray) throws ProcessingFailureException {
		List<FlowDomainFragment_T> flowDomainList=new ArrayList<FlowDomainFragment_T>();
		int count = 0;
		try {

			if (null != elineInfoList && elineInfoList.size() > 0) {
				for (ElineInfo elineInfo : elineInfoList) {
					flowDomainFragmentArray[count] = this.convertFDFr(elineInfo);
					flowDomainList.add(flowDomainFragmentArray[count]);
					count++;
				}
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return flowDomainList;
	}

	/**
	 * 把etree转换成FDFr对象
	 * 
	 * @param etreeInfoMap
	 *            etree集合
	 * @param flowDomainFragmentArray
	 *            FDFr数组
	 */
	public List<FlowDomainFragment_T> convertEtreeToFDFr(Map<Integer, List<EtreeInfo>> etreeInfoMap, FlowDomainFragment_T[] flowDomainFragmentArray) throws ProcessingFailureException {
		List<FlowDomainFragment_T> flowDomainList=new ArrayList<FlowDomainFragment_T>();
		int count = 0;
		try {

			if (null != etreeInfoMap && etreeInfoMap.keySet().size() > 0) {
				for (Integer key : etreeInfoMap.keySet()) {
					// tree对象转换第一个就可以
					flowDomainFragmentArray[count] = this.convertFDFr(etreeInfoMap.get(key).get(0));
					flowDomainList.add(flowDomainFragmentArray[count]);
					count++;
				}
			}

		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return flowDomainList;
	}

	/**
	 * 把elan转换成FDFr对象
	 * 
	 * @param elanInfoMap
	 *            elan集合
	 * @param flowDomainFragmentArray
	 *            FDFr数组
	 */
	public List<FlowDomainFragment_T> convertElanToFDFr(Map<Integer, List<ElanInfo>> elanInfoMap, FlowDomainFragment_T[] flowDomainFragmentArray) throws ProcessingFailureException {
		List<FlowDomainFragment_T> flowDomainList=new ArrayList<FlowDomainFragment_T>();
		int count = 0;
		try {

			if (null != elanInfoMap && elanInfoMap.keySet().size() > 0) {
				for (Integer key : elanInfoMap.keySet()) {
					// tree对象转换第一个就可以
					flowDomainFragmentArray[count] = this.convertFDFr(elanInfoMap.get(key).get(0));
					flowDomainList.add(flowDomainFragmentArray[count]);
					count++;
				}
			}

		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return flowDomainList;
	}

	/**
	 * eline转换fdfr
	 * 
	 * @param elineInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private FlowDomainFragment_T convertFDFr(ElineInfo elineInfo) throws ProcessingFailureException {
		FlowDomainFragment_T flowDomainFragment = new FlowDomainFragment_T();
		flowDomainFragment.userLabel = elineInfo.getName();// 本地名称
		flowDomainFragment.name=super.convertName(ELayerRate.ETHSERVICE.getValue(), elineInfo.getId(), 0);
		flowDomainFragment.nativeEMSName = elineInfo.getName();
		flowDomainFragment.owner =super.getOwner();
		flowDomainFragment.fdfrType = EServiceType.ELINE.toString();// 类型
		//设置默认属性
		flowDomainFragment=this.setDefauoltFdfr(flowDomainFragment,false);	
		flowDomainFragment.transmissionParams.layer=(short) ELayerRate.ETHSERVICE.getValue();
		flowDomainFragment.fdfrState=elineInfo.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()?(SNCState_T.SNCS_ACTIVE):(SNCState_T.SNCS_NONEXISTENT);
		return flowDomainFragment;
	}

	/**
	 * etree转换fdfr
	 * 
	 * @param elineInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private FlowDomainFragment_T convertFDFr(EtreeInfo etreeInfo) throws ProcessingFailureException {
		FlowDomainFragment_T flowDomainFragment = new FlowDomainFragment_T();
		flowDomainFragment.userLabel = etreeInfo.getName();// 本地名称
		flowDomainFragment.name=super.convertName(ELayerRate.ETHSERVICE.getValue(), etreeInfo.getId(), 0);
		flowDomainFragment.nativeEMSName = etreeInfo.getName();
		flowDomainFragment.owner =super.getOwner();
		flowDomainFragment.fdfrType = EServiceType.ETREE.toString();// 类型
		//设置默认属性
		flowDomainFragment=this.setDefauoltFdfr(flowDomainFragment,false);
		flowDomainFragment.transmissionParams.layer=(short) ELayerRate.ETHSERVICE.getValue();
		flowDomainFragment.fdfrState=etreeInfo.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()?(SNCState_T.SNCS_ACTIVE):(SNCState_T.SNCS_NONEXISTENT);
		return flowDomainFragment;
	}

	/**
	 * elan转换fdfr
	 * 
	 * @param elineInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
		FlowDomainFragment_T flowDomainFragment = new FlowDomainFragment_T();
		private FlowDomainFragment_T convertFDFr(ElanInfo elanInfo) throws ProcessingFailureException {
		flowDomainFragment.userLabel = elanInfo.getName();// 本地名称
		flowDomainFragment.name=super.convertName(ELayerRate.ETHSERVICE.getValue(), elanInfo.getId(), 0);
		flowDomainFragment.nativeEMSName = elanInfo.getName();
		flowDomainFragment.owner =super.getOwner();
		flowDomainFragment.fdfrType = EServiceType.ELAN.toString();// 类型
		//设置默认属性
		flowDomainFragment=this.setDefauoltFdfr(flowDomainFragment,false);
		flowDomainFragment.transmissionParams.layer=(short) ELayerRate.ETHSERVICE.getValue();
		flowDomainFragment.fdfrState=elanInfo.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()?(SNCState_T.SNCS_ACTIVE):(SNCState_T.SNCS_NONEXISTENT);
		
		
		return flowDomainFragment;
	}
	
	/**
	 * serviceInfo转换fdfr 验证serviceInfo是什么类型，调用相应的转换方法
	 * 
	 * @param serviceInfo
	 *            eline etree elan 的基类
	 * @return
	 * @throws ProcessingFailureException
	 */
	public FlowDomainFragment_T convertFDFr(ServiceInfo serviceInfo) throws ProcessingFailureException {
		FlowDomainFragment_T flowDomainFragment = null;
		if (serviceInfo instanceof ElineInfo) {
			flowDomainFragment = this.convertFDFr((ElineInfo) serviceInfo);
		} else if (serviceInfo instanceof EtreeInfo) {
			flowDomainFragment = this.convertFDFr((EtreeInfo) serviceInfo);
		} else if (serviceInfo instanceof ElanInfo) {
			flowDomainFragment = this.convertFDFr((ElanInfo) serviceInfo);
		}
		return flowDomainFragment;
	}

	/**
	 * eline转换MFDFr
	 * 
	 * @param elineInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private MatrixFlowDomainFragment_T convertMFDFr(ElineInfo elineInfo) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T matrixFlowDomainFragment_T = new MatrixFlowDomainFragment_T();
		matrixFlowDomainFragment_T.direction = ConnectionDirection_T.CD_BI;// 双向

		matrixFlowDomainFragment_T.active = elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
		matrixFlowDomainFragment_T.mfdfrType = EServiceType.from(elineInfo.getServiceType()).toString();// 路由类型 eline点到点-（1） etree point-to-multipoint（2） elan多点（3）
		matrixFlowDomainFragment_T.transmissionParams = new LayeredParameters_T();
		matrixFlowDomainFragment_T.transmissionParams.layer = (short) ELayerRate.ETHSERVICE.getValue();// 标识层速率
		//默认值
		matrixFlowDomainFragment_T.transmissionParams.transmissionParams=new NameAndStringValue_T[0];
		matrixFlowDomainFragment_T.aEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.aEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(elineInfo.getaAcId()), elineInfo.getaSiteId());
		matrixFlowDomainFragment_T.zEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.zEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(elineInfo.getzAcId()), elineInfo.getzSiteId());
		matrixFlowDomainFragment_T.additionalInfo = this.getAdditionalInfo(elineInfo, elineInfo.getPwId());
		matrixFlowDomainFragment_T.additionalInfo[27]=super.getMegId(elineInfo.getId(), elineInfo.getaSiteId(), EServiceType.ELINE.toString(),super.AMEG,OamTypeEnum.MEP.getValue());
		matrixFlowDomainFragment_T.additionalInfo[28]=super.getMegId(elineInfo.getId(), elineInfo.getzSiteId(), EServiceType.ELINE.toString(),super.ZMEG,OamTypeEnum.MEP.getValue());
		return matrixFlowDomainFragment_T;
	}
	public static void main(String[] args) {
		int id=10;
		NameAndStringValue_T[] a=new NameAndStringValue_T[3];
		NameAndStringValue_T n=null;
		n=new NameAndStringValue_T("1","10");
		a[0]=n;
		id=24;
		n=new NameAndStringValue_T("2","24");
		a[1]=n;
		System.out.println(a[0].value+" :  dfssdf :"+a[1].value);
		
		
	}
	/**
	 * etree转换MFDFr
	 * 
	 * @param etreeInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private MatrixFlowDomainFragment_T convertMFDFr(EtreeInfo etreeInfo) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T matrixFlowDomainFragment_T = new MatrixFlowDomainFragment_T();
		matrixFlowDomainFragment_T.direction = ConnectionDirection_T.CD_BI;// 双向
		matrixFlowDomainFragment_T.active = etreeInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
		matrixFlowDomainFragment_T.mfdfrType = EServiceType.from(etreeInfo.getServiceType()).toString();// 路由类型 eline点到点-（1） etree point-to-multipoint（2） elan多点（3）
		matrixFlowDomainFragment_T.transmissionParams = new LayeredParameters_T();
		matrixFlowDomainFragment_T.transmissionParams.layer = (short) ELayerRate.ETHSERVICE.getValue();// 标识层速率
		//默认值
		matrixFlowDomainFragment_T.transmissionParams.transmissionParams=new NameAndStringValue_T[0];
		matrixFlowDomainFragment_T.aEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.aEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(etreeInfo.getaAcId()), etreeInfo.getaSiteId());
		matrixFlowDomainFragment_T.zEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.zEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(etreeInfo.getzAcId()), etreeInfo.getzSiteId());
		matrixFlowDomainFragment_T.additionalInfo = this.getAdditionalInfo(etreeInfo, etreeInfo.getPwId());
		return matrixFlowDomainFragment_T;
	}

	/**
	 * elan转换MFDFr
	 * 
	 * @param elanInfo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private MatrixFlowDomainFragment_T convertMFDFr(ElanInfo elanInfo) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T matrixFlowDomainFragment_T = new MatrixFlowDomainFragment_T();
		matrixFlowDomainFragment_T.direction = ConnectionDirection_T.CD_BI;// 双向
		matrixFlowDomainFragment_T.active = elanInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
		matrixFlowDomainFragment_T.mfdfrType = EServiceType.from(elanInfo.getServiceType()).toString();// 路由类型 eline点到点-（1） etree point-to-multipoint（2） elan多点（3）
		matrixFlowDomainFragment_T.transmissionParams = new LayeredParameters_T();
		matrixFlowDomainFragment_T.transmissionParams.layer = (short) ELayerRate.ETHSERVICE.getValue();// 标识层速率
		//默认值
		matrixFlowDomainFragment_T.transmissionParams.transmissionParams=new NameAndStringValue_T[0];
		matrixFlowDomainFragment_T.aEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.aEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(elanInfo.getaAcId()), elanInfo.getaSiteId());
		matrixFlowDomainFragment_T.zEnd = new NameAndStringValue_T[1][];
		matrixFlowDomainFragment_T.zEnd[0] = super.convertName(ELayerRate.PORT.getValue(), getByAcId(elanInfo.getzAcId()), elanInfo.getzSiteId());
		matrixFlowDomainFragment_T.additionalInfo = this.getAdditionalInfo(elanInfo, elanInfo.getPwId());
		return matrixFlowDomainFragment_T;
	}
	
	/**
	 * 转换详细的路由信息 验证serviceInfo是什么类型，调用相应的转换方法
	 * 
	 * @param serviceInfo
	 *            eline etree elan 的基类
	 * @return
	 * @throws ProcessingFailureException
	 */
	public MatrixFlowDomainFragment_T convertMFDFr(ServiceInfo serviceInfo) throws ProcessingFailureException {
		MatrixFlowDomainFragment_T matrixFlowDomainFragment_T = null;
		if (serviceInfo instanceof ElineInfo) {
			matrixFlowDomainFragment_T = this.convertMFDFr((ElineInfo) serviceInfo);
		} else if (serviceInfo instanceof EtreeInfo) {
			matrixFlowDomainFragment_T = this.convertMFDFr((EtreeInfo) serviceInfo);
		} else if (serviceInfo instanceof ElanInfo) {
			matrixFlowDomainFragment_T = this.convertMFDFr((ElanInfo) serviceInfo);
		}
		return matrixFlowDomainFragment_T;
	}

	/**
	 * 以太网附加信息
	 * 
	 * @param serviceInfo
	 * @param pwId
	 *            业务关联的pw的主键ID
	 * @param layer
	 *            层速率
	 * @return
	 * @throws ProcessingFailureException
	 */
	private NameAndStringValue_T[] getAdditionalInfo(ServiceInfo serviceInfo, int pwId) throws ProcessingFailureException {
		if (serviceInfo == null) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "the parameter is null");
		}
		NameAndStringValue_T[] nameAndStringValue = null;
		AcPortInfoService_MB acInfoService = null;
		List<QosInfo> qosList = null;
		AcPortInfo serviceAacInfo = null;
		AcPortInfo serviceZacInfo = null;
		QosInfo qosInfo = null;
		ElineInfo elineInfo=null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			qosList = new ArrayList<QosInfo>();
			serviceAacInfo = acInfoService.selectById(serviceInfo.getaAcId());
			qosInfo = serviceAacInfo.getSimpleQos();
			qosList.add(qosInfo);// 加入A端AC即： A2Z
			serviceZacInfo = acInfoService.selectById(serviceInfo.getzAcId());
			qosInfo = serviceZacInfo.getSimpleQos();
			qosList.add(qosInfo);// 加入A端AC即： Z2A
			if(serviceInfo.getServiceType()==EServiceType.ELINE.getValue()){
				elineInfo=(ElineInfo) serviceInfo;
				if(UiUtil.isNull(elineInfo.getOamList())){
					nameAndStringValue = new NameAndStringValue_T[28];
				}else{
					nameAndStringValue = new NameAndStringValue_T[26];
				}
			}
			
			super.converQos(nameAndStringValue, qosList);// 调用父类，添加qos信息（固定15个数据）
			/*
			 * AC -vlan A 端标识A2Z开头
			 */
			nameAndStringValue[14] = new NameAndStringValue_T(this.CREATEUSER, serviceInfo.getCreateUser());// 用户名称
			nameAndStringValue[15] = new NameAndStringValue_T(this.A2ZVLANID, serviceAacInfo.getVlanId());// vlanid=下话增加VLAN ID
			nameAndStringValue[16] = new NameAndStringValue_T(this.A2ZEXITRULE, this.getExitRule_select(serviceAacInfo)+""); // 出口规则，关联code表主键 = 下话TAG行为
			nameAndStringValue[17] = new NameAndStringValue_T(this.A2ZVLANPRI, serviceAacInfo.getVlanpri());// vlanpri=下话增加VLAN PRI
			nameAndStringValue[18] = new NameAndStringValue_T(this.A2ZPORTMODEL, UiUtil.getCodeById(serviceAacInfo.getPortModel()).getCodeValue());// 端口模式，关联code表主键

			// Z端标识Z2A 开头
			nameAndStringValue[19] = new NameAndStringValue_T(this.Z2AVLANID, serviceZacInfo.getVlanId());// vlanid=下话增加VLAN ID
			nameAndStringValue[20] = new NameAndStringValue_T(this.Z2AEXITRULE,this.getExitRule_select(serviceZacInfo)+""); // 出口规则，关联code表主键 = 下话TAG行为
			nameAndStringValue[21] = new NameAndStringValue_T(this.Z2AVLANPRI, serviceZacInfo.getVlanpri() + "");// vlanpri=下话增加VLAN PRI
			nameAndStringValue[22] = new NameAndStringValue_T(this.Z2APORTMODEL, UiUtil.getCodeById(serviceZacInfo.getPortModel()).getCodeValue());// 端口模式，关联code表主键

			nameAndStringValue[23] = new NameAndStringValue_T(super.CREATETIME, DateTimeUtil.converCorbaTime(serviceInfo.getCreateTime(),DateUtil.FULLTIME));// 创建时间
			nameAndStringValue[24] = new NameAndStringValue_T(super.SERVERCONNECTIONS, ELayerRate.PW.getValue() + "/"+pwId );// 使用的PW路径标识
			nameAndStringValue[25] = new NameAndStringValue_T(super.CLIENTNAME,super.queryById(serviceInfo.getClientId()));// 客户名称
			nameAndStringValue[25] = new NameAndStringValue_T(super.CLIENTNAME,super.queryById(serviceInfo.getClientId()));// 客户名称
			
		}catch(ProcessingFailureException e){ 
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return nameAndStringValue;
	}

	/**
	 * 把createData、mfdfrs对象转换成serviceInfoList集合。 创建用
	 * 
	 * @param createData
	 *            创建的FDFr信息。 里面只有基本信息，名称、类型、激活状态
	 * @param mfdfrs
	 *            创建业务的详细路由信息。
	 * @param serviceInfoList
	 *            转换后的serviceinfo集合
	 * @return 具体的操作调度类实现
	 * @throws ProcessingFailureException
	 */
	public DispatchInterface convertServiceInfoList(FDFrCreateData_T createData, MatrixFlowDomainFragment_T[] mfdfrs, List<ServiceInfo> serviceInfoList,ICorbaSession userSession) throws ProcessingFailureException {
		if (createData.fdfrType == null || createData.fdfrType.equals("") || mfdfrs == null || mfdfrs.length <= 0) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrType is invalid!");
		}
		DispatchInterface dispatchInterface = null;
		String className = null; // 要实例化的类名

		try {
			if (EServiceType.ELINE.toString().equals(createData.fdfrType)) {
				dispatchInterface = new ElineDispatch();
				className = ElineInfo.class.getName();
			} else if (EServiceType.ETREE.toString().equals(createData.fdfrType)) {
				searchRoot(mfdfrs);// 添加etree的验证（A端网元是否为跟节点）
				dispatchInterface = new EtreeDispatch();
				className = EtreeInfo.class.getName();
			} else if (EServiceType.ELAN.toString().equals(createData.fdfrType)) {
				dispatchInterface = new ElanDispatch();
				className = ElanInfo.class.getName();
			}

			// 遍历所有路由信息，把每条路由通过类名转换成对应的对象
			for (MatrixFlowDomainFragment_T matrixFlowDomainFragment : mfdfrs) {

				serviceInfoList.add(this.convertServiceInfo(matrixFlowDomainFragment, createData, className,userSession));
			}

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			className = null;
		}
		return dispatchInterface;
	}

	/**
	 * 把corba的路由信息通过className参数转换成eline、etree、elan对象
	 * 
	 * @param matrixFlowDomainFragment
	 *            corba的详细路由信息
	 * @param createData
	 *            corba的业务信息
	 * @param className
	 *            要转换的类名
	 * @return 转换后返回的对象
	 * @throws ProcessingFailureException
	 */
	private ServiceInfo convertServiceInfo(MatrixFlowDomainFragment_T matrixFlowDomainFragment, FDFrCreateData_T createData, String className,ICorbaSession userSession) throws ProcessingFailureException {
		ServiceInfo serviceInfo = null;
		List<QosInfo> qosList = null;
		AcPortInfo service_AacInfo = null;
		AcPortInfo service_ZacInfo = null;
		try {
			// 通过反射，取到要转换的对象是eline、etree、elan
			@SuppressWarnings("rawtypes")
			Class classInfo = Class.forName(className);
			serviceInfo = (ServiceInfo) classInfo.newInstance();

			// 设置业务基本属性
			serviceInfo.setName(createData.userLabel);
			serviceInfo.setActiveStatus(matrixFlowDomainFragment.active ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
			serviceInfo.setServiceType(EServiceType.valueOf(matrixFlowDomainFragment.mfdfrType).getValue());
			serviceInfo.setaSiteId(Integer.parseInt(super.getValueByKey(matrixFlowDomainFragment.aEnd[0], super.MANAGEELEMENT)));
			serviceInfo.setzSiteId(Integer.parseInt(super.getValueByKey(matrixFlowDomainFragment.zEnd[0], super.MANAGEELEMENT)));
			//pw命名规则： 速率/主键   --如： 3/13  既分割pwId 得到2个元素（取出第2个元素）
			serviceInfo.setPwId(super.converByElaytoId(ELayerRate.PW.getValue(),super.getValueByKey(matrixFlowDomainFragment.additionalInfo, super.SERVERCONNECTIONS)));			
			//serviceInfo.setCreateTime(super.getValueByKey(matrixFlowDomainFragment.additionalInfo, super.CREATETIME));
			serviceInfo.setCreateUser(userSession.getUserName());
			serviceInfo.setClientId(super.queryByName(super.getValueByKey(matrixFlowDomainFragment.additionalInfo,super.CLIENTNAME)));//客户名称
			// 通过附加信息取出此业务的qos对象。 qos中第0个元素为A端的qos 第1个元素为Z端的qos
			qosList = super.converQosList(matrixFlowDomainFragment.additionalInfo);// 获取qos信息（包括A,Z端）
			if (null == qosList || qosList.size() != 2) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}

			// 分别创建A、Z端AC对象
			service_AacInfo = this.convertAcinfo(matrixFlowDomainFragment, "a", serviceInfo.getaSiteId(), qosList);
			service_ZacInfo = this.convertAcinfo(matrixFlowDomainFragment, "z", serviceInfo.getzSiteId(), qosList);

			// 设置AC对象
			serviceInfo.setCreateAc_a(service_AacInfo);
			serviceInfo.setCreateAc_z(service_ZacInfo);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			qosList = null;
			service_AacInfo = null;
			service_ZacInfo = null;
		}
		return serviceInfo;
	}

	/**
	 * 通过AC主键acId 查找此AC下的端口ID
	 * 
	 * @param serviceInifo
	 * @return
	 * @throws ProcessingFailureException
	 */
	private int getByAcId(int acId) throws ProcessingFailureException {
		AcPortInfoService_MB acInfoService = null;
		AcPortInfo acPortInfo = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = acInfoService.selectById(acId);
		} 
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "no find Port ID");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return acPortInfo.getPortId();
	}

	/**
	 * Corba对象转为Etree业务时：判断A端网元ID是否正确-- 所有A 端为跟节点（即：A端id都相同）
	 * 
	 * @param mfdfrs
	 * @throws ProcessingFailureException
	 */
	private void searchRoot(MatrixFlowDomainFragment_T[] mfdfrs) throws ProcessingFailureException {
		boolean flag = false;
		int siteId = 0;
		try {
			// 取出第一条路由信息，将此路由下A 端网元ID 取出作为标记（siteId）：遍历所有路由，若有路由的A端Id与标记的ID 不同，则转换对象失败
			siteId = Integer.parseInt(super.getValueByKey(mfdfrs[0].aEnd[0], super.MANAGEELEMENT));
			// 遍历所有路由信息，每条路由A Z端网元
			for (MatrixFlowDomainFragment_T matrixFlowDomainFragment : mfdfrs) {
				if (siteId != Integer.parseInt(super.getValueByKey(matrixFlowDomainFragment.aEnd[0], super.MANAGEELEMENT))) {
					flag = true;
					break;
				}
			}
			if (flag) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "FlowDomain is invalid!");
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
	}

	/**
	 * 转换AC对象
	 * 
	 * @param matrixFlowDomainFragment
	 *            界面中的路由信息
	 * @param type
	 *            "a"或"z" 区分该取哪端信息
	 * @param siteId
	 *            网元主键
	 * @param qosList
	 *            qos集合
	 * @return 转换后的AC对象
	 * @throws ProcessingFailureException
	 */
	private AcPortInfo convertAcinfo(MatrixFlowDomainFragment_T matrixFlowDomainFragment, String type, int siteId, List<QosInfo> qosList) throws ProcessingFailureException {
		AcPortInfo acPortInfo = new AcPortInfo();
		NameAndStringValue_T[] portNames = null; // 端口名称数组
		String str_vlanId = null; // vlanid标识
		String str_vlanpri = null; // vlanpri标识
		QosInfo qosInfo = null; // qos对象
		String str_portMode = null; // 端口模式
		String str_exitRule = null;
		SiteService_MB siteService=null;
		PortService_MB portService=null;
		try {	
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portService=(PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			acPortInfo.setName("AC_" + new Date().getTime());
			acPortInfo.setSiteId(siteId);
			// 通过A或Z取出对应的标识和对象
			if ("a".equals(type)) {
				portNames = matrixFlowDomainFragment.aEnd[0];
				str_vlanId = this.A2ZVLANID;
				str_vlanpri = this.A2ZVLANPRI;
				qosInfo = qosList.get(0);
				str_portMode = this.A2ZPORTMODEL;
				str_exitRule = this.A2ZEXITRULE;
			} else {
				portNames = matrixFlowDomainFragment.zEnd[0];
				str_vlanId = this.Z2AVLANID;
				str_vlanpri = this.Z2AVLANPRI;
				qosInfo = qosList.get(1);
				str_portMode = this.Z2APORTMODEL;
				str_exitRule = this.Z2AEXITRULE;
			}
			// 设置AC的基础属性
			int portId=Integer.parseInt(super.getValueByKey(portNames, super.PTP,siteId));
			PortInst info=portService.selectPortybyid(portId);//去得端口主键，通过端口主键查询端口表
			if(!(info.getIsEnabled_code()==1&&info.getPortType().equals("UNI")&&info.getSiteId()==siteId)){//验证端口是否可用(为使能UNI端口-可用)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "PTP is Unused!");
			}
			
			acPortInfo.setPortId(portId);// 端口ID
			acPortInfo.setClientVlanId(super.getValueByKey(matrixFlowDomainFragment.additionalInfo, str_vlanId));// vlanid=下话增加VLAN ID
			acPortInfo.setVlanpri(super.getValueByKey(matrixFlowDomainFragment.additionalInfo, str_vlanpri));// vlanpri=下话增加VLAN PRI
			acPortInfo.setSimpleQos(qosInfo);
			acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", "0").getId());
			acPortInfo.setAcStatus(EActiveStatus.ACTIVITY.getValue());

			// 如果是晨晓设备，设置晨晓需要的默认属性， 否则设置武汉的。
			if (siteService.getManufacturer(acPortInfo.getSiteId()) == EManufacturer.CHENXIAO.getValue()) {
				acPortInfo.setVlanId(1 + "");
				acPortInfo.setOperatorVlanId(1 + "");
				acPortInfo.setVlancri(0 + "");
				acPortInfo.setManagerEnable(UiUtil.getCodeByValue("MACLEARN", "0").getId());// 管理状态，关联code表主键
				acPortInfo.setPortModel(UiUtil.getCodeByValue("portModel", super.getValueByKey(matrixFlowDomainFragment.additionalInfo, str_portMode)).getId());// 端口模式，关联code表主键
				acPortInfo.setExitRule(this.getExitRule(true, super.getValueByKey(matrixFlowDomainFragment.additionalInfo, str_exitRule)));// 出口规则，关联code表主键 = 下话TAG行为
			} else {
				acPortInfo.setPortModel(UiUtil.getCodeByValue("UNIPORTMODE", "0").getId());// 端口模式，关联code表主键
				acPortInfo.setMacAddressLearn(UiUtil.getCodeByValue("MACLEARN", "0").getId());// MAC，关联code表主键
				acPortInfo.setHorizontalDivision(UiUtil.getCodeByValue("VCTRAFFICPOLICING", "0").getId());// 水平分割，关联code表主键
				acPortInfo.setManagerEnable(UiUtil.getCodeByValue("VCTRAFFICPOLICING", "0").getId());// TMC流量监管使能
				acPortInfo.setModel(UiUtil.getCodeByValue("MODEL", "0").getId());// 模式，关联code表主键
				acPortInfo.setExitRule(this.getExitRule(false, super.getValueByKey(matrixFlowDomainFragment.additionalInfo, str_exitRule)));// 出口规则，关联code表主键 = 下话TAG行为
				acPortInfo.setTagAction(UiUtil.getCodeByValue("TAGRECOGNITION", "0").getId());
				//设置武汉设备  特有的 属性： 默认值
				acPortInfo.setBufferList(super.getAcBufferList(siteId));
			}

		}catch(ProcessingFailureException e){
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(siteService);
		}
		return acPortInfo;
	}

	/**
	 * 根据corba传入的出口规则查询出对应的CODE主键
	 * @param isCX 是否为晨晓
	 * @param corbaExitRule corba的出口规则值
	 * @return code主键
	 * @throws ProcessingFailureException
	 */
	public int getExitRule(boolean isCX, String corbaExitRule) throws ProcessingFailureException {
		int exitRuleValue = 0;
		int exitRuleId=0;
		String groupName=null;
		try {
			exitRuleValue = Integer.parseInt(corbaExitRule);
			
			//如果是晨晓。 那么 2=修改  3=删除
			if (isCX) {
				switch (exitRuleValue) {
				case 2:
					exitRuleValue = 3;
					break;
				case 3:
					exitRuleValue = 2;
					break;
				default:
					break;
				}
				groupName="exitRule";
			}else{
				groupName="PORTTAGBEHAVIOR";
			}
			exitRuleId=UiUtil.getCodeByValue(groupName, exitRuleValue+"").getId();
			
			
		} 
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getExitRule.");
		} finally{
			groupName=null;
		}
		return exitRuleId;
	}

	/**
	 * 查询时，获取AC的出口规则具体的值  晨晓的删除为3返回2  修改为2返回3
	 * @param acPortInfo ac端口对象
	 * @return 出口规则值
	 * @throws ProcessingFailureException
	 */
	public int getExitRule_select(AcPortInfo acPortInfo) throws ProcessingFailureException {
		int exitRuleValue=0;
		SiteService_MB siteService=null;
		
		try {	
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			exitRuleValue=Integer.parseInt(UiUtil.getCodeById(acPortInfo.getExitRule()).getCodeValue());
			
			if(siteService.getManufacturer(acPortInfo.getSiteId()) == EManufacturer.CHENXIAO.getValue()){
				switch (exitRuleValue) {
				case 2:
					exitRuleValue = 3;
					break;
				case 3:
					exitRuleValue = 2;
					break;
				default:
					break;
				}
			}
			
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return exitRuleValue;
	}
	/**
	 * 设置FlowDomainFragment_T对象的默认值
	 * @param flowDomainFragment
	 * @param flag
	 * 			true:为FlowDomainFragment_T对象的所有属性设置默认值
	 * 			false: 为FlowDomainFragment_T对象，对于Corba以太网业务来说没有用到的属性设置默认值
	 * @return
	 */
	public FlowDomainFragment_T setDefauoltFdfr(FlowDomainFragment_T flowDomainFragment,boolean flag){
		if(flag){
			//说明需要为FlowDomainFragment_T对象赋值，如删除时没用到此参数，却必须为其赋值
			flowDomainFragment.userLabel = "";// 本地名称
			flowDomainFragment.name=new NameAndStringValue_T[0];
			flowDomainFragment.nativeEMSName = "";
			flowDomainFragment.owner = super.getOwner();
			flowDomainFragment.fdfrType = ELayerRate.ETHSERVICE.toString();// 类型
			flowDomainFragment.fdfrState=(SNCState_T.SNCS_ACTIVE);
		}
		//设置  FlowDomainFragment_T 的默认值，即填补属性为null的属性
		flowDomainFragment.administrativeState=AdministrativeState_T.AS_Locked;
		flowDomainFragment.direction=ConnectionDirection_T.CD_BI;
		flowDomainFragment.flexible=true;
		flowDomainFragment.zEnd=new TPData_T[0];
		flowDomainFragment.aEnd=new TPData_T[0];
		flowDomainFragment.transmissionParams=new LayeredParameters_T();
		flowDomainFragment.transmissionParams.layer=(short) ELayerRate.ETHSERVICE.getValue();
		flowDomainFragment.transmissionParams.transmissionParams=new NameAndStringValue_T[0];
		flowDomainFragment.additionalInfo=new NameAndStringValue_T[0];
		return flowDomainFragment;
	}
	/**
	 * @author sy
	 * 		验证创建以太网业务时： 输入的pw 是否已经被使用
	 * @param matrixFlowDomainFragment_T[]
	 * 			创建输入的参数
	 * @return  flag
	 * 		true:  输入的pw 已经被使用，创建失败
	 * 		false:  输入的pw 可以使用
	 */
	public boolean checkPwIsUser(MatrixFlowDomainFragment_T[] matrixFlowDomainFragment_T)throws ProcessingFailureException {
		boolean flag=false;
		int pwId=0;
		PwInfoService_MB pwInfoService=null;
		PwInfo pwInfo=null;
		try{
			pwInfoService=(PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			for( MatrixFlowDomainFragment_T matFlowDomain:matrixFlowDomainFragment_T){
				//pw命名规则： 速率/主键   --如： 3/13  既分割pwId 得到2个元素（取出第2个元素）
				pwId=(super.converByElaytoId(ELayerRate.PW.getValue(),super.getValueByKey(matFlowDomain.additionalInfo, super.SERVERCONNECTIONS)));		
				pwInfo = pwInfoService.selectByPwId(pwId);
				if(pwInfo!=null){
					if(pwInfo.getRelatedServiceId()>0){//此pw 已经被使用
						flag=true;
						break;
					}
				}else {
					flag=true;
					break;
				}
			}
			
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
		
		return flag;
	}
	/**
	 * 设置FlowDomain_T对象
	 * 	name要求：NameAndStringValue_T[]
	 * 		2层结构   EMS，FD “1” 
	 * @return
	 * @throws ProcessingFailureException 
	 */
	public FlowDomain_T getFlowDomain(NameAndStringValue_T[] name) throws ProcessingFailureException{
		FlowDomain_T flowDomain=null;
		try{
			flowDomain=new FlowDomain_T();			
			//名称 如下： 不可为其他
			flowDomain.name=name;
			flowDomain.fdType="";
			flowDomain.transmissionParams=new LayeredParameters_T[0];
			flowDomain.fDConnectivityState=ConnectivityState_T.CS_FULLY_CONNECTED;
			flowDomain.additionalInfo=new NameAndStringValue_T[0];
		}catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return flowDomain;
	}
	/**
	 * 设置FD 名称
	 * @return
	 */
	public NameAndStringValue_T[] getNameAndString(){
		NameAndStringValue_T[] name=new NameAndStringValue_T[2];
		name[0]=new NameAndStringValue_T("EMS",super.getOwner());
		name[1]=new NameAndStringValue_T("FlowDomain","1");
		return name;
	}
}
