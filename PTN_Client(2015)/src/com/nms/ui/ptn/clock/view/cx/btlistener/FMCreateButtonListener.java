package com.nms.ui.ptn.clock.view.cx.btlistener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;

import com.nms.ui.manager.ExceptionManage;

public class FMCreateButtonListener extends ButtonActionListener {

	private JDialog jDialog = null;

	public FMCreateButtonListener(JDialog jDialog) {

		this.jDialog = jDialog;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		JButton jButton = null;
		String str = null;
		try {
			jButton = (JButton) actionEvent.getSource();
			str = jButton.getText();
			if ("取消".equalsIgnoreCase(str)) {

				this.jDialog.dispose();
			}
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}

	}

}
