package com.nms.service.impl.util;

public class TypeAndActionUtil {

	public static final String TYPE_NNI = "nni";
	public static final String TYPE_UNI = "uni";
	public static final String TYPE_TUNNEL = "tunnel";
	public static final String TYPE_PW = "pw";
	public static final String TYPE_PWPDH = "pwpdh";
	public static final String TYPE_PWETH = "pweth";
	public static final String TYPE_PWSDH = "pwsdh";
	public static final String TYPE_ELINE = "eline";
	public static final String TYPE_ETREE = "etree";
	public static final String TYPE_ELAN = "elan";
	public static final String TYPE_AC = "ac";
	public static final String TYPE_SITE = "site";
	public static final String TYPE_XC = "xc";
	public static final String TYPE_PORT = "port";
	public static final String TYPE_SEGMENT = "segment";
	public static final String TYPE_ALARM = "alarm";
	public static final String TYPE_PERFORMANCE = "performance";
	public static final String TYPE_PORTSTMTIMESLOT = "portstmtimeslot";
	public static final String TYPE_PORTSTM = "portstm";
	public static final String TYPE_CARD = "card";
	public static final String TYPE_E1 = "E1";
	public static final String TYPE_CESPDH = "cespdh";
	public static final String TYPE_CESSDH = "cessdh";
	public static final String TYPE_PORTQOS = "portqos";
	public static final String TYPE_OSPF = "ospf";// TXC
	public static final String TYPE_OSPFAREA = "area";//TXC
	public static final String TYPE_REDISTRIBUTE = "redistribute";//TXC
	public static final String TYPE_QOS = "qos";// qos
	public static final String TYPE_MCN = "mcn";// mcn
	public static final String TYPE_CCN = "ccn";// ccn
	public static final String TYPE_OSPFINTERFACE = "ospfinterface"; //OSPF接口
	public static final String TYPE_OSPFINTERFACECCN = "ospfinterfaceccn"; //OSPFCCN接口
	public static final String TYPE_OSPFINTERFACEMCN = "ospfinterfacemcn"; //OSPFMCN接口
	public static final String TYPE_PORTLAG = "portlag"; //lag
	public static final String TYPE_LSP = "lsp"; //lsp
	public static final String TYPE_ACQOS = "acqos"; //acqos
	public static final String TYPE_OAM = "oam"; //oam
	public static final String TYPE_MIPOAM = "mipoam"; //mipoam
	public static final String TYPE_ROTATE="rotate";	//倒换
	public static final String TYPE_ACN="acn";	//acn
	public static final String TYPE_LOOPPROTECT="loopProtect";	//loopProtect
	public static final String TYPE_TIMEMANAGER="timeManager";	//时间管理（网元PTP配置）
	public static final String TYPE_FREQUENCY_INFO="frequency";	//频率管理（网元时钟属性）
	public static final String TYPE_EXTERNALCLOCKINTERF="clockInterf";	//外时钟接口
	public static final String TYPE_CLOCKSOURCE_CONFIG="ClockSourceConfig";	//时钟源配置
	public static final String TYPE_PORTCONFIG="portConfig";	//端口配置
	public static final String TYPE_CLOCKINTERFACE="clockInterface";	//线路时钟接口
	public static final String TYPE_MSPPROTECT="mspProtect";	//msp保护
	public static final String TYPE_DUALPROTECT="type_dualprotect";	//双规保护

	public static final String ACTION_INSERT = "insert";
	public static final String ACTION_UPDATE = "update";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_SELECT = "select";
	public static final String ACTION_SAVEANDUPDATE = "saveOrUpdate";
	public static final String ACTION_SYNCHRO = "synchro";
	public static final String ACTION_LOGIN = "login";
	public static final String ACTION_LOGOUT = "logout";
	public static final String ACTION_REGISTER = "regsiter";
	public static final String ACTION_ROTATE = "rotate";
	

	public static final String ACTION_ROOT = "root";
	public static final String ACTION_BRANCH = "branch";
	public static final String ACTION_CLEARSITE = "clearSite"; //清除网元
	public static final String ACTION_SEARCHSEGMENT = "searchSegment"; //段搜索
	/**
	 * oam以太网链路
	 */
	public static final String TYPE_ETHLINKOAM="type_ethlinkoam";	//oam以太网链路
	
	/**
	 * EXP映射 LLSP输入
	 */
	public static final String TYPE_EXPMAPPINGLLSPINPUT="type_expmappingllspinput";	//EXP映射 LLSP 输入
	
	/**
	 * EXP映射 LLSP输出
	 */
	public static final String TYPE_EXPMAPPINGLLSPOUTPUT="type_expmappingllspoutput";	//EXP映射 LLSP	输出
	
	/**
	 * EXP映射 ELSP输入
	 */
	public static final String TYPE_EXPMAPPINGELSPINPUT="type_expmappingelspinput";	//EXP映射 ELSP	输入
	
	/**
	 * EXP映射 ELSP输出
	 */
	public static final String TYPE_EXPMAPPINGELSPOUTPUT="type_expmappingelspoutput";	//EXP映射 ELSP	输出
	
	/**
	 * VLANPRI_COLOR映射 
	 */
	public static final String TYPE_MAPPINGVLANPRITOCOLOR="type_mappingvlanpritocolor";
	
	/**
	 *	COS_VLANPRI映射 
	 */
	public static final String TYPE_MAPPINGCOSTOVLANPRI="type_mappingcostovlanpri";
	/**
	 *slotTemp 温度管理
	 */
	public static final String TYPE_SLOTTEMP="type_slotTemp";
	/**
	 * pw的 VLAN配置
	 */
	public static final String TYPE_PW_VLAN="type_pwvlan";
	
	
}
