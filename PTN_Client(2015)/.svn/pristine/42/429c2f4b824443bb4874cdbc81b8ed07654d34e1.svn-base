/*
 * This source code is part of TWaver 4.1
 *
 * Serva Software PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright 2002 - 2011 Serva Software. All rights reserved.
 */

package com.nms.ui.ptn.ne.camporeData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.tree.LazyLoader;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CccInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;

public class CamporeInfoLoader implements LazyLoader {

	private TDataBox box = null;
	private boolean isDifferent;//是否只显示差异项
	private CamporeDataDialog camporeDataDialog;
	/**
	 * 
	 * @param box
	 * @param emsList 网管数据
	 * @param neLists 网元数据
	 */
	public CamporeInfoLoader(TDataBox box, Object emsList, Object neList, CamporeDataDialog camporeDataDialog) {
		this.camporeDataDialog = camporeDataDialog;
		isDifferent = false;
		this.box = box;
		camporeData(isDifferent,emsList,neList);
	}

	public void camporeData(boolean isEqual,Object emsList, Object neList){
		this.isDifferent = isEqual;
		Object busiObject = null;
			List emslist = (List) emsList;
			List nelist = (List)neList;
			if(emslist != null && emslist.size() > 0){
				busiObject = emslist.get(0);//在界面做判断，如果网管数据为空，不做数据比较
			}else if(nelist != null && nelist.size() > 0){
				busiObject = nelist.get(0);
			}
			if (busiObject instanceof PortInst) {//端口
				camporeDataDialog.setType(EServiceType.ETH.getValue());
				camporePortElement(emslist,nelist);
			}else if(busiObject instanceof ElineInfo){//eline业务
				this.camporeDataDialog.setType(EServiceType.ELINE.getValue());
				this.camporeServiceElement(emslist, nelist, 1);
			}else if(busiObject instanceof Tunnel){//tunnel
				camporeDataDialog.setType(EServiceType.TUNNEL.getValue());
				camporeTunnelElement(emslist,nelist);
			}else if(busiObject instanceof EtreeInfo){//etree
				this.camporeDataDialog.setType(EServiceType.ETREE.getValue());
				this.camporeServiceElement(emslist, nelist, 3);
			}else if(busiObject instanceof ElanInfo){//elan
				this.camporeDataDialog.setType(EServiceType.ELAN.getValue());
				this.camporeServiceElement(emslist, nelist, 2);
			}else if(busiObject instanceof CesInfo){//ces
				this.camporeDataDialog.setType(EServiceType.CES.getValue());
				this.camporeServiceElement(emslist, nelist, 0);
			}else if(busiObject instanceof PwInfo || busiObject instanceof MsPwInfo){//pw
				camporeDataDialog.setType(EServiceType.PW.getValue());
				camporePwElement(emslist,nelist);
			}else if(busiObject instanceof CccInfo){//ccc
				this.camporeDataDialog.setType(EServiceType.CCC.getValue());
				this.camporeServiceElement(emslist, nelist, 52);
			}
		}
	
	/**
	 * tunnel数据排序
	 * @param emsTunnels
	 * @param neTunnels
	 */
	private void camporeTunnelElement(List<Tunnel> emsTunnels,List<Tunnel> neTunnels){
		List<Integer> tunnelIds = new ArrayList<Integer>();//所有tunnel业务id集合
		List<Integer> protectIds = new ArrayList<Integer>();//所有保护id集合
		HashMap<Integer,Tunnel> emsProtectHashMap = new HashMap<Integer, Tunnel>();
		HashMap<Integer,Tunnel> neProtectHashMap = new HashMap<Integer, Tunnel>();
		HashMap<Integer,Tunnel> emsTunnelHashMap = this.getHashMapTunnnel(emsTunnels, tunnelIds,protectIds,emsProtectHashMap);
		HashMap<Integer,Tunnel> neTunnelHashMap = this.getHashMapTunnnel(neTunnels, tunnelIds,protectIds,neProtectHashMap);
		Collections.sort(tunnelIds);
		Collections.sort(protectIds);
		
		//TUNNEL列表
		for(Integer id : tunnelIds){
			createTunnelElement(emsTunnelHashMap.get(id), neTunnelHashMap.get(id),id);
		}
		
		//保护列表
		for(Integer integer : protectIds){
			createProtectTunnelElement(emsProtectHashMap.get(integer), neProtectHashMap.get(integer));
		}
	}
	
