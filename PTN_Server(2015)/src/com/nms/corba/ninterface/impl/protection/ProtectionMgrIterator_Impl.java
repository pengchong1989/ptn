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

import protection.ProtectionGroupIterator_IHelper;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupIterator_IPOA;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionGroup_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;

/**
 * 保护组迭代
 * @author dzy
 *
 */
public class ProtectionMgrIterator_Impl extends ProtectionGroupIterator_IPOA {

	private static Logger LOG = Logger
			.getLogger(ProtectionMgrIterator_Impl.class.getName());
	private ICorbaSession userSession = null;
	private ProtectionGroup_T[] list;
	private byte[] oid = null;

	public ProtectionMgrIterator_Impl(ICorbaSession session) {

		userSession = session;
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

	@Override
	public int getLength() throws ProcessingFailureException {
		return list.length;
	}

	/**
	 * 数据拆分（第一组和剩余） 
	 * @param howMany 第一组信息数量
	 * @param protectionGroupList 保护组集合
	 * @return
	 * @throws ProcessingFailureException
	 */
	public boolean next_n(int howMany,
			ProtectionGroupList_THolder protectionGroupList)
			throws ProcessingFailureException {
		LOG.info("next_n(): begin.");

		if (null == list) {
			return false;
		}

		if (howMany >= list.length) {
			protectionGroupList.value = list;
			list = null;
			return false;
		}

		List<ProtectionGroup_T> tempList = Arrays.asList(list);
		List<ProtectionGroup_T> requestList = tempList.subList(0, howMany);
		list = ((ProtectionGroup_T[]) tempList.subList(howMany,tempList.size()).toArray(new ProtectionGroup_T[0]));
		protectionGroupList.value = ((ProtectionGroup_T[]) requestList.toArray(new ProtectionGroup_T[0]));
		LOG.info("next_n(): end.");
		return true;
	}

	/**
	 * 保护组迭代
	 * @param meIt  迭代
	 * @param meList 第一组数据
	 * @param how_many  第一组数据数量
	 * @throws ProcessingFailureException 
	 */
	public void setIterator(ProtectionGroupIterator_IHolder pgpIt,
			ProtectionGroupList_THolder pgpList, int how_many) throws ProcessingFailureException {
		LOG.info("[ProtectionMgr_Impl][setIterator]begin.");

		if ((null == pgpList.value) || (how_many >= pgpList.value.length)) {
			pgpIt = null;
		} else {
			Object obj = registerToPOA();
			pgpIt.value = ProtectionGroupIterator_IHelper.narrow((org.omg.CORBA.Object) obj);
			setList(pgpList.value);
			try {
				next_n(how_many, pgpList);
			} catch (ProcessingFailureException e) {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INTERNAL_ERROR,
						"ProtectionMgrIterator_Impl ex.");
			}
		}
		LOG.info("[setIterator] end.");

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
	
	protected void setList(ProtectionGroup_T[] list) {
		this.list = list;
	}
}
