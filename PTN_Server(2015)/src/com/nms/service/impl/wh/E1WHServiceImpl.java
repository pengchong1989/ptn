package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.E1LegObject;
import com.nms.drive.service.bean.E1Object;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
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

public class E1WHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;

	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		E1InfoService_MB e1InfoService = null;
		List<E1Info> e1InfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObject = null;
		OperationObject e1Card1OperationObject = null;
		OperationObject e1Card2OperationObject = null;
		OperationObject e1Card3OperationObject = null;
		OperationObject operationObjectResult=null;
		E1Object card1Object = null;
		E1Object card2Object = null;
		E1Object card3Object = null;
		List<E1Object> e1ObjectList = null;
		NEObject neObject = null;
		E1Object e1Object = null;
		SiteService_MB siteService = null;
		String siteType=null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			e1InfoList = (List<E1Info>) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(e1InfoList.get(0).getSiteId());
			if (super.isLockBySiteIdList(siteIdList)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
			}
			super.lockSite(siteIdList, SiteLockTypeUtil.E1_UPDATE);//锁住操作的网元		
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(e1InfoList.get(0).getSiteId())+"")){//非失连网元、非虚拟网元下发设备
				e1ObjectList = new ArrayList<E1Object>();
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(e1InfoList.get(0).getSiteId());
				siteType=siteService.getSiteType(e1InfoList.get(0).getSiteId());
//				if("710B".equals(siteType) || "710A".equals(siteType)){
				 if(siteType.contains("710")||"CSG T5000".equals(siteType)){
					e1ObjectList = new ArrayList<E1Object>();
					card1Object = new E1Object();
					card2Object = new E1Object();
					card3Object = new E1Object();
					getE1Object(e1InfoList, card1Object, card2Object, card3Object);
					if(card1Object.getE1LegObjectList().size()>0){
						e1ObjectList.add(card1Object);
						e1Card1OperationObject = new OperationObject();
						neObject.setControlPanelAddress(1);
						neObject.setControlPanelType(118492006);
						super.sendAction(e1Card1OperationObject, neObject, e1ObjectList, PtnServiceEnum.E1);//下发E1配置
						operationObjectResult = super.verification(e1Card1OperationObject);//获取设备返回信息
					}
					if(card2Object.getE1LegObjectList().size()>0){
						e1ObjectList.clear();
						e1ObjectList.add(card2Object);
						e1Card2OperationObject = new OperationObject();
						neObject.setControlPanelAddress(2);
						neObject.setControlPanelType(118489105);
						super.sendAction(e1Card2OperationObject, neObject, e1ObjectList, PtnServiceEnum.E1);//下发E1配置
						operationObjectResult = super.verification(e1Card2OperationObject);//获取设备返回信息
					}
					if(card3Object.getE1LegObjectList().size()>0){
						e1ObjectList.clear();
						e1ObjectList.add(card3Object);
						e1Card3OperationObject = new OperationObject();
						neObject.setControlPanelAddress(3);
						neObject.setControlPanelType(118489105);
						super.sendAction(e1Card3OperationObject, neObject, e1ObjectList, PtnServiceEnum.E1);//下发E1配置
						operationObjectResult = super.verification(e1Card3OperationObject);//获取设备返回信息
					}
					
				}else{
					e1Object = this.getE1Object(e1InfoList);
					e1ObjectList.add(e1Object);
					operationObject = new OperationObject();
					super.sendAction(operationObject, neObject, e1ObjectList, PtnServiceEnum.E1);//下发E1配置
					operationObjectResult = super.verification(operationObject);//获取设备返回信息
				}
				
				if (operationObjectResult.isSuccess()) {
					for(E1Info e1Info :e1InfoList){
						e1InfoService.update(e1Info);//更新数据库
					}
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(e1InfoService);
			UiUtil.closeService_MB(siteService);
		}
	}


	/**
	 * 封装e1Object对象
	 * 
	 * @param e1Info
	 * @return
	 */
	private void getE1Object(List<E1Info> e1InfoList,E1Object card1Object,E1Object card2Object,E1Object card3Object) {
		
		List<E1LegObject> card1List = new ArrayList<E1LegObject>();
		List<E1LegObject> card2List = new ArrayList<E1LegObject>();
		List<E1LegObject> card3List = new ArrayList<E1LegObject>();
		card1Object.setE1LegOutPutClockSource(e1InfoList.get(0).getE1LegOutPutClockSource()+1);
		card1Object.setRtpEnable(e1InfoList.get(0).getRtpEnable());
		card1Object.setTdmClockSource(e1InfoList.get(0).getTdmClockSource());
		card2Object.setE1LegOutPutClockSource(e1InfoList.get(0).getE1LegOutPutClockSource()+1);
		card2Object.setRtpEnable(e1InfoList.get(0).getRtpEnable());
		card2Object.setTdmClockSource(e1InfoList.get(0).getTdmClockSource());
		card3Object.setE1LegOutPutClockSource(e1InfoList.get(0).getE1LegOutPutClockSource()+1);
		card3Object.setRtpEnable(e1InfoList.get(0).getRtpEnable());
		card3Object.setTdmClockSource(e1InfoList.get(0).getTdmClockSource());
		for (E1Info e1LegInfo : e1InfoList) {
			if(e1LegInfo.getPortName().startsWith("e1.1.")){
				E1LegObject e1LegObject = new E1LegObject();
				e1LegObject.setLegEnable(e1LegInfo.getLegEnable());
				e1LegObject.setLegId(e1LegInfo.getLegId());
				e1LegObject.setLegShield(e1LegInfo.getLegShield());
				e1LegObject.setPinCount(e1LegInfo.getPinCount());
				e1LegObject.setPrestoreTime(e1LegInfo.getPrestoreTime());
				e1LegObject.setPrestoreTimeEnable(e1LegInfo.getPrestoreTimeEnable());
				e1LegObject.setPwLable(e1LegInfo.getPwLabel());
				card1List.add(e1LegObject);
			}else if(e1LegInfo.getPortName().startsWith("e1.2.")){
				E1LegObject e1LegObject = new E1LegObject();
				e1LegObject.setLegEnable(e1LegInfo.getLegEnable());
				e1LegObject.setLegId(e1LegInfo.getLegId());
				e1LegObject.setLegShield(e1LegInfo.getLegShield());
				e1LegObject.setPinCount(e1LegInfo.getPinCount());
				e1LegObject.setPrestoreTime(e1LegInfo.getPrestoreTime());
				e1LegObject.setPrestoreTimeEnable(e1LegInfo.getPrestoreTimeEnable());
				e1LegObject.setPwLable(e1LegInfo.getPwLabel());
				card2List.add(e1LegObject);
			}else if(e1LegInfo.getPortName().startsWith("e1.3.")){
				E1LegObject e1LegObject = new E1LegObject();
				e1LegObject.setLegEnable(e1LegInfo.getLegEnable());
				e1LegObject.setLegId(e1LegInfo.getLegId());
				e1LegObject.setLegShield(e1LegInfo.getLegShield());
				e1LegObject.setPinCount(e1LegInfo.getPinCount());
				e1LegObject.setPrestoreTime(e1LegInfo.getPrestoreTime());
				e1LegObject.setPrestoreTimeEnable(e1LegInfo.getPrestoreTimeEnable());
				e1LegObject.setPwLable(e1LegInfo.getPwLabel());
				card3List.add(e1LegObject);
			}
				
		}
		card1Object.setE1LegObjectCount(card1List.size());
		card1Object.setE1LegObjectList(card1List);
		card2Object.setE1LegObjectCount(card2List.size());
		card2Object.setE1LegObjectList(card2List);
		card3Object.setE1LegObjectCount(card3List.size());
		card3Object.setE1LegObjectList(card3List);
	}

	/**
	 * 封装e1Object对象
	 * 
	 * @param e1Info
	 * @return
	 */
	private E1Object getE1Object(List<E1Info> e1InfoList) {
		E1Object e1Object = new E1Object();
		List<E1LegObject> card1List = new ArrayList<E1LegObject>();
		List<E1LegObject> card2List = new ArrayList<E1LegObject>();
		List<E1LegObject> card3List = new ArrayList<E1LegObject>();
		e1Object.setE1LegOutPutClockSource(e1InfoList.get(0).getE1LegOutPutClockSource()+1);
		e1Object.setRtpEnable(e1InfoList.get(0).getRtpEnable());
		e1Object.setTdmClockSource(e1InfoList.get(0).getTdmClockSource());
		for (E1Info e1LegInfo : e1InfoList) {
			E1LegObject e1LegObject = new E1LegObject();
			e1LegObject.setLegEnable(e1LegInfo.getLegEnable());
			e1LegObject.setLegId(e1LegInfo.getLegId());
			e1LegObject.setLegShield(e1LegInfo.getLegShield());
			e1LegObject.setPinCount(e1LegInfo.getPinCount());
			e1LegObject.setPrestoreTime(e1LegInfo.getPrestoreTime());
			e1LegObject.setPrestoreTimeEnable(e1LegInfo.getPrestoreTimeEnable());
			e1LegObject.setPwLable(e1LegInfo.getPwLabel());
			card1List.add(e1LegObject);
				
		}
		e1Object.setE1LegObjectCount(card1List.size());
		e1Object.setE1LegObjectList(card1List);
		return e1Object;
	}
	
	/**
	 * 与设备同步PDH端口
	 * @author guoqc
	 * @param siteId
	 * @return 
	 * @exception
	 */
	public Object synchro(int siteId)throws Exception {
		OperationObject operaObj = new OperationObject();
		E1InfoService_MB e1InfoService = null;
		List<E1Info> e1InfoList = null;
		int e1=0;
		int e2=0;
		int e3=0;
		PortService_MB portService = null;
		List<PortInst> portInsts = null;
		try {
			e1InfoService=(E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);	
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			e1InfoList = e1InfoService.selectBySiteId(siteId);
			if(e1InfoList !=null && e1InfoList.size()!=0 ){
				for (E1Info e1Info : e1InfoList) {
					if(e1Info.getPortName().startsWith("e1.1.")){						
							e1++;
					}else if(e1Info.getPortName().startsWith("e1.2.")){
						    e2++;
					}else if(e1Info.getPortName().startsWith("e1.3.")){
						    e3++;
					}
				}				
			}
			//同步前修改E1端口的isendabled_code属性
			PortInst portInst = new PortInst();
			portInst.setSiteId(siteId);
			portInst.setPortType("e1");
			portInsts = portService.select(portInst);			
			if(portInsts != null && portInsts.size()>0){
				for(PortInst inst : portInsts){
					    inst.setIsEnabled_code(0);
					    portService.saveOrUpdate(inst);
					}
				}
			
			if(e1>0){
				operaObj = this.getSynchroOperationObject(siteId,e1,0,0);//封装下发数据			
				super.sendAction(operaObj);//下发数据
				super.verification(operaObj);//验证是否下发成功
				if(operaObj.isSuccess()){ 
					/*成功，则与数据库进行同步*/					
					for(ActionObject actionObject : operaObj.getActionObjectList()){
						this.synchro_db(actionObject.getE1Object(),siteId,"e1.1.");
					}										
				}
			}
			if(e2>0){
				operaObj = this.getSynchroOperationObject(siteId,0,e2,0);//封装下发数据			
				super.sendAction(operaObj);//下发数据
				super.verification(operaObj);//验证是否下发成功
				if(operaObj.isSuccess()){ 
					/*成功，则与数据库进行同步*/
					for(ActionObject actionObject : operaObj.getActionObjectList()){
						this.synchro_db(actionObject.getE1Object(),siteId,"e1.2.");
					}
				}
			}
            if(e3>0){
				operaObj = this.getSynchroOperationObject(siteId,0,0,e3);//封装下发数据			
				super.sendAction(operaObj);//下发数据
				super.verification(operaObj);//验证是否下发成功
				if(operaObj.isSuccess()){ 
					/*成功，则与数据库进行同步*/
					for(ActionObject actionObject : operaObj.getActionObjectList()){
						this.synchro_db(actionObject.getE1Object(),siteId,"e1.3.");
					}					
				}				
			}			
			
		  return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(e1InfoService);
			UiUtil.closeService_MB(portService);
			portInsts=null;
			e1InfoList=null;
		}
		return ResultString.CONFIG_TIMEOUT;
	}

	/**
	 * 与数据库进行同步
	 * @param e1Object
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(E1Object e1Object, int siteId,String e1) throws Exception{
		List<E1Info> e1Infos = null;
		E1InfoService_MB e1InfoService = null;
		try {
			e1Infos = this.getE1Info(e1Object,siteId);
			try {
				e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);				
				SynchroUtil synchroUtil = new SynchroUtil();
				for(E1Info e1Info : e1Infos){				
					synchroUtil.e1Synchro(e1Info,e1);					
				}
				
				
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(e1InfoService);
		}
	}

	/**
	 * 将设备信息封装到e1Info表中
	 * @param e1Object
	 * @param siteId
	 * @return e1Info
	 * @throws Exception
	 */
	private List<E1Info> getE1Info(E1Object e1Object, int siteId) throws Exception{
		E1Info e1Info = null;
		List<E1Info> e1Infos = null;
		try {		
			e1Infos = new ArrayList<E1Info>();		
			for(E1LegObject e1Leg : e1Object.getE1LegObjectList()){
				e1Info = new E1Info();
				e1Info.setSiteId(siteId);
				e1Info.setE1LegOutPutClockSource(e1Object.getE1LegOutPutClockSource()-1);
				e1Info.setTdmClockSource(e1Object.getTdmClockSource());
				e1Info.setRtpEnable(e1Object.getRtpEnable());
				e1Info.setLegShield(e1Leg.getLegShield());
				e1Info.setLegEnable(e1Leg.getLegEnable());
				e1Info.setPrestoreTimeEnable(e1Leg.getPrestoreTimeEnable());
				e1Info.setPrestoreTime(e1Leg.getPrestoreTime());
				e1Info.setPinCount(e1Leg.getPinCount());
				e1Info.setPwLabel(e1Leg.getPwLable());
				e1Info.setLegId(e1Leg.getLegId());
				e1Infos.add(e1Info);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return e1Infos;
	}

	/**
	 * 封装下发数据
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId,int e1,int e2,int e3)throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);	
			if(e1>0){
				neObject.setControlPanelAddress(1);
				neObject.setControlPanelType(118492006);
			}else if(e2>0){
				neObject.setControlPanelAddress(2);
				neObject.setControlPanelType(118489105);
			}else if(e3>0){
				neObject.setControlPanelAddress(3);
				neObject.setControlPanelType(118489105);
			}
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("e1Synchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			 actionObject = null;
		}
		return operationObject;
	}
	
}
