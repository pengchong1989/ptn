package com.nms.ui.ptn.ne.ac.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;

public class AddAcDialogStep4 extends PtnDialog {
	private static final long serialVersionUID = -2137009763124250754L;
	private JScrollPane rightPanel;
	private JPanel topPanel;
	private JPanel buttomPanel;
	private JTable jTable;
	private JButton confirm;
	private JButton cancelBtn;
	private DefaultTableModel tableModel;
	private TableColumn stepColumn;
	private int vlanStep = 1;//vlan步长
	private int vlanPriStep = 1;//802.1p步长
	private int sourceMacStep = 1;//源mac步长
	private int endMacStep = 1;//目的mac步长
	private int sourceIPStep = 1;//源ip步长
	private int endIPStep = 1;//目的ip步长
	
	public AddAcDialogStep4() {
		try {
			this.setTitle(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_SECOND));
			this.setModal(true);
			this.initComponents();
			this.setLayout();
			this.addActionListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void addActionListener() {
		this.confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getValue();
			}
		});
		
		this.cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	/**
	 * 获取界面数据
	 */
	@SuppressWarnings("unchecked")
	private void getValue() {
		if (this.jTable.isEditing()) {
			this.jTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_STEP)).getCellEditor().stopCellEditing();
		}
		DefaultTableModel tableModel = (DefaultTableModel) this.jTable.getModel();
		Vector dataVector = tableModel.getDataVector();
		try {
			this.vlanStep = Integer.parseInt(((Vector) dataVector.get(0)).get(2).toString());
			this.sourceMacStep = Integer.parseInt(((Vector) dataVector.get(1)).get(2).toString());
			this.endMacStep = Integer.parseInt(((Vector) dataVector.get(2)).get(2).toString());
			this.sourceIPStep = Integer.parseInt(((Vector) dataVector.get(3)).get(2).toString());
			this.endIPStep = Integer.parseInt(((Vector) dataVector.get(4)).get(2).toString());
			this.vlanPriStep = Integer.parseInt(((Vector) dataVector.get(5)).get(2).toString());
			this.dispose();
		}catch(Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private void setLayout() {
		this.topPanel.add(rightPanel, BorderLayout.CENTER);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		this.buttomPanel.setLayout(fl);
		this.buttomPanel.add(confirm);
		this.buttomPanel.add(cancelBtn);
		JPanel jpanel = new JPanel(new BorderLayout());
		this.buttomPanel.setPreferredSize(new Dimension(600, 40));
		jpanel.add(this.topPanel, BorderLayout.CENTER);
		jpanel.add(this.buttomPanel, BorderLayout.SOUTH);
		this.add(jpanel);
	}

	private void initComponents() throws Exception {
		this.rightPanel = new JScrollPane();
		this.rightPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.rightPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.topPanel = new JPanel();
		this.buttomPanel = new JPanel();
		this.confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		this.cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.jTable = new JTable();
		this.jTable.getTableHeader().setResizingAllowed(true);
		this.jTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.setTableModel();
		this.jTable.setModel(this.tableModel);
		this.configSpinner(this.jTable);
		Object[] vlan = new Object[]{"1",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_VLAN),0};
		this.tableModel.addRow(vlan);
		Object[] sourceMac = new Object[]{"2",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_SOURCE_MAC),0};
		this.tableModel.addRow(sourceMac);
		Object[] targetMac = new Object[]{"3",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_END_MAC),0};
		this.tableModel.addRow(targetMac);
		Object[] sourceIP = new Object[]{"4",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_SOURCE_IP),0};
		this.tableModel.addRow(sourceIP);
		Object[] targetIP = new Object[]{"5",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_END_IP),0};
		this.tableModel.addRow(targetIP);
		Object[] vlanPri = new Object[]{"6",ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_VLANPRI),0};
		this.tableModel.addRow(vlanPri);
		this.rightPanel.setViewportView(this.jTable);
	}
	
	private void setTableModel() {
		this.tableModel = new DefaultTableModel(null, 
				new String[] {ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_ATTR), 
				ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_STEP)}) {
			private static final long serialVersionUID = -1583249404349686087L;
			@SuppressWarnings("unchecked")
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if(columnIndex == 0 || columnIndex == 1){
					return false;
				}
				return true;
			}
		};
	}

	/**
	 * 对设置带宽的列的单元格添加Spinner
	 */
	private void configSpinner(JTable table) {
		this.stepColumn = table.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_STEP));
		SpinnerNumberEditor snEditor = new SpinnerNumberEditor("0", "100", "1");
		this.stepColumn.setCellEditor(snEditor);
	}

	public int getVlanStep() {
		return vlanStep;
	}

	public int getSourceMacStep() {
		return sourceMacStep;
	}

	public int getEndMacStep() {
		return endMacStep;
	}

	public int getSourceIPStep() {
		return sourceIPStep;
	}

	public int getEndIPStep() {
		return endIPStep;
	}

	public int getVlanPriStep() {
		return vlanPriStep;
	}
}
