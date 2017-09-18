package com.nms.ui.ptn.ne.ac.view;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;
import com.nms.ui.ptn.ne.ac.controller.BufferController;

public class AddStreamDialog extends PtnDialog {

	private static final long serialVersionUID = 4373994947614744399L;
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private PtnButton confirmButton;
	private JComboBox enableJComboBox;
	private JComboBox portModeJComboBox;
	private JComboBox cmJComboBox;
	private JComboBox strategyJComboBox;
	private JComboBox assignJComboBox;
	private JLabel titleLabel;
	private JLabel enableLabel;
	private JLabel portModeLabel;
	private JLabel vlanLabel;
	private JLabel cirLabel;
	private JLabel sourceMacLabel;
	private JLabel pirLabel;
	private JLabel sinkMacLabel;
	private JLabel cmLabel;
	private JLabel baIpLabel;
	private JLabel cbsLabel;
	private JLabel sourceIpLabel;
	private JLabel pbsLabel;
	private JLabel sinkIpLabel;
	private JLabel strategyLabel;
	private JLabel ipDscpLabel;
	private JLabel assignLabel;
	private JTextField vlanTextField;
	private JTextField cirTextField;
	private JTextField sourceMacTextField;
	private JTextField sinkMacTextField;
	private JTextField pirTextField;
	private JTextField baIpTextField;
	private JTextField cbsTextField;
	private JTextField sourceIpTextField;
	private JTextField sinkIpTextField;
	private JTextField pbsTextField;
	private JTextField ipDscpTextField;
	private Acbuffer acbuffer = null;
	private AddACDialog addAcDialog;
	private JLabel lblMessage;
	private JLabel sourceTcpPortLab;//源TCP/UDP端口号
	private JTextField sourceTcpPortText;
	private JLabel endTcpPortLab;//宿TCP/UDP端口号
	private JTextField endTcpPortText;
	private JLabel IPTOSLab;//IP TOS
	private JTextField IPTOSText;
	private JLabel portTypeLab;//端口类型
	private JComboBox portTypeCmb;

	private BufferController controller ;
	
	/** Creates new form AddStreamDialog */
	public AddStreamDialog(Frame parent, boolean modal) {
		
		this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_SUBDIVIDE_QOS));
		this.initComponents();
		this.sourceMacTextField.setText("00-00-00-00-00-00");
		this.sinkMacTextField.setText("00-00-00-00-00-00");
		try {
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
			addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/** Creates new form AddStreamDialog */
	public AddStreamDialog(AddACDialog addAcDialog, boolean modal, Acbuffer obj) {
		this.setModal(modal);
		this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_SUBDIVIDE_QOS));
		this.initComponents();
		this.addAcDialog = addAcDialog;
		this.acbuffer = obj;
		try {
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
			addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	void initComponents() {
		mainPanel = new JPanel();
		contentPanel = new JPanel();
		buttonPanel = new JPanel();
		titlePanel = new JPanel();
		cancelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		enableJComboBox = new JComboBox();
		portModeJComboBox = new JComboBox();
		cmJComboBox = new JComboBox();
		strategyJComboBox = new JComboBox();
		assignJComboBox = new JComboBox();
		titleLabel = new JLabel("");
		lblMessage=new JLabel();
		enableLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLED_STATUS));
		portModeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MODAL));
		vlanLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN));
//		cirLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CIR_WUHAN));
		cirLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CIR));
		sourceMacLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC));
