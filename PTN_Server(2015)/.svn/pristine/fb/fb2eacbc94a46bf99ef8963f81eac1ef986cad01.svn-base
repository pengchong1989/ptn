package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.ObjectInfo;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

public class PerformanceCXServiceImpl extends CXOperationBase {

	public List<CurrentPerforInfo> selectPerformance(CurrentPerformanceFilter filter) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectresult = null;
		List<CurrentPerforInfo> currentPerforInfoList = null;
		operationObject = this.getOperationObject(filter);
		super.sendAction(operationObject);
		operationObject = super.verification(operationObject);
		operationObjectresult = operationObject;
		currentPerforInfoList = this.getPerformanceInfo(operationObjectresult,filter);
		return currentPerforInfoList;
	}
	
	public List<HisPerformanceInfo> selectPerformanceHis(List<Integer> siteIds,PerformanceTaskInfo filter) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectresult = null;
		List<HisPerformanceInfo> currentPerforInfoList = null;
		
		operationObject = this.getOperationHisObject(siteIds,filter);
		super.sendAction(operationObject);
		operationObject = super.verification(operationObject);
		operationObjectresult = operationObject;
		
		currentPerforInfoList = this.getPerformanceInfoHis(operationObjectresult,filter);
		return currentPerforInfoList;
	}
	/**
	 * 根据网元id列表，封装性能的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationHisObject(List<Integer> siteIds,PerformanceTaskInfo filter) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		List<SlotInst> slotInsts = null;
		try {
			operationObject = new OperationObject();
			for (Integer id : siteIds) {
				slotInsts = this.getslotId(id);
				if(slotInsts!=null&&slotInsts.size()>0){
					for (SlotInst slotInst : slotInsts) {
						cxneObject = super.getCXNEObject(slotInst.getSiteId());
						cxActionObject = new CXActionObject();
						cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
						cxActionObject.setType(TypeAndActionUtil.TYPE_PERFORMANCE);
						cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
						cxActionObject.setSlot(slotInst.getNumber());
						if(filter.getMonitorCycle().toString().equals(ResourceUtil.srcStr(StringKeysObj.OBJ_15_MINUTES))){
							cxActionObject.setPersvrTime("m15");
						}else{
							cxActionObject.setPersvrTime("h24");
						}
						cxActionObject.setCxNeObject(cxneObject);
						if (slotInst.getNumber() != 0 && slotInst.getNumber() != 4) {
							operationObject.getCxActionObjectList().add(cxActionObject);
						}
					}
				}
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			cxActionObject = null;
			cxneObject = null;
			slotInsts = null;
		}
		return operationObject;
	}

	/**
	 * 根据网元id列表，封装性能的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(CurrentPerformanceFilter filter) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		List<SlotInst> slotInsts = null;
		String persvrTime="";
		String persvrFileName="";
		try {
			operationObject = new OperationObject();
			for (Integer id : filter.getSiteInsts()) {
				
				slotInsts = this.getslotId(id);
				for (SlotInst slotInst : slotInsts) {
					cxneObject = super.getCXNEObject(slotInst.getSiteId());
					cxActionObject = new CXActionObject();
					cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
					cxActionObject.setType(TypeAndActionUtil.TYPE_PERFORMANCE);
					cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
					cxActionObject.setSlot(slotInst.getNumber());
					if(filter.getMonitorCycle()!=null){						
						if(filter.getMonitorCycle().toString().equals(ResourceUtil.srcStr(StringKeysObj.OBJ_15_MINUTES))){
							persvrTime="m15";
							persvrFileName=persvrTime+"-"+filter.getPerformanceMonitorTime()+".per";
						}else{
							persvrTime="h24";
							persvrFileName=persvrTime+"-"+filter.getPerformanceMonitorTime()+".per";
						}
					}
					cxActionObject.setPersvrTime(persvrTime);
					cxActionObject.setPersvrFileName(persvrFileName);
					cxActionObject.setCxNeObject(cxneObject);
					if (slotInst.getNumber() != 0 && slotInst.getNumber() != 4) {
						operationObject.getCxActionObjectList().add(cxActionObject);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			cxActionObject = null;
			cxneObject = null;
			slotInsts = null;			
			persvrTime="";
			persvrFileName="";
		}
		return operationObject;
	}

	/**
	 * 获取性能对象
	 * @param operationObject
	 * @return
	 * @throws Exception
	 */
	private List<CurrentPerforInfo> getPerformanceInfo(OperationObject operationObject,CurrentPerformanceFilter filter) throws Exception {
		List<CurrentPerforInfo> currentPerforInfos = null;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		ObjectInfo objectInfo = null;
		try {
			currentPerforInfos = new ArrayList<CurrentPerforInfo>();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = new SiteInst();
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				siteInst = new SiteInst();
				if (cxActionObject.getPersvrobjectList() != null) {
					for (int i = 0; i < cxActionObject.getPersvrobjectList().size(); i++) {
						PersvrObject persvrObject = cxActionObject.getPersvrobjectList().get(i);
						String siteid = cxActionObject.getCxNeObject().getNeIp();
						siteInst.setCellDescribe(siteid);
						siteInst = siteService.select(siteInst).get(0);

						CurrentPerforInfo currentperforinfo = new CurrentPerforInfo();
						objectInfo = super.converObjectIdAlarm(persvrObject.getObjId(), siteInst.getSite_Inst_Id());
						if(objectInfo != null && objectInfo.getObjectType() != null){
							currentperforinfo.setSiteName(cxActionObject.getCxNeObject().getNeIp());
							currentperforinfo.setSlotId(objectInfo.getSlotId());
							currentperforinfo.setSiteId(siteInst.getSite_Inst_Id());
							currentperforinfo.setObjectId(objectInfo.getObjectId());
							currentperforinfo.setObjectType(objectInfo.getObjectType());
							currentperforinfo.setObjectName(objectInfo.getObjectName());
							currentperforinfo.setStartTime(filter.getPerformanceMonitorTime());
							currentperforinfo.setPerformanceEndTime(filter.getPerformanceOverTime());
							currentperforinfo.setPerformanceCode(Integer.parseInt(persvrObject.getPerid()));
							currentperforinfo.setPerformanceValue(Integer.parseInt(persvrObject.getValue()));
							currentperforinfo.setPerformanceTime(DateUtil.getDate(this.date, DateUtil.FULLTIME));
							currentperforinfo.setMonitorCycle(filter.getMonitorCycle().getValue());				
							currentPerforInfos.add(currentperforinfo);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return currentPerforInfos;
	}

	/**
	 * 获取性能对象 HisPerformanceInfo
	 * @param operationObject
	 * @return
	 * @throws Exception
	 */
	private List<HisPerformanceInfo> getPerformanceInfoHis(OperationObject operationObject,PerformanceTaskInfo filter) throws Exception {
		List<HisPerformanceInfo> historytPerforInfos = null;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		ObjectInfo objectInfo = null;
		try {
			historytPerforInfos = new ArrayList<HisPerformanceInfo>();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = new SiteInst();
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				siteInst = new SiteInst();
				if (cxActionObject.getPersvrobjectList() != null) {
					for (int i = 0; i < cxActionObject.getPersvrobjectList().size(); i++) {
						PersvrObject persvrObject = cxActionObject.getPersvrobjectList().get(i);

						String siteid = cxActionObject.getCxNeObject().getNeIp();
						siteInst.setCellDescribe(siteid);
						siteInst = siteService.select(siteInst).get(0);

						HisPerformanceInfo hisperforinfo = new HisPerformanceInfo();
						objectInfo = super.converObjectIdAlarm(persvrObject.getObjId(), siteInst.getSite_Inst_Id());
						if(objectInfo != null && objectInfo.getObjectType() != null){
							hisperforinfo.setSiteName(cxActionObject.getCxNeObject().getNeIp());
							hisperforinfo.setSlotId(objectInfo.getSlotId());
							hisperforinfo.setSiteId(siteInst.getSite_Inst_Id());
							hisperforinfo.setObjectId(objectInfo.getObjectId());
							hisperforinfo.setObjectType(objectInfo.getObjectType());
							hisperforinfo.setObjectName(objectInfo.getObjectName());
							hisperforinfo.setPerformanceCode(Integer.parseInt(persvrObject.getPerid()));
							hisperforinfo.setPerformanceValue(Integer.parseInt(persvrObject.getValue()));
							hisperforinfo.setPerformanceTime(DateUtil.getDate(this.date, DateUtil.FULLTIME));
							hisperforinfo.setStartTime(filter.getCreateTime());
							hisperforinfo.setPerformanceEndTime(DateUtil.getDate(this.date, DateUtil.FULLTIME));
							//currentperforinfo.setMonitorCycle(EMonitorCycle.MIN15);
							hisperforinfo.setMonitorCycle(filter.getMonitorCycle());
							if(filter.getSlotCard()==null){
								hisperforinfo.setIsCardOrSite(1);
							}else{
								hisperforinfo.setIsCardOrSite(2);
							}
							historytPerforInfos.add(hisperforinfo);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return historytPerforInfos;
	}
	/**
	 * 获得槽位对象
	 * @param siteId 网元ID
	 * @return
	 * @throws Exception
	 */
	private List<SlotInst> getslotId(Integer siteId) throws Exception {
		SlotService_MB slotService = null;
		CardService_MB cardService = null;
		List<SlotInst> slotInstList = null;
		List<CardInst> cardInsts = null;
		CardInst cardInst = null;
		SlotInst slotInst = null;
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			cardInst = new CardInst();
			cardInst.setSiteId(siteId);
			cardInsts = cardService.select(cardInst);

			slotInst = new SlotInst();
			if(cardInsts!=null&&cardInsts.size()>0){
				slotInst.setId(cardInsts.get(0).getSlotId());
				slotInstList = slotService.select(slotInst);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(cardService);
			cardInsts = null;
			cardInst = null;
			slotInst = null;
		}
		return slotInstList;
	}
}
