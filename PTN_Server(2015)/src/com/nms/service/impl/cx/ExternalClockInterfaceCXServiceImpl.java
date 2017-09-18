package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.model.ptn.clock.ExternalClockInterfaceService_MB;
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
 * 外时钟
 * @author Administrator
 *
 */
public class ExternalClockInterfaceCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@Override
	public String excutionInsert(Object object) throws Exception {
		String result = null;
		OperationObject operationObject = null;
		ExternalClockInterface externalClockInterface = null;
		try {
			externalClockInterface = (ExternalClockInterface) object;
			operationObject = this.convertOperation(operationObject, externalClockInterface, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;	
		} finally {
			operationObject = null;
			externalClockInterface = null;
		}
		return result;
	}
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		int resultUpdate = 0;
		ExternalClockInterface externalClockInterface = null;
		ExternalClockInterface externalClockInterfaceBefore = null;
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		try {
			externalClockInterfaceService = (ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			externalClockInterface = (ExternalClockInterface) object;
			externalClockInterfaceBefore = externalClockInterfaceService.select(externalClockInterface).get(0);
			
		//	resultUpdate = externalClockInterfaceService.update(externalClockInterface);
			operationObject = this.convertOperation(operationObject, externalClockInterface, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				externalClockInterfaceService.update(externalClockInterfaceBefore);
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
		return result;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		String result = null;
		OperationObject operationObject = null;
		List<ExternalClockInterface> externalClockInterfaceList = null;
		try {
			externalClockInterfaceList = objectList;
			operationObject = this.convertOperationForList(operationObject, externalClockInterfaceList, TypeAndActionUtil.ACTION_DELETE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		}finally {
			operationObject = null;
			externalClockInterfaceList = null;
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		ExternalClockInterface externalClockInterface = null;
		OperationObject operationObject = null;
		ExternalClockInterfaceService_MB externalClockInterfaceService = null;
		try {
			externalClockInterface = new ExternalClockInterface();
			externalClockInterface.setSiteId(siteId);
			externalClockInterfaceService=(ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
				operationObject = this.convertOperation(operationObject, externalClockInterface, TypeAndActionUtil.ACTION_SYNCHRO);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					externalClockInterfaceService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
					this.getExtclkObject(operationObject.getCxActionObjectList().get(0).getExtclkObjectList(), siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(externalClockInterfaceService);
		}
		return result;
	}
	
	private OperationObject convertOperation(OperationObject operationObject,
			ExternalClockInterface externalClockInterface, String action) {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(externalClockInterface.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_EXTERNALCLOCKINTERF);
			cxActionObject.setExtclkObject((ExtclkObject)this.getExtclkObject(externalClockInterface));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * db对象转换成驱动成对象
	 * @param externalClockInterface
	 * @return
	 * @throws Exception 
	 */
	private ExtclkObject getExtclkObject(
			ExternalClockInterface externalClockInterface) throws Exception {
		ExtclkObject extclkObject = new ExtclkObject(); 
		extclkObject.setName(externalClockInterface.getInterfaceName());
		if(0!=externalClockInterface.getManagingStatus()){
			if("0".equals(UiUtil.getCodeById(externalClockInterface.getManagingStatus()).getCodeValue())){
				extclkObject.setAdmin("down");
			}else{
				extclkObject.setAdmin("up");
			}
			
		}
		/*		extclkObject.setOper(externalClockInterface.getWorkingStatus());
*/		if(null!=externalClockInterface.getInterfaceName()){
			if(0==externalClockInterface.getInterfaceName().indexOf("extclk")){
				extclkObject.setExtclkmode(UiUtil.getCodeById(externalClockInterface.getInterfaceMode()).getCodeValue());
			}else{
				extclkObject.setMode(UiUtil.getCodeById(externalClockInterface.getInterfaceMode()).getCodeValue());
			}
		}
		
		if(0!=externalClockInterface.getInputImpedance()){
			extclkObject.setImpedance(UiUtil.getCodeById(externalClockInterface.getInputImpedance()).getCodeValue());
		}
		
		if(0!=externalClockInterface.getSanBits()){
			extclkObject.setSsmslot(UiUtil.getCodeById(externalClockInterface.getSanBits()).getCodeValue());
		}
		extclkObject.setTimeoffset(externalClockInterface.getTimeOffsetCompensation());
		return extclkObject;
	}
	private OperationObject convertOperationForList(OperationObject operationObject,
			List<ExternalClockInterface> externalClockInterfaceList, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(externalClockInterfaceList.get(0).getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_EXTERNALCLOCKINTERF);
			for(ExternalClockInterface externalClockInterface:externalClockInterfaceList){
				cxActionObject.getExtclkObjectList().add((ExtclkObject)this.getExtclkObject(externalClockInterface));
			}
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}
	/**
	 * 与数据库同步
	 * 
	 * @author wangwf
	 * 
	 * @param eLanObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getExtclkObject(List<ExtclkObject> ExtclkObjectList, int siteId) throws Exception {

		if (null == ExtclkObjectList) {
			throw new Exception("ExtclkObject is null");
		}
		ExternalClockInterface externalClockInterface;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (ExtclkObject extclkObject : ExtclkObjectList) {
			externalClockInterface = new ExternalClockInterface();
			externalClockInterface.setSiteId(siteId);
			externalClockInterface.setInterfaceName(extclkObject.getIfname());
			externalClockInterface.setManagingStatus(UiUtil.getCodeByValue("ENABLEDSTATUE", extclkObject.getAdmin()).getId());
			externalClockInterface.setWorkingStatus(extclkObject.getOper());
			if(null!=externalClockInterface.getInterfaceName()){
				if(0==extclkObject.getIfname().indexOf("extclk")){
					externalClockInterface.setInterfaceMode(UiUtil.getCodeByValue("intefaceModelTwo", 	extclkObject.getExtclkmode()).getId());
				}else{
					externalClockInterface.setInterfaceMode(UiUtil.getCodeByValue("intefaceModel", 	extclkObject.getMode()).getId());
				}
			}
			externalClockInterface.setInputImpedance(UiUtil.getCodeByValue("inputImpedance", extclkObject.getImpedance()).getId());
			externalClockInterface.setSanBits(UiUtil.getCodeByValue("SANByte", extclkObject.getSsmslot()).getId());
			externalClockInterface.setTimeOffsetCompensation(extclkObject.getTimeoffset());
			externalClockInterface.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
			synchroUtil.externalClockSynchro(externalClockInterface, siteId);
		}
	}
}
