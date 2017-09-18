package com.nms.ui.ptn.business.pw;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * PW的qos详细信息面板
 * 
 * @author lepan
 * 
 */
public class PwQosQueuePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 51765220482975914L;
	private static String ATTRS = "pwBusinessQoSTable";
	private JLabel lblQueueType;
	private JTextField tfQueueType;
	private JScrollPane scrollPane;
	private ViewDataTable<QosInfo> table;

	public PwQosQueuePanel() {
		init();
	}

	private void init() {
		initComponents();
		setLayout();

	}

	public void clear() {
		table.clear();
		tfQueueType.setText("");
	}

	public void initData(List<QosInfo> qosQueueList) {
		if (qosQueueList != null && qosQueueList.size() > 0) {
			tfQueueType.setText(qosQueueList.get(0).getQosType().trim());
//			if (CodeConfigItem.getInstance().getWuhan() == 1) {
//				//武汉设备的qos做处理,显示的时候要把Mbps换算成kbps
//				for (QosInfo qosInfo : qosQueueList) {
//					qosInfo.setCir(qosInfo.getCir()*1000);
//					qosInfo.setEir(qosInfo.getEir()*1000);
//					qosInfo.setPir(qosInfo.getPir()*1000);
//				}
//			}
			
			table.initData(qosQueueList);
			
//			if (CodeConfigItem.getInstance().getWuhan() == 1) {
//				//武汉设备的qos做处理,显示完之后再还原
//				for (QosInfo qosInfo : qosQueueList) {
//					qosInfo.setCir(qosInfo.getCir()/1000);
//					qosInfo.setEir(qosInfo.getEir()/1000);
//					qosInfo.setPir(qosInfo.getPir()/1000);
//				}
//			}
		}
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, lblQueueType, 0, 0, 0.05, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 10, 5, 0), GridBagConstraints.WEST, gridBagConstraints);
		addComponent(this, tfQueueType, 1, 0, 1.0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 0, 5, 0), GridBagConstraints.WEST, gridBagConstraints);
		addComponent(this, scrollPane, 0, 1, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void initComponents() {
		lblQueueType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUEUE_SCHEDULING));
		tfQueueType = new JTextField();
		tfQueueType.setEditable(false);
		tfQueueType.setMinimumSize(new Dimension(120, 18));
		table = new ViewDataTable<QosInfo>(ATTRS);
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

	public ViewDataTable<QosInfo> getTable() {
		return table;
	}
}
