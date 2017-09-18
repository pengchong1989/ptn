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

import com.nms.ui.manager.xmlbean.LoginConfig;

/**
 * 登陆配置的帮助类
 * 
 * 项目名称：WuHanPTN2012 类名称：LoginUtil 类描述： 创建人：kk 创建时间：2013-6-25 下午04:26:11 修改人：kk 修改时间：2013-6-25 下午04:26:11 修改备注：
 * 
 * @version
 * 
 */
public class LoginUtil {

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
	public LoginConfig readLoginConfig() {
		LoginConfig loginConfig = new LoginConfig();

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
			doc = builder.parse(new File(System.getProperty("user.dir") + "/config/loginConfig.xml"));
			// root = doc.getDocumentElement();
			nodeList = doc.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				if ("login".equals(element.getAttribute("type"))) {
					loginConfig.setServiceIp(element.getElementsByTagName("serviceIp").item(0).getTextContent());
					loginConfig.setUsername(element.getElementsByTagName("username").item(0).getTextContent());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, LoginUtil.class);
		} finally {
			factory = null;
			builder = null;
			doc = null;
			nodeList = null;
			element = null;
		}

		return loginConfig;
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
	public void writeLoginConfig(LoginConfig loginConfig) {
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
			doc = builder.parse(new File(System.getProperty("user.dir") + "/config/loginConfig.xml"));
			// root = doc.getDocumentElement();
			nodeList = doc.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				if ("login".equals(element.getAttribute("type"))) {
					break;
				}
			}
			element.getElementsByTagName("serviceIp").item(0).setTextContent(loginConfig.getServiceIp());
			element.getElementsByTagName("username").item(0).setTextContent(loginConfig.getUsername());
			output(element);

		} catch (Exception e) {
			ExceptionManage.dispose(e, LoginUtil.class);
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
			StreamResult result = new StreamResult(System.getProperty("user.dir") + "/config/loginConfig.xml");

			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			ExceptionManage.dispose(e, LoginUtil.class);
		} catch (TransformerException e) {
			ExceptionManage.dispose(e, LoginUtil.class);
		}
	}

}
