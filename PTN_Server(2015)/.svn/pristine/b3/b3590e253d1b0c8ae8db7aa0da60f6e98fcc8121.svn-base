package com.nms.drivechenxiao.service.impl.bean;

import java.util.Date;

import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.service.bean.OperationObject;

public class CXDriveUtilObject {

	public static String OPER_CREATE = "CREATE";
	public static String OPER_UPDATE = "UPDATE";
	public static String OPER_DELETE = "DELETE";
	public static String OPER_SELETE = "SELETE";
	public static String OPER_SAVE = "SAVE";
	public static String OPER_LOGIN = "LOGIN";
	public static String OPER_LOGOUT = "LOGOUT";
	public static String OPER_HOOKNOTIFY = "HOOKNOTIFY";

	public static String OBJ_ETHPORT = "ETHPORT";
	public static String OBJ_PDHPORT = "PDHPORT";
	public static String OBJ_SDHPORT = "SDHPORT";
	public static String OBJ_SEMENT = "SEMENT";
	public static String OBJ_TUNNEL = "TUNNEL";
	public static String OBJ_ETHPW = "ETHPW";
	public static String OBJ_PDHPW = "PDHPW";
	public static String OBJ_SDHPW = "SDHPW";
	public static String OBJ_XC = "XC";
	public static String OBJ_ETHAC = "ETHAC";
	public static String OBJ_SDHAC = "SDHAC";
	public static String OBJ_ELAN = "ELAN";
	public static String OBJ_ELINE = "ELINE";
	public static String OBJ_ETREE = "ETREE";
	public static String OBJ_CESPDH = "CESPDH";
	public static String OBJ_CESSDH = "CESSDH";
	public static String OBJ_CES = "CES";
	public static String OBJ_NE = "NE";
	public static String OBJ_ALARM = "ALARM";
	public static String OBJ_PERFORMANCE = "PERFORMANCE";
	public static String OBJ_SLOT = "SLOT";
	public static String OBJ_OSPF = "OSPF";
	public static String OBJ_OSPFAREA = "area";
	public static String OBJ_REDISTRIBUTE = "redistribute";
	public static String OBJ_MCN = "mcn";
	public static String OBJ_QOS = "qos";
	public static String OBJ_QOSAC = "acqos";
	public static String OBJ_CCN = "ccn";
	public static String OBJ_OSPFINTERFACE = "ospfinterface";
	public static String OBJ_PORTLAG = "protlag";
	public static String OBJ_LSP = "lsp";
	public static String OBJ_ETHPORTUNI = "ethportuni";
	public static String OBJ_ETHPORTNNI = "ethportnni";
	public static String OBJ_OAM = "oam";
	public static String OBJ_MIPOAM = "mipoam";
	public static String OBJ_APSCMD = "apscmd";
	public static String OBJ_ACN = "acn";
	public static String OBJ_RING = "ring";
	public static String OBJ_PTPSITEOBJECT = "PtpSite";
	public static String OBJ_CLOCK = "clock";
	public static String OBJ_CLOCKPortES = "clockportes";
	public static String OBJ_TOD = "tod";
	public static String OBJ_PTP = "ptp";
	public static String OBJ_PTPPORT = "ptpport";
	public static String OBJ_EXTCLK = "extclk";
	public static String OBJ_MSP = "msp";
	public static String OBJ_EFM = "ethoam";
	public static String OBJ_DUAL = "dual";
	public static String OBJ_CLRTOEXP = "clrtoexp";//Clrtoexp
	public static String OBJ_COSTOEXP = "costoexp";//Costoexp
	public static String OBJ_EXPTOCLR = "exptoclr";
	public static String OBJ_EXPTOCOS = "exptocos";
	public static String OBJ_COS2VPN = "cos2vlanpri";
	public static String OBJ_VLAN2CNG = "vlanpri2cng";
	public static String OBJ_STATICROUTE = "staticRoute";
	public static String OBJ_SLOTTEMP = "slotTemp";
	public static String OBJ_POWER="power";//光功率
	public static String OBJ_CARDPRO="cardPro";//板卡保护
	public static String OBJ_PROTECTlOG="protectLog";//保护倒换事件
	public static String OBJ_TMDLOOPBACK="loopBack";//TMD环回
	
	private String seqId = "";// seqId
	private String sessionId = "";// sessionId
	private String actionId = "";// 操作ID
	private Date sendDate = null;// 命令发送时间
	private Date responseDate = null;// 命令响应时间
	private int states = 0;// 状态 0：待发送 1：已发送 2：以获得响应信息
	private Object ptnDataObject = null;// 命令对应的对象
	private byte[] sendCommands = null;// 待发送命令
	private byte[] responseSendCommands = null;// 命令响应回来的命令
	private OperationObject operationObject = null;// 回调函数
	private CXNEObject cXNEObject = null;
	private String operType = "";
	private String objType = "";
	private int direction = 0;// 方向 0：网关发出的请求 1：代表PTN设备向网关发出的请求
	private long sendTimeL = 0l;
	
	
	public long getSendTimeL() {
		return sendTimeL;
	}

	public void setSendTimeL(long sendTime) {
		this.sendTimeL = sendTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public CXNEObject getCXNEObject() {
		return cXNEObject;
	}

	public void setCXNEObject(CXNEObject object) {
		cXNEObject = object;
	}

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}

	public Object getPtnDataObject() {
		return ptnDataObject;
	}

	public void setPtnDataObject(Object ptnDataObject) {
		this.ptnDataObject = ptnDataObject;
	}

	public byte[] getSendCommands() {
		return sendCommands;
	}

	public void setSendCommands(byte[] sendCommands) {
		this.sendCommands = sendCommands;
	}

	public byte[] getResponseSendCommands() {
		return responseSendCommands;
	}

	public void setResponseSendCommands(byte[] responseSendCommands) {
		this.responseSendCommands = responseSendCommands;
	}

	public OperationObject getOperationObject() {
		return operationObject;
	}

	public void setOperationObject(OperationObject operationObject) {
		this.operationObject = operationObject;
	}

}
