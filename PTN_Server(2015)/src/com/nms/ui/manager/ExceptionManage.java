package com.nms.ui.manager;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * 异常处理
 * @author kk
 *
 */
public class ExceptionManage {
	
	private static String disposeWay;
	private static Logger logger = null;
	
	@SuppressWarnings("rawtypes")
	public static void dispose(Exception exception, Class clazz){
//		exception.printStackTrace();
		logger = Logger.getLogger(clazz);
		logger.error(getStackTrace(exception));
//		if(exception instanceof RemoteException){
//			DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.NETWORK_CONNECTIONXCEPTION));
//		}
//		if(exception instanceof MySQLNonTransientConnectionException){
//			DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.NETWORK_CONNECTIONXCEPTION));
//			System.exit(0);
//		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void infor(Object msg, Class clazz){
		logger = Logger.getLogger(clazz);
		logger.info(msg);
	}
	
	public static String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
	
	/**
	 * log输出
	* @author kk
	
	* @param   
	
	* @return 
	
	* @Exception 异常对象
	 */
	@SuppressWarnings("rawtypes")
	public static void logDebug(String str, Class clazz){
		logger = Logger.getLogger(clazz);
		logger.debug(str);
	}
	
	public static String getDisposeWay() {
		
		if(null==disposeWay){
			setDisposeWay("run");
		}
		
		return disposeWay;
	}
	public static void setDisposeWay(String disposeWay) {
		ExceptionManage.disposeWay = disposeWay;
	}
}
