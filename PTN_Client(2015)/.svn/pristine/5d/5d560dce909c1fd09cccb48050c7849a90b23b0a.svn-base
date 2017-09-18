package com.nms.ui.ptn.ne.dual.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.dual.controller.DualProtectController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 双规保护列表界面
 * 
 * @author kk
 * 
 */
public class DualProtectPanel extends ContentView<DualProtect> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -337602332552435028L;
	private DualTunnelPanel dualTunnelPanel; // tunnel列表panel

	/**
	 * 创建一个新的实例
	 */
	public DualProtectPanel() {
		super("dualProtect",RootFactory.CORE_MANAGE);
		try {
			this.initComponent();
			this.setLayout();
			this.addTab();
			addListention();
			this.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 给标签panel添加标签页
	 */
	private void addTab() {
		this.tabbedPane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_TUNNELINFO), this.dualTunnelPanel);
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		super.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_DUALPROTECT)));
		this.tabbedPane = new JTabbedPane();
		this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		this.splitPane.setDividerLocation(high - 65);
		this.splitPane.setTopComponent(super.getContentPanel());
		this.splitPane.setBottomComponent(tabbedPane);

		this.dualTunnelPanel = new DualTunnelPanel();
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
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
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	/**
	 * 移除按钮
	 */
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	/**
	 * 添加按钮
	 */
	@Override
	public List<JButton> setAddButtons() {

		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(this.getRotateButton());
		return needRemoveButtons;

	}
	/**
	 * 添加监听
	 */
	private void addListention(){
		//添加table点击行事件
		super.getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					// 清除详细面板数据
					
					tunnelPanelClear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}

		
		});
	}
	private void tunnelPanelClear() {
		this.dualTunnelPanel.clear();
		
	}
	/**
	 * 设置倒换按钮
	 * 
	 * @return
	 */
	private JButton getRotateButton() {
		PtnButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ROTATE),RootFactory.CORE_MANAGE);

		// 倒换按钮事件
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Rotate();
				
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		return jButton;
	}
	public void Rotate() {
		//倒换只能选择一条数据
		if (this.getAllSelect().size() != 1) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			return;
		}
		DualProtect dualProtect=this.getSelect();
		new DualProtectRotateDialog(dualProtect,this);
		
	}
	/**
	 * 设置控制器
	 */
	@Override
	public void setController() {
		super.controller = new DualProtectController(this);
	}

	private JSplitPane splitPane; // 分割panel
	private JTabbedPane tabbedPane; // 选项卡panel

	public DualTunnelPanel getDualTunnelPanel() {
		return dualTunnelPanel;
	}
}
