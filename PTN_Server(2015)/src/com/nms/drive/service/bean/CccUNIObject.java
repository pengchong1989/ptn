package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class CccUNIObject {

	private int slot = 0;// 槽位号
	private int port = 0;// 端口号
	private int lanPortId = 0;// LAN口-ID
	private int portSplitHorizon = 0;// (端口)水平分割(0)/1=(关)/开
	private int VCTrafficPolicing = 0;// VC流量监管使能(0)/1=(关)/开
	private int model = 0;// (端口)模式(0)/1/2/=(不使能)/trTCM/Modified trTCM
	private int cir = 0;// (端口)CIR
	private int pir = 0;// (端口)PIR
	private int cm = 0;// (端口)CM(0)/1=(color-blind)/color-aware
	private int cbs = 0;// (端口)CBS
	private int pbs = 0;// (端口)PBS
	private int upTagRecognition = 0;// (端口)上话TAG识别(0)/1=(不识别)/识别
	private int downTagBehavior = 0; // (端口)下话TAG行为(0)/1/2=(NOP)/增加/删除
	private int downTagValnId = 0;// (端口)下话增加VLAN ID
	private int downTagValnPri = 0;// (端口)下话增加VLAN PRI
	private int macAddresslearn = 0;// (端口)MAC地址学习0/(1)=禁止/(使能)
	private int vpwsBufferCount = 0;// 流数目的总数

	private List<VpwsBuffer> vpwsBufferList = new ArrayList<VpwsBuffer>();// 流数目

	public int getVpwsBufferCount() {
		return vpwsBufferCount;
	}

	public void setVpwsBufferCount(int vpwsBufferCount) {
		this.vpwsBufferCount = vpwsBufferCount;
	}

	public List<VpwsBuffer> getVpwsBufferList() {
		return vpwsBufferList;
	}

	public void setVpwsBufferList(List<VpwsBuffer> vpwsBufferList) {
		this.vpwsBufferList = vpwsBufferList;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getLanPortId() {
		return lanPortId;
	}

	public void setLanPortId(int lanPortId) {
		this.lanPortId = lanPortId;
	}

	public int getPortSplitHorizon() {
		return portSplitHorizon;
	}

	public void setPortSplitHorizon(int portSplitHorizon) {
		this.portSplitHorizon = portSplitHorizon;
	}

	public int getVCTrafficPolicing() {
		return VCTrafficPolicing;
	}

	public void setVCTrafficPolicing(int trafficPolicing) {
		VCTrafficPolicing = trafficPolicing;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getCir() {
		return cir;
	}

	public void setCir(int cir) {
		this.cir = cir;
	}

	public int getPir() {
		return pir;
	}

	public void setPir(int pir) {
		this.pir = pir;
	}

	public int getCm() {
		return cm;
	}

	public void setCm(int cm) {
		this.cm = cm;
	}

	public int getCbs() {
		return cbs;
	}

	public void setCbs(int cbs) {
		this.cbs = cbs;
	}

	public int getPbs() {
		return pbs;
	}

	public void setPbs(int pbs) {
		this.pbs = pbs;
	}

	public int getUpTagRecognition() {
		return upTagRecognition;
	}

	public void setUpTagRecognition(int upTagRecognition) {
		this.upTagRecognition = upTagRecognition;
	}

	public int getDownTagBehavior() {
		return downTagBehavior;
	}

	public void setDownTagBehavior(int downTagBehavior) {
		this.downTagBehavior = downTagBehavior;
	}

	public int getDownTagValnId() {
		return downTagValnId;
	}

	public void setDownTagValnId(int downTagValnId) {
		this.downTagValnId = downTagValnId;
	}

	public int getDownTagValnPri() {
		return downTagValnPri;
	}

	public void setDownTagValnPri(int downTagValnPri) {
		this.downTagValnPri = downTagValnPri;
	}

	public int getMacAddresslearn() {
		return macAddresslearn;
	}

	public void setMacAddresslearn(int macAddresslearn) {
		this.macAddresslearn = macAddresslearn;
	}

}
