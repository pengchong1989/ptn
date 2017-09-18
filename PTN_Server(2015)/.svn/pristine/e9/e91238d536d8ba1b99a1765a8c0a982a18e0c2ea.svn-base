package com.nms.ui.ptn.alarm.controller;

import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.wh.AlarmWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 网管轮询设备所有未失连网元的当前告警
 * @author pc
 *
 */
public class QueryCurrAlarmBySitesTask extends Thread{

	private boolean isRun=false;
	private int siteCount = 1;//根据网元个数，动态变化轮询间隔
	public QueryCurrAlarmBySitesTask(){
		this.setName("QueryCurrAlarmBySitesTask");
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
	public void run() {
		while(isRun){
			SiteService_MB siteService = null;
			AlarmWHServiceImpl alarmWHServiceImpl = null;
			List<SiteInst> siteInsts = null;//武汉所有在线网元
			try {
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				alarmWHServiceImpl = new AlarmWHServiceImpl();
				siteInsts = siteService.selectSiteInsts();//查询所有网元
				siteCount = (siteInsts.size()/10+1);
				alarmWHServiceImpl.queryCurrAlarmBySites(siteInsts);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}finally{
				UiUtil.closeService_MB(siteService);
			}
			try {
				Thread.sleep(15*60*1000);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
	}

}
