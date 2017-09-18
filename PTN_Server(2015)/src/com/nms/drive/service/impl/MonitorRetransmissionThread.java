package com.nms.drive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.bean.RetransmissionObject;

public class MonitorRetransmissionThread extends Thread {

	private static Byte retransmissionListLock = 0;// 同步锁
	private List<RetransmissionObject> retransmissionList = new ArrayList<RetransmissionObject>();// 重传命令存放列表
	private PtnDirveService ptnDirveService = null;

	@Override
	public void run() {
		 
	}

	@Override
	public void destroy() {
		// 销毁
	}

	/**
	 * 根据要重发的配置找到相应的命令重新发送
	 */
	private void monitorRetransmission() {

	}

	public void setPtnDirveService(PtnDirveService ptnDirveService) {
		this.ptnDirveService = ptnDirveService;
	}

}
