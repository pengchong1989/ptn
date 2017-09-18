package com.nms.corba.ninterface.impl.service;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import subnetworkConnection.CCIterator_IHelper;
import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CCIterator_IPOA;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;

import com.nms.corba.ninterface.framework.CorbaSession;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.ui.manager.ExceptionManage;
/**
 * function:查询tunnel或PW的迭代器
 * @author zk
 */
public class CrossConnectionsIterator_impl extends CCIterator_IPOA{
	private  static Logger LOG = Logger.getLogger(CrossConnectionsIterator_impl.class.getName());
	private ICorbaSession userSession = null; 
	CrossConnect_T[] list;
	private byte[] oid = null;
	
	public CrossConnectionsIterator_impl(ICorbaSession userSession){
		this.userSession = userSession;
	}
	
	@Override
	public void destroy() throws ProcessingFailureException {
		// TODO Auto-generated method stub
		LOG.info("[destroy]begin.");
		if(null != list){
			list = null;
		}
		if(null == userSession){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"destroy() error : userSession is NULL!.");
		}
		POA userPOA = ((CorbaSession)userSession).getCorbaPOA().getUserPOA();
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

	/**
	 * 从迭代器中查询how_many条记录。当how_many>=迭代器剩余记录时，系统在数据取完后应该自动销毁该迭代器对象
	 */
	@Override
	public boolean next_n(int howMany,CrossConnectList_THolder CrossConnectList)throws ProcessingFailureException {
		List tempList = null;
		List requestList = null;
		try {
			LOG.info("next_n(): begin.");
			if(null == CrossConnectList){
				return false;
			}
			if(howMany >= list.length){
				CrossConnectList.value = list ;
				list =null ;
				return false;
			}
			tempList = Arrays.asList(list);
			//每次返回的数据集
			requestList = tempList.subList(0, howMany);
			list = (CrossConnect_T[]) tempList.subList(howMany, tempList.size()).toArray(new CrossConnect_T[0]);
			CrossConnectList.value = (CrossConnect_T[])requestList.toArray(new CrossConnect_T[0]);
			LOG.info("next_n(): end.");
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}finally{
			 tempList = null;
			 requestList  =null;
		}
		return false;
	}
	
	public void setIterator(CCIterator_IHolder ccIt,CrossConnectList_THolder ccList,int how_many){
		LOG.info("[CrossConnectList_THolder][setIterator]begin.");
		if((null == ccList) ||(how_many >= ccList.value.length)){
			ccIt = null;
		}else{
			Object obj = registerToPOA();
			ccIt.value = CCIterator_IHelper.narrow((org.omg.CORBA.Object)obj);
			setList(ccList.value);
			try {
				next_n(how_many, ccList);
			} catch (ProcessingFailureException e) {
				LOG.error(e.getMessage());
			}
		}
		LOG.info("[setIterator] end.");
	}

	private Object registerToPOA() {
		LOG.info("[registerToPOA]begin.");
		if(null == userSession){
			return null;
		}
		POA userPOA = ((CorbaSession)userSession).getCorbaPOA().getUserPOA();
		Object obj = null;
        try {
			oid = userPOA.activate_object(this);
			obj = userPOA.id_to_reference(oid);
		} catch (ServantAlreadyActive e) {
			LOG.error(e.getMessage());
		} catch (WrongPolicy e) {
			LOG.error(e.getMessage());
		}catch (ObjectNotActive e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error(e.getMessage());
		}
		return obj;
	}
	
	protected void setList(CrossConnect_T[] list){
		this.list = list;
	}
	
}
