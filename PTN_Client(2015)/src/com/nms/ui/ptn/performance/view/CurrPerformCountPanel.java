package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.nms.db.bean.perform.CurrPerformCountInfo;
import com.nms.ui.Ptnf;
import com.nms.ui.frame.ContentView;
import com.nms.ui.ptn.performance.controller.CurrPerformCountController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 实时性能统计面板
 * guoqc
 */
public class CurrPerformCountPanel extends ContentView<CurrPerformCountInfo> {
	private static final long serialVersionUID = -7047330173179201099L;
	private CurrPerformCountThread thread = null;
	
	public CurrPerformCountPanel() {
		super("currPerCountTable", RootFactory.PROFOR_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder("实时性能统计"));
		setLayout();
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getInportButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getClearFilterButton());
		needRemoveButtons.add(getFiterZero());
		return needRemoveButtons;
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	
	@Override
	public void setController() {
		controller = new CurrPerformCountController(this);
	}
	
	public void stopMinotoring(CurrPerformCountThread thread) {
		this.thread = thread;
		JTabbedPane jtab = Ptnf.getPtnf().getjTabbedPane1();
		for (int i = 0; i < jtab.getTabCount(); i++) {
			JPanel jPanel2 = (JPanel) jtab.getTabComponentAt(i);
			for (int j = 0; j < jPanel2.getComponentCount(); j++) {
				if (jPanel2.getComponents()[j] instanceof JButton) {
					JButton closeBtn = (JButton) jPanel2.getComponents()[j];
					closeBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							CurrPerformCountThread thread = ((CurrPerformCountController)controller).getDialog().getThread();
							if(thread != null){
								thread.stopThread();
							}
						}
					});
				}
			}
		}
	}

	public CurrPerformCountThread getThread() {
		return thread;
	}
}
