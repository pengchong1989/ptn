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

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.protect.Protect_Corba;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.protect.ProtectServiceCorba_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;


public class ProtectionGroupConvertXml {
	public static void main(String[] args) {
		ConstantUtil.serviceFactory = new ServiceFactory();
		ProtectionGroupConvertXml ProtectionGroupConverXml = new ProtectionGroupConvertXml();
		ProtectionGroupConverXml.getProtectionGroupXml();
	}
	
	public String getProtectionGroupXml(){
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlFile = {"snmpData\\NRM", "CMCC-PTN-NRM-PG-"+version+"-"+this.getTime()+".xml"};
		FileTools fileTools = null;
		filePath = xmlFile[0]+File.separator+xmlFile[1];
		this.createFile(xmlFile);//创建xml文件
		try {
			Document doc = this.getDocument();
			//生成doucument
			createXml(doc, this.getPGList());
			fileTools = new FileTools();
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return filePath;
	}
	
	private List<Protect_Corba> getPGList(){
		List<Protect_Corba> protectList = new ArrayList<Protect_Corba>();
		SiteService_MB siteService = null;
		ProtectServiceCorba_MB protectService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteList = siteService.select();
			List<Integer> siteIdList = new ArrayList<Integer>();
			for (SiteInst s : siteList) {
				if(!siteIdList.contains(s.getSite_Inst_Id())){
					siteIdList.add(s.getSite_Inst_Id());
				}
			}
			protectService = (ProtectServiceCorba_MB) ConstantUtil.serviceFactory.newService_MB(Services.PROTECT_CORBA);
			for (Integer siteId : siteIdList) {
				Protect_Corba protect_Corba = new Protect_Corba();
				protect_Corba.setSiteId(siteId);
				protectList.addAll(protectService.queryProtectByCondition(protect_Corba));
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(protectService);
			UiUtil.closeService_MB(siteService);
		}
		return protectList;
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
    
    private void createXml(Document doc,List<Protect_Corba> protectCorbas){
    	doc.setXmlVersion("1.0");
    	doc.setXmlStandalone(true);
    	Element root = doc.createElement("dm:Descriptor");
    	root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
    	root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    	root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
    	Element segmentElement = this.createFileContent(doc, protectCorbas);
    	createFileContent(doc, protectCorbas);
    	root.appendChild(segmentElement);
		doc.appendChild(root);
    }
    
	private Element createFileContent(Document doc, List<Protect_Corba> protectCorbas) {
		Element protectList = doc.createElement("ProtectionGroupList_T");
		for(Protect_Corba protectCorba : protectCorbas){
			Element protectElement = doc.createElement("ProtectionGroup");
			 this.createElementNode(doc, "name",protectCorba.getName()+"", protectElement,protectCorba);//保护组名称
			 this.createElementNode(doc, "reversionMode",protectCorba.getReversionMode()==0?"NON_REVERTIVE":"REVERTIVE", protectElement,protectCorba);//恢复模式
			 this.createElementNode(doc, "rate","1", protectElement,protectCorba);//层速率
			 this.createElementNode(doc, "pgpParameters","", protectElement,protectCorba);//保护组参数
			 this.createElementNode(doc, "pgpTPList","", protectElement,protectCorba);//终端点列表
			 //缺少附加信息中 DetailedPGType
			 this.createElementNode(doc, "additionalInfo","", protectElement,protectCorba);//DetailedPGType
			 protectList.appendChild(protectElement);
		}
		return protectList;
	}
	
	private void createElementNode(Document doc, String childName, String childValue, Element segmentElement,Protect_Corba protectCorba) {
		Element e = doc.createElement(childName);
		if("name".equals(childName)){
			addElement(doc, "node", "name", "EMS", "value", "YIXUN/1", e);
			addElement(doc, "node", "name", "ManagedElement", "value", protectCorba.getSiteId()+"", e);
			addElement(doc, "node", "name", "PGP", "value", protectCorba.getId()+"", e);
		}else if("pgpParameters".equals(childName)){
			addElement(doc, "oneParam", "name", "waitTime", "value", protectCorba.getWaitTime()+"", e);
			addElement(doc, "oneParam", "name", "delayTime", "value", protectCorba.getDelaytime()+"", e);
		}else if("pgpTPList".equals(childName)){
			Element element1 = doc.createElement("name");
			end(doc, childName, protectCorba.getPortId_pro()+"", element1, protectCorba.getSiteId()+"");
			Element element2 = doc.createElement("name");
			end(doc, childName, protectCorba.getPortId_work()+"", element2, protectCorba.getSiteId()+"");
			e.appendChild(element1);
			e.appendChild(element2);
		}else if("additionalInfo".equals(childName)){
			addElement(doc, "oneParam", "name", "DetailedPGType", "value", "1:1", e);
		}
		else{
			e.setTextContent(childValue);
		}
        segmentElement.appendChild(e);
	}
	
	private void end(Document doc, String childName, String childValue,Element e,String managed){
		addElement(doc, "node", "name", "EMS", "value","YIXUN/1", e);
		addElement(doc, "node", "name", "ManagedElement","value", managed, e);
		addElement(doc, "node", "name", "PTP", "value",childValue, e);
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
