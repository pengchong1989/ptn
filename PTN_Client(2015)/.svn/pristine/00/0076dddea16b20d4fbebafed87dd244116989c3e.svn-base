package com.nms.ui.ptn.safety;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import com.nms.db.bean.system.loginlog.LoginLog;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.controller.LogPanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 用户登陆日志显示Panel
 * @author Administrator
 *
 */
public class LoginLogPanel extends ContentView<LoginLog>{

	private static final long serialVersionUID = -4401494086417559560L;
	public LoginLogPanel(){
		super("loginLogTable",RootFactory.SATYMODU);
		init();
		
	}
	//初始化
	public void init(){	
		try{
			
			this.setViewLayout();
			
			setLayout();
			this.getController().refresh();
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}
			
	}
	
	private void setViewLayout(){
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TT_SELECT_LOG)));
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public void setController() {
		controller = new LogPanelController(this);
	}
	
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
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());
	}
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getFiterZero());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(this.getInportButton());
		needRemoveButtons.add(this.getFiterZero());
		return needRemoveButtons;
	}
	@Override
	public List<JButton> setAddButtons() {
		//添加移除 按钮
		final PtnButton removeButton=new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_REMOVE_DATA),false,RootFactory.SATY_MANAGE);
		removeButton.addActionListener(new MyActionListener(){

			@Override
			public boolean checking() {
				
				return true;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				//y移除事件，弹出时间选择框
				((LogPanelController) controller).removeAction();
			}
			
		
		});
		List<JButton> list=new ArrayList<JButton>();
		list.add(removeButton);
		return list;
	}

}
