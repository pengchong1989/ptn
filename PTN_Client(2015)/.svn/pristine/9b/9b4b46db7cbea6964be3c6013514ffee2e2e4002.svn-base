package com.nms.ui.ptn.basicinfo.dialog.field;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.NetWork;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.NetService_MB;
import com.nms.model.system.SubnetService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.topology.NetworkElementPanel;
/**
 * 网元移动
 * @author dzy
 *
 */
public class MoveSiteDialog extends PtnDialog {
				
	/**
	 * 
	 */
	private static final long serialVersionUID = -3441383475655949740L;
	private JLabel fieldcomboLabel;
	private JLabel comboLabel;
	private JLabel subnetComboLabel;
	private JComboBox fieldcomboBox; //域下拉列表
	private JComboBox groupComboBox; //组下拉列表
	private JComboBox subnetComboBox; //子网下拉列表
	private JButton cancelButton;
	private PtnButton saveButton;
	private SiteInst siteInst;
	/**
	 * 构造方法
	 * @param siteInst
	 * 			网元
	 */
	public MoveSiteDialog(SiteInst siteInst) {
		try {
			this.siteInst = siteInst;
			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_MOVE_SITE_FIELD));
			super.setModal(true);
			initComponents();
			setLayout();
		    this.initFieldCombox(siteInst);			
			setActionListention();
			UiUtil.showWindow(this, 380, 240);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 初始化域下拉列表
	 * @param comboBox  下拉列表控件
	 */
	public void initFieldCombox(SiteInst siteInst){
		List<NetWork> netWorkList = null ;
		NetService_MB netService=null;
		List<Field> field=null;
		List<Field> subfield=null;
		SubnetService_MB service = null;
		FieldService_MB fieldService=null;		
		try {
			fieldcomboBox.removeAllItems();
			groupComboBox.removeAllItems();
			subnetComboBox.removeAllItems();			
			DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) fieldcomboBox.getModel();
			DefaultComboBoxModel groupComboBoxModel = (DefaultComboBoxModel) groupComboBox.getModel();
			DefaultComboBoxModel subnetComboBoxModel = (DefaultComboBoxModel) subnetComboBox.getModel();
			service = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
			fieldService=(FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			netService = (NetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NETWORKSERVICE);
			netWorkList = netService.select();
			for (NetWork network : netWorkList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(network.getNetWorkId() + "", network.getNetWorkName(), network));
			}
			fieldcomboBox.setModel(defaultComboBoxModel);
			Field ff=fieldService.selectByFieldId(siteInst.getFieldID()).get(0);
			if(ff.getObjectType().equals("field")){
				super.getComboBoxDataUtil().comboBoxSelect(fieldcomboBox, ff.getNetWorkId()+"");
				field=fieldService.queryByNetWorkid(ff.getNetWorkId());
				for (Field group : field) {
					groupComboBoxModel.addElement(new ControlKeyValue(group.getId() + "", group.getFieldName(), group));
				}
				groupComboBox.setModel(groupComboBoxModel);
				super.getComboBoxDataUtil().comboBoxSelect(groupComboBox,siteInst.getFieldID()+"");		
				Field fie=new Field();
				fie.setId(siteInst.getFieldID());
				subfield=service.querySiteByCondition(fie);				   
				for (Field fi : subfield) {
					 subnetComboBoxModel.addElement(new ControlKeyValue(fi.getId() + "", fi.getFieldName(), fi));
				}
				subnetComboBoxModel.addElement(null);					   				  
				subnetComboBox.setModel(subnetComboBoxModel);
				subnetComboBox.setSelectedItem(null);
			}else if(ff.getObjectType().equals("subnet")){
				Field f=fieldService.selectByFieldId(ff.getParentId()).get(0);
				super.getComboBoxDataUtil().comboBoxSelect(fieldcomboBox, f.getNetWorkId()+"");
				field=fieldService.queryByNetWorkid(f.getNetWorkId());
				for (Field group : field) {
				  groupComboBoxModel.addElement(new ControlKeyValue(group.getId() + "", group.getFieldName(), group));
				}
				groupComboBox.setModel(groupComboBoxModel);
				super.getComboBoxDataUtil().comboBoxSelect(groupComboBox,ff.getParentId()+"");		
				Field fie=new Field();
				fie.setId(ff.getParentId());
				subfield=service.querySiteByCondition(fie);
				for (Field fi : subfield) {
					 subnetComboBoxModel.addElement(new ControlKeyValue(fi.getId() + "", fi.getFieldName(), fi));
				}
			    subnetComboBoxModel.addElement(null);			  
			    subnetComboBox.setModel(subnetComboBoxModel);
			    super.getComboBoxDataUtil().comboBoxSelect(subnetComboBox,ff.getId()+"");
			}
					
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(netService);
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(service);
		}
	}
	
	
	
	/**
	 * 初始化组件
	 */
	private void initComponents() {
		this.fieldcomboLabel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FIELD_BELONG ));
		this.comboLabel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GROUP_BELONG));
		this.fieldcomboBox=new JComboBox();
		this.groupComboBox = new JComboBox();
		this.subnetComboBox = new JComboBox();
		this.cancelButton = new JButton (ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));;
		this.saveButton = new PtnButton (ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.subnetComboLabel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SUBNET));
	}
	/**
	 * 布局
	 */
	private void setLayout(){
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 25, 40, 40, 40, 15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.insets = new Insets(15, 5, 5, 5);
		componentLayout.setConstraints(this.fieldcomboLabel, c);
		this.add(this.fieldcomboLabel);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.fieldcomboBox, c);
		this.add(this.fieldcomboBox);
			
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.insets = new Insets(15, 5, 5, 5);
		componentLayout.setConstraints(this.comboLabel, c);
		this.add(this.comboLabel);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.groupComboBox, c);
		this.add(this.groupComboBox);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.subnetComboLabel, c);
		this.add(this.subnetComboLabel);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.subnetComboBox, c);
		this.add(this.subnetComboBox);
		
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.saveButton, c);
		this.add(this.saveButton);
		c.gridx = 2;
		componentLayout.setConstraints(this.cancelButton, c);
		this.add(this.cancelButton);

	}

	/**
	 * 子网下拉列表
	 */
	private void initSubnetCombo(JComboBox groupComboBox)  {
		DefaultComboBoxModel subnetComboBoxModel = null;
		SubnetService_MB service = null;
		List<Field> subnetList = new ArrayList<Field>();
		try {
			subnetComboBox.removeAllItems();
			subnetComboBoxModel = new DefaultComboBoxModel();
			service = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
	        if((ControlKeyValue) groupComboBox.getSelectedItem()!= null){
				Field fie=new Field();
				fie.setId(Integer.parseInt(((ControlKeyValue) (groupComboBox.getSelectedItem())).getId()));
				subnetList =service.querySiteByCondition(fie);
				for (Field subnet : subnetList) {
					subnetComboBoxModel.addElement(new ControlKeyValue(subnet.getId() + "", subnet.getFieldName(), subnet));
				}
	        }
			subnetComboBoxModel.addElement(null);
			subnetComboBox.setModel(subnetComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 监听事件
	 */
	private void setActionListention() {
		//与下拉列表监听
		fieldcomboBox.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent e) {						
				 jButtonActionPerformed(e);
				
			}
		});
		groupComboBox.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent e) {					
				 jButton1ActionPerformed(e);
				
			}
				
		});
	
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jButton2ActionPerformed(e);
				
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton3ActionPerformed(e);
			}		
		});
		
	}
	/**
	 * 与下拉列表事件
	 * @param e2
	 */
	private void jButton1ActionPerformed(ItemEvent e2) {
		try {
			initSubnetCombo(this.groupComboBox);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}	
	
	/**
	 * 与下拉列表事件
	 * @param e2
	 */
	private void jButtonActionPerformed(ItemEvent e2) {
		try {
			initGroupCombox(this.fieldcomboBox);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}	
	
	
	/**
	 * 初始化域下拉列表
	 * @param comboBox  下拉列表控件
	 */
	public void initGroupCombox(JComboBox comboBox){
		FieldService_MB fieldService=null;
		SubnetService_MB service = null;
		List<Field> fieldList = null ;
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) groupComboBox.getModel();
		try {
			groupComboBox.removeAllItems();
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			service = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
			fieldList = fieldService.queryByNetWorkid(Integer.parseInt(((ControlKeyValue) (comboBox.getSelectedItem())).getId()));
			for (Field field : fieldList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(field.getId() + "", field.getFieldName(), field));
			}
			groupComboBox.setModel(defaultComboBoxModel);			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(fieldService);
		}
	}
	
	/**
	 * 保存
	 * @param e
	 */
	private void jButton2ActionPerformed(ActionEvent e) {
		SiteService_MB service = null;
		NetService_MB netService = null;
		FieldService_MB fieldService=null;
		String result = null;
		int fieldComboId = siteInst.getFieldID();
		Field newField=new Field();
		Field oldField=new Field();
		try {
			service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			netService = (NetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NETWORKSERVICE);
			fieldService= (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);			
			if((ControlKeyValue) this.groupComboBox.getSelectedItem()== null){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_GROUP_BELONG));
				return;
			}
			Field group=(Field) ((ControlKeyValue) this.groupComboBox.getSelectedItem()).getObject();
			//旧的	
			Field field=fieldService.selectByFieldId(siteInst.getFieldID()).get(0);
			if(field.getObjectType().equals("subnet")){
				oldField.setFieldName(field.getFieldName());
				if(field.getParentId()!=0){
					Field oldf=fieldService.selectByFieldId(field.getParentId()).get(0);
					oldField.setParentName(oldf.getFieldName());
					NetWork oldnet = new NetWork();
					oldnet.setNetWorkId(oldf.getNetWorkId());
					NetWork oldnetwork=netService.select(oldnet).get(0);
					oldField.setNetWorkName(oldnetwork.getNetWorkName());	
				}
			}else if(field.getObjectType().equals("field")){
				oldField.setParentName(field.getFieldName());
				NetWork oldnet = new NetWork();
				oldnet.setNetWorkId(field.getNetWorkId());
				NetWork oldnetwork=netService.select(oldnet).get(0);
				oldField.setNetWorkName(oldnetwork.getNetWorkName());				
			}
			if((ControlKeyValue) this.subnetComboBox.getSelectedItem()!= null){				
				Field fieldCombo = (Field) ((ControlKeyValue) this.subnetComboBox.getSelectedItem()).getObject();
				siteInst.setFieldID(fieldCombo.getId());
				//新的
				newField.setFieldName(fieldCombo.getFieldName());
				newField.setParentName(group.getFieldName());
				NetWork net = new NetWork();
				net.setNetWorkId(group.getNetWorkId());
				NetWork network=netService.select(net).get(0);
				newField.setNetWorkName(network.getNetWorkName());									
				result = service.saveOrUpdate(siteInst);						    							
			}else{
				Field fieldCombo = (Field) ((ControlKeyValue) this.groupComboBox.getSelectedItem()).getObject();
				siteInst.setFieldID(fieldCombo.getId());
				//新的
				newField.setParentName(group.getFieldName());
				NetWork net = new NetWork();
				net.setNetWorkId(group.getNetWorkId());
				NetWork network=netService.select(net).get(0);
				newField.setNetWorkName(network.getNetWorkName());									
				result = service.saveOrUpdate(siteInst);			
			}
				
	        	this.insertOpeLog(EOperationLogType.MOVESITE.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), oldField, newField);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(netService);
			UiUtil.closeService_MB(fieldService);
		}
		this.dispose();
		if(null!=result&&!"".equals(result)){
			siteInst.setFieldID(fieldComboId);
			DialogBoxUtil.succeedDialog(this, result);
		}else{
			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
		}
		try {
			NetworkElementPanel.getNetworkElementPanel().showTopo(true);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		}
	}
	
	
	private void insertOpeLog(int operationType, String result, Field oldfield, Field newfield){
		AddOperateLog.insertOperLog(saveButton, operationType, result, oldfield, newfield, this.siteInst.getSite_Inst_Id(), this.siteInst.getCellId(), "MoveSite");		
	}
	
	/**
	 * 取消
	 * @param e
	 */
	protected void jButton3ActionPerformed(ActionEvent e) {
		this.dispose();
	}

	
}
