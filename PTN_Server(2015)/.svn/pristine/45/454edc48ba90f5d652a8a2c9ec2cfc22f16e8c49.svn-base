package com.nms.corba.ninterface.framework;

import org.apache.log4j.Logger;
import org.omg.CORBA.IntHolder;
import org.omg.CosEventComm.Disconnected;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;
import org.omg.CosNotifyChannelAdmin.ClientType;
import org.omg.CosNotifyChannelAdmin.ConsumerAdmin;
import org.omg.CosNotifyChannelAdmin.EventChannel;
import org.omg.CosNotifyChannelAdmin.EventChannelFactory;
import org.omg.CosNotifyChannelAdmin.InterFilterGroupOperator;
import org.omg.CosNotifyChannelAdmin.ProxyConsumer;
import org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumer;
import org.omg.CosNotifyChannelAdmin.StructuredProxyPushConsumerHelper;
import org.omg.CosNotifyChannelAdmin.SupplierAdmin;
import org.omg.CosNotifyFilter.FilterFactory;

public class CorbaEventPublisher {

	static Logger LOG = Logger.getLogger(CorbaEventPublisher.class.getName());
	private CorbaSession userSession = null;

	EventChannel userEventChannel = null;

	SupplierAdmin userSupplierAdmin = null;

	ConsumerAdmin userConsumerAdmin = null;

	StructuredProxyPushConsumer userConsumerProxy = null;

	FilterFactory userFilterFactory = null;

	boolean init(CorbaSession session) {
		LOG.info("init event channel publisher");

		userSession = session;
		try {
			EventChannelFactory eventChannelFactory = CorbaServer.getInstanse()
					.getEventChannelFactory();

			Property[] initialQoS = new Property[0];
			Property[] initialadmin = new Property[0];
			IntHolder channelId = new IntHolder();
			userEventChannel = eventChannelFactory.create_channel(initialQoS,
					initialadmin, channelId);

			IntHolder adminId = new IntHolder();
			userSupplierAdmin = userEventChannel.new_for_suppliers(
					InterFilterGroupOperator.AND_OP, adminId);
			userConsumerAdmin = userEventChannel.new_for_consumers(
					InterFilterGroupOperator.AND_OP, adminId);

			userFilterFactory = userEventChannel.default_filter_factory();

			IntHolder supplierProxyId = new IntHolder();
			ProxyConsumer proxy = userSupplierAdmin
					.obtain_notification_push_consumer(
							ClientType.STRUCTURED_EVENT, supplierProxyId);
			userConsumerProxy = StructuredProxyPushConsumerHelper.narrow(proxy);
		} catch (Exception e) {
			LOG.error("exception happen when init");
			return false;
		}

		return true;
	}

	boolean connectStructuredPushConsumer() {
		try {
			userConsumerProxy.connect_structured_push_supplier(null);
		} catch (Exception e) {
			LOG.error("activatePOAMgr exception");
			return false;
		}
		return true;
	}

	void disconnectStructuredPushConsumer() {
		try {
			userConsumerProxy.disconnect_structured_push_consumer();
		} catch (Exception e) {
			LOG.error("activatePOAMgr exception");
		}
	}

	public EventChannel getUserEventChannel() {
		return userEventChannel;
	}

	ConsumerAdmin getConsumerAdmin() {
		return userConsumerAdmin;
	}

	FilterFactory getFilterFactory() {
		return userFilterFactory;
	}

	boolean publishEvent(StructuredEvent event) {
		String strEventName = event.header.fixed_header.event_name;
		LOG.info("publish " + strEventName + " event to eventchanel begin");
		
		if (!userSession.getSessionState()) {
			LOG.info("failed to publish %s event to eventchanel because session is deactivate"
					+ strEventName);
			return false;
		}

		try {
			userConsumerProxy.push_structured_event(event);
		} catch (Disconnected e) {
			System.out.println("Disconnected exception:" + e.getMessage());
			LOG.error("exception happened when publish event");
			return false;
		} catch (Exception e) {
			System.out.println("happened exception :" + e.getMessage());
			LOG.error("exception happened when publish event");
			return false;
		}

		LOG.info("publish " + strEventName + " event to eventchanel end");

		return true;
	}
}
