/*
 * UdaGroupPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemconfig;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.code.CodeGroupService_MB;
import com.nms.model.system.code.CodeService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.systemconfig.dialog.code.AddCodeDialog;
import com.nms.ui.ptn.systemconfig.dialog.code.AddCodeGroupDialog;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class CodePanel extends javax.swing.JPanel {

	/** Creates new form UdaGroupPanel */
	public CodePanel() {
		initComponents();

		try {
			this.showHide(false);
			this.tableGroupData();
			UiUtil.jTableColumsHide(jTable2, 1);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void tableGroupData() throws Exception {
		UiUtil.jTableColumsHide(jTable1, 2);

		CodeGroupService_MB codeGroupService = null;
		List<CodeGroup> codeGroupList = null;
		DefaultTableModel defaultTableModel = null;

		try {
			codeGroupService = (CodeGroupService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.CodeGroup);
			codeGroupList = codeGroupService.selectCodeByCodeGroup();

			if (null != codeGroupList) {
				defaultTableModel = (DefaultTableModel) jTable1.getModel();
				defaultTableModel.getDataVector().clear();
				defaultTableModel.fireTableDataChanged();
				for (int i = 0; i < codeGroupList.size(); i++) {
					Object[] obj = new Object[] { codeGroupList.get(i).getId(),
							codeGroupList.get(i).getCodeList(), i + 1,
							codeGroupList.get(i).getCodeGroupName(),
							codeGroupList.get(i).getCodeIdentily(),
							codeGroupList.get(i).getCodeDesc() };
					defaultTableModel.addRow(obj);
				}
				jTable1.setModel(defaultTableModel);
				this.showHide(false);

				ConstantUtil.codeGroupMap = null;
				UiUtil.getCodeGroupMap();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(codeGroupService);
		}
	}

	@SuppressWarnings("unchecked")
	public void tableInfoData() throws Exception {
		DefaultTableModel defaultTableModel = null;
		List<Code> codeList = null;

		try {
			if (jTable1.getSelectedRows().length > 0) {
				if (null != jTable1.getValueAt(jTable1.getSelectedRows()[0], 1)) {

					codeList = (List<Code>) jTable1.getValueAt(jTable1
							.getSelectedRows()[0], 1);
					defaultTableModel = (DefaultTableModel) jTable2.getModel();
					defaultTableModel.getDataVector().clear();
					defaultTableModel.fireTableDataChanged();

					for (int i = 0; i < codeList.size(); i++) {
						Object[] obj = new Object[] { codeList.get(i).getId(),
								i + 1, codeList.get(i).getCodeName(),codeList.get(i).getCodeENName(),
								codeList.get(i).getCodeValue() };
						defaultTableModel.addRow(obj);
					}
					jTable2.setModel(defaultTableModel);
					this.showHide(true);
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			defaultTableModel = null;
			codeList = null;
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

	private void codeGroupUpdate() throws Exception {
		String selectId = null;
		AddCodeGroupDialog addCodeGroupDialog = null;
		try {
			selectId = jTable1.getValueAt(jTable1.getSelectedRows()[0], 0)
					.toString();
			addCodeGroupDialog = new AddCodeGroupDialog(this, true, selectId);
			addCodeGroupDialog.setLocation(UiUtil
					.getWindowWidth(addCodeGroupDialog.getWidth()), UiUtil
					.getWindowHeight(addCodeGroupDialog.getHeight()));
			addCodeGroupDialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		} finally {
			selectId = null;
			addCodeGroupDialog = null;
		}

	}

	private void codeInfoUpdate() throws Exception {
		String selectId = null;
		AddCodeDialog addCodeDialog = null;
		try {
			selectId = jTable2.getValueAt(jTable2.getSelectedRows()[0], 0)
					.toString();
			addCodeDialog = new AddCodeDialog(this, true, selectId, "");
			addCodeDialog.setLocation(UiUtil.getWindowWidth(addCodeDialog
					.getWidth()), UiUtil.getWindowHeight(addCodeDialog
					.getHeight()));
			addCodeDialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		} finally {
			selectId = null;
			addCodeDialog = null;
		}

	}

	//GEN-BEGIN:initComponents
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

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Code\u914d\u7f6e"));

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
				jButton2ActionPerformed(evt);
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

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "id", "codelist", "序号", "组名", "标识", "说明" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false };

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
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
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
				.createTitledBorder("Code\u8be6\u7ec6\u4fe1\u606f"));

		jButton6.setText("\u65b0\u5efa");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jButton7.setText("\u4fee\u6539");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
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

				}, new String[] { "id", "序号", "名称","英文名称", "Code值" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,false };

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
				jTable2MouseClicked(evt);
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
	//GEN-END:initComponents

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			try {
				this.codeInfoUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

		if (evt.getClickCount() == 1) {
			try {
				this.tableInfoData();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		} else if (evt.getClickCount() == 2) {
			try {
				this.codeGroupUpdate();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}

	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			if (jTable2.getSelectedRowCount() != 1) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				UiUtil.insertOperationLog(EOperationLogType.UPDATASELECTERROR.getValue());
			} else {

				this.codeInfoUpdate();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {

		String selectGroupId = jTable1.getValueAt(jTable1.getSelectedRows()[0],
				0).toString();

		AddCodeDialog addCodeDialog = new AddCodeDialog(this, true, "",
				selectGroupId);
		addCodeDialog
				.setLocation(UiUtil.getWindowWidth(addCodeDialog.getWidth()),
						UiUtil.getWindowHeight(addCodeDialog.getHeight()));
		addCodeDialog.setVisible(true);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			if (jTable1.getSelectedRowCount() != 1) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				UiUtil.insertOperationLog(EOperationLogType.UPDATASELECTERROR.getValue());
			} else {

				this.codeGroupUpdate();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		AddCodeGroupDialog addCodeGroupDialog = new AddCodeGroupDialog(this,
				true, "");
		addCodeGroupDialog.setLocation(UiUtil.getWindowWidth(addCodeGroupDialog
				.getWidth()), UiUtil.getWindowHeight(addCodeGroupDialog
				.getHeight()));
		addCodeGroupDialog.setVisible(true);
	}

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jTable2.getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			UiUtil.insertOperationLog(EOperationLogType.SELECTERROR.getValue());
		} else {

			CodeService_MB codeService = null;
			String selectId = null;
			Code code = null;

			try {
				int result = DialogBoxUtil.confirmDialog(this,
						ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
				if (result == 0) {
					codeService = (CodeService_MB) ConstantUtil.serviceFactory
							.newService_MB(Services.Code);

					for (int i = 0; i < jTable2.getSelectedRows().length; i++) {
						selectId = jTable2.getValueAt(
								jTable2.getSelectedRows()[i], 0).toString();
						code = new Code();
						code.setId(Integer.parseInt(selectId));
						codeService.delete(code.getId());
					}

					DialogBoxUtil.succeedDialog(this,
							ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
					this.tableGroupData();
				}

			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(codeService);
			}

		}
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			this.tableGroupData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jTable1.getSelectedRowCount() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
			UiUtil.insertOperationLog(EOperationLogType.SELECTERROR.getValue());
		} else {

			CodeGroupService_MB codeGroupService = null;
			String selectId = null;
			CodeGroup codegroup = null;

			try {

				int result = DialogBoxUtil.confirmDialog(this,
						ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
				if (result == 0) {
					codeGroupService = (CodeGroupService_MB) ConstantUtil.serviceFactory
							.newService_MB(Services.CodeGroup);

					for (int i = 0; i < jTable1.getSelectedRows().length; i++) {
						selectId = jTable1.getValueAt(
								jTable1.getSelectedRows()[i], 0).toString();
						codegroup = new CodeGroup();
						codegroup.setId(Integer.parseInt(selectId));
						codeGroupService.delete(codegroup);
					}

					DialogBoxUtil.succeedDialog(this,
							ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
					this.tableGroupData();
				}

			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(codeGroupService);
			}
		}
	}

	//GEN-BEGIN:variables
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