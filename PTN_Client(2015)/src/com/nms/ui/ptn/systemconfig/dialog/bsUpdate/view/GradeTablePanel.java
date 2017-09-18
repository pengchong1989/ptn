package com.nms.ui.ptn.systemconfig.dialog.bsUpdate.view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.TableColumn;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.contriller.GradeController;


public class GradeTablePanel extends ContentView<SiteInst>{
	
	private static final long serialVersionUID = -4442083213455101239L;

	public GradeTablePanel() {
		super("SiteInst_Soft",RootFactory.CORE_MANAGE);				
		try {
			init();	
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	/**
	 * 动态刷新table数据
	 * 先刷新整个table看效果，可以优化为刷新table某行某列数据
	 * @param values
	 */
	public void refresh(List<SiteInst> siteInsts){
		this.clear();
		this.initData(siteInsts);
		this.updateUI();
		
	}
	
	private void init(){
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_RESULT)));
		initComponents();
		setLayout();
		TableColumn statusColumn = this.getTable().getTableModel().getColumnByName("schedule");
		statusColumn.setCellRenderer(new ProgressBarRenderer());
	}

	private void initComponents() {
	
	}

	private void setLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(350, 200);
	}
	@Override
	public void setController() {
		controller = new GradeController(this);
		
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getDeleteButton());
		return needRemoveButtons;
	}
}
