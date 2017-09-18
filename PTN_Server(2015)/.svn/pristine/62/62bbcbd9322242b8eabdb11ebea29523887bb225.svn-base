package com.nms.drivechenxiao.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.service.bean.AlarmObjectService;

public class CXPtnDirveService {

	protected int seqId = 999;
	protected static byte[] lockSeqId = new byte[0];

	protected Map<String, CXMonitorSendCommandThread> cXMonitorSendCommandThreadMap = new HashMap<String, CXMonitorSendCommandThread>();
	protected Map<String, CXMonitorResponseThread> cXMonitorResponseThreadMap = new HashMap<String, CXMonitorResponseThread>();
	protected Map<String, CXMonitorCallbackThread> cXMonitorCallbackThreadMap = new HashMap<String, CXMonitorCallbackThread>();
	protected Map<String, TcpNetwork> tcpNetworkList = new HashMap<String, TcpNetwork>();
	public AlarmObjectService alarmObjectService = null;

	public void setAlarmObjectService(AlarmObjectService alarmObjectService) {
		this.alarmObjectService = alarmObjectService;
	}

	protected void init(CXNEObject cxNeObject) throws Exception {
		// 初始化各种监控线程
		try {
			TcpNetwork tcpNetwork = new TcpNetwork();
			tcpNetwork.connect(cxNeObject.getNeIp(), cxNeObject.getNePort());
			tcpNetworkList.put(cxNeObject.getNeIp(), tcpNetwork);

			CXMonitorSendCommandThread cXMonitorSendCommandThread = new CXMonitorSendCommandThread();
			cXMonitorSendCommandThread.setCXPtnDirveService(this);
			cXMonitorSendCommandThread.setTcpNetwork(tcpNetwork);
			cXMonitorSendCommandThread.start();
			cXMonitorSendCommandThreadMap.put(cxNeObject.getNeIp(), cXMonitorSendCommandThread);

			CXMonitorResponseThread cXMonitorResponseThread = new CXMonitorResponseThread();
			cXMonitorResponseThread.setCXPtnDirveService(this);
			cXMonitorResponseThread.setCXMonitorSendCommandThread(cXMonitorSendCommandThread);
			cXMonitorResponseThread.setTcpNetwork(tcpNetwork);
			cXMonitorResponseThread.start();
			cXMonitorResponseThreadMap.put(cxNeObject.getNeIp(), cXMonitorResponseThread);

			CXMonitorCallbackThread cXMonitorCallbackThread = new CXMonitorCallbackThread();
			cXMonitorCallbackThread.setCXPtnDirveService(this);
			cXMonitorCallbackThread.setCXMonitorResponseThread(cXMonitorResponseThread);
			cXMonitorCallbackThread.setAlarmObjectService(alarmObjectService);
			cXMonitorCallbackThread.start();
			cXMonitorCallbackThreadMap.put(cxNeObject.getNeIp(), cXMonitorCallbackThread);

		} catch (Exception e) {
			throw e;
		}
	}

	protected void destroy(CXNEObject cxNeObject) {
		try {
			CXMonitorSendCommandThread cXMonitorSendCommandThread = cXMonitorSendCommandThreadMap.get(cxNeObject.getNeIp());
			if (cXMonitorSendCommandThread != null) {
				cXMonitorSendCommandThread.destroy();
				cXMonitorSendCommandThread.stop();
				cXMonitorSendCommandThread = null;
			}

			CXMonitorResponseThread cXMonitorResponseThread = cXMonitorResponseThreadMap.get(cxNeObject.getNeIp());
			if (cXMonitorResponseThread != null) {
				cXMonitorResponseThread.destroy();
				cXMonitorResponseThread.stop();
				cXMonitorResponseThread = null;
			}

			CXMonitorCallbackThread cXMonitorCallbackThread = cXMonitorCallbackThreadMap.get(cxNeObject.getNeIp());
			if (cXMonitorCallbackThread != null) {
				cXMonitorCallbackThread.destroy();
				cXMonitorCallbackThread.stop();
				cXMonitorCallbackThread = null;
			}
			TcpNetwork tcpNetwork = tcpNetworkList.get(cxNeObject.getNeIp());
			if (tcpNetwork != null) {
				tcpNetwork.close();
				tcpNetworkList.remove(cxNeObject.getNeIp());
			}
		} catch (Exception e) {
		}
	}

	protected int getSeqId() {
		synchronized (lockSeqId) {
			return ++seqId;
		}
	}

	/**
	 * 日志输出
	 * 
	 * @param classObj
	 *            类对象
	 * @param e
	 *            异常对象
	 * @param type
	 *            异常类型
	 */
	public void logPrintln(Class classObj, Exception e, String type) {
		logPrintln(classObj, e.toString(), type);
	}

	/**
	 * 日志输出
	 * 
	 * @param classObj
	 *            类对象
	 * @param logs
	 *            异常对象
	 * @param type
	 *            异常类型
	 */
	public void logPrintln(Class classObj, String logs, String type) {
		String typeStr = "调试信息：";
		if ("error".equalsIgnoreCase(type)) {
			typeStr = "异常信息:";
		}
		System.out.println(typeStr + "【" + classObj.getName() + "】 " + logs);
	}

}
