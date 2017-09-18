package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.EthServiceInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.EthServiceInfoObject;
import com.nms.drive.service.bean.EthServiceObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.ptn.EthService_MB;
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
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EthServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		EthService_MB ethService = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<EthServiceInfo> ethServiceInfoList = null;
		try {
			ethService = (EthService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHSERVICE);
			ethServiceInfoList = (List<EthServiceInfo>)objectList;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,ethServiceInfoList);
			if(null!= siteCheck){
				return siteCheck;
			}
			ethService.delete(ethServiceInfoList);
			// 下发设备
			if(super.getManufacturer(ethServiceInfoList.get(0).getSiteId()) == EManufacturer.WUHAN.getValue()){
				operationObject = this.getOperationObject(ethService,ethServiceInfoList.get(0).getSiteId(),"ETHSERVICE");
				super.sendAction(operationObject);
				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// 如果失败 将还原删除的数据
					ethService.save(ethServiceInfoList);
					return super.getErrorMessage(operationObjectResult);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ethService);
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {

		List<EthServiceInfo> ethServiceInst = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		EthService_MB ethService = null;
		List<EthServiceInfo> aclInfoUpdata = null;
		
		try {
			ethServiceInst =(List<EthServiceInfo>) object;
			
			aclInfoUpdata = new ArrayList<EthServiceInfo>();
			ethService = (EthService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHSERVICE);
			if(super.getManufacturer(ethServiceInst.get(0).getSiteId()) == EManufacturer.WUHAN.getValue()){
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,ethServiceInst);
				if(null!=siteCheck){
					return siteCheck;
				}
				// 创建时，先入库 在查询所有的下设备
				if (ethServiceInst.get(0).getId() <= 0) {
					 ethService.save(ethServiceInst);
				}
				// 更新 
				else {
					aclInfoUpdata = ethService.queryByNeID(ethServiceInst.get(0));
					ethService.update(ethServiceInst.get(0));
				}
				// 下设备
				operationObjectAfter = this.getOperationObject(ethService,ethServiceInst.get(0).getSiteId(),"ETHSERVICE");
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// 下设备失败时删除新建的数据
					if (ethServiceInst.get(0).getId() <= 0) {
						ethService.delete(ethServiceInst);
					}
					// 下设备失败时把已经跟新的数据还原
					else {
						ethService.update(aclInfoUpdata.get(0));
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(ethService);
			operationObjectAfter = null;
			operationObjectResult = null;
			aclInfoUpdata = null;
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 同步设备数据
	 * 
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		EthService_MB service = null;
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				//成功，则与数据库进行同步 
				 service = (EthService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHSERVICE);
				//与数据库比较之前，将数据库中的数据删除
				 service.deleteBySiteId(siteId);
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(service,actionObject.getEthServiceObject(), siteId);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(service);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
	}

	private OperationObject getOperationObject(EthService_MB ethService,int siteId,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		EthServiceObject ethServiceObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			ethServiceObject = new EthServiceObject();
			ethServiceObject.setEthServiceInfoObjectList(this.setEthServiceInfoObjectContrlList(ethService,siteId));
			actionObject.setEthServiceObject(ethServiceObject);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ethServiceObject = null;
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
		private List<EthServiceInfoObject> setEthServiceInfoObjectContrlList(EthService_MB ethService,int siteId) {
			
			List<EthServiceInfo> ethServiceInfoList = null;
			List<EthServiceInfoObject> ethServiceInfoObjectList = null;
			
			try {
				ethServiceInfoObjectList = new ArrayList<EthServiceInfoObject>();
				ethServiceInfoList = new ArrayList<EthServiceInfo>();
				ethServiceInfoList = ethService.select(siteId);
				
				if(ethServiceInfoList !=null && ethServiceInfoList.size()>0){
					for (EthServiceInfo ethServiceInfo : ethServiceInfoList) {
						EthServiceInfoObject ethServiceInfoObject = new EthServiceInfoObject();
						ethServiceInfoObject.setVlanIdObject(ethServiceInfo.getVlanId());
						ethServiceInfoObject.setPortLine1Object(ethServiceInfo.getPortLine1());
						ethServiceInfoObject.setPortLine2Object(ethServiceInfo.getPortLine2());
						ethServiceInfoObject.setPortLine3Object(ethServiceInfo.getPortLine3());
						ethServiceInfoObject.setPortLine4Object(ethServiceInfo.getPortLine4());
						ethServiceInfoObjectList.add(ethServiceInfoObject);
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				ethServiceInfoList = null;
			}
			return ethServiceInfoObjectList;
		}
		
    private OperationObject getSynchroOperationObject(int siteId) {
    	
			OperationObject operationObject = new OperationObject();
			try {
				WhImplUtil whImplUtil = new WhImplUtil();
				NEObject neObject = whImplUtil.siteIdToNeObject(siteId);
				ActionObject actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("ethServiceSynchro");
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
	private void synchro_db(EthService_MB service,EthServiceObject ethServiceObject, int siteId){
		SynchroUtil synchroUtil = null;
		List<EthServiceInfo> ethServiceList = null;
		try {
			ethServiceList = this.getFanList(ethServiceObject.getEthServiceInfoObjectList(), siteId);
			synchroUtil = new SynchroUtil();
			synchroUtil.updateEthService(service,ethServiceList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			synchroUtil = null;
			ethServiceList = null;
		}
	}
	
	private List<EthServiceInfo> getFanList(List<EthServiceInfoObject> list, int siteId) {
		
		List<EthServiceInfo> ethServiceList = new ArrayList<EthServiceInfo>();
		for (EthServiceInfoObject ethServiceObjectInfo : list) {
			EthServiceInfo ethServiceInfo = new EthServiceInfo();
			ethServiceInfo.setSiteId(siteId);
			ethServiceInfo.setVlanId(ethServiceObjectInfo.getVlanIdObject());
			ethServiceInfo.setPortLine1(ethServiceObjectInfo.getPortLine1Object());
			ethServiceInfo.setPortLine2(ethServiceObjectInfo.getPortLine2Object());
			ethServiceInfo.setPortLine3(ethServiceObjectInfo.getPortLine3Object());
			ethServiceInfo.setPortLine4(ethServiceObjectInfo.getPortLine4Object());
			ethServiceList.add(ethServiceInfo);
		}
		return ethServiceList;
	}
}
