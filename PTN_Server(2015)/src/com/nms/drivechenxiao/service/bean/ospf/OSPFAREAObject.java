package com.nms.drivechenxiao.service.bean.ospf;

public class OSPFAREAObject {
	private String id = "";
	private String neId = "";
	private String defmetric = "";//[1,65535]	设置生成的默认路由的开销值	 id
	private String type = "";
	private String nosummaries="";// bool 设置区域是否启用no sumamry

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNeId() {
		return neId;
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	

	public String getDefmetric() {
		return defmetric;
	}

	public void setDefmetric(String defmetric) {
		this.defmetric = defmetric;
	}

	public String getNosummaries() {
		return nosummaries;
	}

	public void setNosummaries(String nosummaries) {
		this.nosummaries = nosummaries;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
