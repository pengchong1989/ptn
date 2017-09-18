package com.nms.ui.ptn.ne.pri.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTitle;

public class UpdatePriDialog extends PtnDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5724950613205861648L;
	private JTable legTable;
	private JScrollPane legJScrollPanel;
	private QosMappingMode qosMappingMode;
	private List<QosMappingAttr> mappingAttrList;
	private JPanel upPanel;
	private JButton btnSave; // 确定按钮
	private JButton canSave;//取消按钮
	private JPanel panelBtn; // 按钮布局
	
	private PriMainPanel priMainPanel;
	
	public UpdatePriDialog(QosMappingMode qosMappingMode,PriMainPanel priMainPanel)throws Exception{
		try {
			this.priMainPanel = priMainPanel;
			this.qosMappingMode = qosMappingMode;
			mappingAttrList = qosMappingMode.getQosMappingAttrList();
			initComponents();
			configE1LegTableUI();
			UiUtil.jTableColumsHide(legTable, 1);
			setUpLayout();
			setButtonLayout();
			setLayout();
			setDefaultTable();
			initData();
			addListener();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		UiUtil.showWindow(this, 750, 600);
	}
	
	private void initData(){
		this.txtName.setText(qosMappingMode.getName());
	}
	
	private void initComponents() throws Exception {
		
		panelBtn=new JPanel();
		upPanel=new JPanel();
		legTable=new JTable();
	
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_PRI_MAPPING));
		this.btnSave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.canSave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		legJScrollPanel = new JScrollPane();
		legJScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		legJScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		legTable.getTableHeader().setResizingAllowed(true);
		legTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		legTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "",ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), "PHB", "PRI" }) {
			private static final long serialVersionUID = 3052301446264370527L;
			@SuppressWarnings("unchecked")
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class ,java.lang.Object.class };
			@SuppressWarnings("unchecked")
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
		legJScrollPanel.setViewportView(legTable);
		
		lblName=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		txtName=new JTextField();
	}
	
	/**
	 * 设置按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 450,50,50};
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
		c.gridx=2;
		componentLayout.setConstraints(this.canSave, c);
		this.panelBtn.add(this.canSave);
	}
	
	/**
	 * 选取默认值填入支路信息表中
	 */
	private void setDefaultTable() {
		List<QosMappingAttr> qosMappingAttrList=qosMappingMode.getQosMappingAttrList();
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel) legTable.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		Object[] obj = null;
		for(int i=0;i<qosMappingAttrList.size();i++){
			obj = new Object[] { qosMappingAttrList.get(i),i+1,qosMappingAttrList.get(i).getGrade(),String.valueOf(qosMappingAttrList.get(i).getValue())};
			defaultTableModel.addRow(obj);
		}
		legTable.setModel(defaultTableModel);

	}
	
	private void setUpLayout() {
		GridBagLayout componentLayout = new GridBagLayout();// 网格布局
		componentLayout.columnWidths = new int[] { 0, 100, 510, 0 };
		componentLayout.columnWeights = new double[] { 0, 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 15, 40};
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0,0};
		this.upPanel.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;

		gridCon.gridx = 0;
		gridCon.gridy = 0;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblName, gridCon);
		this.upPanel.add(this.lblName);
		gridCon.gridx = 1;
		gridCon.gridwidth = 3;
		gridCon.gridheight = 1;
		componentLayout.setConstraints(this.txtName, gridCon);
		this.upPanel.add(this.txtName);
	}
	
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {680 };
		componentLayout.columnWeights = new double[] { 0 };
		componentLayout.rowHeights = new int[] { 100,400,100};
		componentLayout.rowWeights = new double[] { 0,0,0};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;        
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.upPanel, c);
		this.add(this.upPanel);
		
		c.gridy=1;
		componentLayout.setConstraints(this.legJScrollPanel, c);
		this.add(this.legJScrollPanel);
		
		c.gridy=2;
		componentLayout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}
	
	/**
	 *让表格中的PHB成为下拉列表的格式
	 * 
	 * @throws Exception
	 */
	private void configE1LegTableUI() throws Exception {
		pinCountCombox = new JComboBox();
		pinCountCombox.addItem("0");
		pinCountCombox.addItem("1");
		pinCountCombox.addItem("2");
		pinCountCombox.addItem("3");
		pinCountCombox.addItem("4");
		pinCountCombox.addItem("5");
		pinCountCombox.addItem("6");
		pinCountCombox.addItem("7");
		TableColumn pinCountColumn = legTable.getColumn("PRI");
		pinCountColumn.setCellEditor(new DefaultCellEditor(pinCountCombox));

		cospinCountCombox = new JComboBox();
		cospinCountCombox.addItem("BE");
		cospinCountCombox.addItem("AF1");
		cospinCountCombox.addItem("AF2");
		cospinCountCombox.addItem("AF3");
		cospinCountCombox.addItem("AF4");
		cospinCountCombox.addItem("EF");
		cospinCountCombox.addItem("CS6");
		cospinCountCombox.addItem("CS7");
		TableColumn prestoreTimeColumn = legTable.getColumn("PHB");
		prestoreTimeColumn.setCellEditor(new DefaultCellEditor(cospinCountCombox));
	}
	

	/**
	 * 添加监听
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void addListener() {
		cospinCountCombox.setEnabled(false);
		pinCountCombox.setEnabled(true);
		this.btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnSaveListener();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

		});
		this.canSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					dispose();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

		});
		}
		
	
	/**
	 * 保存按钮事件
	 * @throws Exception
	 */
	protected void btnSaveListener() throws Exception{
		//收集界面数据
		DispatchUtil expMappingPhbDispatch = null;
		String result = null;
		try {
			List<QosMappingMode>  infos = getTableData();
			expMappingPhbDispatch = new DispatchUtil(RmiKeys.RMI_EXPMAPPINGPHB);
			result = expMappingPhbDispatch.excuteUpdate(infos);
			DialogBoxUtil.succeedDialog(this,result);
			this.priMainPanel.getController().refresh();
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	@SuppressWarnings("unchecked")
	private List<QosMappingMode> getTableData() throws Exception{
		
		DefaultTableModel tableModel = (DefaultTableModel) legTable.getModel();
		Vector vector = tableModel.getDataVector();
		Iterator it = vector.iterator();
		int i = 1;
		while (it.hasNext()) {
			Vector temp = (Vector) it.next();
			mappingAttrList.get(i-1).setSiteId(ConstantUtil.siteId);
			if (temp.get(0) != null) {
				mappingAttrList.get(i-1).setId(((QosMappingAttr) temp.get(0)).getId());
				
			}
			mappingAttrList.get(i-1).setPhbId(qosMappingMode.getId());
			
			if(temp.get(2)!=null){
				mappingAttrList.get(i-1).setGrade(temp.get(2)+"");
			}
			
			if(temp.get(3)!=null){
				mappingAttrList.get(i-1).setValue(Integer.parseInt(temp.get(3).toString()));
			}
			i += 1;
		}
		qosMappingMode.setName(txtName.getText());
		qosMappingMode.setQosMappingAttrList(mappingAttrList);
		List<QosMappingMode> modeList = new ArrayList<QosMappingMode>();
		
		modeList.add(qosMappingMode);
		return modeList;
		
	}
	private JComboBox cospinCountCombox;
	private JComboBox pinCountCombox ;
	private JLabel lblName;
	private JTextField txtName;
	
}
