package com.nms.ui.ptn.basicinfo.dialog.subnet.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.nms.db.bean.system.Field;
import com.nms.model.system.FieldService_MB;
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
/**
 * 子网管理对话框
 * @author Dzy
 *
 */
public class SubnetManagerPanel extends PtnDialog {
	
	private static final long serialVersionUID = 1486668727510698070L;
	private SubnetTablePanel subnetTablePanel ;
	private JLabel comboLabel;
	private JComboBox comboBox; //域下拉列表
	private JButton queryButton;//查询按钮
	private JButton cancelButton;
	public SubnetManagerPanel() {
		try {
			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_SUBNET_MANAGER));
			super.setModal(true);
			initComponents();
			setLayout();
			initData();
			this.subnetTablePanel.getController().refresh();
			setActionListention();
			UiUtil.showWindow(this, 470,440);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	public void initData() {
		initCombobox(this.comboBox);
		
	}
	public void initCombobox(JComboBox comboBox){
		
		FieldService_MB service = null;
		List<Field> fieldList = null ;
		Field f = new Field();
		
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
		try {
			service = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fieldList = service.selectRootField(ConstantUtil.user);
			if(ConstantUtil.user.getIsAll()==1){
				f.setFieldName("ALL");
				defaultComboBoxModel.addElement(new ControlKeyValue("ALL", "ALL", f));
			}
			
			if(fieldList!=null&&fieldList.size()>0){
				for (Field field : fieldList) {
					defaultComboBoxModel.addElement(new ControlKeyValue(field.getId() + "", field.getFieldName(), field));
				}
			}
			
			comboBox.setModel(defaultComboBoxModel);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	private void initComponents() {
		this.comboLabel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GROUP_BELONG));
		this.comboBox = new JComboBox();
		this.queryButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT));
		this.subnetTablePanel = new SubnetTablePanel(comboBox);
		this.cancelButton = new JButton (ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));;
	}




	private void setLayout(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 80, 230, 80 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 ,0.0 };
		gridBagLayout.rowHeights = new int[] { 80, 300, 70 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0 ,0.0 };
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		addComponent(this, comboLabel, 0, 0, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(50, 50, 20, 0), GridBagConstraints.CENTER, c);
		addComponent(this, comboBox,   1, 0, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(50, 25, 20, 100), GridBagConstraints.CENTER, c);
		addComponent(this, queryButton,1, 0, 0.0, 0.0, 1, 1,  GridBagConstraints.BOTH, new Insets(50, 230, 20, 30), GridBagConstraints.CENTER, c);
		addComponent(this, subnetTablePanel, 0,1, 0.0, 0.0,  0, 0, GridBagConstraints.BOTH, new Insets(0, 0, 80, 0), GridBagConstraints.CENTER, c);
		addComponent(this, cancelButton, 2, 2, 0.0,0.0, 1, 1,  GridBagConstraints.BOTH, new Insets(15, 0, 40, 15), GridBagConstraints.SOUTH, c);
		
	}

	private void addComponent(PtnDialog PtnDialog, JComponent component, int gridx, int gridy, double weightx, double weighty, 
			int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		PtnDialog.add(component,gridBagConstraints);
	}
	private void setActionListention() {
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2ActionPerformed(e);
				
			}

		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1ActionPerformed(e);
			}		
		});
	}
	protected void jButton1ActionPerformed(ActionEvent e) {
		this.dispose();
	}
	private void jButton2ActionPerformed(ActionEvent evt) {
		try {
			this.subnetTablePanel.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}		
	
}
