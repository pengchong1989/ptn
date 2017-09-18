package com.nms.ui.ptn.safety.dialog;

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
import javax.swing.JTextField;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnCalendarField;
import com.nms.ui.manager.control.PtnDateDocument;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysOperaType;
/**
 * 查询操作日志 过滤对话框
 * @author deng.xinhong
 *
 */
public class LoginLogFilterDialog extends PtnDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton confirm;  //确认按钮
	private JButton cancel;   //取消按钮
	public JTextField userField;//用户名
	
	private JLabel lblObjectType; 
	private JCheckBox chbLiekSelect;//模糊查询 复选框
	private JPanel buttonPanel;
	private JLabel lblLoginTime; 
	private JLabel overLabel;
	private JLabel overLabel2;
	private JLabel lblLeaveTime; 
	private JLabel lblLoginIP; 
	private PtnCalendarField loginBeginTime;//登录开始 时间
	private PtnCalendarField loginEndTime;//登录结束时间
	private PtnCalendarField leaveBeginTime;//离开开始 时间
	private PtnCalendarField leaveEndTime;//离开结束时间
	private JTextField loginIp;//登录IP
	private JCheckBox chbLiekSelect2;//登录IP模糊查询 复选框
	private JLabel operatingTypeLabel;//操作类型过滤:登录成功/登录失败/退出成功/退出失败
	private JComboBox operatingCmb;
	
	public void setUserField(JTextField userField) {
		this.userField = userField;
	}

	public LoginLogFilterDialog() {
		this.setModal(true);
		init();
	}
	
	public void init() {
		initComponents();
		setLayout();
		addListener();
	}

	private void addListener() {
		
		//取消按钮
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginLogFilterDialog.this.dispose();
			}
		});
	}

	private void initComponents() {
			this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));

		//用户名LBL_USERNAM
		lblObjectType = new JLabel(ResourceUtil.srcStr(StringKeysBtn.TEXTUSERNAME));	
		userField=new javax.swing.JTextField("");
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.chbLiekSelect=new JCheckBox();
		this.chbLiekSelect2=new JCheckBox();
		this.chbLiekSelect.setText(ResourceUtil.srcStr(StringKeysOperaType.BTN_LIKE_SELECT));
		this.chbLiekSelect2.setText(ResourceUtil.srcStr(StringKeysOperaType.BTN_LIKE_SELECT));
		this.buttonPanel=new JPanel();
		lblLoginTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOGIN_SCOPE));
		overLabel=new JLabel("---");
		overLabel2=new JLabel("---");
		lblLeaveTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LEAVE_SCOPE));
		lblLoginIP = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOGINIP_SCOPE));
		this.loginBeginTime=new PtnCalendarField(new PtnDateDocument());
		this.loginEndTime=new PtnCalendarField(new PtnDateDocument());
		this.leaveBeginTime=new PtnCalendarField(new PtnDateDocument());
		this.leaveEndTime=new PtnCalendarField(new PtnDateDocument());
		this.loginIp=new JTextField();
		this.operatingTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_TYPE));
		this.operatingCmb = new JComboBox();
		this.initCmb();
	}
	
	private void initCmb() {
		this.operatingCmb.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_LOGIN_SUCCESS)
				+ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS));//登录成功
		this.operatingCmb.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_LOGIN_SUCCESS)
				+ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE));//登陆失败
		this.operatingCmb.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_EXIT_SYSTEM)
				+ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS));//退出成功
		this.operatingCmb.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_EXIT_SYSTEM)
				+ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE));//退出失败
	}

	//按钮布局
	private void setButtonLayout() {
		GridBagLayout layout = new GridBagLayout();
	layout.columnWidths = new int[] {  245,70,100 };
		layout.columnWeights = new double[] { 0.2, 0, 0};
		layout.rowHeights = new int[] { 10};
		layout.rowWeights = new double[] {0.1};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		//第一行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(confirm, c);
		this.buttonPanel.add(confirm);
		c.gridx =2;
		layout.addLayoutComponent(this.cancel, c);
		this.buttonPanel.add(cancel);
	}
	//页面布局
	private void setLayout() {
		setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 60, 170, 15, 170 };
		layout.columnWeights = new double[] { 0, 0.1, 0, 0.1 };
		layout.rowHeights = new int[] { 10, 20, 20, 20, 20, 20, 20, 20 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		// 第一行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblObjectType, c);
		this.add(lblObjectType);
		c.gridx =1;
		c.gridwidth = 2;
		layout.addLayoutComponent(userField, c);
		this.add(userField);
		
		c.gridx=3;
		c.gridwidth = 1;
		layout.addLayoutComponent(this.chbLiekSelect, c);
		this.add(chbLiekSelect);
		
		
		//第二行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.lblLoginTime, c);
		this.add(lblLoginTime);
		
		c.gridx = 1;
		c.gridy =2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.loginBeginTime, c);
		this.add(loginBeginTime);
		
		//3
		c.gridx = 2;
		c.gridy =2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.overLabel, c);
		this.add(overLabel);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.loginEndTime, c);
		this.add(loginEndTime);
		
		//第三行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.lblLeaveTime, c);
		this.add(lblLeaveTime);
		
		c.gridx = 1;
		c.gridy =3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.leaveBeginTime, c);
		this.add(leaveBeginTime);
		
		//3
		c.gridx = 2;
		c.gridy =3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.overLabel2, c);
		this.add(overLabel2);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.leaveEndTime, c);
		this.add(leaveEndTime);
		
		// 第四行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblLoginIP, c);
		this.add(lblLoginIP);
		c.gridx =1;
		c.gridwidth = 2;
		layout.addLayoutComponent(loginIp, c);
		this.add(loginIp);
		
		c.gridx=3;
		c.gridwidth = 1;
		layout.addLayoutComponent(this.chbLiekSelect2, c);
		this.add(chbLiekSelect2);
		
		//第五行 操作类型
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(this.operatingTypeLabel, c);
		this.add(this.operatingTypeLabel);
		c.gridx =1;
		c.gridwidth = 3;
		layout.addLayoutComponent(this.operatingCmb, c);
		this.add(this.operatingCmb);
		
		//第六行，按钮
		c.gridx = 3;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(buttonPanel , c);
		this.add(buttonPanel );
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JCheckBox getChbLiekSelect() {
		return chbLiekSelect;
	}
	public PtnCalendarField getLoginBeginTime()
	{
		return loginBeginTime;
	}

	public void setLoginBeginTime(PtnCalendarField loginBeginTime)
	{
		this.loginBeginTime = loginBeginTime;
	}

	public PtnCalendarField getLoginEndTime()
	{
		return loginEndTime;
	}

	public void setLoginEndTime(PtnCalendarField loginEndTime)
	{
		this.loginEndTime = loginEndTime;
	}

	public PtnCalendarField getLeaveBeginTime()
	{
		return leaveBeginTime;
	}

	public void setLeaveBeginTime(PtnCalendarField leaveBeginTime)
	{
		this.leaveBeginTime = leaveBeginTime;
	}

	public PtnCalendarField getLeaveEndTime()
	{
		return leaveEndTime;
	}

	public void setLeaveEndTime(PtnCalendarField leaveEndTime)
	{
		this.leaveEndTime = leaveEndTime;
	}

	public JTextField getLoginIp()
	{
		return loginIp;
	}

	public void setLoginIp(JTextField loginIp)
	{
		this.loginIp = loginIp;
	}

	public JCheckBox getChbLiekSelect2()
	{
		return chbLiekSelect2;
	}

	public void setChbLiekSelect2(JCheckBox chbLiekSelect2)
	{
		this.chbLiekSelect2 = chbLiekSelect2;
	}

	public JComboBox getOperatingCmb() {
		return operatingCmb;
	}

	public void setOperatingCmb(JComboBox operatingCmb) {
		this.operatingCmb = operatingCmb;
	}
	
}
