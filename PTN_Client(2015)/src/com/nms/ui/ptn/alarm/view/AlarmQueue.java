package com.nms.ui.ptn.alarm.view;

import com.nms.db.bean.alarm.WarningLevel;
import com.nms.ui.frame.ContentView;
import com.nms.ui.ptn.alarm.controller.AlarmQueueController;

public class AlarmQueue extends ContentView<WarningLevel> {
	
	public  AlarmQueueController controller;
	
	public AlarmQueue(String tableAttrs, int rootLabel) {
		super("alarmShow", rootLabel);
	}

	@Override
	public void setController() {
		controller = new AlarmQueueController(this);
	}
	

}
