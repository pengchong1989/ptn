package com.nms.drive.network.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.nms.ui.manager.ExceptionManage;

public class UDPClient {

	public static void main(String[] args) {
		DatagramSocket client;
		try {
			client = new DatagramSocket();
			String sendStr = "你好，我是客户机";
			byte[] sendBuf;
			sendBuf = sendStr.getBytes();
			InetAddress address = InetAddress.getByName("localhost");
			int port = 9999;
			DatagramPacket sendpacket = new DatagramPacket(sendBuf, sendBuf.length, address, port);
			client.send(sendpacket);
			byte[] recBuf = new byte[100];
			DatagramPacket recpacket = new DatagramPacket(recBuf, recBuf.length);
			client.receive(recpacket);
			String str = new String(recpacket.getData(), 0, recBuf.length);
			System.out.println("收到:" + str);
			client.close();
		} catch (Exception e) {

			ExceptionManage.dispose(e,UDPClient.class);
		}

	}

}
