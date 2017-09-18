package com.nms.snmp.ninteface.framework.trap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.nms.service.notify.Message;
import com.nms.service.notify.NotifyPublisher;
import com.nms.snmp.ninteface.framework.Agent;
import com.nms.snmp.ninteface.framework.SnmpConfig;

public class SnmpNotifyMgr {

	private static final Logger LOG = Logger.getLogger(SnmpNotifyMgr.class.getName());
	
	private static SnmpNotifyMgr instance = new SnmpNotifyMgr();
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(2);

	private SnmpNotifyTask alarmTask = new SnmpNotifyTask();

	private SnmpNotifyTask resCDUTask = new SnmpNotifyTask();
	private HeartBeatThread  heartBeat=null;
	
	private SnmpNotifyListener listener = new SnmpNotifyListener();
	private Agent agent = null;
	public static SnmpNotifyMgr getInstance() {
		return instance;
	}
	
	private SnmpNotifyMgr() {
		
	}

	public void init(Agent agent) {
		this.agent = agent;
		SnmpConfig.getInstanse().init();
//		agent.init();
		SnmpNotifyHanderMgr.getInstance().init();
		registerListener();
		exec.submit(this.alarmTask);
		Thread resCDUThread = new Thread(this.resCDUTask);
		resCDUTask.submitTask(new Message());
		resCDUThread.setName("NotifyCreateDelModifyThread");
		resCDUThread.start();
	    heartBeat= new HeartBeatThread();
	}

	public void start(){
	    heartBeat.start();
	}
	
	public Agent getAgent(){
	    return agent;
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
