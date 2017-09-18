package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelTwoTICX;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;

public class CIUpdateDialog extends PtnDialog {

	private static final long serialVersionUID = -2249918601521756276L;

	private JLabel port = null;

	private JTextField port_ = null;

	private JLabel ssmSendingEnabled = null;

	private JCheckBox ssmSendingEnabled_ = null;

	private JLabel dnuGroup = null;

	private JTextField dnuGroup_ = null;

	private PtnButton confirm = null;

	private JButton cancel = null;

	private GridBagLayout gridBagLayout = null;

	private LineClockInterface lineClockInterface = null;

	private JPanel buttonPanel = null;
	
	private JDialog jDialog=null;
	
	private TabPanelTwoTICX tabPanelTwoTICX=null;

	public CIUpdateDialog(LineClockInterface lineClockInterface,TabPanelTwoTICX tabPanelTwoTICX) throws Exception {

		try {

			this.lineClockInterface = lineClockInterface;
			this.tabPanelTwoTICX=tabPanelTwoTICX;
			init();
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {
			this.setTitle(ResourceUtil.srcStr(StringKeysTip.TIP_LINE_CLOCK));
			gridBagLayout = new GridBagLayout();

			port = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT));
			port_ = new JTextField();
			port_.setEnabled(false);
			ssmSendingEnabled = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SSM_SENDING_ENABLED));
			ssmSendingEnabled_ = new JCheckBox();
			dnuGroup = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DNU_GROUP));
			dnuGroup_ = new JTextField();

			buttonPanel = new JPanel();
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			setButtonLayout();/* 按钮所在panel布局 */
			buttonPanel.add(confirm);
			buttonPanel.add(cancel);

			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			this.add(port);
			this.add(port_);
			this.add(ssmSendingEnabled);
			this.add(ssmSendingEnabled_);
			this.add(dnuGroup);
			this.add(dnuGroup_);
			this.add(buttonPanel);
            initDate();
            addButtonListener();/*增加按钮的事件处理*/
            addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
			addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
			UiUtil.showWindow(this, 380, 280);
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
			gridBagLayout.columnWeights = new double[] {0.2, 0, 0 };
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
			gridBagLayout.columnWeights = new double[] { 0, 0.2 };
			gridBagLayout.rowHeights = new int[] { 50, 50, 50,50 };
			gridBagLayout.rowWeights = new double[] { 0, 0, 0,0 };
			gridBagConstraints.insets = new Insets(10, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(port, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(ssmSendingEnabled, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(dnuGroup, gridBagConstraints);

			/** *************************************************************** */
			gridBagConstraints.insets = new Insets(10, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(port_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(ssmSendingEnabled_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(dnuGroup_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);

		} catch (Exception e) {

			throw e;
		}
	}
/**
 *赋初始值 
 */
	private void initDate() {
		try {
//			this.port_.setText(UiUtil.getCodeById(lineClockInterface.getPort()).getCodeName()); 
			this.port_.setText(String.valueOf(lineClockInterface.getPortName())); 
			this.ssmSendingEnabled_.setSelected(lineClockInterface.getSsmSendingEnabled()==1?true:false);
			this.dnuGroup_.setText(lineClockInterface.getDnuGroup());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
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
		try {
			jDialog=this;
			this.confirm.addActionListener(new MyActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSaveConfirm();
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
		} catch (Exception e) {

			throw e;
		}
	}
	/**
	 * 处理事件处理
	 * 
	 */
	public void btnSaveConfirm(){
		DispatchUtil lineClockInterfaceDispatch;
		try {
			lineClockInterfaceDispatch = new DispatchUtil(RmiKeys.RMI_LINECLOCKINTERFACE);
			lineClockInterface.setSsmSendingEnabled(this.ssmSendingEnabled_.isSelected()?1:0);
			lineClockInterface.setDnuGroup(this.dnuGroup_.getText());
			lineClockInterface.setSiteId(ConstantUtil.siteId);
			String result=lineClockInterfaceDispatch.excuteUpdate(lineClockInterface);
			//添加日志记录
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				confirm.setResult(1);
			}else{
				confirm.setResult(2);
			}
			this.confirm.setOperateKey(EOperationLogType.LINECLUPDATE.getValue());
			DialogBoxUtil.succeedDialog(this, result);
			jDialog.dispose();
			//刷新
			tabPanelTwoTICX.controller.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
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
			/* 为1：只接受数字 */
			textFIledKeyListener = new TextFiledKeyListener(1);
			dnuGroup_.addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
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
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_DNU_GROUP);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,1,dnuGroup_);
			dnuGroup_.addFocusListener(textfieldFocusListener);
//			System.out.println("---------");
		}catch(Exception e){
			
			throw e;
		}
	}
}