	/**
	 * pw数据排序
	 * @param emsTunnels
	 * @param neTunnels
	 */
	private void camporePwElement(List<Object> emspw,List<Object> nepw){
		List<Integer> pwInfoIds = new ArrayList<Integer>();//所有的pwinfo业务id集合
		List<Integer> msPwIds = new ArrayList<Integer>();//所有的多段pw业务集合
		HashMap<Integer, PwInfo> emsPwHashMap = new HashMap<Integer, PwInfo>();
		HashMap<Integer, PwInfo> nePwHashMap = new HashMap<Integer, PwInfo>();
		HashMap<Integer, PwInfo> emsMsPwHashMap = new HashMap<Integer, PwInfo>();
		HashMap<Integer, PwInfo> neMsPwHashMap = new HashMap<Integer, PwInfo>();
		PwInfo emsInfo = null;
		PwInfo neInfo = null;
		try {
			this.getHashMapPw(emspw, emsPwHashMap,emsMsPwHashMap,pwInfoIds,msPwIds);
			this.getHashMapPw(nepw, nePwHashMap,neMsPwHashMap,pwInfoIds,msPwIds);
			//普通的pw
			for(Integer id : pwInfoIds){
				try {
					if(emsPwHashMap != null){
						emsInfo = emsPwHashMap.get(id);
					}
					if(nePwHashMap != null){
						neInfo = nePwHashMap.get(id);
					}
					createPwElement(emsInfo, neInfo);
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}
			}
			/*********增加PW多段 一致性检查****************/
			for(Integer id : msPwIds){
				try {
					if(emsMsPwHashMap != null){
						emsInfo = emsMsPwHashMap.get(id);
					}
					if(neMsPwHashMap != null){
						neInfo = neMsPwHashMap.get(id);
					}
					createMspPwElement(emsInfo, neInfo);
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}
			}
			
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
	}
}
	 /**
	  * 创建多段PW
	  * @param pwInfo
	  * @param pwInfo2
	  */
		private void createMspPwElement(PwInfo emsPwInfo1, PwInfo nePwInfo1) {
			PwInfo emsPwInfo = null;
			PwInfo nePwInfo = null;
			MsPwInfo emsMspPwInof = null;
			MsPwInfo neMspPwInfo = null;
			int label =1;
			try {
				Node elements = new Node();
				box.addElement(elements);
				if(emsPwInfo1 == null &&  nePwInfo1 != null){
					emsPwInfo = nePwInfo1;
					nePwInfo = emsPwInfo1;
					label =2;
				}else{
					emsPwInfo = emsPwInfo1;
					nePwInfo = nePwInfo1;
				}
				if(emsPwInfo != null){
					emsMspPwInof = emsPwInfo.getMsPwInfos().get(0);
					if(nePwInfo != null)
					{
					 neMspPwInfo = nePwInfo.getMsPwInfos().get(0);
					}
					
					elements.setName(emsPwInfo.getPwName());
					//pw类型
					//super.getComboBoxDataUtil().comboBoxSelectByValue(pwTypeComboBox, this.pwInfo.getBusinessType());
					createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE),
							       nePwInfo == null ? null:UiUtil.getCodeByValue("BUSINESSTYPE",nePwInfo.getBusinessType()+"").getCodeName(),
							       UiUtil.getCodeByValue("BUSINESSTYPE",emsPwInfo.getBusinessType()+"").getCodeName(),
							       elements,label);
					//前向LSP
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_FRONTLSP),
							        neMspPwInfo == null ? null:getTunnelName(neMspPwInfo.getFrontTunnelId()),
							        getTunnelName(emsMspPwInof.getFrontTunnelId()),
									elements,label);
					//前向入标签
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_FRONTINLSP),
							       neMspPwInfo == null ? null:neMspPwInfo.getFrontInlabel()+"",
							       emsMspPwInof.getFrontInlabel()+"",
							       elements,label);
					//前向出标签
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_FRONTBLACKLSP),
							       neMspPwInfo == null ? null:neMspPwInfo.getFrontOutlabel()+"",
							       emsMspPwInof.getFrontOutlabel()+"",
							       elements,label);
					//后项LSP
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_BALCKLSP),
							        neMspPwInfo == null ? null:getTunnelName(neMspPwInfo.getBackTunnelId()),
							        getTunnelName(emsMspPwInof.getBackTunnelId()),
									elements,label);
					//后向入标签
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_FRONTINLSP),
							       neMspPwInfo == null ? null:neMspPwInfo.getBackInlabel()+"",
							       emsMspPwInof.getBackInlabel()+"",
							       elements,label);
					//后向出标签
					createchildNode(ResourceUtil.srcStr(StringKeysTip.SING1_MULTE_FRONTBLACKLSP),
							       neMspPwInfo == null ? null:neMspPwInfo.getBackOutlabel()+"",
							       emsMspPwInof.getBackOutlabel()+"",
							       elements,label);
					//MIP
					createchildNode("MIP",
							       neMspPwInfo == null ? null:neMspPwInfo.getMipId()+"",
							       emsMspPwInof.getMipId()+"",
							       elements,label);
				}
				
			} catch (Exception e) {
				ExceptionManage.dispose(e, getClass());
			}
		}
		
		
	private String getTunnelName(int tunnelId)
	{
		TunnelService_MB tunnelServiceMB = null;
		Tunnel tunnel = null;
		try {
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel = tunnelServiceMB.selectId(tunnelId);
			if(tunnel != null)
			{
				return tunnel.getTunnelName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(tunnelServiceMB);
			tunnel = null;
		}
		return "";
	}
 /**
  * 创建PW
  * @param pwInfo
  * @param pwInfo2
  */
	private void createPwElement(PwInfo emsPwInfo1, PwInfo nePwInfo1) {
		PwInfo emsPwInfo = null;
		PwInfo nePwInfo = null;
		int label =1;
		try {
			Node elements = new Node();
			box.addElement(elements);
			if(emsPwInfo1 == null &&  nePwInfo1 != null){
				emsPwInfo = nePwInfo1;
				nePwInfo = emsPwInfo1;
				label =2;
			}else{
				emsPwInfo = emsPwInfo1;
				nePwInfo = nePwInfo1;
			}
			if(emsPwInfo != null){
				elements.setName(emsPwInfo.getPwName());
				//pw类型
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE),
						       nePwInfo == null ? null:UiUtil.getCodeByValue("PWTYPESITE",nePwInfo.getType().getValue()+"").getCodeName(),
						       UiUtil.getCodeByValue("PWTYPESITE",emsPwInfo.getType().getValue()+"").getCodeName(),
						       elements,label);
				//入标签
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_INLABEL),
						        nePwInfo == null ? null:nePwInfo.getInlabelValue()+"",
								emsPwInfo.getInlabelValue()+"",
								elements,label);
				//出标签
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTLABEL),
				               nePwInfo == null ? null:nePwInfo.getOutlabelValue()+"",
						       emsPwInfo.getOutlabelValue()+"",
						       elements,label);
				//角色
				String role ="";
				if(nePwInfo != null){
					if(nePwInfo.getRole() == 0){
						role = "A";
					}else{
						role = "Z";
					}
				}
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ROLE),
						    role,
						    emsPwInfo.getASiteId() == ConstantUtil.siteId?"A":"Z",
						    elements,label);
				//模式
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL),
						    nePwInfo == null ? null:UiUtil.getCodeByValue("MODEL",nePwInfo.getQosModel()+"").getCodeName(),
						    UiUtil.getCodeByValue("MODEL",emsPwInfo.getQosModel()+"").getCodeName(),
						    elements,label);
				//CIR     
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_CIR),
						 nePwInfo == null ? null:nePwInfo.getQosList().get(0).getCir()+"",
						 emsPwInfo.getQosList().get(0).getCir()+"",
						 elements,label);
				//PIR
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PIR),
						 nePwInfo == null ? null:nePwInfo.getQosList().get(0).getPir()+"",
						 emsPwInfo.getQosList().get(0).getPir()+"",
						 elements,label);
				//CM 没有
				//CBS
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS),
						 nePwInfo == null ? null:nePwInfo.getQosList().get(0).getCbs()+"",
						 emsPwInfo.getQosList().get(0).getCbs() == -1?"0":emsPwInfo.getQosList().get(0).getCbs()+"",
						 elements,label);
				//PBS
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PBS),
						 nePwInfo == null ? null:nePwInfo.getQosList().get(0).getPbs()+"",
						 emsPwInfo.getQosList().get(0).getPbs()+"",
						 elements,label);
				
				//PHB到tmc/tmp EXP映射
				Node pwPHBElement = new Node();
				pwPHBElement.setName(ResourceUtil.srcStr(StringKeysBtn.BTN_PHB_TMCTMP_EXP));
				pwPHBElement.setParent(elements);
				box.addElement(pwPHBElement);
				//策略
				createchildNode(ResourceUtil.srcStr(StringKeysBtn.BTN_STRATEGY_),
						         nePwInfo == null ? null:UiUtil.getCodeByValue("Policy_PHB",nePwInfo.getExpStrategy()+"").getCodeName(),
								 UiUtil.getCodeByValue("Policy_PHB",emsPwInfo.getExpStrategy()+"").getCodeName(),
						         pwPHBElement,label);
				//指配EXP
				createchildNode(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_EXP),
						         nePwInfo == null ? null:UiUtil.getCodeByValue("TC",nePwInfo.getExpAssignment()+"").getCodeName(),
								 UiUtil.getCodeByValue("TC",emsPwInfo.getExpAssignment()+"").getCodeName(),
						         pwPHBElement,label);
				//PHB2EXP_ID
				createchildNode("PHB2EXP_ID",
						        nePwInfo == null ? null:UiUtil.getCodeByValue("PHBAndEXPId",nePwInfo.getPhbToExpId()+"").getCodeName(),
								UiUtil.getCodeByValue("PHBAndEXPId",emsPwInfo.getPhbToExpId()+"").getCodeName(),
						       pwPHBElement,label);
				//TMP EXP到PHB映射
				Node pwTMPElement = new Node();
				pwTMPElement.setName(ResourceUtil.srcStr(StringKeysBtn.BTN_TMC_EXP_PHB));
				pwTMPElement.setParent(elements);
				box.addElement(pwTMPElement);
				//策略
				createchildNode(ResourceUtil.srcStr(StringKeysBtn.BTN_STRATEGY_),
						        nePwInfo == null ? null:UiUtil.getCodeByValue("Policy_EXP",nePwInfo.getPhbStrategy()+"").getCodeName(),
								 UiUtil.getCodeByValue("Policy_EXP",emsPwInfo.getPhbStrategy()+"").getCodeName(),
								 pwTMPElement,label);
				//指配EXP
				createchildNode(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_EXP),
						 nePwInfo == null ? null:UiUtil.getCodeByValue("CONRIRMPHB",nePwInfo.getPhbAssignment()+"").getCodeName(),
								 UiUtil.getCodeByValue("CONRIRMPHB",emsPwInfo.getPhbAssignment()+"").getCodeName(),
								 pwTMPElement,label);
				//EXP2PHB_ID
				createchildNode("EXP2PHB_ID",
						         nePwInfo == null ? null:UiUtil.getCodeByValue("PHBAndEXPId",nePwInfo.getExpTophbId()+"").getCodeName(),
								 UiUtil.getCodeByValue("PHBAndEXPId",emsPwInfo.getExpTophbId()+"").getCodeName(),
								 pwTMPElement,label);

				//-------------------------Oam信息
				String cvNeenable = "";
				String cvEmsenable = "";
				String neCycel = "";
				String emsCycel = "";
				String neMegIcc = "";
				String emsMegIcc = "";
				String neMegumc = "";
				String emsMegumc = "";
				String melNe = "";
				String melEms = "";
				String LocalMepIdNe = "";
				String LocalMepIdEms = "";
				String tcEnabelNe = "";
				String tcEnabelEms = "";
				String PWTCEnabelNe = "";
				String PWTCEnabelEms = "";
				if(emsPwInfo.getOamList() != null && emsPwInfo.getOamList().size()>0){
					if(nePwInfo!= null && nePwInfo.getOamList()!= null && nePwInfo.getOamList().size()>0 ){
						cvNeenable = nePwInfo.getOamList().get(0).getOamMep().isCv()?ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED):ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO);
					}
					cvEmsenable = emsPwInfo.getOamList().get(0).getOamMep().isCv()?ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED):ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO);
					
					if(nePwInfo != null && nePwInfo.getOamList()!=null&& emsPwInfo.getOamList().size()>0){
						if(emsPwInfo.getOamList().get(0).getOamMep().getCvCycle() >0){
							neCycel = UiUtil.getCodeById(emsPwInfo.getOamList().get(0).getOamMep().getCvCycle()).getCodeName();
						}
					}
					if(emsPwInfo.getOamList().get(0).getOamMep().getCvCycle() > 0){
						emsCycel = UiUtil.getCodeById(emsPwInfo.getOamList().get(0).getOamMep().getCvCycle()).getCodeName();
					}
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						neMegIcc = nePwInfo.getOamList().get(0).getOamMep().getMegIcc();
					}
					emsMegIcc = emsPwInfo.getOamList().get(0).getOamMep().getMegIcc();
					
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						neMegumc = nePwInfo.getOamList().get(0).getOamMep().getMegUmc();
					}
					emsMegumc = emsPwInfo.getOamList().get(0).getOamMep().getMegUmc();
					//MEL 
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						melNe = nePwInfo.getOamList().get(0).getOamMep().getMel()+"";
					}
					melEms = emsPwInfo.getOamList().get(0).getOamMep().getMel()+"";
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						LocalMepIdNe = nePwInfo.getOamList().get(0).getOamMep().getLocalMepId()+"";
					}
					LocalMepIdEms = emsPwInfo.getOamList().get(0).getOamMep().getLocalMepId()+"";
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						tcEnabelNe = UiUtil.getCodeById(nePwInfo.getOamList().get(0).getOamMep().getLspTc()).getCodeName();
					}
					tcEnabelEms = UiUtil.getCodeById(emsPwInfo.getOamList().get(0).getOamMep().getLspTc()).getCodeName()+"";
					if(nePwInfo != null && nePwInfo.getOamList()!= null){
						PWTCEnabelNe = UiUtil.getCodeById(nePwInfo.getOamList().get(0).getOamMep().getPwTc()).getCodeName();
					}
					PWTCEnabelEms = UiUtil.getCodeById(emsPwInfo.getOamList().get(0).getOamMep().getPwTc()).getCodeName()+"";
				}
				//cv使能
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST),cvNeenable,cvEmsenable, elements,label);
					//CV帧周期  
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST_PERIOD),neCycel,emsCycel,elements,label);
				 //OAM micc   ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_ICC)
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_ICC),neMegIcc,emsMegIcc,elements,label);
				//MEG UMC
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_UMC),neMegumc,emsMegumc,elements,label);
				//MEG MEL
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_LEVLE),melNe,melEms,elements,label);
				//维护点ID
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_A_ID),LocalMepIdNe,LocalMepIdEms+"",elements,label);
				//远端维护点ID
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_Z_ID),LocalMepIdNe,LocalMepIdEms,elements,label);
				//tc使能 
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_TC), tcEnabelNe,tcEnabelEms,elements,label);
				//PWTC使能
				createchildNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PWTC),PWTCEnabelNe,PWTCEnabelEms,elements,label); 
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	/**
	 * 
	 * @param labelName 字节点的名称
	 * @param neValue 设备的值
	 * @param emsValue 数据库的值
	 * @param parent 父类节点
	 * @param label 用于判断设备和网管的值
	 */
	private void createchildNode(String labelName,String neValue,String emsValue,Node parent,int label){
		Node nodeEnable = new Node();
		CamporeData camporeInfoType = new CamporeData();
		if(label == 1 ){
			camporeInfoType.setNE(neValue);
			setNode(labelName,camporeInfoType, nodeEnable,emsValue, parent);
		}else{
			camporeInfoType.setNE(emsValue);
			setNode(labelName,camporeInfoType, nodeEnable,neValue, parent);
		}
		if(isDifferent){
			if(neValue!= null && !neValue.equalsIgnoreCase(emsValue)){
				box.addElement(nodeEnable);
			}else if(emsValue != null && !emsValue.equalsIgnoreCase(neValue)){
				box.addElement(nodeEnable);
			}
		}else{
			box.addElement(nodeEnable);
		}
	}
	
	private void createProtectTunnelElement(Tunnel tunnelEMS,Tunnel tunnelNE){
		Node node = new Node();
		node.setName("TNP");
		
		Node mainNode = new Node();
		CamporeData mainCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_PATH),mainCamporeData, mainNode, "", node);
		
		Node standNode = new Node();
		CamporeData standCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PROTECT_PATH),standCamporeData, standNode, "", node);
		
		Node waitTimeNode = new Node();
		CamporeData waitTimeCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME),waitTimeCamporeData, waitTimeNode, "", node);
		
		Node backTypeNode = new Node();
		CamporeData backTypeCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_BACK),backTypeCamporeData, backTypeNode, "", node);
		
		Node delayNode = new Node();
		CamporeData delayCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAY_TIME),delayCamporeData, delayNode, "", node);
		
		if(tunnelEMS != null){
			mainCamporeData.setEMS(tunnelEMS.getTunnelName());
			standCamporeData.setEMS(tunnelEMS.getProtectTunnel().getTunnelName());
			waitTimeCamporeData.setEMS(tunnelEMS.getWaittime()+"");
			backTypeCamporeData.setEMS(tunnelEMS.getProtectType()==1?"NO":"YES");
			delayCamporeData.setEMS(tunnelEMS.getDelaytime()+"");
		} 
		
		if(tunnelNE != null){
			mainCamporeData.setNE(tunnelNE.getTunnelName());
			standCamporeData.setNE(tunnelNE.getProtectTunnel().getTunnelName());
			waitTimeCamporeData.setNE(tunnelNE.getWaittime()+"");
			backTypeCamporeData.setNE(tunnelNE.getProtectType()==1?"NO":"YES");
			delayCamporeData.setNE(tunnelNE.getDelaytime()+"");	
		}
		
		if(isDifferent){
			if(!mainCamporeData.getEMS().equals(mainCamporeData.getNE())){
				box.addElement(mainNode);
			}
			if(!standCamporeData.getEMS().equals(standCamporeData.getNE())){
				box.addElement(standNode);
			}
			if(!waitTimeCamporeData.getEMS().equals(waitTimeCamporeData.getNE())){
				box.addElement(waitTimeNode);
			}
			if(!backTypeCamporeData.getEMS().equals(backTypeCamporeData.getNE())){
				box.addElement(backTypeNode);
			}
			if(!delayCamporeData.getEMS().equals(delayCamporeData.getNE())){
				box.addElement(delayNode);
			}
		}else{
			box.addElement(mainNode);
			box.addElement(standNode);
			box.addElement(waitTimeNode);
			box.addElement(backTypeNode);
			box.addElement(delayNode);
		}
		box.addElement(node);
	}
	
	
	/**
	 * 解析成相应的集合和ID
	 * @param pw
	 * @param emsPwHashMap
	 * @param emsMsPwHashMap
	 * @param pwInfoIds
	 * @param msPwIds
	 */
	private void getHashMapPw(List<Object> pwObject,HashMap<Integer, PwInfo> emsPwHashMap,
			              HashMap<Integer, PwInfo> emsMsPwHashMap, List<Integer> pwInfoIds,List<Integer> msPwIds) {
		 try {
			 if(pwObject != null && pwObject.size() >0){
				 try {
					 for(Object object : pwObject){
						 if(object instanceof PwInfo){
							 if(((PwInfo)object).getASiteId() == ConstantUtil.siteId){
								 setPwHashMap(emsPwHashMap,pwInfoIds,((PwInfo)object).getApwServiceId(),((PwInfo)object));
							 }else if(((PwInfo)object).getZSiteId() == ConstantUtil.siteId){
								 setPwHashMap(emsPwHashMap,pwInfoIds,((PwInfo)object).getZpwServiceId(),((PwInfo)object));
							 }else if(((PwInfo)object).getMsPwInfos()!= null &&!((PwInfo)object).getMsPwInfos().isEmpty())
							 {
								 setMspPwHashMap(emsMsPwHashMap,msPwIds,((PwInfo)object),((PwInfo)object).getMsPwInfos().get(0).getFrontInlabel());
							 }
						 }
					 }
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}
			 }
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}

	private void setMspPwHashMap(HashMap<Integer, PwInfo> emsMsPwHashMap,List<Integer> msPwIds, PwInfo pwInfo,int frontInLabel) 
	{
		emsMsPwHashMap.put(frontInLabel, pwInfo);
		if(!msPwIds.contains(frontInLabel))
		{
			msPwIds.add(frontInLabel);
		}
	}

	/**
	 * 将普通pw放入指定的集合中
	 * @param emsPwHashMap
	 * @param pwInfoIds
	 * @param pwId
	 * @param pwInfo
	 */
	private void setPwHashMap(HashMap<Integer, PwInfo> emsPwHashMap,List<Integer> pwInfoIds, int pwId, PwInfo pwInfo) {
		emsPwHashMap.put(pwId, pwInfo);
		if(!pwInfoIds.contains(pwId)){
			pwInfoIds.add(pwId);
		}
	}
	
	/**
	 * 添加tunnel属性节点
	 * @param tunnelEMS
	 * @param tunnelNE
	 */
	private void createTunnelElement(Tunnel tunnelEMS,Tunnel tunnelNE,int id){
		Node node = new Node();
		node.setName("TUNNEL");
		Node roleNode = new Node();
		CamporeData roleCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ROLE),roleCamporeData, roleNode, "", node);
		Node idNode = new Node();
		CamporeData idCamporeData = new CamporeData();
		setNode("TUNNEL ID",idCamporeData, idNode, "", node);
		
		Node inControleNode = new Node();
		CamporeData inControleCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_INBANDWIDTH),inControleCamporeData, inControleNode, "", node);
		
		Node outControleNode = new Node();
		CamporeData outControleCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTBANDWIDTH),outControleCamporeData, outControleNode, "", node);
		
		Node vlanNode = new Node();
		vlanNode.setName(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN));
		vlanNode.setParent(node);
		box.addElement(vlanNode);
		
		Node invlanNode = new Node();
		invlanNode.setName(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD)+ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN));
		invlanNode.setParent(vlanNode);
		box.addElement(invlanNode);
		
		Node inVlanEnableNode = new Node();
		CamporeData inVlanEnableCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ENABLE),inVlanEnableCamporeData, inVlanEnableNode, "", invlanNode);
		
		Node inVlanValueNode = new Node();
		CamporeData inVlanValueCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN_VALUE),inVlanValueCamporeData, inVlanValueNode, "", invlanNode);
		
		Node inTP_IPNode = new Node();
		CamporeData inTP_IPCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.TP_ID),inTP_IPCamporeData, inTP_IPNode, "", invlanNode);
		
		Node outvlanNode = new Node();
		outvlanNode.setName(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD)+ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN));
		outvlanNode.setParent(vlanNode);
		box.addElement(outvlanNode);
		
		
		Node outVlanEnableNode = new Node();
		CamporeData outVlanEnableCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ENABLE),outVlanEnableCamporeData, outVlanEnableNode, "", outvlanNode);
		
		Node outVlanValueNode = new Node();
		CamporeData outVlanValueCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN_VALUE),outVlanValueCamporeData, outVlanValueNode, "", outvlanNode);
		
		Node outTP_IPNode = new Node();
		CamporeData outTP_IPCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.TP_ID),outTP_IPCamporeData, outTP_IPNode, "", outvlanNode);
		
		Node leftNode = new Node();
		leftNode.setName(ResourceUtil.srcStr(StringKeysPanel.PANEL_LEFT_LSP));
		leftNode.setParent(node);
		box.addElement(leftNode);
		
		Node leftPortNode = new Node();
		CamporeData leftPortCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT),leftPortCamporeData, leftPortNode, "", leftNode);
		
		Node leftInLabelNode = new Node();
		CamporeData leftInLabelCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_INLABEL),leftInLabelCamporeData, leftInLabelNode, "", leftNode);
		
		Node leftOutLabelNode = new Node();
		CamporeData leftOutLabelCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTLABEL),leftOutLabelCamporeData, leftOutLabelNode, "", leftNode);
		
		Node leftSourceNode = new Node();
		CamporeData leftSourceCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC),leftSourceCamporeData, leftSourceNode, "", leftNode);
		
		Node leftTargetNode = new Node();
		CamporeData leftTargetCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC),leftTargetCamporeData, leftTargetNode, "", leftNode);
		
		
		Node rightNode = new Node();
		rightNode.setName(ResourceUtil.srcStr(StringKeysPanel.PANEL_RIGHT_LSP));
		rightNode.setParent(node);
		box.addElement(rightNode);
		
		Node rightPortNode = new Node();
		CamporeData rightPortCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT),rightPortCamporeData, rightPortNode, "", rightNode);
		
		Node rightInLabelNode = new Node();
		CamporeData rightInLabelCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_INLABEL),rightInLabelCamporeData, rightInLabelNode, "", rightNode);
		
		Node rightOutLabelNode = new Node();
		CamporeData rightOutLabelCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTLABEL),rightOutLabelCamporeData, rightOutLabelNode, "", rightNode);
		
		Node rightSourceNode = new Node();
		CamporeData rightSourceCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC),rightSourceCamporeData, rightSourceNode, "", rightNode);
		
		Node rightTargetNode = new Node();
		CamporeData rightTargetCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC),rightTargetCamporeData, rightTargetNode, "", rightNode);
		
		Node qosNode = new Node();
		qosNode.setName("QoS");
		qosNode.setParent(node);
		box.addElement(qosNode);
		
		Node inCosNode = new Node();
		CamporeData inCosCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD)+"COS",inCosCamporeData, inCosNode, "", qosNode);
		
		Node inCirNode = new Node();
		CamporeData inCirCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD)+"CIR",inCirCamporeData, inCirNode, "", qosNode);
		
		Node inPirNode = new Node();
		CamporeData inPirCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD)+"PIR",inPirCamporeData, inPirNode, "", qosNode);
		
		Node outCosNode = new Node();
		CamporeData outCosCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD)+"COS",outCosCamporeData, outCosNode, "", qosNode);
		
		Node outCirNode = new Node();
		CamporeData outCirCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD)+"CIR",outCirCamporeData, outCirNode, "", qosNode);
		
		Node outPirNode = new Node();
		CamporeData outPirCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD)+"PIR",outPirCamporeData, outPirNode, "", qosNode);
		
		Node oamNode = new Node();
		oamNode.setName("OAM");
		oamNode.setParent(node);
		box.addElement(oamNode);
		
		Node megNode = new Node();
		CamporeData megCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_LEVLE),megCamporeData, megNode, "", oamNode);
		
		Node cvNode = new Node();
		CamporeData cvCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST),cvCamporeData, cvNode, "", oamNode);
		
		Node cycleNode = new Node();
		CamporeData cycleCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST_PERIOD),cycleCamporeData, cycleNode, "", oamNode);
		
		Node megiccNode = new Node();
		CamporeData megiccCamporeData = new CamporeData();
		setNode("MEG ICC",megiccCamporeData, megiccNode, "", oamNode);
		
		Node megumcNode = new Node();
		CamporeData megumcCamporeData = new CamporeData();
		setNode("MEG UMC",megumcCamporeData, megumcNode, "", oamNode);
		
		Node nearIDNode = new Node();
		CamporeData nearIDCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEPID),nearIDCamporeData, nearIDNode, "", oamNode);
		
		Node farIDNode = new Node();
		CamporeData farIDCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_FAR_POINT_ID),farIDCamporeData, farIDNode, "", oamNode);
		
		
		Node apsNode = new Node();
		CamporeData apsCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_APS),apsCamporeData, apsNode, "", oamNode);
		
		Node lmNode = new Node();
		CamporeData lmCamporeData = new CamporeData();
		setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LM),lmCamporeData, lmNode, "", oamNode);
		
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(tunnelNE != null){//网管存在，设备不存在的数据
				node.setName("tunnel_"+id);
				idCamporeData.setNE(id+"");
				inControleCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelNE.getInBandwidthControl()+"").getCodeName());
				outControleCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelNE.getOutBandwidthControl()+"").getCodeName());
				inVlanEnableCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelNE.getaVlanEnable()+"").getCodeName());
				inVlanValueCamporeData.setNE(tunnelNE.getaOutVlanValue()+"");
				inTP_IPCamporeData.setNE(UiUtil.getCodeByValue("LAGVLANTPID",tunnelNE.getzTp_id()+"").getCodeName());
				outVlanEnableCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelNE.getzVlanEnable()+"").getCodeName());
				outVlanValueCamporeData.setNE(tunnelNE.getaOutVlanValue()+"");
				outTP_IPCamporeData.setNE(UiUtil.getCodeByValue("LAGVLANTPID",tunnelNE.getzTp_id()+"").getCodeName());
				inCosCamporeData.setNE(UiUtil.getCodeByValue("CONRIRMPHB", tunnelNE.getQosList().get(0).getCos()+"").getCodeName());
				inCirCamporeData.setNE(tunnelNE.getQosList().get(0).getCir()+"");
				inPirCamporeData.setNE(tunnelNE.getQosList().get(0).getPir()+"");
				outCosCamporeData.setNE(UiUtil.getCodeByValue("CONRIRMPHB", tunnelNE.getQosList().get(1).getCos()+"").getCodeName());
				outCirCamporeData.setNE(tunnelNE.getQosList().get(1).getCir()+"");
				outPirCamporeData.setNE(tunnelNE.getQosList().get(1).getPir()+"");
				
				for(OamInfo oamInfo : tunnelNE.getOamList()){
					if(oamInfo.getOamMep() != null){
						if(oamInfo.getOamMep().getSiteId() == ConstantUtil.siteId){
							megCamporeData.setNE(oamInfo.getOamMep().getMegId()+"");
							cvCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isCv()?"1":"0").getCodeName());
							cycleCamporeData.setNE(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeName());
							megiccCamporeData.setNE(oamInfo.getOamMep().getMegIcc().trim());
							megumcCamporeData.setNE(oamInfo.getOamMep().getMegUmc().trim());
							nearIDCamporeData.setNE(oamInfo.getOamMep().getLocalMepId()+"");
							farIDCamporeData.setNE(oamInfo.getOamMep().getRemoteMepId()+"");
							apsCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isAps()?"1":"0").getCodeName());
							lmCamporeData.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isLm()?"1":"0").getCodeName());
							break;
						}
					}else if(oamInfo.getOamMip() != null){
						if(oamInfo.getOamMip().getSiteId() == ConstantUtil.siteId){
							megCamporeData.setNE(oamInfo.getOamMip().getMegId()+"");
							nearIDCamporeData.setNE(oamInfo.getOamMip().getMipId()+"");
							break;
						}
					}
				}
				
				leftInLabelCamporeData.setNE(tunnelNE.getLspParticularList().get(0).getFrontLabelValue()+"");
				leftOutLabelCamporeData.setNE(tunnelNE.getLspParticularList().get(0).getBackLabelValue()+"");
				leftSourceCamporeData.setNE(tunnelNE.getSourceMac());
				leftTargetCamporeData.setNE(tunnelNE.getEndMac());
				PortInst portinst = new PortInst();
				PortInst inst = new PortInst();
				if(tunnelNE.getaSiteId() == ConstantUtil.siteId){
					roleCamporeData.setNE("ingress");
					portinst = portService.selectPortybyid(tunnelNE.getLspParticularList().get(0).getAPortId());
					leftPortCamporeData.setNE(portinst.getPortName());
					
				}else if(tunnelNE.getzSiteId() == ConstantUtil.siteId){
					roleCamporeData.setNE("egress");
					portinst = portService.selectPortybyid(tunnelNE.getLspParticularList().get(0).getZPortId());
					leftPortCamporeData.setNE(portinst.getPortName());
				}else{
					roleCamporeData.setNE("xc");
					portinst = portService.selectPortybyid(tunnelNE.getLspParticularList().get(0).getZPortId());
					inst = portService.selectPortybyid(tunnelNE.getLspParticularList().get(1).getAPortId());
					leftPortCamporeData.setNE(portinst.getPortName());
					rightPortCamporeData.setNE(inst.getPortName());
					rightInLabelCamporeData.setNE(tunnelNE.getLspParticularList().get(1).getFrontLabelValue()+"");
					rightOutLabelCamporeData.setNE(tunnelNE.getLspParticularList().get(1).getBackLabelValue()+"");
					rightSourceCamporeData.setNE(tunnelNE.getSourceMac());
					rightTargetCamporeData.setNE(tunnelNE.getEndMac());
				}
				
			}	
			if(tunnelEMS != null){//设备存在，网管不存在的数据
				node.setName(tunnelEMS.getTunnelName());
				idCamporeData.setEMS(id+"");
				inControleCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelEMS.getInBandwidthControl()+"").getCodeName());
				outControleCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelEMS.getOutBandwidthControl()+"").getCodeName());
				inVlanEnableCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelEMS.getaVlanEnable()+"").getCodeName());
				inVlanValueCamporeData.setEMS(tunnelEMS.getaOutVlanValue()+"");
				inTP_IPCamporeData.setEMS(UiUtil.getCodeByValue("LAGVLANTPID",tunnelEMS.getzTp_id()+"").getCodeName());
				outVlanEnableCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",tunnelEMS.getzVlanEnable()+"").getCodeName());
				outVlanValueCamporeData.setEMS(tunnelEMS.getaOutVlanValue()+"");
				outTP_IPCamporeData.setEMS(UiUtil.getCodeByValue("LAGVLANTPID",tunnelEMS.getzTp_id()+"").getCodeName());
				inCosCamporeData.setEMS(UiUtil.getCodeByValue("CONRIRMPHB", tunnelEMS.getQosList().get(0).getCos()+"").getCodeName());
				inCirCamporeData.setEMS(tunnelEMS.getQosList().get(0).getCir()+"");
				inPirCamporeData.setEMS(tunnelEMS.getQosList().get(0).getPir()+"");
				outCosCamporeData.setEMS(UiUtil.getCodeByValue("CONRIRMPHB", tunnelEMS.getQosList().get(1).getCos()+"").getCodeName());
				outCirCamporeData.setEMS(tunnelEMS.getQosList().get(1).getCir()+"");
				outPirCamporeData.setEMS(tunnelEMS.getQosList().get(1).getPir()+"");
				
				for(OamInfo oamInfo : tunnelEMS.getOamList()){
					if(oamInfo.getOamMep() != null){
						if(oamInfo.getOamMep().getSiteId() == ConstantUtil.siteId){
							megCamporeData.setEMS(oamInfo.getOamMep().getMegId()+"");
							cvCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isCv()?"1":"0").getCodeName());
							cycleCamporeData.setEMS(UiUtil.getCodeById(oamInfo.getOamMep().getCvCycle()).getCodeName());
							megiccCamporeData.setEMS(oamInfo.getOamMep().getMegIcc().trim());
							megumcCamporeData.setEMS(oamInfo.getOamMep().getMegUmc().trim());
							nearIDCamporeData.setEMS(oamInfo.getOamMep().getLocalMepId()+"");
							farIDCamporeData.setEMS(oamInfo.getOamMep().getRemoteMepId()+"");
							apsCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isAps()?"1":"0").getCodeName());
							lmCamporeData.setEMS(UiUtil.getCodeByValue("ENABLEDSTATUE",oamInfo.getOamMep().isLm()?"1":"0").getCodeName());
							break;
						}
					}else if(oamInfo.getOamMip() != null){
						if(oamInfo.getOamMip().getSiteId() == ConstantUtil.siteId){
							megCamporeData.setEMS(oamInfo.getOamMip().getMegId()+"");
							nearIDCamporeData.setEMS(oamInfo.getOamMip().getMipId()+"");
							break;
						}
					}
				}
				
				leftInLabelCamporeData.setEMS(tunnelEMS.getLspParticularList().get(0).getFrontLabelValue()+"");
				leftOutLabelCamporeData.setEMS(tunnelEMS.getLspParticularList().get(0).getBackLabelValue()+"");
				leftSourceCamporeData.setEMS(tunnelEMS.getSourceMac());
				leftTargetCamporeData.setEMS(tunnelEMS.getEndMac());
				PortInst portinst = new PortInst();
				PortInst inst = new PortInst();
				if(tunnelEMS.getaSiteId() == ConstantUtil.siteId){
					roleCamporeData.setEMS("ingress");
					portinst = portService.selectPortybyid(tunnelEMS.getLspParticularList().get(0).getAPortId());
					leftPortCamporeData.setEMS(portinst.getPortName());
					
				}else if(tunnelEMS.getzSiteId() == ConstantUtil.siteId){
					roleCamporeData.setEMS("egress");
					portinst = portService.selectPortybyid(tunnelEMS.getLspParticularList().get(0).getZPortId());
					leftPortCamporeData.setEMS(portinst.getPortName());
				}else{
					roleCamporeData.setEMS("xc");
					portinst = portService.selectPortybyid(tunnelEMS.getLspParticularList().get(0).getZPortId());
					inst = portService.selectPortybyid(tunnelEMS.getLspParticularList().get(1).getAPortId());
					leftPortCamporeData.setEMS(portinst.getPortName());
					rightPortCamporeData.setEMS(inst.getPortName());
					rightInLabelCamporeData.setEMS(tunnelEMS.getLspParticularList().get(1).getFrontLabelValue()+"");
					rightOutLabelCamporeData.setEMS(tunnelEMS.getLspParticularList().get(1).getBackLabelValue()+"");
					rightSourceCamporeData.setEMS(tunnelEMS.getLspParticularList().get(1).getSourceMac());
					rightTargetCamporeData.setEMS(tunnelEMS.getLspParticularList().get(1).getTargetMac());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
		
		if(isDifferent){
			if(!idCamporeData.getEMS().equals(idCamporeData.getNE())){
				box.addElement(idNode);
			}
			if(!roleCamporeData.getEMS().equals(roleCamporeData.getNE())){
				box.addElement(roleNode);
			}
			if(!inControleCamporeData.getEMS().equals(inControleCamporeData.getNE())){
				box.addElement(inControleNode);
			}
			if(!outControleCamporeData.getEMS().equals(outControleCamporeData.getNE())){
				box.addElement(outControleNode);
			}
			if(!inVlanEnableCamporeData.getEMS().equals(inVlanEnableCamporeData.getNE())){
				box.addElement(inVlanEnableNode);
			}
			if(!inVlanValueCamporeData.getEMS().equals(inVlanValueCamporeData.getNE())){
				box.addElement(inVlanValueNode);
			}
			if(!inTP_IPCamporeData.getEMS().equals(inTP_IPCamporeData.getNE())){
				box.addElement(inTP_IPNode);
			}
			if(!outVlanEnableCamporeData.getEMS().equals(outVlanEnableCamporeData.getNE())){
				box.addElement(outVlanEnableNode);
			}
			if(!outVlanValueCamporeData.getEMS().equals(outVlanValueCamporeData.getNE())){
				box.addElement(outVlanValueNode);
			}
			if(!outTP_IPCamporeData.getEMS().equals(outTP_IPCamporeData.getNE())){
				box.addElement(outTP_IPNode);
			}
			if(!outTP_IPCamporeData.getEMS().equals(outTP_IPCamporeData.getNE())){
				box.addElement(outTP_IPNode);
			}
			if(!leftPortCamporeData.getEMS().equals(leftPortCamporeData.getNE())){
				box.addElement(leftPortNode);
			}
			if(!leftInLabelCamporeData.getEMS().equals(leftInLabelCamporeData.getNE())){
				box.addElement(leftInLabelNode);
			}
			if(!leftOutLabelCamporeData.getEMS().equals(leftOutLabelCamporeData.getNE())){
				box.addElement(leftOutLabelNode);
			}
			if(!leftSourceCamporeData.getEMS().equals(leftSourceCamporeData.getNE())){
				box.addElement(leftSourceNode);
			}
			if(!leftTargetCamporeData.getEMS().equals(leftTargetCamporeData.getNE())){
				box.addElement(leftTargetNode);
			}
			if(!rightPortCamporeData.getEMS().equals(rightPortCamporeData.getNE())){
				box.addElement(rightPortNode);
			}
			if(!rightInLabelCamporeData.getEMS().equals(rightInLabelCamporeData.getNE())){
				box.addElement(rightInLabelNode);
			}
			if(!rightOutLabelCamporeData.getEMS().equals(rightOutLabelCamporeData.getNE())){
				box.addElement(rightOutLabelNode);
			}
			if(!rightSourceCamporeData.getEMS().equals(rightSourceCamporeData.getNE())){
				box.addElement(rightSourceNode);
			}
			if(!rightTargetCamporeData.getEMS().equals(rightTargetCamporeData.getNE())){
				box.addElement(rightTargetNode);
			}
			if(!inCosCamporeData.getEMS().equals(inCosCamporeData.getNE())){
				box.addElement(inCosNode);
			}
			if(!inCirCamporeData.getEMS().equals(inCirCamporeData.getNE())){
				box.addElement(inCirNode);
			}
			if(!inPirCamporeData.getEMS().equals(inPirCamporeData.getNE())){
				box.addElement(inPirNode);
			}
			if(!outCosCamporeData.getEMS().equals(outCosCamporeData.getNE())){
				box.addElement(outCosNode);
			}
			if(!outCirCamporeData.getEMS().equals(outCirCamporeData.getNE())){
				box.addElement(outCirNode);
			}
			if(!outPirCamporeData.getEMS().equals(outPirCamporeData.getNE())){
				box.addElement(outPirNode);
			}
			if(!megCamporeData.getEMS().equals(megCamporeData.getNE())){
				box.addElement(megNode);
			}
			if(!cvCamporeData.getEMS().equals(cvCamporeData.getNE())){
				box.addElement(cvNode);
			}
			if(!cycleCamporeData.getEMS().equals(cycleCamporeData.getNE())){
				box.addElement(cycleNode);
			}
			if(!megiccCamporeData.getEMS().equals(megiccCamporeData.getNE())){
				box.addElement(megiccNode);
			}
			if(!megumcCamporeData.getEMS().equals(megumcCamporeData.getNE())){
				box.addElement(megumcNode);
			}
			if(!nearIDCamporeData.getEMS().equals(nearIDCamporeData.getNE())){
				box.addElement(nearIDNode);
			}
			if(!farIDCamporeData.getEMS().equals(farIDCamporeData.getNE())){
				box.addElement(farIDNode);
			}
			if(!apsCamporeData.getEMS().equals(apsCamporeData.getNE())){
				box.addElement(apsNode);
			}
			if(!lmCamporeData.getEMS().equals(lmCamporeData.getNE())){
				box.addElement(lmNode);
			}
			
		}else{
			box.addElement(idNode);
			box.addElement(roleNode);
			box.addElement(inControleNode);
			box.addElement(outControleNode);
			box.addElement(inVlanEnableNode);
			box.addElement(inVlanValueNode);
			box.addElement(inTP_IPNode);
			box.addElement(outVlanEnableNode);
			box.addElement(outVlanValueNode);
			box.addElement(outTP_IPNode);
			box.addElement(leftPortNode);
			box.addElement(leftInLabelNode);
			box.addElement(leftOutLabelNode);
			box.addElement(leftSourceNode);
			box.addElement(leftTargetNode);
			box.addElement(rightPortNode);
			box.addElement(rightInLabelNode);
			box.addElement(rightOutLabelNode);
			box.addElement(rightSourceNode);
			box.addElement(rightTargetNode);
			box.addElement(inCosNode);
			box.addElement(inCirNode);
			box.addElement(inPirNode);
			box.addElement(outCosNode);
			box.addElement(outCirNode);
			box.addElement(outPirNode);
			box.addElement(megNode);
			box.addElement(cvNode);
			box.addElement(cycleNode);
			box.addElement(megiccNode);
			box.addElement(megumcNode);
			box.addElement(nearIDNode);
			box.addElement(farIDNode);
			box.addElement(lmNode);
		}
		
		box.addElement(node);
		
	}
	/**
	 * 生成相应的maptunnel和业务id集合
	 * @param tunnels
	 * @param ids
	 * @return
	 */
	private HashMap<Integer,Tunnel> getHashMapTunnnel(List<Tunnel> tunnels,List<Integer> ids,List<Integer> proetctIds,HashMap<Integer,Tunnel> hashMap){
		HashMap<Integer,Tunnel> tunnelMap = new HashMap<Integer, Tunnel>();
		for(Tunnel tunnel : tunnels){
			if(tunnel.getLspParticularList().size() ==1){
				if(tunnel.getaSiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(0).getAtunnelbusinessid(), tunnel);
				}else if(tunnel.getzSiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(0).getZtunnelbusinessid(), tunnel);
				}
			}else if(tunnel.getLspParticularList().size() == 2){
				if(tunnel.getLspParticularList().get(0).getASiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(0).getAtunnelbusinessid(), tunnel);
				}else if(tunnel.getLspParticularList().get(0).getZSiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(0).getZtunnelbusinessid(), tunnel);
				}else if(tunnel.getLspParticularList().get(1).getASiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(1).getAtunnelbusinessid(), tunnel);
				}else if(tunnel.getLspParticularList().get(1).getZSiteId() == ConstantUtil.siteId){
					setHashMap(tunnelMap, ids,tunnel.getLspParticularList().get(1).getZtunnelbusinessid(), tunnel);
				}
			}
			if(tunnel.getProtectTunnel() != null){
				if(tunnel.getProtectTunnel().getaSiteId() == ConstantUtil.siteId){
					if(!proetctIds.contains(tunnel.getProtectTunnel().getAprotectId())){
						proetctIds.add(tunnel.getProtectTunnel().getAprotectId());
					}
					hashMap.put(tunnel.getProtectTunnel().getAprotectId(), tunnel);
				}else if(tunnel.getProtectTunnel().getzSiteId() == ConstantUtil.siteId){
					if(!proetctIds.contains(tunnel.getProtectTunnel().getZprotectId())){
						proetctIds.add(tunnel.getProtectTunnel().getZprotectId());
					}
					hashMap.put(tunnel.getProtectTunnel().getZprotectId(), tunnel);
				}
			}
		}
		return tunnelMap;
	}
	
	private void setHashMap(HashMap<Integer,Tunnel> tunnelMap,List<Integer> ids,int tunnelbusinessid,Tunnel tunnel){
		tunnelMap.put(tunnelbusinessid, tunnel);
		if(!ids.contains(tunnelbusinessid)){
			ids.add(tunnelbusinessid);
		}
	}
	
	/**
	 * 端口数据排序
	 * @param emsPortInsts
	 * @param nePortInsts
	 */
	private void camporePortElement(List<PortInst> emsPortInsts,List<PortInst> nePortInsts){
		boolean hasAdd = false;
		for (PortInst portInst : emsPortInsts) {
			for(PortInst inst : nePortInsts){
				if(portInst.getNumber() == inst.getNumber()){
					createPortElement(portInst, inst);
					hasAdd = true;
					break;
				}
			}
			if(!hasAdd){
				createPortElement(portInst, null);
			}
			hasAdd = false;
		}
	}
	
	/**
	 * 业务数据排序
	 * @param type 
	 */
	private void camporeServiceElement(List<ServiceInfo> emslist, List<ServiceInfo> nelist, int type) {
		List<Integer> serviceIdList = new ArrayList<Integer>();
		Map<Integer, ServiceInfo> emsMap = new HashMap<Integer, ServiceInfo>();
		Map<Integer, ServiceInfo> neMap = new HashMap<Integer, ServiceInfo>();
		this.getServiceMap(emslist, emsMap, serviceIdList, type);
		this.getServiceMap(nelist, neMap, serviceIdList, type);
		Collections.sort(serviceIdList);
		
		for (Integer id : serviceIdList) {
			this.createServiceElement(emsMap.get(id), neMap.get(id), type);
		}
	}
	
	private void getServiceMap(List<ServiceInfo> servicelist, Map<Integer, ServiceInfo> map, 
			List<Integer> serviceIdList, int type) {
		for (ServiceInfo serviceInfo : servicelist) {
			int serviceId = 0;
			if(type == 0){
				if(serviceInfo.getaSiteId() == ConstantUtil.siteId){
					serviceId = ((CesInfo)serviceInfo).getAxcId();
				}else{
					serviceId = ((CesInfo)serviceInfo).getZxcId();
				}
			}else if(type == 1){
				if(serviceInfo.getaSiteId() == ConstantUtil.siteId){
					serviceId = ((ElineInfo)serviceInfo).getaXcId();
				}else{
					serviceId = ((ElineInfo)serviceInfo).getzXcId();
				}
			}else if(type == 2){
				if(serviceInfo.getaSiteId() == ConstantUtil.siteId){
					serviceId = ((ElanInfo)serviceInfo).getAxcId();
				}else{
					serviceId = ((ElanInfo)serviceInfo).getZxcId();
				}
			}else if(type == 3){
				if(((EtreeInfo)serviceInfo).getRootSite() == ConstantUtil.siteId){
					serviceId = ((EtreeInfo)serviceInfo).getaXcId();
				}else{
					serviceId = ((EtreeInfo)serviceInfo).getzXcId();
				}
			}else if(type == 52){
				if(serviceInfo.getaSiteId() == ConstantUtil.siteId){
					serviceId = ((	CccInfo)serviceInfo).getaXcId();
				}
			}
			if(!serviceIdList.contains(serviceId) && serviceId != 0){
				serviceIdList.add(serviceId);
			}
			if(serviceId != 0){
				map.put(serviceId, serviceInfo);
			}
		}
	}
	
	/**
	 * 添加业务节点
	 * @param type 
	 */
	private void createServiceElement(ServiceInfo serviceEms, ServiceInfo serviceNe, int type) {
		Node element = new Node();
		element.setName(serviceEms == null ? null:serviceEms.getName());
		if(serviceEms == null){
			element.setName(serviceNe == null ? null:serviceNe.getName());
		}
		if(type == 3){
			//etree业务,显示是根节点还是叶子节点
			Node serviceType = new Node();
			CamporeData serviceTypeData = new CamporeData();
			String neName = serviceNe == null ? null:this.getServiceType(((EtreeInfo)serviceNe));
			serviceTypeData.setNE(neName);
			String emsName = serviceEms == null ? null:this.getServiceType((EtreeInfo)serviceEms);
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SERVICENAME_TYPE),serviceTypeData, serviceType, emsName, element);
			if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
				box.addElement(serviceType);
			}else{
				if(!neName.equals(emsName)){
					box.addElement(serviceType);
				}
			}
		}
		if(type == 0){
			//ces业务,显示端口名称
			Node e1Name = new Node();
			CamporeData e1NameData = new CamporeData();
			String neName = serviceNe == null ? null:this.getE1PortName(((CesInfo)serviceNe));
			e1NameData.setNE(neName);
			String emsName = serviceEms == null ? null:this.getE1PortName((CesInfo)serviceEms);
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME), e1NameData, e1Name, emsName, element);
			if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
				box.addElement(e1Name);
			}else{
				if(!neName.equals(emsName)){
					box.addElement(e1Name);
				}
			}
			//ces业务显示e1端口绑定的pw标签
