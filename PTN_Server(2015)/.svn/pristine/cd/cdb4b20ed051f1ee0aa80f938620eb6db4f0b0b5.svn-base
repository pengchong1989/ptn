package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.alarm.AlarmShieldInfo;
import com.nms.db.bean.alarm.AlarmShieldInfo_t;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.drive.service.bean.AlarmMasterCardObject;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.bean.AlarmShieldType;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.AlarmTools;

public class AlarmWHServiceImpl extends WHOperationBase {

//	private Ptnf ptnf = Ptnf.getPtnf();
	/**
	 * 获取设备主动上报的告警信息
	 * @param alarmObject
	 * @return
	 */
	public List<CurrentAlarmInfo> registerHisAlarm(List<AlarmObject> alarmObjectList) {
		List<CurrentAlarmInfo> currentAlarmInfo = null;
		AlarmTools alarmTools = null;
		//保存数据库
		try {
			alarmTools = new AlarmTools();
			currentAlarmInfo = alarmTools.convertAlarm(alarmObjectList);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			alarmTools = null;
		}
		return currentAlarmInfo;
	}
	
	/**
	 * 
	 * 网管主动轮询所有网元告警
	 * @param siteIdList
	 *            网元数据库id列表
	 * @throws Exception
	 */
	public void queryCurrAlarmBySites(List<SiteInst> siteInsts)throws Exception {
		OperationObject operationObject = null;
		String type = "queryCurrAlarmBySite";
		try {
			for(SiteInst siteInst : siteInsts){
				operationObject = this.getOperationObject(siteInst,type);
				super.sendAction(operationObject);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
		}
	}

	/**
	 * 同步设备当前告警
	 * 
	 * @param siteId
	 * @throws Exception
	 */
	public void synchroCurrAlarmBySiteId(Integer siteId) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		String type = "queryCurrAlarmBySite";
		List<AlarmObject> alarmObjects = null;
		SiteInst siteInst = null;
		AlarmTools alarmTools=new AlarmTools();
		try {
			siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(siteId);
			operationObject = this.getOperationObject(siteInst,type);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				alarmObjects = new ArrayList<AlarmObject>();
				alarmObjects.add(operationObjectResult.getActionObjectList().get(0).getAlarmObject());
				alarmTools.operateCurrentAlarm(alarmObjects);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
	}
	
	/**
	 * 根据网元id列表，查询网元下所有槽位的历史告警信息，并保存到数据库中
	 * 
	 * @param siteIdList
	 *            网元数据库id列表
	 * @return
	 * @throws Exception
	 */
	public String queryHisAlarmBySites(Integer siteId)
			throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<HisAlarmInfo> hisInfos = null;
		String type = "queryHistAlarmBySite";
		AlarmTools alarmTools=new AlarmTools();
		try {
			operationObject = this.getOperationObject(siteId,type);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				// 如果查询历史告警，则保存数据库中
				hisInfos = alarmTools.convertObject2HisInfo(operationObjectResult);
				saveHisAlarmInfos(hisInfos);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
		}
		return null;
	}

