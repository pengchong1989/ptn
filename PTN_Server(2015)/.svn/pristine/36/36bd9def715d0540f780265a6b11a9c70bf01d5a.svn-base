package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.OSPFInterfaceCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OSPFInterfaceDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 修改
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OSPFInterface ospfInterface=null;
		try {
			if (object == null) {
				throw new Exception("ospfInterface is null");
			}
			ospfInterface=(OSPFInterface) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,ospfInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(Integer.valueOf(ospfInterface.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new OSPFInterfaceCXServiceImpl();
				result = operationServiceI.excutionUpdate(ospfInterface);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ospfInterface = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	/**
	 * 新建
	 */
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OSPFInterface ospfInterface=null;
		try {
			if (object == null) {
				throw new Exception("ospfInterface is null");
			}
			ospfInterface=(OSPFInterface) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,ospfInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(Integer.valueOf(ospfInterface.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new OSPFInterfaceCXServiceImpl();
				result = operationServiceI.excutionInsert(ospfInterface);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ospfInterface = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	/**
	 * 删除
	 */
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<OSPFInterface> ospfInterface =null;
		try {
			if (object == null) {
				throw new Exception("ospfInterface is null");
			}
			ospfInterface=(List<OSPFInterface>) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,ospfInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(Integer.valueOf(ospfInterface.get(0).getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new OSPFInterfaceCXServiceImpl();
				result = operationServiceI.excutionDelete(ospfInterface);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			operationServiceI = null;
			ospfInterface = null;
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
	 * @author wangwf
	 * 
	 * @param siteId
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
			} else {
				operationServiceI = new OSPFInterfaceCXServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
