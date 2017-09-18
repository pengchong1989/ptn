package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.SsMacStudy;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.SecondMacStudyObject;
import com.nms.model.ptn.SecondMacStudyService_MB;
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

public class SsmacWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		SecondMacStudyService_MB secondMacStudyService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<SsMacStudy> ssMacStudyList = null;
		SsMacStudy ssMacStudy = null;
		
		try {
			ssMacStudy = new SsMacStudy();
			siteIdList = new ArrayList<Integer>();
			secondMacStudyService = (SecondMacStudyService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SECONDMACSTUDY);
			ssMacStudyList = (List<SsMacStudy>)objectList;			
			for(SsMacStudy macInst:ssMacStudyList){
				siteIdList.add(macInst.getSiteId());
			}
			ssMacStudy=ssMacStudyList.get(0);			
			// 锁着网元
			super.lockSite(siteIdList, SiteLockTypeUtil.ACL_DELETE);
			for(SsMacStudy ssMacStudyOther:ssMacStudyList){
				// 在删除数据
				secondMacStudyService.delete(ssMacStudyOther);
			}
			// 下发设备
			if(super.getManufacturer(ssMacStudy.getSiteId()) == EManufacturer.WUHAN.getValue()){
				operationObject = this.getOperationObject(ssMacStudy.getSiteId(),"secondMacStudy");
				super.sendAction(operationObject);
				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// 如果失败 将还原删除的数据
					for(SsMacStudy rollBackAclInfo:ssMacStudyList){
						secondMacStudyService.save(rollBackAclInfo);
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(secondMacStudyService);
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		
		SsMacStudy  ssMacStudy = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SecondMacStudyService_MB secondMacStudyService = null;
		int inserResule = 0;
		List<SsMacStudy> macList = null;
		List<SsMacStudy> macInfoUpdata = null;
		
		try {
			ssMacStudy=(SsMacStudy) object;
			macList = new ArrayList<SsMacStudy>();
			macList.add(ssMacStudy);
			macInfoUpdata = new ArrayList<SsMacStudy>();
			secondMacStudyService =(SecondMacStudyService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SECONDMACSTUDY);			
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(ssMacStudy.getSiteId());
			if(super.getManufacturer(ssMacStudy.getSiteId()) == EManufacturer.WUHAN.getValue()){
				if(ssMacStudy.getId()>0){
					// 锁着网元
					super.lockSite(siteIdList, SiteLockTypeUtil.SSMAC_UPDATE);
				}else{
					// 锁着网元
					super.lockSite(siteIdList, SiteLockTypeUtil.SSMAC_INSERT);
				}
				
				// 创建时，先入库 在查询所有的下设备
				if (ssMacStudy.getId() == 0) {
					inserResule = secondMacStudyService.save(ssMacStudy);
				}
				// 更新 
				else {
					macInfoUpdata = secondMacStudyService.selectBySsMacStudyAddressInfo(ssMacStudy);
					secondMacStudyService.update(ssMacStudy);
				}
				// 下设备
				operationObjectAfter = this.getOperationObject(ssMacStudy.getSiteId(),"secondMacStudy");
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);	
				if (operationObjectResult.isSuccess()) {						
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					// 下设备失败时删除新建的数据
					if (ssMacStudy.getId() <= 0) {
						SsMacStudy macInfoOther = new SsMacStudy();
						macInfoOther.setSiteId(ssMacStudy.getSiteId());
						macInfoOther.setId(inserResule);
						secondMacStudyService.delete(macInfoOther);
					}
					// 下设备失败时把已经跟新的数据还原
					else {
						secondMacStudyService.update(macInfoUpdata.get(0));
					}
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(secondMacStudyService);
			operationObjectAfter = null;
			operationObjectResult = null;
		}
	}

	private OperationObject getOperationObject(int siteId,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		try {
			WhImplUtil whImplUtil = new WhImplUtil();
			SiteUtil siteUtil=new SiteUtil();			
			operationObject = new OperationObject();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){			
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			actionObject.setSecondMacStudyObjectList(this.setMacInfoObjectContrlList(siteId));									
			operationObject.getActionObjectList().add(actionObject);
			}else{				
			actionObject.setStatus(ResultString.CONFIG_SUCCESS);
			operationObject.getActionObjectList().add(actionObject);			
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
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
	private List<SecondMacStudyObject> setMacInfoObjectContrlList(int siteId) {
		SecondMacStudyService_MB secondMacStudyService = null;
		List<SecondMacStudyObject> macInfoObjectList = null;	
		List<SsMacStudy> macInfoObjectList1 = null;	
		try {
			macInfoObjectList=new ArrayList<SecondMacStudyObject>();
			macInfoObjectList1=new ArrayList<SsMacStudy>();
			secondMacStudyService =(SecondMacStudyService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SECONDMACSTUDY);			
			macInfoObjectList1=secondMacStudyService.select(siteId);
			for(SsMacStudy secondMacStudyInfo :macInfoObjectList1){//pw
				SecondMacStudyObject secondMacStudyObject = new SecondMacStudyObject();
				secondMacStudyObject.setNum(secondMacStudyInfo.getNum());
				secondMacStudyObject.setVlan(secondMacStudyInfo.getVlan());
				secondMacStudyObject.setMacNum(secondMacStudyInfo.getMacCount());	
				secondMacStudyObject.setMacAddressList(secondMacStudyInfo.getMacAddressList());
			    secondMacStudyObject.setPortId(secondMacStudyInfo.getPortId());
				macInfoObjectList.add(secondMacStudyObject);
			}
			     		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(secondMacStudyService);
		}
		
		return macInfoObjectList;
	}
	
	
	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		SecondMacStudyService_MB secondMacStudyService = null;
		try {
			operationObject = super.getSynchroOperationObject(siteId, "secondMacStudySynchro");
			super.sendAction(operationObject);//下发数据
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){ 
				/*成功，则与数据库进行同步*/					
				secondMacStudyService = (SecondMacStudyService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SECONDMACSTUDY);	
				secondMacStudyService.deleteBySiteId(siteId);
				for(ActionObject actionObject : operationObjectResult.getActionObjectList()){
					this.synchro_db(actionObject.getsecondMacStudyObjectList(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(secondMacStudyService);
		}
		return ResultString.CONFIG_SUCCESS;
	}
	
	/**
	 * 与数据库进行同步
	 * @param staticUnicast
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<SecondMacStudyObject> secondMacStudyList, int siteId)throws Exception {
		List<SsMacStudy> ssMacStudyList= null;
		try {
			ssMacStudyList = this.getStaticSecondMacInfo(secondMacStudyList,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			for(SsMacStudy ssMacStudy : ssMacStudyList){
				synchroUtil.secondMacSynchro(ssMacStudy);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			ssMacStudyList=null;
		}
		
	}
	
	
	/**
	 * 将设备信息封装到staticUnicastInfo表中
	 * @param staticUnicastList
	 * @param siteId
	 * @return List<StaticUnicastInfo>
	 * @throws Exception
	 */
	private List<SsMacStudy> getStaticSecondMacInfo(List<SecondMacStudyObject> secondMacStudyList, int siteId) {
		List<SsMacStudy> ssMacStudyList = new ArrayList<SsMacStudy>();
		SsMacStudy ssMacStudy = null;
		for(SecondMacStudyObject macObj : secondMacStudyList){
			try {
				ssMacStudy = new SsMacStudy();
				ssMacStudy.setPortId(macObj.getPortId());
				ssMacStudy.setNum(macObj.getNum());
				ssMacStudy.setVlan(macObj.getVlan());
				ssMacStudy.setMacCount(macObj.getMacNum());
				ssMacStudy.setMacAddressList(macObj.getMacAddressList());
				ssMacStudy.setSiteId(siteId);
				String mac="";
				for(int i=0;i<macObj.getMacAddressList().size();i++){
					mac+=macObj.getMacAddressList().get(i)+"|";
				}
				ssMacStudy.setMacAddress(mac);
				ssMacStudyList.add(ssMacStudy);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return ssMacStudyList;
	}
}
