package com.nms.ui.ptn.systemManage.monitor.view;

import java.lang.management.ManagementFactory;

import javax.swing.table.DefaultTableModel;

import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

/**
 * 
 * @author zk
 *网管客户端和服务器的进程
 */
public class ClientAndServiceCourse extends DataBasePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3607525482140586204L;

	private int lable;//用于标记是客户端还是服务器端
	
	public ClientAndServiceCourse(int label,String tableName){
		this.lable = label;
		initDataBse(tableName);
		setValue();
	}
	

	private void initDataBse(String tableName) {
		
        super.table.setModel(new DefaultTableModel(new Object[][]{},new String[]{ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLEID)
													        		,ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLEUSER)
													        		,tableName
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
	
	private void setValue() {
		DefaultTableModel defaultTableModel = null;
		try {
			defaultTableModel = (DefaultTableModel)super.table.getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();
			defaultTableModel.addRow(getObject());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	private Object[] getObject(){
		Object[] obj = null;
		String pid = "";
		try {
			//label ==1 客户端
			if(lable == 1){
				String name = ManagementFactory.getRuntimeMXBean().getName();  
				pid = name.split("@")[0];  
				obj = new Object[] {
						1, 
						pid,
						ConstantUtil.user.getUser_Name(),
						ResourceUtil.srcStr(StringKeysTitle.TIT_PTN_CLIENT),
						"Running"
				};
			}else{
				//服务器端
				DispatchUtil serviceDispatch = new DispatchUtil(RmiKeys.RMI_SERVICE);
				pid = serviceDispatch.excuteUpdate(null);
				obj = new Object[] {
						1, 
						pid,
						ConstantUtil.user.getUser_Name(),
						ResourceUtil.srcStr(StringKeysTitle.TIT_PTN_CLIENT)+ResourceUtil.srcStr(StringKeysBtn.BTN_SERVICE),
						"Running"
				};
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return obj;
	}
}
