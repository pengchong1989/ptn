package com.nms.ui.ptn.ne.eth.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.enums.EMappingColorEnum;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.model.ptn.qos.QosMappingModeAttrService_MB;
import com.nms.model.ptn.qos.QosMappingModeService_MB;
import com.nms.model.ptn.qos.QosMappingTemplateService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * EXP映射页面
 * @author dzy
 *
 */
public class ExpMappingPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 653240278079048714L;
	private PortInst portInst; //选中的端口
	private int type; 
	private String column;
	
	/**
	 * 创建一个实例
	 * @param portInst 选中端口
	 * @param type 555为LLSP模式，556为ELSP模式
	 * @throws Exception
	 */
	public ExpMappingPanel(PortInst portInst,int type)throws Exception{
		try {
			this.type = type;
			this.portInst = portInst;
			initComponents();
			setInputLayout();
			setOutputLayout();
			setButtonLayout();
			setLayout();
			initData();
			addListener();
			this.setSize(800,350);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化数据
	 */
	private void initData(){
		initInputTableDate(((ControlKeyValue)this.cmbNameInput.getSelectedItem()),type);
		initOutputTableDate(((ControlKeyValue)this.cmbNameOutput.getSelectedItem()),type);
		initInputTableDate(((ControlKeyValue)this.cmbNameInput.getSelectedItem()),type);
		initOutputTableDate(((ControlKeyValue)this.cmbNameOutput.getSelectedItem()),type);
		initInputCombobox(this.cmbNameInput);
		initOutputCombobox(this.cmbNameOutput);
	}

	/**
	 * 初始化控件
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	private void initComponents() throws Exception {
		mainPanel = new JPanel();
		panelBtn=new JPanel();
		inputPanel = new JPanel();
		outputPanel = new JPanel();
		inputTable=new JTable();
		outputTable=new JTable();
		lblLlspName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		cmbNameInput = new JComboBox(); 
		cmbNameInput.setEditable(true);
		lblElspName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		cmbNameOutput = new JComboBox(); 
		cmbNameOutput.setEditable(true);
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		if(this.type==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
			column = ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR);
		}else{
			column = "COS";
		}
		Dimension dimension = new Dimension();
		dimension.setSize(350,180);
		//输入列表
		inputJScrollPane = new JScrollPane();
		inputJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		inputJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		inputJScrollPane.setPreferredSize(dimension);
		inputTable.getTableHeader().setResizingAllowed(true);
		inputTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		inputTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "",ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), "EXP", column }) {
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class ,java.lang.Object.class };
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 1 ){
					return false;
				}else{
				return true;
				}
			}
		}
		);
		inputJScrollPane.setViewportView(inputTable);
		//输出列表
		outputJScrollPane = new JScrollPane();
		outputJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outputJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		outputJScrollPane.setPreferredSize(dimension);
		outputTable.getTableHeader().setResizingAllowed(true);
		outputTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		outputTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "",ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), "EXP", column}) {
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class ,java.lang.Object.class };
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if (columnIndex == 1 ){
					return false;
				}else{
				return true;
				}
			}
		}
		);
		outputJScrollPane.setViewportView(outputTable);
		configTableUI(type);
		UiUtil.jTableColumsHide(inputTable, 1);
		UiUtil.jTableColumsHide(outputTable, 1);
	}
	
	/**
	 * 设置主布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {400,400};
		componentLayout.columnWeights = new double[] { 0,0 };
		componentLayout.rowHeights = new int[] { 250,50};
		componentLayout.rowWeights = new double[] { 0,0,0};
		this.mainPanel.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.mainPanel.setSize(800, 300);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;        
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.inputPanel, c);
		this.mainPanel.add(this.inputPanel);
		c.gridx=1;
		componentLayout.setConstraints(this.outputPanel, c);
		this.mainPanel.add(this.outputPanel);
		c.gridx=1;
		c.gridy=1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.panelBtn, c);
		this.mainPanel.add(this.panelBtn);
		
		this.add(mainPanel);
		
	}
	
	/**
	 * 输入面板布局
	 */
	private void setInputLayout() {
		
		this.inputPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_INPUT)));
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {50,350};
		componentLayout.columnWeights = new double[] { 0,0 };
		componentLayout.rowHeights = new int[] { 30,180};
		componentLayout.rowWeights = new double[] { 0,0};
		this.inputPanel.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.inputPanel.setSize(400, 250);

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;        
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblLlspName, c);
		this.inputPanel.add(this.lblLlspName);
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbNameInput, c);
		this.inputPanel.add(this.cmbNameInput);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;        
		c.gridwidth = 2;
		c.insets = new Insets(10, 5, 5, 5);
		componentLayout.setConstraints(this.inputJScrollPane, c);
		this.inputPanel.add(this.inputJScrollPane);
		
	}

	/**
	 *  输出面板布局
	 */
	private void setOutputLayout() {
		this.outputPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_OUTPUT)));
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {50,350};
		componentLayout.columnWeights = new double[] { 0,0 };
		componentLayout.rowHeights = new int[] { 30,180};
		componentLayout.rowWeights = new double[] { 0,0};
		this.outputPanel.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.outputPanel.setSize(400, 250);

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;        
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblElspName, c);
		this.outputPanel.add(this.lblElspName);
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbNameOutput, c);
		this.outputPanel.add(this.cmbNameOutput);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;        
		c.gridwidth = 2;
		c.insets = new Insets(10, 5, 5, 5);
		componentLayout.setConstraints(this.outputJScrollPane, c);
		this.outputPanel.add(this.outputJScrollPane);
	}

	/**
	 * 设置按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {300,50};
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 30 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);
		this.panelBtn.setSize(400, 30);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
	}
	
	/**
	 * 选取默认值填入支路信息表中
	 */
	@SuppressWarnings("unchecked")
	private void initInputTableDate(ControlKeyValue qosMappingAttrObject,int lspTypy) {
		DefaultTableModel inputTableModel = null;
		List<QosMappingAttr>qosMappingAttrList = null;
		if(null!=qosMappingAttrObject){
			if(qosMappingAttrObject.getObject() instanceof List<?>){
				qosMappingAttrList = (List<QosMappingAttr>) qosMappingAttrObject.getObject();
			}
		}
		//输入
		inputTableModel = (DefaultTableModel) inputTable.getModel();
		inputTableModel.getDataVector().clear();
		inputTableModel.fireTableDataChanged();
		Object[] obj = null;
		try {
			//LLSP
			if(lspTypy==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
				//输入
				if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
					for (int i = 0; i < qosMappingAttrList.size(); i++) {
						obj = new Object[] { qosMappingAttrList.get(i),i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),"YELLOW".equals(EMappingColorEnum.from(qosMappingAttrList.get(i).getColor()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN)};
						inputTableModel.addRow(obj);
					}
				}else{
					obj = new Object[] { null,1,"EXP0",ResourceUtil.srcStr(StringKeysObj.YELLOW)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,2,"EXP1",ResourceUtil.srcStr(StringKeysObj.GREEN)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,3,"EXP2",ResourceUtil.srcStr(StringKeysObj.YELLOW)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,4,"EXP3",ResourceUtil.srcStr(StringKeysObj.GREEN)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,5,"EXP4",ResourceUtil.srcStr(StringKeysObj.YELLOW)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,6,"EXP5",ResourceUtil.srcStr(StringKeysObj.GREEN)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,7,"EXP6",ResourceUtil.srcStr(StringKeysObj.YELLOW)};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,8,"EXP7",ResourceUtil.srcStr(StringKeysObj.GREEN)};
					inputTableModel.addRow(obj);
					inputTable.setModel(inputTableModel);
				}
				llspInputExpCountCombox.setEnabled(false);
				llspInputColorCountCombox.setEnabled(true);
			}
			//ELSP
			else{
				//输入
				if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
					for (int i = 0; i < qosMappingAttrList.size(); i++) {
						obj = new Object[] { qosMappingAttrList.get(i),i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),QosCosLevelEnum.from(Integer.parseInt(qosMappingAttrList.get(i).getGrade()))};
						inputTableModel.addRow(obj);
					}
				}else{
					obj = new Object[] { null,1,"EXP0","BE"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,2,"EXP1","AF1"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,3,"EXP2","AF2"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,4,"EXP3","AF3"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,5,"EXP4","AF4"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,6,"EXP5","EF"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,7,"EXP6","CS6"};
					inputTableModel.addRow(obj);
					obj = new Object[] { null,8,"EXP7","CS7"};
					inputTableModel.addRow(obj);
					inputTable.setModel(inputTableModel);
				}
				this.elspInputExpCountCombox.setEnabled(false);
				this.elspInputCosCountCombox.setEnabled(true);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 选取默认值填入支路信息表中
	 */
	@SuppressWarnings("unchecked")
	private void initOutputTableDate(ControlKeyValue qosMappingAttrObject,int lspTypy) {
		DefaultTableModel outputTableModel = null;
		List<QosMappingAttr>qosMappingAttrList = null;
		if(null!=qosMappingAttrObject){
			if(qosMappingAttrObject.getObject() instanceof List<?>){
				qosMappingAttrList = (List<QosMappingAttr>) qosMappingAttrObject.getObject();
			}
		}
		//输出
		outputTableModel = (DefaultTableModel) outputTable.getModel();
		outputTableModel.getDataVector().clear();
		outputTableModel.fireTableDataChanged();
		Object[] obj = null;
		try {
			//LLSP
			if(lspTypy==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
				//输出
				if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
					for (int i = 0; i < qosMappingAttrList.size(); i++) {
						obj = new Object[] { qosMappingAttrList.get(i),i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),"YELLOW".equals(EMappingColorEnum.from(qosMappingAttrList.get(i).getColor()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN)};
						outputTableModel.addRow(obj);
					}
				}else{
					obj = new Object[] { null,1,"EXP0",ResourceUtil.srcStr(StringKeysObj.YELLOW)};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,2,"EXP1",ResourceUtil.srcStr(StringKeysObj.GREEN)};
					outputTableModel.addRow(obj);
					outputTable.setModel(outputTableModel);
				}
				
				llspOutputExpCountCombox.setEnabled(true);
				llspOutputColorCountCombox.setEnabled(false);
			}
			//ELSP
			else{
				//输出
				if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
					for (int i = 0; i < qosMappingAttrList.size(); i++) {
						obj = new Object[] { qosMappingAttrList.get(i),i+1,"EXP"+String.valueOf(qosMappingAttrList.get(i).getValue()),QosCosLevelEnum.from(Integer.parseInt(qosMappingAttrList.get(i).getGrade()))};
						outputTableModel.addRow(obj);
					}
				}else{
					obj = new Object[] { null,1,"EXP0","BE"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,2,"EXP1","AF1"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,3,"EXP2","AF2"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,4,"EXP3","AF3"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,5,"EXP4","AF4"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,6,"EXP5","EF"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,7,"EXP6","CS6"};
					outputTableModel.addRow(obj);
					obj = new Object[] { null,8,"EXP7","CS7"};
					outputTableModel.addRow(obj);
					outputTable.setModel(outputTableModel);
				}
				this.elspOutputExpCountCombox.setEnabled(true);
				this.elspOutputCosCountCombox.setEnabled(false);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	/**
	 * 初始化 表格 列模型
	 * @param lspType  lsp类型
	 * @throws Exception
	 */
	private void configTableUI(int lspType) throws Exception {
		
		if(lspType==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
			//输入
			llspInputExpCountCombox = new JComboBox();
			llspInputExpCountCombox.addItem("EXP0");
			llspInputExpCountCombox.addItem("EXP1");
			llspInputExpCountCombox.addItem("EXP2");
			llspInputExpCountCombox.addItem("EXP3");
			llspInputExpCountCombox.addItem("EXP4");
			llspInputExpCountCombox.addItem("EXP5");
			llspInputExpCountCombox.addItem("EXP6");
			llspInputExpCountCombox.addItem("EXP7");
			TableColumn inEXPCount = inputTable.getColumn("EXP");
			inEXPCount.setCellEditor(new DefaultCellEditor(llspInputExpCountCombox));
			
			llspInputColorCountCombox = new JComboBox();
			llspInputColorCountCombox.addItem(ResourceUtil.srcStr(StringKeysObj.YELLOW));
			llspInputColorCountCombox.addItem(ResourceUtil.srcStr(StringKeysObj.GREEN));
			TableColumn inColorCount = inputTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR));
			inColorCount.setCellEditor(new DefaultCellEditor(llspInputColorCountCombox));
			
			//输出
			llspOutputExpCountCombox = new JComboBox();
			llspOutputExpCountCombox.addItem("EXP0");
			llspOutputExpCountCombox.addItem("EXP1");
			llspOutputExpCountCombox.addItem("EXP2");
			llspOutputExpCountCombox.addItem("EXP3");
			llspOutputExpCountCombox.addItem("EXP4");
			llspOutputExpCountCombox.addItem("EXP5");
			llspOutputExpCountCombox.addItem("EXP6");
			llspOutputExpCountCombox.addItem("EXP7");
			TableColumn outEXPCount = outputTable.getColumn("EXP");
			outEXPCount.setCellEditor(new DefaultCellEditor(llspOutputExpCountCombox));
			
			llspOutputColorCountCombox = new JComboBox();
			llspOutputColorCountCombox.addItem(ResourceUtil.srcStr(StringKeysObj.YELLOW));
			llspOutputColorCountCombox.addItem(ResourceUtil.srcStr(StringKeysObj.GREEN));
			TableColumn outColorCount = outputTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR));
			outColorCount.setCellEditor(new DefaultCellEditor(llspOutputColorCountCombox));
		}else{
			//输入
			elspInputExpCountCombox = new JComboBox();
			elspInputExpCountCombox.addItem("EXP0");
			elspInputExpCountCombox.addItem("EXP1");
			elspInputExpCountCombox.addItem("EXP2");
			elspInputExpCountCombox.addItem("EXP3");
			elspInputExpCountCombox.addItem("EXP4");
			elspInputExpCountCombox.addItem("EXP5");
			elspInputExpCountCombox.addItem("EXP6");
			elspInputExpCountCombox.addItem("EXP7");
			TableColumn inEXPCount = inputTable.getColumn("EXP");
			inEXPCount.setCellEditor(new DefaultCellEditor(elspInputExpCountCombox));
			
			elspInputCosCountCombox = new JComboBox();
			elspInputCosCountCombox.addItem("BE");
			elspInputCosCountCombox.addItem("AF1");
			elspInputCosCountCombox.addItem("AF2");
			elspInputCosCountCombox.addItem("AF3");
			elspInputCosCountCombox.addItem("AF4");
			elspInputCosCountCombox.addItem("EF");
			elspInputCosCountCombox.addItem("CS6");
			elspInputCosCountCombox.addItem("CS7");
			TableColumn inColorCount = inputTable.getColumn("COS");
			inColorCount.setCellEditor(new DefaultCellEditor(elspInputCosCountCombox));
			
			//输出
			elspOutputExpCountCombox = new JComboBox();
			elspOutputExpCountCombox.addItem("EXP0");
			elspOutputExpCountCombox.addItem("EXP1");
			elspOutputExpCountCombox.addItem("EXP2");
			elspOutputExpCountCombox.addItem("EXP3");
			elspOutputExpCountCombox.addItem("EXP4");
			elspOutputExpCountCombox.addItem("EXP5");
			elspOutputExpCountCombox.addItem("EXP6");
			elspOutputExpCountCombox.addItem("EXP7");
			TableColumn outEXPCount = outputTable.getColumn("EXP");
			outEXPCount.setCellEditor(new DefaultCellEditor(elspOutputExpCountCombox));
			
			elspOutputCosCountCombox = new JComboBox();
			elspOutputCosCountCombox.addItem("BE");
			elspOutputCosCountCombox.addItem("AF1");
			elspOutputCosCountCombox.addItem("AF2");
			elspOutputCosCountCombox.addItem("AF3");
			elspOutputCosCountCombox.addItem("AF4");
			elspOutputCosCountCombox.addItem("EF");
			elspOutputCosCountCombox.addItem("CS6");
			elspOutputCosCountCombox.addItem("CS7");
			TableColumn outColorCount = outputTable.getColumn("COS");
			outColorCount.setCellEditor(new DefaultCellEditor(elspOutputCosCountCombox));
		}
	}
	
	/**
	 * 添加监听
	 * 
	 * @author dzy
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void addListener() {
		this.btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnSaveListener();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		this.cmbNameInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbNameInputActionPerformed();
			}
		});
	this.cmbNameOutput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbNameOutputActionPerformed();
			}
		});
	}
	
	/**
	 * cmbNameInput监听
	 */
	private void cmbNameInputActionPerformed() {
		if(null!=this.cmbNameInput.getSelectedItem()&&!"".equals(this.cmbNameInput.getSelectedItem())&&
				this.cmbNameInput.getSelectedItem() instanceof ControlKeyValue){
			initInputTableDate(((ControlKeyValue)this.cmbNameInput.getSelectedItem()), type);
		}
		
	}
	
	/**
	 * cmbNameOutput监听
	 */
	private void cmbNameOutputActionPerformed() {
		if(null!=this.cmbNameOutput.getSelectedItem()&&!"".equals(this.cmbNameOutput.getSelectedItem())&&
				this.cmbNameOutput.getSelectedItem() instanceof ControlKeyValue){
			initOutputTableDate(((ControlKeyValue)this.cmbNameOutput.getSelectedItem()), type);
		}
	}
	/**
	 * 保存按钮事件
	 * @throws Exception
	 */
	protected void btnSaveListener() throws Exception{
		//收集界面数据
		String result = null;
		try {
			result = inputSaveAction();
			if( ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				result =  outputSaveAction();
			}
			DialogBoxUtil.succeedDialog(this,result);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 输入窗口保存
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private String inputSaveAction() throws Exception {
		String result = null;
		String name;
		ControlKeyValue  nameInput = null;
		QosMappingMode  qosMappingMode;
		int direction = UiUtil.getCodeByValue("EXPDIRECTION", "1").getId();
		if(this.cmbNameInput.getSelectedItem() instanceof ControlKeyValue){
			nameInput = (ControlKeyValue) this.cmbNameInput.getSelectedItem();
			if(null!=nameInput.getId()&&!"".equals(nameInput.getId())){
				qosMappingMode = new QosMappingMode();
				//如果是llsp
				if(555==this.type){
					qosMappingMode.setType(QosTemplateTypeEnum.LLSP.getValue());
					qosMappingMode.setTypeName(QosTemplateTypeEnum.LLSP.toString());
				}
				//ESLP
				else{
					qosMappingMode.setType(QosTemplateTypeEnum.ELSP.getValue());
					qosMappingMode.setTypeName(QosTemplateTypeEnum.ELSP.toString());
				}
				qosMappingMode.setSiteId(ConstantUtil.siteId);
				qosMappingMode.setName(((List<QosMappingAttr>)nameInput.getObject()).get(0).getName());
				qosMappingMode.setQosMappingAttrList((List<QosMappingAttr>)nameInput.getObject());
				result = sendAction(qosMappingMode,direction);
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		}else{
			name =  this.cmbNameInput.getSelectedItem().toString();
			//收集数据
			 qosMappingMode = getTableData(name,direction);
			 sendAction(qosMappingMode,direction);
		}
		return result;
	}
	
	/**
	 * 输出窗口保存
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private String outputSaveAction() throws Exception {
		String name;
		String result = null;
		ControlKeyValue  nameOutput = null;
		QosMappingMode  qosMappingMode;
		int direction = UiUtil.getCodeByValue("EXPDIRECTION", "0").getId();
		if(this.cmbNameOutput.getSelectedItem() instanceof ControlKeyValue){
			nameOutput = (ControlKeyValue) this.cmbNameOutput.getSelectedItem();
			if(null!=nameOutput.getId()&&!"".equals(nameOutput.getId())){
				qosMappingMode = new QosMappingMode();
				//如果是llsp
				if(555==this.type){
					qosMappingMode.setType(QosTemplateTypeEnum.LLSP.getValue());
					qosMappingMode.setTypeName(QosTemplateTypeEnum.LLSP.toString());
				}
				//ESLP
				else{
					qosMappingMode.setType(QosTemplateTypeEnum.ELSP.getValue());
					qosMappingMode.setTypeName(QosTemplateTypeEnum.ELSP.toString());
				}
				qosMappingMode.setSiteId(ConstantUtil.siteId);
				qosMappingMode.setName(((List<QosMappingAttr>)nameOutput.getObject()).get(0).getName());
				qosMappingMode.setQosMappingAttrList((List<QosMappingAttr>)nameOutput.getObject());
				result = sendAction(qosMappingMode,direction);
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		}else{
			name =  this.cmbNameInput.getSelectedItem().toString();
			//收集数据
			 qosMappingMode = getTableData(name,direction);
			 sendAction(qosMappingMode,direction);
		}
		return result;
	}
	
	/**
	 * 下发设备
	 * @param qosMappingMode
	 */
	private String sendAction(QosMappingMode qosMappingMode,int direction) {
		String result = null;
		List<QosMappingMode> infos ;
		DispatchUtil portDispatch = null;
		DispatchUtil expMappingPhbDispatch = null;
		QosMappingModeService_MB mappingModeService = null;
		try {
			portDispatch = new DispatchUtil(RmiKeys.RMI_PORT);
			infos = new ArrayList<QosMappingMode>();
			expMappingPhbDispatch = new DispatchUtil(RmiKeys.RMI_EXPMAPPINGPHB);
			mappingModeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			infos.add(qosMappingMode);
			//验证端口是否使用模板
			if(verifyModel(qosMappingMode)){
				//如果使用本模板,不修改模板,直接返回成功
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
			//如果未使用模板新建模板并且修改端口引用
			else{
				result = expMappingPhbDispatch.excuteInsert(infos);
			}
			//如果下发成功
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				//查询入库的模板
				infos = mappingModeService.queryByCondition(infos.get(0));
				if(null!=infos&&infos.size()>0){
					qosMappingMode = infos.get(0);
				}
				//下发端口
				//设置端口和模板的关联
				this.setPortInstBusiId(this.portInst,qosMappingMode,direction);
				result = portDispatch.excuteUpdate(this.portInst);
			}
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(mappingModeService);
		}
		return result;
	}
	
	/**
	 * 为端口赋值businessId
	 * @param portInst 
	 * 			端口对象
	 * @param qosMappingMode
	 * 			Exp映射
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void setPortInstBusiId(PortInst portInst,
			QosMappingMode qosMappingMode,int direction) throws NumberFormatException, Exception {
 		int businessId = qosMappingMode.getBusinessId();
 		//llsp输入
		if(UiUtil.getCodeByValue("EXPTYPE", "0").getId()==this.type&&
				UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
				portInst.setExpMappingLLspInput(businessId);
		}
		//llsp输出
		if(UiUtil.getCodeByValue("EXPTYPE", "0").getId()==this.type&&
				UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
			portInst.setExpMappingLLspOutput(businessId);
		}
		//elsp输入
		if(UiUtil.getCodeByValue("EXPTYPE", "1").getId()==this.type&&
				UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
			portInst.setExpMappingELspInput(businessId);
		}
		//elsp输出
		if(UiUtil.getCodeByValue("EXPTYPE", "1").getId()==this.type&&
				UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
			portInst.setExpMappingELspOutput(businessId);
		}
	}
	
	/**
	 * 验证模板在此网元是否被使用
	 * @param qosMappingMode
	 * 				Exp映射
	 * @return
	 * @throws Exception
	 */
	private boolean verifyModel(QosMappingMode qosMappingMode) throws Exception {
		int count = 0;
		boolean flag = false;
		QosMappingModeAttrService_MB mappingModeAttrService = null;
		try {
			List<QosMappingAttr> qosMappingAttrSelList = null;
			mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
				qosMappingAttrSelList = mappingModeAttrService.isExit(qosMappingAttr);
				if(null!=qosMappingAttrSelList&&qosMappingAttrSelList.size()==1){
					count ++;
				}
			}
			//模板存在模板
			if(count == qosMappingMode.getQosMappingAttrList().size()){
				flag = true;
			}else{
				flag = false;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(mappingModeAttrService);
		}
		return flag;
	}

	/**
	 * 收集数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private QosMappingMode getTableData(String name,int direction) {
		Vector vector ;
		DefaultTableModel tableModel ;
		QosMappingAttr qosMappingAttr = null;
		QosMappingMode qosMappingMode = null ;
		List<QosMappingAttr> qosMappingAttrList;
		try {
			qosMappingMode = new QosMappingMode();
	 		qosMappingAttrList= new ArrayList<QosMappingAttr>();
			if(direction==(UiUtil.getCodeByValue("EXPDIRECTION", "1").getId())){
				tableModel = (DefaultTableModel) inputTable.getModel();
			}else{
				tableModel = (DefaultTableModel) outputTable.getModel();
			}
			vector = tableModel.getDataVector();
			Iterator it = vector.iterator();
			
			//如果是llsp
			if(555==this.type){
				qosMappingMode.setType(QosTemplateTypeEnum.LLSP.getValue());
				qosMappingMode.setTypeName(QosTemplateTypeEnum.LLSP.toString());
			}
			//ESLP
			else{
				qosMappingMode.setType(QosTemplateTypeEnum.ELSP.getValue());
				qosMappingMode.setTypeName(QosTemplateTypeEnum.ELSP.toString());
			}
			qosMappingMode.setSiteId(ConstantUtil.siteId);
			qosMappingMode.setName(name);
			while (it.hasNext()) {
				qosMappingAttr = new QosMappingAttr();
				Vector temp = (Vector) it.next();
				if(temp.get(2)!=null){
					qosMappingAttr.setValue(Integer.parseInt(temp.get(2).toString().substring(3, 4)));
				}
				if(temp.get(3)!=null){
					//如果是llsp
					if(555==this.type){
						qosMappingAttr.setColor(EMappingColorEnum.from(ResourceUtil.srcStr(StringKeysObj.YELLOW).equals(temp.get(3))?"YELLOW":"GREEN"));
					}
					//ESLP
					else{
						qosMappingAttr.setGrade(QosCosLevelEnum.from((temp.get(3)+""))+"");
					}
				}
				qosMappingAttr.setMappingType(QosTemplateTypeEnum.EXP.getValue());
				qosMappingAttr.setName(name);
				qosMappingAttr.setModel(this.type);
				qosMappingAttr.setDirection(direction);
				qosMappingAttr.setSiteId(ConstantUtil.siteId);
				qosMappingAttrList.add(qosMappingAttr);
			}
			qosMappingMode.setQosMappingAttrList(qosMappingAttrList);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return qosMappingMode;
	}
	
	/**
	 * 初始化输入框name
	 * @param cmbNameInput
	 */
	private void initInputCombobox(JComboBox cmbNameInput) {
		List<QosMappingAttr>  qosMappingAttrList;
		QosMappingTemplateService_MB qosMappingTemplateService = null;
		List<QosMappingAttr> qosMappingAttrSelList = null;
		QosMappingAttr defaultMapping = new QosMappingAttr();
		defaultMapping.setName("  ");
		QosMappingAttr qosMappingAttrSel;
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) cmbNameInput.getModel();
		int groupId = 0;
		try {
			qosMappingAttrList = new ArrayList<QosMappingAttr>();
			qosMappingAttrSel = new QosMappingAttr();
			qosMappingTemplateService = (QosMappingTemplateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSMAPPINGTEMPLATESERVICE);
			if(this.type==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
				qosMappingAttrSel.setModel(555);
			}else{
				qosMappingAttrSel.setModel(556);
			}
			qosMappingAttrSel.setDirection(558);
			qosMappingAttrSelList = qosMappingTemplateService.queryByCondition(qosMappingAttrSel);
			
			defaultComboBoxModel.addElement(new ControlKeyValue("", "", defaultMapping));
			for(QosMappingAttr qosMappingAttr:qosMappingAttrSelList){
				qosMappingAttr.setId(0);
				qosMappingAttr.setSiteId(ConstantUtil.siteId);
				if(groupId != qosMappingAttr.getGroupid()){
					if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
						defaultComboBoxModel.addElement(new ControlKeyValue(qosMappingAttrList.get(0).getId() + "", qosMappingAttrList.get(0).getName(), qosMappingAttrList));
					}
					qosMappingAttrList = new ArrayList<QosMappingAttr>();
					groupId = qosMappingAttr.getGroupid();
				}
				qosMappingAttrList.add(qosMappingAttr);
			}
			if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
				defaultComboBoxModel.addElement(new ControlKeyValue(qosMappingAttrList.get(0).getId() + "", qosMappingAttrList.get(0).getName(), qosMappingAttrList));
			}
			cmbNameInput.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosMappingTemplateService);
		}
	}
	/**
	 * 初始化输出框name
	 * @param cmbNameOutput
	 */
	private void initOutputCombobox(JComboBox cmbNameOutput) {
		List<QosMappingAttr>  qosMappingAttrList;
		QosMappingTemplateService_MB qosMappingTemplateService = null;
		List<QosMappingAttr> qosMappingAttrSelList = null;
		QosMappingAttr defaultMapping = new QosMappingAttr();
		defaultMapping.setName("  ");
		QosMappingAttr qosMappingAttrSel;
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) cmbNameOutput.getModel();
		int groupId = 0;
		try {
			qosMappingAttrList = new ArrayList<QosMappingAttr>();
			qosMappingAttrSel = new QosMappingAttr();
			qosMappingTemplateService = (QosMappingTemplateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSMAPPINGTEMPLATESERVICE);
			if(this.type==UiUtil.getCodeByValue("EXPTYPE", "0").getId()){
				qosMappingAttrSel.setModel(555);
			}else{
				qosMappingAttrSel.setModel(556);
			}
			qosMappingAttrSel.setDirection(557);
			qosMappingAttrSelList = qosMappingTemplateService.queryByCondition(qosMappingAttrSel);
			
			defaultComboBoxModel.addElement(new ControlKeyValue("", "", defaultMapping));
			for(QosMappingAttr qosMappingAttr:qosMappingAttrSelList){
				qosMappingAttr.setId(0);
				if(groupId != qosMappingAttr.getGroupid()){
					if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
						defaultComboBoxModel.addElement(new ControlKeyValue(qosMappingAttrList.get(0).getId() + "", qosMappingAttrList.get(0).getName(), qosMappingAttrList));
					}
					qosMappingAttrList = new ArrayList<QosMappingAttr>();
					groupId = qosMappingAttr.getGroupid();
				}
				qosMappingAttrList.add(qosMappingAttr);
			}
			if(null!=qosMappingAttrList&&qosMappingAttrList.size()>0){
				defaultComboBoxModel.addElement(new ControlKeyValue(qosMappingAttrList.get(0).getId() + "", qosMappingAttrList.get(0).getName(), qosMappingAttrList));
			}
			cmbNameOutput.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosMappingTemplateService);
		}
	}
	private JComboBox llspInputExpCountCombox ; //LLSP输入EXP列
	private JComboBox llspInputColorCountCombox; //LLSP输入颜色列
	private JComboBox llspOutputExpCountCombox ; //LLSP输出EXP列
	private JComboBox llspOutputColorCountCombox; //LLSP输出COS列
	private JComboBox elspInputExpCountCombox ; //LLSP输入EXP列
	private JComboBox elspInputCosCountCombox; //LLSP输入颜色列
	private JComboBox elspOutputExpCountCombox ; //LLSP输出EXP列
	private JComboBox elspOutputCosCountCombox; //LLSP输出COS列
	private JLabel lblLlspName; //LLSP名字JLabel
	private JComboBox cmbNameInput;//LLSP名字JComboBox
	private JLabel lblElspName;//ELSP名字JLabel
	private JComboBox cmbNameOutput;//ELSP名字JComboBox
	private JPanel mainPanel;//主面板
	private JTable inputTable; //输入列表
	private JPanel inputPanel;	//输入面板
	private JScrollPane inputJScrollPane;//输入列表面板
	private JScrollPane outputJScrollPane;//输出列表面板
	private JTable outputTable;//输出列表
	private JPanel outputPanel;//输出面板
	private PtnButton btnSave; // 确定按钮
	private JPanel panelBtn; // 按钮布局

	
}
