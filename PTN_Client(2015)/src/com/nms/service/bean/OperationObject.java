package com.nms.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 下发设备与回调对象
 * 
 * @author Administrator
 * 
 */
public class OperationObject {

	private boolean isSuccess;// 设备返回是否成功标志
	private List<ActionObject> actionObjectList = new ArrayList<ActionObject>();// 武汉驱动事件对象
	private int errorLabel = 0;// 用于区分 不成功的标志(0:表示 设备返回来的不成功，1:表示超时而导致的不成功)

	public List<ActionObject> getActionObjectList() {
		return actionObjectList;
	}

	public void setActionObjectList(List<ActionObject> actionObjectList) {
		this.actionObjectList = actionObjectList;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(int errorLabel) {
		this.errorLabel = errorLabel;
	}
}
