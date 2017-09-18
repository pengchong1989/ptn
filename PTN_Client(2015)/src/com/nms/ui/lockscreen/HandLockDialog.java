package com.nms.ui.lockscreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.nms.db.bean.system.user.UserInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTip;

public class HandLockDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7512352924996291890L;

	private JPanel jPanel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4user = null;
	private JTextField userName = null;
	private JLabel jLabelPassword = null;
	private JPasswordField passWord = null;
	private PtnButton confirm = null;
	private String imagePath;
	private GridBagLayout gridBagLayout = null;
	private int height = 0;
	private int weight = 0;
	private int lablHeight = 0;
	private String textPassWord;
    private UserInst user = null;
    
	public HandLockDialog() {
		init();
	}

	private void init() {
		Image image = null;
		Dimension screenSize = null;
		try {
			imagePath=UiUtil.getIconImagePath();
			
			user = new UserInst();
			image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imagePath));
			gridBagLayout = new GridBagLayout();
			jLabel1 = new JLabel();
			jPanel = new JPanel(new BorderLayout());
			jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagePath)));
			jPanel = new JPanel();
			jLabel2 = new JLabel(ResourceUtil.srcStr(StringKeysBtn.ISNOWUSER));
			jLabel3 = new JLabel();
			jLabel3.setText(ResourceUtil.srcStr(StringKeysBtn.WRITERPASSWOR));
			jLabel4user = new JLabel(ResourceUtil.srcStr(StringKeysBtn.TEXTUSERNAME));
			userName = new JTextField();
			passWord = new JPasswordField();
			jLabelPassword = new JLabel(ResourceUtil.srcStr(StringKeysBtn.TEXTPASSWORD));
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
//			chb = new JCheckBox(ResourceUtil.srcStr(StringKeysTip.TIP_CLEAR_SITELOCK));
			this.setTitle(ResourceUtil.srcStr(StringKeysMenu.MENU_HONDLOCK));
			this.add(jLabel1);
			this.add(jLabel2);
			this.add(jLabel3);
			jPanel.add(jLabel4user);
			jPanel.add(userName);
			jPanel.add(passWord);
			jPanel.add(jLabelPassword);
			jPanel.add(confirm);
			this.add(jPanel);
			initBorderLayout();
			setGridBagLayout();
			this.setLayout(gridBagLayout);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setSize(image.getWidth(jLabel1), (int) (screenSize.getHeight() / 1.5));
			height = (int) screenSize.getHeight() / 2;
			weight = image.getWidth(jLabel1);
			lablHeight = image.getHeight(jLabel1);
			this.setResizable(false);
			setValue();
			addButtonListener();
			addActionListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void addActionListener() {
		this.passWord.addKeyListener(new KeyAdapter() {
			 @Override
			public void keyTyped(KeyEvent evt) {
				    if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
				    	btnSaveConfirm();
				    }

			}

		});
	}
	private void addButtonListener() {
		try {
			this.confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSaveConfirm();
				}
			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


	@SuppressWarnings("deprecation")
	private void btnSaveConfirm() {
		UserInst userother = null;
		UserInstServiece_Mb userInstServiece = null;
		List<UserInst> list = null;
		try {
			userInstServiece = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);
			list = new ArrayList<UserInst>();
			if (!userName.isEditable()) {
				if (passWord.getText().trim().length() > 0) {
					if (passWord.getText().trim().equals(textPassWord)) {
						this.dispose();
					} else {
						DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE));
						this.insertOpeLog(EOperationLogType.PASSWORDERROR.getValue(), ResultString.CONFIG_FAILED, null, null);	
					}
				} else {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE));
					this.insertOpeLog(EOperationLogType.PASSWORDERROR.getValue(), ResultString.CONFIG_FAILED, null, null);	
				}
			} else {
				userother = new UserInst();
				if (userName.getText().trim().length() > 0) {
					userother.setUser_Name(userName.getText().trim());
					list = userInstServiece.select(userother);
					if (list != null && list.size() > 0) {
						userother = list.get(0);
						if (passWord.getText().trim().equals(userother.getUser_Pass())) {
							this.dispose();
						} else {
							DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE));
							this.insertOpeLog(EOperationLogType.PASSWORDERROR.getValue(), ResultString.CONFIG_FAILED, null, null);	
						}
					}
				}else{
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.WRITEXTUSERNAME));
					this.insertOpeLog(EOperationLogType.PASSWORDERROR.getValue(), ResultString.CONFIG_FAILED, null, null);	
				}
			}
			this.insertOpeLog(EOperationLogType.HANDLOCK.getValue(), ResultString.CONFIG_SUCCESS, null, null);	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(userInstServiece);
		}

	}

	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(confirm, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysMenu.MENU_HONDLOCK),"");		
	}
	private void setValue() {
		try {
			user=ConstantUtil.user;
			userName.setText(user.getUser_Name());
			textPassWord = user.getUser_Pass();
			userName.setEditable(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initBorderLayout() {
		GridBagLayout gridBagLayouts = null;
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagLayouts = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();

			jPanel.setLayout(gridBagLayouts);
			gridBagLayouts.columnWidths = new int[] { 30,150,20};
			gridBagLayouts.columnWeights = new double[] { 0, 1, 0, 1 };
			gridBagLayouts.rowHeights = new int[] { 30, 30, 30 };
			gridBagLayouts.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayouts.setConstraints(jLabel4user, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayouts.setConstraints(userName, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayouts.setConstraints(jLabelPassword, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayouts.setConstraints(passWord, gridBagConstraints);

			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.fill=GridBagConstraints.NONE;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayouts.setConstraints(confirm, gridBagConstraints);
			jPanel.setLayout(gridBagLayouts);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { weight };
			gridBagLayout.columnWeights = new double[] { 0, 0 };
			gridBagLayout.rowHeights = new int[] { lablHeight, 40, 40, 30,12};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.NORTH;
			gridBagLayout.setConstraints(jLabel1, gridBagConstraints);

			gridBagConstraints.fill = GridBagConstraints.BOTH;
			 gridBagConstraints.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(jLabel2, gridBagConstraints);
			 gridBagConstraints.insets = new Insets(5, 10,5, 5);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(jLabel3, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(jPanel, gridBagConstraints);
			
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public JPasswordField getPassWord() {
		return passWord;
	}

	public void setPassWord(JPasswordField passWord) {
		this.passWord = passWord;
	}
	
}
