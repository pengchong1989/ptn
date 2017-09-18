package com.nms.db.bean.ptn.path.protect;

import java.io.Serializable;
/**
 * 保护组 实体类
 * @author dzy
 *
 */
public class Protect_Corba implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1532028094635778121L;
	private String name;	//保护组名称
	private int id;//各保护主键ID
	private int siteId;	//所属的网元标识符
	private String siteName;	//所属的网元
	private int protectionGroupType;	//保护组类型   0=_PGT_MSP_1_PLUS_1，1=_PGT_MSP_1_FOR_N，2=_PGT_2_FIBER_BLSR ，3=_PGT_4_FIBER_BLSR
	private int rate;	//层速率  34=protect
	private int protectionSchemeState;	//保护倒换状态 0=_PSS_UNKNOWN（空闲），1= _PSS_AUTOMATIC（正在切换），2= _PSS_FORCED_OR_LOCKED_OUT（已切换），
	private int waitTime;	//恢复等待时间
	private int delaytime;	//拖延时间
	private int alarmContinueTime;	//告警持续时间
	private int portId_work;	//被保护的终端点ID  \west网元ID
	private int portId_pro;	//用来保护的终端点ID\east网元ID
	private String portName_work;	//被保护的终端点NAME  \west网元
	private String portName_pro;	//用来保护的终端点NAME\east网元
	private String objType;//objType 用于corba接口name的唯一标识
	private int rotateOrder;//倒换命令
	private int beRotated; //是否倒换 1=倒换 0=未倒换
	private int apsenable;//asp使能 1=使能 0=未使能
	private int sfApsenable; //af优先级 1=高 2=低
	private int sdApsenable;	//ad优先级 1=高 2=低
	private int reversionMode;	//回复模式 1=可恢复 2=不可恢复
	private int protectType;	//保护类型 参考code MSPPROTECTTYPE
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getProtectionGroupType() {
		return protectionGroupType;
	}
	public void setProtectionGroupType(int protectionGroupType) {
		this.protectionGroupType = protectionGroupType;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getProtectionSchemeState() {
		return protectionSchemeState;
	}
	public void setProtectionSchemeState(int protectionSchemeState) {
		this.protectionSchemeState = protectionSchemeState;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getAlarmContinueTime() {
		return alarmContinueTime;
	}
	public void setAlarmContinueTime(int alarmContinueTime) {
		this.alarmContinueTime = alarmContinueTime;
	}
	
	public int getPortId_pro() {
		return portId_pro;
	}
	public void setPortId_pro(int portId_pro) {
		this.portId_pro = portId_pro;
	}
	public int getPortId_work() {
		return portId_work;
	}
	public void setPortId_work(int portId_work) {
		this.portId_work = portId_work;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getPortName_work() {
		return portName_work;
	}
	public void setPortName_work(String portName_work) {
		this.portName_work = portName_work;
	}
	public String getPortName_pro() {
		return portName_pro;
	}
	public void setPortName_pro(String portName_pro) {
		this.portName_pro = portName_pro;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRotateOrder() {
		return rotateOrder;
	}
	public void setRotateOrder(int rotateOrder) {
		this.rotateOrder = rotateOrder;
	}
	public int getBeRotated() {
		return beRotated;
	}
	public void setBeRotated(int beRotated) {
		this.beRotated = beRotated;
	}
	public int getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(int delaytime) {
		this.delaytime = delaytime;
	}
	public int getApsenable() {
		return apsenable;
	}
	public void setApsenable(int apsenable) {
		this.apsenable = apsenable;
	}
	public int getSfApsenable() {
		return sfApsenable;
	}
	public void setSfApsenable(int sfApsenable) {
		this.sfApsenable = sfApsenable;
	}
	public int getSdApsenable() {
		return sdApsenable;
	}
	public void setSdApsenable(int sdApsenable) {
		this.sdApsenable = sdApsenable;
	}
	public int getReversionMode() {
		return reversionMode;
	}
	public void setReversionMode(int reversionMode) {
		this.reversionMode = reversionMode;
	}
	
}
