package com.nms.ui.thread;

import java.util.ArrayList;
import java.util.List;

import com.nms.model.alarm.AlarmVoiceService_MB;
import com.nms.model.util.Services;
import com.nms.ui.Ptnf;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.model.AlarmAudio;
import com.nms.ui.ptn.alarm.model.AlarmVoiceInfo;

/**
 * 告警声音线程
 * 
 * @author Administrator
 * 
 */
public class AlarmSoundThread implements Runnable {

	private AlarmAudio alarmAudio = null;
	
	public AlarmSoundThread()
	{
		Thread.currentThread().setName("AlarmSoundThread");
	}

	@Override
	public void run() {
		long currTime = 0;
		List<AlarmVoiceInfo> voiceList = new ArrayList<AlarmVoiceInfo>();
		int oldUrgencyNum = 0;
		int oldMajorNum = 0;
		int oldMinorNum = 0;
		int oldWarningNum = 0;
		int count = 0;//告警声音响的次数,每响一次就增加1
		AlarmVoiceService_MB voiceService = null;
		try {
			voiceService = (AlarmVoiceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALARMVOICE);
			try {
				voiceList = voiceService.queryAllVoice();
				this.setAlarmTime(voiceList);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(voiceService);
			}
			Ptnf ptnf = Ptnf.getPtnf();
			while (Ptnf.getPtnf().isAlarmSoundSwitch()) {
				boolean urgencyFlag = false;
				boolean majorFlag = false;
				boolean minorFlag = false;
				boolean warningFlag = false;
				int newUrgencyNum = Integer.parseInt(ptnf.getLblToolAlarmUrgencyNum().getText());
				int newMajorNum = Integer.parseInt(ptnf.getLblToolAlarmMajorNum().getText());
				int newMinorNum = Integer.parseInt(ptnf.getLblToolAlarmMinorNum().getText());
				int newWarningNum = Integer.parseInt(ptnf.getLblToolAlarmPromptNum().getText());
				if (newUrgencyNum > 0 && ConstantUtil.URGENCY_TIME > 0) {
					if(oldUrgencyNum < newUrgencyNum){
						//说明有新告警上报
						alarmAudio = new AlarmAudio(ConstantUtil.URGENCYSOUNDPATH, ConstantUtil.URGENCY_TIME);
						urgencyFlag = true;
					}
					oldUrgencyNum = newUrgencyNum;
				} else if (newMajorNum > 0 && ConstantUtil.MAJOR_TIME > 0) {
					if(oldMajorNum < newMajorNum){
						alarmAudio = new AlarmAudio(ConstantUtil.MAJORSOUNDPATH, ConstantUtil.MAJOR_TIME);
						majorFlag = true;
					}
					oldMajorNum = newMajorNum;
				} else if (newMinorNum > 0 && ConstantUtil.MINOR_TIME > 0) {
					if(oldMinorNum < newMinorNum){
						alarmAudio = new AlarmAudio(ConstantUtil.MINORSOUNDPATH, ConstantUtil.MINOR_TIME);
						minorFlag = true;
					}
					oldMinorNum = newMinorNum;
				} else if (newWarningNum > 0 && ConstantUtil.WARNING_TIME > 0) {
					if(oldWarningNum < newWarningNum){
						alarmAudio = new AlarmAudio(ConstantUtil.WARNINGSOUNDPATH, ConstantUtil.WARNING_TIME);
						warningFlag = true;
					}
					oldWarningNum = newWarningNum;
				} else {
					alarmAudio = null;
				}

				if (null != alarmAudio) {
					if(count == 0 && (urgencyFlag || majorFlag || minorFlag || warningFlag)){
						currTime = System.currentTimeMillis();
					}
					int continueTime = alarmAudio.getAlarmContinueTime();
					//当持续时间为0的时候，说明是声音关闭状态，大于60小于300，说明是打开状态
					if(continueTime > 0){
						if(System.currentTimeMillis() <= (currTime+continueTime*1000)){
							alarmAudio.sound();
							count++;
						}else{
							count = 0;
						}
					}else if(continueTime == -1){
						//当持续时间为-1时，说明是没有进行声音开关设置，声音将一直开启
						alarmAudio.sound();
					}
				}else{
					count = 0;
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void setAlarmTime(List<AlarmVoiceInfo> voiceList) {
		if(voiceList.size() > 0){
			for (AlarmVoiceInfo voice : voiceList) {
				if(voice.getAlarmType() == 1 && voice.getAlarmSwitch() == 1){
					ConstantUtil.URGENCY_TIME = voice.getAlarmContinueTime();
					ConstantUtil.URGENCYSOUNDPATH = voice.getAlarmSoundPath();
				}
				if(voice.getAlarmType() == 2 && voice.getAlarmSwitch() == 1){
					ConstantUtil.MAJOR_TIME = voice.getAlarmContinueTime();
					ConstantUtil.MAJORSOUNDPATH = voice.getAlarmSoundPath();
				}
				if(voice.getAlarmType() == 3 && voice.getAlarmSwitch() == 1){
					ConstantUtil.MINOR_TIME = voice.getAlarmContinueTime();
					ConstantUtil.MINORSOUNDPATH = voice.getAlarmSoundPath();
				}
				if(voice.getAlarmType() == 4 && voice.getAlarmSwitch() == 1){
					ConstantUtil.WARNING_TIME = voice.getAlarmContinueTime();
					ConstantUtil.WARNINGSOUNDPATH = voice.getAlarmSoundPath();
				}
			}
		}
	}
}
