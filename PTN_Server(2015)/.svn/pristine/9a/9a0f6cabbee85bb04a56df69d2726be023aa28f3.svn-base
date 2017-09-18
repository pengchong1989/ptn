package com.nms.drive.analysis.xmltool;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;

public class LoadDriveXml {
	private DriveRoot driveRoot = new DriveRoot();

	public DriveRoot loadXmlToBean(String xmlPath) throws Exception {
		try {
//			File xmlFile = new File(xmlPath);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(LoadDriveXml.class.getClassLoader().getResource(xmlPath).toString());
			Element root = doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			readNodeList(nodeList, null, null);
		} catch (Exception e) {
			throw e; 
		}
		return driveRoot;
	}

	private void readNodeList(NodeList nodeList, DriveAttribute driveAttribute, ListRoot listRoot) throws Exception {
		try {
			NodeList nodeListTemp = null;
			DriveAttribute driveAttributeTemp = null;
			ListRoot listRootTemp = null;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if ("attribute".equals(node.getNodeName()) && driveAttribute == null && listRoot == null) {
						String length = node.getAttributes().getNamedItem("length").getNodeValue();
						String description = node.getAttributes().getNamedItem("description").getNodeValue();
						String value = node.getAttributes().getNamedItem("value").getNodeValue();
						String mapping = node.getAttributes().getNamedItem("mapping").getNodeValue();
						driveAttributeTemp = new DriveAttribute();
						driveAttributeTemp.setLength(length);
						driveAttributeTemp.setDescription(description);
						driveAttributeTemp.setValue(value);
						driveAttributeTemp.setMapping(mapping);
						driveRoot.getDriveAttributeList().add(driveAttributeTemp);

						nodeListTemp = node.getChildNodes();
						if (nodeListTemp != null && nodeListTemp.getLength() > 0) {
							readNodeList(nodeListTemp, driveAttributeTemp, null);
						}
					} else if ("listRoot".equals(node.getNodeName()) && driveAttribute != null) {
						String file = node.getAttributes().getNamedItem("file").getNodeValue();
						listRootTemp = new ListRoot();
						listRootTemp.setFile(file);
						driveAttribute.getListRootList().add(listRootTemp);

						nodeListTemp = node.getChildNodes();
						if (nodeListTemp != null && nodeListTemp.getLength() > 0) {
							readNodeList(nodeListTemp, null, listRootTemp);
						}
					} else if ("attribute".equals(node.getNodeName()) && listRoot != null) {
						String length = node.getAttributes().getNamedItem("length").getNodeValue();
						String description = node.getAttributes().getNamedItem("description").getNodeValue();
						String value = node.getAttributes().getNamedItem("value").getNodeValue();
						String mapping = node.getAttributes().getNamedItem("mapping").getNodeValue();
						driveAttributeTemp = new DriveAttribute();
						driveAttributeTemp.setLength(length);
						driveAttributeTemp.setDescription(description);
						driveAttributeTemp.setValue(value);
						driveAttributeTemp.setMapping(mapping);
						listRoot.getDriveAttributeList().add(driveAttributeTemp);

						nodeListTemp = node.getChildNodes();
						if (nodeListTemp != null && nodeListTemp.getLength() > 0) {
							readNodeList(nodeListTemp, driveAttributeTemp, null);
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
