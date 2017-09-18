package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.MspCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * msp保护 调度类
 * @author dzy
 *
 */
public class MspDispatch extends DispatchBase implements DispatchInterface{
	/**
	 * 添加msp
	 * 
	 * @param object  msp保护 Bean
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
		MspProtect mspProtect=null;
		try {
			if (object == null) {
				throw new Exception("mspProtect is null");
			}
			mspProtect = (MspProtect) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,mspProtect);
			if(null!=siteCheck){
				return siteCheck;
			}
			//通过网元ID判断设备类型
			manufacturer = super.getManufacturer(mspProtect.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				//武汉设备转换
			} else {
				operationServiceI = new MspCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(mspProtect);
		} catch (BusinessIdException e) {  //数量超出设备限制
			return ResourceUtil.srcStr(StringKeysTip.TIP_BUSINESSID_NULL);
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}
	/**
	 * 修改msp
	 * @param object msp保护 Bean
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
		MspProtect mspProtect = null;
		if (object == null) {
			throw new Exception("mspProtect is null");
		}
		try {
			mspProtect = (MspProtect) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,mspProtect);
			if(null!=siteCheck){
				return siteCheck;
			}
			//通过网元ID判断设备类型
			manufacturer = super.getManufacturer(mspProtect.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				//武汉设备转换
			} else {
				operationServiceI = new MspCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(mspProtect);
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
	 * 删除msp
	 * @param object  msp保护 Bean
	 * 
	 * @throws Exception
	 *  
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String excuteDelete(Object object) throws Exception {
		List<MspProtect> mspProtectList = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (object != null ) {
				mspProtectList = (List<MspProtect>) object;
				
				//虚拟网元操作
				SiteUtil siteUtil = new SiteUtil();
				String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,mspProtectList);
				if(null!=siteCheck){
					return siteCheck;
				}
				//通过网元ID判断设备类型
				if(null!=mspProtectList&&mspProtectList.size()>0){
					manufacturer = super.getManufacturer(mspProtectList.get(0).getSiteId());
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						//武汉设备转换
					} else {
						operationServiceI = new MspCXServiceImpl();
					}
					result = operationServiceI.excutionDelete(mspProtectList);
					if (ResultString.CONFIG_SUCCESS.equals(result)) {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				} 
			}else {
				throw new Exception("CXMspProtectList is null");
			}
				
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			mspProtectList = null;
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
				operationServiceI = new MspCXServiceImpl();
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
