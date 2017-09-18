package com.nms.jms.serviceCourse;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.nms.drive.service.bean.AlarmObject;
import com.nms.jms.common.OpviewMessage;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.alarm.AlarmTools;


/**
 * 服务进程监听
 * @author Administrator
 *
 */
public class CopyOfServiceCourseMessageListener implements MessageListener{
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
			ObjectMessage objectMessage = (ObjectMessage) message;
			OpviewMessage opviewMessage = null;
			try {
				if(objectMessage.getObject() instanceof OpviewMessage){
					opviewMessage = (OpviewMessage) objectMessage.getObject();
					if(opviewMessage.getObject() != null && opviewMessage.getMessageSource().equals("Alarm")){//告警分支
						AlarmTools alarmTools = new AlarmTools();
						List<AlarmObject> alarms = (List<AlarmObject>) opviewMessage.getObject();
						try {
							alarmTools.convertAlarm(alarms);
						} catch (Exception e) {
							ExceptionManage.dispose(e, CopyOfServiceCourseMessageListener.class);
						}
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
