package com.nms.snmp.ninteface.framework;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.ui.manager.ExceptionManage;

public class MOTabelMgr {
	private static Logger LOG = Logger.getLogger(MOTabelMgr.class
			.getClass());
	private static MOTabelMgr instance = new MOTabelMgr();
	
	private HashMap<String, String> oidTableNameMap = new HashMap<String, String>();
	private HashMap<String, TableHandler> oidTableMap = new HashMap<String, TableHandler>();

	private MOTabelMgr() {
	}

	public static MOTabelMgr getInstance() {
		return instance;
	}

	public void init() {
//		LOG.info("init begin.");
		try {
			DocumentBuilderFactory factory = null;
			DocumentBuilder builder = null;
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(MOTabelMgr.class.getClassLoader().getResource("config/motable.xml").toString());
			Element root = document.getDocumentElement();
			int i =0;
			for (int j = 0; j < root.getElementsByTagName("motable").getLength(); j++) {
				Element iter = (Element) root.getElementsByTagName("motable").item(j);
				String oidStr = iter.getAttribute("oid");
				String className = iter.getAttribute("className");
				oidTableNameMap.put(oidStr, className);
//				LOG.info("[init]Reg oidStr = " + oidStr + " className = "
//						+ className);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e, this.getClass());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private TableHandler createTableHander(String handlerClassName) {
		
		TableHandler handler = null;
		try {
			Class handlerClass = Class.forName(handlerClassName);
			handler = (TableHandler) handlerClass.newInstance();
			
		} catch (ClassNotFoundException e) {
			LOG.error("createTableHander() ClassNotFoundException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (InstantiationException e) {
			LOG.error("createTableHander() InstantiationException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		} catch (IllegalAccessException e) {
			LOG.error("createTableHander() IllegalAccessException ex:"+e.getMessage());
			ExceptionManage.dispose(e,this.getClass());
		}
		return handler;
	}

	public synchronized TableHandler getTable(String oidStr) {
		TableHandler Table = oidTableMap.get(oidStr);
		if (Table == null){
			String className = oidTableNameMap.get(oidStr);
			if(className == null){
				LOG.error("getTable() oidStr : " + oidStr+ " isn't exit ");
				return null;
			}
			Table = createTableHander(className);
			if(Table != null){
				oidTableMap.put(oidStr, Table);
			}
		}

		return Table;
	}
	
}
