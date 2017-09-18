package com.nms.ui.ptn.systemManage.monitor.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.DataBaseInfo;
import com.nms.model.system.DataBaseService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.CheckBoxPanel;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 
 * @author zk
 * 数据库表信息
 */
public class DataBaseTableInfoPanel extends DataBasePanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5839497109574103104L;
	public DataBaseTableInfoPanel(){
		 initDataBse();
		 setValue();
	}

	private void initDataBse() {
		  super.table.setModel(new DefaultTableModel(new Object[][]{},new String[]{ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)
				  ,ResourceUtil.srcStr(StringKeysTip.TABLENAME)
				  ,ResourceUtil.srcStr(StringKeysTip.COUNTSIZE)
				  ,ResourceUtil.srcStr(StringKeysTip.KEEPSPACE)
				  ,ResourceUtil.srcStr(StringKeysTip.DATASPACE)
				  ,ResourceUtil.srcStr(StringKeysTip.INDEXSPACE)
		         }){
				@SuppressWarnings("rawtypes")
				Class[] types = new Class[]{java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class};
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
	//为表格赋值
	public void setValue(){
		
		DataBaseService_MB dataBaseService  =  null;
    	List<DataBaseInfo> dataBaseInfoList = null;
    	CheckBoxPanel checkBoxPanel = new CheckBoxPanel();
    	List<String> tablesName = new ArrayList<String>();
    	try {
    		
    		dataBaseInfoList = new ArrayList<DataBaseInfo>();
    		dataBaseService = (DataBaseService_MB)ConstantUtil.serviceFactory.newService_MB(Services.DATABASEINFO);
    		String[] tables = new String[]{ServerConstant.BACKUPS_ALL,ServerConstant.BACKUPS_USER,ServerConstant.BACKUPS_SITE};
    		
    		for (int i = 0; i<tables.length;i++) {
    			tablesName.addAll(checkBoxPanel.getTableNameForXml(tables[i]));
			}
    		for(String tableName : tablesName){
    			dataBaseInfoList.add(dataBaseService.selectTableInfo(tableName,2));
    		}
    		super.refresh(dataBaseInfoList,2);
    		
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(dataBaseService);
			dataBaseInfoList = null;
			checkBoxPanel = null;
		}
	}
}
