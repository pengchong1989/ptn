package com.nms.ui.ptn.systemManage.monitor.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.ptn.DbInfoTask;
import com.nms.db.bean.system.DataBaseInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.DataBaseService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * <p>文件名称:MointorDbTask.java</p>
 * <p>文件描述: 配置监控数据库容量的字界面</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要: 创建、修改DbInfoTask配置都使用此子对话框</p>
 * <p>其他说明: </p>
 * <p>完成日期: 2015年2月9日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author zhangkun
 */

public class MointorDbTask extends PtnDialog{

	 /** 监控选择 */
	private JLabel mointorSelect;
	
	/**数据库总空间使用监控 */
	protected JCheckBox totalDbSpace;
	
	/**数据库使用空间监控 */
	protected JCheckBox dbSpace;
	
	/** 监控选择容器 */
	protected JPanel mointorSelectJPanel;
	
	/** 监控周期标示*/
	private JLabel mointorCycleJlabel;
	
	/**监控周期 */
	private JComboBox mointorCycle;
	
	/** 监控周期容器 */
	private JPanel mointorCycleJPanel;
	
	/**数据库总空间使用告警门限(GB) */
	private JLabel totalDbAlarmLimit;
	
	/**数据库总空间使用告警门限值*/
	private PtnTextField totalDbAlarmLimitValue;
	
	/** 数据库总空间使用告警门容器 */
	private JPanel mointorAlarmLimitJPanel;
	
	/**数据库使用空间监控*/
	private JPanel dbJpan = new JPanel();
	
	/**数据库使用空间监控DbInfoTask*/
	protected DbInfoTask dbInfoTask = null;
	
	/**用于提交用户配置信息*/
	protected JButton confirButton = null;
	
	/**用于提示输入有误的提示*/
	protected JLabel lblMessage = null;
	
	/**用于取消用户配置信息*/
	protected JButton cancelButton = null;
	
	/**用于显示数据库表格配置信息*/
	protected DataBasePanel dataBaseMointView = null;
	
	/**用于显示按钮的位置*/
	protected JPanel buttonJpanel = null;
	
	protected Map<String, Integer> dbIdMap;
	
	protected String labelTitle = "";
	
	//****** 代码段: 构造方法 *******************************************************************************/
	
	public MointorDbTask(DbInfoTask dbInfoTask)
	{
		this.dbInfoTask = dbInfoTask;
		init();
		setLayoutJpanel();
		setVaue();
		addListener();
		UiUtil.showWindow(this,900, 550);
	}
	//****** 代码段: 初始化界面 *******************************************************************************/
	private void init() 
	{
		
		try { 
			confirButton = new JButton(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_SURE));
			lblMessage = new JLabel();
			cancelButton = new JButton(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CANCEL));
			
