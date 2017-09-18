package com.nms.corba.ninterface.framework.notify;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.nms.ui.manager.ExceptionManage;

public class CorbaNotifyHanderMgr {

	private static Logger LOG = Logger.getLogger(CorbaNotifyHanderMgr.class
			.getClass());
	private static CorbaNotifyHanderMgr instance = new CorbaNotifyHanderMgr();
	
	private HashMap<String, String> notifyCvtHandlerNameMap = new HashMap<String, String>();
	private HashMap<String, INotifyCvtHandler> notifyCvtHandlerMap = new HashMap<String, INotifyCvtHandler>();

	private CorbaNotifyHanderMgr() {

	}

	public static CorbaNotifyHanderMgr getInstance() {
		return instance;
	}

	public void init() {
		LOG.info("init begin.");
		try {
			
			String strXmlPath =  CorbaNotifyHanderMgr.class.getClassLoader().getResource("config/corba/notify_cvthandler.xml").toString();
			LOG.info("[init] notify_cvthandler.xml path is " + strXmlPath);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(strXmlPath);
			Element root = document.getRootElement();

			for (Iterator iter = root.getChildren().iterator(); iter.hasNext();) {
				Element config = (Element) iter.next();

				String msgObjName = config.getAttributeValue("msgObjName");
				String className = config.getAttributeValue("className");
				notifyCvtHandlerNameMap.put(msgObjName, className);
				LOG.info("[init]Reg msgObjName = " + msgObjName + " className = "
						+ className);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	private INotifyCvtHandler createNotifyCvtHander(String cvtHandlerClassName) {
		
		INotifyCvtHandler cvtHandler = null;
		try {
			Class cvtHandlerClass = Class.forName(cvtHandlerClassName);
			cvtHandler = (INotifyCvtHandler) cvtHandlerClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			LOG.error("createNotifyCvtHander() ClassNotFoundException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (InstantiationException e) {
			LOG.error("createNotifyCvtHander() InstantiationException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (IllegalAccessException e) {
			LOG.error("createNotifyCvtHander() IllegalAccessException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		}
		return cvtHandler;
	}

	public synchronized INotifyCvtHandler getConvertHandler(String msgObjName) {
		INotifyCvtHandler cvtHandler = notifyCvtHandlerMap.get(msgObjName);
		if (cvtHandler == null){
			String className = notifyCvtHandlerNameMap.get(msgObjName);
			if(className == null){
				LOG.error("getConvertHandler() msgObjName : " + msgObjName+ " isn't exit ");
				return null;
			}
			cvtHandler = createNotifyCvtHander(className);
			if(cvtHandler != null){
				notifyCvtHandlerMap.put(msgObjName, cvtHandler);
			}
		}

		return cvtHandler;
	}

}