//		pirLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PIR_WUHAN));
		pirLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PIR));
		sinkMacLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC));
		cmLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CM));
		baIpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EZT_IP));
		cbsLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CBS));
		sourceIpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP));
		pbsLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PBS));
		sinkIpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP));
		strategyLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_STRATEGY_MODAL));
		ipDscpLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_IPDSCP));
		assignLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_APPOINT_PHB));
		vlanTextField = new JTextField();
		cirTextField = new JTextField();
		sourceMacTextField = new JTextField();
		sinkMacTextField = new JTextField();
		pirTextField = new JTextField();
		baIpTextField = new JTextField();
		cbsTextField = new JTextField();
		sourceIpTextField = new JTextField();
		sinkIpTextField = new JTextField();
		pbsTextField = new JTextField();
		ipDscpTextField = new JTextField();
		sourceTcpPortLab = new JLabel(ResourceUtil.srcStr(StringKeysTip.TIP_SOURCE_TCP));
		sourceTcpPortText = new JTextField("0");
		endTcpPortLab = new JLabel(ResourceUtil.srcStr(StringKeysTip.TIP_END_TCP));
		endTcpPortText = new JTextField("0");
		IPTOSLab = new JLabel("IPTOS");
		IPTOSText = new JTextField("0");
		portTypeLab = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_TYPE));
		portTypeCmb = new JComboBox();
		setViewLayout();
	}

	private void setViewLayout() {
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		addComponent(mainPanel, titlePanel, 0, 0, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 0, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(mainPanel, contentPanel, 0, 1, 1.0, 1.0, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 0, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(mainPanel, buttonPanel, 0, 2, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 0, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		titlePanel.setLayout(new GridBagLayout());
		addComponent(titlePanel, titleLabel, 0, 0, 0.02, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 0, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		contentPanel.setLayout(new GridBagLayout());
		addComponent(contentPanel, enableLabel, 0, 0, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, enableJComboBox, 1, 0, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, portModeLabel, 2, 0, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, portModeJComboBox, 3, 0, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, vlanLabel, 0, 1, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, vlanTextField, 1, 1, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cirLabel, 2, 1, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cirTextField, 3, 1, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, sourceMacLabel, 0, 2, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sourceMacTextField, 1, 2, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, pirLabel, 2, 2, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, pirTextField, 3, 2, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, sinkMacLabel, 0, 3, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sinkMacTextField, 1, 3, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cmLabel, 2, 3, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cmJComboBox, 3, 3, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, baIpLabel, 0, 4, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, baIpTextField, 1, 4, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cbsLabel, 2, 4, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, cbsTextField, 3, 4, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, sourceIpLabel, 0, 5, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sourceIpTextField, 1, 5, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, pbsLabel, 2, 5, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, pbsTextField, 3, 5, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, sinkIpLabel, 0, 6, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sinkIpTextField, 1, 6, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, strategyLabel, 2, 6, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, strategyJComboBox, 3, 6, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		
		addComponent(contentPanel, ipDscpLabel, 0, 7, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, ipDscpTextField, 1, 7, 1.0, 0.020, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, assignLabel, 2, 7, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, assignJComboBox, 3, 7, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sourceTcpPortLab, 0, 8, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, sourceTcpPortText, 1, 8, 1.0, 0.020, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, endTcpPortLab, 2, 8, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 20, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, endTcpPortText, 3, 8, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, IPTOSLab, 0, 9, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, IPTOSText, 1, 9, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, portTypeLab, 2, 9, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 10, 5, 0),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		addComponent(contentPanel, portTypeCmb, 3, 9, 1.0, 0.02, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 0, 5, 20),
				GridBagConstraints.NORTHWEST, gridBagConstraints);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(fl);
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(mainPanel);
		
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
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_EZT_IP);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,8,baIpTextField);
			baIpTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_IPDSCP);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,11,ipDscpTextField);
			ipDscpTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,12,sourceIpTextField);
			sourceIpTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,12,sinkIpTextField);
			sinkIpTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,13,sourceMacTextField);
			sourceMacTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,13,sinkMacTextField);
			sinkMacTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PBS);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,14,pbsTextField);
			pbsTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CBS);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,14,cbsTextField);
			cbsTextField.addFocusListener(textfieldFocusListener);
			
//			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CIR_WUHAN);
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_CIR);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,15,cirTextField);
			cirTextField.addFocusListener(textfieldFocusListener);
			
