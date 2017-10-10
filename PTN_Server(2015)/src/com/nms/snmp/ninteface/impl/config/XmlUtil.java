package com.nms.snmp.ninteface.impl.config;

import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.ui.manager.DateUtil;

public class XmlUtil {
	public static Element fileHeader(Document doc){
		Element FileHeader = doc.createElement("FileHeader");
		
		Element TimeStamp = doc.createElement("TimeStamp");
		TimeStamp.setTextContent(DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm:ss").replace(" ", "T"));
		FileHeader.appendChild(TimeStamp);
		
		Element TimeZone = doc.createElement("TimeZone");
		TimeZone.setTextContent("UTF-8");
		FileHeader.appendChild(TimeZone);
		
		Element VendorName = doc.createElement("VendorName");
		VendorName.setTextContent("EBANG");
		FileHeader.appendChild(VendorName);
		
		Element ElementType = doc.createElement("ElementType");
		ElementType.setTextContent("PTN");
		FileHeader.appendChild(ElementType);
		
		Element CmVersion = doc.createElement("CmVersion");
		CmVersion.setTextContent("1.2");
		FileHeader.appendChild(CmVersion);
		return FileHeader;
	}
}
