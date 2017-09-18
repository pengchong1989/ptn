package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.bean.system.code.Code;
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

public class FMUpdateDialog extends PtnDialog {

	private static final long serialVersionUID = -7335679379181021280L;

	private JLabel clockType = null;

	private JComboBox clockType_ = null;

	private JLabel port = null;

	private JTextField port_ = null;

	private JLabel recoveryMode = null;

	private JComboBox recoveryMode_ = null;

	private JLabel priority = null;

	private PtnTextField priority_ = null;

	private JLabel dnuGroup = null;

	private PtnTextField dnuGroup_ = null;

	private JLabel receiveSSMSettingValue = null;

	private JComboBox receiveSSMSettingValue_ = null;

	private JLabel ssmSendingEnabled = null;

	private JCheckBox ssmSendingEnabled_ = null;

	private JPanel buttonPanel = null;

	private PtnButton confirm = null;

	private JButton cancel = null;

	private GridBagLayout gridBagLayout = null;
	
	private ClockSource clockSource = null;
	private JDialog jDialog = null;
	private TabPanelTwoCX tabPanelTwoCX;
	private JLabel lblMessage=null;
	
	public FMUpdateDialog(ClockSource clockSource,TabPanelTwoCX tabPanelTwoCX) throws Exception {

		try {
			this.tabPanelTwoCX=tabPanelTwoCX;
			this.clockSource = clockSource;
			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void init() throws Exception {

		try {
			gridBagLayout = new GridBagLayout();
			lblMessage=new JLabel();
			clockType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_TYPE));
			clockType_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.clockType_, "clockType");
			clockType_.setEnabled(false);
			port = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT));
			port_ = new JTextField();
			port_.setEnabled(false);
			recoveryMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RECOVERY_MODE));
			recoveryMode_= new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.recoveryMode_, "systemClockRecoveryMode");
			recoveryMode_.setEnabled(false);
			priority = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY));
			
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
			
			priority_ = new PtnTextField(false, null,1, 3, lblMessage, confirm, this);
			priority_.setMaxValue(255);
			priority_.setMinValue(0);
			priority_.setCheckingMaxValue(true);
			priority_.setCheckingMinValue(true);
			priority_.setText("0");
			dnuGroup = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DNU_GROUP));
			dnuGroup_ = new PtnTextField(false, null,1, 3, lblMessage, confirm, this);	
			dnuGroup_.setMaxValue(255);
			dnuGroup_.setMinValue(0);
			dnuGroup_.setCheckingMaxValue(true);
			dnuGroup_.setCheckingMinValue(true);
			
			if("1".equals(UiUtil.getCodeById(clockSource.getClockType()).getCodeValue())){
				dnuGroup_.setEnabled(false);
			}
			receiveSSMSettingValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RECEIVE_SSM_SETTING_VALUE));
			receiveSSMSettingValue_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.receiveSSMSettingValue_, "receiveSSMSettingValue");
			ssmSendingEnabled = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SSM_SENDING_ENABLED));
			ssmSendingEnabled_ = new JCheckBox();
			
			buttonPanel = new JPanel();
			
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			setButtonLayout();/*按钮所在panel布局*/
			buttonPanel.add(confirm);
			buttonPanel.add(cancel);
			

			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			this.add(this.lblMessage);
			this.add(port);
			this.add(port_);
			this.add(clockType);
			this.add(clockType_);
			this.add(recoveryMode);
			this.add(recoveryMode_);
			this.add(priority);
			this.add(priority_);
			this.add(dnuGroup);
			this.add(dnuGroup_);
			this.add(receiveSSMSettingValue);
			this.add(receiveSSMSettingValue_);
			this.add(ssmSendingEnabled);
			this.add(ssmSendingEnabled_);
			this.add(buttonPanel);
			
			setValueForDialog();/*为对话框里的每项赋值*/
			addButtonListener();/*为按钮添加事件*/
			UiUtil.showWindow(this, 400, 480);
		} catch (Exception e) {

			throw e;
		}
	}

	private void setValueForDialog()throws Exception{
		PortService_MB portService = null;
		try{
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			super.getComboBoxDataUtil().comboBoxSelect(clockType_,String.valueOf(clockSource.getClockType()));
//			clockType_.setSelectedItem("1");
//			port_.setText(UiUtil.getCodeById(clockSource.getPort()).getCodeName());
			port_.setText(portService.getPortname(clockSource.getPort()));
			super.getComboBoxDataUtil().comboBoxSelect(recoveryMode_,String.valueOf(clockSource.getRecoverModel()));
			priority_.setText(clockSource.getSystemPriorLevel());
			dnuGroup_.setText(clockSource.getDNUGroup());
			super.getComboBoxDataUtil().comboBoxSelect(receiveSSMSettingValue_,String.valueOf(clockSource.getReceiveSSMValue()));
			ssmSendingEnabled_.setSelected(clockSource.getSSMSend()==1?true:false);
		}catch(Exception e){
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
	
	/**
	 * <p>
	 * 按钮所在panel布局
	 * </p>
	 * @throws Exception
	 */
	private void setButtonLayout()throws Exception{
		
		GridBagConstraints gridBagConstraints = null;
		GridBagLayout gridBagLayout = null;
		try{
			
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] {150, 25,25 };
			gridBagLayout.columnWeights = new double[] { 0,0  };
			gridBagLayout.rowHeights = new int[] { 21};
			gridBagLayout.rowWeights = new double[] {0 };
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
		}catch(Exception e){
			
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
			gridBagLayout.columnWidths = new int[] { 100,200 };
			gridBagLayout.columnWeights = new double[] { 0,1  };
			gridBagLayout.rowHeights = new int[] { 50,50, 50,50,50,50,50,50,50};
			gridBagLayout.rowWeights = new double[] {0, 0,0,0,0,0,0,0 ,0};
			gridBagConstraints.insets = new Insets(10, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridwidth=2;
			gridBagLayout.setConstraints(this.lblMessage, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.gridwidth=1;
			gridBagLayout.setConstraints(clockType, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(port, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(recoveryMode, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(priority, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(dnuGroup, gridBagConstraints);
			
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
			gridBagLayout.setConstraints(clockType_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(port_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(recoveryMode_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(priority_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(dnuGroup_, gridBagConstraints);
			
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
		jDialog=this;
//		ButtonActionListener ButtonActionListener = null;
		try {
			this.confirm.addActionListener(new MyActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					bensaveListener();
					
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
//			ButtonActionListener = new FMUpdateButtonListener(this);
//			this.confirm.addActionListener(ButtonActionListener);
//			this.cancel.addActionListener(ButtonActionListener);
		} catch (Exception e) {

			throw e;
		}
	}
//保存修改的值
	public void bensaveListener(){
		
		DispatchUtil clockSourceDispatch = null;
		
		try {
			clockSourceDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKSOURCE);
			if(null== this.clockType_.getSelectedItem()||null==this.recoveryMode_.getSelectedItem()){
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
			}else{
				    //时钟类型
				    ControlKeyValue clockTypekey_broad = (ControlKeyValue) this.clockType_.getSelectedItem();
				   // clockSource.setClockType(Integer.parseInt(clockTypekey_broad.getId()));
				    //端口
//				    clockSource.setPort(Integer.parseInt(this.port_.getText()));
					//恢复模式
					ControlKeyValue recoveryModekey_broad = (ControlKeyValue) this.recoveryMode_.getSelectedItem();
					clockSource.setRecoverModel(Integer.parseInt(recoveryModekey_broad.getId()));
				    //优先级
					if("1".equals(UiUtil.getCodeById(clockSource.getClockType()).getCodeValue())){
						//导出时钟
						clockSource.setSystemPriorLevel(priority_.getText());
					}else{
						clockSource.setSystemPriorLevel(priority_.getText());
					}
					
				    //DNU组
					clockSource.setDNUGroup(dnuGroup_.getText());
					//SSM设置值
					ControlKeyValue receiveSSMSettingValuekey_broad = (ControlKeyValue) this.receiveSSMSettingValue_.getSelectedItem();
					clockSource.setReceiveSSMValue(Integer.parseInt(receiveSSMSettingValuekey_broad.getId()));
				    //SSM使能发送
					clockSource.setSSMSend(ssmSendingEnabled_.isSelected()?1:0);
					//设置ID
					clockSource.setId(clockSource.getId());
					String result=clockSourceDispatch.excuteUpdate(clockSource);
					
					if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
						confirm.setResult(1);
					}else{
						confirm.setResult(2);
					}
					this.confirm.setOperateKey(EOperationLogType.FREQUENCYUPDATE.getValue());
					DialogBoxUtil.succeedDialog(this, result);
				    //掩藏界面
					jDialog.dispose();
					//刷新组界面
					tabPanelTwoCX.getController().refresh();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	};
	/**
	 *function:将组界面的“收取SSM设置值” 转换到下拉列表的选择状态 
	 * @param jComboBox
	 * @param values
	 */
	public  void comboBoxSelectByValue(JComboBox jComboBox, String values) {
		ControlKeyValue controlKeyValue = null;
		String value = null;
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			controlKeyValue = (ControlKeyValue) jComboBox.getItemAt(i);
			value = ((Code) controlKeyValue.getObject()).getCodeName();
			if (value.equals(values)) {
				jComboBox.setSelectedIndex(i);
				return;
			}
		}
	}
}
