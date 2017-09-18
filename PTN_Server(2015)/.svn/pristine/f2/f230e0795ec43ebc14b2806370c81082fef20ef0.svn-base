package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twaver.AlarmSeverity;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.TCAAlarm;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.enums.EObjectType;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.alarm.TCAAlarmService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.ObjectInfo;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.AlarmTools;

public class AlarmCXServiceImpl extends CXOperationBase {

	public void convertMap(List<AlarmObject> alarmObject_cx) throws Exception {
		if (null == alarmObject_cx || alarmObject_cx.size() == 0) {
			throw new Exception("alarmObject_cx没有值");
		}
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		CurrentAlarmInfo currentAlarmInfo = null;
		AlarmSeverity alarmSeverity = null;
		ObjectInfo objectInfo = null;
		CurAlarmService_MB curAlarmService = null;
		HisAlarmService_MB hisAlarmService = null;
		List<CurrentAlarmInfo> currentAlarmInfoList = null;
		List<Integer> curIds = new ArrayList<Integer>();
		List<SiteInst> siteInstList = null;
		DispatchBase dispatchBase=null;
		AlarmTools alarmTools = new AlarmTools();
		try {
			dispatchBase=new DispatchBase();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			
			for (AlarmObject alarmObject : alarmObject_cx) {
				siteInst = new SiteInst();
				siteInst.setCellDescribe(alarmObject.getCXNEObject().getNeIp());
				siteInstList = siteService.select(siteInst);
				if (null != siteInstList && siteInstList.size() > 0) {
					siteInst = siteInstList.get(0);
				} else {
					continue;
				}

				currentAlarmInfo = new CurrentAlarmInfo();
				currentAlarmInfo.setSiteName(siteInst.getCellDescribe());
				currentAlarmInfo.setSiteId(siteInst.getSite_Inst_Id());
				// currentAlarmInfo.setSiteAddress(Integer.parseInt(siteInst.getCellId()));
				currentAlarmInfo.setAlarmCode(Integer.parseInt(alarmObject.getCode()));
				currentAlarmInfo.setAlarmTime(alarmObject.getTime());
				objectInfo = super.converObjectIdAlarm(alarmObject.getObjId(), siteInst.getSite_Inst_Id());
				if (null == objectInfo) {
					continue;
				}
				currentAlarmInfo.setObjectId(objectInfo.getObjectId());
				currentAlarmInfo.setObjectName(objectInfo.getObjectName());
				currentAlarmInfo.setObjectType(objectInfo.getObjectType());
				alarmSeverity = alarmTools.getAlarmSeverity(Integer.parseInt(alarmObject.getLevel()));
				currentAlarmInfo.setAlarmSeverity(alarmSeverity);
				currentAlarmInfo.setAlarmLevel(Integer.parseInt(alarmObject.getLevel()));
				currentAlarmInfo.setRaisedTime(DateUtil.StringToDate(alarmObject.getTime(), DateUtil.FULLTIME));
				int slotId = currentAlarmInfo.getSlotId();
				if(slotId >=0){
					int num = dispatchBase.getSlotNumber(slotId);
					if(num !=-1){
						currentAlarmInfo.setSlotNumber(num);
					}
				}
				// 将存入内存的数据存入数据库中 modify by wangwf 20130415
				// 如果为清除警告删除当前警告表中数据插入历史表。如果为新增警告插入当前警告表
				if ("0".equals(alarmObject.getLevel())) {
					CurrentAlarmInfo condition = new CurrentAlarmInfo();
					condition.setObjectName(currentAlarmInfo.getObjectName());
					condition.setObjectType(currentAlarmInfo.getObjectType());
					condition.setAlarmLevel(Integer.parseInt(alarmObject.getLevel()));
					condition.setAlarmCode(currentAlarmInfo.getAlarmCode());
					currentAlarmInfoList = curAlarmService.select(condition);

					for (CurrentAlarmInfo obj : currentAlarmInfoList) {
						curIds.add(obj.getId());
						obj.setClearedTime(new Date());
						//上报清除告警
						obj.setAlarmLevel(0);
						hisAlarmService.saveOrUpdate(changeCurToHis(obj));
	
						dispatchBase.notifyCorba("alarm", MessageType.ALARM, obj ,"", ResultString.CONFIG_SUCCESS);
					}
					curAlarmService.delete(curIds);
				} else {
					curAlarmService.saveOrUpdate(currentAlarmInfo);
					//上报新增加告警
					dispatchBase.notifyCorba("alarm", MessageType.ALARM, currentAlarmInfo,"", ResultString.CONFIG_SUCCESS);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			UiUtil.closeService_MB(curAlarmService);
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(hisAlarmService);
		}
	}

	/**
	 * 查询告警
	 * 
	 * @param siteIds
	 *            多个网元ID
	 * @throws Exception
	 */
	public void selectAlarm(List<Integer> siteIds) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectresult = null;
		List<CurrentAlarmInfo> curInfos = null;
		CurAlarmService_MB curAlarmService = null;
		try {
			curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			operationObject = this.getOperationObject(siteIds);
			super.sendAction(operationObject);
			operationObjectresult = super.verification(operationObject);
			if (operationObjectresult.isSuccess()) {
				curInfos = this.getAlarmInfo(operationObjectresult);
				for (CurrentAlarmInfo curAlarmInfo : curInfos) {
					Boolean isExit = this.isExit(curAlarmInfo);
					// 如果不存在，保存此告警
					if (!isExit) {
						curAlarmService.saveOrUpdate(curAlarmInfo);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			UiUtil.closeService_MB(curAlarmService);
			operationObject = null;
			operationObjectresult = null;
			curInfos = null;
		}
	}

	/**
	 * 根据网元id列表，封装告警的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return OperationObject
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		try {
			operationObject = new OperationObject();
			if (siteIdList != null) {
				for (Integer id : siteIdList) {
					cxneObject = super.getCXNEObject(id);
					cxActionObject = new CXActionObject();
					cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
					cxActionObject.setType(TypeAndActionUtil.TYPE_ALARM);
					cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
					cxActionObject.setCxNeObject(cxneObject);
					operationObject.getCxActionObjectList().add(cxActionObject);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		}finally{
			cxneObject=null;
			cxActionObject=null;
		}
		return operationObject;
	}

	/**
	 * 获得当前告警
	 * 
	 * @param operationObject
	 * @return List<HisAlarmInfo> 集合
	 * @throws Exception
	 */
	private List<CurrentAlarmInfo> getAlarmInfo(OperationObject operationObject) throws Exception {
		List<CurrentAlarmInfo> currentAlarmInfos = null;
		SiteService_MB siteService = null;
		SlotService_MB slotService = null;
		SiteInst siteInst = null;
		SlotInst slotInst = null;
		ObjectInfo objectInfo = null;
		AlarmObject alarmObject = null;
		try {
			currentAlarmInfos = new ArrayList<CurrentAlarmInfo>();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			siteInst = new SiteInst();
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				siteInst = new SiteInst();
				if (cxActionObject.getAlarmObjectList() != null) {
					for (int i = 0; i < cxActionObject.getAlarmObjectList().size(); i++) {
						alarmObject = cxActionObject.getAlarmObjectList().get(i);

						String siteip = cxActionObject.getCxNeObject().getNeIp();
						siteInst.setCellDescribe(siteip);
						siteInst = siteService.select(siteInst).get(0);

						CurrentAlarmInfo currentAlarmInfo = new CurrentAlarmInfo();
						objectInfo = super.converObjectIdAlarm(alarmObject.getObjId(), siteInst.getSite_Inst_Id());
						if (objectInfo != null && objectInfo.getObjectType() != null) {

						slotInst = new SlotInst();
							if (EObjectType.PORT.getValue() == objectInfo.getObjectType().getValue()) {
								slotInst = slotService.select(objectInfo.getObjectId());
							} else {
						slotInst.setSiteId(siteInst.getSite_Inst_Id());
						slotInst = slotService.select(slotInst).get(0);
							}

						WarningLevel warningLevel = new WarningLevel();
						currentAlarmInfo.setSiteId(siteInst.getSite_Inst_Id());
						currentAlarmInfo.setSlotId(slotInst.getId());
						currentAlarmInfo.setAlarmCode(Integer.parseInt(alarmObject.getCode()));
						currentAlarmInfo.setRaisedTime(DateUtil.StringToDate(alarmObject.getTime(), DateUtil.FULLTIME));
						currentAlarmInfo.setAlarmTime(null);
						currentAlarmInfo.setClearedTime(null);
						currentAlarmInfo.setAlarmLevel(Integer.parseInt(alarmObject.getLevel()));
						warningLevel.setWarninglevel(Integer.parseInt(alarmObject.getLevel()));
						warningLevel.setWarningcode(Integer.parseInt(alarmObject.getCode()));
						// 如果是性能门限告警，添加性能关联字段
						if (null != alarmObject.getPerVal() && !"".equals(alarmObject.getPerVal())) {
							currentAlarmInfo.setCapabilityCode(Integer.parseInt(alarmObject.getPerVal()));
							currentAlarmInfo.setCapabilityIdentity(alarmObject.getPerid());
						}
						currentAlarmInfo.setWarningLevel(warningLevel);

							currentAlarmInfo.setObjectType(objectInfo.getObjectType());
							currentAlarmInfo.setObjectName(objectInfo.getObjectName());
							currentAlarmInfo.setObjectId(objectInfo.getObjectId());
							currentAlarmInfos.add(currentAlarmInfo);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(siteService);
		}
		return currentAlarmInfos;
	}

	/**
	 * 判断当前告警是否存在 如果存在的话 不入库
	 * 
	 * @param CurrentAlarmInfo
	 *            实体
	 * @return true or false
	 * @throws Exception
	 */
	private Boolean isExit(CurrentAlarmInfo curAlarmInfo) throws Exception {
		Boolean boolean1 = false;
		CurAlarmService_MB curAlarmService = null;
		HisAlarmService_MB hisAlarmService = null;
		List<CurrentAlarmInfo> alarmInfos = null;
		List<HisAlarmInfo> hisAlarmInfos = null;
		HisAlarmInfo hisAlarmInfo = null;
		TCAAlarmService_MB tcaAlarmService = null;
		TCAAlarm tcaAlarm = null;
		List<TCAAlarm> tcaAlarmList = null;
		try {
			hisAlarmInfo = getHisAlarm(curAlarmInfo);

			hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			hisAlarmInfos = hisAlarmService.select(hisAlarmInfo);

			// 查询是否在历史告警中存在
			if (hisAlarmInfos != null && hisAlarmInfos.size() > 0) {
				return true;
			}

			// 查询是否在当前告警中存在
			curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			alarmInfos = curAlarmService.select(curAlarmInfo);

			if (alarmInfos != null && alarmInfos.size() > 0) {
				return true;
			}

			// 查询是否在TCA告警中存在
			tcaAlarmService = (TCAAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TCAALARM);
			tcaAlarm = new TCAAlarm();
			// 根据网元ID、告警源、性能code、性能标识查询
			tcaAlarm.setSiteId(curAlarmInfo.getSiteId());
			tcaAlarm.setAlarmSource(curAlarmInfo.getObjectName());
			tcaAlarm.setCapabilityCode(curAlarmInfo.getCapabilityCode());
			tcaAlarm.setCapabilityIdentity(curAlarmInfo.getCapabilityIdentity());
			tcaAlarmList = tcaAlarmService.select(tcaAlarm);

			if (null != tcaAlarmList && tcaAlarmList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(curAlarmService);
			UiUtil.closeService_MB(hisAlarmService);
			UiUtil.closeService_MB(tcaAlarmService);
		}

		return boolean1;
	}

	/**
	 * 获得历史告警类
	 * 
	 * @param curAlarmInfo
	 * @return
	 */
	public HisAlarmInfo getHisAlarm(CurrentAlarmInfo curAlarmInfo) {
		HisAlarmInfo hisAlarmInfo = new HisAlarmInfo();
		hisAlarmInfo.setSiteId(curAlarmInfo.getSiteId());
		hisAlarmInfo.setSlotId(curAlarmInfo.getSlotId());
		hisAlarmInfo.setObjectId(curAlarmInfo.getObjectId());
		hisAlarmInfo.setObjectType(curAlarmInfo.getObjectType());
		hisAlarmInfo.setAlarmCode(curAlarmInfo.getAlarmCode());
		hisAlarmInfo.setAlarmLevel(curAlarmInfo.getAlarmLevel());
		hisAlarmInfo.setObjectName(curAlarmInfo.getObjectName());

		return hisAlarmInfo;
	}

	/**
	 * 将当前警告对象转换成历史警告对象
	 * 
	 * @param currentAlarmInfo
	 *            实体
	 * @return hisAlarmInfo
	 * @throws Exception
	 */
	private HisAlarmInfo changeCurToHis(CurrentAlarmInfo currentAlarmInfo) throws Exception {
		HisAlarmInfo hisAlarmInfo = new HisAlarmInfo();
		hisAlarmInfo.setSiteId(currentAlarmInfo.getSiteId());
		hisAlarmInfo.setSlotId(currentAlarmInfo.getSlotId());
		hisAlarmInfo.setObjectId(currentAlarmInfo.getObjectId());
		hisAlarmInfo.setObjectName(currentAlarmInfo.getObjectName());
		hisAlarmInfo.setObjectType(currentAlarmInfo.getObjectType());
		hisAlarmInfo.setAlarmCode(currentAlarmInfo.getAlarmCode());
		hisAlarmInfo.setAlarmDesc(currentAlarmInfo.getAlarmDesc());
		hisAlarmInfo.setAlarmLevel(currentAlarmInfo.getAlarmLevel());
		hisAlarmInfo.setClearedTime(currentAlarmInfo.getClearedTime());
		hisAlarmInfo.setAckUser(currentAlarmInfo.getAckUser());
		hisAlarmInfo.setRaisedTime(currentAlarmInfo.getRaisedTime());
		return hisAlarmInfo;
	}
}
