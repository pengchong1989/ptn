package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.StaticUnicastInfo;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.StaticUnicastObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.path.SingleSpreadService_MB;
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
import com.nms.ui.manager.UiUtil;

public class StaticUniWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<StaticUnicastInfo> infoList = null;
		SingleSpreadService_MB spreadService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<Integer> siteIdList = null;
		try {
			infoList = objectList;
			spreadService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(infoList.get(0).getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			spreadService.delete(infoList);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
				if (flag) {
					System.out.println("回滚成功");
				} else {
					System.out.println("回滚失败");
				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(spreadService);
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {

		StaticUnicastInfo info = null;
		SingleSpreadService_MB spreadService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<Integer> siteIdList = null;
		try {
			info = (StaticUnicastInfo) object;
			spreadService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(info.getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			spreadService.update(info);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
				if (flag) {
					System.out.println("回滚成功");
				} else {
					System.out.println("回滚失败");
				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(spreadService);
		}
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		StaticUnicastInfo unicastInfo = null;
		SingleSpreadService_MB spreadService = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		List<Integer> siteIdList = null;
		try {
			unicastInfo = (StaticUnicastInfo) object;
			spreadService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(unicastInfo.getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			/** 入库 */
			spreadService.insert(unicastInfo);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				// spreadService.delete(unicastInfo);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(spreadService);
		}
	}

	private Map<Integer, ActionObject> getOperationBefore(List<Integer> siteIdList) throws Exception {
		Map<Integer, ActionObject> operationBefore = null;
		OperationObject operationObject = null;
		try {
			operationBefore = new HashMap<Integer, ActionObject>();
			operationObject = this.getOperationObject(siteIdList);
			for (ActionObject actionObject : operationObject.getActionObjectList()) {
				operationBefore.put(actionObject.getNeObject().getNeAddress(), actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
		}
		return operationBefore;
	}

	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (int siteId : siteIdList) {
				neObject = whImplUtil.siteIdToNeObject(siteId);
				// actionObject=new ActionObject();
				// actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				// actionObject.setNeObject(neObject);
				// actionObject.setType("NNI");
				// actionObject.setProtObjectList(this.getProtObjectNNI(siteId));
				// operationObject.getActionObjectList().add(actionObject);
				List<StaticUnicastInfo> staticUnicastInfoList = new ArrayList<StaticUnicastInfo>();// 通过网元ID保存tunnel
				actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("Static");
				actionObject.setStaticUnicast(this.getStaticObject(siteId, staticUnicastInfoList));
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

	private List<StaticUnicastObject> getStaticObject(int siteId, List<StaticUnicastInfo> staticUnicastInfoList) {
		List<StaticUnicastObject> staticUnicastObjectList = null;
		SingleSpreadService_MB singelSpreadService = null;
		try {
			singelSpreadService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			staticUnicastInfoList = singelSpreadService.query(siteId);
			staticUnicastObjectList = new ArrayList<StaticUnicastObject>();
			for (StaticUnicastInfo staticUnicastInfo : staticUnicastInfoList) {
				StaticUnicastObject staticUnicastObject = new StaticUnicastObject();
				staticUnicastObject.setMacAddress(CoderUtils.transformMac(staticUnicastInfo.getMacAddress()));
				staticUnicastObject.setPortChoice(staticUnicastInfo.getPortChoice());
				staticUnicastObject.setSuId(staticUnicastInfo.getSuId());
				staticUnicastObject.setVplsVs(staticUnicastInfo.getVplsVs());
				staticUnicastObjectList.add(staticUnicastObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(singelSpreadService);
		}
		return staticUnicastObjectList;
	}
	
	/**
	 * 与设备同步静态单播配置
	 * @author guoqc
	 * @param siteId
	 * @return
	 * @exception
	 */
	public Object synchro(int siteId) throws Exception{
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		try {
			operationObject = super.getSynchroOperationObject(siteId, "staticUniSynchro");
			super.sendAction(operationObject);//下发数据
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operationObjectResult.getActionObjectList()){
					this.synchro_db(actionObject.getStaticUnicast(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}
	
	/**
	 * 与数据库进行同步
	 * @param staticUnicast
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<StaticUnicastObject> staticUnicastList, int siteId)throws Exception {
		List<StaticUnicastInfo> singleSpreadList = null;
		try {
			singleSpreadList = this.getSingleSpreadInfo(staticUnicastList,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(StaticUnicastInfo unicastInfo : singleSpreadList){
				synchroUtil.singleSpreadSynchro(unicastInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			singleSpreadList = null;
		}
		
	}

	/**
	 * 将设备信息封装到staticUnicastInfo表中
	 * @param staticUnicastList
	 * @param siteId
	 * @return List<StaticUnicastInfo>
	 * @throws Exception
	 */
	private List<StaticUnicastInfo> getSingleSpreadInfo(List<StaticUnicastObject> staticUnicastList, int siteId) {
		List<StaticUnicastInfo> unicastInfoList = new ArrayList<StaticUnicastInfo>();
		StaticUnicastInfo staticUnicastInfo = null;
		for(StaticUnicastObject unicastObj : staticUnicastList){
			try {
				staticUnicastInfo = new StaticUnicastInfo();
				staticUnicastInfo.setSuId(unicastObj.getSuId());
				staticUnicastInfo.setVplsVs(unicastObj.getVplsVs());
				staticUnicastInfo.setPortChoice(unicastObj.getPortChoice());
				staticUnicastInfo.setMacAddress(unicastObj.getMacAddress());
				staticUnicastInfo.setSiteId(siteId);
				unicastInfoList.add(staticUnicastInfo);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return unicastInfoList;
	}
}
