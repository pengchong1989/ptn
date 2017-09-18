package com.nms.ui.ptn.systemManage.monitor.view;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.DataBaseInfo;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class MointorCpu extends DataBasePanel{
	
	
	//****** 代码段: 构造方法 *******************************************************************************/
	public MointorCpu()
	{
		initDataBase();
		configSpinner();
	}
	
	@Override
	public void refresh(List<DataBaseInfo> dataBaseInfoList,int label )
	{
		 ((DefaultTableModel)this.table.getModel()).getDataVector().clear();
		 ((DefaultTableModel)this.table.getModel()).fireTableDataChanged();
		 Object data[] = new Object[] {};
		 int rowCount = 0;
		if(dataBaseInfoList != null && dataBaseInfoList.size()>0){
			for (DataBaseInfo dataBaseInfo : dataBaseInfoList) 
			{
				if(dataBaseInfo.getMointorLevel() == 2 || dataBaseInfo.getMointorLevel() == 4){
					try {
						data = new Object[] { 
								++rowCount
								,dataBaseInfo.getName()
								,dataBaseInfo.getCountSize()
								,dataBaseInfo.getCriticalRate()
								,dataBaseInfo.getMajorRate()
								,dataBaseInfo.getMinorRate()
								,dataBaseInfo.getWarningRate() 
								};
						((DefaultTableModel)this.table.getModel()).addRow(data);
					} catch (Exception e) 
					{
					}
				}
			}
		}
	}
	
	/*
	 * 对设置带宽的列的单元格添加Spinner
	 */
	public void configSpinner()
	{
		JTable table =  super.table;
		JTextField dbName = new JTextField();
		dbName.setEditable(false);
		//ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_SERVICEINFO)
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_OBJECT)).setCellEditor(new DefaultCellEditor(dbName));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CIRCALE)).setCellEditor(new DefaultCellEditor(new JTextField()));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MAJOR)).setCellEditor(new DefaultCellEditor(new JTextField()));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MINOR)).setCellEditor(new DefaultCellEditor(new JTextField()));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_WARNING)).setCellEditor(new DefaultCellEditor(new JTextField()));
	}
	/**
	 *设置表格列表 
	 */
	private void initDataBase() 
	{
		
		  super.table.setModel(new DefaultTableModel(new Object[][]{},new String[]{ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_OBJECT)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERRATE)+"%"
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CIRCALE)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MAJOR)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MINOR)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_WARNING)
		         }){
				@SuppressWarnings("rawtypes")
				Class[] types = new Class[]{java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
					                        java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
					                        };
				@Override
				public Class getColumnClass(int columnIndex)
				{
					return types[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex,int columnIndex)
				{
					if(rowIndex == 1)
					{
						return true;
					}
					return true;
				}
			});
	}
	
	public JTable getJTable(){
		return super.table;
	}
	
}
