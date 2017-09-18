package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.BlackWhiteMacObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.BlackWhiteMacService_MB;
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

public class BlackWhiteMacWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoList = null;
		BlackWhiteMacService_MB blackWhiteMacService = null;
		List<BlackAndwhiteMacInfo> deleteInfoBefore = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<BlackWhiteMacObject> blackWhiteMacObjectList = null;
		int siteId = 0 ;
		try {
			blackWhiteMacService = (BlackWhiteMacService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BLACKWHITEMAC);
			blackAndwhiteMacInfoList = (List<BlackAndwhiteMacInfo>) objectList;
			siteIdList = new ArrayList<Integer>();
			siteId = blackAndwhiteMacInfoList.get(0).getSiteId();
			siteIdList.add(siteId);
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.BLACKWHITEMAC_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if ("0".equals(siteUtil.SiteTypeUtil(siteId) + "")) {// //非失连网元、非虚拟网元下发设备s
				deleteInfoBefore = new ArrayList<BlackAndwhiteMacInfo>();
				//现将数据需要删除的数据取出来
				for(BlackAndwhiteMacInfo blackAndwhiteMacInfo : blackAndwhiteMacInfoList){
					deleteInfoBefore.addAll(blackWhiteMacService.selectByBlackAndwhiteMacInfo(blackAndwhiteMacInfo));
				}
				//在删除数据库中的数据
				for(BlackAndwhiteMacInfo blackAndwhiteMacInfo : blackAndwhiteMacInfoList){
					blackWhiteMacService.delete(blackAndwhiteMacInfo);
				}
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteId);
				blackWhiteMacObjectList = new ArrayList<BlackWhiteMacObject>();
				blackWhiteMacObjectList.addAll(this.getBlackWhiteMacObject(blackWhiteMacService,siteId));
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, blackWhiteMacObjectList, PtnServiceEnum.BLACKWHITEMAC);// 下发以太网环回
				operationObjectResult = super.verification(operationObjectAfter);// 获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {// 失败 将还原要删除的数据
					//在删除数据库中的数据
					for(BlackAndwhiteMacInfo blackAndwhiteMacInfo : blackAndwhiteMacInfoList){
						blackWhiteMacService.save(blackAndwhiteMacInfo);
					}
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				for(BlackAndwhiteMacInfo blackAndwhiteMacInfo : blackAndwhiteMacInfoList){
					blackWhiteMacService.delete(blackAndwhiteMacInfo);
				}
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(blackWhiteMacService);
			blackAndwhiteMacInfoList = null;
			deleteInfoBefore = null;
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
			neObject = null;
			blackWhiteMacObjectList = null;
		}
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		
		BlackAndwhiteMacInfo blackAndwhiteMacInfo = null;
		BlackWhiteMacService_MB blackWhiteMacService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<BlackWhiteMacObject> blackWhiteMacObjectList = null;
		int dbId = 0 ;//数据库组件ID
		try {
			blackWhiteMacService = (BlackWhiteMacService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BLACKWHITEMAC);
			blackAndwhiteMacInfo = (BlackAndwhiteMacInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(blackAndwhiteMacInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.BLACKWHITEMAC_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if ("0".equals(siteUtil.SiteTypeUtil(blackAndwhiteMacInfo.getSiteId()) + "")) {// //非失连网元、非虚拟网元下发设备s
				//现将数据保存在数据库中
				dbId = blackWhiteMacService.save(blackAndwhiteMacInfo);
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(blackAndwhiteMacInfo.getSiteId());
				blackWhiteMacObjectList = new ArrayList<BlackWhiteMacObject>();
				blackWhiteMacObjectList.addAll(this.getBlackWhiteMacObject(blackWhiteMacService,blackAndwhiteMacInfo.getSiteId()));
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, blackWhiteMacObjectList, PtnServiceEnum.BLACKWHITEMAC);// 
				operationObjectResult = super.verification(operationObjectAfter);// 获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {// 失败 将删除在数据库中的数据
					blackAndwhiteMacInfo.setId(dbId);
					blackWhiteMacService.delete(blackAndwhiteMacInfo);
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				blackWhiteMacService.save(blackAndwhiteMacInfo);
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(blackWhiteMacService);
			blackAndwhiteMacInfo = null;
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
			neObject = null;
			blackWhiteMacObjectList = null;
		}
		return null;
	}

	/**
	 * function:将数据库对象转换成适配对象
	 * 
	 * @param blackAndwhiteMacInfo
	 * @return
	 */
	private List<BlackWhiteMacObject> getBlackWhiteMacObject(BlackWhiteMacService_MB blackWhiteMacService,int siteId) {
		List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoFromDb = null;
		List<BlackWhiteMacObject> blackWhiteMacObjectList =null;
		BlackAndwhiteMacInfo blackAndwhiteMac = null;
		try {
			blackWhiteMacObjectList = new ArrayList<BlackWhiteMacObject>();
			blackAndwhiteMac =new BlackAndwhiteMacInfo();
			blackAndwhiteMac.setSiteId(siteId);
			blackAndwhiteMacInfoFromDb = blackWhiteMacService.selectByBlackAndwhiteMacInfo(blackAndwhiteMac);
			//将数据库对象转换成适配对象
			if(blackAndwhiteMacInfoFromDb !=null && !blackAndwhiteMacInfoFromDb.isEmpty()){
				for(BlackAndwhiteMacInfo blackAndwhiteMacInst : blackAndwhiteMacInfoFromDb){
					BlackWhiteMacObject BlackWhiteMacObject = new BlackWhiteMacObject();
					BlackWhiteMacObject.setVsId(blackAndwhiteMacInst.getElanBussinessId());
					BlackWhiteMacObject.setNameList(Integer.parseInt(UiUtil.getCodeById(blackAndwhiteMacInst.getNameList()).getCodeValue()));
					BlackWhiteMacObject.setMacAddress(CoderUtils.transformMac(blackAndwhiteMacInst.getMac()));
					blackWhiteMacObjectList.add(BlackWhiteMacObject);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			blackAndwhiteMacInfoFromDb = null;
			blackAndwhiteMac = null;
		}
		return blackWhiteMacObjectList;
	}
	@Override
	public String excutionUpdate(Object object) throws Exception {
		BlackAndwhiteMacInfo blackAndwhiteMacInfo = null;
		BlackWhiteMacService_MB blackWhiteMacService = null;
		BlackAndwhiteMacInfo saveUpdateBefore = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<BlackWhiteMacObject> blackWhiteMacObjectList = null;
		
		try {
			blackWhiteMacService = (BlackWhiteMacService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BLACKWHITEMAC);
			blackAndwhiteMacInfo = (BlackAndwhiteMacInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(blackAndwhiteMacInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.BLACKWHITEMAC_INSERT);
			SiteUtil siteUtil=new SiteUtil();
			if ("0".equals(siteUtil.SiteTypeUtil(blackAndwhiteMacInfo.getSiteId()) + "")) {// //非失连网元、非虚拟网元下发设备s
				//跟新之前将原来的数据查出来
				saveUpdateBefore = blackWhiteMacService.selectByBlackAndwhiteMacInfo(blackAndwhiteMacInfo).get(0);
				//在更新数据库中的数据
				blackWhiteMacService.update(blackAndwhiteMacInfo);
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(blackAndwhiteMacInfo.getSiteId());
				blackWhiteMacObjectList = new ArrayList<BlackWhiteMacObject>();
				blackWhiteMacObjectList.addAll(this.getBlackWhiteMacObject(blackWhiteMacService,blackAndwhiteMacInfo.getSiteId()));
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, blackWhiteMacObjectList, PtnServiceEnum.BLACKWHITEMAC);// 下发以太网环回
				operationObjectResult = super.verification(operationObjectAfter);// 获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					blackWhiteMacService.update(blackAndwhiteMacInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {// 失败
					//换成原来的数据
					blackWhiteMacService.update(saveUpdateBefore);
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			} else {
				blackWhiteMacService.update(blackAndwhiteMacInfo);
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(blackWhiteMacService);
			blackAndwhiteMacInfo = null;
			saveUpdateBefore = null;
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
			neObject = null;
			blackWhiteMacObjectList = null;
		}
		return null; 
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		BlackWhiteMacService_MB blackWhiteMacService = null;
		try {
			operationObject = super.getSynchroOperationObject(siteId, "blackwhitemacSynchro");
			super.sendAction(operationObject);//下发数据
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){ 
				/*成功，则与数据库进行同步*/					
				blackWhiteMacService = (BlackWhiteMacService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BLACKWHITEMAC);	
				blackWhiteMacService.deleteBySiteId(siteId);
				for(ActionObject actionObject : operationObjectResult.getActionObjectList()){
					this.synchro_db(actionObject.getBlackWhiteMacObjectList(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(blackWhiteMacService);
		}
		return ResultString.CONFIG_SUCCESS;
	}
	/**
	 * 与数据库进行同步
	 * @param macmanage
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<BlackWhiteMacObject> blackWhiteMacObjectList, int siteId)throws Exception {
		List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoList= null;
		try {
			blackAndwhiteMacInfoList = this.getBlackAndwhiteMacInfo(blackWhiteMacObjectList,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(BlackAndwhiteMacInfo blackAndwhiteMacInfo : blackAndwhiteMacInfoList){
				synchroUtil.blackwhiteSynchro(blackAndwhiteMacInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 将设备信息封装到BlackAndwhiteMacInfo表中
	 * @param staticUnicastList
	 * @param siteId
	 * @return List<BlackAndwhiteMacInfo>
	 * @throws Exception
	 */
	private List<BlackAndwhiteMacInfo> getBlackAndwhiteMacInfo(List<BlackWhiteMacObject> blackWhiteMacObjectList, int siteId) {
		List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoList = new ArrayList<BlackAndwhiteMacInfo>();
		BlackAndwhiteMacInfo blackAndwhiteMacInfo = null;
		for(BlackWhiteMacObject macObj : blackWhiteMacObjectList){
			try {
				blackAndwhiteMacInfo = new BlackAndwhiteMacInfo();
				blackAndwhiteMacInfo.setSiteId(siteId);
				blackAndwhiteMacInfo.setElanBussinessId(macObj.getVsId());
				blackAndwhiteMacInfo.setNameList(UiUtil.getCodeByValue("NAMELIST", macObj.getNameList()+"").getId());
				blackAndwhiteMacInfo.setMac(macObj.getMacAddress());
				blackAndwhiteMacInfoList.add(blackAndwhiteMacInfo);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return blackAndwhiteMacInfoList;
	}
}
