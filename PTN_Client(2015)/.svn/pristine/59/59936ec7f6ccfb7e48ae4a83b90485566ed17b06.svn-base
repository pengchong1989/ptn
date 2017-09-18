package com.nms.ui.ptn.business.serviceRepaired;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import twaver.Element;
import twaver.TDataBox;
import twaver.list.TList;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class PortMovedDialog extends PtnDialog {
	private static final long serialVersionUID = -6907244028557028956L;
	private JLabel currSiteLabel = null;//当前网元
	private JComboBox currSiteCmb = null;
	private JLabel sourcePortLabel = null;//原端口
	private JLabel selectedEndPortLabel = null;//已选目的端口
	private JLabel endPortLabel = null;//可选目的端口
	private TList sourcePortTList = null;
	private JScrollPane sourcePortScrollPanel = null;
	protected TDataBox sourcePortBox = new TDataBox();//原端口
	protected TDataBox needChooseBox = new TDataBox();//可选端口
	private TList needChooseList = null;
	protected TDataBox choosedBox = new TDataBox();//已选端口
	private TList choosedList = null;
	private JButton addButton = null;
	private JButton removeButton = null;
	private JPanel controlPane = null;
	private JScrollPane needChooseScrollPane = null;
	private JScrollPane choosedScrollPane = null;
	private PtnButton confirm = null;
	private JButton cancel = null;
	private JPanel bottomPanel = null;
	protected SiteInst siteInst = null;//当前网元
	protected List<PortInst> sourcePortList = new ArrayList<PortInst>();
	protected List<PortInst> selectedPortList = new ArrayList<PortInst>();
	private Comparator<Element> comparator = new Comparator<Element>() {
		@Override
		public int compare(Element e1, Element e2) {
			Integer id1 = (Integer) e1.getID();
			Integer id2 = (Integer) e2.getID();
			return id1.compareTo(id2);
		}
	};

	public PortMovedDialog() {
		this.setModal(true);
		this.init();
		this.needChooseList.setSortComparator(comparator);
	}

	private void init() {
		this.initComponents();
		this.setLayout();
		this.addActionLister();
		this.initSiteCombBox();
	}

	private void initSiteCombBox() {
		SiteService_MB service = null;
		try {
			service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteList = service.select();
			if(siteList != null){
				for (SiteInst site : siteList) {
//					if(site.getLoginstatus() == 1){
						ControlKeyValue keyValue = new ControlKeyValue(site.getSite_Inst_Id()+"", site.getCellId(), site);
						this.currSiteCmb.addItem(keyValue);
//					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	@SuppressWarnings("unchecked")
	private void addActionLister() {
		this.addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				move(needChooseBox.getSelectionModel().getAllSelectedElement(), needChooseBox, choosedBox);
			}
		});
		
		this.removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				move(choosedBox.getSelectionModel().getAllSelectedElement(), choosedBox, needChooseBox);
			}
		});

		this.cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		this.confirm.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				confirmActionListener();
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		
		this.currSiteCmb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedSiteActionListener();
			}
		});
	}

	/**
	 * 当选中某个网元时，界面所有数据都要清空
	 */
	protected void selectedSiteActionListener() {
		this.siteInst = (SiteInst) ((ControlKeyValue)this.currSiteCmb.getSelectedItem()).getObject();
		this.sourcePortBox.clear();
		this.choosedBox.clear();
		this.needChooseBox.clear();
	}

	protected void confirmActionListener() {
	}

	/**
	 * 数据校验
	 * 1 原端口不能为空，已选端口不能为空
	 * 2 原端口的数量等于已选端口的数量
	 */
	@SuppressWarnings("unchecked")
	protected boolean validated() {
		if(this.siteInst == null){
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NO_SELECTED_SITE));
			return false;
		}
		List<Element> elementList = this.sourcePortBox.getAllElements();
		for (Element element : elementList) {
			if(element.isSelected()){
				this.sourcePortList.add((PortInst) element.getUserObject());
			}
		}
		elementList = this.choosedBox.getAllElements();
		for (Element element : elementList) {
			if(element.isSelected()){
				this.selectedPortList.add((PortInst) element.getUserObject());
			}
		}
		if(this.sourcePortList.isEmpty()){
			this.clearData();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SOURCE_PORT_IS_NULL));
			return false;
		}
		if (this.selectedPortList.isEmpty()) {
			this.clearData();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_END_PORT_IS_NULL));
			return false;
		}
		if(this.sourcePortList.size() != this.selectedPortList.size()){
			this.clearData();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SOURCE_END_PORT_NOT_EQUAL));
			return false;
		}
		if(this.sourcePortList.size() != 1){
			this.clearData();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_OPERATION_PORT_ONCE));
			return false;
		}
		if(!this.checkPortSpeed(this.sourcePortList.get(0), this.selectedPortList.get(0))){
			this.clearData();
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SAME_SPEED_PROT));
			return false;
		}
		return true;
	}

	private void clearData() {
		this.sourcePortList.clear();
		this.selectedPortList.clear();
	}

	/**
	 * 比较原端口和目的端口的速率是否相同
	 * 相同/不相同 = true/false
	 */
	private boolean checkPortSpeed(PortInst sourPortInst, PortInst endPortInst) {
		SegmentService_MB service = null;
		try {
			service = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			return service.comparePortSpeed(sourPortInst, endPortInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return false;
	}

	private void move(List<Element> elements, TDataBox source, TDataBox target) {
		Collections.sort(elements, comparator);
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (!element.isSelected()) {
				it.remove();
			}
			source.removeElement(element);
			target.addElement(element);
		}
		if (elements.size() > 0) {
			target.getSelectionModel().setSelection(elements);
		}
	}

	private void setLayout() {
		this.initControlPanel();
		this.initBottomPanel();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {130, 130, 30, 130 };
		layout.columnWeights = new double[] { 0.5, 0, 0.5 };
		layout.rowHeights = new int[] { 10, 10, 250, 30 };
		layout.rowWeights = new double[] { 1.0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		//第一行 当前网元下拉框
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.currSiteLabel, c);
		this.add(this.currSiteLabel);
		c.gridx = 1;
		layout.setConstraints(this.currSiteCmb, c);
		this.add(this.currSiteCmb);
		//第二行 源端口 已选目的端口 可选目的端口
		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(this.sourcePortLabel, c);
		this.add(this.sourcePortLabel);
		c.gridx = 1;
		layout.setConstraints(this.selectedEndPortLabel, c);
		this.add(this.selectedEndPortLabel);
		c.gridx = 3;
		layout.setConstraints(this.endPortLabel, c);
		this.add(this.endPortLabel);
		//第三行  源端口/目的端口选择框
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		layout.setConstraints(this.sourcePortScrollPanel, c);
		this.add(this.sourcePortScrollPanel);
		c.gridx = 1;
		layout.setConstraints(this.choosedScrollPane, c);
		this.add(this.choosedScrollPane);
		c.gridx = 2;
		c.insets = new Insets(5, 0, 5, 0);
		layout.setConstraints(this.controlPane, c);
		this.add(this.controlPane);
		c.gridx = 3;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.needChooseScrollPane, c);
		this.add(this.needChooseScrollPane);
		//第四行 按钮面板
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.EAST;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(this.bottomPanel, c);
		this.add(this.bottomPanel);
	}

	private void initControlPanel() {
		GridBagLayout controlLayout = new GridBagLayout();
		controlLayout.columnWidths = new int[] { 25 };
		controlLayout.columnWeights = new double[] { 0.0 };
		controlLayout.rowHeights = new int[] { 5, 5, 5, 5 };
		controlLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		controlPane.setLayout(controlLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(addButton, c);
		controlPane.add(addButton);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(removeButton, c);
		controlPane.add(removeButton);
	}

	private void initBottomPanel() {
		GridBagLayout layout = new GridBagLayout();
		bottomPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(confirm, c);
		bottomPanel.add(confirm);
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(cancel, c);
		bottomPanel.add(cancel);
	}

	private void initComponents() {
		this.sourcePortLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_PORT));
		this.selectedEndPortLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECTED_END_PORT));
		this.endPortLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_END_PORT));
		this.currSiteLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECTED_SITE));
		this.currSiteCmb = new JComboBox();
		this.sourcePortTList = new TList(sourcePortBox);
		this.sourcePortTList.setTListSelectionMode(TList.CHECK_SELECTION);
		this.sourcePortTList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.sourcePortScrollPanel = new JScrollPane();
		this.sourcePortScrollPanel.setViewportView(sourcePortTList);
		
		this.needChooseBox = new TDataBox("NeedChooseList");
		this.needChooseList = new TList(needChooseBox);
		this.needChooseList.setTListSelectionMode(TList.CHECK_SELECTION);

		this.choosedBox = new TDataBox("ChoosedList");
		this.choosedList = new TList(choosedBox);
		this.choosedList.setTListSelectionMode(TList.CHECK_SELECTION);

		this.addButton = new JButton("<");
		this.removeButton = new JButton(">");
		this.controlPane = new JPanel();
		this.needChooseScrollPane = new JScrollPane(needChooseList);
		this.needChooseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.needChooseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.needChooseScrollPane.setViewportView(needChooseList);

		this.choosedScrollPane = new JScrollPane(choosedList);
		this.choosedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.choosedScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.choosedScrollPane.setViewportView(choosedList);
		this.confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE), true, RootFactory.CORE_MANAGE, this);
		this.cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.bottomPanel = new JPanel();
	}

	protected JButton getConfirm() {
		return confirm;
	}

	protected JButton getCancel() {
		return cancel;
	}
}
