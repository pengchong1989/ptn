package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.MacManagementInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.MacManageObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.MacManageService_MB;
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
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class MacManagementWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<MacManagementInfo> macInfoList = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		MacManageService_MB service = null;
		List<MacManagementInfo> delete_beforeList = new ArrayList<MacManagementInfo>();
		try {
			service = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);
			macInfoList = (List<MacManagementInfo>)objectList;
			if(macInfoList != null){
				for (MacManagementInfo m : macInfoList) {
					delete_beforeList.add(m);
					service.deleteById(m.getId());
				}
			}
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macInfoList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_DELETE);
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
						for (MacManagementInfo m : delete_beforeList) {
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
		MacManagementInfo macInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<MacManagementInfo> macList = new ArrayList<MacManagementInfo>();
		MacManageService_MB service = null;
		MacManagementInfo macInfo_before = null;
		try {
			service = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);
			macInfo = (MacManagementInfo)object;
			macInfo_before = service.selectById(macInfo.getId());
			service.update(macInfo);
			macList.add(macInfo);
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_UPDATE);
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
		MacManagementInfo macInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<MacManagementInfo> macList = new ArrayList<MacManagementInfo>();
		MacManageService_MB service = null;
		try {
			service = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);
			macInfo = (MacManagementInfo)object;
			service.save(macInfo);
			macList.add(macInfo);
			/**获取此mac下的所有网元id*/
			siteIdList = this.getSiteIds(macList);
			/**锁住此mac下的所有网元*/
			super.lockSite(siteIdList, SiteLockTypeUtil.MAC_INSERT);
			operationObjectAfter = this.getOperationObject(siteIdList);
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					//如果下发失败,删除该条数据
					service.deleteById(macInfo.getId());
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
	private List<Integer> getSiteIds(List<MacManagementInfo> macList) throws Exception {
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();
			for (MacManagementInfo mac : macList) {
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
				actionObject.setType("blackListMac");
				actionObject.setMacManageObjectList(this.getMacObject(siteId));//实现MacInfo到MacObject的转换
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
	
	private List<MacManageObject> getMacObject(int siteId) throws Exception {
		MacManageService_MB service = null;
		List<MacManageObject> objectList = new ArrayList<MacManageObject>();
		List<MacManagementInfo> infoList = new ArrayList<MacManagementInfo>();
		MacManageObject obj = null;
		PortService_MB portService = null;
		List<PortInst> portList = new ArrayList<PortInst>();
		Map<Integer, Integer> portId_number = new HashMap<Integer,Integer>();
		PortInst port = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			port = new PortInst();
			port.setSiteId(siteId);
			portList = portService.select(port);
			for (PortInst p : portList) {
				portId_number.put(p.getPortId(), p.getNumber());
			}
			service = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);
			infoList = service.selectBySiteId(siteId);
			if(infoList.size() > 0){
				for (MacManagementInfo m : infoList) {
					obj = new MacManageObject();
					obj.setPortId(portId_number.get(m.getPortId()));
					obj.setVlanId(m.getVlanId());
					obj.setMac(transformMac(m.getMac()));
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
	
	/**
	 * mac地址转换int
	 * @param mac
	 * @return
	 */
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

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		MacManageService_MB macManageService = null;
		try {
			operationObject = super.getSynchroOperationObject(siteId, "blackmaclistSynchro");
			super.sendAction(operationObject);//下发数据
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){ 
				/*成功，则与数据库进行同步*/					
				macManageService = (MacManageService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACMANAGE);	
				macManageService.deleteBySiteId(siteId);
				for(ActionObject actionObject : operationObjectResult.getActionObjectList()){
					this.synchro_db(actionObject.getMacManageObjectList(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(macManageService);
		}
		return ResultString.CONFIG_SUCCESS;
	}

	/**
	 * 与数据库进行同步
	 * @param macmanage
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<MacManageObject> macManageObjectList, int siteId)throws Exception {
		List<MacManagementInfo> macManagementInfoList= null;
		try {
			macManagementInfoList = this.getMacManageInfo(macManageObjectList,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(MacManagementInfo macManagementInfo : macManagementInfoList){
				synchroUtil.macManageSynchro(macManagementInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	
	/**
	 * 将设备信息封装到MacManagementInfo表中
	 * @param staticUnicastList
	 * @param siteId
	 * @return List<MacManagementInfo>
	 * @throws Exception
	 */
	private List<MacManagementInfo> getMacManageInfo(List<MacManageObject> macManageObjectList, int siteId) {
		List<MacManagementInfo> macManagementInfoList = new ArrayList<MacManagementInfo>();
		MacManagementInfo macManagementInfo = null;
		for(MacManageObject macObj : macManageObjectList){
			try {
				macManagementInfo = new MacManagementInfo();
				macManagementInfo.setSiteId(siteId);
				macManagementInfo.setId(macObj.getId());
				macManagementInfo.setVlanId(macObj.getVlanId());
				macManagementInfo.setMac(macObj.getMac());
				macManagementInfo.setPortId(macObj.getPortId());
				macManagementInfoList.add(macManagementInfo);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return macManagementInfoList;
	}
}
