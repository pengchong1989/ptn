package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.MCNCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * mcn 设备调度
 * @author Administrator
 *
 */
public class MCNDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 修改
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		MCN mcn=null;
		try {
			if (object == null) {
				throw new Exception("MCN is null");
			}
			mcn=(MCN) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,mcn);
			if(null!=siteCheck){
				return siteCheck;
			}
			manufacturer = super.getManufacturer(Integer.valueOf(mcn.getNeId()));
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				//武汉实现
			} else {
				operationServiceI = new MCNCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(mcn);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
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
				//武汉实现
			} else {
				operationServiceI = new MCNCXServiceImpl();
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		return ResultString.QUERY_FAILED;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
