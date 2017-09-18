package com.nms.ui.ptn.ne.loopProtRotate.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.dialog.loopProtect.LoopProRotateDialog;
import com.nms.ui.ptn.ne.loopProtRotate.controller.LoopProtRotateNodeController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * @author guoqc
 */
public class LoopProtRotateNodePanel extends ContentView<LoopProtectInfo> {
	private static final long serialVersionUID = 6809391197628746161L;
	private LoopProtRotateNodePanel view;
	
	public LoopProtRotateNodePanel() {
		super("loopProtect", RootFactory.CORE_MANAGE);
		this.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_LOOPPROTECT)));
		this.setLayout();
		try {
			this.view = this;
			super.controller.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 界面布局
	 */
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getFiterZero());
		needRemoveButtons.add(getUpdateButton());
//		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needAddButtons = new ArrayList<JButton>();
		needAddButtons.add(this.getRotateButton());
		return needAddButtons;
	}
	
	/**
	 * 设置倒换按钮
	 * @Exception 异常对象
	 */
	private JButton getRotateButton() {
		JButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ROTATE),RootFactory.CORE_MANAGE);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					rotateButtonListener();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		return jButton;
	}
	
	private void rotateButtonListener() {
		if (super.getAllSelect().size() != 1) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			return;
		}
		new LoopProRotateDialog(true, super.getSelect(), 1);
	}

	@Override
	public void setController() {
		super.controller = new LoopProtRotateNodeController(this);
	}

	public LoopProtRotateNodePanel getView() {
		return view;
	}
}