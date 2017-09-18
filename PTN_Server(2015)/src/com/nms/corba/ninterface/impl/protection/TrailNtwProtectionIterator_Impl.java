package com.nms.corba.ninterface.impl.protection;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import trailNtwProtection.TrailNtwProtectionIterator_IHelper;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionIterator_IPOA;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;
/**
 * TNP迭代
 * @author dzy
 *
 */
public class TrailNtwProtectionIterator_Impl extends
		TrailNtwProtectionIterator_IPOA {
	
	private ICorbaSession userSession = null;
	private static Logger LOG = Logger.getLogger(ProtectionMgrIterator_Impl.class.getName());
	private TrailNtwProtection_T[] list;
	private byte[] oid = null;
	
	public TrailNtwProtectionIterator_Impl(ICorbaSession userSession){
		this.userSession = userSession;
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
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"destroy() error : userSession is NULL!.");
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

	/**
	 * 迭代
	 * @param tnpIt		迭代
	 * @param tnpList	TNP首次返回集合
	 * @param how_many	TNP首次返回数量
	 */
	public void setIterator(TrailNtwProtectionIterator_IHolder tnpIt,
			TrailNtwProtectionList_THolder tnpList, int how_many) {
		LOG.info("[TrailNtwProtMgr_Impl][setIterator]begin.");

		if ((null == tnpList.value) || (how_many >= tnpList.value.length)) {
			tnpIt = null;
		} else {
			Object obj = registerToPOA();
			tnpIt.value = TrailNtwProtectionIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(tnpList.value);
			try {
				next_n(how_many, tnpList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		LOG.info("[setIterator] end.");
		
	}

	/**
	 * 下一页
	 * @param how_many	每页显示数量
	 * @param tnpList	下一页信息
	 */
	@Override
	public boolean next_n(int how_many, TrailNtwProtectionList_THolder tnpList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (how_many >= list.length) {
			tnpList.value = list;
			list = null;
			return false;
		}

		List<TrailNtwProtection_T> tempList = Arrays.asList(list);
		List<TrailNtwProtection_T> requestList = tempList.subList(0, how_many);
		list = ((TrailNtwProtection_T[]) tempList.subList(how_many,tempList.size()).toArray(new TrailNtwProtection_T[0]));
		tnpList.value = ((TrailNtwProtection_T[]) requestList.toArray(new TrailNtwProtection_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}
	
	/**
	 * 等级
	 * @return
	 */
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
	
	protected void setList(TrailNtwProtection_T[] list) {
		this.list = list;
	}
}
