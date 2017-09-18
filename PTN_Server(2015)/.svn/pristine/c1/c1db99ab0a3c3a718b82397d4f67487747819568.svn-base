package com.nms.corba.ninterface.framework;

import org.apache.log4j.Logger;
import org.omg.CORBA.ORB;

import com.nms.ui.manager.ExceptionManage;

public class OrbThread extends Thread {

	private static Logger ThreadLog = Logger.getLogger(OrbThread.class.getName());

	private ORB orb = null;

	OrbThread(ORB threadOrb) {
		this.orb = threadOrb;
	}

	public void run() {
		try {
			ThreadLog.info("run begin.");
			this.orb.run();
			ThreadLog.info("run end.");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			ThreadLog.error("orb run exception");
		}
	}
}