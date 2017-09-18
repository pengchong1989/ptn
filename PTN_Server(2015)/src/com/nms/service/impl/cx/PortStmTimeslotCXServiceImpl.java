package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.enums.EManagerStatus;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PortStmTimeslotCXServiceImpl extends CXOperationBase implements OperationServiceI {
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
		PortStmTimeslotService_MB porStmTimeslotService = null;
		PortStmTimeslot portStmTimeslot = null;
		String action = null;
		OperationObject operationObject = null;
		String result=null;
		try {
			porStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslot = (PortStmTimeslot) object;
			action = this.getAction(portStmTimeslot);
			if (action.equals("")) {
				if(!portStmTimeslot.isDataDownLoad()){
					porStmTimeslotService.update(portStmTimeslot);
				}
				return ResultString.CONFIG_SUCCESS;
			} else {
				operationObject = this.convertOperation(operationObject, action, portStmTimeslot);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					if(!portStmTimeslot.isDataDownLoad()){
						porStmTimeslotService.update(portStmTimeslot);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(porStmTimeslotService);
			portStmTimeslot = null;
			action = null;
			operationObject = null;
		}
		return result;
	}

	/**
	 * 获取OperationObject
	 * 
	 * @param operationObject
	 * @param action
	 * @param portStmTimeslot
	 * @return
	 * @throws Exception
	 */
	private OperationObject convertOperation(OperationObject operationObject, String action, PortStmTimeslot portStmTimeslot) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(portStmTimeslot.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTSTMTIMESLOT);
			cxActionObject.setAction(action);
			cxActionObject.setSdhPortObject(this.convertSdhPort(portStmTimeslot));
			cxActionObject.setSdhAcObject(this.convertSdgAc(portStmTimeslot));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	/**
	 * 获取SdhPortObject
	 * 
	 * @param portStmTimeslot
	 * @return
	 * @throws Exception
	 */
	private SdhPortObject convertSdhPort(PortStmTimeslot portStmTimeslot) throws Exception {
		SdhPortObject sdhPortObject = new SdhPortObject();
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setPortId(portStmTimeslot.getPortid());
			portInstList = portService.select(portInst);
			if (portInstList != null && portInstList.size() == 1) {
				portInst = portInstList.get(0);
			} else {
				throw new Exception("查询portInst失败");
			}
			sdhPortObject.setName(portInst.getPortName());
			sdhPortObject.setAdmin(portInst.getManageStatus() == EManagerStatus.NOENABLED.getValue() ? "down" : "up");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
		}
		return sdhPortObject;
	}

	/**
	 * 获取SdhAcObject
	 * 
	 * @param portStmTimeslot
	 * @return
	 */
	private SdhAcObject convertSdgAc(PortStmTimeslot portStmTimeslot) {
		SdhAcObject sdhAcObject = new SdhAcObject();
		sdhAcObject.setName(portStmTimeslot.getTimeslotname());
		sdhAcObject.setAdmin(portStmTimeslot.getManagerStatus() == EManagerStatus.NOENABLED.getValue() ? "down" : "up");
		sdhAcObject.setOper(portStmTimeslot.getJobstatus());
		sdhAcObject.setExpectj2(portStmTimeslot.getExpectjtwo());
		sdhAcObject.setReceivedj2(portStmTimeslot.getRealityjtwo());
		sdhAcObject.setSendj2(portStmTimeslot.getSendjtwo());
		sdhAcObject.setExpectv5(portStmTimeslot.getExpectvfive());
		try {
			sdhAcObject.setSendv5(UiUtil.getCodeById(Integer.getInteger(portStmTimeslot.getSendvfive()).intValue()).getCodeValue() );
		} catch (Exception e) {
			//由于转换code的id 字符串 到int出错。暂不更新本字段
		}
		sdhAcObject.setReceivedv5(portStmTimeslot.getRealityvfive());
		
		if(1==portStmTimeslot.getCheckvfive()){
			sdhAcObject.setCheckv5("true");
		}else if(0==portStmTimeslot.getCheckvfive()){
			sdhAcObject.setCheckv5("false");
		}
		if(1==portStmTimeslot.getLptim()){
			sdhAcObject.setCheckj2("true");
		}else if(0==portStmTimeslot.getLptim()){
			sdhAcObject.setCheckj2("false");
		}

		return sdhAcObject;
	}

	/**
	 * 获得ACTION
	 * 
	 * @param portStmTimeslot
	 * @return
	 * @throws Exception
	 */
	private String getAction(PortStmTimeslot portStmTimeslot) throws Exception {

		PortStmTimeslot portStmTimeslotBefore = null;
		String result=null;
		PortStmTimeslotService_MB porStmTimeslotService = null;;
		try {
			porStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslotBefore = porStmTimeslotService .selectById(portStmTimeslot.getId());
			//数据下载
			if(portStmTimeslot.isDataDownLoad()){
				if (portStmTimeslot.getManagerStatus()==EManagerStatus.NOENABLED.getValue()) {
					result = TypeAndActionUtil.ACTION_DELETE;
				}else{
					result = TypeAndActionUtil.ACTION_INSERT;
				}
			}else{
				if (portStmTimeslot.getManagerStatus()==EManagerStatus.NOENABLED.getValue()) {
					if (portStmTimeslotBefore.getManagerStatus()==EManagerStatus.NOENABLED.getValue()) {
						result = "";
					} else {
						result = TypeAndActionUtil.ACTION_DELETE;
					}
				} else {
					if (portStmTimeslotBefore.getManagerStatus()==EManagerStatus.NOENABLED.getValue()) {
						result = TypeAndActionUtil.ACTION_INSERT;
					} else {
						result = TypeAndActionUtil.ACTION_UPDATE;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(porStmTimeslotService);
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		PortStmTimeslotService_MB portStmTimeslotService = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_PORTSTMTIMESLOT);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
//				PortService portService = (PortService) ConstantUtil.serviceFactory.newService(Services.PORT);
//				portService.updateActiveStatus(siteId, 0);
//				portService = null;
				portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
				portStmTimeslotService.updateActiveStatus(siteId, 0);
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getSdhAcObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
		   UiUtil.closeService_MB(portStmTimeslotService);
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
	private void synchro_db(List<SdhAcObject> sdhAcObjectList, int siteId) throws Exception {

		if (null == sdhAcObjectList) {
			throw new Exception("sdhAcObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (SdhAcObject sdhAcObject : sdhAcObjectList) {

			PortStmTimeslot portStmTimeslot = new PortStmTimeslot();
//			String portStmTimeslotName = "portStmTimeslot_" + super.getNowMS();
			portStmTimeslot.setSiteId(siteId);
//			portStmTimeslot.setTimeslotnumber(sdhAcObject.getIfname()+"/"+sdhAcObject.getAdmin());
			portStmTimeslot.setPortid(super.getPortByName(siteId, sdhAcObject.getIfname()).getPortId());
			portStmTimeslot.setTimeslotname(sdhAcObject.getName());
			portStmTimeslot.setManagerStatus(Integer.parseInt(sdhAcObject.getAdmin()));
			portStmTimeslot.setJobstatus(sdhAcObject.getOper());
			portStmTimeslot.setExpectjtwo(sdhAcObject.getExpectj2());
			portStmTimeslot.setSendjtwo(sdhAcObject.getSendj2());
			portStmTimeslot.setRealityjtwo(sdhAcObject.getReceivedj2());
			if (!"".equals(sdhAcObject.getCheckj2())){
				portStmTimeslot.setLptim("true".equals(sdhAcObject.getCheckj2())?1:0);
			}
//			portStmTimeslot.setLptim(lptim);
			portStmTimeslot.setExpectvfive("Async");
//			portStmTimeslot.setSendvfive(sdhAcObject.getSendv5());
			portStmTimeslot.setSendvfive(UiUtil.getCodeByValue("SENDV5", sdhAcObject.getSendv5()).getId()+"");
			portStmTimeslot.setRealityvfive(sdhAcObject.getReceivedv5());
			if (!"".equals(sdhAcObject.getCheckv5())){
				portStmTimeslot.setCheckvfive("true".equals(sdhAcObject.getCheckv5())?1:0);
			}
			
			portStmTimeslot.setIsUsed(0);
//System.out.println("PortStmTimeslotCXServiceImpl.syncro_db . sdhAc="+sdhAcObject.toString());			
			synchroUtil.portStmTimeslotSynchro(portStmTimeslot, siteId);
		}
	}
}
