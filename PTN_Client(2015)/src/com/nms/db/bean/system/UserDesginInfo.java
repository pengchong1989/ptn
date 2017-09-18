package com.nms.db.bean.system;

public class UserDesginInfo {
	private int id;
	private int isSelect;// 用户自定义是否勾选 1：勾选，0：未勾选
	private String minute;// 自定义锁屏的时间
	private String userName;//用户名

	public int getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(int isSelect) {
		this.isSelect = isSelect;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
