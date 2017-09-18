package com.nms.db.bean.ptn.path.protect;

import java.io.Serializable;

public class PwProtect  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8690539777495064067L;
	private int id;//数据库主键id
	private int protectType;//保护类型
	private int mainSlot;//主用槽位
	private int mainPort;//主用端口
	private int mainTunnelId;//主用tunnelId
	private int mainPwId;//主用pwId
	private int standSlot;//备用槽位
	private int standPort;//备用端口
	private int standTunnelId;//备用tunnelId
	private int standPwId;//备用pwId
	private int delayTime;//拖延时间
	private int backType;//返回类型
	private int siteId;//网元id
	private int serviceId;//关联业务id
	private int businessId;//设备条目id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public int getMainSlot() {
		return mainSlot;
	}
	public void setMainSlot(int mainSlot) {
		this.mainSlot = mainSlot;
	}
	public int getMainPort() {
		return mainPort;
	}
	public void setMainPort(int mainPort) {
		this.mainPort = mainPort;
	}
	public int getMainTunnelId() {
		return mainTunnelId;
	}
	public void setMainTunnelId(int mainTunnelId) {
		this.mainTunnelId = mainTunnelId;
	}
	public int getMainPwId() {
		return mainPwId;
	}
	public void setMainPwId(int mainPwId) {
		this.mainPwId = mainPwId;
	}
	public int getStandSlot() {
		return standSlot;
	}
	public void setStandSlot(int standSlot) {
		this.standSlot = standSlot;
	}
	public int getStandPort() {
		return standPort;
	}
	public void setStandPort(int standPort) {
		this.standPort = standPort;
	}
	public int getStandTunnelId() {
		return standTunnelId;
	}
	public void setStandTunnelId(int standTunnelId) {
		this.standTunnelId = standTunnelId;
	}
	public int getStandPwId() {
		return standPwId;
	}
	public void setStandPwId(int standPwId) {
		this.standPwId = standPwId;
	}
	public int getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	
}
