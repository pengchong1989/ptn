/*
 * NeConfigPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import twaver.Node;
import twaver.SubNetwork;
import twaver.TDataBox;
import twaver.tree.ElementNode;
import twaver.tree.TTree;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.system.Field;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.TopologyUtil;
import com.nms.ui.ptn.basicinfo.dialog.site.AddSiteDialog;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class NeConfigPanel extends javax.swing.JPanel {

	private TDataBox box = new TDataBox(ResourceUtil.srcStr(StringKeysObj.PTN_MANAGE_SYSTEM));
	private TTree tree = new TTree(box);
	private static Field constField = null;

	public NeConfigPanel() {
		initComponents();
		initTree();
	}

	private void initTree() {
		FieldService_MB fieldService = null;
		List<Field> fieldList = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fieldList = fieldService.select();
			refreshTreeBox(box, fieldList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(fieldService);
		}
	}

	private void refreshTreeBox(TDataBox box, List<Field> fieldList) {
		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		Node node = null;
		SubNetwork subNetwork = null;
		TopologyUtil topologyUtil=new TopologyUtil();
		try {
			box.clear();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			for (Field field : fieldList) {
				subNetwork = new SubNetwork();
				subNetwork.setName(field.getFieldName());
				subNetwork.setUserObject(field);
				box.addElement(subNetwork);

				SiteInst siteInst = new SiteInst();
				siteInst.setFieldID(field.getId());
				siteInstList = siteService.select(siteInst);

				for (int i = 0; i < siteInstList.size(); i++) {
					node = new Node();
					node.setName(siteInstList.get(i).getCellId() + "");
					node.setLocation(siteInstList.get(i).getSiteX(), siteInstList.get(i).getSiteY());
					node.setParent(subNetwork);

					topologyUtil.setNodeImage(node, siteInstList.get(i));
					node.setUserObject(siteInstList.get(i));
					box.addElement(node);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		// jTree1 = new javax.swing.JTree();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel3 = new javax.swing.JPanel();
		Confirm = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		BtnModify = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_UPDATE));
		BtnDel = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE));
		tree.expandAll();
		jScrollPane1.setViewportView(tree);

		tree.addTreeNodeClickedActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (tree.getSelectionPath().getLastPathComponent() instanceof ElementNode) {
					ElementNode elementNode = (ElementNode) tree.getSelectionPath().getLastPathComponent();
					if (elementNode.getElement().getClass().getSimpleName().equals("SubNetwork")) {
						SubNetwork network = (SubNetwork) elementNode.getElement();						
						Field field = (Field) network.getUserObject();
						constField = field;
						initTabledata(field);

					}
				}

			}
		});
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(25, 25, 25).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(14, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE));

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM),
				ResourceUtil.srcStr(StringKeysLbl.LBL_NAME), ResourceUtil.srcStr(StringKeysObj.AREA_NAME), ResourceUtil.srcStr(StringKeysLbl.LBL_SIETTYPE),
				ResourceUtil.srcStr(StringKeysObj.MANAGE_TYPE), ResourceUtil.srcStr(StringKeysLbl.LBL_SWITCH_STATUS), 
				ResourceUtil.srcStr(StringKeysLbl.LBL_SITE_DESC), ResourceUtil.srcStr(StringKeysObj.STRING_CREATE_TIME) }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
//		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
//			public void mousePressed(MouseEvent et) {
//				if (SwingUtilities.isRightMouseButton(et)) {
//					if (jTable1.getSelectedRow() >= 0 && jTable1.getSelectedRowCount() == 1) {
//						SiteInst siteInst = null;
//						List<Integer> idList = new ArrayList<Integer>();
//						siteInst = (SiteInst) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
//						idList.add(siteInst.getSite_Inst_Id());
//
//						JMenuItem modify = new JMenuItem("修改网元信息");
//						JPopupMenu menu = new JPopupMenu();
//						menu.add(modify);
//						menu.show(et.getComponent(), et.getX(), et.getY());
//					}
//
//				}
//			}
//		});
		jScrollPane2.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 948, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 209, Short.MAX_VALUE));

		jTabbedPane1.addTab("\u8be6\u7ec6\u4fe1\u606f", jPanel3);

//		jLabel1.setFont(new java.awt.Font("宋体", 0, 14));
//		jLabel1.setText("你好");

		BtnModify.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ModifyActionPerformed(evt);
				initTabledata(constField);
			}
		});
		
		BtnDel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteSiteActionPerformed(evt);
				initTabledata(constField);
				initTree();
			}
		});
		
		Confirm.setText("\u4e0b\u53d1\u914d\u7f6e");
		Confirm.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ConfirmActionPerformed(evt);
			}
		});

		jButton2.setText("\u6e05\u9664");
		jButton2.setActionCommand("jButton2");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
				.addComponent(BtnModify).addGap(24, 24, 24)
				.addComponent(BtnDel).addGap(24, 24, 24).addContainerGap(896, Short.MAX_VALUE))
				.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(783, Short.MAX_VALUE)
						.addComponent(Confirm).addGap(24, 24, 24 ).addComponent(jButton2).addGap(24, 24, 24))
						.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
								.addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(BtnModify).addComponent(BtnDel))
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton2).addComponent(Confirm)).addGap(18, 18, 18).addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(23, 23, 23)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
	}

	protected void deleteSiteActionPerformed(ActionEvent evt) {
		SegmentService_MB segmentService = null;
		SiteService_MB siteService = null;
		Segment segment = null;
		List<Segment> list = null;
		try {
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			if (jTable1.getSelectedRowCount() != 1) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				return;
			} else {
				int result = DialogBoxUtil.confirmDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
				if (result == 0) {				
					SiteInst site = (SiteInst) jTable1.getValueAt(jTable1.getSelectedRows()[0], 0);

					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);

					segment = new Segment();
					segment.setASITEID(site.getSite_Inst_Id());
					list = segmentService.select(segment);
					if (list != null && list.size() > 0) {
						DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
						return;
					}
					segment = new Segment();
					segment.setZSITEID(site.getSite_Inst_Id());
					list = segmentService.select(segment);
					if (list != null && list.size() > 0) {
						DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
						return;
					}
//					siteService.delete(site);
					siteDispatch.excuteDelete(site);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(segmentService);
			UiUtil.closeService_MB(siteService);
		}	
	}

//	protected Field getFieldBySite(SiteInst s) {
//		Field field = null;
//		FieldService fieldService = null;
//		try {
//			fieldService = (FieldService) ConstantUtil.serviceFactory.newService(Services.Field);
//			field = new Field();
//			field.setId(s.getFieldID());
//			field = fieldService.select(field).get(0);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}finally{
//			 fieldService = null;
//		}
//		return field;
//	}

	private void ModifyActionPerformed(ActionEvent evt) {
		try {
			if (jTable1.getSelectedRowCount() != 1) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				return;
			} else {
				SiteInst site = (SiteInst) jTable1.getValueAt(jTable1.getSelectedRows()[0], 0);
				
			AddSiteDialog addSiteDialog = new AddSiteDialog(true, site.getSite_Inst_Id()+"");
			addSiteDialog.setLocation(UiUtil.getWindowWidth(addSiteDialog.getWidth()),
					UiUtil.getWindowHeight(addSiteDialog.getHeight()));
			addSiteDialog.setVisible(true);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	
	}

	private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {
		 
		try {
			DispatchUtil  configDispath = new DispatchUtil(RmiKeys.RMI_ADMINISTRATECONFIG);
			if(constField != null)
				configDispath.excuteInsert(constField.getGroupId());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initTabledata(Field field) {
		if (field == null) {
			return;
		}
		UiUtil.jTableColumsHide(jTable1, 1);

		SiteService_MB siteService = null;
		List<SiteInst> siteInstList = null;
		DefaultTableModel defaultTableModel = null;
		int index = 0;
		try {
			defaultTableModel = (DefaultTableModel) jTable1.getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();

			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);

			SiteInst siteInst = new SiteInst();
			siteInst.setFieldID(field.getId());
			siteInstList = siteService.select(siteInst);

			for (SiteInst site : siteInstList) {
				Object[] obj = new Object[] { site, index + 1, site.getCellId(), field.getFieldName(), site.getCellType(), site.getType()==1?"M":"A", site.getSwich(), site.getCellDescribe(), site.getCellTime() };
				defaultTableModel.addRow(obj);
				index++;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}

	}

	private javax.swing.JButton Confirm;
	private javax.swing.JButton jButton2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JButton BtnModify;
	private javax.swing.JButton BtnDel;

}