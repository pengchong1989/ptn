package com.nms.ui.ptn.ne.ac.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.system.code.Code;
import com.nms.model.ptn.port.AcBufferService_MB;
import com.nms.model.system.code.CodeService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * AC附加流
 * @author dzy
 *
 */
public class AddBufferCXDialog extends PtnDialog {
	private Map<Integer, String> codeIdAndCodeNameMap = new HashMap<Integer, String>();
	private JPanel bufferPanel;
	private JPanel qosPanel;
	private JPanel vlanPriPanel;
	private JPanel topPanel;
	private JPanel buttomPanel;
	private JLabel lblCOS;//cos
	private JLabel lblCIR;//CIR
	private JLabel lblCBS;//CBS
	private JLabel lblColorAware;//ColorAware
	private JLabel lblPIR;//PIR
	private JLabel lblSEQ;
	private JLabel lblEIR;
	private JLabel lblEBS;
	private JLabel vlanPri;
	private JComboBox COSText;
	private PtnSpinner CIRText;
	private PtnSpinner CBSText;
	private JCheckBox ColorAwareText;
	private PtnSpinner PIRText;
	private PtnSpinner SEQText;
	private PtnSpinner EIRText;
	private PtnSpinner EBSText;
	private JLabel sourceMacLabel;
	private JLabel sinkMacLabel;
	private JLabel clientVlanId;
	private JLabel operatorVlanId;
	private JLabel clientVlanPriority;
	private JLabel operatorVlanPriority;
	private JLabel iPType;
	private JLabel ethernetType;
	private JLabel typeMapping;
	private JLabel sourceIpLabel;
	private JLabel sinkIpLabel;
	private JLabel valueMapping;
	private JLabel mask;
	private JTextField clientVlanIdValue;
	private JTextField clientVlanIdMask;
	private JTextField operatorVlanIdValue;
	private JTextField operatorVlanIdMask;
	private JTextField clientVlanPriorityValue;
	private JTextField clientVlanPriorityMask;
	private JTextField operatorVlanPriorityValue;
	private JTextField operatorVlanPriorityMask;
	private JTextField ethernetTypeValue;
	private JTextField ethernetTypeMask;
	private JTextField sourceMacLabelValue;
	private JTextField sourceMacLabelMask;
	private JTextField sinkMacLabelValue;
	private JTextField sinkMacLabelMask;
	private JTextField iPTypeValue;
	private JTextField iPTypeMask;
	private JTextField sourceIpLabelValue;
	private JTextField sourceIpLabelMask;
	private JTextField sinkIpLabelValue;
	private JTextField sinkIpLabelMask;
	private JComboBox vlanPriCombo;
	private PtnButton confirmBtn;
	private JButton cancelBtn;
	AddACDialog dialog;
	Acbuffer acbuffer = null;
	/**
	 * 创建一个新的实例
	 * @param addAcDialog
	 * 				主页面
	 * @param modal
	 * @param obj
	 * 			细分流对象
	 */
	public AddBufferCXDialog(AddACDialog addAcDialog, boolean modal, Acbuffer obj) {
		try {
			this.setModal(true);
			this.acbuffer = obj;
			codeIdAndCodeNameMap = this.getCodeIdAndCodeNameMap();
			this.dialog=addAcDialog;
			initComponents();
			initDate();
			setLayout();
			this.addListener();
			UiUtil.showWindow(this, 500, 600);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initDate() throws Exception {
		//修改
		if (this.getAcbuffer() != null) {
			this.operatorVlanIdValue.setText(this.getAcbuffer().getOperatorVlanIdValue());
			this.operatorVlanIdMask.setText(this.getAcbuffer().getOperatorVlanIdMask());
			this.clientVlanIdValue.setText(this.getAcbuffer().getClientVlanIdValue());
			this.clientVlanIdMask.setText(this.getAcbuffer().getClientVlanIdMask());
			this.operatorVlanPriorityValue.setText(this.getAcbuffer().getOperatorVlanPriorityValue());
			this.operatorVlanPriorityMask.setText(this.getAcbuffer().getOperatorVlanPriorityMask());
			this.clientVlanPriorityValue.setText(this.getAcbuffer().getClientVlanPriorityValue());
			this.clientVlanPriorityMask.setText(this.getAcbuffer().getClientVlanPriorityMask());
			this.ethernetTypeValue.setText(this.getAcbuffer().getEthernetTypeValue());
			this.ethernetTypeMask.setText(this.getAcbuffer().getEthernetTypeMask());
			this.sinkMacLabelValue.setText(this.getAcbuffer().getTargetMac());
			this.sinkMacLabelMask.setText(this.getAcbuffer().getSinkMacLabelMask());
			this.sourceMacLabelValue.setText(this.getAcbuffer().getSourceMac());
			this.sourceMacLabelMask.setText(this.getAcbuffer().getSourceMacLabelMask());
			this.iPTypeValue.setText(this.getAcbuffer().getiPTypeValue());
			this.iPTypeMask.setText(this.getAcbuffer().getiPTypeMask());
			this.sinkIpLabelValue.setText(this.getAcbuffer().getTargetIp());
			this.sinkIpLabelMask.setText(this.getAcbuffer().getSinkIpLabelMask());
			this.sourceIpLabelValue.setText(this.getAcbuffer().getSourceIp());
			this.sourceIpLabelMask.setText(this.getAcbuffer().getSourceIpLabelMask());
			this.SEQText.setValue(this.getAcbuffer().getSeq());
			this.CIRText.setValue(this.getAcbuffer().getCir());
			this.CBSText.setValue(this.getAcbuffer().getCbs());
			this.PIRText.setValue(this.getAcbuffer().getPir());
			this.EIRText.setValue(this.getAcbuffer().getEir());
			this.EBSText.setValue(this.getAcbuffer().getEbs());
			if(this.getAcbuffer().getCm()==0){
				this.ColorAwareText.setSelected(false);;
			}else{
				this.ColorAwareText.setSelected(true);;
			}
			
			super.getComboBoxDataUtil().comboBoxData(this.COSText, "CONRIRMPHB");
			if(UiUtil.getCodeByValue("BUFTYPE", "2").getCodeValue().equals(this.getAcbuffer().getQosType())){
				super.getComboBoxDataUtil().comboBoxData(this.vlanPriCombo, "VLANPRI");
			}
		}
		//新建
		else{
			this.operatorVlanIdValue.setText("0");
			this.operatorVlanIdMask.setText("0");
			this.clientVlanIdValue.setText("0");
			this.clientVlanIdMask.setText("0");
			this.operatorVlanPriorityValue.setText("0");
			this.operatorVlanPriorityMask.setText("0");
			this.clientVlanPriorityValue.setText("0");
			this.clientVlanPriorityMask.setText("0");
			this.CIRText.setValue(0);
			this.CBSText.setValue(-1);
			this.SEQText.setValue(0);
			this.EIRText.setValue(0);
			this.EBSText.setValue(-1);
			this.PIRText.setValue(Integer.parseInt(this.CIRText.getValue().toString())+Integer.parseInt(this.EIRText.getValue().toString()));
			super.getComboBoxDataUtil().comboBoxData(this.COSText, "CONRIRMPHB");
			super.getComboBoxDataUtil().comboBoxData(this.vlanPriCombo, "VLANPRI");
			if(UiUtil.getCodeByValue("BUFTYPE", "0").getId()==Integer.parseInt(((ControlKeyValue)dialog.getStep3().getBufferTypeJCB().getModel().getSelectedItem()).getId())){
				this.ethernetTypeValue.setText("0");
				this.ethernetTypeMask.setText("0");
				this.sinkMacLabelValue.setText("0");
				this.sinkMacLabelMask.setText("0");
				this.sourceMacLabelValue.setText("0");
				this.sourceMacLabelMask.setText("0");
			}else{
				this.iPTypeValue.setText("0");
				this.iPTypeMask.setText("0");
				this.sinkIpLabelValue.setText("0");
				this.sinkIpLabelMask.setText("0");
				this.sourceIpLabelValue.setText("0");
				this.sourceIpLabelMask.setText("0");
			}
		}
	}

	/**
	 * 主布局管理
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void setLayout() throws NumberFormatException, Exception {
		
		bufferSetLayOut();
		qosSetLayOut();
		vlanSetLayOut();
		this.topPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_SUBDIVIDE_QOS)));
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 450 };
		layout.columnWeights = new double[] { 0, 0 };
		layout.rowHeights = new int[] { 150,50,150,50 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1,0.1,0.1,0.6};
		topPanel.setLayout(layout);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor =GridBagConstraints.NORTHWEST;
		//流
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor =GridBagConstraints.NORTH;
		layout.setConstraints(this.bufferPanel, c);
		topPanel.add(this.bufferPanel);
		
		//vlanpri
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor =GridBagConstraints.NORTH;
		layout.setConstraints(this.vlanPriPanel, c);
		topPanel.add(this.vlanPriPanel);
		//QOS
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor =GridBagConstraints.CENTER;
		layout.setConstraints(this.qosPanel, c);
		topPanel.add(this.qosPanel);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		buttomPanel.setLayout(fl);
		buttomPanel.add(confirmBtn);
		buttomPanel.add(cancelBtn);

		JPanel jpanel = new JPanel(new BorderLayout());
		buttomPanel.setPreferredSize(new Dimension(600, 40));

		jpanel.add(topPanel, BorderLayout.CENTER);
		jpanel.add(buttomPanel, BorderLayout.SOUTH);

		this.add(jpanel);

	}

	/**
	 * VLANPRI布局管理
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void vlanSetLayOut() {
		GridBagConstraints c = new GridBagConstraints();
		this.vlanPriPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_VLANPRI)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 180,160};
		layout.columnWeights = new double[] { 0, 0, 0 ,0};
		layout.rowHeights = new int[] { 80 };
		layout.rowWeights = new double[] { 0.1, };
		vlanPriPanel.setLayout(layout);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor =GridBagConstraints.NORTHWEST;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.vlanPri, c);
		vlanPriPanel.add(this.vlanPri);
		c.gridx = 1;
		layout.setConstraints(this.vlanPriCombo, c);
		vlanPriPanel.add(this.vlanPriCombo);
		
	}

	/**
	 * QOS布局管理
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void qosSetLayOut() {
		GridBagConstraints c = new GridBagConstraints();
		this.qosPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_QOS)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 130, 70 ,130};
		layout.columnWeights = new double[] { 0, 0, 0 ,0};
		layout.rowHeights = new int[] { 40, 40, 40,40 };
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.6 };
		qosPanel.setLayout(layout);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor =GridBagConstraints.NORTHWEST;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblSEQ, c);
		qosPanel.add(this.lblSEQ);
		c.gridx = 1;
		layout.setConstraints(this.SEQText, c);
		qosPanel.add(this.SEQText);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblCOS, c);
		qosPanel.add(this.lblCOS);
		c.gridx =3;
		layout.setConstraints(this.COSText, c);
		qosPanel.add(this.COSText);
		
		
		c.gridx = 0;
		c.gridy =1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblCIR, c);
		qosPanel.add(this.lblCIR);
		c.gridx = 1;
		layout.setConstraints(this.CIRText, c);
		qosPanel.add(this.CIRText);
		
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblCBS, c);
		qosPanel.add(this.lblCBS);
		c.gridx =3;
		layout.setConstraints(this.CBSText, c);
		qosPanel.add(this.CBSText);
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblEIR, c);
		qosPanel.add(this.lblEIR);
		c.gridx =1;
		layout.setConstraints(this.EIRText, c);
		qosPanel.add(this.EIRText);
		
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblEBS, c);
		qosPanel.add(this.lblEBS);
		c.gridx =3;
		layout.setConstraints(this.EBSText, c);
		qosPanel.add(this.EBSText);
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblColorAware, c);
		qosPanel.add(this.lblColorAware);
		c.gridx =3;
		layout.setConstraints(this.ColorAwareText, c);
		qosPanel.add(this.ColorAwareText);
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.lblPIR, c);
		qosPanel.add(this.lblPIR);
		c.gridx =1;
		layout.setConstraints(this.PIRText, c);
		qosPanel.add(this.PIRText);
	
		
	}

	/**
	 * 基本信息布局管理
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private void bufferSetLayOut() throws NumberFormatException, Exception {
		GridBagConstraints c = new GridBagConstraints();
		this.bufferPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_APPEND_BUFFER)));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 180, 180  };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40 ,40};
		layout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1 };
		bufferPanel.setLayout(layout);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.anchor =GridBagConstraints.NORTHWEST;
		
		//运营商vlanid
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.typeMapping, c);
		bufferPanel.add(this.typeMapping);
		c.gridx = 1;
		layout.setConstraints(this.valueMapping, c);
		bufferPanel.add(this.valueMapping);
		c.gridx = 2;
		layout.setConstraints(this.mask, c);
		bufferPanel.add(this.mask);
				
				
		//运营商vlanid
		c.gridx = 0;
		c.gridy =1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.operatorVlanId, c);
		bufferPanel.add(this.operatorVlanId);
		c.gridx = 1;
		layout.setConstraints(this.operatorVlanIdValue, c);
		bufferPanel.add(this.operatorVlanIdValue);
		c.gridx = 2;
		layout.setConstraints(this.operatorVlanIdMask, c);
		bufferPanel.add(this.operatorVlanIdMask);
		
		//客户vlanid
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.clientVlanId, c);
		bufferPanel.add(this.clientVlanId);
		c.gridx = 1;
		layout.setConstraints(this.clientVlanIdValue, c);
		bufferPanel.add(this.clientVlanIdValue);
		c.gridx = 2;
		layout.setConstraints(this.clientVlanIdMask, c);
		bufferPanel.add(this.clientVlanIdMask);
		//运营商vlan优先级
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.operatorVlanPriority, c);
		bufferPanel.add(this.operatorVlanPriority);
		c.gridx = 1;
		layout.setConstraints(this.operatorVlanPriorityValue, c);
		bufferPanel.add(this.operatorVlanPriorityValue);
		c.gridx = 2;
		layout.setConstraints(this.operatorVlanPriorityMask, c);
		bufferPanel.add(this.operatorVlanPriorityMask);
		//客户vlan优先级
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.clientVlanPriority, c);
		bufferPanel.add(this.clientVlanPriority);
		c.gridx = 1;
		layout.setConstraints(this.clientVlanPriorityValue, c);
		bufferPanel.add(this.clientVlanPriorityValue);
		c.gridx = 2;
		layout.setConstraints(this.clientVlanPriorityMask, c);
		bufferPanel.add(this.clientVlanPriorityMask);
		
		if(UiUtil.getCodeByValue("BUFTYPE", "0").getId()==Integer.parseInt(((ControlKeyValue)dialog.getStep3().getBufferTypeJCB().getModel().getSelectedItem()).getId())){
			//以太网类型
			c.gridx = 0;
			c.gridy = 5;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.ethernetType, c);
			bufferPanel.add(this.ethernetType);
			c.gridx = 1;
			layout.setConstraints(this.ethernetTypeValue, c);
			bufferPanel.add(this.ethernetTypeValue);
			c.gridx = 2;
			layout.setConstraints(this.ethernetTypeMask, c);
			bufferPanel.add(this.ethernetTypeMask);
			
			//目的mac地址
			c.gridx = 0;
			c.gridy = 6;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.sinkMacLabel, c);
			bufferPanel.add(this.sinkMacLabel);
			c.gridx = 1;
			layout.setConstraints(this.sinkMacLabelValue, c);
			bufferPanel.add(this.sinkMacLabelValue);
			c.gridx = 2;
			layout.setConstraints(this.sinkMacLabelMask, c);
			bufferPanel.add(this.sinkMacLabelMask);
			//源mac地址
			c.gridx = 0;
			c.gridy = 7;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.sourceMacLabel, c);
			bufferPanel.add(this.sourceMacLabel);
			c.gridx = 1;
			layout.setConstraints(this.sourceMacLabelValue, c);
			bufferPanel.add(this.sourceMacLabelValue);
			c.gridx = 2;
			layout.setConstraints(this.sourceMacLabelMask, c);
			bufferPanel.add(this.sourceMacLabelMask);
		}else{
			c.gridx = 0;
			c.gridy = 5;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.sinkIpLabel, c);
			bufferPanel.add(this.sinkIpLabel);
			c.gridx = 1;
			layout.setConstraints(this.sinkIpLabelValue, c);
			bufferPanel.add(this.sinkIpLabelValue);
			c.gridx = 2;
			layout.setConstraints(this.sinkIpLabelMask, c);
			bufferPanel.add(this.sinkIpLabelMask);
			
			//目的mac地址
			c.gridx = 0;
			c.gridy = 6;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.sourceIpLabel, c);
			bufferPanel.add(this.sourceIpLabel);
			c.gridx = 1;
			layout.setConstraints(this.sourceIpLabelValue, c);
			bufferPanel.add(this.sourceIpLabelValue);
			c.gridx = 2;
			layout.setConstraints(this.sourceIpLabelMask, c);
			bufferPanel.add(this.sourceIpLabelMask);
			//源mac地址
			c.gridx = 0;
			c.gridy = 7;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 5);
			layout.setConstraints(this.iPType, c);
			bufferPanel.add(this.iPType);
			c.gridx = 1;
			layout.setConstraints(this.iPTypeValue, c);
			bufferPanel.add(this.iPTypeValue);
			c.gridx = 2;
			layout.setConstraints(this.iPTypeMask, c);
			bufferPanel.add(this.iPTypeMask);
		}
		
	}

	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponents() throws Exception {
		qosPanel = new JPanel();
		this.bufferPanel = new JPanel();
		topPanel = new JPanel();
		buttomPanel = new JPanel();
		vlanPriPanel = new JPanel();

		confirmBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	
		
		COSText=new JComboBox();
		CIRText=new PtnSpinner(10000000,0,64,ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		CBSText=new PtnSpinner(-1,-1,256000000,1);
		ColorAwareText=new JCheckBox();
		PIRText=new PtnSpinner(20000000,0,64,ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		PIRText.setEnabled(false);
		this.SEQText = new PtnSpinner(1000,0,1,ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		this.EIRText = new PtnSpinner(10000000,0,64,ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		this.EBSText = new PtnSpinner(-1,-1,256000000,1);
		lblCOS=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_COS));
		lblCIR=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		lblCBS=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
		lblColorAware=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_COLOR_AWARE));
		lblPIR=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PIR));
		this.lblSEQ = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SEQ));
		this.lblEIR = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EIR));
		this.lblEBS = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EBS));
		
		typeMapping = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE_MAPPING));
		valueMapping = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VALUE_MAPPING));
		mask = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MASK));
		clientVlanId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_CLIENTID));
		operatorVlanId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_OPERATOR_ID));
		clientVlanPriority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_CLIENT_PRIORITY));
		operatorVlanPriority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_OPERATOR_PRIORITY));
		ethernetType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ETHERNET_TYPE));
		sourceMacLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC));
		sinkMacLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC));
		iPType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_IP_TYPE));
		sourceIpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP));
		sinkIpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP));
		
		clientVlanIdValue = new JTextField();
		clientVlanIdMask = new JTextField();
		operatorVlanIdValue = new JTextField();
		operatorVlanIdMask = new JTextField();
		clientVlanPriorityValue = new JTextField();
		clientVlanPriorityMask = new JTextField();
		operatorVlanPriorityValue = new JTextField();
		operatorVlanPriorityMask = new JTextField();
		ethernetTypeValue = new JTextField();
		ethernetTypeMask = new JTextField();
		sourceMacLabelValue = new JTextField();
		sourceMacLabelMask = new JTextField();
		sinkMacLabelValue = new JTextField();
		sinkMacLabelMask = new JTextField();
		iPTypeValue = new JTextField();
		iPTypeMask = new JTextField();
		sourceIpLabelValue = new JTextField();
		sourceIpLabelMask = new JTextField();
		sinkIpLabelValue = new JTextField();
		sinkIpLabelMask = new JTextField();
		
		vlanPri = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_PRI));
		vlanPriCombo = new JComboBox();
		
		//L2
		if(UiUtil.getCodeByValue("BUFTYPE", "2").getId()==Integer.parseInt(((ControlKeyValue)dialog.getStep3().getBufferTypeJCB().getModel().getSelectedItem()).getId())){
			clientVlanIdValue.setEnabled(false);
			clientVlanIdMask.setEnabled(false);
			operatorVlanIdValue.setEnabled(false);
			operatorVlanIdMask.setEnabled(false);
			clientVlanPriorityValue.setEnabled(false);
			clientVlanPriorityMask.setEnabled(false);
			operatorVlanPriorityValue .setEnabled(false);
			operatorVlanPriorityMask.setEnabled(false);
			ethernetTypeValue.setEnabled(false);
			ethernetTypeMask.setEnabled(false);
			sourceMacLabelValue.setEnabled(false);
			sourceMacLabelMask.setEnabled(false);
			sinkMacLabelValue.setEnabled(false);
			sinkMacLabelMask.setEnabled(false);
			iPTypeValue.setEnabled(false);
			iPTypeMask.setEnabled(false);
			sourceIpLabelValue.setEnabled(false);
			sourceIpLabelMask.setEnabled(false);
			sinkIpLabelValue.setEnabled(false);
			sinkIpLabelMask.setEnabled(false);
		}else{
			this.vlanPriCombo.setEnabled(false);
		}
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener() {
		this.confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmBtnActionPerformed(e);

			}
		});
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelBtnActionPerformed(e);
			}

		
		});
		this.CBSText.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cbsChangeActionPerformed(arg0);
				
			}

			
		});
		this.EBSText.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cbsChangeActionPerformed(arg0);
				
			}
		});
		this.CIRText.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cirChangeActionPerformed(arg0);
				
			}
		});
		this.EIRText.addChangeListener(new ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cirChangeActionPerformed(arg0);
				
			}
		});
	}
	
	//监听事件实现方法
	private void cirChangeActionPerformed(ChangeEvent arg0) {
		this.PIRText.setValue(Integer.parseInt(this.CIRText.getValue().toString())+Integer.parseInt(this.EIRText.getValue().toString()));
		
		
	}
	//监听事件实现方法
	private void cbsChangeActionPerformed(ChangeEvent arg0) {
		try {
			if(Integer.parseInt(this.CBSText.getValue().toString())>-1&&Integer.parseInt(this.CBSText.getValue().toString())<2000){
				this.CBSText.setValue(4096);
			}
			if(Integer.parseInt(this.CBSText.getValue().toString())>2000&&Integer.parseInt(this.CBSText.getValue().toString())<4096){
				this.CBSText.setValue(-1);
			}
			if(Integer.parseInt(this.EBSText.getValue().toString())>-1&&Integer.parseInt(this.EBSText.getValue().toString())<2000){
				this.EBSText.setValue(4096);
			}
			if(Integer.parseInt(this.EBSText.getValue().toString())>2000&&Integer.parseInt(this.EBSText.getValue().toString())<4096){
				this.EBSText.setValue(-1);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 保存方法
	 * @param e
	 */
	private void confirmBtnActionPerformed(ActionEvent e) {
		Acbuffer newbuf = null;
		try {
			newbuf = collectData(); // 收集数据
			
			if (this.getAcbuffer() != null) { 
				dialog.getBufferList().remove(this.acbuffer);
			}
			this.getDialog().getBufferList().add(newbuf);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		} finally {
			newbuf = null;
		}
		detailTableDataBox();
		this.dispose();
	}
	
	/**
	 * 更新第三步的数据
	 */
	public void detailTableDataBox() {
		DefaultTableModel defaultTableModel = null;
		try {
			defaultTableModel = (DefaultTableModel) this.getDialog().getStep3().getDetailTable().getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();
			for (int i = 0; i < this.getDialog().getBufferList().size(); i++) {
				Object[] obj = new Object[] {
					this.getDialog().getBufferList().get(i),
					i + 1,
					this.getDialog().getBufferList().get(i).getSeq(),
					this.codeIdAndCodeNameMap.get(UiUtil.getCodeByValue("CONRIRMPHB", this.getDialog().getBufferList().get(i).getPhb()+"").getId()),
					this.getDialog().getBufferList().get(i).getCir(),
					this.getDialog().getBufferList().get(i).getCbs(),
					this.getDialog().getBufferList().get(i).getEir(),
					this.getDialog().getBufferList().get(i).getEbs(),
					this.getDialog().getBufferList().get(i).getCm() == 0 ? new Boolean(false): new Boolean(true),
					this.getDialog().getBufferList().get(i).getPir(),
					this.getDialog().getBufferList().get(i).getPbs() 
				};
				defaultTableModel.addRow(obj);
			}
			this.getDialog().getStep3().getDetailTable().setModel(defaultTableModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			defaultTableModel = null;
		}

	}
	/**
	 * 保存对象
	 * @return
	 * @throws Exception
	 */
	private Acbuffer collectData() throws Exception {
		Acbuffer acbuffer = new Acbuffer();
		ControlKeyValue COS = (ControlKeyValue) this.COSText.getSelectedItem();
		acbuffer.setSeq(Integer.parseInt(this.SEQText.getValue().toString()));
		acbuffer.setPhb(Integer.valueOf(((Code) COS.getObject())
				.getCodeValue()));
		acbuffer.setCir(Integer.parseInt(this.CIRText.getValue().toString()));
		acbuffer.setCbs(Integer.parseInt(this.CBSText.getValue().toString()));
		acbuffer.setEir(Integer.parseInt(this.EIRText.getValue().toString()));
		acbuffer.setEbs(Integer.parseInt(this.EBSText.getValue().toString()));
		acbuffer.setCm(this.ColorAwareText.isSelected()?1:0);
		acbuffer.setPir(Integer.parseInt(this.PIRText.getValue().toString()));
		acbuffer.setOperatorVlanIdValue(this.operatorVlanIdValue.getText());
		acbuffer.setOperatorVlanIdMask(this.operatorVlanIdMask.getText());
		acbuffer.setClientVlanIdValue(this.clientVlanIdValue.getText());
		acbuffer.setClientVlanIdMask(this.clientVlanIdMask.getText());
		acbuffer.setOperatorVlanPriorityValue(this.operatorVlanPriorityValue.getText());
		acbuffer.setOperatorVlanPriorityMask(this.operatorVlanPriorityMask.getText());
		acbuffer.setClientVlanPriorityValue(this.clientVlanPriorityValue.getText());
		acbuffer.setClientVlanPriorityMask(this.clientVlanPriorityMask.getText());
		acbuffer.setiPTypeValue(this.iPTypeValue.getText());
		acbuffer.setiPTypeMask(this.iPTypeMask.getText());
		acbuffer.setTargetIp(this.sinkIpLabelValue.getText());
		acbuffer.setSinkIpLabelMask(this.sinkIpLabelMask.getText());
		acbuffer.setSourceIp(this.sourceIpLabelValue.getText());
		acbuffer.setSourceIpLabelMask(this.sourceIpLabelMask.getText());
		acbuffer.setTargetMac(this.sinkMacLabelValue.getText());
		acbuffer.setSinkMacLabelMask(this.sinkMacLabelMask.getText());
		acbuffer.setSourceMac(this.sourceMacLabelValue.getText());
		acbuffer.setSourceMacLabelMask(this.sourceMacLabelMask.getText());
		acbuffer.setEthernetTypeValue(this.ethernetTypeValue.getText());
		acbuffer.setEthernetTypeMask(this.ethernetTypeMask.getText());
		if(UiUtil.getCodeByValue("BUFTYPE", "0").getId()==Integer.parseInt(((ControlKeyValue)dialog.getStep3().getBufferTypeJCB().getModel().getSelectedItem()).getId())){
			acbuffer.setQosType(UiUtil.getCodeByValue("BUFTYPE", "0").getCodeValue());
		}else if(UiUtil.getCodeByValue("BUFTYPE", "1").getId()==Integer.parseInt(((ControlKeyValue)dialog.getStep3().getBufferTypeJCB().getModel().getSelectedItem()).getId())){
			acbuffer.setQosType(UiUtil.getCodeByValue("BUFTYPE", "1").getCodeValue());
		}else{
			acbuffer.setQosType(UiUtil.getCodeByValue("BUFTYPE", "2").getCodeValue());
		}
		if(UiUtil.getCodeByValue("BUFTYPE", "2").getCodeValue().equals(acbuffer.getQosType())){
			ControlKeyValue vlan = (ControlKeyValue) this.vlanPriCombo.getSelectedItem();
			acbuffer.setAppendBufferName(((Code)vlan.getObject()).getCodeValue());
		}
		return acbuffer;
	}
		
	/**
	 * 取消
	 * @param e
	 */
	private void cancelBtnActionPerformed(ActionEvent e) {
		this.dispose();
		
	}

	private Map<Integer, String> getCodeIdAndCodeNameMap() {
		Map<Integer, String> codeIdAndValueMap = null;
		CodeService_MB codeService = null;
		List<Code> codeList = null;
		try {
			codeIdAndValueMap = new HashMap<Integer, String>();
			codeService = (CodeService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.Code);
			codeList = codeService.selectAll();
			for (Code code : codeList) {
				codeIdAndValueMap.put(code.getId(), code.getCodeName());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(codeService);
		}
		return codeIdAndValueMap;
	}
	
	/**
	 * 判断模板的数量
	 * @param acbuffer
	 * @return
	 * @throws Exception
	 */
	public Boolean checkAppendBuffer(Acbuffer acbuffer) throws Exception{
		AcBufferService_MB bufservice = null;
		List<Acbuffer> List = new ArrayList<Acbuffer>();
		boolean flag = true;
		try {
			bufservice = (AcBufferService_MB) ConstantUtil.serviceFactory.newService_MB(Services.UniBuffer);
			acbuffer.setSiteId(ConstantUtil.siteId);
			List = bufservice.appendBufferCount(acbuffer);
			if(List.size()>16){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_APPENDBUFFER_LIMIT));
				flag = false;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(bufservice);
		}
		return flag;
	}	
	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public void setButtomPanel(JPanel buttomPanel) {
		this.buttomPanel = buttomPanel;
	}

	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public JPanel getBufferPanel() {
		return bufferPanel;
	}

	public void setBufferPanel(JPanel bufferPanel) {
		this.bufferPanel = bufferPanel;
	}

	public JPanel getQosPanel() {
		return qosPanel;
	}

	public void setQosPanel(JPanel qosPanel) {
		this.qosPanel = qosPanel;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public JPanel getButtomPanel() {
		return buttomPanel;
	}


	public JButton getCancelBtn() {
		return cancelBtn;
	}


	public Acbuffer getAcbuffer() {
		return acbuffer;
	}


	public void setAcbuffer(Acbuffer acbuffer) {
		this.acbuffer = acbuffer;
	}


	public AddACDialog getDialog() {
		return dialog;
	}


	public void setDialog(AddACDialog dialog) {
		this.dialog = dialog;
	}


}
