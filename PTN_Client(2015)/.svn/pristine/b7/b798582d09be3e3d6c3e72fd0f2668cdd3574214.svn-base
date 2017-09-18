package com.nms.db.bean.ptn;

import java.io.Serializable;

public class AllConfigInfo implements Serializable {
	private static final long serialVersionUID = -3335261285205308063L;
	private int id;
	private String singleMACAddress = "00-00-00-00-00-00";// 单盘MAC地址
	private int mirrorModel = 0;// 镜像模式
	private String mirrorModelLog;//镜像模式，log日志使用
	private int addressAgeSwitch = 1;// 地址老化开关
	private int mirrorByPort = 0;// 被镜像端口
	private String mirrorByPortNameLog;//被镜像端口名称，log日志使用
	private int macAddressAgeDate = 300;// MAC地址老化时间
	private int mirrorPort = 0;// 镜像端口
	private String mirrorPortNameLog;//镜像端口名称，log日志使用
	private int throwWrapDateGap = 1;// 丢包率统计时间间隔
	private int mplsTPControl = 0;// MPLS-TP控制字使能
	private int fdiBIT0 =0;// FDI帧 帧发送使能BIT0:0/1=不使能/使能
	private int channelType = 0;// Channel Type
	private int fdiB1IT3 = 0;// FDI帧 FDI帧MEL:BIT3-BIT1: 0/1/2..6/7
	private int tmcfdiB1IT3 = 0;// FDI帧 FDI帧MEL:BIT3-BIT1: 0/1/2..6/7	
	private int apsRecoverTime=5;//aps等待恢复时间
	private int tmcfdiBIT0 =0;// FDI帧 帧发送使能BIT0:0/1=不使能/使能
	private int apsModel=1;//aps模式
	private int roundEnable;//环路检测功能
	private int vlanMAC;//基于vlan的MAC学习限制
	private int macNumber;//MAC学习限制数
	private int vlanValue=1;//MAC学习限制基于vlan值
	private int equipmentPriority;//设备优先级
	private int lacp;//LACP协议开关
	private int dhcpModel = 2;//DHCP模式
	private int loopCheck = 0;//环路检测发包开关 0/1
	private int ssmModel = 0;//ssm帧模式
	private int twoLayer = 0;//二层功能模式开关 0/1
	private int alarmModel = 1;//掉电告警上联模式 : 1/2/3 NNI侧模式/UNI侧模式/802.3ah模式  （默认NNI侧模式）
	private int alarmPort = 1;//掉电告警上联端口 1/2/3/4/5/6/7 GE1.1/GE1.2/GE1.3/GE1.4/FE1.1/FE1.2 （默认GE1.1）
	private String alarmPortNameLog;//掉电告警上联端口名称，log日志使用
	private int loopAvoid;//环路避免功能开关：0/1 = 关/开
	private int crcVerify = 65535;// CRC校验错门限
	private int throwWrap = 65535;// 丢包个数门限
	private int receiveBadWrap = 65535;// 收坏包数门限
	private int align = 65535;// 对齐错门限
	private int siteId = 0;//网元Id
	private int tmsWorsen = 0;//TMS通道信号劣化门限
	private int tmsLose = 0;//TMS通道信号失效门限
	
