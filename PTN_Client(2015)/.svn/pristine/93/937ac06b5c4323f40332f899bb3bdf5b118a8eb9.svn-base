package com.nms.ui.ptn.ne.ecn.ospf.areaconfig.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;

public class SaveAreaDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OSPFAREAInfo OSPFAREAInfo;
	private AreaConfigPanel panel;

	public SaveAreaDialog(OSPFAREAInfo OSPFAREAInfo, AreaConfigPanel panel2) {
		this.setModal(true);
		try {
			initComponents();
			this.OSPFAREAInfo = OSPFAREAInfo;
			this.panel = panel2;
			setLayout();
			initDate();
			addListener();
			if (OSPFAREAInfo != null) {
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_AREA));
				ospfareaidtext.setText(OSPFAREAInfo.getArea_range());
				//UiUtil.comboBoxSelectByValue(areatypecom, OSPFAREAInfo.getType());
				
				if(OSPFAREAInfo.getType().equals("stub")){
					areatypecom.setSelectedIndex(1);
					nosummaryistrue.setSelected(OSPFAREAInfo.getSummary()==0?false:true);
					metrictext.setText(OSPFAREAInfo.getMetric()+"");
				}else if(OSPFAREAInfo.getType().equals("nssa")){
					areatypecom.setSelectedIndex(2);
					nosummaryistrue.setSelected(OSPFAREAInfo.getSummary()==0?false:true);
					metrictext.setText(OSPFAREAInfo.getMetric()+"");
				}else {
					areatypecom.setSelectedIndex(0);
				}
				
			} else {
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_AREA));
			}
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void initComponents() {
		
		try {
			jPanel = new JPanel();
			btnsave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
			btncanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			ospfareaid = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OSPF_AREA_ID));
			lblMessage=new JLabel();
			ospfareaidtext = new PtnTextField(true, null, PtnTextField.TYPE_INT, 3, lblMessage, btnsave, this);
			ospfareaidtext.setCheckingMaxValue(true);
			ospfareaidtext.setCheckingMinValue(true);
			ospfareaidtext.setMinValue(0);
			ospfareaidtext.setMaxValue(255);
			areatype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_AREA_TYPE));
			areatypecom = new JComboBox();			
			super.getComboBoxDataUtil().comboBoxData(areatypecom, "OSPFAREA区域类型");
			metric = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEFAULT_METRIC));		
			metrictext = new PtnTextField(false, null, PtnTextField.TYPE_INT, 5, lblMessage, btnsave, this)	;
			metrictext.setCheckingMaxValue(true);
			metrictext.setCheckingMinValue(true);
			metrictext.setMinValue(1);
			metrictext.setMaxValue(65535);
			metrictext.setEnabled(false);
			nosummary = new JLabel("No Summary");
			nosummaryistrue = new JCheckBox();		
			nosummaryistrue.setEnabled(false);
			jPanel1 = new JPanel();
			jPanel2 = new JPanel();
		
			// jTabbedPane = new JTabbedPane();
			// jTabbedPane.add("区域边界配置表",jPanel1);
			// jTabbedPane.add("虚拟链路配置表",jPanel2);
			// jTabbedPane.setPreferredSize(new Dimension(200,300));
		} catch (Exception e1) {
			DialogBoxUtil.errorDialog(this, e1.getMessage());
		}		
		
	}

	private void setLayout() {

		Dimension dimension = new Dimension(450, 200);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 120, 50, 120, 40 };
		layout.columnWeights = new double[] { 0, 0.1, 0, 0.1 };
		layout.rowHeights = new int[] { 25, 35, 35, 10, 35,  };
		layout.rowWeights = new double[] { 0, 0, 0,  0, 0.2 };
		this.jPanel.setLayout(layout);
		this.add(jPanel);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblMessage, c);
		this.jPanel.add(lblMessage);
		
		
		/** 第一行 OSPF区域ID 区域类型 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(ospfareaid, c);
		this.jPanel.add(ospfareaid);

		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(ospfareaidtext, c);
		this.jPanel.add(ospfareaidtext);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(areatype, c);
		this.jPanel.add(areatype);
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(areatypecom, c);
		this.jPanel.add(areatypecom);

		/** 第二行 默认Metric No summary */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(metric, c);
		this.jPanel.add(metric);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(metrictext, c);
		this.jPanel.add(metrictext);
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(nosummary, c);
		this.jPanel.add(nosummary);
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(nosummaryistrue, c);
		this.jPanel.add(nosummaryistrue);

		

		/** 第5行 空出一行确定和取消按钮 */
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.setConstraints(btnsave, c);
		this.jPanel.add(btnsave);

		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.fill = GridBagConstraints.EAST;
		layout.addLayoutComponent(btncanel, c);
		this.jPanel.add(btncanel);

		SavaAdreePanel savaadreepanel = new SavaAdreePanel();
		savaadreepanel.setPreferredSize(new Dimension(400, 150));
		this.jPanel1.add(savaadreepanel);

		SaveVLPanel saveVLPanel = new SaveVLPanel();
		saveVLPanel.setPreferredSize(new Dimension(400, 150));
		this.jPanel2.add(saveVLPanel);
	}

	private void showWindow() {
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}
	private void initDate()  throws Exception {
		try {
		
				SaveAreaDialog.this.lblMessage.setText("");
				btnsave.setEnabled(true);
				SaveAreaDialog.this.metrictext.setBorder(PtnTextField.textFieldBorder);
		} catch (Exception e) {
			throw e;
		}
	}
	private void addListener() {
		
	
		this.areatypecom.addItemListener(new java.awt.event.ItemListener()
		{
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent evt)
			{
				if (evt.getStateChange() == 1)
				{
					try
					{
						Code code = (Code) ((ControlKeyValue) evt.getItem())
								.getObject();
						
						if(code.getCodeValue().equals("0")){
							nosummaryistrue.setEnabled(false);
							nosummaryistrue.setSelected(false);
							metrictext.setEnabled(false);
							metrictext.setText("");
						}else {
							nosummaryistrue.setEnabled(true);
							metrictext.setEnabled(true);
						}
						if(metrictext.getText()==null||metrictext.getText().equals("null")){
							metrictext.setText("");
						}
					}catch(Exception e1){
						DialogBoxUtil.errorDialog(SaveAreaDialog.this, e1.getMessage());
					}
				}
			}
		});
			
		btnsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});

		btncanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveAreaDialog.this.dispose();
			}
		});
	}

	private void save() {
		// System.out.println("保存");
		String message = "";
		try {
			OSPFAREAInfo OSPFAREAInfo = new OSPFAREAInfo();
			OSPFAREAInfo.setNeId(ConstantUtil.siteId + "");
			OSPFAREAInfo.setType(areatypecom.getSelectedItem()+"");
			String area_range = ospfareaidtext.getText();
			OSPFAREAInfo.setArea_range(area_range);
			OSPFAREAInfo.setStatus(1);
			OSPFAREAInfo.setSummary(this.nosummaryistrue.isSelected()?1:0);
			if(this.metrictext.getText()!=null&&!this.metrictext.getText().equals("")){
				OSPFAREAInfo.setMetric(Integer.parseInt(this.metrictext.getText()));
			}
			
			DispatchUtil OSPFAREADispatch = new DispatchUtil(RmiKeys.RMI_OSPFAREA);
			if(this.OSPFAREAInfo!=null){
				message = OSPFAREADispatch.excuteUpdate(OSPFAREAInfo);
			}else{
				message = OSPFAREADispatch.excuteInsert(OSPFAREAInfo);
			}
			

			DialogBoxUtil.succeedDialog(this, message);
			OSPFAREADispatch = null;
			this.panel.initData();
			this.dispose();
		} catch (Exception e) {
			DialogBoxUtil.errorDialog(this, e.getMessage());
		}

	}
	

	private JLabel ospfareaid;
	private PtnTextField ospfareaidtext;
	private JLabel areatype;
	private JComboBox areatypecom;
	private JLabel metric;
	private PtnTextField metrictext;
	private JLabel nosummary;
	private JCheckBox nosummaryistrue;
	// private JTabbedPane jTabbedPane;
	private JButton btnsave;
	private JButton btncanel;
	private JPanel jPanel;
	private JPanel jPanel1;
	private JPanel jPanel2;

	private JLabel lblMessage;
}
