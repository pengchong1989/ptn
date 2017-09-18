package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.drivechenxiao.service.bean.ospf.OSPFAREAObject;
import com.nms.model.ptn.ecn.OSPFAREAService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OSPFAREACXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 获得OSPF对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		CXActionObject cxActionObject = null;
		OSPFAREAInfo OSPFAREAInfo = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			OSPFAREAInfo = (OSPFAREAInfo) object;
			cxActionObject = this.getCXObject(OSPFAREAInfo, action);

			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			OSPFAREAInfo = null;
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 获得CX对象
	 * 
	 * @param oSPFInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private CXActionObject getCXObject(OSPFAREAInfo OSPFAREAInfo, String action) throws Exception {

		if (OSPFAREAInfo == null) {
			throw new Exception("OSPFAREAInfo is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			cxActionObject = new CXActionObject();
			operationObject = new OperationObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(OSPFAREAInfo.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_OSPFAREA);
			cxActionObject.setAction(action);
			cxActionObject.setOSPFAREAObject(this.OSPFINFOtoOSPFOBJECT(OSPFAREAInfo));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	private OSPFAREAObject OSPFINFOtoOSPFOBJECT(OSPFAREAInfo OSPFAREAInfo) {
		OSPFAREAObject OSPFAREAObject = new OSPFAREAObject();
		OSPFAREAObject.setDefmetric(OSPFAREAInfo.getMetric()+"");
		OSPFAREAObject.setType(OSPFAREAInfo.getType());
		OSPFAREAObject.setNeId(OSPFAREAInfo.getArea_range());
		OSPFAREAObject.setNosummaries(OSPFAREAInfo.getSummary()==1?"true":"false");
		return OSPFAREAObject;
	}

	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		OSPFAREAInfo oSPFAREAInfo = null;
		String result = null;
		String errorMessage = null;
		int id = 0;
		OSPFAREAService_MB oSPFAREAService = null;
		try {
			oSPFAREAService = (OSPFAREAService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFAREA);
			oSPFAREAInfo = (OSPFAREAInfo) object;
			if(oSPFAREAInfo.getStatus()==0){
				id = oSPFAREAService.insert(oSPFAREAInfo);
				if(id!=0){
					result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}else{
				int count = oSPFAREAService.queryName(oSPFAREAInfo.getNeId(), oSPFAREAInfo.getArea_range());
				if (count > 0) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_OSPF_ID_EXIST);
				}

				operationObject = this.getOperationObject(oSPFAREAInfo, TypeAndActionUtil.ACTION_INSERT);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					id = oSPFAREAService.insert(oSPFAREAInfo);
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_DELETE));
					super.sendAction(operationObject);

					oSPFAREAService.delete(id);
					result = errorMessage;
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFAREAService);
		}
		return result;
	}
	public String excutionUpdate(Object object) throws Exception {
		// System.out.println("excutionUpdate");
		OperationObject operationObject = null;
		OSPFAREAInfo oSPFAREAInfo =null;
		String result = null;
		OSPFAREAService_MB oSPFAREAService = null;
		try {
			oSPFAREAService = (OSPFAREAService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFAREA);
			oSPFAREAInfo = (OSPFAREAInfo) object;
			if(oSPFAREAInfo.getStatus()==0){
				int n=oSPFAREAService.update(oSPFAREAInfo);
				if(n!=0){
					result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}else {
				
			}
			operationObject = this.getOperationObject(oSPFAREAInfo, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {

				int ospfInfoId = oSPFAREAService.update(oSPFAREAInfo);
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				throw new Exception(super.getErrorMessage(operationObject));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFAREAService);
		}
		return result;
	}
//	public String excutionUpdate(Object object) throws Exception {
//		try {
//			OSPFAREAInfo oSPFAREAInfo = (OSPFAREAInfo) object;
//			oSPFAREAService.update(oSPFAREAInfo);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		return "";
//	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		OSPFAREAInfo oSPFAREAInfo = null;
		String result = "";
		OSPFAREAService_MB ospfAREAService=null;
		try {
			ospfAREAService = (OSPFAREAService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFAREA);
			for (int i = 0; i < objectList.size(); i++) {
				oSPFAREAInfo = (OSPFAREAInfo) objectList.get(i);
				/*
				 * 未激活状态，只删除库
				 */
				if(oSPFAREAInfo.getStatus()==0){
					ospfAREAService.delete(oSPFAREAInfo);
				}
				else{
					
					operationObject = this.getOperationObject(oSPFAREAInfo, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						ospfAREAService.delete(oSPFAREAInfo.getId());
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			UiUtil.closeService_MB(ospfAREAService);
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OSPFAREAService_MB ospfAREAService=null;
		OSPFAREAInfo info=null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_OSPFAREA);
			ospfAREAService = (OSPFAREAService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFAREA);
			info=new OSPFAREAInfo();
			info.setNeId(siteId+"");
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//修改  库中数据  ，激活状态 改为 ： 未激活
				ospfAREAService.updateStatus(info);
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getOspfAREAObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(ospfAREAService);
			info=null;
		}

		return null;
	}

	/**
	 * 获取OperationObject对象 查询用
	 * 
	 * @author wangwf
	 * 
	 * @param operationObject
	 *            OperationObject对象
	 * @param siteId
	 *            网元id
	 * @param type
	 *                    
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getOperactionObject_select(OperationObject operationObject, int siteId, String type) throws Exception {
		CXActionObject actionObject = new CXActionObject();

		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		operationObject.getCxActionObjectList().add(actionObject);

	}

	/**
	 * 与数据库同步
	 * 
	 * @author wangwf
	 * 
	 * @param ospfAREAObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<OSPFAREAObject> ospfAREAObjectList, int siteId) throws Exception {

		if (null == ospfAREAObjectList) {
			throw new Exception("ospfAREAObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (OSPFAREAObject ospfAREAObject : ospfAREAObjectList) {

			OSPFAREAInfo ospfAREAInfo = new OSPFAREAInfo();
			String ospfAREAName = "ospfAREA_" + super.getNowMS();
			ospfAREAInfo.setName(ospfAREAName);
			ospfAREAInfo.setNeId(siteId+"");
			if(ospfAREAObject.getDefmetric()!=null&&!ospfAREAObject.getDefmetric().equals("")){
				ospfAREAInfo.setMetric(Integer.parseInt(ospfAREAObject.getDefmetric()));
			}else{
				ospfAREAInfo.setMetric(0);
			}
			
			ospfAREAInfo.setStatus(1);
			ospfAREAInfo.setArea_range(ospfAREAObject.getNeId());//  区域id  34
			
			String typ;
			if(ospfAREAObject.getType().equals("2")){
				typ="nssa";
			}else if(ospfAREAObject.getType().equals("1")){
				typ="stub";
			}else {
				typ="none";
			}
			ospfAREAInfo.setType(typ);
			ospfAREAInfo.setSummary(ospfAREAObject.getNosummaries().equals("true")?1:0);
			synchroUtil.ospfAreaSynchro(ospfAREAInfo, siteId);
		}
	}

}
