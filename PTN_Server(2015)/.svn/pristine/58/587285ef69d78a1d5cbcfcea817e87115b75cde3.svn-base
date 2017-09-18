package com.nms.service.impl.base;

import java.util.Date;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class OperationBase {

	/** 超时时间 秒 */
	private int timeout = 30000;

	/**
	 * 是否超时
	 * 
	 * @return
	 */
	public boolean isTimeOut(Date date) {

		long nowDate = new Date().getTime();
		long timeoutDate = date.getTime() + this.timeout;
		if (nowDate >= timeoutDate) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isTimeOut(Date date,int time) {

		long nowDate = new Date().getTime();
		long timeoutDate = date.getTime() + time;
		if (nowDate >= timeoutDate) {
			return true;
		} else {
			return false;
		}
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
}
