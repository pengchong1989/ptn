package com.nms.snmp.ninteface.framework.trap;

import com.nms.service.notify.INotifyListener;
import com.nms.service.notify.Message;


public class SnmpNotifyListener implements INotifyListener{

	@Override
	public void onMessage(Message msg) {
		SnmpNotifyMgr.getInstance().Dispatch(msg);
	}
}
