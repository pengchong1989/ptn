package com.nms.snmp.ninteface.impl.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.AttributeValueExp;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.util.Mybatis_DBManager;

public class TPBXml {
	public static void main(String[] args) {
		Mybatis_DBManager.init("127.0.0.1");
		ConstantUtil.serviceFactory = new ServiceFactory();
		SnmpConfig.getInstanse().init();
		new TPBXml().getTPBXml();
	}
	
	public String getTPBXml() {
		//CMCC-PTN-NRM-ME-V1.0.0-20140411-1602-P00.xml
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\NRM", "CM-PTN-TPB-A1-"+version+"-"+XmlUtil.getTime()+".xml"};
		FileTools fileTools = null;
		try {
			filePath = xmlPath[0] + File.separator + xmlPath[1];//生成文件路径
			List<Tunnel> tunnelList = this.getProtTunnelList();
	    	this.createFile(xmlPath);//根据文件路径和文件名生成xml文件
	    	Document doc = this.getDocument(xmlPath);//生成doucument
		    this.createXML(doc,tunnelList);//生成xml文件内容
		    XmlUtil.createFile(doc, "CM-PTN-TPB-A1-");
		} catch (Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
		return filePath;
	}

    	private List<Tunnel> getProtTunnelList() {
    		List<Tunnel> tunnelList = new ArrayList<Tunnel>();
    		TunnelService_MB tunnelService = null;
    		try {
    			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
    			List<Tunnel> tList = tunnelService.select();
    			for (Tunnel tunnel : tList) {
    				if(!tunnel.getTunnelType().equals("185")){
    					tunnelList.add(tunnel);
    				}
    			}
    		} catch (Exception e) {
    			ExceptionManage.dispose(e, this.getClass());
    		} finally {
    			UiUtil.closeService_MB(tunnelService);
    		}
    		return tunnelList;
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
	private void createXML(Document doc,List<Tunnel> tunnelList){
		doc.setXmlVersion("1.0");
		doc.setXmlStandalone(true);
		Element root = doc.createElement("DataFile");
		root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
		root.appendChild(XmlUtil.fileHeader(doc,"TunnelPGBackupInfo"));
		Element emsList = this.createFileContent(doc,tunnelList);
		root.appendChild(emsList);
		doc.appendChild(root);
	}
	
	private Element createFileContent(Document doc,List<Tunnel> tunnelList) {
		Element Objects = doc.createElement("Objects");
		
		Element FieldName = doc.createElement("FieldName");
		this.createElementNode(doc, "N", "rmUID", FieldName, "i", "1");
		this.createElementNode(doc, "N", "nativeName", FieldName, "i", "2");
		this.createElementNode(doc, "N", "reversionMode", FieldName, "i", "3");
		this.createElementNode(doc, "N", "type", FieldName, "i", "4");
		Objects.appendChild(FieldName);
		
		Element FieldValue = doc.createElement("FieldValue");
		for (Tunnel tunnel :tunnelList) {
			
			Element Object = doc.createElement("Object");
			Object.setAttribute("rmUID","3301EBCRD1"+tunnel.getProtectTunnelId());
			this.createElementNode(doc, "N", "3301EBCRD1"+tunnel.getProtectTunnelId(), Object, "i", "1");
			this.createElementNode(doc, "N", "3301EBCRD1"+tunnel.getName(), Object, "i", "2");
			this.createElementNode(doc, "N", "RM_REVERTIVE", Object, "i", "3");
			this.createElementNode(doc, "N", "1:1", Object, "i", "4");
			FieldValue.appendChild(Object);
		}
		
		Objects.appendChild(FieldValue);
		return Objects;
	}

	/**
	 * 根据名称创建元素,并赋值
	 */
	private void createElementNode(Document doc, String childName, String childValue, Element node,String attname,String arrvalue){
		Element e = doc.createElement(childName);
		e.setAttribute(attname,arrvalue);
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
