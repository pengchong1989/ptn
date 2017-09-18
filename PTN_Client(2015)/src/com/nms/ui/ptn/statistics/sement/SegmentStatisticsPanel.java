package com.nms.ui.ptn.statistics.sement;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import com.nms.db.bean.path.Segment;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class SegmentStatisticsPanel extends ContentView<Segment>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SegmentStatisticsPanel() {
		super("segmentinfoTable",RootFactory.COUNTMODU);
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

    public void init() throws Exception {
    	getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTNFO)));
		setLayout();
		this.getController().refresh();
		}
	
	public void setLayout(){
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
	public void setController() {
		controller = new SegmentStatisticsController(this);
	}
	

}