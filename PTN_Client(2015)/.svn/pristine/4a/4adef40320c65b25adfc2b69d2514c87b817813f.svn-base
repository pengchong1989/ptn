package com.nms.ui.datamanager.databackup;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.CheckBoxPanel;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.datamanager.DataManagerDialog;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.util.Mybatis_DBManager;

public class DataBackupJDialog extends DataManagerDialog{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 创建一个新的实例
	 */
	public DataBackupJDialog() {
		this.initComponent();
		super.setLayout();
		super.addBtnListener();
		this.addListener();
	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		// 备份按钮
		this.btn.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnBackupsAction();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}
			
			@Override
			public boolean checking() {
				// 验证是否启动
				try {
					Connection conn = Mybatis_DBManager.getInstance().getConnection();
					if(null == conn){
						DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
						return false;
					}else{
						conn.close();
					}
				} catch (Exception e) {
					DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNSERVER));
					return false;
				}
	            if(!checkPanel()){
	            	return false;
	            }
	            return true;
			}

		});		
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
			//获取所有表名
			tableNames = this.checkBoxPanel.getALlSelectTableName();

			//执行备份操作
			backupsDatabase = new BackupsDatabase();
			boolean flag = backupsDatabase.backups(tableNames, this.txtSelect.getText());
			if(flag){
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP_SUCCESE));
				this.insertOpeLog(EOperationLogType.DATABASEBACK.getValue(), ResultString.CONFIG_SUCCESS, null, null);
				this.dispose();
			}else{
				this.insertOpeLog(EOperationLogType.DATABASEBACK.getValue(), ResultString.CONFIG_FAILED, null, null);
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(btn, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP_DATA),"");		
	}
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.checkBoxPanel = new CheckBoxPanel(false);
		this.jPanel=new JPanel();
		jPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP_DATA)));
		this.btn = new PtnButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_BACKUP));
		this.lblSelect = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CATA_ROUTE));
		this.txtSelect = new JTextField();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKCATA));
		this.txtSelect.setEditable(false);
		//取消按钮
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.weight=550;
	}

}
