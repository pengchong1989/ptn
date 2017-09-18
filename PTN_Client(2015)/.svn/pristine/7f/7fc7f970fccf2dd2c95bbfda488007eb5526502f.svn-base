package com.nms.ui.ptn.ne.macManagement.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.MacLearningInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.MacLearningService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

public class AddMacLearningDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666883742001674590L;
	
	private JLabel portLab;
	private JComboBox portCom;//端口下拉列表
	private JLabel modelLab;
	private JComboBox modelCom;//mac学习限制模式
//	private JLabel vlanIdLab;
//	private JTextField vlanIdTxt;//vlan值
	private JLabel countLab;
	private JTextField countTxt;//mac地址学习限制数目
	private JLabel messageLab;
	private JPanel topPanel;
	private PtnButton saveBtn;
	private JButton cancelBtn;
	private JPanel btnPanel;
	
	private MacLearningLimitPanel view;
	
	private MacLearningInfo macInfo;
	
	public AddMacLearningDialog(MacLearningInfo info, MacLearningLimitPanel macPanel) {
		this.initCompoment();
		this.setLayout();
		this.setListener();
		this.initComboBoxAndTxt();
		if(info == null){
			//新建
			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_ADD_MAC_LEARN));
			macInfo = new MacLearningInfo();
			this.initPortValue();
		}else{
			//修改
			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_MAC_LEARN));
			macInfo = info;
			this.setValue();
		}
		this.view = macPanel;
		UiUtil.showWindow(this, 440, 300);
	}

	/**
	 * 初始化组件
	 */
	private void initCompoment() {
		this.portLab = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		this.modelLab = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_LEARN_MODEL));
//		this.vlanIdLab = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_CODE));
		this.countLab = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_COUNT));
		this.portCom = new JComboBox();
		this.modelCom = new JComboBox();
//		this.vlanIdTxt = new JTextField();
		this.countTxt = new JTextField();
		this.saveBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.topPanel = new JPanel();
		this.btnPanel = new JPanel();
		this.messageLab = new JLabel();
	}

	/**
	 * 界面布局
	 */
	private void setLayout() {
		this.setTopLayout();
		this.setBtnLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 300};
		componentLayout.columnWeights = new double[] { 0, 0.2, 0 };
		componentLayout.rowHeights = new int[] { 200, 50};
		componentLayout.rowWeights = new double[] { 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// 第一行
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.topPanel, c);
		this.add(this.topPanel);
		// 第一行
		c.gridx = 0;
		c.gridy = 1;
		componentLayout.setConstraints(this.btnPanel, c);
		this.add(this.btnPanel);
		
	}

	private void setTopLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200};
		componentLayout.rowHeights = new int[] { 50, 30, 30, 30, 30};
		componentLayout.rowWeights = new double[] { 0, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0, 0, 0, 0};
		this.topPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		// 第一行
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.portLab, c);
		this.topPanel.add(this.portLab);
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.portCom, c);
		this.topPanel.add(this.portCom);
		// 第二行
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		componentLayout.setConstraints(this.modelLab, c);
		this.topPanel.add(this.modelLab);
		
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.modelCom, c);
		this.topPanel.add(this.modelCom);
		// 第三行
//		c.gridx = 0;
//		c.gridy = 2;
//		c.fill = GridBagConstraints.BOTH;
//		componentLayout.setConstraints(this.vlanIdLab, c);
//		this.topPanel.add(this.vlanIdLab);
//		
//		c.gridx = 1;
//		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		componentLayout.setConstraints(this.vlanIdTxt, c);
//		this.topPanel.add(this.vlanIdTxt);
		// 第四行
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		componentLayout.setConstraints(this.countLab, c);
		this.topPanel.add(this.countLab);
		
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.countTxt, c);
		this.topPanel.add(this.countTxt);
		// 第五行
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		componentLayout.setConstraints(this.messageLab, c);
		this.topPanel.add(this.messageLab);
	}

	private void setBtnLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 350, 20};
		componentLayout.rowHeights = new int[] { 25};
		this.btnPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		// 第一行
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 25);
		c.anchor = GridBagConstraints.EAST;
		componentLayout.setConstraints(this.saveBtn, c);
		this.btnPanel.add(this.saveBtn);
		
		c.gridx = 1;
		c.gridy = 0;
		componentLayout.setConstraints(this.cancelBtn, c);
		this.btnPanel.add(this.cancelBtn);
	}

	/**
	 * 添加监听
	 */
	private void setListener() {
		this.saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveMacInfo();
			}
		});
		
		this.cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
