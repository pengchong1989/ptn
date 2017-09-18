package com.nms.ui.topology.routebusiness.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.path.Segment;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.ptn.SfpPowInfo;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

/**
 * @author guoqc
 */
public class QuerySfpPowPanel extends JPanel {
	private static final long serialVersionUID = 7211560055416010545L;
	private ViewDataTable<SfpPowInfo> table; // table对象
	private JScrollPane scrollPane;
	private final String TABLENAME = "querySfpPow"; // table表名

	public QuerySfpPowPanel(Segment segment) {
		try {
			this.initComponent();
			this.setLayout();
			initData(segment);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.table = new ViewDataTable<SfpPowInfo>(this.TABLENAME);
		this.table.getTableHeader().setResizingAllowed(true);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		this.table.setTableHeaderPopupMenuFactory(null);
		this.table.setTableBodyPopupMenuFactory(null);
		this.scrollPane = new JScrollPane();
		this.scrollPane.setViewportView(this.table);
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 230 };
		layout.columnWeights = new double[] { 0 };
		layout.rowHeights = new int[] {200};
		layout.rowWeights = new double[] { 0.1};
		this.setLayout(layout);
		addComponent(this, this.scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, c);
	}

	/**
	 * 添加控件
	 * 
	 * @param panel
	 *            要添加的容器
	 * @param component
	 *            要添加的控件
	 */
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

	/**
	 * 绑定数据
	 * @throws Exception 
	 */
	public void initData(Segment segment) throws Exception {
		int aSiteId = segment.getASITEID();
		int zSiteId = segment.getZSITEID();
		CurrentPerformanceFilter filter = this.getCurrentPerforFilter(aSiteId, zSiteId);
		DispatchUtil dispatch = new DispatchUtil(RmiKeys.RMI_PERFORMANCE);
		List<CurrentPerforInfo> queryPerforList = dispatch.executeQueryCurrPerforByFilter(filter);
		List<SfpPowInfo> sfpPowList = this.convertCurrPerformance(this.getSfpPowInfoList(queryPerforList, filter), segment);
		this.table.initData(sfpPowList);
	}

	/**
	 * 获取查询当前光模块发射/接收功率性能值
	 * @return 
	 */
	private CurrentPerformanceFilter getCurrentPerforFilter(int aSiteId, int zSiteId) {
		CurrentPerformanceFilter filter = new CurrentPerformanceFilter();
		//监控对象
		List<Integer> siteInstList = new ArrayList<Integer>(); 
		siteInstList.add(aSiteId);
		siteInstList.add(zSiteId);
		filter.setSiteInsts(siteInstList);
		filter.setObjectType(EObjectType.SITEINST);
		//性能类型 PORT
		filter.getCapabilityIdList().add(197);
		filter.getCapabilityIdList().add(198);
		filter.setTypeStr("197,198");
		//性能类别 光模块接收/发送功率
		filter.getCapabilityNameList().add("SFPTxPOW");
		filter.getCapabilityNameList().add("SFPRxPOW");
		//监控周期
		filter.setMonitorCycle(EMonitorCycle.MIN15);
		filter.setPerformanceType(0);
		filter.setPerformanceCount(0);
		filter.setPerformanceBeginCount(0);
		filter.setPerformanceMonitorTime(this.porseTime(0));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		filter.setPerformanceOverTime(df.format(new Date()));
		//零值过滤
		filter.setFilterZero(0);
		return filter;
	}

