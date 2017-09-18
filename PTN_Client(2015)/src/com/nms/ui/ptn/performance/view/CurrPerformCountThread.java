package com.nms.ui.ptn.performance.view;

import java.util.List;

import com.nms.db.bean.perform.CurrPerformCountInfo;
import com.nms.ui.manager.ExceptionManage;

public class CurrPerformCountThread implements Runnable {
	
	private boolean suspendFlag = false;// 控制线程的执行
	private boolean flag = true;
	private CurrPerformCountFilterDialog dialog = null;
	private CurrPerformCountPanel view = null;
	
	public CurrPerformCountThread(CurrPerformCountFilterDialog dialog, CurrPerformCountPanel view){
		this.dialog = dialog;
		this.view = view;
	}
	
	@Override
	public void run() {
		while(this.flag){
			try {
				this.queryPerformance();
				synchronized (this) {
					while (suspendFlag)
						wait();
				}
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		this.view.clear();
	}

	private void queryPerformance() {
		List<CurrPerformCountInfo> performList = this.dialog.getCurrPerformCount();
		if(this.flag){
			this.view.getTable().addData(performList);
		}
	}
	
	/**
	 * 线程停止
	 */
	public void stopThread(){
		this.flag = false;
	}
	
	/**
	 * 线程暂停
	 */
	public void setSuspendFlag() {
		this.suspendFlag = true;
	}

	/**
	 * 唤醒线程
	 */
	public synchronized void setResume() {
		this.suspendFlag = false;
		notify();
	}
	
	/**
	 * 返回线程状态
	 * @return
	 */
	public boolean getSuspendFlag(){
		return this.suspendFlag;
	}
}
