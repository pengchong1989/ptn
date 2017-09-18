package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
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

public class E1CXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		E1Info e1Info = null;
		OperationObject operationObject = null;
		String result = null;
		List<E1Info> e1InfoList=null;
		E1InfoService_MB e1InfoService = null;
		try {
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			e1InfoList=(List<E1Info>) object;
			if(e1InfoList.size()>0){
				e1Info = e1InfoList.get(0);
				operationObject = this.convertOperation(operationObject, e1Info);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					if(!e1Info.isDataDownLoad()){
						e1InfoService.update(e1Info);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}else{
				throw new Exception("object is null");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(e1InfoService);
			e1Info = null;
			operationObject = null;
		}
		return result;
	}

	private OperationObject convertOperation(OperationObject operationObject, E1Info e1Info) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(e1Info.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_E1);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setPdhPortObject(this.convertPdhPortObject(e1Info));
			operationObject.getCxActionObjectList().add(cxActionObject);		
			//operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
/**add 20130830 this for turn db to CX_driver
 * E1Info DB对象
 * PdhPortObject 陈晓对象
 * **/
	private PdhPortObject convertPdhPortObject(E1Info e1info) throws Exception {
		PdhPortObject pdhportobject = new PdhPortObject();

		try {
			pdhportobject.setName(e1info.getPortName());
			if (e1info.getPortInst().getIsEnabled_code() == 0) {
				pdhportobject.setAdmin("down");
			} else {
				pdhportobject.setAdmin("up");
			}
			pdhportobject.setLinecode(e1info.getLinecoding());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pdhportobject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		PortService_MB portService = null;
		try {
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_E1);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portService.updateActiveStatus(siteId, 0, "e1");
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getPdhPortObjectList(), siteId);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			operationObject = null;
			cxActionObject = null;
		}
		return null;
	}

	/**
	 * 把设备读上来的对象转换成数据库对象，与数据库做同步
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
	private void synchro_db(List<PdhPortObject> pdhPortList, int siteId) throws Exception {

		if (null == pdhPortList) {
			throw new Exception("pdhPortList is null");
		}

		E1Info e1Info = null;
		PortInst portInst = null;
		try {
			SynchroUtil synchroUtil = new SynchroUtil();
			for (PdhPortObject pdhPortObject : pdhPortList) {
				e1Info = new E1Info();
				e1Info.setSiteId(siteId);
				e1Info.setPortName(pdhPortObject.getName());
				e1Info.setModel(pdhPortObject.getMode());
				e1Info.setLinecoding(UiUtil.getCodeByValue("LINECODE", pdhPortObject.getLinecode()).getId()+"") ;// 线路编码
				if("0".equals(pdhPortObject.getTermination())){
					e1Info.setImpedance(75);
				}else if("1".equals(pdhPortObject.getTermination())){
					e1Info.setImpedance(120);
				}// 阻抗
					
//System.out.println("E1CXServiceImpl.synchro_db .pdhPortObject="+pdhPortObject.toString());				

				portInst = new PortInst();
				portInst.setJobStatus(pdhPortObject.getOper());	
				portInst.setIsEnabled_code(Integer.parseInt(pdhPortObject.getAdmin()));
				portInst.setLinecoding(UiUtil.getCodeByValue("LINECODE", pdhPortObject.getLinecode()).getId()+"") ;// 线路编码
				portInst.setPortType("e1");
				if("0".equals(pdhPortObject.getTermination())){
					portInst.setImpedance(75);
				}else if("1".equals(pdhPortObject.getTermination())){
					portInst.setImpedance(120);
				}// 阻抗

				synchroUtil.pdhportSynchro(portInst, e1Info);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			e1Info = null;
			portInst = null;
		}

	}
}
