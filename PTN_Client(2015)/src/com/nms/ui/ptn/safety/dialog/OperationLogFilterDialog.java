package com.nms.ui.ptn.safety.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnCalendarField;
import com.nms.ui.manager.control.PtnDateDocument;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysOperaType;
/**
 * 查询操作日志 过滤对话框
 * @author sy
 *
 */
public class OperationLogFilterDialog extends PtnDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PtnButton confirm;  //确认按钮
	private JButton cancel;   //取消按钮
	private JButton clear;    //清除按钮
	public JTextField userField;//用户名
	public JLabel siteField;//网元名称
	private JComboBox siteCombox;
	private PtnCalendarField startChooseTime;//选择 时间
	private PtnCalendarField overChooseTime;//选择 时间
	private JComboBox operationLogTypeField;
	private JLabel operationTypeLabel; //操作类型
	private JComboBox operationResultBox;
	private JLabel operationResultLabel;//结果
	private JLabel userType; 
	private JLabel startLabel;//起始时间
	private JLabel overLabel;//截止时间
	private JPanel buttonConfirCanel;
	private JPanel claerJpanel;
	private JPanel buttonPanel;
	private JCheckBox chbLikeSelect;//模糊查询复选框
	public void setUserField(JTextField userField) {
		this.userField = userField;
	}
	/**
	 * 实例化对象
	 */
	public OperationLogFilterDialog() {
		this.setModal(true);
		init();
	}
	//初始化
	public void init() {
		initComponents();
		this.initData();
		setLayout();
		addListener();
	}

	public void initData(){
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteList=siteService.select();
			DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) siteCombox.getModel();
			defaultComboBoxModel.addElement(ResourceUtil.srcStr(StringKeysOperaType.BTN_ALL));
			for (SiteInst site : siteList) {
				try {
					defaultComboBoxModel.addElement(new ControlKeyValue(site.getSite_Inst_Id()+"", site.getCellId(), site));					
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
			SiteInst siteinst=new SiteInst();
			siteinst.setSite_Inst_Id(0);
			siteinst.setCellId(ResourceUtil.srcStr(StringKeysLbl.LBL_SYSTEM));
			ControlKeyValue ck = new ControlKeyValue(0+"", siteinst.getCellId(), siteinst);
			defaultComboBoxModel.addElement(ck);
			siteinst.setSite_Inst_Id(-1);
			siteinst.setCellId(ResourceUtil.srcStr(StringKeysObj.OBJ_ALLSITE_IN_CONFIG));
			ControlKeyValue c = new ControlKeyValue(-1+"", siteinst.getCellId(), siteinst);
			defaultComboBoxModel.addElement(c);
			siteCombox.setModel(defaultComboBoxModel);
		}catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}
	
	public PtnButton getConfirm() {
		return confirm;
	}
	//时间处理
	private void addListener() {
		
		//取消按钮
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				OperationLogFilterDialog.this.dispose();
			}
		});
	}
	private void initComponents() {
		buttonConfirCanel=new JPanel();
		claerJpanel=new JPanel();
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		//选择日期
		startLabel = new JLabel(ResourceUtil.srcStr(StringKeysOperaType.BTN_START_TIME));
		overLabel = new JLabel(ResourceUtil.srcStr(StringKeysOperaType.BTN_OVER_TIME));
		overLabel=new JLabel("---");
		//网元名称
		siteField= new JLabel(ResourceUtil.srcStr(StringKeysObj.STRING_SITE_NAME));//网元名称
		siteCombox=new JComboBox();	
		//用户名LBL_USERNAM
		userType = new JLabel(ResourceUtil.srcStr(StringKeysBtn.TEXTUSERNAME));	
		userField=new javax.swing.JTextField("");
		this.chbLikeSelect=new JCheckBox();
		this.chbLikeSelect.setText(ResourceUtil.srcStr(StringKeysOperaType.BTN_LIKE_SELECT));
		this.operationTypeLabel=new JLabel(ResourceUtil.srcStr(StringKeysOperaType.BTN_OPERATIONTYPE));
		this.operationLogTypeField=new JComboBox();
		operationLogTypeField.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_ALL));
		for(int i=1; i<EOperationLogType.values().length;i++){
			operationLogTypeField.addItem(EOperationLogType.forms(i));
			
		}		
		this.operationResultLabel=new JLabel(ResourceUtil.srcStr(StringKeysOperaType.BTN_RESULT));
		this.operationResultBox=new JComboBox();
		operationResultBox.addItem(ResourceUtil.srcStr(StringKeysOperaType.BTN_ALL));
		operationResultBox.addItem(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS));
		operationResultBox.addItem(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE));		
		this.startChooseTime=new PtnCalendarField(new PtnDateDocument());
		this.overChooseTime=new PtnCalendarField(new PtnDateDocument());
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		buttonPanel=new JPanel();
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
		layout.rowHeights = new int[] { 10, 20, 20, 20, 20, 20, 20 ,20};
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
		layout.addLayoutComponent(userType, c);
		this.add(userType);
		c.gridx =1;
		c.gridwidth = 2;
		layout.addLayoutComponent(userField, c);
		this.add(userField);
		
		c.gridx=3;
		c.gridwidth = 1;
		layout.addLayoutComponent(this.chbLikeSelect, c);
		this.add(chbLikeSelect);
		
		// 第二行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(siteField, c);
		this.add(siteField);
		c.gridx =1;
		c.gridwidth = 2;
		layout.addLayoutComponent(this.siteCombox, c);
		this.add(siteCombox);
				
		
		//第三行
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.startLabel, c);
		this.add(startLabel);
		
		c.gridx = 1;
		c.gridy =3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.startChooseTime, c);
		this.add(startChooseTime);
		
		//3
		c.gridx = 2;
		c.gridy =3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.overLabel, c);
		this.add(overLabel);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.overChooseTime, c);
		this.add(overChooseTime);
		//4
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.operationTypeLabel, c);
		this.add(operationTypeLabel);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.operationLogTypeField, c);
		this.add(operationLogTypeField);
		//5
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.operationResultLabel, c);
		this.add(operationResultLabel);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 6;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(this.operationResultBox, c);
		this.add(operationResultBox);
		//7
		c.gridx = 3;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(buttonPanel , c);
		this.add(buttonPanel );
	}

	public JTextField getUserField() {
		return userField;
	}

	public JButton getCancel() {
		return cancel;
	}

	public JButton getClear() {
		return clear;
	}

	public JComboBox getSiteCombox() {
		return siteCombox;
	}
	
	public JComboBox getOperationLogTypeField() {
		return operationLogTypeField;
	}

	public JLabel getOperationTypeLabel() {
		return operationTypeLabel;
	}
	public JComboBox getOperationResultBox() {
		return operationResultBox;
	}

	public JLabel getOperationResultLabel() {
		return operationResultLabel;
	}

	public JLabel getUserType() {
		return userType;
	}

	public JLabel getStartLabel() {
		return startLabel;
	}

	public JLabel getOverLabel() {
		return overLabel;
	}

	public JPanel getButtonConfirCanel() {
		return buttonConfirCanel;
	}

	public JPanel getClaerJpanel() {
		return claerJpanel;
	}

	public PtnCalendarField getStartChooseTime() {
		return startChooseTime;
	}

	public PtnCalendarField getOverChooseTime() {
		return overChooseTime;
	}

	public JCheckBox getChbLikeSelect() {
		return chbLikeSelect;
	}

}
