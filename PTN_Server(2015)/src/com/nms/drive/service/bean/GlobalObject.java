package com.nms.drive.service.bean;
/**
 * 全局配置块(03H)
 * @author 张坤
 *
 */
public class GlobalObject {
	private String singleMACAddress = "";// 单盘MAC地址
	private int addressStudyModel = 0;// 地址学习模式
	private int addressAgeSwitch = 0;// 地址老化开关
	private int MACAddressAgeDate = 0;// MAC地址老化时间
	private int throwWrapDateGap = 0;// 丢包率统计时间间隔
	private String  FDIBIT0 ="";// FDI帧 帧发送使能BIT0:0/1=不使能/使能
	private String FDIB1IT3 = "";// FDI帧 FDI帧MEL:BIT3-BIT1: 0/1/2..6/7
	private String  FDIB4IT7 = "0000";// FDI帧 BIT7-BIT4:备用00
	private int apsRecoverTime =0;//aps等待恢复时间
	private int mirrorModel = 0;// 镜像模式
	private int mirrorByPort = 0;// 被镜像端口
	private int mirrorPort = 0;// 镜像端口
	private int MPLSTPControl = 0;// MPLS-TP控制字使能
	private int ChannelType = 0;// Channel Type
	private String  tmcFDIBIT0 ="";// tmcFDI帧 帧发送使能BIT0:0/1=不使能/使能
	private String tmcFDIB1IT3 = "";// tmcFDI帧 FDI帧MEL:BIT3-BIT1: 0/1/2..6/7
	private String  tmcFDIB4IT7 = "0000";// tmcFDI帧 BIT7-BIT4:备用00
	private int apsModel;//aps模式
	private int roundEnable;//环路检测功能
	private int vlanMAC;//基于vlan的MAC学习限制
	private int vlanValue;//MAC学习限制基于vlan值
	private int macNumber;//MAC学习限制数
	private int lacp;//LACP协议开关
	private int equipmentPriority;//设备优先级
	private int dhcpModel;//DHCP模式
	private int loopCheck = 0;//环路检测发包开关 0/1
	private int ssmModel = 0;//ssm帧模式
	private int twoLayer = 0;//二层功能模式开关0/1
	private int alarmModel = 1;//掉电告警上联模式 : 1/2/3 NNI侧模式/UNI侧模式/802.3ah模式  （默认NNI侧模式）
	private int alarmPort = 1;//掉电告警上联端口 1/2/3/4/5/6/7 GE1.1/GE1.2/GE1.3/GE1.4/FE1.1/FE1.2 （默认GE1.1）
	private int loopAvoid;//环路避免功能开关：0/1 = 关/开
	
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

	public String getTmcFDIBIT0() {
		return tmcFDIBIT0;
	}

	public void setTmcFDIBIT0(String tmcFDIBIT0) {
		this.tmcFDIBIT0 = tmcFDIBIT0;
	}

	public String getTmcFDIB1IT3() {
		return tmcFDIB1IT3;
	}

	public void setTmcFDIB1IT3(String tmcFDIB1IT3) {
		this.tmcFDIB1IT3 = tmcFDIB1IT3;
	}

	public String getTmcFDIB4IT7() {
		return tmcFDIB4IT7;
	}

	public void setTmcFDIB4IT7(String tmcFDIB4IT7) {
		this.tmcFDIB4IT7 = tmcFDIB4IT7;
	}

	public int getApsRecoverTime() {
		return apsRecoverTime;
	}

	public void setApsRecoverTime(int apsRecoverTime) {
		this.apsRecoverTime = apsRecoverTime;
	}

	public int getAddressStudyModel() {
		return addressStudyModel;
	}

	public void setAddressStudyModel(int addressStudyModel) {
		this.addressStudyModel = addressStudyModel;
	}

	public int getAddressAgeSwitch() {
		return addressAgeSwitch;
	}

	public void setAddressAgeSwitch(int addressAgeSwitch) {
		this.addressAgeSwitch = addressAgeSwitch;
	}

	public int getMACAddressAgeDate() {
		return MACAddressAgeDate;
	}

	public void setMACAddressAgeDate(int mACAddressAgeDate) {
		MACAddressAgeDate = mACAddressAgeDate;
	}

	public int getThrowWrapDateGap() {
		return throwWrapDateGap;
	}

	public void setThrowWrapDateGap(int throwWrapDateGap) {
		this.throwWrapDateGap = throwWrapDateGap;
	}

	public String getFDIBIT0() {
		return FDIBIT0;
	}

	public void setFDIBIT0(String fDIBIT0) {
		FDIBIT0 = fDIBIT0;
	}

	public String getFDIB1IT3() {
		return FDIB1IT3;
	}

	public void setFDIB1IT3(String fDIB1IT3) {
		FDIB1IT3 = fDIB1IT3;
	}

	public String getFDIB4IT7() {
		return FDIB4IT7;
	}

	public void setFDIB4IT7(String fDIB4IT7) {
		FDIB4IT7 = fDIB4IT7;
	}


	public String getSingleMACAddress() {
		return singleMACAddress;
	}

	public void setSingleMACAddress(String singleMACAddress) {
		this.singleMACAddress = singleMACAddress;
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

	public int getMPLSTPControl() {
		return MPLSTPControl;
	}

	public void setMPLSTPControl(int mPLSTPControl) {
		MPLSTPControl = mPLSTPControl;
	}

	public int getChannelType() {
		return ChannelType;
	}

	public void setChannelType(int channelType) {
		ChannelType = channelType;
	}

	public int getVlanMAC() {
		return vlanMAC;
	}

	public void setVlanMAC(int vlanMAC) {
		this.vlanMAC = vlanMAC;
	}

	public int getVlanValue() {
		return vlanValue;
	}

	public void setVlanValue(int vlanValue) {
		this.vlanValue = vlanValue;
	}

	public int getMacNumber() {
		return macNumber;
	}

	public void setMacNumber(int macNumber) {
		this.macNumber = macNumber;
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
