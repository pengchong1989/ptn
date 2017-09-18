package com.nms.corba.ninterface.impl.service.proxy;

import flowDomain.FDIterator_IHolder;
import flowDomain.FDList_THolder;
import flowDomain.FlowDomain_T;
import flowDomain.FlowDomain_THolder;
import flowDomainFragment.FDFrCreateData_T;
import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FDFrModifyData_T;
import flowDomainFragment.FDFrRoute_THolder;
import flowDomainFragment.FlowDomainFragment_T;
import flowDomainFragment.FlowDomainFragment_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.StringHolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.FDFrIterator_Impl;
import com.nms.corba.ninterface.impl.service.FDIterator_Impl;
import com.nms.corba.ninterface.impl.service.tool.CorbaFlowDomainConvertTools;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.AcDispatch;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 端到端以太网业务代理类
 * 
 * @author kk
 * 
 */
public class CorbaFlowDomainProxy {

	private ICorbaSession session;

	public CorbaFlowDomainProxy(ICorbaSession userSession) {
		this.session = userSession;

	}

	/**
	 * 查询所有以太网业务
	 * 
	 * @param fdName
	 * 				名称-2层结构
	 * @param how_many
	 *            查询属相
	 * @param connectivityRateList
	 *            查询的速率，区分eline、etree、elan
	 * @param fdfrList
	 *            输出参数，查询的结果返回值
	 * @param fdfrIt
	 *            查询迭代器
	 */
	public void getAllFDFrs(NameAndStringValue_T[] fdName, int how_many, short[] connectivityRateList, FDFrList_THolder fdfrList, FDFrIterator_IHolder fdfrIt) throws ProcessingFailureException {
		// 验证
		if (how_many == 0) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "how_many is 0");
		}
		//验证输入名称
		if(!CheckParameterUtil.checkFDName(fdName)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"fdName is invalid!");
		}
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		ElineInfoService_MB elineService = null;
		List<ElineInfo> elineInfoList = new ArrayList<ElineInfo>();
		EtreeInfoService_MB etreeService = null;
		Map<Integer, List<EtreeInfo>> etreeInfoMap = new HashMap<Integer, List<EtreeInfo>>(); // key为组ID value为此组下的所有etree对象
		ElanInfoService_MB elanInfoService = null;
		Map<Integer, List<ElanInfo>> elanInfoMap = new HashMap<Integer, List<ElanInfo>>(); // key为组ID value为此组下的所有elan对象
		FDFrIterator_Impl fdfrIteratorImpl = null;
		FlowDomainFragment_T[] flowDomains = null;
		List<FlowDomainFragment_T> flowDomainList = new ArrayList<FlowDomainFragment_T>();// 存入：所有-查询，转换成的List
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			
			// 查询所有eline
			elineInfoList = elineService.select();
			// 查询所有etree
			etreeInfoMap = etreeService.select();
			// 查询所有elan
			elanInfoMap = elanInfoService.select();

			if (elineInfoList.size() > 0) {
				flowDomains = new FlowDomainFragment_T[elineInfoList.size()];
				flowDomainList.addAll(corbaFlowDomainConvertTools.convertElineToFDFr(elineInfoList, flowDomains));
			}
			if (etreeInfoMap.size() > 0) {
				flowDomains = new FlowDomainFragment_T[etreeInfoMap.size()];
				flowDomainList.addAll(corbaFlowDomainConvertTools.convertEtreeToFDFr(etreeInfoMap, flowDomains));
			}
			if (elanInfoMap.size() > 0) {
				flowDomains = new FlowDomainFragment_T[elanInfoMap.size()];
				flowDomainList.addAll(corbaFlowDomainConvertTools.convertElanToFDFr(elanInfoMap, flowDomains));
			}
			// 结果集的数量等于eline+etree+elan
			// fdfrList.value = new FlowDomainFragment_T[elineInfoList.size() + etreeInfoMap.keySet().size() + elanInfoMap.keySet().size()];
			fdfrList.value = flowDomainList.toArray(new FlowDomainFragment_T[flowDomainList.size()]);
			// 设置迭代器
			fdfrIteratorImpl = new FDFrIterator_Impl(this.session);
			fdfrIteratorImpl.setIterator(fdfrIt, fdfrList, how_many);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getAllFDFrs ex.!");
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elanInfoService);
			elineInfoList = null;
			etreeInfoMap = null;
			elanInfoMap = null;
			fdfrIteratorImpl = null;
		}
	}

	/**
	 * 根据主键查询对应的以太网业务
	 * 
	 * @param fdfrName
	 *            业务命名
	 * @param fdfr
	 *            查询后返回的FDFR对象
	 * @throws ProcessingFailureException
	 */
	public void getFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdName is invalid!");
			}
			this.getServiceList(fdfrName, serviceInfoList);
			if(serviceInfoList.size()>0){
				fdfr.value = corbaFlowDomainConvertTools.convertFDFr(serviceInfoList.get(0));
			}else{
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
			
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getFDFr ex.");
		} finally {
			serviceInfoList = null;
		}

	}

	/**
	 * 根据主键查询一条业务的完整路由，主要是etree和elan
	 * 
	 * @param fdfrName
	 *            业务命名
	 * @param route
	 *            查询后的所有详细路由
	 * @throws ProcessingFailureException
	 */
	public void getFDFrRoute(NameAndStringValue_T[] fdfrName, FDFrRoute_THolder route) throws ProcessingFailureException {
		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
			this.getServiceList(fdfrName, serviceInfoList);

			route.value = new MatrixFlowDomainFragment_T[serviceInfoList.size()];
			for (int i = 0; i < serviceInfoList.size(); i++) {
				route.value[i] = corbaFlowDomainConvertTools.convertMFDFr(serviceInfoList.get(i));
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getFDFrRoute ex.!");
		} finally {
			serviceInfoList = null;
		}
	}

	/**
	 * 修改一条以太网业务的名称 userlabel
	 * 
	 * @param fdfrName
	 *            业务命名
	 * @param fdfrModifyData
	 *            业务要修改的对象
	 * @param newFDFr
	 *            修改后返回的业务对象
	 * @param errorReason
	 *            修改失败原因
	 * @throws ProcessingFailureException
	 */
	public void modifyFDFr(NameAndStringValue_T[] fdfrName, FDFrModifyData_T fdfrModifyData, FlowDomainFragment_THolder newFDFr, StringHolder errorReason) throws ProcessingFailureException {
		if (!UiUtil.isNull(fdfrModifyData.userLabel)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userLabel is invalid!");
		}
		List<ServiceInfo> serviceInfoList = null;
		DispatchInterface dispatchInterface = null;
		String result = "";// 操作结果
		VerifyNameUtil verifyNameUtil = null;// 验证名称是否重复
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
			serviceInfoList = new ArrayList<ServiceInfo>();
			verifyNameUtil = new VerifyNameUtil();
			dispatchInterface = this.getServiceList(fdfrName, serviceInfoList);
			if (serviceInfoList != null && serviceInfoList.size() > 0) {// 判断：根据北向接口提供数据在数据库中：是否查到相关信息
				if (!verifyNameUtil.verifyName(serviceInfoList.get(0).getServiceType(), fdfrModifyData.userLabel, serviceInfoList.get(0).getName())) {
					// 验证通过，修改名称
					for (ServiceInfo serviceInfo : serviceInfoList) {
						serviceInfo.setName(fdfrModifyData.userLabel);
					}
					result = dispatchInterface.excuteUpdate(serviceInfoList);
					newFDFr.value = corbaFlowDomainConvertTools.convertFDFr(serviceInfoList.get(0));
				} else {
					// 验证未通过： 名称已经存在
					result = ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
					newFDFr.value = corbaFlowDomainConvertTools.setDefauoltFdfr(new FlowDomainFragment_T(), true);
				}
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
			errorReason.value = corbaFlowDomainConvertTools.outErrorMessage(result);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : modifyFDFr ex.");
		} finally {
			serviceInfoList = null;
			dispatchInterface = null;
			result = null;
		}
	}

	/**
	 * 创建一条以太网业务
	 * 
	 * @param createData
	 *            要创建的业务信息对象
	 * @param mfdfrs
	 *            业务的详细路由信息
	 * @param theFDFr
	 *            创建后返回的业务对象
	 * @param errorReason
	 *            创建失败原因
	 * @throws ProcessingFailureException
	 */
	public void createAndActivateFDFr(FDFrCreateData_T createData, MatrixFlowDomainFragment_T[] mfdfrs, FlowDomainFragment_THolder newFDFr, StringHolder errorReason) throws ProcessingFailureException {
		if (!UiUtil.isNull(createData.fdfrType)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrType is invalid!");
		}
		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		DispatchInterface dispatchInterface = null;
		Map<Integer, AcPortInfo> acPortInfoMap = null;
		String result = "";
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		VerifyNameUtil verifyNameUtil = null;// 验证名称是否重复
		try {
			verifyNameUtil = new VerifyNameUtil();
			// 判断输入参数，创建名称是否已经存在
  			if (!verifyNameUtil.verifyName(EServiceType.valueOf(createData.fdfrType).getValue(), createData.userLabel, null)) {
				// 判断输入的pw是否可用
				if (corbaFlowDomainConvertTools.checkPwIsUser(mfdfrs)) {
					// 有已经使用的pw 创建失败，返回错误信息
					newFDFr.value = corbaFlowDomainConvertTools.setDefauoltFdfr(new FlowDomainFragment_T(), true);
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_USE);
				} else { // 验证条件通过： 开始创建
					dispatchInterface = corbaFlowDomainConvertTools.convertServiceInfoList(createData, mfdfrs, serviceInfoList, this.session);
					// 把serviceInfoList中要创建的AC对象找到。
					acPortInfoMap = this.getCreateAc(serviceInfoList);
					// 批量创建AC对象。 如果创建AC时没有成功,直接返回
					result = this.createAcBath(acPortInfoMap);
					if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
						// 创建AC 失败： 返回错误信息result,并且为 out参数设置默认值
						newFDFr.value = corbaFlowDomainConvertTools.setDefauoltFdfr(new FlowDomainFragment_T(), true);
					} else {
						// AC创建成功后，把serviceinfo中的aAcId和zAcId赋值
						this.setAcId(acPortInfoMap, serviceInfoList);
						// 创建serviceinfo对象
						if (createData.fdfrType.equals(EServiceType.ELINE.toString())) {
							result = dispatchInterface.excuteInsert(serviceInfoList.get(0));
						} else {
							result = dispatchInterface.excuteInsert(serviceInfoList);
						}
						newFDFr.value = corbaFlowDomainConvertTools.convertFDFr(serviceInfoList.get(0));
					}
				}
			} else {// 验证未通过：名称已经存在，创建失败
				result = ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
				newFDFr.value = corbaFlowDomainConvertTools.setDefauoltFdfr(new FlowDomainFragment_T(), true);
			}
			errorReason.value = corbaFlowDomainConvertTools.outErrorMessage(result);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error: createAndActivateFDFr ex.");
		} finally {
			serviceInfoList = null;
			dispatchInterface = null;
			acPortInfoMap = null;
			result = null;
		}
	}

	/**
	 * 删除一条以太网业务
	 * 
	 * @param fdfrName
	 *            要删除的业务命名
	 * @param errorReason
	 *            删除失败原因
	 * @throws ProcessingFailureException
	 */
	public void deactivateAndDeleteFDFr(NameAndStringValue_T[] fdfrName, StringHolder errorReason) throws ProcessingFailureException {
		List<ServiceInfo> serviceInfoList = null;
		DispatchInterface dispatchInterface = null;
		String result = "";// 操作结果
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
			serviceInfoList = new ArrayList<ServiceInfo>();
			dispatchInterface = this.getServiceList(fdfrName, serviceInfoList);
			result = dispatchInterface.excuteDelete(serviceInfoList);

			// 如果执行结果为成功，那么删除AC对象
			if (ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)) {
				this.deleteAcBach(serviceInfoList);
			}

			errorReason.value = corbaFlowDomainConvertTools.outErrorMessage(result);
		}catch (ProcessingFailureException e) { 
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error: deactivateAndDeleteFDFr ex.");
		} finally {
			serviceInfoList = null;
			dispatchInterface = null;
			result = null;
		}
	}

	/**
	 * 激活一个以太网业务
	 * 
	 * @param fdfrName
	 *            要激活的业务命名
	 * @param fdfr
	 *            激活后的业务对象
	 * @throws ProcessingFailureException
	 */
	public void activateFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		List<ServiceInfo> serviceInfoList = null;
		DispatchInterface dispatchInterface = null;
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
			serviceInfoList = new ArrayList<ServiceInfo>();
			dispatchInterface = this.getServiceList(fdfrName, serviceInfoList);
			if (serviceInfoList != null && serviceInfoList.size() > 0) {// 判断：根据北向接口提供数据是否找到相关信息
				for (ServiceInfo serviceInfo : serviceInfoList) {
					serviceInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());// 修改 激活状态为激活
				}
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
			String result=dispatchInterface.excuteUpdate(serviceInfoList);
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				fdfr.value = corbaFlowDomainConvertTools.convertFDFr(serviceInfoList.get(0));
			}else{
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : ServerConnections is invalid!");
			}
			
		}catch (ProcessingFailureException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error:activateFDFr ex.");
		} finally {
			serviceInfoList = null;
			dispatchInterface = null;
		}
	}

	/**
	 * 去激活一个以太网业务
	 * 
	 * @param fdfrName
	 *            去激活的业务命名
	 * @param fdfr
	 *            去激活后的业务对象
	 * @throws ProcessingFailureException
	 */
	public void deactivateFDFr(NameAndStringValue_T[] fdfrName, FlowDomainFragment_THolder fdfr) throws ProcessingFailureException {
		List<ServiceInfo> serviceInfoList = new ArrayList<ServiceInfo>();
		DispatchInterface dispatchInterface = null;
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
			dispatchInterface = this.getServiceList(fdfrName, serviceInfoList);
			if (serviceInfoList != null && serviceInfoList.size() > 0) {// 判断：更加北向接口提供数据是否找到相关信息
				for (ServiceInfo serviceInfo : serviceInfoList) {
					serviceInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());// 修改 激活状态为不激活
				}
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
			dispatchInterface.excuteUpdate(serviceInfoList);
			fdfr.value = corbaFlowDomainConvertTools.convertFDFr(serviceInfoList.get(0));
		}catch(ProcessingFailureException e){ 
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error:deactivateFDFr ex.");
		} finally {
			serviceInfoList = null;
			dispatchInterface = null;
		}
	}

	/**
	 * 根据corba传入的名字，从数据库查询出业务。
	 * 
	 * @param fdfrName
	 *            corba的命名对象
	 * @param serviceInfos
	 *            出参 返回的service集合
	 * @return 具体的调度类实现
	 * @throws ProcessingFailureException
	 */
	private DispatchInterface getServiceList(NameAndStringValue_T[] fdfrName, List<ServiceInfo> serviceInfos) throws ProcessingFailureException {
		ElineInfoService_MB elineService = null;
		ElanInfoService_MB elanService = null;
		EtreeInfoService_MB etreeService = null;
		ElineInfo elineinfo = null;
		DispatchInterface dispatchInterface = null;
		int serviceInfoId=0;
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkFlowDomainName(fdfrName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}			
			serviceInfoId = Integer.parseInt(fdfrName[2].value);
			//获取业务类型
			int serviceType=this.getServiceType(serviceInfoId);
			if (serviceType == EServiceType.ELINE.getValue()) {
				elineinfo = new ElineInfo();
				elineinfo.setId(serviceInfoId);
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				serviceInfos.addAll(elineService.selectByCondition(elineinfo));
				dispatchInterface = new ElineDispatch();
			} else if (serviceType == EServiceType.ETREE.getValue()) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				serviceInfos.addAll(etreeService.selectByServiceId(serviceInfoId));
				dispatchInterface = new EtreeDispatch();
			} else if (serviceType == EServiceType.ELAN.getValue()) {
				elanService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				serviceInfos.addAll(elanService.selectByServiceId(serviceInfoId));
				dispatchInterface = new ElanDispatch();
			}else{
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdfrName is invalid!");
			}
		}catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getServiceList ex.");
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elanService);
			elineinfo = null;
		}
		return dispatchInterface;
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
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally{
			UiUtil.closeService_MB(serviceInfoService);
		}
	}

	/**
	 * 从serviceInfoList集合中获取所有要创建的AC对象
	 * 
	 * @param serviceInfoList
	 * @return 要创建的AC对象。 key为网元主键 value为AC对象
	 * @throws ProcessingFailureException
	 */
	public Map<Integer, AcPortInfo> getCreateAc(List<ServiceInfo> serviceInfoList) throws ProcessingFailureException {
		Map<Integer, AcPortInfo> acInfoMap = null;
		try {
			acInfoMap = new HashMap<Integer, AcPortInfo>();

			for (ServiceInfo serviceInfo : serviceInfoList) {
				if (null == acInfoMap.get(serviceInfo.getaSiteId()) && null != serviceInfo.getCreateAc_a()) {
					acInfoMap.put(serviceInfo.getaSiteId(), serviceInfo.getCreateAc_a());
				}

				if (null == acInfoMap.get(serviceInfo.getzSiteId()) && null != serviceInfo.getCreateAc_z()) {
					acInfoMap.put(serviceInfo.getzSiteId(), serviceInfo.getCreateAc_z());
				}
			}

	
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return acInfoMap;
	}

	/**
	 * 批量创建AC
	 * 
	 * @param acPortInfoMap
	 *            要创建的AC集合
	 * @throws ProcessingFailureException
	 */
	public String createAcBath(Map<Integer, AcPortInfo> acPortInfoMap) throws ProcessingFailureException {
		AcDispatch acDispatch = null;
		String result = null;
		List<AcPortInfo> acportInfoListSuccess = null; // 创建成功的AC端口集合，回滚用
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		try {
			acDispatch = new AcDispatch();
			acportInfoListSuccess = new ArrayList<AcPortInfo>();
			// 遍历MAP创建AC
			for (int siteId : acPortInfoMap.keySet()) {
				result = acDispatch.excuteInsert(acPortInfoMap.get(siteId));
				// 如果AC创建成功，则把成功信息放入到LIST中 否则结束循环
				if (result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
					acportInfoListSuccess.add(acPortInfoMap.get(siteId));
				} else {
					result = corbaFlowDomainConvertTools.outErrorMessage(result);
					break;
				}
			}

			// 循环结束后，验证结果是否成功， 如果不成功，那么做回滚操作，就是把已经成功的删除掉
			if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
				if (acportInfoListSuccess.size() > 0) {
					acDispatch.excuteDelete(acportInfoListSuccess);
				}
				// throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}
		}catch(ProcessingFailureException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			acDispatch = null;
			acportInfoListSuccess = null;
		}
		return result;
	}

	/**
	 * 给serviceInfo中的a、z端的AC外键赋值
	 * 
	 * @param acPortInfoMap
	 * @param serviceInfoList
	 * @throws ProcessingFailureException
	 */
	public void setAcId(Map<Integer, AcPortInfo> acPortInfoMap, List<ServiceInfo> serviceInfoList) throws ProcessingFailureException {
		try {
			if(!UiUtil.isNull(serviceInfoList)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
			for (ServiceInfo serviceInfo : serviceInfoList) {
				if (serviceInfo.getaSiteId() > 0) {
					serviceInfo.setaAcId(acPortInfoMap.get(serviceInfo.getaSiteId()).getId());
				}
				if (serviceInfo.getzSiteId() > 0) {
					serviceInfo.setzAcId(acPortInfoMap.get(serviceInfo.getzSiteId()).getId());
				}

				if (serviceInfo instanceof EtreeInfo) {
					((EtreeInfo) serviceInfo).setRootSite(serviceInfo.getaSiteId());
					((EtreeInfo) serviceInfo).setBranchSite(serviceInfo.getzSiteId());
				}
			}

		}catch (ProcessingFailureException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}

	}

	/**
	 * 批量删除AC对象，在删除业务时调用
	 * 
	 * @param serviceInfoList
	 *            要删除的业务 删除此业务的AZ端AC
	 * @throws ProcessingFailureException
	 */
	public void deleteAcBach(List<ServiceInfo> serviceInfoList) throws ProcessingFailureException {
		List<Integer> acIdList = null;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfoList = null;
		AcDispatch acDispatch = null;
		try {
			acIdList = new ArrayList<Integer>();

			// 把A、Z两端的AC主键添加到集合中
			for (ServiceInfo serviceInfo : serviceInfoList) {
				if (serviceInfo.getaAcId() > 0 && !acIdList.contains(serviceInfo.getaAcId())) {
					acIdList.add(serviceInfo.getaAcId());
				}
				if (serviceInfo.getzAcId() > 0 && !acIdList.contains(serviceInfo.getzAcId())) {
					acIdList.add(serviceInfo.getzAcId());
				}
			}

			// 通过ID集合查询出所有AC对象
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			if(!UiUtil.isNull(acIdList)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error :delete fail! ");
			}
			acportInfoList = acInfoService.select(acIdList);

			// 删除AC对象
			if (null != acportInfoList && acportInfoList.size() > 0) {
				acDispatch = new AcDispatch();
				acDispatch.excuteDelete(acportInfoList);
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}

	/**
	 * 补充方法，未知作用
	 * 
	 * @param how_many
	 * @param flowDomains
	 * @param fdIt
	 * @throws ProcessingFailureException
	 */
	public void getAllFlowDomains(int how_many, FDList_THolder flowDomains, FDIterator_IHolder fdIt) throws ProcessingFailureException {
		if (how_many == 0) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "how_many is 0");
		}
		FDIterator_Impl fdIteratorImpl = null;
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = null;
		try {
			corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
			// FDFrIterator_I
			flowDomains.value = new FlowDomain_T[2];
			flowDomains.value[0] = corbaFlowDomainConvertTools.getFlowDomain(corbaFlowDomainConvertTools.getNameAndString());
			flowDomains.value[1] = corbaFlowDomainConvertTools.getFlowDomain(corbaFlowDomainConvertTools.getNameAndString());
			// 设置迭代器
			fdIteratorImpl = new FDIterator_Impl(this.session);
			fdIteratorImpl.setIterator(fdIt, flowDomains, how_many);
		}catch(ProcessingFailureException e){
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getAllFlowDomains ex.!");
		}
	}

	/**
	 * 补充方法，未知作用
	 * 
	 * @param fdName
	 * @param flowDomain
	 * @throws ProcessingFailureException
	 */
	public void getFlowDomain(NameAndStringValue_T[] fdName, FlowDomain_THolder flowDomain) throws ProcessingFailureException {
		CorbaFlowDomainConvertTools corbaFlowDomainConvertTools = new CorbaFlowDomainConvertTools();
		// 验证输入名称
		if (!CheckParameterUtil.checkFlowDomainName(fdName)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "fdName is invalid!");
		}
		flowDomain.value = corbaFlowDomainConvertTools.getFlowDomain(fdName);
	}

	// public static void main1(){
	// CorbaFlowDomainProxy corbaFlowDomainProxy=new CorbaFlowDomainProxy(null);
	// try {
	// NameAndStringValue_T[] nameAndStringValues = new NameAndStringValue_T[3];
	// NameAndStringValue_T nameAndStringValue_T = new NameAndStringValue_T();
	// nameAndStringValue_T.name = "1";
	// nameAndStringValue_T.value = "1";
	// nameAndStringValues[0] = nameAndStringValue_T;
	//
	// nameAndStringValue_T = new NameAndStringValue_T();
	// nameAndStringValue_T.name = "2";
	// nameAndStringValue_T.value = "2";
	// nameAndStringValues[1] = nameAndStringValue_T;
	//
	// nameAndStringValue_T = new NameAndStringValue_T();
	// nameAndStringValue_T.name = "3";
	// nameAndStringValue_T.value = "9/65";
	// nameAndStringValues[2] = nameAndStringValue_T;
	//
	// FlowDomainFragment_THolder flowDomainFragment_THolder=new FlowDomainFragment_THolder();
	// // corbaFlowDomainProxy.activateFDFr(nameAndStringValues, flowDomainFragment_THolder);
	// corbaFlowDomainProxy.deactivateAndDeleteFDFr(nameAndStringValues, new StringHolder());
	// } catch (ProcessingFailureException e) {
	// ExceptionManage.dispose(e,this.getClass());
	// }
	// }
}
