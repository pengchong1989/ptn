package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;
import com.nms.drive.service.bean.GroupSpreadObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.ptn.path.GroupSpreadService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class GroupWHServiceImpl extends WHOperationBase implements
		OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<GroupSpreadInfo> infoList = null;
		GroupSpreadService_MB spreadService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<Integer> siteIdList = null;
		try {
			infoList = objectList;
			spreadService = (GroupSpreadService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.GROUPSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(infoList.get(0).getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			spreadService.delete(infoList);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0)
						.getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult,
						operationObjectBefore);
//				if (flag) {
//					System.out.println("回滚成功");
//				} else {
//					System.out.println("回滚失败");
//				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(spreadService);
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {

		GroupSpreadInfo info = null;
		GroupSpreadService_MB spreadService = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<Integer> siteIdList = null;
		try {
			info = (GroupSpreadInfo)object;
			spreadService = (GroupSpreadService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.GROUPSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(info.getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			spreadService.update(info);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0)
						.getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult,
						operationObjectBefore);
//				if (flag) {
//					System.out.println("回滚成功");
//				} else {
//					System.out.println("回滚失败");
//				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(spreadService);
		}
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		GroupSpreadInfo unicastInfo = null;
		GroupSpreadService_MB spreadService = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		List<Integer> siteIdList = null;
		try {
			unicastInfo =(GroupSpreadInfo)object;
			spreadService = (GroupSpreadService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.GROUPSPREAD);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(unicastInfo.getSiteId());
			operationObjectBefore = this.getOperationBefore(siteIdList);
			/** 入库 */
			spreadService.insert(unicastInfo);

			operationObjectAfter = this.getOperationObject(siteIdList);
			super.sendAction(operationObjectAfter);

			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0)
						.getStatus();
			} else {
//				spreadService.delete(unicastInfo);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(spreadService);
		}
	}

	private Map<Integer, ActionObject> getOperationBefore(
			List<Integer> siteIdList) throws Exception {
		Map<Integer, ActionObject> operationBefore = null;
		OperationObject operationObject = null;
		try {
			operationBefore = new HashMap<Integer, ActionObject>();
			operationObject = this.getOperationObject(siteIdList);
			for (ActionObject actionObject : operationObject
					.getActionObjectList()) {
				operationBefore.put(actionObject.getNeObject().getNeAddress(),
						actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
		}
		return operationBefore;
	}
	
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject=null;
		ActionObject actionObject=null;
		NEObject neObject=null;
		
		try {
			operationObject=new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(int siteId : siteIdList){
				actionObject=new ActionObject();
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){
					neObject = whImplUtil.siteIdToNeObject(siteId);
					List<GroupSpreadInfo> groupSpreadInfoList = new ArrayList<GroupSpreadInfo>();//通过网元ID保存tunnel
					actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
					actionObject.setNeObject(neObject);
					actionObject.setType("group");
	 				actionObject.setGroupSpreadObject(this.getGroupSpreadObject(siteId,groupSpreadInfoList));
					operationObject.getActionObjectList().add(actionObject);
				}else{
					actionObject.setStatus(ResultString.CONFIG_SUCCESS);
					operationObject.getActionObjectList().add(actionObject);
				}
				
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			actionObject=null;
			neObject=null;
		}
		return operationObject;
	}

	private List<GroupSpreadObject> getGroupSpreadObject(int siteId,List<GroupSpreadInfo> groupSpreadInfoList) {
		List<GroupSpreadObject> groupSpreadObjectList = null;
		GroupSpreadService_MB groupSpreadService = null;
		GroupSpreadInfo groupInfo = null;
		try {
			groupSpreadService = (GroupSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.GROUPSPREAD);
			groupInfo = new GroupSpreadInfo();
			groupInfo.setSiteId(siteId);
			groupSpreadInfoList = groupSpreadService.query(groupInfo);
			groupSpreadObjectList = new ArrayList<GroupSpreadObject>();
			for(GroupSpreadInfo groupSpreadInfo :groupSpreadInfoList){
				GroupSpreadObject groupSpreadObject = new GroupSpreadObject();
				groupSpreadObject.setAddressMAC(transformMac(groupSpreadInfo.getMacAddress()));
				groupSpreadObject.setChannelProt(groupSpreadInfo.getPortChoice());
				groupSpreadObject.setSMID(groupSpreadInfo.getSmId());
				groupSpreadObject.setVpls_Vs(groupSpreadInfo.getVpls_vs());
				groupSpreadObjectList.add(groupSpreadObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(groupSpreadService);
		}
		return groupSpreadObjectList;
	}

	private String transformMac(String mac){
		StringBuffer buffer = new StringBuffer();
		String[] strs = mac.split("-");
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if(i == strs.length-1){
				buffer.append(transformInt(str));
			}else{
				buffer.append(transformInt(str)+"-");
			}
		}
		return buffer.toString();
	}
	
	private int transformInt(String str){
		int first = 0;
		int last = 0;
		str.subSequence(0,1);
		if("0".equals(str.subSequence(0,1))){
			first = 0;
		}
		if("0".equals(str.substring(1))){
			last = 0;
		}
		if("1".equals(str.subSequence(0,1))){
			first = 1;
		}
		if("1".equals(str.substring(1))){
			last = 1;
		}
		if("2".equals(str.subSequence(0,1))){
			first = 2;
		}
		if("2".equals(str.substring(1))){
			last = 2;
		}
		if("3".equals(str.subSequence(0,1))){
			first = 3;
		}
		if("3".equals(str.substring(1))){
			last = 3;
		}
		if("4".equals(str.subSequence(0,1))){
			first = 4;
		}
		if("4".equals(str.substring(1))){
			last = 4;
		}
		if("5".equals(str.subSequence(0,1))){
			first = 5;
		}
		if("5".equals(str.substring(1))){
			last = 5;
		}
		if("6".equals(str.subSequence(0,1))){
			first = 6;
		}
		if("6".equals(str.substring(1))){
			last = 6;
		}
		if("7".equals(str.subSequence(0,1))){
			first = 7;
		}
		if("7".equals(str.substring(1))){
			last = 7;
		}
		if("8".equals(str.subSequence(0,1))){
			first = 8;
		}
		if("8".equals(str.substring(1))){
			last = 8;
		}
		if("9".equals(str.subSequence(0,1))){
			first = 9;
		}
		if("9".equals(str.substring(1))){
			last = 9;
		}
		if("A".equals(str.subSequence(0,1))){
			first = 10;
		}
		if("A".equals(str.substring(1))){
			last = 10;
		}
		if("B".equals(str.subSequence(0,1))){
			first = 11;
		}
		if("B".equals(str.substring(1))){
			last = 11;
		}
		if("C".equals(str.subSequence(0,1))){
			first = 12;
		}
		if("C".equals(str.substring(1))){
			last = 12;
		}
		if("D".equals(str.subSequence(0,1))){
			first = 13;
		}
		if("D".equals(str.substring(1))){
			last = 13;
		}
		if("E".equals(str.subSequence(0,1))){
			first = 14;
		}
		if("E".equals(str.substring(1))){
			last = 14;
		}
		if("F".equals(str.subSequence(0,1))){
			first = 15;
		}
		if("F".equals(str.substring(1))){
			last = 15;
		}
		return first*16+last;
	}
	/**
	 * 与设备同步静态组播
	 * @author guoqc
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public Object synchro(int siteId)throws Exception {
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = this.getSynchroOperationObject(siteId);//封装下发数据
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					/*成功，则与数据库进行同步*/
					this.synchro_db(actionObject.getGroupSpreadObject(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}

	/**
	 * 与数据库进行同步
	 * @param groupSpreadObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<GroupSpreadObject> groupSpreadObject,int siteId)throws Exception {
		List<GroupSpreadInfo> groupSpreadInfoList = null;
		try {
			groupSpreadInfoList = this.getGroupSpreadInfo(groupSpreadObject,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(GroupSpreadInfo groupSpreadInfo : groupSpreadInfoList){
				try {
					synchroUtil.groupSpreadSynchro(groupSpreadInfo);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 将设备信息封装到groupSpreadInfo表中
	 * @param groupSpreadObject
	 * @param siteId
	 * @return List<GroupSpreadInfo> 
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	private List<GroupSpreadInfo> getGroupSpreadInfo(List<GroupSpreadObject> groupSpreadObject, int siteId) throws Exception {
		List<GroupSpreadInfo> groupSpreadInfoList = new ArrayList<GroupSpreadInfo>();
		GroupSpreadInfo groupSpreadInfo = null;
		for(GroupSpreadObject groupSpreadObj : groupSpreadObject){
			groupSpreadInfo.setSmId(groupSpreadObj.getSMID());
			groupSpreadInfo.setVpls_vs(groupSpreadObj.getVpls_Vs());
			groupSpreadInfo.setPortChoice(groupSpreadObj.getChannelProt());
			groupSpreadInfo.setMacAddress(groupSpreadObj.getAddressMAC());
			groupSpreadInfo.setSiteId(siteId);
			groupSpreadInfoList.add(groupSpreadInfo);
		}
		return groupSpreadInfoList;
	}

	/**
	 * 封装下发数据
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("groupSpreadSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			 actionObject = null;
		}
		return operationObject;
	}
}
