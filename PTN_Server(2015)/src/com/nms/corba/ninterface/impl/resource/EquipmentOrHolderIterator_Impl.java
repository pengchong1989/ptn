package com.nms.corba.ninterface.impl.resource;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;

import equipment.EquipmentOrHolderIterator_IHelper;
import equipment.EquipmentOrHolderIterator_IHolder;
import equipment.EquipmentOrHolderIterator_IPOA;
import equipment.EquipmentOrHolderList_THolder;
import equipment.EquipmentOrHolder_T;
import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

public class EquipmentOrHolderIterator_Impl extends EquipmentOrHolderIterator_IPOA {
	private Logger LOG = Logger.getLogger(EquipmentOrHolderIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private byte[] oid = null;
	private EquipmentOrHolder_T[] list;

	public EquipmentOrHolderIterator_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
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
	public boolean next_n(int howMany, EquipmentOrHolderList_THolder pmDataList)
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
		List<EquipmentOrHolder_T> tempList = Arrays.asList(list);
		List<EquipmentOrHolder_T> requestList = tempList.subList(0, howMany);
		list = ((EquipmentOrHolder_T[]) tempList.subList(howMany, tempList.size()).toArray(new EquipmentOrHolder_T[0]));
		pmDataList.value = ((EquipmentOrHolder_T[]) requestList.toArray(new EquipmentOrHolder_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	public void setIterator(EquipmentOrHolderIterator_IHolder eqIt,
								EquipmentOrHolderList_THolder eqList, int howMany) {
		LOG.info("[ManagedElementIterator_Impl][setIterator]begin.");

		if ((null == eqList.value) || (howMany >= eqList.value.length)) {
			eqIt = null;
		} else {
			Object obj = this.registerToPOA();
			eqIt.value = EquipmentOrHolderIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			this.setList(eqList.value);
			try {
				this.next_n(howMany, eqList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
				ExceptionManage.dispose(e,this.getClass());
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

	private void setList(EquipmentOrHolder_T[] value) {
		this.list = value;
	}

}
