package com.nms.rmi.ui.util;

import java.io.File;
import java.rmi.registry.Registry;

import com.champor.license.Features;

/**
 * service端的常量
 * 
 * @author Administrator
 * 
 */
public class ServerConstant {

	public static String localhostIp = "127.0.0.1"; // 服务器IP地址，默认本机
	public static Registry registry = null; // 服务端rmi接口
	public static final String LICENSEPATH = System.getProperty("user.dir") + "/licenses/"; // license存放目录
	public static final String LICENSEFILENAME = "license.xml"; // license文件名
	public static final String LICENSEDLLNAME = "nmdb.dll"; // license需要的dll文件名
	public static Features features = null; // 许可对象
	// 初始化数据库时，各文件名
	public static final String INIT_ALL = "all.sql";
	public static final String INIT_SITE = "site.sql";
	public static final String INIT_BUSINESS = "business.sql";
	public static final String INIT_ALARM = "alarm.sql";
	public static final String INIT_PERFORMANCE = "performance.sql";
	public static final String INIT_SAFETY = "safety.sql";
	public static final String INIT_LOG = "log.sql";

	public static final String INIT_PATH = System.getProperty("user.dir") + "/config/truncate/"; // 初始化数据库文件存放目录

	// 备份数据库时，选择的key类型11
	public static final String BACKUPS_ALL = "all";
	public static final String BACKUPS_SITE = "site";
	public static final String BACKUPS_USER = "user";

	public static final String BACKUPS_FILE = System.getProperty("user.dir") + "/config/backups.xml"; // 备份数据库时读取配置文件的文件全路径

	public static final String FILE_LABEL = "PTN Data Transfer";
	public static final String FILE_TYPE = "Target Server Type    : PTN";
	public static final String FILE_VERSION = "Target Server Version : 90624599";
	public static final String FILE_TABLENUMBER = "Target Table Number : ";
	public static final String FILE_DATE = "Target Date : ";
	public static final String AUTODATABACK_FILE = System.getProperty("user.dir")+File.separator+"databaseBack";//数据库自动转储的目录地址
	public static final String AUTODATABACKPM_FILE = System.getProperty("user.dir")+File.separator+"databaseBackPM";//性能自动转储的目录地址
	public static final String AUTODATABACKALARM_FILE = System.getProperty("user.dir")+File.separator+"databaseBackAlarm";//告警自动转储的目录地址
	public static final String AUTODATABACKOPERATION_FILE = System.getProperty("user.dir")+File.separator+"databaseBackoperationLog";//操作日志转储的目录地址
	public static final String AUTODATABACKLOGING_FILE = System.getProperty("user.dir")+File.separator+"databaseBackloginLog";//登录日志转储的目录地址
	public static final long CYCLETIME = 12;//数据库自动转储功能的周期
	public static final int AUTODATABACKCOUNT = 5000;//数据库告警和性能自动转储功能的默认总条目数5000
	public static final String HELPMANUALADDRESS =System.getProperty("user.dir")+File.separator+"/config/INM200网管手册.chm";//数据库自动转储的目录地址
}
