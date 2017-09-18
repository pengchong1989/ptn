package com.nms.ui.ptn.ne.eth.view.dialog.wh;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;

public class PortPriDialog extends  JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3866081193407401162L;
	private PortInst portInst;
	
	private PtnButton btnSave; // 确定按钮
//	private JButton btnCanel; // 取消按钮
	private JPanel panelBtn; // 按钮布局
	
	private JTable table; // 数据表
	private JScrollPane tableScroller;//数据表滚动条
	
	private JLabel lblName;// 名称标签
	private JTextField txtName;//名称对话框
	private JPanel panelName; //表布局
	
	public PortPriDialog(PortInst portInst) {
		this.portInst = portInst;
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysMenu.MENU_PRIORITY_PRI)));
		try {
			initComponents();
			initTableUI();
			UiUtil.jTableColumsHide(table, 1);
			setLayout();
			this.initData();
			addListeners();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@SuppressWarnings("serial")
	private void initComponents()throws Exception{
//		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.panelBtn = new JPanel();
		//数据表格
		this.table = new JTable(); 
		table.setModel(new DefaultTableModel(
				new Object[][]{},
				new String[]{"",ResourceUtil.srcStr(StringKeysObj.ORDER_NUM),ResourceUtil.srcStr(StringKeysObj.OBJ_LEVEL),
						ResourceUtil.srcStr(StringKeysObj.OBJ_LEVEL_CODE)})//序号,等级,等级值
		{
			@SuppressWarnings("unchecked")
			Class[] types = new Class[] {java.lang.Object.class,
										 java.lang.Object.class, 
										 java.lang.Object.class,
										 java.lang.Object.class};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 1 || columnIndex == 2)
					return false;
				return true;
			}
		}
		);
		table.getTableHeader().setResizingAllowed(true);//允许客户通过在头间拖动来调整各列的大小
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.tableScroller = new JScrollPane();
		tableScroller.setViewportView(table);
		tableScroller.setPreferredSize(new Dimension(0,160));
		this.lblName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		this.txtName = new JTextField();
		txtName.setEditable(false);
		this.panelName = new JPanel();
		
	}

	/**
	 * 让表格中的每个等级的值成为下拉列表的格式
	 * @throws Exception
	 */
	private void initTableUI()throws Exception {
		JComboBox cmbValue = new JComboBox();
		
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
		int grade = 8;
		for (int i = 0; i < grade; i++) {
			defaultComboBoxModel.addElement(i+"");
		}
		cmbValue.setModel(defaultComboBoxModel);
		TableColumn valueColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.OBJ_LEVEL_CODE));
		valueColumn.setCellEditor(new DefaultCellEditor(cmbValue));
	}
	
	/**
	 * 设置布局
	 */
	private void setLayout()throws Exception {
		this.setButtonLayout();
		this.setTextLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 40,620,40 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40, 120, 40 };
		componentLayout.rowWeights = new double[] { 0, 0.2 };
		this.setLayout(componentLayout);                                                                                             

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridx = 1;
		c.gridy = 0;
		componentLayout.setConstraints(this.panelName, c);
		this.add(this.panelName);
		
		c.gridy = 1;
		componentLayout.setConstraints(this.tableScroller, c);
		this.add(this.tableScroller);
		
		c.gridy = 2;
		componentLayout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
		
	}

	/**
	 * 设置按钮布局
	 */
	private void setButtonLayout() throws Exception{
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {570};
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 10 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
	}

	/**
	 * 设置文本框布局
	 */
	private void setTextLayout()throws Exception {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 570 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelName.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.lblName, c);
		this.panelName.add(this.lblName);

		c.gridx = 1;
		c.ipadx = 150;
		c.anchor = GridBagConstraints.WEST;
		componentLayout.setConstraints(this.txtName, c);
		this.panelName.add(this.txtName);
		
	}

	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initData() throws Exception{
		try {
			if(portInst == null ||portInst.getPriority() == null || portInst.getPriority().equals("")){
				initTableDataAdd();
			}else{
				initTableData(portInst);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void initTableDataAdd() {
		
		this.txtName.setText(this.portInst.getPortName());
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel)table.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		String[] grades = {"BE","AF1","AF2","AF3","AF4","EF","CS6","CS7"};
		Object[] obj = null;
		for (int i = 0; i < grades.length; i++) {
			obj = new Object[] {"", i+1,grades[i],i};
			defaultTableModel.addRow(obj);
		}
		table.setModel(defaultTableModel);
//		TableColumn valueColumn = table.getColumn(ResourceUtil.srcStr(StringKeysObj.OBJ_LEVEL_CODE));
//		System.out.println(valueColumn.getCellEditor().getCellEditorValue());
		;
	}

	private void initTableData(PortInst portInst)  throws Exception{
		this.txtName.setText(this.portInst.getPortName());
		
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel)table.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		String[] grades = {"BE","AF1","AF2","AF3","AF4","EF","CS6","CS7"};
		String[] values = portInst.getPriority().split(" ");
		Object[] obj = null;
		for (int i = 0; i < grades.length; i++) {
			obj = new Object[] {"", i+1,grades[i],String.valueOf(values[i])};
			defaultTableModel.addRow(obj);
		}
		table.setModel(defaultTableModel);	
	}

	/**
	 * 添加监听事件
	 * @throws Exception
	 */
	private void addListeners() throws Exception{
//		this.btnCanel.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent evt) {
////				dispose();
//			}
//		});

		this.btnSave.addActionListener(new MyActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnSaveListener();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				return true;
			}

		});
	}

	/**
	 * 保存按钮事件
	 * @throws Exception
	 */
	protected void btnSaveListener() throws Exception{
		//收集界面数据
		DispatchUtil protDispatchUtil = null;
		String result = null;
		try {
			PortInst portBefore = new PortInst();
			this.getPriorityList(this.portInst.getPriority(), portBefore);
			String value = getTableData();
			portInst.setPriority(value);
			portInst.setIsPriority(1);
			protDispatchUtil = new DispatchUtil(RmiKeys.RMI_PORT);
			result = protDispatchUtil.excuteUpdate(portInst);
			DialogBoxUtil.succeedDialog(this,result);
			this.getPriorityList(this.portInst.getPriority(), this.portInst);
			AddOperateLog.insertOperLog(btnSave, EOperationLogType.PORTPRI.getValue(), result, portBefore, this.portInst,
					ConstantUtil.siteId, portInst.getPortName()+"_"+ResourceUtil.srcStr(StringKeysMenu.MENU_PRIORITY_PRI), "priority");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	private void getPriorityList(String priority, PortInst portInst){
		if(priority != null){
			List<CommonBean> nameValueList = new ArrayList<CommonBean>();
			String[] nameArr = {"BE","AF1","AF2","AF3","AF4","EF","CS6","CS7"};
			String[] valueArr = priority.split(" ");
			for (int i = 0; i < valueArr.length; i++) {
				CommonBean cb = new CommonBean();
				cb.setCosName(nameArr[i]);
				cb.setCosValue(valueArr[i]);
				nameValueList.add(cb);
			}
			portInst.setPriorityList(nameValueList);
		}
	}

	@SuppressWarnings("unchecked")
	private String getTableData() throws Exception{
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		Vector vector = tableModel.getDataVector();
		Iterator it = vector.iterator();
		StringBuffer values = new StringBuffer();
 		while (it.hasNext()) {
			Vector temp = (Vector) it.next();
			values.append(temp.get(3).toString()+" ");
		}
		return values.toString().trim();
	}
}
