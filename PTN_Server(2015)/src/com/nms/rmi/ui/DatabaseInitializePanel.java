package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.ibatis.common.jdbc.ScriptRunner;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.util.Mybatis_DBManager;

/**
 * 数据库初始化管理
 * 
 * @author kk
 * 
 */
public class DatabaseInitializePanel extends JPanel {

	private static final long serialVersionUID = -8176752904816228224L;
	private CheckBoxPanel checkBoxPanel = null; // 复选框panel

	/**
	 * 创建一个新的实例
	 */
	public DatabaseInitializePanel() {
		this.checkBoxPanel = new CheckBoxPanel(true);
		this.initComponent();
		this.setLayout();
		this.addListener();

	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		// 初始化按钮
		this.btnInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnInitAction();
			}

		});
	}

	/**
	 * 初始化按钮事件
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	private void btnInitAction() {
		ScriptRunner runner = null;
		Map<String, JCheckBox> mapChk = null;
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
			// 验证是否选中复选框
			if (!this.checkBoxPanel.checkingSelect()) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_INIT_TYPE));
				return;
			}
			mapChk = this.checkBoxPanel.getMapChk();

			runner = new ScriptRunner(conn, false, false);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);

			// 遍历所有复选框，如果是选中状态的就执行sql脚本
			for (String key : mapChk.keySet()) {
				if (mapChk.get(key).isSelected()) {
					if(key.equals(ServerConstant.INIT_BUSINESS)){
						//如果是初始化核心业务数据，需要将labelinfo表的数据初始化
						this.initLabelInfoTable();
					}
					InputStreamReader isr = new InputStreamReader(new FileInputStream(ServerConstant.INIT_PATH + key), "UTF-8");
					ExceptionManage.infor(new FileInputStream(ServerConstant.INIT_PATH + key), this.getClass());
					runner.runScript(isr);
					isr.close();
					isr = null;
				}
			}
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_INIT_SUCCESS));

		} catch (FileNotFoundException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_NOFIND_FILE));
		} catch (IOException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_FILE_EXCEPTION));
		} catch (SQLException e) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SQL_EXCEPTION));
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		}

	}

	/**
	 * 初始化labelinfo表的数据
	 */
	private void initLabelInfoTable() {
		LabelInfoService_MB service = null;
		try {
			service = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO);
			service.initAllLabel();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_DATA_INIT)));
		this.btnInit = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_INIT));

	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 450 };
		componentLayout.columnWeights = new double[] { 0.1 };
		componentLayout.rowHeights = new int[] { 200, 50, 20 };
		componentLayout.rowWeights = new double[] { 0, 0, 0.1 };
		this.setLayout(componentLayout);

		// 初始化全部复选框
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.checkBoxPanel, c);
		this.add(this.checkBoxPanel);

		// 初始化按钮
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 15, 0, 0);
		componentLayout.setConstraints(this.btnInit, c);
		this.add(this.btnInit);
	}

	private JButton btnInit; // 初始化按钮
}
