package com.nms.db.bean.equipment.port;

import java.util.List;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
/**
 * 端口
 * @author Administrator
 *
 */
public class PortInst extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222160112526325509L;
	private int portId;
	private int cardId;
	private int equipId;
	private int siteId;
	private String portName;
	private String portType;
	private String portStatus;
	private String bandwidth;
	private int manageStatus;
	private String jobStatus;
	private String imagePath;
	private int portx;
	private int porty;
	private int isOccupy;
	private int number;
	private int slotNumber;
	private PortAttr portAttr;
	private int isEnabled_code;
	private int parentId;
	private int lagId;
	private int lagNumber;
	private String linecoding;
	private int impedance;
	private List<PortInst> childPortList = null;
	private List<QosQueue> qosQueues = null;
	private OamInfo oamInfo = null;
	private PortLagInfo lagInfo = null;
	private String macAddress;
	private int isEnabled_QinQ = 0;
	private int ExpMappingLLspInput;  //EXP映射llsp 输入
	private int ExpMappingLLspOutput; //EXP映射llsp 输出
	private int ExpMappingELspInput;  //EXP映射Elsp 输入
	private int ExpMappingELspOutput;  //EXP映射Elsp 输出
	private int mappingVlanpriToColor;  //vlanpriToColor映射
	private int mappingPriorityToVlanpri;//PriorityToVlanpri映射
	private int isEnabledLaser ;//激光器关闭使能
	private String moduleType="";	//模块类型 (o)=电口 (e)=光口
	private String snmpName;//为了适应SNMP接口需要修改 端口名称
	private int isEnabledAlarmReversal ;//告警反转使能
	private int servicePort;//业务端口环回状态：0/1/2=不环回/内环回/外环回
	private int useCirBandwidth;//使用的
	private int cirCount;//总和
	private String priority;//pri优先级
	private int isPriority;//是否是pri设置
	private Port2LayerAttr port2LayerAttr = new Port2LayerAttr();//端口2层属性
	public Port2LayerAttr getPort2LayerAttr() {
		return port2LayerAttr;
	}
	public void setPort2LayerAttr(Port2LayerAttr port2LayerAttr) {
		this.port2LayerAttr = port2LayerAttr;
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
	public String toString(){
		StringBuffer s=new StringBuffer().append(" portId=").append(portId)
		.append(" ;cardId=").append(cardId).append(" ;equipId=").append(equipId)
		.append(" ;siteId=").append(siteId).append(" ;portName=").append(portName)
		.append(" ;portType=").append(portType).append(" ;portStatus=").append(portStatus)
		.append(" ;bandwidth=").append(bandwidth).append(" ;manageStatus=").append(manageStatus)
		.append(" ;jobStatus=").append(jobStatus).append(" ;imagePath=").append(imagePath)
		.append(" ;portx=").append(portx).append(" ;porty=").append(porty)
		.append(" ;isOccupy=").append(isOccupy).append(" ;number=").append(number)
		.append(" ;slotNumber=").append(slotNumber).append(" ;portAttr={").append(portAttr.toString()).append("}")
		.append(" ;isEnabled_code=").append(isEnabled_code).append(" ;parentId=").append(parentId)
		.append(" ;linecoding=").append(linecoding).append(" ;oamInfo=").append(oamInfo)
		.append(" ;lagInfo=").append(lagInfo).append(" ;macAddress=").append(macAddress)
		.append(" ;qosQueues = { ").append(this.toQosString()).append(" } ")
		;
		return s.toString();
	}
	private String toQosString(){
		if(qosQueues != null){
			StringBuffer sbqos = new StringBuffer();
			for(QosQueue q : qosQueues){
				sbqos.append(" ").append(q.getName()).append("=").append(q.toString());
			}
			return sbqos.toString();
		}else {return "";}
	}

	public OamInfo getOamInfo() {
		return oamInfo;
	}

	public void setOamInfo(OamInfo oamInfo) {
		this.oamInfo = oamInfo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getLinecoding() {
		return linecoding;
	}

	public void setLinecoding(String linecoding) {
		this.linecoding = linecoding;
	}

	public int getImpedance() {
		return impedance;
	}

	public void setImpedance(int impedance) {
		this.impedance = impedance;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public String getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(String portStatus) {
		this.portStatus = portStatus;
	}

	public String getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getManageStatus() {
		return manageStatus;
	}

	public void setManageStatus(int manageStatus) {
		this.manageStatus = manageStatus;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getPortx() {
		return portx;
	}

	public void setPortx(int portx) {
		this.portx = portx;
	}

	public int getPorty() {
		return porty;
	}

	public void setPorty(int porty) {
		this.porty = porty;
	}

	public int getIsOccupy() {
		return isOccupy;
	}

	public void setIsOccupy(int isOccupy) {
		this.isOccupy = isOccupy;
	}

	public int getIsEnabled_code() {
		return isEnabled_code;
	}

	public void setIsEnabled_code(int isEnabled_code) {
		this.isEnabled_code = isEnabled_code;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<PortInst> getChildPortList() {
		return childPortList;
	}

	public void setChildPortList(List<PortInst> childPortList) {
		this.childPortList = childPortList;
	}

	public int getLagId() {
		return lagId;
	}

	public void setLagId(int lagId) {
		this.lagId = lagId;
	}

	public int getLagNumber() {
		return lagNumber;
	}

	public void setLagNumber(int lagNumber) {
		this.lagNumber = lagNumber;
	}

	public List<QosQueue> getQosQueues() {
		return qosQueues;
	}

	public void setQosQueues(List<QosQueue> qosQueues) {
		this.qosQueues = qosQueues;
	}

	public PortLagInfo getLagInfo() {
		return lagInfo;
	}

	public void setLagInfo(PortLagInfo lagInfo) {
		this.lagInfo = lagInfo;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	public PortAttr getPortAttr() {
		return portAttr;
	}

	public void setPortAttr(PortAttr portAttr) {
		this.portAttr = portAttr;
	}
	
	public int getIsEnabled_QinQ() {
		return isEnabled_QinQ;
	}
	public void setIsEnabled_QinQ(int isEnabledQinQ) {
		isEnabled_QinQ = isEnabledQinQ;
	}

	public int getIsEnabledLaser() {
		return isEnabledLaser;
	}
	public void setIsEnabledLaser(int isEnabledLaser) {
		this.isEnabledLaser = isEnabledLaser;
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
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getSnmpName() {
		return snmpName;
	}
	public void setSnmpName(String snmpName) {
		this.snmpName = snmpName;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		this.getClientProperties().put("id", this.getPortId());
		this.getClientProperties().put("portName", this.getPortName());
		this.getClientProperties().put("portType", this.getPortType());
		this.getClientProperties().put("portNo", super.getJobStatus(this.getJobStatus()));
		this.getClientProperties().put("enable", this.getEnable());
		this.getClientProperties().put("isWithSegment",isOnline() == false? ResourceUtil.srcStr(StringKeysObj.OBJ_CONNECTION_NO) : ResourceUtil.srcStr(StringKeysObj.OBJ_CONNECTION_YES));
		this.getClientProperties().put("belongCard", getCardInst(this.getCardId()));
		this.getClientProperties().put("cirObject", getSiteName(this.getSiteId())+"/"+this.getPortName());
		this.getClientProperties().put("cirCount",this.getCirCount());
		
		if(this.isOccupy == 1 && this.getUseCirBandwidth() == 0){
			this.getClientProperties().put("surplusCirCount",this.getCirCount());
		}else{
			this.getClientProperties().put("surplusCirCount",this.getCirCount()-this.getUseCirBandwidth());
		}
	}

	private String getCardInst(int cardId) {
		CardService_MB cardService = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			CardInst cardInst = new CardInst();
			cardInst.setId(cardId);

			return cardService.select(cardInst).get(0).getCardName();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cardService);
		}

		return null;
	}
	private boolean isOnline(){
		CurAlarmService_MB curAlarmService = null;
		boolean fals = true;
		CurrentAlarmInfo currentAlarmInfo = null;
		List<CurrentAlarmInfo> currentAlarmInfos = null;
		SiteService_MB siteService=null;
		try {
			if(this.getIsEnabled_code() == 0){
				return false;
			}
			
			if(!this.getPortType().equals("NNI")){
				return false;
			}
			
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			
			//武汉设备验证告警
			if(siteService.getManufacturer(this.getSiteId()) == EManufacturer.WUHAN.getValue()){
				
				curAlarmService=(CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
				currentAlarmInfo = new CurrentAlarmInfo();
				currentAlarmInfo.setSiteId(getSiteId());
				currentAlarmInfo.setObjectId(getPortId());
				currentAlarmInfo.setObjectName(getPortName());
				currentAlarmInfo.setAlarmCode(11);
				currentAlarmInfo.setAlarmLevel(5);
				currentAlarmInfos = curAlarmService.select(currentAlarmInfo);
				if(currentAlarmInfos != null && currentAlarmInfos.size()>0){
					return false;
				}
			}else{
				//晨晓设备有直接的字段  为1时是有连接。
				fals=this.getPortAttr().getPortNniAttr().getStat()==1;
			}
			 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(curAlarmService);
		}
		return fals;
	}
	
	public int getServicePort() {
		return servicePort;
	}
	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}
	public int getIsEnabledAlarmReversal() {
		return isEnabledAlarmReversal;
	}
	public void setIsEnabledAlarmReversal(int isEnabledAlarmReversal) {
		this.isEnabledAlarmReversal = isEnabledAlarmReversal;
	}
	
	public int getUseCirBandwidth() {
		return useCirBandwidth;
	}
	public void setUseCirBandwidth(int useCirBandwidth) {
		this.useCirBandwidth = useCirBandwidth;
	}
	
	public int getCirCount() {
		return cirCount;
	}
	public void setCirCount(int cirCount) {
		this.cirCount = cirCount;
	}
	/**
	 * 获取使能状态
	 * @return
	 */
	private String getEnable(){
		
		String groupIdentity="ENABLEDSTATUE";
		String result=null;
		SiteService_MB siteService = null;
		
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			//武汉设备验证告警
			if(siteService.getManufacturer(this.getSiteId()) == EManufacturer.CHENXIAO.getValue()){
				groupIdentity="ENABLEDSTATUECX";
			}
			
			result= UiUtil.getCodeByValue(groupIdentity, this.getIsEnabled_code()+"").getCodeName();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return result;
		
	}

	//通过网元ID来查询相应的网元名称
	private String getSiteName(int siteId){
		SiteService_MB siteService = null;
		try {
		 siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
		 return siteService.select(siteId).getCellId();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return "";
	}
	
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public int getIsPriority() {
		return isPriority;
	}
	public void setIsPriority(int isPriority) {
		this.isPriority = isPriority;
	}
	
}
