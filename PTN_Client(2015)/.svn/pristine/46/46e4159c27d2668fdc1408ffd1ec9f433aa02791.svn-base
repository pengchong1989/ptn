package com.nms.ui.ptn.alarm.controller;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.alarm.WarningLevel;
import com.nms.model.alarm.WarningLevelService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.view.AlarmQueue;

public class AlarmQueueController extends AbstractController{
	
	private AlarmQueue alarmQueue;
	public AlarmQueueController(AlarmQueue view) {
		this.alarmQueue = view;
	}
	@Override
	public void refresh() throws Exception {
		WarningLevel warningLevel = null;
		WarningLevelService_MB warningLevelService = null;
		List<WarningLevel> warnList = null; 
		try {
			warningLevelService = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
			warningLevel = new WarningLevel();
			warnList = new ArrayList<WarningLevel>();
			warningLevel.setManufacturer(1);//表示武汉
			warnList = warningLevelService.select(warningLevel);
			this.alarmQueue.initData(warnList);
			this.alarmQueue.updateUI();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(warningLevelService);
		}
	}
}
