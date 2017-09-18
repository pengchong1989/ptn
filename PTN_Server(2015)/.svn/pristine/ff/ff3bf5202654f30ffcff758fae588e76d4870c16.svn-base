package com.nms.snmp.ninteface.framework;

import java.io.File;

import org.apache.log4j.Logger;

import com.nms.snmp.ninteface.util.FileTools;
import com.nms.snmp.ninteface.util.PropertyUtil;


public class SnmpConfig {
	private static Logger LOG = Logger.getLogger(SnmpConfig.class.getName());
	// snmp.properties
	private static String snmpConfigFile = "config" + File.separator
			+ "snmp.properties";

	private String strLocalIp = "127.0.0.1/161";
	private String trapIpAndPort = "127.0.0.1/162";
	private String strEmsName;
	private int iHeartBeatInterval = 30;
	private String strEmsLocation;
	private String strEmsUserlabelZH;
	private String strEmsUserlabelEN;
	private String strEmsNativeNameZH;
	private String strEmsNativeNameEN;
	private String strEmsVersion;
	private String strEmsVendorName;
	private String strEmsType;
	private String emsaddinalInfo = "";
	private String strLanguage = "";
	private String emsRunningState = "";
	private static SnmpConfig snmpConfig = new SnmpConfig();
	private String emsMaxSiteCount;
	private String softwareVersion = "";
	private String hardwareVersion = "";
	/*ftp上传配置信息*/
	private String remoteIp = "";
	private String remotePort = "";
	private String userName = "";
	private String passWord = "";
	private String remoteFilePath = "";
	private String SerialNumber;//单板序列号
	private String installedPartNumber;//单板硬件版本
	private String installedVersion;//单板软件版本
	
	public static SnmpConfig getInstanse() {
		return snmpConfig;
	}

