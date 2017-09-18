package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.Port2LayerAttr;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.Port2LayerObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.Port2LayerAttrService_MB;
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
import com.nms.ui.manager.UiUtil;

public class Port2LayerAttrWHServiceImpl extends WHOperationBase implements
		OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	private OperationObject getSendOperation(Port2LayerAttr port2LayerAttr) {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil = new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			actionObject = new ActionObject();
			if("0".equals(siteUtil.SiteTypeUtil(port2LayerAttr.getSiteId())+"")){
				neObject = whImplUtil.siteIdToNeObject(port2LayerAttr.getSiteId());
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("port2layer");
				actionObject.setPort2LayerObjectList(this.getPort2LayerList(port2LayerAttr.getSiteId()));
				operationObject.getActionObjectList().add(actionObject);
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return operationObject;
	}

	private List<Port2LayerObject> getPort2LayerList(int siteId) {
		List<Port2LayerObject> objectList = new ArrayList<Port2LayerObject>();
		Port2LayerAttrService_MB service = null;
		try {
			service = (Port2LayerAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT_2LAYER_ATTR);
			Port2LayerAttr condition = new Port2LayerAttr();
			condition.setSiteId(siteId);
			List<Port2LayerAttr> portAttrList = service.selectByCondition(condition);
			for (Port2LayerAttr port2LayerAttr : portAttrList) {
				Port2LayerObject object = new Port2LayerObject();
				CoderUtils.copy(port2LayerAttr, object);
				objectList.add(object);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return objectList;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		Port2LayerAttr port2LayerAttr = null;
		OperationObject operationObjectResult = null;
		try {
			port2LayerAttr = (Port2LayerAttr) object;
			OperationObject operationObject = this.getSendOperation(port2LayerAttr);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
