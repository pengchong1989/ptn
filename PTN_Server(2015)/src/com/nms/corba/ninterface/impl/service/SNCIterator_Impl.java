package com.nms.corba.ninterface.impl.service;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import subnetworkConnection.SNCIterator_IHelper;
import subnetworkConnection.SNCIterator_IHolder;
import subnetworkConnection.SNCIterator_IPOA;
import subnetworkConnection.SubnetworkConnectionList_THolder;
import subnetworkConnection.SubnetworkConnection_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

public class SNCIterator_Impl extends SNCIterator_IPOA {

	private static Logger LOG = Logger.getLogger(SNCIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	SubnetworkConnection_T[] list;
	private byte[] oid = null;
	
	public SNCIterator_Impl(ICorbaSession session) {

		userSession = session;
	}
	
	@Override
	public void destroy() throws ProcessingFailureException {
		LOG.info("[destroy]begin.");

		if (null != list) {
			this.list = null;
		}

		if (null == userSession) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "destroy() error : userSession is NULL!.");
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
	public boolean next_n(int howMany, SubnetworkConnectionList_THolder sncList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (howMany >= list.length) {
			sncList.value = list;
			list = null;
			return false;
		}

		List tempList = Arrays.asList(list);
		List requestList = tempList.subList(0, howMany);
		list = ((SubnetworkConnection_T[]) tempList.subList(howMany, tempList.size()).toArray(new SubnetworkConnection_T[0]));
		sncList.value = ((SubnetworkConnection_T[]) requestList.toArray(new SubnetworkConnection_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}
	
	public void setIterator(SNCIterator_IHolder sncIt, SubnetworkConnectionList_THolder sncList, int how_many) {
		LOG.info("[SNCIterator_Impl][setIterator]begin.");
		if ((null == sncList.value) || (how_many >= sncList.value.length)) {
			sncIt = null;
		} else {
			Object obj = registerToPOA();
			sncIt.value = SNCIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(sncList.value);
			try {
				next_n(how_many, sncList);
			} catch (ProcessingFailureException e) {
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
		;
		try {
			oid = userPOA.activate_object(this);
			Object obj = userPOA.id_to_reference(oid);

			LOG.info("[registerToPOA]end.");
			return obj;
		} catch (ServantAlreadyActive e) {
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			LOG.error(e.getMessage());
		} catch (ObjectNotActive e) {
			LOG.error(e.getMessage());
		}

		return null;
	}

	protected void setList(SubnetworkConnection_T[] list) {
		this.list = list;
	}

}
