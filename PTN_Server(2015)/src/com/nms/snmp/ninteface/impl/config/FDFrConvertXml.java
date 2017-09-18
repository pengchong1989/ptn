package com.nms.snmp.ninteface.impl.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
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


public class FDFrConvertXml {
	
	public static void main(String[] args) {
		ConstantUtil.serviceFactory = new ServiceFactory();
		FDFrConvertXml ProtectionGroupConverXml = new FDFrConvertXml();
		ProtectionGroupConverXml.getFDFrXml();
	}
	public void getFDFrXml(){
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-FDFr-"+version+"-"+this.getTime()+".xml"};
		FileTools fileTools = new FileTools();
	    try {
			String filePath = fileTools.createFile(xmlPath);
			Document doc = fileTools.getDocument();
			this.createXml(doc,this.getFDFrMap());
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public Map<Integer,Object> getFDFrMap() {
		Map<Integer,Object> flowDomainFragmentMap = new HashMap<Integer, Object>();
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			elanService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			List<ElineInfo> elineList = elineService.select();
			Map<Integer, List<EtreeInfo>> etreeMap = etreeService.select();
			Map<Integer, List<ElanInfo>> elanMap = elanService.select();
			for (ElineInfo eline : elineList) {                             						
				flowDomainFragmentMap.put(eline.getId(), eline);
			}
			for (Integer key : etreeMap.keySet()) {
				for (EtreeInfo etree : etreeMap.get(key)) {
					flowDomainFragmentMap.put(etree.getId(), etree);
				}
			}
			for (Integer key : elanMap.keySet()) {
				for (ElanInfo elan : elanMap.get(key)) {
					flowDomainFragmentMap.put(elan.getId(), elan);
				}
			}
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elanService);
		}
		return flowDomainFragmentMap;
	}

	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}
	
	private void createXml(Document doc,Map<Integer, Object> flowDomainFragmentMap) {
		FileTools fileTool = new FileTools();
		try {
			doc.setXmlVersion("1.0");
			doc.setXmlStandalone(true);
			Element root = fileTool.rootElement(doc);
			Element flowDomainFragmentList = this.crateFileContent(doc,flowDomainFragmentMap);
			root.appendChild(flowDomainFragmentList);
			doc.appendChild(root);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 根据数据内容生成对应的数据格式
	 * @param doc
	 * @param flowDomainFragmentMap
	 * @return
	 */
	private Element crateFileContent(Document doc,Map<Integer, Object> flowDomainFragmentMap) {
		Element flowDomainFragmentList = doc.createElement("FlowDomainFragmentList_T");
		String serviceName = "";
		String serviceId = "";
		String pwId = "";
		String aEndSite = "";
		String zEndSite = "";
		String fdfrState = "";//激活
		String fdfrType = "";
		int aSiteId = 0;
		int zSiteId = 0;
		int aAcId = 0 ;
		int zAcId = 0;
		TransmissionParamsUtil transmissionParamsUtil = new TransmissionParamsUtil();
		for(Integer indexId : flowDomainFragmentMap.keySet()){
			Element flowDomainFragment = doc.createElement("FlowDomainFragment_T");
			try {
				if(flowDomainFragmentMap.get(indexId) instanceof ElineInfo){
					ElineInfo elineInfo = (ElineInfo)flowDomainFragmentMap.get(indexId);;
					serviceId = elineInfo.getId()+"";
					serviceName= elineInfo.getName();
					pwId = elineInfo.getPwId()+"";
					aEndSite  = elineInfo.getASiteName();
					zEndSite  = elineInfo.getZSiteName();
					aSiteId = elineInfo.getaSiteId();
					aAcId = elineInfo.getaAcId();
					zSiteId = elineInfo.getzSiteId();
					zAcId = elineInfo.getzAcId();
				   if(elineInfo.getActiveStatus() ==1){
						 fdfrState = "SNCS_ACTIVE";
					}else{
						fdfrState = "SNCS_UNACTIVE";
					}
				   fdfrType = "FDFRT_POINT_TO_POINT";
				   
				}else if(flowDomainFragmentMap.get(indexId) instanceof EtreeInfo){
					EtreeInfo etreeInfo = (EtreeInfo)flowDomainFragmentMap.get(indexId);
					serviceId = etreeInfo.getId()+"";
					serviceName= etreeInfo.getName();
					pwId = etreeInfo.getPwId()+"";
					aEndSite  = etreeInfo.getASiteName();
					zEndSite  = etreeInfo.getZSiteName();
					aSiteId = etreeInfo.getaSiteId();
					aAcId = etreeInfo.getaAcId();
					zSiteId = etreeInfo.getzSiteId();
					zAcId = etreeInfo.getzAcId();
					
					if(etreeInfo.getActiveStatus() ==1){
						 fdfrState = "SNCS_ACTIVE";
					}else{
						fdfrState = "SNCS_UNACTIVE";
					}
					  fdfrType = "FDFRT_Multipoint_TO_Multipoint";
				}else if(flowDomainFragmentMap.get(indexId) instanceof ElanInfo){
					ElanInfo elanInfo = ((ElanInfo)flowDomainFragmentMap.get(indexId));
					serviceId = elanInfo.getId()+"";
					serviceName= elanInfo.getName();
					pwId = elanInfo.getPwId()+"";
					aEndSite  = elanInfo.getASiteName();
					zEndSite  = elanInfo.getZSiteName();
					aSiteId = elanInfo.getaSiteId();
					aAcId = elanInfo.getaAcId();
					zSiteId = elanInfo.getzSiteId();
					zAcId = elanInfo.getzAcId();
					if(elanInfo.getActiveStatus() ==1){
						 fdfrState = "SNCS_ACTIVE";
					}else{
						fdfrState = "SNCS_UNACTIVE";
					}
					  fdfrType = "FDFRT_Multipoint_TO_Multipoint";
				}
				this.crateElementNodeName(doc,"name",serviceId,flowDomainFragment);//Name
				this.createElementNode(doc, "userLabel", serviceName, flowDomainFragment);
				this.createElementNode(doc, "nativeEMSName",SnmpConfig.getInstanse().getEmsNativeName(), flowDomainFragment);
				this.createElementNode(doc, "direction","D_BIDIRECTIONAL", flowDomainFragment);
				this.crateElementNodeName(doc,"transmissionParams","1",flowDomainFragment);//transmissionParams
				this.createElementNode(doc,"fdfrState",fdfrState,flowDomainFragment);//激活状态
				this.createElementNode(doc,"fdfrType",fdfrType,flowDomainFragment);//FDFr类型
				this.crateElementNodeaEnd(doc,"aEnd",aEndSite,aSiteId,aAcId,flowDomainFragment,transmissionParamsUtil);//aEnd
				this.crateElementNodeaEnd(doc,"zEnd",zEndSite,zSiteId,zAcId,flowDomainFragment,transmissionParamsUtil);//zEnd
				this.crateElementNodeName(doc,"additionalInfo",pwId,flowDomainFragment);//additionalInfo
				
				flowDomainFragmentList.appendChild(flowDomainFragment);
			} catch (Exception e) {
				 ExceptionManage.dispose(e,this.getClass());
			}
		}
		return flowDomainFragmentList;
	}

	private void crateElementNodeaEnd(Document doc, String rootName, String siteName,int siteId,int acId,Element flowDomainFragment,TransmissionParamsUtil transmissionParamsUtil) {
		Element root = doc.createElement(rootName);
		AcPortInfoService_MB acService = null;
		PortService_MB portService = null;
		PortLagService_MB lagService = null;
		AcPortInfo acPort = null;
		  try {
		    portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
		    acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
		    lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
		    acPort =  acService.selectById(acId);
		    Element node = doc.createElement("tpData");
			Element nodeTpName = doc.createElement("tpName");
			Element tpNamenode = doc.createElement("node");
			this.createElementNode(doc, "name","EMS", tpNamenode);
			this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), tpNamenode);
			Element tpNamenodeOther = doc.createElement("node");
			this.createElementNode(doc, "name","ManagedElement", tpNamenodeOther);
			this.createElementNode(doc, "value",siteName, tpNamenodeOther);
			nodeTpName.appendChild(tpNamenode);
			nodeTpName.appendChild(tpNamenodeOther);
			
			Element nodeTransmissParams = doc.createElement("transmissionParams");
			Element nodeName = doc.createElement("LayeredParameterList_T");
			Element LayeredParameterList_TName = doc.createElement("LayeredParameters_T");
			this.createElementNode(doc,"layer",ELayerRate.PORT.getValue()+"",LayeredParameterList_TName);
			
			//判断 端口速率还是LAG速率 acPort.getPortId()>0 就是端口; acPort.getPortId() = 0 就是LAG的
			Element layerTransmissionParamsName = null;
			if(acPort.getPortId()>0){
				PortInst portInst = new PortInst();
				portInst.setPortId(acPort.getPortId());
				List<PortInst> portInstList = portService.select(portInst);
				if(portInstList != null && portInstList.size()>0){
					portInst = portInstList.get(0);
				}
				layerTransmissionParamsName =   transmissionParamsUtil.setTransmissionParamsModel(portInst,doc,null);
			}else if(acPort.getLagId()>0){
				PortLagInfo lagPortInfo = new PortLagInfo();
				lagPortInfo.setId(acPort.getLagId());
				List<PortLagInfo> LagPortInfoList = lagService.selectPortByCondition(lagPortInfo);
				if(LagPortInfoList != null && LagPortInfoList.size()>0){
					lagPortInfo = LagPortInfoList.get(0);
				}
				layerTransmissionParamsName = transmissionParamsUtil.setTransmissionParamsModel(null,doc,lagPortInfo);
			}
			LayeredParameterList_TName.appendChild(layerTransmissionParamsName);
			
			nodeName.appendChild(LayeredParameterList_TName);
			nodeTransmissParams.appendChild(nodeName);
			
			node.appendChild(nodeTpName);
			node.appendChild(nodeTransmissParams);
			root.appendChild(node);
			flowDomainFragment.appendChild(root);
				
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }	finally{
			 UiUtil.closeService_MB(acService);
			 UiUtil.closeService_MB(portService);
			 UiUtil.closeService_MB(lagService);
		 }
	}
	
	private void crateElementNodeName(Document doc, String nodeName, String value,Element flowDomainFragment) {
		Element d = doc.createElement(nodeName);
		try {
			if("name".equals(nodeName)){
				Element node = doc.createElement("node");
				this.createElementNode(doc, "name","EMS", node);
				this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), node);
				Element nodeOther = doc.createElement("node");
				this.createElementNode(doc, "name","TopologicalLink", nodeOther);
				this.createElementNode(doc, "value",value+"", nodeOther);
				d.appendChild(node);
				d.appendChild(nodeOther);
			}else if("transmissionParams".equals(nodeName)){
				Element node = doc.createElement("LayeredParameterList_T");
				this.createElementNode(doc, "layer",value, node);
				d.appendChild(node);
			}else if("additionalInfo".equals(nodeName)){
				Element node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","ServerConnections", node);
				this.createElementNode(doc, "value",value, node);
				d.appendChild(node);
			}else{
				this.createElementNode(doc, nodeName, value, flowDomainFragment);
			}
			flowDomainFragment.appendChild(d);
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
