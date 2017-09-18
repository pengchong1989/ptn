package com.nms.db.bean.ptn;

import java.io.Serializable;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class MacManagementInfo extends ViewDataObj implements Serializable {
	private static final long serialVersionUID = -2637445145868291127L;
	private int id;
	private int siteId;
	private int vlanId;//Vlan值：1~4094（默认1）
	private String mac;//黑名单MAC：（最多50个）（默认00-00-00-00-00-00）
	private int portId;
	private String portNa;
	
	public String getPortNa() {
		return portNa;
	}

	public void setPortNa(String portNa) {
		this.portNa = portNa;
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", getId());
		this.putClientProperty("portId", getPortName());
		this.putClientProperty("mac", getMac());
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
}
