package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

import transmissionDescriptor.QoSProfileIterator_IHelper;
import transmissionDescriptor.QoSProfileIterator_IHolder;
import transmissionDescriptor.QoSProfileIterator_IPOA;
import transmissionDescriptor.QoSProfileList_THolder;
import transmissionDescriptor.QoSProfile_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

public class QoSProfileIterator_Impl extends QoSProfileIterator_IPOA{
	private Logger LOG = Logger.getLogger(QoSProfileIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	QoSProfile_T[] list;
	private byte[] oid = null;
	
	public QoSProfileIterator_Impl(ICorbaSession session) {
		this.userSession = session;
	}

	@Override
	public void destroy() throws ProcessingFailureException {
		LOG.info("[destroy]begin.");

		if (null != list) { 	
			this.list = null;
		}

		if (null == userSession) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"destroy() error : userSession is NULL!.");
		}

		POA userPOA = ((CorbaSession) userSession).getCorbaPOA().getUserPOA();
		try {
			userPOA.deactivate_object(oid);
			LOG.info("[destroy]end.");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
	}

	@Override
	public int getLength() throws ProcessingFailureException {
		return list.length;
	}

	@Override
	public boolean next_n(int howMany, QoSProfileList_THolder paramQoSProfileListTHolder)
			              throws ProcessingFailureException {
		LOG.info("next_n(): begin.");
		if (null == list) {
			return false;
		}
		if (howMany >= list.length) {
			paramQoSProfileListTHolder.value = list;
			list = null;
			return false;
		}

		List<QoSProfile_T> tempList = Arrays.asList(list);
		List<QoSProfile_T> requestList = tempList.subList(0, howMany);
		list = ((QoSProfile_T[]) tempList.subList(howMany, tempList.size()).toArray(new QoSProfile_T[0]));
		paramQoSProfileListTHolder.value = ((QoSProfile_T[]) requestList.toArray(new QoSProfile_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	public void setIterator(QoSProfileList_THolder qosProfileList,
							QoSProfileIterator_IHolder qosProfileIt, int howMany) {
		LOG.info("[ManagedElementIterator_Impl][setIterator]begin.");
		if ((null == qosProfileList.value) || (howMany >= qosProfileList.value.length)) {
			qosProfileIt = null;
		} else {
			Object obj = this.registerToPOA();
			qosProfileIt.value = QoSProfileIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			this.setList(qosProfileList.value);
			try {
				this.next_n(howMany, qosProfileList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.info("[setIterator] end.");
	}
	
	private Object registerToPOA() {
		LOG.info("[registerToPOA]begin.");
		if (null == userSession) {
			return null;
		}

		POA userPOA = ((CorbaSession) userSession).getCorbaPOA().getUserPOA();
		try {
			oid = userPOA.activate_object(this);
			Object obj = userPOA.id_to_reference(oid);
			LOG.info("[registerToPOA]end.");
			return obj;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return null;
	}
	
	private void setList(QoSProfile_T[] list) {
		this.list = list;
	}

}
