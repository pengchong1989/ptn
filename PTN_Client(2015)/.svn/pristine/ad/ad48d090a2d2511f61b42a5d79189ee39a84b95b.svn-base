package com.nms.ui.ptn.oam.Node.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.enums.EServiceType;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.oam.Node.controller.TestOamNodeController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TestOamNodePanel extends ContentView<OamInfo> {

	private static final long serialVersionUID = -1684556081419007864L;
	private JPanel topPanel;
	private JPanel oamTypeFilterPanel;

	private JLabel oamTypeLabel;
	private JComboBox oamTypeComboBox;
	private JTextField melField;
	private JCheckBox lckCheckBox;
	private JCheckBox loopCheckBox;
	private JCheckBox tstCheckBox;
	private JTabbedPane oamTypePane;

	public TestOamNodePanel() {
		super("oamTable",RootFactory.CORE_MANAGE);
		try {
			initComponent();
			setComponentLayout();
			addListener();
//			getRefreshButton().doClick();
			super.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponent() {
		topPanel = new JPanel();
		oamTypeFilterPanel = new JPanel();
		oamTypePane = new JTabbedPane();
		oamTypePane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_BASE_FILTER_TYPE), oamTypeFilterPanel);
		oamTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		oamTypeComboBox = new JComboBox();
		this.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_INFO_LIST)));

		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(0, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE));
		map.put(EServiceType.SECTION.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_SEGMENT_OAM_CONFIG));
		map.put(EServiceType.TUNNEL.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_TUNNEL_OAM_CONFIG));
		map.put(EServiceType.PW.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_PW_OAM_CONFIG));
//		map.put(EServiceType.ELINE.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_ELINE_OAM_CONFIG));
//		map.put(EServiceType.LINKOAM.getValue(), "ETH UNI OAM");

		setModel(oamTypeComboBox, map);
	}

	private void setComponentLayout() {
		setoamTypePanleLayout();
		setTopPanelLayout();
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(topPanel, c);
		this.add(topPanel);

	}

	private void setoamTypePanleLayout() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		oamTypeFilterPanel.setLayout(flowLayout);
		oamTypeFilterPanel.add(oamTypeLabel);
		oamTypeFilterPanel.add(oamTypeComboBox);
	}

	private void setTopPanelLayout() {
		GridBagLayout topPanelLayout = new GridBagLayout();
		topPanelLayout.rowHeights = new int[] { 50, 200 };
		topPanelLayout.rowWeights = new double[] { 0.0, 1.0 };
		topPanel.setLayout(topPanelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 8, 0, 0);
		topPanelLayout.setConstraints(oamTypePane, c);
		topPanel.add(oamTypePane);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(0, 0, 0, 0);
		topPanelLayout.setConstraints(this.getContentPanel(), c);
		topPanel.add(this.getContentPanel());
	}

	private void addListener() {
		oamTypeComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				getRefreshButton().doClick();
			}
		});

		getTable().addElementClickedActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getSelect() == null) {
					return;
				} else {
					getController().initDetailInfo();
				}
			}
		});
	}

	private boolean checkSelectItemIsNull() {

		if (this.getSelect() == null || this.getAllSelect().size() > 1) {
			return true;
		}
		return false;

	}

	@Override
	public void setController() {
		controller = new TestOamNodeController(this);
	}

	public void setModel(JComboBox comboBox, Map<Integer, String> map) {
		DefaultComboBoxModel boxModel = (DefaultComboBoxModel) comboBox.getModel();
		for (Integer key : map.keySet()) {
			boxModel.addElement(new ControlKeyValue(key.toString(), map.get(key)));
		}
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		try {
			needRemoveButtons.add(getSearchButton());
			needRemoveButtons.add(getSynchroButton());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return needRemoveButtons;
	}

//	@Override
//	public List<JButton> setAddButtons() {
//		List<JButton> needAddButtons = new ArrayList<JButton>();
//		createButton = new JButton("新建");
//		createButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if (checkSelectItemIsNull()) {
//					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
//					return;
//				} else {
//					try {
//						testAction(arg0);
//					} catch (Exception e) {
//						ExceptionManage.dispose(e,this.getClass());
//
//					}
//				}
//			}
//		});
//		try {
////			if (UiUtil.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
//				needAddButtons.add(this.createButton);
////			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		return needAddButtons;
//	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(200, ConstantUtil.INT_WIDTH_THREE);
	}

	public JComboBox getOamTypeComboBox() {
		return oamTypeComboBox;
	}

	public void setOamTypeComboBox(JComboBox oamTypeComboBox) {
		this.oamTypeComboBox = oamTypeComboBox;
	}

	public JTextField getMelField() {
		return melField;
	}

	public void setMelField(JTextField melField) {
		this.melField = melField;
	}

	public JCheckBox getLckCheckBox() {
		return lckCheckBox;
	}

	public void setLckCheckBox(JCheckBox lckCheckBox) {
		this.lckCheckBox = lckCheckBox;
	}

	public JCheckBox getLoopCheckBox() {
		return loopCheckBox;
	}

	public void setLoopCheckBox(JCheckBox loopCheckBox) {
		this.loopCheckBox = loopCheckBox;
	}

	public JCheckBox getTstCheckBox() {
		return tstCheckBox;
	}

	public void setTstCheckBox(JCheckBox tstCheckBox) {
		this.tstCheckBox = tstCheckBox;
	}

}