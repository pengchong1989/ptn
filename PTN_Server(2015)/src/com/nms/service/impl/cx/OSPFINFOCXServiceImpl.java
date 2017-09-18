package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFInfo;
import com.nms.drivechenxiao.service.bean.ospf.OSPFObject;
import com.nms.model.ptn.ecn.OSPFInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class OSPFINFOCXServiceImpl extends CXOperationBase implements OperationServiceI {
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
		OSPFInfo OSPFInfo = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			OSPFInfo = (OSPFInfo) object;
			cxActionObject = this.getCXObject(OSPFInfo, action);

			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			OSPFInfo = null;
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
	private CXActionObject getCXObject(OSPFInfo oSPFInfo, String action) throws Exception {

		if (oSPFInfo == null) {
			throw new Exception("oSPFInfo is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			cxActionObject = new CXActionObject();
			operationObject = new OperationObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(oSPFInfo.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_OSPF);
			cxActionObject.setAction(action);
			cxActionObject.setOSPFObject(this.OSPFINFOtoOSPFOBJECT(oSPFInfo));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	private OSPFObject OSPFINFOtoOSPFOBJECT(OSPFInfo ospfinfo) {
		OSPFObject OSPFObject = new OSPFObject();
		OSPFObject.setRt_id_area(ospfinfo.getRt_id_area());
		OSPFObject.setDefmetric(ospfinfo.getDefmetric());
		OSPFObject.setAbr(ospfinfo.getAbr());
		OSPFObject.setCompatiblerfc1583(ospfinfo.getCompatiblerfc1583());
		OSPFObject.setRefbandwidth(ospfinfo.getRefbandwidth());
		OSPFObject.setDistance(ospfinfo.getDistance());
		OSPFObject.setSpf_holdtime(ospfinfo.getSpf_holdtime());
		OSPFObject.setSpf_maxholdtime(ospfinfo.getSpf_maxholdtime());
		OSPFObject.setSpf_delay(ospfinfo.getSpf_delay());
		OSPFObject.setRefresh_time(ospfinfo.getRefresh_time());
		OSPFObject.setVersion(ospfinfo.getVersion());
		return OSPFObject;
	}

	public String excutionInsert(Object object) throws Exception {
		// System.out.println("excutionInsert");
		OperationObject operationObject = null;
		OSPFInfo OSPFInfo = null;
		String result = null;
		OSPFInfoService_MB oSPFInfoService = null;
		try {
			oSPFInfoService = (OSPFInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFInfo);
			OSPFInfo = (OSPFInfo) object;
			operationObject = this.getOperationObject(OSPFInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {

				int ospfInfoId = oSPFInfoService.insert(OSPFInfo);
				result = ospfInfoId + "";
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFInfoService);
		}
		return result;
	}

	public String excutionUpdate(Object object) throws Exception {
		// System.out.println("excutionUpdate");
		OperationObject operationObject = null;
		OSPFInfo OSPFInfo = null;
		String result = null;
		OSPFInfoService_MB oSPFInfoService = null;
		try {
			oSPFInfoService = (OSPFInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFInfo);
			OSPFInfo = (OSPFInfo) object;
			operationObject = this.getOperationObject(OSPFInfo, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {

				int ospfInfoId = oSPFInfoService.update(OSPFInfo);
				result = ospfInfoId + "";
			} else {
				throw new Exception(super.getErrorMessage(operationObject));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFInfoService);
		}
		return result;
	}

	public String haveOSPF(Object object) throws Exception {
		OperationObject operationObject = null;
		OSPFInfo OSPFInfo = null;
		String result = null;
		OSPFInfoService_MB oSPFInfoService = null;
		try {
			oSPFInfoService = (OSPFInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFInfo);
			OSPFInfo = (OSPFInfo) object;
			operationObject = this.getOperationObject(OSPFInfo, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {

				int ospfInfoId = oSPFInfoService.insert(OSPFInfo);
				result = ospfInfoId + "";
			} else {
				throw new Exception(super.getErrorMessage(operationObject));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFInfoService);
		}
		return result;
	}

	public String excutionDelete(Object object) throws Exception {
		OperationObject operationObject = null;
		OSPFInfo OSPFInfo = null;
		String result = null;
		OSPFInfoService_MB oSPFInfoService = null;
		try {
			oSPFInfoService = (OSPFInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFInfo);
			OSPFInfo = (OSPFInfo) object;
			operationObject = this.getOperationObject(OSPFInfo, TypeAndActionUtil.ACTION_DELETE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {

				oSPFInfoService.delete(OSPFInfo.getId());
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				throw new Exception(super.getErrorMessage(operationObject));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFInfoService);
		}
		return result;
	}

	public OSPFInfo excutionQuery(Object object) throws Exception {
		OSPFInfo OSPFInfo = null;
		OSPFInfoService_MB oSPFInfoService = null;
		try {
			OSPFInfo = (OSPFInfo) object;
			oSPFInfoService = (OSPFInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFInfo);
			OSPFInfo = oSPFInfoService.queryByNeID(Integer.parseInt(OSPFInfo.getNeId()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oSPFInfoService);
		}
		return OSPFInfo;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();

		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_OSPF);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getOSPFObject(), siteId);
			}
		} catch (Exception e) {
			throw e;
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
	 * @param ospfObject
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(OSPFObject ospfObject, int siteId) throws Exception {

		if (null == ospfObject) {
			throw new Exception("ospfObject is null");
		}

		OSPFInfo ospfInfo = new OSPFInfo();
		SynchroUtil synchroUtil = new SynchroUtil();
		ospfInfo.setNeId(siteId+"");
//		ospfInfo.setEnabled(false);
		ospfInfo.setRt_id_area(ospfObject.getRt_id_area());
		ospfInfo.setDefmetric(ospfObject.getDefmetric());
		ospfInfo.setAbr(ospfObject.getAbr());
		ospfInfo.setCompatiblerfc1583(ospfObject.getCompatiblerfc1583());
		ospfInfo.setRefbandwidth(ospfObject.getRefbandwidth());
		ospfInfo.setDistance(ospfObject.getDistance());
		ospfInfo.setSpf_delay(ospfObject.getSpf_delay());
		ospfInfo.setSpf_holdtime(ospfObject.getSpf_holdtime());
		ospfInfo.setSpf_maxholdtime(ospfObject.getSpf_maxholdtime());
		ospfInfo.setRefresh_time(ospfObject.getRefresh_time());
		ospfInfo.setVersion(ospfObject.getVersion());
		ospfInfo.setEnabled("1");
		
		
		synchroUtil.ospfInfoSynchro(ospfInfo, siteId);
	}

}
