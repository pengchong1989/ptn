package com.nms.db.bean.ptn;


import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class SsMacStudyInfo extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318487589378511002L;
	private int id;
	private int macId;
	private int vlan;
	private int portId;
	private int macCount;
	private String macAddress;
	private int siteId;//设备id




	public int getMacId() {
		return macId;
	}






	public void setMacId(int macId) {
		this.macId = macId;
	}






	public int getVlan() {
		return vlan;
	}






	public void setVlan(int vlan) {
		this.vlan = vlan;
	}






	public int getMacCount() {
		return macCount;
	}






	public void setMacCount(int macCount) {
		this.macCount = macCount;
	}






	public String getMacAddress() {
		return macAddress;
	}






	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}






	public int getSiteId() {
		return siteId;
	}






	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}






	public int getPortId() {
		return portId;
	}






	public void setPortId(int portId) {
		this.portId = portId;
	}






	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		try {
			this.putClientProperty("id", this.getId());
			this.putClientProperty("macId", this.getMacId());
			this.putClientProperty("vlan",this.getVlan());
			this.putClientProperty("portName",getPortName());
			this.putClientProperty("macAddress", this.getMacAddress());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}




	private String getPortName() {
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			return portService.selectPortybyid(this.getPortId()).getPortName();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		finally
		{
			UiUtil.closeService_MB(portService);
		}
		return "";
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}

	





	



}
