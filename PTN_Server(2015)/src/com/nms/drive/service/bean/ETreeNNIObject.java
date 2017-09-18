package com.nms.drive.service.bean;

public class ETreeNNIObject {
	private int lanPortId = 0;// 仿真LAN-ID
	private int pwID = 0;// PW ID 业务ID
	private int downTagRecognition = 0;// (仿真LAN口组)下话TAG识别(0)/1=(不识别)/识别
	private int upTagBehavior = 0; // (仿真LAN口组)上话TAG行为(0)/1/2=(NOP)/增加/删除
	private int upTagValnId = 0;// (仿真LAN口组)上话增加VLAN ID
	private int upTagValnPri = 0;// (仿真LAN口组)上话增加VLAN PRI
	private int macAddresslearn = 0;// (仿真LAN口组)MAC地址学习0/(1)=禁止/(使能)
	private int portSplitHorizon = 0;// (仿真LAN口组)水平分割(0)/1=(关)/开
	private int controlEnabl=1;//控制字使能
	public int getLanPortId() {
		return lanPortId;
	}

	public void setLanPortId(int lanPortId) {
		this.lanPortId = lanPortId;
	}

	public int getPwID() {
		return pwID;
	}

	public void setPwID(int pwID) {
		this.pwID = pwID;
	}

	public int getDownTagRecognition() {
		return downTagRecognition;
	}

	public void setDownTagRecognition(int downTagRecognition) {
		this.downTagRecognition = downTagRecognition;
	}

	public int getUpTagBehavior() {
		return upTagBehavior;
	}

	public void setUpTagBehavior(int upTagBehavior) {
		this.upTagBehavior = upTagBehavior;
	}

	public int getUpTagValnId() {
		return upTagValnId;
	}

	public void setUpTagValnId(int upTagValnId) {
		this.upTagValnId = upTagValnId;
	}

	public int getUpTagValnPri() {
		return upTagValnPri;
	}

	public void setUpTagValnPri(int upTagValnPri) {
		this.upTagValnPri = upTagValnPri;
	}

	public int getMacAddresslearn() {
		return macAddresslearn;
	}

	public void setMacAddresslearn(int macAddresslearn) {
		this.macAddresslearn = macAddresslearn;
	}

	public int getPortSplitHorizon() {
		return portSplitHorizon;
	}

	public void setPortSplitHorizon(int portSplitHorizon) {
		this.portSplitHorizon = portSplitHorizon;
	}

	public int getControlEnabl() {
		return controlEnabl;
	}

	public void setControlEnabl(int controlEnabl) {
		this.controlEnabl = controlEnabl;
	}

}
