package com.nms.ui.manager;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.nms.ui.manager.xmlbean.Telnet;

/**
 * 登陆配置的帮助类
 * 
 * 项目名称：WuHanPTN2012 类名称：TelnetUtil 类描述： 创建人：kk 创建时间：2013-6-25 下午04:26:11 修改人：kk 修改时间：2013-6-25 下午04:26:11 修改备注：
 * 
 * @version
 * 
 */
public class TelnetUtil {

	/**
	 * 获取登陆配置对象，如果没有就去读XML文件
	 * 
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public Telnet readTelnet() {
		Telnet telnet = new Telnet();

		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		NodeList nodeList = null;
		org.w3c.dom.Element element = null;
		try {
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(new File(System.getProperty("user.dir") + "/config/telnet.xml"));
			// root = doc.getDocumentElement();
			nodeList = doc.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				if ("telnet".equals(element.getAttribute("type"))) {
					telnet.setServiceIp(element.getElementsByTagName("serviceIp").item(0).getTextContent());
					telnet.setUsername(element.getElementsByTagName("username").item(0).getTextContent());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, TelnetUtil.class);
		} finally {
			factory = null;
			builder = null;
			doc = null;
			nodeList = null;
			element = null;
		}

		return telnet;
	}

	/**
	 * 登陆之后 把页面上的配置写到XML中
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void writeLoginConfig(Telnet telnet) {
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		NodeList nodeList = null;
		org.w3c.dom.Element element = null;
		try {
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(new File(System.getProperty("user.dir") + "/telnet.xml"));
			// root = doc.getDocumentElement();
			nodeList = doc.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				if ("telnet".equals(element.getAttribute("type"))) {
					break;
				}
			}
			if(null !=telnet.getNeIp()){
			   element.getElementsByTagName("neIp").item(0).setTextContent(telnet.getNeIp());
			}
			if(null !=telnet.getUsername()){
			   element.getElementsByTagName("user").item(0).setTextContent(telnet.getUsername());
			}
			if(null !=telnet.getPassword()){
				   element.getElementsByTagName("password").item(0).setTextContent(telnet.getPassword());
				}
			if(null !=telnet.getServiceIp()){
				   element.getElementsByTagName("serverIp").item(0).setTextContent(telnet.getServiceIp());
				}
			if(null !=telnet.getNePassword()){
				   element.getElementsByTagName("nePassword").item(0).setTextContent(telnet.getNePassword());
				}
			output(element);

		} catch (Exception e) {
			ExceptionManage.dispose(e, TelnetUtil.class);
		} finally {
			factory = null;
			builder = null;
			doc = null;
			nodeList = null;
			element = null;
		}
	}

	private void output(Node node) {// 将node的XML字符串输出到控制台
		TransformerFactory transFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transFactory.newTransformer();
			transformer.setOutputProperty("encoding", "utf-8");
			transformer.setOutputProperty("indent", "yes");
			DOMSource source = new DOMSource();
			source.setNode(node);
			StreamResult result = new StreamResult(System.getProperty("user.dir") + "/telnet.xml");

			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			ExceptionManage.dispose(e, TelnetUtil.class);
		} catch (TransformerException e) {
			ExceptionManage.dispose(e, TelnetUtil.class);
		}
	}

}
