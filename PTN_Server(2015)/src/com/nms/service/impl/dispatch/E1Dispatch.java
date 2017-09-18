package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.E1CXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.E1WHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class E1Dispatch extends DispatchBase implements DispatchInterface{
	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<E1Info> elInfoList=null;
		try {
			if (object == null) {
				throw new Exception("e1Info is null");
			}
			elInfoList=(List<E1Info>) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,elInfoList);
			if(null!=siteCheck){
				//上报到CORBA
				for (E1Info e1Info : elInfoList) {
					super.notifyCorba("port", MessageType.ATTRIBUTECHG, e1Info, "e1", ResultString.CONFIG_SUCCESS);
				}
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(elInfoList.get(0).getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			manufacturer = super.getManufacturer(elInfoList.get(0).getSiteId());

			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new E1WHServiceImpl();
			} else {
				operationServiceI = new E1CXServiceImpl();
			}
			result=operationServiceI.excutionUpdate(elInfoList);
			//上报到CORBA
			for (E1Info e1Info : elInfoList) {
				super.notifyCorba("port", MessageType.ATTRIBUTECHG, e1Info, "e1", result);
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
			return result;
	}
	
//	public String excuteUpdateCX(E1Info e1Info) throws Exception {
//		int manufacturer = 0;
//		OperationServiceI operationServiceI = null;
//		String result=null;
//		if (e1Info == null) {
//			throw new Exception("e1Info is null");
//		}
//
//		try {
//			
//			//虚拟网元操作
//			String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,e1Info);
//			if(null!=siteCheck){
//				return siteCheck;
//			}
//			
//			manufacturer = super.getManufacturer(e1Info.getSiteId());
//
//			if (manufacturer==EManufacturer.WUHAN.getValue()) {
//				operationServiceI = new E1WHServiceImpl();
//			} else {
//				operationServiceI = new E1CXServiceImpl();
//			}
//			result=operationServiceI.excutionUpdate(e1Info);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//		} else {
//			return result;
//		}
//	}
//	
//	public String excuteWHUpdate(List<E1Info> e1Infos) throws Exception {
//		int manufacturer = 0;
//		OperationServiceI operationServiceI = null;
//		String result=null;
//		if (e1Infos == null) {
//			throw new Exception("e1Infos is null");
//		}
//
//		try {
//			//虚拟网元操作
//			String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,e1Infos);
//			if(null!=siteCheck){
//				return siteCheck;
//			}
//			manufacturer = super.getManufacturer(e1Infos.get(0).getSiteId());
//
//			if (manufacturer==EManufacturer.WUHAN.getValue()) {
//				operationServiceI = new E1WHServiceImpl();
//			} 
//			result=operationServiceI.excutionUpdate(e1Infos);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//		} else {
//			return result;
//		}
//	}
	

	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		E1Info e1Info=null;
		try {
			if (object == null) {
				throw new Exception("acPortInfo is null");
			}
			e1Info=(E1Info) object;
			
		/*	//虚拟网元操作
			String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,e1Info);
			if(null!=siteCheck){
				return siteCheck;
			}*/
			
			manufacturer = super.getManufacturer(e1Info.getSiteId());

			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				operationServiceI = new E1WHServiceImpl();
			} else {
		//		operationServiceI = new AcCXServiceImpl();
			}
			result=operationServiceI.excutionInsert(e1Info);
			//上报到CORBA
			super.notifyCorba("port", MessageType.CREATION, e1Info, "e1", result);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		return null;
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
				return siteCheck;
			}
			
			manufacturer=super.getManufacturer(siteId);
			if(manufacturer==EManufacturer.WUHAN.getValue()){
				//同步武汉的NNI、UNI
				operationServiceI = new E1WHServiceImpl();
			}else{
				//同步晨晓的NNI、UNI
				operationServiceI=new E1CXServiceImpl();
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
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
