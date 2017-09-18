/*
 * AcPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.ne.ac.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.ui.frame.ContentView;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.ne.ac.controller.AcPanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 
 * @author __USER__
 */
public class AcPanel extends ContentView<AcPortInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3803454731929136294L;

	private JSplitPane splitPane;
	private JTabbedPane tabbedPane;
	private JPanel basicInfoPanel;
	private JPanel bufInfoPanel;
	private JPanel simpleQosPanel;
	private JScrollPane simpleQosScrollPane;

	private JPanel detailQosPanel;
	private JScrollPane detailQosScrollPane;
	private ViewDataTable<QosInfo> simpleqosTable;
	private ViewDataTable<Acbuffer> detailqosTable;

	private JLabel simpleqosLable;
	private JLabel detailqosLable;

	private JLabel tagReconfigLabel;
	private JLabel tagActionLabel;
	private JLabel addVlanIdLabel;
	private JLabel addVlanPriLabel;

	private JTextField tagReconfigTF;
	private JTextField tagActionTF;
	private JTextField addVlanIdTF;
	private JTextField addVlanPriTF;

	Map<Integer, String> codeIdAndCodeNameMap = new HashMap<Integer, String>();

	public AcPanel() throws Exception {
		super(ConstantUtil.siteId,"acTableWH","acTableCX",RootFactory.CORE_MANAGE);
		init();
	}

	private void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_TIP_AC)));
		this.initComponents();
		setLayout();
		setActionListention();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setActionListention() {
		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {

					return;
				} else {
					((AcPanelController) getController()).initDetailInfo();
				}
			}
		});
		getTable().addElementDoubleClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (getTable().getAllSelect().size() != 1) {
						return;
					} else {
						if(checkRoot(RootFactory.CORE_MANAGE)){
							getController().openUpdateDialog();
						}
						
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		addComponent(this, splitPane, 0, 1, 1.0, 1.0, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	/**
	 * Creates new form AcPanel
	 * 
	 * @throws Exception
	 * @throws Exception
	 */

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		tagReconfigLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG));
		tagActionLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_ACTION));
		addVlanIdLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID));
		addVlanPriLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI));

		tagReconfigTF = new JTextField();
		tagActionTF = new JTextField();
		addVlanIdTF = new JTextField();
		addVlanPriTF = new JTextField();

		tabbedPane = new JTabbedPane();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 80, 80, 40, 80, 160 };
		layout.columnWeights = new double[] { 0, 0.1, 0.2, 0, 0.1, 0.4 };
		layout.rowHeights = new int[] { 40, 40, 40 };
		layout.rowWeights = new double[] { 0, 0, 0.4 };
		basicInfoPanel = new JPanel(layout);
		bufInfoPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints basicInfoGBC = new GridBagConstraints();
		addComponent(basicInfoPanel, tagReconfigLabel, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);
		addComponent(basicInfoPanel, tagReconfigTF, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);

		addComponent(basicInfoPanel, addVlanIdLabel, 3, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);
		addComponent(basicInfoPanel, addVlanIdTF, 4, 0, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);

		addComponent(basicInfoPanel, tagActionLabel, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);
		addComponent(basicInfoPanel, tagActionTF, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);

		addComponent(basicInfoPanel, addVlanPriLabel, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);
		addComponent(basicInfoPanel, addVlanPriTF, 4, 1, 1, 1, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, basicInfoGBC);

		simpleQosPanel = new JPanel(new GridBagLayout());
		simpleQosPanel.setPreferredSize(new Dimension(1000, 100));
		simpleqosLable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SIMPLENESS_QOS));
		simpleQosScrollPane = new JScrollPane();

		detailQosPanel = new JPanel(new GridBagLayout());
		detailqosLable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SUBDIVIDE_QOS));
		detailQosScrollPane = new JScrollPane();

		simpleqosTable = new ViewDataTable<QosInfo>("simpleQosTable");
		detailqosTable = new ViewDataTable<Acbuffer>("detatilQosTable");
		simpleqosTable.getTableHeader().setResizingAllowed(true);
		simpleqosTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		simpleqosTable.setTableHeaderPopupMenuFactory(null);
		simpleqosTable.setTableBodyPopupMenuFactory(null);

		detailqosTable.getTableHeader().setResizingAllowed(true);
		detailqosTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		detailqosTable.setTableHeaderPopupMenuFactory(null);
		detailqosTable.setTableBodyPopupMenuFactory(null);

		simpleQosScrollPane.setViewportView(simpleqosTable);
		GridBagConstraints simpleGBC = new GridBagConstraints();
		addComponent(simpleQosPanel, simpleqosLable, 0, 0, 0, 0.01, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, simpleGBC);
		addComponent(simpleQosPanel, simpleQosScrollPane, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, simpleGBC);

		detailQosScrollPane.setViewportView(detailqosTable);
		GridBagConstraints detailGBC = new GridBagConstraints();
		addComponent(detailQosPanel, detailqosLable, 0, 0, 0.1, 0.01, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, detailGBC);
		addComponent(detailQosPanel, detailQosScrollPane, 0, 1, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, detailGBC);

		GridBagConstraints bufInfoGBC = new GridBagConstraints();
		bufInfoPanel.setPreferredSize(new Dimension(ConstantUtil.INT_WIDTH_THREE, 120));
		addComponent(bufInfoPanel, simpleQosPanel, 0, 0, 0.1, 0.1, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, bufInfoGBC);
		addComponent(bufInfoPanel, detailQosPanel, 0, 1, 0.1, 0.3, 1, 1, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), GridBagConstraints.WEST, bufInfoGBC);

		
//		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO), basicInfoPanel);
		tabbedPane.addTab(ResourceUtil.srcStr(StringKeysTab.TAB_STREAM_INFO), bufInfoPanel);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);

		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);

	}// </editor-fold>

	@Override
	public void setController() {
		controller = new AcPanelController(this);
	}

	public ViewDataTable<QosInfo> getSimpleqosTable() {
		return simpleqosTable;
	}

	public ViewDataTable<Acbuffer> getDetailqosTable() {
		return detailqosTable;
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getSynchroButton());
		return needRemoveButtons;
	}

	public JTextField getTagReconfigTF() {
		return tagReconfigTF;
	}

	public void setTagReconfigTF(JTextField tagReconfigTF) {
		this.tagReconfigTF = tagReconfigTF;
	}

	public JTextField getTagActionTF() {
		return tagActionTF;
	}

	public void setTagActionTF(JTextField tagActionTF) {
		this.tagActionTF = tagActionTF;
	}

	public JTextField getAddVlanIdTF() {
		return addVlanIdTF;
	}

	public void setAddVlanIdTF(JTextField addVlanIdTF) {
		this.addVlanIdTF = addVlanIdTF;
	}

	public JTextField getAddVlanPriTF() {
		return addVlanPriTF;
	}

	public void setAddVlanPriTF(JTextField addVlanPriTF) {
		this.addVlanPriTF = addVlanPriTF;
	}

}