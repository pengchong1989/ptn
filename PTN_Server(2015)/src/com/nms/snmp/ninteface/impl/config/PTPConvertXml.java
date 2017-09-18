package com.nms.snmp.ninteface.impl.config;

import globaldefs.ProcessingFailureException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.snmp.ninteface.util.TransmissionParamsUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class PTPConvertXml {
	
	public static void main(String[] args) throws Exception {
		ConstantUtil.serviceFactory = new ServiceFactory();
		new PTPConvertXml().getPTPxml();
	}
	/**
	 * 根据数据生成对应的数据文件
	 * @param portList
	 */
	public void getPTPxml() {
		 FileTools fileTool = new FileTools();
		try {
			String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
			 String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-PTP-"+version+"-"+this.getTime()+".xml"};
			String filePath = fileTool.createFile(xmlPath);
			Document doc = fileTool.getDocument();
			createXml(doc,this.getPortList());
			fileTool.putFile(doc, filePath);
			fileTool.zipFile(filePath, filePath+".zip");
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
	 }

	private List<PortInst> getPortList() {
		PortService_MB portService = null;
		List<PortInst> portList = new ArrayList<PortInst>();
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portList = portService.select(portInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return portList;
	}

	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}

	private void createXml(Document doc, List<PortInst> portList) {
		FileTools fileTool = new FileTools();
		try {
			doc.setXmlVersion("1.0");
			doc.setXmlStandalone(true);
			Element root =fileTool.rootElement(doc);
			Element terminationPointList = this.crateFileContent(doc,portList);
			root.appendChild(terminationPointList);
			doc.appendChild(root);
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
	}

	private Element crateFileContent(Document doc, List<PortInst> portList) {
		Element TerminationPointList = doc.createElement("TerminationPointList_T");
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		portLagInfoList = getAllPortLagInfo();
		for(PortInst portInfo : portList){
			if(!("system").equals(portInfo.getPortType())){
				try {
					TerminationPointList.appendChild(getterminationPoint(doc,portInfo));
				} catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				}	
			}
		}
		if(portLagInfoList != null && portLagInfoList.size() > 0 ){
			for(PortLagInfo portLagInst: portLagInfoList){
					try {
						TerminationPointList.appendChild(getterminationPoint(doc,portLagInst));
					} catch (Exception e) {
						 ExceptionManage.dispose(e,this.getClass());
					}	
			 }
		}
		
		return TerminationPointList;
	}
	
	/**
	 * @param doc
	 * @param object
	 * @return
	 */
	private Element getterminationPoint(Document doc,Object object){
		
		Element terminationPoint = doc.createElement("TerminationPoint_T");
		String name = "";
		boolean isUser = false;
		try {
			if(object instanceof PortInst){
				name = ((PortInst)object).getPortName()+"";
				isUser = getUsingState(((PortInst)object));
			}else if(object instanceof PortLagInfo){  
				name ="lag/"+((PortLagInfo)object).getLagID();
				isUser = ((PortLagInfo)object).getIsUsed() == 1?true:false;
			}
			
			this.crateElementNodeName(doc,"name",object,terminationPoint);//Name
			this.createElementNode(doc,"userLabel",name,terminationPoint);//userLabel
			this.createElementNode(doc,"nativeEMSName",name,terminationPoint);//nativeEMSName
			
			this.createElementNode(doc,"connectionState",isUser ==true ?"TPCS_BI_CONNECTED":"TPCS_NOT_CONNECTED",terminationPoint);//nativeEMSName
			this.createElementNode(doc,"direction","D_BIDIRECTIONAL",terminationPoint);//nativeEMSName
			this.crateElementNodeName(doc,"transmissionParams",object,terminationPoint);//nativeEMSName
			this.crateElementNodeName(doc,"additionalInfo",object,terminationPoint);//nativeEMSName
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}	
		return terminationPoint;
	}
	
	/**
	 * 获取端口的使用状态，以太网端口没有段为未使用， e1端口没有创建业务为未使用
	 * @param portInst
	 * @return true=占用 false=空闲
	 * @throws Exception
	 */
	private boolean getUsingState(PortInst portInst) throws ProcessingFailureException {
		boolean result = false;
		PortService_MB portService = null;
		try {
			if(portInst.getIsOccupy() == 1){
				result = true;
			}else{
				if ("NONE".equals(portInst.getPortType()) || "NNI".equals(portInst.getPortType()) || "UNI".equals(portInst.getPortType())) {
					portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
					result = portService.getPortUse(portInst);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}

		return result;
	}

	private void crateElementNodeName(Document doc, String nodeName,Object object, Element terminationPoint) {
		String siteId = "";
		String slotNumber = "";
		String portId = "";
		String ModuleType = "";
		int value = 0;
		SiteService_MB siteService = null;
		try {
			if(object instanceof PortInst){
				siteId = ((PortInst)object).getSiteId()+"";
				slotNumber = ((PortInst)object).getSlotNumber()+"";
				portId = ((PortInst)object).getPortId()+"";
				ModuleType =((PortInst)object).getModuleType()+"";
				if(((PortInst)object).getPortType().contains("e1")){
					value = ELayerRate.E1.getValue();
				}else{
					value = ELayerRate.PORT.getValue();
				}
				
			}else if(object instanceof PortLagInfo){
				siteId = ((PortLagInfo)object).getSiteId()+"";
				slotNumber = ((PortLagInfo)object).getLagID()+"";
				portId = ((PortLagInfo)object).getLagID()+"";
				ModuleType ="(s)";
				value = ELayerRate.LAG.getValue();
			}
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteInst = siteService.select(Integer.parseInt(siteId));
			Element d = doc.createElement(nodeName);
			if("name".equals(nodeName)){
				Element node = doc.createElement("node");
				this.createElementNode(doc, "name","EMS", node);
				this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), node);
				Element nodeOther = doc.createElement("node");
				this.createElementNode(doc, "name","ManagedElement", nodeOther);
				this.createElementNode(doc, "value",siteId, nodeOther);
				Element ptpNode = doc.createElement("node");
				this.createElementNode(doc, "name","PTP", ptpNode);
				this.createElementNode(doc, "value","/rack="+siteInst.getRack()+"/shelf="+siteInst.getShelf()+"/slot="+slotNumber+"/port="+portId, ptpNode);
				d.appendChild(node);
				d.appendChild(nodeOther);
				d.appendChild(ptpNode);
			}
			else if("transmissionParams".equals(nodeName)){
				TransmissionParamsUtil transmissionParamsUtil = new TransmissionParamsUtil();
				Element node = doc.createElement("LayeredParameterList_T");
				Element layeredParameters_node = doc.createElement("LayeredParameters_T");
				this.createElementNode(doc, "layer",value+"", layeredParameters_node);
				Element layerTransmissionParams_node = null;
				if(object instanceof PortInst){
					siteId = ((PortInst)object).getSiteId()+"";
					slotNumber = ((PortInst)object).getSlotNumber()+"";
					portId = ((PortInst)object).getPortId()+"";
					ModuleType =((PortInst)object).getModuleType()+"";
					layerTransmissionParams_node =  transmissionParamsUtil.setTransmissionParamsModel(((PortInst)object),doc,null);
					
				}else if(object instanceof PortLagInfo){
					layerTransmissionParams_node =  transmissionParamsUtil.setTransmissionParamsModel(null,doc,((PortLagInfo)object));
				}
				layeredParameters_node.appendChild(layerTransmissionParams_node);
				node.appendChild(layeredParameters_node);
				d.appendChild(node);
			}else if("additionalInfo".equals(nodeName)){
				Element oneParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","PhysicalType", oneParam_node);
				this.createElementNode(doc, "value",ModuleType.equals("(o)")?"electricty":"optical", oneParam_node);
				d.appendChild(oneParam_node);
			}
			terminationPoint.appendChild(d);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 根据名称创建元素,并赋值
	 */
	private void createElementNode(Document doc, String childName, String childValue, Element element){
		Element e = doc.createElement(childName);
	    e.setTextContent(childValue);
	    element.appendChild(e);
	}
	/**
	 * 获取所有的LAG断口
	 * @param siteId
	 * @return
	 */
	private List<PortLagInfo> getAllPortLagInfo() {
		PortLagService_MB lagService = null;
		PortLagInfo portLagInfo = null;
		List<PortLagInfo> portLagInfoList = null;
		try {
			lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = new PortLagInfo();
			portLagInfoList = lagService.selectByCondition(portLagInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(lagService);
		}
		return portLagInfoList;
	}
}
