package com.nms.service.impl.dispatch.rmi;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.qos.QosInfo;

/**
 * qos模块rmi接口
 * 
 * @author kk
 * 
 */
public interface QosDispatchI extends DispatchInterface {

	/**
	 * 修改qos
	 * @param qosInfoList  要修改的qos列队
	 * @param object	修改的业务类型。 tnnel、pw、ac
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String excutionUpdate(List<QosInfo> qosInfoList, Object object) throws RemoteException, Exception;

}
