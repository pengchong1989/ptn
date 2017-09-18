package com.nms.ui.ptn.ne.statusData.view.ethOamPing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.oamStatus.PingOrderControllerInfo;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;

public class PingOrderControllerDialog extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3433164751859573427L;
	private JLabel id;
	private PtnTextField idJTextField;
	private JLabel farMepId;
	private PtnTextField farMepIdJTextField;
	private JLabel farMac;
	private PtnTextField farMacJTextField;
	private JLabel ping;
	private PtnTextField pingJTextField;
	private PtnButton confirmButton;//保存
	private JButton cancleButton;//取消
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JScrollPane scrollPanel;
	private JPanel titlePanel;
	private JLabel lblTitle;
	private int types;
	private JLabel lblMessage;
	
	public PingOrderControllerDialog(int type) {
		super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CONTROLLER_ORDER));
		types = type;
		initComponent();
		setLayout();
		this.addListener();
	}
	private void initComponent() {
		confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);//保存
		cancleButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));//取消
		contentPanel = new JPanel();
		buttonPanel = new JPanel();
		scrollPanel = new JScrollPane();
		titlePanel = new JPanel();
		lblTitle = new JLabel();
		lblMessage = new JLabel();
		id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NMUBER_ID));
		try {
			idJTextField = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.confirmButton, this);
			idJTextField.setText("1");
			idJTextField.setMaxValue(6);
			idJTextField.setMinValue(1);
			idJTextField.setCheckingMaxValue(true);
			idJTextField.setCheckingMinValue(true);
			farMepId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FAR_MEP));
			farMepIdJTextField = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.confirmButton, this);
			farMepIdJTextField.setText("1");
			farMepIdJTextField.setMaxValue(8191);
			farMepIdJTextField.setMinValue(0);
			farMepIdJTextField.setCheckingMaxValue(true);
			farMepIdJTextField.setCheckingMinValue(true);
			farMac = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FAR_MAC));
			farMacJTextField = new PtnTextField(false, PtnTextField.TYPE_MAC, PtnTextField.MAC_MAXLENGTH, this.lblMessage, this.confirmButton, this);
			farMacJTextField.setText("00-00-00-00-00-01");
			pingJTextField = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.confirmButton, this);
			pingJTextField.setCheckingMaxValue(true);
			pingJTextField.setCheckingMinValue(true);
			pingJTextField.setMaxValue(255);
			pingJTextField.setMinValue(0);
			if(types == 0){
				contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PING)));
				ping = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PING_FRAME));
				pingJTextField.setText("4");
			}else{
				contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_TRACE)));
				ping = new JLabel("TTL");
				pingJTextField.setText("255");
			}
			
			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(contentPanel);
			scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass() );
		}
		
	}
	/**
	 * 控件布局
	 */
	private void setLayout() {
		
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout.rowHeights = new int[] { 20 };
		layout.rowWeights = new double[] { 0 };
		layout.columnWidths = new int[] { 60, ConstantUtil.INT_WIDTH_THREE - 60 };
		layout.columnWeights = new double[] { 0, 1.0 };
		titlePanel.setLayout(layout);
		addComponent(titlePanel, lblTitle, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 20, 5, 5), GridBagConstraints.CENTER, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 60, 300, 60 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_THREE };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
		addComponentJDialog(this, titlePanel, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		setRorateButton();
		// button面板布局
		setButtonLayout();
	}
	
	/**
	 * 保存，取消按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		buttonLayout.columnWidths = new int[] { 60, 60, 60, 6 };
		buttonLayout.columnWeights = new double[] { 1.0, 0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 60 };
		buttonLayout.rowWeights = new double[] { 1 };
		buttonPanel.setLayout(buttonLayout);
		addComponent(buttonPanel, lblMessage, 0, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, cancleButton, 1, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, confirmButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
	}

	
	/**
	 * 倒换按钮面板布局
	 */
	private void setRorateButton(){
		GridBagLayout contentLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(contentLayout);
		Insets insert = new Insets(5, 50, 5, 5);
		addComponent(contentPanel, id, 0, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, idJTextField, 1, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, farMepId, 0, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, farMepIdJTextField, 1, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, farMac, 0, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, farMacJTextField, 1, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, ping, 0, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, pingJTextField, 1, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
	}
	
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}
	
	public void addComponentJDialog(JDialog panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}

	private void addListener() {
		// TODO Auto-generated method stub
		cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cancleButtonActionPerformed();
			}
			
		});
		
		confirmButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				confirmButtonActionPerformed();
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	
	private void cancleButtonActionPerformed() {
		this.dispose();
	}
	

	private void confirmButtonActionPerformed() {
		// TODO Auto-generated method stub
		PingOrderControllerInfo pingOrderControllerInfo = new PingOrderControllerInfo();
		try {
			pingOrderControllerInfo.setSiteId(ConstantUtil.siteId);
			pingOrderControllerInfo.setType(types);
			pingOrderControllerInfo.setFarMac(farMacJTextField.getText());
			pingOrderControllerInfo.setId(Integer.parseInt(idJTextField.getText()));
			pingOrderControllerInfo.setMepId(Integer.parseInt(farMepIdJTextField.getText()));
			pingOrderControllerInfo.setPing(Integer.parseInt(pingJTextField.getText()));
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_OAMPING);
			String result = dispatchUtil.excuteInsert(pingOrderControllerInfo);
			DialogBoxUtil.succeedDialog(this, result);
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
