/*
 * AddStreamDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.business.dialog.eline;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.enums.EOperationLogType;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.ac.view.AddACDialog;

/**
 * 
 * @author __USER__
 */
@SuppressWarnings("serial")
public class AddStreamDialog extends PtnDialog {

	private Acbuffer acbuffer = null;
	private AddACDialog addAcDialog;

	/** Creates new form AddStreamDialog */
	public AddStreamDialog(java.awt.Frame parent, boolean modal) {
		initComponents();
		this.jTextField2.setText("00-00-00-00-00-00");
		this.jTextField3.setText("00-00-00-00-00-00");
	}

	/** Creates new form AddStreamDialog */
	public AddStreamDialog(AddACDialog addAcDialog, boolean modal, Acbuffer obj) {
		this.setModal(modal);
		initComponents();

		this.addAcDialog = addAcDialog;
		try {
			// Id = id;
			super.getComboBoxDataUtil().comboBoxData(jComboBox1, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(jComboBox2, "UNIPORTMODE");
			super.getComboBoxDataUtil().comboBoxData(jComboBox3, "STRATEGYMODE");
			super.getComboBoxDataUtil().comboBoxData(jComboBox4, "CONRIRMPHB");
			super.getComboBoxDataUtil().comboBoxData(jComboBox5, "BUFFERCM");

			if (obj != null) {
				this.acbuffer = obj;
				this.comboBoxSelect(jComboBox1, "ENABLEDSTATUE",
						this.acbuffer.getBufferEnable());
				this.jTextField1.setText(this.acbuffer.getVlanId() + "");
				this.jTextField2.setText(this.acbuffer.getSourceMac());
				this.jTextField3.setText(this.acbuffer.getTargetMac());
				this.jTextField4.setText(this.acbuffer.getEightIp() + "");
				this.jTextField5.setText(this.acbuffer.getSourceIp());
				this.jTextField6.setText(this.acbuffer.getTargetIp());
				this.jTextField7.setText(this.acbuffer.getIpDscp() + "");
				this.comboBoxSelect(jComboBox2, "UNIPORTMODE",
						this.acbuffer.getModel());
				this.jTextField8.setText(this.acbuffer.getCir() + "");
				this.jTextField9.setText(this.acbuffer.getPir() + "");
				this.comboBoxSelect(jComboBox5, "BUFFERCM",
						this.acbuffer.getCm());
				this.jTextField11.setText(this.acbuffer.getCbs() + "");
				this.jTextField12.setText(this.acbuffer.getPbs() + "");
				this.comboBoxSelect(jComboBox3, "STRATEGYMODE",
						this.acbuffer.getStrategy());
				this.comboBoxSelect(jComboBox4, "CONRIRMPHB",
						this.acbuffer.getPhb());
			} else {
				this.comboBoxSelect(jComboBox1, "ENABLEDSTATUE", 1);
				this.jTextField1.setText(2 + "");
				this.jTextField2.setText("00-00-00-00-00-00");
				this.jTextField3.setText("00-00-00-00-00-00");
				this.jTextField4.setText(1 + "");
				this.jTextField5.setText("");
				this.jTextField6.setText("");
				this.jTextField7.setText(0 + "");
				this.comboBoxSelect(jComboBox2, "UNIPORTMODE", 0);
				this.jTextField8.setText(0 + "");
				this.jTextField9.setText(100 + "");
				this.comboBoxSelect(jComboBox5, "BUFFERCM", 0);
				this.jTextField11.setText(0 + "");
				this.jTextField12.setText(0 + "");
				this.comboBoxSelect(jComboBox3, "STRATEGYMODE", 0);
				this.comboBoxSelect(jComboBox4, "CONRIRMPHB", 0);

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private void comboBoxSelect(JComboBox jComboBox, String identity, int key)
			throws Exception {

		CodeGroup codeGroup = null;
		List<Code> codeList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		try {
			codeGroup = UiUtil.getCodeGroupByIdentity(identity);
			codeList = codeGroup.getCodeList();
			for (Code obj : codeList) {
				if (Integer.parseInt(obj.getCodeValue()) == key) {
					defaultComboBoxModel = (DefaultComboBoxModel) jComboBox
							.getModel();
					defaultComboBoxModel.setSelectedItem(new ControlKeyValue(
							obj.getId() + "", obj.getCodeName(), obj));
					jComboBox.setModel(defaultComboBoxModel);
					break;
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			codeList = null;
			defaultComboBoxModel = null;
		}
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jTextField7 = new javax.swing.JTextField();
		jComboBox2 = new javax.swing.JComboBox();
		jTextField8 = new javax.swing.JTextField();
		jTextField9 = new javax.swing.JTextField();
		jTextField11 = new javax.swing.JTextField();
		jTextField12 = new javax.swing.JTextField();
		jComboBox3 = new javax.swing.JComboBox();
		jComboBox4 = new javax.swing.JComboBox();
		Confirm = new javax.swing.JButton();
		Cancel = new javax.swing.JButton();
		jComboBox5 = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("\u4f7f\u80fd\u72b6\u6001");

		jLabel2.setText("Vlan");

		jLabel3.setText("\u6e90MAC");

		jLabel4.setText("\u76ee\u7684MAC");

		jLabel5.setText("802.IP");

		jLabel6.setText("\u6e90IP");

		jLabel7.setText("\u76ee\u7684IP");

		jLabel8.setText("IpDscp");

		jLabel9.setText("\u7aef\u53e3\u6a21\u5f0f");

		jLabel10.setText("cir");

		jLabel11.setText("pir");

		jLabel12.setText("cm");

		jLabel13.setText("cbs");

		jLabel14.setText("pbs");

		jLabel15.setText("\u7b56\u7565\u6a21\u5f0f");

		jLabel16.setText("\u6307\u914dPHB");

		Confirm.setText("\u786e\u5b9a");
		Confirm.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ConfirmActionPerformed(evt);
			}
		});

		Cancel.setText("\u53d6\u6d88");
		Cancel.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		Confirm)
																.addGap(49, 49,
																		49)
																.addComponent(
																		Cancel))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(54, 54,
																		54)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel7)
																				.addComponent(
																						jLabel6)
																				.addComponent(
																						jLabel5)
																				.addComponent(
																						jLabel4)
																				.addComponent(
																						jLabel3)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jLabel1)
																				.addComponent(
																						jLabel8))
																.addGap(57, 57,
																		57)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jTextField1)
																				.addComponent(
																						jTextField3)
																				.addComponent(
																						jTextField4)
																				.addComponent(
																						jTextField5)
																				.addComponent(
																						jTextField6)
																				.addComponent(
																						jTextField7)
																				.addComponent(
																						jComboBox1,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jTextField2,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						122,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(100,
																		100,
																		100)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel9)
																				.addComponent(
																						jLabel10)
																				.addComponent(
																						jLabel11)
																				.addComponent(
																						jLabel12)
																				.addComponent(
																						jLabel13)
																				.addComponent(
																						jLabel14)
																				.addComponent(
																						jLabel15)
																				.addComponent(
																						jLabel16))
																.addGap(63, 63,
																		63)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jComboBox5,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jComboBox2,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jTextField8)
																				.addComponent(
																						jTextField9)
																				.addComponent(
																						jTextField12)
																				.addComponent(
																						jTextField11)
																				.addComponent(
																						jComboBox4,
																						0,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jComboBox3,
																						0,
																						96,
																						Short.MAX_VALUE))))
								.addContainerGap(97, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel9)
												.addComponent(
														jComboBox1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(jLabel10)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextField8,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(jLabel11)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextField9,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(jLabel12)
												.addComponent(
														jTextField3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(jLabel13)
												.addComponent(
														jTextField4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextField11,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(jLabel14)
												.addComponent(
														jTextField5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextField12,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(jLabel15)
												.addComponent(
														jTextField6,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jTextField7,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel8)
												.addComponent(jLabel16)
												.addComponent(
														jComboBox4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										36, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(Cancel)
												.addComponent(Confirm))
								.addContainerGap()));

		pack();
	}// </editor-fold>

	protected void ConfirmActionPerformed(ActionEvent evt) {
		Acbuffer newbuf = null;
		try {
			newbuf = collectData(); // 收集数据
			// 验证mac和IP
			if (!verifyData(newbuf)) {
				return;
			}
			if (this.acbuffer == null) { // 新建流
				this.addAcDialog.getBufferList().add(newbuf);
			} else { // 修改流
				this.acbuffer.setBufferEnable(newbuf.getBufferEnable());
				this.acbuffer.setVlanId(newbuf.getVlanId());
				this.acbuffer.setSourceIp(newbuf.getSourceIp());
				this.acbuffer.setSourceMac(newbuf.getSourceMac());
				this.acbuffer.setTargetIp(newbuf.getTargetIp());
				this.acbuffer.setTargetMac(newbuf.getTargetMac());
				this.acbuffer.setEightIp(newbuf.getEightIp());
				this.acbuffer.setIpDscp(newbuf.getIpDscp());
				this.acbuffer.setModel(newbuf.getModel());
				this.acbuffer.setCir(newbuf.getCir());
				this.acbuffer.setPir(newbuf.getPir());
				this.acbuffer.setCm(newbuf.getCm());
				this.acbuffer.setCbs(newbuf.getCbs());
				this.acbuffer.setPbs(newbuf.getPbs());
				this.acbuffer.setStrategy(newbuf.getStrategy());
				this.acbuffer.setPhb(newbuf.getPhb());
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			newbuf = null;
		}

		this.dispose();
	}

	private boolean verifyData(Acbuffer newbuf) {
		boolean flag = true;

		if (newbuf.getSourceMac() != null && newbuf.getSourceMac().equals("")) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MACEMPTY));
			flag = false;
			return flag;
		}
		if (newbuf.getTargetMac() != null && newbuf.getTargetMac().equals("")) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MACEMPTY));
			flag = false;
			return flag;
		}

		if (newbuf.getSourceIp() != null && newbuf.getSourceIp().equals("")) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_IPEMPTY));
			flag = false;
			return flag;
		}

		if (newbuf.getTargetIp() != null && newbuf.getTargetIp().equals("")) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_IPEMPTY));
			flag = false;
			return flag;
		}
		// 验证mac
		if (!verifyMac(newbuf.getSourceMac())) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
			flag = false;
			return flag;
		}

		if (!verifyMac(newbuf.getTargetMac())) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
			flag = false;
			return flag;
		}

		// 验证IP
		if (!verifyIP(newbuf.getSourceIp())) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR));
			flag = false;
			return flag;
		}
		if (!verifyIP(newbuf.getTargetIp())) {
			DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_IPERROR));
			flag = false;
			return flag;
		}
		return flag;

	}

	private boolean verifyIP(String ip) {
		String[] ips = ip.split("\\.");
		boolean flag = true;
		if (ips.length == 4) {
			for (String str : ips) {
				char[] chars = str.toCharArray();
				for (char ch : chars) {
					if (ch < '0' || ch > '9') {
						flag = false;
						break;
					}
				}
				if (!flag) {
					break;
				}
				int intIp = Integer.parseInt(str);
				if (intIp < 0 || intIp > 255) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}

		return flag;
	}

	private boolean verifyMac(String mac) {
		String[] macStrs = mac.split("-");
		boolean flag = true;
		if (macStrs.length == 6) {
			for (String macStr : macStrs) {
				char[] chars = macStr.toCharArray();
				if (chars.length == 2) {
					String tempStr = null;
					for (char ch : chars) {
						char[] temp = new char[] { ch };
						tempStr = new String(temp);
						if (!tempStr.matches("\\p{XDigit}")) {
							flag = false;
							break;
						}
					}

				} else {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}

		return flag;
	}

	public Acbuffer collectData() {
		ControlKeyValue enable = (ControlKeyValue) jComboBox1.getSelectedItem();
		ControlKeyValue model = (ControlKeyValue) jComboBox2.getSelectedItem();
		ControlKeyValue cm = (ControlKeyValue) jComboBox5.getSelectedItem();
		ControlKeyValue strategy = (ControlKeyValue) jComboBox3
				.getSelectedItem();
		ControlKeyValue phb = (ControlKeyValue) jComboBox4.getSelectedItem();

		Acbuffer acbuffer = new Acbuffer();
		acbuffer.setBufferEnable(Integer.valueOf(((Code) enable.getObject())
				.getCodeValue()));
		acbuffer.setVlanId(Integer.valueOf(jTextField1.getText()));
		acbuffer.setSourceMac(jTextField2.getText());
		acbuffer.setTargetMac(jTextField3.getText());
		acbuffer.setEightIp(Integer.valueOf(jTextField4.getText()));
		acbuffer.setSourceIp(jTextField5.getText());
		acbuffer.setTargetIp(jTextField6.getText());
		acbuffer.setIpDscp(Integer.valueOf(jTextField7.getText()));
		acbuffer.setModel(Integer.valueOf(((Code) model.getObject())
				.getCodeValue()));
		acbuffer.setCir(Integer.valueOf(jTextField8.getText()));
		acbuffer.setPir(Integer.valueOf(jTextField9.getText()));
		acbuffer.setCm(Integer.valueOf(((Code) cm.getObject()).getCodeValue()));
		acbuffer.setCbs(Integer.valueOf(jTextField11.getText()));
		acbuffer.setPbs(Integer.valueOf(jTextField12.getText()));
		acbuffer.setStrategy(Integer.valueOf(((Code) strategy.getObject())
				.getCodeValue()));
		acbuffer.setPhb(Integer.valueOf(phb.getId()));

		return acbuffer;
	}

	// GEN-END:initComponents

	private void CancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				AddStreamDialog dialog = new AddStreamDialog(
						new javax.swing.JFrame(), true);
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
	private javax.swing.JButton Cancel;
	private javax.swing.JButton Confirm;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField2; // 源MAC
	private javax.swing.JTextField jTextField3; // 目的MAC
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5; // 源IP
	private javax.swing.JTextField jTextField6; // 目的IP
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;
	// End of variables declaration//GEN-END:variables

}