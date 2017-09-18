package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.clock.TimeLineClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 线路时钟CX设备下发
 * @author dzy
 *
 */
public class LineClockInterfaceCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@Override
	public String excutionInsert(Object object) throws Exception {
		String result = null;
/*		OperationObject operationObject = null;
		PortConfigInfo portConfigInfo = null;
		try {
			portConfigInfo = (PortConfigInfo) object;
			operationObject = this.convertOperation(operationObject, portConfigInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			portConfigInfo = null;
		}
*/		return result;
	}
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		LineClockInterface lineClockInterface = null;
		try {
			lineClockInterface = (LineClockInterface) object;
			operationObject = this.convertOperation(operationObject, lineClockInterface, TypeAndActionUtil.ACTION_SAVEANDUPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			lineClockInterface = null;
		}
		return result;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		String result = null;
	
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		LineClockInterface lineClockInterface = null;
		OperationObject operationObject = null;
		TimeLineClockInterfaceService_MB timeLineClockInterfaceService = null;
		try {
			lineClockInterface = new LineClockInterface();
			lineClockInterface.setSiteId(siteId);
			timeLineClockInterfaceService=(TimeLineClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.TimeLineClockInterfaceService);
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
				operationObject = this.convertOperation(operationObject, lineClockInterface, TypeAndActionUtil.ACTION_SYNCHRO);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
				//	timeLineClockInterfaceService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
					this.getLineClockInterface(operationObject.getCxActionObjectList().get(0).getClockPortESObjectList(), siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationObject = null;
			lineClockInterface = null;
			UiUtil.closeService_MB(timeLineClockInterfaceService);
		}
		return result;
	}
	private OperationObject convertOperation(OperationObject operationObject,
			LineClockInterface lineClockInterface, String action) {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(lineClockInterface.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG);
			cxActionObject.setClockPortESObject((ClockPortESObject)this.getClockPortESObject(lineClockInterface));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	
/**
 * 转换驱动层对象
 */
	private ClockPortESObject getClockPortESObject(LineClockInterface lineClockInterface) throws NumberFormatException, Exception {
		ClockPortESObject clockPortESObject=new ClockPortESObject();
//		PortService portService = (PortService) ConstantUtil.serviceFactory.newService(Services.PORT);
//		PortInst portInst = new PortInst();
//		portInst.setPortId(lineClockInterface.getPort());
//		portInst =  portService.select(portInst).get(0);
		clockPortESObject.setPortname(lineClockInterface.getPortName());
		clockPortESObject.setSsmoutputenable(1==lineClockInterface.getSsmSendingEnabled()?"true":"false");
		clockPortESObject.setSpeed(lineClockInterface.getRate());
		clockPortESObject.setDnugroup(lineClockInterface.getDnuGroup());
		return clockPortESObject;
	}
	/**
	 * @param clockPortESObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getLineClockInterface(List<ClockPortESObject> clockPortESObjectList, int siteId) throws Exception {
		if (null == clockPortESObjectList) {
			throw new Exception("clockPortESObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		LineClockInterface lineClockInterface;
		PortService_MB portService = null;
		PortInst port=null;
		List<PortInst> portInst=null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<ClockPortESObject> clockPortESObjectListAction = new ArrayList<ClockPortESObject>();
			for (ClockPortESObject clockPortESObject : clockPortESObjectList) {
				//if("true".equals(clockPortESObject.getIsClock().toString())){
					clockPortESObjectListAction.add(clockPortESObject);
				//}
			}
			for (ClockPortESObject clockPortESObject : clockPortESObjectListAction) {
				lineClockInterface=new LineClockInterface();
				lineClockInterface.setSiteId(siteId);
				lineClockInterface.setPortName(clockPortESObject.getPortname());
				port =new PortInst();
				port.setSiteId(siteId);
				port.setPortName(clockPortESObject.getPortname());
				portInst=new ArrayList<PortInst>();
				portInst=portService.select(port);
				if(portInst.size()>0){
					lineClockInterface.setPort(portInst.get(0).getPortId());
				}
				lineClockInterface.setSsmSendingEnabled("true".equals(clockPortESObject.getSsmoutputenable())?1:0);
				lineClockInterface.setRate(clockPortESObject.getSpeed());
				lineClockInterface.setDnuGroup(clockPortESObject.getDnugroup());
				synchroUtil.lineClockInterfaceSynchro(lineClockInterface, siteId);
			
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}

}
