package com.nms.ui.manager;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nms.ui.manager.xmlbean.BusinessidXml;

public class BusinessIdUtil {

	public List<BusinessidXml> getList(String fileName) throws Exception {

		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		org.w3c.dom.Element root = null;
		NodeList nodeList = null;
		org.w3c.dom.Element element = null;
		List<BusinessidXml> businessidXmlList = null;
		try {
			businessidXmlList = new ArrayList<BusinessidXml>();
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(UiUtil.class.getClassLoader().getResource("config/businessid/" + fileName).toString());
			root = doc.getDocumentElement();
			nodeList = root.getElementsByTagName("business");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				BusinessidXml businessidXml = new BusinessidXml();
				businessidXml.setType(element.getAttribute("type"));
				businessidXml.setBegin(Integer.parseInt(element.getAttribute("begin")));
				businessidXml.setEnd(Integer.parseInt(element.getAttribute("end")));
				businessidXmlList.add(businessidXml);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, BusinessIdUtil.class);
		} finally{
			factory = null;
			builder = null;
			doc = null;
			root = null;
			nodeList = null;
			element = null;
		}
		return businessidXmlList;
	}
}
