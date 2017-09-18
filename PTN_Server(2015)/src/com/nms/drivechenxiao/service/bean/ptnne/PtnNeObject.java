package com.nms.drivechenxiao.service.bean.ptnne;

public class PtnNeObject {
	private String id = "";
	private String type = "";
	private String ver = "";
	private String oui = "";
	private String icccode = "";
	private String tpoamchntype = "";
	private String desc = "";
	private String timezone = "";
	private String topalmlvl = "";

	public String toString(){
		StringBuffer sb=new StringBuffer().append(" id=").append(id)
		.append(" ;type=").append(type).append(" ;ver=").append(ver)
		.append(" ;oui=").append(oui).append(" ;icccode=").append(icccode)
		.append(" ;tpoamchntype=").append(tpoamchntype).append(" ;desc=").append(desc)
		.append(" ;timezone=").append(timezone).append(" ;topalmlvl=").append(topalmlvl);
		return sb.toString();
	}
	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getOui() {
		return oui;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	public String getIcccode() {
		return icccode;
	}

	public void setIcccode(String icccode) {
		this.icccode = icccode;
	}

	public String getTpoamchntype() {
		return tpoamchntype;
	}

	public void setTpoamchntype(String tpoamchntype) {
		this.tpoamchntype = tpoamchntype;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getTopalmlvl() {
		return topalmlvl;
	}

	public void setTopalmlvl(String topalmlvl) {
		this.topalmlvl = topalmlvl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
