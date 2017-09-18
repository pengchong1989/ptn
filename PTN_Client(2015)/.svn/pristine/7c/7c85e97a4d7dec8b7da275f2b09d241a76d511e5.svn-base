package com.nms.ui.ptn.portlag.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;

public class RelevanceInstallDialog extends PtnDialog{
	private static final long serialVersionUID = -4111460164627969704L;
	private JLabel vlanRelevanceLabel;//VLAN关联设置
	private JComboBox vlanRelevanceComboBox;
	private JLabel ipLabel;//802.1P关联设置:0/1=不关联/关联
	private JComboBox ipComboBox;
	private JLabel sourceMACLabel;//源MAC地址关联设置:0/1=不关联/关联
	private JComboBox sourceMACComboBox;
	private JLabel targetMACLabel;//目的MAC地址关联设置:0/1=不关联/关联
	private JComboBox targetMACComboBox;
	private JLabel sourceIPLabel;//源IP地址关联设置:0/1=不关联/关联
	private JComboBox sourceIPComboBox;
	private JLabel targetIPLabel;//目的IP地址关联设置:0/1=不关联/关联
	private JComboBox targetIPComboBox;
	private JLabel pwLabel;//pw关联设置:0/1=不关联/关联
	private JComboBox pwComboBox;
	private JLabel dscpLabel;//DSCP关联设置:0/1=不关联/关联
	private JComboBox dscpComboBox;
	
	private JButton confirm;
	private JButton cancel;
	private JPanel buttonPanel;
	private JPanel contentPanel;
	
