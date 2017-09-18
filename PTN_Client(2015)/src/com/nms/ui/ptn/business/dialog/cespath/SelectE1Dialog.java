package com.nms.ui.ptn.business.dialog.cespath;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import twaver.Card;
import twaver.DataBoxSelectionAdapter;
import twaver.DataBoxSelectionEvent;
import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.tree.TTree;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EManagerStatus;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.dialog.cespath.modal.CesPortInfo;

public class SelectE1Dialog extends PtnDialog{
	private static final long serialVersionUID = -2007113486876034197L;
	private final TDataBox treebox = new TDataBox();
	private TTree tree;
	private JScrollPane jScrollPane;
	private JSplitPane jsplitPane;
	private JPanel centerPanel;
	private JPanel jcheJPanel;
	private JPanel leftPanel;
	private JPanel btnPanel;
	private final String type;
	private JButton okBtn;
	private JButton cancelBtn;
	private final AddCESAllDialog addCesDialog;
	private final SiteInst siteInst;
	private Map<JCheckBox, PortInst> jcbAndE1PortSlotMap = null;
	private List<JCheckBox> jcbList = null;

	public SelectE1Dialog(JDialog parent, boolean modal , String type, SiteInst siteInst) {
		super();
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_SELECT_E1_PORT));
		this.setModal(modal);	
		this.type = type;
		this.siteInst = siteInst;
		this.addCesDialog = (AddCESAllDialog) parent;
        this.initComponet();
        this.setLayout();
        this.addActionListener();
        this.initData();
        this.setSize(new Dimension(650, 450));
        this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
        this.setVisible(true);
	}

	private void initComponet() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tree = new TTree(treebox);
		tree.setRootVisible(false);
		jScrollPane = new JScrollPane(tree);
		centerPanel = new JPanel(new BorderLayout());
		jcheJPanel = new JPanel();
		leftPanel = new JPanel(new BorderLayout());
		btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(400, 80));
		okBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
	}

	private void setLayout() {
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		btnPanel.setLayout(fl);
		btnPanel.add(okBtn);
		btnPanel.add(cancelBtn);
		
		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.add(jcheJPanel , BorderLayout.CENTER);
		
		leftPanel.add(jScrollPane , BorderLayout.CENTER);
		jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, centerPanel);
		jsplitPane.setDividerLocation(150);

		this.setLayout(new BorderLayout());
		this.add(btnPanel, BorderLayout.SOUTH);
		this.add(jsplitPane , BorderLayout.CENTER);
	}
	
	private void addActionListener() {
		this.treebox.getSelectionModel().addDataBoxSelectionListener(new DataBoxSelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void selectionChanged(DataBoxSelectionEvent e) {
				Element element = treebox.getLastSelectedElement();
				List<PortInst> portInstList = null;
				if (element instanceof Card) {
					try {
						portInstList = (List<PortInst>) element.getUserObject();
						initE1Port(portInstList);
					} catch (Exception e1) {
						ExceptionManage.dispose(e1, this.getClass());
					}
				}
			}
		});

		this.okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<PortInst> portInstList = new ArrayList<PortInst>();
				if(jcbList != null){
					for (JCheckBox jcb : jcbList) {
						if (jcb.isSelected()) {
							portInstList.add(jcbAndE1PortSlotMap.get(jcb));
						}
					}
				}
				List<CesPortInfo> cesPortInfoList = new ArrayList<CesPortInfo>();
				CesPortInfo cesPort = null;
				for (PortInst e1 : portInstList) {
					cesPort = new CesPortInfo();
					cesPort.setE1PortInst(e1);
					cesPortInfoList.add(cesPort);
				}
				// 拿出端口对应的网元
				int siteId = 0;
				if (!portInstList.isEmpty()) {
					siteId = portInstList.get(0).getSiteId();
				}
				
				if (type.equalsIgnoreCase("A")) {
					if(addCesDialog.getzSiteId() == siteId){
						addCesDialog.getPortTable_z().clear();
					}
					// 将选择的端口放到A端表中
					addCesDialog.loadPortTable_a(cesPortInfoList, siteId);
					
				} else if (type.equalsIgnoreCase("Z")) {
					if(addCesDialog.getaSiteId() == siteId){
						addCesDialog.getPortTable_a().clear();
					}
					// 将选择的端口放到Z端表中
					addCesDialog.loadPortTable_z(cesPortInfoList, siteId);
				}
				dispose();
			}

		});
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	private void initE1Port(List<PortInst> portInstList) {
		JCheckBox jcb = null;
		if (jcbAndE1PortSlotMap != null) {
			jcbAndE1PortSlotMap.clear();
		}
		if (jcbList != null) {
			jcbList.clear();
		}
		if (portInstList != null && !portInstList.isEmpty()) {
			jcbAndE1PortSlotMap = new HashMap<JCheckBox, PortInst>();
			jcbList = new ArrayList<JCheckBox>();
			for (int i = 0; i < portInstList.size(); i++) {
				jcb = new JCheckBox(portInstList.get(i).getPortName());
				jcbList.add(jcb);
				jcbAndE1PortSlotMap.put(jcb, portInstList.get(i));
			}
			// 把创建的E1放到对话框的面板里
			placeE1JCheck();
		}
	}

	private void placeE1JCheck() {
		this.getJcheJPanel().removeAll();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 50, 50, 50, 50 };
		layout.columnWeights = new double[] { 0, 0, 0, 0 };
		layout.rowHeights = new int[] { 30, 30, 30, 30 };
		layout.rowWeights = new double[] { 0, 0, 0, 0 };
		this.getJcheJPanel().setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 5);
		int index = 0;
		int row = 0;
		int length = jcbList.size();
		if (length % 4 == 0) {
			row = length / 4;
		} else {
			row = length / 4 + 1;
		}
		for (int i = 0; i < row; i++) {
			for (int col = 0; col < 4; col++) {
				c.gridx = col;
				c.gridy = i;
				layout.setConstraints(jcbList.get(index), c);
				this.getJcheJPanel().add(jcbList.get(index));
				index++;
			}
		}
		setCheckBoxIsEnable();
		this.getCenterPanel().updateUI();
		this.getJcheJPanel().updateUI();

	}

	// 如果该e1被用了或者是未使能状态，不能选择
	private void setCheckBoxIsEnable() {
		PortInst e1Port = null;
		try {
			for (JCheckBox jcb : jcbList) {
				jcb.setEnabled(false);
				e1Port = jcbAndE1PortSlotMap.get(jcb);
				if (e1Port.getIsEnabled_code() == EManagerStatus.ENABLED.getValue() && e1Port.getIsOccupy() == 0) {
					jcb.setEnabled(true);
				}
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			e1Port = null;
		}

	}
	
	public void initData() {
		PortService_MB portService = null;
		PortInst portinst = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portinst = new PortInst();
			portinst.setPortType("e1");
			portinst.setSiteId(this.siteInst.getSite_Inst_Id());
			List<PortInst> portList = portService.select(portinst);
			initTreeData(portList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			portinst=null;
			UiUtil.closeService_MB(portService);
		}

	}

	private void initTreeData(List<PortInst> e1ortInst) {
		Map<Integer, List<PortInst>> cardIdAndPortListMap = new HashMap<Integer, List<PortInst>>();
		Set<Integer> cardIdSet = new HashSet<Integer>();
		List<PortInst> portInstList = null;
		
		for (PortInst portInst : e1ortInst) {
			cardIdSet.add(portInst.getCardId());
			if (cardIdAndPortListMap.get(portInst.getCardId()) == null) {
				portInstList = new ArrayList<PortInst>();
				cardIdAndPortListMap.put(portInst.getCardId(), portInstList);
			}
			portInstList = cardIdAndPortListMap.get(portInst.getCardId());
			portInstList.add(portInst);
			cardIdAndPortListMap.put(portInst.getCardId(), portInstList);
		}
		Node nodeParent = new Node();
		this.getTreebox().addElement(nodeParent);
		nodeParent.setName("NE:" + siteInst.getCellId());
		Card card = null;

		for (Integer cardId : cardIdSet) {
			card = new Card(cardId);
			card.setParent(nodeParent);
			card.setName("card:" + cardId);
			card.setUserObject(cardIdAndPortListMap.get(cardId));
			this.getTreebox().addElement(card);
		}
	}
	
	public String getType() {
		return type;
	}

	public TDataBox getTreebox() {
		return treebox;
	}

	public JButton getOkBtn() {
		return okBtn;
	}

	public JButton getCancelBtn() {
		return cancelBtn;
	}

	
	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public JPanel getJcheJPanel() {
		return jcheJPanel;
	}
}
