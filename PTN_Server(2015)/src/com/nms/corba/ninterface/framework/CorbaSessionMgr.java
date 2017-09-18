package com.nms.corba.ninterface.framework;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import nmsSession.NmsSession_I;

import org.apache.log4j.Logger;

import emsSession.EmsSession_I;

public class CorbaSessionMgr {

	static Logger LOG = Logger.getLogger(CorbaSessionMgr.class.getName());
	private static CorbaSessionMgr instance = new CorbaSessionMgr();
	private int iSessionId;
	private Set<CorbaSession> corbaSessionSet = new HashSet();
	
	private CorbaSessionMgr() {

	}
	public static CorbaSessionMgr getInstanse() {
		return instance;
	}

	private synchronized int gernarateSessionId() {
		return this.iSessionId++;
	}

	public EmsSession_I createEmsSession(String strUserName, NmsSession_I nmsSession) {
		LOG.info("[createEmsSession]begin.");
		CorbaSession session = new CorbaSession();

		session.setSessionId(gernarateSessionId());
		session.SetUserName(strUserName);

		if (!session.init(nmsSession)) {
			LOG.error("[createEmsSession]corbaSession init failed.");
			return null;
		}

		if (!session.startWork()) {
			LOG.error("[createEmsSession]session startwork failed.");
			session.stopWork();
			return null;
		}

		registerSession(session);

		LOG.info("[createEmsSession]end.");

		return session.getEmsSessionContainer().getEmsSession();
	}

	synchronized void registerSession(CorbaSession session) {
		corbaSessionSet.add(session);
	}

	synchronized void unRegisterSession(CorbaSession session) {
		corbaSessionSet.remove(session);
	}

	public void destroyEmsSession(CorbaSession session) {
		LOG.info("[destroyEmsSession]begin");
		unRegisterSession(session);
		session.stopWork();
		LOG.info("[destroyEmsSession]end");
	}

	public synchronized Set<CorbaSession> getUserSessions() {
		Set resultSet = new HashSet();

		Iterator iter = this.corbaSessionSet.iterator();
		while (iter.hasNext()) {
			resultSet.add(iter.next());
		}

		return resultSet;
	}

	synchronized int getUserSessionCount() {
		return this.corbaSessionSet.size();
	}

	public synchronized boolean isUserOnline(String strUserName) {
		Iterator iter = this.corbaSessionSet.iterator();
		while (iter.hasNext()) {
			if (((CorbaSession) iter.next()).getUserName() == strUserName) {
				return true;
			}
		}
		return false;
	}
}
