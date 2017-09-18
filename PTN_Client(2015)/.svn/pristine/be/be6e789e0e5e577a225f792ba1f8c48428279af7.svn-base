package com.nms.ui.ptn.systemconfig.Template.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.enums.EMappingColorEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class ExpToColorInfoPanel extends PtnPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1594834876810227661L;
	private JTable legTable; //table
	private JScrollPane legJScrollPanel; //table面板
	private JPanel upPanel; //选项面板
	private QosMappingMode qosMappingMode; //模板
	
	/**
	 * 创建一个新实例
	 * @param qosMappingMode	模板
	 */
	public ExpToColorInfoPanel(QosMappingMode qosMappingMode){
		try{
			this.qosMappingMode=qosMappingMode;
			initComponents();
			jTableColumsHide(legTable,0);
			setUpLayout();
			setLayout();
			setDefaultTable();
			initData();
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化数据
	 * @throws Exception 
	 */
	private void initData() throws Exception{
		if(null!=qosMappingMode){
			this.txtName.setText(qosMappingMode.getName());
			super.getComboBoxDataUtil().comboBoxData(this.cmbDirection, "EXPDIRECTION");
			super.getComboBoxDataUtil().comboBoxData(this.cmbType, "EXPTYPE");
			super.getComboBoxDataUtil().comboBoxSelect(this.cmbDirection, qosMappingMode.getQosMappingAttrList().get(0).getDirection()+"");
			super.getComboBoxDataUtil().comboBoxSelect(this.cmbType, qosMappingMode.getQosMappingAttrList().get(0).getModel()+"");
		}
	}
	
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	private void initComponents() throws Exception {
		String column = ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR);
		//如果是llsp
		if(null!=qosMappingMode){
			if(555==this.qosMappingMode.getQosMappingAttrList().get(0).getModel()){
				column = ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR);
			}
			//Elsp
			else{
				column = "COS";
			}
		}
		upPanel=new JPanel();
		legTable=new JTable();
		legJScrollPanel = new JScrollPane();
		legJScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		legJScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		legTable.getTableHeader().setResizingAllowed(true);
		legTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		legTable.setModel(new DefaultTableModel(new Object[][] {
				
		}, new String[] { ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), "EXP", column }) {
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] {  java.lang.Object.class, java.lang.Object.class ,java.lang.Object.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 0 )
					return false;
				return true;
			}
		}

		);
		legJScrollPanel.setViewportView(legTable);
		
		lblName=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		txtName=new JTextField();
		lblType=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		cmbType=new JComboBox();
		lblDirection=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DIR));
		cmbDirection=new JComboBox();
		
		
		this.txtName.setEditable(false);
		this.cmbDirection.setEnabled(false);
		this.cmbType.setEnabled(false);
		this.legTable.setEnabled(false);
	}
	
	/**
	 * 选取默认值填入支路信息表中
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void setDefaultTable() throws NumberFormatException, Exception {
		if(null!=qosMappingMode){
		List<QosMappingAttr> qosMappingAttrList=qosMappingMode.getQosMappingAttrList();
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel) legTable.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		Object[] obj = null;
		for(int i=0;i<qosMappingAttrList.size();i++){
			if(UiUtil.getCodeByValue("EXPTYPE", "0").getId()==UiUtil.getCodeById(qosMappingAttrList.get(i).getModel()).getId()){
				obj = new Object[] { i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),"YELLOW".equals(EMappingColorEnum.from(qosMappingAttrList.get(i).getColor()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN)};
			}else{
				obj = new Object[] {i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),QosCosLevelEnum.from(Integer.parseInt(qosMappingAttrList.get(i).getGrade()))};
			}
			defaultTableModel.addRow(obj);
		}
		legTable.setModel(defaultTableModel);
		}
	}

	/**
	 * 选着面板布局
	 */
	private void setUpLayout() {
		
		GridBagLayout componentLayout = new GridBagLayout();// 网格布局
		componentLayout.columnWidths = new int[] { 0, 100, 1100, 0 };
		componentLayout.columnWeights = new double[] { 0, 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 15, 35, 35, 35 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0 };
		this.upPanel.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;
//		第一行名称
		gridCon.gridx = 1;
		gridCon.gridy = 1;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblName, gridCon);
		this.upPanel.add(this.lblName);
		gridCon.gridx = 2;
		gridCon.gridwidth = 1;
		gridCon.gridheight = 1;
		componentLayout.setConstraints(this.txtName, gridCon);
		this.upPanel.add(this.txtName);
//		第二行类型
		gridCon.gridx = 1;
		gridCon.gridy = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblType, gridCon);
		this.upPanel.add(this.lblType);
		gridCon.gridx = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.cmbType, gridCon);
		this.upPanel.add(this.cmbType);
//		第三行方向
		gridCon.gridx = 1;
		gridCon.gridy = 3;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblDirection, gridCon);
		this.upPanel.add(this.lblDirection);
		gridCon.gridx = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.cmbDirection, gridCon);
		this.upPanel.add(this.cmbDirection);

		
	}
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {800 };
		componentLayout.columnWeights = new double[] { 0 };
		componentLayout.rowHeights = new int[] { 100,400};
		componentLayout.rowWeights = new double[] { 0,0,0};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
//		选项面板
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;        
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.upPanel, c);
		this.add(this.upPanel);
//		映射面板
		c.gridy=1;
		componentLayout.setConstraints(this.legJScrollPanel, c);
		this.add(this.legJScrollPanel);
	}
	
	
	private void jTableColumsHide(JTable jTable, int count) {

		jTable.getColumnModel().getColumn(count).setMinWidth(40);
		jTable.getColumnModel().getColumn(count).setMaxWidth(40);

	}
	
	/**
	 * 清空表
	 */
	public void clear() {
		this.legTable.clearSelection();
	}

	private JLabel lblName;	//名称 lbl
	private JTextField txtName;	//名称 
	private JLabel lblType;	//类型 lbl
	private JComboBox cmbType;	//类型
	private JLabel lblDirection;	//方向 lbl
	private JComboBox cmbDirection;	//方向 
}
