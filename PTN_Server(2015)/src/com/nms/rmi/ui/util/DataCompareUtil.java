package com.nms.rmi.ui.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.nms.ui.manager.ExceptionManage;

public class DataCompareUtil {
	
	private List<String> insertSqls = new ArrayList<String>();
	private List<String> updateSqls = new ArrayList<String>();
	public void compareData(String path) throws ParserConfigurationException, SAXException, IOException{
		NodeList oldNodeList = readTableXml(path);
		String newPath = System.getProperty("user.dir") + "\\config\\DataBaseStruct.xml";
		NodeList newNodeList = readTableXml(newPath);
		ExceptionManage.logDebug(oldNodeList.getLength()+"++++"+newNodeList.getLength(), this.getClass());
		for(int i=0;i<newNodeList.getLength();i++){
			Node node = newNodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE ){
				if(i+1>oldNodeList.getLength()){//i+1后面节点表示新增数据表
					insertDate(node);
				}else if(true){//需更新字段数据表
					Node oldNode = oldNodeList.item(i);
					updateDate(node, oldNode);
				}
			}
			
		}
	}
	
	/**
	 * 新增数据表
	 * @param node
	 */
	private void insertDate(Node node){
		
		String tableName =  node.getAttributes().getNamedItem("tableName").getNodeValue();
		NodeList childNode = node.getChildNodes();
		String sql = "";
		String primary = "";
		for (int j = 0; j < childNode.getLength(); j++) {
			Node child = childNode.item(j);
			if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("attribute")) {
				String name = child.getAttributes().getNamedItem("name").getNodeValue();//字段名
				String type = child.getAttributes().getNamedItem("type").getNodeValue();//类型
				String length = child.getAttributes().getNamedItem("length").getNodeValue();//长度
//				String decimal = child.getAttributes().getNamedItem("decimal").getNodeValue();
//				String AllowNulls = child.getAttributes().getNamedItem("AllowNulls").getNodeValue();
				
				if(j==1){//第一行表示主键
					primary = name;
					sql = "CREATE TABLE `"+tableName+"` ( `"+name+"` "+type+"("+length+") NOT NULL AUTO_INCREMENT,";
				}else{
					if("datetime".equals(type)){//datetime格式字段不需要默认长度
						sql = sql+"`"+name+"` "+type+" DEFAULT NULL,";
					}else{
						sql = sql+"`"+name+"` "+type+"("+length+") DEFAULT NULL,";
					}
					
				}
			}
		}
		sql = sql+"PRIMARY KEY (`"+primary+"`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
		if(!"".equals(sql)){
			insertSqls.add(sql);
		}
	}
	
	/**
	 * 更新数据库字段
	 * @param newNode
	 * @param oldNode
	 */
	private void updateDate(Node newNode,Node oldNode){
		String newTableName =  newNode.getAttributes().getNamedItem("tableName").getNodeValue();
		NodeList newchildNode = newNode.getChildNodes();
		NodeList oldchildNode = oldNode.getChildNodes();
		String sql = "";
		for(int i=0;i<newchildNode.getLength();i++){
			Node child = newchildNode.item(i);
			if(child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("attribute")){
				if(i+1>oldchildNode.getLength()){
					String name = child.getAttributes().getNamedItem("name").getNodeValue();//字段名
					String type = child.getAttributes().getNamedItem("type").getNodeValue();//类型
					String length = child.getAttributes().getNamedItem("length").getNodeValue();//长度
//					String decimal = child.getAttributes().getNamedItem("decimal").getNodeValue();
//					String AllowNulls = child.getAttributes().getNamedItem("AllowNulls").getNodeValue();
					String defaultValue= child.getAttributes().getNamedItem("defaultValue").getNodeValue();//默认值
					sql ="ALTER TABLE `"+newTableName+"` ADD COLUMN `"+name+"` "+type+"("+length+") NULL DEFAULT '"+defaultValue+"';";
				}
			}
		}
		if(!sql.equals("")){
			ExceptionManage.infor(sql, this.getClass());
			updateSqls.add(sql);
		}
	}
	
	/**
	 * 读取xml，返回表节点集合
	 * @param path
	 * @return
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public NodeList readTableXml(String path) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document document = null;
		Element root = null;
		NodeList nodeList = null;
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		document = builder.parse(new File(path));
		root = document.getDocumentElement();
		nodeList = root.getChildNodes();		
		return nodeList;
	}

	public List<String> getInsertSqls() {
		return insertSqls;
	}

	public void setInsertSqls(List<String> insertSqls) {
		this.insertSqls = insertSqls;
	}

	public List<String> getUpdateSqls() {
		return updateSqls;
	}

	public void setUpdateSqls(List<String> updateSqls) {
		this.updateSqls = updateSqls;
	}
	
	
}
