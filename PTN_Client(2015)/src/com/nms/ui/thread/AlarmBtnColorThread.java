package com.nms.ui.thread;

import java.awt.Color;
import java.util.List;

import com.nms.model.alarm.AlarmVoiceService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.model.AlarmVoiceInfo;
import com.nms.ui.ptn.alarm.view.CircleButton;

public class AlarmBtnColorThread implements Runnable {
	
	private CircleButton urgencyBtn;//紧急告警灯
	private CircleButton majorBtn;//主要告警灯
	private CircleButton minorBtn;//次要告警灯
	private CircleButton promptBtn;//提示告警灯
	private int urgencycolor = 0;//告警灯闪烁开关
	private int majorcolor = 0;
	private int minorcolor = 0;
	private int promptcolor = 0;
	
	public AlarmBtnColorThread(CircleButton urgencyBtn, CircleButton majorBtn, CircleButton minorBtn, CircleButton promptBtn) {
		Thread.currentThread().setName("AlarmBtnColorThread");
		this.urgencyBtn = urgencyBtn;
		this.majorBtn = majorBtn;
		this.minorBtn = minorBtn;
		this.promptBtn = promptBtn;
	}

	@Override
	public void run() {
		AlarmVoiceService_MB voiceService = null;
		try {
			try {
				voiceService = (AlarmVoiceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALARMVOICE);
				List<AlarmVoiceInfo> voiceList = voiceService.queryAllVoice();
				this.setColorRGB(voiceList);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(voiceService);
			}
			while (true) {
				this.urgencyBtn.setBackground(new Color(ConstantUtil.URGENCYCOLOR));
				this.majorBtn.setBackground(new Color(ConstantUtil.MAJORCOLOR));
				this.minorBtn.setBackground(new Color(ConstantUtil.MINORCOLOR));
				this.promptBtn.setBackground(new Color(ConstantUtil.PROMPTCOLOR));
				Thread.sleep(500);
				if(urgencycolor > 0){
					this.urgencyBtn.setBackground(new Color(204,204,204));//灰色
				}
				if(majorcolor > 0){
					this.majorBtn.setBackground(new Color(204,204,204));
				}
				if(minorcolor > 0){
					this.minorBtn.setBackground(new Color(204,204,204));
				}
				if(promptcolor > 0){
					this.promptBtn.setBackground(new Color(204,204,204));
				}
				Thread.sleep(500);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setColorRGB(List<AlarmVoiceInfo> voiceList) {
		for (AlarmVoiceInfo voice : voiceList) {
			if(voice.getAlarmType() == 1){
				ConstantUtil.URGENCYCOLOR = voice.getAlarmColorRGB();
			}else if(voice.getAlarmType() == 2){
				ConstantUtil.MAJORCOLOR = voice.getAlarmColorRGB();
			}else if(voice.getAlarmType() == 3){
				ConstantUtil.MINORCOLOR = voice.getAlarmColorRGB();
			}else if(voice.getAlarmType() == 4){
				ConstantUtil.PROMPTCOLOR = voice.getAlarmColorRGB();
			}
		}
	}

	public void setUrgencycolor(int urgencycolor) {
		this.urgencycolor = urgencycolor;
	}

	public void setMajorcolor(int majorcolor) {
		this.majorcolor = majorcolor;
	}

	public void setMinorcolor(int minorcolor) {
		this.minorcolor = minorcolor;
	}

	public void setPromptcolor(int promptcolor) {
		this.promptcolor = promptcolor;
	}
}
