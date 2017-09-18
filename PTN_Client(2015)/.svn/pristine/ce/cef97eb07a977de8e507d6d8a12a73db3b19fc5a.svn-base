package com.nms.ui.filter.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EServiceType;
import com.nms.ui.filter.FilterDialog;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnComboBox;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class TunnelFilterDialog extends FilterDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblNE = null; // 网元label
	private PtnComboBox cmbNE = null; // 网元下拉列表
	private JLabel lblPort = null; // 以太网端口label
	private PtnComboBox cmbPort = null; // 以太网端口label

	public TunnelFilterDialog(Object object) {
		super(object);
	}

	@Override
	protected void initComponent() {

		this.lblNE = new JLabel(ResourceUtil.srcStr(StringKeysObj.STRING_SITE_NAME));
		this.cmbNE = new PtnComboBox();
		this.lblPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		this.cmbPort = new PtnComboBox();
		// this.cmbNE.setEditable(true);

	}

	@Override
	protected int getPanelChildHeight() {
		return 100;
	}

	@Override
	protected void setLayoutChild(JPanel panelChild) {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 220 };
		layout.columnWeights = new double[] { 0, 0.1 };
		layout.rowHeights = new int[] { 40, 40 };
		layout.rowWeights = new double[] { 0.1, 0.1 };

		panelChild.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.NORTH;

		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(this.lblNE, c);
		panelChild.add(this.lblNE);

		c.gridx = 1;
		layout.setConstraints(this.cmbNE, c);
		panelChild.add(this.cmbNE);

		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(this.lblPort, c);
		panelChild.add(this.lblPort);

		c.gridx = 1;
		layout.setConstraints(this.cmbPort, c);
		panelChild.add(this.cmbPort);
	}

	@Override
	protected void addListenerChild() {
		this.cmbNE.addItemListener(new java.awt.event.ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ControlKeyValue controlKeyValue = (ControlKeyValue) e.getItem();
					getComboBoxDataUtil().initNNIPortData(cmbPort, Integer.parseInt(controlKeyValue.getId()),EServiceType.SITE);
				}
			}
		});
	}

	@Override
	protected void btnConfirmListener() {
		Tunnel tunnel = (Tunnel) super.getObject();
		tunnel.setTunnelName(super.getTxtName().getText());

		if (this.cmbNE.getSelectedIndex() > -1) {
			ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbNE.getSelectedItem();
			tunnel.setASiteId(Integer.parseInt(controlKeyValue.getId()));
		}

		if (this.cmbPort.getSelectedIndex() > -1) {
			ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbPort.getSelectedItem();
			tunnel.setAPortId(Integer.parseInt(controlKeyValue.getId()));
		}

		super.dispose();
	}

	@Override
	protected void loadData() {
		try {
			super.getComboBoxDataUtil().initNEData(this.cmbNE);
			this.loadFormData();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 绑定表单数据
	 */
	private void loadFormData(){
		Object object=super.getObject();
		if(null!=object){
			Tunnel tunnel=(Tunnel) object;
			super.getTxtName().setText(tunnel.getTunnelName());
			super.getComboBoxDataUtil().comboBoxSelect(this.cmbNE, tunnel.getASiteId()+"");
			super.getComboBoxDataUtil().comboBoxSelect(this.cmbPort, tunnel.getAPortId()+"");
		}
	}

}
