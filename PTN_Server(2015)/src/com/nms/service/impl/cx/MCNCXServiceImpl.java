package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.drivechenxiao.service.bean.mcn.MCNObject;
import com.nms.model.ptn.ecn.MCNService_MB;
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
/**
 * mcn 设备实现类
 * @author Administrator
 *
 */
public class MCNCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 获得Operation对象
	 * 
	 * @param siteId
	 * 			网元ID
	 * @param action
	 * 			操作方法
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		CXActionObject cxActionObject = null;
		MCN mcn = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			mcn = (MCN) object;
			cxActionObject = this.getCXObject(mcn, action);			
			operationObject.getCxActionObjectList().add(cxActionObject);	
			return operationObject;
		} catch (Exception e) {
			throw e;
		} finally {
			mcn = null;
			cxActionObject = null;
		}
	}

	/**
	 * 获得CX对象
	 * 
	 * @param oSPFInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private CXActionObject getCXObject(MCN mcn, String action) throws Exception {

		if (mcn == null) {
			throw new Exception("mcn is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(mcn.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_MCN);
			cxActionObject.setAction(action);
			cxActionObject.setMcnObject(MCNToMCNObject(mcn));

			return cxActionObject;
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			cxActionObject = null;
		}
	}

	/**
	 * 转换成驱动层对象
	 * @param mcn
	 * @return
	 */
	private MCNObject MCNToMCNObject(MCN mcn) {
		MCNObject mcnobject = new MCNObject();
		mcnobject.setIp(mcn.getIp());
		mcnobject.setMtu(mcn.getMtu() + "");
		return mcnobject;
	}

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		MCNService_MB mcnService = null;
		MCN mcn = (MCN) object;
		OperationObject operationObject = null;
		String result = null;
		try {
			mcnService = (MCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MCN);
			operationObject = this.getOperationObject(mcn, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				if(!mcn.isDataDownLoad()){
					//如果成功 操作数据库
					mcnService.update(mcn);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(mcnService);
			mcn = null;
		}
		return result;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		 
		return null;
	}

	/**
	 * 查询MCN对象
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<MCN> excutionQuery(Object object) throws Exception {
		List<MCN> mcnList = null;
		MCN mcn = null;
		MCNService_MB mcnService = null;
		try {
			mcnService = (MCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MCN);
			mcn = (MCN) object;
			mcnList = mcnService.queryByNeID(mcn.getNeId());
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(mcnService);
		}
		return mcnList;
	}

	/**
	 * 同步
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();

		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_MCN);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getMcnObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			operationObject = null;
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
	private void synchro_db(List<MCNObject> mcnObjectList, int siteId) throws Exception {

		if (null == mcnObjectList) {
			throw new Exception("mcnObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (MCNObject mcnObject : mcnObjectList) {

			MCN mcn = new MCN();
			mcn.setNeId(siteId+"");
			mcn.setIp(mcnObject.getIp());
			mcn.setMtu(Integer.parseInt(mcnObject.getMtu()));
			synchroUtil.mcnSynchro(mcn, siteId);
		}
	}

}
