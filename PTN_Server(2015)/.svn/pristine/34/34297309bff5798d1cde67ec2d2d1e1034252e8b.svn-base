package com.nms.corba.ninterface.framework.notify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaSessionAcessTool;
import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

public class CorbaNotifyTask implements Runnable {

	private static Logger LOG = Logger.getLogger(CorbaNotifyTask.class.getName());

	private BlockingQueue<Message> messageQueue = new LinkedBlockingQueue();

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Message msg = (Message) this.messageQueue.take();
				LOG.info("Notify begin! msgType--" + msg.getMsgType());

				StructuredEvent corbaEvent = convert2CorbaEvent(msg);
				if (corbaEvent == null) {
					LOG.debug("Skip a message, because the convert2CorbaEvent result is null.");
					continue;
				}
				CorbaSessionAcessTool.BroadcastPublishEvent(corbaEvent);
				LOG.info("Notify end! msgObjName--" + msg.getMsgObjName());

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

	private StructuredEvent convert2CorbaEvent(Message msg) {

		INotifyCvtHandler convertHandler = CorbaNotifyHanderMgr.getInstance().getConvertHandler(msg.getMsgObjName());
		StructuredEvent event = null;
		if (convertHandler != null) {
			event = convertHandler.convert(msg);
		}

		return event;
	}
}