	public String getMirrorByPortNameLog() {
		return mirrorByPortNameLog;
	}
	public void setMirrorByPortNameLog(String mirrorByPortNameLog) {
		this.mirrorByPortNameLog = mirrorByPortNameLog;
	}
	public String getMirrorPortNameLog() {
		return mirrorPortNameLog;
	}
	public void setMirrorPortNameLog(String mirrorPortNameLog) {
		this.mirrorPortNameLog = mirrorPortNameLog;
	}
	public String getAlarmPortNameLog() {
		return alarmPortNameLog;
	}
	public void setAlarmPortNameLog(String alarmPortNameLog) {
		this.alarmPortNameLog = alarmPortNameLog;
	}
	public String getMirrorModelLog() {
		return mirrorModelLog;
	}
	public void setMirrorModelLog(String mirrorModelLog) {
		this.mirrorModelLog = mirrorModelLog;
	}
	public int getLoopAvoid() {
		return loopAvoid;
	}
	public void setLoopAvoid(int loopAvoid) {
		this.loopAvoid = loopAvoid;
	}
	public int getAlarmModel() {
		return alarmModel;
	}
	public void setAlarmModel(int alarmModel) {
		this.alarmModel = alarmModel;
	}
	public int getAlarmPort() {
		return alarmPort;
	}
	public void setAlarmPort(int alarmPort) {
		this.alarmPort = alarmPort;
	}
	public int getTwoLayer() {
		return twoLayer;
	}
	public void setTwoLayer(int twoLayer) {
		this.twoLayer = twoLayer;
	}
	public int getDhcpModel() {
		return dhcpModel;
	}
	public void setDhcpModel(int dhcpModel) {
		this.dhcpModel = dhcpModel;
	}
	public int getLacp() {
		return lacp;
	}
	public void setLacp(int lacp) {
		this.lacp = lacp;
	}
	public int getEquipmentPriority() {
		return equipmentPriority;
	}
	public void setEquipmentPriority(int equipmentPriority) {
		this.equipmentPriority = equipmentPriority;
	}
	public int getRoundEnable() {
		return roundEnable;
	}
	public void setRoundEnable(int roundEnable) {
		this.roundEnable = roundEnable;
	}
	public int getApsModel() {
		return apsModel;
	}
	public void setApsModel(int apsModel) {
		this.apsModel = apsModel;
	}
	public int getTmsWorsen() {
		return tmsWorsen;
	}
	public void setTmsWorsen(int tmsWorsen) {
		this.tmsWorsen = tmsWorsen;
	}
	public int getTmsLose() {
		return tmsLose;
	}
	public void setTmsLose(int tmsLose) {
		this.tmsLose = tmsLose;
	}
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
	public int getApsRecoverTime() {
		return apsRecoverTime;
	}
	public void setApsRecoverTime(int apsRecoverTime) {
		this.apsRecoverTime = apsRecoverTime;
	}
	public String getSingleMACAddress() {
		return singleMACAddress;
	}
	public void setSingleMACAddress(String singleMACAddress) {
		this.singleMACAddress = singleMACAddress;
	}
	public int getAddressAgeSwitch() {
		return addressAgeSwitch;
	}
	public void setAddressAgeSwitch(int addressAgeSwitch) {
		this.addressAgeSwitch = addressAgeSwitch;
	}
	public int getMacAddressAgeDate() {
		return macAddressAgeDate;
	}
	public void setMacAddressAgeDate(int macAddressAgeDate) {
		this.macAddressAgeDate = macAddressAgeDate;
	}
	public int getThrowWrapDateGap() {
		return throwWrapDateGap;
	}
	public void setThrowWrapDateGap(int throwWrapDateGap) {
		this.throwWrapDateGap = throwWrapDateGap;
	}
	public int getFdiBIT0() {
		return fdiBIT0;
	}
	public void setFdiBIT0(int fdiBIT0) {
		this.fdiBIT0 = fdiBIT0;
	}
	public int getFdiB1IT3() {
		return fdiB1IT3;
	}
	public void setFdiB1IT3(int fdiB1IT3) {
		this.fdiB1IT3 = fdiB1IT3;
	}
	public int getCrcVerify() {
		return crcVerify;
	}
	public void setCrcVerify(int crcVerify) {
		this.crcVerify = crcVerify;
	}
	public int getThrowWrap() {
		return throwWrap;
	}
	public void setThrowWrap(int throwWrap) {
		this.throwWrap = throwWrap;
	}
	public int getReceiveBadWrap() {
		return receiveBadWrap;
	}
	public void setReceiveBadWrap(int receiveBadWrap) {
		this.receiveBadWrap = receiveBadWrap;
	}
	public int getAlign() {
		return align;
	}
	public void setAlign(int align) {
		this.align = align;
	}
	public int getMirrorModel() {
		return mirrorModel;
	}
	public void setMirrorModel(int mirrorModel) {
		this.mirrorModel = mirrorModel;
	}
	public int getMirrorByPort() {
		return mirrorByPort;
	}
	public void setMirrorByPort(int mirrorByPort) {
		this.mirrorByPort = mirrorByPort;
	}
	public int getMirrorPort() {
		return mirrorPort;
	}
	public void setMirrorPort(int mirrorPort) {
		this.mirrorPort = mirrorPort;
	}
	public int getMplsTPControl() {
		return mplsTPControl;
	}
	public void setMplsTPControl(int mplsTPControl) {
		this.mplsTPControl = mplsTPControl;
	}
	public int getChannelType() {
		return channelType;
	}
	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}
	public int getTmcfdiBIT0() {
		return tmcfdiBIT0;
	}
	public void setTmcfdiBIT0(int tmcfdiBIT0) {
		this.tmcfdiBIT0 = tmcfdiBIT0;
	}
	public int getTmcfdiB1IT3() {
		return tmcfdiB1IT3;
	}
	public void setTmcfdiB1IT3(int tmcfdiB1IT3) {
		this.tmcfdiB1IT3 = tmcfdiB1IT3;
	}
	public int getVlanMAC() {
		return vlanMAC;
	}
	public void setVlanMAC(int vlanMAC) {
		this.vlanMAC = vlanMAC;
	}
	public int getMacNumber() {
		return macNumber;
	}
	public void setMacNumber(int macNumber) {
		this.macNumber = macNumber;
	}
	public int getVlanValue() {
		return vlanValue;
	}
	public void setVlanValue(int vlanValue) {
		this.vlanValue = vlanValue;
	}
	public int getLoopCheck() {
		return loopCheck;
	}
	public void setLoopCheck(int loopCheck) {
		this.loopCheck = loopCheck;
	}
	public int getSsmModel() {
		return ssmModel;
	}
	public void setSsmModel(int ssmModel) {
		this.ssmModel = ssmModel;
	}
	
}
