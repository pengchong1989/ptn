package com.nms.drive.service.bean;

import java.io.File;
import java.util.List;

public class NEObject {

	private int neAddress = 513;// NE地址
	private int controlPanelType = 50332320;// 盘类型
	private int controlPanelGroupId = 1;// 盘组号
	private int controlPanelAddress = 1;// 盘地址
	private int neStatus = 1;
	private String manageIP = "10.18.1.2";
	private String totalTime;//上电累计时间
	private String coverIP;//掩码
	private String complieTime;//编译时间
	private String softEdition;//软件版本
	private String hardEdition;//硬件版本
	private int currectTime;//校时状态
	private int manageSatus;//管理配置状态
	private int equiplmentStatus;//设备配置状态
	private File file;//网元上载/下载时，界面获取的file
	private int statusMark;//网元某状态标志
	private String bootTime;//boot编译时间
	private String fpgaTime;//fpag编译时间
	private String plateNumber;//盘号
	private String cardNumber;//卡号
	private String createPlateNumber;//制盘时间
	private String programmeTime;//编程时间
	private int FieldID;//域id
	private String Site_Hum_Id;//网元id
	private String CellTime;	//创建时间
	private int tdmLoopback;
	private int isCreateDiscardFlow; // 丢弃流的创建和删除 默认 0:创建 1:删除  
	private String sn;//SN码
	private String cellType;//设备类型
	private String neIP;//网元IP
	private String rootIP;//父节点IP
	private String linkPort;//端口号
	private long l;//网元时间
    private String neMAC;//设备MAC地址
	private List<String> vlanMac;

	public String getNeMAC() {
		return neMAC;
	}

	public void setNeMAC(String neMAC) {
		this.neMAC = neMAC;
	}
	public long getL() {
		return l;
	}

	public void setL(long l) {
		this.l = l;
	}

	public String getNeIP() {
		return neIP;
	}

	public void setNeIP(String neIP) {
		this.neIP = neIP;
	}

	public String getCellType() {
		return cellType;
	}

	public void setCellType(String cellType) {
		this.cellType = cellType;
	}

	public int getNeAddress() {
		return neAddress;
	}

	public void setNeAddress(int neAddress) {
		this.neAddress = neAddress;
	}

	public int getControlPanelType() {
		return controlPanelType;
	}

	public void setControlPanelType(int controlPanelType) {
		this.controlPanelType = controlPanelType;
	}

	public int getControlPanelGroupId() {
		return controlPanelGroupId;
	}

	public void setControlPanelGroupId(int controlPanelGroupId) {
		this.controlPanelGroupId = controlPanelGroupId;
	}

	public int getControlPanelAddress() {
		return controlPanelAddress;
	}

	public void setControlPanelAddress(int controlPanelAddress) {
		this.controlPanelAddress = controlPanelAddress;
	}

	public int getNeStatus() {
		return neStatus;
	}

	public void setNeStatus(int neStatus) {
		this.neStatus = neStatus;
	}

	public String getManageIP() {
		return manageIP;
	}

	public void setManageIP(String manageIP) {
		this.manageIP = manageIP;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getCoverIP() {
		return coverIP;
	}

	public void setCoverIP(String coverIP) {
		this.coverIP = coverIP;
	}

	public String getComplieTime() {
		return complieTime;
	}

	public void setComplieTime(String complieTime) {
		this.complieTime = complieTime;
	}

	public String getSoftEdition() {
		return softEdition;
	}

	public void setSoftEdition(String softEdition) {
		this.softEdition = softEdition;
	}

	public String getHardEdition() {
		return hardEdition;
	}

	public void setHardEdition(String hardEdition) {
		this.hardEdition = hardEdition;
	}

	public int getCurrectTime() {
		return currectTime;
	}

	public void setCurrectTime(int currectTime) {
		this.currectTime = currectTime;
	}

	public int getManageSatus() {
		return manageSatus;
	}

	public void setManageSatus(int manageSatus) {
		this.manageSatus = manageSatus;
	}

	public int getEquiplmentStatus() {
		return equiplmentStatus;
	}

	public void setEquiplmentStatus(int equiplmentStatus) {
		this.equiplmentStatus = equiplmentStatus;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getStatusMark() {
		return statusMark;
	}

	public void setStatusMark(int statusMark) {
		this.statusMark = statusMark;
	}

	public String getBootTime() {
		return bootTime;
	}

	public void setBootTime(String bootTime) {
		this.bootTime = bootTime;
	}

	public String getFpgaTime() {
		return fpgaTime;
	}

	public void setFpgaTime(String fpgaTime) {
		this.fpgaTime = fpgaTime;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCreatePlateNumber() {
		return createPlateNumber;
	}

	public void setCreatePlateNumber(String createPlateNumber) {
		this.createPlateNumber = createPlateNumber;
	}

	public String getProgrammeTime() {
		return programmeTime;
	}

	public void setProgrammeTime(String programmeTime) {
		this.programmeTime = programmeTime;
	}

	public int getFieldID() {
		return FieldID;
	}

	public void setFieldID(int fieldID) {
		FieldID = fieldID;
	}

	public String getSite_Hum_Id() {
		return Site_Hum_Id;
	}

	public void setSite_Hum_Id(String siteHumId) {
		Site_Hum_Id = siteHumId;
	}

	public String getCellTime() {
		return CellTime;
	}

	public void setCellTime(String cellTime) {
		CellTime = cellTime;
	}

	public int getTdmLoopback() {
		return tdmLoopback;
	}

	public void setTdmLoopback(int tdmLoopback) {
		this.tdmLoopback = tdmLoopback;
	}

	public int getIsCreateDiscardFlow() {
		return isCreateDiscardFlow;
	}

	public void setIsCreateDiscardFlow(int isCreateDiscardFlow) {
		this.isCreateDiscardFlow = isCreateDiscardFlow;
	}
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getRootIP() {
		return rootIP;
	}

	public void setRootIP(String rootIP) {
		this.rootIP = rootIP;
	}

	public String getLinkPort() {
		return linkPort;
	}

	public void setLinkPort(String linkPort) {
		this.linkPort = linkPort;
	}

	public List<String> getVlanMac() {
		return vlanMac;
	}

	public void setVlanMac(List<String> vlanMac) {
		this.vlanMac = vlanMac;
	}
	
	
}
