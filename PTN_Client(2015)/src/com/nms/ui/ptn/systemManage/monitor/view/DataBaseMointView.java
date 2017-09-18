package com.nms.ui.ptn.systemManage.monitor.view;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.db.bean.system.DataBaseInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * <p>文件名称:DataBaseMointView.java</p>
 * <p>文件描述: 配置监控数据库每张表的配置</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要: 创建、修改DataBaseMointView配置列表</p>
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
public class DataBaseMointView extends DataBasePanel {

	
	//******监控类型 ***************/
	private JComboBox mointorType;
	//******监控类型 ***************/
	private JTextField criticalField;
	//******监控类型 ***************/
	private JTextField majorField;
	//******监控类型 ***************/
	private JTextField minorField;
	//******监控类型 ***************/
	private JTextField warningField;
	//******库名***************/
	private JTextField dbName;
	
	
	//****** 代码段: 构造方法 *******************************************************************************/
	public DataBaseMointView()
	{
		initDataBase();
		configSpinner();
	}

	@Override
	public void refresh(List<DataBaseInfo> dataBaseInfoList,int label)
	{
		 ((DefaultTableModel)this.table.getModel()).getDataVector().clear();
		 ((DefaultTableModel)this.table.getModel()).fireTableDataChanged();
		 Object data[] = new Object[] {};
		 int rowCount = 0;
		 DecimalFormat    df   = new DecimalFormat("######0.00"); 
		 if(dataBaseInfoList != null && dataBaseInfoList.size()>0)
		 {
			 for (DataBaseInfo dataBaseInfo : dataBaseInfoList) 
			 {
					 if(dataBaseInfo.getMointorLevel() == 1)
					 {
						 double  totalSzie = (Double) (dataBaseInfo.getCountSize() == 0?(dataBaseInfo.getDataSize()+dataBaseInfo.getFreeSize()+dataBaseInfo.getIndexSize()):dataBaseInfo.getCountSize());
						 try {
							 data = new Object[] { ++rowCount
									 ,dataBaseInfo.getName()
									 ,(dataBaseInfo.getDataSize()+"/"+totalSzie)
									 , df.format((dataBaseInfo.getDataSize()/totalSzie)*100)
									 ,dataBaseInfo.getMointorType()== 1?ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE):ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE1)
									 ,dataBaseInfo.getCriticalRate()
									 ,dataBaseInfo.getMajorRate(),
									  dataBaseInfo.getMinorRate(), 
									  dataBaseInfo.getWarningRate() };
							 ((DefaultTableModel)this.table.getModel()).addRow(data);
						 } catch (Exception e) 
						 {
							 ExceptionManage.dispose(e, getClass());
						 }
						 
					 }else if(dataBaseInfo.getMointorLevel() == 3){
						 double countMemory = dataBaseInfo.getCountMemory()/1048576;
						 double useMemory = dataBaseInfo.getUseMemory()/1048576;
						 try {
							 data = new Object[] { ++rowCount
									 ,dataBaseInfo.getName()
									 ,df.format(useMemory)+"/"+Math.rint(countMemory)
									 ,dataBaseInfo.getCountSize()
									 ,dataBaseInfo.getMointorType()== 1?ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE):ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE1)
									 ,dataBaseInfo.getCriticalRate()
									 ,dataBaseInfo.getMajorRate()
									 ,dataBaseInfo.getMinorRate()
									 ,dataBaseInfo.getWarningRate() };
							 ((DefaultTableModel)this.table.getModel()).addRow(data);
						 } catch (Exception e) 
						 {
							 ExceptionManage.dispose(e, getClass());
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
		TableColumn  mointorColumn = table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_TYPE));
		mointorType = new JComboBox();
		mointorType.addItem(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE));
		mointorType.addItem(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_VALUE1));
		mointorColumn.setCellEditor(new DefaultCellEditor(mointorType));
		criticalField = new JTextField();
		majorField = new JTextField();
		minorField = new JTextField();
		warningField = new JTextField();
		dbName = new JTextField();
		dbName.setEditable(false);
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_OBJECT)).setCellEditor(new DefaultCellEditor(dbName));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CIRCALE)).setCellEditor(new DefaultCellEditor(criticalField));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MAJOR)).setCellEditor(new DefaultCellEditor(majorField));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MINOR)).setCellEditor(new DefaultCellEditor(minorField));
		table.getColumn(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_WARNING)).setCellEditor(new DefaultCellEditor(warningField));
	}

	/**
	 *设置表格列表 
	 */
	private void initDataBase() 
	{
		
		  super.table.setModel(new DefaultTableModel(new Object[][]{},new String[]{ResourceUtil.srcStr(StringKeysTip.COURSEINFOTABLENUMBER)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_OBJECT)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERMEMORY)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERRATE)+"%"
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_TYPE)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_CIRCALE)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MAJOR)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_MINOR)
				  ,ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_WARNING)
		         }){
				@SuppressWarnings("rawtypes")
				Class[] types = new Class[]{java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
					                        java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
					                        java.lang.Object.class};
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
	
	/**
	 * 返回一个表格面板
	 */
	public JTable getTable()
	{
		return super.getTable();
	}

	public JComboBox getMointorType() 
	{
		return mointorType;
	}

	public void setMointorType(JComboBox mointorType) 
	{
		this.mointorType = mointorType;
	}

	public JTextField getCriticalField() 
	{
		return criticalField;
	}

	public void setCriticalField(JTextField criticalField) 
	{
		this.criticalField = criticalField;
	}

	public JTextField getMajorField() 
	{
		return majorField;
	}

	public void setMajorField(JTextField majorField) 
	{
		this.majorField = majorField;
	}

	public JTextField getMinorField() 
	{
		return minorField;
	}

	public void setMinorField(JTextField minorField) 
	{
		this.minorField = minorField;
	}

	public JTextField getWarningField() 
	{
		return warningField;
	}

	public void setWarningField(JTextField warningField) 
	{
		this.warningField = warningField;
	}

	public JTextField getDbName() 
	{
		return dbName;
	}

	public void setDbName(JTextField dbName) 
	{
		this.dbName = dbName;
	}
	
}
