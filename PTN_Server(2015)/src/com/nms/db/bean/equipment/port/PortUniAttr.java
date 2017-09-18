package com.nms.db.bean.equipment.port;

import java.io.Serializable;


/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortUniAttr   
* 类描述：   端口属性uni部分
* 创建人：kk   
* 创建时间：2013-7-16 上午10:01:41   
* 修改人：kk   
* 修改时间：2013-7-16 上午10:01:41   
* 修改备注：   
* @version    
*
 */
public class PortUniAttr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1085724265349701616L;
	private int ethernetPackaging;//以太网封装 对应code表主键
	private int vlanTpId;//运营商vlantpid 对应code表主键
	private int outerVlanTpId;//outer vlan tp id，对应code表主键
	private int vlanMode;//以太网vlan模式，对应code表主键
	private int isBroadcast;//广播包抑制开关 0=false 1=true
	private String broadcast;//广播报文抑制=武汉 广播包抑制
	private int isUnicast;//洪泛包抑制开关 0=false 1=true
	private String unicast;//单播报文抑制=洪泛包抑制
	private int isMulticast;//组播包抑制开关 0=false 1=true
	private String multicast;//组播报文抑制=组播包抑制
	private String vlanId;//缺省vlanid
	private String vlanPri;//缺省vlan优先级
	private int vlanRelevance;//VLAN关联设置 0=false 1=true
	private int eightIpRelevance;//802.iP关联设置 0=false 1=true
	private int sourceMacRelevance;//源MAC地址关联设置 0=false 1=true
	private int destinationMacRelevance;//目的MAC地址关联设置 0=false 1=true
	private int sourceIpRelevance;//源IP地址关联设置 0=false 1=true
	private int destinationIpRelevance;//目的MAC地址关联设置 0=false 1=true
	private int pwRelevance;//pw关联设置 0=false 1=true
	private int dscpRelevance;//dscp关联设置 0=false 1=true
	private int mappingEnable = 1;//基于PRI到PHB映射使能
	private int sourceTcpPortRelevance;//源tcp/udp端口号关联设置
	private int endTcpPortRelevance;//宿tcp/udp端口号关联设置
	private int tosRelevance;//tos关联设置
	@Override
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" ethernetPackaging=").append(ethernetPackaging)
		.append(" ;vlanTpId=").append(vlanTpId).append(" ;outerVlanTpId=").append(outerVlanTpId)
		.append(" ;vlanMode=").append(vlanMode).append(" ;isBroadcast=").append(isBroadcast)
		.append(" ;broadcast=").append(broadcast).append(" ;isUnicast=").append(isUnicast)
		.append(" ;unicast=").append(unicast).append(" ;isMulticast=").append(isMulticast)
		.append(" ;multicast=").append(multicast).append(" ;vlanId=").append(vlanId)
		.append(" ;vlanPri=").append(vlanPri).append(" ;vlanRelevance=").append(vlanRelevance)
		.append(" ;eightIpRelevance=").append(eightIpRelevance).append(" ;sourceMacRelevance=").append(sourceMacRelevance)
		.append(" ;destinationMacRelevance=").append(destinationMacRelevance).append(" ;sourceIpRelevance=").append(sourceIpRelevance)
		.append(" ;destinationIpRelevance=").append(destinationIpRelevance).append(" ;pwRelevance=").append(pwRelevance)
		.append(" ;dscpRelevance=").append(dscpRelevance);
		return sb.toString();
	}
	
	public int getTosRelevance() {
		return tosRelevance;
	}

	public void setTosRelevance(int tosRelevance) {
		this.tosRelevance = tosRelevance;
	}

	public int getEthernetPackaging() {
		return ethernetPackaging;
	}
	public void setEthernetPackaging(int ethernetPackaging) {
		this.ethernetPackaging = ethernetPackaging;
	}
	public int getVlanTpId() {
		return vlanTpId;
	}
	public void setVlanTpId(int vlanTpId) {
		this.vlanTpId = vlanTpId;
	}
	public int getOuterVlanTpId() {
		return outerVlanTpId;
	}
	public void setOuterVlanTpId(int outerVlanTpId) {
		this.outerVlanTpId = outerVlanTpId;
	}
	public int getVlanMode() {
		return vlanMode;
	}
	public void setVlanMode(int vlanMode) {
		this.vlanMode = vlanMode;
	}
	public int getIsBroadcast() {
		return isBroadcast;
	}
	public void setIsBroadcast(int isBroadcast) {
		this.isBroadcast = isBroadcast;
	}
	public String getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public int getIsUnicast() {
		return isUnicast;
	}
	public void setIsUnicast(int isUnicast) {
		this.isUnicast = isUnicast;
	}
	public String getUnicast() {
		return unicast;
	}
	public void setUnicast(String unicast) {
		this.unicast = unicast;
	}
	public int getIsMulticast() {
		return isMulticast;
	}
	public void setIsMulticast(int isMulticast) {
		this.isMulticast = isMulticast;
	}
	public String getMulticast() {
		return multicast;
	}
	public void setMulticast(String multicast) {
		this.multicast = multicast;
	}
	public String getVlanId() {
		return vlanId;
	}
	public void setVlanId(String vlanId) {
		this.vlanId = vlanId;
	}
	public String getVlanPri() {
		return vlanPri;
	}
	public void setVlanPri(String vlanPri) {
		this.vlanPri = vlanPri;
	}
	public int getVlanRelevance() {
		return vlanRelevance;
	}
	public void setVlanRelevance(int vlanRelevance) {
		this.vlanRelevance = vlanRelevance;
	}
	public int getEightIpRelevance() {
		return eightIpRelevance;
	}
	public void setEightIpRelevance(int eightIpRelevance) {
		this.eightIpRelevance = eightIpRelevance;
	}
	public int getSourceMacRelevance() {
		return sourceMacRelevance;
	}
	public void setSourceMacRelevance(int sourceMacRelevance) {
		this.sourceMacRelevance = sourceMacRelevance;
	}
	public int getDestinationMacRelevance() {
		return destinationMacRelevance;
	}
	public void setDestinationMacRelevance(int destinationMacRelevance) {
		this.destinationMacRelevance = destinationMacRelevance;
	}
	public int getSourceIpRelevance() {
		return sourceIpRelevance;
	}
	public void setSourceIpRelevance(int sourceIpRelevance) {
		this.sourceIpRelevance = sourceIpRelevance;
	}
	public int getDestinationIpRelevance() {
		return destinationIpRelevance;
	}
	public void setDestinationIpRelevance(int destinationIpRelevance) {
		this.destinationIpRelevance = destinationIpRelevance;
	}
	public int getPwRelevance() {
		return pwRelevance;
	}
	public void setPwRelevance(int pwRelevance) {
		this.pwRelevance = pwRelevance;
	}
	public int getDscpRelevance() {
		return dscpRelevance;
	}
	public void setDscpRelevance(int dscpRelevance) {
		this.dscpRelevance = dscpRelevance;
	}

	public int getMappingEnable() {
		return mappingEnable;
	}

	public void setMappingEnable(int mappingEnable) {
		this.mappingEnable = mappingEnable;
	}

	public int getSourceTcpPortRelevance() {
		return sourceTcpPortRelevance;
	}

	public void setSourceTcpPortRelevance(int sourceTcpPortRelevance) {
		this.sourceTcpPortRelevance = sourceTcpPortRelevance;
	}

	public int getEndTcpPortRelevance() {
		return endTcpPortRelevance;
	}

	public void setEndTcpPortRelevance(int endTcpPortRelevance) {
		this.endTcpPortRelevance = endTcpPortRelevance;
	}
}
