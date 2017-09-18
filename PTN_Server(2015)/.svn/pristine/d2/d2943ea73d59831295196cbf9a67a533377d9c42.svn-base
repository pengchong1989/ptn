package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.model.ptn.ecn.CCNService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.dispatch.AcnDispatch;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class CCNCXServiceImpl extends CXOperationBase implements OperationServiceI {
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
		CCN ccn = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			ccn = (CCN) object;
			cxActionObject = this.getCXObject(ccn, action);

			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ccn = null;
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
	private CXActionObject getCXObject(CCN ccn, String action) throws Exception {

		if (ccn == null) {
			throw new Exception("ccn is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(ccn.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_CCN);
			cxActionObject.setAction(action);
			cxActionObject.setCcnObject(this.ccnToCcnOBJECT(ccn));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	private CCNObject ccnToCcnOBJECT(CCN ccn) throws NumberFormatException, Exception {
		CCNObject ccnObject = new CCNObject();
		if ("1".equals(UiUtil.getCodeById(Integer.parseInt(ccn.getAdmin())).getCodeValue())) {
			ccnObject.setAdmin("up");
		} else {
			ccnObject.setAdmin("down");
		}
		ccnObject.setName(ccn.getCcnName());
		ccnObject.setIp(ccn.getIp());
		ccnObject.setPeer(ccn.getPeer());
		ccnObject.setMtu(ccn.getMtu() + "");
		return ccnObject;
	}

	public List<CCN> excutionQuery(Object object) throws Exception {
		List<CCN> ccnList = null;
		CCN ccn = null;
		CCNService_MB ccnService = null;
		try {
			ccn = (CCN) object;
			ccnService = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
			ccnList = ccnService.queryByNeID(ccn.getNeId());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ccnService);
		}
		return ccnList;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		CCN ccn = null;
		String result = null;
		CCNService_MB ccnService = null;
		try {
			ccnService = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
			for (int i = 0; i < objectList.size(); i++) {
				ccn = (CCN) objectList.get(i);
				if(ccn.getStatus()==0){
					int res=ccnService.delete(ccn);
					if(res==1){
						result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				}else {
					/*
					 * 只有 ccn 在激活状态时，，删除设备上数据
					 */
					operationObject = this.getOperationObject(ccn, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						ccnService.delete(ccn);
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}
				
			}
			//result = operationObject.getCxActionObjectList().get(0).getStatus();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ccnService);
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		CCN ccn = null;
		String result = null;
		AcnDispatch acnDispatch = null;
		CCNService_MB ccnService = null;
		try {
			ccnService = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
			ccn = (CCN) object;
			/**
			 * 未激活不 下发设备
			 */
			if(ccn.getStatus()==0){
				int res=ccnService.insert(ccn);
				if(res==1){
				result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}else{
				// 激活  
				//如果设备是700E时。 要删掉ACN下所有配置。
				if("cxt20a".equals(super.getSiteType(Integer.parseInt(ccn.getNeId())))){
					//新建时，为了网元ID重复，先删掉所有的ACN
					acnDispatch = new AcnDispatch();
					acnDispatch.selectAndDelete(Integer.parseInt(ccn.getNeId()));
				}
				
				operationObject = this.getOperationObject(ccn, TypeAndActionUtil.ACTION_INSERT);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					ccnService.insert(ccn);
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ccnService);
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		CCN ccn = (CCN) object;
		OperationObject operationObject = null;
		String result = null;
		AcnDispatch acnDispatch = null;
		CCNService_MB ccnService = null;
		try {
			ccnService = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
			//如果设备是700E时。 要删掉ACN下所有配置。
			if("cxt20a".equals(super.getSiteType(Integer.parseInt(ccn.getNeId())))){
				//修改时，为了网元ID重复，先删掉所有的ACN
				acnDispatch = new AcnDispatch();
				acnDispatch.selectAndDelete(Integer.parseInt(ccn.getNeId()));
			}

			operationObject = this.getOperationObject(ccn, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				ccnService.update(ccn);
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ccnService);
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) {
		String result="";
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		CCNService_MB service=null;
		CCN ccn=null;
		try {
			service = (CCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CCN);
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CCN);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				ccn=new CCN();
				ccn.setNeId(siteId+"");
				ccn.setStatus(0);
				service.updateStatus(ccn);
				for (CXActionObject cxActionObjectList : operationObject.getCxActionObjectList()) {
					
					this.synchro_db(cxActionObjectList.getCcnObjectList(), siteId);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(service);
			ccn=null;
		}

		return result;
	}

	public void synchro_db(List<CCNObject> ccnList, int siteId) throws Exception {
		if (ccnList == null) {
			throw new Exception("ccnList is null");
		}
		for (CCNObject ccn : ccnList) {
			CCN ccn_db = new CCN();
			ccn_db.setCcnName(ccn.getName());
			ccn_db.setAdmin(UiUtil.getCodeByValue("ENABLEDSTATUE", ccn.getAdmin()).getId() + "");
			ccn_db.setNeId(siteId + "");
			ccn_db.setOper(ccn.getOper());
			ccn_db.setIp(ccn.getIp());
			ccn_db.setPeer(ccn.getPeer());
			ccn_db.setStatus(1);//激活
			ccn_db.setMtu(Integer.valueOf(ccn.getMtu()));
			ccn_db.setPortname(ccn.getDatalink());
			SynchroUtil.ccnSynchro(ccn_db);
		}
	}
}
