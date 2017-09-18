package com.nms.snmp.ninteface.framework.trap;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.snmp.ninteface.framework.MOTabelMgr;
import com.nms.ui.manager.ExceptionManage;


public class SnmpNotifyHanderMgr {

	private static Logger LOG = Logger.getLogger(SnmpNotifyHanderMgr.class
			.getClass());
	private static SnmpNotifyHanderMgr instance = new SnmpNotifyHanderMgr();
	
	private HashMap<String, String> notifyHandlerNameMap = new HashMap<String, String>();
	private HashMap<String, INotifyHandler> notifyHandlerMap = new HashMap<String, INotifyHandler>();

	private SnmpNotifyHanderMgr() {

	}

	public static SnmpNotifyHanderMgr getInstance() {
		return instance;
	}

	public void init() {
		try {
			DocumentBuilderFactory factory = null;
			DocumentBuilder builder = null;
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(MOTabelMgr.class.getClassLoader().getResource("config/notify_handler.xml").toString());
			Element root = document.getDocumentElement();
			for (int j = 0; j < root.getElementsByTagName("INotifyCvtHandler").getLength(); j++) {
				Element iter = (Element) root.getElementsByTagName("INotifyCvtHandler").item(j);
				String msgObjName = iter.getAttribute("msgObjName");
				String className = iter.getAttribute("className");
				notifyHandlerNameMap.put(msgObjName, className);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
	}

	private INotifyHandler createNotifyHander(String handlerClassName) {
		INotifyHandler handler = null;
		try {
			Class handlerClass = Class.forName(handlerClassName);
			handler = (INotifyHandler) handlerClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			LOG.error("createNotifyHander() ClassNotFoundException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (InstantiationException e) {
			LOG.error("createNotifyHander() InstantiationException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (IllegalAccessException e) {
			LOG.error("createNotifyHander() IllegalAccessException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		}
		return handler;
	}

	public synchronized INotifyHandler getHandler(String msgObjName) {
		INotifyHandler handler = notifyHandlerMap.get(msgObjName);
		if (handler == null){
			String className = notifyHandlerNameMap.get(msgObjName);
			if(className == null){
				LOG.error("getHandler() msgObjName : " + msgObjName+ " isn't exit ");
				return null;
			}
			handler = createNotifyHander(className);
			if(handler != null){
				notifyHandlerMap.put(msgObjName,handler);
			}
		}

		return handler;
	}

}
