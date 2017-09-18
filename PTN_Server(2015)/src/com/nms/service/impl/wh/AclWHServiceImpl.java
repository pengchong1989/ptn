package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.AclInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.AclInfoObject;
import com.nms.drive.service.bean.AclObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.AclService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class AclWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		AclService_MB aclService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<AclInfo> aclInfoList = null;
		AclInfo aclInfo = null;
		
		try {
			aclInfo = new AclInfo();
			siteIdList = new ArrayList<Integer>();
			aclService = (AclService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ACLSERCVICE);
			aclInfoList = (List<AclInfo>)objectList;
			
			for(AclInfo aclInst:aclInfoList){
				siteIdList.add(aclInst.getSiteId());
			}
			aclInfo=aclInfoList.get(0);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,aclInfoList);
			if(null!=siteCheck){
				return siteCheck;
			}
			// 锁着网元
			super.lockSite(siteIdList, SiteLockTypeUtil.ACL_DELETE);
			for(AclInfo aclInfoOther:aclInfoList){
				// 在删除数据
				aclService.delete(aclInfoOther);
			}
			// 下发设备
			if(super.getManufacturer(aclInfo.getSiteId()) == EManufacturer.WUHAN.getValue()){
				operationObject = this.getOperationObject(aclInfo.getSiteId(),"ACLConfig");
				super.sendAction(operationObject);
				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// 如果失败 将还原删除的数据
					for(AclInfo rollBackAclInfo:aclInfoList){
						aclService.save(rollBackAclInfo);
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(aclService);
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		AclInfo  aclInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			aclInfo=(AclInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(aclInfo.getSiteId());
			if(aclInfo.getId()>0){
				// 锁着网元
				super.lockSite(siteIdList, SiteLockTypeUtil.ACL_UPDATE);
			}else{
				// 锁着网元
				super.lockSite(siteIdList, SiteLockTypeUtil.ACL_INSERT);
			}
			operationObjectAfter = this.getOperationObject(aclInfo.getSiteId(),"ACLConfig");
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
		} finally {
			super.clearLock(siteIdList);
		}
	}

	private OperationObject getOperationObject(int siteId,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		AclObject aclObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			aclObject = new AclObject();
			aclObject.setAclInfoObjectList(this.setAclInfoObjectContrlList(siteId));
			actionObject.setAclObject(aclObject);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			aclObject = null;
			neObject = null;
			actionObject = null;
		}
		return operationObject;
	}
	
 /**
  * 将数据库的对象转换成驱动对象
  * @param siteId
  * @return
  */
	private List<AclInfoObject> setAclInfoObjectContrlList(int siteId) {
		AclService_MB aclService = null;
		List<AclInfoObject> aclInfoObjectList = null;
		List<AclInfo> aclInfoList = null;
		AclInfoObject aclInfoObject = null;
		try {
			aclInfoObjectList = new ArrayList<AclInfoObject>();
			aclInfoList=new ArrayList<AclInfo>();
			aclService = (AclService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ACLSERCVICE);
			aclInfoList = aclService.select(siteId);
			
			if(aclInfoList !=null && aclInfoList.size()>0){
				for (AclInfo aclInfo : aclInfoList) {
					aclInfoObject = new AclInfoObject();
					aclInfoObject.setAct(aclInfo.getAct());
					aclInfoObject.setDirection(aclInfo.getDirection());
					aclInfoObject.setPortNumber(aclInfo.getPortNumber());
					aclInfoObject.setVlanId(aclInfo.getVlanId());
					aclInfoObject.setIsSourceMac(aclInfo.getIsSourceMac());
					if(!aclInfo.getSourceMac().equals("")){
						aclInfoObject.setSourceMac(CoderUtils.transformMac(aclInfo.getSourceMac()));
					}
					aclInfoObject.setIsPurposeMac(aclInfo.getIsPurposeMac());
					if(!aclInfo.getPurposeMac().equals("")){
						aclInfoObject.setPurposeMac(CoderUtils.transformMac(aclInfo.getPurposeMac()));
					}
					aclInfoObject.setIsSourceIp(aclInfo.getIsSourceIp());
					if(!aclInfo.getSourceIp().equals("")){
						aclInfoObject.setSourceIp(aclInfo.getSourceIp());
					}
					aclInfoObject.setIsPurposeIp(aclInfo.getIsPurposeIp());
					if(!aclInfo.getPurposeIp().equals("")){
						aclInfoObject.setPurposeIp(aclInfo.getPurposeIp());
					}
					aclInfoObject.setIsMatchCos(aclInfo.getIsMatchCos());
					aclInfoObject.setCosValue(aclInfo.getCosValue());
					aclInfoObject.setIsMatchDSCP(aclInfo.getIsMatchDSCP());
					aclInfoObject.setDscpValue(aclInfo.getDscpValue());
					aclInfoObject.setIsMatchTOS(aclInfo.getIsMatchTOS());
					aclInfoObject.setTosValue(aclInfo.getTosValue());
					aclInfoObject.setIsSourcePort(aclInfo.getIsSourcePort());
					aclInfoObject.setSourcePort(aclInfo.getSourcePort());
					aclInfoObject.setIsPurposePort(aclInfo.getIsPurposePort());
					aclInfoObject.setPurposePort(aclInfo.getPurposePort());
					aclInfoObject.setRuleObject(aclInfo.getRuleObject());
					aclInfoObject.setTreatyType(aclInfo.getTreatyType());
					aclInfoObjectList.add(aclInfoObject);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(aclService);
			aclInfoList = null;
			aclInfoObject = null;
		}
		return aclInfoObjectList;
	}
	
	
	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<AclInfo> getAclInfo(AclObject aclObject, int siteId)
	{
		List<AclInfo> acls = new ArrayList<AclInfo>();
		if(aclObject.getAclInfoObjectList()!=null && aclObject.getAclInfoObjectList().size()>0)
		{
			for(AclInfoObject aclObj : aclObject.getAclInfoObjectList())
			{
				AclInfo acl = new AclInfo();
				acl.setAct(aclObj.getAct());
				acl.setDirection(aclObj.getDirection());
				acl.setPortNumber(aclObj.getPortNumber());
				acl.setVlanId(aclObj.getVlanId());
				acl.setIsSourceMac(aclObj.getIsSourceMac());
				acl.setSourceMac(aclObj.getSourceMac());
				acl.setIsPurposeMac(aclObj.getIsPurposeMac());
				acl.setPurposeMac(aclObj.getPurposeMac());
				acl.setIsSourceIp(aclObj.getIsSourceIp());
				acl.setIsPurposeIp(aclObj.getIsPurposeIp());
				acl.setIsMatchCos(aclObj.getIsMatchCos());
				acl.setCosValue(aclObj.getCosValue());
				acl.setIsMatchDSCP(aclObj.getIsMatchDSCP());
				acl.setDscpValue(aclObj.getDscpValue());
				acl.setIsMatchTOS(aclObj.getIsMatchTOS());
				acl.setTosValue(aclObj.getTosValue());
				acl.setIsSourcePort(aclObj.getIsSourcePort());
				acl.setSourcePort(aclObj.getSourcePort());
				acl.setIsPurposePort(aclObj.getIsPurposePort());
				acl.setPurposePort(aclObj.getPurposePort());
				acl.setRuleObject(aclObj.getRuleObject());
				acl.setSourceIp(aclObj.getSourceIp());
				acl.setPurposeIp(aclObj.getPurposeIp());
				acl.setTreatyType(aclObj.getTreatyType());
				acl.setSiteId(siteId);
				acls.add(acl);
			}
		}
		return acls;
	}
	
	/**
	 * 与数据库进行同步
	 * @param aclObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(AclObject aclObject, int siteId)throws Exception {
		List<AclInfo> aclInfoList = null;
		 try {
			 aclInfoList = this.getAclInfo(aclObject,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.aclConfigSynchro(aclInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		AclService_MB aclService = null;
		try {
			operaObj = super.getSynchroOperationObject(siteId, "aclSynchro");
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){
				/*成功，则与数据库进行同步*/
				//同步之前删掉数据库数据
				aclService = (AclService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ACLSERCVICE);
				aclService.deleteBySiteId(siteId);
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					this.synchro_db(actionObject.getAclObject(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(aclService);
		}
		return ResultString.CONFIG_SUCCESS;
	}

}
