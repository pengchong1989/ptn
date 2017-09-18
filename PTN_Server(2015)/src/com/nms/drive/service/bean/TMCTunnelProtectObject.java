package com.nms.drive.service.bean;

/**
 * TMC通道保护 (0EH)
 * 
 * @author hulei
 * 
 */
public class TMCTunnelProtectObject {
	private int tmcTunnelId = 0;// 条目id
	private int protectionType = 0;// 保护类型
	private int mainSlot = 255;// 主用槽位
	private int mainPort = 0;// 主用端口
	private int mainLspId = 0;// 主用LSP ID
	private int mainPwId = 0;// 主用Pw ID
	private int standbySlot = 255;// 备用槽位
	private int stnadbyPort = 0;// 备用端口
	private int standbyLspId = 0;// 备用LSP ID
	private int standbyPwId = 0;// 备用Pw ID
	private int protractedTime = 0;// 拖延时间:00-FFH =0-255
	private int returnType = 0;// 返回类型:00/01=返回型/不返回型
	private int reserve = 0;// 备用,00

	public int getTmcTunnelId() {
		return tmcTunnelId;
	}

	public void setTmcTunnelId(int tmcTunnelId) {
		this.tmcTunnelId = tmcTunnelId;
	}

	public int getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(int protectionType) {
		this.protectionType = protectionType;
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

	public int getMainLspId() {
		return mainLspId;
	}

	public void setMainLspId(int mainLspId) {
		this.mainLspId = mainLspId;
	}

	public int getMainPwId() {
		return mainPwId;
	}

	public void setMainPwId(int mainPwId) {
		this.mainPwId = mainPwId;
	}

	public int getStandbySlot() {
		return standbySlot;
	}

	public void setStandbySlot(int standbySlot) {
		this.standbySlot = standbySlot;
	}

	public int getStnadbyPort() {
		return stnadbyPort;
	}

	public void setStnadbyPort(int stnadbyPort) {
		this.stnadbyPort = stnadbyPort;
	}

	public int getStandbyLspId() {
		return standbyLspId;
	}

	public void setStandbyLspId(int standbyLspId) {
		this.standbyLspId = standbyLspId;
	}

	public int getStandbyPwId() {
		return standbyPwId;
	}

	public void setStandbyPwId(int standbyPwId) {
		this.standbyPwId = standbyPwId;
	}

	public int getProtractedTime() {
		return protractedTime;
	}

	public void setProtractedTime(int protractedTime) {
		this.protractedTime = protractedTime;
	}

	public int getReturnType() {
		return returnType;
	}

	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

}
