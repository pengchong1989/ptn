package com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.contriller.BatchUpgradeVersionsDialogController;


public class BatchUpgradeVersionsDialog extends PtnDialog {

	private static final long serialVersionUID = 1320343121147368431L;
	private JLabel chooseFileLabel;
	private JTextField chooseFileTextField;	
	private JButton open;
	private JButton confirm;
	private JButton cancel;
	private BatchUpgradeVersionsDialogController controller;
	private JFileChooser fileChooser;
	public  int total = 100;
	public  int current = 0;
	private List<UpgradeManage> upgradeManages;
	private BatchSoftwareUpdateRightPanle upgradeManagePanel;
	private int type;
	public BatchUpgradeVersionsDialog(BatchSoftwareUpdateRightPanle upgradeManagePanel,int type) {
		this.upgradeManagePanel = upgradeManagePanel;
		this.type = type;
		this.setModal(true);
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_VERSIONS_UPGRADE));
		initComponent();
		setLayout();
		controller = new BatchUpgradeVersionsDialogController(this);
		addActionListener();
	}

	private void addActionListener() {
		open.addActionListener(controller);
//		cancel.addActionListener(controller);
		confirm.addActionListener(controller);
	}

	private void initComponent() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);   
		chooseFileLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_FILE));
		chooseFileTextField = new JTextField(50);
		chooseFileTextField.setEditable(false);		
		open = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_OPEN));
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		fileChooser = new JFileChooser();
	}

	private void setLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 30, 20 };
		layout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		layout.rowHeights = new int[] { 20, 10, 20};
		layout.rowWeights = new double[] { 0, 0, 0};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(25, 10, 10, 10);
		layout.setConstraints(chooseFileLabel, c);
		this.add(chooseFileLabel);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(25, 10, 10, 10);
		layout.setConstraints(chooseFileTextField, c);
		this.add(chooseFileTextField);
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(25, 0, 10, 10);
		layout.setConstraints(open, c);
		this.add(open);				
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 10, 10, 10);
		c.fill = GridBagConstraints.EAST;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(confirm, c);
		this.add(confirm);
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 10, 10, 10);
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		layout.setConstraints(cancel, c);
		this.add(cancel);
	}

	public JTextField getChooseFileTextField() {
		return chooseFileTextField;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public JButton getConfirm() {
		return confirm;
	}

	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}

	
	public List<UpgradeManage> getUpgradeManages() {
		return upgradeManages;
	}

	public void setUpgradeManages(List<UpgradeManage> upgradeManages) {
		this.upgradeManages = upgradeManages;
	}


	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

	public JButton getOpen() {
		return open;
	}

	public void setOpen(JButton open) {
		this.open = open;
	}

	public BatchSoftwareUpdateRightPanle getBatchSoftwareUpgradeRightPanel() {
		return upgradeManagePanel;
	}

	public void setBatchSoftwareUpgradeRightPanel(BatchSoftwareUpdateRightPanle upgradeManagePanel) {
		this.upgradeManagePanel = upgradeManagePanel;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
 


}
