package com.nms.ui.ptn.clock.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.list.TList;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.util.ClockUtil;
import com.nms.ui.ptn.clock.controller.FrequencyPanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 频率管理面板
 * 
 * @author lp
 * 
 */
public class FrequencyPanel extends JPanel {
	private static final long serialVersionUID = -6214984749060107095L;
	@SuppressWarnings("unused")
	private FrequencyPanelController controller;
//	private JLabel lblTitle;
	private JPanel titlePanel;
	private JPanel contentPanel;
	private JLabel lblClockWorkMode; // 时钟工作模式
	private JComboBox cbClockWorkMode;
	private JLabel lblEnable; // 时能状态
	private JComboBox cbEnable;
	private JButton setClockPRIList; // 时钟优先级排列
	private JLabel lblOutInSelect; // 外时钟输入选择
	private JComboBox cbOutInSelect;
	private JLabel lblClockOutEnable1;// 外时钟输出使能选择1
	private JComboBox cbClockOutEnable1;
	private JLabel lblClockOutEnable2;// 外时钟输出使能选择1
	private JComboBox cbClockOutEnable2;
	private JButton setOutSelectList; // 输出时钟选择
	private JLabel lblSmOuter;// SM门限外时钟
	private JComboBox cbSmOuter;
	private JLabel lblSmSystem; // SM门限系统时钟
	private JComboBox cbSmSystem;
	private JLabel lblQlIn;// QL使用SA选择输入源SA选择
	private JComboBox cbQlIn;
	private JLabel lblQlOut;// QL使用SA选择输出源SA选择
	private JComboBox cbQlOut;
//	private JLabel lblInQLOuterClock;// 输入源QL值外时钟
	private JComboBox cbInQLOuterClock;
	private JButton setClockInQLValueList; // 输入源QL值GE1
	private JButton setClockOutQLValueList;// 输出源的QL值GE1
	private JLabel lblClockInResumeTime; // 时钟输入源等待恢复时间
	private JSpinner snClockInResumeTime;
	private JScrollPane scrollPanel;
	private TList tlWaitResumeTime;
	private JScrollPane resumeTimeScrollPanel;
	private JPanel buttonPanel;
	private PtnButton confimButton;
	private PtnButton queryButton;
	private TDataBox box = new TDataBox();
	// 初始化时钟优先级排列界面的map对象
	private Map<String, String> clockPRIMapItems = new LinkedHashMap<String, String>();
	private Map<String, String> outSelectMapItems = new LinkedHashMap<String, String>();
	// 默认输出时钟选择
	private String defaultOutSelect = "0/1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31/32";
	// 默认时钟优先级排列
	private String defaultClockPRI = "0/1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/16/17/18/19/20/21/22/23/24/25/26/27/28/29/30/31";
	// 默认输入源QL值GE1
	private String defaultClockInQLValue = "11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11";
	// 默认输出源的QL值GE1值
	private String defaultClockOutQLValue = "11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11/11";
	private FrequencyInfo info = null;
	private JButton clockRorate;
	private PtnButton syncButton;

