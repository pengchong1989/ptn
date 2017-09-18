package com.nms.ui.ptn.safety;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import twaver.table.TTable;
import twaver.table.TTablePopupMenuFactory;

import com.nms.db.bean.system.OperationLog;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.controller.OperationLogPanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 操作日志查询
 * @author Administrator
 *
 */
public class OperationLogPanel extends ContentView<OperationLog>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem showDataLogMenu;
	//实例化对象
	public OperationLogPanel(){
		super("operationLogTable",RootFactory.SATYMODU);
		init();
		
	}
	//初始化
	public void init(){	
		try{
			this.initComponent();
			this.setViewLayout();			
			setLayout();
			this.addActionListener();
			this.getController().refresh();
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}
			
	}
	
	private void initComponent() {
		this.showDataLogMenu = new JMenuItem("显示详细信息");
	}
	
	private void setViewLayout(){
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TT_OPERATION_SELECT_LOG)));
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		TTablePopupMenuFactory menuFactory = new TTablePopupMenuFactory() {
			@Override
			public JPopupMenu getPopupMenu(TTable ttable, MouseEvent evt) {
				if (SwingUtilities.isRightMouseButton(evt)) {
					int count = ttable.getSelectedRows().length;
					JPopupMenu menu = new JPopupMenu();
					OperationLog log = getSelect();
					if(count == 1 && log.getDataLogList().size() > 0){
						menu.add(showDataLogMenu);
						checkRoot(showDataLogMenu, RootFactory.CORE_MANAGE);
						menu.show(evt.getComponent(), evt.getX(), evt.getY());
						return menu;
					}
				}
				return null;
			}
		};
		super.setMenuFactory(menuFactory);
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	private void addActionListener() {
		this.showDataLogMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OperationLog log = getSelect();
				new ShowDataLogDialog(log);
			}
		});
	}
	
	@Override
	public void setController() {
		controller = new OperationLogPanelController(this);
	}
	//布局
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
				((OperationLogPanelController) controller).removeAction();
			}					
		});
		List<JButton> list=new ArrayList<JButton>();
		list.add(removeButton);
		return list;
	}

}
