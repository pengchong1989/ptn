package com.nms.ui.ptn.portlag.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.system.code.Code;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;

public class ExportQueueDialog extends PtnDialog{
	private JLabel modelLabel0;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox0;
	private JLabel popedomLabel0;//权重:1-127(十进制显示)
	private JTextField popedomComboBox0;
	private JLabel modelLabel1;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox1;
	private JLabel popedomLabel1;//权重:1-127(十进制显示)
	private JTextField popedomComboBox1;
	private JLabel modelLabel2;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox2;
	private JLabel popedomLabel2;//权重:1-127(十进制显示)
	private JTextField popedomComboBox2;
	private JLabel modelLabel3;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox3;
	private JLabel popedomLabel3;//权重:1-127(十进制显示)
	private JTextField popedomComboBox3;
	private JLabel modelLabel4;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox4;
	private JLabel popedomLabel4;//权重:1-127(十进制显示)
	private JTextField popedomComboBox4;
	private JLabel modelLabel5;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox5;
	private JLabel popedomLabel5;//权重:1-127(十进制显示)
	private JTextField popedomComboBox5;
	private JLabel modelLabel6;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox6;
	private JLabel popedomLabel6;//权重:1-127(十进制显示)
	private JTextField popedomComboBox6;
	private JLabel modelLabel7;//模式:0/1=SP/WFQ
	private JComboBox modelComboBox7;
	private JLabel popedomLabel7;//权重:1-127(十进制显示)
	private JTextField popedomComboBox7;
	
	private JButton confirm;
	private JButton cancel;
	private JPanel buttonPanel;
	
