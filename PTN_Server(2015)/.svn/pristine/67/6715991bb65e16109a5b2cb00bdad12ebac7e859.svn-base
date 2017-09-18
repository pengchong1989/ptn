package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.PortLAGMember;
import com.nms.drive.service.bean.PortLAGObject;
import com.nms.drive.service.bean.PortLAGbuffer;
import com.nms.drive.service.bean.PortLAGexitQueue;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
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
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortLagWHServiceImpl extends WHOperationBase implements OperationServiceI{

	
	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		List<PortLagInfo> portLagInfoList = null;
		List<PortLagInfo> portLagInfoActivate = null;
		List<Integer> siteIdList=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		
		try {
			portLagInfoList = objectList;
			portLagInfoActivate = this.getActivate(portLagInfoList);
			siteIdList = this.getSiteIds(portLagInfoList);
			if(portLagInfoActivate.size()>0){
				return ResourceUtil.srcStr(StringKeysTip.TIP_LAG_USE);
			}
			/**锁住该网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			/**获取之前的portLag  回滚用*/
			operationObjectAfter=this.getOperationObject(siteIdList);	
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter);
			if(operationObjectResult.isSuccess()){
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus(); 
			}else{
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			
			}
			
			 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			super.clearLock(siteIdList);
		}
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		PortLagInfo portLagInfo = null;
		List<Integer> siteIdList=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		try {
			
			portLagInfo = (PortLagInfo)object;
			portLagInfoList.add(portLagInfo);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(portLagInfo.getSiteId());
			/**锁住该网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			/**获取之前的portLag  回滚用*/
			
			operationObjectAfter=this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);
			
			operationObjectResult=super.verification(operationObjectAfter);
			if(operationObjectResult.isSuccess()){
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus(); 
			}else{
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			
			} 
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			super.clearLock(siteIdList);
		}
		
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortLagInfo portLagInfo = null;
		List<Integer> siteIdList=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		try {
			
			portLagInfo = (PortLagInfo)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(portLagInfo.getSiteId());
			/**锁住该网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
			/**获取之前的portLag  回滚用*/
			/**入库*/
			
			operationObjectAfter=this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);
			
			operationObjectResult=super.verification(operationObjectAfter);
			if(operationObjectResult.isSuccess()){
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus(); 
			}else{
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			
			} 
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			super.clearLock(siteIdList);
		}
		
		return ResultString.CONFIG_SUCCESS;
	}

	/**
	 * 获取siteID集合
	 * @param tunnel tunnel对象
	 * @return siteID集合
	 * @throws Exception 
	 */
	private List<Integer> getSiteIds(List<PortLagInfo> portLagInfoList) throws Exception{
		List<Integer> siteIds=null;
		try {
			siteIds=new ArrayList<Integer>();
			
			for(PortLagInfo portLagInfo : portLagInfoList){
				siteIds.add(portLagInfo.getSiteId());
			}
			
		} catch (Exception e) {
			throw e;
		}
		return siteIds;
	}
	
	/**
	 * 获取下发之前之前
	 * @param siteIdList 
	 * @return
	 * @throws Exception
	 */
	private Map<Integer, ActionObject> getOperationBefore(List<Integer> siteIdList) throws Exception{
		
		Map<Integer, ActionObject> operationBefore=null;
		OperationObject operationObject= null;
		try {
			operationBefore=new HashMap<Integer, ActionObject>();
			operationObject=this.getOperationObject(siteIdList);
			for(ActionObject actionObject : operationObject.getActionObjectList()){
				operationBefore.put(actionObject.getNeObject().getNeAddress(), actionObject);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			operationObject=null;
		}
		return operationBefore;
	}
	
	/**
	 * 获取operationobject对象
	 * @param siteIdList 网元ID
	 * @return 
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception{
		OperationObject operationObject=null;
		ActionObject actionObject=null;
		NEObject neObject=null;
		try {
			operationObject=new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(int siteId : siteIdList){
				actionObject=new ActionObject();
				if(0 == siteUtil.SiteTypeUtil(siteId)){//非失连网元、非虚拟网元下发设备
					neObject = whImplUtil.siteIdToNeObject(siteId);
					List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();//通过网元ID保存tunnel
					actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
					actionObject.setNeObject(neObject);
					actionObject.setType("portLag");
	 				actionObject.setPortLAGList(this.getPortLagObject(siteId,portLagInfoList));
	 				operationObject.getActionObjectList().add(actionObject);
				}else{
					actionObject.setStatus(ResultString.CONFIG_SUCCESS);
					operationObject.getActionObjectList().add(actionObject);
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			actionObject=null;
			neObject=null;
		}
		return operationObject;
	}

	/**
	 * 获取tunnel集合中集合的tunnel
	 * @return
	 */
	private List<PortLagInfo> getActivate(List<PortLagInfo> portLagInfoList){
		
		List<PortLagInfo> portLagInfoActivate=new ArrayList<PortLagInfo>();
		for(PortLagInfo portLagInfo:portLagInfoList){
			if(portLagInfo.getIsUsed() == 1){
				portLagInfoActivate.add(portLagInfo);
			}
		}
		return portLagInfoActivate;
	}
	
	/**
	 * 封装端口聚合对象
	 * @param siteId
	 * @param portLagInfoList
	 * @return
	 * @throws Exception
	 */
	private List<PortLAGObject> getPortLagObject(int siteId, List<PortLagInfo> portLagInfoList) throws Exception {
		List<PortLAGObject> portLAGObjectList = new ArrayList<PortLAGObject>();
		PortLAGObject portLAGObject = null;
		PortLagService_MB portLagService = null;
		
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			PortLagInfo portLagInfo1 = new PortLagInfo();
			portLagInfo1.setSiteId(siteId);
			portLagInfoList = portLagService.selectLAGByCondition(portLagInfo1);
			for(PortLagInfo portLagInfo:portLagInfoList){
				portLAGObject = new PortLAGObject();
				portLAGObject.setAimIP(portLagInfo.getAimIP());
				portLAGObject.setAimMAC(portLagInfo.getAimMAC());
				portLAGObject.setBroadcastBate(Integer.parseInt(UiUtil.getCodeById(portLagInfo.getBroadcastBate()).getCodeValue()));
				portLAGObject.setBroadcastFlux(portLagInfo.getBroadcastFlux());
				portLAGObject.setExitQueueList(this.getPortLAGexitQueue(portLagInfo.getExportQueue()));
				portLAGObject.setFloodFlux(portLagInfo.getFloodFlux());
				portLAGObject.setFountainIP(portLagInfo.getFountainIP());
				portLAGObject.setFountainMAC(portLagInfo.getFountainMAC());
				portLAGObject.setGroupBroadcastBate(Integer.parseInt(UiUtil.getCodeById(portLagInfo.getGroupBroadcastBate()).getCodeValue()));
				portLAGObject.setGroupBroadcastFlux(portLagInfo.getGroupBroadcastFlux());
				portLAGObject.setIpDSCPSet(portLagInfo.getIpDSCPSet());
				portLAGObject.setLagID(portLagInfo.getLagID());
				portLAGObject.setLagMode(portLagInfo.getLagMode());
				portLAGObject.setMemberList(this.getPortLAGMember(portLagInfo));
				portLAGObject.setMtu(portLagInfo.getMtu());
				portLAGObject.setPortLimitation(portLagInfo.getPortLimitation());
				portLAGObject.setPwSet(portLagInfo.getPwSet());
				portLAGObject.setRelatingSet(portLagInfo.getRelatingSet());
				portLAGObject.setVlanRelating(portLagInfo.getVlanRelating());
				portLAGObject.setBufferList(getPortLAGbuffer());
				
				portLAGObject.setPortEnable(portLagInfo.getPortEnable());
				PortInst mainPortInst = this.setPortnumber(portLagInfo.getFloodBate());
				if(mainPortInst != null){
					portLAGObject.setFloodBate(mainPortInst.getNumber());
					portLAGObject.setMainSlot(mainPortInst.getSlotNumber());
				}
				PortInst standPortInst = this.setPortnumber(portLagInfo.getFlowControl());
				if(standPortInst != null){
					portLAGObject.setFlowControl(standPortInst.getNumber());
					portLAGObject.setStandSlot(standPortInst.getSlotNumber());
				}
				portLAGObjectList.add(portLAGObject);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portLagService);
		}
		return portLAGObjectList;
	}
	
	private PortInst setPortnumber(int portId){
		PortInst portInst = new PortInst();
		PortService_MB portService  = null; 
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst.setPortId(portId);
			portInst = portService.selectPortybyid(portId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return portInst;
	}
	/**
	 * 获得(出口队列调度策略)
	 * @param str
	 * @return
	 */
	public List<PortLAGexitQueue> getPortLAGexitQueue(String str){
		List<PortLAGexitQueue> portLAGexitQueueList = new ArrayList<PortLAGexitQueue>();
		try {
			String[] strs = str.split(",");
			for(String s :strs){
				String[] ss = s.split("-");
				PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
				portLAGexitQueue.setMode(Integer.parseInt(ss[0]));
				portLAGexitQueue.setPopedom(Integer.parseInt(ss[1]));
				portLAGexitQueueList.add(portLAGexitQueue);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portLAGexitQueueList;
		
	}
	
	/**
	 * 获得聚合成员端口
	 * @param str
	 * @param portLagInfo
	 * @return
	 * @throws Exception
	 */
	public List<PortLAGMember> getPortLAGMember(PortLagInfo portLagInfo) throws Exception{
		List<PortLAGMember> portLAGMemberList = new ArrayList<PortLAGMember>();
		PortService_MB portService = null;
		PortInst portInst = null;
		PortLAGMember portLAGMember = null;
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			String[] strs = portLagInfo.getPortLagMember().split(",");
			for(String s : strs){
				portLAGMember = new PortLAGMember();
				if(Integer.parseInt(s)>0){
				portInst = new PortInst();
				portInst.setSiteId(portLagInfo.getSiteId());
				portInst.setNumber(Integer.parseInt(s));
//				portInst.setLagId(portLagInfo.getLagID());
				portInst = portService.select(portInst).get(0);
				portLAGMember.setPort(Integer.parseInt(s));
				portLAGMember.setSlot(portInst.getSlotNumber());
				}
				portLAGMemberList.add(portLAGMember);
			}
			portInst = new PortInst();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return portLAGMemberList;
	}
	
	public List<PortLAGbuffer> getPortLAGbuffer(){
		List<PortLAGbuffer> portLAGbuffers = new ArrayList<PortLAGbuffer>();
		for(int i=0;i<8;i++){
			PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
			portLAGbuffers.add(portLAGbuffer);
		}
		return portLAGbuffers;
	}
	
	/**
	 * 与设备同步LAG配置
	 * @author guoqc
	 * @param siteId
	 * @return 
	 * @exception
	 */
	public Object synchro(int siteId) throws Exception{
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = this.getSynchroOperationObject(siteId);//封装下发数据
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					this.synchro_db(actionObject.getPortLAGList(),siteId);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return "超时";
	}

	/**
	 * 与数据库进行同步
	 * @param portLAGList
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<PortLAGObject> portLAGList, int siteId) throws Exception {
		List<PortLagInfo> portLagInfoList = null;
		try {
			portLagInfoList = this.getportLagInfo(portLAGList,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(PortLagInfo portLagInfo : portLagInfoList){
				try {
					synchroUtil.portLagInfoSynchro(portLagInfo);	
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagInfoList = null;
		}
		
	}

	/**
	 * 将设备信息封装到portLagInfo表中
	 * @param portLagList
	 * @param siteId
	 * @return List<portLagInfo>
	 * @throws Exception
	 */
	private List<PortLagInfo> getportLagInfo(List<PortLAGObject> portLagList,int siteId) throws Exception{
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		PortLagInfo portLagInfo = null;
		
		for(PortLAGObject portLagObj : portLagList){
			try {
				portLagInfo = new PortLagInfo();
				portLagInfo.setSiteId(siteId);
				portLagInfo.setLagID(portLagObj.getLagID());
				portLagInfo.setLagMode(portLagObj.getLagMode());
				portLagInfo.setPortLagMember(this.getMember(portLagObj));
				portLagInfo.setPortEnable(portLagObj.getPortEnable());
				portLagInfo.setFlowControl(this.getPortId(portLagObj.getFlowControl()));//主聚合成员端口号
				portLagInfo.setMtu(portLagObj.getMtu());
				portLagInfo.setVlanRelating(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getVlanRelating()+"").getCodeValue())));
				portLagInfo.setRelatingSet(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getRelatingSet()+"").getCodeValue())));
				portLagInfo.setFountainMAC(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getFountainMAC()+"").getCodeValue())));
				portLagInfo.setAimMAC(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getAimMAC()+"").getCodeValue())));
				portLagInfo.setFountainIP(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getFountainIP()+"").getCodeValue())));
				portLagInfo.setAimIP(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getAimIP()+"").getCodeValue())));
				portLagInfo.setPwSet(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getPwSet()+"").getCodeValue())));
				portLagInfo.setIpDSCPSet(Integer.parseInt((UiUtil.getCodeByValue("GUANLIAN", portLagObj.getIpDSCPSet()+"").getCodeValue())));
				portLagInfo.setExportQueue(getReturnPortLAGexitQueue(portLagObj.getExitQueueList()));
				portLagInfo.setPortLimitation(portLagObj.getPortLimitation());
				portLagInfo.setBroadcastBate((UiUtil.getCodeByValue("WORKMODEL", portLagObj.getBroadcastBate()+"").getId()));
				portLagInfo.setBroadcastFlux(portLagObj.getBroadcastFlux());
				portLagInfo.setGroupBroadcastBate((UiUtil.getCodeByValue("VCTRAFFICPOLICING", portLagObj.getGroupBroadcastBate()+"").getId()));
				portLagInfo.setGroupBroadcastFlux(portLagObj.getGroupBroadcastFlux());
				portLagInfo.setFloodBate(this.getPortId(portLagObj.getFloodBate()));//从聚合成员端口号
				portLagInfo.setFloodFlux(portLagObj.getFloodFlux());
				portLagInfo.setType(1);
				portLagInfo.setLagStatus(EActiveStatus.ACTIVITY.getValue());