//			Node pwLabel = new Node();
//			CamporeData pwLabelData = new CamporeData();
//			String labelNe = serviceNe == null ? null:this.getE1Label(((CesInfo)serviceNe));
//			pwLabelData.setNE(labelNe);
//			String labelEms = serviceEms == null ? null:this.getE1Label((CesInfo)serviceEms);
//			this.setNode(ResourceUtil.srcStr(StringKeysObj.PW_LABEL), pwLabelData, pwLabel, labelEms, element);
//			if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
//				box.addElement(pwLabel);
//			}else{
//				if(!labelNe.equals(labelEms)){
//					box.addElement(pwLabel);
//				}
//			}
		}
		int length = 0;
		if(serviceEms != null && !serviceEms.getAcPortList().isEmpty()){
			length = serviceEms.getAcPortList().size();
		}else if(serviceNe != null && !serviceNe.getAcPortList().isEmpty()){
			length = serviceNe.getAcPortList().size();
		}
		
		for(int k = 0 ;k < length; k++)
		{
			//ac信息
			AcPortInfo acInfo = null;
			if(serviceEms != null){
				if(serviceEms.getAcPortList().size() > 0){
					acInfo = serviceEms.getAcPortList().get(k);
				}
			}
			AcPortInfo acInfoNE = null;
			if(serviceNe != null){
				if(serviceNe.getAcPortList().size() > 0){
					acInfoNE = serviceNe.getAcPortList().get(k);
				}
			}
			if(acInfo != null || acInfoNE != null)
			{
				Node acElement = new Node();
				acElement.setName(ResourceUtil.srcStr(StringKeysPanel.PANEL_AC_LIST));
				acElement.setParent(element);
				box.addElement(acElement);
				//端口名称
				Node portName = new Node();
				CamporeData portNameData = new CamporeData();
				portNameData.setNE(acInfoNE == null ? null:this.getPortName(acInfoNE));
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME), portNameData, portName, acInfo == null ? null:this.getPortName(acInfo), acElement);
				//端口模式
				Node portModel = new Node();
				CamporeData portModelData = new CamporeData();
				try {
					portModelData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getPortModel()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_MODAL), portModelData, portModel, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getPortModel()).getCodeName(), acElement);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				//MAC地址学习
				Node macLearn = new Node();
				CamporeData macLearnData = new CamporeData();
				try {
					macLearnData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getMacAddressLearn()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_LEARN), macLearnData, macLearn, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getMacAddressLearn()).getCodeName(), acElement);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				//水平分割(vpls业务用到)
				Node horizontalDivision = new Node();
				//MAC地址表容量(vpls业务用到)
				Node macCount = new Node();
				if(type == 2 || type == 3 ||type ==52){
					CamporeData horizontalDivisionData = new CamporeData();
					try {
						horizontalDivisionData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getHorizontalDivision()).getCodeName());
						this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_HORIZONAL), horizontalDivisionData, horizontalDivision, acInfo == null ? 
								null:UiUtil.getCodeById(acInfo.getHorizontalDivision()).getCodeName(), acElement);
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
					CamporeData macCountData = new CamporeData();
					macCountData.setNE(acInfoNE == null ? null:acInfoNE.getMacCount()+"");
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_TABLE_COUNT), macCountData, macCount, acInfo == null ? 
							null:acInfo.getMacCount()+"", acElement);
				}
				//VC流量监管使能
				Node vcEnable = new Node();
				CamporeData vcEnableData = new CamporeData();
				try {
					vcEnableData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getManagerEnable()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_VCFLOW), vcEnableData, vcEnable, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getManagerEnable()).getCodeName(), acElement);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				//上话TAG识别
				Node tagAction = new Node();
				CamporeData tagActionData = new CamporeData();
				try {
					tagActionData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getTagAction()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_WUHAN), tagActionData, tagAction, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getTagAction()).getCodeName(), acElement);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				//下话TAG行为
				Node exitRule = new Node();
				CamporeData exitRuleData = new CamporeData();
				try {
					exitRuleData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getExitRule()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_ACTION), exitRuleData, exitRule, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getExitRule()).getCodeName(), acElement);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
				//下话增加VLAN ID
				Node vlanId = new Node();
				CamporeData vlanIdData = new CamporeData();
				vlanIdData.setNE(acInfoNE == null ? null:acInfoNE.getVlanId());
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID), vlanIdData, vlanId, acInfo == null ? null:acInfo.getVlanId(), acElement);
				//下话增加VLAN PRI
				Node vlanPri = new Node();
				CamporeData vlanPriData = new CamporeData();
				vlanPriData.setNE(acInfoNE == null ? null:acInfoNE.getVlanpri());
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI), vlanPriData, vlanPri, acInfo == null ? null:acInfo.getVlanpri(), acElement);
				//模式
				Node model = new Node();
				CamporeData modelData = new CamporeData();
				try 
				{
					modelData.setNE(acInfoNE == null ? null:UiUtil.getCodeById(acInfoNE.getModel()).getCodeName());
					this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL), modelData, model, acInfo == null ? 
							null:UiUtil.getCodeById(acInfo.getModel()).getCodeName(), acElement);
				} catch (Exception e) 
				{
					ExceptionManage.dispose(e, this.getClass());
				}
				/*简单QoS***********************************/
				QosInfo simpleQos = acInfo == null ? null:acInfo.getSimpleQos();
				QosInfo simpleQosNE = acInfoNE == null ? null:acInfoNE.getSimpleQos();
				Node simpleQosElement = new Node();
				simpleQosElement.setName(ResourceUtil.srcStr(StringKeysTip.TIP_SIMPLEQOS));
				simpleQosElement.setParent(acElement);
				box.addElement(simpleQosElement);
				//流类型
				Node qosType = new Node();
				CamporeData qosTypeData = new CamporeData();
				qosTypeData.setNE(simpleQosNE == null ? null:simpleQosNE.getQosType());
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_STREAM_TYPE), qosTypeData, qosType, simpleQos == null ? null:simpleQos.getQosType(), simpleQosElement);
				//SEQ
				Node seq = new Node();
				CamporeData seqData = new CamporeData();
				seqData.setNE(simpleQosNE == null ? null:simpleQosNE.getSeq()+"");
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SEQ), seqData, seq, simpleQos == null ? null:simpleQos.getSeq()+"", simpleQosElement);
				//COS
