package com.nms.drivechenxiao.test.core;

import java.io.InputStream;
import java.io.OutputStream;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.network.TcpNetwork;

public class SendCommand {

	private TcpNetwork tcpNetwork = null;

	public void write(byte[] bytes) throws Exception {
		try {
			OutputStream outputStream = tcpNetwork.getOutputStream();
			outputStream.write(bytes);
			System.out.println("-----------发送命令：");
			CoreOper.print16String(bytes);
			InputStream inputStream = tcpNetwork.getInputStream();
//			while(inputStream.){
//				
//			}
//			System.out.println("inputStream = "+inputStream.);
		} catch (Exception e) {
			throw e;
		}
	}

	public void setTcpNetwork(TcpNetwork tcpNetwork) {
		this.tcpNetwork = tcpNetwork;
	}

}
