package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.PerformanceTaskFilter;

public class PerforTaskFilterDialog extends PtnDialog {

	private static final long serialVersionUID = 4936458179366907767L;
	private PtnButton confirm;
	private JButton cancel;
	private JButton clear;
	private JLabel lblTitle;
	private JLabel lblTaskObj;
	private JLabel lblCycle;
	private JRadioButton rb15min;
	private JRadioButton rb24hour;
	private ButtonGroup group;
	private String filterInfo;
	private JLabel lblObjectType;
	private JComboBox cbObjectType;
	private NeTreePanel neTreePanel = null; // 网元树panel
	public PerforTaskFilterDialog() {
		this.setModal(true);
		init();
	}

	private void init() {
		initComponents();
		setLayout();
		addListener();
	}

	/**
	 * 验证输入数据的正确性
	 * @return
	 */
	public boolean validateParams(){
		try {
			if (!this.neTreePanel.verifySelect()) {
			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_MONITORING_OBJ));
			return false;
		}
		}catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		} 
		return true;
	}
	
	// 获取当前对话框的过滤对象
	public boolean get(PerformanceTaskFilter filter) throws Exception {
		// 添加监控对象条件
		StringBuilder strBuilder = new StringBuilder();
		// 添加监控对象条件
		if (cbObjectType.getSelectedItem().equals(ResourceUtil.srcStr(StringKeysObj.NET_BASE))) {
			// 根据网元查询当前性能值
			List<Integer> siteIds = this.neTreePanel.getPrimaryKeyList("site");
			if(null == siteIds || siteIds.isEmpty())
			{
				return false;
			}
			filter.setSiteInsts(siteIds);
			filter.setObjectType(EObjectType.SITEINST);
			
			
		} else if (cbObjectType.getSelectedItem().equals(ResourceUtil.srcStr(StringKeysObj.BOARD))) {
			filter.setSlotInsts(this.neTreePanel.getPrimaryKeyList("slot"));
			filter.setObjectType(EObjectType.SLOTINST);
		}
		if(strBuilder.toString().contains(",")){
			strBuilder.replace(strBuilder.length() - 1, strBuilder.length(), ";");
		}
		if (rb24hour.isSelected()) {
			strBuilder.append(ResourceUtil.srcStr(StringKeysObj.MONITORING_PERIOD)).append("：");
			filter.setMonitorCycle(EMonitorCycle.HOUR24);
			strBuilder.append(EMonitorCycle.HOUR24.toString());
		} else if (rb15min.isSelected()) {
			filter.setMonitorCycle(EMonitorCycle.MIN15);
			strBuilder.append(ResourceUtil.srcStr(StringKeysObj.MONITORING_PERIOD)).append("：");
			strBuilder.append(EMonitorCycle.HOUR24.toString());
		} else {
			filter.setMonitorCycle(null);
		}
		this.setFilterInfo(strBuilder.toString());
		return true;
	}

	private void addListener() {

		// 取消按钮
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PerforTaskFilterDialog.this.dispose();
			}
		});

		// 清除按钮
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PerforTaskFilterDialog.this.clear();
			}
		});
		// 下拉列表的选项改变事件
		this.cbObjectType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					if (itemEvent.getItem().equals(ResourceUtil.srcStr(StringKeysObj.NET_BASE))) {
						neTreePanel.setLevel(2);
					} else {
						neTreePanel.setLevel(3);
					}
				}
			}
		});
	}
	// 清除面板信息
	private void clear() {
		this.neTreePanel.clear();
		group.clearSelection();
	}

	private void initComponents() {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		lblTitle = new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		lblTaskObj = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_OBJ));
		lblObjectType = new JLabel(ResourceUtil.srcStr(StringKeysObj.OBJ_TYPE));
		cbObjectType = new JComboBox();
		cbObjectType.addItem(ResourceUtil.srcStr(StringKeysObj.NET_BASE));
//		cbObjectType.addItem(ResourceUtil.srcStr(StringKeysObj.BOARD));
		this.neTreePanel=new NeTreePanel(false, 2, null,false);
		lblCycle = new JLabel(ResourceUtil.srcStr(StringKeysObj.RUN_PERIOD));
		group = new ButtonGroup();
		rb15min = new JRadioButton("15" + ResourceUtil.srcStr(StringKeysObj.MINUTES));
		rb24hour = new JRadioButton("24" + ResourceUtil.srcStr(StringKeysObj.HOURS));
		group.add(rb15min);
		group.add(rb24hour);
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		clear = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER_CLEAR));
		buttonPanel=new javax.swing.JPanel();
	}

	private void setLayout() {
		this.setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 80, 40, 40, 230 };
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0.3 };
		layout.rowHeights = new int[] { 30, 30, 30, 30, 30, 20 };
		layout.rowWeights = new double[] { 0, 0, 0, 0.3, 0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.insets = new Insets(5, 5, 5, 0);
		layout.setConstraints(lblTitle, c);
		//this.add(lblTitle);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblObjectType, c);
		this.add(lblObjectType);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(cbObjectType, c);
		this.add(cbObjectType);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(lblTaskObj, c);
		this.add(lblTaskObj);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(this.neTreePanel, c);
		this.add(this.neTreePanel);

	

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblCycle, c);
		this.add(lblCycle);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 0);
		layout.addLayoutComponent(rb15min, c);
		this.add(rb15min);
		c.gridx = 2;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(rb24hour, c);
		this.add(rb24hour);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(clear, c);
		this.add(clear);
//		c.gridx = 3;
//		c.gridy = 5;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(10, 5, 10, 5);
//		layout.addLayoutComponent(confirm, c);
//		this.add(confirm);
		c.gridx = 4;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		//c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.CENTER;
		layout.addLayoutComponent(this.buttonPanel, c);
		this.add(buttonPanel);
	}
	private void setButtonLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 70, 80, 80 };
		layout.columnWeights = new double[] { 0, 0.3, 0.3};
		layout.rowHeights = new int[] { 20, };
		layout.rowWeights = new double[] { 0,};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.confirm, c);
		buttonPanel.add(confirm);
		c.gridx=2;
		layout.setConstraints(this.cancel, c);
		buttonPanel.add(cancel);
	}
	public String getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(String filterInfo) {
		this.filterInfo = filterInfo;
	}

	public PtnButton getConfirm() {
		return confirm;
	}
	private JPanel buttonPanel;
}
