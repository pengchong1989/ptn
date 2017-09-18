package com.nms.corba.ninterface.framework;

import java.util.Iterator;
import java.util.Set;

import org.omg.CosNotification.StructuredEvent;
import org.omg.CosNotifyChannelAdmin.ConsumerAdmin;
import org.omg.CosNotifyFilter.FilterFactory;
import org.omg.PortableServer.POA;

import com.nms.ui.manager.ExceptionManage;

public class CorbaSessionAcessTool {
	public static ConsumerAdmin getConsumerAdmin(ICorbaSession userSession) {
		if (null == userSession) {
			return null;
		}
		return ((CorbaSession) userSession).getCorbaEventPublisher()
				.getConsumerAdmin();
	}

	public static FilterFactory getFilterFacotry(ICorbaSession userSession) {
		if (null == userSession) {
			return null;
		}
		return ((CorbaSession) userSession).getCorbaEventPublisher()
				.getFilterFactory();
	}

	public static POA getUserPOA(ICorbaSession userSession) {
		if (null == userSession) {
			return null;
		}

		CorbaSession session = (CorbaSession) userSession;
		return session.getCorbaPOA().getUserPOA();
	}

	public static void BroadcastPublishEvent(StructuredEvent event) {
		Set sessionsSet = CorbaSessionMgr.getInstanse().getUserSessions();
		if (null == sessionsSet)
			return;
		try {
			Iterator iter = null;
			for (iter = sessionsSet.iterator(); iter.hasNext();)
				((CorbaSession) iter.next()).getCorbaEventPublisher()
						.publishEvent(event);
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaSessionAcessTool.class);
		}
	}

	public static void publishEvent(ICorbaSession userSession,
			StructuredEvent event) {
		if (null == userSession)
			return;
		try {
			((CorbaSession) userSession).getCorbaEventPublisher().publishEvent(
					event);
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaSessionAcessTool.class);
		}
	}
}
