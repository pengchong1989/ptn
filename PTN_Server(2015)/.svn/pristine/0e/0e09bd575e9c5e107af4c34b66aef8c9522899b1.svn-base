package com.nms.snmp.ninteface.impl.inventory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class FlowDomainFragmentTable extends TableHandler {

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		String id = "";
		String oid = "";
		String[] oids = null;
		try {
			Map<Integer,Object> flowDomainFragmentMap = getAllFlowDomainFragment();	
			if(vbList != null && vbList.size()>0){
			 for(VariableBinding vb : vbList){
				oid = vb.getOid().toString(); 
				oids = oid.trim().split("\\.");
				id =oids[oids.length-1];
				for(Integer indexId : flowDomainFragmentMap.keySet()){
					if(indexId == Integer.parseInt(id)){
					  dispathSerivce(flowDomainFragmentMap.get(indexId),vb.getVariable().toString().split(";")[1]);
					}
			  }
			}
		 }
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
		return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		for(VariableBinding vb : vbList){
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		Map<Integer,Object> flowDomainFragmentMap = new HashMap<Integer, Object>();
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanService = null;
		AcPortInfoService_MB acService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			elanService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			List<ElineInfo> elineList = elineService.select();
			Map<Integer, List<EtreeInfo>> etreeMap = etreeService.select();
			Map<Integer, List<ElanInfo>> elanMap = elanService.select();
			Map<Integer, AcPortInfo> acMap = new HashMap<Integer, AcPortInfo>();
			AcPortInfo acinfo =new AcPortInfo();
			List<AcPortInfo> acList = acService.queryByAcPortInfo(acinfo);
			for (AcPortInfo ac : acList) {
				acMap.put(ac.getId(), ac);
			}
			for (ElineInfo eline : elineList) {                             						 // 层参数
				String[] attrs = {eline.getId()+"", eline.getName(), eline.getName(), "bidirection", "1",
								  eline.getActiveStatus()==1?"activity":"unactivity", "PointToPoint", "siteId:"+eline.getaSiteId()+"" +
								  		"/portId:"+acMap.get(eline.getaAcId()).getPortId(),"siteId:"+eline.getzSiteId()+"" +
								  		"/portId:"+acMap.get(eline.getzAcId()).getPortId(), eline.getPwId()+""};
				this.createRow(attrs);
				flowDomainFragmentMap.put(eline.getId(), eline);
			}
			for (Integer key : etreeMap.keySet()) {
				for (EtreeInfo etree : etreeMap.get(key)) {
					String[] attrs = {etree.getId()+"", etree.getName(), etree.getName(), "bidirection", "1",
									  etree.getActiveStatus()==1?"activity":"unactivity", "PointToMultipoint", 
									  "siteId:"+etree.getaSiteId()+"/portId:"+acMap.get(etree.getaAcId()).getPortId(),
									  "siteId:"+etree.getzSiteId()+"/portId:"+acMap.get(etree.getzAcId()).getPortId(),
									  etree.getPwId()+""};
					this.createRow(attrs);
					flowDomainFragmentMap.put(etree.getId(), etree);
				}
			}
			for (Integer key : elanMap.keySet()) {
				for (ElanInfo elan : elanMap.get(key)) {
					String[] attrs = {elan.getId()+"", elan.getName(), elan.getName(), "bidirection", "1",
							          elan.getActiveStatus()==1?"activity":"unactivity", "MultipointToMultipoint",
							          "siteId:"+elan.getaSiteId()+"/portId:"+acMap.get(elan.getaAcId()).getPortId(),
							          "siteId:"+elan.getzSiteId()+"/portId:"+acMap.get(elan.getzAcId()).getPortId(),
						              elan.getPwId()+""};
					this.createRow(attrs);
					flowDomainFragmentMap.put(elan.getId(), elan);
				}
			}
			
//			//生成对应的文件
//			crateFlowDomainFragmentFile(flowDomainFragmentMap);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(elanService);
			UiUtil.closeService_MB(etreeService);
		}
	}
	
	/**
	 * 根据数据生成对应的数据文件
	 * @param portList
	 */
	private void crateFlowDomainFragmentFile(Map<Integer, Object> flowDomainFragmentMap) {
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-FDFr-"+version+"-"+this.getTime()+"-P00.xml"};
		FileTools fileTools = new FileTools();
	    try {
			String filePath = fileTools.createFile(xmlPath);
			Document doc = fileTools.getDocument();
			createXml(doc,flowDomainFragmentMap);
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}
	
	private void createRow(String[] attrs) {
		int i = 0;
		Variable[] rowValues = new Variable[] {
			new OctetString(attrs[i++]),
			new OctetString(attrs[i++]),
			new OctetString(attrs[i++]),
			new OctetString(attrs[i++]),//Direction
			new OctetString(attrs[i++]),//TransmissionParams
			new OctetString(attrs[i++]),
			new OctetString(attrs[i++]),//FdfrType,点到点,多点到多点
			new OctetString(attrs[i++]),//asiteId
			new OctetString(attrs[i++]),//zsiteId
			new OctetString(attrs[i++]),//pwId
		};
		addRow(new OID(""+attrs[0]), rowValues);
	}
	
	/**
	 * 返回所有的业务
	 * @return
	 */
	private Map<Integer,Object> getAllFlowDomainFragment(){
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
			if(elineList != null && elineList.size()>0){
				for(ElineInfo eline : elineList){
					flowDomainFragmentMap.put(eline.getId(), eline);
				}
			}
			if(etreeMap != null && etreeMap.size()>0){
				for (Integer key : etreeMap.keySet()) {
					for (EtreeInfo etree : etreeMap.get(key)) {
						flowDomainFragmentMap.put(etree.getId(), etree);
					}
				}
			}
			if(elanMap != null && elanMap.size()>0){
				for (Integer key : elanMap.keySet()) {
					for (ElanInfo elanInfo : elanMap.get(key)) {
						flowDomainFragmentMap.put(elanInfo.getId(), elanInfo);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(elanService);
			UiUtil.closeService_MB(etreeService);
		}
		return flowDomainFragmentMap;
	}
	
	
	/**
	 * 修改网管上业务的属性
	 * @param object
	 * @param value
	 */
	private void dispathSerivce(Object object,String value){
		DispatchInterface dispath = null;
		try {
			if(object instanceof EtreeInfo){
				EtreeInfo etreeInfo = (EtreeInfo)object;
				etreeInfo.setName(value);
				List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
				etreeInfoList.add(etreeInfo);
				dispath = new EtreeDispatch();
				dispath.excuteUpdate(etreeInfoList);
			}else if(object instanceof ElineInfo){
				ElineInfo elineInfo = (ElineInfo)object;
				elineInfo.setName(value);
				dispath = new ElineDispatch();
				dispath.excuteUpdate(elineInfo);
			}else if(object instanceof ElanInfo){
				ElanInfo elanInfo = (ElanInfo)object;
				List<ElanInfo> elanInfoList = new ArrayList<ElanInfo>();
				elanInfo.setName(value);
				elanInfoList.add(elanInfo);
				dispath = new ElanDispatch();
				dispath.excuteUpdate(elanInfoList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void createXml(Document doc,Map<Integer, Object> flowDomainFragmentMap) {
		FileTools fileTool = new FileTools();
		try {
			doc.setXmlVersion("1.0");
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
		String maxFrameSize = " ";
		for(Integer indexId : flowDomainFragmentMap.keySet()){
			Element flowDomainFragment = doc.createElement("FlowDomainFragment_T");
			try {
				if(flowDomainFragmentMap.get(indexId) instanceof ElineInfo){
					serviceId = ((ElineInfo)flowDomainFragmentMap.get(indexId)).getId()+"";
					serviceName= ((ElineInfo)flowDomainFragmentMap.get(indexId)).getName();
					pwId = ((ElineInfo)flowDomainFragmentMap.get(indexId)).getPwId()+"";
					aEndSite  = ((ElineInfo)flowDomainFragmentMap.get(indexId)).getASiteName();
					zEndSite  = ((ElineInfo)flowDomainFragmentMap.get(indexId)).getZSiteName();
				}else if(flowDomainFragmentMap.get(indexId) instanceof EtreeInfo){
					serviceId = ((EtreeInfo)flowDomainFragmentMap.get(indexId)).getId()+"";
					serviceName= ((EtreeInfo)flowDomainFragmentMap.get(indexId)).getName();
					pwId = ((EtreeInfo)flowDomainFragmentMap.get(indexId)).getPwId()+"";
					aEndSite  = ((EtreeInfo)flowDomainFragmentMap.get(indexId)).getASiteName();
					zEndSite  = ((EtreeInfo)flowDomainFragmentMap.get(indexId)).getZSiteName();
				}else if(flowDomainFragmentMap.get(indexId) instanceof ElanInfo){
					serviceId = ((ElanInfo)flowDomainFragmentMap.get(indexId)).getId()+"";
					serviceName= ((ElanInfo)flowDomainFragmentMap.get(indexId)).getName();
					pwId = ((ElanInfo)flowDomainFragmentMap.get(indexId)).getPwId()+"";
					aEndSite  = ((ElanInfo)flowDomainFragmentMap.get(indexId)).getASiteName();
					zEndSite  = ((ElanInfo)flowDomainFragmentMap.get(indexId)).getZSiteName();
				}
				this.crateElementNodeName(doc,"name",serviceId,flowDomainFragment);//Name
				this.createElementNode(doc, "userLabel", serviceName, flowDomainFragment);
				this.createElementNode(doc, "nativeEMSName",SnmpConfig.getInstanse().getEmsNativeName(), flowDomainFragment);
				this.createElementNode(doc, "direction","D_BIDIRECTIONAL", flowDomainFragment);
				this.crateElementNodeName(doc,"transmissionParams",ELayerRate.ETHSERVICE.getValue()+"",flowDomainFragment);//transmissionParams
				this.crateElementNodeaEnd(doc,"aEnd",aEndSite,ELayerRate.PORT.getValue()+"",maxFrameSize,flowDomainFragment);//aEnd
				this.crateElementNodeaEnd(doc,"zEnd",zEndSite,ELayerRate.PORT.getValue()+"",maxFrameSize,flowDomainFragment);//zEnd
				this.crateElementNodeName(doc,"additionalInfo",pwId,flowDomainFragment);//additionalInfo
				
				flowDomainFragmentList.appendChild(flowDomainFragment);
			} catch (Exception e) {
				 ExceptionManage.dispose(e,this.getClass());
			}
		}
		return flowDomainFragmentList;
	}

private void crateElementNodeaEnd(Document doc, String rootName, String aEndSite,String rate, String maxFrameSize, Element flowDomainFragment) {
	Element root = doc.createElement(rootName);
  try {
	    Element node = doc.createElement("tpData");
	    
		Element nodeTpName = doc.createElement("tpName");
		Element tpNamenode = doc.createElement("node");
		this.createElementNode(doc, "name","EMS", tpNamenode);
		this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), tpNamenode);
		Element tpNamenodeOther = doc.createElement("node");
		this.createElementNode(doc, "name","ManagedElement", tpNamenodeOther);
		this.createElementNode(doc, "value",aEndSite, tpNamenodeOther);
		nodeTpName.appendChild(tpNamenode);
		nodeTpName.appendChild(tpNamenodeOther);
		
		Element nodeTransmissParams = doc.createElement("transmissionParams");
		Element nodeName = doc.createElement("LayeredParameterList_T");
		Element LayeredParameterList_TName = doc.createElement("LayeredParameters_T");
		this.createElementNode(doc,"layer",rate,LayeredParameterList_TName);
		Element layerTransmissionParamsName = doc.createElement("layerTransmissionParams");
		Element oneParamName = doc.createElement("oneParam");
		this.createElementNode(doc, "name","TrafficMappingTo_Table_Priority", oneParamName);
		this.createElementNode(doc, "value",maxFrameSize, oneParamName);
		layerTransmissionParamsName.appendChild(oneParamName);
		LayeredParameterList_TName.appendChild(layerTransmissionParamsName);
		nodeName.appendChild(LayeredParameterList_TName);
		nodeTransmissParams.appendChild(nodeName);
		
		node.appendChild(nodeTpName);
		node.appendChild(nodeTransmissParams);
		root.appendChild(node);
		flowDomainFragment.appendChild(root);
 } catch (Exception e) {
	 ExceptionManage.dispose(e,this.getClass());
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