//		this.vlanIdTxt.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseExited(MouseEvent a) {
//				if (checkLength(1, 4094, vlanIdTxt)) {
//					return;
//				}
//			}
//		});
//		this.vlanIdTxt.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent a) {
//				if (checkLength(1, 4094, vlanIdTxt)) {
//					return;
//				}
//			}
//		});
		
		this.countTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent a) {
				if (checkLength(0, 32000, countTxt)) {
					return;
				}
			}
		});
		this.countTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent a) {
				if (checkLength(0, 32000, countTxt)) {
					return;
				}
			}
		});
	}

	/**
	 * 检验文本框输入的值是否合理
	 * @param min值
	 * @param max值
	 * @param 要验证文本框
	 * @return
	 */
	private boolean checkLength(int min, int max, JTextField text) {
		if ("".equals(text.getText())
				|| Integer.valueOf(text.getText()) > max
				|| Integer.valueOf(text.getText()) < min) {
			this.messageLab.setText(ResourceUtil.srcStr(StringKeysTip.TIP_OUT_LIMIT) + min+"-"+max);
			text.setBorder(new LineBorder(Color.RED));
			this.saveBtn.setEnabled(false);
			this.cancelBtn.setEnabled(false);
			return true;
		}
		this.messageLab.setText("   ");
		text.setBorder(new LineBorder(Color.gray));
		this.saveBtn.setEnabled(true);
		this.cancelBtn.setEnabled(true);
		return false;
	}
	
	/**
	 * 收集界面数据,下发入库
	 */
	private void saveMacInfo() {
		if(!this.checkIsFull()){
			return;
		}
		macInfo.setSiteId(ConstantUtil.siteId);
		macInfo.setPortId(Integer.parseInt(((ControlKeyValue) this.portCom.getSelectedItem()).getId()));
		macInfo.setMacModel(Integer.parseInt(((Code)((ControlKeyValue)this.modelCom.getSelectedItem()).getObject()).getCodeValue()));
//		macInfo.setVlanId(Integer.parseInt(this.vlanIdTxt.getText()));
		macInfo.setMacCount(Integer.parseInt(this.countTxt.getText()));
		DispatchUtil macDispatch = null;
		String result = null;
		MacLearningService_MB macService = null;
		MacLearningInfo mac = null;
		try {
			macDispatch = new DispatchUtil(RmiKeys.RMI_MACLEARNINGLIMIT);
			macService = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			if(macInfo.getId() == 0){
				//新建
				result = macDispatch.excuteInsert(macInfo);
				macInfo.setPortNa(this.getPortName(macInfo.getPortId()));
				this.insertOpeLog(EOperationLogType.MACLEARNINGLIMITINSERT.getValue(), result, null,macInfo);				
			}else{
				//修改
				mac=macService.selectById(macInfo.getId());
				mac.setPortNa(this.getPortName(mac.getPortId()));
				macInfo.setPortNa(this.getPortName(macInfo.getPortId()));
				result = macDispatch.excuteUpdate(macInfo);
				this.insertOpeLog(EOperationLogType.MACLEARNINGLIMIUPDATE.getValue(), result, mac,macInfo);		
			}
			DialogBoxUtil.succeedDialog(this, result);
			this.view.getController().refresh();
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private String getPortName(int portId) {
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			return portService.selectPortybyid(portId).getPortName();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		finally
		{
			UiUtil.closeService_MB(portService);
		}
		return "";
	}
	
	private void insertOpeLog(int operationType, String result, MacLearningInfo oldMac, MacLearningInfo newMac){
	   AddOperateLog.insertOperLog(saveBtn, operationType, result, oldMac, newMac, newMac.getSiteId(),ResourceUtil.srcStr(StringKeysTitle.TIT_MAC_LIMIT_MANAGE),"MacLearningInfo");
	}
	
	
	/**
	 * 检查数据是否为空,是否合理
	 */
	private boolean checkIsFull() {
		if(this.portCom.getSelectedItem() == null){
			//如果端口为空,说明没有可用的端口
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.LBL_NO_PORT));
			return false;
		}
		if(
//			this.vlanIdTxt.getText() == null ||
			this.countTxt.getText() == null){
			//如果有其中一项没有填写完整,则提示
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
			return false;
		}
		return true;
	}

	/**
	 * 初始化下拉列表和文本框
	 */
	private void initComboBoxAndTxt() {
		try {
			super.getComboBoxDataUtil().comboBoxData(this.modelCom, "MACLEARNMODEL");
//			this.vlanIdTxt.setText("1");
			this.countTxt.setText("0");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化端口信息
	 */
	private void initPortValue() {
		PortService_MB portService = null;
		MacLearningService_MB macService = null;
		List<PortInst> portInstList = null;
		List<Integer> portIdList = null;
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			macService = (MacLearningService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MACLEARN);
			PortInst portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			portInstList = portService.select(portInst);
			portIdList = macService.selectAllPortId(ConstantUtil.siteId);
			for (PortInst inst : portInstList) {
				if(inst.getPortType().equals("NONE") || inst.getPortType().equals("UNI") 
						||inst.getPortType().equals("NNI")){
					if(!portIdList.contains(inst.getPortId())){
						defaultComboBoxModel.addElement(new ControlKeyValue(inst.getPortId() + "", inst.getPortName(), inst));
					}
				}
			}
			this.portCom.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(macService);
		}

	}

	/**
	 * 修改时,给界面赋值
	 */
	private void setValue() {
		this.setValueToPort();
		this.portCom.setEnabled(false);
		super.getComboBoxDataUtil().comboBoxSelectByValue(this.modelCom, macInfo.getMacModel()+"");
//		this.vlanIdTxt.setText(macInfo.getVlanId()+"");
		this.countTxt.setText(macInfo.getMacCount()+"");
	}

	private void setValueToPort() {
		PortService_MB portService = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		PortInst inst = null;
		try {
			defaultComboBoxModel = new DefaultComboBoxModel();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			inst = new PortInst();
			inst.setPortId(macInfo.getPortId());
			inst = portService.select(inst).get(0);
			defaultComboBoxModel.addElement(new ControlKeyValue(inst.getPortId() + "", inst.getPortName(), inst));
			this.portCom.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}
}
