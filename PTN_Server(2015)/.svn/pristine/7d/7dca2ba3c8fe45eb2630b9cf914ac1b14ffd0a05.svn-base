package com.nms.snmp.ninteface.impl.inventory;

import globaldefs.ProcessingFailureException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.system.code.Code;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.PortDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class TerminationPointTable extends TableHandler{

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		List<PortInst> portInsts = new ArrayList<PortInst>();
		PortService_MB portService = null;
		String oid = "";
		String[] oids = null;
		String siteId = null;
		DispatchInterface dispath = null;
		try {
			dispath  = new PortDispatch();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
		    PortInst port = new PortInst();
			portInsts = portService.select(port);
			if(vbList != null && vbList.size()>0){
				  for(VariableBinding vb : vbList){
					oid = vb.getOid().toString();
					oids = oid.trim().split("\\.");
					siteId =oids[oids.length-1];
                    for(PortInst portInst : portInsts){
                      if(portInst.getPortId() == Integer.parseInt(siteId)){
                    	  portInst.setSnmpName(vb.getVariable().toString().split(";")[1]);
                    	  dispath.excuteUpdate(portInst);
                   }
                 }			
			  }
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		PortInst portInfo = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
		    PortInst portInst = new PortInst();
			List<PortInst> portList = portService.select(portInst);
			if(portList != null && portList.size() >0){
				  for(int i = 0; i < portList.size(); i++){
					  try {
						  portInfo = portList.get(i);
						  if(!portInfo.getPortName().equals("system")){
							  Variable[] variables = new Variable[]{
									  new OctetString(portInfo.getPortId()+""),
									  new OctetString(portInfo.getSnmpName()),
									  new OctetString(SnmpConfig.getInstanse().getEmsNativeName()),
									  new OctetString(getUsingState(portInfo) ==true ?"TPCS_NOT_CONNECTED":"TPCS_BI_CONNECTED"),
									  new OctetString(getUsingState(portInfo) == true ? "one-way":"D_BIDIRECTIONAL"),
									  new OctetString(ELayerRate.PORT.getValue()+"/"+setPortRate(portInfo)),
									  new OctetString(portInfo.getModuleType().equals("(o)")?"electricty":"optical"),
							  };
							  addRow(new OID(portInfo.getPortId()+""), variables);
						  }
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				 }
				  //生成对应的文件
//				  createTerminationPointFile(portList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 获取端口的使用状态，以太网端口没有段为未使用， e1端口没有创建业务为未使用
	 * 
	 * @param portInst
	 * @return true=占用 false=空闲
	 * @throws Exception
	 */
	private boolean getUsingState(PortInst portInst) throws ProcessingFailureException {
		boolean result = false;
		PortService_MB portService = null;
		try {
			if ("e1".equals(portInst.getPortType())) {
				if (portInst.getIsOccupy() == 0) {
					result = true;
				}
			} else if ("NONE".equals(portInst.getPortType()) || "NNI".equals(portInst.getPortType()) || "UNI".equals(portInst.getPortType())) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				result = portService.getPortUse(portInst);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}

		return result;
	}

/**
 * @param portInst
 *            端口信息
 * @return 端口的速率
 */
private String setPortRate(PortInst portInst){

	/**
	 * 自动协商/1000M全双工/100M全双工/10M全双工 fe 端口的 “自动协商”相当于 “100M” ge 端口的 “自动协商”相当于 “1000M” xg 端口的 “自动协商”相当于 “10000M” fx 端口的 “自动协商”相当于 “1000M”
	 */
	Code portWorkModel = null;
	try {
		portWorkModel = UiUtil.getCodeById(portInst.getPortAttr().getWorkModel() == 0 ? 70 : portInst.getPortAttr().getWorkModel());
		// 端口为"自动协商"且 端口属于”fe“类型的
		if (portInst.getPortName().contains("fe") || portInst.getPortName().contains("ge") || portInst.getPortName().contains("xg") || portInst.getPortName().contains("fx")) {
			if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("fe")) {
				return "100M";
			}
			if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("ge")) {
				return "1000M";
			}
			if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("xg")) {
				return "10000M";
			}
			if (portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME)) && portInst.getPortName().contains("fx")) {
				return "1000M";
			} else {
				return portWorkModel.getCodeName();
			}
		} else {
			return "0";
		}
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	} finally {
		portWorkModel = null;
	}
	return "0";
}

