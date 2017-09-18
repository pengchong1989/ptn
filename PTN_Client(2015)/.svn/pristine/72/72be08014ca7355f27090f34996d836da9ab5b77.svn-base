package com.nms.ui.ptn.help;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.nms.db.bean.system.user.UserInst;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
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
	private JButton confirm = null;
	private final String imagePath = "/com/nms/ui/images/ptn_login_top.png";
	private GridBagLayout gridBagLayout = null;
	private int height = 0;
	private int weight = 0;
	private int lablHeight = 0;
	private JDialog jDialog = null;
	private String textPassWord;

	public HandLockDialog() {
		init();
	}

	/**
	 *初始化
	 */
	private void init() {
		Image image = null;
		Dimension screenSize = null;
		try {
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
			confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
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
			setGridBagLayout();/* 主窗口布局 */
			this.setLayout(gridBagLayout);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setSize(image.getWidth(jLabel1), (int) screenSize.getHeight() / 2);
			height = (int) screenSize.getHeight() / 2;
			weight = image.getWidth(jLabel1);
			lablHeight = image.getHeight(jLabel1);
			this.setResizable(false);
			setValue();
			addButtonListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void addButtonListener() {
		try {
			jDialog = this;
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
		try {
			if (passWord.getText().trim().length() > 0) {
				if (passWord.getText().trim().equals(textPassWord)) {
					jDialog.dispose();
				} else {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE));
				}
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PASSWORDERRORANAGE));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void setValue() {
		UserInstServiece_Mb userInstServiece = null;
		UserInst user = null;
		List<UserInst> list = null;
		try {
			list = new ArrayList<UserInst>();
			user = new UserInst();
			userInstServiece = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);
			list = userInstServiece.select(user);
			if (list != null && list.size() > 0) {
				user = list.get(0);
				userName.setText(user.getUser_Name());
				textPassWord = user.getUser_Pass();
				userName.setEditable(false);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(userInstServiece);
		}
	}

	private void initBorderLayout() {
		GridBagLayout gridBagLayouts = null;
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagLayouts = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();

			jPanel.setLayout(gridBagLayouts);
			gridBagLayouts.columnWidths = new int[] { 50, 200 };
			gridBagLayouts.columnWeights = new double[] { 0, 1, 0, 1 };
			gridBagLayouts.rowHeights = new int[] { 20, 20, 20 };
			gridBagLayouts.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayouts.setConstraints(jLabel4user, gridBagConstraints);
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayouts.setConstraints(userName, gridBagConstraints);
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayouts.setConstraints(jLabelPassword, gridBagConstraints);
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayouts.setConstraints(passWord, gridBagConstraints);
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
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
			gridBagLayout.rowHeights = new int[] { lablHeight, 30, 30, 60 };
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.NORTH;
			gridBagLayout.setConstraints(jLabel1, gridBagConstraints);

			gridBagConstraints.fill = GridBagConstraints.BOTH;

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(jLabel2, gridBagConstraints);
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
}
