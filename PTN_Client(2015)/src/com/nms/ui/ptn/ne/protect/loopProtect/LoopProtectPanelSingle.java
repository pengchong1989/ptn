package com.nms.ui.ptn.ne.protect.loopProtect;

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

import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.protect.loopProtect.controller.LoopProtectPanelSingleController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 单网元环保护
 * @author dzy
 *
 */
public class LoopProtectPanelSingle  extends ContentView<LoopProtectInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7071781585669211756L;
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private LoopProtectPanelSingle LoopProtectPanelSingle;
	private LoopInfoPanel loopInfoPanel;
	public LoopProtectPanelSingle() {
		super("loopProtectSingle",RootFactory.CORE_MANAGE);
		init();
		LoopProtectPanelSingle = this;
		
	}
	
	public LoopProtectPanelSingle getLoopProtectPanelSingle() {
		return LoopProtectPanelSingle;
	}
	
	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_LOOPPROTECT)));
		initComponent();
		setLayout();
		setActionListention();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
		this.loopInfoPanel = new LoopInfoPanel();
		
	}
	
	public void setLayout() {
		setTabbedPaneLayout();
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
	public void setTabbedPaneLayout() {
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_CONNECT_CONFIG), loopInfoPanel);
	}
	
	
	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					// 清除详细面板数据
					loopInfoPanel.clear();
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}

	@Override
	public void setController() {
		controller = new LoopProtectPanelSingleController(this);
	}

	
	
	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public LoopInfoPanel getLoopInfoPanel() {
		return loopInfoPanel;
	}

	public void setLoopInfoPanel(LoopInfoPanel loopInfoPanel) {
		this.loopInfoPanel = loopInfoPanel;
	}


}
