package com.nms.service.impl.wh;

import java.util.List;

import com.nms.db.bean.equipment.port.TdmLoopInfo;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TdmLoopObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;

public class TdmLoopBackWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		TdmLoopInfo tdm = (TdmLoopInfo) object;
		operationObject = this.getOperationObject(tdm);
		super.sendAction(operationObject);
		operationObjectResult = super.verification(operationObject);
		if (operationObjectResult.isSuccess()) {
			return operationObjectResult.getActionObjectList().get(0).getStatus();
		}else{
			return super.getErrorMessage(operationObjectResult);
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		 
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		 
		return null;
	}

	public OperationObject getOperationObject(TdmLoopInfo tdm){
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(tdm.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("tdmloopback");
			actionObject.setTdmLoopObject(this.getTdmLoopObject(tdm));
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private TdmLoopObject getTdmLoopObject(TdmLoopInfo tdm) {
		TdmLoopObject tdmObj = new TdmLoopObject();
		tdmObj.setSiteId(tdm.getSiteId());
		tdmObj.setLegId(tdm.getLegId());
		tdmObj.setLoopType(tdm.getLoopType());
		tdmObj.setSwitchStatus(tdm.getSwitchStatus());
 		return tdmObj;
	}
}
