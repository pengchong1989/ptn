package com.nms.snmp.ninteface.framework.trap;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

public class SnmpNotifyTask implements Runnable {
	private static Logger LOG = Logger.getLogger(SnmpNotifyTask.class.getName());
	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue(); 

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Message msg = (Message) this.messageQueue.take();
//				LOG.info("Notify begin! msgType--" + msg.getMsgType());
				DispatchAndTrapMsg(msg);
//				LOG.info("Notify end! msgObjName--" + msg.getMsgObjName());
			} catch (InterruptedException e) {
				LOG.error("waking from take");
			} catch (Exception e) {
				LOG.error("Notify Exception!");
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		LOG.error("Notify Thread has dead!");
	}

	public void submitTask(Message msg) {
		try {
			this.messageQueue.put(msg);
		} catch (InterruptedException e) {
			LOG.error("waking from submittask");
		}
	}

	private void DispatchAndTrapMsg(Message msg) {
		INotifyHandler handler = SnmpNotifyHanderMgr.getInstance().getHandler(msg.getMsgObjName());
		if (handler != null) {
			handler.handleAndTrapMsg(msg);
		}
	}
}
