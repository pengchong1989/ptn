package com.nms.corba.ninterface.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import com.nms.ui.manager.ExceptionManage;

public class PropertyUtil {

	private Properties FileProperties = null;
	private Properties BundleProperties = null;

	// 文件在指定包中,
	private Properties getPropObjFromBundle(String bundleName) {
		BundleProperties = new Properties();

		PropertyResourceBundle bundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(bundleName);
		Enumeration enm = bundle.getKeys();
		while (enm.hasMoreElements()) {
			String key = (String) enm.nextElement();
			String value = bundle.getString(key);
			BundleProperties.setProperty(key, value);

		}
		return BundleProperties;
	}

	public String getPropertyFromBundle(String key, String defaultValue,
			String bundleName) {
		if (BundleProperties == null) {
			BundleProperties = getPropObjFromBundle(bundleName);
		}

		return BundleProperties.getProperty(key, defaultValue);
	}

	// 文件在指定的目录中,filePath为文件全目录
	private  Properties getPropObjFromFile(String filePath) {
		FileProperties = new Properties();
		File file = new File(filePath);
		InputStream inStream;
		try {
			inStream = new FileInputStream(file);
			FileProperties.load(inStream);
		} catch (FileNotFoundException e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return FileProperties;
	}

	public  String getProperty(String key, String defaultValue,
			String filePath) {
		if (FileProperties == null) {
			FileProperties = getPropObjFromFile(filePath);
		}
		return FileProperties.getProperty(key, defaultValue);
	}
	//默认参数
	public  String getProperty(String key, String filePath) {
		return getProperty(key, "", filePath);
	}

}
