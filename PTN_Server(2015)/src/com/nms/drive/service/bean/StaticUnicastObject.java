package com.nms.drive.service.bean;
/**
 * 静态单播(0BH)
 * @author 闵何招
 *
 */
public class StaticUnicastObject {
	private int suId=0;//SU ID 1-64
	private int vplsVs=0;//VPLS-VS 选择: 0/1/2/3/4/5/6/7/8/…/40H=无/VS1/VS2/../VS64
	private int portChoice=0;//端口选择:1/2/3/../16H/../4CH=LAN1/LAN2/LAN3/../LAN22/eLAN1 / eLAN2/../ eLAN64
	private String macAddress="0,0,0,0,0";//MAC地址:0x00 00 00 00 00 01
	public int getSuId() {
		return suId;
	}
	public void setSuId(int suId) {
		this.suId = suId;
	}
	public int getVplsVs() {
		return vplsVs;
	}
	public void setVplsVs(int vplsVs) {
		this.vplsVs = vplsVs;
	}
	public int getPortChoice() {
		return portChoice;
	}
	public void setPortChoice(int portChoice) {
		this.portChoice = portChoice;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	

}
