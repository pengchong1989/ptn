package com.nms.jms.common;


import java.io.Serializable;


/**
 * 消息体
 * 
 * @author Administrator
 * 
 */
public class OpviewMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7288102246627554779L;
	private long occuredTime;// 消息发送时间
	private String messageSource;// 标记
	private Object object;// 消息体

	public OpviewMessage() {
	}

	public OpviewMessage(String messageSource) {
		this.messageSource = messageSource;
	}

	public long getOccuredTime() {
		return this.occuredTime;
	}

	public void setOccuredTime(long occuredTime) {
		this.occuredTime = occuredTime;
	}

	public String getMessageSource() {
		return this.messageSource;
	}

	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}


}