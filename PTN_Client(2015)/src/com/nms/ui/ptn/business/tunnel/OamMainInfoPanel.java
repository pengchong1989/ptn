package com.nms.ui.ptn.business.tunnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.path.tunnel.OamMainInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.ui.frame.ViewDataTable;

public class OamMainInfoPanel extends JPanel {
	private static final long serialVersionUID = -5144073007603459587L;
	private static String ATTRS = "tunnelOamMainTable";
	private JScrollPane scrollPane;
	private ViewDataTable<OamMainInfo> table;

	public OamMainInfoPanel() {
		this.initComponents();
		this.setLayout();
	}

	public void clear() {
		table.clear();
	}

	public void initData(List<Tunnel> tunnelList) {
		List<OamMainInfo> oamList = new ArrayList<OamMainInfo>();
		for (Tunnel tunnel : tunnelList) {
			OamMainInfo oam = new OamMainInfo();
			oam.setTunnelType(tunnel.getTunnelType());
			oam.setIsReverse(tunnel.getProtectBack() == 0 ? 1:0);
			oam.setWaitTime(tunnel.getWaittime());
			oam.setDelayTime(tunnel.getDelaytime());
			oam.setaSiteId(tunnel.getaSiteId());
			oam.setOamInfoList(tunnel.getOamList());
			if(tunnel.getProtectTunnel() != null){
				oam.setReserveOamInfoList(tunnel.getProtectTunnel().getOamList());
			}
			oamList.add(oam);
		}
		table.initData(oamList);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, scrollPane, 0, 1, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void initComponents() {
		table = new ViewDataTable<OamMainInfo>(ATTRS);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

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

	public ViewDataTable<OamMainInfo> getTable() {
		return table;
	}
}
