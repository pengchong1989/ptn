package com.nms.ui.ptn.systemManage.monitor.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import com.nms.model.util.ExportExcel;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysTip;

public class DataInfoAllPanel extends PtnDialog{
 
	private static final long serialVersionUID = -2363933178454847355L;
	private JTabbedPane tabbedPanl ;
    private JPanel bottonPanel ;
    private DataBasePanel dataBaseInfoPanel;//数据库信息
    private DataBasePanel dataBaseTableInfoPanel;//数据表信息
    private DataBasePanel courseInfoPanel;
    private DataBasePanel emsPanel;
    private DataBasePanel servicePanel;
	private JButton exportButton;
	private JButton closeButton;
	private int label;
	
	
    public DataInfoAllPanel(int label){
    	this.label = label;
    	init();
    	addListener();
    	UiUtil.showWindow(this,700, 450);
    }

	//初始化
	private void init() {
		
		tabbedPanl = new JTabbedPane();
		/***************当标记为 1时;代表是查询数据库******************************/
		if(label == 1){
			this.setTitle(ResourceUtil.srcStr(StringKeysTip.DATABASE));
			dataBaseInfoPanel = new DataBaseInfoPanel();
			dataBaseTableInfoPanel = new DataBaseTableInfoPanel();
			courseInfoPanel = new CourseInofPanel();
			emsPanel = new ClientAndServiceCourse(1,ResourceUtil.srcStr(StringKeysTip.EMSCLIENTINFO));
			servicePanel = new ClientAndServiceCourse(2,ResourceUtil.srcStr(StringKeysTip.SERVICE));
			tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.DATABASEINFO), dataBaseInfoPanel);
			tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.DATABASETABLE), dataBaseTableInfoPanel);
			tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.COURSEINFO), courseInfoPanel);
			tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.EMSCLIENT), emsPanel);
			tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.SERVICESEINFO), servicePanel);
		}
		/***************当标记不为 1时;代表是服务器内存和CPU值******************************/
		else
		{
			DispatchUtil serviceDispatch = null;
			Map<Integer, Object> servicePerformance = null;
			try {
				serviceDispatch = new DispatchUtil(RmiKeys.RMI_SERVICE);
				servicePerformance = (Map<Integer, Object>) serviceDispatch.consistence(label);
				this.setTitle(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_SERVICEINFO)+ConstantUtil.serviceIp);
				tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CPUINFO), new CpuAndMemoryJpanel(1,servicePerformance.get(1)));
				tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MEMORYINFO), new CpuAndMemoryJpanel(2,servicePerformance.get(2)));
				tabbedPanl.add(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_DISCINFO), new CpuAndMemoryJpanel(3,servicePerformance.get(3)));
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				serviceDispatch = null;
			}
		}
		bottonPanel = new JPanel();
		exportButton = new JButton(ResourceUtil.srcStr(StringKeysTip.EXPORTDATA));
		closeButton = new JButton(ResourceUtil.srcStr(StringKeysTip.CLOSE));
		
		//设置按钮布局
		setButtonGridBagLayout();
		//设置主页布局
		setGridBagLayout();
		
	}

	private void setGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		this.setLayout(gridBagLayout);
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowHeights = new int[] {400,50};
		gridBagLayout.rowWeights = new double[] { 0.1,0};
		
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagLayout.setConstraints(tabbedPanl, gridBagConstraints);
		this.add(tabbedPanl);
		
		gridBagConstraints.fill = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(5, 400, 10, 0);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagLayout.setConstraints(bottonPanel, gridBagConstraints);
		
		this.add(bottonPanel);
	}

	private void setButtonGridBagLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagLayout.rowHeights = new int[]{};
		gridBagLayout.rowWeights = new double[]{10,10};
		
		gridBagConstraints.fill = GridBagConstraints.EAST;
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weighty = 2;
		gridBagLayout.setConstraints(exportButton, gridBagConstraints);
		bottonPanel.add(exportButton);
		
		gridBagConstraints.insets = new Insets(0, 30,0 , 0);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weighty = 2;
		gridBagLayout.setConstraints(closeButton, gridBagConstraints);
		bottonPanel.add(closeButton);
		
		bottonPanel.setLayout(gridBagLayout);
		
	}
   //监听事件
    private void addListener() {
    	
    	closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//取消关闭界面
				closeActionListener();
			}
		});
    	
    	exportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exportData();
			}
		});
	}
    
	private void closeActionListener() {
		this.dispose();
	}
	 
   //导出数据
	private void exportData() {
		
		try {
			Component selectTabbed = (Component)tabbedPanl.getSelectedComponent();
			//数据库信息/数据库表信息/数据库进程信息
			if(selectTabbed instanceof DataBasePanel){
				DataBasePanel dataBaseInfo = (DataBasePanel)selectTabbed;
				export(dataBaseInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		
	}
	
	private void export(DataBasePanel dataBaseInfo){
		
		ExportExcel exportExcel = null;
		List<String> headNames = null;
		List<String[]> listData = null;
		String[] datas = null;
		String[] splitDatas = null;
		int label = 0;
		try {
			headNames = new ArrayList<String>();
			listData = new ArrayList<String[]>();
			exportExcel = new ExportExcel();
			DefaultTableModel defaultTableModel = (DefaultTableModel) dataBaseInfo.getTable().getModel();
			headNames.add(ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER));
			for(int i=0 ; i<defaultTableModel.getColumnCount(); i++){
				if(!defaultTableModel.getColumnName(i).equals(ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)))
				{
					headNames.add(defaultTableModel.getColumnName(i));
				}else
				{
					label = 1;
				}
			}
			Vector vector = defaultTableModel.getDataVector();
			if(vector != null && !vector.isEmpty()){
				for(int i = 0 ; i <vector.size();i++){
				String data = vector.get(i).toString();
				data = data.substring(1, data.length()-1);
				if(data != null && !"".equals(data)){
					splitDatas = data.split(",");
					datas = new String[splitDatas.length];
					if(label == 1)
					{
					 System.arraycopy(splitDatas, 1, datas, 0, datas.length-1);
					}else
					{
					 System.arraycopy(splitDatas, 0, datas, 0, datas.length);
					}
					listData.add(datas);
				}
			  }
			}
			exportExcel.exportExcel(listData,headNames);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			 exportExcel = null;
			 headNames = null;
			 listData = null;
			 datas = null;
			 splitDatas = null;
		}
	}
	
}
