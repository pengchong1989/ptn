package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nms.model.system.loginlog.LoginLogServiece_Mb;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RecoverFileChecking;
import com.nms.rmi.ui.util.backups.DataBaseUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.util.Mybatis_DBManager;

/**
 * 数据库恢复管理
 * 
 * @author kk
 * 
 */
public class DatabaseRecoverPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CheckBoxPanel checkBoxPanel = null; // 复选框panel

	/**
	 * 创建一个新的实例
	 */
	public DatabaseRecoverPanel() {
		this.checkBoxPanel = new CheckBoxPanel(false);
		this.initComponent();
		this.setLayout();
		this.addListener();

	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		// 恢复按钮
		this.btnRecover.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRecoverAction();
			}

		});

		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}

		});
	}

	/**
	 * 初始化按钮事件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private void btnSelectAction() {
		new PtnFileChooser(PtnFileChooser.TYPE_FILE, this.txtSelect, new FileNameExtensionFilter("SQL", "sql"));
	}

	/**
	 * 初始化按钮事件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private void btnRecoverAction() {
		List<String> tableNames = null;
		RecoverFileChecking recoverFileChecking = null;
		Connection conn = null;
		LoginLogServiece_Mb loginLogServiece = null;
		try {
			// 验证是否启动
			try {
				conn = Mybatis_DBManager.getInstance().getConnection();
				if(null == conn){
					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
					return;
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
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RECOVERY_TYPE));
				return;
			}

			// 验证是否选择恢复文件
			if (this.txtSelect.getText().length() == 0) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SQL_FILE));
				return;
			}

			// 获取应恢复的所有table名称
			tableNames = this.checkBoxPanel.getALlSelectTableName();
			recoverFileChecking = new RecoverFileChecking();
			// 验证文件是否匹配
			if (!recoverFileChecking.checkingFile(tableNames, this.txtSelect.getText().trim())) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SQL_CORRECTFILE));
				return;
			}

			//数据库恢复
			//在恢复之前先注销所有用户
			loginLogServiece = (LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
			loginLogServiece.deleteAll();
			DataBaseUtil dbUtil = new DataBaseUtil();
			boolean flag = dbUtil.recover(this.txtSelect.getText().trim(), conn);
			if(flag){
				// 如果没有异常。提示成功
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RECOVERY_SUCCESS));
			}else{
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_FAIL));
			}
		} catch (FileNotFoundException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_NOFIND_FILE));
		} catch (IOException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_EXCEPTION));
		} catch (SQLException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SQL_EXCEPTION));
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			tableNames = null;
			recoverFileChecking = null;
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			UiUtil.closeService_MB(loginLogServiece);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RECOVERY_DATA)));
		this.btnRecover = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RECOVERY));
		this.lblSelect = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_ROUTE));
		this.txtSelect = new JTextField();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_FILE));
		this.txtSelect.setEditable(false);

	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100, 250, 100 };
		componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		componentLayout.rowHeights = new int[] { 50, 200, 50, 20 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();

		// 选择文件标签
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.lblSelect, c);
		this.add(this.lblSelect);
		// 选择文件文本框
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		componentLayout.setConstraints(this.txtSelect, c);
		this.add(this.txtSelect);
		// 选择文件按钮
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

		// 恢复按钮
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 2;
		c.insets = new Insets(0, 15, 0, 0);
		componentLayout.setConstraints(this.btnRecover, c);
		this.add(this.btnRecover);
	}

	private JButton btnRecover; // 备份按钮
	private JLabel lblSelect; // 选择文件标签
	private JTextField txtSelect; // 选择文件文本框
	private JButton btnSelect; // 选择文件按钮

}
