package com.nms.db.bean.system;

import java.io.Serializable;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;

/**
 * 枚举类
 * 表 unload_manager中
 * unloadType 数字转义成String
 * @author Administrator
 *
 */
public class UnLoadFactory  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String trans(int unloadType){
		switch (unloadType) {
		case UnLoads.SITE:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORY_ALARM);//历史告警（1）
		case UnLoads.SLOT:
			return ResourceUtil.srcStr(StringKeysTab.TAB_HISPERFOR);//历史性能（2）
		case UnLoads.CARD:
			return ResourceUtil.srcStr(StringKeysBtn.BTN_LOGIN_LOG);//操作日志记录（3）			
		case UnLoads.LOGIN:
//			return ResourceUtil.srcStr(StringKeysBtn.BTN_LOGIN_LOG);//登录日志记录（3）
			return ResourceUtil.srcStr(StringKeysBtn.BTN_LOGIN_OPER_LOG);//登录日志记录（3）
//		case UnLoads.FOUR:
//			return ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORY_DAY_PERFORMANCE);//操作日志记录（3）			
//		
		}
		return null;
	}
}
