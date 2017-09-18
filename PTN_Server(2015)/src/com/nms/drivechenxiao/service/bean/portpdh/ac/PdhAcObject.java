package com.nms.drivechenxiao.service.bean.portpdh.ac;

import com.nms.drivechenxiao.service.bean.pwpdh.service.CesServiceObject;

public class PdhAcObject {
	CesServiceObject cesServiceObject = new CesServiceObject();

	private String name = "";// 名称
	private String admin = "";// 接口管理状态
	/**
	 * 修改业务 (ces-sdh)
	 * identify  原来的名称-与name相对应
	 * 判断   pw是否修改
	 */
	private String identify="";
	
	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public CesServiceObject getCesServiceObject() {
		return cesServiceObject;
	}

	public void setCesServiceObject(CesServiceObject cesServiceObject) {
		this.cesServiceObject = cesServiceObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