	public ExportQueueDialog(String actionCommand) {
		this.setModal(true);
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_PORT_AGGREGATION_CONFIGURATION));
		// 初始化控件
		initComponent();
		// 界面布局
		setQLDialogLayout();
	}
	private void initComponent() {
		SiteService_MB siteService = null;
		try {
			modelLabel0 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox0 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox0, "VCTRAFFICPOLICING");
			popedomLabel0 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox0 = new JTextField(); 
			modelLabel1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox1 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox1, "VCTRAFFICPOLICING");
			popedomLabel1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox1 = new JTextField(); 
			modelLabel2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox2 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox2, "VCTRAFFICPOLICING");
			popedomLabel2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox2 = new JTextField(); 
			modelLabel3 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox3 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox3, "VCTRAFFICPOLICING");
			popedomLabel3 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox3 = new JTextField(); 
			modelLabel4 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox4 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox4, "VCTRAFFICPOLICING");
			popedomLabel4 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox4 = new JTextField(); 
			modelLabel5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox5 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox5, "VCTRAFFICPOLICING");
			popedomLabel5 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox5 = new JTextField(); 
			modelLabel6 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox6 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox6, "VCTRAFFICPOLICING");
			popedomLabel6 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox6 = new JTextField();
			modelLabel7 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.VCTRAFFICPOLICING));
			modelComboBox7 = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(modelComboBox7, "VCTRAFFICPOLICING");
			popedomLabel7 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.PORT_PRIORITY));
			popedomComboBox7 = new JTextField(); 
			confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIG));
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteService.getSiteType(ConstantUtil.siteId).contains("703")){
				popedomLabel4.setEnabled(false);
				popedomComboBox4.setEditable(false);
				popedomLabel5.setEnabled(false);
				popedomComboBox5.setEditable(false);
				popedomLabel6.setEnabled(false);
				popedomComboBox6.setEditable(false);
				popedomLabel7.setEnabled(false);
				popedomComboBox7.setEditable(false);
			}
			buttonPanel = new JPanel();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		
		
	}
	private void setQLDialogLayout() {
		setButtonLayout();

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 30, 100, 30, 100 };
		layout.columnWeights = new double[] { 0.2, 0.3, 0.2, 0.2 };
		layout.rowHeights = new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		layout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 60, 5, 5);
		layout.setConstraints(modelLabel0, c);
		this.add(modelLabel0);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 5, 5, 80);
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(modelComboBox0, c);
		this.add(modelComboBox0);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 5, 5, 5);
		layout.setConstraints(popedomLabel0, c);
		this.add(popedomLabel0);
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20, 5, 5, 60);
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(popedomComboBox0, c);
		this.add(popedomComboBox0);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel1, c);
		this.add(modelLabel1);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox1, c);
		this.add(modelComboBox1);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel1, c);
		this.add(popedomLabel1);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox1, c);
		this.add(popedomComboBox1);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel2, c);
		this.add(modelLabel2);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox2, c);
		this.add(modelComboBox2);
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel2, c);
		this.add(popedomLabel2);
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox2, c);
		this.add(popedomComboBox2);
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel3, c);
		this.add(modelLabel3);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox3, c);
		this.add(modelComboBox3);
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel3, c);
		this.add(popedomLabel3);
		c.gridx = 3;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox3, c);
		this.add(popedomComboBox3);
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel4, c);
		this.add(modelLabel4);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox4, c);
		this.add(modelComboBox4);
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel4, c);
		this.add(popedomLabel4);
		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox4, c);
		this.add(popedomComboBox4);
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel5, c);
		this.add(modelLabel5);
		c.gridx = 1;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox5, c);
		this.add(modelComboBox5);
		c.gridx = 2;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel5, c);
		this.add(popedomLabel5);
		c.gridx = 3;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox5, c);
		this.add(popedomComboBox5);
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel6, c);
		this.add(modelLabel6);
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox6, c);
		this.add(modelComboBox6);
		c.gridx = 2;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel6, c);
		this.add(popedomLabel6);
		c.gridx = 3;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox6, c);
		this.add(popedomComboBox6);
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 60, 5, 5);
		layout.setConstraints(modelLabel7, c);
		this.add(modelLabel7);
		c.gridx = 1;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 80);
		layout.setConstraints(modelComboBox7, c);
		this.add(modelComboBox7);
		c.gridx = 2;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(popedomLabel7, c);
		this.add(popedomLabel7);
		c.gridx = 3;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 60);
		layout.setConstraints(popedomComboBox7, c);
		this.add(popedomComboBox7);
		c.gridx = 3;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(15, 5, 15, 40);
		layout.setConstraints(buttonPanel, c);
		this.add(buttonPanel);

	}
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 18, 18 };
		buttonLayout.columnWeights = new double[] { 0.0, 0.0 };
		buttonLayout.rowHeights = new int[] { 30 };
		buttonLayout.rowWeights = new double[] { 0.0 };
		buttonPanel.setLayout(buttonLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		buttonLayout.setConstraints(confirm, c);
		buttonPanel.add(confirm);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		buttonLayout.setConstraints(cancel, c);
		buttonPanel.add(cancel);
	}
	/**
	 * 初始化界面的值
	 * 
	 * @param values
	 */
	public void init(String values) {
		try {
			if (values != null && !"".equals(values)) {
				String[] qlValues = values.split(",");
				int i = 0;
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox0, qlValues[i].toString().split("-")[0]);
				popedomComboBox0.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox1, qlValues[i].toString().split("-")[0]);
				popedomComboBox1.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox2, qlValues[i].toString().split("-")[0]);
				popedomComboBox2.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox3, qlValues[i].toString().split("-")[0]);
				popedomComboBox3.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox4, qlValues[i].toString().split("-")[0]);
				popedomComboBox4.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox5, qlValues[i].toString().split("-")[0]);
				popedomComboBox5.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox6, qlValues[i].toString().split("-")[0]);
				popedomComboBox6.setText(qlValues[i++].toString().split("-")[1] + "");
				super.getComboBoxDataUtil().comboBoxSelectByValue(modelComboBox7, qlValues[i].toString().split("-")[0]);
				popedomComboBox7.setText(qlValues[i].toString().split("-")[1] + "");

			} 

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public String get(){

		StringBuffer sb=null;
		try {
			sb=new StringBuffer();
			ControlKeyValue model0 = (ControlKeyValue) modelComboBox0.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model0.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox0.getText()+",");
			ControlKeyValue model1 = (ControlKeyValue) modelComboBox1.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model1.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox1.getText()+",");
			ControlKeyValue model2 = (ControlKeyValue) modelComboBox2.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model2.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox2.getText()+",");
			ControlKeyValue model3 = (ControlKeyValue) modelComboBox3.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model3.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox3.getText()+",");
			ControlKeyValue model4 = (ControlKeyValue) modelComboBox4.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model4.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox4.getText()+",");
			ControlKeyValue model5 = (ControlKeyValue) modelComboBox5.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model5.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox5.getText()+",");
			ControlKeyValue model6 = (ControlKeyValue) modelComboBox6.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model6.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox6.getText()+",");
			ControlKeyValue model7 = (ControlKeyValue) modelComboBox7.getSelectedItem();
			sb.append(Integer.parseInt(((Code) model7.getObject()).getCodeValue())).append("-");
			sb.append(popedomComboBox7.getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
		return sb.toString();
	
	}
	
	public JButton getConfirm() {
		return confirm;
	}
	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}
	public JButton getCancel() {
		return cancel;
	}
	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
}
