package com.nms.corba.ninterface.framework;

import java.io.File;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.util.FileTools;
import com.nms.corba.ninterface.util.PropertyUtil;

public class CorbaConfig {
	private static Logger LOG = Logger.getLogger(CorbaConfig.class.getName());

	private static CorbaConfig instance = new CorbaConfig();

	// corba.properties
	private static String corbaConfigFile = "config" + File.separator+ "corba.properties";

	private String strLocalIp = "127.0.0.1";

	private String strEmsSessionFactoryName = "DATAX/INM200";

	private String strEmsName = "DATAX/1";

	private int iBindmode = 2;

	private String strConputerName = "localhost";

	private boolean bFixCorbaPort = false;

	private String strCorbaServicePort = "8800";

	private int iNameServicePort = 8801;

	private String strNameserviceConfig = new String();

	private String strNotifyserviceConfig = new String();

	private String strOrbDebugLever = "0";

	private int iHeartBeatInterval = 30;

	private int iHeartEnablefailedTimes = 3;

	private int iPingInterval = 20;

	private int iPingEnableFailedTimes = 3;

	private String strIdlVersion = "V3.20";

	private String strEmsLocation = "ShenYang";

	private String strEmsUserlabelZH = new String();

	private String strEmsUserlabelEN = new String();

	private String strEmsNativeNameZH = new String();

	private String strEmsNativeNameEN = new String();

	private String strEmsVersion = new String();

	private String strEmsVendorName = new String();

	private String strEmsType = new String();

	private String strLanguage = "zh";

	private String manageablemaxnumforme = "";

	private String emsrole = "Main";

	private String emsmanagingdevices = "PTN";

	private String emscreator = "admin";

	private String emscreatetime = "";

	private String emshardware = "";

	private String emssoftware = "";

	private String emscontactinfo = "";

	private boolean isGetVirtualMe = false;

	private String dns_enable = "off";

	private CorbaConfig() {

	}

	public static CorbaConfig getInstanse() {
		// TODO Auto-generated method stub
		return instance;
	}

