package com.nms.snmp.ninteface.impl.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class XmlUtil {
	public static Element fileHeader(Document doc,String objectType){
		Element FileHeader = doc.createElement("FileHeader");
		
		Element TimeStamp = doc.createElement("TimeStamp");
		TimeStamp.setTextContent(DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm:ss").replace(" ", "T"));
		FileHeader.appendChild(TimeStamp);
		
		Element TimeZone = doc.createElement("TimeZone");
		TimeZone.setTextContent("UTC+8");
		FileHeader.appendChild(TimeZone);
		
		Element VendorName = doc.createElement("VendorName");
		VendorName.setTextContent("EBANG");
		FileHeader.appendChild(VendorName);
		
		Element ElementType = doc.createElement("ElementType");
		ElementType.setTextContent("PTN");
		FileHeader.appendChild(ElementType);
		
		Element CmVersion = doc.createElement("CmVersion");
		CmVersion.setTextContent("1.0");
		FileHeader.appendChild(CmVersion);
		
		Element rmUID = doc.createElement("rmUID");
		rmUID.setTextContent("");
		FileHeader.appendChild(rmUID);
		
		Element Dn = doc.createElement("Dn");
		Dn.setTextContent("");
		FileHeader.appendChild(Dn);
		
		Element UserLabel = doc.createElement("UserLabel");
		UserLabel.setTextContent("");
		FileHeader.appendChild(UserLabel);
		
		
		Element ObjectType = doc.createElement("ObjectType");
		ObjectType.setTextContent(objectType);
		FileHeader.appendChild(ObjectType);
		
		return FileHeader;
	}
	
	public static String createFile(Document doc,String fileName){
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\NRM", fileName+version+"-"+getTime()+".xml"};
		FileTools fileTools = null;
		try {
			filePath = xmlPath[0] + File.separator + xmlPath[1];//生成文件路径
		    fileTools = new FileTools();
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath.substring(0, filePath.length())+".zip");
		} catch (Exception e){
			ExceptionManage.dispose(e, XmlUtil.class);
		}
		return "";
	}
	
	public static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
		return format.format(System.currentTimeMillis()).replace("-", "");
	}
}
