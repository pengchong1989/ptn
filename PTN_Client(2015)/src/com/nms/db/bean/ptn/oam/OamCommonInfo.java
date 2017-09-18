package com.nms.db.bean.ptn.oam;

import java.io.Serializable;

public class OamCommonInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4118257296985950324L;
	private int id;
	private int siteId;
	private String siteName;//log日志显示时使用
	private String objNameLog;//log日志显示时使用
	private String objType;
	private int objId;
	private String megIcc; // MEG ICC :123456 (字符方式显示,左侧字符对齐)
	private String megUmc; // MEG UMC:789ABC (字符方式显示,左侧字符对齐)
	private int serviceId;
	private int megId;

	public String getObjNameLog() {
		return objNameLog;
	}

	public void setObjNameLog(String objNameLog) {
		this.objNameLog = objNameLog;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getMegIcc() {
		return megIcc;
	}

	public void setMegIcc(String megIcc) {
		this.megIcc = megIcc;
	}

	public String getMegUmc() {
		return megUmc;
	}

	public void setMegUmc(String megUmc) {
		this.megUmc = megUmc;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getMegId() {
		return megId;
	}

	public void setMegId(int megId) {
		this.megId = megId;
	}

}