//			Node cos = new Node();
//			CamporeData cosData = new CamporeData();
//			cosData.setNE(simpleQosNE == null ? null:simpleQosNE.getCos()+"");
//			this.setNode("COS", cosData, cos, simpleQos == null ? null:simpleQos.getCos()+"", simpleQosElement);
				//CIR
				Node cir = new Node();
				CamporeData cirData = new CamporeData();
				cirData.setNE(simpleQosNE == null ? null:simpleQosNE.getCos()+"");
				this.setNode("CIR", cirData, cir, simpleQos == null ? null:simpleQos.getCos()+"", simpleQosElement);
				//CBS
				Node cbs = new Node();
				CamporeData cbsData = new CamporeData();
				cbsData.setNE(simpleQosNE == null ? null:simpleQosNE.getCbs()+"");
				this.setNode("CBS", cbsData, cbs, simpleQos == null ? null:simpleQos.getCbs()+"", simpleQosElement);
				//EIR
				Node eir = new Node();
				CamporeData eirData = new CamporeData();
				eirData.setNE(simpleQosNE == null ? null:simpleQosNE.getEir()+"");
				this.setNode("EIR", eirData, eir, simpleQos == null ? null:simpleQos.getEir()+"", simpleQosElement);
//				//EBS
//				Node ebs = new Node();
//				CamporeData ebsData = new CamporeData();
//				ebsData.setNE(simpleQosNE == null ? null:simpleQosNE.getEbs()+"");
//				this.setNode("EBS", ebsData, ebs, simpleQos == null ? null:simpleQos.getEbs()+"", simpleQosElement);
				//PIR
				Node pir = new Node();
				CamporeData pirData = new CamporeData();
				pirData.setNE(simpleQosNE == null ? null:simpleQosNE.getPir()+"");
				this.setNode("PIR", pirData, pir, simpleQos == null ? null:simpleQos.getPir()+"", simpleQosElement);
				//PBS
				Node pbs = new Node();
				CamporeData pbsData = new CamporeData();
				pbsData.setNE(simpleQosNE == null ? null:simpleQosNE.getPbs()+"");
				this.setNode("PBS", pbsData, pbs, simpleQos == null ? null:simpleQos.getPbs()+"", simpleQosElement);
				//色感模式
				Node colorSence = new Node();
				CamporeData colorSenceData = new CamporeData();
				colorSenceData.setNE(simpleQosNE == null ? null:simpleQosNE.getColorSence()+"");
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR_AWARE), colorSenceData, colorSence, simpleQos == null ? null:simpleQos.getColorSence()+"", simpleQosElement);
				/*细分QoS***********************************/
				List<Acbuffer> acBufferList = acInfo == null ? null:acInfo.getBufferList();
				List<Acbuffer> acBufferListNE = acInfoNE == null? null:acInfoNE.getBufferList();
				Node bufferElement = new Node();
				bufferElement.setName(ResourceUtil.srcStr(StringKeysTip.TIP_BUFFQOS));
				bufferElement.setParent(acElement);
				box.addElement(bufferElement);
				if( acBufferList != null){
					for (int i = 0; i < acBufferList.size(); i++) {
						Acbuffer acbufferNE = null;
						if(acBufferListNE != null && i < acBufferListNE.size()){
							acbufferNE = acBufferListNE.get(i);
						}
						this.createAcBuffElement(acBufferList.get(i), acbufferNE, i, bufferElement, serviceEms, serviceNe);
					}
				}else if(acBufferListNE != null){
					for (int i = 0; i < acBufferListNE.size(); i++) {
						this.createAcBuffElement(null, acBufferListNE.get(i), i, bufferElement, serviceEms, serviceNe);
					}
				}
				if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
					box.addElement(portName);
					box.addElement(portModel);
					box.addElement(macLearn);
					if(type == 2 || type == 3 ||type==52){
						box.addElement(horizontalDivision);
						box.addElement(macCount);
					}
					box.addElement(vcEnable);
					box.addElement(tagAction);
					box.addElement(exitRule);
					box.addElement(vlanId);
					box.addElement(vlanPri);
					box.addElement(model);
					/*简单流***********************/
					box.addElement(qosType);
					box.addElement(seq);
