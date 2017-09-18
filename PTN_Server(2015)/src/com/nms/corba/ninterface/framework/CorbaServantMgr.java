package com.nms.corba.ninterface.framework;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.omg.PortableServer.Servant;

import com.nms.ui.manager.ExceptionManage;

public class CorbaServantMgr {
	private static Logger LOG = Logger.getLogger(CorbaServantMgr.class
			.getName());

	private CorbaSession userSession = null;

	private HashMap<String, byte[]> corbaServantOidMap = new HashMap();

	private HashMap<String, Servant> corbaServantMap = new HashMap();

	void init(CorbaSession session) {
		userSession = session;
	}

	public org.omg.CORBA.Object getCorbaServantByName(String strManagerName) {
		LOG.info("[getCorbaServantByName] get " + strManagerName + " begin.");

		if (null == userSession) {
			LOG.error("[getCorbaServantByName] userSession is null.");
			return null;
		}
		try {
			byte[] oid = (byte[]) corbaServantOidMap.get(strManagerName);
			if (null == oid) {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT, strManagerName
								+ "is not implement or wrong manger name");
			}

			org.omg.CORBA.Object obj = userSession.getCorbaPOA()
					.getUserPOA().id_to_reference(oid);

			LOG.info("[getCorbaServantByName] get " + strManagerName + " end.");
			return obj;
		} catch (Exception e) {
			LOG.error("create manger " + strManagerName + " exception.");
		}
		return null;
	}

	boolean startWork() {
		LOG.info("[startWork]begin.");
		if (!createCorbaServants()) {
			LOG.error("[startWork] failed.");
			return false;
		}
		LOG.info("[startWork]end.");
		return true;
	}

	boolean stopWork() {
		LOG.info("[stopWork]begin.");
		releaseCorbaObject();
		LOG.info("[stopWork]end.");
		return true;
	}

	void releaseCorbaObject() {
		LOG.info("[releaseCorbaObject]begin.");
		try {
			for (Iterator iter = corbaServantOidMap.keySet().iterator(); iter
					.hasNext();) {
				userSession.getCorbaPOA().deactivateObject(
						(byte[]) corbaServantOidMap.get(iter.next()));
			}

			corbaServantOidMap.clear();
			corbaServantOidMap = null;

			corbaServantMap.clear();
			corbaServantMap = null;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("[releaseCorbaObject] exception.");
		}

		LOG.info("[releaseCorbaObject]end.");
	}

	boolean createCorbaServants() {
		LOG.info("[createCorbaServants]begin.");

		if (null == userSession) {
			LOG.error("[createCorbaServants]session is null.");
			return false;
		}

		Set managerNameSet = CorbaManagerObjMgr.getInstanse()
				.getManagerNames();
		try {
			for (Iterator iter = managerNameSet.iterator(); iter.hasNext();) {
				String strManagerName = (String) iter.next();
				Servant servant = CorbaManagerObjMgr.getInstanse()
						.CreateCorbaObj(userSession, strManagerName);

				byte[] oid = userSession.getCorbaPOA().activateObject(
						servant);
				if (null == oid) {
					LOG.error("[createCorbaServants] oid is null.");
					return false;
				}

				corbaServantOidMap.put(strManagerName, oid);
				corbaServantMap.put(strManagerName, servant);

				LOG.info("[createCorbaServants]create Corba servant with manager name "
						+ strManagerName + " succssfully.");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.info("[createCorbaServants]Create corba servant exception.");
			return false;
		}

		LOG.info("[createCorbaServants]end.");

		return true;
	}
}
