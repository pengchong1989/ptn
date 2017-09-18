package com.nms.db.bean.ptn.path;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class GroupSpreadInfo extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int smId;
	private int vpls_vs;
	private String portChoice;
	private String macAddress;
	private int siteId;
	
	
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

	public int getSmId() {
		return smId;
	}

	public void setSmId(int smId) {
		this.smId = smId;
	}

	public int getVpls_vs() {
		return vpls_vs;
	}

	public void setVpls_vs(int vplsVs) {
		vpls_vs = vplsVs;
	}

	public String getPortChoice() {
		return portChoice;
	}

	public void setPortChoice(String portChoice) {
		this.portChoice = portChoice;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("smId", getSmId());
			getClientProperties().put("vpls_vs", getVpls_vs());
			getClientProperties().put("portChoice", getPortChoice());
			getClientProperties().put("macAddress", getMacAddress());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		
	}

}