//				box.addElement(cos);
					box.addElement(cir);
					box.addElement(cbs);
					box.addElement(eir);
//					box.addElement(ebs);
					box.addElement(pir);
					box.addElement(pbs);
					box.addElement(colorSence);
				}else if(isDifferent)
				{//显示不相同的节点
					if(acInfo.getPortId() != acInfoNE.getPortId()){
						box.addElement(portName);
					}
					if(acInfo.getPortModel() != acInfoNE.getPortModel()){
						box.addElement(portModel);
					}
					if(acInfo.getMacAddressLearn() != acInfoNE.getMacAddressLearn()){
						box.addElement(macLearn);
					}
					if(type == 2 || type == 3){
						if(acInfo.getHorizontalDivision() != acInfoNE.getHorizontalDivision()){
							box.addElement(horizontalDivision);
						}
						if(acInfo.getMacCount() != acInfoNE.getMacCount()){
							box.addElement(macCount);
						}
					}
					if(acInfo.getManagerEnable() != acInfoNE.getManagerEnable()){
						box.addElement(vcEnable);
					}
					if(acInfo.getTagAction() != acInfoNE.getTagAction()){
						box.addElement(tagAction);
					}
					if(acInfo.getExitRule() != acInfoNE.getExitRule()){
						box.addElement(exitRule);
					}
					if(!acInfo.getVlanId().equals(acInfoNE.getVlanId())){
						box.addElement(vlanId);
					}
					if(!acInfo.getVlanpri().equals(acInfoNE.getVlanpri())){
						box.addElement(vlanPri);
					}
					if(acInfo.getModel() != acInfoNE.getModel()){
						box.addElement(model);
					}
					if(!simpleQos.getQosType().equals(simpleQosNE.getQosType())){
						box.addElement(qosType);
					}
					if(simpleQos.getSeq() != simpleQosNE.getSeq()){
						box.addElement(seq);
					}
//				if(simpleQos.getCos() != simpleQosNE.getCos()){
//					box.addElement(cos);
//				}
					if(simpleQos.getCir() != simpleQosNE.getCir()){
						box.addElement(cir);
					}
					if(simpleQos.getCbs() != simpleQosNE.getCbs()){
						box.addElement(cbs);
					}
					if(simpleQos.getEir() != simpleQosNE.getEir()){
						box.addElement(eir);
					}
//					if(simpleQos.getEbs() != simpleQosNE.getEbs()){
//						box.addElement(ebs);
//					}
					if(simpleQos.getPir() != simpleQosNE.getPir()){
						box.addElement(pir);
					}
					if(simpleQos.getPbs() != simpleQosNE.getPbs()){
						box.addElement(pbs);
					}
					if(simpleQos.getColorSence() != simpleQosNE.getColorSence()){
						box.addElement(colorSence);
					}
				}
			}
			
		}
		/*nni端口的流信息**********************/
		if(type!=52){
		Node pwElement = new Node();
		pwElement.setName(ResourceUtil.srcStr(StringKeysTip.TIP_NNIBUFFLIST));
		pwElement.setParent(element);
		box.addElement(pwElement);
		List<PwNniInfo> pwNniList = serviceEms == null ? null:serviceEms.getPwNniList();
		List<PwNniInfo> pwNniListNE = serviceNe == null ? null:serviceNe.getPwNniList();
		if(pwNniList != null){
			for (int i = 0; i < pwNniList.size(); i++) {
				PwNniInfo pwNniNE = null;
				if(pwNniListNE != null && i < pwNniListNE.size()){
					pwNniNE = pwNniListNE.get(i);
				}
				this.createPwNniElement(pwNniList.get(i), pwNniNE, i, pwElement, serviceEms, serviceNe, type);
			}
		}else if(pwNniListNE != null){
			for (int i = 0; i < pwNniListNE.size(); i++) {
				this.createPwNniElement(null, pwNniListNE.get(i), i, pwElement, serviceEms, serviceNe, type);
			}
		}
		}
		element.setUserObject(serviceEms);
		box.addElement(element);
	}

