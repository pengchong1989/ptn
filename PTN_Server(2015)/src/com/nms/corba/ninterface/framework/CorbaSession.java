package com.nms.corba.ninterface.framework;

import nmsSession.NmsSession_I;

import org.apache.log4j.Logger;

public class CorbaSession extends ICorbaSession{

	private static Logger LOG = Logger.getLogger(CorbaSession.class.getName());

	private int iSessionId = -1;

	private boolean bSessionState = false;

	private CorbaPingThread pingThread = null;

	private CorbaUserPOA userPoa = null;

	private CorbaServantMgr userCorbaObjContainer = null;

	private CorbaEmsSessionContainer userCorbaEmsSessionContainer = null;

	private CorbaEventPublisher userEventPublisher = null;

	boolean init(NmsSession_I nmsSession) {
		LOG.info("Init sessionPOA begin.");
		userPoa = new CorbaUserPOA();
		if (!userPoa.init(CorbaServer.getInstanse().getUserPoaName())) {
			LOG.error("Init m_SessionPOA failed.");
			return false;
		}
		LOG.info("Init sessionPOA end.");

		LOG.info("Init corba ems session container begin.");
		userCorbaEmsSessionContainer = new CorbaEmsSessionContainer();
		userCorbaEmsSessionContainer.init(this);
		LOG.info("Init corba ems session container end.");

		LOG.info("Init corba obj container begin.");
		userCorbaObjContainer = new CorbaServantMgr();
		userCorbaObjContainer.init(this);
		LOG.info("Init corba obj container end.");

		LOG.info("Corba event channel begin.");
		userEventPublisher = new CorbaEventPublisher();
		if (!userEventPublisher.init(this)) {
			LOG.error("Init m_CorbaEventPublisher failed.");
			return false;
		}
		LOG.info("Corba event channel end.");

		LOG.info("Init pingTask begin.");
		pingThread = new CorbaPingThread();
		pingThread.init(this, nmsSession);
		LOG.info("Init pingTask end.");

		return true;
	}

	public CorbaUserPOA getCorbaPOA() {
		return userPoa;
	}

	CorbaEmsSessionContainer getEmsSessionContainer() {
		return userCorbaEmsSessionContainer;
	}

	public CorbaEventPublisher getCorbaEventPublisher() {
		return userEventPublisher;
	}

	public CorbaServantMgr getCorbaObjContainer() {
		return userCorbaObjContainer;
	}

	int getSessionId() {
		return iSessionId;
	}

	void setSessionId(int id) {
		iSessionId = id;
	}

	boolean getSessionState() {
		return bSessionState;
	}

	boolean startWork() {
		LOG.info("[startWork]corbaEmsSessionContainer StartWork begin.");
		if (!userCorbaEmsSessionContainer.startWork()) {
			LOG.error("[startWork]corbaEmsSessionContainer startWork failed.");
			return false;
		}
		LOG.info("[startWork]corbaEmsSessionContainer StartWork end.");

		LOG.info("[startWork]corba obj contaner start work begin.");
		if (!userCorbaObjContainer.startWork()) {
			LOG.error("[startWork]corba obj contaner start work failed.");
			return false;
		}
		LOG.info("[startWork]corba obj contaner start work end.");

		LOG.info("[startWork]corbaEventPublisher connectStructuredPushConsumer begin.");
		if (!userEventPublisher.connectStructuredPushConsumer()) {
			LOG.error("[startWork]corbaEventPublisher connectStructuredPushConsumer failed.");
			return false;
		}
		LOG.info("[startWork]corbaEventPublisher connectStructuredPushConsumer end.");

		LOG.info("[startWork]userPoa activatePOAMgr begin.");
		if (!userPoa.activatePOAMgr()) {
			LOG.error("[startWork]sessionPOA activatePOAMgr failed.");
			return false;
		}
		LOG.info("[startWork]userPoa activatePOAMgr end.");

		LOG.info("[startWork]session StartWork begin.");
		bSessionState = true;
		LOG.info("[startWork]session StartWork end.");

		LOG.info("[startWork]pingTask StartWork begin.");
		pingThread.start();
		LOG.info("[startWork]pingTask StartWork end.");
		
		return true;
	}

	boolean stopWork() {
		LOG.info("[stopWork]Corba Session StopWork begin.");
		bSessionState = false;
		LOG.info("[stopWork]Corba Session StopWork end.");

		LOG.info("[stopWork]sessionPOA StopWork begin.");
		userPoa.deactivatePOAMgr();
		LOG.info("[stopWork]sessionPOA StopWork end.");

		LOG.info("[stopWork]userEventPublisher StopWork begin.");
		userEventPublisher.disconnectStructuredPushConsumer();
		LOG.info("[stopWork]userEventPublisher StopWork end.");

		LOG.info("[stopWork]corbaEmsSessionContainer stopWork begin.");
		if (!userCorbaEmsSessionContainer.stopWork()) {
			LOG.error("[stopWork]corbaEmsSessionContainer stopWork failed.");
		}
		LOG.info("[stopWork]corbaEmsSessionContainer stopWork end.");

		LOG.info("[stopWork]corba obj contaner stopWork begin.");
		if (!userCorbaObjContainer.stopWork()) {
			LOG.error("[stopWork]corba obj contaner stopWork failed.");
		}
		LOG.info("[stopWork]corba obj contaner stopWork end.");

		pingThread = null;
		userPoa = null;
		userCorbaObjContainer = null;
		userCorbaEmsSessionContainer = null;
		userEventPublisher = null;

		return true;
	}
}
