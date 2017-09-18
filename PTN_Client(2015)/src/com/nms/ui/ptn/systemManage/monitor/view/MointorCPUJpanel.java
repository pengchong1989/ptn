package com.nms.ui.ptn.systemManage.monitor.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * <p>文件名称:MointorCPUJpanel.java</p>
 * <p>文件描述: 配置监控CPU和内存的字界面</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要: 创建、修改MointorCPUJpanel配置都使用此子对话框</p>
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
public class MointorCPUJpanel extends MointorDbTask{
	
	/***********监控cpu/内存的表格*************/
	private DataBasePanel mointorCpu ;
	
	/***********监控cpu/内存的周期标签**********/
	private JLabel cycleCpuLabel ;
	
	/***********监控cpu/内存的周期值************/
	private JComboBox cycleBox;
	
	private JPanel cpuJpane;
	
    /***********监控硬盘内存周期标签************/
	private JLabel cycleDiscLabel ;
	
	   /***********监控硬盘内存周期值************/
	private JComboBox cycleDiscBox;
	
	private JPanel discJpane;
	

	/*************初始化父类**************************/
	public MointorCPUJpanel(DbInfoTask dbInfoTask) {
		super(dbInfoTask);
	}
	
	private void initJpanel() {
		super.totalDbSpace.setText(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CPUMEMORY));
		super.dbSpace.setText(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DISC));
		/**************CPU********************/
		mointorCpu = new MointorCpu();
		cycleCpuLabel = new JLabel(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CYCLE));
		cycleBox = createCycleVaue(20,120);
		cpuJpane = new JPanel();
		cpuJpane.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CPUMEMORYSET)));
		createLayoutJpane(cpuJpane,cycleCpuLabel,cycleBox,mointorCpu);
		
		/**************硬盘监控阀值设置********************/
		cycleDiscLabel = new JLabel(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CYCLE));
		cycleDiscBox = createCycleVaue(30,300);
		discJpane = new JPanel();
		discJpane.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MEMORYSET)));
		super.dataBaseMointView.setBorder(null);
		createLayoutJpane(discJpane,cycleDiscLabel,cycleDiscBox,super.dataBaseMointView);
	}


    private void createLayoutJpane(JPanel mainJpanel,JLabel label,JComboBox box, DataBasePanel basePanel) {
		GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{130,10};
		gridBagLayout.rowWeights = new double[]{150,150,150};
		gridBagLayout.columnWidths = new int[]{20,20};
		gridBagLayout.columnWeights = new double[]{0.1,0.1,0.1,0.1};
		mainJpanel.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 10,10, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(basePanel, c);
		mainJpanel.add(basePanel);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 10, 10, 10);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridBagLayout.setConstraints(label, c);
		mainJpanel.add(label);
		
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(20, 150, 0, 10);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridBagLayout.setConstraints(box, c);
		mainJpanel.add(box);
		
	}
    
    @Override
    public  void setLayoutJpanel()
	{
    	
    	initJpanel();
		GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{30,100,230,280,150,50};
		gridBagLayout.rowWeights = new double[]{100,300,300,300,400,300};
		gridBagLayout.columnWidths = new int[]{20,20};
		gridBagLayout.columnWeights = new double[]{0,0.1,0.1,0.1,0.1,0.1,0.1};
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
		gridBagLayout.setConstraints(super.lblMessage, c);
		this.add(super.lblMessage);

		c.insets = new Insets(10, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(super.mointorSelectJPanel, c);
		this.add(super.mointorSelectJPanel);
		
		c.insets = new Insets(40, 10, 0, 5);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(cpuJpane, c);
		this.add(cpuJpane);
		
		c.insets = new Insets(30, 10, 0, 5);
		c.gridx =0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridBagLayout.setConstraints(discJpane, c);
		this.add(discJpane);
		
		c.insets = new Insets(40,730,10, 5);
		c.fill = GridBagConstraints.EAST;
		c.gridx =0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridBagLayout.setConstraints(super.buttonJpanel, c);
		this.add(super.buttonJpanel);
	}


	/**
     * 创建一个范围内的周期值
     * @return
     */
	private JComboBox createCycleVaue(int start , int end) {
		JComboBox cycleAllBox = new JComboBox();
		for(int i=start; i<end+1;i++){
			cycleAllBox.addItem(i);
		}
		return cycleAllBox;
	}
	
	
	@Override
	public void addListener(){
		
		/**CPU监控事件 */
		super.totalDbSpace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!totalDbSpace.isSelected()){
					mointorCpu.getTable().setEnabled(false);
					cycleBox.setEnabled(false);
				}else{
					mointorCpu.getTable().setEnabled(true);
					cycleBox.setEnabled(true);
				}
			}
		});
		
		/**磁盘空间监控事件 */
		dbSpace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!dbSpace.isSelected()){
					dataBaseMointView.getTable().setEnabled(false);
					cycleDiscBox.setEnabled(false);
				}else{
					dataBaseMointView.getTable().setEnabled(true);
					cycleDiscBox.setEnabled(true);
				}
			}
		});
		
		/***************取消事件********************/
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelAction();
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
				//CPU
				dbInfoTask.setMointorTotal(true);
			}else
			{
				dbInfoTask.setMointorTotal(false);
			}
			//监控CPU周期
			dbInfoTask.setTotalDbSpace(Integer.parseInt(cycleBox.getSelectedItem().toString()));
			
			if(dbSpace.isSelected())
			{
				dbInfoTask.setMointorTypeDb(true);
			}
			else
			{
				dbInfoTask.setMointorTypeDb(false);
			}
			List<DataBaseInfo> daTableList = super.getDbTable(dbInfoTask.getDaTableList(),2);
			this.getCPUdateList(daTableList);
			dbInfoTask.setDaTableList(daTableList);
			//监控内存的周期
			dbInfoTask.setMiintorCycle(Integer.parseInt(cycleDiscBox.getSelectedItem().toString()));
			
			dataBaseService = (DataBaseService_MB)ConstantUtil.serviceFactory.newService_MB(Services.DATABASEINFO);
			dataBaseService.saveOrUpdate(dbInfoTask);
			
			DialogBoxUtil.succeedDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			UiUtil.insertOperationLog(EOperationLogType.MOINTORSERVICEDB.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.dispose(); 
			
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(dataBaseService);
		}
	}
	
	/**
	 * 取消按钮的监听器
	 */
	private void cancelAction() 
	{
           this.dispose();		
	}
	
	/**
	 * 重载 为CPU设置的 赋值
	 */
	@Override
	public void setVaue()
	{
		if(dbInfoTask == null)
		{
			dbInfoTask = new DbInfoTask();
		}else
		{
			if(dbInfoTask.isMointorTotal())
			{
				totalDbSpace.setSelected(true);
				mointorCpu.getTable().setEnabled(true);
				cycleBox.setEnabled(true);
				cycleBox.setSelectedItem(dbInfoTask.getTotalDbSpace());
				/***********为表格赋值***********************/
			}else
			{
				totalDbSpace.setSelected(false);
				mointorCpu.getTable().setEnabled(false);
				cycleBox.setEnabled(false);
			}
			mointorCpu.refresh(dbInfoTask.getDaTableList(),0);
			
			if(dbInfoTask.isMointorTypeDb())
			{
				dbSpace.setSelected(true);
				cycleDiscBox.setEnabled(true);
				cycleDiscBox.setSelectedItem(dbInfoTask.getMiintorCycle());
				dataBaseMointView.getTable().setEnabled(true);
				/***********为表格赋值***********************/
			}else
			{
				dbSpace.setSelected(false);
				dataBaseMointView.getTable().setEnabled(false);
				cycleDiscBox.setEnabled(false);
			}
			
			dataBaseMointView.refresh(dbInfoTask.getDaTableList(),0);
	 }
	}
	
	private void getCPUdateList(List<DataBaseInfo> datableList) 
	{
		String[] splitDatas = null;
		Map<String, Integer> dbIdMap = super.dbIdMap;
		try {
			
			 DefaultTableModel defaDefaultTableModel  = (DefaultTableModel) mointorCpu.getTable().getModel();
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
						dataBaseInfo.setCriticalRate(splitDatas[3].trim());
						dataBaseInfo.setMajorRate(splitDatas[4].trim());
						dataBaseInfo.setMinorRate(splitDatas[5].trim());
						dataBaseInfo.setWarningRate(splitDatas[6].trim());
						dataBaseInfo.setMointorLevel(3);
					}
					datableList.add(dataBaseInfo);
				  }
				}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
	}
}
