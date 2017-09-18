package com.nms.corba.ninterface.framework.notify;

import com.nms.service.notify.INotifyListener;
import com.nms.service.notify.Message;

public class CorbaNotifyListener implements INotifyListener{

	@Override
	public void onMessage(Message msg) {
		CorbaNotifyMgr.getInstance().Dispatch(msg);
	}

}
