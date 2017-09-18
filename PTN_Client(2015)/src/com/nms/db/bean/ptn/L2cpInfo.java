package com.nms.db.bean.ptn;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;

/**
 * 
 * L2CP 配置块 对象
 * @author zk
 *
 */
public class L2cpInfo extends ViewDataObj implements Serializable{


	private static final long serialVersionUID = 3079441365306477654L;
	private int id;
	private int siteId;
	private int l2cpEnable;//L2CP使能:0/1=不使能/使能
	private int bpduTreaty;//BPDU协议：0/1/2=透传/CPU处理/丢弃
	private int lldpTreaty;//LLDP协议：0/1/2=透传/ CPU处理/丢弃
	private int lacpTreaty;//LACP协议：0/1/2=透传/ CPU处理/丢弃
	private int ieeeTreaty;//IEEE802.1x协议：0/1/2=透传/ CPU处理/丢弃
	private String macAddress;//指配目的MAC:00-00-00-00-00-00
	private String treatyNumber;//指配协议号:0x0000 0-65535;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}


	public int getL2cpEnable() {
		return l2cpEnable;
	}


	public void setL2cpEnable(int l2cpEnable) {
		this.l2cpEnable = l2cpEnable;
	}


	public int getBpduTreaty() {
		return bpduTreaty;
	}


	public void setBpduTreaty(int bpduTreaty) {
		this.bpduTreaty = bpduTreaty;
	}


	public int getLldpTreaty() {
		return lldpTreaty;
	}


	public void setLldpTreaty(int lldpTreaty) {
		this.lldpTreaty = lldpTreaty;
	}


	public int getLacpTreaty() {
		return lacpTreaty;
	}


	public void setLacpTreaty(int lacpTreaty) {
		this.lacpTreaty = lacpTreaty;
	}


	public int getIeeeTreaty() {
		return ieeeTreaty;
	}


	public void setIeeeTreaty(int ieeeTreaty) {
		this.ieeeTreaty = ieeeTreaty;
	}


	public String getMacAddress() {
		return macAddress;
	}


	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}


	public String getTreatyNumber() {
		return treatyNumber;
	}

	public void setTreatyNumber(String treatyNumber) {
		this.treatyNumber = treatyNumber;
	}

	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		
	}

}
