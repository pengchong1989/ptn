package com.nms.ui.ptn.safety.roleManage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.model.util.RoleRootPanel;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.controller.RoleInfoController;

/**
 * 角色显示界面（左）
 * 
 * @author Administrator
 * 
 */
public class RoleInfoLeftPanel extends ContentView<RoleInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleRootPanel rightPanel; // 右边树panel

	public RoleInfoLeftPanel(RoleRootPanel rightPanel) {
		super("RoInfoTable",RootFactory.SATYMODU);
		try {
			this.rightPanel = rightPanel;
			init();
			this.addAction();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void setController() {
		super.controller = new RoleInfoController(this);
	}

	/**
	 * 添加监听
	 */
	private void addAction() {
		super.getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					// 清除详细面板数据
					getRightPanel().clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
	}

	private void init() throws Exception {

		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_ROLE_LIST)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());

	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}

	public RoleRootPanel getRightPanel() {
		return rightPanel;
	}

}
