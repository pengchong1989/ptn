package com.nms.drivechenxiao.network;

import java.util.List;

import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**守护进程
 * **/
public class GuardianshipThread extends Thread {
	private boolean isRun = false; // 允许外围修改停止守护进程.

	public GuardianshipThread(){
		this.setName("GuardianshipThread");
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
		while (isRun) {
			try {
				Thread.sleep(10000); // 
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			GuardianshipCXTcp();
		}
	}

	/**
	 * 轮询扫描失败的连接，然后 1 删除内存失败连接，2 更新数据库 3 刷新页面
	 * **/
	public void GuardianshipCXTcp() {
		// 轮询扫描
		List<String> ipList = ConstantUtil.cxDriveService.getTcpNetworkList();
		if (ipList.size() > 0) {
			SiteService_MB siteService = null;
			try {
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			for (String ip : ipList) {
				// 删除内存中坏的连接
				ConstantUtil.cxDriveService.removeTcp(ip);
				// update 数据库表
				try {
					siteService.updateByIp(ip, 0);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				}finally{
					UiUtil.closeService_MB(siteService);
				}
//			// 刷新页面 showTopo(false);
//			try {
//				NetworkElementPanel.getNetworkElementPanel().showTopo(false);
//			} catch (Exception e) {
//				ExceptionManage.dispose(e);
//			}
		}
	}

}
