package com.nms.corba.ninterface.framework;

import org.apache.log4j.Logger;

import emsSession.EmsSession_I;
import emsSession.EmsSession_IHelper;

public class CorbaEmsSessionContainer {
	private static Logger LOG = Logger.getLogger(CorbaEmsSessionContainer.class.getName());

	private CorbaSession userSession = null;

	private EmsSession_I emsSession = null;

	EmsSessionImpl emsSessionImpl = null;

	void init(CorbaSession session) {
		userSession = session;
	}

	EmsSession_I getEmsSession() {
		return emsSession;
	}

	boolean startWork() {
		try {
			LOG.info("Create Corba Object EmsSession_I begin.");

			emsSessionImpl = new EmsSessionImpl(userSession);
			byte[] objId = userSession.getCorbaPOA().activateObject(
					emsSessionImpl);
			emsSession = EmsSession_IHelper.narrow(userSession
					.getCorbaPOA().getUserPOA().id_to_reference(objId));

			LOG.info("Create Corba Object EmsSession_I end.");
		} catch (Exception e) {
			LOG.error("Create Corba Obj unknown exception.");
			return false;
		}

		return true;
	}

	boolean stopWork() {
		try {
			LOG.info("stop work begin.");

			byte[] objId = userSession.getCorbaPOA().getUserPOA()
					.reference_to_id(emsSession);
			userSession.getCorbaPOA().deactivateObject(objId);

			emsSession = null;
			emsSessionImpl = null;
			userSession = null;

			LOG.info("stop work end.");
		} catch (Exception e) {
			LOG.error("stop work exception.");
			return false;
		}

		return true;
	}
}
