package com.nms.snmp.ninteface.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.path.Segment;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class TopologicalLinkConvertXml {
	
	public static void main(String[] args) {
		ConstantUtil.serviceFactory = new ServiceFactory();
		SnmpConfig.getInstanse().init();
		new TopologicalLinkConvertXml().getTopologicalLinkXml();
	}
	
	public String getTopologicalLinkXml(){
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlFile = {"snmpData\\NRM", "CMCC-PTN-NRM-TL-"+version+"-"+this.getTime()+".xml"};
		FileTools fileTools = null;
		filePath = xmlFile[0]+File.separator+xmlFile[1];
		this.createFile(xmlFile);//创建xml文件
		try {
			Document doc = this.getDocument();
			//生成doucument
			createXml(doc, this.getSegmentList());
			fileTools = new FileTools();
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return filePath;
	}
	
	private List<Segment> getSegmentList(){
		SegmentService_MB segmentService = null;
		List<Segment> segmentList = new ArrayList<Segment>();
		try {
			segmentService = (SegmentService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segmentList = segmentService.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(segmentService);
		}
		return segmentList;
	}
	
	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}
	
	/**
	 * 创建文件夹
	 * @param xmlFile
	 */
	private void createFile(String[] xmlFile){
		File file = new File(xmlFile[0]);
		if(!file.exists()){
			file.mkdir();
		}
		try {
			new FileOutputStream(xmlFile[0]+File.separator+xmlFile[1]);
		} catch (FileNotFoundException e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	 /**
     * 生成document
     */
    private Document getDocument() throws Exception {
	    try {  
	     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder builder = factory.newDocumentBuilder();
	     Document doc = builder.newDocument();
	     return doc;
	    } catch (Exception e) {  
	     ExceptionManage.dispose(e,this.getClass());  
	    }
		return null;
	}
    
    private void createXml(Document doc,List<Segment> segments){
    	doc.setXmlVersion("1.0");
    	doc.setXmlStandalone(true);
    	Element root = doc.createElement("dm:Descriptor");
    	root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
    	root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    	root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
    	Element segmentElement = this.createFileContent(doc, segments);
    	createFileContent(doc, segments);
    	root.appendChild(segmentElement);
		doc.appendChild(root);
    }
    
	private Element createFileContent(Document doc, List<Segment> segments) {
		Element segmentsList = doc.createElement("TopologicalLinkList_T");
		for(Segment segment : segments){
			Element segmentElement = doc.createElement("TopologicalLink");
			 this.createElementNode(doc, "name",segment.getId()+"", segmentElement,segment);//拓扑连接的标识符
			 this.createElementNode(doc, "userLabel",segment.getNAME()+"", segmentElement,segment);//用户友好名称
			 this.createElementNode(doc, "direction","D_BIDIRECTIONAL", segmentElement,segment);//方向
			 this.createElementNode(doc, "layerRate",ELayerRate.TOPOLOGICALLINK.getValue()+"", segmentElement,segment);//层速率
			 this.createElementNode(doc, "aEnd",segment.getShowPortAname()+"", segmentElement,segment);//A端终端点
			 this.createElementNode(doc, "zEnd",segment.getShowPortZname()+"", segmentElement,segment);//Z端终端点
			 segmentsList.appendChild(segmentElement);
		}
		return segmentsList;
	}
	
	private void createElementNode(Document doc, String childName, String childValue, Element segmentElement,Segment segment) {
		Element e = doc.createElement(childName);
		if("name".equals(childName)){
			addElement(doc, "node", "name", "EMS", "value", "YIXUN/1", e);
			addElement(doc, "node", "name", "TopologicalLink", "value", childValue, e);
		}else if("aEnd".equals(childName)){
			end(doc, childName, childValue, e, segment.getASITEID()+"");
		}else if("zEnd".equals(childName)){
			end(doc, childName, childValue, e, segment.getZSITEID()+"");
		}else{
			e.setTextContent(childValue);
		}
        segmentElement.appendChild(e);
	}
	
	private void end(Document doc, String childName, String childValue,Element e,String managed){
		addElement(doc, "node", "name", "EMS", "value", "YIXUN/1", e);
		addElement(doc, "node", "name", "ManagedElement", "value", managed, e);
		addElement(doc, "node", "name", "PTP", "value", childValue, e);
	}
	
	private void addElement(Document doc,String node,String elementName1,String elementValue1,String elementName2,String elementValue2,Element e){
		Element node1 = doc.createElement(node);
		Element nameElement1 = doc.createElement(elementName1);
		nameElement1.setTextContent(elementValue1);
		node1.appendChild(nameElement1);
		Element valueElement1 = doc.createElement(elementName2);
		valueElement1.setTextContent(elementValue2);
		node1.appendChild(valueElement1);
		e.appendChild(node1);
	} 
}
