package com.nms.corba.ninterface.framework;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.omg.PortableServer.Servant;

import com.nms.ui.manager.ExceptionManage;

public class CorbaManagerObjMgr {
	static Logger LOG = Logger.getLogger(CorbaManagerObjMgr.class.getName());
	private HashMap<String, String> reflectMap = new HashMap();
	private static CorbaManagerObjMgr instance = new CorbaManagerObjMgr();
	
	private CorbaManagerObjMgr(){
		
	}
	
	public static CorbaManagerObjMgr getInstanse() {
		// TODO Auto-generated method stub
		return instance;
	}

	Servant CreateCorbaObj(ICorbaSession userSession, String strManagerName) {
		String strClassName = (String) reflectMap.get(strManagerName);
		if (null == strClassName) {
			LOG.error("Manager " + strManagerName + "is not configed");
			return null;
		}

		try {
			Class cls = Class.forName(strClassName);

			Class[] classPara = new Class[1];
			classPara[0] = ICorbaSession.class;

			Constructor con = cls.getConstructor(classPara);

			return (Servant) con.newInstance(new Object[] { userSession });
		} catch (ClassNotFoundException e) {
			LOG.error(strClassName + " has no implement");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (SecurityException e) {
			LOG.error(strClassName + " SecurityException");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (NoSuchMethodException e) {
			LOG.error(strClassName + " don't have such method");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (IllegalArgumentException e) {
			LOG.error(strClassName + " IllegalArgumentException");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (InstantiationException e) {
			LOG.error(strClassName + " IllegalArgumentException");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (IllegalAccessException e) {
			LOG.error(strClassName + " IllegalAccessException");
			ExceptionManage.dispose(e,this.getClass());
			return null;
		} catch (InvocationTargetException e) {
			LOG.error(strClassName + " InvocationTargetException");
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}

	public Set<String> getManagerNames() {
		return reflectMap.keySet();
	}

	public boolean init() {
		LOG.info("init begin.");
		try {
			String strXmlPath = CorbaManagerObjMgr.class.getClassLoader().getResource("config/corba/manager_object_conf.xml").toString();

			LOG.info("[init]manager_object_conf.xml path is " + strXmlPath);
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(strXmlPath);
			Element root = document.getRootElement();

			for (Iterator iter = root.getChildren().iterator(); iter.hasNext();) {
				Element config = (Element) iter.next();

				String strManagerName = config.getAttributeValue("managerName");
				String strClassName = config.getAttributeValue("className");
				reflectMap.put(strManagerName, strClassName);

				LOG.info("[init]Reg manger = " + strManagerName + " class = "
						+ strClassName);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			return false;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return false;
		}

		return true;
	}
}
