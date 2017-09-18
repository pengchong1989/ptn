package com.nms.ui.ptn.clock.view.cx.dialog;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.frequency.TabPanelTwoCX;

public class FMCreateDialog extends PtnDialog {

	private static final long serialVersionUID = -1652162221167056477L;

	private JLabel port = null;

	private JComboBox port_ = null;

	private JLabel systemClockPriority = null;

	private PtnTextField systemClockPriority_ = null;

	private JLabel systemClockRecoveryMode = null;

	private JComboBox systemClockRecoveryMode_ = null;

	private JLabel systemClockDNUGroup = null;

	private PtnTextField systemClockDNUGroup_ = null;

	private JLabel exportClockPriority = null;

	private PtnTextField exportClockPriority_ = null;

	private JLabel receiveSSMSettingValue = null;

	private JComboBox receiveSSMSettingValue_ = null;

	private JLabel ssmSendingEnabled = null;

	private JCheckBox ssmSendingEnabled_ = null;

	private JPanel buttonPanel = null;

	private PtnButton confirm = null;

	private JButton cancel = null;

	private GridBagLayout gridBagLayout = null;
	
	private JLabel lblMessage=null;

	private JDialog jDialog = null;
	private TabPanelTwoCX tabPanelTwoCX ;
	public FMCreateDialog(TabPanelTwoCX tabPanelTwoCX) throws Exception {
		try {
			this.tabPanelTwoCX = tabPanelTwoCX;
			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {
			gridBagLayout = new GridBagLayout();
			this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_CLOCKSOURCE));
			port = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT));
			port_ = new JComboBox();/* 下拉框所加数据暂不实现 */
			this.intalPortComboBox(port_);
			lblMessage=new JLabel();
			systemClockPriority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYSTEM_CLOCK_PRIORITY));
			
			systemClockRecoveryMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYSTEM_CLOCK_RECOVERY_MODE));
			systemClockRecoveryMode_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.systemClockRecoveryMode_, "systemClockRecoveryMode");
			systemClockDNUGroup = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYSTEM_CLOCK_DNU_GROUP));
			exportClockPriority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXPORT_CLOCK_PRIORITY));
			
			receiveSSMSettingValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RECEIVE_SSM_SETTING_VALUE));
			receiveSSMSettingValue_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.receiveSSMSettingValue_, "receiveSSMSettingValue");
			ssmSendingEnabled = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SSM_SENDING_ENABLED));
			ssmSendingEnabled_ = new JCheckBox();

			buttonPanel = new JPanel();
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			setButtonLayout();/* 按钮所在panel布局 */
			buttonPanel.add(confirm);
			buttonPanel.add(cancel);
			systemClockDNUGroup_ = new PtnTextField(false, null,1,3, lblMessage, confirm, this);
			systemClockDNUGroup_.setMaxValue(255);
			systemClockDNUGroup_.setMinValue(0);
			systemClockDNUGroup_.setCheckingMaxValue(true);
			systemClockDNUGroup_.setCheckingMinValue(true);
			systemClockDNUGroup_.setText("0");
			systemClockPriority_ =new PtnTextField(false, null,1,3, lblMessage, confirm, this);
			systemClockPriority_.setMaxValue(255);
			systemClockPriority_.setMinValue(0);
			systemClockPriority_.setCheckingMaxValue(true);
			systemClockPriority_.setCheckingMinValue(true);
			systemClockPriority_.setText("0");
			
			exportClockPriority_ =new PtnTextField(false, null,1,3, lblMessage, confirm, this);
			exportClockPriority_.setMaxValue(255);
			exportClockPriority_.setMinValue(0);
			exportClockPriority_.setCheckingMaxValue(true);
			exportClockPriority_.setCheckingMinValue(true);
			exportClockPriority_.setText("0");
			
			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			this.add(this.lblMessage);
			this.add(port);
			this.add(port_);
			this.add(systemClockPriority);
			this.add(systemClockPriority_);
			this.add(systemClockRecoveryMode);
			this.add(systemClockRecoveryMode_);
			this.add(systemClockDNUGroup);
			this.add(systemClockDNUGroup_);
			this.add(exportClockPriority);
			this.add(exportClockPriority_);
			this.add(receiveSSMSettingValue);
			this.add(receiveSSMSettingValue_);
			this.add(ssmSendingEnabled);
			this.add(ssmSendingEnabled_);
			this.add(buttonPanel);
			addButtonListener();/* 为按钮添加事件 */
			UiUtil.showWindow(this, 400, 480);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 按钮所在panel布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setButtonLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		GridBagLayout gridBagLayout = null;
		try {

			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 150,25, 25 };
			gridBagLayout.columnWeights = new double[] { 0.1,0, 0 };
			gridBagLayout.rowHeights = new int[] { 21 };
			gridBagLayout.rowWeights = new double[] { 0 };
			gridBagConstraints.insets = new Insets(5, 10, 5, 0);

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(confirm, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(cancel, gridBagConstraints);

			buttonPanel.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 主窗口布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 100, 200 };
			gridBagLayout.columnWeights = new double[] { 0, 1 };
			gridBagLayout.rowHeights = new int[] { 50,50, 50, 50, 50, 50, 50, 50, 50 };
			gridBagLayout.rowWeights = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.insets = new Insets(10, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridwidth=2;
			gridBagLayout.setConstraints(this.lblMessage, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.gridwidth=1;
			gridBagLayout.setConstraints(port, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(systemClockPriority, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(systemClockRecoveryMode, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(systemClockDNUGroup, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(exportClockPriority, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(receiveSSMSettingValue, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 7;
			gridBagLayout.setConstraints(ssmSendingEnabled, gridBagConstraints);

			gridBagConstraints.insets = new Insets(10, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(port_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(systemClockPriority_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(systemClockRecoveryMode_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(systemClockDNUGroup_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(exportClockPriority_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(receiveSSMSettingValue_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 7;
			gridBagLayout.setConstraints(ssmSendingEnabled_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 8;
			gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);

		} catch (Exception e) {

			throw e;
		}
	}

	
	/**
	 * <p>
	 * 按钮添加监听
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void addButtonListener() throws Exception {
		jDialog = this;
//		ButtonActionListener ButtonActionListener = null;
		try {
			this.confirm.addActionListener(new MyActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnsaveData();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}

				@Override
				public boolean checking() {
					
					return true;
				}
			});
			this.cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialog.dispose();
				}
			});
//			ButtonActionListener = new FMCreateButtonListener(this);
//			this.confirm.addActionListener(ButtonActionListener);
//			this.cancel.addActionListener(ButtonActionListener);
		} catch (Exception e) {

			throw e;
		}
	}
	/**
	 * 保存新建的数据
	 * @throws Exception 
	 */
	private void btnsaveData() throws Exception{
		ClockSource clockSource=null;
		DispatchUtil clockSourceDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKSOURCE);
		try {
			clockSource=new ClockSource();
			//设置端口
			if(null==this.port_.getSelectedItem()){
				  DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE));
			}else{
				ControlKeyValue portkey_broad = (ControlKeyValue) this.port_.getSelectedItem();
  			    clockSource.setPort(Integer.parseInt(portkey_broad.getId()));
				//系统优先级
				clockSource.setSystemPriorLevel(this.systemClockPriority_.getText());
				//系统恢复模式
				ControlKeyValue recoverModelkey_broad = (ControlKeyValue) this.systemClockRecoveryMode_.getSelectedItem();
				clockSource.setRecoverModel(Integer.parseInt(recoverModelkey_broad.getId()));
				//系统时钟DNU组
				clockSource.setDNUGroup(this.systemClockDNUGroup_.getText());
				// 导出时钟优先级
				clockSource.setExportPriorLevel(this.systemClockPriority_.getText());
				//收SSM设置值
				ControlKeyValue receiveSSMSettingValuekey_broad = (ControlKeyValue) this.receiveSSMSettingValue_.getSelectedItem();
				clockSource.setReceiveSSMValue(Integer.parseInt(receiveSSMSettingValuekey_broad.getId()));
				//SSM使能发送
				clockSource.setSSMSend(this.ssmSendingEnabled_.isSelected()?1:0);
				//设置网元相关的ID
				clockSource.setSiteId(ConstantUtil.siteId);
				// 默认    外部命令
				clockSource.setScslockout("false");
				clockSource.setEcslockout("true");
				clockSource.setScsforce("false");
				clockSource.setEcsforce("false");
				clockSource.setScsmanual("false");
				clockSource.setEcsmanual("false");
			//	ClockSourceDispatch x=new ClockSourceDispatch();
				String result=clockSourceDispatch.excuteInsert(clockSource);
				this.confirm.setOperateKey(EOperationLogType.FREQUENCYINSERT.getValue());
				if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
					confirm.setResult(1);
				}else{
					confirm.setResult(2);
				}

				DialogBoxUtil.succeedDialog(this, result);
				//创建界面消失
				jDialog.dispose();
				this.tabPanelTwoCX.getController().refresh();
			}
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	};
	private void intalPortComboBox(JComboBox jComboBox) throws Exception {
		PortService_MB portService = null;
		List<PortInst> portInstList = null;
		DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			portInst.setPortType("NNI");

			portInstList = portService.select(portInst);
			for (PortInst inst : portInstList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(inst.getPortId() + "", inst.getPortName(), inst));
			}
			jComboBox.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			portInstList = null;
		}

	}
	
}
