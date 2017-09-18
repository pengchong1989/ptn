package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHelper;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesIterator_IPOA;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

public class NamingAttributesIterator_Impl extends NamingAttributesIterator_IPOA {
	private Logger LOG = Logger.getLogger(ManagedElementIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	NameAndStringValue_T[][] list;
	private byte[] oid = null;
	
	public NamingAttributesIterator_Impl(ICorbaSession session) {
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
	public boolean next_n(int howMany, NamingAttributesList_THolder nameList)
			              throws ProcessingFailureException {
		LOG.info("next_n(): begin.");
		if (null == list) {
			return false;
		}
		if (howMany >= list.length) {
			nameList.value = list;
			list = null;
			return false;
		}

		List<NameAndStringValue_T[]> tempList = Arrays.asList(list);
		List<NameAndStringValue_T[]> requestList = tempList.subList(0, howMany);
		list = ((NameAndStringValue_T[][]) tempList.subList(howMany,
				tempList.size()).toArray(new NameAndStringValue_T[0][0]));
		nameList.value = ((NameAndStringValue_T[][]) requestList
				.toArray(new NameAndStringValue_T[0][0]));
		LOG.info("next_n(): end.");
		return true;
	}

	public void setIterator(NamingAttributesIterator_IHolder nameIt,
			                NamingAttributesList_THolder nameList, int how_many) {
		LOG.info("[ManagedElementIterator_Impl][setIterator]begin.");
		if ((null == nameList.value) || (how_many >= nameList.value.length)) {
			nameIt = null;
		} else {
			Object obj = this.registerToPOA();
			nameIt.value = NamingAttributesIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			this.setList(nameList.value);
			try {
				this.next_n(how_many, nameList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.info("[setIterator] end.");
	}
	
	private void setList(NameAndStringValue_T[][] list) {
		this.list = list;
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
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return null;
	}
}
