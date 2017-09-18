package com.nms.ui.datamanager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nms.rmi.ui.CheckBoxPanel;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.keys.StringKeysLbl;

public class DataManagerDialog extends PtnDialog {

	private static final long serialVersionUID = 1L;
	protected JPanel jPanel = null;
	protected CheckBoxPanel checkBoxPanel = null; // 复选框panel
	protected PtnButton btn = null; // 恢复,备份按钮
	protected JButton btnCanel = null;     //取消按钮
	protected JLabel lblSelect = null; // 选择文件，目录 标签
	protected JTextField txtSelect = null; // 选择文件，目录 文本框
	protected JButton btnSelect = null; // 选择文件，目录 按钮
	protected int weight = 0;
	public DataManagerDialog() {

	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
	
	}
	
	/**
	 * 设置布局
	 */
	public void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100, 250, 100 };
		componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		componentLayout.rowHeights = new int[] { 50, 200, 50, 20 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0.1 };
		this.jPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();

		// 选择目录标签
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.lblSelect, c);
		this.jPanel.add(this.lblSelect);
		// 选择目录文本框
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		componentLayout.setConstraints(this.txtSelect, c);
		this.jPanel.add(this.txtSelect);
		// 选择目录按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelect, c);
		this.jPanel.add(this.btnSelect);

		// 复选框panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		componentLayout.setConstraints(this.checkBoxPanel, c);
		this.jPanel.add(this.checkBoxPanel);

		// 备份按钮
		c.fill = GridBagConstraints.NONE;
		//c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(0, 15, 0, -150);
		componentLayout.setConstraints(this.btn, c);
		this.jPanel.add(this.btn);
		
		//取消按钮
		c.fill = GridBagConstraints.NONE;
		//c.anchor = GridBagConstraints.EAST;
		c.gridx = 2;
		c.insets = new Insets(0, 15, 0, 0);
		componentLayout.setConstraints(this.btnCanel, c);
		this.jPanel.add(this.btnCanel);
		
		this.add(jPanel);
	}
	
	public boolean checkPanel(){

		// 验证是否选中复选框
		try {
			if (!this.checkBoxPanel.checkingSelect()) {
				if(this.btn.getText().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP))){
					DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_TYPE));
				}
				else{
					DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RECOVERY_TYPE));
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 验证是否设置备份路径或恢复的文件
		if (this.txtSelect.getText().length() == 0) {
			if(this.btn.getText().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP))){
				DialogBoxUtil.succeedDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_ROUTE));
			}
			else{
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SQL_FILE));
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 添加监听
	 */
	protected void addBtnListener() {

		// 选择目录按钮事件
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}

		});
		
		// 取消按钮事件
		this.btnCanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}

		});
		
	}
	
	private void cancel() {
		this.dispose();
	}

	/**
	 * 选择目录按钮事件
	 */
	protected void btnSelectAction() {
		if(this.btn.getText().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP))){
			new PtnFileChooser(PtnFileChooser.TYPE_FOLDER, this.txtSelect, null);
		}
		else
			new PtnFileChooser(PtnFileChooser.TYPE_FILE, this.txtSelect, new FileNameExtensionFilter("SQL", "sql"));
		
	}
	
	public int getWeight() {
		return weight;
	}

}
