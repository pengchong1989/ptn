package com.nms.db.bean.equipment.port;

import java.io.Serializable;

/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortAttr   
* 类描述：   端口属性
* 创建人：kk   
* 创建时间：2013-7-16 上午10:02:23   
* 修改人：kk   
* 修改时间：2013-7-16 上午10:02:23   
* 修改备注：   
* @version    
*
 */
@SuppressWarnings("serial")
public class PortAttr implements Serializable {
	
	private int id;	//自增、主键
	private int siteId;	//对应网元表主键
	private int portId;	//对应端口表主键
	private int portSpeed; //设置端口速率，对应code表主键			--
	private String actualSpeed;	//实际速率							---
	private int workModel;//工作模式，对应code表主键
	private String maxFrameSize="1518";//最大帧长=武汉MTU	   --
	private int fluidControl;//流控=武汉802.3流控				--
	private String exitLimit;//出口限速								---
	private String entranceLimit;//入口限速							---
	private int slowAgreement;//启动慢协议
	private int tenWan;//启动10G WAN								--
	private int messageLoopback;//是否启动报文环回				--
	private int sfpExpectType;//SFP期望类型 对应code表主键		--
	private String sfpActual;//SFP实际类型                                                          --
	private String workWavelength;//工作波长						--
	private String sfpVender;//sfp厂家信息						--
	private PortUniAttr portUniAttr = new PortUniAttr();	//uni属性
	private PortNniAttr portNniAttr = new PortNniAttr();	//nni属性
	
	@Override
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" id=").append(id)
		.append(" ;siteId=").append(siteId).append(" ;portId=").append(portId)
		.append(" ;portSpeed=").append(portSpeed).append(" ;actualSpeed=").append(actualSpeed)
		.append(" ;workModel=").append(workModel).append(" ;maxFrameSize=").append(maxFrameSize)
		.append(" ;fluidControl=").append(fluidControl).append(" ;exitLimit=").append(exitLimit)
		.append(" ;entranceLimit=").append(entranceLimit).append(" ;slowAgreement=").append(slowAgreement)
		.append(" ;tenWan=").append(tenWan).append(" ;messageLoopback=").append(messageLoopback)
		.append(" ;sfpExpectType=").append(sfpExpectType).append(" ;sfpActual=").append(sfpActual)
		.append(" ;workWavelength=").append(workWavelength).append(" ;sfpVender=").append(sfpVender)
		.append(" ;portUniAttr={").append(portUniAttr.toString())
		.append("} ;portNniAttr={").append(portNniAttr.toString()).append("}");
		return sb.toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPortId() {
		return portId;
	}
	public void setPortId(int portId) {
		this.portId = portId;
	}
	public int getPortSpeed() {
		return portSpeed;
	}
	public void setPortSpeed(int portSpeed) {
		this.portSpeed = portSpeed;
	}
	public String getActualSpeed() {
		return actualSpeed;
	}
	public void setActualSpeed(String actualSpeed) {
		this.actualSpeed = actualSpeed;
	}
	public int getWorkModel() {
		return workModel;
	}
	public void setWorkModel(int workModel) {
		this.workModel = workModel;
	}
	public String getMaxFrameSize() {
		return maxFrameSize;
	}
	public void setMaxFrameSize(String maxFrameSize) {
		this.maxFrameSize = maxFrameSize;
	}
	public int getFluidControl() {
		return fluidControl;
	}
	public void setFluidControl(int fluidControl) {
		this.fluidControl = fluidControl;
	}
	public String getExitLimit() {
		return exitLimit;
	}
	public void setExitLimit(String exitLimit) {
		this.exitLimit = exitLimit;
	}
	public String getEntranceLimit() {
		return entranceLimit;
	}
	public void setEntranceLimit(String entranceLimit) {
		this.entranceLimit = entranceLimit;
	}
	public int getSlowAgreement() {
		return slowAgreement;
	}
	public void setSlowAgreement(int slowAgreement) {
		this.slowAgreement = slowAgreement;
	}
	public int getTenWan() {
		return tenWan;
	}
	public void setTenWan(int tenWan) {
		this.tenWan = tenWan;
	}
	public int getMessageLoopback() {
		return messageLoopback;
	}
	public void setMessageLoopback(int messageLoopback) {
		this.messageLoopback = messageLoopback;
	}
	public int getSfpExpectType() {
		return sfpExpectType;
	}
	public void setSfpExpectType(int sfpExpectType) {
		this.sfpExpectType = sfpExpectType;
	}
	public String getSfpActual() {
		return sfpActual;
	}
	public void setSfpActual(String sfpActual) {
		this.sfpActual = sfpActual;
	}
	public String getWorkWavelength() {
		return workWavelength;
	}
	public void setWorkWavelength(String workWavelength) {
		this.workWavelength = workWavelength;
	}
	public String getSfpVender() {
		return sfpVender;
	}
	public void setSfpVender(String sfpVender) {
		this.sfpVender = sfpVender;
	}
	public PortUniAttr getPortUniAttr() {
		return portUniAttr;
	}
	public void setPortUniAttr(PortUniAttr portUniAttr) {
		this.portUniAttr = portUniAttr;
	}
	public PortNniAttr getPortNniAttr() {
		return portNniAttr;
	}
	public void setPortNniAttr(PortNniAttr portNniAttr) {
		this.portNniAttr = portNniAttr;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}
