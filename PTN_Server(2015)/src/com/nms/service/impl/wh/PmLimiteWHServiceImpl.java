package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.perform.PmValueLimiteInfo;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.PmValueLimiteObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.SiteLockTypeUtil;

public class PmLimiteWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		PmValueLimiteInfo pmValueLimiteInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;

		try {
			pmValueLimiteInfo = (PmValueLimiteInfo) object;
			/** 获取此网元ID */
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(pmValueLimiteInfo.getSiteId());
			/** 锁住此网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PW_INSERT);
			operationObjectAfter = this.getOperationObject(pmValueLimiteInfo);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
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
		PmValueLimiteInfo pmValueLimiteInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;

		try {
			pmValueLimiteInfo = (PmValueLimiteInfo) object;
			/** 获取此网元ID */
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(pmValueLimiteInfo.getSiteId());
			/** 锁住此网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.PW_INSERT);
			operationObjectAfter = this.getOperationObject(pmValueLimiteInfo);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
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
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = super.getSynchroOperationObject(siteId,"pmlimiteSynchro");//封装下发数据
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					this.synchro_db(actionObject.getPmValueLimiteObject(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}
	
	/**
	 * 与数据库进行同步
	 * @param PHBToEXPObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(PmValueLimiteObject pmValueObj, int siteId)throws Exception {
		 PmValueLimiteInfo	pmValueLimiteInfo  = null;
		 try {
			 pmValueLimiteInfo = getPmLimiInfo(pmValueObj, siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.pmlimiteSynchro(pmValueLimiteInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private PmValueLimiteInfo getPmLimiInfo(PmValueLimiteObject pmValueObj, int siteId)
	{
		PmValueLimiteInfo	pmValueLimiteInfo  = new PmValueLimiteInfo();
		pmValueLimiteInfo.setAlign(pmValueObj.getAlign());
		pmValueLimiteInfo.setCrcError(pmValueObj.getCrcError());
		pmValueLimiteInfo.setHighTemp(pmValueObj.getHighTemp());
		pmValueLimiteInfo.setLossNum(pmValueObj.getLossNum());
		pmValueLimiteInfo.setLowTemp(pmValueObj.getLowTemp());
		pmValueLimiteInfo.setReceiveBadWrap(pmValueObj.getReceiveBadWrap());
		pmValueLimiteInfo.setTmcLose(pmValueObj.getTmcLose());
		pmValueLimiteInfo.setSiteId(siteId);
		pmValueLimiteInfo.setTmcWorsen(pmValueObj.getTmcWorsen());
		pmValueLimiteInfo.setTmpLose(pmValueObj.getTmpLose());
		pmValueLimiteInfo.setTmpWorsen(pmValueObj.getTmpWorsen());
		pmValueLimiteInfo.setTmsLose(pmValueObj.getTmsLose());
		pmValueLimiteInfo.setTmsWorsen(pmValueObj.getTmsWorsen());
		
		return pmValueLimiteInfo;
	}
	private OperationObject getOperationObject(PmValueLimiteInfo pmValueLimiteInfo) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			actionObject = new ActionObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(pmValueLimiteInfo.getSiteId());
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("pmValueLimite");
			actionObject.setPmValueLimiteObject(getPmValueLimiteObject(pmValueLimiteInfo));
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 封装PmValueLimiteObject
	 * @param pmValueLimiteInfo
	 * @return
	 */
	private PmValueLimiteObject getPmValueLimiteObject(PmValueLimiteInfo pmValueLimiteInfo){
		PmValueLimiteObject pmValueLimiteObject = new PmValueLimiteObject();
		pmValueLimiteObject.setAlign(pmValueLimiteInfo.getAlign());
		pmValueLimiteObject.setCrcError(pmValueLimiteInfo.getCrcError());
		pmValueLimiteObject.setHighTemp(pmValueLimiteInfo.getHighTemp());
		pmValueLimiteObject.setLossNum(pmValueLimiteInfo.getLossNum());
		pmValueLimiteObject.setLowTemp(pmValueLimiteInfo.getLowTemp());
		pmValueLimiteObject.setReceiveBadWrap(pmValueLimiteInfo.getReceiveBadWrap());
		pmValueLimiteObject.setTmcLose(pmValueLimiteInfo.getTmcLose());
		pmValueLimiteObject.setTmcWorsen(pmValueLimiteInfo.getTmcWorsen());
		pmValueLimiteObject.setTmpWorsen(pmValueLimiteInfo.getTmpWorsen());
		pmValueLimiteObject.setTmpLose(pmValueLimiteInfo.getTmpLose());
		pmValueLimiteObject.setTmsLose(pmValueLimiteInfo.getTmsLose());
		pmValueLimiteObject.setTmsWorsen(pmValueLimiteInfo.getTmsWorsen());
		return pmValueLimiteObject;
	}
}
