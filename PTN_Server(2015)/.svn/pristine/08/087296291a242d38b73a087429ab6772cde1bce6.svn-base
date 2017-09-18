package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import terminationPoint.TerminationPointIterator_IHelper;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointIterator_IPOA;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;

/**
 * function:将查询的“以太网层速率” 加入迭代器中
 * @author zhangkun
 */
public class TerminationPointIterator_Impl extends TerminationPointIterator_IPOA {
	
	private static Logger LOG = Logger.getLogger(TerminationPointIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private TerminationPoint_T[] list;
	private byte[] oid = null;
	
	public TerminationPointIterator_Impl(ICorbaSession session) {

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
	public boolean next_n(int howMany, TerminationPointList_THolder tpLit)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (howMany >= list.length) {
			tpLit.value = list;
			list = null;
			return false;
		}

		List tempList = Arrays.asList(list);
		List requestList = tempList.subList(0, howMany);
		list = ((TerminationPoint_T[]) tempList.subList(howMany, tempList.size()).toArray(new TerminationPoint_T[0]));
		tpLit.value = ((TerminationPoint_T[]) requestList.toArray(new TerminationPoint_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}
	
	public void setIterator(TerminationPointIterator_IHolder tpIt, TerminationPointList_THolder tpList, int how_many) {
		LOG.info("[TerminationPointIterator_Impl][setIterator]begin.");
		if ((null == tpList.value) || (how_many >= tpList.value.length)) {
			tpIt = null;
		} else {
			Object obj = registerToPOA();
			tpIt.value = TerminationPointIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(tpList.value);
			try {
				next_n(how_many, tpList);
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

	protected void setList(TerminationPoint_T[] list) {
		this.list = list;
	}
}
