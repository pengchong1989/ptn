package com.nms.drive.network.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.nms.ui.manager.ExceptionManage;

public class UDPServer {

	public static void main(String[] args) {
		DatagramSocket server;
		try {
			server = new DatagramSocket(9999);
			byte[] buf = new byte[10];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			server.receive(packet);
			String str = new String(packet.getData(), 0, buf.length);
			System.out.println("Hello!" + str);
			int port = packet.getPort();
			InetAddress address = packet.getAddress();
			String str1 = "我是服务器，我广播信息给你";
			byte[] sendBuf;
			sendBuf = str1.getBytes();
			DatagramPacket sendpacket = new DatagramPacket(sendBuf, sendBuf.length, address, port);
			server.send(sendpacket);
			server.close();
		} catch (Exception e) {

			ExceptionManage.dispose(e,UDPServer.class);
		}
	}

}
