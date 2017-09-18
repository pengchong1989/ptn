package com.nms.snmp.ninteface.util;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class TransmissionParamsUtil {
	
	public Element setTransmissionParamsModel(PortInst portInfo,Document doc,PortLagInfo portLagInfo) {
		
		Element layerTransmissionParams_node  = null;
		try {
			layerTransmissionParams_node = doc.createElement("layerTransmissionParams");
			if(portInfo != null && ("UNI".equals(portInfo.getPortType())||"NNI".equals(portInfo.getPortType()))){
				//自协商
				Code portWorkModel = UiUtil.getCodeById(portInfo.getPortAttr().getWorkModel() == 0 ? 70 : portInfo.getPortAttr().getWorkModel());
				Element autoParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","AutoNegotiation", autoParam_node);
				this.createElementNode(doc, "value",portWorkModel.getCodeName().equals(ResourceUtil.srcStr(StringKeysLbl.LBLZIDONG_NAME))?"Enabled":"Disabled", autoParam_node);
				layerTransmissionParams_node.appendChild(autoParam_node);
				//双工模式
				Element DuplexModeParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","DuplexMode", DuplexModeParam_node);
				this.createElementNode(doc, "value",portInfo.getPortAttr().getWorkModel()>680?"Half":"FULL", DuplexModeParam_node);
				layerTransmissionParams_node.appendChild(DuplexModeParam_node);
				//端口速率
				Element speedrateParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","AdministrativeSpeedRate", speedrateParam_node);
				this.createElementNode(doc, "value",setPortRate(portInfo), speedrateParam_node);
				layerTransmissionParams_node.appendChild(speedrateParam_node);
				//最大帧长度
				Element oneParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","MaximumFrameSize", oneParam_node);
				this.createElementNode(doc, "value",portInfo.getPortAttr().getMaxFrameSize(), oneParam_node);
				layerTransmissionParams_node.appendChild(oneParam_node);
				//端口流控属性
				Element oneParam_FlowControl = doc.createElement("oneParam");
				this.createElementNode(doc, "name","FlowControl", oneParam_FlowControl);
				this.createElementNode(doc, "value",portInfo.getPortAttr().getFluidControl()== 28?"Enabled":"Disabled", oneParam_FlowControl);
				layerTransmissionParams_node.appendChild(oneParam_FlowControl);
				
				//端口MAC地址
				Element oneParam_PhysAddress = doc.createElement("oneParam");
				this.createElementNode(doc, "name","PhysAddress", oneParam_PhysAddress);
				this.createElementNode(doc, "value"," ",oneParam_PhysAddress);
				layerTransmissionParams_node.appendChild(oneParam_PhysAddress);
				//端口TAG属性  ??
				Element oneParam_TAG = doc.createElement("oneParam");
				this.createElementNode(doc, "name","VLANMode", oneParam_TAG);
				this.createElementNode(doc, "value"," ",oneParam_TAG);
				layerTransmissionParams_node.appendChild(oneParam_TAG);
				//VLAN ID ??
				Element oneParam_Valn = doc.createElement("oneParam");
				this.createElementNode(doc, "name","VID", oneParam_Valn);
				this.createElementNode(doc, "value"," ",oneParam_Valn);
				layerTransmissionParams_node.appendChild(oneParam_Valn);
				//VLAN优先级 ??
				Element oneParam_Priority= doc.createElement("oneParam");
				this.createElementNode(doc, "name","Priority", oneParam_Priority);
				this.createElementNode(doc, "value"," ",oneParam_Priority);
				layerTransmissionParams_node.appendChild(oneParam_Priority);
				}
			//TDM端口
			else if(portInfo != null && (portInfo.getPortType().contains("e1"))){
				E1InfoService_MB e1InfoService = null;
				List<E1Info> e1InfoList = null;
				E1Info e1Info = new E1Info();
				try {
					e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
					e1Info.setSiteId(ConstantUtil.siteId);
					e1Info.setPortName(portInfo.getPortName());
					e1InfoList = e1InfoService.selectByCondition(e1Info);
                   if(e1InfoList != null && e1InfoList.size()>0){
                	   e1Info = e1InfoList.get(0);
                	   //结构化类型 与非结构化   结构化（CESoPSN）、非结构化（SAToP）
                	   Element oneParam_Priority= doc.createElement("oneParam");
                	   this.createElementNode(doc, "name","StructuredType", oneParam_Priority);
                	   this.createElementNode(doc, "value",e1Info.getFzType()==0?"SAToP":"CESoPSN",oneParam_Priority);
                	   layerTransmissionParams_node.appendChild(oneParam_Priority);
                	   //E1端口成帧方式  不成帧，PCM30帧格式，PCM30CRC帧格式，PCM31帧格式，PCM31CRC帧格式
                	   Element oneParam_format = doc.createElement("oneParam");
                	   this.createElementNode(doc, "name","StructuredType", oneParam_format);
                	   this.createElementNode(doc, "value","PCM30",oneParam_format);
                	   layerTransmissionParams_node.appendChild(oneParam_format);
                	   //开销实际接收值，如J0、J1、J2开销字节等，最大64个字符
                	   Element oneParam_actualRx = doc.createElement("oneParam");
                	   this.createElementNode(doc, "name","TrailTraceActualRx", oneParam_actualRx);
                	   this.createElementNode(doc, "value","0",oneParam_actualRx);
                	   layerTransmissionParams_node.appendChild(oneParam_actualRx);
                	   //开销实际发送值，如J0、J1、J2开销字节等，最大64个字符
                	   Element oneParam_TraceactualRx = doc.createElement("oneParam");
                	   this.createElementNode(doc, "name","TrailTraceActualTx", oneParam_TraceactualRx);
                	   this.createElementNode(doc, "value","0",oneParam_TraceactualRx);
                	   layerTransmissionParams_node.appendChild(oneParam_TraceactualRx);
                	   //开销期望接收值
                	   Element oneParam_TraceExpectactualRx = doc.createElement("oneParam");
                	   this.createElementNode(doc, "name","TrailTraceExpectedRx", oneParam_TraceExpectactualRx);
                	   this.createElementNode(doc, "value","0",oneParam_TraceExpectactualRx);
                	   layerTransmissionParams_node.appendChild(oneParam_TraceExpectactualRx);
                   }
				} catch (Exception e) {
					throw e;
				} finally {
					UiUtil.closeService_MB(e1InfoService);
					e1InfoList = null;
				}
			}
			//LAG
			if(portLagInfo != null){
				//LAG聚合组类型 ：手工聚合、静态聚合  ??
				Element autoParam_node = doc.createElement("oneParam");
				this.createElementNode(doc, "name","LAGType", autoParam_node);
				if(((PortLagInfo)portLagInfo).getBroadcastBate() == 173){
					this.createElementNode(doc, "value","手工聚合", autoParam_node);
				}else{
					this.createElementNode(doc, "value","静态聚合", autoParam_node);
				}
				layerTransmissionParams_node.appendChild(autoParam_node);
				//LAG恢复模式 恢复、非恢复  ???
				Element autoParam_Mode = doc.createElement("oneParam");
				this.createElementNode(doc, "name","LAGReversionMode", autoParam_Mode);
				this.createElementNode(doc, "value","恢复", autoParam_Mode);
				layerTransmissionParams_node.appendChild(autoParam_Mode);
				//是否负载分担 负载分担、非负载分担  ??
				Element autoParam_Blance= doc.createElement("oneParam");
				this.createElementNode(doc, "name","ifLAGBlance", autoParam_Blance);
				if(((PortLagInfo)portLagInfo).getPortEnable() == 0){
					this.createElementNode(doc, "value","非负载分担", autoParam_Blance);
				}else{
					this.createElementNode(doc, "value","负载分担", autoParam_Blance);
				}
				layerTransmissionParams_node.appendChild(autoParam_Blance);
				//负载分担类型 自动、源MAC、宿MAC、源端口、宿端口等  ???
				Element autoParam_Type = doc.createElement("oneParam");
				this.createElementNode(doc, "name","LAGBlanceType", autoParam_Type);
				this.createElementNode(doc, "value"," ", autoParam_Type);
				layerTransmissionParams_node.appendChild(autoParam_Type);
				//LAG优先级
				Element autoParam_Priority = doc.createElement("oneParam");
				this.createElementNode(doc, "name","LAGPriority", autoParam_Priority);
				if(portLagInfo.getExportQueue() != null){
				  this.createElementNode(doc, "value",portLagInfo.getExportQueue().split(",")[0].split("-")[1], autoParam_Priority);
				}else{
				  this.createElementNode(doc, "value","1", autoParam_Priority);
				}
				layerTransmissionParams_node.appendChild(autoParam_Priority);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return layerTransmissionParams_node;
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
				return "0M";
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portWorkModel = null;
		}
		return "0M";
	}
}
