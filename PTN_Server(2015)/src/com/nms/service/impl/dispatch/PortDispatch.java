package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.NNICXServiceImpl;
import com.nms.service.impl.cx.PortCXServiceImpl;
import com.nms.service.impl.cx.UNICXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.PortWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PortDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 修改
	 */
	public String excuteUpdate(Object object) throws Exception{
		PortInst portInst=null;
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			portInst=(PortInst) object;
			
//			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,portInst);
			if(null!=siteCheck){
				super.notifyCorba("port", MessageType.ATTRIBUTECHG, portInst, "port", ResultString.CONFIG_SUCCESS);
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(portInst.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			manufacturer=super.getManufacturer(portInst.getSiteId());
			
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				operationServiceI=new PortWHServiceImpl();
				((PortWHServiceImpl) operationServiceI).setType(portInst.getPortType());
			}else{
				if (portInst.getPortType().equalsIgnoreCase(TypeAndActionUtil.TYPE_UNI)) {
					operationServiceI = new UNICXServiceImpl();
				} else if(portInst.getPortType().equalsIgnoreCase(TypeAndActionUtil.TYPE_NNI)){
					operationServiceI = new NNICXServiceImpl();
				} else {
					operationServiceI = new PortCXServiceImpl();
				}
			}
			result = operationServiceI.excutionUpdate(object);
			super.notifyCorba("port", MessageType.ATTRIBUTECHG, portInst, "port", ResultString.CONFIG_SUCCESS);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			portInst=null;
			operationServiceI=null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}
	
	/**
	 *  同步
	
	* @author kk
	
	* @param   
	
	* @return 
	 * @throws Exception 
	
	* @Exception 异常对象
	 */
	public String synchro(int siteId) throws Exception{
		int manufacturer=0;
		OperationServiceI operationServiceI=null;
		String result = ResultString.QUERY_FAILED;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(siteId);
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			manufacturer=super.getManufacturer(siteId);
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				//同步武汉的NNI、UNI
				operationServiceI = new PortWHServiceImpl();
			}else{
				//同步晨晓的NNI、UNI
				operationServiceI=new PortCXServiceImpl();
			}
			 result = (String)operationServiceI.synchro(siteId);
		} catch (Exception e) {
			throw e;
		} finally{
			operationServiceI=null;
		}
		return result;
		
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		if(super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()){
			PortWHServiceImpl portWHServiceImpl = new PortWHServiceImpl();
			List<PortInst> portInsts = portWHServiceImpl.consistence(siteId);
			return portInsts;
		}
		return null;
	}
}
