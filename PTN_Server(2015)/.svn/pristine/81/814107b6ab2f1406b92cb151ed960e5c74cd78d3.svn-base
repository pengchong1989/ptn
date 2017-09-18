package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.RoundProtectionObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class WrappingWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<LoopProtectInfo> loopProtectInfoList = null;

		try {
			loopProtectInfoList = objectList;
			siteIdList = this.getsiteIdList(loopProtectInfoList);

			if (super.isLockBySiteIdList(siteIdList)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK);
			}

			super.lockSite(siteIdList, SiteLockTypeUtil.TUNNEL_DELETE);
			// operationObjectBefore = this.getOperationBefore(siteIdList);

			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// boolean flag = super.rollback(operationObjectResult,
					// operationObjectBefore);
					// if (flag) {
					// System.out.println("回滚成功");
					// } else {
					// System.out.println("回滚失败");
					// }
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
		}

	}

	@Override
	public String excutionInsert(Object object) throws Exception {

		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<LoopProtectInfo> loopProtectInfoList = null;
		loopProtectInfoList = (List<LoopProtectInfo>) object;
		siteIdList = this.getsiteIdList(loopProtectInfoList);

		try {
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
//					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// boolean flag = super.rollback(operationObjectResult,
					// operationObjectBefore);
					// if (flag) {
					// System.out.println("回滚成功");
					// } else {
					// System.out.println("回滚失败");
					// }
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
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {

		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<LoopProtectInfo> loopProtectInfoList = null;
		loopProtectInfoList = (List<LoopProtectInfo>) object;
		siteIdList = this.getsiteIdList(loopProtectInfoList);

		try {
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
//					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// boolean flag = super.rollback(operationObjectResult,
					// operationObjectBefore);
					// if (flag) {
					// System.out.println("回滚成功");
					// } else {
					// System.out.println("回滚失败");
					// }
//					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
		}
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}

	/**
	 * 获得此环网中所有网元id
	 * 
	 * @param loopProtectInfoList
	 * @return
	 * @throws Exception 
	 */
	private List<Integer> getsiteIdList(List<LoopProtectInfo> loopProtectInfoList) throws Exception {
		List<Integer> siteIdList = new ArrayList<Integer>();
		SiteUtil siteUtil=new SiteUtil();
		for (LoopProtectInfo loopProtectInfo : loopProtectInfoList) {
			if (!siteIdList.contains(loopProtectInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(loopProtectInfo.getSiteId())) {
				siteIdList.add(loopProtectInfo.getSiteId());
			}
		}
		return siteIdList;
	}

	/**
	 * 获取operationobject对象
	 * 
	 * @param siteIdList
	 *            网元ID
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (int siteId : siteIdList) {
				actionObject = new ActionObject();
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){
					neObject = whImplUtil.siteIdToNeObject(siteId);
					actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
					actionObject.setNeObject(neObject);
					actionObject.setType("wrapping");
					actionObject.setRoundProtectionObject(this.getLoopProtectObject(siteId));
					operationObject.getActionObjectList().add(actionObject);
				}else{
					actionObject.setStatus(ResultString.CONFIG_SUCCESS);
					operationObject.getActionObjectList().add(actionObject);
				}
				
			}

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private List<RoundProtectionObject> getLoopProtectObject(int siteId) {
		List<RoundProtectionObject> roundProtectionObjectList = new ArrayList<RoundProtectionObject>();
		List<LoopProtectInfo> loopProtectInfoList = null;
		WrappingProtectService_MB wrappingProtectService = null;
		
		PortService_MB portService = null;
		try {
			wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			LoopProtectInfo loopProtectInfo = new LoopProtectInfo();
			loopProtectInfo.setSiteId(siteId);
			loopProtectInfoList = wrappingProtectService.select(loopProtectInfo);
			for (LoopProtectInfo protectInfo : loopProtectInfoList) {
				PortInst westportInst = new PortInst();
				PortInst eastPortInst = new PortInst();
				RoundProtectionObject roundProtectionObject = new RoundProtectionObject();
				roundProtectionObject.setDelayTime(protectInfo.getDelaytime());
				roundProtectionObject.setEastLspId(protectInfo.getEastLspId());
				eastPortInst.setPortId(protectInfo.getEastPort());
				eastPortInst = portService.select(eastPortInst).get(0);
				roundProtectionObject.setEastPort(eastPortInst.getNumber());
				roundProtectionObject.setEastSlot(protectInfo.getEastSlot());
				roundProtectionObject.setLogicRoundID(protectInfo.getLogicId());
				roundProtectionObject.setNodeCount(protectInfo.getLoopNodeNumber());
				roundProtectionObject.setNodeID(protectInfo.getNodeId());
				roundProtectionObject.setProtectType(protectInfo.getProtectType());
				roundProtectionObject.setReturnType(protectInfo.getBackType());
				roundProtectionObject.setRoundID(protectInfo.getLoopId());
				roundProtectionObject.setTargetNodeId(protectInfo.getTargetNodeId());
				roundProtectionObject.setWestLspId(protectInfo.getWestLspId());
				westportInst.setPortId(protectInfo.getWestPort());
				westportInst = portService.select(westportInst).get(0);
				roundProtectionObject.setWestProt(westportInst.getNumber());
				roundProtectionObject.setWestSlot(protectInfo.getWestSlot());
				roundProtectionObject.setWaitTime(protectInfo.getWaittime());
				roundProtectionObjectList.add(roundProtectionObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(wrappingProtectService);
		}
		return roundProtectionObjectList;
	}

}
