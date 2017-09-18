package com.nms.ui.frame;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import twaver.ElementAttribute;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class ReadTableXml {

	/**
	 * 读取配置文件src/com/nms/ui/ptn/tableAttrs.xml表头信息
	 * 
	 * @param attributes
	 *            建立表头信息
	 * @param tableAttrs
	 * @return String 返回布局
	 */
	public String readTableXml(List<ElementAttribute> attributes, String tableAttrs) {
		String path = "config/tableAttrs.xml";
		if ("en_US".equals(ResourceUtil.language)) {
			path = "config/tableAttrs_en.xml";
		}
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		Element root = null;
//		File file = null;
		String layout = "default";
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
//			file = new File(path);
			document = builder.parse(ReadTableXml.class.getClassLoader().getResource(path).toString());
			root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			ElementAttribute attribute = new ElementAttribute();
			attribute.setName("index");
			attribute.setDisplayName(ResourceUtil.srcStr(StringKeysObj.ORDER_NUM));
			attribute.setVisible(true);
			attribute.setEditable(false);
			attribute.setMaxWidth(40);
			attributes.add(attribute);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getAttributes().getNamedItem("name").getNodeValue().equals(tableAttrs)) {
					if (node.getAttributes().getNamedItem("layout") != null) {
						layout = node.getAttributes().getNamedItem("layout").getNodeValue();
					}
					NodeList childNode = node.getChildNodes();
					for (int j = 0; j < childNode.getLength(); j++) {
						if (childNode.item(j).getNodeType() == Node.ELEMENT_NODE && childNode.item(j).getNodeName().equals("attribute")) {
							Node child = childNode.item(j);
							String displayName = child.getAttributes().getNamedItem("name").getNodeValue();
							String name = child.getAttributes().getNamedItem("id").getNodeValue();
							Boolean visible = true;
							if (child.getAttributes().getNamedItem("visible") != null) {
								visible = Boolean.parseBoolean(child.getAttributes().getNamedItem("visible").getNodeValue());
							}
							Boolean isEdit = false;
							if (child.getAttributes().getNamedItem("edit") != null) {
								isEdit = Boolean.parseBoolean(child.getAttributes().getNamedItem("edit").getNodeValue());
							}
							attribute = new ElementAttribute();
							attribute.setName(name);
							attribute.setDisplayName(displayName);
							attribute.setVisible(visible);
							attribute.setEditable(isEdit);
							attribute.setClientPropertyKey(name);
							attributes.add(attribute);
						}
					}

				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,ReadTableXml.class);
		} finally {
			factory = null;
			builder = null;
			document = null;
			root = null;
//			file = null;
		}
		return layout;
	}
}
