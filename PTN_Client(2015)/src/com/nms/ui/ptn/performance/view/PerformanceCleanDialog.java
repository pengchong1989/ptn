package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.enums.EObjectType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

/**
 * 性能值清零对话框
 * 
 * 2014-3-25 康凯修改
 * 
 * 修改内容 调用统一的网元树。
 * 
 * @author xx
 * 
 */
public class PerformanceCleanDialog extends PtnDialog {

	private static final long serialVersionUID = 2219190791950771503L;
	private JButton confirm; // 确认按钮
	private JButton cancel; // 取消按钮
	private JLabel lblTaskObj; // 监控对象label
	private JLabel lblObjectType; // 对象类型label
	private JComboBox cbObjectType; // 对象类型下拉列表
	private String filterInfo;
	private Map<Integer, String> siteNameMap = new HashMap<Integer, String>();
	private NeTreePanel neTreePanel = null;	//网元树panel

	/**
	 * 创建一个新的实例
	 */
	public PerformanceCleanDialog() {
		this.setModal(true);
		initComponents();
		setLayout();
		addListener();
		UiUtil.showWindow(this, 420, 360);
	}

	public JButton getConfirm() {
		return confirm;
	}

	/**
	 * 添加按钮事件
	 */
	private void addListener() {

		// 取消按钮
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
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

		// 确认按钮
		this.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				confirmButtonActin();
			}
		});
	}

	/**
	 * 确认按钮事件
	 */
	private void confirmButtonActin() {
		try {
			if (this.validateParams()) {
				setFilter();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private void setFilter() {
		String result = null;
		String isSuccess = "";
		CurrentPerformanceFilter currentPerformanceFilter = null;
		try {
			currentPerformanceFilter = this.getCurrentPerformanceFilter();
			if (currentPerformanceFilter != null) {
				Map<Integer, String> siteNameMap = this.getSiteNameMap();
				result = queryPerforByFilter(currentPerformanceFilter);
				if (result != null && result.length() > 0) {
					String[] sitIds = result.split(",");
					for (String s : sitIds) {
						isSuccess += siteNameMap.get(Integer.parseInt(s)) + ",";
					}
					isSuccess = isSuccess.substring(0, isSuccess.length() - 1);
					DialogBoxUtil.succeedDialog(this, isSuccess + ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL));
				} else {
					// 说明所有的网元性能都清除成功
					DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				}
			} else {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
				return;
			}
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			this.dispose();
		}
	}

	private String queryPerforByFilter(CurrentPerformanceFilter currentPerformanceFilter) throws Exception {
		DispatchUtil dispatch = null;
		String result = null;
		try {
			dispatch = new DispatchUtil(RmiKeys.RMI_PERFORMANCE);
			result = dispatch.executeCleanCurrPerforByFilter(currentPerformanceFilter);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			dispatch = null;
		}
		return result;
	}

	/**
	 * 验证输入数据的正确性
	 * 
	 * @return true成功 false失败
	 * @throws Exception
	 */
	public boolean validateParams() throws Exception {
		if (!this.neTreePanel.verifySelect()) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_MONITORING_OBJ));
			return false;
		}
		return true;
	}


	/**
	 * 获取界面上的过滤条件
	 * @return 性能过滤条件
	 * @throws Exception
	 */
	public CurrentPerformanceFilter getCurrentPerformanceFilter() throws Exception {
		CurrentPerformanceFilter currentPerformanceFilter = null;
		List<SlotInst> slotInstList=null;
		List<Integer> slotInstsCardAddress = null;
		List<String> site_slotInsts = null;
		try {
			currentPerformanceFilter = new CurrentPerformanceFilter();
			
			//如果界面选择的是网元类型  把tree上选中的网元加载到过滤条件中
			if (cbObjectType.getSelectedItem().equals(ResourceUtil.srcStr(StringKeysObj.NET_BASE))) {
				// 根据网元查询当前性能值
				currentPerformanceFilter.setSiteInsts(this.neTreePanel.getPrimaryKeyList("site"));
				currentPerformanceFilter.setObjectType(EObjectType.SITEINST);
			} else if (cbObjectType.getSelectedItem().equals(ResourceUtil.srcStr(StringKeysObj.BOARD))) {
				//如果选中的板卡，把槽位的mastercardaddress属性和网元主键/mastercardaddress 加载到过滤条件中
				slotInstsCardAddress = new ArrayList<Integer>();
				site_slotInsts = new ArrayList<String>();
				slotInstList=this.neTreePanel.getSelectSlotInst();
				
				for(SlotInst slotInst : slotInstList){
					slotInstsCardAddress.add(Integer.valueOf(slotInst.getMasterCardAddress()));
					site_slotInsts.add(slotInst.getSiteId() + "/" + slotInst.getMasterCardAddress());
				}
				
				currentPerformanceFilter.setSlotInsts(slotInstsCardAddress);
				currentPerformanceFilter.setSite_slotInsts(site_slotInsts);
				currentPerformanceFilter.setObjectType(EObjectType.SLOTINST);
			}
		} catch (Exception e) {
			throw e;
		} finally{
			slotInstList=null;
			slotInstsCardAddress = null;
			site_slotInsts = null;
		}
		
		return currentPerformanceFilter;
	}

	/**
	 * 控件初始化
	 */
	private void initComponents() {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		lblTaskObj = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_OBJ));
		lblObjectType = new JLabel(ResourceUtil.srcStr(StringKeysObj.OBJ_TYPE));
		cbObjectType = new JComboBox();
		cbObjectType.addItem(ResourceUtil.srcStr(StringKeysObj.NET_BASE));
//		cbObjectType.addItem(ResourceUtil.srcStr(StringKeysObj.BOARD));
		this.neTreePanel = new NeTreePanel(true, 2, null,false);
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 80, 40, 40, 150, 80 };
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0.3 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		layout.rowWeights = new double[] { 0, 0.3, 0, 0, 0.2, 0, 0, 0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 10, 5, 15);
		layout.addLayoutComponent(lblObjectType, c);
		this.add(lblObjectType);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(cbObjectType, c);
		this.add(cbObjectType);

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(lblTaskObj, c);
		this.add(lblTaskObj);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 8;
		c.gridwidth = 5;
		layout.addLayoutComponent(this.neTreePanel, c);
		this.add(this.neTreePanel);

		c.gridx = 4;
		c.gridy = 10;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(confirm, c);
		this.add(confirm);
		c.gridx = 5;
		c.gridy = 10;
		c.fill = GridBagConstraints.WEST;
		layout.addLayoutComponent(cancel, c);
		this.add(cancel);
	}

	public Map<Integer, String> getSiteNameMap() {
		return siteNameMap;
	}

	public void setSiteNameMap(Map<Integer, String> siteNameMap) {
		this.siteNameMap = siteNameMap;
	}

	public String getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(String filterInfo) {
		this.filterInfo = filterInfo;
	}

}
