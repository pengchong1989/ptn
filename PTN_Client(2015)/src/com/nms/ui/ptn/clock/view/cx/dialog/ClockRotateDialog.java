package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
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
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.clock.view.cx.frequency.TabPanelTwoCX;

public class ClockRotateDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1036908704433139558L;

	private ButtonGroup buttonGroup = null;
	private JRadioButton forceMain = null;// 强制倒换
	private JRadioButton manpower = null;//人工倒换
	private JRadioButton chkShutting=null;//闭锁
	private JRadioButton clear = null;// 清除
	private JButton clearWTR=null;//清除WTR状态
	private PtnButton confirmButton=null;//保存
	private JButton cancleButton=null;//取消
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JScrollPane needChooseScrollPane;
	private JDialog jdialog = null;
	private TabPanelTwoCX tabPanelTwoCX = null;
	private GridBagLayout layout=null;

	public ClockRotateDialog(TabPanelTwoCX tabPanelTwoCX) {
		this.tabPanelTwoCX = tabPanelTwoCX;
		init();
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		 ClockSource clockSource=null;
		try {
			clockSource=tabPanelTwoCX.getSelect();
			if(null==clockSource){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			}else{
				layout=new GridBagLayout();
				 buttonGroup = new ButtonGroup();
			     forceMain =new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTL_FORCETUNNEL));
				 manpower = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTL_MANPOWERTUNNEL));//人工倒换
				 clear = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));// 清除
				 chkShutting = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING));
				 buttonGroup.add(forceMain);
				 buttonGroup.add(manpower);
				 buttonGroup.add(chkShutting);
				 buttonGroup.add(clear);
				// if(clockSource.getClockType()==UiUtil.getCodeByValue("clockType", "0").getId()){
				 if(clockSource.getExternalOrder()!=null){
					 if(clockSource.getExternalOrder().equals("force")){
							forceMain.setSelected(true);
						}else if(clockSource.getExternalOrder().equals("manpower")){
							this.manpower.setSelected(true);
						}else if(clockSource.getExternalOrder().equals("lock")){
							this.chkShutting.setSelected(true);
							this.manpower.setEnabled(false);
							this.forceMain.setEnabled(false);
						}
				 }
				contentPanel = new JPanel();                                                              
				contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_ORDER_INSTALL)));
				
				buttonPanel=new JPanel();
				cancleButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
				confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
				clearWTR=new JButton(ResourceUtil.srcStr(StringKeysBtn.BTL_CLEARW));;//清除WTR状态
				
				setStlot2Layout();//设置按钮的布局
				setStlRaioxLayout();//设置按钮的布局
				setGridBagLayout();/* 主窗口布局 */
				this.setLayout(layout);
				this.add(buttonPanel);
				this.add(contentPanel);
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER));
				addButtonListener();
				UiUtil.showWindow(this, 350, 300);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			clockSource=null;
		}
	}
	/**
	 *单选按钮布局 
	 */
	private void setStlRaioxLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {80,50};
		componentLayout.columnWeights = new double[] { 0, 0.1 };
		componentLayout.rowHeights = new int[] { 15, 30, 30, 30, 30 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		this.contentPanel.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
	//	c.insets = new Insets(5, 1, 5, 5);
		componentLayout.setConstraints(this.forceMain, c);
		this.contentPanel.add(this.forceMain);

		c.gridx = 0;
		c.gridy = 2;
		componentLayout.setConstraints(this.manpower, c);
		this.contentPanel.add(this.manpower);

		c.gridx = 0;
		c.gridy = 3;
		componentLayout.setConstraints(this.chkShutting, c);
		this.contentPanel.add(this.chkShutting);

	//	c.insets = new Insets(5, 10, 5, 5); 
		c.gridx = 0;
		c.gridy = 4;
		componentLayout.setConstraints(this.clear, c);
		this.contentPanel.add(this.clear);
	}

	/**
	 *增加监听事件 
	 */
   private void addButtonListener() {
	try {
		jdialog=this;

		this.forceMain.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				
			}
		});
		this.confirmButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ptnConfirm();
				jdialog.dispose();
			}

			@Override
			public boolean checking() {
			
				return true;
			}
		});
		this.cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jdialog.dispose();
			}
		});
		this.clearWTR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				jdialog.dispose();
			}
		});
		
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	}
	}
   public void ptnConfirm(){
	   
	   DispatchUtil clockSourceDispatch = null;
	   ClockSource clockSource=null;
	   try{
		   clockSource= tabPanelTwoCX.getSelect();
		   //系统时钟
		   if(clockSource.getClockType()==UiUtil.getCodeByValue("clockType", "0").getId()){
			   if(this.forceMain.isSelected()){
				   clockSource.setScsforce("true");
				   clockSource.setScslockout("false");
				   clockSource.setScsmanual("false");
				   clockSource.setExternalOrder("force");
			   }
			   if(this.manpower.isSelected()){
				   clockSource.setScsforce("false");
				   clockSource.setScslockout("false");
				   clockSource.setScsmanual("true");
				   clockSource.setExternalOrder("manpower");
			   }
			   if(this.chkShutting.isSelected()){
				   clockSource.setScsforce("false");
				   clockSource.setScslockout("true");
				   clockSource.setScsmanual("false");
				   clockSource.setExternalOrder("lock");
			   }
			   if(this.clear.isSelected()){
				   clockSource.setScsforce("false");
				   clockSource.setScslockout("false");
				   clockSource.setScsmanual("false");
				   clockSource.setExternalOrder("");
			   }
		   }
		   //导出时钟
		   if(clockSource.getClockType()==UiUtil.getCodeByValue("clockType", "1").getId()){
			   if(this.forceMain.isSelected()){
				   clockSource.setEcsforce("true");
				   clockSource.setEcslockout("false");
				   clockSource.setEcsmanual("false");
				   clockSource.setExternalOrder("force");
			   }
			   if(this.manpower.isSelected()){
				   clockSource.setEcsforce("false");
				   clockSource.setEcslockout("false");
				   clockSource.setEcsmanual("true");
				   clockSource.setExternalOrder("manpower");
			   }
			   if(this.chkShutting.isSelected()){
				   clockSource.setEcsforce("false");
				   clockSource.setEcslockout("true");
				   clockSource.setEcsmanual("false");
				   clockSource.setExternalOrder("lock");
			   }
			   if(this.clear.isSelected()){
				   clockSource.setEcsforce("false");
				   clockSource.setEcslockout("false");
				   clockSource.setEcsmanual("false");
			   }
		   }		   
		 //  ClockSourceDispatch c=new ClockSourceDispatch();
			clockSourceDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKSOURCE);
			String result=clockSourceDispatch.excuteUpdate(clockSource);
			
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				confirmButton.setResult(1);
			}else{
				confirmButton.setResult(2);
			}
			this.confirmButton.setOperateKey(EOperationLogType.FREQUENCYUPDATE.getValue());
			DialogBoxUtil.succeedDialog(this, result);
		    //掩藏界面
		//	this.dispose();
			//刷新组界面
			tabPanelTwoCX.getController().refresh();
	   }catch(Exception e){
		   ExceptionManage.dispose(e,this.getClass());
	   }finally{
		   clockSourceDispatch = null;
		   clockSource=null;
	   }
   }

