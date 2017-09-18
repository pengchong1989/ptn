/*
 * ChooseTimeSlotDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.business.dialog.cespath;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import twaver.TDataBox;
import twaver.tree.TTree;

import com.nms.model.util.ServiceFactory;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;

/**
 *
 * @author  __USER__
 */
public class ChooseTimeSlotDialog extends PtnDialog {

	private static final long serialVersionUID = 6879418502176877294L;

	private final TDataBox treebox = new TDataBox();
	private TTree tree;
	private JSplitPane jsplitPane;
	private JPanel leftPanel;
	private JScrollPane jScrollPane;
	private JPanel centerPanel;
	private JPanel jcheJPanel;
	private JButton okBtn;
	private JButton cancelBtn;
	private JPanel btnJPanel;

	private String type;
	private AddCesDialog addCesDialog;

	public ChooseTimeSlotDialog(JDialog parent, boolean modal, String type) {
		super();
		this.setModal(modal);
		this.type = type;
		this.addCesDialog = (AddCesDialog) parent;
		init();
	}

	public ChooseTimeSlotDialog() {
		this.setModal(true);
		init();
	}

	public ChooseTimeSlotDialog(String type) {
		this.setModal(true);
		this.type = type;
		init();
	}

	private void init() {
		initComponents();
	}

	public void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tree = new TTree(treebox);
		tree.setRootVisible(false);
		leftPanel = new JPanel(new BorderLayout());
		jScrollPane = new JScrollPane(tree);
		jcheJPanel = new JPanel();
		centerPanel = new JPanel(new BorderLayout());
		leftPanel.add(jScrollPane, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.add(jcheJPanel, BorderLayout.CENTER);
		jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
		jsplitPane.setDividerLocation(150);
		okBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		btnJPanel = new JPanel();
		btnJPanel.setPreferredSize(new Dimension(400, 80));
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		btnJPanel.setLayout(fl);
		btnJPanel.add(okBtn);
		btnJPanel.add(cancelBtn);
		this.setLayout(new BorderLayout());
		this.add(jsplitPane, BorderLayout.CENTER);
		this.add(btnJPanel, BorderLayout.SOUTH);

	}
	
	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public static void main(String args[]) throws Exception {
		ServiceFactory serviceFactory = new ServiceFactory();
//		Properties properties = new Properties();
//		properties.put(ServiceFactory.HOSTNAME, "192.168.200.212");
//		properties.put(ServiceFactory.PTNUSER, "admin");
//		serviceFactory.startup(properties);
		ConstantUtil.serviceFactory = serviceFactory;
		ChooseTimeSlotDialog dialog = new ChooseTimeSlotDialog();
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
		dialog.setSize(new Dimension(700, 480));
		dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
		dialog.setVisible(true);

	}

	public TTree getTree() {
		return tree;
	}

	public TDataBox getTreebox() {
		return treebox;
	}

	public String getType() {
		return type;
	}

	public AddCesDialog getAddCesDialog() {
		return addCesDialog;
	}

	public JPanel getJcheJPanel() {
		return jcheJPanel;
	}

	public JButton getOkBtn() {
		return okBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}
}