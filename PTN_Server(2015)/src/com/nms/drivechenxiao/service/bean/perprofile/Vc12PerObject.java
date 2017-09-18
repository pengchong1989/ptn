package com.nms.drivechenxiao.service.bean.perprofile;

/**用户性能配置vc12
 * 
 * **/
public class Vc12PerObject {
	private String name;
	private String type;
	private String lpes;//低阶通道误码秒
	private String lpses;//低阶通道严重误码秒
	private String lpbbe;//低阶通道背景块误码
	private String lpuas;//低阶通道不可用秒
	private String ref;
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
	public String getLpes() {
		return lpes;
	}
	public void setLpes(String lpes) {
		this.lpes = lpes;
	}
	public String getLpses() {
		return lpses;
	}
	public void setLpses(String lpses) {
		this.lpses = lpses;
	}
	public String getLpbbe() {
		return lpbbe;
	}
	public void setLpbbe(String lpbbe) {
		this.lpbbe = lpbbe;
	}
	public String getLpuas() {
		return lpuas;
	}
	public void setLpuas(String lpuas) {
		this.lpuas = lpuas;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
}
