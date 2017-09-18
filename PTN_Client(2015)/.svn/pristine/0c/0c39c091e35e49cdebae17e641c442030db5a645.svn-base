package com.nms.ui.ptn.systemManage.monitor.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.DataBaseInfo;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 
 * @author zk
 * 数据库进程信息
 */
public class CourseInofPanel extends DataBasePanel{
	
  /**
	 * ClientAndServiceCourseInof
	 */
	private static final long serialVersionUID = -6178149754598369239L;

public CourseInofPanel(){
	   initDataBse();
	   setValue(getAllCourse());
  }

private void initDataBse() {
	
        super.table.setModel(new DefaultTableModel(new Object[][]{},new String[]{ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLEID)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLEUSER)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLTABLE)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLRUNNING)}){
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[]{java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class};
		@Override
		public Class getColumnClass(int columnIndex){
			return types[columnIndex];
		}
		@Override
		public boolean isCellEditable(int rowIndex,int columnIndex){
			if(rowIndex == 1){
				return false;
			}
			return true;
		}
	});
}

//为界面赋值
 private void setValue(List<DataBaseInfo> dataBaseInfoList){
	 
	DefaultTableModel defaultTableModel = null;
	try {
		Properties props = new Properties();
		InputStream propsIs = CourseInofPanel.class.getClassLoader().getResourceAsStream("config/config.properties");
		props.load(propsIs);
		String userName = props.getProperty("jdbc.username");
		String dataBaseName = props.getProperty("jdbc.url").substring(props.getProperty("jdbc.url").lastIndexOf("?")-3, props.getProperty("jdbc.url").lastIndexOf("?"));
		defaultTableModel = (DefaultTableModel)super.table.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		Object[] obj = null;
		if(dataBaseInfoList != null && dataBaseInfoList.size()>0){
			for(int i = 0 ; i< dataBaseInfoList.size(); i++){
				DataBaseInfo dataBaseInfo = dataBaseInfoList.get(i);
				obj = new Object[] {
						i+1, 
						(int)dataBaseInfo.getCountSize(),
						userName,
						dataBaseName,
						dataBaseInfo.getName()
				};
				defaultTableModel.addRow(obj);
			}
		}
	} catch (Exception e) {
		ExceptionManage.dispose(e, getClass());
	}
 };

 //查询关于数据库的进程信息
  private  List<DataBaseInfo> getAllCourse()
  {
	List<DataBaseInfo> dataBaseInfoList = new ArrayList<DataBaseInfo>();
	DispatchUtil serviceDispatch = null;
	  try {
		serviceDispatch = new DispatchUtil(RmiKeys.RMI_SERVICE);
		String result = serviceDispatch.excuteInsert(null);
		DataBaseInfo dataBaseInfo = new DataBaseInfo();
		dataBaseInfo.setName(result.trim().split(",")[1]);
		dataBaseInfo.setCountSize(Double.parseDouble(result.trim().split(",")[0]));
		dataBaseInfoList.add(dataBaseInfo);
	} catch (Exception e) {
		ExceptionManage.dispose(e, getClass());
	}finally{
		serviceDispatch = null;
	}
	return dataBaseInfoList;
 }
}
