package com.nms.db.bean.ptn;

import java.io.Serializable;
import java.util.List;

import com.nms.db.bean.ptn.qos.QosTemplateInfo;
import com.nms.db.enums.EClockQLType;
import com.nms.ui.frame.ViewDataObj;


public class CommonBean extends ViewDataObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318487589378511002L;
	private String macAdd;
	private String portName;
	private String pwName4vpls;//elan用到
	private String acName4vpls;//etree,elan,端口丢弃流用到
	private String loopPathName;//环网保护路径名称,即段名称，环网保护用到
	private String qinQVlanLimit;//QinQ的上下限以及vlanId的组合，端口二层属性用到
	private String cosName;//端口pri映射等级，be，af1,af2等，端口pri映射用到
	private String cosValue;//端口pri映射等级值，端口pri映射用到
	private String clockPortName;
	private EClockQLType qlClock;
	private String qosTemplateName;//qos模板用到
	private List<QosTemplateInfo> qosTemplateList;//qos模板用到
	private String protocolSwitch;//协议开关，端口聚合用到
	private String priorityLag;//优先级，端口聚合用到
	private String sourcePortName;//原端口,端口迁移用到
	private String selectedPortName;//已选目的端口，端口迁移用到
	private String addLspName;//选择修改路径，扩容，缩容用到
	private String addSiteName;//选择加入网元，扩容，缩容用到
	private String portNameWithA;//和A端网元相连端口，扩容，缩容用到
	private String portNameWithZ;//和Z端网元相连端口，扩容，缩容用到
	private boolean attributeEnable;//tunnel右键批量修改关键字用到
	private boolean mainPathEnable;//tunnel右键批量修改关键字用到
	private boolean reservePathEnable;//tunnel右键批量修改关键字用到
	//拓扑自动发现用
	private String topuSiteName;
	private String topuPortName;
	private String topuPortType;
	private int topuIsOccupy;
	private String topuMaxFrameSize="1518";//最大帧长=武汉MTU
	//QUEUE
	private String qosqueAsiteName;
	private String qosqueAType;
	private String qosqueZsiteName;
	private String qosqueZType;
	
	public boolean isAttributeEnable() {
		return attributeEnable;
	}

	public void setAttributeEnable(boolean attributeEnable) {
		this.attributeEnable = attributeEnable;
	}

	public boolean isMainPathEnable() {
		return mainPathEnable;
	}

	public void setMainPathEnable(boolean mainPathEnable) {
		this.mainPathEnable = mainPathEnable;
	}

	public boolean isReservePathEnable() {
		return reservePathEnable;
	}

	public void setReservePathEnable(boolean reservePathEnable) {
		this.reservePathEnable = reservePathEnable;
	}

	public String getAddLspName() {
		return addLspName;
	}

	public void setAddLspName(String addLspName) {
		this.addLspName = addLspName;
	}

	public String getAddSiteName() {
		return addSiteName;
	}

	public void setAddSiteName(String addSiteName) {
		this.addSiteName = addSiteName;
	}

	public String getPortNameWithA() {
		return portNameWithA;
	}

	public void setPortNameWithA(String portNameWithA) {
		this.portNameWithA = portNameWithA;
	}

	public String getPortNameWithZ() {
		return portNameWithZ;
	}

	public void setPortNameWithZ(String portNameWithZ) {
		this.portNameWithZ = portNameWithZ;
	}

	public String getSourcePortName() {
		return sourcePortName;
	}

	public void setSourcePortName(String sourcePortName) {
		this.sourcePortName = sourcePortName;
	}

	public String getSelectedPortName() {
		return selectedPortName;
	}

	public void setSelectedPortName(String selectedPortName) {
		this.selectedPortName = selectedPortName;
	}

	public String getProtocolSwitch() {
		return protocolSwitch;
	}

	public void setProtocolSwitch(String protocolSwitch) {
		this.protocolSwitch = protocolSwitch;
	}

	public String getPriority() {
		return priorityLag;
	}

	public void setPriority(String priority) {
		this.priorityLag = priority;
	}

	public String getQosTemplateName() {
		return qosTemplateName;
	}

	public void setQosTemplateName(String qosTemplateName) {
		this.qosTemplateName = qosTemplateName;
	}

	public List<QosTemplateInfo> getQosTemplateList() {
		return qosTemplateList;
	}

	public void setQosTemplateList(List<QosTemplateInfo> qosTemplateList) {
		this.qosTemplateList = qosTemplateList;
	}

	public String getCosName() {
		return cosName;
	}

	public String getQosqueZType() {
		return qosqueZType;
	}

	public void setQosqueZType(String qosqueZType) {
		this.qosqueZType = qosqueZType;
	}

	public String getQosqueZsiteName() {
		return qosqueZsiteName;
	}

	public void setQosqueZsiteName(String qosqueZsiteName) {
		this.qosqueZsiteName = qosqueZsiteName;
	}

	public String getQosqueAType() {
		return qosqueAType;
	}

	public void setQosqueAType(String qosqueAType) {
		this.qosqueAType = qosqueAType;
	}

	public String getQosqueAsiteName() {
		return qosqueAsiteName;
	}

	public void setQosqueAsiteName(String qosqueAsiteName) {
		this.qosqueAsiteName = qosqueAsiteName;
	}

	public int getTopuIsOccupy() {
		return topuIsOccupy;
	}

	public void setTopuIsOccupy(int topuIsOccupy) {
		this.topuIsOccupy = topuIsOccupy;
	}

	public String getTopuSiteName() {
		return topuSiteName;
	}

	public void setTopuSiteName(String topuSiteName) {
		this.topuSiteName = topuSiteName;
	}

	public String getTopuMaxFrameSize() {
		return topuMaxFrameSize;
	}

	public void setTopuMaxFrameSize(String topuMaxFrameSize) {
		this.topuMaxFrameSize = topuMaxFrameSize;
	}

	public String getTopuPortType() {
		return topuPortType;
	}

	public void setTopuPortType(String topuPortType) {
		this.topuPortType = topuPortType;
	}

	public String getTopuPortName() {
		return topuPortName;
	}

	public void setTopuPortName(String topuPortName) {
		this.topuPortName = topuPortName;
	}

	public EClockQLType getQlClock() {
		return qlClock;
	}

	public void setCosName(String cosName) {
		this.cosName = cosName;
	}

	public String getCosValue() {
		return cosValue;
	}

	public void setCosValue(String cosValue) {
		this.cosValue = cosValue;
	}

	public String getQinQVlanLimit() {
		return qinQVlanLimit;
	}

	public void setQinQVlanLimit(String qinQVlanLimit) {
		this.qinQVlanLimit = qinQVlanLimit;
	}

	public String getLoopPathName() {
		return loopPathName;
	}

	public void setLoopPathName(String loopPathName) {
		this.loopPathName = loopPathName;
	}

	public String getPwName4vpls() {
		return pwName4vpls;
	}

	public void setPwName4vpls(String pwName4vpls) {
		this.pwName4vpls = pwName4vpls;
	}

	public String getAcName() {
		return acName4vpls;
	}

	public void setAcName(String acName) {
		this.acName4vpls = acName;
	}

	public void setQlClock(EClockQLType qlClock) {
		this.qlClock = qlClock;
	}

	public String getClockPortName() {
		return clockPortName;
	}

	public void setClockPortName(String clockPortName) {
		this.clockPortName = clockPortName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	public String getMacAdd() {
		return macAdd;
	}

	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
	}

	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		
	}







}
