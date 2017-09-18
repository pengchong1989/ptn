package com.nms.snmp.ninteface.framework.trap;

import com.nms.service.notify.Message;


public interface INotifyHandler {
	public void handleAndTrapMsg(Message msg);
}
