package com.nms.snmp.ninteface.impl.config;

import com.nms.db.bean.path.Segment;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.util.Mybatis_DBManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.rmi.ConnectException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TPLXml
{
  public static void main(String[] args)
  {
    Mybatis_DBManager.init("127.0.0.1");
    ConstantUtil.serviceFactory = new ServiceFactory();
    SnmpConfig.getInstanse().init();
    new TPLXml().getTPLXml();
  }
  
  public String getTPLXml()
  {
    String filePath = "";
    String version = ResourceUtil.srcStr("LBL_SNMPMODEL_VERSION");
    String[] xmlPath = { "snmpData\\NRM", "CM-PTN-TPLXml-A1-" + version + "-" + getTime() + ".xml" };
    FileTools fileTools = null;
    try
    {
      filePath = xmlPath[0] + File.separator + xmlPath[1];
      List<Segment> segments = getTPLList();
      createFile(xmlPath);
      Document doc = getDocument(xmlPath);
      createXML(doc, segments);
      fileTools = new FileTools();
      fileTools.putFile(doc, filePath);
      fileTools.zipFile(filePath, filePath.substring(0, filePath.length() - 5) + ".zip");
    }
    catch (Exception e)
    {
      ExceptionManage.dispose(e, getClass());
    }
    return filePath;
  }
  
  private List<Segment> getTPLList()
  {
    SegmentService_MB segmentService_MB = null;
    List<Segment> mapList = null;
    try
    {
      segmentService_MB = (SegmentService_MB)ConstantUtil.serviceFactory.newService_MB(9);
      mapList = segmentService_MB.select_north();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      UiUtil.closeService_MB(segmentService_MB);
    }
    return mapList;
  }
  
  private String getTime()
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
    return format.format(Long.valueOf(System.currentTimeMillis()));
  }
  
  private void createFile(String[] xmlPath)
    throws FileNotFoundException
  {
    File dirname = new File(xmlPath[0]);
    if (!dirname.exists()) {
      dirname.mkdirs();
    }
    new FileOutputStream(xmlPath[0] + File.separator + xmlPath[1]);
  }
  
  private Document getDocument(String[] xmlPath)
    throws Exception
  {
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.newDocument();
    }
    catch (Exception e)
    {
      ExceptionManage.dispose(e, getClass());
    }
    return null;
  }
  
  private void createXML(Document doc, List<Segment> segments)
  {
    doc.setXmlVersion("1.0");
    doc.setXmlStandalone(true);
    Element root = doc.createElement("DataFile");
    root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
    root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
    root.appendChild(XmlUtil.fileHeader(doc,"TopoLink"));
    Element emsList = createFileContent(doc, segments);
    root.appendChild(emsList);
    doc.appendChild(root);
  }
  
  private Element createFileContent(Document doc, List<Segment> segments)
  {
    Element Objects = doc.createElement("Objects");
    
    Element FieldName = doc.createElement("FieldName");
    createElementNode(doc, "N", "rmUID", FieldName, "i", "1");
    createElementNode(doc, "N", "nativeName", FieldName, "i", "2");
    createElementNode(doc, "N", "aEndNermUID", FieldName, "i", "3");
    createElementNode(doc, "N", "zEndNermUID", FieldName, "i", "4");
    createElementNode(doc, "N", "aEndPortrmUID", FieldName, "i", "5");
    createElementNode(doc, "N", "zEndPortrmUID", FieldName, "i", "5");
    createElementNode(doc, "N", "rate", FieldName, "i", "5");
    createElementNode(doc, "N", "direction", FieldName, "i", "5");
    createElementNode(doc, "N", "reality", FieldName, "i", "5");
    Objects.appendChild(FieldName);
    
    Element FieldValue = doc.createElement("FieldValue");
    for (Segment segment : segments)
    {
      Element Object = doc.createElement("Object");
      Object.setAttribute("rmUID", "3301EBTPL" + segment.getId());
      createElementNode(doc, "N", "3301EBTPL" + segment.getId(), Object, "i", "1");
      createElementNode(doc, "N", segment.getNAME(), Object, "i", "2");
      createElementNode(doc, "N", "3301EBNEL" + segment.getASITEID(), Object, "i", "3");
      createElementNode(doc, "N", "3301EBNEL" + segment.getZSITEID(), Object, "i", "4");
      createElementNode(doc, "N", "3301EBPRT" + segment.getAPORTID(), Object, "i", "5");
      createElementNode(doc, "N", "3301EBPRT" + segment.getZPORTID(), Object, "i", "5");
      createElementNode(doc, "N", "rate", Object, "i", "5");
      createElementNode(doc, "N", "CD_UNI", Object, "i", "5");
      createElementNode(doc, "N", "real", Object, "i", "5");
      FieldValue.appendChild(Object);
    }
    Objects.appendChild(FieldValue);
    return Objects;
  }
  
  private void createElementNode(Document doc, String childName, String childValue, Element node, String attname, String arrvalue)
  {
    Element e = doc.createElement(childName);
    e.setAttribute(attname, arrvalue);
    e.setTextContent(childValue);
    node.appendChild(e);
  }
  
  private boolean isLine()
  {
    String host = ConstantUtil.serviceIp;
    try
    {
      InetAddress address = null;
      if ((host != null) && (host.trim().length() > 0)) {
        address = InetAddress.getByName(host);
      }
      if (address.isReachable(5000)) {
        return true;
      }
    }
    catch (Exception e)
    {
      ExceptionManage.dispose(e, getClass());
    }
    return false;
  }
  
  private boolean verificationRmi()
    throws Exception
  {
    DispatchUtil dispatchUtil = null;
    ServiceBean serviceBean = null;
    boolean flag = false;
    try
    {
      if (!isLine()) {
        return false;
      }
      dispatchUtil = new DispatchUtil("rmiinit");
      serviceBean = dispatchUtil.init();
      if (serviceBean != null) {
        if (serviceBean.getConnectionResult() == 1)
        {
          ConstantUtil.serviceBean = serviceBean;
          flag = true;
        }
        else
        {
          flag = false;
        }
      }
    }
    catch (ConnectException e)
    {
      flag = false;
    }
    finally
    {
      dispatchUtil = null;
      serviceBean = null;
    }
    return flag;
  }
}
