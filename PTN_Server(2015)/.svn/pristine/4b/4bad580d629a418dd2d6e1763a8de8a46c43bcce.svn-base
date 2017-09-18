package com.nms.corba.ninterface.impl.performance;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

import performance.PMDataIterator_IHelper;
import performance.PMDataIterator_IHolder;
import performance.PMDataIterator_IPOA;
import performance.PMDataList_THolder;
import performance.PMData_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

public class PMDataIterator_Impl extends PMDataIterator_IPOA {
	private Logger LOG = Logger.getLogger(PMDataIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private PMData_T[] list;
	private byte[] oid = null;
	
	public PMDataIterator_Impl(ICorbaSession session) {
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
	public boolean next_n(int howMany, PMDataList_THolder pmDataList)
							throws ProcessingFailureException {
		LOG.info("next_n(): begin.");
		if (null == list) {
			return false;
		}
		if (howMany >= list.length) {
			pmDataList.value = list;
			list = null;
			return false;
		}
		List<PMData_T> tempList = Arrays.asList(list);
		List<PMData_T> requestList = tempList.subList(0, howMany);
		list = ((PMData_T[]) tempList.subList(howMany, tempList.size()).toArray(new PMData_T[0]));
		pmDataList.value = ((PMData_T[]) requestList.toArray(new PMData_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	public void setIterator(PMDataIterator_IHolder pmIt, PMDataList_THolder pmDataList, int how_many) {
		LOG.info("[PMDataIterator_Impl][setIterator]begin.");

		if ((null == pmDataList.value) || (how_many >= pmDataList.value.length)) {
			pmIt = null;
		} else {
			Object obj = this.registerToPOA();
			pmIt.value = PMDataIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			this.setList(pmDataList.value);
			try {
				this.next_n(how_many, pmDataList);
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
	
	private void setList(PMData_T[] list) {
		this.list = list;
	}
}
