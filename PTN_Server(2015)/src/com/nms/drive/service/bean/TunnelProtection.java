package com.nms.drive.service.bean;

public class TunnelProtection {

	private int id ;
	private int siteId = 0;  //网元ID
	private String objProtectType ; //对象保护类型 PW LSP
	private int protectionType = 0;// 保护类型:00/01/02 =无/LSP 1+1/LSP 1:1
	private int mainSlot = 255;// 主用槽位:255/10/11/14/15/…/19H=无/Slot10/ Slot11/Slot14/ Slot15/…/ Slot19（界面不显示）
	private int mainPort = 0;// 主用端口:0/1/…/4=无/WAN1/…/WAN4
	private int mainLspID = 0;// 主用LSP ID:1-512
	private int standbySlot = 255;// 备用槽位:255/10/11/14/15/…/19H=无/Slot10/ Slot11/Slot14/ Slot15/…/ Slot19（界面不显示）
	private int standbyPort = 0;// 备用端口:0/1/…/4=无/WAN1/…/WAN4
	private int standbyLspID = 0;// 备用LSP ID:0-512
	private int protractedTime = 0;// 拖延时间:00-FFH =0-255
	private int returnType = 0;// 返回类型:00/01=返回型/不返回型
	private int waittime = 0;//等待恢复时间
	
	private int lspLogicId = 0; //LSP逻辑ID" value="0" mapping="(0)"
	private int operationType = 0;  //业务类型" value="0" mapping="0/1 = VPWS/VPLS"
	
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

	public String getObjProtectType() {
		return objProtectType;
	}

	public void setObjProtectType(String objProtectType) {
		this.objProtectType = objProtectType;
	}

	public int getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(int protectionType) {
		this.protectionType = protectionType;
	}

	public int getMainSlot() {
		return mainSlot;
	}

	public void setMainSlot(int mainSlot) {
		this.mainSlot = mainSlot;
	}

	public int getMainPort() {
		return mainPort;
	}

	public void setMainPort(int mainPort) {
		this.mainPort = mainPort;
	}

	public int getMainLspID() {
		return mainLspID;
	}

	public void setMainLspID(int mainLspID) {
		this.mainLspID = mainLspID;
	}

	public int getStandbySlot() {
		return standbySlot;
	}

	public void setStandbySlot(int standbySlot) {
		this.standbySlot = standbySlot;
	}

	public int getStandbyPort() {
		return standbyPort;
	}

	public void setStandbyPort(int standbyPort) {
		this.standbyPort = standbyPort;
	}

	public int getStandbyLspID() {
		return standbyLspID;
	}

	public void setStandbyLspID(int standbyLspID) {
		this.standbyLspID = standbyLspID;
	}

	public int getProtractedTime() {
		return protractedTime;
	}

	public void setProtractedTime(int protractedTime) {
		this.protractedTime = protractedTime;
	}

	public int getReturnType() {
		return returnType;
	}

	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}

	public int getLspLogicId() {
		return lspLogicId;
	}

	public void setLspLogicId(int lspLogicId) {
		this.lspLogicId = lspLogicId;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

}
