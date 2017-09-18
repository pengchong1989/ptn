package com.nms.jms.jmsMeanager;


import java.net.InetAddress;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nms.ui.manager.ExceptionManage;

/**
 * 启动borker
 * @author Administrator
 *
 */
public class Broker {
    private static BrokerService broker = new BrokerService();
    public static void init(String path) throws Exception { 
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { path });  
        broker = (BrokerService) ctx.getBean("broker");  
        try {  
        	InetAddress addr = InetAddress.getLocalHost();
            String serverIp = addr.getHostAddress().toString();
            String jmsIp = "tcp://" + serverIp + ":61616";
            broker.setBrokerName("myBroker");  
            broker.setUseJmx(true);  
            broker.setSchedulerSupport(false);
            broker.addConnector(jmsIp);  
            broker.start();  
            ExceptionManage.infor("DATAX:   broker start", Broker.class);
        } catch (InterruptedException e) {  
        	ExceptionManage.dispose(e, Broker.class);
        } catch (Exception e) {  
        	ExceptionManage.dispose(e, Broker.class);
        }  
    }  
}
