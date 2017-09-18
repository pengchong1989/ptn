package com.nms.db.bean.ptn.oam;

public class OamLinkInfo implements java.io.Serializable{
	private static final long serialVersionUID = -5763899642355858907L;
	private int id;
	private int objId; //端口ID
	private String objType;//LINKOAM
	private boolean oamEnable; // OAM使能: 0/1=不使能/使能
	private int mode; // OAM模式：0/1=主动/被动
	private int mib; // 支持MIB库变量查询：0/1=否/是 (对应武汉 对端环回)
	private int maxFrameLength; // 最大帧长：200-1518 (对应武汉 对端变量获取)
	
	private int remoteLoop;// 是否支持远端环回 0/1=否/是
	private int linkEvent;// 链路事件0/1=否/是 (对应武汉  环回请求重传次数)
	private int organicId;// 组织Id (对应武汉  Dying gasp使能)
	private int factoryInfo;// 厂商信息 (对应武汉  本端critical事件使能)
	private int sendCycle;// 发包周期
	private int linkfailCycle;// linkfail周期

	private int errorFrameEvent; // 支持错误事件帧通知: 0/1=否/是
	private int lpb; // 支持环回命令: 0/1=否/是
	private int unDirection; // 支持单向OAM: 0/1=否/是
	private int reserve1;
	private int reserve2;
	private int responseOutTimeThreshold;// 回环超时时间  应答超时门限=5-10 单位：秒

	private int errorSymboEventCycle; // 错误符号事件周期=1-60 单位：秒
	private int errorSymboEventThreshold; // 错误符号事件门限=0-1-255
	private int errorFrameEventCycle; // 错误帧事件周期=1-60 单位：秒
	private int errorFrameEventThreshold; // 错误帧事件门限=0-1-255
	private int errorFrameCycleEventCycle; // 错误帧周期事件周期=1-60 单位：秒
	private int errorFrameCycleEventThreshold; // 错误帧周期事件门限=0-5-255
	private int errorFrameSecondEventCycle; // 错误帧秒事件周期=10-60-900 单位：秒
	private int errorFrameSecondEventThreshold; // 错误帧秒事件门限=0-1-255
	private int reserve3;
	private int siteId;
	private int equipExit;  //设备上如果存在为0 ，不存在为1
	private int oamFarme;//每秒发送oam帧上限： 1-10-100
	public int getOamFarme() {
		return oamFarme;
	}

	public void setOamFarme(int oamFarme) {
		this.oamFarme = oamFarme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public boolean isOamEnable() {
		return oamEnable;
	}

	public void setOamEnable(boolean oamEnable) {
		this.oamEnable = oamEnable;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMib() {
		return mib;
	}

	public void setMib(int mib) {
		this.mib = mib;
	}

	public int getErrorFrameEvent() {
		return errorFrameEvent;
	}

	public void setErrorFrameEvent(int errorFrameEvent) {
		this.errorFrameEvent = errorFrameEvent;
	}

	public int getLpb() {
		return lpb;
	}

	public void setLpb(int lpb) {
		this.lpb = lpb;
	}

	public int getUnDirection() {
		return unDirection;
	}

	public void setUnDirection(int unDirection) {
		this.unDirection = unDirection;
	}

	public int getMaxFrameLength() {
		return maxFrameLength;
	}

	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}

	public int getResponseOutTimeThreshold() {
		return responseOutTimeThreshold;
	}

	public void setResponseOutTimeThreshold(int responseOutTimeThreshold) {
		this.responseOutTimeThreshold = responseOutTimeThreshold;
	}

	public int getErrorSymboEventCycle() {
		return errorSymboEventCycle;
	}

	public void setErrorSymboEventCycle(int errorSymboEventCycle) {
		this.errorSymboEventCycle = errorSymboEventCycle;
	}

	public int getErrorSymboEventThreshold() {
		return errorSymboEventThreshold;
	}

	public void setErrorSymboEventThreshold(int errorSymboEventThreshold) {
		this.errorSymboEventThreshold = errorSymboEventThreshold;
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

	public int getErrorFrameCycleEventCycle() {
		return errorFrameCycleEventCycle;
	}

	public void setErrorFrameCycleEventCycle(int errorFrameCycleEventCycle) {
		this.errorFrameCycleEventCycle = errorFrameCycleEventCycle;
	}

	public int getErrorFrameCycleEventThreshold() {
		return errorFrameCycleEventThreshold;
	}

	public void setErrorFrameCycleEventThreshold(
			int errorFrameCycleEventThreshold) {
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

	public void setErrorFrameSecondEventThreshold(
			int errorFrameSecondEventThreshold) {
		this.errorFrameSecondEventThreshold = errorFrameSecondEventThreshold;
	}

	public int getReserve1() {
		return reserve1;
	}

	public void setReserve1(int reserve1) {
		this.reserve1 = reserve1;
	}

	public int getReserve2() {
		return reserve2;
	}

	public void setReserve2(int reserve2) {
		this.reserve2 = reserve2;
	}

	public int getReserve3() {
		return reserve3;
	}

	public void setReserve3(int reserve3) {
		this.reserve3 = reserve3;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getRemoteLoop() {
		return remoteLoop;
	}

	public void setRemoteLoop(int remoteLoop) {
		this.remoteLoop = remoteLoop;
	}

	public int getLinkEvent() {
		return linkEvent;
	}

	public void setLinkEvent(int linkEvent) {
		this.linkEvent = linkEvent;
	}

	public int getOrganicId() {
		return organicId;
	}

	public void setOrganicId(int organicId) {
		this.organicId = organicId;
	}

	public int getFactoryInfo() {
		return factoryInfo;
	}

	public void setFactoryInfo(int factoryInfo) {
		this.factoryInfo = factoryInfo;
	}

	public int getSendCycle() {
		return sendCycle;
	}

	public void setSendCycle(int sendCycle) {
		this.sendCycle = sendCycle;
	}

	public int getLinkfailCycle() {
		return linkfailCycle;
	}

	public void setLinkfailCycle(int linkfailCycle) {
		this.linkfailCycle = linkfailCycle;
	}

	public int getEquipExit() {
		return equipExit;
	}

	public void setEquipExit(int equipExit) {
		this.equipExit = equipExit;
	}

}
