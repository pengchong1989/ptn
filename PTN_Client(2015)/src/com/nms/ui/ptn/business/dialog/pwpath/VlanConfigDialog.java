package com.nms.ui.ptn.business.dialog.pwpath;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class VlanConfigDialog extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6042855944919242001L;
	private Tunnel tunnel;
	private JLabel aVlanEnable;//a外层VLAN使能
	private JLabel aOutVlanValue;//a外层vlan值
	private JLabel atp_id;//a外层TP_ID
	private JComboBox aVlanEnableComboBox;//
	private PtnTextField aOutVlanValueField;//
	private JComboBox atp_idComboBox;//
	private JPanel apanel;
	
	private JLabel zVlanEnable;//z外层VLAN使能
	private JLabel zOutVlanValue;//z外层vlan值
	private JLabel ztp_id;//z外层TP_ID
	private JComboBox zVlanEnableComboBox;//
	private PtnTextField zOutVlanValueField;//
	private JComboBox ztp_idComboBox;//
	private JPanel zpanel;
	
	private JButton btnSave;//保存按钮
	private JButton btnCancel;//取消按钮
	private JPanel panelBtn;
	private JLabel lblMessage;
	public VlanConfigDialog(Tunnel tunnelInfo, String titile){
		super.setTitle(titile);
		this.tunnel = tunnelInfo;
		this.initComponents();
		this.setLayout();
		this.initData();
		this.addListeners();
		UiUtil.showWindow(this, 700, 350);
	}
	private void initComponents() {
		try {
			this.lblMessage = new JLabel();
			btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
			aVlanEnable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ENABLE));
			aVlanEnableComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.aVlanEnableComboBox, "ENABLEDSTATUEOAM");
			aOutVlanValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN_VALUE));
			aOutVlanValueField = new PtnTextField(true,PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.btnSave,this);
			this.aOutVlanValueField.setCheckingMaxValue(true);
			this.aOutVlanValueField.setCheckingMinValue(true);
			this.aOutVlanValueField.setMaxValue(4095);
			this.aOutVlanValueField.setMinValue(2);
			this.aOutVlanValueField.setText(2+"");
			atp_id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.TP_ID));
			atp_idComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.atp_idComboBox, "TP_ID");
			apanel = new JPanel();
			
			zVlanEnable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ENABLE));
			zVlanEnableComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.zVlanEnableComboBox, "ENABLEDSTATUEOAM");
			zOutVlanValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN_VALUE));
			zOutVlanValueField = new PtnTextField(true,PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.btnSave,this);
			this.zOutVlanValueField.setCheckingMaxValue(true);
			this.zOutVlanValueField.setCheckingMinValue(true);
			this.zOutVlanValueField.setMaxValue(4095);
			this.zOutVlanValueField.setMinValue(2);
			this.zOutVlanValueField.setText(2+"");
			ztp_id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.TP_ID));
			ztp_idComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.ztp_idComboBox, "TP_ID");
			zpanel = new JPanel();
			
			
			btnCancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			panelBtn = new JPanel();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		
	}
	private void setLayout(){
		this.setApanleLayout();
		this.setZpanleLayout();
		this.setBtnLayout();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		layout.columnWidths = new int[] { 5,140,140,5 };
		layout.columnWeights = new double[] { 0, 0.1, 0.1, 0 };
		layout.rowHeights = new int[] { 180, 20};
		layout.rowWeights = new double[] { 0, 0 };
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 1;
		c.gridy = 0;
		layout.setConstraints(this.apanel, c);
		this.add(this.apanel);
		
		c.gridx = 2;
		layout.setConstraints(this.zpanel, c);
		this.add(this.zpanel);
		
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20, 5, 5, 5);
		layout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}
	
	private void setZpanleLayout() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 5,20,140,5 };
		componentLayout.columnWeights = new double[] { 0,0,0,0 };
		componentLayout.rowHeights = new int[] {5,25,25,25,5};
		componentLayout.rowWeights = new double[] {0,0,0,0,0};
		this.zpanel.setLayout(componentLayout);
		this.zpanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.STRING_BACKWARD)+
				ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN)));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 5, 10, 5);
		//第一行
		c.gridx = 1;
		c.gridy = 1;
		componentLayout.setConstraints(this.zVlanEnable, c);
		this.zpanel.add(this.zVlanEnable);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.zVlanEnableComboBox, c);
		this.zpanel.add(this.zVlanEnableComboBox);
		//第二行
		c.gridx = 1;
		c.gridy = 2;
		componentLayout.setConstraints(this.zOutVlanValue, c);
		this.zpanel.add(this.zOutVlanValue);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.zOutVlanValueField, c);
		this.zpanel.add(this.zOutVlanValueField);
		//第三行
		c.gridx = 1;
		c.gridy = 3;
		componentLayout.setConstraints(this.ztp_id, c);
		this.zpanel.add(this.ztp_id);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.ztp_idComboBox, c);
		this.zpanel.add(this.ztp_idComboBox);
		
	
	}
	private void setApanleLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 5,20,140,5 };
		componentLayout.columnWeights = new double[] { 0,0,0,0 };
		componentLayout.rowHeights = new int[] {5,25,25,25,5};
		componentLayout.rowWeights = new double[] {0,0,0,0,0};
		this.apanel.setLayout(componentLayout);
		this.apanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.STRING_FORWARD)+
				ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN)));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 5, 10, 5);
		//第一行
		c.gridx = 1;
		c.gridy = 1;
		componentLayout.setConstraints(this.aVlanEnable, c);
		this.apanel.add(this.aVlanEnable);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.aVlanEnableComboBox, c);
		this.apanel.add(this.aVlanEnableComboBox);
		//第二行
		c.gridx = 1;
		c.gridy = 2;
		componentLayout.setConstraints(this.aOutVlanValue, c);
		this.apanel.add(this.aOutVlanValue);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.aOutVlanValueField, c);
		this.apanel.add(this.aOutVlanValueField);
		//第三行
		c.gridx = 1;
		c.gridy = 3;
		componentLayout.setConstraints(this.atp_id, c);
		this.apanel.add(this.atp_id);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.atp_idComboBox, c);
		this.apanel.add(this.atp_idComboBox);
		
	}
	private void setBtnLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 200, 10 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 60 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.btnCancel, c);
		this.panelBtn.add(this.btnCancel);
	}
	
	private void initData() {
		if(tunnel.getTunnelId()>0){
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.aVlanEnableComboBox, tunnel.getaVlanEnable()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.atp_idComboBox, tunnel.getaTp_id()+"");
			aOutVlanValueField.setText(tunnel.getaOutVlanValue()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.zVlanEnableComboBox, tunnel.getzVlanEnable()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.ztp_idComboBox, tunnel.getzTp_id()+"");
			zOutVlanValueField.setText(tunnel.getzOutVlanValue()+"");
		}
		
	}
	private void addListeners() {
		this.btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getData();
				dispose();
			}
		});
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	private void getData() {
		ControlKeyValue aVlanEnable = (ControlKeyValue) this.aVlanEnableComboBox.getSelectedItem();
		tunnel.setaVlanEnable(Integer.parseInt(((Code)aVlanEnable.getObject()).getCodeValue()));
		tunnel.setaOutVlanValue(Integer.parseInt(aOutVlanValueField.getText()));
		ControlKeyValue atp_id = (ControlKeyValue) this.atp_idComboBox.getSelectedItem();
		tunnel.setaTp_id(Integer.parseInt(((Code)atp_id.getObject()).getCodeValue()));
		ControlKeyValue zVlanEnable = (ControlKeyValue) this.zVlanEnableComboBox.getSelectedItem();
		tunnel.setzVlanEnable(Integer.parseInt(((Code)zVlanEnable.getObject()).getCodeValue()));
		tunnel.setzOutVlanValue(Integer.parseInt(zOutVlanValueField.getText()));
		ControlKeyValue ztp_id = (ControlKeyValue) this.ztp_idComboBox.getSelectedItem();
		tunnel.setzTp_id(Integer.parseInt(((Code)ztp_id.getObject()).getCodeValue()));
	}
}