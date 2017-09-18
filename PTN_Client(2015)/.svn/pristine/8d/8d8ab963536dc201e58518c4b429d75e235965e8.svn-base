package com.nms.ui.ptn.e1;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class EPanel extends ContentView<E1Info> {

	private static final long serialVersionUID = 7536458888010973995L;
	private JLabel outputClockSrcLabel;
	private JComboBox outputClockSrcComboBox;
	private JLabel tdmClocksrcLabel;
	private JComboBox tdmClocksrcComboBox;
	private JLabel rtpEnableLabel;
	private JComboBox rtpEnableComboBox;
	private JPanel e1InfoPanel;

	public EPanel() throws Exception {
		super("whE1",RootFactory.CORE_MANAGE);
		init();
	}

	private void init() throws Exception {

		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_E1_INFO)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());

	}

	private void setFilterLayout() {
		List<JComponent> defaultButtons = new ArrayList<JComponent>();
		defaultButtons.add(this.getOutputClockSrcComboBox());
		defaultButtons.add(this.getOutputClockSrcLabel());
		defaultButtons.add(this.getRtpEnableLabel());
		defaultButtons.add(this.getRtpEnableComboBox());
		defaultButtons.add(this.getTdmClocksrcLabel());
		defaultButtons.add(this.getTdmClocksrcComboBox());
		removeButton(defaultButtons);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 10, 5);
		this.getE1InfoPanel().setLayout(flowLayout);
		for (int i = 0; i < defaultButtons.size(); i++) {
			this.getE1InfoPanel().add(defaultButtons.get(i));
		}
	}

	private void removeButton(List<JComponent> defaultButtons) {
		List<JButton> needRemoveButtons = setNeedRemoveButtons();
		if (needRemoveButtons != null) {
			for (JButton button : needRemoveButtons) {
				if (defaultButtons.contains(button)) {
					defaultButtons.remove(button);
				}
			}
		}
	}

	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getDeleteButton());
		needButtons.add(getUpdateButton());
		return needButtons;
	}

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	@Override
	public void setController() {
		controller = new E1Contorller(this);
	}

	public JPanel getE1InfoPanel() {
		return e1InfoPanel;
	}

	public void setE1InfoPanel(JPanel e1InfoPanel) {
		this.e1InfoPanel = e1InfoPanel;
	}

	public JLabel getOutputClockSrcLabel() {
		return outputClockSrcLabel;
	}

	public void setOutputClockSrcLabel(JLabel outputClockSrcLabel) {
		this.outputClockSrcLabel = outputClockSrcLabel;
	}

	public JComboBox getOutputClockSrcComboBox() {
		return outputClockSrcComboBox;
	}

	public void setOutputClockSrcComboBox(JComboBox outputClockSrcComboBox) {
		this.outputClockSrcComboBox = outputClockSrcComboBox;
	}

	public JLabel getTdmClocksrcLabel() {
		return tdmClocksrcLabel;
	}

	public void setTdmClocksrcLabel(JLabel tdmClocksrcLabel) {
		this.tdmClocksrcLabel = tdmClocksrcLabel;
	}

	public JComboBox getTdmClocksrcComboBox() {
		return tdmClocksrcComboBox;
	}

	public void setTdmClocksrcComboBox(JComboBox tdmClocksrcComboBox) {
		this.tdmClocksrcComboBox = tdmClocksrcComboBox;
	}

	public JLabel getRtpEnableLabel() {
		return rtpEnableLabel;
	}

	public void setRtpEnableLabel(JLabel rtpEnableLabel) {
		this.rtpEnableLabel = rtpEnableLabel;
	}

	public JComboBox getRtpEnableComboBox() {
		return rtpEnableComboBox;
	}

	public void setRtpEnableComboBox(JComboBox rtpEnableComboBox) {
		this.rtpEnableComboBox = rtpEnableComboBox;
	}

}
