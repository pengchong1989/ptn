package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.util.Mybatis_DBManager;


/**
 * 
 * 
 * 数据库的自动备份设置
 * @author zk
 *
 */
public class AutoDatabaseBackupsPanel extends DatabaseBackupsPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4171726700401805015L;
	private  Map<String,Runnable> threadMap;
	public AutoDatabaseBackupsPanel(){
		super();
		initComponent();
	}
	private void initComponent(){
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_BACKDATA)));
	}
	
	public static void main(String[] args) {
		//String path = "databaseBack";
		System.out.println(System.getProperty("user.dir"));
	}
	
	@Override
	public void setLayout(){
		try {
			super.timeLable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_STARTTIME_AUTO_BACKDATA));
			super.selectTimeButton = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_SETTIME_AUTO_BACKDATA));
			super.timeText = new JTextField();
			super.backInterval = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIME_AUTO_BACKDATA));
			super.backIntervalCombox = new JComboBox();
			super.backIntervalCombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ONETIME_AUTO_BACKDATA)); 
			super.backIntervalCombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_TWOTIME_AUTO_BACKDATA));
			super.backIntervalCombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_THREETIME_AUTO_BACKDATA));
			super.backIntervalCombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ONE_WEEK_TIME_AUTO_BACKDATA));
			super.backIntervalCombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_ONE_MONTH_TIME_AUTO_BACKDATA));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			super.timeText.setText(format.format(new Date()));
			
			GridBagLayout componentLayout = new GridBagLayout();
			componentLayout.columnWidths = new int[] {80,150,100 };
			componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
			componentLayout.rowHeights = new int[] { 30,150,20,20};
			componentLayout.rowWeights = new double[] { 0,0,0,0};
			
			super.setLayout(componentLayout);
			GridBagConstraints c = new GridBagConstraints();

			// 选择目录标签
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(0, 15, 0, 0);
			componentLayout.setConstraints(super.lblSelect, c);
			super.add(super.lblSelect);
			// 选择目录文本框
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			componentLayout.setConstraints(super.txtSelect, c);
			//给自动备份赋默认的目录地址
			super.txtSelect.setText(ServerConstant.AUTODATABACK_FILE);
			super.add(super.txtSelect);
			// 选择目录按钮
			c.fill = GridBagConstraints.NONE;
			c.gridx = 2;
			componentLayout.setConstraints(super.btnSelect, c);
			super.add(super.btnSelect);

			// 复选框panel
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 3;
			componentLayout.setConstraints(super.checkBoxPanel, c);
			super.add(super.checkBoxPanel);

			c.gridx = 0;
			c.gridy = 2;
			c.insets = new Insets(0, 15, 0, 0);
			componentLayout.setConstraints(super.timeLable, c);
			super.add(super.timeLable);
			
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 1;
			c.insets = new Insets(0,5, 0, 5);
			componentLayout.setConstraints(super.timeText, c);
			super.add(super.timeText);
			
//			c.fill = GridBagConstraints.EAST;
//			c.gridx = 2;
//			c.gridy = 2;
//			componentLayout.setConstraints(super.selectTimeButton, c);
//			super.add(super.selectTimeButton);
			
			c.fill = GridBagConstraints.BOTH;
			c.insets = new Insets(15, 15, 0, 0);
			c.gridx = 0;
			c.gridy = 3;
			componentLayout.setConstraints(super.backInterval, c);
			super.add(super.backInterval);
			
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 3;
			c.gridwidth = 1;
			c.insets = new Insets(15,5, 0, 5);
			componentLayout.setConstraints(super.backIntervalCombox, c);
			super.add(super.backIntervalCombox);
			
			// 备份按钮
			c.fill = GridBagConstraints.NONE;
			c.anchor = GridBagConstraints.WEST;
			c.gridx = 0;
			c.gridy = 4;
			c.insets = new Insets(15, 15, 0, 0);
			super.btnBackups.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
			componentLayout.setConstraints(super.btnBackups, c);
			super.add(super.btnBackups);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, super.getClass()); 
		}
	}
	
	@Override
	public void addListener(){
		//自动备份按钮
		super.btnBackups.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				autoBackData();
			}
		});
		
		// 选择目录按钮事件
		super.btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}
		});
		
		super.selectTimeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	//自动备份按钮
	public void autoBackData(){
		SimpleDateFormat format = null;
		List<String> tableNames = null;//所有表名
		String filePath ;//保存地址
		long cycleTime =0;//周期
		long startTime;//开始时间
		AutoDatabaseBackThradUtil autoDatabaseBackThradUtil = null;
		String	 regex = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
		try {
			//验证是否启动
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
			// 验证是否选中复选框
			if (!super.checkBoxPanel.checkingSelect()) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_TYPE));
				return;
			}
			// 验证是否设置备份路径
			if (super.txtSelect.getText().length() == 0) {
				DialogBoxUtil.succeedDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CHECKBACKUP_ROUTE));
				return;
			}
			//验证是否设置了时间
			if (super.timeText.getText().length() == 0) {
				DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_SET_TIME_AUTO_BACKDATA));
				return;
			}
			if(!super.timeText.getText().trim().matches(regex)){
				DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysLbl.LBL_TIME_ERROR));
				return ;
			}
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime = format.parse(super.timeText.getText()).getTime();
			
			if(super.backIntervalCombox.getSelectedIndex() ==0){
				cycleTime = 1;//一天
			}else if(super.backIntervalCombox.getSelectedIndex() ==1){
				cycleTime = 2;//两天
			}else if(super.backIntervalCombox.getSelectedIndex() ==2){
				cycleTime = 3;//三天
			}else if(super.backIntervalCombox.getSelectedIndex() ==3){
				cycleTime = 7;//一周
			}else if(super.backIntervalCombox.getSelectedIndex() ==4){
				cycleTime = 30;//一个月
			}
			cycleTime = cycleTime*24*60*60*1000;
			
			tableNames = super.checkBoxPanel.getALlSelectTableName();
			filePath = super.txtSelect.getText().trim();
			
			 autoDatabaseBackThradUtil = new AutoDatabaseBackThradUtil();
			 autoDatabaseBackThradUtil.createThread(tableNames, filePath, startTime, cycleTime);
			
			
			DialogBoxUtil.succeedDialog(null,ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass()); 
		}finally{
			autoDatabaseBackThradUtil = null;
		}
	}
	
	public Map<String, Runnable> getThreadMap() {
		return threadMap;
	}
	
	public void setThreadMap(Map<String, Runnable> threadMap) {
		this.threadMap = threadMap;
	}
	
}
