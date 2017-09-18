package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.nms.model.system.DataCompareService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.DataCompareUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.util.Mybatis_DBManager;

public class DatabaseUpgradePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 创建一个新的实例
	 */
	public DatabaseUpgradePanel() {
		this.initComponent();
		this.setLayout();
		this.addListener();

	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		// 升级按钮
		this.btnUpgrade.addActionListener(new ActionListener() {

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
		new PtnFileChooser(PtnFileChooser.TYPE_FILE, this.txtSelect, new FileNameExtensionFilter("XML", "xml"));
	}

	/**
	 * 初始化按钮事件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private void btnRecoverAction() {
		DataCompareService_MB dataCompareService = null;
		Connection conn = null;
		try {
			// 验证是否启动
			try {
				conn = Mybatis_DBManager.getInstance().getConnection();
				if(null == conn){
					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
					return ;
				}
			} catch (Exception e) {
				DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
				return ;
			}
//			if (null == DBManager.getInstance().getConnection()) {
//				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
//				return;
//			}

			// 验证是否选择恢复文件
			if (this.txtSelect.getText().length() == 0) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_XML_FILE));
				return;
			}

			DataCompareUtil dateCompareUtil = new DataCompareUtil();
			dateCompareUtil.compareData(this.txtSelect.getText());
		    dataCompareService = (DataCompareService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DATACOMPARESERVICE);
			dataCompareService.dataCompare(dateCompareUtil.getInsertSqls());
			dataCompareService.dataCompare(dateCompareUtil.getUpdateSqls());
			// 如果没有异常。提示成功
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_UPGRADE_SUCCESS));

		} catch (ParserConfigurationException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_EXCEPTION));
		} catch (SQLException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_UPGRADE_ERROR));
		}catch (IOException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_EXCEPTION));
		} catch (SAXException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_EXCEPTION));
		}catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			UiUtil.closeService_MB(dataCompareService);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_UPGRADE_DATA)));
		this.btnUpgrade = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_UPGRADE_BTN));
		this.lblSelect = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_ROUTE));
		this.txtSelect = new JTextField();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_UPGRADE_FILE));
		this.txtSelect.setEditable(false);
		this.prompt = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PROMPT));
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100, 250, 100 };
		componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		componentLayout.rowHeights = new int[] { 50, 50, 50, 20 };
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
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.insets = new Insets(0, 25, 0, 0);
		componentLayout.setConstraints(this.prompt, c);
		this.add(this.prompt);

		// 恢复按钮
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 2;
		c.insets = new Insets(0, 25, 0, 0);
		componentLayout.setConstraints(this.btnUpgrade, c);
		this.add(this.btnUpgrade);
	}

	private JButton btnUpgrade; // 升级按钮
	private JLabel lblSelect; // 选择文件标签
	private JTextField txtSelect; // 选择文件文本框
	private JButton btnSelect; // 选择文件按钮
	private JLabel prompt;//提示


}
