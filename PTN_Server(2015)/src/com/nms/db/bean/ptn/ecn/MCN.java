package com.nms.db.bean.ptn.ecn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

@SuppressWarnings("serial")
public class MCN extends ViewDataObj {
	private int id;
	private String neId = "";
	private String ip = "";
	private int mtu;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getMtu() {
		return mtu;
	}

	public void setMtu(int mtu) {
		this.mtu = mtu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNeId() {
		return neId;
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		 
		try {

			getClientProperties().put("portname", "MCN/1");
			getClientProperties().put("ipaddress", this.getIp());
			getClientProperties().put("max", this.getMtu());

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
