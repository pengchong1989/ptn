package com.nms.ui.filter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;

public abstract class FilterDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int windth = 350;
	private int height = 100;
	private Object object=null;

	public FilterDialog(Object object) {
		this.object=object;
		this.initComponentBase();
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		this.setLayout();
		this.addListener();
		this.loadData();
		UiUtil.showWindow(this, this.windth, this.height + this.getPanelChildHeight());
	}

	private void addListener() {
		// 取消按钮
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 确定按钮
		this.btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfirmListener();
			}
		});

		this.addListenerChild();
	}

	/**
	 * 设置panelChild高度
	 * 
	 * @return
	 */
	protected abstract int getPanelChildHeight();

	/**
	 * 设置panelChild的布局
	 * 
	 * @param panelChild
	 */
	protected abstract void setLayoutChild(JPanel panelChild);

	/**
	 * 加listen
	 */
	protected abstract void addListenerChild();

	/**
	 * 初始化子类的控件，需要子类实现
	 */
	protected abstract void initComponent();

	/**
	 * 确认按钮
	 */
	protected abstract void btnConfirmListener();

	/**
	 * 加载数据
	 */
	protected abstract void loadData();
	
	private void setLayout() {
		this.setLayoutBase();
		this.setLayoutChild(panelChild);
		this.setLayoutButton();

		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { this.windth };
		layout.columnWeights = new double[] { 1 };
		layout.rowHeights = new int[] { 50, this.getHeight(), 50 };
		layout.rowWeights = new double[] { 0.5, 0.5, 0 };

		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(this.panelBase, c);
		this.add(this.panelBase);

		c.gridy = 1;
		layout.setConstraints(this.panelChild, c);
		this.add(this.panelChild);

		c.gridy = 2;
		layout.setConstraints(this.panelButton, c);
		this.add(this.panelButton);

	}

	private void setLayoutButton() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 220, 80 };
		layout.columnWeights = new double[] { 0.1, 0 };
		layout.rowHeights = new int[] { 50 };
		layout.rowWeights = new double[] { 0 };

		this.panelButton.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		layout.setConstraints(this.btnConfirm, c);
		this.panelButton.add(this.btnConfirm);

		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(this.btnCanel, c);
		this.panelButton.add(this.btnCanel);
	}

	private void setLayoutBase() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 80, 220 };
		layout.columnWeights = new double[] { 0, 0.1 };
		layout.rowHeights = new int[] { 50 };
		layout.rowWeights = new double[] { 0.1 };

		this.panelBase.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;

		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(this.labelName, c);
		this.panelBase.add(this.labelName);

		c.gridx = 1;
		layout.setConstraints(this.txtName, c);
		this.panelBase.add(this.txtName);
	}

	/**
	 * 初始化基类的控件
	 */
	private void initComponentBase() {
		this.labelName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.txtName = new PtnTextField();
		this.txtName.setMaxLength(100);
		this.panelBase = new PtnPanel();
		this.panelChild = new PtnPanel();
		this.panelButton = new PtnPanel();
		this.btnConfirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		this.btnCanel = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.initComponent();
	}

	private JLabel labelName = null;
	private PtnTextField txtName = null;
	private PtnPanel panelBase = null;
	private PtnPanel panelChild = null;
	private PtnPanel panelButton = null;
	private PtnButton btnConfirm = null;
	private PtnButton btnCanel = null;

	public PtnTextField getTxtName() {
		return txtName;
	}
	
	public Object getObject(){
		return this.object;
	}
}
