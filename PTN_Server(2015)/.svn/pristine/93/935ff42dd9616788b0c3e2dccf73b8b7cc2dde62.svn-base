package com.nms.snmp.ninteface.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class EMSConvertXml {
	public static void main(String[] args) {
		new EMSConvertXml().getEMSXml();
	}
	
	public String getEMSXml() {
		//CMCC-PTN-NRM-ME-V1.0.0-20140411-1602-P00.xml
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-EMS-"+version+"-"+this.getTime()+".xml"};
		FileTools fileTools = null;
		try {
			filePath = xmlPath[0] + File.separator + xmlPath[1];//生成文件路径
	    	this.createFile(xmlPath);//根据文件路径和文件名生成xml文件
	    	Document doc = this.getDocument(xmlPath);//生成doucument
		    this.createXML(doc);//生成xml文件内容
		    fileTools = new FileTools();
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");
		} catch (Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
		return filePath;
	}

	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}

	/**
     * 根据文件路径和文件名生成xml文件
     */
    private void createFile(String[] xmlPath) throws FileNotFoundException {
    	//根据路径生成文件目录
    	File dirname = new File(xmlPath[0]);
    	//如果本地目录不存在，则需要创建一个目录
		if (!dirname.exists()) 
			dirname.mkdirs();
		//生成xml文件
    	new FileOutputStream(xmlPath[0]+File.separator+xmlPath[1]);
	}
    
    /**
     * 生成document
     */
    private Document getDocument(String[] xmlPath) throws Exception {
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

    /**
     * 根据需求生成相应的xml文件
     */
	private void createXML(Document doc){
		doc.setXmlVersion("1.0");
		doc.setXmlStandalone(true);
		Element root = doc.createElement("dm:Descriptor");
		root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
		Element emsList = this.createFileContent(doc);
		root.appendChild(emsList);
		doc.appendChild(root);
	}
	
	private Element createFileContent(Document doc) {
		Element EMS = doc.createElement("EMS_T");
		try{
			//name
	        Element name = doc.createElement("name");
	        Element node = doc.createElement("node");
	        this.createElementNode(doc, "name", "EMS", node);
	        this.createElementNode(doc, "value", SnmpConfig.getInstanse().getsnmpEmsName(), node);
	        name.appendChild(node);
	        EMS.appendChild(name);
	        //userLabel
	        this.createElementNode(doc, "userLabel", SnmpConfig.getInstanse().getEmsUserlabel(), EMS);
	        //nativeEMSName
	        this.createElementNode(doc, "nativeEMSName", SnmpConfig.getInstanse().getEmsNativeName(), EMS);
	        //emsVersion
	        this.createElementNode(doc, "emsVersion", "V1.1.6", EMS);
	        //type
	        this.createElementNode(doc, "type", SnmpConfig.getInstanse().getEmsType(), EMS);
	        //additionalInfo
	        Element add = doc.createElement("additionalInfo");
	        for (int i = 0; i < 4; i++) {
	        	Element oneParam = doc.createElement("oneParam");
	        	if(i == 0){
	        		//厂商网管系统北向接口版本
	        		this.createElementNode(doc, "name", "NBIVersion", oneParam);
	        		this.createElementNode(doc, "value", SnmpConfig.getInstanse().getEmsVersion(), oneParam);
				}else if(i == 1){
					//厂商网管系统所在的地理位置
					this.createElementNode(doc, "name", "location", oneParam);
	        		this.createElementNode(doc, "value", SnmpConfig.getInstanse().getEmsLocation(), oneParam);
				}else if(i == 2){
					//厂商网管系统运行状态
					this.createElementNode(doc, "name", "RunningState", oneParam);
					//如果网管DCN连接正常就说明:网管可用（IN_SERVICE）否则不可用（OUT_OF_SERVICE）
					if(verificationRmi()){
						this.createElementNode(doc, "value","IN_SERVICE", oneParam);
					}else{
						this.createElementNode(doc, "value","OUT_OF_SERVICE", oneParam);
					}
				}else if(i == 3){
					//厂商网管系统最大网元数目
					this.createElementNode(doc, "name", "emsMaxSupportedME", oneParam);
	        		this.createElementNode(doc, "value",ConstantUtil.serviceBean.getMaxSiteNumner()+"", oneParam);
				}
	        	add.appendChild(oneParam);
			}
	        EMS.appendChild(add);
		}
	    catch(Exception e){
	    	ExceptionManage.dispose(e, this.getClass());
	    }
		return EMS;
	}

	/**
	 * 根据名称创建元素,并赋值
	 */
	private void createElementNode(Document doc, String childName, String childValue, Element node){
		Element e = doc.createElement(childName);
        e.setTextContent(childValue);
        node.appendChild(e);
	}
	
	//用于定时来跟新客户端是否是连上服务器
	private boolean isLine(){
		 String host = ConstantUtil.serviceIp;
		 try {
			 InetAddress address = null;
			 if (host != null && host.trim().length() > 0) {
				 address = InetAddress.getByName(host);
			 }
			 if (address.isReachable(5000)){
				 return true;
			 }
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return false;
	 }
	
	//用来验证网管是否正常
	private boolean verificationRmi() throws Exception {
		DispatchUtil dispatchUtil = null;
		ServiceBean serviceBean = null;
		boolean flag = false;
		try {
			if(!isLine()){
				return false;
			}
			dispatchUtil = new DispatchUtil(RmiKeys.RMI_INIT);
			serviceBean = dispatchUtil.init();
			if (null != serviceBean) {
				// 1为成功
				if (serviceBean.getConnectionResult() == 1) {
					ConstantUtil.serviceBean = serviceBean;
					flag = true;
				} else {
					flag = false;
				}
			}

		} catch (java.rmi.ConnectException e) {
			flag = false;
		} finally {
			dispatchUtil = null;
			serviceBean = null;
		}
		return flag;
	}
}
