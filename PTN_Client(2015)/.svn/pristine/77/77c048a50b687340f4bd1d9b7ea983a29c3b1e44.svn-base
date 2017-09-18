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

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
/**
 * 选择接口对话框
 * @author Administrator
 *
 */
public class ChooseE1Dialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600003477536168014L;
	private final TDataBox treebox = new TDataBox();
	private TTree tree;
	private JScrollPane jScrollPane;
	private JSplitPane jsplitPane;
	
	private JPanel centerPanel;
	private JPanel jcheJPanel;
	private JPanel leftPanel;
	private JPanel btnPanel;
	private final String type;
	private JButton okBtn;
	private JButton cancelBtn;
	
	private final AddCesDialog addCesDialog;

	public ChooseE1Dialog(JDialog parent, boolean modal , String type) {
		super();
		this.setModal(modal);	
		this.type = type;
		this.addCesDialog = (AddCesDialog) parent;
        initComponet();
	}

	private void initComponet() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tree = new TTree(treebox);
		tree.setRootVisible(false);
		jScrollPane = new JScrollPane(tree);
		centerPanel = new JPanel(new BorderLayout());
		jcheJPanel = new JPanel();
		leftPanel = new JPanel(new BorderLayout());
		btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(400, 80));
		okBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		setLayout();
	}

	private void setLayout() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		btnPanel.setLayout(fl);
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		
		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.add(jcheJPanel , BorderLayout.CENTER);
		
		leftPanel.add(jScrollPane , BorderLayout.CENTER);
		jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
		jsplitPane.setDividerLocation(150);

		this.setLayout(new BorderLayout());
		this.add(btnPanel, BorderLayout.SOUTH);
		this.add(jsplitPane , BorderLayout.CENTER);
	}
	
	public String getType() {
		return type;
	}

	public AddCesDialog getAddCesDialog() {
		return addCesDialog;
	}
	
	public TDataBox getTreebox() {
		return treebox;
	}

	public JButton getOkBtn() {
		return okBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	
	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public JPanel getJcheJPanel() {
		return jcheJPanel;
	}

}
