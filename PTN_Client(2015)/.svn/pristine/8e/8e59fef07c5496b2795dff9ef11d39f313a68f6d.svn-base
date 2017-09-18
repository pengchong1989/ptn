/*
 * ShelfTopology.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.topology;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import twaver.AlarmModel;
import twaver.Card;
import twaver.Element;
import twaver.PopupMenuGenerator;
import twaver.Port;
import twaver.Slot;
import twaver.TDataBox;
import twaver.TView;
import twaver.TWaverConst;
import twaver.network.InteractionEvent;
import twaver.network.InteractionListener;
import twaver.network.NetworkToolBarFactory;
import twaver.network.TNetwork;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.CodeConfigItem;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.EquimentDataUtil;
import com.nms.ui.manager.util.TableUtil;
import com.nms.ui.manager.util.TopologyUtil;
import com.nms.ui.manager.xmlbean.CardXml;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.ui.ptn.safety.roleManage.RoleRoot;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.topology.action.MatchingAction;
import com.nms.ui.topology.util.CreateNeTopo;

/**
 * 设备面板图
 * 
 * @author __USER__
 */
public class ShelfTopology extends javax.swing.JPanel {

	private static final long serialVersionUID = -4188070459951756075L;
	private TDataBox box = new TDataBox();
	private TNetwork network = new TNetwork(box);

