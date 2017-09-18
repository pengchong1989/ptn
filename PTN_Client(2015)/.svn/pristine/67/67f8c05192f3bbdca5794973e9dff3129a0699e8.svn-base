package com.nms.ui.ptn.oam.Node.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.oam.Node.controller.OamNodeController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class OamNodePanel extends ContentView<OamInfo> {

	private static final long serialVersionUID = -1684556081419007864L;
	private JPanel topPanel;
	private JPanel oamTypeFilterPanel;
	private JPanel bottomPanel;

	private JLabel oamTypeLabel;
	private JComboBox oamTypeComboBox;
	private JLabel vertifyLabel;
	private JLabel melLabel;
	private JTextField melField;
	private JLabel lckLabel;
	private JCheckBox lckCheckBox;
	private JLabel loopLabel;
	private JCheckBox loopCheckBox;
	private JLabel tstLabel;
	private JCheckBox tstCheckBox;
	private JTabbedPane oamTypePane;

	public OamNodePanel() {
		super("oamNodeTable",RootFactory.CORE_MANAGE);
		try {
			initComponent();
			setComponentLayout();
			addListener();
			super.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponent() throws Exception {
		SiteService_MB siteService = null;
		try {
			melLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_LEVLE));
			melField = new JTextField();
			melField.setEditable(false);
			lckLabel = new JLabel("LCK");
			lckCheckBox = new JCheckBox();
			loopLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LOOP_FRAME_ENABLED));
			loopCheckBox = new JCheckBox();
			
			tstLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_TST_ENABLED));
			tstCheckBox = new JCheckBox();
			vertifyLabel = new JLabel();
			topPanel = new JPanel();
			oamTypeFilterPanel = new JPanel();
			oamTypePane = new JTabbedPane();
			oamTypePane.add(ResourceUtil.srcStr(StringKeysPanel.PANEL_BASE_FILTER_TYPE), oamTypeFilterPanel);
			oamTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
			oamTypeComboBox = new JComboBox();
			
			bottomPanel = new JPanel();
			
			this.getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_INFO_LIST)));
			
			Map<Integer, String> map = new LinkedHashMap<Integer, String>();
			map.put(0, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE));
			map.put(EServiceType.SECTION.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_SEGMENT_OAM_CONFIG));
			map.put(EServiceType.TUNNEL.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_TUNNEL_OAM_CONFIG));
			map.put(EServiceType.PW.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_PW_OAM_CONFIG));
			
            siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.CHENXIAO.getValue()){
				map.put(EServiceType.ELINE.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_ELINE_OAM_CONFIG));
			}else{
				map.put(EServiceType.ETHERNET.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_ETHERNET_OAM_CONFIG));
			}
			map.put(EServiceType.LINKOAM.getValue(), ResourceUtil.srcStr(StringKeysObj.STRING_ETH_LINK_OAM_CONFIG));
			setModel(oamTypeComboBox, map);
		} catch (Exception e) {
			throw e;	
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	private void setComponentLayout() {
		setBottomLayout();
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
		// oamTypeFilterPanel.add(testButton);
		oamTypeFilterPanel.add(vertifyLabel);
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

	private void setBottomLayout() {
		GridBagLayout layout = new GridBagLayout();
		bottomPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(melLabel, c);
		bottomPanel.add(melLabel);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.03;
		c.weighty = 0.0;
		c.insets = new Insets(5, 50, 20, 300);
		layout.setConstraints(melField, c);
		bottomPanel.add(melField);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(lckLabel, c);
		bottomPanel.add(lckLabel);
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.03;
		c.weighty = 0.0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(lckCheckBox, c);
		bottomPanel.add(lckCheckBox);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(loopLabel, c);
		bottomPanel.add(loopLabel);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(loopCheckBox, c);
		bottomPanel.add(loopCheckBox);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(tstLabel, c);
		bottomPanel.add(tstLabel);
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.03;
		c.weighty = 0.0;
		c.insets = new Insets(5, 50, 20, 50);
		layout.setConstraints(tstCheckBox, c);
		bottomPanel.add(tstCheckBox);
	}

	
	//监听下拉列表事件:如果是以太网OAM讲显示"删除按钮"否则隐藏改按钮
	private void addListener() {
		oamTypeComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				getRefreshButton().doClick();
				if (itemEvent.getStateChange() == 2) {
					try {
						getSynchroButton().setVisible(false);
						if (oamTypeComboBox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysObj.STRING_SEGMENT_OAM_CONFIG))
								|| oamTypeComboBox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysObj.STRING_ETHERNET_OAM_CONFIG))
								|| oamTypeComboBox.getSelectedItem().toString().equals(ResourceUtil.srcStr(StringKeysObj.STRING_ETH_LINK_OAM_CONFIG))
								|| oamTypeComboBox.getSelectedIndex()==0){
							getSynchroButton().setVisible(true);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
		}
		});
	}

	@Override
	public void setController() {
		controller = new OamNodeController(this);
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
//			needRemoveButtons.add(getSynchroButton());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return needRemoveButtons;
	}

	@Override
	public List<JButton> setAddButtons() {
		return null;
	}

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
