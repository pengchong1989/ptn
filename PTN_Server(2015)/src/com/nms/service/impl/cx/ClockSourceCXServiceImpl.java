package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 时钟源配置/状态设备实现类
 * @author dzy
 *
 */
public class ClockSourceCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 *根据ID  封装对象
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int id) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		try {
			operationObject = new OperationObject();
			cxneObject = super.getCXNEObject(id);
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setCxNeObject(cxneObject);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
		return operationObject;
	}
	/**
	 * 新建后，需要查询设备，去得物理状态，逻辑 等属性
	 */
	public ClockSource  excutinoSelect(ClockSource clockSource)throws Exception{
		ClockSource clock=null;
		OperationObject operationObject=null;
		if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(clockSource.getSiteId())) {
			operationObject = this.getOperationObject(clockSource.getSiteId());
			super.sendAction(operationObject);					
			operationObject = super.verification(operationObject);						
			if (operationObject.isSuccess()) {	
				
				clock=		this.getClockSource(operationObject)		;		
			} 	
		}
		operationObject = null;
		return clock;
		
	}
	/**
	 * 添加
	 */
	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		ClockSource clockSource = null;
		try {
			clockSource = (ClockSource) object;
			operationObject = this.convertOperation(operationObject, clockSource, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} finally {
			operationObject = null;
			clockSource = null;
		}
		return result;
	}
	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		ClockSource clockSource = null;
		try {
			clockSource = (ClockSource) object;
			operationObject = this.convertOperation(operationObject, clockSource, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			clockSource = null;
		}
		return result;
	}
	/**
	 * 删除
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		List<ClockSource> clockSourceList = null;
		try {
			clockSourceList = objectList;
			operationObject = this.convertOperationForList(operationObject, clockSourceList, TypeAndActionUtil.ACTION_DELETE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			clockSourceList = null;
		}
		return result;
	}
	/**
	 * 同步
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		ClockSource clockSource = null;
		OperationObject operationObject = null;
		clockSource =  new ClockSource();
		clockSource.setSiteId(siteId);
		try {
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
				operationObject = this.convertOperation(operationObject, clockSource, TypeAndActionUtil.ACTION_SYNCHRO);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
//					frequencyClockManageService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
					this.getClockSourceObject(operationObject.getCxActionObjectList().get(0).getClockPortESObjectList(), siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		clockSource = null;
		return result;
	}
	
	/**
	 * 下发对象添加参数
	 * @param operationObject
	 * @param clockSourceList
	 * @param action
	 * @return
	 */
	private OperationObject convertOperation(OperationObject operationObject,
			ClockSource clockSource, String action) {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(clockSource.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG);
			cxActionObject.setClockPortESObject(this.getClockPortESObject(clockSource));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	
	/**
	 * 下发对象添加参数
	 * @param operationObject
	 * @param clockSourceList
	 * @param action
	 * @return
	 * @throws Exception 
	 */
	private OperationObject convertOperationForList(OperationObject operationObject,
			List<ClockSource> clockSourceList, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(clockSourceList.get(0).getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG);
			for(ClockSource clockSource:clockSourceList){
				cxActionObject.setClockPortESObject(this.getClockPortESObject(clockSource));
			}
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	/**
	 * 转换驱动层对象
	 * @param clockSource
	 * @return
	 * @throws Exception
	 */
	private ClockPortESObject getClockPortESObject(ClockSource clockSource) throws Exception {
		
		ClockPortESObject clockPortESObject = new ClockPortESObject();
		PortService_MB portService = null;
		try {
			
//		PortService portService = (PortService) ConstantUtil.serviceFactory.newService(Services.PORT);
//		PortInst portInst = new PortInst();
//		portInst.setPortId(clockSource.getPort());
//		portInst =  portService.select(portInst).get(0);
		portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
		clockPortESObject.setPortname(portService.getPortname(clockSource.getPort()));
		clockPortESObject.setScspri(clockSource.getSystemPriorLevel());//SCS优先级 int [0,255] 系统优先级
		clockPortESObject.setPhysicalState(clockSource.getPhysicsState());//物理状态
		clockPortESObject.setScslockout(clockSource.getScslockout());
		clockPortESObject.setEcslockout(clockSource.getEcslockout());
		clockPortESObject.setScsforce(clockSource.getScsforce());
		clockPortESObject.setEcsforce(clockSource.getEcsforce());
		clockPortESObject.setScsmanual(clockSource.getScsmanual());
		clockPortESObject.setEcsmanual(clockSource.getEcsmanual());
		clockPortESObject.setSCSLogicalState(clockSource.getLogicState());//逻辑状态
		if(0!=clockSource.getRecoverModel()){
			clockPortESObject.setRecoverModel(UiUtil.getCodeById(clockSource.getRecoverModel()).getCodeValue());
		}
		clockPortESObject.setDnugroup(clockSource.getDNUGroup());
		clockPortESObject.setEcspri(clockSource.getExportPriorLevel());//ECS优先级  int [0,255] 导出优先级
		if(0!=clockSource.getReceiveSSMValue()){
			clockPortESObject.setForcelevel(UiUtil.getCodeById(clockSource.getReceiveSSMValue()).getCodeValue());
		}
		clockPortESObject.setSsmoutputenable(clockSource.getSSMSend()==1?"true":"false");
		
		} catch (Exception e) {
		  throw e;
		}finally{
		  UiUtil.closeService_MB(portService);
		}
		return clockPortESObject;
	}
	/**
	 * 转化DB 对象
	 * @param clockPortESObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getClockSourceObject(List<ClockPortESObject> clockPortESObjectList, int siteId) throws Exception {

		if (null == clockPortESObjectList) {
			throw new Exception("clockPortESObjectList is null");
		}
		SynchroUtil SynchroUtil = new SynchroUtil();
		PortInst portInst;
		ClockSource clockSource;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<ClockPortESObject> clockPortESObjectListAction = new ArrayList<ClockPortESObject>();
			for (ClockPortESObject clockPortESObject : clockPortESObjectList) {
				if("true".equals(clockPortESObject.getIsClock().toString())){
					clockPortESObjectListAction.add(clockPortESObject);
				}
			}
			for (ClockPortESObject clockPortESObjectAction : clockPortESObjectListAction) {
				portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setPortName(clockPortESObjectAction.getPortname());
				portInst = portService.select(portInst).get(0);
				clockSource = new ClockSource();
				clockSource.setSiteId(siteId);
				//ECS  与   scs   如何分别
				clockSource.setPort(portInst.getPortId());
				clockSource.setSelectQuelityLevel(clockPortESObjectAction.getECSQL());// 选择器质量等级
				clockSource.setSystemPriorLevel(clockPortESObjectAction.getScspri());////系统时钟优先等级
				clockSource.setRecoverModel(UiUtil.getCodeByValue("systemClockRecoveryMode", clockPortESObjectAction.getRecoverModel()).getId());//1
				clockSource.setDNUGroup(clockPortESObjectAction.getDnugroup());
				clockSource.setExportPriorLevel(clockPortESObjectAction.getEcspri());
				clockSource.setReceiveSSMValue(UiUtil.getCodeByValue("receiveSSMSettingValue", clockPortESObjectAction.getForcelevel()).getId());//1 收取SSM设置值
				clockSource.setSSMSend("true".equals(clockPortESObjectAction.getSsmoutputenable())?1:0);
				clockSource.setJobState(clockPortESObjectAction.getOper());
				clockSource.setManageState(clockPortESObjectAction.getAdmin());
				clockSource.setReceiveSSMRealityValue(Integer.parseInt(clockPortESObjectAction.getSSMCode()));
				clockSource.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
				
				/*
				 * 倒换
//				 */
				
				clockSource.setScsforce(clockPortESObjectAction.getScsforce());
				clockSource.setEcsforce(clockPortESObjectAction.getEcsforce());
				clockSource.setScslockout(clockPortESObjectAction.getScslockout());
				clockSource.setEcslockout(clockPortESObjectAction.getEcslockout());
				clockSource.setScsmanual(clockPortESObjectAction.getScsmanual());
				clockSource.setEcsmanual(clockPortESObjectAction.getEcsmanual());
				
				clockSource.setPhysicsState(clockPortESObjectAction.getPhysicalState());// 物理状态
				clockSource.setLogicState(clockPortESObjectAction.getSCSLogicalState());// 逻辑状态
				SynchroUtil.clockSourceSynchro(clockSource, siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
	/**
	 *读取设备
	 * @param clockPortESObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private ClockSource getClockSource(OperationObject operationObject) throws Exception {
		if (null == operationObject) {
			throw new Exception("operationObject is null");
		}
		PortInst portInst;
		ClockPortESObject clockPortESObjectAction=null;
		ClockSource clockSource = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<ClockPortESObject> clockPortESObjectListAction = new ArrayList<ClockPortESObject>();
			for (CXActionObject cxActionObject  : operationObject.getCxActionObjectList()) {
				if(cxActionObject.getClockPortESObjectList().size()>0){
					clockPortESObjectAction=(ClockPortESObject) cxActionObject.getClockPortESObjectList().get(0);
					if("true".equals(clockPortESObjectAction.getIsClock().toString())){
						clockPortESObjectListAction.add(clockPortESObjectAction);
					}
			//	for (ClockPortESObject clockPortESObjectAction : clockPortESObjectListAction) {
					portInst = new PortInst();
					//portInst.setSiteId(siteId);
					portInst.setPortName(clockPortESObjectAction.getPortname());
					portInst = portService.select(portInst).get(0);
					clockSource = new ClockSource();
					//clockSource.setSiteId(clockPortESObjectAction);
					//ECS  与   scs   如何分别
					clockSource.setPort(portInst.getPortId());
					clockSource.setSelectQuelityLevel(clockPortESObjectAction.getECSQL());// 选择器质量等级
					clockSource.setSystemPriorLevel(clockPortESObjectAction.getScspri());////系统时钟优先等级
					clockSource.setRecoverModel(UiUtil.getCodeByValue("systemClockRecoveryMode", clockPortESObjectAction.getRecoverModel()).getId());//1
					clockSource.setDNUGroup(clockPortESObjectAction.getDnugroup());
					clockSource.setExportPriorLevel(clockPortESObjectAction.getEcspri());
					clockSource.setReceiveSSMValue(UiUtil.getCodeByValue("receiveSSMSettingValue", clockPortESObjectAction.getForcelevel()).getId());//1 收取SSM设置值
					clockSource.setSSMSend("true".equals(clockPortESObjectAction.getSsmoutputenable())?1:0);
					clockSource.setJobState(clockPortESObjectAction.getOper());
					clockSource.setManageState(clockPortESObjectAction.getAdmin());
					clockSource.setReceiveSSMRealityValue(Integer.parseInt(clockPortESObjectAction.getSSMCode()));
					clockSource.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					/*
					 * 倒换
		//			 */
					clockSource.setScsforce(clockPortESObjectAction.getScsforce());
					clockSource.setEcsforce(clockPortESObjectAction.getEcsforce());
					clockSource.setScslockout(clockPortESObjectAction.getScslockout());
					clockSource.setEcslockout(clockPortESObjectAction.getEcslockout());
					clockSource.setScsmanual(clockPortESObjectAction.getScsmanual());
					clockSource.setEcsmanual(clockPortESObjectAction.getEcsmanual());
					clockSource.setPhysicsState(clockPortESObjectAction.getPhysicalState());// 物理状态
					clockSource.setLogicState(clockPortESObjectAction.getSCSLogicalState());// 逻辑状态		
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return clockSource;
	}
}