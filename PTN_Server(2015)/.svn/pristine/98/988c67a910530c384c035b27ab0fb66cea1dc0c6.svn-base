package com.nms.corba.ninterface.impl.notification.cvthander;

import notifications.ObjectType_T;

import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.resource.tool.CorbaAlarmTool;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.service.notify.Message;

public class Alarm_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_AID;
	}
	
	protected void convertAlarm2Event(Message msg, StructuredEvent idlEvent) {
		CurrentAlarmInfo alarm = (CurrentAlarmInfo) msg.getMsgBody();
		CorbaAlarmTool.convertAlarm2Event(alarm,idlEvent);
	}	
}
