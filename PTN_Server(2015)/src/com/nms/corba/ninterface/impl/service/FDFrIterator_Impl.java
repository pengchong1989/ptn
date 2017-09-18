package com.nms.corba.ninterface.impl.service;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;

import flowDomainFragment.FDFrIterator_IHelper;
import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrIterator_IPOA;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FlowDomainFragment_T;
import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

public class FDFrIterator_Impl extends FDFrIterator_IPOA {
	
	private static Logger LOG = Logger.getLogger(FDFrIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private FlowDomainFragment_T[] list;
	private byte[] oid = null;
	
	public FDFrIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}


	@Override
	public boolean next_n(int how_many, FDFrList_THolder fdfrList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (how_many >= list.length) {
			fdfrList.value = list;
			list = null;
			return false;
		}

		List tempList = Arrays.asList(list);
		List requestList = tempList.subList(0, how_many);
		list = ((FlowDomainFragment_T[]) tempList.subList(how_many, tempList.size()).toArray(new FlowDomainFragment_T[0]));
		fdfrList.value = ((FlowDomainFragment_T[]) requestList.toArray(new FlowDomainFragment_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	@Override
	public int getLength() throws ProcessingFailureException {
		return list.length;
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
			ExceptionManage.dispose(e,this.getClass());
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error(e.getMessage());
		}

	}
	
	public void setIterator( FDFrIterator_IHolder fdfrIt, FDFrList_THolder fdfrList, int how_many) {
		LOG.info("[FDFrIterator_Impl][setIterator]begin.");

		if ((null == fdfrList.value) || (how_many >= fdfrList.value.length)) {
			fdfrIt = null;
		} else {
			Object obj = registerToPOA();
			fdfrIt.value = FDFrIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(fdfrList.value);
			try {
				next_n(how_many, fdfrList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
				ExceptionManage.dispose(e,this.getClass());
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
			ExceptionManage.dispose(e,this.getClass());
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

	protected void setList(FlowDomainFragment_T[] list) {
		this.list = list;
	}

}
