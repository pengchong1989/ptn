package com.nms.ui.ptn.systemManage.monitor.view;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.DataBaseInfo;
import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * 数据库信息 基本类
 * @author zk
 */
public class DataBasePanel extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8967751316333668407L;
	protected JTable table;
	
	public DataBasePanel(){
		init();
	}

	private void init() {
		
		table = new JTable();
		table.getTableHeader().setResizingAllowed(true);//允许客户通过在头间拖动来调整各列的大小
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.setViewportView(table);
	}
	
	//为界面赋值
	protected void refresh(List<DataBaseInfo> dataBaseInfoList,int label){
		
		DefaultTableModel defaultTableModel = null;
    	try {
    		//DataBaseInfo
    		defaultTableModel = (DefaultTableModel)table.getModel();
    		defaultTableModel.getDataVector().clear();
    		defaultTableModel.fireTableDataChanged();
    		Object[] obj = null;
    		if(dataBaseInfoList != null && dataBaseInfoList.size()>0){
    			for(int i = 0 ; i< dataBaseInfoList.size(); i++){
    				DataBaseInfo dataBaseInfo = dataBaseInfoList.get(i);
    				obj = new Object[] {
    						i+1, 
    						dataBaseInfo.getName(), 
    						label == 1 ?(dataBaseInfo.getCountSize() == 0?(dataBaseInfo.getDataSize()+dataBaseInfo.getFreeSize()+dataBaseInfo.getIndexSize())+"MB":dataBaseInfo.getCountSize()+"MB")
    							        :(int)dataBaseInfo.getCountSize(),
    					    label == 1? (dataBaseInfo.getFreeSize()+"MB"):dataBaseInfo.getFreeSize(), 
    					    label == 1? (dataBaseInfo.getDataSize()+"MB"):dataBaseInfo.getDataSize(), 
    					    label == 1? (dataBaseInfo.getIndexSize()+"MB"):dataBaseInfo.getIndexSize(), 
    				};
    				defaultTableModel.addRow(obj);
    			}
    		}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	};
}
