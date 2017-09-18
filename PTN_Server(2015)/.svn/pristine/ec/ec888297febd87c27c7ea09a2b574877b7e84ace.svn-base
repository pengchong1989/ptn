package com.nms.db.bean.system;

import com.nms.ui.frame.ViewDataObj;

public class OperationDataLog extends ViewDataObj {

	private static final long serialVersionUID = -4523926163983697621L;
	private int id;//主键id
	private int opeLogId;//OperationLog的主键id
	private String value_before;//操作之前的值
	private String value_after;//操作之后的值
	private String fieldNameId;//属性名称标识
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpeLogId() {
		return opeLogId;
	}

	public void setOpeLogId(int opeLogId) {
		this.opeLogId = opeLogId;
	}

	public String getValue_before() {
		return value_before;
	}

	public void setValue_before(String valueBefore) {
		value_before = valueBefore;
	}

	public String getValue_after() {
		return value_after;
	}

	public void setValue_after(String valueAfter) {
		value_after = valueAfter;
	}

	public String getFieldNameId() {
		return fieldNameId;
	}

	public void setFieldNameId(String fieldNameId) {
		this.fieldNameId = fieldNameId;
	}

	@Override
	public void putObjectProperty() {
		this.putClientProperty("fieldNameId", this.fieldNameId);
		this.putClientProperty("value_Before", this.value_before);
		this.putClientProperty("value_after", this.value_after);
	}

}
