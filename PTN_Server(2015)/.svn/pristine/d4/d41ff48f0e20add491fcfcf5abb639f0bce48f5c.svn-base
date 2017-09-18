package com.nms.jms.jmsMeanager;


import com.nms.jms.common.ApplicationBeanFactory;
import com.nms.jms.common.OpviewMessage;


/**
 * 发送消息工具类
 * @author Administrator
 *
 */
 public class JmsUtil{
	 
	 /**
	  * 服务进程上报
	  * @param opviewMessage
	  */
	 public static void sendService(OpviewMessage opviewMessage){
		 JmsSender jmsSender = (JmsSender) ApplicationBeanFactory.getBean("JmsSender");
		 jmsSender.sendToServer(opviewMessage);
	 }
	 
 }