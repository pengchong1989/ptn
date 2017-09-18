package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.enums.EActiveStatus;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.drivechenxiao.service.bean.ospf.interfaces.OSPFinterfacesObject;
import com.nms.model.ptn.ecn.OSPFInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OSPFInterfaceCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 获得Operation对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		List<CXActionObject> cxActionObjectList = null;
		CXActionObject cxActionObject = null;
		OSPFInterface ospfInterface = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();
			cxActionObjectList = new ArrayList<CXActionObject>();
			ospfInterface = (OSPFInterface) object;
			cxActionObject = this.getCXObject(ospfInterface, action);
			cxActionObjectList.add(cxActionObject);
			operationObject.getCxActionObjectList().add(cxActionObject);				
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ospfInterface = null;
			cxActionObject = null;
			cxActionObjectList = null;
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
	private CXActionObject getCXObject(OSPFInterface ospfInterface, String action) throws Exception {

		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(ospfInterface.getNeId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_OSPFINTERFACE);
			cxActionObject.setAction(action);
			cxActionObject.setOSPFInterfacesObject(this.ospfInterfaceToOspfInterfaceOBJECT(ospfInterface));
			if (ospfInterface.getInterfaceName().indexOf("CCN") != -1) {
				CCNObject object = new CCNObject();
				object.setName(ospfInterface.getInterfaceName().substring(ospfInterface.getInterfaceName().indexOf("/") + 1));
				cxActionObject.setCcnObject(object);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	/**
	 * 
	 * @param ospfInterface
	 * @return
	 */
	private OSPFinterfacesObject ospfInterfaceToOspfInterfaceOBJECT(OSPFInterface ospfInterface) {
		OSPFinterfacesObject interfacesObject = new OSPFinterfacesObject();
		interfacesObject.setArea(ospfInterface.getArea());
		interfacesObject.setType(ospfInterface.getType());
		interfacesObject.setHello_interval(ospfInterface.getHello_interval() + "");
		interfacesObject.setDead_interval(ospfInterface.getDead_interval() + "");
		interfacesObject.setRetransmit_interval(ospfInterface.getRetransmit_interval() + "");
		interfacesObject.setTransmit_delay(ospfInterface.getTransmit_delay() + "");
		interfacesObject.setPassive(ospfInterface.isPassive() ? "true" : "false");
		interfacesObject.setCost(ospfInterface.getCost() + "");
		interfacesObject.setPrioriy(ospfInterface.getPrioriy() + "");
		return interfacesObject;
	}

	/**
	 * 删除
	 */
	@Override
		 
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		OSPFInterface ospfInterface = null;
		String result = null;
		OSPFInterfaceService_MB ospfInterfaceService = null;
		try {
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			for (int i = 0; i < objectList.size(); i++) {
				ospfInterface = (OSPFInterface) objectList.get(i);
				if(EActiveStatus.ACTIVITY.getValue()==ospfInterface.getActiveStatus()){
					operationObject = this.getOperationObject(ospfInterface, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						if(!ospfInterface.isDataDownLoad()){
							//操作成功 修改数据库
							ospfInterfaceService.delete(ospfInterface);
						}
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else{
					if(!ospfInterface.isDataDownLoad()){
						ospfInterfaceService.delete(ospfInterface);
					}
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ospfInterfaceService);
			operationObject = null;
			ospfInterface = null;
		}
		return result;
	}

	/**
	 * 新建
	 */
	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		OSPFInterface ospfInterface = null;
		String result = null;
		OSPFInterfaceService_MB ospfInterfaceService = null;
		try {
			ospfInterface = (OSPFInterface) object;
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			operationObject = this.getOperationObject(ospfInterface, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				if(!ospfInterface.isDataDownLoad()){
					//操作成功 修改数据库
					ospfInterfaceService.insert(ospfInterface);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(ospfInterfaceService);
			operationObject = null;
			ospfInterface = null;
		}
		return result;
	}

	/**
	 * 修改
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		 
		OSPFInterface ospfInterface = (OSPFInterface) object;
		OperationObject operationObject = null;
		String result = null;
		OSPFInterfaceService_MB ospfInterfaceService = null;
		try {
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			if(!ospfInterface.isDataDownLoad()){
				ospfInterfaceService.update(ospfInterface);
			}
			if(EActiveStatus.ACTIVITY.getValue()==ospfInterface.getActiveStatus()){
				operationObject = this.getOperationObject(ospfInterface, TypeAndActionUtil.ACTION_UPDATE);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally {
			UiUtil.closeService_MB(ospfInterfaceService);
			operationObject = null;
			ospfInterface = null;
		}
		return result;
	}

	/**
	 * 同步
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = null;
		OSPFInterfaceService_MB ospfInterfaceService = null;
		try {
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			operationObject = new OperationObject();
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_OSPFINTERFACEMCN);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				ospfInterfaceService.updateActiveStatus(siteId,EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getOspfInterfacesObjectList(), siteId);
			}
			operationObject = new OperationObject();
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_OSPFINTERFACECCN);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getOspfInterfacesObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(ospfInterfaceService);
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
	private void synchro_db(List<OSPFinterfacesObject> ospfInterfacesObjectList, int siteId) throws Exception {

		if (null == ospfInterfacesObjectList) {
			throw new Exception("ospfInterfacesObjectList is null");
		}
		SynchroUtil synchroUtil = new SynchroUtil();
		for (OSPFinterfacesObject ospfInterfacesObject : ospfInterfacesObjectList) {

			OSPFInterface ospfInterface = new OSPFInterface();
			ospfInterface.setNeId(siteId + "");
			if (ospfInterfacesObject.getName() == null || "".equals(ospfInterfacesObject.getName())) {
				ospfInterface.setInterfaceName("MCN/1");
			} else {
				ospfInterface.setInterfaceName("CCN/"+ospfInterfacesObject.getName());
			}
			ospfInterface.setArea(ospfInterfacesObject.getArea());
			ospfInterface.setType(ospfInterfacesObject.getType().equals("1")?"ptp":"broadcast");
			ospfInterface.setHello_interval(Integer.parseInt(ospfInterfacesObject.getHello_interval()));
			ospfInterface.setDead_interval(Integer.parseInt(ospfInterfacesObject.getDead_interval()));
			ospfInterface.setRetransmit_interval(Integer.parseInt(ospfInterfacesObject.getRetransmit_interval()));
			ospfInterface.setTransmit_delay(Integer.parseInt(ospfInterfacesObject.getTransmit_delay()));
			ospfInterface.setPassive(false);
			ospfInterface.setCost(Integer.parseInt(ospfInterfacesObject.getCost()));
			ospfInterface.setPrioriy(Integer.parseInt(ospfInterfacesObject.getPrioriy()));
			ospfInterface.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
			synchroUtil.ospfInterfaceSynchro(ospfInterface, siteId);
		}
	}

}
