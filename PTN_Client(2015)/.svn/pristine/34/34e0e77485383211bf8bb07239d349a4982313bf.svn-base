package com.nms.ui.topology.routebusiness.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.RouteBusiness;
import com.nms.model.ptn.RouteBusinessService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
/**
 * 查询路由业务窗口列表
 * @author dzy
 *
 */
public class RouteBusinessTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -341235160692964833L;

	private JScrollPane scrollPane; // 滚动条
	private ViewDataTable<RouteBusiness> table; // table对象
	private final String TABLENAME = "routebusiness"; // table表名
	
	/**
	 * 创建一个新的实例
	 */
	public RouteBusinessTablePanel(SiteInst siteInst, String type) {
		try {
			this.initComponent();
			this.setLayout(type);
			initData(siteInst,type);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 清空表
	 */
	public void clear() {
		this.table.clear();
	}
	
	/**
	 * 绑定数据
	 * @param mspPortInfoList
	 * @throws Exception 
	 */
	public void initData(SiteInst siteInst,String type) throws Exception {
		RouteBusinessService_MB routeBusinessService = null;
		try {
			List<RouteBusiness> routeBusinessList;
			routeBusinessService= (RouteBusinessService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ROUTEBUSINESS);
			if("XC".equals(type)){//经过路由业务
				routeBusinessList = routeBusinessService.queryBySiteName_XC(siteInst.getSite_Inst_Id());
			}else{//本站路由业务
				routeBusinessList = routeBusinessService.queryBySiteName(siteInst.getCellId());
			}
			//初始化列表数据
			if(null!=routeBusinessList&&routeBusinessList.size()>0){
				table.initData(routeBusinessList);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(routeBusinessService);
		}
	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.table = new ViewDataTable<RouteBusiness>(this.TABLENAME);
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
	private void setLayout(String type) {
		if("XC".equals(type)){//经过路由业务
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_XCROUTEBUSINESS)));
		}else{//本站路由业务
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_HOMEROUTEBUSINESS)));
		}
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
	 * 获取Table
	 * @return
	 */
	public ViewDataTable<RouteBusiness> getTable() {
		return table;
	}
}
