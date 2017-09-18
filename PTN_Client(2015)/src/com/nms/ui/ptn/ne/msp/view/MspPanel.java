package com.nms.ui.ptn.ne.msp.view;

import java.awt.Dimension;
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

import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.msp.controller.MspController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * MSP保护界面
 * 
 * @author kk
 * 
 */
public class MspPanel extends ContentView<MspProtect> {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane; // 选项卡控件
	private JSplitPane splitPane; // 上下分割控件
	private MspPortPanel mspPortPanel = null; // msp端口信息panel

	/**
	 * 创建一个新实例
	 */
	public MspPanel() {
		super("mspTable",RootFactory.CORE_MANAGE);
		try {
			this.initComponent();
			this.setLayout();
			this.addListention();
			//刷新列表
			this.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
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
					getMspPortPanel().clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		super.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_MSP_MANAGE)));
		this.tabbedPane = new JTabbedPane();
		this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		this.splitPane.setDividerLocation(high - 65);
		this.splitPane.setTopComponent(super.getContentPanel());
		this.splitPane.setBottomComponent(tabbedPane);

		this.mspPortPanel = new MspPortPanel();
		this.tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), this.mspPortPanel);
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
		panelLayout.setConstraints(this.splitPane, c);
		this.add(this.splitPane);
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
	 * 设置倒换按钮
	 * 
	 * @return
	 */
	private JButton getRotateButton() {
		JButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ROTATE),RootFactory.CORE_MANAGE);

		// 倒换按钮事件
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mspProtectRotate();
			}
		});

		return jButton;
	}
	/**
	 * 倒换
	 */
	private void mspProtectRotate() {
		//倒换只能选择一条数据
		if (this.getAllSelect().size() != 1) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			return;
		}
		MspProtect mspProtect = this.getSelect();
		new MspProtectRotateDialog(mspProtect,this);
	}
	/**
	 * 设置大小
	 */
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	/**
	 * 给此界面添加控制类
	 */
	@Override
	public void setController() {
		super.controller = new MspController(this);
	}

	public MspPortPanel getMspPortPanel() {
		return mspPortPanel;
	}
}
