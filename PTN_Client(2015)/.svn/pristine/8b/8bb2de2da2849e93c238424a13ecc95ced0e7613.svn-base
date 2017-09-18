package com.nms.ui.ptn.ne.ac.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;

public class AddAcDialogStep2 extends PtnDialog {
	private JPanel leftPanel;
	private JPanel rightPanel;

	private JPanel topPanel;
	private JPanel buttomPanel;

	private JLabel operJLabel; // 操作步骤
	private JSeparator js1;
	private JLabel createStep2JLabel; // 创建第二步

	private JLabel tagReconfigJLabel;
	private JLabel tagActionJLabel;
	private JLabel addVlanIdJLabel;
	private JLabel addVlanPriJLabel;
	private JLabel lblModel;//模式
	
	private JTextField addVlanIdJTF;
//	private PtnTextField  addVlanIdJTF;
	private JTextField addVlanPriJTF;
	private JComboBox tagReconfigJCB;
	private JComboBox tagActionJCB;
	private JComboBox cmbModel;//模式:0/1/2/=不使能/trTCM/Modified trTCM

	private JButton nextBtn;
	private JButton previousBtn;
	private JButton cancelBtn;
	private JLabel macCountJLabel;
	private JTextField macCountField;
	
	public AddAcDialogStep2() {
		try {
			this.setModal(true);
			initComponents();
			setLayout();
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
			addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
			addActionListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void addActionListener() {
		this.tagActionJCB.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1){
					int id = ((Code)((ControlKeyValue)e.getItem()).getObject()).getId();
					if(id == 567 || id == 568){
						addVlanPriJLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_REPLACE_VLAN_PRI_DOWN));//下话替换VLAN PRI
						addVlanIdJLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_REPLACE_VLAN_ID_DOWN));//下话替换VLAN ID
					}else{
						addVlanPriJLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI));
						addVlanIdJLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID));
					}
				}
			}
		});
	}

	private void setLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
//		leftPanel.setLayout(new GridBagLayout());
//		leftPanel.setBorder(BorderFactory.createTitledBorder(""));
//		addComponent(leftPanel, operJLabel, 0, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(leftPanel, js1, 0, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
//		addComponent(leftPanel, createStep2JLabel, 0, 2, 0.1, 0.6, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTH, gbc);
		this.rightPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_SECOND)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 150, 250, 150 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 80 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.6 };
		rightPanel.setLayout(layout);

		addComponent(rightPanel, tagReconfigJLabel, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, tagActionJLabel, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, addVlanIdJLabel, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, addVlanPriJLabel, 0, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, lblModel, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, macCountJLabel, 0, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		
		addComponent(rightPanel, tagReconfigJCB, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, tagActionJCB, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, addVlanIdJTF, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, addVlanPriJTF, 1, 3, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, cmbModel, 1, 4, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		addComponent(rightPanel, macCountField, 1, 5, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, gbc);
		
		topPanel.setLayout(new BorderLayout());
		/*
		 * addComponent(topPanel, leftPanel, 0, 0, 0.05, 0.8, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST ,gbc); addComponent(topPanel, rightPanel, 1, 0, 0.3, 0.8, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST ,gbc);
		 */
//		leftPanel.setPreferredSize(new Dimension(150, 300));
//		topPanel.add(leftPanel, BorderLayout.WEST);
		topPanel.add(rightPanel, BorderLayout.CENTER);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		buttomPanel.setLayout(fl);
		buttomPanel.add(previousBtn);
		buttomPanel.add(nextBtn);
		buttomPanel.add(cancelBtn);

		JPanel jpanel = new JPanel(new BorderLayout());

		buttomPanel.setPreferredSize(new Dimension(600, 40));
		jpanel.add(topPanel, BorderLayout.CENTER);
		jpanel.add(buttomPanel, BorderLayout.SOUTH);
		this.add(jpanel);

	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	private void initComponents() {
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		topPanel = new JPanel();
		buttomPanel = new JPanel();

		operJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPERATING_STEPS)); // 操作步骤
		js1 = new JSeparator();
		createStep2JLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_AC_SECOND)); // 创建第一步

		tagReconfigJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_WUHAN));
		tagActionJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_ACTION));
		addVlanIdJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID));
		addVlanPriJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI));
		this.lblModel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL));
		macCountJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_TABLE_COUNT));
		macCountField = new JTextField();
		macCountField.setText("512");
//		this.addVlanIdJTF=new PtnTextField(false,PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, this.lblMessage, this.nextBtn, this);
		addVlanIdJTF = new JTextField("2");
		addVlanPriJTF = new JTextField("0");
		tagReconfigJCB = new JComboBox();
		tagActionJCB = new JComboBox();
		this.cmbModel=new JComboBox();

		nextBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NEXT));
		previousBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_BACK));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}
	/**
	 * <p>
	 * 判断输入数值是否在指定区间内
	 * </p>
	 * @throws Exception
	 */
	private void addFocusListenerForTextfield()throws Exception{
		
		TextfieldFocusListener textfieldFocusListener=null;
		String whichTextTield=null;
		try{
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ID);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,22,addVlanIdJTF);
			addVlanIdJTF.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_PRI);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,8,addVlanPriJTF);
			addVlanPriJTF.addFocusListener(textfieldFocusListener);
			
			whichTextTield = ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_TABLE_COUNT);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield, 2, macCountField);
			macCountField.addFocusListener(textfieldFocusListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	/**
	 * <p>
	 * textfield添加监听，只允许输入数字
	 * </p>
	 * @throws Exception
	 */
	private void addKeyListenerForTextfield()throws Exception{
		
		TextFiledKeyListener textFIledKeyListener=null;
		try{
			/* 为1：只接受数字 **/
			textFIledKeyListener = new TextFiledKeyListener(1);
			addVlanIdJTF.addKeyListener(textFIledKeyListener);
			addVlanPriJTF.addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	
	public JButton getNextBtn() {
		return nextBtn;
	}

	public JButton getPreviousBtn() {
		return previousBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	public JTextField getAddVlanIdJTF() {
		return addVlanIdJTF;
	}

	public JTextField getAddVlanPriJTF() {
		return addVlanPriJTF;
	}

	public JComboBox getTagReconfigJCB() {
		return tagReconfigJCB;
	}

	public JComboBox getTagActionJCB() {
		return tagActionJCB;
	}

	public JComboBox getCmbModel() {
		return cmbModel;
	}

	public void setCmbModel(JComboBox cmbModel) {
		this.cmbModel = cmbModel;
	}

	public JTextField getMacCountField() {
		return macCountField;
	}

	public void setMacCountField(JTextField macCountField) {
		this.macCountField = macCountField;
	}

}
