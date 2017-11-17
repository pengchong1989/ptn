package com.nms.snmp.ninteface.impl.config;

import com.nms.db.bean.system.Field;
import com.nms.model.system.SubnetService_MB;
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

public class SBNXml
{
  public static void main(String[] args)
  {
    Mybatis_DBManager.init("127.0.0.1");
    ConstantUtil.serviceFactory = new ServiceFactory();
    SnmpConfig.getInstanse().init();
    new SBNXml().getSBNXml();
  }
  
  public String getSBNXml()
  {
    String filePath = "";
    String version = ResourceUtil.srcStr("LBL_SNMPMODEL_VERSION");
    String[] xmlPath = { "snmpData\\NRM", "CM-PTN-SBN-A1-" + version + "-" + getTime() + ".xml" };
    FileTools fileTools = null;
    try
    {
      filePath = xmlPath[0] + File.separator + xmlPath[1];
      List<Field> mapList = getSBNList();
      createFile(xmlPath);
      Document doc = getDocument(xmlPath);
      createXML(doc, mapList);
      XmlUtil.createFile(doc, "CM-PTN-SBN-A1-");
    }
    catch (Exception e)
    {
      ExceptionManage.dispose(e, getClass());
    }
    return filePath;
  }
  
  private List<Field> getSBNList()
  {
    SubnetService_MB subnetService_MB = null;
    List<Field> fields = null;
    try
    {
      subnetService_MB = (SubnetService_MB)ConstantUtil.serviceFactory.newService_MB(88);
      fields = subnetService_MB.select_SBN_north();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      UiUtil.closeService_MB(subnetService_MB);
    }
    return fields;
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
  
  private void createXML(Document doc, List<Field> mapList)
  {
    doc.setXmlVersion("1.0");
    doc.setXmlStandalone(true);
    Element root = doc.createElement("DataFile");
    root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/Configure/v1");
    root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/Configure/v1 ../Inventory.xsd");
    root.appendChild(XmlUtil.fileHeader(doc,"SubNet"));
    Element emsList = createFileContent(doc, mapList);
    root.appendChild(emsList);
    doc.appendChild(root);
  }
  
  private Element createFileContent(Document doc, List<Field> mapList)
  {
    Element Objects = doc.createElement("Objects");
    
    Element FieldName = doc.createElement("FieldName");
    createElementNode(doc, "N", "rmUID", FieldName, "i", "1");
    createElementNode(doc, "N", "nativeName", FieldName, "i", "2");
    createElementNode(doc, "N", "parentSubnetrmUID", FieldName, "i", "3");
    createElementNode(doc, "N", "xPos", FieldName, "i", "4");
    createElementNode(doc, "N", "yPos", FieldName, "i", "5");
    Objects.appendChild(FieldName);
    
    Element FieldValue = doc.createElement("FieldValue");
    for (Field field : mapList)
    {
      Element Object = doc.createElement("Object");
      Object.setAttribute("rmUID", "3301EBSBN" + field.getId());
      createElementNode(doc, "N", "3301EBSBN" + field.getId(), Object, "i", "1");
      createElementNode(doc, "N", field.getFieldName(), Object, "i", "2");
      createElementNode(doc, "N", "", Object, "i", "3");
      createElementNode(doc, "N", field.getFieldX()+"", Object, "i", "4");
      createElementNode(doc, "N", field.getFieldY()+"", Object, "i", "5");
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
