package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class NNICXServiceImpl extends CXOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		String result = null;
		try {
			portInst = (PortInst) object;
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			operationObject = this.getOperationObject(object);

			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);

			if (operationObject.isSuccess()) {
				if(!portInst.isDataDownLoad()){
					portService.saveOrUpdate(portInst);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			UiUtil.closeService_MB(portService);
			portInst = null;
		}
		return result;
	}

	/**
	 * 获得NNI对象
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object) throws Exception {
		CXActionObject cxActionObject = null;
		PortInst portInst = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			portInst = (PortInst) object;
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(portInst.getSiteId()));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_NNI);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setEthPortObject(super.getPortObject(portInst, "SECTION", portInst.getQosQueues(), TypeAndActionUtil.TYPE_NNI));
			cxActionObject.setNNIObject(this.getNniObject(portInst));

			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portInst = null;
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 获取驱动层uni端口对象
	 * 
	 * @param portInst
	 *            db层端口对象
	 * @return 驱动层uni端口对象
	 * @throws Exception
	 */
	private NNIObject getNniObject(PortInst portInst) throws Exception {

		if (portInst == null) {
			throw new Exception("portInst is null");
		}
		NNIObject nniObject = null;
		try {
			nniObject = new NNIObject();
			nniObject.setInbwlimit(portInst.getPortAttr().getEntranceLimit());
			nniObject.setEgbwlimit(portInst.getPortAttr().getExitLimit());
			nniObject.setPvid(portInst.getPortAttr().getPortNniAttr().getNniVlanid());
			if(0!=portInst.getExpMappingLLspInput()){
				nniObject.setLlspexptoclr(portInst.getExpMappingLLspInput()+"");
			}
			if(0!=portInst.getExpMappingLLspOutput()){
				nniObject.setLlspclrtoexp(portInst.getExpMappingLLspOutput()+"");
			}
			if(0!=portInst.getExpMappingELspInput()){
				nniObject.setElspexptocos(portInst.getExpMappingELspInput()+"");
			}
			if(0!=portInst.getExpMappingELspOutput()){
				nniObject.setElspcostoexp(portInst.getExpMappingELspOutput()+"");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return nniObject;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}

	/**
	 * 特殊同步。根据端口名称，同步此端口下的uni信息
	 * 
	 * @author kk
	 * 
	 * @param portname
	 *            端口名称
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public NNIObject synchro(int siteid, String portname) throws Exception {
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		EthPortObject ethPortObject = new EthPortObject();
		NNIObject nniObject = null;
		try {
			ethPortObject.setName(portname);
			cxActionObject.setEthPortObject(ethPortObject);
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_NNI);
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				nniObject = operationObject.getCxActionObjectList().get(0).getNNIObject();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			cxActionObject = null;
			ethPortObject = null;
		}

		return nniObject;
	}
}
