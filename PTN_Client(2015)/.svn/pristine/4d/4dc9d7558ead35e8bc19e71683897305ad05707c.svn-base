package com.nms.ui.filter.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;

public class EthNeFilterDialog extends EthServiceFilterDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EthNeFilterDialog(Object object)
	{
		super(object);
	}
	
	@Override
	protected void setLayoutChild(JPanel panelChild) {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 220 };
		layout.columnWeights = new double[] { 0, 0.1 };
		layout.rowHeights = new int[] { 40, 40, 40 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1 };

		panelChild.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.NORTH;
		
		// 端口名称
		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(this.lblPort, c);
		panelChild.add(this.lblPort);

		c.gridx = 1;
		layout.setConstraints(this.cmbPort, c);
		panelChild.add(this.cmbPort);

		// pw名称
		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(this.lblPwName, c);
		panelChild.add(this.lblPwName);

		c.gridx = 1;
		layout.setConstraints(this.cmbPwName, c);
		panelChild.add(this.cmbPwName);

		// 激活状态
		c.gridx = 0;
		c.gridy = 2;
		layout.setConstraints(this.lblActivatedState, c);
		panelChild.add(this.lblActivatedState);

		c.gridx = 1;
		layout.setConstraints(this.cmbActivatedState, c);
		panelChild.add(this.cmbActivatedState);
	}
	
	@Override
	protected void btnConfirmListener() {

		Object object = super.getObject();
		try {
			ServiceInfo serviceInfo = (ServiceInfo) object;
			serviceInfo.setName(super.getTxtName().getText());
			
			if (this.cmbPort.getSelectedIndex() > -1) {
				ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbPort.getSelectedItem();
				serviceInfo.setAportId(Integer.parseInt(controlKeyValue.getId()));
			}
			
			if (this.cmbPwName.getSelectedIndex() > -1) {
				ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbPwName.getSelectedItem();
				serviceInfo.setPwId(Integer.parseInt(controlKeyValue.getId()));
			}
			
			if(this.cmbActivatedState.getSelectedIndex()>-1){
				ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbActivatedState.getSelectedItem();
				serviceInfo.setActiveStatus(Integer.parseInt(controlKeyValue.getId()));
			}

			super.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	@Override
	protected void addListenerChild() {
		getComboBoxDataUtil().initUNIPortData(cmbPort, ConstantUtil.siteId, EServiceType.SITE);
	}

	@Override
	protected void loadData() {

		try {
			super.getComboBoxDataUtil().initNEData(this.cmbNEName);
			super.getComboBoxDataUtil().initActivatedData(this.cmbActivatedState);
			super.getComboBoxDataUtil().initPWData(this.cmbPwName,1,true);
			loadFormData();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}

	}
	


}