	public void init() {
		PropertyUtil propUtil = new PropertyUtil();
		String strTemp = propUtil.getProperty("snmp.agentIpAndPort", "127.0.0.1/161",snmpConfigFile);
		if (null != strTemp) {
			this.strLocalIp = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.trapIpAndPort", "127.0.0.1/162",snmpConfigFile);
		if (null != strTemp) {
			this.trapIpAndPort = strTemp;
		}
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsname", snmpConfigFile);
		if (null != strTemp) {
			this.strEmsName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.heartbeatinterval",snmpConfigFile);
		if (null != strTemp && !strTemp.equals("")) {
			int i = Integer.parseInt(strTemp);
			if (i >= 0) {
				this.iHeartBeatInterval = i;
			}

		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emslocation", snmpConfigFile);
		if (null != strTemp) {
			this.strEmsLocation = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsuserlabelzh",snmpConfigFile);
		if (null != strTemp) {
			this.strEmsUserlabelZH = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsuserlabelen",snmpConfigFile);
		if (null != strTemp) {
			this.strEmsUserlabelEN = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsnativenamezh",snmpConfigFile);
		if (null != strTemp) {
			this.strEmsNativeNameZH = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsnativenameen",snmpConfigFile);
		if (null != strTemp) {
			this.strEmsNativeNameEN = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsversion", snmpConfigFile);
		if (null != strTemp) {
			this.strEmsVersion = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsvendorname",snmpConfigFile);
		if (null != strTemp) {
			this.strEmsVendorName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emstype", snmpConfigFile);
		if (null != strTemp) {
			this.strEmsType = strTemp;
		}


		strTemp = null;
		strTemp = propUtil.getProperty("snmp.emsaddinalInfo",snmpConfigFile);
		if (null != strTemp) {
			this.emsaddinalInfo = strTemp;
		}
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.runningState",snmpConfigFile);
		if (null != strTemp) {
			this.emsRunningState = strTemp;
		}
		

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.locale", snmpConfigFile);
		if (null != strTemp) {
			if (strTemp.equals("zh_CN")) {
				this.strLanguage = "zh";
			} else {
				this.strLanguage  = "en";
			}
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.softwareversion", snmpConfigFile);
		if(null != strTemp){
			this.softwareVersion = strTemp;
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.hardwareversion", snmpConfigFile);
		if(null != strTemp){
			this.hardwareVersion = strTemp;
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.remoteIp", snmpConfigFile);
		if(null != strTemp){
			this.remoteIp = strTemp; 
		}

		strTemp = null;
		strTemp = propUtil.getProperty("snmp.remotePort", snmpConfigFile);
		if(null != strTemp){
			this.remotePort = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.userName", snmpConfigFile);
		if(null != strTemp){
			this.userName = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.passWord", snmpConfigFile);
		if(null != strTemp){
			this.passWord = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("snmp.remoteFilePath", snmpConfigFile);
		if(null != strTemp){
			this.remoteFilePath = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("maxNeCount", snmpConfigFile);
		if(null != strTemp){
			this.emsMaxSiteCount = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("SerialNumber", snmpConfigFile);
		if(null != strTemp){
			this.SerialNumber = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("installedPartNumber", snmpConfigFile);
		if(null != strTemp){
			this.installedPartNumber = strTemp; 
		}
		
		strTemp = null;
		strTemp = propUtil.getProperty("installedVersion", snmpConfigFile);
		if(null != strTemp){
			this.installedVersion = strTemp; 
		}
	}

	public String getSerialNumber()
	{
		return SerialNumber;
	}
	
	public String getsnmpEmsName() {
		return this.strEmsName;
	}

	public int getHeartBeatInterval() {
		return this.iHeartBeatInterval;
	}

	public void setHeartBeatInterval(int iInterval) {
		if (iInterval >= 0) {
			this.iHeartBeatInterval = iInterval;
		}
	}

	public String getComputerIp() {
		return this.strLocalIp;
	}


	public String getEmsLocation() {
		return this.strEmsLocation;
	}

	public String getEmsUserlabel() {
		if (this.strLanguage.equals("zh")) {
			return this.strEmsUserlabelZH;
		}

		return this.strEmsUserlabelEN;
	}

	public boolean setEmsUserlabel(String userlabel) {
		boolean bl = false;
		if (this.strLanguage.equals("zh")) {
			strEmsUserlabelZH = userlabel;
			bl = FileTools.setProperty("snmp.emsuserlabelzh",userlabel,
					snmpConfigFile);
		
		} else {
			strEmsUserlabelEN = userlabel;
			bl = FileTools.setProperty("snmp.emsuserlabelen",userlabel,
					snmpConfigFile);
		}
		return bl;
	}

	public String getEmsVersion() {
		return this.strEmsVersion;
	}

	public String getEmsVendorName() {
		return this.strEmsVendorName;
	}

	public String getEmsNativeName() {
		if (this.strLanguage.equals("zh")) {
			return this.strEmsNativeNameZH;
		}

		return this.strEmsNativeNameEN;
	}

	public String getEmsType() {
		return this.strEmsType;
	}

	public String getEmsAddinalInfo() {
		return emsaddinalInfo;
	}

	public boolean setEmsAddinalInfo(String emsaddinalInfo) {
		return FileTools.setProperty("snmp.emsaddinalInfo",emsaddinalInfo,
				snmpConfigFile);
	}

	public String getEmsRunningState() {
		return emsRunningState;
	}

	public void setEmsRunningState(String emsRunningState) {
		this.emsRunningState = emsRunningState;
	}

	public String getEmsMaxSiteCount() {
		return emsMaxSiteCount;
	}

	public void setEmsMaxSiteCount(String emsMaxSiteCount) {
		this.emsMaxSiteCount = emsMaxSiteCount;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRemoteFilePath() {
		return remoteFilePath;
	}

	public void setRemoteFilePath(String remoteFilePath) {
		this.remoteFilePath = remoteFilePath;
	}
	public String getInstalledPartNumber()
	{
		return installedPartNumber;
	}

	public String getInstalledVersion()
	{
		return installedVersion;
	}

}
