package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.ProtectRorateObject;
import com.nms.model.ptn.path.protect.PwProtectService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
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
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class ProtectRorateWHServicveImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		List<SiteRoate> siteRoates = null;
		List<Integer> siteIdList=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		try {
			siteRoates = (List<SiteRoate>) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList = this.getSiteId(siteRoates);
			
			if(siteIdList.size()>0){
				/**锁住该网元*/
				super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
				/**获取之前的portLag  回滚用*/
//				operationObjectBefore = this.getOperationBefore(siteIdList);
				/**入库*/
				for(SiteRoate siteRoate : siteRoates){
					operationObjectAfter=this.getOperationObject(siteRoate);
					super.sendAction(operationObjectAfter);
					
					operationObjectResult=super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						super.clearLock(siteIdList);
						return operationObjectResult.getActionObjectList().get(0).getStatus(); 
					}else{
						super.clearLock(siteIdList);
						return super.getErrorMessage(operationObjectResult);
					
					} 
				}
				
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
			
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			super.clearLock(siteIdList);
		}
		
		return ResultString.CONFIG_SUCCESS;
	}

	private OperationObject getOperationObject(SiteRoate siteRoate) {
		OperationObject operationObject=null;
		ActionObject actionObject=null;
		NEObject neObject=null;
		try {
			operationObject=new OperationObject();
			actionObject = new ActionObject();
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(siteRoate.getSiteId())+"")){
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteRoate.getSiteId());
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("protectRorate");
	 			actionObject.setProtectRorateObject(getProtectRorateObject(siteRoate));
				operationObject.getActionObjectList().add(actionObject);
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
				operationObject.getActionObjectList().add(actionObject);
			}	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			actionObject=null;
			neObject=null;
		}
		return operationObject;
	}


	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<SiteRoate> siteRoates = null;
		List<Integer> siteIdList=null;
		OperationObject operationObjectAfter=null;
		OperationObject operationObjectResult=null;
		List<Integer> isSuccess = null;
		try {
			isSuccess = new ArrayList<Integer>();
			siteRoates = (List<SiteRoate>)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList = this.getSiteId(siteRoates);
			if(siteIdList.size()>0){
				/**锁住该网元*/
				super.lockSite(siteIdList, SiteLockTypeUtil.PORTLAG_INSERT);
				/**获取之前的portLag  回滚用*/
//				operationObjectBefore = this.getOperationBefore(siteIdList);
				/**入库*/
				for(int i = 0; i< siteRoates.size(); i++){
					operationObjectAfter = this.getOperationObject(siteRoates.get(i));
					super.sendAction(operationObjectAfter);
					
					operationObjectResult = super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						super.clearLock(siteIdList);
						if(operationObjectResult.getActionObjectList().get(0).getStatus().equals(ResultString.CONFIG_SUCCESS)){
							isSuccess.add(i);
						}
					}else{
						super.clearLock(siteIdList);
					} 
				}
				if(isSuccess.size() == isSuccess.size()){
					return ResultString.CONFIG_SUCCESS;
				}else{
					return super.getErrorMessage(operationObjectResult);					
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
			
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			isSuccess = null;
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}

	private List<Integer> getSiteId(List<SiteRoate> siteRoates) throws Exception {
		List<Integer> idList = new ArrayList<Integer>();
		for(SiteRoate siteRoate : siteRoates){
			if(super.getManufacturer(siteRoate.getSiteId()) == EManufacturer.WUHAN.getValue() && siteRoate.getSiteId() >0 ){
				idList.add(siteRoate.getSiteId());
			}
		}
		return idList;
	}
	
	private ProtectRorateObject getProtectRorateObject(SiteRoate siteRoate){
		ProtectRorateObject protectRorateObject = new ProtectRorateObject();
		protectRorateObject.setRoate(siteRoate.getRoate());
		protectRorateObject.setSiteRorate(siteRoate.getSiteRoate());
		protectRorateObject.setRoateType(siteRoate.getType());
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		PwProtectService_MB pwProtectService = null;
		PwProtect pwProtect = null;
			try {
				if(siteRoate.getType().equals("tunnel")){
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = new Tunnel();
				tunnel.setTunnelId(siteRoate.getTypeId());
				if(tunnelService.selectNodeByTunnelId(tunnel) != null && tunnelService.selectNodeByTunnelId(tunnel).size()>0){
					tunnel = tunnelService.selectNodeByTunnelId(tunnel).get(0);
					if(siteRoate.getSiteId() == tunnel.getASiteId()){
						protectRorateObject.setTunnelbusinessid(tunnel.getAprotectId());
					}else if(siteRoate.getSiteId() == tunnel.getZSiteId()){
						protectRorateObject.setTunnelbusinessid(tunnel.getZprotectId());
					}
				}
				}else if(siteRoate.getType().equals("pw")){
					pwProtectService = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
					pwProtect = new PwProtect();
					pwProtect.setId(siteRoate.getTypeId());
					if(pwProtectService.select(pwProtect) !=null && pwProtectService.select(pwProtect).size()>0){
						pwProtect =pwProtectService.select(pwProtect).get(0);
						protectRorateObject.setPwbussinessid(pwProtect.getBusinessId());
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				UiUtil.closeService_MB(tunnelService);
				UiUtil.closeService_MB(pwProtectService);
				tunnel = null;
				pwProtect = null;
			}
		return protectRorateObject;
	}

}
