package com.nms.ui.ptn.business.dialog.elanpath;

/*
 * EquipmentDialog.java
 *
 * Created on __DATE__, __TIME__
 */

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.WindowConstants;

import twaver.Card;
import twaver.Element;
import twaver.Port;
import twaver.Rack;
import twaver.Slot;
import twaver.TDataBox;
import twaver.network.InteractionEvent;
import twaver.network.InteractionListener;
import twaver.network.TNetwork;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.EquipInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.system.Field;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.TableUtil;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class ElanTopology extends javax.swing.JDialog {

	private final TDataBox box = new TDataBox();
	private final TNetwork network = new TNetwork(box);
	private SiteInst siteInst = null;

	/** Creates new form EquipmentDialog */
	public ElanTopology(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public ElanTopology(boolean modal, final SiteInst siteInst, final String type, final Element element) {
		this.setModal(modal);
		try {
			this.panelLayout();
			this.siteInst = siteInst;
			this.createTopo();
			this.jPanel1.add(network);
			this.setModal(modal);
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setBounds(0, 0, 900, 400);

			network.addInteractionListener(new InteractionListener() {
				@Override
				public void interactionPerformed(InteractionEvent event) {
					try {
						MouseEvent mouseEvent = event.getMouseEvent();
						Element element = network.getLastSelectedElement();
						PortInst portInst = null;
						if (element instanceof Port) {
							portInst = (PortInst) element.getUserObject();
							if (mouseEvent.getClickCount() == 2) {
								if (portInst.getIsOccupy() == 0) {
									setPath(siteInst, portInst, type);
									dispose();
								} else {
									tipPort();
								}
							} else {
								TableUtil tableUtil = new TableUtil();
								tableUtil.portTableDate(jTable1, portInst);
							}
						}

					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			});

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void tipPort() {
		DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORT_OCCUPY));
	}

	/**
	 * 给tunnel面板上的控件赋值
	 * 
	 * @param siteInst
	 * @param portInst
	 * @param type
	 */
	private void setPath(SiteInst siteInst, PortInst portInst, String type) {
		Field field = null;
		FieldService_MB fieldService = null;
		List<Field> fieldList = null;

		try {
			field = new Field();
			field.setId(siteInst.getFieldID());
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fieldList = fieldService.select(field);
			if (fieldList == null || fieldList.size() != 1) {
				throw new Exception(ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_FIELD_ERROR));
			}
			// AddElanDialog.getDialog();
			/*
			 * if (type.equals("A")) { addpwdialog.setPortInst_a(portInst); addpwdialog.setAText(text); } else if(type.equals("Z")){ addpwdialog.setPortInst_z(portInst); addpwdialog.setZText(text); }
			 */

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(fieldService);
		}
	}

	/**
	 * 创建拓扑界面
	 * 
	 * @throws Exception
	 */
	public void createTopo() throws Exception {
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		Rack rack = null;
		Slot slot = null;
		Card card = null;
		Port port = null;
		try {
			box.clear();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(this.siteInst.getSite_Inst_Id());
			siteInst = siteService.select(siteInst).get(0);

			rack = this.createRack(siteInst.getEquipInst());
			box.addElement(rack);
			for (SlotInst slotInst : siteInst.getEquipInst().getSlotlist()) {
				slot = this.createSlot(slotInst, rack);
				box.addElement(slot);

				if (null != slotInst.getCardInst()) {
					card = this.createCard(slotInst.getCardInst(), slot);
					box.addElement(card);
					if (slotInst.getCardInst().getPortList() != null) {
						for (PortInst portInst : slotInst.getCardInst().getPortList()) {
							port = this.createPort(portInst, card);
							box.addElement(port);
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 创建机架
	 * 
	 * @param equipInst
	 * @return
	 */
	private Rack createRack(EquipInst equipInst) {
		Rack rack = new Rack();
		rack.setImage(equipInst.getImagePath());
		rack.setLocation(equipInst.getEquipx(), equipInst.getEquipy());
		rack.setUserObject(equipInst);
		return rack;
	}

	/**
	 * 创建卡槽
	 * 
	 * @param slotInst
	 * @param rack
	 * @return
	 */
	private Slot createSlot(SlotInst slotInst, Rack rack) {
		Slot slot = new Slot();
		slot.setImage(slotInst.getImagePath());
		slot.setLocation(slotInst.getSlotx(), slotInst.getSloty());
		slot.setParent(rack);
		slot.setUserObject(slotInst);
		return slot;
	}

	/**
	 * 创建板卡
	 * 
	 * @param cardInst
	 * @return
	 */
	private Card createCard(CardInst cardInst, Slot slot) {

		Card card = new Card();
		card.setImage(cardInst.getImagePath());
		card.setLocation(cardInst.getCardx(), cardInst.getCardy());
		card.setParent(slot);
		card.setUserObject(cardInst);
		return card;
	}

	/**
	 * 创建端口
	 * 
	 * @param portInst
	 * @param card
	 * @return
	 */
	private Port createPort(PortInst portInst, Card card) {
		Port port = new Port();
		port.setImage(portInst.getImagePath());
		port.setLocation(portInst.getPortx(), portInst.getPorty());
		port.setParent(card);
		port.setUserObject(portInst);
		return port;
	}

	private void panelLayout() {

		jPanel1 = new javax.swing.JPanel(new BorderLayout());
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE), ResourceUtil.srcStr(StringKeysObj.STRING_VALUE) }) {
			boolean[] canEdit = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setRowHeight(25);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 618, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 396, Short.MAX_VALUE));

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE), ResourceUtil.srcStr(StringKeysObj.STRING_VALUE) }) {
			boolean[] canEdit = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setRowHeight(25);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ElanTopology dialog = new ElanTopology(new javax.swing.JFrame(), true);
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

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables

}