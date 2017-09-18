/*
 * EquipmentDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.basicinfo.dialog.segment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.WindowConstants;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.Port;
import twaver.TDataBox;
import twaver.TWaverConst;
import twaver.network.InteractionEvent;
import twaver.network.InteractionListener;
import twaver.network.TNetwork;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.TableUtil;
import com.nms.ui.topology.util.CreateNeTopo;

/**
 * 显示段的 对话框
 * @author __USER__
 */
public class SegmentDiaLog extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  TDataBox box = new TDataBox();
	private  TNetwork network = new TNetwork(box);
	private SiteInst siteInst = null;
	public static Link link = null;

	/** Creates new form EquipmentDialog */
	public SegmentDiaLog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public SegmentDiaLog(boolean modal, final SiteInst siteInst, final String type, Element element,final AddSegment dialog) {
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
						if (element instanceof Port) {
							PortInst portInst = (PortInst) element.getUserObject();
							if (mouseEvent.getClickCount() == 2) {
								if (portInst.getIsOccupy() == 0) {
									if (portInst.getPortType().equals("NNI") && portInst.getIsEnabled_code() == 1) {
										setPath(siteInst, portInst, type,dialog);
										dispose();
									} else {
										DialogBoxUtil.errorDialog(SegmentDiaLog.this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTTYPEANDENABLE));
										UiUtil.insertOperationLog(EOperationLogType.ADDSEGMENT9.getValue());
									}
								} else {
									tipPort();
								}
							} else {
								TableUtil tableUtil=new TableUtil();
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

	public TNetwork getNetwork() {
		return network;
	}

	private void tipPort() {
		DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORT_OCCUPY));
		UiUtil.insertOperationLog(EOperationLogType.ADDSEGMENT10.getValue());
	}

	/**
	 * 给Segment面板上的控件赋�?
	 * 
	 * @param siteInst
	 * @param portInst
	 * @param type
	 */
	@SuppressWarnings("unchecked")
	private void setPath(SiteInst siteInst, PortInst portInst, String type,AddSegment dialog) {
		String text = null;
		AddSegment addsegment = null;
		try {
			text= portInst.getPortName();
			addsegment = dialog;

			if (type.equals("A")) {				
				//SegmentTopology.getTopology();
				if (dialog.getSegementTopology().getNode_z() != null && dialog.getSegementTopology().getNode_z().getUserObject() == siteInst) {
					addsegment.setZText("");
					addsegment.setPortInst_z(null);
					//SegmentTopology.getTopology();
					dialog.getSegementTopology().getNode_z().removeAttachment("topoTitle");
					dialog.getSegementTopology().setNode_z(null);
				}
				addsegment.setPortInst_a(portInst);
				addsegment.setAText(text);

				List<Element> elementList = dialog.getSegementTopology().getBox().getAllElements();
				for (Element element : elementList) {
					if (element instanceof Node) {
						if (element.getBusinessObject() != null) {
							if (element.getBusinessObject().equals(type)) {
								element.removeAttachment("topoTitle");
								element.setBusinessObject(null);
							}
						}
					}
				}
				dialog.getSegementTopology().getNode_a().removeAttachment("topoTitle");
				dialog.getSegementTopology().getNode_a().setBusinessObject(type);
				//SegmentTopology.getTopology();
				dialog.getSegementTopology().getNode_a().addAttachment("topoTitle");
			} else if (type.equals("Z")) {
				//SegmentTopology.getTopology();
				//dialog.getSegementTopology().getTopology();
				if (dialog.getSegementTopology().getNode_a() != null && dialog.getSegementTopology().getNode_a().getUserObject() == siteInst) {
					addsegment.setPortInst_a(null);
					addsegment.setAText("");
					//SegmentTopology.getTopology();
					dialog.getSegementTopology().getNode_a().removeAttachment("topoTitle");
					dialog.getSegementTopology().setNode_a(null);
				}
				addsegment.setPortInst_z(portInst);
				addsegment.setZText(text);

				List<Element> elementList =dialog.getSegementTopology().getBox().getAllElements();
				for (Element element : elementList) {
					if (element instanceof Node) {
						if (element.getBusinessObject() != null) {
							if (element.getBusinessObject().equals(type)) {
								element.removeAttachment("topoTitle");
								element.setBusinessObject(null);
							}
						}
					}
				}
				//SegmentTopology.getTopology();
				dialog.getSegementTopology().getNode_z().removeAttachment("topoTitle");
				//SegmentTopology.getTopology();
				dialog.getSegementTopology().getNode_z().setBusinessObject(type);
				//SegmentTopology.getTopology();
				dialog.getSegementTopology().getNode_z().addAttachment("topoTitle");
			}

//			SegmentTopology.getTopology();
//			SegmentTopology.getTopology();
//			SegmentTopology.getTopology();
//			SegmentTopology.getTopology();
			if (dialog.getSegementTopology().getNode_a() != null && dialog.getSegementTopology().getNode_z() != null && dialog.getSegementTopology().getNode_a() != dialog.getSegementTopology().getNode_z()) {
				link = createLink(dialog.getSegementTopology().getNode_a(), dialog.getSegementTopology().getNode_z());
				dialog.getSegementTopology().getBox().addElement(link);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			 text = null;
			 addsegment = null;
		}
	}

	/**
	 * 创建link的基础属�?
	 */
	private Link createLink(Node nodea, Node nodez) throws Exception {
		try {
			link = new Link();
			link.setFrom(nodea);
			link.setTo(nodez);
			link.setLinkType(TWaverConst.LINE_TYPE_DEFAULT);
			link.putLinkColor(Color.GREEN);
			link.putLinkFlowingWidth(3);
			link.putLinkWidth(3);
			return link;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 创建拓扑界面
	 * 
	 * @throws Exception
	 */
	public void createTopo() throws Exception {
		CreateNeTopo createNeTopo=null;
		try {
			createNeTopo=new CreateNeTopo(this.box,this.siteInst.getSite_Inst_Id());
			createNeTopo.createTopo();
			
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("serial")
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
	@SuppressWarnings("serial")
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
				SegmentDiaLog dialog = new SegmentDiaLog(new javax.swing.JFrame(), true);
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