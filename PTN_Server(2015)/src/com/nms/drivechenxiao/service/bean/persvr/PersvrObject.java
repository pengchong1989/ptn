package com.nms.drivechenxiao.service.bean.persvr;

import com.nms.drivechenxiao.service.bean.CXNEObject;

public class PersvrObject {
	private CXNEObject cXNEObject = null;
	private String objId = "";
	private String perid = "";
	private String value = "";
	private String code = "";

	public String toString(){
		return new StringBuffer().append("objId=").append(objId).append(";perid=").append(perid)
		.append(";value=").append(value).toString();
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CXNEObject getCXNEObject() {
		return cXNEObject;
	}

	public void setCXNEObject(CXNEObject object) {
		cXNEObject = object;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getPerid() {
		return perid;
	}

	public void setPerid(String perid) {
		this.perid = perid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
