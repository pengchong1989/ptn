package com.nms.db.bean.ptn;

/**
 * 
 * function:端口mac地址学习数目查询 和 基于vlan的mac地址学习数目查询
 * Bean d对象
 * @author zk
 *
 */
public class MacStudyBean implements java.io.Serializable{

	private int siteId;
	private int portNumber;//端口号
	private int vlanId;
	private String macNumber;//mac学习数目
	
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public String getMacNumber() {
		return macNumber;
	}
	public void setMacNumber(String macNumber) {
		this.macNumber = macNumber;
	}
}
