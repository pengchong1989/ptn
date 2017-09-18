package com.nms.service.impl.util;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.base.DispatchBase;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
	
/**
 * 虚拟网元实现的方法，不下发设备，只入库
 * @author dzy
 *
 */
public class SiteUtil {
	
	/**
	 * 验证网元类型
	 * 
	 * @param SiteId  网元ID
	 * 
	 * @return  0为真是网元 、1为虚拟网元或离线网元
	 * @throws Exception 
	 */
	public int SiteTypeUtil(int siteId) throws Exception {
		int flag =0;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		try {
		    if (0 != siteId)
		    {
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteInst = (SiteInst) siteService.select(siteId);
				if(null!=siteInst && "1".equals(UiUtil.getCodeById(siteInst.getSiteType()).getCodeValue())||0==siteInst.getLoginstatus()){
					flag =1;
				}
			}else
			{
				flag =1;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,SiteUtil.class);
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	
		return flag;
	}
	

	public boolean isNeOnLine(int siteId) throws Exception
	{
		boolean flag = false;
		SiteService_MB service = null;
		try
		{
			service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(service.queryNeStatus(siteId) == 1)
			{
				flag=true;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			UiUtil.closeService_MB(service);
		}
		
		return flag;
		
	}

	/**
	 * 根据网元ID获取网元厂商
	 * @param siteId 网元主键
	 * @return 厂商
	 * @throws Exception
	 */
	public int getManufacturer(int siteId) throws Exception{
		int manufacturer=0;
		SiteService_MB siteService= null;
		SiteInst siteInst=null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst=siteService.select(siteId);
			if(siteInst == null){
				throw new Exception("根据ID查询网元出错");
			}
			manufacturer=Integer.parseInt(UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeValue());
		} catch (Exception e) {
			ExceptionManage.dispose(e,DispatchBase.class);
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return manufacturer;
	}
	
	
	
	/**
	 * 验证网元是否为在线脱管网元
	 * 
	 * @param SiteId  网元ID
	 * 
	 * @return  1为在线脱管网元
	 * @throws Exception 
	 */
	public int SiteTypeOnlineUtil(int siteId) throws Exception {
		int flag =0;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		try {
		    if (0 != siteId)
		    {
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteInst = new SiteInst();
				siteInst = (SiteInst) siteService.select(siteId);
				if(null!=siteInst && "0".equals(UiUtil.getCodeById(siteInst.getSiteType()).getCodeValue())&& 0==siteInst.getLoginstatus()){
					flag =1;
				}
			}else
			{
				flag =1;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,SiteUtil.class);
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return flag;
	}
}	