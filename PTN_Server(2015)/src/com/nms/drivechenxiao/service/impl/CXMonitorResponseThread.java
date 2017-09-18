package com.nms.drivechenxiao.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.impl.bean.CXDriveUtilObject;
import com.nms.service.bean.OperationObject;

public class CXMonitorResponseThread extends Thread {

	private static Byte responseListLock = 0;// 响应列表同步锁
	private CXPtnDirveService cXPtnDirveService = null; 
	private CXMonitorSendCommandThread cXMonitorSendCommandThread = null;

	private byte[] command = new byte[0];// 临时存储
	// 已收到响应结束的命令
	private ConcurrentHashMap<String, CXDriveUtilObject> commmandListMap = new ConcurrentHashMap<String, CXDriveUtilObject>();
	private boolean pd = true;

	private TcpNetwork tcpNetwork = null;

	public void setTcpNetwork(TcpNetwork tcpNetwork) {
		this.tcpNetwork = tcpNetwork;
	}

	@Override
	public void run() {
		while (pd) {
			try {

				InputStream inputStream = tcpNetwork.getInputStream();
				byte[] tempBytes = new byte[1024];
				byte[] commandTemp = null;
				int readByteCount = 0;
				while (true) {
					readByteCount = inputStream.read(tempBytes);
					commandTemp = new byte[command.length + readByteCount];
					System.arraycopy(command, 0, commandTemp, 0, command.length);
					System.arraycopy(tempBytes, 0, commandTemp, command.length, readByteCount);
					command = commandTemp;

					cfml();// 拆分命令
				}

			} catch (Exception e) {
//				ExceptionManage.dispose(e,this.getClass());
				cXPtnDirveService.logPrintln(this.getClass(), e, "error");
				pd=false;
			}
			try {
				Thread.sleep(50);// 休眠防止拥塞
			} catch (InterruptedException e) {
			}
		}
	}

	private void cfml() {
		if (command != null && command.length > 8) {
			while (true) {
				byte[] lenbyte = new byte[] { command[4], command[5], command[6], command[7] };
				int len = CoderUtils.bytesToInt(lenbyte) + 8;
				if (command.length < len) {
					return;
				}
				byte[] oneCommand = new byte[len];
				System.arraycopy(command, 0, oneCommand, 0, len);

				// 获得返回命令
				// CoreOper.print16String(oneCommand);
				int seqId = CoderUtils.bytesToInt(new byte[] { oneCommand[17], oneCommand[18], oneCommand[19], oneCommand[20] });
				CXDriveUtilObject cXDriveUtilObject = cXMonitorSendCommandThread.getDriveUtilObject(seqId + "");

				if (cXDriveUtilObject != null) {
					cXDriveUtilObject.setResponseSendCommands(oneCommand);
					cXDriveUtilObject.setStates(2);// 标记为响应完整
					cXDriveUtilObject.setResponseDate(new Date());
					putCXDriveUtilObject(seqId + "", cXDriveUtilObject);
				} else {
					cXDriveUtilObject = new CXDriveUtilObject();
					cXDriveUtilObject.setDirection(1);// 主动响应
					cXDriveUtilObject.setResponseSendCommands(oneCommand);
					cXDriveUtilObject.setStates(2);// 标记为响应完整
					cXDriveUtilObject.setResponseDate(new Date());
					cXDriveUtilObject.setActionId("9999");
					cXDriveUtilObject.setSeqId("ZDTS" + new Date().getTime() + "");
					cXDriveUtilObject.setOperationObject(new OperationObject());// 临时的

					CXNEObject cXNEObject = new CXNEObject();
					cXNEObject.setNeIp(this.tcpNetwork.getClientSocket().getInetAddress().getHostAddress());
					cXDriveUtilObject.setCXNEObject(cXNEObject);

					putCXDriveUtilObject(cXDriveUtilObject.getSeqId(), cXDriveUtilObject);
				}

				byte[] newCommand = new byte[command.length - len];
				System.arraycopy(command, len, newCommand, 0, newCommand.length);
				command = newCommand;

				if (command.length <= 8) {
					break;
				}
			}
		}
	}

	@Override
	public void destroy() {
		pd = false;// 停止线程
	}

	/**
	 * 获得全部响应完成的命令对象
	 * 
	 * @return
	 */
	public ConcurrentHashMap<String, CXDriveUtilObject> getCXDriveUtilObjectList() {
		synchronized (responseListLock) {
			return commmandListMap;
		}
	}

	/**
	 * 通过SeqID删除响应完成的命令对象
	 * 
	 * @param seqId
	 */
	public void removeCXDriveUtilObject(String seqId) {
		synchronized (responseListLock) {
			commmandListMap.remove(seqId);
		}
	}

	/**
	 * 添加已响应完成 CXDriveUtilObject
	 * 
	 * @param seqId
	 * @param cXDriveUtilObject
	 */
	private void putCXDriveUtilObject(String seqId, CXDriveUtilObject cXDriveUtilObject) {
		synchronized (responseListLock) {
			commmandListMap.put(seqId, cXDriveUtilObject);
		}
	}

	public CXPtnDirveService getCXPtnDirveService() {
		return cXPtnDirveService;
	}

	public void setCXPtnDirveService(CXPtnDirveService ptnDirveService) {
		cXPtnDirveService = ptnDirveService;
	}

	public void setCXMonitorSendCommandThread(CXMonitorSendCommandThread monitorSendCommandThread) {
		cXMonitorSendCommandThread = monitorSendCommandThread;
	}
}
