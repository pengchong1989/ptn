package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;
import com.nms.drivechenxiao.service.bean.redistribute.RedistributeObject;
import com.nms.model.ptn.ecn.RedistributeService_MB;
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

public class RedistributeCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 获得Operation对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		CXActionObject cxActionObject = null;
		OspfRedistribute ospfRedistribute = null;
		OperationObject operationObject = null;
		RedistributeService_MB redistributeService = null;
		try {
			operationObject = new OperationObject();
			redistributeService = (RedistributeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.REDISTRIBUTE);
			ospfRedistribute = (OspfRedistribute) object;
			cxActionObject = this.getCXObject(ospfRedistribute, action);

			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(redistributeService);
			ospfRedistribute = null;
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
	private CXActionObject getCXObject(OspfRedistribute ospfRedistribute, String action) throws Exception {

		if (ospfRedistribute == null) {
			throw new Exception("OSPFAREAInfo is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(ospfRedistribute.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_REDISTRIBUTE);
			cxActionObject.setAction(action);
			cxActionObject.setRedistributeObject(this.OSPFRedistributetoOSPFRedistributeOBJECT(ospfRedistribute));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	private RedistributeObject OSPFRedistributetoOSPFRedistributeOBJECT(OspfRedistribute ospfRedistribute) {
		RedistributeObject redistributeObject = new RedistributeObject();
		redistributeObject.setType(ospfRedistribute.getType());
		redistributeObject.setEnable(ospfRedistribute.getEnable() ? 0 : 1);
		redistributeObject.setRedistribute_type(ospfRedistribute.getRedistribute_type());
		redistributeObject.setMetric(ospfRedistribute.getMetric());
		redistributeObject.setMetrictype(ospfRedistribute.getMetrictype());
		return redistributeObject;
	}

	public String excutionInsert(Object object) throws Exception {
		// System.out.println("excutionInsert");
		OperationObject operationObject = null;
		OspfRedistribute ospfRedistribute = null;
		String result = null;
		RedistributeService_MB redistributeService = null;
		try {
			ospfRedistribute = (OspfRedistribute) object;
			redistributeService = (RedistributeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.REDISTRIBUTE);
			int count = redistributeService.queryName(ospfRedistribute);
			if (count > 0) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_REDISTRIBUTE_EXIST);
			}

			operationObject = this.getOperationObject(ospfRedistribute, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				redistributeService.insert(ospfRedistribute);
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(redistributeService);
		}
		return result;
	}

	public String excutionUpdate(Object object) throws Exception {
		OspfRedistribute ospfRedistribute = (OspfRedistribute) object;
		OperationObject operationObject = null;
		String result = null;
		RedistributeService_MB redistributeService = null;
		try {
			redistributeService = (RedistributeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.REDISTRIBUTE);
			operationObject = this.getOperationObject(ospfRedistribute, TypeAndActionUtil.ACTION_UPDATE);
			/**
			 * 未激活时，操作 库，
			 */
			if(ospfRedistribute.getStatus()==0){
				int n=redistributeService.update(ospfRedistribute);
				if(n!=0){
					result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}else {
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					redistributeService.update(ospfRedistribute);
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(redistributeService);
			}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		RedistributeService_MB redistributeService = null;
		OperationObject operationObject = null;
		OspfRedistribute ospfRedistribute = null;
		String result = null;
		try {
			redistributeService = (RedistributeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.REDISTRIBUTE);
			for (int i = 0; i < objectList.size(); i++) {
				ospfRedistribute = (OspfRedistribute) objectList.get(i);
				/**
				 * 未激活时，只删除 库，
				 */
				if(ospfRedistribute.getStatus()==0){
					int n=redistributeService.delete(ospfRedistribute);
					if(n!=0){
						result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				}else {
					operationObject = this.getOperationObject(ospfRedistribute, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						redistributeService.delete(ospfRedistribute);
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				}
				
				}
				
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(redistributeService);
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		OspfRedistribute ospfRedistribute=null;
		RedistributeService_MB redistributeService = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_REDISTRIBUTE);
			redistributeService = (RedistributeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.REDISTRIBUTE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				ospfRedistribute=new OspfRedistribute();
				ospfRedistribute.setNeId(siteId+"");
				redistributeService.updateStatus(ospfRedistribute);
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getRedistributeObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(redistributeService);
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
	 * @param redistributeObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<RedistributeObject> redistributeObjectList, int siteId) throws Exception {

		if (null == redistributeObjectList) {
			throw new Exception("redistributeObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (RedistributeObject redistributeObject : redistributeObjectList) {

			OspfRedistribute ospfRedistribute = new OspfRedistribute();
			String ospfRedistributeName = "ospfRedistribute_" + super.getNowMS();
			ospfRedistribute.setName(ospfRedistributeName);
			ospfRedistribute.setNeId(siteId+"");
			ospfRedistribute.setRedistribute_type(redistributeObject.getRedistribute_type());
			if(redistributeObject.getType().equals("1")){
				ospfRedistribute.setType("RIB");
			}else if(redistributeObject.getType().equals("2")){
				ospfRedistribute.setType("AWAYLS");
			}
			ospfRedistribute.setStatus(1);
			ospfRedistribute.setMetrictype(redistributeObject.getMetrictype().equals("1")?"TYPE1":"TYPE2");
			ospfRedistribute.setMetric(redistributeObject.getMetric());
			ospfRedistribute.setEnable(redistributeObject.getEnable()==0?false:true);
			synchroUtil.ospfRedistributeSynchro(ospfRedistribute, siteId);
		}
	}
}
