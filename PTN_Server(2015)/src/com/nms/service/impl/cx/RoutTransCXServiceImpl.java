package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;

/**
 * 路由转发表的 实现
 * @author sy
 *
 */
public class RoutTransCXServiceImpl extends CXOperationBase implements OperationServiceI{

	@Override
	public String excutionInsert(Object object) throws Exception {
		
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		
		return null;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		DualProtect dualProtect = null;
		OperationObject operationObject = null;
		try {
			dualProtect =  new DualProtect();
			dualProtect.setSiteId(siteId);
			operationObject = this.convertOperation(operationObject, dualProtect, TypeAndActionUtil.ACTION_SYNCHRO);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
			
				//this.getPortStmObject(operationObject.getCxActionObjectList().get(0).getRouteList(), siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		dualProtect = null;
		return result;
		
	}
	/**
	 * 获得路由转发对象
	 * @param operationObject
	 * @param dualProtect  
	 * @param action 执行方法
	 * @return
	 * @throws Exception
	 */
	public OperationObject convertOperation(OperationObject operationObject, DualProtect dualProtect, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(dualProtect.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_DUALPROTECT);
		//	cxActionObject.setDualObject((DualObject)this.getRoateObject(dualProtect));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	
}
