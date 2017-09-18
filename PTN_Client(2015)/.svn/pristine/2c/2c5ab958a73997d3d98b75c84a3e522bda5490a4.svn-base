package com.nms.ui.ptn.business.lsppath;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.ui.frame.ContentView;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class LspPathPanel extends ContentView<Lsp>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4900853630658691120L;
	
	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	public LspPathPanel() {
		super("lspPathTable",RootFactory.CORE_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder("查询结果"));
		initComponent();
		setLayout();
		setActionListention();
	}

	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					//清除详细面板数据
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> removeButton = new ArrayList<JButton>();
		removeButton.add(getAddButton());
		removeButton.add(getDeleteButton());
		removeButton.add(getUpdateButton());
		return removeButton;
	}
	
	private void initComponent() {
		tabbedPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		double high = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		splitPane.setDividerLocation(Double.valueOf(high).intValue() / 2 - 20);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);		
	}
	
	public void setTabbedPaneLayout() {
		tabbedPane.add("用户信息", new JPanel());
		tabbedPane.add("QoS信息", new JPanel());
		tabbedPane.add("OAM信息", new JPanel());
		tabbedPane.add("路由信息", new JPanel());
		tabbedPane.add("一致性", new JPanel());
		tabbedPane.add("图形化展示", new JPanel());
		tabbedPane.add("TOPO展示", new JPanel());
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

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	@Override
	public void setController() {
		controller = new LspPathController(this);
	}

	
}
