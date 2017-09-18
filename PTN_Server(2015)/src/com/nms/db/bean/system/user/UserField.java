package com.nms.db.bean.system.user;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserField implements Serializable {
	public int id;
	public int user_id;
	public int field_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		user_id = userId;
	}

	public int getField_id() {
		return field_id;
	}

	public void setField_id(int fieldId) {
		field_id = fieldId;
	}

}
