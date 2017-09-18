package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ControlKeyValue;
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
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelOneTICX;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class ExternalClockUpdataOther extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6705915776837939308L;
	private JLabel interfaceType;
	private JTextField interfaceType_;
	private JLabel manageState;
	private JComboBox manageState_;
	private JLabel jobState;
	private JTextField jobState_;
	private JLabel interfaceModel;
	private JComboBox interfaceModel_;
	private JLabel timeCompensate;
    private JTextField timeCompensate_;
    private PtnButton confirm=null;
	private JButton cancel=null;
    private JDialog jDialog=null;
    private JPanel buttonJpan=null;
    private GridBagLayout gridBagLayout=null;
    private ExternalClockInterface externalClockInterface=null;
    private TabPanelOneTICX tabPanelOneTICX =null;
    
    public ExternalClockUpdataOther(TabPanelOneTICX tabPanelOneTICX,ExternalClockInterface externalClockInterface){
    	this.tabPanelOneTICX=tabPanelOneTICX;
    	this.externalClockInterface=externalClockInterface;
    	init();
    }

	/**
	 *初始化界面
	 */
	private void init() {
		try {
			super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_EXTERNALCLOCK));
			gridBagLayout = new GridBagLayout();
			gridBagLayout = new GridBagLayout();
			interfaceType=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INTERFACE_TYPE));
			interfaceType_=new JTextField();
			interfaceType_.setEditable(false);
			manageState=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANAGE_STATUS)); 
			manageState_=new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.manageState_, "ENABLEDSTATUE");
			
			jobState=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_STATUS));
			jobState_=new JTextField();
			jobState_.setEditable(false);

			interfaceModel=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INTERFACE_MODEL));
			interfaceModel_=new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.interfaceModel_, "intefaceModel");
			
			timeCompensate=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIME_COMPENSATE));
			timeCompensate_=new JTextField();
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL)); 
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false,RootFactory.CORE_MANAGE);
			buttonJpan=new JPanel();
			setLayout();
			
			buttonJpan.add(confirm);
			buttonJpan.add(cancel);
			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			this.add(buttonJpan);
			this.add(interfaceType);
			this.add(interfaceType_);
			this.add(manageState);
			this.add(manageState_);
			this.add(jobState);
			this.add(jobState_);
			this.add(interfaceModel);
			this.add(interfaceModel_);
			this.add(timeCompensate);
			this.add(timeCompensate_);
			initdata();
			addButtonListener();
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
		    addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
			UiUtil.showWindow(this, 400, 350);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 *初始化数据 
	 */
	private void initdata() {
		try {
			
			this.interfaceType_.setText(externalClockInterface.getInterfaceName());
			super.getComboBoxDataUtil().comboBoxSelect(this.manageState_,String.valueOf(externalClockInterface.getManagingStatus()));
			this.jobState_.setText(externalClockInterface.getJobStatus(externalClockInterface.getWorkingStatus()));
			super.getComboBoxDataUtil().comboBoxSelect(this.interfaceModel_,String.valueOf(externalClockInterface.getInterfaceMode()));
		   //当接口模式为“输出模式时” 事件偏移补偿Label和JTextField有要影藏
		   if( UiUtil.getCodeById(externalClockInterface.getInterfaceMode()).getCodeName().toString().equals("输出模式")){
		    	timeCompensate.setVisible(false);
		    	timeCompensate_.setVisible(false);
		    }else{
		    	timeCompensate_.setText(externalClockInterface.getTimeOffsetCompensation());
		    }
		   
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 设置布局
	 */
	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints=new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 50,50,200};
			gridBagLayout.columnWeights = new double[] {0,0,0};
			gridBagLayout.rowHeights = new int[] {35, 35, 35, 35, 35, 35, 35, 35};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagConstraints.insets = new Insets(5, 10, 0, 0); 
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			
			
			gridBagConstraints.gridwidth=30;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(interfaceType, gridBagConstraints);
			
			gridBagConstraints.gridwidth=30;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(manageState, gridBagConstraints);
			
			gridBagConstraints.gridwidth=30;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(jobState, gridBagConstraints);
			
			gridBagConstraints.gridwidth=30;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(interfaceModel, gridBagConstraints);
			
			gridBagConstraints.gridwidth=50;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(timeCompensate, gridBagConstraints);
   //******************************************************************		
////			gridBagConstraints.insets = new Insets(5, 30, 0, 0);
//			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(interfaceType_, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(manageState_, gridBagConstraints);
		
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(jobState_, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(interfaceModel_, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(timeCompensate_, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(buttonJpan, gridBagConstraints);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 
	 * 创建按钮的布局
	 */
	private void setLayout() {
		GridBagConstraints gridBagConstraints=null;
		 GridBagLayout gridLayout = null;
		try {
			gridLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridLayout.columnWidths=new int[]{20,20};
			gridLayout.columnWeights=new double[]{0,0};
			gridLayout.rowHeights=new int[]{21};
			gridLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,10,5,0);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(confirm, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 25, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(cancel, gridBagConstraints);
			
			buttonJpan.setLayout(gridLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}	
		
	}

	/**
	 * 处理各个单击事件处理
	 * 
	 */
	private void addButtonListener() {
		try {
			jDialog=this;
			this.confirm.addActionListener(new MyActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					btnSaveData();
					
				}
			
				@Override
				public boolean checking() {
					return false;
				}
			}); 
				
			this.cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialog.dispose();
				}
			});
			
			this.interfaceModel_.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(interfaceModel_.getSelectedItem().toString().equals("输出模式")){
						 timeCompensate.setVisible(false);
					     timeCompensate_.setVisible(false);
					}else{
						timeCompensate.setVisible(true);
					    timeCompensate_.setVisible(true);
					}
				}
			});
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 保存事件 
	 */
	private void btnSaveData() {
		DispatchUtil externalClockDispatch;
		try {
		
		externalClockInterface.setInterfaceName(this.interfaceType_.getText());
		//管理状态
	    ControlKeyValue manageStatekey_broad = (ControlKeyValue) this.manageState_.getSelectedItem();
	    externalClockInterface.setManagingStatus(Integer.parseInt(manageStatekey_broad.getId()));
	   // externalClockInterface.setWorkingStatus(this.jobState_.getText());
	    //接口模式
	    ControlKeyValue interfaceModelkey_broad = (ControlKeyValue) this.interfaceModel_.getSelectedItem();
	    externalClockInterface.setInterfaceMode(Integer.parseInt(interfaceModelkey_broad.getId()));
	   //如果"时间偏移补偿"是显示的就获取值 否则不管
	    if(this.timeCompensate_.isVisible()){
	    	if("".equals(this.timeCompensate_.getText())){
	    		externalClockInterface.setTimeOffsetCompensation("0");
	    	}else{
	    		externalClockInterface.setTimeOffsetCompensation(this.timeCompensate_.getText());
	    	}
	    }else{
	    	externalClockInterface.setTimeOffsetCompensation("-");
	    }
	    //操作数据库/刷新主界面/影藏界面
	    externalClockDispatch = new DispatchUtil(RmiKeys.RMI_EXTERNALCLOCK);
	  //  ExternalClockDispatch e=new ExternalClockDispatch();
	    String result=externalClockDispatch.excuteUpdate(externalClockInterface);
//	    AddOperateLog.insertOperLog(confirm, EOperationLogType.REMOVELOGINLOG.getValue(), result);
	    //externalClockInterfaceService.update(externalClockInterface);
	    DialogBoxUtil.succeedDialog(null, result);
	    this.tabPanelOneTICX.controller.refresh();
	    jDialog.dispose();
			
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
			timeCompensate_.addKeyListener(textFIledKeyListener);
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
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_TIME_COMPENSATE);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,6,timeCompensate_);
			timeCompensate_.addFocusListener(textfieldFocusListener);
		}catch(Exception e){
			
			throw e;
		}
	}

	public JLabel getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(JLabel interfaceType) {
		this.interfaceType = interfaceType;
	}

	public JTextField getInterfaceType_() {
		return interfaceType_;
	}

	public void setInterfaceType_(JTextField interfaceType) {
		interfaceType_ = interfaceType;
	}

	public JLabel getManageState() {
		return manageState;
	}

	public void setManageState(JLabel manageState) {
		this.manageState = manageState;
	}

	public JComboBox getManageState_() {
		return manageState_;
	}

	public void setManageState_(JComboBox manageState) {
		manageState_ = manageState;
	}

	public JLabel getJobState() {
		return jobState;
	}

	public void setJobState(JLabel jobState) {
		this.jobState = jobState;
	}

	public JTextField getJobState_() {
		return jobState_;
	}

	public void setJobState_(JTextField jobState) {
		jobState_ = jobState;
	}

	public JLabel getInterfaceModel() {
		return interfaceModel;
	}

	public void setInterfaceModel(JLabel interfaceModel) {
		this.interfaceModel = interfaceModel;
	}

	public JComboBox getInterfaceModel_() {
		return interfaceModel_;
	}

	public void setInterfaceModel_(JComboBox interfaceModel) {
		interfaceModel_ = interfaceModel;
	}

	public JLabel getTimeCompensate() {
		return timeCompensate;
	}

	public void setTimeCompensate(JLabel timeCompensate) {
		this.timeCompensate = timeCompensate;
	}

	public JTextField getTimeCompensate_() {
		return timeCompensate_;
	}

	public void setTimeCompensate_(JTextField timeCompensate) {
		timeCompensate_ = timeCompensate;
	}

	public PtnButton getConfirm() {
		return confirm;
	}

	public void setConfirm(PtnButton confirm) {
		this.confirm = confirm;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JDialog getjDialog() {
		return jDialog;
	}

	public void setjDialog(JDialog jDialog) {
		this.jDialog = jDialog;
	}

	public JPanel getButtonJpan() {
		return buttonJpan;
	}

	public void setButtonJpan(JPanel buttonJpan) {
		this.buttonJpan = buttonJpan;
	}

	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}

	public void setGridBagLayout(GridBagLayout gridBagLayout) {
		this.gridBagLayout = gridBagLayout;
	}

	public ExternalClockInterface getExternalClockInterface() {
		return externalClockInterface;
	}

	public void setExternalClockInterface(ExternalClockInterface externalClockInterface) {
		this.externalClockInterface = externalClockInterface;
	}

	public TabPanelOneTICX getTabPanelOneTICX() {
		return tabPanelOneTICX;
	}

	public void setTabPanelOneTICX(TabPanelOneTICX tabPanelOneTICX) {
		this.tabPanelOneTICX = tabPanelOneTICX;
	}
	
}
