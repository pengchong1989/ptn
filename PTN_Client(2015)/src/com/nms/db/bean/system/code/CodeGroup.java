package com.nms.db.bean.system.code;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class CodeGroup implements Serializable {
	private int id;
	private String codeGroupName;
	private String codeIdentily;
	private List<Code> codeList;
	private String codeDesc;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeGroupName() {
		return codeGroupName;
	}

	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}

	public String getCodeIdentily() {
		return codeIdentily;
	}

	public void setCodeIdentily(String codeIdentily) {
		this.codeIdentily = codeIdentily;
	}
	
    
	public List<Code> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<Code> codeList) {
		this.codeList = codeList;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}
