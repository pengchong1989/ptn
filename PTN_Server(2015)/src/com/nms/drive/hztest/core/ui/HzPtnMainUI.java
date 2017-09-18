/*
 * HzPtnMainUI.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.drive.hztest.core.ui;

import com.nms.drive.hztest.core.CoreOper;
import com.nms.drive.hztest.core.HzMain;
import com.nms.drive.hztest.core.MonitorResponseThread;
import com.nms.drive.hztest.core.SendCommand;
import com.nms.drive.hztest.core.TcpNetwork;
import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * @author __USER__
 */
public class HzPtnMainUI extends javax.swing.JFrame {

	public javax.swing.JTextArea getJTextArea1() {
		return jTextArea1;
	}

	public javax.swing.JTextArea getJTextArea2() {
		return jTextArea2;
	}

	public void setJTextArea2(javax.swing.JTextArea textArea2) {
		jTextArea2 = textArea2;
	}

	public void setJTextArea1(javax.swing.JTextArea textArea1) {
		jTextArea1 = textArea1;
	}

	/** Creates new form HzPtnMainUI */
	public HzPtnMainUI() {
		initComponents();
		initJComboBox();
		initjScrollPane();
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jComboBox1 = new javax.swing.JComboBox();
		jButton7 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton5 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jButton6 = new javax.swing.JButton();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		exitMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("PTN\u6d4b\u8bd5");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jTextField1.setText("localhost");

		jTextField2.setText("3333");
		jTextField2.setAutoscrolls(false);

		jLabel1.setText("\u76ee\u6807\u5730\u5740\uff1a");

		jLabel2.setText("\u7aef\u53e3\uff1a");

		jButton1.setText("\u8fde\u63a5");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("\u64cd\u4f5c\u5217\u8868"));

		jComboBox1.setEnabled(false);

		jButton7.setText("\u6267\u884c");
		jButton7.setEnabled(false);
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(37, 37, 37).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(42, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton7).addContainerGap(19, Short.MAX_VALUE)));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("\u54cd\u5e94\u7ed3\u679c"));

		jTextArea1.setColumns(20);
		jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12));
		jTextArea1.setForeground(new java.awt.Color(255, 51, 102));
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE).addContainerGap()));

		jButton5.setText("\u65ad\u5f00\u8fde\u63a5");
		jButton5.setEnabled(false);
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("\u53d1\u9001\u547d\u4ee4"));

		jTextArea2.setColumns(20);
		jTextArea2.setFont(new java.awt.Font("Monospaced", 0, 12));
		jTextArea2.setForeground(new java.awt.Color(51, 102, 255));
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE).addContainerGap()));

		jButton6.setText("\u6e05\u5c4f");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addGap(28, 28, 28).addComponent(jLabel1).addGap(18, 18, 18).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(26, 26, 26).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jButton5).addGap(18, 18, 18).addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2).addComponent(jTextField2).addComponent(jButton1).addComponent(jButton5).addComponent(jButton6)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));

		fileMenu.setText("\u6587\u4ef6");

		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void initjScrollPane() {
		jScrollPane1.setHorizontalScrollBar(jScrollPane2.getHorizontalScrollBar());// 垂直
		jScrollPane1.setVerticalScrollBar(jScrollPane2.getVerticalScrollBar());// 水平
	}

	private void initJComboBox() {
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "----选择操作----", "登陆PTN", "HookNotify", "获得ne/interfaces/eth/ge.1.1的NNI" }));
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			tbgd();
			String oper = this.jComboBox1.getSelectedItem().toString();
			if ("登陆PTN".equals(oper)) {
				byte[] command = HzMain.getLoginBytes("admin", "", 0, 1, 1);
				sendCommand.write(command);
				this.jTextArea2.setText(this.jTextArea2.getText() + "--------------------------------------------\r\n" + CoreOper.print16String(command) + "\r\n");
			} else if ("HookNotify".equals(oper)) {
				byte[] command = HzMain.getHookNotifyBytes(1, 2);
				sendCommand.write(command);
				this.jTextArea2.setText(this.jTextArea2.getText() + "--------------------------------------------\r\n" + CoreOper.print16String(command) + "\r\n");
			} else if ("获得ne/interfaces/eth/ge.1.1的NNI".equals(oper)) {
				byte[] command = HzMain.getNNIBytes(1, 3);
				sendCommand.write(command);
				this.jTextArea2.setText(this.jTextArea2.getText() + "--------------------------------------------\r\n" + CoreOper.print16String(command) + "\r\n");
			}
			zdgd();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		this.jTextArea1.setText("");
		this.jTextArea2.setText("");
	}

	HzMain HzMain = new HzMain();
	SendCommand sendCommand = new SendCommand();
	TcpNetwork tcpNetwork = new TcpNetwork();

	public void zdgd() {
		jTextArea1.setCaretPosition(jTextArea1.getText().length());
		jTextArea2.setCaretPosition(jTextArea2.getText().length());
	}

	public void tbgd() {
		// 同步高度
		int JT1Rows = jTextArea1.getLineCount();// 收到
		int JT2Rows = jTextArea2.getLineCount();// 发送
		int cz = 0;
		if (JT1Rows > JT2Rows) {
			cz = JT1Rows - JT2Rows;
			for (int i = 0; i < cz; i++) {
				jTextArea2.setText(jTextArea2.getText() + "00000000\r\n");
			}
		} else if (JT2Rows > JT1Rows) {
			cz = JT2Rows - JT1Rows;
			for (int i = 0; i < cz; i++) {
				jTextArea1.setText(jTextArea1.getText() + "00000000\r\n");
			}
		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			String ipAddres = this.jTextField1.getText();
			int port = Integer.valueOf(this.jTextField2.getText());
			tcpNetwork.connect(ipAddres, port);

			sendCommand.setTcpNetwork(tcpNetwork);
			MonitorResponseThread monitorResponseThread = new MonitorResponseThread();
			monitorResponseThread.setHzPtnMainUI(this);
			monitorResponseThread.setTcpNetwork(tcpNetwork);
			monitorResponseThread.start();

			tbgd();
			this.getJTextArea1().setText("连接成功【" + ipAddres + "】 【" + port + "】\r\n");
			this.jTextArea2.setText("连接成功【" + ipAddres + "】 【" + port + "】\r\n");
			zdgd();
			this.jButton1.setEnabled(false);
			this.jButton5.setEnabled(true);
			this.jComboBox1.setEnabled(true);
			this.jButton7.setEnabled(true);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		this.jButton1.setEnabled(true);
		this.jButton5.setEnabled(false);
		this.jButton7.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		tcpNetwork.close();

		tbgd();
		this.getJTextArea1().setText(this.getJTextArea1().getText() + "断开连接成功【" + tcpNetwork.getClientSocket().getInetAddress().getHostName() + "】 【" + tcpNetwork.getClientSocket().getPort() + "】\r\n");
		this.jTextArea2.setText(jTextArea2.getText() + "断开连接成功【" + tcpNetwork.getClientSocket().getInetAddress().getHostName() + "】 【" + tcpNetwork.getClientSocket().getPort() + "】\r\n");
		zdgd();
	}

	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
		tcpNetwork.close();
		System.exit(0);
	}// GEN-LAST:event_exitMenuItemActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HzPtnMainUI().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JMenuBar menuBar;
	// End of variables declaration//GEN-END:variables

}