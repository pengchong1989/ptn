/*
 * UdaGroupPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.UdaAttr;
import com.nms.db.bean.system.UdaGroup;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.UdaAttrService_MB;
import com.nms.model.system.UdaGroupService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.systemconfig.dialog.uda.AddUdaAttrDialog;
import com.nms.ui.ptn.systemconfig.dialog.uda.AddUdaGroupDialog;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class UdaGroupPanel extends javax.swing.JPanel {

	/** Creates new form UdaGroupPanel */
	public UdaGroupPanel() {
		initComponents();
		try {
			this.udaGroupData();
			UiUtil.jTableColumsHide(jTable2, 1);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void udaGroupData() throws Exception {
		UiUtil.jTableColumsHide(jTable1, 2);

		UdaGroupService_MB udaGroupService = null;
		List<UdaGroup> udaGroupList = null;
		DefaultTableModel defaultTableModel = null;
		try {
			udaGroupService = (UdaGroupService_MB ) ConstantUtil.serviceFactory.newService_MB (Services.UDAGROUP);
			udaGroupList = udaGroupService.select();

			defaultTableModel = (DefaultTableModel) jTable1.getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();

			for (int i = 0; i < udaGroupList.size(); i++) {
				Object[] obj = new Object[] {
						udaGroupList.get(i).getId(),
						udaGroupList.get(i).getUdaAttrList(),
						i + 1,
						udaGroupList.get(i).getGroupName(),
						UiUtil.getCodeById(Integer.parseInt(udaGroupList.get(i).getGroupType())).getCodeName(),
						udaGroupList.get(i).getParentName() };
				
			 defaultTableModel.addRow(obj);
			}
			jTable1.setModel(defaultTableModel);
			this.showHide(false);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(udaGroupService);
		}

	}

	private void udaGroupUpdate() throws Exception {
		String selectId = jTable1.getValueAt(jTable1.getSelectedRows()[0], 0).toString();
		AddUdaGroupDialog addUdaGroupDialog = new AddUdaGroupDialog(this, true,selectId);
		addUdaGroupDialog.setLocation(UiUtil.getWindowWidth(addUdaGroupDialog.getWidth()), UiUtil.getWindowHeight(addUdaGroupDialog.getHeight()));
		addUdaGroupDialog.setVisible(true);
	}
	
    private void udaAttrUpdate() throws Exception{
    	String selectId = jTable2.getValueAt(jTable2.getSelectedRows()[0], 0).toString();
		String selectGroupId = jTable1.getValueAt(jTable1.getSelectedRows()[0],0).toString();
		
    	AddUdaAttrDialog addudaattrdialog = new AddUdaAttrDialog(this, true,selectId , selectGroupId);
    	addudaattrdialog.setLocation(UiUtil.getWindowWidth(addudaattrdialog.getWidth()), UiUtil.getWindowHeight(addudaattrdialog.getHeight()));
    	addudaattrdialog.setVisible(true);
    }
    
	@SuppressWarnings("unchecked")
	public void udaAttrData() throws Exception {

		List<UdaAttr> udaAttrList = null;
		DefaultTableModel defaultTableModel = null;

		try {
			if (jTable1.getSelectedRows().length > 0) {
//				if (null != jTable1.getValueAt(jTable1.getSelectedRows()[0], 5)
//						&& !"".equals(jTable1.getValueAt(
//								jTable1.getSelectedRows()[0], 5).toString())) {
					if (null != jTable1.getValueAt(jTable1.getSelectedRows()[0], 1)) {
						udaAttrList = (List<UdaAttr>) jTable1.getValueAt(jTable1.getSelectedRows()[0], 1);
						defaultTableModel = (DefaultTableModel) jTable2.getModel();
						defaultTableModel.getDataVector().clear();
						defaultTableModel.fireTableDataChanged();

						for (int i = 0; i < udaAttrList.size(); i++) {
							Object[] obj = new Object[] {
									udaAttrList.get(i).getId(), i + 1,
									udaAttrList.get(i).getAttrName(),
									UiUtil.getCodeById(Integer.parseInt(udaAttrList.get(i).getAttrType())).getCodeName() };
							defaultTableModel.addRow(obj);
						}
						jTable2.setModel(defaultTableModel);
						this.showHide(true);
					}
				} else {
					this.showHide(false);
				}
//			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void showHide(boolean flag) {
		this.jButton6.setEnabled(flag);
		this.jButton7.setEnabled(flag);
		this.jButton8.setEnabled(flag);
		this.jTable2.setEnabled(flag);
		if (!flag) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) jTable2
					.getModel();
			defaultTableModel.getDataVector().clear();
			defaultTableModel.fireTableDataChanged();
		}
	}
    

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel2 = new javax.swing.JPanel();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();

		setPreferredSize(new java.awt.Dimension(1000, 570));

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("UDA\u914d\u7f6e"));
		jButton1.setText("\u65b0\u5efa");
		
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u4fee\u6539");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton2ActionPerformed(evt);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		jButton3.setText("\u5220\u9664");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton5.setText("\u5237\u65b0");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, 
				        new String[] { "id", "udaattrlist", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysObj.GROUP_NAME),
						ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE), ResourceUtil.srcStr(StringKeysObj.UP_GROUP_NAME) }) {
			           boolean[] canEdit = new boolean[] { false, false, false,
				       false, false, false };
					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
		 });
		
		jTable1.setRowHeight(21);
		jTable1.getTableHeader().setReorderingAllowed(false);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jTable1MouseClicked(evt);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		
		jScrollPane1.setViewportView(jTable1);
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton5)
										.addContainerGap(724, Short.MAX_VALUE))
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 984,
								Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3)
														.addComponent(jButton5))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												195, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("UDA\u8be6\u7ec6\u4fe1\u606f"));

		jButton6.setText("\u65b0\u5efa");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton6ActionPerformed(evt);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		jButton7.setText("\u4fee\u6539");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton7ActionPerformed(evt);
				} catch (Exception e) {
					 
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		jButton8.setText("\u5220\u9664");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "id", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysObj.GROUP_NAME), ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE) }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable2.setRowHeight(21);
		jTable2.getTableHeader().setReorderingAllowed(false);
		jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					jTable2MouseClicked(evt);
				} catch (Exception e) {
					 
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		jScrollPane2.setViewportView(jTable2);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jButton6)
										.addGap(5, 5, 5)
										.addComponent(jButton7)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton8)
										.addContainerGap(788, Short.MAX_VALUE))
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.DEFAULT_SIZE, 984,
								Short.MAX_VALUE));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton8)
														.addComponent(jButton7)
														.addComponent(jButton6))
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												217, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>

	// GEN-END:initComponents

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt){
      if (evt.getClickCount() == 2) {
			try {
				this.udaAttrUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
      }
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt){
		if (jTable2.getSelectedRowCount() != 1) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.UPDATASELECTERROR.getValue());
		} else {
			try {
				this.udaAttrUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt){
		
		AddUdaAttrDialog addUdaAttrDialog = new AddUdaAttrDialog(this, true, "" ,jTable1.getValueAt(jTable1.getSelectedRows()[0], 0).toString());
		addUdaAttrDialog.setLocation(UiUtil.getWindowWidth(addUdaAttrDialog.getWidth()), UiUtil.getWindowHeight(addUdaAttrDialog.getHeight()));
		addUdaAttrDialog.setVisible(true);
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt){
		if (evt.getClickCount() == 1) {
			try {
				this.udaAttrData();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		} else if (evt.getClickCount() == 2) {
			try {
				this.udaGroupUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
		if (jTable1.getSelectedRowCount() != 1) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			UiUtil.insertOperationLog(EOperationLogType.UPDATASELECTERROR.getValue());
		} else {
			try {
				this.udaGroupUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		AddUdaGroupDialog addUdaGroupDialog = new AddUdaGroupDialog(this, true,null);
		addUdaGroupDialog.setLocation(UiUtil.getWindowWidth(addUdaGroupDialog.getWidth()), UiUtil.getWindowHeight(addUdaGroupDialog.getHeight()));
		addUdaGroupDialog.setVisible(true);
	}

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jTable2.getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			UiUtil.insertOperationLog(EOperationLogType.SELECTERROR.getValue());
		} else {

			UdaAttrService_MB udaaAttrService = null;

			try {
				int result = DialogBoxUtil.confirmDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
				if (result == 0) {
					udaaAttrService = (UdaAttrService_MB) ConstantUtil.serviceFactory
							.newService_MB(Services.UDAATTR);

					for (int i = 0; i < jTable2.getSelectedRows().length; i++) {
						String selectId = jTable2.getValueAt(
								jTable2.getSelectedRows()[i], 0).toString();
						UdaAttr udaAttr = new UdaAttr();
						udaAttr.setId(Integer.parseInt(selectId));
						udaaAttrService.delete(udaAttr.getId());
					}

					DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
					this.udaGroupData();
				}

			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(udaaAttrService);
			}
		}
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			this.udaGroupData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jTable1.getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			UiUtil.insertOperationLog(EOperationLogType.SELECTERROR.getValue());
		} else {
			UdaGroupService_MB udaGroupService = null;
			UdaGroup udaGroupDelete = null;
			UdaGroup udaGroupSelect = null;
			List<UdaGroup> udaGroupList = null;
			try {

				int result = DialogBoxUtil.confirmDialog(this,
						ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
				if (result == 0) {
					udaGroupService = (UdaGroupService_MB) ConstantUtil.serviceFactory
							.newService_MB(Services.UDAGROUP);
					for (int i = 0; i < jTable1.getSelectedRows().length; i++) {
						String selectId = jTable1.getValueAt(
								jTable1.getSelectedRows()[i], 0).toString();
						udaGroupDelete = new UdaGroup();
						udaGroupDelete.setId(Integer.parseInt(selectId));
						if (null != jTable1.getValueAt(jTable1
								.getSelectedRows()[i], 0)
								&& !"".equals(jTable1.getValueAt(
										jTable1.getSelectedRows()[i], 0)
										.toString())) {
							udaGroupSelect = new UdaGroup();
							udaGroupSelect.setParentId(Integer
									.parseInt(selectId));
							udaGroupList = udaGroupService
									.select(udaGroupSelect);
							for (int j = 0; j < udaGroupList.size(); j++) {
								udaGroupService.delete(udaGroupList.get(j));
							}
						}

						udaGroupService.delete(udaGroupDelete);
						this.udaGroupData();
					}
				}

			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(udaGroupService);
			}

		}
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	// End of variables declaration//GEN-END:variables

}