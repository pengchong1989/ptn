package com.nms.service.impl.cx;

import java.util.List;

import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;

public class AcnCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}

	/**
	 * 查询出全部的ACN并删除
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String selectAndDelete(int siteId) throws Exception {
		OperationObject operationObject = null;
		try {
			operationObject=this.getOperation(siteId, TypeAndActionUtil.ACTION_SELECT);
			
			super.sendAction(operationObject);
			super.verification(operationObject);
			if(operationObject.isSuccess()){
				//如果查询到ACN就删除。否则返回配置成功结果
				if(operationObject.getCxActionObjectList().get(0).getAcnObjectList().size()>0){
					
					//把动作改成删除。并且把返回状态和成功状态都改成默认 重新下发删除动作。
					operationObject.getCxActionObjectList().get(0).setAction(TypeAndActionUtil.ACTION_DELETE);
					operationObject.getCxActionObjectList().get(0).setStatus("");
					operationObject.setSuccess(false);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if(operationObject.isSuccess()){
						return operationObject.getCxActionObjectList().get(0).getStatus();
					}else{
						return super.getErrorMessage(operationObject);
					}
				}else{
					return operationObject.getCxActionObjectList().get(0).getStatus();
				}
			}else{
				return super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 通过网元ID获取operationObject对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private OperationObject getOperation(int siteId, String action) throws Exception {

		OperationObject operationObject = new OperationObject();
		this.getAction(operationObject, siteId, action);
		
		return operationObject;
	}

	/**
	 * 通过网元ID获取actionObject对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception 
	 * 
	 * @Exception 异常对象
	 */
	private void getAction(OperationObject operationObject, int siteId, String action) throws Exception {

		CXActionObject cxActionObject = new CXActionObject();
		cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		cxActionObject.setAction(action);
		cxActionObject.setType(TypeAndActionUtil.TYPE_ACN);
		cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
		
		operationObject.getCxActionObjectList().add(cxActionObject);
	}
}