	/**
	 * 根据网元+槽位，查询所有历史告警信息，并保存到数据库中
	 * 
	 * @param siteId
	 *            网元数据库id
	 * @param slotNumberList
	 *            槽位设备id列表
	 * @return
	 * @throws Exception
	 */
	public String queryHisAlarmBySlots(Integer siteId,Integer slotNumber) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<Integer> siteIdList = null;
		List<HisAlarmInfo> hisInfos = null;
		String type = "queryHisAlarmBySlots";
		AlarmTools alarmTools=new AlarmTools();
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(siteId);
			operationObject = this.getOperationObject(siteId, slotNumber,type);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				// 如果查询成功，则保存数据库中(历史性能)
				hisInfos = alarmTools.convertObject2HisInfo(operationObjectResult);
				saveHisAlarmInfos(hisInfos);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return null;
	}

	

	/**
	 * 将历史告警信息保存到数据库中
	 * 
	 * @param hisInfos
	 */
	private void saveHisAlarmInfos(List<HisAlarmInfo> hisInfos)throws Exception {
		HisAlarmService_MB serevices = null;
		try {
			serevices = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			for (HisAlarmInfo hisInfo : hisInfos) {
//				if (!isExist(hisInfo)) {
					serevices.saveOrUpdate(hisInfo);
//				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(serevices);
		}
	}

	/**
	 * 判断告警信息是否已经存在
	 * 
	 * @param hisInfo
	 * @return
	 */
	private boolean isExist(HisAlarmInfo hisInfo) throws Exception {
		boolean flag = false;
		HisAlarmService_MB serevices = null;
		HisAlarmInfo condition = null;
		List<HisAlarmInfo> hisInfos = null;
		try {
			serevices = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			condition = new HisAlarmInfo();
			condition.setSiteId(hisInfo.getSiteId());
			condition.setObjectId(hisInfo.getObjectId());
			condition.setObjectName(hisInfo.getObjectName());
			condition.setObjectType(hisInfo.getObjectType());
			condition.setAlarmCode(hisInfo.getAlarmCode());
			condition.setAlarmTime(hisInfo.getAlarmTime());
			condition.setAlarmLevel(hisInfo.getAlarmLevel());
			hisInfos = serevices.select(condition);
			if (hisInfos != null && hisInfos.size() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(serevices);
		}
		return flag;
	}

	
	
	/**
	 * 根据网元id+槽位id列表，封装告警的OperationObject对象
	 * 
	 * @param siteId
	 *            网元数据库id
	 * @param slotNumberList
	 *            槽位设备id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(Integer siteId,Integer slotAddressId,String type) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		AlarmMasterCardObject object = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
	 		neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.getAlarmObject().setNeAddress(neObject.getNeAddress());
			object = new AlarmMasterCardObject();
			object.setMasterCardAddress(slotAddressId);
			actionObject.getAlarmObject().getAlarmMasterCardObjectList().add(object);
			
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	/**
	 * 根据网元id列表，封装告警的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(Integer siteId,String type)throws Exception {
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
			actionObject.getAlarmObject().setNeAddress(neObject.getNeAddress());
			actionObject.getAlarmObject().setAlarmMasterCardObjectList(getMasterCardObjectList(siteId));
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	/**
	 * 根据网元id，封装槽位信息AlarmMasterCardObject
	 * 
	 * @param id
	 *            数据库网元id
	 * @return
	 */
	private List<AlarmMasterCardObject> getMasterCardObjectList(Integer siteId)throws Exception {
		List<AlarmMasterCardObject> objectList = null;
		SlotService_MB slotService = null;
		SlotInst slotInst = null;
		List<SlotInst> slotInsts = null;
		AlarmMasterCardObject object = null;
		try {
			objectList = new ArrayList<AlarmMasterCardObject>();
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			slotInst = new SlotInst();
			slotInst.setSiteId(siteId);
			slotInsts = slotService.select(slotInst);
			for (SlotInst slot : slotInsts) {
				object = new AlarmMasterCardObject();
				object.setMasterCardAddress(slot.getNumber());
				objectList.add(object);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(slotService);
			slotInst = null;
			slotInsts = null;
			object = null;
		}
		return objectList;
	}
	
	/**
	 * 根据网元id列表，封装告警的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(SiteInst siteInst,String type)throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			neObject = new NEObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.getAlarmObject().setNeAddress(neObject.getNeAddress());
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public String queryShieldAlarm(Object object)throws Exception {
		
		OperationObject operationObject = null;
		String type = "queryShieldAlarm";
		AlarmShieldInfo alarmShieldInfo = null;
		try {
			alarmShieldInfo = (AlarmShieldInfo)object;
			SiteUtil siteUtil=new SiteUtil();
			if(siteUtil.SiteTypeUtil(alarmShieldInfo.getSiteId()) == 0){//非失连网元、非虚拟网元下发设备
				operationObject = this.getOperationAlarmShield(alarmShieldInfo,type);
				super.sendAction(operationObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			alarmShieldInfo = null;
		}
		return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}
	
	/**
	 * 根据网元id列表，封装告警的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationAlarmShield(AlarmShieldInfo alarmShieldInfo,String type)throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(alarmShieldInfo.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.getAlarmShieldObject().setNeShield(alarmShieldInfo.getNeShield());
			actionObject.getAlarmShieldObject().setLineOrAlarmCode(alarmShieldInfo.getLineOrAlarmCode());
			actionObject.getAlarmShieldObject().setShieldType(alarmShieldInfo.getShieldType());
			actionObject.getAlarmShieldObject().setAlarmShieldTypeList(getalarmShieldTypeList(alarmShieldInfo.getAlarmShieldTypeList()));
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	private List<AlarmShieldType> getalarmShieldTypeList(List<AlarmShieldInfo_t> alarmShieldTypeList) throws Exception{
		List<AlarmShieldType> alarmShieldList = new ArrayList<AlarmShieldType>();
		try {
			for(AlarmShieldInfo_t alarmShieldInfo_t : alarmShieldTypeList){
				AlarmShieldType alarmShieldType = new AlarmShieldType();
				alarmShieldType.setLineCountList(alarmShieldInfo_t.getLineCountList());
				alarmShieldType.setAreaCountList(alarmShieldInfo_t.getAreaCountList());
				alarmShieldType.setShieldAlarmCode(alarmShieldInfo_t.getShieldAlarmCode());
				alarmShieldType.setShieldAlarmCodeCountList(alarmShieldInfo_t.getShieldAlarmCodeCountList());
				alarmShieldList.add(alarmShieldType);
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmShieldList;
	}
}
