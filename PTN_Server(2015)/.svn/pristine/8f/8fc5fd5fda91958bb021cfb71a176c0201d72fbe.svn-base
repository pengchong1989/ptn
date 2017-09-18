package com.nms.model.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.nms.ui.manager.ExceptionManage;

public class CodeConfigItem
{
	private static CodeConfigItem configitem=null;
	private int codeconfig;
	private int macEnable;
	private int wuhan;
	private int language;
	private int IconImageShowOrHide;
	private int alarmPoll;
    
	private Properties localProperties;
	private int snmpStartOrClose;
	private String serviceTaskName;//服务器任务名称
	private int batchSoftwareNumber;//批量升级网元数量
	private int noCheck;//强制取消验证
	public static CodeConfigItem getInstance()
	{
		if(configitem == null)
		{
			configitem = new CodeConfigItem();
		}
		
		return configitem;
	}
	
	private CodeConfigItem()
	{
		String fileName = "config" + File.separator + "CodeConfig.properties";
		File localFile = new File(fileName);
		localProperties = new Properties();

		FileInputStream fileInput;
		try
		{
			fileInput = new FileInputStream(localFile);
			BufferedInputStream fileBuffer = new BufferedInputStream(fileInput);
			localProperties.load(fileBuffer);
		}
		catch (FileNotFoundException e)
		{
			ExceptionManage.dispose(e, this.getClass());
		}
		catch (IOException e)
		{
			ExceptionManage.dispose(e, this.getClass());
		}
		
		String str = localProperties.getProperty("codeconfig").toString();
		String mac = localProperties.getProperty("macEnable").toString();
		String wuhan = localProperties.getProperty("wuhan").toString();
		String language = localProperties.getProperty("language").toString();
		String IconImageShowOrHide = localProperties.getProperty("IconImageShowOrHide").toString();
		String snmp = localProperties.getProperty("snmpStartOrClose").toString();
		String alarmPoll = localProperties.getProperty("alarmPoll").toString();
		this.setCodeconfig(Integer.parseInt(str));
		this.setMacEnable(Integer.parseInt(mac));
		this.setWuhan(Integer.parseInt(wuhan));
		this.setLanguage(Integer.parseInt(language));
		this.setIconImageShowOrHide(Integer.parseInt(IconImageShowOrHide));
		this.setSnmpStartOrClose(Integer.parseInt(snmp));
		this.setAlarmPoll((Integer.parseInt(alarmPoll)));
		this.setServiceTaskName(localProperties.getProperty("serverTask").toString());
		this.setBatchSoftwareNumber(Integer.parseInt(localProperties.getProperty("batchSoftwareNumber").toString()));
		String noCheck = localProperties.getProperty("noCheck").toString();
		this.setNoCheck((Integer.parseInt(noCheck)));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new CodeConfigItem();

	}

	/**
	 * 根据key值返回value
	 * 
	 * @author kk
	 * @param key
	 *            properties中的key
	 * @return
	 */
	public String getValueByKey(String key)
	{
		String str = this.localProperties.getProperty(key).toString();
		return str;
	}

	public int getCodeconfig()
	{
		return codeconfig;
	}
	public void setCodeconfig(int codeconfig)
	{
		this.codeconfig = codeconfig;
	}
	public int getMacEnable()
	{
		return macEnable;
	}
	public void setMacEnable(int macEnable)
	{
		this.macEnable = macEnable;
	}
	
	public int getWuhan()
	{
		return wuhan;
	}

	public void setWuhan(int wuhan)
	{
		this.wuhan = wuhan;
	}
	
	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public int getIconImageShowOrHide() {
		return IconImageShowOrHide;
	}

	public void setIconImageShowOrHide(int iconImageShowOrHide) {
		IconImageShowOrHide = iconImageShowOrHide;
	}

	public int getSnmpStartOrClose() {
		return snmpStartOrClose;
	}

	public void setSnmpStartOrClose(int snmpStartOrClose) {
		this.snmpStartOrClose = snmpStartOrClose;
	}
	
	public int getAlarmPoll()
	{
		return alarmPoll;
	}

	public void setAlarmPoll(int alarmPoll)
	{
		this.alarmPoll = alarmPoll;
	}

	public String getServiceTaskName() {
		return serviceTaskName;
	}

	public void setServiceTaskName(String serviceTaskName) {
		this.serviceTaskName = serviceTaskName;
	}

	public int getBatchSoftwareNumber() {
		return batchSoftwareNumber;
	}

	public void setBatchSoftwareNumber(int batchSoftwareNumber) {
		this.batchSoftwareNumber = batchSoftwareNumber;
	}

	public int getNoCheck() {
		return noCheck;
	}

	public void setNoCheck(int noCheck) {
		this.noCheck = noCheck;
	}
	
}