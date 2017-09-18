package com.nms.snmp.ninteface.impl.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class SNCConvertXml {
	public static void main(String[] args) {
		ConstantUtil.serviceFactory = new ServiceFactory();
		SNCConvertXml ProtectionGroupConverXml = new SNCConvertXml();
		ProtectionGroupConverXml.getSNCXml();
	}
	public void getSNCXml(){
		 FileTools fileTool = new FileTools();
		 try {
			 Map<Integer,Object> allSubNetWorkConnData = this.getSNCMap();
//			 if(allSubNetWorkConnData.size() >0){
				 String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
				 String[] xmlPath = {"snmpData\\NRM", "CMCC-PTN-NRM-SNC-"+version+"-"+this.getTime()+".xml"};
				 String filePath = fileTool.createFile(xmlPath);
				 Document doc = fileTool.getDocument();
				 this.createXml(doc,allSubNetWorkConnData);
				 fileTool.putFile(doc, filePath);
				 fileTool.zipFile(filePath, filePath+".zip");
//			 }
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }
 	}
	
	private Map<Integer,Object> getSNCMap(){
		Map<Integer,Object> subNetWorkConnData = new HashMap<Integer,Object>();
		PwInfoService_MB pwService = null;
		TunnelService_MB tunnelService = null;
		CesInfoService_MB cesService = null;
		try {
			int rowIndex = 1;
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			List<PwInfo> pwList = pwService.selectAll();
			for (PwInfo pw : pwList) {
				subNetWorkConnData.put(rowIndex, pw);
				rowIndex++;
			}
			
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnelList = tunnelService.selectAllData();
			Tunnel tunnel = null;
			if(tunnelList != null && tunnelList.size() >0){
			  for(int i = 0; i < tunnelList.size(); i++){
				  tunnel = tunnelList.get(i);
				  subNetWorkConnData.put(rowIndex, tunnel);
				  rowIndex++;
			  }
			}
			cesService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			List<CesInfo> cesList = cesService.select();
			for (CesInfo ces : cesList) {
				subNetWorkConnData.put(rowIndex, ces);
				rowIndex++;
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(cesService);
			UiUtil.closeService_MB(tunnelService);
		}
		return subNetWorkConnData;
	}
	 
 	private String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
		return format.format(System.currentTimeMillis());
	}

	private void createXml(Document doc, Map<Integer, Object> allSubNetWorkConnData) {
		FileTools fileTool = new FileTools();
		try {
			doc.setXmlVersion("1.0");
			doc.setXmlStandalone(true);
			Element root =fileTool.rootElement(doc);
		    Element terminationPointList = this.crateFileContent(doc,allSubNetWorkConnData);
			root.appendChild(terminationPointList);
		    doc.appendChild(root);
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
	}

	private Element crateFileContent(Document doc,Map<Integer, Object> allSubNetWorkConnData) {
		Element subNetWorkList = doc.createElement("SubnetworkConnectionList_T");
		for(Integer indexId : allSubNetWorkConnData.keySet()){
			Element subData = doc.createElement("SubnetworkConnection_T");
			try {
				this.crateElementNodeName(doc,"name",allSubNetWorkConnData.get(indexId),subData);//Name
				this.crateElementNodeName(doc,"userLabel",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"nativeEMSName",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"direction",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"rate",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"sncState",allSubNetWorkConnData.get(indexId),subData);//激活
				this.crateElementNodeName(doc,"sncType",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"aEnd",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"zEnd",allSubNetWorkConnData.get(indexId),subData);
				this.crateElementNodeName(doc,"additionalInfo",allSubNetWorkConnData.get(indexId),subData);
				subNetWorkList.appendChild(subData);
			} catch (Exception e) {
			   ExceptionManage.dispose(e,this.getClass());
			}
		}
		return subNetWorkList;
	}

	private void crateElementNodeName(Document doc, String childName, Object object,Element subData) {
		Element d = doc.createElement(childName);
		if("name".equals(childName)){
			Element node = doc.createElement("node");
			this.createElementNode(doc, "name","EMS", node);
			this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), node);
			Element nodeOther = doc.createElement("node");
			this.createElementNode(doc, "name","SubnetworkConnection", nodeOther);
			if(object instanceof Tunnel){
				 this.createElementNode(doc, "value",((Tunnel)object).getTunnelId()+"", nodeOther);
			}else if(object instanceof PwInfo){
				 this.createElementNode(doc, "value",((PwInfo)object).getPwId()+"", nodeOther);
			}else{
				this.createElementNode(doc, "value", ((CesInfo)object).getId()+"", nodeOther);
			}
			d.appendChild(node);
			d.appendChild(nodeOther);
		}else if("userLabel".equals(childName)){
			d.setTextContent(userLable(object));
		}else if("nativeEMSName".equals(childName)){
			d.setTextContent(userLable(object));
		}else if("sncState".equals(childName)){     
			if(object instanceof Tunnel){                                  
				d.setTextContent(((Tunnel)object).getTunnelStatus() == 1 ?"ACTIVE":"UNACTIVE");
			}else if(object instanceof PwInfo){
				d.setTextContent(((PwInfo)object).getPwStatus() == 1?"ACTIVE":"UNACTIVE");
			}else{
				d.setTextContent(((CesInfo)object).getActiveStatus() == 1?"ACTIVE":"UNACTIVE");
			}
		}else if("direction".equals(childName)){
			//双向
			d.setTextContent("D_BIDIRECTIONAL");
		}else if("rate".equals(childName)){
			if(object instanceof Tunnel){
				d.setTextContent(ELayerRate.TUNNEL.getValue()+"");
			}else if(object instanceof PwInfo){
				d.setTextContent(ELayerRate.PW.getValue()+"");
			}else{
				d.setTextContent(ELayerRate.CES.getValue()+"");
			}
		}else if("sncType".equals(childName)){
			if(object instanceof Tunnel){
				d.setTextContent("tunnel");
			}else if(object instanceof PwInfo){
				d.setTextContent("pw");
			}else{
				d.setTextContent("ces");
			}
		}else if("aEnd".equals(childName)){
			d.appendChild(cretateAZNode(doc,object,1));
		}else if("zEnd".equals(childName) ){
			d.appendChild(cretateAZNode(doc,object,2));
			
		}else if("additionalInfo".equals(childName) ){
			Element oneParam1 = doc.createElement("oneParam"); 
			Element oneParam2 = doc.createElement("oneParam"); 
			Element oneParam3 = doc.createElement("oneParam"); 
			Element oneParam4 = doc.createElement("oneParam"); 
			Element oneParam5 = doc.createElement("oneParam"); 
			Element oneParam6 = doc.createElement("oneParam"); 
			Element oneParam7 = doc.createElement("oneParam"); 
			Element oneParam8 = doc.createElement("oneParam"); 
			Element oneParam9 = doc.createElement("oneParam"); 
			
			//如果不使用的类型就在相应的配置中不显示
			this.createElementNode(doc, "name","CreateTime", oneParam1);//创建时间 都必须要
			this.createElementNode(doc, "name","ServerConnections", oneParam2); //只TDM业务使用
			this.createElementNode(doc, "name","ServiceType", oneParam3);//只PW使用
			this.createElementNode(doc, "name","EmulationType", oneParam4);//只TDM业务使用
			this.createElementNode(doc, "name","E1ChannelList", oneParam5);//只TDM业务使用
			this.createElementNode(doc, "name","RTPEnable", oneParam6);//只TDM业务使用
			this.createElementNode(doc, "name","FrameNumber", oneParam7);//只TDM业务使用
			this.createElementNode(doc, "name","PDVT", oneParam8);//只TDM业务使用
			this.createElementNode(doc, "name","EcapsulateType", oneParam9);//只TDM业务使用
			
			if(object instanceof Tunnel){
				this.createElementNode(doc, "value",((Tunnel)object).getCreateTime(), oneParam1);
				d.appendChild(oneParam1);
			}else if(object instanceof PwInfo){
				this.createElementNode(doc, "value",((PwInfo)object).getCreateTime(), oneParam1);
				this.createElementNode(doc, "value", ((PwInfo)object).getType().toString(), oneParam3);
				d.appendChild(oneParam1);
				d.appendChild(oneParam3);
			}else{
				this.createElementNode(doc, "value",((CesInfo)object).getCreateTime(), oneParam1);
				this.createElementNode(doc, "value",((CesInfo)object).getPwId()+"", oneParam2);
				this.createElementNode(doc, "value","E1", oneParam4);//EmulationType
				E1Info e1Info = this.getE1Info((CesInfo)object);
				this.createElementNode(doc, "value", " ", oneParam5);//E1ChannelList
				this.createElementNode(doc, "value",e1Info == null? "" : (e1Info.getRtpEnable()==0?"Enabled":"DisEnabled"), oneParam6);//RTPEnable
				this.createElementNode(doc, "value",e1Info == null? "" : (e1Info.getPinCount()+""), oneParam7);//FrameNumber
				this.createElementNode(doc, "value",e1Info.getPrestoreTime()+"", oneParam8);//PDVT抖动缓存
				this.createElementNode(doc, "value",e1Info == null? "" : (e1Info.getFzType()==0?"SAToP":"CESoPSN"), oneParam9);//EcapsulateType
				d.appendChild(oneParam1);
				d.appendChild(oneParam2);
				d.appendChild(oneParam4);
				d.appendChild(oneParam5);
				d.appendChild(oneParam6);
				d.appendChild(oneParam7);
				d.appendChild(oneParam8);
				d.appendChild(oneParam9);
			}
		}
		subData.appendChild(d);
	 }
	 

	private E1Info getE1Info(CesInfo ces) {
		E1InfoService_MB e1Service = null;
		try {
			e1Service = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			int portId = 0;
			if(ces.getIsSingle() == 0){
				portId = ces.getaAcId();
			}else{
				if(ces.getaAcId() > 0){
					portId = ces.getaAcId();
				}else{
					portId = ces.getzAcId();
				}
			}
			E1Info e1 = new E1Info();
			e1.setPortId(portId);
			List<E1Info> e1List = e1Service.selectByCondition(e1);
			if(e1List.size() > 0){
				return e1List.get(0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(e1Service);
		}
		return null;
	}

	private String userLable(Object object){
		String uerLable = "";
		try {
			if(object instanceof Tunnel){
				uerLable = ((Tunnel)object).getTunnelName();
			}else if(object instanceof PwInfo){
				uerLable = ((PwInfo)object).getPwName();
			}else{
				uerLable = ((CesInfo)object).getName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return uerLable;
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
	 * 
	 * @param doc
	 * @param object
	 * @param label 用于标记是A端还是Z端 1：A 2：Z
	 * @return
	 */
	private Element cretateAZNode(Document doc,Object object,int label){
		TunnelService_MB tunnelService = null;
		Element node = doc.createElement("tpData");
		try {
			tunnelService = (TunnelService_MB)ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			Element nodeTpName = doc.createElement("tpName");
			Element nodeTransmissParams = doc.createElement("transmissionParams");
			Element nodeName = doc.createElement("node");
			this.createElementNode(doc, "name","EMS", nodeName);
			this.createElementNode(doc, "value",SnmpConfig.getInstanse().getsnmpEmsName(), nodeName);
			Element nodeOther = doc.createElement("node");
			Element nodePtp = doc.createElement("node");
			this.createElementNode(doc, "name","ManagedElement", nodeOther);
			this.createElementNode(doc, "name","PTP", nodePtp);
			Element transmissParamsNode = doc.createElement("LayeredParameterList_T");
			this.createElementNode(doc, "layer",ELayerRate.PORT.getValue()+"", transmissParamsNode);
			if(object instanceof Tunnel){
				if(label ==1){
					this.createElementNode(doc, "value",((Tunnel)object).getaSiteId()+"", nodeOther);
					this.createElementNode(doc, "value","port="+((Tunnel)object).getaPortId(), nodePtp);
				}else{
					this.createElementNode(doc, "value",((Tunnel)object).getzSiteId()+"", nodeOther);
					this.createElementNode(doc, "value","port="+((Tunnel)object).getzPortId(), nodePtp);
				}
			}else if(object instanceof PwInfo){
				if(label ==1){
					this.createElementNode(doc, "value",((PwInfo)object).getASiteId()+"", nodeOther);
					Tunnel tunnel = new Tunnel();
					tunnel.setTunnelId(((PwInfo)object).getTunnelId());
					String portNumber = " ";
					List<Tunnel> tunnelList = tunnelService.select(tunnel);
					if(tunnelList != null && tunnelList.size()>0){
						portNumber = tunnelList.get(0).getaPortId()+"";
					}
					this.createElementNode(doc, "value","port="+portNumber, nodePtp);
				}else{                                     
					this.createElementNode(doc, "value",((PwInfo)object).getZSiteId()+"", nodeOther);
					Tunnel tunnel = new Tunnel();
					tunnel.setTunnelId(((PwInfo)object).getTunnelId());
					String portNumber = " ";
					List<Tunnel> tunnelList = tunnelService.select(tunnel);
					if(tunnelList != null && tunnelList.size()>0){
						portNumber = tunnelList.get(0).getzPortId()+"";
					}
					
					this.createElementNode(doc, "value","port="+portNumber, nodePtp);
				}
			}else{
				if(label == 1){
					this.createElementNode(doc, "value", ((CesInfo)object).getaSiteId()+"", nodeOther);
					this.createElementNode(doc, "value","port="+((CesInfo)object).getaAcId(), nodePtp);
				}else{
					this.createElementNode(doc, "value", ((CesInfo)object).getzSiteId()+"", nodeOther);
					this.createElementNode(doc, "value","port="+((CesInfo)object).getzAcId(), nodePtp);
				}
			}
			nodeTransmissParams.appendChild(transmissParamsNode);
			nodeTpName.appendChild(nodeName);
			nodeTpName.appendChild(nodeOther);
			nodeTpName.appendChild(nodePtp);
			node.appendChild(nodeTpName);
			node.appendChild(nodeTransmissParams);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return node;
	}
}
