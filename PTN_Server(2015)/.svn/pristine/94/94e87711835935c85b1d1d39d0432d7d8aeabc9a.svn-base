package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.SmartFan;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.SmartFanObject;
import com.nms.model.ptn.SmartFanService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class SmartFanWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
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
		SmartFanService_MB smartFanService = null;
		try {
			SmartFan fan = (SmartFan) object;
			List<Integer> siteIdList = new ArrayList<Integer>();
			siteIdList.add(fan.getSiteId());
			smartFanService = (SmartFanService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SMARTFNASERVICE);
			List<SmartFan> fanListBefore = smartFanService.selectAll(fan.getSiteId());
			smartFanService.update(fan);
			OperationObject operationObject = this.getOperationObject(smartFanService,fan);
			super.sendAction(operationObject);
			OperationObject operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()){
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				//回滚
				for (SmartFan smartFan : fanListBefore) {
					smartFanService.update(smartFan);
				}
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(smartFanService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}

	/**
	 * 获取操作对象
	 * @throws Exception 
	 */
	private OperationObject getOperationObject(SmartFanService_MB smartFanService,SmartFan fan) throws Exception {
		OperationObject operationObject = new OperationObject();
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			NEObject neObject = whImplUtil.siteIdToNeObject(fan.getSiteId());
			ActionObject actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("smartFan");
			actionObject.setSmartFanObjectList(this.getSmartObject(smartFanService,fan.getSiteId()));
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	private List<SmartFanObject> getSmartObject(SmartFanService_MB service,int siteId) throws Exception {
		
		List<SmartFanObject> fanObjectList = new ArrayList<SmartFanObject>();
		
		try {
			List<SmartFan> fanList = service.selectAll(siteId);
			if(fanList.size() == 3){
				int i = 1;
				for (SmartFan smartFan : fanList) {
					SmartFanObject fanObj = new SmartFanObject();
					fanObj.setId(i);//条目Id
					fanObj.setSiteId(siteId);
					fanObj.setWorkType(smartFan.getWorkType());
					fanObj.setGearLevel(smartFan.getGearLevel());
					fanObjectList.add(fanObj);
					i++;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return fanObjectList;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		SmartFanService_MB service = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				service = (SmartFanService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SMARTFNASERVICE);
				//成功，则与数据库进行同步 
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					if(!actionObject.getSmartFanObjectList().isEmpty()){
						this.synchro_db(service,actionObject.getSmartFanObjectList(), siteId);
					}else{
						return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
					}
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(service);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	private OperationObject getSynchroOperationObject(int siteId) {
		OperationObject operationObject = new OperationObject();
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			NEObject neObject = whImplUtil.siteIdToNeObject(siteId);
			ActionObject actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("smartFanSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	/**
	 * 转换后的信息与数据库进行对比
	 * @param list
	 * @param siteId
	 */
	private void synchro_db(SmartFanService_MB service,List<SmartFanObject> list, int siteId){
		try {
			List<SmartFan> fanList = this.getFanList(list, siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.updateSmartFan(service,fanList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private List<SmartFan> getFanList(List<SmartFanObject> list, int siteId) {
		List<SmartFan> fanList = new ArrayList<SmartFan>();
		for (SmartFanObject fan : list) {
			SmartFan fanInfo = new SmartFan();
			fanInfo.setWorkType(fan.getWorkType());
			fanInfo.setGearLevel(fan.getGearLevel());
			fanInfo.setSiteId(siteId);
			fanList.add(fanInfo);
		}
		return fanList;
	}
}