//	private String getE1Label(CesInfo serviceNe) {
//		E1InfoService e1Service = null;
//		try {
//			e1Service = (E1InfoService) ConstantUtil.serviceFactory.newService(Services.E1Info);
//			E1Info e1 = new E1Info();
//			if(serviceNe.getaSiteId() == ConstantUtil.siteId){
//				e1.setPortId(serviceNe.getaAcId());
//			}else{
//				e1.setPortId(serviceNe.getzAcId());
//			}
//			List<E1Info> e1List = e1Service.selectByCondition(e1);
//			if(e1List.size() > 0){
//				return e1List.get(0).getPwLabel()+"";
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e, this.getClass());
//		} finally {
//			UiUtil.closeService(e1Service);
//		}
//		return null;
//	}
	
	private void createPwNniElement(PwNniInfo pwNni, PwNniInfo pwNniNE, int i, Node pwElement,
			ServiceInfo serviceEms, ServiceInfo serviceNe, int type) {
		Node nniElement = new Node();
		nniElement.setName(ResourceUtil.srcStr(StringKeysTip.TIP_NNIBUFF)+(i+1));
		nniElement.setParent(pwElement);
		box.addElement(nniElement);
		//控制字使能
		Node controlEnable = new Node();
		CamporeData controlEnableData = new CamporeData();
		try {
			controlEnableData.setNE(pwNniNE == null ? null:UiUtil.getCodeById(pwNniNE.getControlEnable()).getCodeName());
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_CONTROLENABLE), controlEnableData, controlEnable, pwNni == null ? 
					null:UiUtil.getCodeById(pwNni.getControlEnable()).getCodeName(), nniElement);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		//水平分割
		Node horizontalDivisionNni = new Node();
		if(type != 0 && type != 1){
			CamporeData horizontalDivisionNniData = new CamporeData();
			try {
				horizontalDivisionNniData.setNE(pwNniNE == null ? null:UiUtil.getCodeById(pwNniNE.getHorizontalDivision()).getCodeName());
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_HORIZONAL), horizontalDivisionNniData, horizontalDivisionNni, pwNni == null ? 
						null:UiUtil.getCodeById(pwNni.getHorizontalDivision()).getCodeName(), nniElement);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		//上话增加VLAN ID
		Node svlan = new Node();
		CamporeData svlanData = new CamporeData();
		svlanData.setNE(pwNniNE == null ? null:pwNniNE.getSvlan()+"");
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID_UP), svlanData, svlan, pwNni == null ? null:pwNni.getSvlan()+"", nniElement);
		//上话增加VLAN PRI
		Node vlanPriNni = new Node();
		CamporeData vlanPriNniData = new CamporeData();
		vlanPriNniData.setNE(pwNniNE == null ? null:pwNniNE.getVlanpri()+"");
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI_UP), vlanPriNniData, vlanPriNni, pwNni == null ? null:pwNni.getVlanpri()+"", nniElement);
		//下话TAG识别
		Node tagActionNni = new Node();
		CamporeData tagActionNniData = new CamporeData();
		try {
			tagActionNniData.setNE(pwNniNE == null ? null:UiUtil.getCodeById(pwNniNE.getTagAction()).getCodeName());
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_DOWM_WUHAN), tagActionNniData, tagActionNni, pwNni == null ? 
					null:UiUtil.getCodeById(pwNni.getTagAction()).getCodeName(), nniElement);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		//上话TAG行为
		Node exitRuleNni = new Node();
		CamporeData exitRuleNniData = new CamporeData();
		try {
			exitRuleNniData.setNE(pwNniNE == null ? null:UiUtil.getCodeById(pwNniNE.getExitRule()).getCodeName());
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_ACTION_WUHAN), exitRuleNniData, exitRuleNni, pwNni == null ? 
					null:UiUtil.getCodeById(pwNni.getExitRule()).getCodeName(), nniElement);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		//MAC地址学习
		Node macLearnNni = new Node();
		if(type != 0 && type != 1){
			CamporeData macLearnNniData = new CamporeData();
			try {
				macLearnNniData.setNE(pwNniNE == null ? null:UiUtil.getCodeById(pwNniNE.getMacAddressLearn()).getCodeName());
				this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_LEARN), macLearnNniData, macLearnNni, pwNni == null ? 
						null:UiUtil.getCodeById(pwNni.getMacAddressLearn()).getCodeName(), nniElement);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
			box.addElement(controlEnable);
			box.addElement(svlan);
			box.addElement(vlanPriNni);
			box.addElement(tagActionNni);
			box.addElement(exitRuleNni);
			if(type != 0 && type != 1){
				box.addElement(horizontalDivisionNni);
				box.addElement(macLearnNni);
			}
		}else if(isDifferent){//显示不相同的节点
			if(pwNni.getControlEnable() != pwNniNE.getControlEnable()){
				box.addElement(controlEnable);
			}
			if(type != 0 && type != 1){
				if(pwNni.getHorizontalDivision() != pwNniNE.getHorizontalDivision()){
					box.addElement(horizontalDivisionNni);
				}
			}
			if(!pwNni.getSvlan().equals(pwNniNE.getSvlan())){
				box.addElement(svlan);
			}
			if(!pwNni.getVlanpri().equals(pwNniNE.getVlanpri())){
				box.addElement(vlanPriNni);
			}
			if(pwNni.getTagAction() != pwNniNE.getTagAction()){
				box.addElement(tagActionNni);
			}
			if(pwNni.getExitRule() != pwNniNE.getExitRule()){
				box.addElement(exitRuleNni);
			}
			if(type != 0 && type != 1){
				if(pwNni.getMacAddressLearn() != pwNniNE.getMacAddressLearn()){
					box.addElement(macLearnNni);
				}
			}
		}
	}

	private void createAcBuffElement(Acbuffer acbuffer, Acbuffer acbufferNE, int i, Node bufferElement,
			ServiceInfo serviceEms, ServiceInfo serviceNe) {
		Node buffElement = new Node();
		buffElement.setName(ResourceUtil.srcStr(StringKeysTip.TIP_BUFF)+(i+1));
		buffElement.setParent(bufferElement);
		box.addElement(buffElement);
		//使能状态
		Node bufferEnable = new Node();
		CamporeData bufferEnableData = new CamporeData();
		bufferEnableData.setNE(acbufferNE == null ? null:(acbufferNE.getBufferEnable()==0 ? 
				ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO):ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED)));
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLED_STATUS), bufferEnableData, 
				bufferEnable, acbuffer == null ? 
				null:(acbuffer.getBufferEnable()==0 ?
						ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO):ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED)), buffElement);
		//模式
		Node bufferModel = new Node();
		CamporeData bufferModelData = new CamporeData();
		bufferModelData.setNE(acbufferNE == null ? null:this.getAcBufferModel(acbufferNE));
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL), bufferModelData, bufferModel, acbuffer == null ? 
				null:this.getAcBufferModel(acbuffer), buffElement);
		//VLAN
		Node buffVlanId = new Node();
		CamporeData buffVlanIdData = new CamporeData();
		buffVlanIdData.setNE(acbufferNE == null ? null:acbufferNE.getVlanId()+"");
		this.setNode("VLAN", buffVlanIdData, buffVlanId, acbuffer == null ? null:acbuffer.getVlanId()+"", buffElement);
		//CIR
		Node buffCir = new Node();
		CamporeData buffCirData = new CamporeData();
		buffCirData.setNE(acbufferNE == null ? null:acbufferNE.getCir()+"");
		this.setNode("CIR", buffCirData, buffCir, acbuffer == null ? null:acbuffer.getCir()+"", buffElement);
		//PIR
		Node buffPir = new Node();
		CamporeData buffPirData = new CamporeData();
		buffPirData.setNE(acbufferNE == null ? null:acbufferNE.getPir()+"");
		this.setNode("PIR", buffPirData, buffPir, acbuffer == null ? null:acbuffer.getPir()+"", buffElement);
		//源MAC
		Node sourceMac = new Node();
		CamporeData sourceMacData = new CamporeData();
		sourceMacData.setNE(acbufferNE == null ? null:acbufferNE.getSourceMac());
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC), sourceMacData, sourceMac, acbuffer == null ? null:acbuffer.getSourceMac(), buffElement);
		//目的MAC
		Node targetMac = new Node();
		CamporeData targetMacData = new CamporeData();
		targetMacData.setNE(acbufferNE == null ? null:acbufferNE.getTargetMac());
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC), targetMacData, targetMac, acbuffer == null ? null:acbuffer.getTargetMac(), buffElement);
		//CM(色感模式)
		Node cm = new Node();
		CamporeData cmData = new CamporeData();
		cmData.setNE(acbufferNE == null ? null:acbufferNE.getCm()+"");
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR_AWARE), cmData, cm, acbuffer == null ? null:acbuffer.getCm()+"", buffElement);
		//802.1P
		Node eight1P =new Node();
		CamporeData eight1pData = new CamporeData();
		eight1pData.setNE(acbufferNE == null ? null:acbufferNE.getEightIp()+"");
		this.setNode("802.1P", eight1pData, eight1P, acbuffer == null ? null:acbuffer.getEightIp()+"", buffElement);
		//CBS
		Node buffCbs = new Node();
		CamporeData buffCbsData = new CamporeData();
		buffCbsData.setNE(acbufferNE == null ? null:acbufferNE.getCbs()+"");
		this.setNode("CBS", buffCbsData, buffCbs, acbuffer == null ? null:acbuffer.getCbs()+"", buffElement);
		//PBS
		Node buffPbs = new Node();
		CamporeData buffPbsData = new CamporeData();
		buffPbsData.setNE(acbufferNE == null ? null:acbufferNE.getPbs()+"");
		this.setNode("PBS", buffPbsData, buffPbs, acbuffer == null ? null:acbuffer.getPbs()+"", buffElement);
		//源IP
		Node sourceIp = new Node();
		CamporeData sourceIpData = new CamporeData();
		sourceIpData.setNE(acbufferNE == null ? null:acbufferNE.getSourceIp());
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP), sourceIpData, sourceIp, acbuffer == null ? null:acbuffer.getSourceIp(), buffElement);
		//目的IP
		Node targetIp = new Node();
		CamporeData targetIpData = new CamporeData();
		targetIpData.setNE(acbufferNE == null ? null:acbufferNE.getTargetIp());
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_END_IP), targetIpData, targetIp, acbuffer == null ? null:acbuffer.getTargetIp(), buffElement);
		//策略模式
		Node strategy = new Node();
		CamporeData strategyData = new CamporeData();
		strategyData.setNE(acbufferNE == null ? null:(acbufferNE.getStrategy() == 0 ?
				ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_PHB):ResourceUtil.srcStr(StringKeysTip.TIP_BASE_PHB)));
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_STRATEGY_MODAL), strategyData, strategy, acbuffer == null ? 
				null:(acbuffer.getStrategy() == 0 ?
						ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_PHB):ResourceUtil.srcStr(StringKeysTip.TIP_BASE_PHB)), buffElement);
		//DSCP
		Node dscp = new Node();
		CamporeData dscpData = new CamporeData();
		dscpData.setNE(acbufferNE == null ? null:acbufferNE.getIpDscp()+"");
		this.setNode("DSCP", dscpData, dscp, acbuffer == null ? null:acbuffer.getIpDscp()+"", buffElement);
		//指定PHB
		Node phb = new Node();
		CamporeData phbData = new CamporeData();
		try {
			phbData.setNE(acbufferNE == null ? null:UiUtil.getCodeById(acbufferNE.getPhb()).getCodeName());
			this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_APPOINT_PHB), phbData, phb, acbuffer == null ? 
					null:UiUtil.getCodeById(acbuffer.getPhb()).getCodeName(), buffElement);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		//源TCP/UDP端口号
		Node sourceTcpId = new Node();
		CamporeData sourceTcpIdData = new CamporeData();
		sourceTcpIdData.setNE(acbufferNE == null ? null:acbufferNE.getSourceTcpPortId()+"");
		this.setNode(ResourceUtil.srcStr(StringKeysTip.TIP_SOURCE_TCP), sourceTcpIdData, sourceTcpId, acbuffer == null ? 
				null:acbuffer.getSourceTcpPortId()+"", buffElement);
		//宿TCP/UDP端口号
		Node endTcpId = new Node();
		CamporeData endTcpIdData = new CamporeData();
		endTcpIdData.setNE(acbufferNE == null ? null:acbufferNE.getEndTcpPortId()+"");
		this.setNode(ResourceUtil.srcStr(StringKeysTip.TIP_END_TCP), endTcpIdData, endTcpId, acbuffer == null ? 
				null:acbuffer.getEndTcpPortId()+"", buffElement);
		//IPTOS
		Node ipTos = new Node();
		CamporeData ipTosData = new CamporeData();
		ipTosData.setNE(acbufferNE == null ? null:acbufferNE.getIPTOS()+"");
		this.setNode("IPTOS", ipTosData, ipTos, acbuffer == null ? 
				null:acbuffer.getIPTOS()+"", buffElement);
		//端口类型
		Node portType = new Node();
		CamporeData portTypeData = new CamporeData();
		portTypeData.setNE(acbufferNE == null ? null:this.getPortType(acbufferNE));
		this.setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_TYPE), portTypeData, portType, acbuffer == null ? 
				null:this.getPortType(acbuffer), buffElement);
		if(serviceNe == null || serviceEms == null || !isDifferent){//显示所有节点
			box.addElement(bufferEnable);
			box.addElement(bufferModel);
			box.addElement(buffVlanId);
			box.addElement(buffCir);
			box.addElement(buffPir);
			box.addElement(sourceMac);
			box.addElement(targetMac);
			box.addElement(cm);
			box.addElement(eight1P);
			box.addElement(buffCbs);
			box.addElement(buffPbs);
			box.addElement(sourceIp);
			box.addElement(targetIp);
			box.addElement(strategy);
			box.addElement(dscp);
			box.addElement(phb);
			box.addElement(sourceTcpId);
			box.addElement(endTcpId);
			box.addElement(ipTos);
			box.addElement(portType);
		}else if(isDifferent){//显示不相同的节点
			if(acbuffer.getBufferEnable() != acbufferNE.getBufferEnable()){
				box.addElement(bufferEnable);
			}
			if(acbuffer.getModel() != acbufferNE.getModel()){
				box.addElement(bufferModel);
			}
			if(acbuffer.getVlanId() != acbufferNE.getVlanId()){
				box.addElement(buffVlanId);
			}
			if(acbuffer.getCir() != acbufferNE.getCir()){
				box.addElement(buffCir);
			}
			if(acbuffer.getPir() != acbufferNE.getPir()){
				box.addElement(buffPir);
			}
			if(!acbuffer.getSourceMac().equals(acbufferNE.getSourceMac())){
				box.addElement(sourceMac);
			}
			if(!acbuffer.getTargetMac().equals(acbufferNE.getTargetMac())){
				box.addElement(targetMac);
			}
			if(acbuffer.getCm() != acbufferNE.getCm()){
				box.addElement(cm);
			}
			if(acbuffer.getEightIp() != acbufferNE.getEightIp()){
				box.addElement(eight1P);
			}
			if(acbuffer.getCbs() != acbufferNE.getCbs()){
				box.addElement(buffCbs);
			}
			if(acbuffer.getPbs() != acbufferNE.getPbs()){
				box.addElement(buffPbs);
			}
			if(!acbuffer.getSourceIp().equals(acbufferNE.getSourceIp())){
				box.addElement(sourceIp);
			}
			if(!acbuffer.getTargetIp().equals(acbufferNE.getTargetIp())){
				box.addElement(targetIp);
			}
			if(acbuffer.getStrategy() != acbufferNE.getStrategy()){
				box.addElement(strategy);
			}
			if(acbuffer.getIpDscp() != acbufferNE.getIpDscp()){
				box.addElement(dscp);
			}
			if(acbuffer.getPhb() != acbufferNE.getPhb()){
				box.addElement(phb);
			}
			if(acbuffer.getSourceTcpPortId() != acbufferNE.getSourceTcpPortId()){
				box.addElement(sourceTcpId);
			}
			if(acbuffer.getEndTcpPortId() != acbufferNE.getEndTcpPortId()){
				box.addElement(endTcpId);
			}
			if(acbuffer.getIPTOS() != acbufferNE.getIPTOS()){
				box.addElement(ipTos);
			}
			if(acbuffer.getPortType() != acbufferNE.getPortType()){
				box.addElement(portType);
			}
		}
	}

	private String getE1PortName(CesInfo serviceNe) {
		E1InfoService_MB e1Service = null;
		try {
			e1Service = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			E1Info e1 = new E1Info();
			if(serviceNe.getaSiteId() == ConstantUtil.siteId){
				e1.setPortId(serviceNe.getaAcId());
			}else{
				e1.setPortId(serviceNe.getzAcId());
			}
			List<E1Info> e1List = e1Service.selectByCondition(e1);
			if(e1List.size() > 0){
				return e1List.get(0).getPortName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(e1Service);
		}
		return null;
	}

	private String getServiceType(EtreeInfo etreeInfo) {
		if(etreeInfo.getRootSite() > 0 && etreeInfo.getRootSite() == ConstantUtil.siteId){
			return ResourceUtil.srcStr(StringKeysObj.ROOT);
		}else if(etreeInfo.getBranchSite() > 0 && etreeInfo.getBranchSite() == ConstantUtil.siteId){
			return ResourceUtil.srcStr(StringKeysObj.LEAF);
		}
		return null;
	}

	private String getPortType(Acbuffer acbuffer) {
		int type = acbuffer.getPortType();
		if(type == 0){
			return ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO);
		}else if(type == 1){
			return "TCP";
		}else if(type == 2){
			return "UDP";
		}
		return "";
	}

	private String getAcBufferModel(Acbuffer acbuffer) {
		int model = acbuffer.getModel();
		if(model == 0){
			return ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO);
		}else if(model == 1){
			return "trTCM";
		}else if(model == 2){
			return "Modified trTCM";
		}
		return "";
	}

	private String getPortName(AcPortInfo acInfo) {
		PortService_MB portService = null;
		PortLagService_MB lagService = null;
		try {
			if(acInfo.getPortId() > 0){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				PortInst port = portService.selectPortybyid(acInfo.getPortId());
				if(port != null){
					return port.getPortName();
				}
			}else{
				lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
				PortLagInfo lag = new PortLagInfo();
				lag.setId(acInfo.getLagId());
				List<PortLagInfo> lagList = lagService.selectByCondition(lag);
				if(lagList.size() > 0){
					return "lag/"+lagList.get(0).getId();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(lagService);
		}
		return null;
	}

	/**
	 * 添加端口节点
	 * @param emsobject
	 * @param neObject
	 */
	private void createPortElement(Object emsobject, Object neObject) {
		PortInst portInstEMS = (PortInst) emsobject;
		Node element = new Node();
		element.setName(portInstEMS.getPortName());
		PortInst portInstNE = null;
		try {
			
			if (emsobject instanceof PortInst) {
				Node nodeEnable = new Node();
				CamporeData camporeInfoEnable = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_ENABLED),camporeInfoEnable, nodeEnable, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getIsEnabled_code()+"").getCodeName(), element);
				
				Node nodePortType = new Node();
				CamporeData camporeInfoPortType = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_TYPE),camporeInfoPortType, nodePortType, portInstEMS.getPortType()+"", element);
				
				Node nodeMTU = new Node();
				CamporeData camporeInfoMTU = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MAX_FRAME_WORDS),camporeInfoMTU, nodeMTU, portInstEMS.getPortAttr().getMaxFrameSize()+"", element);
				
				Node nodeFlow = new Node();
				CamporeData camporeInfoFlow = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_802_3),camporeInfoFlow, nodeFlow, UiUtil.getCodeById(portInstEMS.getPortAttr().getFluidControl()).getCodeName(), element);
				
				Node nodeOutSpeed = new Node();
				CamporeData camporeInfoOutSpeed = new CamporeData();
				String exitLimit = portInstEMS.getPortAttr().getExitLimit();
				if(exitLimit == null || ("-1").equals(exitLimit) || ("").equals(exitLimit)){
					exitLimit = 10000000+"";
				}
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_LIMIT_WUHAN),camporeInfoOutSpeed, nodeOutSpeed, exitLimit, element);
				
				Node nodeModel = new Node();
				CamporeData camporeInfoModel = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_WORK_MODEL),camporeInfoModel, nodeModel, UiUtil.getCodeById(portInstEMS.getPortAttr().getWorkModel()).getCodeName(), element);
				
				Node nodeQinq = new Node();
				CamporeData camporeInfoQinq = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_QINQ_ENABLE),camporeInfoQinq, nodeQinq, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getIsEnabled_QinQ()+"").getCodeName(), element);
				
				Node nodeisEnabledLaser = new Node();
				CamporeData camporeInfoisEnabledLaser = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_LASER_ENABLE),camporeInfoisEnabledLaser, nodeisEnabledLaser, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getIsEnabledLaser()+"").getCodeName(), element);
				
				Node nodeAlarm = new Node();
				CamporeData camporeInfoAlarm = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REVERSAL),camporeInfoAlarm, nodeAlarm, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getIsEnabledAlarmReversal()+"").getCodeName(), element);
				
				Node nodeLoop = new Node();
				CamporeData camporeInfoLoop = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SERVICESTATE),camporeInfoLoop, nodeLoop, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getServicePort()+"").getCodeName(), element);
				
				Node nodeBroadcastBate = new Node();
				CamporeData camporeInfoBroadcastBate = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_RESTRAIN),camporeInfoBroadcastBate, nodeBroadcastBate, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getIsBroadcast()).getCodeName(), element);
				
				Node nodeBroadcastFolw = new Node();
				CamporeData camporeInfoBroadcastFolw = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_FLOW),camporeInfoBroadcastFolw, nodeBroadcastFolw, portInstEMS.getPortAttr().getPortUniAttr().getBroadcast()+"", element);
				
				Node nodeMulticastBate = new Node();
				CamporeData camporeInfoMulticastBate = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_RESTRAIN),camporeInfoMulticastBate, nodeMulticastBate, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getIsMulticast()).getCodeName(), element);
				
				Node nodeMulticastFolw = new Node();
				CamporeData camporeInfoMulticastFolw = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_FLOW),camporeInfoMulticastFolw, nodeMulticastFolw, portInstEMS.getPortAttr().getPortUniAttr().getMulticast()+"", element);
				
				Node nodeUnicastBate = new Node();
				CamporeData camporeInfoUnicastBate = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_RESTRAIN),camporeInfoUnicastBate, nodeUnicastBate, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getIsUnicast()).getCodeName(), element);
				
				Node nodeUnicastFolw = new Node();
				CamporeData camporeInfoUnicastFolw = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_FLOW),camporeInfoUnicastFolw, nodeUnicastFolw, portInstEMS.getPortAttr().getPortUniAttr().getUnicast()+"", element);
				
				Node nodeVlan = new Node();
				CamporeData camporeInfoVlan = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ASSOCIATED_SETTINGS),camporeInfoVlan, nodeVlan, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getVlanRelevance()).getCodeName(), element);
				
				Node node802 = new Node();
				CamporeData camporeInfo802 = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_8021P_ASSOCIATED_SETTINGS),camporeInfo802, node802, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getEightIpRelevance()).getCodeName(), element);
				
				Node nodeSourceMac = new Node();
				CamporeData camporeInfoSourceMac = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MACADDRESS_ASSOCIATED_SETTINGS),camporeInfoSourceMac, nodeSourceMac, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getSourceMacRelevance()).getCodeName(), element);
				
				Node nodeTargetMac = new Node();
				CamporeData camporeInfoTargetMac = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_MACADDRESS_ASSOCIATED_SETTINGS),camporeInfoTargetMac, nodeTargetMac, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getDestinationMacRelevance()).getCodeName(), element);
				
				Node nodeSourceIp = new Node();
				CamporeData camporeInfoSourceIp = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IPADDRESS_ASSOCIATED_SETTINGS),camporeInfoSourceIp, nodeSourceIp, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getSourceIpRelevance()).getCodeName(), element);
				
				Node nodeTargetIp = new Node();
				CamporeData camporeInfoTargetIp = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_IPADDRESS_ASSOCIATED_SETTINGS),camporeInfoTargetIp, nodeTargetIp, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getDestinationIpRelevance()).getCodeName(), element);
				
				Node nodePw = new Node();
				CamporeData camporeInfoPw = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_ASSOCIATED_SETTINGS),camporeInfoPw, nodePw, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getPwRelevance()).getCodeName(), element);
				
				Node nodeDscp= new Node();
				CamporeData camporeInfoDscp = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_DSCP_ASSOCIATED_SETTINGS),camporeInfoDscp, nodeDscp, UiUtil.getCodeById(portInstEMS.getPortAttr().getPortUniAttr().getDscpRelevance()).getCodeName(), element);
				
				Node nodeMapping= new Node();
				CamporeData camporeInfoMapping = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_MAPPINGENABLE),camporeInfoMapping, nodeMapping, UiUtil.getCodeByValue("ENABLEDSTATUE",portInstEMS.getPortAttr().getPortUniAttr().getMappingEnable()+"").getCodeName(), element);
				
				Node nodesourceTcpPortRelevance= new Node();
				CamporeData camporeInfosourceTcpPortRelevance = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_TCP_UDP_PORT),camporeInfosourceTcpPortRelevance, nodesourceTcpPortRelevance, UiUtil.getCodeByValue("GUANLIAN",portInstEMS.getPortAttr().getPortUniAttr().getSourceTcpPortRelevance()+"").getCodeName(), element);
				
				Node nodeTargetTcpPortRelevance= new Node();
				CamporeData camporeInfoTargetTcpPortRelevance = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_TCP_UDP_PORT),camporeInfoTargetTcpPortRelevance, nodeTargetTcpPortRelevance, UiUtil.getCodeByValue("GUANLIAN",portInstEMS.getPortAttr().getPortUniAttr().getEndTcpPortRelevance()+"").getCodeName(), element);
				
				Node nodeTosRelevance= new Node();
				CamporeData camporeInfoTosRelevance = new CamporeData();
				setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_TOS_RELEVANCE),camporeInfoTosRelevance, nodeTosRelevance, UiUtil.getCodeByValue("GUANLIAN",portInstEMS.getPortAttr().getPortUniAttr().getTosRelevance()+"").getCodeName(), element);
			
				if(portInstEMS.getQosQueues() == null){
					List<QosQueue> qosQueues = new  ArrayList<QosQueue>();
					for (int i = 0; i < 8; i++) {
						QosQueue qosQueue = new QosQueue();
						qosQueue.setCos(i);
						qosQueue.setWeight(1);
						qosQueue.setQueueType("SP");
						qosQueue.setGreenLowThresh(50);
						qosQueue.setGreenHighThresh(90);
						qosQueue.setWredEnable(false);
						qosQueue.setDisCard(50);
						qosQueues.add(qosQueue);
					}
					portInstEMS.setQosQueues(qosQueues);
				}
				for (int i = 0; i < 8; i++) {
					Node node1 = new Node();
					node1.setName(ResourceUtil.srcStr(StringKeysTab.TAB_USER_PRIORITY_LEVEL)+(i+1));
					node1.setParent(element);
					
					Node priporNode = new Node();
					CamporeData camporeInfopripor = new CamporeData();
					setNode("PHB",camporeInfopripor, priporNode,UiUtil.getCodeByValue("CONRIRMPHB", portInstEMS.getQosQueues().get(i).getCos()+"").getCodeName(), node1);
					
					
					Node weightNode = new Node();
					CamporeData camporeInfoweight = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysObj.SCHEDUL),camporeInfoweight, weightNode,portInstEMS.getQosQueues().get(i).getWeight()+"", node1);
					
					
					Node queueTypeNode = new Node();
					CamporeData camporeInfoQueueType = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING),camporeInfoQueueType, queueTypeNode,portInstEMS.getQosQueues().get(i).getQueueType(), node1);
					
					
					Node greenLowThreshNode = new Node();
					CamporeData camporeInfoGreenLowThresh = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysObj.GREEN_LOW).substring(2),camporeInfoGreenLowThresh, greenLowThreshNode,portInstEMS.getQosQueues().get(i).getGreenLowThresh()+"", node1);
					
					
					Node greenHighThreshNode = new Node();
					CamporeData camporeInfoGreenHighThresh = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysObj.GREEN_HIGH).substring(2),camporeInfoGreenHighThresh, greenHighThreshNode,portInstEMS.getQosQueues().get(i).getGreenHighThresh()+"", node1);
					
					
					Node wredNode = new Node();
					CamporeData camporeInfoWred = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysObj.OPEN_WRED_YESORNO),camporeInfoWred, wredNode,portInstEMS.getQosQueues().get(i).isWredEnable()?"YES":"NO", node1);
					
					
					Node discardNode = new Node();
					CamporeData camporeInfodiscard = new CamporeData();
					setNode(ResourceUtil.srcStr(StringKeysObj.GREEN_DISCARD),camporeInfodiscard, discardNode,portInstEMS.getQosQueues().get(i).getDisCard()+"", node1);
					
					
					if(neObject != null){
						portInstNE = (PortInst) neObject;
						camporeInfopripor.setNE(UiUtil.getCodeByValue("CONRIRMPHB", portInstNE.getQosQueues().get(i).getCos()+"").getCodeName());
						camporeInfoweight.setNE(portInstNE.getQosQueues().get(i).getWeight()+"");
						camporeInfoQueueType.setNE(portInstNE.getQosQueues().get(i).getQueueType());
						camporeInfoGreenLowThresh.setNE(portInstNE.getQosQueues().get(i).getGreenLowThresh()+"");
						camporeInfoGreenHighThresh.setNE(portInstNE.getQosQueues().get(i).getGreenHighThresh()+"");
						camporeInfoWred.setNE(portInstNE.getQosQueues().get(i).isWredEnable()?"YES":"NO");
						camporeInfodiscard.setNE(portInstNE.getQosQueues().get(i).getDisCard()+"");
					}
					if(portInstNE == null || !isDifferent){//显示所有节点
						box.addElement(discardNode);
						box.addElement(wredNode);
						box.addElement(greenHighThreshNode);
						box.addElement(greenLowThreshNode);
						box.addElement(queueTypeNode);
						box.addElement(weightNode);
						box.addElement(priporNode);
					}else if(isDifferent){
						if(portInstEMS.getQosQueues().get(i).getCos() != portInstNE.getQosQueues().get(i).getCos()){
							box.addElement(priporNode);
						}
						if(portInstEMS.getQosQueues().get(i).getWeight() != portInstNE.getQosQueues().get(i).getWeight()){
							box.addElement(weightNode);
						}
						if(!portInstEMS.getQosQueues().get(i).getQueueType().equals(portInstNE.getQosQueues().get(i).getQueueType())){
							box.addElement(queueTypeNode);
						}
						if(portInstEMS.getQosQueues().get(i).getGreenLowThresh() != portInstNE.getQosQueues().get(i).getGreenLowThresh()){
							box.addElement(greenLowThreshNode);
						}
						if(portInstEMS.getQosQueues().get(i).getGreenHighThresh() != portInstNE.getQosQueues().get(i).getGreenHighThresh()){
							box.addElement(greenHighThreshNode);
						}
						if(portInstEMS.getQosQueues().get(i).isWredEnable() != portInstNE.getQosQueues().get(i).isWredEnable()){
							box.addElement(wredNode);
						}
						if(portInstEMS.getQosQueues().get(i).getDisCard() != portInstNE.getQosQueues().get(i).getDisCard()){
							box.addElement(discardNode);
						}
					}
					box.addElement(node1);
				}
				
				
				if(portInstNE != null){
					camporeInfoEnable.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getIsEnabled_code()+"").getCodeName());
					camporeInfoPortType.setNE(portInstNE.getPortType());
					camporeInfoMTU.setNE(portInstNE.getPortAttr().getMaxFrameSize() + "");
					
					camporeInfoFlow.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getFluidControl()).getCodeName());
					camporeInfoOutSpeed.setNE(portInstNE.getPortAttr().getExitLimit()==null?"1000000":portInstNE.getPortAttr().getExitLimit());
					camporeInfoModel.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getWorkModel()).getCodeName());
					camporeInfoQinq.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getIsEnabled_QinQ()+"").getCodeName());
					camporeInfoisEnabledLaser.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getIsEnabledLaser()+"").getCodeName());
					camporeInfoAlarm.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getIsEnabledAlarmReversal()+"").getCodeName());
					camporeInfoLoop.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getServicePort()+"").getCodeName());
					camporeInfoBroadcastBate.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getIsBroadcast()).getCodeName());
					camporeInfoBroadcastFolw.setNE(portInstNE.getPortAttr().getPortUniAttr().getBroadcast());
					camporeInfoMulticastBate.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getIsMulticast()).getCodeName());
					camporeInfoMulticastFolw.setNE(portInstNE.getPortAttr().getPortUniAttr().getMulticast());
					camporeInfoUnicastBate.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getIsUnicast()).getCodeName());
					camporeInfoUnicastFolw.setNE(portInstNE.getPortAttr().getPortUniAttr().getUnicast());
					camporeInfoVlan.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getVlanRelevance()).getCodeName());
					camporeInfo802.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getEightIpRelevance()).getCodeName());
					camporeInfoSourceMac.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getSourceMacRelevance()).getCodeName());
					
					camporeInfoTargetMac.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getDestinationMacRelevance()).getCodeName());
					camporeInfoSourceIp.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getSourceIpRelevance()).getCodeName());
					camporeInfoTargetIp.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getDestinationIpRelevance()).getCodeName());
					camporeInfoPw.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getPwRelevance()).getCodeName());
					camporeInfoDscp.setNE(UiUtil.getCodeById(portInstNE.getPortAttr().getPortUniAttr().getDscpRelevance()).getCodeName());
					camporeInfoMapping.setNE(UiUtil.getCodeByValue("ENABLEDSTATUE",portInstNE.getPortAttr().getPortUniAttr().getMappingEnable()+"").getCodeName());
					camporeInfosourceTcpPortRelevance.setNE(UiUtil.getCodeByValue("GUANLIAN",portInstNE.getPortAttr().getPortUniAttr().getSourceTcpPortRelevance()+"").getCodeName());
					camporeInfoTargetTcpPortRelevance.setNE(UiUtil.getCodeByValue("GUANLIAN",portInstNE.getPortAttr().getPortUniAttr().getEndTcpPortRelevance()+"").getCodeName());
					camporeInfoTosRelevance.setNE(UiUtil.getCodeByValue("GUANLIAN",portInstNE.getPortAttr().getPortUniAttr().getTosRelevance()+"").getCodeName());
				}
				
				if(portInstNE == null || !isDifferent){//显示所有节点
					
					box.addElement(nodeEnable);
					box.addElement(nodePortType);
					box.addElement(nodeMTU);
					box.addElement(nodeFlow);
					box.addElement(nodeOutSpeed);
					box.addElement(nodeModel);
					box.addElement(nodeQinq);
					box.addElement(nodeisEnabledLaser);
					box.addElement(nodeAlarm);
					box.addElement(nodeLoop);
					box.addElement(nodeBroadcastBate);
					box.addElement(nodeBroadcastFolw);
					box.addElement(nodeMulticastBate);
					box.addElement(nodeMulticastFolw);
					box.addElement(nodeUnicastBate);
					box.addElement(nodeUnicastFolw);
					box.addElement(nodeVlan);
					box.addElement(node802);
					box.addElement(nodeSourceMac);
					box.addElement(nodeTargetMac);
					box.addElement(nodeSourceIp);
					box.addElement(nodeTargetIp);
					box.addElement(nodePw);
					box.addElement(nodeDscp);
					box.addElement(nodeMapping);
					box.addElement(nodesourceTcpPortRelevance);
					box.addElement(nodeTargetTcpPortRelevance);
					box.addElement(nodeTosRelevance);
				}else if(isDifferent){//只显示不相同的节点
					
					if(portInstEMS.getIsEnabled_code() != portInstNE.getIsEnabled_code()){
						box.addElement(nodeEnable);
					}
					if(!portInstEMS.getPortType().equals(portInstNE.getPortType())){
						box.addElement(nodePortType);
					}
					if(Integer.parseInt(portInstEMS.getPortAttr().getMaxFrameSize()) != Integer.parseInt(portInstNE.getPortAttr().getMaxFrameSize())){
						box.addElement(nodeMTU);
					}
					if(portInstEMS.getPortAttr().getFluidControl() != portInstNE.getPortAttr().getFluidControl()){
						box.addElement(nodeFlow);
					}
					if(!(exitLimit).equals(portInstNE.getPortAttr().getExitLimit()+"")){
						box.addElement(nodeOutSpeed);
					}
					if(portInstEMS.getPortAttr().getWorkModel() != portInstNE.getPortAttr().getWorkModel()){
						box.addElement(nodeModel);
					}
					if(portInstEMS.getIsEnabled_QinQ() != portInstNE.getIsEnabled_QinQ()){
						box.addElement(nodeQinq);
					}
					if(portInstEMS.getIsEnabledLaser() != portInstNE.getIsEnabledLaser()){
						box.addElement(nodeisEnabledLaser);
					}
					if(portInstEMS.getIsEnabledAlarmReversal() != portInstNE.getIsEnabledAlarmReversal()){
						box.addElement(nodeAlarm);
					}
					if(portInstEMS.getServicePort() != portInstNE.getServicePort()){
						box.addElement(nodeLoop);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getIsBroadcast() != portInstNE.getPortAttr().getPortUniAttr().getIsBroadcast()){
						box.addElement(nodeBroadcastBate);
					}
					if(!portInstEMS.getPortAttr().getPortUniAttr().getBroadcast().equals(portInstNE.getPortAttr().getPortUniAttr().getBroadcast())){
						box.addElement(nodeBroadcastFolw);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getIsMulticast() != portInstNE.getPortAttr().getPortUniAttr().getIsMulticast()){
						box.addElement(nodeMulticastBate);
					}
					if(!portInstEMS.getPortAttr().getPortUniAttr().getMulticast().equals(portInstNE.getPortAttr().getPortUniAttr().getMulticast())){
						box.addElement(nodeMulticastFolw);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getIsUnicast() != portInstNE.getPortAttr().getPortUniAttr().getIsUnicast()){
						box.addElement(nodeUnicastBate);
					}
					if(!portInstEMS.getPortAttr().getPortUniAttr().getUnicast().equals(portInstNE.getPortAttr().getPortUniAttr().getUnicast())){
						box.addElement(nodeUnicastFolw);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getEightIpRelevance() != portInstNE.getPortAttr().getPortUniAttr().getEightIpRelevance()){
						box.addElement(node802);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getVlanRelevance() != portInstNE.getPortAttr().getPortUniAttr().getVlanRelevance()){
						box.addElement(nodeVlan);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getSourceMacRelevance() != portInstNE.getPortAttr().getPortUniAttr().getSourceMacRelevance()){
						box.addElement(nodeSourceMac);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getDestinationMacRelevance() != portInstNE.getPortAttr().getPortUniAttr().getDestinationMacRelevance()){
						box.addElement(nodeTargetMac);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getSourceIpRelevance() != portInstNE.getPortAttr().getPortUniAttr().getSourceIpRelevance()){
						box.addElement(nodeSourceIp);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getDestinationIpRelevance() != portInstNE.getPortAttr().getPortUniAttr().getDestinationIpRelevance()){
						box.addElement(nodeTargetIp);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getSourceTcpPortRelevance() != portInstNE.getPortAttr().getPortUniAttr().getSourceTcpPortRelevance()){
						box.addElement(nodesourceTcpPortRelevance);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getEndTcpPortRelevance() != portInstNE.getPortAttr().getPortUniAttr().getEndTcpPortRelevance()){
						box.addElement(nodeTargetTcpPortRelevance);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getPwRelevance() != portInstNE.getPortAttr().getPortUniAttr().getPwRelevance()){
						box.addElement(nodePw);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getDscpRelevance() != portInstNE.getPortAttr().getPortUniAttr().getDscpRelevance()){
						box.addElement(nodeDscp);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getMappingEnable() != portInstNE.getPortAttr().getPortUniAttr().getMappingEnable()){
						box.addElement(nodeMapping);
					}
					if(portInstEMS.getPortAttr().getPortUniAttr().getTosRelevance() != portInstNE.getPortAttr().getPortUniAttr().getTosRelevance()){
						box.addElement(nodeTosRelevance);
					}
				}
				
			}
			element.setUserObject(emsobject);
			box.addElement(element);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	private void setNode(String title,CamporeData camporeInfoMTU,Node nodeMTU,String value,Node element){
		nodeMTU.setName(title);
		camporeInfoMTU.setEMS(value);
		nodeMTU.setParent(element);
		nodeMTU.setBusinessObject(camporeInfoMTU);
	}
	
	// load children
	public void load(Element element) {
	}

	@Override
	public boolean isLeaf(Element arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isLoaded(Element arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public TDataBox getBox() {
		return box;
	}

	public void setBox(TDataBox box) {
		this.box = box;
	}
}