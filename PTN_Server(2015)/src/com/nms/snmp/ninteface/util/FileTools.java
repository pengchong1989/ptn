package com.nms.snmp.ninteface.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	/**
	 * 根据文件路径和文件名生成xml文件
	 * @param xmlPath 文件路径地址和文件名 xmlPath[0] = snmp\\SubnetworkConnectionList_T xmlPath[1] =SubnetworkConnectionList_T.xml
	 * @throws Exception 
	 */
	public String createFile(String[] xmlPath) throws Exception{
		if(xmlPath != null && xmlPath.length >1){
		    //根据路径生成文件目录
			File dirName = new File(xmlPath[0]);
			//如果本地目录不存在,则需要创建一个目录
			if(!dirName.exists()){
				dirName.mkdirs();
			}
			//生成对应的xml文件
			new FileOutputStream(xmlPath[0]+File.separator+xmlPath[1]);
			return xmlPath[0]+File.separator+xmlPath[1];
		}else{
			throw new Exception("xmlPath:缺少参数");
		}
	}
	
	public Document getDocument()throws Exception{
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (Exception e) {
			ExceptionManage.dispose(e,FileTools.class);
		}
		return doc;
	}
	
	
	public void putFile(Document doc, String filePath) throws Exception {
		if(doc != null && (filePath!=null)){
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			tf.setAttribute("indent-number", 2);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			if(pw != null){
		    	pw.close();
		    }
		}else{
			throw new Exception("filePath or doc is null");
		}
	}
	
	public Element rootElement(Document doc) throws Exception{
		Element root = null;
		if(doc != null ){
			doc.setXmlStandalone(true);
			root = doc.createElement("dm:Descriptor");
			root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
			root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");	
		}else{
			throw new Exception("doc is null");
		}
		return root;
	}
	
    /** 
	 * 压缩文件 
	 * @param srcFilePath 需要压缩的文件的完整路径
	 * 					  d:/ptn.sql
	 * @param zipFilePath 压缩生成的文件的路径
	 * 					  e:/ptn.sql.zip
	 * 
	 */
	public void zipFile(String srcFilePath, String zipFilePath) {
		if(srcFilePath == null){
			throw new RuntimeException("需要压缩的文件的完整路径不能为空!");
		}
		if(zipFilePath == null){
			throw new RuntimeException("压缩生成的文件的路径不能为空!");
		}
		
		FileInputStream fin = null;
		ZipOutputStream zout = null;
		try{
			File txtFile = new File(srcFilePath);
			fin = new FileInputStream(txtFile);
		}catch (FileNotFoundException e) {
			throw new RuntimeException("压缩失败!找不到文件" + srcFilePath);
		}finally {
			try {
				if(fin != null){
				  fin.close();
				}
			} catch (IOException e) {
				ExceptionManage.dispose(e,FileTools.class);
			}
		}
		
		try {
			zout = new ZipOutputStream(new FileOutputStream(new File(zipFilePath)));
			File srcFile = new File(srcFilePath);
			fin = new FileInputStream(srcFile);
			byte[] bb = new byte[4096];
			int i = 0;
			zout.putNextEntry(new ZipEntry(srcFile.getName()));
			while ((i = fin.read(bb)) != -1) {
				zout.setLevel(9);
				zout.write(bb, 0, i);
			}
		}  catch (IOException e) {
			throw new RuntimeException("压缩失败!", e);
		} finally {
			try {
				if(zout != null){
					zout.close();
				}
				if(fin != null){
					fin.close();
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,FileTools.class);
			}
		}
	}	
}