	public RelevanceInstallDialog(PortLagInfo portLagInfo) {
		this.setModal(true);
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_ASSOCIATED_SETTINGS));
		// 初始化控件
		initComponent();
		// 界面布局
		setQLDialogLayout();
		init(portLagInfo);
	}
	
	
	
	private void initComponent() {
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		buttonPanel = new JPanel();
		contentPanel = new JPanel();
		vlanRelevanceLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ASSOCIATED_SETTINGS));
		vlanRelevanceComboBox = new JComboBox();
		ipLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_8021P_ASSOCIATED_SETTINGS));
		ipComboBox = new JComboBox();
		sourceMACLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MACADDRESS_ASSOCIATED_SETTINGS));
		sourceMACComboBox = new JComboBox();
		targetMACLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_MACADDRESS_ASSOCIATED_SETTINGS));
		targetMACComboBox = new JComboBox();
		sourceIPLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IPADDRESS_ASSOCIATED_SETTINGS));
		sourceIPComboBox = new JComboBox();
		targetIPLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TARGET_IPADDRESS_ASSOCIATED_SETTINGS));
		targetIPComboBox = new JComboBox();
		pwLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_ASSOCIATED_SETTINGS));
		pwComboBox = new JComboBox();
		dscpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DSCP_ASSOCIATED_SETTINGS));
		dscpComboBox = new JComboBox();
	 
		initialDataBoxs();
	}

	private void initialDataBoxs() {
		try {
			super.getComboBoxDataUtil().comboBoxData(vlanRelevanceComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(ipComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(sourceMACComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(targetMACComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(sourceIPComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(targetIPComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(pwComboBox, "GUANLIAN");
			super.getComboBoxDataUtil().comboBoxData(dscpComboBox, "GUANLIAN");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setQLDialogLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {120, 80, 120, 80};
		layout.columnWeights = new double[] {0, 0, 0, 0};
		layout.rowHeights = new int[] { 30, 30, 30, 30, 30 ,30};
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0 };
		
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(layout);
	
		addComponent(contentPanel, vlanRelevanceLabel, 0, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 60, 15, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, vlanRelevanceComboBox, 1, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 10, 15, 20), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, ipLabel, 0, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 60, 15, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, ipComboBox, 1, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 20), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, sourceMACLabel, 0, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 60, 15, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, sourceMACComboBox, 1, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 20), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, targetMACLabel, 0, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 60, 15, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, targetMACComboBox, 1, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 20), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, sourceIPLabel, 2, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 50, 15, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, sourceIPComboBox, 3, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 10, 15, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, targetIPLabel, 2, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 50, 15, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, targetIPComboBox, 3, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, pwLabel, 2, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 50, 15, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, pwComboBox, 3, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, dscpLabel, 2, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 50, 15, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, dscpComboBox, 3, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(15, 10, 15, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, buttonPanel, 1, 5, 1.0, 0.1, 4, 1, 
				GridBagConstraints.BOTH, new Insets(15, 5, 10, 100), GridBagConstraints.NORTHWEST, c);
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
		
		this.add(contentPanel);
		
	}

	/**
	 * 初始化界面的值
	 * 
	 * @param values
	 */
	public void init(PortLagInfo portLagInfo) {
		try{
			super.getComboBoxDataUtil().comboBoxSelectByValue(vlanRelevanceComboBox, portLagInfo.getVlanRelating()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(ipComboBox,portLagInfo.getRelatingSet()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(sourceMACComboBox, portLagInfo.getFountainMAC()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(targetMACComboBox, portLagInfo.getAimMAC()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(sourceIPComboBox, portLagInfo.getFountainIP()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(targetIPComboBox, portLagInfo.getAimIP()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(pwComboBox, portLagInfo.getPwSet()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(dscpComboBox, portLagInfo.getIpDSCPSet()+"");
			//验证ac是否关联vlan等关联规则
			if(portLagInfo != null && portLagInfo.getId() > 0){
				boolean flag = this.isJoinRelevance(portLagInfo);
				//验证ac是否关联vlan
				if(portLagInfo.getVlanRelating() == 1)
					this.vlanRelevanceComboBox.setEnabled(flag);
				//验证ac是否关联pri
				if(portLagInfo.getRelatingSet() == 1)
					this.ipComboBox.setEnabled(flag);
				//验证ac是否关联源mac
				if(portLagInfo.getFountainMAC() == 1)
					this.sourceMACComboBox.setEnabled(flag);
				//验证ac是否关联目的mac
				if(portLagInfo.getAimMAC() == 1)
					this.targetMACComboBox.setEnabled(flag);
				//验证ac是否关联源ip
				if(portLagInfo.getFountainIP() == 1)
					this.sourceIPComboBox.setEnabled(flag);
				//验证ac是否关联目的ip
				if(portLagInfo.getAimIP() == 1)
					this.targetIPComboBox.setEnabled(flag);
			}
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 验证ac是否有关联规则和ac里面包含多条流
	 * @param portLagInfo 
	 */
	private boolean isJoinRelevance(PortLagInfo portLagInfo) {
		AcPortInfoService_MB acInfoService = null;
		boolean flag = true;
		try {
			AcPortInfo ac = new AcPortInfo();
			ac.setSiteId(ConstantUtil.siteId);
			ac.setLagId(portLagInfo.getId());
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
	
	public void comboBoxSelect(JComboBox jComboBox, String selectId) {
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			if (((ControlKeyValue) jComboBox.getItemAt(i)).getId().equals(selectId)) {
				jComboBox.setSelectedIndex(i);
				return;
			}
		}
	}
	
	public void get(PortLagInfo portLagInfo){
		try {
			ControlKeyValue controlKeyValue = null;
			controlKeyValue = (ControlKeyValue)(vlanRelevanceComboBox.getSelectedItem());
			portLagInfo.setVlanRelating(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(ipComboBox.getSelectedItem());
			portLagInfo.setRelatingSet(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(sourceMACComboBox.getSelectedItem());
			portLagInfo.setFountainMAC(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(targetMACComboBox.getSelectedItem());
			portLagInfo.setAimMAC(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(sourceIPComboBox.getSelectedItem());
			portLagInfo.setFountainIP(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(targetIPComboBox.getSelectedItem());
			portLagInfo.setAimIP(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(pwComboBox.getSelectedItem());
			portLagInfo.setPwSet(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
			controlKeyValue = (ControlKeyValue)(dscpComboBox.getSelectedItem());
			portLagInfo.setIpDSCPSet(Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue()));
//			sb.append(((ControlKeyValue)(vlanRelevanceComboBox.getSelectedItem())).getId()).append(",");
//			sb.append(((ControlKeyValue)(ipComboBox.getSelectedItem())).getId()).append(",");	
//			sb.append(((ControlKeyValue)(sourceMACComboBox.getSelectedItem())).getId()).append(",");			
//			sb.append(((ControlKeyValue)(targetMACComboBox.getSelectedItem())).getId()).append(",");		
//			sb.append(((ControlKeyValue)(sourceIPComboBox.getSelectedItem())).getId()).append(",");
//			sb.append(((ControlKeyValue)(targetIPComboBox.getSelectedItem())).getId()).append(",");			
//			sb.append(((ControlKeyValue)(pwComboBox.getSelectedItem())).getId()).append(",");		
//			sb.append(((ControlKeyValue)(dscpComboBox.getSelectedItem())).getId()).append(",");

	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
	}
	
	public JButton getConfirm() {
		return confirm;
	}
	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}
	public JButton getCancel() {
		return cancel;
	}
	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
}