/**
 * 根据数据生成对应的数据文件
 * @param portList
 */
private void createTerminationPointFile(List<PortInst> portList) {
	 FileTools fileTool = new FileTools();
	try {
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		 String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-PTP-"+version+"-"+this.getTime()+"-P00.xml"};
		String filePath = fileTool.createFile(xmlPath);
		Document doc = fileTool.getDocument();
		createXml(doc,portList);
		fileTool.putFile(doc, filePath);
		fileTool.zipFile(filePath, filePath+".zip");
	} catch (Exception e) {
		 ExceptionManage.dispose(e,this.getClass());
	}
 }

private String getTime() {
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
	return format.format(System.currentTimeMillis());
}

private void createXml(Document doc, List<PortInst> portList) {
	FileTools fileTool = new FileTools();
	try {
		doc.setXmlVersion("1.0");
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
	for(PortInst portInfo : portList){
		Element terminationPoint = doc.createElement("TerminationPoint_T");
		try {
			this.crateElementNodeName(doc,"name",portInfo,terminationPoint);//Name
			this.createElementNode(doc,"userLabel",portInfo.getPortName(),terminationPoint);//userLabel
			this.createElementNode(doc,"nativeEMSName",SnmpConfig.getInstanse().getEmsNativeName(),terminationPoint);//nativeEMSName
			this.createElementNode(doc,"connectionState",getUsingState(portInfo) ==true ?"TPCS_BI_CONNECTED":"TPCS_NOT_CONNECTED",terminationPoint);//nativeEMSName
			this.createElementNode(doc,"direction",getUsingState(portInfo) ==true ?"TPCS_BI_CONNECTED":"TPCS_NOT_CONNECTED",terminationPoint);//nativeEMSName
			this.crateElementNodeName(doc,"transmissionParams",portInfo,terminationPoint);//nativeEMSName
			this.crateElementNodeName(doc,"additionalInfo",portInfo,terminationPoint);//nativeEMSName
			TerminationPointList.appendChild(terminationPoint);
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
	}
	return TerminationPointList;
}

private void crateElementNodeName(Document doc, String nodeName,PortInst portInfo, Element terminationPoint) {
	try {
		Element d = doc.createElement(nodeName);
		if("name".equals(nodeName)){
			Element node = doc.createElement("node");
			this.createElementNode(doc, "name","EMS", node);
			this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), node);
			Element nodeOther = doc.createElement("node");
			this.createElementNode(doc, "name","ManagedElement", nodeOther);
			this.createElementNode(doc, "value",portInfo.getSiteId()+"", nodeOther);
			Element ptpNode = doc.createElement("node");
			this.createElementNode(doc, "name","PTP", ptpNode);
			this.createElementNode(doc, "value","slot="+portInfo.getSlotNumber()+"/port="+portInfo.getPortId(), ptpNode);
			d.appendChild(node);
			d.appendChild(nodeOther);
			d.appendChild(ptpNode);
		}
		else if("transmissionParams".equals(nodeName)){
			Element node = doc.createElement("LayeredParameterList_T");
			Element layeredParameters_node = doc.createElement("LayeredParameters_T");
			this.createElementNode(doc, "layer",ELayerRate.PORT.getValue()+"", layeredParameters_node);
			Element layerTransmissionParams_node = doc.createElement("layerTransmissionParams");
			Element oneParam_node = doc.createElement("oneParam");
			this.createElementNode(doc, "name","TrafficMappingTo_Table_Priority", oneParam_node);
			this.createElementNode(doc, "value",portInfo.getPortAttr().getMaxFrameSize(), oneParam_node);
			layerTransmissionParams_node.appendChild(oneParam_node);
			layeredParameters_node.appendChild(layerTransmissionParams_node);
			node.appendChild(layeredParameters_node);
			d.appendChild(node);
		}else if("additionalInfo".equals(nodeName)){
			Element oneParam_node = doc.createElement("oneParam");
			this.createElementNode(doc, "name","PhysicalType", oneParam_node);
			this.createElementNode(doc, "value",portInfo.getModuleType().equals("(o)")?"electricty":"optical", oneParam_node);
			d.appendChild(oneParam_node);
		}
		terminationPoint.appendChild(d);
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
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


}
