package com.nms.ui.ptn.alarm.model;

import java.io.Serializable;

public class CurrentAlarm implements Serializable{

private static final long serialVersionUID = 665390789261439075L;
private int siteid;

public int getSiteid() {
	return siteid;
}

public void setSiteid(int siteid) {
	this.siteid = siteid;
}

}
