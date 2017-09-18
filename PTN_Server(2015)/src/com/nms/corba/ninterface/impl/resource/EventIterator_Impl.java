package com.nms.corba.ninterface.impl.resource;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import notifications.EventIterator_IHelper;
import notifications.EventIterator_IHolder;
import notifications.EventIterator_IPOA;
import notifications.EventList_THolder;

import org.apache.log4j.Logger;
import org.omg.CosNotification.StructuredEvent;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;


public class EventIterator_Impl extends EventIterator_IPOA {

	private static Logger LOG = Logger.getLogger(EventIterator_Impl.class
			.getName());
	private ICorbaSession userSession = null; 
	private StructuredEvent[] list = null; 
	private byte[] oid = null; 

	public EventIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public void destroy() throws ProcessingFailureException {
		// TODO Auto-generated method stub
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

	/**
	 * funtion:查询迭代器中数据记录的总条数，返回值为迭代器中包含的总记录条数
	 */
	@Override
	public int getLength() throws ProcessingFailureException {
		return list.length;
	}

	/**
	 * 
	 * 从迭代器中查询how_many条记录。当how_many>=迭代器剩余记录时，系统在数据取完后应该自动销毁该迭代器对象
	 */
	@Override
	public boolean next_n(int howMany, EventList_THolder eventList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");
		if (null == eventList) {
			return false;
		}
		if (howMany >= list.length) {
			eventList.value = list;
			list = null;
			return false;
		}
		List tempList = Arrays.asList(list);
		// 每次返回的数据集
		List requestList = tempList.subList(0, howMany);
		list = (StructuredEvent[]) tempList.subList(howMany,
				tempList.size()).toArray(new StructuredEvent[0]);
		eventList.value = (StructuredEvent[]) requestList
				.toArray(new StructuredEvent[0]);

		LOG.info("next_n(): end.");
		return true;
	
	}

	public void setIterator(EventIterator_IHolder eventIt,
			EventList_THolder eventList, int howMany) {
		LOG.info("EventIterator_Impl [setIterator] end.");
		if (null == eventList || howMany >= eventList.value.length) {
			eventIt = null;
		} else {
			// 注册POA
			Object obj = registerToPOA();
			eventIt.value = EventIterator_IHelper
					.narrow((org.omg.CORBA.Object) obj);
			setList(eventList.value);
			try {
				next_n(howMany, eventList);
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.info("[setIterator] end.");

	}

	/**
	 * function:注册POA
	 * 
	 * @return 对象引用
	 */
	private Object registerToPOA() {
		// TODO Auto-generated method stub
		LOG.info("[registerToPOA]begin.");
		if (null == userSession) {
			return null;
		}
		POA userPOA = null;
		try {
			userPOA = ((CorbaSession) userSession).getCorbaPOA().getUserPOA();
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

	protected void setList(StructuredEvent[] structuredEvent) {
		list = structuredEvent;
	}
}
