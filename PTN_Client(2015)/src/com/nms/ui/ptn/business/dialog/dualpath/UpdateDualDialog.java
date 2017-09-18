package com.nms.ui.ptn.business.dialog.dualpath;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.eth.VplsInfo;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.dual.DualBusinessPanel;
import com.nms.ui.ptn.ne.dualManagement.view.DualPanel;

/**
 * @author Administrator
 * 
 */
public class UpdateDualDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel namelabel;
	private PtnTextField nametext;
	private JLabel lblwaitTime;
	private JLabel lbldelayTime;
	private PtnSpinner txtWaitTime;
	private PtnTextField txtDelayTime;
	private JCheckBox active;
	private JCheckBox isBack;
	private JLabel lblMessage;
	private PtnButton confirmButton;//保存
	private JButton cancleButton;//取消
	private JPanel buttonPanel;
	private List<DualInfo> dualInfos;
	private DualBusinessPanel dualBusinessPanel;
	private DualPanel dualPanel;
	private VplsInfo dualInfo_Log;//log日志显示使用

	public UpdateDualDialog(List<DualInfo> dualInfos,DualBusinessPanel dualBusinessPanel) throws Exception {
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_DUAL));
		this.dualBusinessPanel =  dualBusinessPanel;
		this.dualInfos = dualInfos;
		initComponents();
		setLayout();
		setValue(this.dualInfos);
		this.getDualBefore();
	}
	
	public UpdateDualDialog(List<DualInfo> dualInfos,DualPanel dualPanel) throws Exception {
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_DUAL));
		this.dualPanel = dualPanel;
		this.dualInfos = dualInfos;
		initComponents();
		setLayout();
		setValue(this.dualInfos);
		this.getDualBefore();
	} 

	private void initComponents() throws Exception {
		this.lblMessage = new JLabel();
		confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		namelabel = new javax.swing.JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.lblwaitTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME));
		this.lbldelayTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAY_TIME));
		txtDelayTime = new PtnTextField(true, PtnTextField.TYPE_INT, 255, this.lblMessage,confirmButton , this);
		txtDelayTime.setCheckingMaxValue(true);
		txtDelayTime.setCheckingMinValue(true);
		txtDelayTime.setMaxValue(255);
		txtDelayTime.setMinValue(0);
		txtDelayTime.setText("0");
		this.txtWaitTime=new PtnSpinner(12,1,1,ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME));
		this.active = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		this.isBack = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_BACK));
		buttonPanel = new JPanel();
		cancleButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		nametext = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.confirmButton, this);
		confirmButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				try {
					confrimAction();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
	}

	/**
	 * 界面布局设置
	 */
	public void setLayout(){
		this.setButtonLayout();
		GridBagLayout layout=new GridBagLayout(); 
		layout.columnWidths=new int[]{20,120};
		layout.columnWeights=new double[]{0,0.1};
		layout.rowHeights=new int[]{30};
		layout.rowWeights=new double[]{0};
		this.setLayout(layout);
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(10,8,8,10);
		//1 pw名称
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		layout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);
		
		//1 pw名称
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.namelabel, c);
		this.add(this.namelabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.nametext, c);
		this.add(this.nametext);
		
		//2、拖延时间
		c.insets=new Insets(5,8,8,10);
		c.gridx=0;
		c.gridy=2;
		layout.setConstraints(this.lbldelayTime, c);
		this.add(this.lbldelayTime);
		c.gridx=1;
		layout.setConstraints(this.txtDelayTime, c);
		this.add(this.txtDelayTime);
		
		//3、返回状态
		c.gridx=0;
		c.gridy=3;
		layout.setConstraints(this.isBack, c);
		this.add(this.isBack);
		
		//4、激活状态
		c.gridx=0;
		c.gridy=4;
		layout.setConstraints(this.active, c);
		this.add(this.active);
		
		c.insets=new Insets(5,8,5,10);
		c.gridx=0;
		c.gridy=5;
		c.gridwidth=10;
		layout.setConstraints(this.buttonPanel, c);
		this.add(this.buttonPanel);
	}
	


	/**
	 * 保存，取消按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout layout=new GridBagLayout(); 
		layout.columnWidths=new int[]{20,20,20,20};
		layout.columnWeights=new double[]{0,0,0,0};
		layout.rowHeights=new int[]{20};
		layout.rowWeights=new double[]{0.1};
		this.buttonPanel.setLayout(layout);
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//1 清除过滤按钮
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight=1;
		c.gridwidth=1;
		c.insets=new Insets(5,5,0,10);
		layout.setConstraints(this.cancleButton, c);
		buttonPanel.add(this.cancleButton);
		c.gridx=4;
		layout.setConstraints(this.confirmButton, c);
		buttonPanel.add(this.confirmButton);
	}
	
	private void setValue(List<DualInfo> dualInfos) {
		for (int i = 0; i < dualInfos.size(); i++) {
			nametext.setText(dualInfos.get(i).getName());
			active.setSelected(dualInfos.get(i).getActiveStatus()== 1?true:false);
			if(dualInfos.get(i).getPwProtect() != null){
				txtDelayTime.setText(dualInfos.get(i).getPwProtect().getDelayTime()+"");
				isBack.setSelected(dualInfos.get(i).getPwProtect().getBackType()== 0?true:false);
				return;
			}
		}
		
		
	}

	public void confrimAction() throws Exception {
		for (int i = 0; i < dualInfos.size(); i++) {
			if(dualInfos.get(i).getPwProtect() != null){
				dualInfos.get(i).getPwProtect().setBackType(isBack.isSelected()?0:1);
				dualInfos.get(i).getPwProtect().setDelayTime(Integer.parseInt(txtDelayTime.getText()));
			}
			dualInfos.get(i).setActiveStatus(active.isSelected() ? 1 : 2);
			
			// 验证名称是否存在
			String beforeName = null;
			if (dualInfos.get(i).getId() != 0) {
				beforeName = this.dualInfos.get(i).getName();
			}
			VerifyNameUtil verifyNameUtil = new VerifyNameUtil();
			if (verifyNameUtil.verifyName(EServiceType.DUAL.getValue(), this.nametext.getText().trim(), beforeName)) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
				return;
			}
			dualInfos.get(i).setName(nametext.getText());
		}
		DispatchUtil wrappingDispatch = new DispatchUtil(RmiKeys.RMI_DUAL);
		String result = null;
		try {
			result = wrappingDispatch.excuteUpdate(dualInfos);
			DialogBoxUtil.succeedDialog(this, result);
			//添加日志记录
			VplsInfo vpls_Log = new VplsInfo();
			DualInfo dualBefore = new DualInfo();
			vpls_Log.setDualInfo(dualBefore);
			PwProtect pwProtect = new PwProtect();
			dualBefore.setPwProtect(pwProtect);
			vpls_Log.setVplsName(nametext.getText());
			vpls_Log.setActiveStatus(active.isSelected() ? 1 : 2);
			pwProtect.setDelayTime(Integer.parseInt(txtDelayTime.getText()));
			pwProtect.setBackType(isBack.isSelected()?1:0);
			dualBefore.setNode(null);
			if(dualBusinessPanel != null)
			{
				AddOperateLog.insertOperLog(confirmButton, EOperationLogType.DUALPROTECTUPDATE.getValue(), result, 
						this.dualInfo_Log, vpls_Log, this.dualInfos.get(0).getRootSite(), vpls_Log.getVplsName(), "dual");
				AddOperateLog.insertOperLog(confirmButton, EOperationLogType.DUALPROTECTUPDATE.getValue(), result, 
						this.dualInfo_Log, vpls_Log, this.dualInfos.get(0).getBranchMainSite()==0?this.dualInfos.get(1).getBranchMainSite():
							this.dualInfos.get(0).getBranchMainSite(), vpls_Log.getVplsName(), "dual");
				AddOperateLog.insertOperLog(confirmButton, EOperationLogType.DUALPROTECTUPDATE.getValue(), result, 
						this.dualInfo_Log, vpls_Log, this.dualInfos.get(0).getBranchProtectSite()==0?this.dualInfos.get(1).getBranchProtectSite():
							this.dualInfos.get(0).getBranchProtectSite(), vpls_Log.getVplsName(), "dual");
				this.dualBusinessPanel.getController().refresh();
			}
			else
			{
				AddOperateLog.insertOperLog(confirmButton, EOperationLogType.DUALPROTECTUPDATE.getValue(), result, 
						this.dualInfo_Log, vpls_Log, ConstantUtil.siteId, vpls_Log.getVplsName(), "dual");
				this.dualPanel.getController().refresh();
			}
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 保留修改前的数据，便于日志记录比较
	 */
	private void getDualBefore(){
		this.dualInfo_Log = new VplsInfo();
		DualInfo dualBefore = new DualInfo();
		dualBefore.setNode(null);
		this.dualInfo_Log.setDualInfo(dualBefore);
		PwProtect pwProtect = new PwProtect();
		dualBefore.setPwProtect(pwProtect);
		this.dualInfo_Log.setVplsName(this.dualInfos.get(0).getName());
		this.dualInfo_Log.setActiveStatus(this.dualInfos.get(0).getActiveStatus());
		if(this.dualInfos.get(0).getPwProtect() != null){
			pwProtect.setDelayTime(this.dualInfos.get(0).getPwProtect().getDelayTime());
			pwProtect.setBackType(this.dualInfos.get(0).getPwProtect().getBackType());
		}else{
			pwProtect.setDelayTime(this.dualInfos.get(1).getPwProtect().getDelayTime());
			pwProtect.setBackType(this.dualInfos.get(1).getPwProtect().getBackType());
		}
		pwProtect.setBackType(pwProtect.getBackType() == 1?0:1);
	}

	public JLabel getNamelabel() {
		return namelabel;
	}

	public void setNamelabel(JLabel namelabel) {
		this.namelabel = namelabel;
	}

	public PtnTextField getNametext() {
		return nametext;
	}

	public void setNametext(PtnTextField nametext) {
		this.nametext = nametext;
	}

	public JLabel getLblwaitTime() {
		return lblwaitTime;
	}

	public void setLblwaitTime(JLabel lblwaitTime) {
		this.lblwaitTime = lblwaitTime;
	}

	public JLabel getLbldelayTime() {
		return lbldelayTime;
	}

	public void setLbldelayTime(JLabel lbldelayTime) {
		this.lbldelayTime = lbldelayTime;
	}

	public PtnSpinner getTxtWaitTime() {
		return txtWaitTime;
	}

	public void setTxtWaitTime(PtnSpinner txtWaitTime) {
		this.txtWaitTime = txtWaitTime;
	}


	public PtnTextField getTxtDelayTime() {
		return txtDelayTime;
	}

	public void setTxtDelayTime(PtnTextField txtDelayTime) {
		this.txtDelayTime = txtDelayTime;
	}

	public JCheckBox getIsBack() {
		return isBack;
	}

	public void setIsBack(JCheckBox isBack) {
		this.isBack = isBack;
	}

}
