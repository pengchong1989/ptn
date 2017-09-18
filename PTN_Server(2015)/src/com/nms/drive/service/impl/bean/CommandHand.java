package com.nms.drive.service.impl.bean;

public class CommandHand {

	private String commandLable = "";// 命令码
	private int length = 0;// 数据长度
	private int direction = 0;// 方向 0：网关发出的请求 1：代表PTN设备向网关发出的请求

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getCommandLable() {
		return commandLable;
	}

	public void setCommandLable(String commandLable) {
		this.commandLable = commandLable;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
