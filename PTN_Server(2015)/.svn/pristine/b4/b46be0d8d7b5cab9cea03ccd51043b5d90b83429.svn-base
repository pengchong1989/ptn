package com.nms.corba.ninterface.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.nms.ui.manager.ExceptionManage;

public class FileTools {
	private static Logger LOG = Logger.getLogger(FileTools.class.getName());
	public static final String ATTRIBUTE_START = "\"";
	public static final String ATTRIBUTE_END = "\"";
	public static final String SEPARATOR = ",";
	public static final String NEWLINE = System.getProperty("line.separator");

	public static boolean writeFile(File file, String strContent) {
		if (file == null) {
			return false;
		}

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			Writer output = new BufferedWriter(new FileWriter(file));
			output.write(strContent);
			output.flush();
			output.close();
			return true;
		} catch (Exception e) {
			LOG.error("[FileTools] writeFile failed..." + e.getMessage());
			ExceptionManage.dispose(e,FileTools.class);
		}
		return false;
	}

	public static boolean writeFile(String strPathName, String strContent) {
		File file = new File(strPathName);
		return writeFile(file, strContent);
	}

	public static boolean setProperty(String key, String value, String fileName) {
		try {
			// 加载配置文件
			List<String> propContArray = new ArrayList<String>();
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(new FileInputStream(fileName),
							"utf-8"));
			String lineStr = "";
			while ((lineStr = bufferedreader.readLine()) != null) {
				propContArray.add(lineStr);
			}

			String props = setPropKeyValue(key, value, propContArray);

			bufferedreader.close();

			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "utf-8"));

			br.write(props);
			br.flush();
			br.close();
			return true;

		} catch (Exception e) {
			LOG.error("[FileTools] setProperty failed..." + e.getMessage());
			ExceptionManage.dispose(e,FileTools.class);
			return false;
		}
	}

	public static String setPropKeyValue(String key, String value,
			List<String> propContArray) {
		StringBuffer props = new StringBuffer();
		String rowData = "";
		for (int i = 0; i < propContArray.size(); i++) {
			rowData = propContArray.get(i);
			if (!rowData.contains(key)) {
				props.append(rowData).append(
						System.getProperty("line.separator"));
				continue;
			}
			StringBuffer newData = new StringBuffer(key).append("=")
					.append(value).append(System.getProperty("line.separator"));
			props.append(newData);
		}

		return props.toString();
	}

	public static boolean writeFileHead(String filePathAndName, String fileHead) {
		try {
			File myFile = new File(filePathAndName);
			if (!myFile.exists()) {
				if (myFile.getParent() != null) {
					new File(myFile.getParent()).mkdirs();
				}
				myFile.createNewFile();
			}
			PrintWriter writer = new PrintWriter(myFile);
			writer.println(fileHead);
			writer.close();
		} catch (Exception e) {
			ExceptionManage.dispose(e,FileTools.class);
			return false;
		}

		return true;
	}

	public static String getCurrentDate(String pattern) {
		SimpleDateFormat ft = new SimpleDateFormat(pattern);
		return ft.format(new Date());
	}

	public static String getFileHeadStr(Class<?> cls) {
		StringBuffer strBuf = new StringBuffer();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length - 1; i++) {
			strBuf.append("\"").append(fields[i].getName()).append("\"")
					.append(",");
		}
		strBuf.deleteCharAt(strBuf.length() - 1);
		return strBuf.toString();
	}

	public static String getLineStrFromFtpInfo(Object ftpObj) {
		if (ftpObj == null) {
			return null;
		}
		StringBuffer strBuf = new StringBuffer();
		try {
			Field[] fields = ftpObj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length - 1; i++) {
				Object field = fields[i].get(ftpObj);
				if ((field instanceof String)) {
					String fieldStr = (String) field;
					if (fieldStr.contains("\"")) {
						fieldStr = fieldStr.replaceAll("\"", "\"\"");
						strBuf.append("\"" + fieldStr + "\"");
					} else if ((fieldStr.contains(","))
							|| (fieldStr.contains("\n"))
							|| (fieldStr.contains(" "))) {
						strBuf.append("\"" + fieldStr + "\"");
					} else {
						strBuf.append("\"").append(fieldStr).append("\"");
					}
				} else {
					strBuf.append("\"").append(field).append("\"");
				}

				strBuf.append(",");
			}
		} catch (IllegalArgumentException e) {
			ExceptionManage.dispose(e,FileTools.class);
			return null;
		} catch (IllegalAccessException e) {
			ExceptionManage.dispose(e,FileTools.class);
			return null;
		}
		strBuf.deleteCharAt(strBuf.length() - 1);

		strBuf.append(NEWLINE);

		return strBuf.toString();
	}

	public static boolean isExisted(String argChar, String argStr) {
		boolean blnReturnValue = false;
		if ((argStr.indexOf(argChar) >= 0)
				&& (argStr.indexOf(argChar) <= argStr.length())) {
			blnReturnValue = true;
		}
		return blnReturnValue;
	}

	public static String getItemValueFromElement(Element nodeEle,
			String strItemName) {
		Element eleParameter = nodeEle.element(strItemName);
		if (null != eleParameter) {
			return eleParameter.getTextTrim();
		}
		return null;
	}

	public static void writeFile(String strFilePath, String strFileContents,
			boolean isCreate) {
		File xmlFile = new File(strFilePath);
		Writer output = null;
		try {
			if ((!xmlFile.exists()) && (isCreate)) {
				xmlFile.createNewFile();
			}

			output = new BufferedWriter(new FileWriter(xmlFile, true));
			output.write(strFileContents);
			output.flush();
		} catch (Exception e) {
			ExceptionManage.dispose(e,FileTools.class);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					ExceptionManage.dispose(e,FileTools.class);
				}
			}
		}
	}

	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);

		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	public static boolean deleteFile(File file) {
		boolean flag = false;
		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}