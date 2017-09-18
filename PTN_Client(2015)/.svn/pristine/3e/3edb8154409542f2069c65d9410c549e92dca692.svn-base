package com.nms.ui.ptn.business.dialog.pwpath;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 映射表配置
 * @author Administrator
 *
 */
public class MappingConfigDialog extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675752076361234536L;
	
	private JLabel lblPolicy_PHB;//策略标签
	private JLabel lblDistribute_PHB;//指配标签
	private JLabel lblId_PHB;//PHBId标签
	private JComboBox cmbPolicy_PHB;//0/1=指配EXP/基于PHB到TMC|TMP EXP映射
	private JComboBox cmbDistribute_PHB;//指配EXP:0-7
	private JComboBox cmbId_PHB;//PHB2EXP_ID:1-15
	private JPanel panelPHB;
	
	private JLabel lblPolicy_EXP;//策略标签
	private JLabel lblDistribute_EXP;//指配标签
	private JLabel lblId_EXP;//EXPId标签
	private JComboBox cmbPolicy_EXP;//0/1=指配PHB/基于TMP EXP 到PHB映射表
	private JComboBox cmbDistribute_EXP;//指配PHB:0/1/2/3/4/5/6/7=BE/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private JComboBox cmbId_EXP;//EXP 2 PHB _ID:1-15
	private JPanel panelEXP;
	
	private JButton btnSave;//保存按钮
	private JButton btnCancel;//取消按钮
	private JPanel panelBtn;
	
	private PwInfo pwInfo;
	
	public MappingConfigDialog (PwInfo pwinfo){
		try {
			this.setModal(true);
			super.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_MAPPING_MANAGE));
			if(null==pwinfo){
				pwinfo = new PwInfo();
				this.pwInfo = pwinfo;
			}else{
				this.pwInfo = pwinfo;
			}
			this.initComponents();
			this.setLayout();
			this.initData();
			this.addListeners();
			UiUtil.showWindow(this, 700, 350);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponents()throws Exception {
		lblPolicy_PHB = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_STRATEGY_));
		lblDistribute_PHB = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_EXP));
		lblId_PHB = new JLabel("PHB2EXP_ID");
		cmbPolicy_PHB = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbPolicy_PHB, "Policy_PHB");
		cmbDistribute_PHB = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbDistribute_PHB, "TC");
		cmbId_PHB = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbId_PHB, "PHBAndEXPId");
		panelPHB = new JPanel();
		
		lblPolicy_EXP = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_STRATEGY_));
		lblDistribute_EXP = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG_PHB));
		lblId_EXP = new JLabel("EXP2PHB_ID");
		cmbPolicy_EXP = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbPolicy_EXP, "Policy_EXP");
		cmbDistribute_EXP = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbDistribute_EXP, "CONRIRMPHB");
		cmbId_EXP = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbId_EXP, "PHBAndEXPId");
		panelEXP = new JPanel();
		
		btnSave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		btnCancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		panelBtn = new JPanel();
		
	}

	private void setLayout()throws Exception {
		this.setPHBLayout();
		this.setEXPLayout();
		this.setBtnLayout();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		layout.columnWidths = new int[] { 5,140,140,5 };
		layout.columnWeights = new double[] { 0, 0.1, 0.1, 0 };
		layout.rowHeights = new int[] { 180, 20};
		layout.rowWeights = new double[] { 0, 0 };
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 1;
		c.gridy = 0;
		layout.setConstraints(this.panelPHB, c);
		this.add(this.panelPHB);
		
		c.gridx = 2;
		layout.setConstraints(this.panelEXP, c);
		this.add(this.panelEXP);
		
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20, 5, 5, 5);
		layout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}

	private void setPHBLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 5,20,140,5 };
		componentLayout.columnWeights = new double[] { 0,0,0,0 };                              
		componentLayout.rowHeights = new int[] {5,25,25,25,5};
		componentLayout.rowWeights = new double[] {0,0,0,0,0};
		this.panelPHB.setLayout(componentLayout);
		this.panelPHB.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysBtn.BTN_PHB_TMCTMP_EXP)));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 5, 10, 5);
		//第一行
		c.gridx = 1;
		c.gridy = 1;
		componentLayout.setConstraints(this.lblPolicy_PHB, c);
		this.panelPHB.add(this.lblPolicy_PHB);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbPolicy_PHB, c);
		this.panelPHB.add(this.cmbPolicy_PHB);
		//第二行
		c.gridx = 1;
		c.gridy = 2;
		componentLayout.setConstraints(this.lblDistribute_PHB, c);
		this.panelPHB.add(this.lblDistribute_PHB);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbDistribute_PHB, c);
		this.panelPHB.add(this.cmbDistribute_PHB);
		//第三行
		c.gridx = 1;
		c.gridy = 3;
		componentLayout.setConstraints(this.lblId_PHB, c);
		this.panelPHB.add(this.lblId_PHB);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbId_PHB, c);
		this.panelPHB.add(this.cmbId_PHB);
	}

	private void setEXPLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 5,20,140,5 };
		componentLayout.columnWeights = new double[] { 0,0,0,0 };
		componentLayout.rowHeights = new int[] {5,25,25,25,5};
		componentLayout.rowWeights = new double[] {0,0,0,0,0};
		this.panelEXP.setLayout(componentLayout);
		this.panelEXP.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysBtn.BTN_TMC_EXP_PHB)));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 5, 10, 5);
		//第一行
		c.gridx = 1;
		c.gridy = 1;
		componentLayout.setConstraints(this.lblPolicy_EXP, c);
		this.panelEXP.add(this.lblPolicy_EXP);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbPolicy_EXP, c);
		this.panelEXP.add(this.cmbPolicy_EXP);
		//第二行
		c.gridx = 1;
		c.gridy = 2;
		componentLayout.setConstraints(this.lblDistribute_EXP, c);
		this.panelEXP.add(this.lblDistribute_EXP);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbDistribute_EXP, c);
		this.panelEXP.add(this.cmbDistribute_EXP);
		//第三行
		c.gridx = 1;
		c.gridy = 3;
		componentLayout.setConstraints(this.lblId_EXP, c);
		this.panelEXP.add(this.lblId_EXP);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.cmbId_EXP, c);
		this.panelEXP.add(this.cmbId_EXP);
	}

	private void setBtnLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 200, 10 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 60 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.btnCancel, c);
		this.panelBtn.add(this.btnCancel);
	}

	private void initData()throws Exception {
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbPolicy_PHB, pwInfo.getExpStrategy()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbDistribute_PHB, pwInfo.getExpAssignment()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbId_PHB, pwInfo.getPhbToExpId()+"");
		
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbPolicy_EXP, pwInfo.getPhbStrategy()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbDistribute_EXP, pwInfo.getPhbAssignment()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.cmbId_EXP, pwInfo.getExpTophbId()+"");
		
	}

	private void addListeners() throws Exception{
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		this.btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					getData();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
	}

	protected void getData()throws Exception{
		ControlKeyValue key = (ControlKeyValue) this.cmbPolicy_PHB.getSelectedItem();
		pwInfo.setExpStrategy(Integer.parseInt(((Code)key.getObject()).getCodeValue()));
		key = (ControlKeyValue) this.cmbDistribute_PHB.getSelectedItem();
		pwInfo.setExpAssignment(Integer.parseInt(((Code)key.getObject()).getCodeValue()));
		key = (ControlKeyValue) this.cmbId_PHB.getSelectedItem();
		pwInfo.setPhbToExpId(Integer.parseInt(((Code)key.getObject()).getCodeValue()));
		key = (ControlKeyValue) this.cmbPolicy_EXP.getSelectedItem();
		pwInfo.setPhbStrategy(Integer.parseInt(((Code)key.getObject()).getCodeValue()));
		key = (ControlKeyValue) this.cmbDistribute_EXP.getSelectedItem();
		pwInfo.setPhbAssignment(Integer.parseInt(((Code)key.getObject()).getCodeValue()));
		key = (ControlKeyValue) this.cmbId_EXP.getSelectedItem();
		pwInfo.setExpTophbId(Integer.parseInt(((Code)key.getObject()).getCodeValue()));

		DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SAVE_SUCCEED));
		this.dispose();
	}
	
}
