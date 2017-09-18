package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.ARPInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.drive.service.bean.ARPObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.ptn.ARPInfoService_MB;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.protect.PwProtectService_MB;
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

public class ArpWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		try {
			List<ARPInfo> arpInfoList = objectList;
			// 下发设备
			operationObject = this.getOperationObject(arpInfoList.get(0).getSiteId(), "arpManagement");
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;		
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return this.operateActionObject(object);
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		return this.operateActionObject(object);
	}
	
	private String operateActionObject(Object object){
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		try {
			ARPInfo arpInfo = (ARPInfo) object;
			// 下发设备
			operationObject = this.getOperationObject(arpInfo.getSiteId(), "arpManagement");
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;		
	}
	
	private OperationObject getOperationObject(int siteId,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			SiteUtil siteUtil = new SiteUtil();			
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){			
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType(type);
				actionObject.setArpObjectList(this.getArpInfoObjectList(siteId));								
				operationObject.getActionObjectList().add(actionObject);
			}else{				
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);			
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	private List<ARPObject> getArpInfoObjectList(int siteId) {
		ARPInfoService_MB service = null;
		List<ARPObject> arpObjList = new ArrayList<ARPObject>();
		try {
			service = (ARPInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ARP);
			List<ARPInfo> arpList = service.queryBySiteId(siteId);
			if(arpList != null && arpList.size() > 0){
				for (ARPInfo info : arpList) {
					ARPObject obj = new ARPObject();
					obj.setPwProtectId(info.getPwProtectId());
					obj.setSourceMac(this.transformMac(info.getSourceMac()));
					obj.setVlanEnabled(info.getVlanEnabled());
					obj.setVlanId(info.getVlanId());
					obj.setVlanPri(info.getVlanPri());
					obj.setSourceIp(info.getSourceIp());
					obj.setTargetIp(info.getTargetIp());
					obj.setEnabled(info.getEnabled());
					obj.setArpId(info.getPwProtectId());
					arpObjList.add(obj);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return arpObjList;
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
		OperationObject operaObj = new OperationObject();
		ARPInfoService_MB service = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				//成功，则与数据库进行同步 
				service = (ARPInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ARP);
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(actionObject.getArpObjectList(), siteId, service);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return ResultString.CONFIG_TIMEOUT;
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
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("arpSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	private void synchro_db(List<ARPObject> arpObjectList, int siteId, ARPInfoService_MB service) {
		try {
			List<ARPInfo> arpInfoList = this.getARPInfoList(arpObjectList, siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(ARPInfo arpInfo : arpInfoList){
				synchroUtil.arpSynchro(arpInfo, service);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private List<ARPInfo> getARPInfoList(List<ARPObject> arpObjectList, int siteId) {
		PwProtectService_MB service = null;
		DualInfoService_MB dualService = null;
		List<ARPInfo> arpInfoList = new ArrayList<ARPInfo>();
		try {
			service = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			dualService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
			if(arpObjectList != null && arpObjectList.size() > 0){
				for (ARPObject obj : arpObjectList) {
					ARPInfo info = new ARPInfo();
					info.setName("arp/"+siteId+"_"+super.getNowMS());
					info.setDualName(this.getDualName(obj.getPwProtectId(), siteId, service, dualService));
					info.setPwProtectId(obj.getPwProtectId());
					info.setSourceMac(obj.getSourceMac());
					info.setVlanEnabled(obj.getVlanEnabled());
					info.setVlanId(obj.getVlanId());
					info.setVlanPri(obj.getVlanPri());
					info.setSourceIp(obj.getSourceIp());
					info.setTargetIp(obj.getTargetIp());
					info.setEnabled(obj.getEnabled());
					info.setSiteId(siteId);
					arpInfoList.add(info);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(dualService);
			UiUtil.closeService_MB(service);
		}
		return arpInfoList;
	}

	private String getDualName(int pwProtectId, int siteId, PwProtectService_MB service, DualInfoService_MB dualService) {
		try {
			PwProtect pwProtect = new PwProtect();
			pwProtect.setSiteId(siteId);
			pwProtect.setBusinessId(pwProtectId);
			List<PwProtect> pwProtectList = service.select(pwProtect);
			if(pwProtectList != null && pwProtectList.size() > 0){
				for (PwProtect pwPro : pwProtectList) {
					DualInfo dual = dualService.queryById(pwPro.getServiceId());
					if(dual != null){
						return dual.getName();
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return "";
	}
}
