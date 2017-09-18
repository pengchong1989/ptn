package com.nms.corba.ninterface.impl;

import nmsSession.NmsSession_IPOA;
import session.Session_I;

public class NmsSessionImpl extends NmsSession_IPOA {

	@Override
	public void eventLossOccurred(String startTime, String notificationId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eventLossCleared(String endTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alarmLossOccurred(String startTime, String notificationId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Session_I associatedSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ping() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endSession() {
		// TODO Auto-generated method stub

	}

}
