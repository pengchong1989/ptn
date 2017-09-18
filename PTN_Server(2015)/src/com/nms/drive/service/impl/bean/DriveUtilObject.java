package com.nms.drive.service.impl.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.service.bean.OperationObject;

public class DriveUtilObject {

	private int uuid = 0;// PDU流水号
	private String responseType = "";// 响应数据类型 数据，告警，性能 等等
	private int states = 0;// 状态 0：待发送 1：已发送 2：以获得响应信息
	private int direction = 0;// 方向 0：网关发出的请求 1：代表PTN设备向网关发出的请求
	private Date sendDate = null;// 命令发送时间
	private Date responseDate = null;// 命令响应时间
	private Object ptnDataObject = null;// 命令对应的对象
	private byte[] sendCommands = null;// 待发送命令
	private byte[] responseSendCommands = null;// 命令响应回来的命令
	private List<byte[]> sendCommandList = new ArrayList<byte[]>();// 分页后的命令请求命令
	private Map<String, byte[]> responseSendCommandList = new HashMap<String, byte[]>();// 分页后的响应命令
	private OperationObject operationObject = null;// 回调函数
	private String ptnService = "";
	private String operID = "";// 操作ID
	private int sendCount = 0;// 发送次数，也有用来标记重传次数
	private int responseCount = 0;// 响应次数，也有用来标记重传次数
	private String destinationIP = "";//设备目的IP
	public OperationObject getOperationObject() {
		return operationObject;
	}

	public void setOperationObject(OperationObject operationObject) {
		this.operationObject = operationObject;
	}

	public String getResponseType() {
		return responseType;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public int getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}

	public String getPtnService() {
		return ptnService;
	}

	public void setPtnService(String ptnService) {
		this.ptnService = ptnService;
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public byte[] getSendCommands() {
		return sendCommands;
	}

	public void setSendCommands(byte[] sendCommands) {
		this.sendCommands = sendCommands;
	}

	public byte[] getResponseSendCommands() {
		return responseSendCommands;
	}

	public void setResponseSendCommands(byte[] responseSendCommands) {
		this.responseSendCommands = responseSendCommands;
	}

	public List<byte[]> getSendCommandList() {
		return sendCommandList;
	}

	public void setSendCommandList(List<byte[]> sendCommandList) {
		this.sendCommandList = sendCommandList;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}

	public Map<String, byte[]> getResponseSendCommandList() {
		return responseSendCommandList;
	}

	public void setResponseSendCommandList(Map<String, byte[]> responseSendCommandList) {
		this.responseSendCommandList = responseSendCommandList;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Object getPtnDataObject() {
		return ptnDataObject;
	}

	public void setPtnDataObject(Object ptnDataObject) {
		this.ptnDataObject = ptnDataObject;
	}

	public String getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}

}
