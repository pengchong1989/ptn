package com.nms.drivechenxiao.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.impl.bean.CXDriveUtilObject;
import com.nms.service.bean.ActionObject;
import com.nms.ui.manager.ExceptionManage;

public class CXMonitorSendCommandThread extends Thread {

	private static Byte commmandListLock = 0;// 发送同步锁 
	private static Byte commmandListLock2 = 0;// 已发送同步锁
	private CXPtnDirveService cXPtnDirveService = null;
	private List<CXDriveUtilObject> commmandList = new ArrayList<CXDriveUtilObject>();// 待发送
	private Map<String, CXDriveUtilObject> commmandListMap = new HashMap<String, CXDriveUtilObject>();// 已发送
	private boolean pd = true;

	private TcpNetwork tcpNetwork = null;

	public void setTcpNetwork(TcpNetwork tcpNetwork) {
		this.tcpNetwork = tcpNetwork;
	}

	@Override
	public void run() {
		new CommandWatch().start(); //开启守护线程  add by stones 20130712
		while (pd) {
			try {
				monitorSendCommand();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
				cXPtnDirveService.logPrintln(this.getClass(), e, "error");
			}
			try {
				Thread.sleep(50);// 休眠防止拥塞
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void destroy() {
		pd = false;// 停止线程
	}

	/**
	 * 监控是否有要发送的命令
	 */
	private void monitorSendCommand() {
		synchronized (commmandListLock) {
			CXDriveUtilObject cXDriveUtilObject = null;
			OutputStream outputStream = null;
			byte[] commands = null;

			for (int i = 0; i < commmandList.size(); i++) {
				cXDriveUtilObject = commmandList.get(i);
				if (cXDriveUtilObject.getStates() == 0) {// 待发送
					commands = cXDriveUtilObject.getSendCommands();// 分页后的命令请求命令

					if (tcpNetwork != null) {
						outputStream = tcpNetwork.getOutputStream();
						try {
							outputStream.write(commands);
							// CoreOper.print16String(commands);// 打印发送命令
						} catch (Exception e) {
							cXPtnDirveService.logPrintln(this.getClass(), e, "error");
						}

						// 标记为已发送
						cXDriveUtilObject.setStates(1);
						cXDriveUtilObject.setSendTimeL(System.currentTimeMillis());
						// 移动到已发送列表中
						// System.out.println("(cXDriveUtilObject.getSeqId() :" + cXDriveUtilObject.getSeqId());
						putCXDriveUtilObject(cXDriveUtilObject.getSeqId() + "", cXDriveUtilObject);
					} else {
						cXPtnDirveService.logPrintln(this.getClass(), "【" + cXDriveUtilObject.getCXNEObject().getNeIp() + "】网元连接不存在！", "error");
					}
				}
			}
			commmandList.clear();
		}
	}

	/**
	 * 追加要发送的命令
	 * 
	 * @param cXDriveUtilObject
	 */
	public void addDriveUtilObject(CXDriveUtilObject cXDriveUtilObject) {
		synchronized (commmandListLock) {
			commmandList.add(cXDriveUtilObject);
		}
	}

	/**
	 * 通过SeqID获得已发送命令对象
	 * 
	 * @param pduUUID
	 * @return
	 */
	public CXDriveUtilObject getDriveUtilObject(String seqId) {
		synchronized (commmandListLock2) {
			return commmandListMap.get(seqId);
		}
	}

	/**
	 * 通过SeqID删除已发送命令对象
	 * 
	 * @param pduUUID
	 */
	public void removeDriveUtilObject(String seqId) {
		synchronized (commmandListLock2) {
			commmandListMap.remove(seqId);
		}
	}

	/**
	 * 添加已发送 CXDriveUtilObject
	 * 
	 * @param seqId
	 * @param driveUtilObject
	 */
	private void putCXDriveUtilObject(String seqId, CXDriveUtilObject cXDriveUtilObject) {
		synchronized (commmandListLock2) {
			commmandListMap.put(seqId, cXDriveUtilObject);
		}
	}

	public void setCXPtnDirveService(CXPtnDirveService cXPtnDirveService) {
		this.cXPtnDirveService = cXPtnDirveService;
	}
	/**add by stones for watch commmandListMap for time out....
	 * 20130713
	 * **/
	private class CommandWatch extends Thread{
		@Override
		public void run(){
			CXDriveUtilObject cxTemp = null;
			ActionObject acTemp = null;
			while (pd) {
				try {
					if(!commmandListMap.isEmpty()){
						Iterator<CXDriveUtilObject> it = commmandListMap.values().iterator();
						while(it.hasNext()){
							cxTemp = it.next();
							long timeNow = System.currentTimeMillis();
							if((timeNow-cxTemp.getSendTimeL())>=3000){
								Iterator<ActionObject> actionIt = cxTemp.getOperationObject().getActionObjectList().iterator();
								while(actionIt.hasNext()){
									acTemp = actionIt.next();
									if(cxTemp.getActionId().equals(acTemp.getActionId())){
										acTemp.setType(" time out ...");
									}
								}
							}
						}						
						cxTemp = null;
						acTemp = null;
					}					
				} catch (Exception e) {
					cxTemp = null;
					acTemp = null;
					cXPtnDirveService.logPrintln(this.getClass(), e, "error");
				}
				try {
					Thread.sleep(1000);// 休眠防止拥塞 1s
				} catch (Exception e) {
					
				}
			}
		}
	}	
}
