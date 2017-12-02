package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.nms.db.bean.system.SystemLog;
import com.nms.model.system.SystemLogService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.rmi.ui.util.ThreadUtil;
import com.nms.snmp.ninteface.framework.AgentServer;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.controller.OperateCurrAlarmTask;
import com.nms.ui.ptn.alarm.controller.QueryCurrAlarmBySitesTask;


/**
 * 进程管理
 * @author guoqc
 * 
 */
public class ProcessPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ProcessPanel() {
		this.initComponent();			
		this.init();		
		this.setLayout();
		this.addListener();
				
	}

	/**
	 * 初始化IP地址
	 */
	public void init() {
	
		try {		
			if(ServerConstant.registry!=null){
				this.alarmStartBtn.setEnabled(true);
				this.alarmEndBtn.setEnabled(true);
				this.snmpStartBtn.setEnabled(true);
				this.snmpEndBtn.setEnabled(true);
				this.northStartBtn.setEnabled(true);
				this.northEndBtn.setEnabled(true);
			 }else{
				this.alarmStartBtn.setEnabled(false); 
				this.alarmEndBtn.setEnabled(false);  
				this.snmpStartBtn.setEnabled(false); 
				this.snmpEndBtn.setEnabled(false); 
				this.northStartBtn.setEnabled(false); 
				this.northEndBtn.setEnabled(false); 		 
			 }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {

		}
	}

	/**
	 * 添加监听事件
	 */
	private void addListener() {
        this.alarmStartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//控制告警轮询
					// (武汉)网管轮询设备所有未失连网元的当前告警
				SystemLogService_MB sys=null;
				SystemLog sysLog=null;
				try {
					sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);
					if(null==ThreadUtil.queryCurrAlarmBySitesTask){
						ThreadUtil.queryCurrAlarmBySitesTask = new QueryCurrAlarmBySitesTask();
					}
					ThreadUtil.queryCurrAlarmBySitesTask.startThread();
			
					//(武汉)处理网管轮询网元当前告警
					if(null==ThreadUtil.operateCurrAlarmTask){
						ThreadUtil.operateCurrAlarmTask = new OperateCurrAlarmTask();
					}
					ThreadUtil.operateCurrAlarmTask.startThread();
					alarmStartBtn.setEnabled(false);
					alarmEndBtn.setEnabled(true);
					sysLog=new SystemLog();
					sysLog.setOperationResult(1);
					sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_POLLING));
					sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_START_ALARM_POLLING));
					sys.insertSystemLog(sysLog);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					UiUtil.closeService_MB(sys);
				}
				
			}			
		});
        
	    this.alarmEndBtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					//控制告警轮询
					// (武汉)网管轮询设备所有未失连网元的当前告警
					SystemLogService_MB sys=null;
					SystemLog sysLog=null;
					try {
						sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);
						if(null!=ThreadUtil.queryCurrAlarmBySitesTask){					
							ThreadUtil.queryCurrAlarmBySitesTask.stopThread();
						}								
						//(武汉)处理网管轮询网元当前告警
						if(null!=ThreadUtil.operateCurrAlarmTask){
							ThreadUtil.operateCurrAlarmTask.stopThread();
						}
						alarmStartBtn.setEnabled(true);
						alarmEndBtn.setEnabled(false);	
						sysLog=new SystemLog();
						sysLog.setOperationResult(1);
						sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_POLLING));
						sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_END_ALARM_POLLING));
						sys.insertSystemLog(sysLog);
					} catch (Exception e1) {
						e1.printStackTrace();
					}finally{
						UiUtil.closeService_MB(sys);
					}						
			   }			
		});
	    this.snmpStartBtn.addActionListener(new ActionListener() {    	
				@Override
				public void actionPerformed(ActionEvent e) {					
					//等于1说明是开启snmp服务
					SystemLogService_MB sys=null;
					SystemLog sysLog=null;
					try {
						sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);					
						AgentServer agentServer = new AgentServer();
						agentServer.init(new String[]{});	
						snmpStartBtn.setEnabled(false);
						snmpEndBtn.setEnabled(true);
						sysLog=new SystemLog();
						sysLog.setOperationResult(1);
						sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_SNMP_SERVER));
						sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_START_SNMP_SERVER));
						sys.insertSystemLog(sysLog);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						UiUtil.closeService_MB(sys);
					}									
			   }			
		});
	    
	    this.snmpEndBtn.addActionListener(new ActionListener() {
	    	
				@Override
				public void actionPerformed(ActionEvent e) {
					SystemLogService_MB sys=null;
					SystemLog sysLog=null;
					try {
						sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);					
						snmpStartBtn.setEnabled(true);
						snmpEndBtn.setEnabled(false);
						sysLog=new SystemLog();
						sysLog.setOperationResult(1);
						sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_SNMP_SERVER));
						sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_END_SNMP_SERVER));
						sys.insertSystemLog(sysLog);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						UiUtil.closeService_MB(sys);
					}							
			   }			
		});
	    this.northStartBtn.addActionListener(new ActionListener() {    	
				@Override
				public void actionPerformed(ActionEvent e) {
					SystemLogService_MB sys=null;
					SystemLog sysLog=null;
					try {
						sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);					
						northStartBtn.setEnabled(false);
						northEndBtn.setEnabled(true);
						sysLog=new SystemLog();
						sysLog.setOperationResult(1);
						sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_NORTH_SERVER));
						sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_START_NORTH_SERVER));
						sys.insertSystemLog(sysLog);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						UiUtil.closeService_MB(sys);
					}												
						
			   }			
		});
	    
	    this.northEndBtn.addActionListener(new ActionListener() {
	    	
				@Override
				public void actionPerformed(ActionEvent e) {
					SystemLogService_MB sys=null;
					SystemLog sysLog=null;
					try {
						sys=(SystemLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SYSTEMLOG);					
						northStartBtn.setEnabled(true);
						northEndBtn.setEnabled(false);	
						sysLog=new SystemLog();
						sysLog.setOperationResult(1);
						sysLog.setOperationObjName(ResourceUtil.srcStr(StringKeysLbl.LBL_NORTH_SERVER));
						sysLog.setOperationType(ResourceUtil.srcStr(StringKeysLbl.LBL_END_NORTH_SERVER));
						sys.insertSystemLog(sysLog);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						UiUtil.closeService_MB(sys);
					}								
			   }			
		});	    
	    
	}
	

	
	


	
		
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_PROCESS_CONFIG)));
		this.alarmPolling = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_POLLING));
		this.alarmStartBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT_START));
		this.alarmEndBtn = new JButton(ResourceUtil.srcStr(StringKeysTip.CLOSE));
		this.snmpJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SNMP_SERVER));
		this.snmpStartBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT_START));
		this.snmpEndBtn = new JButton(ResourceUtil.srcStr(StringKeysTip.CLOSE));		
		this.northJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NORTH_SERVER));
		this.northStartBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT_START));
		this.northEndBtn = new JButton(ResourceUtil.srcStr(StringKeysTip.CLOSE));		
		this.panel_select = new JPanel();
		this.panel_select.setBorder(null);

	}


	/**
	 * 系统IP设置panel布局
	 */
	private void setLayoutSelect() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 120, 30, 30 };
		componentLayout.columnWeights = new double[] { 0.1, 0,0 };
		componentLayout.rowHeights = new int[] { 20, 20,20,20 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1,0.1,0.1 };
				
		this.panel_select.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.alarmPolling, c);
		this.panel_select.add(this.alarmPolling);

		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.alarmStartBtn, c);
		this.panel_select.add(this.alarmStartBtn);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 0;
		componentLayout.setConstraints(this.alarmEndBtn, c);
		this.panel_select.add(this.alarmEndBtn);
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.snmpJLabel, c);
		this.panel_select.add(this.snmpJLabel);


		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.snmpStartBtn, c);
		this.panel_select.add(this.snmpStartBtn);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 1;
		componentLayout.setConstraints(this.snmpEndBtn, c);
		this.panel_select.add(this.snmpEndBtn);

		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.northJLabel, c);
		this.panel_select.add(this.northJLabel);


		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.northStartBtn, c);
		this.panel_select.add(this.northStartBtn);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 2;
		componentLayout.setConstraints(this.northEndBtn, c);
		this.panel_select.add(this.northEndBtn);		
	}


	/**
	 * 此页面总布局
	 */
	private void setLayout() {
		this.setLayoutSelect();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWeights = new double[] { 0.1 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30, 0, 15, 0);
		componentLayout.setConstraints(this.panel_select, c);
		this.add(this.panel_select);

	}

	
	private JLabel alarmPolling; // 告警轮询
	private JLabel snmpJLabel; // 告警轮询
	private JLabel northJLabel; // 告警轮询
	private JButton alarmStartBtn; // 告警开启按钮
	private JButton alarmEndBtn; // 告警关闭按钮
	private JButton snmpStartBtn; // 告警开启按钮
	private JButton snmpEndBtn; // 告警关闭按钮
	private JButton northStartBtn; // 告警开启按钮
	private JButton northEndBtn; // 告警关闭按钮
	private JPanel panel_select; // 查询本机ID的panel
	
	
	public static void main(String[] args) {
		try {
			FileOutputStream  fs = new FileOutputStream( System.getProperty("user.dir") + "license.xml");
			try {
				fs.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
