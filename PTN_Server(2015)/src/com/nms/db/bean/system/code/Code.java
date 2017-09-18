package com.nms.db.bean.system.code;

import java.io.Serializable;

/**
 * Code类，对应数据库的Code表
 */
public class Code implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int codeGroupId;
	private String codeName;
	private String codeENName;
	private String codeValue;
	private int orderby;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodeGroupId() {
		return codeGroupId;
	}

	public void setCodeGroupId(int codeGroupId) {
		this.codeGroupId = codeGroupId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public int getOrderby() {
		return orderby;
	}

	public void setOrderby(int orderby) {
		this.orderby = orderby;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getCodeENName() {
		return codeENName;
	}
	public void setCodeENName(String codeENName) {
		this.codeENName = codeENName;
	}
}
