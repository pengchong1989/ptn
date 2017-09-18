package com.nms.ui.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import twaver.TWaverUtil;
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final ViewDataTable table = new ViewDataTable("abc");
		JButton button = new JButton("button");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				MyViewDataObj data = (MyViewDataObj) table.getDataBox().getLastSelectedElement();
//				System.out.println(data.getId());
			}
		});
		frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		frame.getContentPane().add(button,BorderLayout.NORTH);
		TWaverUtil.centerWindow(frame);
		frame.setSize(800, 600);
		frame.setVisible(true);
//		table.add(new MyViewDataObj());
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
