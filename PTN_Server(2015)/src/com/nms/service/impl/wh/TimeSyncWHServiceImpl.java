package com.nms.service.impl.wh;


import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TimeSyncObject;
import com.nms.model.ptn.TimeSyncService_MB;
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

public class TimeSyncWHServiceImpl extends WHOperationBase implements OperationServiceI {


	@Override
	public String excutionInsert(Object object) throws Exception {
		TimeSyncInfo timeSyncInfo = null;
		TimeSyncService_MB timeSyncService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		TimeSyncObject timeSyncObject = null;
		List<TimeSyncObject> timeSyncObjectList = null;
		try {

			timeSyncService = (TimeSyncService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TIME_SYNC_MANGE);
			timeSyncInfo = (TimeSyncInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(timeSyncInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(timeSyncInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备s
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(timeSyncInfo.getSiteId());
				timeSyncObject = this.getTimeSynObject(timeSyncInfo.getSiteId(), timeSyncInfo);
				timeSyncObjectList = new ArrayList<TimeSyncObject>();
				timeSyncObjectList.add(timeSyncObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, timeSyncObjectList, PtnServiceEnum.TIMESYNC);//下发全局配置块
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					timeSyncService.save(timeSyncInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				timeSyncService.save(timeSyncInfo);				
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(timeSyncService);
		}
		return null;
	}
	
	private TimeSyncObject getTimeSynObject(int siteId, TimeSyncInfo timesync)
	{
		TimeSyncObject timesyncObj = new TimeSyncObject();
		timesyncObj.setClockModel(timesync.getClockModel());
		timesyncObj.setInCompensation(timesync.getInCompensation());
		timesyncObj.setInDelay(timesync.getInDelay());
		timesyncObj.setSynCicle(timesync.getSynCicle());
		timesyncObj.setNoteCicle(timesync.getNoteCicle());
		timesyncObj.setTimeInfoIt(timesync.getTimeInfoIt());
		timesyncObj.setSyncFreq(timesync.getSyncFreq());
		timesyncObj.setDelayFreq(timesync.getDelayFreq());
		timesyncObj.setAnnounceFreq(timesync.getAnnounceFreq());
		timesyncObj.setDomainNumbe(timesync.getDomainNumbe());
		timesyncObj.setDelayOver(timesync.getDelayOver());
		timesyncObj.setAnnounceOver(timesync.getAnnounceOver());
		timesyncObj.setOutCompensation(timesync.getOutCompensation());
		timesyncObj.setOutDelay(timesync.getOutDelay());	
		timesyncObj.setPtpModel(timesync.getPtpModel());
		timesyncObj.setPtpNum(timesync.getPtpNum());
		timesyncObj.setSiteId(timesync.getSiteId());
		timesyncObj.setSlaveOnlyModel(timesync.getSlaveOnlyModel());
		timesyncObj.setSrcclockaccuray(timesync.getSrcclockaccuray());		
		timesyncObj.setSrcclockId1(timesync.getSrcclockId1());
		timesyncObj.setSrcclockId2(timesync.getSrcclockId2());
		timesyncObj.setSrcclockId3(timesync.getSrcclockId3());
		timesyncObj.setSrcclockId(timesync.getSrcclockId());
		timesyncObj.setSrcclockLevel(timesync.getSrcclockLevel());
		timesyncObj.setSrcclockModel(timesync.getSrcclockModel());
		timesyncObj.setSrcclockpri1(timesync.getSrcclockpri1());
		timesyncObj.setSrcclockpri2(timesync.getSrcclockpri2());
		timesyncObj.setSrcclocktype(timesync.getSrcclocktype());
		timesyncObj.setPtpPort(timesync.getPtpPortlist());
		return timesyncObj;
	}


	public String excutionUpdates(List<TimeSyncInfo> timesyns,Object object) throws Exception {
		TimeSyncInfo timeSyncInfo = null;
		TimeSyncService_MB timeSyncService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		TimeSyncObject timeSyncObject = null;
		List<TimeSyncObject> timeSyncObjectList = null;
		try {

			timeSyncService = (TimeSyncService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TIME_SYNC_MANGE);
			timeSyncInfo = (TimeSyncInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(timeSyncInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(timeSyncInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备s
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(timeSyncInfo.getSiteId());
				timeSyncObject = this.getTimeSynObject(timeSyncInfo.getSiteId(), timeSyncInfo);
				timeSyncObjectList = new ArrayList<TimeSyncObject>();
				timeSyncObjectList.add(timeSyncObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, timeSyncObjectList, PtnServiceEnum.TIMESYNC);//下发全局配置块
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					timeSyncService.update(timeSyncInfo,timesyns);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				timeSyncService.update(timeSyncInfo,timesyns);
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(timeSyncService);
		}
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		TimeSyncService_MB timeSyncService = null;
		try {
			operationObject = super.getSynchroOperationObject(siteId, "timeSynchro");
			super.sendAction(operationObject);//下发数据
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){ 
				/*成功，则与数据库进行同步*/					
				timeSyncService = (TimeSyncService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TIME_SYNC_MANGE);	
				timeSyncService.deleteBySiteId(siteId);
				for(ActionObject actionObject : operationObjectResult.getActionObjectList()){
					this.synchro_db(actionObject.getTimesyncobject(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(timeSyncService);
		}
		return ResultString.CONFIG_SUCCESS;
	}
	/**
	 * 与数据库进行同步
	 * @param globalObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(TimeSyncObject timesyncObject, int siteId)throws Exception {
		TimeSyncInfo timeSyncInfo = null;
		 try {
		    timeSyncInfo = this.getTimeSyncInfo(timesyncObject,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();			
			synchroUtil.timeSyncInfoSynchro(timeSyncInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			timeSyncInfo = null;
		}
		
	}
	
	
	
	/**
	 * 
	 * @param timeSyncObject
	 * @param siteId
	 * @return allConfigInfo
	 * @throws Exception
	 */
	private TimeSyncInfo getTimeSyncInfo(TimeSyncObject timeSyncObject, int siteId)throws Exception {
		TimeSyncInfo timeSyncInfo = null;
		PtpPortinfo  ptpPortinfo  = null;
		try {
			timeSyncInfo=new TimeSyncInfo();
			timeSyncInfo.setPtpModel(timeSyncObject.getPtpModel());
			timeSyncInfo.setClockModel(timeSyncObject.getClockModel());
			timeSyncInfo.setSrcclockModel(timeSyncObject.getSrcclockModel());
			timeSyncInfo.setSrcclockId(timeSyncObject.getSrcclockId());
			timeSyncInfo.setSrcclocktype(timeSyncObject.getSrcclocktype());
			String srcclockId1=timeSyncObject.getSrcclockId1();
			String srcclockId2=timeSyncObject.getSrcclockId2();
			String srcclockId3=timeSyncObject.getSrcclockId3();
			if(Integer.parseInt(srcclockId3,16)==0 && Integer.parseInt(srcclockId2,16)==0){
				timeSyncInfo.setSrcclockId(srcclockId1);			     					      
			}			
			if(Integer.parseInt(srcclockId3,16)==0 && Integer.parseInt(srcclockId2,16)!=0){
				srcclockId2=srcclockId2+srcclockId1;
				timeSyncInfo.setSrcclockId(srcclockId2);	
				}
			if(Integer.parseInt(srcclockId3,16)!=0 ){
				srcclockId3=srcclockId3+srcclockId2+srcclockId1;
				timeSyncInfo.setSrcclockId(srcclockId3);
				}
			timeSyncInfo.setSrcclockId1(timeSyncObject.getSrcclockId1());
			timeSyncInfo.setSrcclockId2(timeSyncObject.getSrcclockId2());
			timeSyncInfo.setSrcclockId3(timeSyncObject.getSrcclockId3());
			timeSyncInfo.setSrcclockpri2(timeSyncObject.getSrcclockpri2());
			timeSyncInfo.setSrcclockpri1(timeSyncObject.getSrcclockpri1());
			timeSyncInfo.setSrcclockLevel(timeSyncObject.getSrcclockLevel());
			timeSyncInfo.setSrcclockaccuray(timeSyncObject.getSrcclockaccuray());
			timeSyncInfo.setSlaveOnlyModel(timeSyncObject.getSlaveOnlyModel());
			timeSyncInfo.setInCompensation(timeSyncObject.getInCompensation());
			timeSyncInfo.setInDelay(timeSyncObject.getInDelay());
			timeSyncInfo.setOutCompensation(timeSyncObject.getOutCompensation());
			timeSyncInfo.setOutDelay(timeSyncObject.getOutDelay());
			timeSyncInfo.setSynCicle(timeSyncObject.getSynCicle());
			timeSyncInfo.setNoteCicle(timeSyncObject.getNoteCicle());
			timeSyncInfo.setTimeInfoIt(timeSyncObject.getTimeInfoIt());
			timeSyncInfo.setPtpNum(timeSyncObject.getPtpNum());
			timeSyncInfo.setSiteId(timeSyncObject.getSiteId());
			timeSyncInfo.setSyncFreq(timeSyncObject.getSyncFreq());
			timeSyncInfo.setDelayFreq(timeSyncObject.getDelayFreq());
			timeSyncInfo.setAnnounceFreq(timeSyncObject.getAnnounceFreq());
			timeSyncInfo.setDelayOver(timeSyncObject.getDelayOver());
			timeSyncInfo.setAnnounceOver(timeSyncObject.getAnnounceOver());
			timeSyncInfo.setDomainNumbe(timeSyncObject.getDomainNumbe());
			timeSyncInfo.setSiteId(siteId);
			List<PtpPortinfo> ptpPort = new ArrayList<PtpPortinfo>();
			List<PtpPortinfo> ptpPort1 =timeSyncObject.getPtpPortinfo();
			if(ptpPort1.size()!=0){
				for(int i=0;i<ptpPort1.size();i++){
					ptpPortinfo  = new PtpPortinfo();
					ptpPortinfo.setIndexId(ptpPort1.get(i).getIndexId());
					ptpPortinfo.setPortId(ptpPort1.get(i).getPortId());
					ptpPortinfo.setWorkModel(ptpPort1.get(i).getWorkModel());
					ptpPortinfo.setLine(ptpPort1.get(i).getLine());
					ptpPortinfo.setLineCpn(ptpPort1.get(i).getLineCpn());
					ptpPortinfo.setMessageMode(ptpPort1.get(i).getMessageMode());
					ptpPortinfo.setPortNum(ptpPort1.get(i).getPortNum());
					ptpPortinfo.setId(ptpPort1.get(i).getId());
					ptpPortinfo.setWorkModel(ptpPort1.get(i).getWorkModel());
					ptpPortinfo.setSiteId(siteId);
					ptpPort.add(ptpPortinfo);
				}
			}
			timeSyncInfo.setPtpPortlist(timeSyncObject.getPtpPortinfo());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			ptpPortinfo =null;
		}
		return timeSyncInfo;
	}
	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
