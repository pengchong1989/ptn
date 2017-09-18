package com.nms.ui.ptn.performance.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import twaver.Node;
import twaver.SubNetwork;
import twaver.TDataBox;
import twaver.combobox.TComboBox;
import twaver.list.TList;
import twaver.tree.TTree;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.util.TopologyUtil;

public class PortSelectDialog extends PtnDialog {
	private static final long serialVersionUID = 9038419319389719031L;
	private JLabel siteLabel;
	private JLabel portLabel;
	private JLabel portListLabel;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox allSelectCheckBox;// 全选
	private TComboBox siteComboBox;// 网元下拉框
	private TComboBox portComboBox;// 端口下拉框
	private TDataBox siteDataBox;
	private TTree siteTree;// 网元树
	private TDataBox portDataBox;
	private TTree portTree;// 端口树
	private TDataBox portListDataBox;
	private TList portList;// 网元列表
	private JScrollPane portListScrollPane;
	private JPanel buttonPanel;

	public PortSelectDialog() {
		this.setModal(true);
		init();
	}

	private void init() {
		initComponents();
		setLayout();
		initData();
		// siteDataBox.clear();
		// UiUtil.initTree(siteDataBox);
	}

	private void initData() {
		siteDataBox.clear();
		FieldService_MB fieldService = null;
		List<Field> fieldList = null;
		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		SubNetwork domain = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldList = fieldService.select();
			for (int i = 0; i < fieldList.size(); i++) {
				domain = createDomain(fieldList.get(i));
				siteDataBox.addElement(domain);
//				siteService = (SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);
				SiteInst siteInst = new SiteInst();
				siteInst.setFieldID(fieldList.get(i).getId());
				siteInstList = siteService.select(siteInst);
				for (SiteInst site : siteInstList) {
					siteDataBox.addElement(createSite(domain, site));
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(siteService);
		}
	}

	private SubNetwork createDomain(Field field) {
		SubNetwork domain = null;
		try {
			Color color = new Color(100, 100, 240);
			domain = new SubNetwork(field.getFieldName());
			domain.setName(field.getFieldName());
			domain.setLocation(field.getFieldX(), field.getFieldY());
			domain.putLabelColor(color);
			domain.setUserObject(field);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {

		}
		return domain;
	}

	private Node createSite(SubNetwork parent, SiteInst siteInst) {
		Node node = null;
		TopologyUtil topologyUtil=new TopologyUtil();
		try {
			node = new Node();
			node.setName(siteInst.getCellId());
			node.setLocation(siteInst.getSiteX(), siteInst.getSiteY());
			node.setParent(parent);
			topologyUtil.setNodeImage(node, siteInst);
			node.setUserObject(siteInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return node;
	}

	private void initComponents() {
		TopologyUtil topologyUtil=new TopologyUtil();
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_PORT_SELECT));
		siteLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.NET_BASE));
		portLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT));
		portListLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_LIST));
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		allSelectCheckBox = new JCheckBox(ResourceUtil.srcStr(StringKeysBtn.BTN_ALLSELECT));

		// --------------此处略去1W字-----
		siteDataBox = new TDataBox("siteDataBox");
		siteTree = new TTree(siteDataBox);
		siteTree.setRootVisible(false);
		siteTree.setTTreeSelectionMode(TTree.CHECK_CHILDREN_SELECTION);
		siteTree.setCheckableFilter(topologyUtil.SiteInstFilter);
		siteTree.getDataBox().getSelectionModel().clearSelection();

		portDataBox = new TDataBox("portDataBox");
		portTree = new TTree(portDataBox);
		portTree.setRootVisible(false);
		portTree.setTTreeSelectionMode(TTree.CHECK_CHILDREN_SELECTION);

		portListDataBox = new TDataBox("portListDataBox");
		portList = new TList(portListDataBox);
		portList.setTListSelectionMode(TList.DEFAULT_SELECTION);

		siteComboBox = new TComboBox(siteDataBox);
		portComboBox = new TComboBox();
		// -----------------------结束
		portListScrollPane = new JScrollPane(portList);
		portListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		portListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		portListScrollPane.setViewportView(portList);

		buttonPanel = new JPanel();

	}

	private void setLayout() {
		setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 10, 40, 40 };
		layout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
		layout.rowHeights = new int[] { 20, 20, 40, 10, 20 };
		layout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0 };
		this.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(15, 10, 5, 10);
		layout.setConstraints(siteLabel, c);
		this.add(siteLabel);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(15, 10, 5, 10);
		layout.setConstraints(siteComboBox, c);
		this.add(siteComboBox);
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 10);
		layout.setConstraints(portLabel, c);
		this.add(portLabel);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 10);
		layout.setConstraints(portComboBox, c);
		this.add(portComboBox);
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 10);
		layout.setConstraints(portListLabel, c);
		this.add(portListLabel);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 10);
		layout.setConstraints(portListScrollPane, c);
		this.add(portListScrollPane);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 0, 5, 10);
		c.fill = GridBagConstraints.WEST;
		layout.setConstraints(allSelectCheckBox, c);
		this.add(allSelectCheckBox);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 10, 10);
		layout.setConstraints(buttonPanel, c);
		this.add(buttonPanel);

	}

	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.rowHeights = new int[] { 10 };
		buttonLayout.rowWeights = new double[] { 0.0 };
		buttonLayout.columnWidths = new int[] { 10, 10, 10, 10 };
		buttonLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0 };
		buttonPanel.setLayout(buttonLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.EAST;
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 5, 5, 5);
		buttonLayout.setConstraints(confirm, c);
		buttonPanel.add(confirm);
		c.fill = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.insets = new Insets(0, 5, 5, 0);
		buttonLayout.setConstraints(cancel, c);
		buttonPanel.add(cancel);

	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				PortSelectDialog dialog = new PortSelectDialog();
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

}
