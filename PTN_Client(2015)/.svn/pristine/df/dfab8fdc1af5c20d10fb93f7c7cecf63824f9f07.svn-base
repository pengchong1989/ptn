package com.nms.ui.ptn.systemconfig.Template.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.Template.controller.ExpMappingController;
import com.nms.ui.ptn.systemconfig.Template.view.dialog.EditeExpMappingDialog;

/**
 * EXP映射界面
 * 
 * @author dzy
 * 
 */
public class ExpMappingPanel extends ContentView<QosMappingMode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5684450077540804254L;

	/**
	 * 创建一个新实例
	 */
	public ExpMappingPanel() {
		super("expMapping",RootFactory.DEPLOY_MANAGE);
		try {
			this.initComponent();
			this.setLayout();
			this.addListention();
			//刷新列表
			this.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 添加监听
	 */
	private void addListention(){
		//添加table点击行事件
		super.getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					// 清除详细面板数据
					getExpToColorInfoPanel().clear();
					return;
				} else {
					try {
						expToColorInfoPanel=new ExpToColorInfoPanel(getSelect());
						scrollPanel.setViewportView(expToColorInfoPanel);
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}
			}
		});
	
	}
	
	/**
	 * 初始化控件
	 * @throws Exception 
	 */
	private void initComponent() throws Exception {
		super.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_COSTOEXPTEMPLATE)));
		this.tabbedPane = new JTabbedPane();
		this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setOneTouchExpandable(true);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		this.splitPane.setDividerLocation(high - 65);
		this.splitPane.setTopComponent(super.getContentPanel());
		this.splitPane.setBottomComponent(tabbedPane);
		this.expToColorInfoPanel = new ExpToColorInfoPanel(this.getSelect());
		this.scrollPanel=new JScrollPane();
		scrollPanel.setViewportView(expToColorInfoPanel);
		this.tabbedPane.add(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), this.scrollPanel);
	}

	/**
	 * 设置布局
	 */
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
		panelLayout.setConstraints(this.splitPane, c);
		this.add(this.splitPane);
	}

	/**
	 * 设置倒换按钮
	 * 
	 * @return
	 */
	private JButton btnDataDownLoad() {
		JButton jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_BATCH_DOWNLOAD),RootFactory.DEPLOY_MANAGE);

		// 倒换按钮事件
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnDataDownLoadActionPerformed();
			}
		});

		return jButton;
	}
	
	/**
	 * 下发模板
	 */
	private void btnDataDownLoadActionPerformed() {
		try {
			if(null==this.getSelect()){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
				return;
			}
			new EditeExpMappingDialog(this.getSelect(),this,ResourceUtil.srcStr(StringKeysBtn.BTN_BATCH_DOWNLOAD), UiUtil.getCodeById(((QosMappingMode)this.getSelect()).getQosMappingAttrList().get(0).getModel()).getId());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	/**
	 * 移除按钮
	 */
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}
	
	/**
	 * 添加按钮
	 */
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(this.btnDataDownLoad());
		return needRemoveButtons;

	}

	/**
	 * 设置大小
	 */
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

	/**
	 * 给此界面添加控制类
	 */
	@Override
	public void setController() {
		super.controller = new ExpMappingController(this);
	}

	public ExpToColorInfoPanel getExpToColorInfoPanel() {
		return expToColorInfoPanel;
	}

	private JTabbedPane tabbedPane; // 选项卡控件
	private JScrollPane scrollPanel; //列表面板
	private JSplitPane splitPane; // 上下分割控件
	private ExpToColorInfoPanel expToColorInfoPanel = null; // COS到EXP映射信息panel
}
