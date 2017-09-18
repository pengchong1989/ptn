package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmService_MB;
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

public class PortStmCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
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
		PortStm portStm = null;
		OperationObject operationObject = null;
		String result = null;
		PortStmService_MB portStmService = null;
		try {
			portStm = (PortStm) object;
			operationObject = this.convertOperation(operationObject, portStm);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				if(!portStm.isDataDownLoad()){
					portStmService = (PortStmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTM);
					portStmService.update(portStm);
				}
				result= operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result= super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			portStm = null;
			operationObject = null;
			UiUtil.closeService_MB(portStmService);
		} 
		return result;
	}

	/**
	 * 获取OperationObject
	 * @param operationObject
	 * @param portStm
	 * @return
	 * @throws Exception
	 */
	private OperationObject convertOperation(OperationObject operationObject, PortStm portStm) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(portStm.getSiteid()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTSTM);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setSdhPortObject(this.convertSdhPort(portStm));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	/**
	 * 获取SdhPort
	 * @param portStm
	 * @return
	 * @throws Exception
	 */
	private SdhPortObject convertSdhPort(PortStm portStm) throws Exception {
		SdhPortObject sdhPortObject = new SdhPortObject();

		try {
			sdhPortObject.setName(portStm.getName());
			sdhPortObject.setAdmin(portStm.getStatus() == 0 ? "down" : "up");
			sdhPortObject.setSfpexptype(UiUtil.getCodeById(Integer.parseInt(portStm.getSfpexpect())).getCodeValue());
			sdhPortObject.setSfptype(portStm.getSfpreality());
			sdhPortObject.setSfpvendor(portStm.getSfpvender());
			sdhPortObject.setWavelength(portStm.getJobwavelength()+"");
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return sdhPortObject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		PortService_MB portService = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_PORTSTM);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portService.updateActiveStatus(siteId, 0, "STM1");
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getSdhPortObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
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
	 * @param sdhPortObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<SdhPortObject> sdhPortObjectList, int siteId) throws Exception {

		if (null == sdhPortObjectList) {
			throw new Exception("sdhPortObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (SdhPortObject sdhPortObject : sdhPortObjectList) {
//System.out.println("PortStmCXServiceImp.synchro_db . sdhP = "+sdhPortObject.toString());
			PortStm portStm = new PortStm();
			portStm.setName(sdhPortObject.getName());
			if (!"".equals(sdhPortObject.getWavelength())){
				portStm.setJobwavelength(Integer.parseInt(sdhPortObject.getWavelength()));
			}
			portStm.setSfpexpect(UiUtil.getCodeByValue("sfptype", sdhPortObject.getSfpexptype()).getId()+"");
			portStm.setSfpreality(sdhPortObject.getSfptype());
			portStm.setSfpvender(sdhPortObject.getSfpvendor());
			portStm.setSiteid(siteId);
			portStm.setStatus(Integer.parseInt(sdhPortObject.getAdmin()));
			portStm.setJobstatus(sdhPortObject.getOper());
			synchroUtil.portStmSynchro(portStm, siteId);
		}
	}
}
