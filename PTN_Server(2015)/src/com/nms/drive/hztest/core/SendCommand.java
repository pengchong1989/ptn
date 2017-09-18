package com.nms.drive.hztest.core;

import java.io.OutputStream;

public class SendCommand {

	private TcpNetwork tcpNetwork = null;

	public void write(byte[] bytes) throws Exception {
		try {
			OutputStream outputStream = tcpNetwork.getOutputStream();
			outputStream.write(bytes);
			System.out.println("-----------发送命令：");
			CoreOper.print16String(bytes);
		} catch (Exception e) {
			throw e;
		}
	}

	public void setTcpNetwork(TcpNetwork tcpNetwork) {
		this.tcpNetwork = tcpNetwork;
	}

}
