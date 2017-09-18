package com.nms.ui.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JTabbedPane;

import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.bean.system.roleManage.RoleRelevance;
import com.nms.db.bean.system.user.UserInst;
import com.nms.drive.service.DriveService;
import com.nms.drive.service.impl.bean.DriveUtilObject;
import com.nms.drivechenxiao.service.CXDriveService;
import com.nms.model.util.ServiceFactory;
import com.nms.service.bean.AlarmObjectService;
import com.nms.service.bean.PerformanceObjectService;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.ui.manager.xmlbean.EquipmentType;

public class ConstantUtil {

	public static ServiceFactory serviceFactory = null;	//服务层工厂类
	public static UserInst user = null;		//登陆的用户
	public static Map<String, CodeGroup> codeGroupMap = null;
	public static final int INT_WIDTH_ONE = 100;
	public static final int INT_WIDTH_TWO = 200;
	public static final int INT_WIDTH_THREE = 300;
	public static int fieldId = 0;	//拓扑中选择的域主键
	public static int siteId = 0;	//拓扑中选择的网元主键
	public static String loginTime = null;	//用户登陆时间
	public static JTabbedPane jTabbedPane = null;	//主界面选项卡控件
	public static String portType = null;
	public static DriveService driveService = null;	//驱动层服务类
	public static CXDriveService cxDriveService = null;	//晨晓驱动层服务类
	public static AlarmObjectService alarmObjectService = null;	//驱动层告警服务类
	public static PerformanceObjectService PerformanceObjectService = null;//驱动层性能服务类
	public static final int QOS_CIR_MAX_10G = 10000000;
	public static final int QOS_CIR_MAX_1G = 1000000;
	public static final int QOS_CIR_MAX_100M = 100000;
	public static int QOS_PORT_MAX = 0;
	public static int QOS_SEGMENT_A = 0;
	public static int QOS_SEGMENT_Z = 0;
	public static final int LABEL_MINVALUE = 16;
	public static final int LABEL_MAXVALUE = 1048575;
	public static final int LABEL_MAXVALUE2 = 1048575;
	public static final int LABELWaitTime_MINVALUE = 0;
	public static final int LABELWaitTime_MAXVALUE = 12;
	public static final int LABELDelayTime_MINVALUE = 0;
	public static final int LABELDelayTime_MAXVALUE = 10000;
	public static final int LABELmdMLevel_MAXVALUE = 7;
	public static final int LABOAMETNVLAN_MAXVALUE = 4094;
	public static final int LABOAMETNMEPID_MAXVALUE = 8191;
	public static final int LABOAMETNVLAN_MINVALUE = 1;
	public static final int LABETHSERVICSE_MINVALUE = 2;
	public static final int LABOAMETHLBMTVL_MAXVALUE = 1495;
	public static final int LABOAMETHLBMTVL_MINVALUE = 41;
	public static final int LABOAMETHTSTTLVLENGTH_MINVALUE = 45;
	public static final int LABOAMETHTSTTLVLENGTH_MAXVALUE = 1499;
	public static final int LABACL_MAXVALUE = 65535;
	public static final int LABACL_DSCPMAXVALUE = 63;
	public static List<RoleRelevance> roleRelevanceList=null;	//登陆用户的所有权限
	public static final int RMI_PORT = 3309; // rmi端口号
	public static String serviceIp = "127.0.0.1"; // 服务器IP地址，默认本机
	public static ServiceBean serviceBean = null;	//初始化rmi时返回的bean 有最大连接数、过期时间、最大网元数等属性
	public static final String LOGOPATH="/com/nms/ui/images/logo.png";
	public static final String LOGOPATHHIDE="/com/nms/ui/images/logo-zhongxing.png";
	public static Map<String,Long> siteStausMap = new HashMap<String,Long>();
	public static int URGENCYCOLOR = -1564897;//紧急告警颜色:红色
	public static int MAJORCOLOR = -26368;//主要告警颜色:橙色
	public static int MINORCOLOR = -1447651;//次要告警颜色:黄色
	public static int PROMPTCOLOR = -7368720;//提示告警颜色:紫色
	public static int URGENCY_TIME;//紧急告警声音持续时间
	public static String URGENCYSOUNDPATH;//紧急告警声音路径
	public static int MAJOR_TIME;//主要告警声音持续时间
	public static String MAJORSOUNDPATH;//主要告警声音路径
	public static int MINOR_TIME;//次要告警声音持续时间
	public static String MINORSOUNDPATH;//次要告警声音路径
	public static int WARNING_TIME;//提示告警声音持续时间
	public static String WARNINGSOUNDPATH;//提示告警声音路径
	public static boolean hasStop = false;
	public static final int CBS_MAXVALUE = 10000;//cbs/pbs的最大值
	public static List<?> allip = null;//查询的ip的值
	public static boolean isCancleRun= false;//是否取消软件升级
	public static Map<String, Capability> capabilityMap = null;//性能解析对照表(只允许客户端使用)
	public static int dbThread = 0;//用来标记是否开启了数据库监控	
	public static int cpuThread = 0;//用来标记是否开启了CPU内存监控	
	public static Map<String,Thread> threadMap = new HashMap<String, Thread>();//用来标记是否开启了数据库监控+CPU内存
	public static boolean isCurrentPerformnace = true;//判断是查询当前性能true/历史性能false
	public static ConcurrentHashMap<String, DriveUtilObject> send_commmandListMap = new ConcurrentHashMap<String, DriveUtilObject>();// 已发送
	public static ConcurrentHashMap<String, DriveUtilObject> recive_commmandListMap = new  ConcurrentHashMap<String, DriveUtilObject>();//收到
	public static ConcurrentLinkedQueue<Map<String,byte[]>> monitorResponseLinkedQueue = new ConcurrentLinkedQueue<Map<String,byte[]>>();
	public static List<EquipmentType> equipmentTypeList = null;	//所有设备类型。客户端启动后加载到内存中。
	public static Map<String,Integer> mSiteMap = new HashMap<String,Integer>();//M网元信息
	public static Map<Integer,HashMap<Integer,Integer>> m_aSiteMap = new HashMap<Integer, HashMap<Integer,Integer>>();//m-a对应关系
}
