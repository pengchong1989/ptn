package com.nms.corba.ninterface.impl.notification;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.omg.CORBA.IntHolder;
import org.omg.CosEventChannelAdmin.AlreadyConnected;
import org.omg.CosEventChannelAdmin.TypeError;
import org.omg.CosNotifyChannelAdmin.AdminLimitExceeded;
import org.omg.CosNotifyChannelAdmin.ClientType;
import org.omg.CosNotifyChannelAdmin.ConnectionAlreadyActive;
import org.omg.CosNotifyChannelAdmin.ConnectionAlreadyInactive;
import org.omg.CosNotifyChannelAdmin.ConsumerAdmin;
import org.omg.CosNotifyChannelAdmin.NotConnected;
import org.omg.CosNotifyChannelAdmin.ProxySupplier;
import org.omg.CosNotifyChannelAdmin.StructuredProxyPushSupplier;
import org.omg.CosNotifyChannelAdmin.StructuredProxyPushSupplierHelper;
import org.omg.CosNotifyChannelAdmin.StructuredProxyPushSupplierHolder;
import org.omg.CosNotifyComm.StructuredPushConsumer;
import org.omg.CosNotifyFilter.ConstraintExp;
import org.omg.CosNotifyFilter.ConstraintExpSeqHolder;
import org.omg.CosNotifyFilter.Filter;
import org.omg.CosNotifyFilter.FilterFactory;
import org.omg.CosNotifyFilter.InvalidConstraint;
import org.omg.CosNotifyFilter.InvalidGrammar;

import subscription.EMSSubscriptionMgr_IPOA;
import subscription.SubscriptionState_T;
import subscription.SubscriptionState_THolder;

import com.nms.corba.ninterface.framework.CorbaSessionAcessTool;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;
import common.CapabilityList_THolder;

public class EMSSubscriptionMgr_Impl extends EMSSubscriptionMgr_IPOA {
	private static Logger LOG = Logger.getLogger(EMSSubscriptionMgr_Impl.class
			.getName());
	private ICorbaSession userSession = null;

	private HashMap<Integer, SubscriptionInfo> subscriptionInfoMap = new HashMap();

	private int iSubscribeId = 0;

	public EMSSubscriptionMgr_Impl(ICorbaSession session) {
		userSession = session;
	}