/**
 * 设置布局
 * 
 */
	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints=new GridBagConstraints();
			layout.columnWidths = new int[] { 140};
			layout.columnWeights = new double[] {0.3};
			layout.rowHeights = new int[] { 10,50};
			layout.rowWeights = new double[] { 0, 0};
			gridBagConstraints.insets = new Insets(15, 10, 0, 15); 
			gridBagConstraints.fill = GridBagConstraints.BOTH; 
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			layout.setConstraints(contentPanel, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			layout.setConstraints(buttonPanel, gridBagConstraints);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	/**
	 * 设置按钮的布局
	 */
	private void setStlot2Layout() {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridLayout = null;
		try {
			gridLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			buttonPanel.setLayout(gridLayout);
			
			gridLayout.columnWidths=new int[]{30,60,20,20};
			gridLayout.columnWeights=new double[]{0,0.2,0,0};
			gridLayout.rowHeights=new int[]{21};
			gridLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,10,5,0);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(clearWTR, gridBagConstraints);
			buttonPanel.add(clearWTR);
			
			gridBagConstraints.insets = new Insets(5,10,5,0);
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(confirmButton, gridBagConstraints);
			buttonPanel.add(confirmButton);
			
			gridBagConstraints.insets =new Insets(5,10,5,0);
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(cancleButton, gridBagConstraints);
			buttonPanel.add(cancleButton);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


}
