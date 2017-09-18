package com.nms.ui.ptn.business.eline;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PortNetworkTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955133785031558871L;
	private JScrollPane scrollPane;
	private ViewDataTable<AcPortInfo> table;
	private final String TABLENAME = "acNetworkTable";

	public PortNetworkTablePanel() {
		initComponents();
		setLayout();
	}

	public void clear() {
		table.clear();
	}

	/**
	 * 绑定tabel数据
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void initData(List<Integer> acIdList) {
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acPortInfoList = null;
		try {
			if (acIdList != null && acIdList.size() > 0) {
				acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				acPortInfoList = acInfoService.select(acIdList);
				this.tableValue(acPortInfoList);
				table.initData(acPortInfoList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}

	/**
	 * 
	 * 给table上的每一列赋值
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void tableValue(List<AcPortInfo> acPortInfoList) throws Exception {
		PortLagService_MB portLagService=null;
		SiteService_MB siteService=null;
		PortService_MB portService=null;
		try {
			
			portLagService=(PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portService=(PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for (AcPortInfo acPortInfo : acPortInfoList) {
				acPortInfo.putClientProperty("sitename", siteService.getSiteName(acPortInfo.getSiteId()));
				if(acPortInfo.getPortId()>0){
					acPortInfo.putClientProperty("portname", portService.getPortname(acPortInfo.getPortId()));
				}else{
					acPortInfo.putClientProperty("portname", portLagService.getLagName(acPortInfo.getLagId()));
				}
				acPortInfo.putClientProperty("model", UiUtil.getCodeById(acPortInfo.getPortModel()).getCodeName());
				if(siteService.getManufacturer(acPortInfo.getSiteId()) == EManufacturer.WUHAN.getValue()){
					acPortInfo.putClientProperty("vlanid", acPortInfo.getBufferList().get(0).getVlanId());
				}else{
					acPortInfo.putClientProperty("vlanid", acPortInfo.getVlanId());
				}
				acPortInfo.putClientProperty("vlanpri", acPortInfo.getVlanpri());
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portLagService);
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(portService);
		}
	}

	private void initComponents() {
		table = new ViewDataTable<AcPortInfo>(TABLENAME);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
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

	public ViewDataTable<AcPortInfo> getTable() {
		return table;
	}
}
