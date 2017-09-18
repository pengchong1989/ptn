package com.nms.drivechenxiao.service.bean.perprofile;
/**
 * 性能配置里面的pdh性能
 * **/
public class PdhPerObject {
	private String name;
	private String type;
	private String pdhcv;//flag=15,m15hh=65535,m15h1=52428,h24hh=6291360,h24h1=5033088
	private String ref; //0x0
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPdhcv() {
		return pdhcv;
	}
	public void setPdhcv(String pdhcv) {
		this.pdhcv = pdhcv;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
