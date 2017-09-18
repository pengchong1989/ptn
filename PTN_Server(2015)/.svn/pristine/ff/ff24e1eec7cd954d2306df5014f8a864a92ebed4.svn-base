package com.nms.corba.ninterface.framework;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.omg.CosNotifyChannelAdmin.EventChannelHolder;

import session.Session_I;

import common.Common_IHelper;
import common.Common_IHolder;

import emsSession.EmsSession_IPOA;
import emsSession.EmsSession_IPackage.managerNames_THolder;
import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

public class EmsSessionImpl extends EmsSession_IPOA {
	private static Logger LOG = Logger
			.getLogger(EmsSessionImpl.class.getName());
	private CorbaSession userSession = null;

	public EmsSessionImpl(CorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void getSupportedManagers(managerNames_THolder supportedManagerList)
			throws ProcessingFailureException {
		Set managerNameSet = CorbaManagerObjMgr.getInstanse().getManagerNames();
		if (managerNameSet == null) {
			LOG.error("getSupportedManagers] mangerNameSet is null");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		java.lang.String[] objArray = new String[managerNameSet.size()];
		int i = 0;
		for (Iterator iter = managerNameSet.iterator(); iter.hasNext();) {
			objArray[(i++)] = ((String) iter.next());
		}

		supportedManagerList.value = objArray;
	}

	@Override
	public void getManager(String managerName, Common_IHolder managerInterface)
			throws ProcessingFailureException {
		LOG.info("[getManager] begin.");

		if (null == this.userSession) {
			LOG.error("[getManager] userSession is null.");
			return;
		}

		Object obj = this.userSession.getCorbaObjContainer()
				.getCorbaServantByName(managerName);

		if (null == obj) {
			LOG.error("[getManager] obj is null.");
			return;
		}

		managerInterface.value = Common_IHelper.narrow((org.omg.CORBA.Object) obj);

		LOG.info("[getManager] end.");
	}

	@Override
	public void getEventChannel(EventChannelHolder eventChannel2)
			throws ProcessingFailureException {
	    LOG.info("[getEventChannel] begin.");

	    if (null == this.userSession) {
	      LOG.error("[getEventChannel] userSession is null.");
	      return;
	    }

	    eventChannel2.value = this.userSession.getCorbaEventPublisher().getUserEventChannel();

	    LOG.info("[getEventChannel] end.");
	}

	@Override
	public Session_I associatedSession() {
		return null;
	}

	@Override
	public void ping() {
		LOG.info("NMS ping successfully.");
	}

	@Override
	public void endSession() {
		LOG.info("[endSession] begin.");

		if (null == userSession) {
			LOG.error("[endSession] userSession is null.");
			return;
		}

		CorbaSessionMgr.getInstanse().destroyEmsSession(userSession);

		LOG.info("[endSession] end.");

	}

}
