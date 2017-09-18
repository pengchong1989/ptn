package com.nms.ui.ptn.alarm.controller;

import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.wh.SiteWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 每十分钟，给武汉的在线网元进行校时操作
 * @author pc
 *
 */
public class CirculateCurrectTime extends Thread{
	private boolean isRun=false;
	public CirculateCurrectTime(){
		this.setName("CirculateCurrectTime");
		this.start();
	}
	/**
	 * 外部停止线程
	 */
	public void stopThread() {
		this.isRun = false;
	}

	/**
	 * 外部开启线程
	 */
	public void startThread() {
		this.isRun = true;
	}
	
	@Override
	public void run(){
		while(isRun){
			SiteService_MB siteService = null;
			SiteWHServiceImpl siteWHServiceImpl = null;
			List<SiteInst> siteInsts = null;//所有网元
			try {
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteWHServiceImpl = new SiteWHServiceImpl();
				siteInsts = siteService.selectSiteInsts();//查询所有网元
				if(siteInsts != null && siteInsts.size()>0){
					for(SiteInst siteInst : siteInsts){
						siteInst.setL(System.currentTimeMillis());
						siteWHServiceImpl.circulateCurrectTime(siteInst);
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}finally{
				UiUtil.closeService_MB(siteService);
				siteWHServiceImpl = null;
				siteInsts = null;
			}
			//将休眠放至释放连接之后
			try {
				Thread.sleep(600000);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
	}
	
}
