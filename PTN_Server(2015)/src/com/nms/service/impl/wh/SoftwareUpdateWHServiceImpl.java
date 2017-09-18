package com.nms.service.impl.wh;
import java.util.List;

import com.nms.db.bean.equipment.manager.Upgrade;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.SoftwareUpdate;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;


public class SoftwareUpdateWHServiceImpl extends WHOperationBase implements OperationServiceI {

	private int datalong = 15*1024;//每个数据包15K，
	private int pduLong = 16;//PDU头长度
	private int commandLong = 21;//通信协议长度
	private int data = 15;//部分数据长度
	
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
		Upgrade upgrade = null;
		OperationObject operationObject = null;
		upgrade = (Upgrade)object;
		OperationObject operationObjectResult=null;
		SiteUtil siteUtil=new SiteUtil();
		try {
			operationObjectResult = new OperationObject();
			
			if("0".equals(siteUtil.SiteTypeUtil(upgrade.getSiteId())+"")){//非失连网元、非虚拟网元下发设备
				operationObject = getOperationObject(upgrade,"softwareUpdate");
				super.sendAction(operationObject);
				operationObjectResult=super.verification(operationObject);
				
				//(0:表示 设备返回来的不成功，1:表示超时而导致的不成功)
				ExceptionManage.infor("判断设备返回的信息是否超时---0:设备有返回信息---1:表示超时,设备没有回应-----"+operationObject.getErrorLabel(), this.getClass());
				if(operationObject.getErrorLabel() ==1 ){
					for(int i=0;i<3;i++){
						super.sendAction(operationObject);
						operationObjectResult=super.verification(operationObject);
						if(operationObject.getErrorLabel() ==0){
							break;
						}
						if(i ==3){
							operationObject = this.getOperationObject(upgrade, "cancelSoftWare");
							super.sendAction(operationObject);
							return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
						}
					}
				}
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
			if(operationObjectResult.isSuccess()){
				return operationObjectResult.getActionObjectList().get(0).getStatus(); 
			}else{
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}
	
	/**
	 * 封装OperationObject
	 * 
	 * @param e1Info
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(Upgrade upgrade,String type) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(upgrade.getSiteId());
			neObject.setControlPanelAddress(upgrade.getCardAddress());
			if(neObject.getControlPanelAddress() == 2 || neObject.getControlPanelAddress() == 3){
				neObject.setControlPanelType(118489105);
			}
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			actionObject.setSoftwareUpdate(this.getSoftwareUpdate(upgrade,neObject));
			operationObject.getActionObjectList().add(actionObject);
			

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private SoftwareUpdate getSoftwareUpdate(Upgrade upgrade,NEObject neObject) {
		
		SoftwareUpdate softwareUpdate = new SoftwareUpdate();
		softwareUpdate.getNeObject().setNeAddress(neObject.getNeAddress());
		softwareUpdate.getNeObject().setControlPanelType(neObject.getControlPanelType());
		softwareUpdate.getNeObject().setControlPanelGroupId(neObject.getControlPanelGroupId());
		softwareUpdate.getNeObject().setControlPanelAddress(neObject.getControlPanelAddress());
		softwareUpdate.setFileLong(upgrade.getFileLength());
		softwareUpdate.setExcursion(upgrade.getExcursion());
		softwareUpdate.setDataLong(upgrade.getDataLong());
		softwareUpdate.setDatabag(upgrade.getDataEach());
		
		return softwareUpdate;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}
	
}