	private String siteType;
	public FrequencyPanel() {
		SiteService_MB siteService=null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteType = siteService.getSiteType(ConstantUtil.siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		init();
		controller = new FrequencyPanelController(this);
		
	}

	private void init() {
		initComponents();
		setLayout();
	}

	/**
	 * 设置界面默认值
	 */
	public void setDefaultValue() {
		cbClockWorkMode.setSelectedIndex(0);
		cbOutInSelect.setSelectedItem("Hz");
		cbClockOutEnable1.setSelectedItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_CLOSE));
		cbClockOutEnable2.setSelectedItem("Hz");
		cbSmOuter.setSelectedIndex(4);
		cbSmSystem.setSelectedIndex(4);
		cbInQLOuterClock.setSelectedIndex(4);
	}

	@SuppressWarnings("unchecked")
	public FrequencyInfo get() {
		StringBuilder str = null;
		try {
			if (info == null) {
				info = new FrequencyInfo();
			}
			info.setSiteId(ConstantUtil.siteId);
			info.setClockWorkMode(cbClockWorkMode.getSelectedIndex());
			info.setQlEnable(cbEnable.getSelectedIndex());
			info.setOuterClockInSelect(cbOutInSelect.getSelectedIndex());
			info.setOuterClockOutSelect1(cbClockOutEnable1.getSelectedIndex());
			info.setOuterClockOutSelect2(cbClockOutEnable2.getSelectedIndex());
			info.setSmOuter(Integer.valueOf(((ControlKeyValue) (cbSmOuter.getSelectedItem())).getId()));
			info.setSmSystem(Integer.valueOf(((ControlKeyValue) (cbSmSystem.getSelectedItem())).getId()));
			info.setQlIn(Integer.valueOf(((ControlKeyValue) (cbQlIn.getSelectedItem())).getId()));
			info.setQlOut(Integer.valueOf(((ControlKeyValue) (cbQlOut.getSelectedItem())).getId()));
			info.setClockInResumeTime(Integer.valueOf(snClockInResumeTime.getValue().toString()));
			info.setClockPRIList(this.defaultClockPRI);
			info.setOutSelectList(this.defaultOutSelect);
			info.setClockInQLValueList(getDefaultClockInQLValue());
			info.setClockOutQLValueList(getDefaultClockOutQLValue());
			str = new StringBuilder();
			List<Element> elements = box.getAllElements();
			String[] waitTimeArr = new String[27];
			for (Element element : elements) {
				if(element.isSelected()){
					String name = element.getName();
					if(!name.equals(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK))){
						PortInst port = (PortInst) element.getUserObject();
						waitTimeArr[port.getNumber()] = "1";	
					}else{
						waitTimeArr[0] = "1";
					}
				}
			}
			for (int i = 0; i < waitTimeArr.length; i++) {
				if(waitTimeArr[i] == null){
					waitTimeArr[i] = "0";
				}
				str.append(waitTimeArr[i]);
			}
			info.setWaitResumeTime(str.toString());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			str = null;
		}
		return info;
	}

	/**
	 * 初始化界面
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void refresh(FrequencyInfo info) throws Exception {
		String str = null;
		try {
			ClockUtil clockUtil=new ClockUtil();
			this.info = info;
			if (info == null) {
				// 初始配置网元的频率管理时，设置界面的默认值
				clockPRIMapItems = clockUtil.getClockPRIList(ConstantUtil.siteId);
				outSelectMapItems = clockUtil.getOutSelectList(ConstantUtil.siteId);
				setDefaultValue();
			} else {
				// 修改网元的频率数据时，用网元的频率对象初始化界面
				cbClockWorkMode.setSelectedIndex(info.getClockWorkMode());
				cbEnable.setSelectedIndex(info.getQlEnable());
				initClockPriAndOutSelect(info.getClockPRIList(),info.getOutSelectList(),
						clockUtil.getClockPRIList(ConstantUtil.siteId),clockUtil.getOutSelectList(ConstantUtil.siteId));
				setDefaultClockInQLValue(info.getClockInQLValueList());
				setDefaultClockOutQLValue(info.getClockOutQLValueList());
				cbOutInSelect.setSelectedIndex(info.getOuterClockInSelect());
				cbClockOutEnable1.setSelectedIndex(info.getOuterClockOutSelect1());
				cbClockOutEnable2.setSelectedIndex(info.getOuterClockOutSelect2());
				int index = 0;
				index = getIndex(info.getSmOuter(), 4);
				cbSmOuter.setSelectedIndex(index);
				index = getIndex(info.getSmSystem(), 4);
				cbSmSystem.setSelectedIndex(index);
				cbQlIn.setSelectedIndex(info.getQlIn());
				cbQlOut.setSelectedIndex(info.getQlOut());
				snClockInResumeTime.getModel().setValue(info.getClockInResumeTime());
				str = info.getWaitResumeTime();
				
				int count = 0;
				if("703A".equals(siteType) || "703B".equals(siteType)
						|| "703B2".equals(siteType) || "703-2A".equals(siteType)){
					count = 6;
				}else if("703-2".equals(siteType) || "703-6".equals(siteType)){
					count = 4;
				}else if("703-1".equals(siteType) || "703-4".equals(siteType) || "703-5".equals(siteType)){
					count = 3;
				}else if("703-3".equals(siteType) || "703-7".equals(siteType)){
					count = 2;
				}else{
					//需要加上外时钟
					count = clockUtil.getFrequencyPorts(ConstantUtil.siteId).size()+1;
				}
				for (int i = 0; i < count; i++) {
					int number = Integer.parseInt(tlWaitResumeTime.getDataBox().getElementByID(i).getDisplayName());	
					if (str.charAt(number) == '0') {
						tlWaitResumeTime.getDataBox().getElementByID(i).setSelected(false);
					} else if (str.charAt(number) == '1') {
						tlWaitResumeTime.getDataBox().getElementByID(i).setSelected(true);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {

		}
	}

	/**
	 * str1 优先级排列
	 * str2 输出时钟选择
	 */
	public void initClockPriAndOutSelect(String str1, String str2, 
			Map<String, String> clockPRIMap, Map<String, String> outSelectMap) {
		clockPRIMapItems.clear();
		outSelectMapItems.clear();
		String[] strArr = null;
		if("703A".equals(siteType)){
			strArr = str1.split("/");
			for (int i = 0; i < strArr.length; i++) {
				if(!strArr[i].equals("")){
					if(Integer.parseInt(strArr[i]) != 255){
						clockPRIMapItems.put((Integer.parseInt(strArr[i])+2)+"", clockPRIMap.get((Integer.parseInt(strArr[i])+2)+""));
					}
				}
			}
			strArr = str2.split("/");
			for (int i = 0; i < strArr.length; i++) {
				if(!strArr[i].equals("")){
					if(Integer.parseInt(strArr[i]) != 255){
						outSelectMapItems.put((Integer.parseInt(strArr[i])+2)+"", outSelectMap.get((Integer.parseInt(strArr[i])+2)+""));
					}
				}
			}
		}else{
			strArr = str1.split("/");
			int priSize = 0;
			for (int i = 0; i < strArr.length; i++) {
				if(!strArr[i].equals("")){
					if(Integer.parseInt(strArr[i]) != 255){
						String value = clockPRIMap.get(strArr[i]);
						if(value != null){
							priSize++;
							clockPRIMapItems.put(strArr[i], clockPRIMap.get(strArr[i]));
						}
					}
				}
			}
			//两者不相等,说明是板卡有变动,重置,之前的配置无效
			if(priSize != clockPRIMap.size()){
				clockPRIMapItems = clockPRIMap;
			}
			priSize = 0;
			strArr = str2.split("/");
			for (int i = 0; i < strArr.length; i++) {
				if(!strArr[i].equals("")){
					if(Integer.parseInt(strArr[i]) != 255){
						String value = outSelectMap.get(strArr[i]);
						if(value != null){
							priSize++;
							outSelectMapItems.put(strArr[i], outSelectMap.get(strArr[i]));
						}
					}
				}
			}
			//两者不相等,说明是板卡有变动,重置,之前的配置无效
 			if(priSize != outSelectMap.size()){
				outSelectMapItems = outSelectMap;
			}
		}
	}

	/**
	 * 根据设备值，得到门限的下拉列表的索引
	 * 
	 * @param num
	 *            门限设备值
	 * @param defaultIndex
	 *            下拉列表的索引
	 * @return
	 */
	private int getIndex(int num, int defaultIndex) {
		int index = defaultIndex;
		switch (num) {
		case 0:
			index = 0;
			break;
		case 2:
			index = 1;
			break;
		case 4:
			index = 2;
			break;
		case 8:
			index = 3;
			break;
		case 11:
			index = 4;
			break;
		case 15:
			index = 5;
			break;
		default:
			index = defaultIndex;
			break;
		}
		return index;
	}

	/**
	 * 初始化界面控件
	 */
	private void initComponents() {
//		lblTitle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FREQUENCY_MANAGE));
		titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEtchedBorder());
		titlePanel.setSize(60, ConstantUtil.INT_WIDTH_THREE);
		lblClockWorkMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_JOB_MODAL));
		cbClockWorkMode = new JComboBox();
		cbClockWorkMode.addItem("AUTO");
		cbClockWorkMode.addItem("HOLD");
		cbClockWorkMode.addItem("FREE");

		lblEnable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QL_SA_STATUS));
		cbEnable = new JComboBox();
		cbEnable.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED));
		cbEnable.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO));
		setClockPRIList = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_PRIORITY_ARRANGE));
		lblOutInSelect = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTER_CLOCK_SELECT));
		cbOutInSelect = new JComboBox();
		cbOutInSelect.addItem("HDB3");
		cbOutInSelect.addItem("Hz");

		lblClockOutEnable1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTER_CLOCK_OUTPUT_SELECT));
		cbClockOutEnable1 = new JComboBox();
		cbClockOutEnable1.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_CLOSE));
		cbClockOutEnable1.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_OPEN));

		lblClockOutEnable2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_CLOCK_MODEL));
		cbClockOutEnable2 = new JComboBox();
		cbClockOutEnable2.addItem("HDB3");
		cbClockOutEnable2.addItem("Hz");

		setOutSelectList = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_OUTPUT_SELECT));
		lblSmOuter = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SM_OUTER_CLOCK));
		cbSmOuter = new JComboBox();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(0, ResourceUtil.srcStr(StringKeysObj.QUALITY_UNKNOWN));
		map.put(2, ResourceUtil.srcStr(StringKeysObj.G811_CLOCK));
		map.put(4, ResourceUtil.srcStr(StringKeysObj.G812_PASS_CLOCK));
		map.put(8, ResourceUtil.srcStr(StringKeysObj.G813_NATIVE_CLOCK));
		map.put(11, ResourceUtil.srcStr(StringKeysObj.G813_CLOCK));
		map.put(15, ResourceUtil.srcStr(StringKeysObj.SYNCHRO_NO));
		setModel(cbSmOuter, map);

		lblSmSystem = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SM_SYSTEM_CLOCK));
		cbSmSystem = new JComboBox();
		setModel(cbSmSystem, map);