//				portLagInfo.setIsUsed();
				portLagInfoList.add(portLagInfo);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return portLagInfoList;
	}
	
	/**
	 * 通过portNumber获取portId
	 */
	private int getPortId(int number) {
		PortService_MB portService = null;
		try {
			if(number > 0){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				PortInst port = new PortInst();
				port.setNumber(number);
				port.setPortType("UNI");
				List<PortInst> portList = portService.select(port);
				if(portList.size() > 0){
					return portList.get(0).getPortId();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return 0;
	}

	/**
	 * F封装下发数据
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("portLagSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			 actionObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 返回端口成员
	 * @return
	 */
	public String getMember(PortLAGObject portLagObj){
		StringBuffer stringBuffer = new StringBuffer();
		PortLAGMember portLAGMember = null;;
		for (int i = 0; i < portLagObj.getMemberList().size(); i++) {
			portLAGMember = portLagObj.getMemberList().get(i);
			 if(i == (portLagObj.getMemberList().size()-1)){
				 stringBuffer.append(portLAGMember.getPort());
			 }else{
				 stringBuffer.append(portLAGMember.getPort()+",");
			 }
		}
		return stringBuffer.toString();
		
	}
	/**
	 * 返回(出口队列调度策略)
	 * @param str
	 * @return
	 */
	public String getReturnPortLAGexitQueue(List<PortLAGexitQueue> portLAGexitQueueList){
		StringBuffer str=null;
		String portLAGqueue=null;
		try {
			str=new StringBuffer();
			if(portLAGexitQueueList!=null&&portLAGexitQueueList.size()>0){
				for(PortLAGexitQueue portLAGexitQueue:portLAGexitQueueList){
					str.append(portLAGexitQueue.getMode()+"-").append(portLAGexitQueue.getPopedom()+",");
				}
			}
		     str.delete(str.length() - 1, str.length()).toString();
			portLAGqueue=str.toString();
		} catch (Exception e) {
			str=null;
		}
		return portLAGqueue;
		
	}
}