package com.nms.rmi.thread;

import java.rmi.server.UnicastRemoteObject;

import com.nms.rmi.ui.ServiceStartPanel;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.rmi.ui.util.ThreadUtil;
import com.nms.service.impl.dispatch.SiteDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

/**
 * 服务端一键关闭线程
 * 
 * @author kk
 * 
 */
public class StopThread implements Runnable {

	private ServiceStartPanel serviceStartPanel = null;

	public StopThread(ServiceStartPanel serviceStartPanel) {
		this.serviceStartPanel = serviceStartPanel;
	}

	@Override
	public void run() {
		try {
			
			stopThread();
			
			// 登出网元
//			if (null != DBManager.getInstance().getConnection()) {
				SiteDispatch siteDispatch = new SiteDispatch();
				siteDispatch.siteLogout();

				// 关闭数据库连接
//				DBManager.getInstance().getConnection().close();
//				DBManager.setConn(null);
//			}

			// 销毁武汉驱动
			if (null != ConstantUtil.driveService) {
				ConstantUtil.driveService.setAlarmObjectService(null);
				ConstantUtil.driveService.setPerformanceObjectService(null);
				ConstantUtil.driveService.destroy();
				ConstantUtil.driveService = null;
			}

			// 关闭MYSQL服务
			String command = "net stop MySQL5";
			Runtime.getRuntime().exec(command);

			// 关闭rmi接口
			if (null != ServerConstant.registry) {
				UnicastRemoteObject.unexportObject(ServerConstant.registry, true);
				ServerConstant.registry=null;
				ConstantUtil.serviceIp="127.0.0.1";
			}
			
			//防止关闭后马上点击开启。 所以停止2秒钟后再释放按钮
			Thread.sleep(4000);
			
			// 如果为空 说明是主界面关闭按钮触发 直接关闭系统
			if (null != this.serviceStartPanel) {
				this.serviceStartPanel.buttonResult(false, true, "");
			} else {
				System.exit(0);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 释放所有线程
	 * 
	 * @throws Exception
	 */
	private void stopThread() throws Exception {

		try {
			if (null != ThreadUtil.curAlarmTimerTask) {
				ThreadUtil.curAlarmTimerTask.stopThread();
				ThreadUtil.curAlarmTimerTask = null;
			}
			
			if (null != ThreadUtil.siteConnectTask) {
				ThreadUtil.siteConnectTask.stopThread();
				ThreadUtil.siteConnectTask = null;
			}
			
			if (null != ThreadUtil.queryCurrAlarmBySitesTask) {
				ThreadUtil.queryCurrAlarmBySitesTask.stopThread();
				ThreadUtil.queryCurrAlarmBySitesTask = null;
			}

			if (null != ThreadUtil.guardianshipThread) {
				ThreadUtil.guardianshipThread.stopThread();
				ThreadUtil.guardianshipThread = null;
			}
			
		} catch (Exception e) {
			throw e;
		}

	}
}
