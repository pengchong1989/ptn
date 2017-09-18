package com.nms.service.notify;

public class Message {

	public enum MessageType {
		CREATION, DELETION, ATTRIBUTECHG, STATECHG, ALARM, TCA, PSE, EPSE
	};

	//标识创建，删除，修改通知等
	private MessageType msgType;

	// 标识事件对象
	private String msgObjName;
	
	// 标识子事件类型
	private String subEventName;

	// 具体消息对象
	private Object msgBody;

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}
	
	// 子事件类型
	public String getSubEventName() {
		return subEventName;
	}
	
	// 子事件类型
	public void setSubEventName(String subEventName) {
		this.subEventName = subEventName;
	}
	
	// managedElement,EQ PTP
	public String getMsgObjName() {
		return msgObjName;
	}

	// managedElement,EQ PTP
	public void setMsgObjName(String objName) {
		msgObjName = objName;
	}

	// 具体消息对象
	public Object getMsgBody() {
		return msgBody;
	}

	// 具体消息对象
	public void setMsgBody(Object obj) {
		msgBody = obj;
	}

}
