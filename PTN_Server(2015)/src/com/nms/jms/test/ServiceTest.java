package com.nms.jms.test;

import com.nms.jms.common.ApplicationBeanFactory;



public class ServiceTest {
	public static void main(String[] args) throws Exception {
		ApplicationBeanFactory.initBeanFactory("applicationContext-jms-send.xml","targetConnectionFactory");
		Thread1 thread1 = new Thread1("alarm");
		new Thread(thread1).start();
		Thread1 thread2 = new Thread1("service");
		new Thread(thread2).start();
	}
}
