package com.nms.db.bean.ptn.oamStatus;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class OamTraceHops extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915290557100271924L;
	private int id;
	private int hops;
	private String farMac;
	private int inSlot;
	private int inPort;
	private String inPortName;
	private int inAction;//入口动作
	private int transmitAction;//转发动作
	private int isTransmitAction;//是否转发
	private int outSlot;
	private int outPort;
	private int outAction;//出口动作
	private int isMep;
	private String outPortName;
	
	
	
	public String getInPortName() {
		return inPortName;
	}

	public void setInPortName(String inPortName) {
		this.inPortName = inPortName;
	}

	public String getOutPortName() {
		return outPortName;
	}

	public void setOutPortName(String outPortName) {
		this.outPortName = outPortName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHops() {
		return hops;
	}

	public void setHops(int hops) {
		this.hops = hops;
	}


	public String getFarMac() {
		return farMac;
	}

	public void setFarMac(String farMac) {
		this.farMac = farMac;
	}

	public int getInSlot() {
		return inSlot;
	}

	public void setInSlot(int inSlot) {
		this.inSlot = inSlot;
	}

	public int getInPort() {
		return inPort;
	}

	public void setInPort(int inPort) {
		this.inPort = inPort;
	}

	public int getInAction() {
		return inAction;
	}

	public void setInAction(int inAction) {
		this.inAction = inAction;
	}

	public int getTransmitAction() {
		return transmitAction;
	}

	public void setTransmitAction(int transmitAction) {
		this.transmitAction = transmitAction;
	}

	public int getIsTransmitAction() {
		return isTransmitAction;
	}

	public void setIsTransmitAction(int isTransmitAction) {
		this.isTransmitAction = isTransmitAction;
	}

	public int getOutSlot() {
		return outSlot;
	}

	public void setOutSlot(int outSlot) {
		this.outSlot = outSlot;
	}

	public int getOutPort() {
		return outPort;
	}

	public void setOutPort(int outPort) {
		this.outPort = outPort;
	}

	public int getOutAction() {
		return outAction;
	}

	public void setOutAction(int outAction) {
		this.outAction = outAction;
	}

	public int getIsMep() {
		return isMep;
	}

	public void setIsMep(int isMep) {
		this.isMep = isMep;
	}

	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		this.putClientProperty("id", getID());
		this.putClientProperty("hops", getHops());
		this.putClientProperty("farMac", getFarMac());
		this.putClientProperty("inslot", getInSlot());
		this.putClientProperty("inport", getInPortName());
		try {
			this.putClientProperty("inaction", (UiUtil.getCodeByValue("outAction", this.getInAction()+"")).getCodeName());
			this.putClientProperty("transmitAction", (UiUtil.getCodeByValue("TransmitAction", this.getTransmitAction()+"")).getCodeName());
			this.putClientProperty("istransmitAction", (UiUtil.getCodeByValue("basedInVlanId", this.getIsTransmitAction()+"")).getCodeName());
			this.putClientProperty("outslot", getOutSlot());
			this.putClientProperty("outport", getOutPortName());
			this.putClientProperty("outaction",(UiUtil.getCodeByValue("outAction", this.getOutAction()+"")).getCodeName());
			this.putClientProperty("ismep", (UiUtil.getCodeByValue("basedInVlanId", this.getIsMep()+"")).getCodeName());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		
	}

}
