package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.MacLearningInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.MacLearningObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.MacLearningService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class MacLearningLimitWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<MacLearningInfo> macInfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		MacLearningService_MB service = null;
		List<MacLearningInfo> delete_beforeList = new ArrayList<MacLearningInfo>();
		try {
			service = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			macInfoList = (List<MacLearningInfo>)objectList;
			if(macInfoList != null){
				delete_beforeList.addAll(macInfoList);	
				service.delete(macInfoList);

			}
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macInfoList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_LEARN_DELETE);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					//如果下发失败,恢复到以前的数据
					if(delete_beforeList.size() > 0){
						for (MacLearningInfo m : delete_beforeList) {
							service.save(m);
						}
					}
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(service);
		}
	}
	
	@Override
	public String excutionUpdate(Object object) throws Exception {
		MacLearningInfo macInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<MacLearningInfo> macList = new ArrayList<MacLearningInfo>();
		MacLearningService_MB service = null;
		MacLearningInfo macInfo_before = null;
		try {
			service = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			macInfo = (MacLearningInfo)object;
			macInfo_before = service.selectById(macInfo.getId());
			service.update(macInfo);
			macList.add(macInfo);
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_LEARN_UPDATE);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					//如果下发失败,恢复到以前的数据
					service.update(macInfo_before);
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(service);
		}
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		MacLearningInfo macInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<MacLearningInfo> macList = new ArrayList<MacLearningInfo>();
		MacLearningService_MB service = null;
		try {
			service = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			macInfo = (MacLearningInfo)object;
			service.save(macInfo);
			macList.add(macInfo);
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_LEARN_INSERT);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					//如果下发失败,删除该条数据
					service.deleteByPortId(macInfo.getPortId());
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 获取网元id集合
	 * @param macList
	 * @return
	 * @throws Exception 
	 */
	private List<Integer> getSiteIds(List<MacLearningInfo> macList) throws Exception {
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();
			for (MacLearningInfo mac : macList) {
				if (mac.getSiteId() > 0) {
					if (!siteIds.contains(mac.getSiteId()) && super.getManufacturer(mac.getSiteId()) == EManufacturer.WUHAN.getValue()) {
						siteIds.add(mac.getSiteId());
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return siteIds;
	}

	/**
	 *  获取operationobject对象
	 * @param siteIdList
	 * @param macDelete 
	 * @param actionType 
	 * @return
	 * @throws Exception 
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (int siteId : siteIdList) {
				actionObject = new ActionObject();
				neObject = whImplUtil.siteIdToNeObject(siteId);
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("macLearningLimit");
				actionObject.setMacLearningObjectList(this.getMacObject(siteId));//实现MacInfo到MacObject的转换
				operationObject.getActionObjectList().add(actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
	private List<MacLearningObject> getMacObject(int siteId) throws Exception {
		MacLearningService_MB service = null;
		PortService_MB portService = null;
		List<MacLearningObject> objectList = new ArrayList<MacLearningObject>();
		List<MacLearningInfo> infoList = new ArrayList<MacLearningInfo>();
		List<PortInst> portList = new ArrayList<PortInst>();
		Map<Integer, Integer> portId_number = new HashMap<Integer,Integer>();
		PortInst port = null;
		MacLearningObject obj = null;
		try {
			service = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			port = new PortInst();
			port.setSiteId(siteId);
			portList = portService.select(port);
			for (PortInst p : portList) {
				portId_number.put(p.getPortId(), p.getNumber());
			}
			infoList = service.selectBySiteId(siteId);
			if(infoList.size() > 0){
				for (MacLearningInfo m : infoList) {
					obj = new MacLearningObject();
					obj.setPortId(portId_number.get(m.getPortId()));
					obj.setMacModel(m.getMacModel());
//					obj.setVlanId(m.getVlanId());
					obj.setMacCount(m.getMacCount());
					objectList.add(obj);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(service);
		}
		return objectList;
	}
	
	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
