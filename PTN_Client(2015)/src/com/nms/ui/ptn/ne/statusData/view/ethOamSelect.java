package com.nms.ui.ptn.ne.statusData.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.ne.statusData.view.ethLinkOam.EthLinkOamPanel;
import com.nms.ui.ptn.ne.statusData.view.ethLinkOamStatus.view.EthLinkOamStatusPanel;
import com.nms.ui.ptn.ne.statusData.view.ethLinkoamMEP.View.EthLinkOamMEPPanel;

public class ethOamSelect extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472418882275304164L;
	
	private JTabbedPane tabbedPane ; 
	
	private GridBagLayout gridBagLayout ; 
	
	private EthLinkOamStatusPanel ethLinkOamStatusPanel;
	
	private EthLinkOamMEPPanel ethLinkOamMEPPanel;
	private EthLinkOamPanel EthLinkOamPanel ;
	
	public ethOamSelect(){
		init();
	}

	private void init() {
		tabbedPane = new JTabbedPane();
		gridBagLayout = new GridBagLayout();
		ethLinkOamStatusPanel = new EthLinkOamStatusPanel();
		ethLinkOamMEPPanel = new EthLinkOamMEPPanel();
		EthLinkOamPanel = new EthLinkOamPanel();
		
		tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_OAM_QUERY),EthLinkOamPanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_OAMLINKSTATUS),ethLinkOamStatusPanel);
		tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.ETHOAMMEPINFO),ethLinkOamMEPPanel);
		//设置主页布局
		setGridBagLayout();
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_OAM_QUERY)));
		this.setLayout(gridBagLayout);
		this.add(tabbedPane);
	}

	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(0, 10, 0,10);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(tabbedPane, gridBagConstraints);
		
	}

}
