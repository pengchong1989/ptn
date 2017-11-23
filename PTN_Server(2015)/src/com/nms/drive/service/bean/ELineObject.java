package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ELineObject VPWS业务
 * @author 彭冲
 *
 */
public class ELineObject {

	private int vpwsId = 0;// VPWS业务实例ID
	private int wrap = 0;//(NNI接口设置)是否为WRAP业务:0/1=不是/是
	private int pwIdNNI = 2;// (NNI接口设置) ID:1-512
	private int upTagNNI = 0;// (NNI接口设置)下话TAG识别 (0)/1=(不识别)/识别
	private int downTagNNI = 0;// (NNI接口设置)上话TAG行为 (0)/1/2=(NOP)/增加/删除
	private int vlanIdNNI = 0;// (NNI接口设置)上话增加VLAN ID
	private int vlanPriNNI = 0;// (NNI接口设置)上话增加VLAN PRI

	private int slotUNI = 10;// UNI接口设置) 入槽位号
	private int protUNI = 0;// UNI接口设置) 入端口号
	private int cirUNI = 0;// UNI接口设置) CIR
	private int pirUNI = 0;// UNI接口设置) PIR
	private int cm = 0;// (端口)CM(0)/1=(color-blind)/color-aware
	private int cbs = 0;// (端口)CBS
	private int pbs = 0;// (端口)PBS
	private int modelUNI = 0;// UNI接口设置 模式 (0)/1/2/=(不使能)/trTCM/Modified trTCM
	private int upTagUNI = 0;// (NNI接口设置)上话TAG识别 (0)/1=(不识别)/识别
	private int downTagUNI = 0;// (NNI接口设置)下话TAG行为 (0)/1/2=(NOP)/增加/删除
	private int vlanIdUNI = 0;// (NNI接口设置)上话增加VLAN ID
	private int vlanPriUNI = 0;// (NNI接口设置)上话增加VLAN PRI
	private int controlEnabl=1;//(NNI接口设置)控制字使能
	private int tVCTrafficPolicing ;//TMC流量监控使能
	private List<VpwsBuffer> vpwsBufferList = new ArrayList<VpwsBuffer>();
	private int type = 1;//业务类型:1/2=ELINE/CES
	private int role = 0;
	private int downTpidUni;
	private int downTpidNni;
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int gettVCTrafficPolicing() {
		return tVCTrafficPolicing;
	}

	public void settVCTrafficPolicing(int tVCTrafficPolicing) {
		this.tVCTrafficPolicing = tVCTrafficPolicing;
	}

	public int getControlEnabl() {
		return controlEnabl;
	}

	public void setControlEnabl(int controlEnabl) {
		this.controlEnabl = controlEnabl;
	}

	public int getWrap() {
		return wrap;
	}

	public void setWrap(int wrap) {
		this.wrap = wrap;
	}

	public int getUpTagNNI() {
		return upTagNNI;
	}

	public void setUpTagNNI(int upTagNNI) {
		this.upTagNNI = upTagNNI;
	}

	public int getDownTagNNI() {
		return downTagNNI;
	}

	public void setDownTagNNI(int downTagNNI) {
		this.downTagNNI = downTagNNI;
	}

	public int getVlanIdNNI() {
		return vlanIdNNI;
	}

	public void setVlanIdNNI(int vlanIdNNI) {
		this.vlanIdNNI = vlanIdNNI;
	}

	public int getVlanPriNNI() {
		return vlanPriNNI;
	}

	public void setVlanPriNNI(int vlanPriNNI) {
		this.vlanPriNNI = vlanPriNNI;
	}

	public int getPwIdNNI() {
		return pwIdNNI;
	}

	public void setPwIdNNI(int pwIdNNI) {
		this.pwIdNNI = pwIdNNI;
	}

	public int getSlotUNI() {
		return slotUNI;
	}

	public void setSlotUNI(int slotUNI) {
		this.slotUNI = slotUNI;
	}

	public int getProtUNI() {
		return protUNI;
	}

	public void setProtUNI(int protUNI) {
		this.protUNI = protUNI;
	}

	public int getCirUNI() {
		return cirUNI;
	}

	public void setCirUNI(int cirUNI) {
		this.cirUNI = cirUNI;
	}

	public int getPirUNI() {
		return pirUNI;
	}

	public void setPirUNI(int pirUNI) {
		this.pirUNI = pirUNI;
	}

	public int getModelUNI() {
		return modelUNI;
	}

	public void setModelUNI(int modelUNI) {
		this.modelUNI = modelUNI;
	}

	public int getUpTagUNI() {
		return upTagUNI;
	}

	public void setUpTagUNI(int upTagUNI) {
		this.upTagUNI = upTagUNI;
	}

	public int getDownTagUNI() {
		return downTagUNI;
	}

	public void setDownTagUNI(int downTagUNI) {
		this.downTagUNI = downTagUNI;
	}

	public int getVlanIdUNI() {
		return vlanIdUNI;
	}

	public void setVlanIdUNI(int vlanIdUNI) {
		this.vlanIdUNI = vlanIdUNI;
	}

	public int getVlanPriUNI() {
		return vlanPriUNI;
	}

	public void setVlanPriUNI(int vlanPriUNI) {
		this.vlanPriUNI = vlanPriUNI;
	}

	public List<VpwsBuffer> getVpwsBufferList() {
		return vpwsBufferList;
	}

	public void setVpwsBufferList(List<VpwsBuffer> vpwsBufferList) {
		this.vpwsBufferList = vpwsBufferList;
	}

	public int getVpwsId() {
		return vpwsId;
	}

	public void setVpwsId(int vpwsId) {
		this.vpwsId = vpwsId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getDownTpidUni() {
		return downTpidUni;
	}

	public void setDownTpidUni(int downTpidUni) {
		this.downTpidUni = downTpidUni;
	}

	public int getDownTpidNni() {
		return downTpidNni;
	}

	public void setDownTpidNni(int downTpidNni) {
		this.downTpidNni = downTpidNni;
	}

	
}
