package com.nms.ui.ptn.ne.eth.view.dialog.wh;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortUniAttr;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 端口
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortAttrWHDialog   
* 类描述：   端口高级属性武汉界面
* 创建人：kk   
* 创建时间：2013-7-15 上午11:52:17   
* 修改人：kk   
* 修改时间：2013-7-15 上午11:52:17   
* 修改备注：   
* @version    
*
 */
public class PortAttrWHDialog extends PtnPanel {
	private static final long serialVersionUID = 6484373663809859582L;
	
	private PortInst portInst = null;
	
	/**
	 * 创建一个新的实例
	 */
	public PortAttrWHDialog(){
		super();
		try {
			this.initComponents();
			this.setLayout();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 创建一个新的实例
	 * @param portInst
	 * 			端口
	 */
	public PortAttrWHDialog(PortInst portInst){
		super();
		try {
			this.portInst = portInst;
			this.initComponents();
			this.setLayout();
			this.initData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化组件
	 * @throws Exception
	 */
	private void initComponents()throws Exception {
		this.labBroadcastBate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_RESTRAIN));
		this.cmbBroadcastBate = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbBroadcastBate, "VCTRAFFICPOLICING");
		this.labBroadcastFlux = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_FLOW));
		this.txtBroadcastFlux =new PtnSpinner(1000000,0,64);
		this.labGroupBroadcastBate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_RESTRAIN));
		this.cmbGroupBroadcastBate = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbGroupBroadcastBate, "VCTRAFFICPOLICING");
		this.labGroupBroadcastFlux = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_FLOW));
		this.txtGroupBroadcastFlux = new PtnSpinner(1000000,0,64);
		this.labFloodBate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_RESTRAIN));
		this.cmbFloodBate = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbFloodBate, "VCTRAFFICPOLICING");
		this.labFloodFlux = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_FLOW));
		this.txtFloodFlux = new PtnSpinner(1000000,0,64);
		this.labVlan = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ASSOCIATED_SETTINGS));
		this.cmbVlan = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbVlan, "GUANLIAN");
		this.labIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_8021P_ASSOCIATED_SETTINGS));
		this.cmbIp = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbIp, "GUANLIAN");
		this.labSourMac = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MACADDRESS_ASSOCIATED_SETTINGS));
		this.cmbSourMac = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbSourMac, "GUANLIAN");
		this.labDistMac = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_MACADDRESS_ASSOCIATED_SETTINGS));
		this.cmbDistMac = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbDistMac, "GUANLIAN");
		this.labSourIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IPADDRESS_ASSOCIATED_SETTINGS));
		this.cmbSourIp = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbSourIp, "GUANLIAN");
		this.labDistIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_IPADDRESS_ASSOCIATED_SETTINGS));
		this.cmbDistIp = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbDistIp, "GUANLIAN");
		this.labPw = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_ASSOCIATED_SETTINGS));
		this.cmbPw = new JComboBox();
		this.cmbPw.setEnabled(false);
		super.getComboBoxDataUtil().comboBoxData(this.cmbPw, "GUANLIAN");
		this.labDscp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DSCP_ASSOCIATED_SETTINGS));
		this.cmbDscp = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbDscp, "GUANLIAN");
		mappingEnable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAPPINGENABLE));
		mappingEnablejComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.mappingEnablejComboBox, "ENABLEDSTATUE");
		this.labSourceTcpPortRelevance = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_TCP_UDP_PORT));
		this.cmbSourceTcpPortRelevance = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbSourceTcpPortRelevance, "GUANLIAN");
		this.labEndTcpPortRelevance = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_TCP_UDP_PORT));
		this.cmbEndTcpPortRelevance = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbEndTcpPortRelevance, "GUANLIAN");
		this.labToSRelevance = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TOS_RELEVANCE));
		this.cmbToSRelevance = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbToSRelevance, "GUANLIAN");
	}

	/**
	 * 设置布局
	 */
	private void setLayout()throws Exception {
		GridBagLayout componentLayout = new GridBagLayout();//网格布局
		componentLayout.columnWidths = new int[]{20,40,80,30,40,80,30,40,80,20};
		componentLayout.columnWeights = new double[]{0,0,0,0,0,0,0};
		componentLayout.rowHeights = new int[]{15,40,40,40,40,40,40,40,40};
		componentLayout.rowWeights = new double[]{0,0,0,0,0,0,0.2};
		this.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.anchor = GridBagConstraints.CENTER;
		gridCon.insets = new Insets(5,5,5,5);
		
		//第一行
		gridCon.gridx = 1;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.labBroadcastBate, gridCon);
		this.add(this.labBroadcastBate);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.cmbBroadcastBate, gridCon);
		this.add(this.cmbBroadcastBate);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labBroadcastFlux, gridCon);
		this.add(this.labBroadcastFlux);
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.txtBroadcastFlux, gridCon);
		this.add(this.txtBroadcastFlux);
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.labGroupBroadcastBate, gridCon);
		this.add(this.labGroupBroadcastBate);
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.cmbGroupBroadcastBate, gridCon);
		this.add(this.cmbGroupBroadcastBate);
		
		//第二行
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.labGroupBroadcastFlux, gridCon);
		this.add(this.labGroupBroadcastFlux);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.txtGroupBroadcastFlux, gridCon);
		this.add(this.txtGroupBroadcastFlux);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labFloodBate, gridCon);
		this.add(this.labFloodBate);
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.cmbFloodBate, gridCon);
		this.add(this.cmbFloodBate);
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.labFloodFlux, gridCon);
		this.add(this.labFloodFlux);
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.txtFloodFlux, gridCon);
		this.add(this.txtFloodFlux);
		
		//第三行
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.labVlan, gridCon);
		this.add(this.labVlan);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.cmbVlan, gridCon);
		this.add(this.cmbVlan);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labIp, gridCon);
		this.add(this.labIp);
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.cmbIp, gridCon);
		this.add(this.cmbIp);
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.labSourMac, gridCon);
		this.add(this.labSourMac );
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.cmbSourMac, gridCon);
		this.add(this.cmbSourMac);
		//第四行
		gridCon.gridx = 1;
		gridCon.gridy = 4;
		componentLayout.setConstraints(this.labDistMac, gridCon);
		this.add(this.labDistMac);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.cmbDistMac, gridCon);
		this.add(this.cmbDistMac);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labSourIp, gridCon);
		this.add(this.labSourIp);
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.cmbSourIp, gridCon);
		this.add(this.cmbSourIp);
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.labDistIp, gridCon);
		this.add(this.labDistIp);
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.cmbDistIp, gridCon);
		this.add(this.cmbDistIp);
		
		//第五行
		gridCon.gridx = 1;
		gridCon.gridy = 5;
		componentLayout.setConstraints(this.labPw, gridCon);
		this.add(this.labPw);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.cmbPw, gridCon);
		this.add(this.cmbPw);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labDscp , gridCon);
		this.add(this.labDscp );
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.cmbDscp , gridCon);
		this.add(this.cmbDscp );
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.mappingEnable , gridCon);
		this.add(this.mappingEnable );
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.mappingEnablejComboBox , gridCon);
		this.add(this.mappingEnablejComboBox );
		
		//第六行
		gridCon.gridx = 1;
		gridCon.gridy = 6;
		componentLayout.setConstraints(this.labSourceTcpPortRelevance, gridCon);
		this.add(this.labSourceTcpPortRelevance);
		gridCon.gridx = 2;
		componentLayout.setConstraints(this.cmbSourceTcpPortRelevance, gridCon);
		this.add(this.cmbSourceTcpPortRelevance);
		
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.labEndTcpPortRelevance, gridCon);
		this.add(this.labEndTcpPortRelevance);
		gridCon.gridx = 5;
		componentLayout.setConstraints(this.cmbEndTcpPortRelevance, gridCon);
		this.add(this.cmbEndTcpPortRelevance);
		
		gridCon.gridx = 7;
		componentLayout.setConstraints(this.labToSRelevance, gridCon);
		this.add(this.labToSRelevance);
		gridCon.gridx = 8;
		componentLayout.setConstraints(this.cmbToSRelevance, gridCon);
		this.add(this.cmbToSRelevance);
	}

	/**
	 * 获取端口对象
	 * @param portInst
	 * 			端口对象
	 * @throws Exception
	 */
	public void getPortInst(PortInst portinst)throws Exception {
		if(null==portinst){
			throw new Exception("portInst is null");
		}
		
		PortUniAttr uniAttr = portinst.getPortAttr().getPortUniAttr();
		uniAttr.setBroadcast(this.txtBroadcastFlux.getTxtData());
		uniAttr.setMulticast(this.txtGroupBroadcastFlux.getTxtData());
		uniAttr.setUnicast(this.txtFloodFlux.getTxtData());
		ControlKeyValue key_broad = (ControlKeyValue) this.cmbBroadcastBate.getSelectedItem();
		uniAttr.setIsBroadcast(Integer.parseInt(key_broad.getId()));
		ControlKeyValue key_group = (ControlKeyValue) this.cmbGroupBroadcastBate.getSelectedItem();
		uniAttr.setIsMulticast(Integer.parseInt(key_group.getId()));
		ControlKeyValue key_flood = (ControlKeyValue) this.cmbFloodBate.getSelectedItem();
		uniAttr.setIsUnicast(Integer.valueOf(key_flood.getId()));
		ControlKeyValue key_lan = (ControlKeyValue) this.cmbVlan.getSelectedItem();
		uniAttr.setVlanRelevance(Integer.parseInt(key_lan.getId()));
		ControlKeyValue key_802 = (ControlKeyValue) this.cmbIp.getSelectedItem();
		uniAttr.setEightIpRelevance(Integer.parseInt(key_802.getId()));
		ControlKeyValue key_sMac = (ControlKeyValue) this.cmbSourMac.getSelectedItem();
		uniAttr.setSourceMacRelevance(Integer.parseInt(key_sMac.getId()));
		ControlKeyValue key_dMac = (ControlKeyValue) this.cmbDistMac.getSelectedItem();
		uniAttr.setDestinationMacRelevance(Integer.parseInt(key_dMac.getId()));
		ControlKeyValue key_sIp = (ControlKeyValue) this.cmbSourIp.getSelectedItem();
		uniAttr.setSourceIpRelevance(Integer.parseInt(key_sIp.getId()));
		ControlKeyValue key_dIp = (ControlKeyValue) this.cmbDistIp.getSelectedItem();
		uniAttr.setDestinationIpRelevance(Integer.parseInt(key_dIp.getId()));
		ControlKeyValue key_pw = (ControlKeyValue) this.cmbPw.getSelectedItem();
		uniAttr.setPwRelevance(Integer.parseInt(key_pw.getId()));
		ControlKeyValue key_dscp = (ControlKeyValue) this.cmbDscp.getSelectedItem();
		uniAttr.setDscpRelevance(Integer.parseInt(key_dscp.getId()));
		ControlKeyValue key_mappingEnable = (ControlKeyValue) this.mappingEnablejComboBox.getSelectedItem();
		uniAttr.setMappingEnable(Integer.parseInt(((Code)key_mappingEnable.getObject()).getCodeValue()));
		ControlKeyValue key_SourceTcp = (ControlKeyValue) this.cmbSourceTcpPortRelevance.getSelectedItem();
		uniAttr.setSourceTcpPortRelevance(Integer.parseInt(((Code)key_SourceTcp.getObject()).getCodeValue()));
		ControlKeyValue key_EndTcp = (ControlKeyValue) this.cmbEndTcpPortRelevance.getSelectedItem();
		uniAttr.setEndTcpPortRelevance(Integer.parseInt(((Code)key_EndTcp.getObject()).getCodeValue()));
		ControlKeyValue key_ToS = (ControlKeyValue) this.cmbToSRelevance.getSelectedItem();
		uniAttr.setTosRelevance(Integer.parseInt(((Code)key_ToS.getObject()).getCodeValue()));
	}
	
	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initData()throws Exception {
		PortUniAttr uniAttr = portInst.getPortAttr().getPortUniAttr();
		if("".equals(uniAttr.getBroadcast()) || null==uniAttr.getBroadcast()){
			txtBroadcastFlux.getTxt().setText("1000000");
		}else{
			txtBroadcastFlux.getTxt().setText(uniAttr.getBroadcast());
		}
		
		if("".equals(uniAttr.getMulticast()) || null==uniAttr.getMulticast()){
			txtGroupBroadcastFlux.getTxt().setText("1000000");
		}else{
			txtGroupBroadcastFlux.getTxt().setText(uniAttr.getMulticast());
		}
		
		if("".equals(uniAttr.getUnicast()) || null==uniAttr.getUnicast()){
			txtFloodFlux.getTxt().setText("1000000");
		}else{
			txtFloodFlux.getTxt().setText(uniAttr.getUnicast());
		}
		
		super.getComboBoxDataUtil().comboBoxSelect(cmbBroadcastBate,uniAttr.getIsBroadcast()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbGroupBroadcastBate,uniAttr.getIsMulticast()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbFloodBate,uniAttr.getIsUnicast()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbVlan,uniAttr.getVlanRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbIp,uniAttr.getEightIpRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbSourMac,uniAttr.getSourceMacRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbDistMac,uniAttr.getDestinationMacRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbSourIp,uniAttr.getSourceIpRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbDistIp,uniAttr.getDestinationIpRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbPw,uniAttr.getPwRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbDscp,uniAttr.getDscpRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(mappingEnablejComboBox,uniAttr.getMappingEnable()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(cmbSourceTcpPortRelevance, uniAttr.getSourceTcpPortRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(cmbEndTcpPortRelevance, uniAttr.getEndTcpPortRelevance()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(cmbToSRelevance, uniAttr.getTosRelevance()+"");
		//验证ac是否关联vlan等关联规则
		int vlanRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getVlanRelevance()).getCodeValue());
		int eightIpRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getEightIpRelevance()).getCodeValue());
		int sourMacRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getSourceMacRelevance()).getCodeValue());
		int endMacRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getDestinationMacRelevance()).getCodeValue());
		int sourIPRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getSourceIpRelevance()).getCodeValue());
		int endIPRelevance = Integer.parseInt(UiUtil.getCodeById(uniAttr.getDestinationIpRelevance()).getCodeValue());
		if(portInst != null){
			boolean flag = this.isJoinRelevance();
			//验证ac是否关联vlan
			if(vlanRelevance == 1)
				this.cmbVlan.setEnabled(flag);
			//验证ac是否关联pri
			if(eightIpRelevance == 1)
				this.cmbIp.setEnabled(flag);
			//验证ac是否关联源mac
			if(sourMacRelevance == 1)
				this.cmbSourMac.setEnabled(flag);
			//验证ac是否关联目的mac
			if(endMacRelevance == 1)
				this.cmbDistMac.setEnabled(flag);
			//验证ac是否关联源ip
			if(sourIPRelevance == 1)
				this.cmbSourIp.setEnabled(flag);
			//验证ac是否关联目的ip
			if(endIPRelevance == 1)
				this.cmbDistIp.setEnabled(flag);
		}
	}
	
	/**
	 * 验证ac是否有关联规则和ac里面包含多条流
	 * @return
	 */
	private boolean isJoinRelevance() {
		AcPortInfoService_MB acInfoService = null;
		boolean flag = true;
		try {
			AcPortInfo ac = new AcPortInfo();
			ac.setPortId(portInst.getPortId());
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			List<AcPortInfo> acList = acInfoService.queryByAcPortInfo(ac);
			//如果有多个ac，则不可更改
			if(acList.size() > 1){
				return false;
			}
			////如果有一个ac，但是有多条流，则不可更改
			if(acList.size() > 0 && acList.get(0).getBufferList().size() > 1){
				return false;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return flag;
	}
	
	/**
	 * 仅用于显示，不能更改数据
	 * @throws Exception
	 */
	public void lockHighAttr()throws Exception {
		try {
			cmbBroadcastBate.setEnabled(false);
			txtBroadcastFlux.setEnabled(false);
			cmbGroupBroadcastBate.setEnabled(false);
			txtGroupBroadcastFlux.setEnabled(false);
			cmbFloodBate.setEnabled(false);
			txtFloodFlux.setEnabled(false);
			cmbVlan.setEnabled(false);
			cmbIp.setEnabled(false);
			cmbSourMac.setEnabled(false);
			cmbDistMac.setEnabled(false);
			cmbSourIp.setEnabled(false);
			cmbDistIp.setEnabled(false);
			cmbPw.setEnabled(false);
			cmbDscp.setEnabled(false);
			mappingEnablejComboBox.setEnabled(false);
			cmbSourceTcpPortRelevance.setEnabled(false);
			cmbEndTcpPortRelevance.setEnabled(false);
			cmbToSRelevance.setEnabled(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private JLabel labBroadcastBate;//广播包抑制开关 0=false 1=true
	private JComboBox cmbBroadcastBate;
	private JLabel labBroadcastFlux;//广播包流量
	private PtnSpinner txtBroadcastFlux;
	private JLabel labGroupBroadcastBate;//组播包抑制开关 0=false 1=true
	private JComboBox cmbGroupBroadcastBate;
	private JLabel labGroupBroadcastFlux;//组播包流量
	private PtnSpinner txtGroupBroadcastFlux;
	private JLabel labFloodBate;//洪泛包抑制开关 0=false 1=true
	private JComboBox cmbFloodBate;
	private JLabel labFloodFlux;//洪泛包流量
	private PtnSpinner txtFloodFlux;
	private JLabel labVlan;//VLAN关联设置 0=false 1=true
	private JComboBox cmbVlan;
	private JLabel labIp;//802.iP关联设置 0=false 1=true
	private JComboBox cmbIp;
	private JLabel labSourMac;// 源MAC地址关联设置 0=false 1=true
	private JComboBox cmbSourMac;
	private JLabel labDistMac;// 目的MAC地址关联设置 0=false 1=true
	private JComboBox cmbDistMac;
	private JLabel labSourIp;// 源IP地址关联设置 0=false 1=true
	private JComboBox cmbSourIp;
	private JLabel labDistIp;//目的IP地址关联设置 0=false 1=true 
	private JComboBox cmbDistIp;
	private JLabel labPw;// pw关联设置 0=false 1=true
	private JComboBox cmbPw;
	private JLabel labDscp;// dscp关联设置 0=false 1=true
	private JComboBox cmbDscp;
	private JLabel mappingEnable;//基于PRI到PHB映射
	private JComboBox mappingEnablejComboBox;
	private JLabel labSourceTcpPortRelevance;//源tcp/udp端口号关联设置
	private JComboBox cmbSourceTcpPortRelevance;
	private JLabel labEndTcpPortRelevance;//宿tcp/udp端口号关联设置
	private JComboBox cmbEndTcpPortRelevance;
	private JLabel labToSRelevance;//tos关联设置
	private JComboBox cmbToSRelevance;
}
