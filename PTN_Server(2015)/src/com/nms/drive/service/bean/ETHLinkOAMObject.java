package com.nms.drive.service.bean;

public class ETHLinkOAMObject {
	private int slot = 0;//槽位号
	private int port = 0;//端口号
	private int oam = 0;//OAM
	private int maxFrame =0;//最大帧长
	private int responseOverTime = 0;//	应答超时门限 
	private int errorSignCycle = 0;  //	错误符号事件周期  
	private int errorSignThreshold = 0;//	错误符号事件门限
	private int errorFrameEventCycle = 0;//	错误帧事件周期  
	private int errorFrameEventThreshold = 0;//	错误帧事件门限   
	private int errorFrameCylceEventCycle = 0;//	错误帧周期事件周期  
	private int errorFrameCycleEventThreshold = 0;//	错误帧周期事件门限 
	private int errorFrameSecondEventCycle = 0;//	错误帧秒事件周期
	private int errorFrameSecondEventThreshold = 0;//	错误帧秒事件门限   
	private int cout;//条目id
	private int oamModel;//oam模式
	private int loopBack;//环回使能
	private int loopBackTime;//环回请求超时时间
	private int loopBackNumber;//环回请求重传次数
	private int oppositeLoop;//对端环回
	private int oppositeVariablel;//对端变量
	private int dyingGasp;//Dying gasp使能
	private int critical;//本端critical事件使能
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
	public int getOam() {
		return oam;
	}
	public void setOam(int oam) {
		this.oam = oam;
	}
	public int getMaxFrame() {
		return maxFrame;
	}
	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}
	public int getResponseOverTime() {
		return responseOverTime;
	}
	public void setResponseOverTime(int responseOverTime) {
		this.responseOverTime = responseOverTime;
	}
	public int getErrorSignCycle() {
		return errorSignCycle;
	}
	public void setErrorSignCycle(int errorSignCycle) {
		this.errorSignCycle = errorSignCycle;
	}
	public int getErrorSignThreshold() {
		return errorSignThreshold;
	}
	public void setErrorSignThreshold(int errorSignThreshold) {
		this.errorSignThreshold = errorSignThreshold;
	}
	public int getErrorFrameEventCycle() {
		return errorFrameEventCycle;
	}
	public void setErrorFrameEventCycle(int errorFrameEventCycle) {
		this.errorFrameEventCycle = errorFrameEventCycle;
	}
	public int getErrorFrameEventThreshold() {
		return errorFrameEventThreshold;
	}
	public void setErrorFrameEventThreshold(int errorFrameEventThreshold) {
		this.errorFrameEventThreshold = errorFrameEventThreshold;
	}
	public int getErrorFrameCylceEventCycle() {
		return errorFrameCylceEventCycle;
	}
	public void setErrorFrameCylceEventCycle(int errorFrameCylceEventCycle) {
		this.errorFrameCylceEventCycle = errorFrameCylceEventCycle;
	}
	public int getErrorFrameCycleEventThreshold() {
		return errorFrameCycleEventThreshold;
	}
	public void setErrorFrameCycleEventThreshold(int errorFrameCycleEventThreshold) {
		this.errorFrameCycleEventThreshold = errorFrameCycleEventThreshold;
	}
	public int getErrorFrameSecondEventCycle() {
		return errorFrameSecondEventCycle;
	}
	public void setErrorFrameSecondEventCycle(int errorFrameSecondEventCycle) {
		this.errorFrameSecondEventCycle = errorFrameSecondEventCycle;
	}
	public int getErrorFrameSecondEventThreshold() {
		return errorFrameSecondEventThreshold;
	}
	public void setErrorFrameSecondEventThreshold(int errorFrameSecondEventThreshold) {
		this.errorFrameSecondEventThreshold = errorFrameSecondEventThreshold;
	}
	public int getCout() {
		return cout;
	}
	public void setCout(int cout) {
		this.cout = cout;
	}
	public int getOamModel() {
		return oamModel;
	}
	public void setOamModel(int oamModel) {
		this.oamModel = oamModel;
	}
	public int getLoopBack() {
		return loopBack;
	}
	public void setLoopBack(int loopBack) {
		this.loopBack = loopBack;
	}
	public int getLoopBackTime() {
		return loopBackTime;
	}
	public void setLoopBackTime(int loopBackTime) {
		this.loopBackTime = loopBackTime;
	}
	public int getLoopBackNumber() {
		return loopBackNumber;
	}
	public void setLoopBackNumber(int loopBackNumber) {
		this.loopBackNumber = loopBackNumber;
	}
	public int getOppositeLoop() {
		return oppositeLoop;
	}
	public void setOppositeLoop(int oppositeLoop) {
		this.oppositeLoop = oppositeLoop;
	}
	public int getOppositeVariablel() {
		return oppositeVariablel;
	}
	public void setOppositeVariablel(int oppositeVariablel) {
		this.oppositeVariablel = oppositeVariablel;
	}
	public int getDyingGasp() {
		return dyingGasp;
	}
	public void setDyingGasp(int dyingGasp) {
		this.dyingGasp = dyingGasp;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	
	
}