	public void init() {
		PropertyUtil propUtil = new PropertyUtil();
		String strTemp = propUtil.getProperty("corba.localip", "127.0.0.1",corbaConfigFile);
		if (null != strTemp) {
			this.strLocalIp = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.sessionfactoryname",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsSessionFactoryName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsname", corbaConfigFile);
		if (null != strTemp) {
			this.strEmsName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.bindnameorip",
				corbaConfigFile);
		if (null != strTemp) {
			int i = Integer.parseInt(strTemp);

			if ((0 == i) || (1 == i) || (2 == i)) {
				this.iBindmode = i;
			} else {
				LOG.error("[init] config bind mode is " + i
						+ ", it should be 0/1/2");
			}
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.computername",
				corbaConfigFile);
		if (null != strTemp) {
			this.strConputerName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.isfixcorbaport",
				corbaConfigFile);
		if (null != strTemp) {
			this.bFixCorbaPort = Boolean.parseBoolean(strTemp);
		}

		strTemp = null;
		strTemp = propUtil
				.getProperty("corba.serviceport", corbaConfigFile);
		if (null != strTemp) {
			this.strCorbaServicePort = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.nameserviceport",
				corbaConfigFile);
		if (null != strTemp) {
			String strIPorComputerName = "127.0.0.1";
			if (1 == this.iBindmode) {
				strIPorComputerName = this.strConputerName;
			} else {
				strIPorComputerName = this.strLocalIp;
			}
			this.iNameServicePort = Integer.parseInt(strTemp);
			this.strNameserviceConfig = ("corbaloc:iiop:" + strIPorComputerName
					+ ":" + strTemp + "/NameService");
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.notifyserviceport",
				corbaConfigFile);
		if (null != strTemp) {
			String strIPorComputerName = "127.0.0.1";
			if (1 == this.iBindmode) {
				strIPorComputerName = this.strConputerName;
			} else {
				strIPorComputerName = this.strLocalIp;
			}

			this.strNotifyserviceConfig = ("corbaloc:iiop:"
					+ strIPorComputerName + ":" + strTemp + "/DefaultEventChannelFactory");
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.idlversion", corbaConfigFile);
		if (null != strTemp) {
			this.strIdlVersion = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.orbdebuglevel",
				corbaConfigFile);
		if (null != strTemp) {
			this.strOrbDebugLever = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.heartbeatinterval",
				corbaConfigFile);
		if (null != strTemp) {
			int i = Integer.parseInt(strTemp);
			if (i >= 0) {
				this.iHeartBeatInterval = i;
			}

		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.heartenablefailedtimes",
				corbaConfigFile);
		if (null != strTemp) {
			int i = Integer.parseInt(strTemp);
			if (i >= 0) {
				this.iHeartEnablefailedTimes = i;
			}

		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.pinginterval",
				corbaConfigFile);
		if (null != strTemp) {
			int i = Integer.parseInt(strTemp);
			if (i >= 0) {
				this.iPingInterval = i;
			}

		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.pingenablefailedtimes",
				corbaConfigFile);
		if (null != strTemp) {
			int i = Integer.parseInt(strTemp);
			if (i >= 0) {
				this.iPingEnableFailedTimes = i;
			}

		}

		strTemp = null;
		strTemp = propUtil
				.getProperty("corba.emslocation", corbaConfigFile);
		if (null != strTemp) {
			this.strEmsLocation = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsuserlabelzh",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsUserlabelZH = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsuserlabelen",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsUserlabelEN = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsnativenamezh",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsNativeNameZH = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsnativenameen",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsNativeNameEN = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsversion", corbaConfigFile);
		if (null != strTemp) {
			this.strEmsVersion = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsvendorname",
				corbaConfigFile);
		if (null != strTemp) {
			this.strEmsVendorName = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emstype", corbaConfigFile);
		if (null != strTemp) {
			this.strEmsType = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsrole", corbaConfigFile);
		if (null != strTemp) {
			this.emsrole = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.manageablemaxnumforme",
				corbaConfigFile);
		if (null != strTemp) {
			this.manageablemaxnumforme = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emsmanagingdevices",
				corbaConfigFile);
		if (null != strTemp) {
			this.emsmanagingdevices = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emscreator", corbaConfigFile);
		if (null != strTemp) {
			this.emscreator = strTemp;
		}
		strTemp = null;
		strTemp = propUtil.getProperty("corba.emscreatetime",
				corbaConfigFile);
		if (null != strTemp) {
			this.emscreatetime = strTemp;
		}
		strTemp = null;
		strTemp = propUtil
				.getProperty("corba.emshardware", corbaConfigFile);
		if (null != strTemp) {
			this.emshardware = strTemp;
		}
		strTemp = null;
		strTemp = propUtil
				.getProperty("corba.emssoftware", corbaConfigFile);
		if (null != strTemp) {
			this.emssoftware = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.emscontactinfo",
				corbaConfigFile);
		if (null != strTemp) {
			this.emscontactinfo = strTemp;
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.getvirtualme",
				corbaConfigFile);
		if (null != strTemp) {
			this.isGetVirtualMe = Boolean.parseBoolean(strTemp);
		}

		strTemp = null;
		strTemp = propUtil.getProperty("ums.locale", corbaConfigFile);
		if (null != strTemp) {
			if (strTemp.equals("zh_CN")) {
				this.strLanguage = "zh";
			} else {
				this.strLanguage = "en";
			}
		}

		strTemp = null;
		strTemp = propUtil.getProperty("corba.is_dns", corbaConfigFile);
		if ((null != strTemp) && (Boolean.parseBoolean(strTemp))) {
			this.dns_enable = "on";
		}
	}

	public String getEmsSessionFactoryName() {
		return this.strEmsSessionFactoryName;
	}

	public String getCorbaEmsName() {
		return this.strEmsName;
	}

	public int getBindMode() {
		return this.iBindmode;
	}

	public String getOrbDebugLevel() {
		return this.strOrbDebugLever;
	}

	public int getPingInterval() {
		return this.iPingInterval;
	}

	public int getPingEnableFailTimes() {
		return this.iPingEnableFailedTimes;
	}

	public int getHeartBeatInterval() {
		return this.iHeartBeatInterval;
	}

	public void setHeartBeatInterval(int iInterval) {
		if (iInterval >= 0) {
			this.iHeartBeatInterval = iInterval;
		}
	}

	public int getHeartEnableFailedTimes() {
		return this.iHeartEnablefailedTimes;
	}

	public String getLanguage() {
		return this.strLanguage;
	}

	public String getHostname() {
		return this.strConputerName;
	}

	public String getComputerIp() {
		return this.strLocalIp;
	}

	public String getNameServiceConfig() {
		return this.strNameserviceConfig;
	}

	public String getIdlVersion() {
		return this.strIdlVersion;
	}

	public String getNotifyServiceConfig() {
		return this.strNotifyserviceConfig;
	}

	public boolean isFixCorbaServicePort() {
		return this.bFixCorbaPort;
	}

	public String getCorbaServicePort() {
		return this.strCorbaServicePort;
	}

	public int getCorbaNameServicePort() {
		return this.iNameServicePort;
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
			bl = FileTools.setProperty("corba.emsuserlabelzh",userlabel,
					corbaConfigFile);
		
		} else {
			strEmsUserlabelEN = userlabel;
			bl = FileTools.setProperty("corba.emsuserlabelen",userlabel,
					corbaConfigFile);
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

	public String getManageablemaxnumforme() {
		return manageablemaxnumforme;
	}

	public String getEmsrole() {
		return emsrole;
	}

	public String getEmsmanagingdevices() {
		return emsmanagingdevices;
	}

	public String getEmscreator() {
		return emscreator;
	}

	public String getEmscreatetime() {
		return emscreatetime;
	}

	public String getEmshardware() {
		return emshardware;
	}

	public String getEmssoftware() {
		return emssoftware;
	}

	public String getEmscontactinfo() {
		return emscontactinfo;
	}

	public boolean setEmscontactinfo(String emscontactinfo) {
		return FileTools.setProperty("corba.emscontactinfo",emscontactinfo,
				corbaConfigFile);
	}
	
	public boolean isGetVirtualMe() {
		return this.isGetVirtualMe;
	}

	public String getdns_enable() {
		return this.dns_enable;
	}

}
