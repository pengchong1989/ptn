package com.nms.drive.network.ui;

import java.util.Date;

import com.nms.ui.manager.ExceptionManage;

public class MessageTh extends Thread {

	private UDPServer UDPServer = null;

	@Override
	public void run() {
		try {
			byte[] buf = null;
			while (true) {
				UDPServer.getJLabel3().setText("上次消息 ." + new Date().toGMTString());
				buf = UDPServer.UDPNetworkServer.get(5000);
				if (buf != null && buf.length > 0) {
					UDPServer.getJTextArea1().setText(UDPServer.getJTextArea1().getText() + "\r\n");
					UDPServer.getJTextArea1().setText(UDPServer.getJTextArea1().getText() + "【" + UDPServer.UDPNetworkServer.get_datagramPacket().getAddress() + "】【" + UDPServer.UDPNetworkServer.get_datagramPacket().getPort() + "】");
					for (int i = 0; i < buf.length; i++) {
						if (String.valueOf(buf[i]) != null && !"0".equals(String.valueOf(buf[i]))) {
							UDPServer.getJTextArea1().setText(UDPServer.getJTextArea1().getText() + String.valueOf(buf[i]) + ",");

							// UDPServer.getJTextArea1().append(String.valueOf(buf[i])
							// + ",");
						}
					}
					UDPServer.getJTextArea1().setText(UDPServer.getJTextArea1().getText() + "\r\n" + "--------------------------");
					UDPServer.getJTextArea1().setCaretPosition(UDPServer.getJTextArea1().getText().length());

				} else {
					UDPServer.getJLabel3().setText("接收到的信息为空！");
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public UDPServer getUDPServer() {
		return UDPServer;
	}

	public void setUDPServer(UDPServer server) {
		UDPServer = server;
	}

}
