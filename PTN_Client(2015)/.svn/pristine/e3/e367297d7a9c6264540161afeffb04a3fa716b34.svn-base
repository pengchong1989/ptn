package com.nms.ui.ptn.statistics.pathstatics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import com.nms.db.bean.report.SSPath;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 端到端路径数量统计界面
 * @author xuxx
 */
public class PathNumStatisticsPanel extends ContentView<SSPath> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PathNumStatisticsPanel() {
		super("PathNumStatisticsPanel", RootFactory.COUNTMODU);
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 界面初始化
	 */
	 public void init() throws Exception {
	    getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_PATHNUM)));
		setLayout();
		this.getController().refresh();
	}
	 /**
	  * 界面布局
	  */
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
		controller = new PathNumStatisticsController(this);
	}
	
}
