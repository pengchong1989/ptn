package com.nms.drive.hztest.core;

import java.io.InputStream;

import com.nms.drive.hztest.core.ui.HzPtnMainUI;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class MonitorResponseThread extends Thread {

	private HzPtnMainUI hzPtnMainUI = null;

	public void setHzPtnMainUI(HzPtnMainUI hzPtnMainUI) {
		this.hzPtnMainUI = hzPtnMainUI;
	}

	private TcpNetwork tcpNetwork = null;
	private byte[] command = new byte[0];

	@Override
	public void run() {
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
				// System.out.println("当前收到的命令字节数：" + command.length);

				cfml();// 拆分命令

				Thread.sleep(100);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
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

				hzPtnMainUI.getJTextArea1().setText(hzPtnMainUI.getJTextArea1().getText() + "--------------------------------------------\r\n" + CoreOper.print16String(oneCommand) + "\r\n");
				hzPtnMainUI.tbgd();
				hzPtnMainUI.zdgd();

				byte[] newCommand = new byte[command.length - len];
				System.arraycopy(command, len, newCommand, 0, newCommand.length);
				command = newCommand;

				// try {
				// CoreOper coreOper = new CoreOper();
				// coreOper.analysisCxtOpBuffer(oneCommand);
				// } catch (Exception e) {
				// System.out.println("--------11111111111111");
				// }

				if (command.length <= 8) {
					break;
				}
			}
		}
	}

	public void setTcpNetwork(TcpNetwork tcpNetwork) {
		this.tcpNetwork = tcpNetwork;
	}

}
