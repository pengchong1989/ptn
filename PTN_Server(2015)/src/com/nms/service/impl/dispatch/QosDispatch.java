package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.QosCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.QosDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.OamWHServiceImpl;
import com.nms.service.impl.wh.QosWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class QosDispatch extends DispatchBase implements QosDispatchI{

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		List<OamInfo> oamlList = null;
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamlList = (List<OamInfo>) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,oamlList);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (oamlList != null && oamlList.size() > 0) {
				oamInfo = oamlList.get(0);

				manufacturer = super.getManufacturer(oamInfo.getOamMep().getObjId());
				// if(manufacturer.equals("武汉")){
				operationServiceI = new OamWHServiceImpl();
				// }else{
				// operationServiceI=new OamWHServiceImpl();
				// }
				result=operationServiceI.excutionDelete(oamlList);
			} else {
				throw new Exception("objectList is null");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oamlList = null;
			oamInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	public String excuteInsert(Object object) throws Exception {
		OamInfo oamInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			oamInfo = (OamInfo) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,oamInfo);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(oamInfo.getOamMep().getObjId());
			// if(manufacturer.equals("武汉")){
			operationServiceI = new OamWHServiceImpl();
			// }else{
			// operationServiceI=new OamWHServiceImpl();
			// }
			result=operationServiceI.excutionInsert(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oamInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	/**
	 * 修改qos配置
	 * 
	 * @param qosInfoList
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String excutionUpdate(List<QosInfo> qosInfoList, Object object) throws Exception {
		int manufacturer = 0;
		int siteid = 0;
		Tunnel tunnel = null;
		PwInfo pwInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,qosInfoList,object);
			if(null!=siteCheck){
				return siteCheck;
			}
			
			if (object instanceof Tunnel) {
				tunnel = (Tunnel) object;
				if (tunnel.getIsSingle() == 0) {
					siteid = tunnel.getASiteId();
				} else {
					if(tunnel.getASiteId()!=0){
						siteid = tunnel.getASiteId();
					}else{
						siteid = tunnel.getZSiteId();
					}
					
				}
			} else if (object instanceof PwInfo) {
				pwInfo = (PwInfo) object;
				if (pwInfo.getIsSingle() == 0) {
					siteid = pwInfo.getASiteId();
				} else {
					if(pwInfo.getASiteId()!=0){
						siteid = pwInfo.getASiteId();
					}else{
						siteid = pwInfo.getZSiteId();
					}
				}
			}
			manufacturer = super.getManufacturer(siteid);

			if (manufacturer==EManufacturer.WUHAN.getValue()) {
				QosWHServiceImpl qosWHServiceImpl = new QosWHServiceImpl();
				result = qosWHServiceImpl.excutionUpdate_qos(qosInfoList, object);
			} else {
				QosCXServiceImpl qosCXServiceImpl = new QosCXServiceImpl();
				result = qosCXServiceImpl.excutionUpdate_qos(qosInfoList, object);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			tunnel = null;
			pwInfo = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}
	
	
	/**
	 * synchro(根据网元id同步)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		String result =  ResultString.QUERY_FAILED;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new QosWHServiceImpl();
			} else {
				operationServiceI = new QosCXServiceImpl();
			}
			result =  (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			throw e;
		} finally{
			 operationServiceI = null;
		}
		return result;

	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
