package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.ClockObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.ptn.clock.ClockFrequService_MB;
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
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ClockFrequWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionInsert(Object object) throws Exception {
		
		FrequencyInfo info= null;
		ClockFrequService_MB InfoService=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		NEObject neObject = null;
		ClockObject clockObject = null;
		List<ClockObject> clockObjectList = null;
		try {
			info = (FrequencyInfo) object;
			InfoService = (ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
			/** 入库 */
			int objId = InfoService.insert(info);
			info.setId(objId);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(info.getSiteId())+"")){
				clockObjectList = new ArrayList<ClockObject>();
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(info.getSiteId());
				clockObject = this.getClockObject(info);
				operationObjectAfter = new OperationObject();
				clockObjectList.add(clockObject);
				super.sendAction(operationObjectAfter, neObject, clockObjectList, PtnServiceEnum.CLOCK);
				operationObjectResult = super.verification(operationObjectAfter);
				if (!(operationObjectResult.isSuccess())) {
					InfoService.delete(info.getSiteId());
					return super.getErrorMessage(operationObjectResult);
				}else{
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(InfoService);
			info= null;
			operationObjectAfter=null;
			operationObjectResult=null;
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		
		FrequencyInfo info= null;
		ClockFrequService_MB InfoService=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		NEObject neObject = null;
		ClockObject clockObject = null;
		List<ClockObject> clockObjectList = null;
		try {
			info = (FrequencyInfo) object;
			InfoService = (ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(info.getSiteId())+"")){
				clockObjectList = new ArrayList<ClockObject>();
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(info.getSiteId());
				clockObject = this.getClockObject(info);
				operationObjectAfter = new OperationObject();
				clockObjectList.add(clockObject);
				super.sendAction(operationObjectAfter, neObject, clockObjectList, PtnServiceEnum.CLOCK);
				operationObjectResult = super.verification(operationObjectAfter);
				if (!(operationObjectResult.isSuccess())) {
					InfoService.delete(info.getSiteId());
					return super.getErrorMessage(operationObjectResult);
				}else{
					InfoService.update(info);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				}
			}else{
				InfoService.update(info);
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(InfoService);
			info= null;
			operationObjectAfter=null;
			operationObjectResult=null;
		}
	}
	/**
	 * 时钟倒换
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public String clockRorate(Object object) throws Exception{

		FrequencyInfo info= null;
		FrequencyInfo beforeInfo = null;
		SiteRoateService_MB  siteRoateService=null;
		Map<Integer, ActionObject> operationObjectBefore=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		SiteRoate siteRoate =null;
		SiteRoate newSiteRoate=null;
		try {
			info = (FrequencyInfo) object;
			
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(info.getSiteId())+"")){
				newSiteRoate = new SiteRoate();
				newSiteRoate.setSiteId(info.getSiteId());
				newSiteRoate.setType("clock");
				if(info.getClockRarate()==30){
					newSiteRoate.setTypeId(2);
					newSiteRoate.setSiteRoate(0);
				}else{
					newSiteRoate.setTypeId(1);
					newSiteRoate.setSiteRoate(info.getClockRarate());
				}
				if(info.getId()>0){
					siteRoateService= (SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
					siteRoate=new SiteRoate();
					siteRoate.setSiteId(ConstantUtil.siteId);
					siteRoate.setType("clock");
					List<SiteRoate> sroate=siteRoateService.querBySiteRoate(siteRoate);	
					if(!sroate.isEmpty()){
						siteRoate=sroate.get(0);
						beforeInfo =new FrequencyInfo();
						beforeInfo.setSiteId(siteRoate.getSiteId());
						if(siteRoate.getTypeId()==2){
							beforeInfo.setClockRarate(30);
						}else{
							beforeInfo.setClockRarate(siteRoate.getSiteRoate());
						}
						operationObjectBefore = this.getOperationBefore(beforeInfo);
						siteRoateService.updateSiteRoate(newSiteRoate);
					}else{
						siteRoateService.insert(newSiteRoate);
					}
				}
				operationObjectAfter = this.getClockRorateOperationObject(info);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_UP_DOWN);
			}
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult,operationObjectBefore);
				if (flag) {
					System.out.println("回滚成功");
				} else {
					System.out.println("回滚失败");
				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(siteRoateService);
			info= null;
			operationObjectAfter=null;
			operationObjectResult=null;
		}
		return null;
	
	}
	
	
	private OperationObject getClockRorateOperationObject(FrequencyInfo info) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		ClockObject clockObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(info.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("clockRorateFreque");
			clockObject = new ClockObject();
			clockObject.setClockRorate(info.getClockRarate());
			actionObject.setClockObject(clockObject);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			actionObject = null;
			neObject =  null;
		}
		return operationObject;
	}

	private Map<Integer, ActionObject> getOperationBefore(FrequencyInfo beforeInfo) throws Exception {
	
		Map<Integer, ActionObject> operationBefore=null;
		OperationObject operationObject= null;
		try {
			operationBefore=new HashMap<Integer, ActionObject>();
			operationObject=this.getOperationObject(beforeInfo);
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
	 * 获取operationObject对象
	 * @param info
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(FrequencyInfo info) throws Exception{
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		ClockObject clockObject = null;
		try {
			operationObject = new OperationObject();
			actionObject = new ActionObject();
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(info.getSiteId())+"")){
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(info.getSiteId());
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("clockFreque");
				
				actionObject.setClockObject(clockObject);
				operationObject.getActionObjectList().add(actionObject);
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);
			}
			
			
		} catch (Exception e) {
			throw e;
		}finally{
			actionObject = null;
			neObject =  null;
			clockObject = null;
		}
		return operationObject;
	}

	/**
	 * 数据库数据转换为驱动数据
	 * @param info
	 * @return
	 */
	private ClockObject getClockObject(FrequencyInfo info){
		ClockObject clockObject = null;
		clockObject = new ClockObject();
		clockObject.setClockWorkMode(info.getClockWorkMode());
		clockObject.setQlEnable(info.getQlEnable());
		clockObject.setClockPRIS(info.getClockPRIList());
		clockObject.setOuterClockInSelect(info.getOuterClockInSelect());
		clockObject.setOuterClockOutSelect1(info.getOuterClockOutSelect1());
		clockObject.setOuterClockOutSelect2(info.getOuterClockOutSelect2());
		clockObject.setOutSelectS(info.getOutSelectList());
		clockObject.setSmOuter(info.getSmOuter());
		clockObject.setSmSystem(info.getSmSystem());
		clockObject.setQlIn(info.getQlIn());
		clockObject.setQlOut(info.getQlOut());
		clockObject.setClockInQLValueS(info.getClockInQLValueList());
		clockObject.setClockOutQLValueS(info.getClockOutQLValueList());
		clockObject.setClockInResumeTime(info.getClockInResumeTime());
		String s1 = info.getWaitResumeTime();
		int i = 0;
		clockObject.setR10(String.valueOf(s1.charAt(i++)));
		clockObject.setR11(String.valueOf(s1.charAt(i++)));
		clockObject.setR12(String.valueOf(s1.charAt(i++)));
		clockObject.setR13(String.valueOf(s1.charAt(i++)));
		clockObject.setR14(String.valueOf(s1.charAt(i++)));
		clockObject.setR15(String.valueOf(s1.charAt(i++)));
		clockObject.setR16(String.valueOf(s1.charAt(i++)));
		clockObject.setR17(String.valueOf(s1.charAt(i++)));
		clockObject.setR18(String.valueOf(s1.charAt(i++)));
		clockObject.setR19(String.valueOf(s1.charAt(i++)));
		clockObject.setR20(String.valueOf(s1.charAt(i++)));
		clockObject.setR21(String.valueOf(s1.charAt(i++)));
		clockObject.setR22(String.valueOf(s1.charAt(i++)));
		clockObject.setR23(String.valueOf(s1.charAt(i++)));
		clockObject.setR24(String.valueOf(s1.charAt(i++)));
		clockObject.setR25(String.valueOf(s1.charAt(i++)));
		clockObject.setR26(String.valueOf(s1.charAt(i++)));
		clockObject.setR27(String.valueOf(s1.charAt(i++)));
		clockObject.setR28(String.valueOf(s1.charAt(i++)));
		clockObject.setR31(String.valueOf(s1.charAt(i++)));
		clockObject.setR32(String.valueOf(s1.charAt(i++)));
		clockObject.setR33(String.valueOf(s1.charAt(i++)));
		clockObject.setR34(String.valueOf(s1.charAt(i++)));
		clockObject.setR35(String.valueOf(s1.charAt(i++)));
		clockObject.setR36(String.valueOf(s1.charAt(i++)));
		clockObject.setR37(String.valueOf(s1.charAt(i++)));
		clockObject.setR38(String.valueOf(s1.charAt(i++)));
		return clockObject;
	}
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public Object synchro(int siteId) {
		OperationObject operaObj = new OperationObject();
		
		try {
			operaObj = this.getSynchroOperationObject(siteId);//封装下发数据
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					if(actionObject.getClockObject() != null){
						this.synchro_db(actionObject.getClockObject(),siteId);
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
	private void synchro_db(ClockObject clockObject, int siteId)throws Exception {
		FrequencyInfo frequInfo = null;
		 try {
			frequInfo = this.getFrequeInfo(clockObject,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.frequSynchro(frequInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 将驱动数据转换成数据库数据
	 */
	private FrequencyInfo getFrequeInfo(ClockObject clockObject, int siteId)
	{
		FrequencyInfo frequInfo = new FrequencyInfo();
		frequInfo.setClockInQLValueList(clockObject.getClockInQLValueS());
		frequInfo.setClockInResumeTime(clockObject.getClockInResumeTime());
		frequInfo.setClockOutQLValueList(clockObject.getClockOutQLValueS());
		frequInfo.setClockPRIList(clockObject.getClockPRIS());
		frequInfo.setClockRarate(clockObject.getClockRorate());
		frequInfo.setClockWorkMode(clockObject.getClockWorkMode());
		frequInfo.setSiteId(siteId);
		frequInfo.setQlEnable(clockObject.getQlEnable());
		frequInfo.setOuterClockInSelect(clockObject.getOuterClockInSelect());
		frequInfo.setOuterClockOutSelect1(clockObject.getOuterClockOutSelect1());
		frequInfo.setOuterClockOutSelect2(clockObject.getOuterClockOutSelect2());
		frequInfo.setOutSelectList(clockObject.getOutSelectS());
		frequInfo.setSmOuter(clockObject.getSmOuter());
		frequInfo.setSmSystem(clockObject.getSmSystem());
		frequInfo.setQlIn(clockObject.getQlIn());
		frequInfo.setQlOut(clockObject.getQlOut());
		
		StringBuffer sb = new StringBuffer();
		sb.append(clockObject.getR10());
		sb.append(clockObject.getR11());
		sb.append(clockObject.getR12());
		sb.append(clockObject.getR13());
		sb.append(clockObject.getR14());
		sb.append(clockObject.getR15());
		sb.append(clockObject.getR16());
		sb.append(clockObject.getR17());
		sb.append(clockObject.getR18());
		sb.append(clockObject.getR19());
		sb.append(clockObject.getR20());
		sb.append(clockObject.getR21());
		sb.append(clockObject.getR22());
		sb.append(clockObject.getR23());
		sb.append(clockObject.getR24());
		sb.append(clockObject.getR25());
		sb.append(clockObject.getR26());
		sb.append(clockObject.getR27());
		sb.append(clockObject.getR28());
		sb.append(clockObject.getR31());
		sb.append(clockObject.getR32());
		sb.append(clockObject.getR33());
		sb.append(clockObject.getR34());
		sb.append(clockObject.getR35());
		sb.append(clockObject.getR36());
		sb.append(clockObject.getR37());
		sb.append(clockObject.getR38());
		frequInfo.setWaitResumeTime(sb.toString());
		return frequInfo;
	}
	
	/**
	 * 封装下发数据
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId)throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("clockFrequSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}
}
