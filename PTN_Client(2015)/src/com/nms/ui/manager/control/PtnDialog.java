package com.nms.ui.manager.control;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.nms.ui.manager.util.ComboBoxDataUtil;

public class PtnDialog extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<PtnTextField> ptnTextFieldList = new ArrayList<PtnTextField>();
	private final List<PtnComboBox> ptnComboBoxList = new ArrayList<PtnComboBox>();
	private final List<PtnPasswordField> ptnPasswordFieldList=new ArrayList<PtnPasswordField>();
	
	private ComboBoxDataUtil comboBoxDataUtil = new ComboBoxDataUtil();
	
	public PtnDialog(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	/**
	 * 
	 * checkingMessage(验证消息)
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public String checkingMessage() {
		String result = "";
		if (getPtnTextFieldList().size() > 0) {
			for (PtnTextField ptnTextField : ptnTextFieldList) {
				if (null != ptnTextField.getErrorMessage() && !"".equals(ptnTextField.getErrorMessage())) {
					result = ptnTextField.getErrorMessage();
					break;
				}
			}
		}
		if ("".equals(result)) {
			if (getPtnComboBoxList().size() > 0) {
				for (PtnComboBox ptnComboBox : getPtnComboBoxList()) {
					if (null != ptnComboBox.getErrorMessage() && !"".equals(ptnComboBox.getErrorMessage())) {
						result = ptnComboBox.getErrorMessage();
						break;
					}
				}
			}
		}
		
		if ("".equals(result)) {
			if (getPtnPasswordFieldList().size() > 0) {
				for (PtnPasswordField ptnPasswordField : getPtnPasswordFieldList()) {
					if (null != ptnPasswordField.getErrorMessage() && !"".equals(ptnPasswordField.getErrorMessage())) {
						result = ptnPasswordField.getErrorMessage();
						break;
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * 添加控件
	 * @param panel 承载面板
	 * @param component	控件
	 * @param gridx gridx 
	 * @param gridy gridy
	 * @param weightx weightx
	 * @param weighty weighty
	 * @param gridwidth gridwidth
	 * @param gridheight gridheight
	 * @param fill fill
	 * @param insets insets
	 * @param anchor anchor
	 * @param gridBagConstraints gridBagConstraints
	 */
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}
	public List<PtnTextField> getPtnTextFieldList() {
		return ptnTextFieldList;
	}

	public void setPtnTextFieldList(PtnTextField ptnTextField) {
		this.getPtnTextFieldList().add(ptnTextField);
	}

	public List<PtnComboBox> getPtnComboBoxList() {
		return ptnComboBoxList;
	}

	public void setPtnComboBoxList(PtnComboBox ptnComboBox) {
		this.getPtnComboBoxList().add(ptnComboBox);
	}
	
	public List<PtnPasswordField> getPtnPasswordFieldList() {
		return ptnPasswordFieldList;
	}

	public void setPtnPasswordFieldList(PtnPasswordField ptnPasswordField) {
		this.getPtnPasswordFieldList().add(ptnPasswordField);
	}


	public ComboBoxDataUtil getComboBoxDataUtil() {
		return comboBoxDataUtil;
	}
	
	public void setButtonLayout(PtnButton btnSave,JButton btnCanel,JPanel buttonPanel,PtnDialog dialog) {
		
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 140,30,30 };
		componentLayout.columnWeights = new double[] { 0.1,0, 0};
		componentLayout.rowHeights = new int[] {  30 };
		componentLayout.rowWeights = new double[] { 0 };
		dialog.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// 第一行 名称
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(btnSave, c);
		buttonPanel.add(btnSave);
		c.gridx=2;
		componentLayout.setConstraints(btnCanel, c);
		buttonPanel.add(btnCanel);
	}
	
}
