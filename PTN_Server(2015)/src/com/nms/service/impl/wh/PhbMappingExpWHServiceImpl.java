package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.PHBToEXPObject;
import com.nms.model.ptn.qos.QosMappingModeService_MB;
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

public class PhbMappingExpWHServiceImpl extends WHOperationBase implements OperationServiceI  {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		QosMappingMode modeInfo = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		List<Integer> siteIdList = new ArrayList<Integer>();
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			modeInfo = (QosMappingMode) object;
			siteIdList.add(modeInfo.getSiteId());
			if(siteIdList.size()>0){
				/*锁住当前网元*/
				super.lockSite(siteIdList, SiteLockTypeUtil.PHB_TO_EXP_UPDATE);
				/* 获取之前的qosMappingMode 回滚用 */
				operationObjectBefore = this.getOperationBefore(siteIdList);
				
				operationObjectAfter = this.getOperationObject(siteIdList);
				super.sendAction(operationObjectAfter);
				
				operationObjectResult = super.verification(operationObjectAfter);
				if (operationObjectResult.isSuccess()) {
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					super.rollback(operationObjectResult,operationObjectBefore);
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		}finally{
			super.clearLock(siteIdList);
		}
	}
	
	/**获取operationobject对象
	 * 
	 * @param siteIdList
	 *            网元ID
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Integer> siteIdList) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (int siteId : siteIdList) {
				actionObject = new ActionObject();
				if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){
					neObject = whImplUtil.siteIdToNeObject(siteId);
					actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
					actionObject.setNeObject(neObject);
					actionObject.setType("phbToexp");
					actionObject.setPhbToEXPObject(this.getPhbToExpObject(siteId));
					operationObject.getActionObjectList().add(actionObject);
				}else{
					actionObject.setStatus(ResultString.CONFIG_SUCCESS);
					operationObject.getActionObjectList().add(actionObject);
				}
				
			}

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private List<PHBToEXPObject> getPhbToExpObject(int siteId) throws Exception{
		List<PHBToEXPObject> phbList = new ArrayList<PHBToEXPObject>();
		QosMappingModeService_MB modeService = null;
		List<QosMappingMode> modeList = new ArrayList<QosMappingMode>();
		PHBToEXPObject phbObj = null;
		QosMappingMode mode = null;
		try {
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			mode = new QosMappingMode();
			mode.setSiteId(siteId);
			mode.setTypeName("PHB_EXP");
			modeList = modeService.queryByCondition(mode);
			int i = 1;
			for (QosMappingMode m : modeList) {
				phbObj = new PHBToEXPObject();
				phbObj.setPhb2EXPID(i);
				i++;
				for (QosMappingAttr a : m.getQosMappingAttrList()) {
					if(a.getGrade().equals("BE")&&a.getName().equals("TMC")){
						phbObj.setBeVCexp(a.getValue());
					}
					if(a.getGrade().equals("BE")&&a.getName().equals("TMP")){
						phbObj.setBeVPexp(a.getValue());
					}
					if(a.getGrade().equals("AF1")&&a.getName().equals("TMC")){
						phbObj.setAf1VC(a.getValue());
					}
					if(a.getGrade().equals("AF1")&&a.getName().equals("TMP")){
						phbObj.setAf1VP(a.getValue());
					}
					if(a.getGrade().equals("AF2")&&a.getName().equals("TMC")){
						phbObj.setAf2VC(a.getValue());
					}
					if(a.getGrade().equals("AF2")&&a.getName().equals("TMP")){
						phbObj.setAf2VP(a.getValue());
					}
					if(a.getGrade().equals("AF3")&&a.getName().equals("TMC")){
						phbObj.setAf3VC(a.getValue());
					}
					if(a.getGrade().equals("AF3")&&a.getName().equals("TMP")){
						phbObj.setAf3VP(a.getValue());
					}
					if(a.getGrade().equals("AF4")&&a.getName().equals("TMC")){
						phbObj.setAf4VC(a.getValue());
					}
					if(a.getGrade().equals("AF4")&&a.getName().equals("TMP")){
						phbObj.setAf4VP(a.getValue());
					}
					if(a.getGrade().equals("EF")&&a.getName().equals("TMC")){
						phbObj.setEfVC(a.getValue());
					}
					if(a.getGrade().equals("EF")&&a.getName().equals("TMP")){
						phbObj.setEfVP(a.getValue());
					}
					if(a.getGrade().equals("CS6")&&a.getName().equals("TMC")){
						phbObj.setCs6VC(a.getValue());
					}
					if(a.getGrade().equals("CS6")&&a.getName().equals("TMP")){
						phbObj.setCs6VP(a.getValue());
					}
					if(a.getGrade().equals("CS7")&&a.getName().equals("TMC")){
						phbObj.setCs7VC(a.getValue());
					}
					if(a.getGrade().equals("CS7")&&a.getName().equals("TMP")){
						phbObj.setCs7VP(a.getValue());
					}
				}
				phbList.add(phbObj);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(modeService);
		}
		return phbList;
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
	
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operaObj = new OperationObject();
		
		try {
		     //封装下发数据
			operaObj = super.getSynchroOperationObject(siteId, "phbMexpSynchro");
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					this.synchro_db(actionObject.getPhbToEXPObject(),siteId);
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
	private void synchro_db(List<PHBToEXPObject> list, int siteId)throws Exception {
		List<QosMappingMode>  qosMaplist = null;
		 try {
			 qosMaplist = this.getQoSMapInfo(list,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			if(!qosMaplist.isEmpty()){
				synchroUtil.phbAndexpSynchro(qosMaplist, "PHB_EXP");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	/**
	 * 同步之后转换成数据库对象
	 * @param phb2explist
	 * @param siteId
	 * @return
	 */
	private List<QosMappingMode>  getQoSMapInfo(List<PHBToEXPObject> phb2explist, int siteId)
	{
		List<QosMappingMode> qosMapList = new ArrayList<QosMappingMode>();
		
		for(int i = 0; i < phb2explist.size(); i++)
		{
			QosMappingMode qosmode =new QosMappingMode();
			List<QosMappingAttr> mapList = new ArrayList<QosMappingAttr>();
			QosMappingAttr attr1 =new QosMappingAttr();
			attr1.setGrade("BE");
			attr1.setName("TMC");
			attr1.setValue(phb2explist.get(i).getBeVCexp());
			mapList.add(attr1);
		
			QosMappingAttr attr2 =new QosMappingAttr();
			attr2.setGrade("BE");
			attr2.setName("TMP");
			attr2.setValue(phb2explist.get(i).getBeVPexp());
			mapList.add(attr2);
			
			QosMappingAttr attr3 =new QosMappingAttr();
			attr3.setGrade("AF1");
			attr3.setName("TMC");
			attr3.setValue(phb2explist.get(i).getAf1VC());
			mapList.add(attr3);
			
			QosMappingAttr attr4 =new QosMappingAttr();
			attr4.setGrade("AF1");
			attr4.setName("TMP");
			attr4.setValue(phb2explist.get(i).getAf1VP());
			mapList.add(attr4);
			
			QosMappingAttr attr5 =new QosMappingAttr();
			attr5.setGrade("AF2");
			attr5.setName("TMC");
			attr5.setValue(phb2explist.get(i).getAf2VC());
			mapList.add(attr5);
			
			QosMappingAttr attr6 =new QosMappingAttr();
			attr6.setGrade("AF2");
			attr6.setName("TMP");
			attr6.setValue(phb2explist.get(i).getAf2VP());
			mapList.add(attr6);
			
			QosMappingAttr attr7 =new QosMappingAttr();
			attr7.setGrade("AF3");
			attr7.setName("TMC");
			attr7.setValue(phb2explist.get(i).getAf3VC());
			mapList.add(attr7);
			
			QosMappingAttr attr8 =new QosMappingAttr();
			attr8.setGrade("AF3");
			attr8.setName("TMP");
			attr8.setValue(phb2explist.get(i).getAf3VP());
			mapList.add(attr8);
			
			QosMappingAttr attr9 =new QosMappingAttr();
			attr9.setGrade("AF4");
			attr9.setName("TMC");
			attr9.setValue(phb2explist.get(i).getAf4VC());
			mapList.add(attr9);
			
			QosMappingAttr attr10 =new QosMappingAttr();
			attr10.setGrade("AF4");
			attr10.setName("TMP");
			attr10.setValue(phb2explist.get(i).getAf4VP());
			mapList.add(attr10);
			
			QosMappingAttr attr11 =new QosMappingAttr();
			attr11.setGrade("EF");
			attr11.setName("TMC");
			attr11.setValue(phb2explist.get(i).getEfVC());
			mapList.add(attr11);
			
			QosMappingAttr attr12 =new QosMappingAttr();
			attr12.setGrade("EF");
			attr12.setName("TMP");
			attr12.setValue(phb2explist.get(i).getEfVP());
			mapList.add(attr12);
			
			QosMappingAttr attr13 =new QosMappingAttr();
			attr13.setGrade("CS6");
			attr13.setName("TMC");
			attr13.setValue(phb2explist.get(i).getCs6VC());
			mapList.add(attr13);
			
			QosMappingAttr attr14 =new QosMappingAttr();
			attr14.setGrade("CS6");
			attr14.setName("TMP");
			attr14.setValue(phb2explist.get(i).getCs6VP());
			mapList.add(attr14);
			
			QosMappingAttr attr15 =new QosMappingAttr();
			attr15.setGrade("CS7");
			attr15.setName("TMC");
			attr15.setValue(phb2explist.get(i).getCs7VC());
			mapList.add(attr15);
			
			QosMappingAttr attr16 =new QosMappingAttr();
			attr16.setGrade("CS7");
			attr16.setName("TMP");
			attr16.setValue(phb2explist.get(i).getCs7VP());
			mapList.add(attr16);
			
			qosmode.setSiteId(siteId);
			qosmode.setQosMappingAttrList(mapList);
			qosMapList.add(qosmode);
		}
		
		return qosMapList;
	}
	private OperationObject getSynchroOperationObject(int siteId)throws Exception {
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
			actionObject.setType("phbMexpSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}
}
