package com.nms.ui.filter.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class CesNeFilterDialog extends CesFilterDialog
{

	public CesNeFilterDialog(Object object)
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

		// ces类型
		c.gridx = 0;
		c.gridy = 3;
		layout.setConstraints(this.lblCestype, c);
		panelChild.add(this.lblCestype);

		c.gridx = 1;
		layout.setConstraints(this.cmbCestype, c);
		panelChild.add(this.cmbCestype);
	}
	
	@Override
	protected void addListenerChild() {
		getComboBoxDataUtil().initPDHPortData(cmbPort, ConstantUtil.siteId, EServiceType.SITE);
	}
	
	@Override
	protected void loadData() {
		try {
			super.getComboBoxDataUtil().initNEData(this.cmbNEName);
			super.getComboBoxDataUtil().initActivatedData(this.cmbActivatedState);
			super.getComboBoxDataUtil().initPWData(this.cmbPwName,1, false);
			this.cmbCestype.addItem(new ControlKeyValue("0", ResourceUtil.srcStr(StringKeysObj.STRING_ALL), null));
			super.getComboBoxDataUtil().comboBoxData(this.cmbCestype, "CESSERVICETYPE");
			loadFormData();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
}
