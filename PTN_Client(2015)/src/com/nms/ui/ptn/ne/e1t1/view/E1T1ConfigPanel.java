package com.nms.ui.ptn.ne.e1t1.view;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 当前性能面板
 * 
 * @author Administrator
 * 
 */
public class E1T1ConfigPanel extends ContentView<CurrentPerforInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2380268802007219880L;
	
	public E1T1ConfigPanel() {
		super("E1T1Table",RootFactory.PROFOR_MANAGE);
		init();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_CHANNEL_E1)));
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
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getFiterZero());
		needRemoveButtons.add(getFilterButton());
		needRemoveButtons.add(getClearFilterButton());
	    needRemoveButtons.add(getInportButton());
	    needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getInportButton());
		
		return needRemoveButtons;
	}

	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(this.getAdddButton());
		needRemoveButtons.add(this.getUpdateeButton());
		needRemoveButtons.add(this.getDeleteeButton());
		needRemoveButtons.add(this.getRefreshhButton());
		return needRemoveButtons;

	}
	
	
	
	//新建按钮
	private JButton getAdddButton() {
		
		PtnButton jButton = getAddButton();
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CHNNEL_ADD));
			}
		});
		
		return jButton;
	}
	//修改按钮
	private JButton getUpdateeButton() {
		
		PtnButton jButton = getUpdateButton();
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			}
		});
		
		return jButton;
	}
	//删除按钮
	private JButton getDeleteeButton() {
		
		PtnButton jButton = getDeleteButton();
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			}
		});
		
		return jButton;
	}
	//刷新按钮
	private JButton getRefreshhButton() {
		
		JButton jButton = getRefreshButton();
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		return jButton;
	}
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
	@Override
	public void setController() {
		// TODO Auto-generated method stub
		
	}
}