	@Override
	public boolean subscribe(StructuredPushConsumer subscriber,
			ConstraintExp[] filterList, IntHolder subscriptionId,
			StructuredProxyPushSupplierHolder supplierRef)
			throws ProcessingFailureException {
		LOG.info("[subscribe]begin.");

		if (isRegistered(subscriber)) {
			LOG.error("[subscribe]subscriber has aready subscribed.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriber has aready subscribed.");
		}

		String strFilter = "filterList is : ";
		for (int i = 0; i < filterList.length; i++) {
			strFilter = strFilter + filterList[i].constraint_expr;
			strFilter = strFilter + ";";
		}
		LOG.info("[subscribe]Input filterList is " + strFilter);

		StructuredProxyPushSupplier structProxySupplier = null;
		ConsumerAdmin consumerAdmin = CorbaSessionAcessTool
				.getConsumerAdmin(userSession);

		if (null == consumerAdmin) {
			LOG.error("[getSubscriptionStatus]consumerAdmin is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		IntHolder proxyId = new IntHolder();
		ProxySupplier proxySupplier = null;
		try {
			proxySupplier = consumerAdmin.obtain_notification_push_supplier(
					ClientType.STRUCTURED_EVENT, proxyId);
		} catch (AdminLimitExceeded e) {
			LOG.error("[getSubscriptionStatus]obtain_notification_push_supplier failed."
					+ e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		if (null == proxySupplier) {
			LOG.error("[getSubscriptionStatus]proxySupplier is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		structProxySupplier = StructuredProxyPushSupplierHelper
				.narrow(proxySupplier);

		FilterFactory filterFactory = CorbaSessionAcessTool
				.getFilterFacotry(userSession);
		if (null == filterFactory) {
			LOG.error("[subscribe]filterFactory is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		Filter filter = null;
		try {
			filter = filterFactory.create_filter("EXTENDED_TCL");
		} catch (InvalidGrammar e) {
			LOG.error("[subscribe]create_filter factory with invalid grammar EXTENDED_TCL");
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		if (null == filter) {
			LOG.error("[getSubscriptionStatus]filter is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		try {
			filter.add_constraints(filterList);
		} catch (InvalidConstraint e) {
			LOG.error("[subscribe]InvalidConstraint" + e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		structProxySupplier.add_filter(filter);
		try {
			structProxySupplier.connect_structured_push_consumer(subscriber);
		} catch (AlreadyConnected e) {
			LOG.error("[subscribe]AlreadyConnected" + e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		} catch (TypeError e) {
			LOG.error("[subscribe]TypeError" + e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		SubscriptionInfo subcriptionInfo = new SubscriptionInfo();
		subcriptionInfo.consumer = subscriber;
		subcriptionInfo.supplierProxy = structProxySupplier;
		subcriptionInfo.state = SubscriptionState_T.SS_WORKING;
		subcriptionInfo.filter = filterList;
		subscriptionId.value = register(subcriptionInfo);

		LOG.info("[subscribe]Subscribe id is " + subscriptionId.value);

		supplierRef.value = structProxySupplier;

		LOG.info("[subscribe]end.");
		return true;
	}

	@Override
	public boolean unsubscribe(int subscriptionId)
			throws ProcessingFailureException {
		LOG.info("[unsubscribe]begin.");
		LOG.info("[unsubscribe]subscriptionId is " + subscriptionId);

		SubscriptionInfo subcribeInfo = getSubcribeInfo(subscriptionId);
		if (null == subcribeInfo) {
			LOG.error("[unsubscribe]subscriptionId is not valid id");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriptionId is not valid id.");
		}

		subcribeInfo.supplierProxy.remove_all_filters();
		subcribeInfo.supplierProxy.disconnect_structured_push_supplier();

		unRegister(subscriptionId);
		LOG.info("[unsubscribe]end.");
		return true;
	}

	@Override
	public boolean suspendSubscription(int subscriptionId)
			throws ProcessingFailureException {
		LOG.info("[suspendSubscription]begin.");
		LOG.info("[suspendSubscription]subscriptionId is " + subscriptionId);

		SubscriptionInfo subcribeInfo = getSubcribeInfo(subscriptionId);
		if (null == subcribeInfo) {
			LOG.error("[suspendSubscription]subscriptionId is not valid id");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriptionId is not valid id.");
		}

		try {
			subcribeInfo.supplierProxy.suspend_connection();
		} catch (ConnectionAlreadyInactive e) {
			LOG.error("[suspendSubscription]ConnectionAlreadyInactive."
					+ e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		} catch (NotConnected e) {
			LOG.error("[suspendSubscription]NotConnected." + e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		subcribeInfo.state = SubscriptionState_T.SS_SUSPEND;
		LOG.info("[suspendSubscription]end.");
		return true;
	}

	@Override
	public void getSubscriptionStatus(int subscriptionId,
			ConstraintExpSeqHolder filterList, SubscriptionState_THolder status)
			throws ProcessingFailureException {
		LOG.info("[getSubscriptionStatus]begin.");
		LOG.info("[getSubscriptionStatus]subscriptionId is " + subscriptionId);

		SubscriptionInfo subcribeInfo = getSubcribeInfo(subscriptionId);
		if (null == subcribeInfo) {
			LOG.error("[getSubscriptionStatus]subscriptionId is not valid id");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriptionId is not valid id.");
		}

		filterList.value = subcribeInfo.filter;
		status.value = subcribeInfo.state;
		LOG.info("[getSubscriptionStatus]end.");
	}

	@Override
	public boolean synchronizeEvent(StructuredPushConsumer pushConsumer,
			ConstraintExp[] constraintList,
			StructuredProxyPushSupplierHolder supplier)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resumeSubscription(int subscriptionId)
			throws ProcessingFailureException {
		LOG.info("[resumeSubscription]begin.");
		LOG.info("[resumeSubscription]subscriptionId is " + subscriptionId);

		SubscriptionInfo subcribeInfo = getSubcribeInfo(subscriptionId);
		if (null == subcribeInfo) {
			LOG.error("[resumeSubscription]subscriptionId is not valid id");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriptionId is not valid id.");
		}

		try {
			subcribeInfo.supplierProxy.resume_connection();
		} catch (ConnectionAlreadyActive e) {
			LOG.error("[resumeSubscription]ConnectionAlreadyActive."
					+ e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		} catch (NotConnected e) {
			LOG.error("[resumeSubscription]NotConnected." + e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		subcribeInfo.state = SubscriptionState_T.SS_WORKING;
		LOG.info("[resumeSubscription]end.");
		return true;
	}

	@Override
	public boolean setSubscriptionFilter(int subscriptionId,
			ConstraintExp[] filterList) throws ProcessingFailureException {
		LOG.info("[setSubscriptionFilter]begin.");
		LOG.info("[setSubscriptionFilter]subscriptionId is " + subscriptionId);

		if (null == filterList) {
			LOG.error("[setSubscriptionFilter]filterList is null");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT, "filterList is null");
		}

		SubscriptionInfo subcribeInfo = getSubcribeInfo(subscriptionId);
		if (null == subcribeInfo) {
			LOG.error("[setSubscriptionFilter]subscriptionId is not valid id");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"subscriptionId is not valid id.");
		}

		subcribeInfo.supplierProxy.remove_all_filters();

		FilterFactory filterFactory = CorbaSessionAcessTool
				.getFilterFacotry(this.userSession);
		if (null == filterFactory) {
			LOG.error("[setSubscriptionFilter]filterFactory is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		Filter filter = null;
		try {
			filter = filterFactory.create_filter("EXTENDED_TCL");
		} catch (InvalidGrammar e) {
			LOG.error("[setSubscriptionFilter]create_filter factory with invalid grammar EXTENDED_TCL");
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		if (null == filter) {
			LOG.error("[setSubscriptionFilter]filter is null.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}
		try {
			filter.add_constraints(filterList);
		} catch (InvalidConstraint e) {
			LOG.error("[setSubscriptionFilter]InvalidConstraint"
					+ e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error.");
		}

		subcribeInfo.supplierProxy.add_filter(filter);

		subcribeInfo.filter = filterList;
		LOG.info("[setSubscriptionFilter]end.");
		return false;
	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName,
			String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName,
			String userLabel, boolean enforceUniqueness)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOwner(NameAndStringValue_T[] objectName, String owner)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCapabilities(CapabilityList_THolder capabilities)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] objectName,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}
	
	private synchronized void unRegister(int subscriptionId) {
		if (this.subscriptionInfoMap.containsKey(Integer
				.valueOf(subscriptionId)))
			this.subscriptionInfoMap.remove(Integer.valueOf(subscriptionId));

	}

	private synchronized SubscriptionInfo getSubcribeInfo(int subscriptionId) {
		return (SubscriptionInfo) this.subscriptionInfoMap.get(Integer
				.valueOf(subscriptionId));
	}
	
	private synchronized int register(SubscriptionInfo subcriptionInfo) {
		subscriptionInfoMap.put(Integer.valueOf(this.iSubscribeId),
				subcriptionInfo);
		return this.iSubscribeId++;
	}

	private synchronized boolean isRegistered(StructuredPushConsumer subscriber) {
		for (Iterator iter = this.subscriptionInfoMap.keySet().iterator(); iter
				.hasNext();) {
			SubscriptionInfo info = (SubscriptionInfo) this.subscriptionInfoMap
					.get(iter.next());
			if (null == info) {
				return false;
			}
			if (null == info.consumer) {
				return false;
			}
			if (null == subscriber) {
				return false;
			}
			if (info.consumer.equals(subscriber)) {
				return true;
			}
		}
		return false;
	}
	
	class SubscriptionInfo {
		public StructuredPushConsumer consumer = null;
		public StructuredProxyPushSupplier supplierProxy = null;
		public SubscriptionState_T state = SubscriptionState_T.SS_NO_EXIST;
		public ConstraintExp[] filter = null;

		SubscriptionInfo() {
		}
	}

}
