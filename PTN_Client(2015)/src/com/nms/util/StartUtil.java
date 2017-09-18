package com.nms.util;

import com.nms.model.util.CodeConfigItem;
import com.nms.rmi.ui.util.BeforInstall;
import com.nms.ui.manager.ExceptionManage;

/**
 * 程序启动程序辅助类
 * 
 * @author kk
 * 
 */
public class StartUtil {

	/**
	 * 服务器进程properties配置节点名称
	 */
	private final String SERVER_TASK = "serverTask";
	/**
	 * 客户端进程properties配置节点名称
	 */
	private final String CLIENT_TASK = "clientTask";

	/**
	 * 验证服务端是否启动
	 * 
	 * @return true 启动 false 没启动
	 */
	public boolean checkingServer() {
		return this.checking(this.SERVER_TASK);
	}

	/**
	 * 验证客户端是否启动
	 * 
	 * @return true 启动 false 没启动
	 */
	public boolean checkingClient() {
		return this.checking(this.CLIENT_TASK);
	}

	/**
	 * 验证是否启动
	 * 
	 * @param taskXmlNodeName
	 *            进程的properties配置节点名称
	 * @return
	 */
	private boolean checking(String taskNodeName) {
		String taskName = null; // 进程名
		CodeConfigItem codeConfigItem = null;
		BeforInstall beforInstall = null;
		String result = null;
		boolean flag = true;
		try {
			//读取properties获取进程名
			codeConfigItem = CodeConfigItem.getInstance();
			taskName = codeConfigItem.getValueByKey(taskNodeName);
			
			//如果取到的进程名不为null  验证进程是否启动。
			if (null != taskName) {
				beforInstall = new BeforInstall();
				result = beforInstall.ishaveThread(taskName);
				if (null == result) {
					flag = false;
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			taskName = null; // 进程名
			codeConfigItem = null;
			beforInstall = null;
			result = null; 
		}
		return flag;
	}
}