//			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PIR_WUHAN);
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_PIR);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,15,pirTextField);
			pirTextField.addFocusListener(textfieldFocusListener);
			
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,16,vlanTextField);
			vlanTextField.addFocusListener(textfieldFocusListener);
			
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield, 21, sourceTcpPortText);
			sourceTcpPortText.addFocusListener(textfieldFocusListener);
			
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield, 21, endTcpPortText);
			endTcpPortText.addFocusListener(textfieldFocusListener);
			
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield, 8, IPTOSText);
			IPTOSText.addFocusListener(textfieldFocusListener);
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
			baIpTextField.addKeyListener(textFIledKeyListener);
			ipDscpTextField.addKeyListener(textFIledKeyListener);
			pbsTextField.addKeyListener(textFIledKeyListener);
			cbsTextField.addKeyListener(textFIledKeyListener);
			cirTextField.addKeyListener(textFIledKeyListener);
			pirTextField.addKeyListener(textFIledKeyListener);
			vlanTextField.addKeyListener(textFIledKeyListener);
			sourceTcpPortText.addKeyListener(textFIledKeyListener);
			endTcpPortText.addKeyListener(textFIledKeyListener);
			IPTOSText.addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public PtnButton getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(PtnButton confirmButton) {
		this.confirmButton = confirmButton;
	}

	public AddACDialog getAddAcDialog() {
		return addAcDialog;
	}

	public void setAddAcDialog(AddACDialog addAcDialog) {
		this.addAcDialog = addAcDialog;
	}

	public BufferController getController() {
		return controller;
	}

	public void setController(BufferController controller) {
		this.controller = controller;
	}

	public JComboBox getEnableJComboBox() {
		return enableJComboBox;
	}

	public void setEnableJComboBox(JComboBox enableJComboBox) {
		this.enableJComboBox = enableJComboBox;
	}

	public JComboBox getPortModeJComboBox() {
		return portModeJComboBox;
	}

	public void setPortModeJComboBox(JComboBox portModeJComboBox) {
		this.portModeJComboBox = portModeJComboBox;
	}

	public JComboBox getCmJComboBox() {
		return cmJComboBox;
	}

	public void setCmJComboBox(JComboBox cmJComboBox) {
		this.cmJComboBox = cmJComboBox;
	}

	public JComboBox getStrategyJComboBox() {
		return strategyJComboBox;
	}

	public void setStrategyJComboBox(JComboBox strategyJComboBox) {
		this.strategyJComboBox = strategyJComboBox;
	}

	public JComboBox getAssignJComboBox() {
		return assignJComboBox;
	}

	public void setAssignJComboBox(JComboBox assignJComboBox) {
		this.assignJComboBox = assignJComboBox;
	}

	public JTextField getVlanTextField() {
		return vlanTextField;
	}

	public void setVlanTextField(JTextField vlanTextField) {
		this.vlanTextField = vlanTextField;
	}

	public JTextField getCirTextField() {
		return cirTextField;
	}

	public void setCirTextField(JTextField cirTextField) {
		this.cirTextField = cirTextField;
	}

	public JTextField getSourceMacTextField() {
		return sourceMacTextField;
	}

	public void setSourceMacTextField(JTextField sourceMacTextField) {
		this.sourceMacTextField = sourceMacTextField;
	}

	public JTextField getSinkMacTextField() {
		return sinkMacTextField;
	}

	public void setSinkMacTextField(JTextField sinkMacTextField) {
		this.sinkMacTextField = sinkMacTextField;
	}

	public JTextField getPirTextField() {
		return pirTextField;
	}

	public void setPirTextField(JTextField pirTextField) {
		this.pirTextField = pirTextField;
	}

	public JTextField getBaIpTextField() {
		return baIpTextField;
	}

	public void setBaIpTextField(JTextField baIpTextField) {
		this.baIpTextField = baIpTextField;
	}

	public JTextField getCbsTextField() {
		return cbsTextField;
	}

	public void setCbsTextField(JTextField cbsTextField) {
		this.cbsTextField = cbsTextField;
	}

	public JTextField getSourceIpTextField() {
		return sourceIpTextField;
	}

	public void setSourceIpTextField(JTextField sourceIpTextField) {
		this.sourceIpTextField = sourceIpTextField;
	}

	public JTextField getSinkIpTextField() {
		return sinkIpTextField;
	}

	public void setSinkIpTextField(JTextField sinkIpTextField) {
		this.sinkIpTextField = sinkIpTextField;
	}

	public JTextField getPbsTextField() {
		return pbsTextField;
	}

	public void setPbsTextField(JTextField pbsTextField) {
		this.pbsTextField = pbsTextField;
	}

	public JTextField getIpDscpTextField() {
		return ipDscpTextField;
	}

	public void setIpDscpTextField(JTextField ipDscpTextField) {
		this.ipDscpTextField = ipDscpTextField;
	}

	public Acbuffer getacbuffer() {
		return acbuffer;
	}

	public void setacbuffer(Acbuffer acbuffer) {
		this.acbuffer = acbuffer;
	}

	public JTextField getSourceTcpPortText() {
		return sourceTcpPortText;
	}

	public void setSourceTcpPortText(JTextField sourceTcpPortText) {
		this.sourceTcpPortText = sourceTcpPortText;
	}

	public JTextField getEndTcpPortText() {
		return endTcpPortText;
	}

	public void setEndTcpPortText(JTextField endTcpPortText) {
		this.endTcpPortText = endTcpPortText;
	}

	public JTextField getIPTOSText() {
		return IPTOSText;
	}

	public void setIPTOSText(JTextField iPTOSText) {
		IPTOSText = iPTOSText;
	}

	public JComboBox getPortTypeCmb() {
		return portTypeCmb;
	}

	public void setPortTypeCmb(JComboBox portTypeCmb) {
		this.portTypeCmb = portTypeCmb;
	}
}
