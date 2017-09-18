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

import flowDomain.FDIterator_IHelper;
import flowDomain.FDIterator_IHolder;
import flowDomain.FDIterator_IPOA;
import flowDomain.FDList_THolder;
import flowDomain.FlowDomain_T;
import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

/**
 * FD 设置 迭代器
 * @author sy
 *
 */
public class FDIterator_Impl extends FDIterator_IPOA {
	
	private static Logger LOG = Logger.getLogger(FDIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private FlowDomain_T[] list;
	private byte[] oid = null;
	
	public FDIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public boolean next_n(int how_many, FDList_THolder fdList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (how_many >= list.length) {
			fdList.value = list;
			list = null;
			return false;
		}

		List tempList = Arrays.asList(list);
		List requestList = tempList.subList(0, how_many);
		list = ((FlowDomain_T[]) tempList.subList(how_many, tempList.size()).toArray(new FlowDomain_T[0]));
		fdList.value = ((FlowDomain_T[]) requestList.toArray(new FlowDomain_T[0]));
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
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			LOG.error(e.getMessage());
		}
	}
	public void setIterator( FDIterator_IHolder fdIt, FDList_THolder fdList, int how_many) {
		LOG.info("[FDIterator_Impl][setIterator]begin.");

		if ((null == fdList.value) || (how_many >= fdList.value.length)) {
			fdIt = null;
		} else {
			Object obj = registerToPOA();
			fdIt.value = FDIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(fdList.value);
			try {
				next_n(how_many, fdList);
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

	protected void setList(FlowDomain_T[] list) {
		this.list = list;
	}


}
