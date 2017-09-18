package com.nms.drive.service.bean.status;

public class PortStatusObject {
	
	private int portNumber;
	private int status;//link down/link up
	private int model;//模式 FE/GE/FX
	private int receivePackage;//收包数
	private int launchPackage;//发包数
	private String portName;
	private int loopState;//端口环回状态
	
	
	public int getLoopState() {
		return loopState;
	}
	public void setLoopState(int loopState) {
		this.loopState = loopState;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getReceivePackage() {
		return receivePackage;
	}
	public void setReceivePackage(int receivePackage) {
		this.receivePackage = receivePackage;
	}
	public int getLaunchPackage() {
		return launchPackage;
	}
	public void setLaunchPackage(int launchPackage) {
		this.launchPackage = launchPackage;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	
}
