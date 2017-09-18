package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

import topologicalLink.TopologicalLinkIterator_IHelper;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkIterator_IPOA;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

public class TopologicalLinkIterator_Impl extends TopologicalLinkIterator_IPOA {
	private Logger LOG = Logger.getLogger(TopologicalLinkIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	TopologicalLink_T[] list;
	private byte[] oid = null;

	public TopologicalLinkIterator_Impl(ICorbaSession session) {
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

	public void setIterator(TopologicalLinkIterator_IHolder topoIt,
			                TopologicalLinkList_THolder topoList,int how_many) {
		LOG.info("[ManagedElementIterator_Impl][setIterator]begin.");
		if ((null == topoList.value) || (how_many >= topoList.value.length)) {
			topoIt = null;
		} else {
			Object obj = this.registerToPOA();
			topoIt.value = TopologicalLinkIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			this.setList(topoList.value);
			try {
				this.next_n(how_many, topoList);
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

	private void setList(TopologicalLink_T[] list) {
		this.list = list;
	}

	@Override
	public boolean next_n(int howMany, TopologicalLinkList_THolder topoLinkList)
						  throws ProcessingFailureException {
		LOG.info("next_n(): begin.");
		if (null == list) {
			return false;
		}
		if (howMany >= list.length) {
			topoLinkList.value = list;
			list = null;
			return false;
		}

		List<TopologicalLink_T> tempList = Arrays.asList(list);
		List<TopologicalLink_T> requestList = tempList.subList(0, howMany);
		list = ((TopologicalLink_T[]) tempList.subList(howMany,tempList.size()).toArray(new TopologicalLink_T[0]));
		topoLinkList.value = ((TopologicalLink_T[]) requestList.toArray(new TopologicalLink_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

}
