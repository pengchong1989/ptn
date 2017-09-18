package com.nms.ui.ptn.statistics.site;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 网元配置信息统计的界面
 * @author sy
 *
 */
public class SiteStatisticsPanel extends ContentView<SiteInst>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int fileId = 0;
	public SiteStatisticsPanel(int fileId) {
		super("siteinfoTable",RootFactory.COUNTMODU);
		try {
			this.fileId = fileId;
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
    public void init() throws Exception {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_SITEINFO)));
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
		controller = new SiteStatisticsPanelController(this);
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

}
