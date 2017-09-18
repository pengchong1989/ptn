package com.nms.corba.ninterface.framework.notify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.nms.service.notify.Message;
import com.nms.service.notify.NotifyPublisher;

public class CorbaNotifyMgr {

	private static final Logger LOG = Logger.getLogger(CorbaNotifyMgr.class.getName());
	
	private static CorbaNotifyMgr instance = new CorbaNotifyMgr();
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(2);

	private CorbaNotifyTask alarmTask = new CorbaNotifyTask();

	private CorbaNotifyTask resCDUTask = new CorbaNotifyTask();
	
	private CorbaNotifyListener listener = new CorbaNotifyListener();
	
	public static CorbaNotifyMgr getInstance() {
		return instance;
	}
	
	private CorbaNotifyMgr() {
		
	}

	public void init() {
		LOG.info("CorbaNotifyMgr init begin!");
		registerListener();
		exec.submit(this.alarmTask);
		Thread resCDUThread = new Thread(this.resCDUTask);
		resCDUThread.setName("NotifyCreateDelModifyThread");
		resCDUThread.start();
		LOG.info("CorbaNotifyMgr init end!");
	}

	private void registerListener() {
		NotifyPublisher.getInstance().register(listener);
	}

	public void Dispatch(Message msg) {
		submitTask(msg);
	}

	private void submitTask(Message msg) {
		switch (msg.getMsgType()) {
		case CREATION:
		case DELETION:
		case ATTRIBUTECHG:
		case STATECHG:
			this.resCDUTask.submitTask(msg);
			break;
		default:
			this.alarmTask.submitTask(msg);
			break;
		}
	}
}
