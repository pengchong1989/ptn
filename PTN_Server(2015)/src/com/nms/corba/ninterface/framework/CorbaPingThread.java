package com.nms.corba.ninterface.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

import nmsSession.NmsSession_I;

import org.apache.log4j.Logger;
import org.omg.CORBA.ORB;
import org.omg.CosNotification.EventHeader;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.ui.manager.ExceptionManage;

public class CorbaPingThread extends Thread {

	private static Logger LOG = Logger.getLogger(CorbaPingThread.class
			.getName());

	private CorbaSession userSession = null;

	private NmsSession_I userNmsSession = null;

	private int iSessionId = -1;

	private long nSumTime = 0L;

	private int nPingEnableFail = 0;

	private boolean bPing = true;

	private int nHeartEnableFail = 0;

	private String strIP = "";

	private int iPort = 0;

	private boolean bHeart = true;

	private String m_IORString = "";

	void init(CorbaSession session, NmsSession_I nmsSession) {
		this.userSession = session;
		this.userNmsSession = nmsSession;
		this.iSessionId = session.getSessionId();

		if (objectParser(nmsSession)) {

			LOG.info("nms session " + this.iSessionId + "IOR: " + getIOR());
		}
	}

	private String getIOR() {

		return m_IORString;
	}

	public Boolean objectParser(org.omg.CORBA.Object obj) {
		ORB corbaOrb = CorbaServer.getInstanse().getORB();
		if ((obj == null) || (corbaOrb == null)) {
			LOG.error("orb is null or corbaOrb is null");
			return false;
		}

		m_IORString = corbaOrb.object_to_string(obj);
		if (m_IORString.isEmpty()) {
			LOG.error("m_IORString.isEmpty");
			return false;
		}

		return true;
	}

	public void run() {
		if (null == this.userSession) {
			return;
		}

		try {
			sleep(5000L);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		}

		while (this.userSession.getSessionState()) {
			try {
				sleep(1000L);
			} catch (Exception e1) {
				ExceptionManage.dispose(e1,CorbaPingThread.class);
			}

			this.nSumTime += 1L;

			int pingInterval = CorbaConfig.getInstanse().getPingInterval();
			if (0 != pingInterval) {
				if (this.nSumTime % pingInterval == 0L) {
					try {
						this.userNmsSession.ping();
						this.bPing = true;
						this.nPingEnableFail = 0;
						LOG.info("[Ping]: NBI ping NMS" + this.iSessionId + "("
								+ this.strIP + ":" + this.iPort
								+ ") successfully.");
					} catch (Exception e) {
						LOG.error("[Ping]: NBI ping NMS failed");
						LOG.error("[Ping]: sessionId: " + this.iSessionId + "("
								+ this.strIP + ":" + this.iPort
								+ ") Ping exception.");

						if (!this.bPing) {
							this.nPingEnableFail += 1;
						}

						this.bPing = false;
						int pingEnableFail = CorbaConfig.getInstanse()
								.getPingEnableFailTimes();
						if (this.nPingEnableFail >= pingEnableFail - 1) {
							LOG.info("[run]ping failed exceed "
									+ pingEnableFail + " times, "
									+ "destroyEmsSession "
									+ this.userSession.getSessionId());
							CorbaSessionMgr.getInstanse().destroyEmsSession(
									this.userSession);

							return;
						}
					}
				}
			}
			int beatHeartInterVal = CorbaConfig.getInstanse()
					.getHeartBeatInterval();
			if (0 != beatHeartInterVal) {
				if (this.nSumTime % beatHeartInterVal == 0L) {
					try {
						pushHeartBeat();

						this.bHeart = true;
						this.nHeartEnableFail = 0;
					} catch (Exception e) {
						LOG.error("sessionId:" + this.iSessionId + "("
								+ this.strIP + ":" + this.iPort
								+ ") PushHeartBeat exception.");

						if (!this.bHeart) {
							this.nHeartEnableFail += 1;
						}
						int iHeartEnableFailTimes = CorbaConfig.getInstanse()
								.getHeartEnableFailedTimes();
						if (this.nHeartEnableFail >= iHeartEnableFailTimes - 1) {
							LOG.info("[run]send heartbeart failed exceed "
									+ iHeartEnableFailTimes + " times");
						}
					}
				}
			}
		}
		LOG.info("sessionId:" + this.iSessionId + "(" + this.strIP + ":"
				+ this.iPort + ") Ping Task exit. ");
	}

	private void pushHeartBeat() {
		if (null == this.userSession) {
			LOG.error("[pushHeartBeat]userSession is null.");
			return;
		}

		StructuredEvent event = new StructuredEvent();

		event.header = new EventHeader();
		event.header.fixed_header = new FixedEventHeader();
		event.header.fixed_header.event_name = new String("");
		event.header.fixed_header.event_type = new EventType();
		event.header.fixed_header.event_type.domain_name = new String(
				"tmf_mtnm");
		event.header.fixed_header.event_type.type_name = new String(
				"NT_HEARTBEAT");

		event.header.variable_header = new Property[0];

		event.filterable_data = new Property[3];
		event.filterable_data[0] = new Property();
		event.filterable_data[0].name = new String("systemName");
		event.filterable_data[0].value = CorbaServer.getInstanse().createAny();
		event.filterable_data[0].value.insert_string(CorbaConfig.getInstanse()
				.getCorbaEmsName());

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strCurrentTime = sdf.format(date);
		event.filterable_data[1] = new Property();
		event.filterable_data[1].name = new String("sendTime");
		event.filterable_data[1].value = CorbaServer.getInstanse().createAny();
		event.filterable_data[1].value.insert_string(strCurrentTime);

		event.filterable_data[2] = new Property();
		event.filterable_data[2].name = new String("sendInterVal");
		event.filterable_data[2].value = CorbaServer.getInstanse().createAny();
		event.filterable_data[2].value.insert_short((short) CorbaConfig
				.getInstanse().getHeartBeatInterval());
		 
		event.remainder_of_body = CorbaServer.getInstanse().createAny();
		if (!this.userSession.getCorbaEventPublisher().publishEvent(event)) {
			LOG.error("[pushHeartBeat] failed");
			return;
		}

		LOG.info("[pushHeartBeat] successfully");
	}

}
