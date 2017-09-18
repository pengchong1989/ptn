package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.drive.service.bean.EXPToPHBObject;
import com.nms.drive.service.bean.NEObject;
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

public class ExpMappingPhbWHServiceImpl extends WHOperationBase implements OperationServiceI{
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
				super.lockSite(siteIdList, SiteLockTypeUtil.EXP_TO_PHB_UPDATE);
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
					actionObject.setType("expTophb");
					actionObject.setExpToPHBObject(this.getExpToPhbObject(siteId));
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
	
	private int transGradetoValue(String grade)
	{
		int value = 0;
		if(grade.equals("BE"))
		{
			value = 0;
		}else if(grade.equals("AF1"))
		{
			value = 1;
		}else if(grade.equals("AF2"))
		{
			value = 2;
		}else if(grade.equals("AF3"))
		{
			value = 3;
		}else if(grade.equals("AF4"))
		{
			value = 4;
		}else if(grade.equals("EF"))
		{
			value = 5;
		}else if(grade.equals("CS6"))
		{
			value = 6;
		}else if(grade.equals("CS7"))
		{
			value = 7;
		}
		
		return value;
	}
	
	private List<EXPToPHBObject> getExpToPhbObject(int siteId)throws Exception{
		List<EXPToPHBObject> expList=new ArrayList<EXPToPHBObject>();
		QosMappingModeService_MB modeService=null;
		List<QosMappingMode> modeList=new ArrayList<QosMappingMode>();
		EXPToPHBObject expObj=null;
		QosMappingMode mode=null;
		try{
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			mode=new QosMappingMode();
			mode.setSiteId(siteId);
			mode.setTypeName("EXP_PHB");
			modeList=modeService.queryByCondition(mode);
			int j=1;
			for (QosMappingMode m : modeList) {
				expObj=new EXPToPHBObject();
				expObj.setExpPHBID(j);
				j++;
				for(int i=0;i<m.getQosMappingAttrList().size();i++)
				{
					QosMappingAttr attr = m.getQosMappingAttrList().get(i);
					if(attr.getValue() == 0)
					{
						expObj.setZero(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 1)
					{
						expObj.setOne(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 2)
					{
						expObj.setTwo(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 3)
					{
						expObj.setThree(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 4)
					{
						expObj.setFour(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 5)
					{
						expObj.setFive(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 6)
					{
						expObj.setSix(transGradetoValue(attr.getGrade()));
					}else if(attr.getValue() == 7)
					{
						expObj.setSeven(transGradetoValue(attr.getGrade()));
					}
				}
				expList.add(expObj);
			}
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(modeService);
		}
		return expList;
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
			operaObj = this.getSynchroOperationObject(siteId);//封装下发数据
			super.sendAction(operaObj);//下发数据
			super.verification(operaObj);//验证是否下发成功
			if(operaObj.isSuccess()){ 
				/*成功，则与数据库进行同步*/
				for(ActionObject actionObject : operaObj.getActionObjectList()){
					this.synchro_db(actionObject.getExpToPHBObject(),siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}
	/**
	 * 与数据库进行同步
	 * @param PHBToEXPObject
	 * @param siteId
	 * @throws Exception
	 */
	private void synchro_db(List<EXPToPHBObject> list, int siteId)throws Exception {
		List<QosMappingMode>  qosMaplist = null;
		 try {
			 qosMaplist = this.getQoSMapInfo(list,siteId);
			SynchroUtil synchroUtil = new SynchroUtil();
			if(!qosMaplist.isEmpty()){
				synchroUtil.phbAndexpSynchro(qosMaplist, "EXP_PHB");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private QosMappingAttr getAttrGrade(int typeValue)
	{
		QosMappingAttr attr = new QosMappingAttr();
		if(typeValue == 0)
		{
			attr.setGrade("BE");
		}else if(typeValue ==1)
		{
			attr.setGrade("AF1");
		}else if(typeValue == 2)
		{
			attr.setGrade("AF2");
		}else if(typeValue == 3)
		{
			attr.setGrade("AF3");
		}else if(typeValue == 4)
		{
			attr.setGrade("AF4");
		}else if(typeValue == 5)
		{
			attr.setGrade("EF");
		}else if(typeValue == 6)
		{
			attr.setGrade("CS6");
		}else if(typeValue == 7)
		{
			attr.setGrade("CS7");
		}
		attr.setValue(typeValue);
		
		return attr;
	}
	private List<QosMappingMode> getQoSMapInfo(List<EXPToPHBObject> list, int siteId)
	{
		List<QosMappingMode> modeList = new ArrayList<QosMappingMode>();
		for(EXPToPHBObject exp2phb : list)
		{
			QosMappingMode qosmode =new QosMappingMode();
			
			List<QosMappingAttr> attrList = new ArrayList<QosMappingAttr>();
			attrList.add(getAttrGrade(exp2phb.getZero()));
			attrList.add(getAttrGrade(exp2phb.getOne()));
			attrList.add(getAttrGrade(exp2phb.getTwo()));
			attrList.add(getAttrGrade(exp2phb.getThree()));
			attrList.add(getAttrGrade(exp2phb.getFour()));
			attrList.add(getAttrGrade(exp2phb.getFive()));
			attrList.add(getAttrGrade(exp2phb.getSix()));
			attrList.add(getAttrGrade(exp2phb.getSeven()));
			
			qosmode.setQosMappingAttrList(attrList);
			qosmode.setSiteId(siteId);
			modeList.add(qosmode);
		}
		
		return modeList;
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
			actionObject.setType("exp2phbSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}

}
