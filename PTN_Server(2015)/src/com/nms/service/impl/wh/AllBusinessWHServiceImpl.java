package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.bean.NEObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;

public class AllBusinessWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		 
		return null;
	}

	/**
	 * 查询设备，获取设备上所有单盘信息
	 * @param siteIdList
	 * @return
	 * @throws Exception
	 */
	public List<ActionObject> queryAllBusiness(int siteId) throws Exception {
		List<ActionObject> actionObjectList = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		try {
			actionObjectList = new ArrayList<ActionObject>();
			operationObject = this.getOperationObject(siteId);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				actionObjectList = operationObjectResult.getActionObjectList();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationObject = null;
			operationObjectResult = null;
		}
		return actionObjectList;
	}

	/**
	 * 根据网元id列表，封装告警的OperationObject对象
	 * 
	 * @param siteIdList网元id列表
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			actionObject = new ActionObject();
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteId);
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.getAlarmObject().setNeAddress(neObject.getNeAddress());
				actionObject.setType("allBusiness");
				operationObject.getActionObjectList().add(actionObject);
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}
	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
