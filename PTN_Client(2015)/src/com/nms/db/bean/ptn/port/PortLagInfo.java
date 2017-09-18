package com.nms.db.bean.ptn.port;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class PortLagInfo extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8014392513619576531L;
	private int id;
	private int siteId;
	private String lagNameLog;//lag名称，log日志用到
	private int lagID ;// LAG ID
	private int lagMode = 0;// 聚合模式0/(1)/2/3=关/(基于源MAC)/基于目的MAC/基于源和目的MAC
	private int portEnable = 0;// 端口使能(0)/1=(关)/开(wh:非负载分担返回模式)
	private int flowControl = 0;// 802.3流控(0)/1=(不使能)/使能(wh:主聚合成员端口号)
	private String mainLagPortLog;//主聚合成员端口号，log日志用到
	private int floodBate = 0;// 洪泛包抑制 (0)/1=(关)/开 (wh:从聚合成员端口号)
	private String standLagPortLog;//从聚合成员端口号，log日志用到
	private int mtu = 1518;		// MTU(wh:等待恢复时间)
	private int broadcastBate = 0;// 广播包抑制 (0)/1=(关)/开 (wh:聚合工作组模式)
	private int vlanRelating = 0;// VLAN关联设置 (0)/1=(不关联)/关联
	private int relatingSet = 0;// 802.1P关联设置 (0)/1=(不关联)/关联 
	private int fountainMAC = 0;// 源MAC地址关联设置 (0)/1=(不关联)/关联 
	private int aimMAC = 0;// 目的MAC地址关联设置 (0)/1=(不关联)/关联 
	private int fountainIP = 0;// 源IP地址关联设置 (0)/1=(不关联)/关联 
	private int aimIP = 0;// 目的IP地址关联设置 (0)/1=(不关联)/关联 
	private int pwSet = 0;// PW关联设置 (0)/1=(不关联)/关联 
	private int ipDSCPSet = 0;// IP DSCP关联设置 (0)/1=(不关联)/关联 
	private List<CommonBean> priorityList;//协议开关与优先级,log日志用到
	private String portLagMember = "0,0,0,0";//聚合成员端口,只有四个 
	private String portLagMember1Log;//端口号1，log日志用到
	private String portLagMember2Log;//端口号2，log日志用到
	private String portLagMember3Log;//端口号3，log日志用到
	private String portLagMember4Log;//端口号4，log日志用到
	private String exportQueue = "0-1,0-1,0-1,0-1,0-1,0-1,0-1,0-1";// (出口队列调度策略)	(wh:协议开关与优先级)
	private int portLimitation = 1000000;// 端口出口限速 		（晨晓：出口带宽限制）
	private int broadcastFlux = 1000000;// 广播包流量 		（晨晓：广播抑制报文）
	private int groupBroadcastBate =0;// 组播包抑制 
	private int groupBroadcastFlux = 1000000;// 组播包流量 		（晨晓：组播抑制报文）
	private int floodFlux = 1000000;// 洪泛包流量 			（晨晓：单播抑制报文）
	private int isUsed=0;//是否被用
	
	private int maxFrameLength = 0;//最大帧长		（晨晓：最大帧长）
	private int vlanIC = 0;//缺省VLAN IC				（晨晓：缺省VLAN ID）
	private int vlanPriority = 0;//VLAN优先级		（晨晓：缺省VLAN优先级）
	private int msgLoop = 0;//是否允许报文环回		（晨晓：是否允许报文环回）
	private int resumeModel = 0;//1:1恢复模式		（晨晓：1:1恢复模式）
	private int inportLimitation = 0;//入口带宽限制	（晨晓：入口带宽限制）
	private int meangerStatus = 0;//管理状态			（晨晓：使能状态）
	private String sMac = "";//本地MAC  				（晨晓：本地mac）
	private int lblNetWrap = 0;//以太网封装			（晨晓：以太网封装）
	private int lblVlanTpId = 0; //运营商VLAN_TPID		（晨晓：运营商VLAN_TPID）
	private int lblouterTpid = 0; //OUTER VLAN的TPID		（晨晓：OUTER VLAN的TPID	）
	private int lblNetVlanMode = 0; //以太网VLAN模式		（晨晓：以太网VLAN模式）
	private int statusActive = 0;
	private String admin = ""; //CX使能  				
	private String role = ""; //cx角色				（晨晓：角色）
	private int PortId;//端口号 
	private int type;//类型
	private int ExpMappingLLspInput;  //EXP映射llsp 输入
	private int ExpMappingLLspOutput; //EXP映射llsp 输出
	private int ExpMappingELspInput;  //EXP映射Elsp 输入
	private int ExpMappingELspOutput;  //EXP映射Elsp 输出
	private int mappingVlanpriToColor;  //vlanpriToColor映射
	private int mappingPriorityToVlanpri;//PriorityToVlanpri映射
	private int lagStatus;//激活状态    1=激活，2=未激活
	
	public String getMainLagPortLog() {
		return mainLagPortLog;
	}
	public void setMainLagPortLog(String mainLagPortLog) {
		this.mainLagPortLog = mainLagPortLog;
	}
	public String getStandLagPortLog() {
		return standLagPortLog;
	}
	public void setStandLagPortLog(String standLagPortLog) {
		this.standLagPortLog = standLagPortLog;
	}
	public List<CommonBean> getPriorityList() {
		return priorityList;
	}
	public void setPriorityList(List<CommonBean> priorityList) {
		this.priorityList = priorityList;
	}
	public String getPortLagMember1Log() {
		return portLagMember1Log;
	}
	public void setPortLagMember1Log(String portLagMember1Log) {
		this.portLagMember1Log = portLagMember1Log;
	}
	public String getPortLagMember2Log() {
		return portLagMember2Log;
	}
	public void setPortLagMember2Log(String portLagMember2Log) {
		this.portLagMember2Log = portLagMember2Log;
	}
	public String getPortLagMember3Log() {
		return portLagMember3Log;
	}
	public void setPortLagMember3Log(String portLagMember3Log) {
		this.portLagMember3Log = portLagMember3Log;
	}
	public String getPortLagMember4Log() {
		return portLagMember4Log;
	}
	public void setPortLagMember4Log(String portLagMember4Log) {
		this.portLagMember4Log = portLagMember4Log;
	}
	public String getLagNameLog() {
		return lagNameLog;
	}
	public void setLagNameLog(String lagNameLog) {
		this.lagNameLog = lagNameLog;
	}
	public int getMappingVlanpriToColor() {
		return mappingVlanpriToColor;
	}
	public void setMappingVlanpriToColor(int mappingVlanpriToColor) {
		this.mappingVlanpriToColor = mappingVlanpriToColor;
	}
	public int getMappingPriorityToVlanpri() {
		return mappingPriorityToVlanpri;
	}
	public void setMappingPriorityToVlanpri(int mappingPriorityToVlanpri) {
		this.mappingPriorityToVlanpri = mappingPriorityToVlanpri;
	}
	private List<PortInst> portList = new ArrayList<PortInst>();
	private List<QosQueue> qosQueueList=new ArrayList<QosQueue>();
	
	public int getPortId() {
		return PortId;
	}
	public void setPortId(int portId) {
		PortId = portId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<PortInst> getPortList() {
		return portList;
	}
	public void setPortList(List<PortInst> portList) {
		this.portList = portList;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public int getStatusActive() {
		return statusActive;
	}
	public void setStatusActive(int statusActive) {
		this.statusActive = statusActive;
	}
	public int getResumeModel() {
		return resumeModel;
	}
	public void setResumeModel(int resumeModel) {
		this.resumeModel = resumeModel;
	}
	public int getLblNetWrap() {
		return lblNetWrap;
	}
	public void setLblNetWrap(int lblNetWrap) {
		this.lblNetWrap = lblNetWrap;
	}
	public int getLblVlanTpId() {
		return lblVlanTpId;
	}
	public void setLblVlanTpId(int lblVlanTpId) {
		this.lblVlanTpId = lblVlanTpId;
	}
	public int getLblouterTpid() {
		return lblouterTpid;
	}
	public void setLblouterTpid(int lblouterTpid) {
		this.lblouterTpid = lblouterTpid;
	}
	public int getLblNetVlanMode() {
		return lblNetVlanMode;
	}
	public void setLblNetVlanMode(int lblNetVlanMode) {
		this.lblNetVlanMode = lblNetVlanMode;
	}
	public String getsMac() {
		return sMac;
	}
	public void setsMac(String sMac) {
		this.sMac = sMac;
	}
	public int getMaxFrameLength() {
		return maxFrameLength;
	}
	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}
	public int getVlanIC() {
		return vlanIC;
	}
	public void setVlanIC(int vlanIC) {
		this.vlanIC = vlanIC;
	}
	public int getVlanPriority() {
		return vlanPriority;
	}
	public void setVlanPriority(int vlanPriority) {
		this.vlanPriority = vlanPriority;
	}
	
	public int getMsgLoop() {
		return msgLoop;
	}
	public void setMsgLoop(int msgLoop) {
		this.msgLoop = msgLoop;
	}
	public int getInportLimitation() {
		return inportLimitation;
	}
	public void setInportLimitation(int inportLimitation) {
		this.inportLimitation = inportLimitation;
	}
	public int getMeangerStatus() {
		return meangerStatus;
	}
	public void setMeangerStatus(int meangerStatus) {
		this.meangerStatus = meangerStatus;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLagID() {
		return lagID;
	}
	public void setLagID(int lagID) {
		this.lagID = lagID;
	}
	public int getLagMode() {
		return lagMode;
	}
	public void setLagMode(int lagMode) {
		this.lagMode = lagMode;
	}
	public String getPortLagMember() {
		return portLagMember;
	}
	public void setPortLagMember(String portLagMember) {
		this.portLagMember = portLagMember;
	}
	public int getPortEnable() {
		return portEnable;
	}
	public void setPortEnable(int portEnable) {
		this.portEnable = portEnable;
	}
	public int getFlowControl() {
		return flowControl;
	}
	public void setFlowControl(int flowControl) {
		this.flowControl = flowControl;
	}
	public int getMtu() {
		return mtu;
	}
	public void setMtu(int mtu) {
		this.mtu = mtu;
	}
	public int getVlanRelating() {
		return vlanRelating;
	}
	public void setVlanRelating(int vlanRelating) {
		this.vlanRelating = vlanRelating;
	}
	public int getRelatingSet() {
		return relatingSet;
	}
	public void setRelatingSet(int relatingSet) {
		this.relatingSet = relatingSet;
	}
	public int getFountainMAC() {
		return fountainMAC;
	}
	public void setFountainMAC(int fountainMAC) {
		this.fountainMAC = fountainMAC;
	}
	public int getAimMAC() {
		return aimMAC;
	}
	public void setAimMAC(int aimMAC) {
		this.aimMAC = aimMAC;
	}
	public int getFountainIP() {
		return fountainIP;
	}
	public void setFountainIP(int fountainIP) {
		this.fountainIP = fountainIP;
	}
	public int getAimIP() {
		return aimIP;
	}
	public void setAimIP(int aimIP) {
		this.aimIP = aimIP;
	}
	public int getPwSet() {
		return pwSet;
	}
	public void setPwSet(int pwSet) {
		this.pwSet = pwSet;
	}
	public int getIpDSCPSet() {
		return ipDSCPSet;
	}
	public void setIpDSCPSet(int ipDSCPSet) {
		this.ipDSCPSet = ipDSCPSet;
	}
	public String getExportQueue() {
		return exportQueue;
	}
	public void setExportQueue(String exportQueue) {
		this.exportQueue = exportQueue;
	}
	public int getPortLimitation() {
		return portLimitation;
	}
	public void setPortLimitation(int portLimitation) {
		this.portLimitation = portLimitation;
	}
	public int getBroadcastBate() {
		return broadcastBate;
	}
	public void setBroadcastBate(int broadcastBate) {
		this.broadcastBate = broadcastBate;
	}
	public int getBroadcastFlux() {
		return broadcastFlux;
	}
	public void setBroadcastFlux(int broadcastFlux) {
		this.broadcastFlux = broadcastFlux;
	}
	public int getGroupBroadcastBate() {
		return groupBroadcastBate;
	}
	public void setGroupBroadcastBate(int groupBroadcastBate) {
		this.groupBroadcastBate = groupBroadcastBate;
	}
	public int getGroupBroadcastFlux() {
		return groupBroadcastFlux;
	}
	public void setGroupBroadcastFlux(int groupBroadcastFlux) {
		this.groupBroadcastFlux = groupBroadcastFlux;
	}
	public int getFloodBate() {
		return floodBate;
	}
	public void setFloodBate(int floodBate) {
		this.floodBate = floodBate;
	}
	public int getFloodFlux() {
		return floodFlux;
	}
	public void setFloodFlux(int floodFlux) {
		this.floodFlux = floodFlux;
	}
	
	public List<QosQueue> getQosQueueList() {
		return qosQueueList;
	}
	public void setQosQueueList(List<QosQueue> qosQueueList) {
		this.qosQueueList = qosQueueList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		PortService_MB portService = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			String model = "";
			if(this.lagMode ==1){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_MACADDRESS);
			}else if(this.lagMode ==2){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_TARGET_MACADDRESS);
			}else if(this.lagMode ==3){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_TARGET_MACADDRESS);
			}else if(this.lagMode ==4){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_IP);
			}else if(this.lagMode ==5){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_TARGET_IP);
			}else if(this.lagMode ==6){
				model = ResourceUtil.srcStr(StringKeysLbl.LBL_ACCORDING_SOURCE_TARGET_IP);
			}else{
				model = ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO);
			}
			if(siteService.getManufacturer(getSiteId()) == EManufacturer.WUHAN.getValue()){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				getClientProperties().put("id", getId());
				getClientProperties().put("name", "lag/"+this.getLagID());
				getClientProperties().put("lagMode",model);
				getClientProperties().put("workModel",UiUtil.getCodeById(this.getBroadcastBate()).getCodeName());
				getClientProperties().put("fuzaiModel",UiUtil.getCodeByValue("BACKTYPE", this.getPortEnable()+"").getCodeName());
				getClientProperties().put("mainPort", portService.getPortname(this.getFlowControl()));
				getClientProperties().put("standPort", portService.getPortname(this.getFloodBate()));
				getClientProperties().put("waitTime", this.getMtu());
				
			}else{
				//晨晓
				getClientProperties().put("PortName", "lag/"+this.getLagID());
//				getClientProperties().put("Admin", UiUtil.getCodeById(this.getMeangerStatus()).getCodeName());
				getClientProperties().put("Oper", "");
				getClientProperties().put("Role", this.getRole());
				getClientProperties().put("Mac", this.getsMac());
				getClientProperties().put("framelen", this.getMaxFrameLength());
				getClientProperties().put("psc", UiUtil.getCodeById(this.getLagMode()).getCodeName());
				getClientProperties().put("iused", this.getInportLimitation());
				getClientProperties().put("oused", this.getPortLimitation());
				getClientProperties().put("VLANID", this.getVlanIC());
				getClientProperties().put("VLANLevel", this.getVlanPriority());
				getClientProperties().put("recover", this.getResumeModel() == 0 ? ResourceUtil.srcStr(StringKeysObj.OBJ_NO) : ResourceUtil.srcStr(StringKeysObj.OBJ_YES));
				getClientProperties().put("permitpktloop", this.getMsgLoop() == 0 ? ResourceUtil.srcStr(StringKeysObj.OBJ_NO) : ResourceUtil.srcStr(StringKeysObj.OBJ_YES));
				getClientProperties().put("activeStatus", getLagStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(siteService);
		}
		
	}
	public int getExpMappingLLspInput() {
		return ExpMappingLLspInput;
	}
	public void setExpMappingLLspInput(int expMappingLLspInput) {
		ExpMappingLLspInput = expMappingLLspInput;
	}
	public int getExpMappingLLspOutput() {
		return ExpMappingLLspOutput;
	}
	public void setExpMappingLLspOutput(int expMappingLLspOutput) {
		ExpMappingLLspOutput = expMappingLLspOutput;
	}
	public int getExpMappingELspInput() {
		return ExpMappingELspInput;
	}
	public void setExpMappingELspInput(int expMappingELspInput) {
		ExpMappingELspInput = expMappingELspInput;
	}
	public int getExpMappingELspOutput() {
		return ExpMappingELspOutput;
	}
	public void setExpMappingELspOutput(int expMappingELspOutput) {
		ExpMappingELspOutput = expMappingELspOutput;
	}
	public int getLagStatus() {
		return lagStatus;
	}
	public void setLagStatus(int lagStatus) {
		this.lagStatus = lagStatus;
	}
	
}
