package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.DualCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 双规保护 调度类
 * @author dzy
 *
 */
public class DualDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 添加双规保护
	 * 
	 * @param object 双规保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		DualProtect dualProtect=null;
		try {
			if (object == null) {
				throw new Exception("dualProtect is null");
			}
			dualProtect = (DualProtect) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,dualProtect);
			if(null!=siteCheck){
				return siteCheck;
			}
			//通过网元ID判断设备类型
			manufacturer = super.getManufacturer(dualProtect.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				//武汉设备转换
			} else {
				operationServiceI = new DualCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(dualProtect);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	/**
	 * 修改双规保护
	 * @param object 双规保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 *  
	 */
	@Override
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		DualProtect dualProtect = null;
		try {
			if (object == null) {
				throw new Exception("dualProtect is null");
			}
			dualProtect = (DualProtect) object;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,dualProtect);
			if(null!=siteCheck){
				return siteCheck;
			}
			//通过网元ID判断设备类型
			manufacturer = super.getManufacturer(dualProtect.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				//武汉设备转换
			} else {
				operationServiceI = new DualCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(dualProtect);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	/**
	 * 删除双规保护
	 * @param object  双规保护 Bean
	 * 
	 * @throws Exception
	 *  
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String excuteDelete(Object object) throws Exception {
		List<DualProtect> dualProtectList = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (object != null ) {
				dualProtectList = (List<DualProtect>) object;
				
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,dualProtectList);
				if(null!=siteCheck){
					return siteCheck;
				}
				//通过网元ID判断设备类型
				if(null!=dualProtectList&&dualProtectList.size()>0){
					manufacturer = super.getManufacturer(dualProtectList.get(0).getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						//武汉设备转换
					} else {
						operationServiceI = new DualCXServiceImpl();
					}
					result = operationServiceI.excutionDelete(dualProtectList);
					if (ResultString.CONFIG_SUCCESS.equals(result)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				} 
			}else {
				throw new Exception("dualProtectList is null");
			}
				
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			dualProtectList = null;
			operationServiceI = null;
		}
		return result;
	}
	
	/**
	 * synchro(根据网元id同步)
	 * 
	 * @param siteId 网元ID
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		String result = ResultString.QUERY_FAILED;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}
			//通过网元ID判断设备类型
			if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
				//武汉设备转换
			} else {
				operationServiceI = new DualCXServiceImpl();
			}
			result =  (String)operationServiceI.synchro(siteId);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
