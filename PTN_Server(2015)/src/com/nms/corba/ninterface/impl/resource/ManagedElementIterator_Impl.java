package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import managedElement.ManagedElementIterator_IHelper;
import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementIterator_IPOA;
import managedElement.ManagedElementList_THolder;
import managedElement.ManagedElement_T;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;

public class ManagedElementIterator_Impl extends ManagedElementIterator_IPOA {

	private static Logger LOG = Logger.getLogger(ManagedElementIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	ManagedElement_T[] list;
	private byte[] oid = null;

	public ManagedElementIterator_Impl(ICorbaSession session) {

		userSession = session;
	}

	@Override
	public void destroy() throws ProcessingFailureException {
		LOG.info("[destroy]begin.");

		if (null != list) {
			this.list = null;
		}

		if (null == userSession) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"destroy() error : userSession is NULL!.");
		}

		POA userPOA = ((CorbaSession) userSession).getCorbaPOA().getUserPOA();
		try {
			userPOA.deactivate_object(oid);
			LOG.info("[destroy]end.");
		} catch (ObjectNotActive e) {
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			LOG.error(e.getMessage());
		}
	}

	@Override
	public int getLength() throws ProcessingFailureException {
		return list.length;
	}

	@Override
	public boolean next_n(int howMany, ManagedElementList_THolder managedElementList) {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (howMany >= list.length) {
			managedElementList.value = list;
			list = null;
			return false;
		}

		List tempList = Arrays.asList(list);
		List requestList = tempList.subList(0, howMany);
		list = ((ManagedElement_T[]) tempList.subList(howMany, tempList.size()).toArray(new ManagedElement_T[0]));
		managedElementList.value = ((ManagedElement_T[]) requestList.toArray(new ManagedElement_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	public void setIterator(ManagedElementIterator_IHolder meIt, ManagedElementList_THolder meList, int how_many) {
		LOG.info("[ManagedElementIterator_Impl][setIterator]begin.");

		if ((null == meList.value) || (how_many >= meList.value.length)) {
			meIt = null;
		} else {
			Object obj = registerToPOA();
			meIt.value = ManagedElementIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(meList.value);
			try {
				next_n(how_many, meList);
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.info("[setIterator] end.");

	}

	protected Object registerToPOA() {
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
		} catch (ServantAlreadyActive e) {
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error(e.getMessage());
		} catch (ObjectNotActive e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error(e.getMessage());
		}

		return null;
	}

	protected void setList(ManagedElement_T[] list) {
		this.list = list;
	}

}