	private String porseTime(int size) {
		try {
			SimpleDateFormat fat =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long parseTime = this.testTime(size);
			return fat.format(new Date(parseTime));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return "";
	}
	
	private long testTime(int size) {
		try {
			Date dateNow = new Date();
			long dateLong = dateNow.getTime();
			long preseTime = dateLong-size*15 * 60 * 1000;
			SimpleDateFormat fat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat fat2 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fat3 =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date(preseTime);
			String preseTimeYear = fat2.format(date);
			String preseTimeString = fat.format(date);
			String[] str = preseTimeString.split(":");
			int i = Integer.valueOf(str[1]) / 15;
			preseTimeYear = preseTimeYear + " " + str[0] + ":";
			if (i == 0) {
				preseTimeString = preseTimeYear  + "00";
			}
			if (i == 1) {
				preseTimeString = preseTimeYear + "15";
			}
			if (i == 2) {
				preseTimeString = preseTimeYear + "30";
			}
			if (i == 3) {
				preseTimeString = preseTimeYear + "45";
			}
			
			return fat3.parse(preseTimeString).getTime();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return 0;
	}

	/**
	 * 过滤当前性能,获取需要的性能并转换成界面数据
	 * @param zPortId 
	 * @param aPortId 
	 */
	private List<CurrentPerforInfo> getSfpPowInfoList(List<CurrentPerforInfo> queryPerforList,
								CurrentPerformanceFilter filter) {
		List<CurrentPerforInfo> cureenPerforFilterList = new ArrayList<CurrentPerforInfo>();
		if(queryPerforList != null){
			String[] filterTypes = filter.getTypeStr().trim().split(",");
			for (CurrentPerforInfo c : queryPerforList) {
				if (c.getCapability() != null && c.getObjectName() != null) {
					Capability cap = c.getCapability();
					for (String strType : filter.getCapabilityNameList()) {
						if (cap.getCapabilityname().equalsIgnoreCase(strType)) {
							for (int i = 0; i < filterTypes.length; i++) {
								if (Integer.parseInt(filterTypes[i]) == cap.getId()) {
									cureenPerforFilterList.add(c);
								}
							}
						}
					}
				}
			}
		}
		
		return cureenPerforFilterList;
	}

	/**
	 * 将性能值转换成界面数据
	 * @param segment 
	 * @param zPortId 
	 * @param aPortId 
	 */
	private List<SfpPowInfo> convertCurrPerformance(
			List<CurrentPerforInfo> cureenPerforFilterList, Segment segment) {
		List<SfpPowInfo> sfpPowList = new ArrayList<SfpPowInfo>();
		int aSiteId = segment.getASITEID();
		int zSiteId = segment.getZSITEID();
		String segName = segment.getNAME();
		SfpPowInfo sfpA = new SfpPowInfo();
		sfpA.setLinkName(segName);
		sfpA.setaPortName(segment.getShowSiteAname()+"/"+segment.getShowPortAname());
		sfpA.setzPortName(segment.getShowSiteZname()+"/"+segment.getShowPortZname());
		sfpA.setSfpTxPow("无法查询");
		sfpA.setSfpRxPow("无法查询");
		SfpPowInfo sfpZ = new SfpPowInfo();
		sfpZ.setLinkName(segName);
		sfpZ.setaPortName(segment.getShowSiteZname()+"/"+segment.getShowPortZname());
		sfpZ.setzPortName(segment.getShowSiteAname()+"/"+segment.getShowPortAname());
		sfpZ.setSfpTxPow("无法查询");
		sfpZ.setSfpRxPow("无法查询");
		sfpPowList.add(sfpA);
		sfpPowList.add(sfpZ);
		for (CurrentPerforInfo c : cureenPerforFilterList) {
			if(c.getSiteId() == aSiteId && (c.getObjectName()).equals(segment.getShowPortAname())){
				this.getPerformanceValue(c, sfpA, sfpZ);
			}
			if(c.getSiteId() == zSiteId && (c.getObjectName()).equals(segment.getShowPortZname())){
				this.getPerformanceValue(c, sfpZ, sfpA);
			}
		}
		return sfpPowList;
	}

	private void getPerformanceValue(CurrentPerforInfo c, SfpPowInfo sfpA, SfpPowInfo sfpZ) {
		BigDecimal b = new BigDecimal(c.getPerformanceValue());  
		float f = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
		String value = String.valueOf(f)+c.getCapability().getUnitName();
		if("SFPTxPOW".equals(c.getCapability().getCapabilityname())){
			sfpA.setSfpTxPow(value);//发送功率
		}else if("SFPRxPOW".equals(c.getCapability().getCapabilityname())){
			sfpZ.setSfpRxPow(value);//接收功率
		}
	}
}
