package com.nms.ui.ptn.statistics.xmlanalysis;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
/**
 * 读取（界面的 表格）XML文件
 * @author Administrator
 *
 */
public class ReadTableAttrsXml {

	/**
	 * 读取配置文件src/com/nms/ui/ptn/tableAttrs.xml表头信息
	 * 
	 * @param tableAttrs 表名
	 * @return List<String> 返回表头，List中的每一条对应表头每一列
	 */
	public static List<String> readTableXml(String tableAttrs) {
		List<String> list = new ArrayList<String>();
		String path = "config/tableAttrs.xml";
		if ("en_US".equals(ResourceUtil.language)) {
			path = "config/tableAttrs_en.xml";
		}
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		Element root = null;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(ReadTableAttrsXml.class.getClassLoader().getResource(path).toString());
			root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getAttributes().getNamedItem("name").getNodeValue().equals(tableAttrs)) {
					NodeList childNode = node.getChildNodes(); //attribute
					for (int j = 0; j < childNode.getLength(); j++) {
						if (childNode.item(j).getNodeType() == Node.ELEMENT_NODE && childNode.item(j).getNodeName().equals("attribute")) {
							Node child = childNode.item(j);  //attribute
							String displayName = child.getAttributes().getNamedItem("name").getNodeValue();
							list.add(displayName);
							
						}
					}

				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,ReadTableAttrsXml.class);
		} finally {
			factory = null;
			builder = null;
			document = null;
			root = null;
		}
		return list;
	}
}