//		lblInQLOuterClock = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INPUT_QL_CLOCK));
		map.put(31, ResourceUtil.srcStr(StringKeysObj.AUTO_S1));
		cbInQLOuterClock = new JComboBox();
		setModel(cbInQLOuterClock, map);

		lblQlIn = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INPUT_SA));
		cbQlIn = new JComboBox();
		map.clear();
		map.put(0, "SA4");
		map.put(1, "SA5");
		map.put(2, "SA6");
		map.put(3, "SA7");
		map.put(4, "SA8");
		setModel(cbQlIn, map);

		lblQlOut = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTPUT_SA));
		cbQlOut = new JComboBox();
		setModel(cbQlOut, map);

		setClockInQLValueList = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_INPUT_QL));
		setClockOutQLValueList = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTPUT_QL));
		lblClockInResumeTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INPUT_TIME));
		
		snClockInResumeTime = new JSpinner(new SpinnerNumberModel(5, 0, 12, 1));
		scrollPanel = new JScrollPane();
		contentPanel = new JPanel();
		scrollPanel.setViewportView(contentPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tlWaitResumeTime = new TList(box);
		tlWaitResumeTime.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SET_TIME)));
		tlWaitResumeTime.setTListSelectionMode(TList.CHECK_SELECTION);
		tlWaitResumeTime.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		resumeTimeScrollPanel = new JScrollPane();
		resumeTimeScrollPanel.setViewportView(tlWaitResumeTime);
		this.initBox();
		buttonPanel = new JPanel();
		syncButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO),true,RootFactory.CORE_MANAGE);
		clockRorate = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLOCK_RORATE),false,RootFactory.CORE_MANAGE);
		confimButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.CORE_MANAGE);
		queryButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL),false,RootFactory.CORE_MANAGE);
	}

	private void initBox(){
		List<String> items = null;
		List<PortInst> portInsts = null;
		ClockUtil clockUtil=new ClockUtil();
		try {
			items = new ArrayList<String>();
			items.add(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
			portInsts = clockUtil.getFrequencyPorts(ConstantUtil.siteId);
			if(portInsts.isEmpty()){
				siteType = null;
			}
			if("703A".equals(siteType)){
				items.add("ge.1.1");
				items.add("ge.1.2");
				items.add("ge.1.3");
				items.add("ge.1.4");
				items.add("E1");
			}else if("703B".equals(siteType) || "703B2".equals(siteType) || "703-2A".equals(siteType)){
				items.add("ge.1.1");
				items.add("ge.1.2");
				items.add("ge.1.3");
				items.add("ge.1.4");
//				items.add("fe1.1");
//				items.add("fe1.2");
				items.add("E1");
			}else if("703-1A".equals(siteType)){
				items.add("ge.1.1");
				items.add("ge.1.2");
				items.add("ge.1.3");
				items.add("ge.1.4");
//				items.add("fe1.1");
//				items.add("fe1.2");
//			}else if("703-4A".equals(siteType)){
//				items.add("ge.1.1");
//				items.add("ge.1.2");
			}else if("703-1".equals(siteType)){
				items.add("ge.1.1");
				items.add("ge.1.2");
//				items.add("fe1.1");
//				items.add("fe1.2");
			}else if("703-2".equals(siteType)){
				items.add("ge.1.1");
				items.add("ge.1.2");
//				items.add("fe1.1");
//				items.add("fe1.2");
				items.add("E1");
			}else if("703-3".equals(siteType)){
				items.add("ge.1.1");
//				items.add("fe1.1");
//				items.add("fe1.2");
			}else if("703-4".equals(siteType)){
				items.add("ge.1.1");
//				items.add("fe1.1");
//				items.add("fe1.2");
				items.add("E1");
			}else if("703-5".equals(siteType)){
				items.add("fe.1.1");
				items.add("fe.1.2");
//				items.add("fe1.3");
//				items.add("fe1.4");
			}else if("703-6".equals(siteType)){
				items.add("fe.1.1");
				items.add("fe.1.2");
//				items.add("fe1.3");
//				items.add("fe1.4");
				items.add("E1");
			}else if("703-7".equals(siteType)){
				items.add("fe.1.1");
//				items.add("fe1.2");
//				items.add("fe1.3");
			}else{
				for (int i = 0; i < portInsts.size(); i++) {
					items.add(portInsts.get(i).getPortName());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		for (int i = 0; i < items.size(); i++) {
			Node node = new Node(i);
			node.setName(items.get(i));
			if(i > 0){
				node.setUserObject(portInsts.get(i-1));
				node.setDisplayName(portInsts.get(i-1).getNumber()+"");
			}else{
				node.setDisplayName("0");
			}
			node.setSelected(false);
			box.addElement(node);
		}
	}

	private void setLayout() {
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout.rowHeights = new int[] { 40 };
		layout.rowWeights = new double[] { 0 };
		layout.columnWidths = new int[] { 60, ConstantUtil.INT_WIDTH_THREE - 60 };
		layout.columnWeights = new double[] { 0, 1.0 };
		titlePanel.setLayout(layout);
//		addComponent(titlePanel, lblTitle, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 20, 5, 5), GridBagConstraints.CENTER, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 60, 300, 60 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_THREE };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
//		addComponent(this, titlePanel, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		GridBagLayout contentLayout = new GridBagLayout();
		contentPanel.setLayout(contentLayout);
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SET_FREQUENCY)));
		Insets insert = new Insets(5, 20, 5, 5);
		addComponent(contentPanel, lblSmOuter, 0, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbSmOuter, 1, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblSmSystem, 0, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbSmSystem, 1, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblClockWorkMode, 0, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbClockWorkMode, 1, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblEnable, 0, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbEnable, 1, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblOutInSelect, 0, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbOutInSelect, 1, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblClockOutEnable1, 0, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbClockOutEnable1, 1, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblClockOutEnable2, 0, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbClockOutEnable2, 1, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblQlIn, 0, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbQlIn, 1, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblQlOut, 0, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, cbQlOut, 1, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
//		addComponent(contentPanel, lblInQLOuterClock, 0, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
//		addComponent(contentPanel, cbInQLOuterClock, 1, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, lblClockInResumeTime, 0, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, snClockInResumeTime, 1, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, setClockPRIList, 0, 11, 0.2, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, setOutSelectList, 1, 11, 0.2, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, setClockInQLValueList, 0, 12, 0.2, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, setClockOutQLValueList, 1, 12, 0.2, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, resumeTimeScrollPanel, 3, 0, 0.5, 0, 1, 24, GridBagConstraints.BOTH, new Insets(0, 100, 5, 100), GridBagConstraints.CENTER, c);
		// button面板布局
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 60, 60, 60, 6 };
		buttonLayout.columnWeights = new double[] { 1.0, 0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 60 };
		buttonLayout.rowWeights = new double[] { 1 };
		buttonPanel.setLayout(buttonLayout);
//		addComponent(buttonPanel, queryButton, 4, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(2, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, confimButton, 3, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(2, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, clockRorate, 1, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(2, 5, 5, 100), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, syncButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(2, 5, 5, 20), GridBagConstraints.WEST, c);

	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame jf = new JFrame();
				FrequencyPanel dialog = new FrequencyPanel();
				jf.add(dialog);
				jf.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				jf.setSize(1000, 580);
				jf.setVisible(true);
			}
		});
	}

	public void setModel(JComboBox cbBox, Map<Integer, String> keyValuse) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbBox.getModel();
		for (Integer key : keyValuse.keySet()) {
			model.addElement(new ControlKeyValue(key.toString(), keyValuse.get(key)));
		}
	}
	
	public PtnButton getSyncButton() {
		return syncButton;
	}
	
	public JButton getSetClockPRIList() {
		return setClockPRIList;
	}

	public JButton getSetOutSelectList() {
		return setOutSelectList;
	}

	public JButton getSetClockInQLValueList() {
		return setClockInQLValueList;
	}

	public JButton getSetClockOutQLValueList() {
		return setClockOutQLValueList;
	}

	public PtnButton getConfimButton() {
		return confimButton;
	}

	public PtnButton getQueryButton() {
		return queryButton;
	}

	public Map<String, String> getClockPRIMapItems() {
		return clockPRIMapItems;
	}

	public Map<String, String> getOutSelectMapItems() {
		return outSelectMapItems;
	}

	public String getDefaultOutSelect() {
		return defaultOutSelect;
	}

	public void setDefaultOutSelect(String defaultOutSelect) {
		this.defaultOutSelect = defaultOutSelect;
	}

	public String getDefaultClockPRI() {
		return defaultClockPRI;
	}

	public void setDefaultClockPRI(String defaultClockPRI) {
		this.defaultClockPRI = defaultClockPRI;
	}

	public String getDefaultClockInQLValue() {
		return defaultClockInQLValue;
	}

	public void setDefaultClockInQLValue(String defaultClockInQLValue) {
		this.defaultClockInQLValue = defaultClockInQLValue;
	}

	public String getDefaultClockOutQLValue() {
		return defaultClockOutQLValue;
	}

	public void setDefaultClockOutQLValue(String defaultClockOutQLValue) {
		this.defaultClockOutQLValue = defaultClockOutQLValue;
	}

	public JButton getClockRorate() {
		return clockRorate;
	}

	public void setClockRorate(JButton clockRorate) {
		this.clockRorate = clockRorate;
	}
	
}
