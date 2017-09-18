package com.nms.drive.network.test;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.ui.manager.ExceptionManage;

public class TestSiteFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int num = 0;
	InetAddress address = null;
	DatagramSocket client = null;

	public void setNum() {
		this.num++;
		this.textField.setText(num + "");
	}

	public TestSiteFrame() {

		try {
			client = new DatagramSocket();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		this.initComponent();
		this.setLayout();
		this.addAction();
		this.showWindow();
	}

	/**
	 * 添加监听
	 */
	private void addAction() {
		this.btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnAction();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		this.btnNum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(num + "");
			}

		});
	}

	/**
	 * 按钮事件
	 */
	private void btnAction() throws Exception {

		address = InetAddress.getByName(this.txtIp.getText());

		int num = Integer.parseInt(this.txtSiteNum.getText());

		for (int i = 1; i <= num; i++) {
			byte b = (byte) i;
			// byte b = 1;
			new Thread(new SendThread(b, this, this.client, this.address)).start();
			Thread.sleep(10);
		}
		// ThreadAction.threadsList();
		// System.out.println("当前线程总数"+);
	}

	private void showWindow() {
		this.setMinimumSize(new Dimension(350, 300));
		this.setVisible(true);
	}

	private void setLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 100, 200 };
		layout.columnWeights = new double[] { 0, 0 };
		layout.rowHeights = new int[] { 40, 40, 40, 40 };
		layout.rowWeights = new double[] { 0, 0, 0, 0 };
		this.panel.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		/** 第一行 端口名称 */
		c.gridx = 0;
		c.gridy = 0;
		layout.setConstraints(this.lblIp, c);
		this.panel.add(this.lblIp);

		c.gridx = 1;
		layout.setConstraints(this.txtIp, c);
		this.panel.add(this.txtIp);

		c.gridx = 0;
		c.gridy = 1;
		layout.setConstraints(this.lblSiteNum, c);
		this.panel.add(this.lblSiteNum);

		c.gridx = 1;
		layout.setConstraints(this.txtSiteNum, c);
		this.panel.add(this.txtSiteNum);

		c.gridx = 0;
		c.gridy = 2;
		layout.setConstraints(this.lblSend, c);
		this.panel.add(this.lblSend);

		c.gridx = 1;
		layout.setConstraints(this.textField, c);
		this.panel.add(this.textField);

		c.gridx = 0;
		c.gridy = 3;
		layout.setConstraints(this.btn, c);
		this.panel.add(this.btn);
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.btn = new JButton("开始发送告警");
		this.panel = new JPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.btnNum = new JButton("查询发送条数");

		this.add(this.panel);

		this.txtIp = new JTextField();
		this.txtSiteNum = new JTextField();
		this.lblIp = new JLabel("服务器IP");
		this.lblSiteNum = new JLabel("发包数量");
		this.lblSend = new JLabel("已发包数量");
		
		this.txtIp.setText("127.0.0.1");
		this.txtSiteNum.setText("100");

	}

	private JButton btn;
	private JPanel panel;
	private JTextField textField;
	private JButton btnNum;
	private JTextField txtIp;
	private JTextField txtSiteNum;
	private JLabel lblIp;
	private JLabel lblSiteNum;
	private JLabel lblSend;

	public static void main(String[] args) {

		new TestSiteFrame();
	}
}
