package com.nms.jms.jmsMeanager;


import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

import com.nms.jms.common.OpviewMessage;


/**
 * 发送消息接口
 * @author Administrator
 *	
 */
public class JmsSender {
	private JmsTemplate template;
	private Destination serverOtherNotify;
	
	
	
	/**
	 * 服务进程
	 * @param message
	 */
	public void sendToServer(OpviewMessage message){
		template.convertAndSend(serverOtherNotify, message);
	}
	
	public JmsTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}


	public Destination getServerOtherNotify() {
		return serverOtherNotify;
	}

	public void setServerOtherNotify(Destination serverOtherNotify) {
		this.serverOtherNotify = serverOtherNotify;
	}
	
}
