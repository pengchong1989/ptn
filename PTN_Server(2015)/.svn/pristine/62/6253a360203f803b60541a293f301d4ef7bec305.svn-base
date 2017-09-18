package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.L2cpInfo;
import com.nms.drive.service.bean.L2CPinfoObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.L2CPService_MB;
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
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class L2CPWHService extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		L2cpInfo l2cpInfo = null;
		L2CPService_MB l2cpService = null;
		List<Integer> siteIdList = null;
		OperationObject OperationObject = null;
		OperationObject operationObjectResult = null;
		SiteUtil siteUtil = new SiteUtil();
		try {
			l2cpService = (L2CPService_MB)ConstantUtil.serviceFactory.newService_MB(Services.L2CPSERVICE);
			l2cpInfo = (L2cpInfo)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(l2cpInfo.getSiteId());
			//锁住该网元
			super.lockSite(siteIdList, SiteLockTypeUtil.L2CP_SERVICE_INSERT);
			//判断是否是失连网元
			if("0".equals(siteUtil.SiteTypeUtil(l2cpInfo.getSiteId())+"")){
				OperationObject = getOperationObject(l2cpInfo,"L2CPConfig");
				super.sendAction(OperationObject);
				operationObjectResult = super.verification(OperationObject);
				if(operationObjectResult.isSuccess()){
					l2cpService.save(l2cpInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				}else{
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				l2cpService.save(l2cpInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(l2cpService);
			siteUtil = null;
		}
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		L2cpInfo l2cpInfo = null;
		L2CPService_MB l2cpService = null;
		List<Integer> siteIdList = null;
		SiteUtil siteUtil = null;
		OperationObject OperationObject = null;
		OperationObject operationObjectResult = null;
		try {
			l2cpService = (L2CPService_MB)ConstantUtil.serviceFactory.newService_MB(Services.L2CPSERVICE);
			l2cpInfo = (L2cpInfo)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(l2cpInfo.getSiteId());
			//锁住该网元
			super.lockSite(siteIdList, SiteLockTypeUtil.L2CP_SERVICE_INSERT);
			siteUtil = new SiteUtil();
			//判断是否是失连网元
			if("0".equals(siteUtil.SiteTypeUtil(l2cpInfo.getSiteId())+"")){
				OperationObject = getOperationObject(l2cpInfo,"L2CPConfig");
				super.sendAction(OperationObject);
				operationObjectResult = super.verification(OperationObject);
				if(operationObjectResult.isSuccess()){
					l2cpService.update(l2cpInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				}else{
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				l2cpService.update(l2cpInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(l2cpService);
			siteUtil = null;
		}
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
        OperationObject operaObj = new OperationObject();		
		try {
			operaObj = super.getSynchroOperationObject(siteId, "l2cpSynchro");
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					if(actionObject.getL2CPinfoObject() != null){
					   this.synchro_db(actionObject.getL2CPinfoObject(),siteId);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}
	
	/**
	 * 与数据库进行同步
	 * @param globalObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(L2CPinfoObject l2cpInfoObject, int siteId)throws Exception {
		 L2cpInfo l2cpInfo = null;
		 L2CPService_MB l2cpService = null;
		 try {
			l2cpInfo = this.getL2cpInfo(l2cpInfoObject,siteId);			
			l2cpService = (L2CPService_MB) ConstantUtil.serviceFactory.newService_MB(Services.L2CPSERVICE);	
			//同步之前删除数据库数据
			SynchroUtil synchroUtil = new SynchroUtil();
			l2cpService.delete(siteId);
			synchroUtil.l2cpSynchro(l2cpInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(l2cpService);
			l2cpInfo = null;
		}
		
	}
	
	
	private OperationObject getOperationObject(L2cpInfo lecpInfo,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		L2CPinfoObject L2CPinfoObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(lecpInfo.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			L2CPinfoObject = new L2CPinfoObject();
			L2CPinfoObject = setL2CPinfoObjecttContrlList(lecpInfo);
			actionObject.setL2CPinfoObject(L2CPinfoObject);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			L2CPinfoObject = null;
			neObject = null;
			actionObject = null;
		}
		return operationObject;
	}

	//将数据库对象转换成驱动对象
	private L2CPinfoObject setL2CPinfoObjecttContrlList(L2cpInfo lecpInfo) {
		L2CPinfoObject L2CPinfoObject = null;
		try {
			L2CPinfoObject = new L2CPinfoObject();
			L2CPinfoObject.setL2cpEnable(lecpInfo.getL2cpEnable());
			L2CPinfoObject.setBpduTreaty(lecpInfo.getBpduTreaty());
			L2CPinfoObject.setLldpTreaty(lecpInfo.getLldpTreaty());
			L2CPinfoObject.setLacpTreaty(lecpInfo.getLacpTreaty());
			L2CPinfoObject.setIeeeTreaty(lecpInfo.getIeeeTreaty());
			L2CPinfoObject.setMacAddress(CoderUtils.transformMac(lecpInfo.getMacAddress()));
			L2CPinfoObject.setTreatyNumber(CoderUtils.transformMac(lecpInfo.getTreatyNumber()));
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return L2CPinfoObject;
	}
	
	/**
	 * 
	 * @param l2cpObject
	 * @param siteId
	 * @return l2cpInfo
	 * @throws Exception
	 */
	private L2cpInfo getL2cpInfo(L2CPinfoObject l2cpinfoObject, int siteId)throws Exception {
		L2cpInfo l2cpInfo = new L2cpInfo();
		try {
			l2cpInfo.setSiteId(siteId);
			l2cpInfo.setL2cpEnable(l2cpinfoObject.getL2cpEnable());
			l2cpInfo.setBpduTreaty(l2cpinfoObject.getBpduTreaty());
			l2cpInfo.setLldpTreaty(l2cpinfoObject.getLldpTreaty());
			l2cpInfo.setLacpTreaty(l2cpinfoObject.getLacpTreaty());
			l2cpInfo.setIeeeTreaty(l2cpinfoObject.getIeeeTreaty());
			l2cpInfo.setMacAddress(l2cpinfoObject.getMacAddress());
			l2cpInfo.setTreatyNumber(l2cpinfoObject.getTreatyNumber());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return l2cpInfo;
	}
	
	
}
