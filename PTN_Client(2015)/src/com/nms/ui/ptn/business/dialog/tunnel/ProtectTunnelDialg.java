/*
 * ProtectTunnelDialg.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.business.dialog.tunnel;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.business.dialog.tunnel.action.TunnelAction;

/**
 * 
 * @author __USER__
 */

public class ProtectTunnelDialg extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static ProtectTunnelDialg protectDialog;
	private AddTunnelPathDialog tunnelDialog;
	private final TunnelAction tunnelAction = new TunnelAction();
	private TunnelTopoPanel tunnelTopoPanel=null;

	public TunnelTopoPanel getTunnelTopoPanel() {
		return tunnelTopoPanel;
	}

	public void setTunnelTopoPanel(TunnelTopoPanel tunnelTopoPanel) {
		this.tunnelTopoPanel = tunnelTopoPanel;
	}

	/** Creates new form ProtectTunnelDialg */
	public ProtectTunnelDialg(java.awt.Frame parent, boolean modal) {
		initComponents();
		//protectDialog = this;
	}

	public ProtectTunnelDialg(AddTunnelPathDialog dialog) {
		this.tunnelDialog=dialog;
		this.setModal(true);
		initComponents();
		try {
			//protectDialog = this;
			tunnelTopoPanel=new TunnelTopoPanel();
			this.jSplitPane1.setRightComponent(tunnelTopoPanel);
			super.getComboBoxDataUtil().comboBoxData(jComboBox1, "PROTECTTYPE");
			tunnelAction.tunnelData(this);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponents() {

		jSplitPane1 = new javax.swing.JSplitPane();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jComboBox2 = new javax.swing.JComboBox();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

		jLabel1.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));

		jLabel2.setText("tunnel");

		jComboBox2.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				tunnelAction.chooseTunnel(evt, ProtectTunnelDialg.this);
			}
		});

		jButton1.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tunnelAction.confirmTunnelPro(evt, ProtectTunnelDialg.this);

			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jComboBox2, 0, 140, Short.MAX_VALUE).addComponent(jComboBox1, 0, 140, Short.MAX_VALUE)))).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(25, 25, 25).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(27, 27, 27).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(51, 51, 51).addComponent(jButton1).addContainerGap(130, Short.MAX_VALUE)));

		jSplitPane1.setLeftComponent(jPanel1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));

		pack();
	}

	public javax.swing.JComboBox getjComboBox1() {
		return jComboBox1;
	}

	public void setjComboBox1(javax.swing.JComboBox jComboBox1) {
		this.jComboBox1 = jComboBox1;
	}

	public javax.swing.JComboBox getjComboBox2() {
		return jComboBox2;
	}

	public void setjComboBox2(javax.swing.JComboBox jComboBox2) {
		this.jComboBox2 = jComboBox2;
	}
//
//	public static ProtectTunnelDialg getProtectDialog() {
//		return protectDialog;
//	}
//
//	public static void setProtectDialog(ProtectTunnelDialg protectDialog) {
//		ProtectTunnelDialg.protectDialog = protectDialog;
//	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ProtectTunnelDialg dialog = new ProtectTunnelDialg(new javax.swing.JFrame(), true);
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

	public AddTunnelPathDialog getTunnelDialog() {
		return tunnelDialog;
	}

	public void setTunnelDialog(AddTunnelPathDialog tunnelDialog) {
		this.tunnelDialog = tunnelDialog;
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JSplitPane jSplitPane1;
	// End of variables declaration//GEN-END:variables

}