	/** Creates new form ShelfTopology */
	public ShelfTopology() {
		super(new BorderLayout());
		this.jPanelLayout();
		jPanel1.add(network);

		try {
			this.toolbarButton();
			this.createTopo();
			this.refreshAlarm();

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}

		network.setPopupMenuGenerator(new PopupMenuGenerator() {
			@Override
			public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {
				if (tview.getDataBox().getSelectionModel().size() != 1) {
					return null;
				}
				final Element element = tview.getDataBox().getLastSelectedElement();
				EquimentDataUtil equimentDataUtil = new EquimentDataUtil();
				JPopupMenu menu = new JPopupMenu();
				RoleRoot roleRoot=new RoleRoot();
				if (element instanceof Slot) {
					if (element.getChildren().size() == 0) {
						try {
							final SlotInst slotInst = (SlotInst) element.getUserObject();
							List<CardXml> cardXmlList = equimentDataUtil.getCardMenu(slotInst.getSlotType());
							if (cardXmlList != null) {
								for (final CardXml cardXml : cardXmlList) {
									JMenuItem jMenuItem = new JMenuItem(cardXml.getName());
									roleRoot.setItemEnbale(jMenuItem, RootFactory.CORE_MANAGE);
									jMenuItem.addActionListener(new java.awt.event.ActionListener() {
										@Override
										public void actionPerformed(java.awt.event.ActionEvent evt) {
											try {
												addCard(cardXml.getXmlPath(), slotInst);
												createTopo();
												refreshAlarm();
											} catch (Exception e) {
												ExceptionManage.dispose(e, this.getClass());
											}
										}
									});
									menu.add(jMenuItem);
								}
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
					return menu;
				} else if (element instanceof Card) {
					final CardInst cardInst = (CardInst) element.getUserObject();
					JMenuItem jMenuItem = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_DELETE_CARD));
					roleRoot.setItemEnbale(jMenuItem, RootFactory.CORE_MANAGE);
					jMenuItem.addActionListener(new java.awt.event.ActionListener() {
						@Override
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							try {
								if (verifyPortIsUsedFromCard(cardInst)) {
									return;
								}
								String result = deleteCard(cardInst);
								DialogBoxUtil.succeedDialog(ShelfTopology.this, result);
								createTopo();
								refreshAlarm();
							} catch (Exception e) {
								ExceptionManage.dispose(e, this.getClass());
							}
						}
					});
					menu.add(jMenuItem);
				}
				return menu;
			}
		});

		network.addInteractionListener(new InteractionListener() {
			@Override
			public void interactionPerformed(InteractionEvent event) {
				try {
					MouseEvent mouseEvent = event.getMouseEvent();
					if (mouseEvent.getClickCount() == 1) {
						TableUtil tableUtil = new TableUtil();
						Element element = network.getLastSelectedElement();
						if (element instanceof Port) {
							PortInst portInst = (PortInst) element.getUserObject();
							tableUtil.portTableDate(jTable1, portInst);
						} else {
							tableUtil.portTableDate(jTable1, null);
						}
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}

		});
	}

	/**
	 * 刷新端口告警
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
	@SuppressWarnings("unchecked")
	public void refreshAlarm() throws Exception {
		List<Element> elements = null;
		PortInst portInst = null;
		List<CurrentAlarmInfo> currentAlarmInfoList = null;
		AlarmModel alarmModel = null;
		try {
			// 获取拓扑的告警对象 并清空
			alarmModel = this.box.getAlarmModel();
			alarmModel.clear();

			elements = this.box.getAllElements();

			// 遍历所有元素
			for (Element element : elements) {

				// 如果是端口，查询端口的告警。
				if (element instanceof Port) {

					portInst = (PortInst) element.getUserObject();
					currentAlarmInfoList = this.getAlarm(portInst);
					for (CurrentAlarmInfo currentAlarmInfo : currentAlarmInfoList) {
						currentAlarmInfo.setElementID(element.getID());
						alarmModel.addAlarm(currentAlarmInfo);
					}
				}

			}

		} catch (Exception e) {
			throw e;
		} finally {
			elements = null;
			portInst = null;
			currentAlarmInfoList = null;
			alarmModel = null;
		}

	}

	/**
	 * 根据端口去当前告警中查询告警
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private List<CurrentAlarmInfo> getAlarm(PortInst portInst){
		CurAlarmService_MB curAlarmService = null;
		List<CurrentAlarmInfo> currentAlarmInfoList = new ArrayList<CurrentAlarmInfo>();
		List<Integer> objectIdList = null;
		try {
			if(portInst.getNumber() > 0){
				curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
				// 通过端的A端口和Z端口查询
				objectIdList = new ArrayList<Integer>();
				objectIdList.add(portInst.getNumber());
				currentAlarmInfoList = curAlarmService.select_type_id(EObjectType.PORT.getValue(), objectIdList, ConstantUtil.siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(curAlarmService);
			objectIdList = null;
		}
		return currentAlarmInfoList;
	}

	/**
	 * 创建拓扑界面
	 * 
	 * @throws Exception
	 */
	public void createTopo() throws Exception {
		CreateNeTopo createNeTopo = null;
		try {
			createNeTopo = new CreateNeTopo(this.box, ConstantUtil.siteId);
			createNeTopo.createTopo();
		} catch (Exception e) {
			throw e;
		} finally {
			createNeTopo = null;
		}
	}

	public boolean verifyPortIsUsedFromCard(CardInst cardInst) {
		SegmentService_MB segmentService = null;
		List<Segment> segmentList = null;
		AcPortInfoService_MB acinfoService = null;
		CesInfoService_MB cesInfoService = null;
		
		List<Integer> portIdListisUsedBysegment = null; // 被段使用的端口
		Set<Integer> portIdSetisUsedByAcPort = null; // 被ac使用
		List<AcPortInfo> acInfoList = null;
		
		try {
			portIdListisUsedBysegment = new ArrayList<Integer>();
			// 端口被段使用
			int siteId = cardInst.getSiteId();
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segmentList = segmentService.queryBySiteId(siteId);
			for (Segment segment : segmentList) {
				portIdListisUsedBysegment.add(segment.getAPORTID());
				portIdListisUsedBysegment.add(segment.getZPORTID());
			}
			for (PortInst portInst : cardInst.getPortList()) {
				if (portIdListisUsedBysegment.contains(portInst.getPortId())) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTISUSEDBYSEGMENT));
					return true;
				}
			}

			// 端口被Ac使用
			portIdSetisUsedByAcPort = new HashSet<Integer>();
			acinfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acInfoList = acinfoService.selectBySiteId(siteId);
			for (AcPortInfo acPortInfo : acInfoList) {
				portIdSetisUsedByAcPort.add(acPortInfo.getPortId());
			}
			for (PortInst portInst : cardInst.getPortList()) {
				if (portIdSetisUsedByAcPort.contains(portInst.getPortId())) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTISUSEDBYACPORT));
					return true;
				}
			}

			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			List<CesInfo> cesInfoList = cesInfoService.selectNodeBySite(siteId);
			
			// 端口被ces业务使用
			for(CesInfo  cesInfo : cesInfoList){
				for(PortInst portInst : cardInst.getPortList()){
					if(portInst.getPortId() == cesInfo.getaAcId() || portInst.getPortId() == cesInfo.getzAcId()){
						DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_IS_USEDBY_CES));
						return true;
					}
				}
			}
			
			// // 端口被pwNNI使用
			// portIdSetisUsedByPwNNI = new HashSet<Integer>();
			// pwNniBufService = (PwNniBufferService) ConstantUtil.serviceFactory.newService(Services.PwNniBuffer);
			// pwNNiInfo = new PwNniInfo();
			// pwNNiInfo.setSiteId(siteId);
			// pwNNIInfoList = pwNniBufService.select(pwNNiInfo);
			// for (PwNniInfo pwnni : pwNNIInfoList) {
			// portIdSetisUsedByPwNNI.add(pwnni.getPortId());
			// }
			// for (PortInst portInst : cardInst.getPortList()) {
			// if (portIdSetisUsedByPwNNI.contains(portInst.getPortId())) {
			// DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTISUSEDBYPWNNI));
			// return true;
			// }
			// }

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(segmentService);
			UiUtil.closeService_MB(acinfoService);
			UiUtil.closeService_MB(cesInfoService);
		}
		return false;
	}

	/**
	 * 卸载板卡
	 */
	public String deleteCard(CardInst cardInst) {
		DispatchUtil cardDispatch = null;
		String result = null;
		try {
			cardDispatch = new DispatchUtil(RmiKeys.RMI_CARD);
			result = cardDispatch.excuteDelete(cardInst);
			/**
			 * 没有按钮 ，直接添加日志记录
			 */		
			this.insertOpeLog(EOperationLogType.CARDDELETE.getValue(), result, null, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cardDispatch = null;
		}
		return result;
	}

	/**
	 * 插卡
	 * 
	 * @param xmlPath
	 * @param slotInst
	 * @throws Exception
	 */
	public void addCard(String xmlPath, SlotInst slotInst) throws Exception {
		CardInst cardInst = null;
		EquimentDataUtil equimentDataUtil = new EquimentDataUtil();
		try {
			cardInst = equimentDataUtil.addCard(xmlPath, slotInst);
			//查询板卡序列号
			if(CodeConfigItem.getInstance().getSnmpStartOrClose() == 1){
				this.querySN(cardInst);
			}
			DispatchUtil cardDispatch = new DispatchUtil(RmiKeys.RMI_CARD);
			String result = cardDispatch.excuteInsert(cardInst);
			/**
			 * 没有按钮 ，直接添加日志记录
			 */
			this.insertOpeLog(EOperationLogType.CARDINSERT.getValue(), result, null, cardInst);		
			DialogBoxUtil.succeedDialog(this, result);

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	private void insertOpeLog(int operationType, String result, CardInst oldCard, CardInst newCard){
		SiteService_MB service = null;
		String siteName="";
		try {
			if(newCard!=null){
			   newCard.setSlotNum(newCard.getSlotInst().getNumber());			  
			}
			 service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);			
			 siteName=service.getSiteName(ConstantUtil.siteId);
		    AddOperateLog.insertOperLog(null, operationType, result, oldCard, newCard, ConstantUtil.siteId,siteName,"addcard");		
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	/**
	 *查询板卡sn并入库 
	 * @param cardInst 
	 */
	private void querySN(CardInst cardInst) {
		SiteService_MB siteService = null;
		CardService_MB cardService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst site = siteService.select(ConstantUtil.siteId);
			if(site != null){
				String sn = site.getSn();
				if(sn == null || "".equals(sn)){
					DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					List<SiteInst> siteList = siteDispatch.querySn(site, 1);
					if(siteList != null && siteList.size() > 0){
						sn = siteList.get(0).getSn();
					}
				}
				if(sn != null && !"".equals(sn)){
					cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
					CardInst card = new CardInst();
					card.setSiteId(ConstantUtil.siteId);
					card.setCardName(cardInst.getCardName());
					List<CardInst> cardList = cardService.select(card);
					String cardName = cardInst.getCardName();
					if("703-2_CARD".equals(cardName)){
						sn += "1";
					}else if("XCTS1".equals(cardName)){
						sn += "1";
					}else if("SP16".equals(cardName)){
						if(cardList != null){
							if(cardList.size() == 0){
								sn += "12";
							}else{
								sn += "13";
							}
						}
					}else if("IPG8".equals(cardName)){
						if(cardList != null){
							if(cardList.size() == 0){
								sn += "22";
							}else{
								sn += "23";
							}
						}
					}else if("FAN".equals(cardName)){
						sn += "33";
					}else if("PWR".equals(cardName)){
						if(cardList != null){
							if(cardList.size() == 0){
								sn += "43";
							}else{
								sn += "53";
							}
						}
					}
					cardInst.setInstalledSerialNumber(sn);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(cardService);
		}
	}

	/**
	 * 加自动匹配按钮
	 */
	private void toolbarButton() {
		// 设备ToolBar
		String[] ids = new String[] { "Selection", "LazyMove", "Magnifier", "Pan", "Up", "ZoomIn", "ZoomOut", TWaverConst.TOOLBAR_ZOOMTOOVERVIEW, "ZoomToRectangle", "ZoomReset", "OverView", "FullScreen" };

		JToolBar toolBar = NetworkToolBarFactory.getToolBar(ids, network);
		// toolBar.setEnabled(RoleRoot.root(RootFactory.CORE_MANAGE));
		TopologyUtil topoloyUtil = new TopologyUtil();
		topoloyUtil.createTopoButton("/com/nms/ui/images/topo/matching.png", new MatchingAction(this), toolBar, ResourceUtil.srcStr(StringKeysObj.AUTO_MATCHING));
		// UiUtil.createTopoButton("/com/nms/ui/images/topo/matching.png", new MatchingAction(this), toolBar, ResourceUtil.srcStr(StringKeysObj.AUTO_MATCHING));
		network.setToolbar(toolBar);
	}

	public TDataBox getTDataBox() {
		return this.box;
	}

	public void jPanelLayout() {
		jPanel1 = new javax.swing.JPanel(new BorderLayout());
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		setPreferredSize(new java.awt.Dimension(1000, 550));

		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jTable1.setModel(new javax.swing.table.DefaultTableModel(null, new String[] { ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE), ResourceUtil.srcStr(StringKeysObj.STRING_VALUE) }) {
			boolean[] canEdit = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setRowHeight(25);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTable1);

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(18, 18, 18).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}

	// GEN-END:initComponents

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables

}