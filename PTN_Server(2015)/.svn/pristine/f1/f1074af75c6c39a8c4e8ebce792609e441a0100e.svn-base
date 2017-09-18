package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.rmi.ui.util.backups.BackupsDatabase;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.util.Mybatis_DBManager;


/**
 * 数据库备份管理
 * 
 * @author kk
 * 
 */
public class DatabaseBackupsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected CheckBoxPanel checkBoxPanel = null; // 复选框panel

	/**
	 * 创建一个新的实例
	 */
	public DatabaseBackupsPanel() {
		this.checkBoxPanel = new CheckBoxPanel(false);
		this.initComponent();
		this.setLayout();
		this.addListener();

	}

	/**
	 * 添加监听
	 */
	public void addListener() {

		// 备份按钮
		this.btnBackups.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnBackupsAction();
			}

		});

		// 选择目录按钮事件
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}

		});
	}

	/**
	 * 选择目录按钮事件
	 */
	protected void btnSelectAction() {
		new PtnFileChooser(PtnFileChooser.TYPE_FOLDER, this.txtSelect, null);
	}

	/**
	 * 初始化按钮事件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private void btnBackupsAction() {
		List<String> tableNames = null; // 要备份的所有表名
		BackupsDatabase backupsDatabase = null;
		try {

			// 验证是否启动
			try {
				Connection conn = Mybatis_DBManager.getInstance().getConnection();
				if(null == conn){
					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
					return ;
				}else{
					conn.close();
				}
			} catch (Exception e) {
				DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
				return ;
			}
//			if (null == DBManager.getInstance().getConnection()) {
//				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
//				return;
//			}
			// 验证是否选中复选框
			if (!this.checkBoxPanel.checkingSelect()) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_TYPE));
				return;
			}

			// 验证是否设置备份路径
			if (this.txtSelect.getText().length() == 0) {
				DialogBoxUtil.succeedDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_ROUTE));
				return;
			}
			
			//获取所有表名
			tableNames = this.checkBoxPanel.getALlSelectTableName();

			//执行备份操作
			backupsDatabase = new BackupsDatabase();
			boolean flag = backupsDatabase.backups(tableNames, this.txtSelect.getText());
			if(flag){
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP_SUCCESE));
			}else{
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SAVE_FAIL));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP_DATA)));
		this.btnBackups = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP));
		this.lblSelect = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CATA_ROUTE));
		this.txtSelect = new JTextField();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKCATA));
		this.txtSelect.setEditable(false);

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
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();

		// 选择目录标签
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.lblSelect, c);
		this.add(this.lblSelect);
		// 选择目录文本框
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		componentLayout.setConstraints(this.txtSelect, c);
		this.add(this.txtSelect);
		// 选择目录按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelect, c);
		this.add(this.btnSelect);

		// 复选框panel
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		componentLayout.setConstraints(this.checkBoxPanel, c);
		this.add(this.checkBoxPanel);

		// 备份按钮
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 2;
		c.insets = new Insets(0, 15, 0, 0);
		componentLayout.setConstraints(this.btnBackups, c);
		this.add(this.btnBackups);
	}
	
	protected JButton btnBackups; // 备份按钮
	protected JLabel lblSelect; // 选择目录标签
	protected JTextField txtSelect; // 选择目录文本框
	protected JButton btnSelect; // 选择目录按钮
	
	protected JLabel timeLable; 
	protected JButton selectTimeButton;
	protected JTextField timeText;
	protected JLabel backInterval;
	protected JComboBox backIntervalCombox;

}
