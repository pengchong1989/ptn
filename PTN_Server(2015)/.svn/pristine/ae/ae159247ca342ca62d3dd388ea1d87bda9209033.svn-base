package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.AllConfigInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.GlobalObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.AllConfigService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class AllConfigWHServiceImpl extends WHOperationBase implements
		OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null; 
	}

	@Override
	public String excutionInsert(Object object) throws Exception {  
		AllConfigInfo allConfigInfo = null;
		AllConfigService_MB allConfigService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		GlobalObject globalObject = null;
		List<GlobalObject> globalObjectList = null;
		try {

			allConfigService = (AllConfigService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALLCONFIG);
			allConfigInfo = (AllConfigInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(allConfigInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(allConfigInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备s
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(allConfigInfo.getSiteId());
				globalObject = this.getAllConfigObject(allConfigInfo.getSiteId(), allConfigInfo);
				globalObjectList = new ArrayList<GlobalObject>();
				globalObjectList.add(globalObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, globalObjectList, PtnServiceEnum.ALLCONFIG);//下发全局配置块
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					allConfigService.save(allConfigInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				allConfigService.save(allConfigInfo);
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(allConfigService);
		}
		return null;

	}

	@Override
	public String excutionUpdate(Object object) throws Exception {  
		AllConfigInfo allConfigInfo = null;
		AllConfigService_MB allConfigService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		GlobalObject globalObject = null;
		List<GlobalObject> globalObjectList = null;
		try {

			allConfigService = (AllConfigService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALLCONFIG);
			allConfigInfo = (AllConfigInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(allConfigInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(allConfigInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(allConfigInfo.getSiteId());
				globalObject = this.getAllConfigObject(allConfigInfo.getSiteId(), allConfigInfo);
				globalObjectList = new ArrayList<GlobalObject>();
				globalObjectList.add(globalObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, globalObjectList, PtnServiceEnum.ALLCONFIG);//下发全局配置块
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					allConfigService.update(allConfigInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				allConfigService.update(allConfigInfo);
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(allConfigService);
		}
		return null;

	}

	/**
	 * 数据库信息向驱动信息转化
	 * @param siteId
	 * @param allConfigInfo
	 * @return
	 */
	private GlobalObject getAllConfigObject(int siteId,AllConfigInfo allConfigInfo) {
		GlobalObject globalObject = new GlobalObject();
		globalObject.setAddressAgeSwitch(allConfigInfo.getAddressAgeSwitch());
		globalObject.setApsRecoverTime(allConfigInfo.getApsRecoverTime());
		globalObject.setChannelType(allConfigInfo.getChannelType());
		globalObject.setFDIBIT0(allConfigInfo.getFdiBIT0()+"");
		globalObject.setFDIB1IT3(getFDI(allConfigInfo.getFdiB1IT3()+""));
		globalObject.setTmcFDIBIT0(allConfigInfo.getTmcfdiBIT0()+"");
		globalObject.setTmcFDIB1IT3(getFDI(allConfigInfo.getTmcfdiB1IT3()+""));
		globalObject.setMACAddressAgeDate(allConfigInfo.getMacAddressAgeDate());
		globalObject.setMirrorByPort(allConfigInfo.getMirrorByPort());
		globalObject.setMirrorModel(allConfigInfo.getMirrorModel());
		globalObject.setMirrorPort(allConfigInfo.getMirrorPort());
		globalObject.setMPLSTPControl(allConfigInfo.getMplsTPControl());
		globalObject.setSingleMACAddress(CoderUtils.transformMac(allConfigInfo.getSingleMACAddress()));
		globalObject.setThrowWrapDateGap(allConfigInfo.getThrowWrapDateGap());
		globalObject.setApsModel(allConfigInfo.getApsModel());
		globalObject.setRoundEnable(allConfigInfo.getRoundEnable());
		globalObject.setVlanMAC(allConfigInfo.getVlanMAC());
		globalObject.setVlanValue(allConfigInfo.getVlanValue());
		globalObject.setMacNumber(allConfigInfo.getMacNumber());
		globalObject.setLacp(allConfigInfo.getLacp());
		globalObject.setEquipmentPriority(allConfigInfo.getEquipmentPriority());
		globalObject.setDhcpModel(allConfigInfo.getDhcpModel());
		globalObject.setLoopCheck(allConfigInfo.getLoopCheck());
		globalObject.setSsmModel(allConfigInfo.getSsmModel());
		globalObject.setTwoLayer(allConfigInfo.getTwoLayer());
		globalObject.setAlarmModel(allConfigInfo.getAlarmModel());
		globalObject.setAlarmPort(allConfigInfo.getAlarmPort());
		globalObject.setLoopAvoid(allConfigInfo.getLoopAvoid());
		return globalObject;
	}

	private String getFDI(String fdiMel){
		fdiMel = Integer.toBinaryString(Integer.parseInt(fdiMel));
		StringBuffer sb = new StringBuffer();
		if(fdiMel.length()<3){			
			for (int i = fdiMel.length(); i < 3; i++) {
				sb.append("0");
			}		
		}
		sb.append(fdiMel);
		return sb.toString();
		
	}
	 
	/**
	 * 与设备同步全局配置块信息
	 * @author guoqc
	 * @param siteId
	 * @return
	 * @exception
	 */
	public Object synchro(int siteId)throws Exception {
		OperationObject operaObj = new OperationObject();
		
		try {
			operaObj = super.getSynchroOperationObject(siteId, "allConfigSynchro");
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					if(actionObject.getGlobalObject() != null){
				    	this.synchro_db(actionObject.getGlobalObject(),siteId);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}

	/**
	 * 与数据库进行同步
	 * @param globalObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(GlobalObject globalObject, int siteId)throws Exception {
		 AllConfigInfo allConfigInfo = null;
		 AllConfigService_MB allConfigService =null;
		 try {			 
			allConfigInfo = this.getAllConfigInfo(globalObject,siteId);
			allConfigService = (AllConfigService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALLCONFIG);	
			//同步之前删除数据库数据
			SynchroUtil synchroUtil = new SynchroUtil();
			allConfigService.delete(siteId);
			synchroUtil.allConfigSynchro(allConfigInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(allConfigService);
		}
		
	}
	
	/**
	 * 
	 * @param globalObject
	 * @param siteId
	 * @return allConfigInfo
	 * @throws Exception
	 */
	private AllConfigInfo getAllConfigInfo(GlobalObject globalObject, int siteId)throws Exception {
		AllConfigInfo allConfigInfo = new AllConfigInfo();
		try {
			allConfigInfo.setSingleMACAddress(globalObject.getSingleMACAddress());
			allConfigInfo.setAddressAgeSwitch(globalObject.getAddressStudyModel());
			allConfigInfo.setAddressAgeSwitch(globalObject.getAddressAgeSwitch());
			allConfigInfo.setMacAddressAgeDate(globalObject.getMACAddressAgeDate());
			allConfigInfo.setThrowWrapDateGap(globalObject.getThrowWrapDateGap());
			allConfigInfo.setFdiBIT0(Integer.parseInt(globalObject.getFDIBIT0()));
			allConfigInfo.setFdiB1IT3(Integer.parseInt(globalObject.getFDIB1IT3()));
			allConfigInfo.setTmcfdiBIT0(Integer.parseInt(globalObject.getTmcFDIBIT0()));
			allConfigInfo.setTmcfdiB1IT3(Integer.parseInt(globalObject.getTmcFDIB1IT3()));
			allConfigInfo.setApsRecoverTime(globalObject.getApsRecoverTime());
			allConfigInfo.setApsModel(globalObject.getApsModel());
			allConfigInfo.setLacp(globalObject.getLacp());
			allConfigInfo.setEquipmentPriority(globalObject.getEquipmentPriority());
			allConfigInfo.setDhcpModel(globalObject.getDhcpModel());
			allConfigInfo.setLoopCheck(globalObject.getLoopCheck());
			allConfigInfo.setSsmModel(globalObject.getSsmModel());
			allConfigInfo.setTwoLayer(globalObject.getTwoLayer());
			allConfigInfo.setMirrorModel(globalObject.getMirrorModel());
			allConfigInfo.setMirrorByPort(globalObject.getMirrorByPort());
			allConfigInfo.setMirrorPort(globalObject.getMirrorPort());
			allConfigInfo.setMplsTPControl(globalObject.getMPLSTPControl());
			allConfigInfo.setChannelType(globalObject.getChannelType());
			allConfigInfo.setRoundEnable(globalObject.getRoundEnable());
			allConfigInfo.setVlanMAC(globalObject.getVlanMAC());
			allConfigInfo.setMacNumber(globalObject.getMacNumber());
			allConfigInfo.setAlarmModel(globalObject.getAlarmModel());
			allConfigInfo.setAlarmPort(globalObject.getAlarmPort());
			allConfigInfo.setLoopAvoid(globalObject.getLoopAvoid());
			allConfigInfo.setSiteId(siteId);
			CoderUtils.copy(globalObject, allConfigInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return allConfigInfo;
	}
}
