package com.nms.drive.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class UDPNetworkServer implements DriveServerI {

	private DatagramSocket _datagramSocket = null;
	private DatagramPacket _datagramPacket = null;//发包
	private DatagramPacket datagramPacket = null;//收包
	private String _address = "";
	private int _port = 0;

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			_datagramSocket.close();
			_datagramSocket = null;
			_datagramPacket = null;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 连接UDP服务
	 * 
	 * @param address对方地址
	 * @param port对方端口
	 * @param localPort本地端口
	 *            （如果不给会自动分配）
	 * @throws Exception
	 */
	public void connection(String address, int port, int localPort) throws Exception {
		try {
			_address = address;
			_port = port;
			InetAddress source = InetAddress.getByName(ConstantUtil.serviceIp);
			if (localPort > 0) {
				_datagramSocket = new DatagramSocket(localPort,source);
			} else {
				_datagramSocket = new DatagramSocket();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 发送命令
	 * 
	 * @param command命令
	 */
	public void send(byte[] command,String destinationIP) {
		try {
			InetAddress address = InetAddress.getByName(destinationIP);
			_datagramPacket = new DatagramPacket(command, command.length, address, _port);
			_datagramSocket.send(_datagramPacket);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 获得命令
	 * 
	 * @param length获得命令长度
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] get(int length) throws Exception {
		byte[] recBuf = new byte[length];
		
		try {
			datagramPacket = new DatagramPacket(recBuf, recBuf.length);
			_datagramSocket.receive(datagramPacket);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return recBuf;
	}

	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public int get_port() {
		return _port;
	}

	public void set_port(int _port) {
		this._port = _port;
	}

	public DatagramSocket get_datagramSocket() {
		return _datagramSocket;
	}

	public void set_datagramSocket(DatagramSocket socket) {
		_datagramSocket = socket;
	}

	public DatagramPacket get_datagramPacket() {
		return _datagramPacket;
	}

	public void set_datagramPacket(DatagramPacket packet) {
		_datagramPacket = packet;
	}

	public DatagramPacket getDatagramPacket() {
		return datagramPacket;
	}

	public void setDatagramPacket(DatagramPacket datagramPacket) {
		this.datagramPacket = datagramPacket;
	}

	public static void main(String[] args) {
		UDPNetworkServer networkServer = new UDPNetworkServer();
		try {
			networkServer.connection("199.199.10.57", 9735, 9735);
			byte[] commands = networkServer.get(1440);
			String sourceIp = networkServer.getDatagramPacket().getAddress().toString();//设备ip
		System.out.println("sourceIp：：："+sourceIp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
