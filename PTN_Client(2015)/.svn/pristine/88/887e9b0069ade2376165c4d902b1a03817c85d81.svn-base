package com.nms.ui.ptn.configinfo.dialog;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.alarm.WarningLevelService_MB;
import com.nms.model.util.Services;
import com.nms.ui.Ptnf;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.TopologyUtil;
import com.nms.ui.ptn.configinfo.AlarmDescPanel;

public class UpdateAlarmDescDialog extends PtnDialog
{
	private static final long serialVersionUID = 2471644119030161694L;
	private WarningLevel warningLevel;
	private AlarmDescPanel alarmDescPanel;

	private PtnButton jButton1;
	private JButton jButton2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JTextArea jTextArea1;
	private JTextArea jTextArea2;
	private JTextArea jTextArea3;
	private JTextField jTextField1;
	private JLabel jLevelLabel;
	private JComboBox jcombox;
	private JPanel panelBtn; // 鎸夐挳甯冨眬
	private Ptnf ptnf = null;
	

	public void initComponents()
	{
		this.setSize(830,450);
		jLabel1 = new JLabel();           
		jLabel2 = new JLabel();           
		jLevelLabel = new JLabel();       
		jTextField1 = new JTextField();   
		jLabel3 = new JLabel();           
		jScrollPane1 = new JScrollPane(); 
		jTextArea1 = new JTextArea();     
		jScrollPane2 = new JScrollPane(); 
		jTextArea2 = new JTextArea();     
		jScrollPane3 = new JScrollPane(); 
		jTextArea3 = new JTextArea();     
		jLabel4 = new JLabel();           
		//jLabel5 = new JLabel();           
		jButton1 = new PtnButton("",false);         
		jButton2 = new JButton();    
		jcombox = new JComboBox();
		panelBtn = new JPanel();		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_UPDATE));		
		jcombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_UrgencyAlarm));
		jcombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_MajorAlarm));
		jcombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_MinorAlarm));
		jcombox.addItem(ResourceUtil.srcStr(StringKeysLbl.LBL_PromptAlarm));
		
		jLabel1.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
	
		jLabel2.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_DESC));
		jLevelLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_LEVEL));
		jTextField1.setEditable(false);

		jLabel3.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_IMPACT));

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		jTextArea3.setColumns(20);
		jTextArea3.setRows(5);
		jScrollPane3.setViewportView(jTextArea3);

		jLabel4.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REMARK));
		
		//jLabel5.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REMARK));
		
		jButton1.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		jButton1.addActionListener(new MyActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton1ActionPerformed(evt);
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});

		jButton2.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		jButton2.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButton2ActionPerformed(evt);
			}
		});	
		
		setLayout();
	}
	private void setButtonLayout() 
	{
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 650, 50 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.jButton1, c);
		this.panelBtn.add(this.jButton1);

		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.jButton2, c);
		this.panelBtn.add(this.jButton2);

	}
	
	private void setLayout() 
	{
		this.setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 20, 50};
		layout.columnWeights = new double[] { 0, 0};
		layout.rowHeights = new int[] { 10, 10, 20, 20,20};
		layout.rowWeights = new double[] {0, 0, 0.0, 0.05, 0.05,0.05};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jLabel1, c);
		this.add(jLabel1);
		c.gridx = 1;
		c.gridy = 0;	
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jTextField1, c);
		this.add(jTextField1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;		
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(jLevelLabel, c);
		this.add(jLevelLabel);
		c.gridx = 1;
		c.gridy = 2;	
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jcombox, c);
		this.add(jcombox);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(jLabel2, c);
		this.add(jLabel2);
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jScrollPane1, c);
		this.add(jScrollPane1);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(jLabel3, c);
		this.add(jLabel3);
		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jScrollPane2, c);
		this.add(jScrollPane2);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(jLabel4, c);
		this.add(jLabel4);
		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(jScrollPane3, c);
		this.add(jScrollPane3);
		
		c.gridy = 6;
		layout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}

	private void jButton2ActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}

	private void jButton1ActionPerformed(ActionEvent evt)
	{
		if (null != warningLevel)
		{
			warningLevel.setWaringeffect(jTextArea2.getText());
			warningLevel.setWaringremark(jTextArea3.getText());
			warningLevel.setWarningdescribe(jTextArea1.getText());
			//5：紧急，4：主要，3：次要，2：提示
			if(jcombox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_UrgencyAlarm)))
			{
				warningLevel.setWarninglevel_temp(5);
			}
			else if(jcombox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_MajorAlarm)))
			{
				warningLevel.setWarninglevel_temp(4);
			}
			else if(jcombox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_MinorAlarm)))
			{
				warningLevel.setWarninglevel_temp(3);
			}
			else if(jcombox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysLbl.LBL_PromptAlarm)))
			{
				warningLevel.setWarninglevel_temp(2);
			}
			
			WarningLevelService_MB warningLevelService = null;
			CurAlarmService_MB curAlarmService = null;
			WarningLevel beforWarningLevel = null;
			try
			{
				warningLevelService = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
				beforWarningLevel = warningLevelService.selectById(warningLevel.getId());
				warningLevelService.saveOrUpdate(warningLevel);
				//添加日志记录
				this.jButton1.setOperateKey(EOperationLogType.ALARMDESCUPDATE.getValue());
				this.jButton1.setResult(1);
				DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				this.dispose();
				alarmDescPanel.getController().refresh();
				//修改完毕之后,刷新拓扑图告警
				if (null != this.ptnf) {
					curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
					TopologyUtil topologyUtil = new TopologyUtil();
					topologyUtil.updateSiteInstAlarm(curAlarmService);
					this.ptnf.refreshAlarmNum(curAlarmService);
				}
				AddOperateLog.insertOperLog(jButton1, EOperationLogType.ALARMDESCUPDATE.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), beforWarningLevel, warningLevel, 0, ResourceUtil.srcStr(StringKeysTab.TAB_ALARM), "WarningLevel");
			}
			catch (Exception e)
			{
				ExceptionManage.dispose(e, getClass());
			}
			finally
			{
				UiUtil.closeService_MB(warningLevelService);
				UiUtil.closeService_MB(curAlarmService);
			}
		}
	}

	/** Creates new form UpdateAlarmDescDialog */
	public UpdateAlarmDescDialog(Frame parent, boolean modal)
	{
		this.ptnf = Ptnf.getPtnf();
		initComponents();
	}
	
	public UpdateAlarmDescDialog(JPanel jPanel, boolean modal, int alarmDescId)
	{
		this.setModal(modal);
		this.ptnf = Ptnf.getPtnf();
		initComponents();
		getWarningLevel(alarmDescId);
		
		if (null != warningLevel)
		{
			this.jTextField1.setText(warningLevel.getWarningname());
			this.jTextArea1.setText(warningLevel.getWarningdescribe());
			this.jTextArea2.setText(warningLevel.getWaringeffect());
			this.jTextArea3.setText(warningLevel.getWaringremark());
			int i = warningLevel.getWarninglevel_temp();
			//5：紧急，4：主要，3：次要，2：提示
			if(i == 5)
			{
				this.jcombox.setSelectedIndex(0);
			}
			else if(i == 4)
			{
				this.jcombox.setSelectedIndex(1);
			}
			else if(i == 3)
			{
				this.jcombox.setSelectedIndex(2);
			}
			else if(i == 2)
			{
				this.jcombox.setSelectedIndex(3);
			}
			
		}
		alarmDescPanel = (AlarmDescPanel) jPanel;
	}

	private void getWarningLevel(int alarmDescId)
	{
		WarningLevelService_MB warningLevelService = null;
		List<WarningLevel> warningLevelList = null;
		try
		{
			warningLevelService = (WarningLevelService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.WarningLevel);
			warningLevel = new WarningLevel();
			warningLevel.setId(alarmDescId);
			warningLevelList = warningLevelService.select(warningLevel);
			if (warningLevelList.size() > 0)
			{
				warningLevel = warningLevelList.get(0);
			}
			else
			{
				warningLevel = null;
			}
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e, this.getClass());
		}
		finally
		{
			UiUtil.closeService_MB(warningLevelService);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()                             
		{                                                             
			public void run()                                           
			{                                                           
				UpdateAlarmDescDialog dialg = new UpdateAlarmDescDialog(new JFrame(), true);                                  
				dialg.setLayout();
				dialg.addWindowListener(new WindowAdapter()              
				{                                                         
					@Override
					public void windowClosing(WindowEvent e)                
					{                                                       
						System.exit(0);                                       
					}                                                       
				});                                                       
				dialg.setVisible(true);                                  
			}                                                           
		});                                                           

	}

}