			/*************初始化监控选择容器******************************/
			mointorSelectJPanel = new JPanel();
			mointorSelectJPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MOINTORSELECT)));
			mointorSelect = new JLabel(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MOINTORSELECT));
			totalDbSpace = new JCheckBox(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBTOTAL));
			dbSpace = new JCheckBox(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBUSER));
			setJPanel();
			
			/*************初始化监控周期容器******************************/
			mointorCycleJPanel = new JPanel();
			mointorCycleJPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBCYCLE)));
			mointorCycleJlabel = new JLabel(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBCYCLEMIN));
			mointorCycle = new JComboBox();
			setMointorCycle();
			setCycleJPanel(mointorCycleJPanel,mointorCycleJlabel,mointorCycle);
			
			/*************数据库总空间使用告警门容器******************************/
			mointorAlarmLimitJPanel = new JPanel();
			mointorAlarmLimitJPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBTOTALMIN)));
			totalDbAlarmLimit = new JLabel(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBTOTALTHRESHOLD));
			totalDbAlarmLimitValue = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.INT_MAXLENGTH, this.lblMessage, this.confirButton,this);
			totalDbAlarmLimitValue.setCheckingMaxValue(true);
			totalDbAlarmLimitValue.setCheckingMinValue(true);
			totalDbAlarmLimitValue.setMaxValue(94371840);
			totalDbAlarmLimitValue.setMinValue(20);
			totalDbAlarmLimitValue.setText("20");
			setCycleJPanel(mointorAlarmLimitJPanel,totalDbAlarmLimit,totalDbAlarmLimitValue);
			
			/*************数据库使用空间监控容器******************************/
			dataBaseMointView = new DataBaseMointView();
			labelTitle = ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBUSERMIN);
			dataBaseMointView.setBorder(BorderFactory.createTitledBorder(labelTitle));
			
			/*************显示按钮的布局*****************************/
			buttonJpanel = new JPanel();
			setButtonGridBagLayout();
			
			this.setTitle(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DBMINSET));
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
	}

	protected void setLayoutJpanel()
	{
		GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{30,60,50,50,200,50};
		gridBagLayout.rowWeights = new double[]{20,80,80,80,400,100};
		gridBagLayout.columnWidths = new int[]{20,20};
		gridBagLayout.columnWeights = new double[]{0,0.1,0.1,0.1,0.1,0.1};
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(3, 10,0, 5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(lblMessage, c);
		this.add(lblMessage);

		c.insets = new Insets(10, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(mointorSelectJPanel, c);
		this.add(mointorSelectJPanel);
		
		c.insets = new Insets(40, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(mointorCycleJPanel, c);
		this.add(mointorCycleJPanel);
		
		c.insets = new Insets(40, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(mointorAlarmLimitJPanel, c);
		this.add(mointorAlarmLimitJPanel);
		
		c.insets = new Insets(40, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(dataBaseMointView, c);
		this.add(dataBaseMointView);
		
		c.insets = new Insets(40,730,30, 5);
		c.fill = GridBagConstraints.EAST;
		c.gridx =0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridBagLayout.setConstraints(buttonJpanel, c);
		this.add(buttonJpanel);
	}

	/**
	 * 监控选择面板
	 * 
	 */
   private void setJPanel()
   {
		GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{10,10,10};
		gridBagLayout.rowWeights = new double[]{20,680};
		gridBagLayout.columnWidths = new int[]{20,20};
		gridBagLayout.columnWeights = new double[]{0.1,0.1,0.1};
		mointorSelectJPanel.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 10,10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(mointorSelect, c);
		mointorSelectJPanel.add(mointorSelect);

		c.insets = new Insets(20, 10, 10, 10);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(totalDbSpace, c);
		mointorSelectJPanel.add(totalDbSpace);
		
		c.insets = new Insets(20, 10, 10, 10);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(dbSpace, c);
		mointorSelectJPanel.add(dbSpace);
	   
   }
   
   private void setCycleJPanel(JPanel mianJpanel,JLabel label,Component component) 
   {
	   GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{5,5};
		gridBagLayout.rowWeights = new double[]{120,600};
		gridBagLayout.columnWidths = new int[]{120,600};
		gridBagLayout.columnWeights = new double[]{0.1,0.1,0.1};
		mianJpanel.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 20, 0, 20);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(label, c);
		mianJpanel.add(label);

		c.insets = new Insets(20, 20, 0, 20);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(component, c);
		mianJpanel.add(component);
	}
   
	private void setButtonGridBagLayout() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagLayout.rowHeights = new int[]{};
		gridBagLayout.rowWeights = new double[]{10,10};
		
		gridBagConstraints.fill = GridBagConstraints.EAST;
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weighty = 2;
		gridBagLayout.setConstraints(confirButton, gridBagConstraints);
		buttonJpanel.add(confirButton);
		
		gridBagConstraints.insets = new Insets(0, 30,0 , 0);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weighty = 2;
		gridBagLayout.setConstraints(cancelButton, gridBagConstraints);
		buttonJpanel.add(cancelButton);
		
		buttonJpanel.setLayout(gridBagLayout);
		
	}
   
	/**
	 * 赋值
	 */
	protected void setVaue() 
	{
		if(dbInfoTask == null)
		{
			dbInfoTask = new DbInfoTask();
		}else
		{
			if(dbInfoTask.isMointorTotal()){
				totalDbSpace.setSelected(true);
				totalDbAlarmLimit.setEnabled(true);
				totalDbAlarmLimitValue.setEditable(true);
				totalDbAlarmLimitValue.setText(dbInfoTask.getTotalDbSpace()+"");
			}else{
				totalDbSpace.setSelected(false);
				totalDbAlarmLimit.setEnabled(false);
				totalDbAlarmLimitValue.setEditable(false);
			}
			
			if(dbInfoTask.isMointorTypeDb()){
				dbSpace.setSelected(true);
				dataBaseMointView.getTable().setEnabled(true);
				/***********为表格赋值***********************/
			}else{
				dbSpace.setSelected(false);
				dataBaseMointView.getTable().setEnabled(false);
			}
			dataBaseMointView.refresh(dbInfoTask.getDaTableList(),1);
			mointorCycle.setSelectedItem(dbInfoTask.getMiintorCycle());
		}
	}
	
   /**
    * 设置监控周期的初始周期
    */
   private void setMointorCycle() 
   {
		for(int i =2 ; i<61 ; i++)
		{
			mointorCycle.addItem(i);
		}
	}

	/**
	 * 增加按钮的监听器
	 * 
	 */
	protected void addListener() {
		/***************取消事件********************/
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelAction();
			}
		});
		
		/**数据库总空间使用监控事件 */
		totalDbSpace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!totalDbSpace.isSelected()){
					totalDbAlarmLimit.setEnabled(false);
					totalDbAlarmLimitValue.setEditable(false);
				}else{
					totalDbAlarmLimit.setEnabled(true);
					totalDbAlarmLimitValue.setEditable(true);
				}
			}
		});
		
		/**数据库使用空间监控事件 */
		dbSpace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!dbSpace.isSelected()){
					dataBaseMointView.getTable().setEnabled(false);
				}else{
					dataBaseMointView.getTable().setEnabled(true);
				}
			}
		});
		
		/**确定按钮事件 */
		confirButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 confirm();
			}
		});
	}
   
	
	/**
	 * 取消按钮的监听器
	 */
	private void cancelAction() {
           this.dispose();		
	}
	/**
	 * 确定按钮事件
	 */
	private void confirm() 
	{
		DataBaseService_MB dataBaseService  =  null;
		try {
		    /**判断选择是否正确 */
			if(!totalDbSpace.isSelected()&& !dbSpace.isSelected())
			{
				DialogBoxUtil.errorDialog(null,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_SELECTOBJECT));
				return ;
			}
			if(totalDbSpace.isSelected())
			{
				dbInfoTask.setMointorTotal(true);
			}else
			{
				dbInfoTask.setMointorTotal(false);
			}
			dbInfoTask.setTotalDbSpace(Integer.parseInt(totalDbAlarmLimitValue.getText().trim()));
			if(dbSpace.isSelected())
			{
				dbInfoTask.setMointorTypeDb(true);
			}
			else
			{
				dbInfoTask.setMointorTypeDb(false);
			}
			List<DataBaseInfo> daTableList = getDbTable(dbInfoTask.getDaTableList(),1);
			dbInfoTask.setDaTableList(daTableList);
			dbInfoTask.setMiintorCycle(Integer.parseInt(mointorCycle.getSelectedItem().toString()));
			
			dataBaseService = (DataBaseService_MB)ConstantUtil.serviceFactory.newService_MB(Services.DATABASEINFO);
			dataBaseService.saveOrUpdate(dbInfoTask);
			
			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			UiUtil.insertOperationLog(EOperationLogType.MOINTORDB.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.dispose();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(dataBaseService);
		}
	}
	/**
	 * 获取table的数据
	 * @return
	 */
	protected List<DataBaseInfo> getDbTable(List<DataBaseInfo> datableList,int label) 
	{
		String[] splitDatas = null;
		List<DataBaseInfo> daTableList = new ArrayList<DataBaseInfo>();
		 getdbInfoId(datableList);
		try {
			
			 DefaultTableModel defaDefaultTableModel  = (DefaultTableModel) dataBaseMointView.getTable().getModel();
			 Vector vector = defaDefaultTableModel.getDataVector();
				if(vector != null && !vector.isEmpty())
				{
					for(int i = 0 ; i <vector.size();i++)
					{
					DataBaseInfo dataBaseInfo = new DataBaseInfo();
					String data = vector.get(i).toString();
					data = data.substring(1, data.length()-1);
					if(data != null && !"".equals(data))
					{
						splitDatas = data.split(",");
						dataBaseInfo.setName(splitDatas[1].trim());
						dataBaseInfo.setId((dbIdMap.get(dataBaseInfo.getName())== null?0:dbIdMap.get(dataBaseInfo.getName())));
						if(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE).equals(splitDatas[4].trim()))
						{
							dataBaseInfo.setMointorType(1);
						}else
						{
							dataBaseInfo.setMointorType(2);
						}
						dataBaseInfo.setCriticalRate(splitDatas[5].trim());
						dataBaseInfo.setMajorRate(splitDatas[6].trim());
						dataBaseInfo.setMinorRate(splitDatas[7].trim());
						dataBaseInfo.setWarningRate(splitDatas[8].trim());
						dataBaseInfo.setMointorLevel(label);
					}
					daTableList.add(dataBaseInfo);
				  }
				}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
		return daTableList;
	}
	
	/***
	 * 获取相应的数据库主键ID
	 * @param datableList
	 * @param name
	 * @return
	 */
	protected void getdbInfoId(List<DataBaseInfo> datableList){
		dbIdMap = new HashMap<String, Integer>();
		try {
			if(datableList !=null && datableList.size()>0)
			{
				for(DataBaseInfo dataBaseInfo : datableList)
				{
					if(dataBaseInfo.getId() >0){
						dbIdMap.put(dataBaseInfo.getName().trim(), dataBaseInfo.getId());
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
}
