package com.nms.rmi.ui.util;

import com.nms.drivechenxiao.network.GuardianshipThread;
import com.nms.ui.ptn.alarm.controller.CirculateCurrectTime;
import com.nms.ui.ptn.alarm.controller.CurAlarmTimerTask;
import com.nms.ui.ptn.alarm.controller.OperateCurrAlarmTask;
import com.nms.ui.ptn.alarm.controller.QueryCurrAlarmBySitesTask;
import com.nms.ui.ptn.alarm.controller.SiteConnectTask;
import com.nms.ui.ptn.performance.thread.PerformanceTimerThread;

/**
 * 服务端线程常量类
 * @author kk
 *
 */
public class ThreadUtil {
	
	public static CurAlarmTimerTask curAlarmTimerTask=null;
	public static SiteConnectTask siteConnectTask=null;
	public static QueryCurrAlarmBySitesTask queryCurrAlarmBySitesTask=null;
	public static GuardianshipThread guardianshipThread=null;
	public static OperateCurrAlarmTask operateCurrAlarmTask = null;
	public static CirculateCurrectTime circulateCurrectTime = null;
	public static PerformanceTimerThread  performanceTimerThread = null;
}
