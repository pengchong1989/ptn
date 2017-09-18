package com.nms.ui.ptn.clock.view.cx.time;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.nms.ui.manager.ExceptionManage;

public class TextFiledKeyListener implements KeyListener {

	private int whichField=0;

	public TextFiledKeyListener(int whichField) {

		this.whichField = whichField;
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent keyEvent) {

		char c;
		try {

			c = keyEvent.getKeyChar();
			/* 只允许数字*/
			if (whichField == 1) {

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					keyEvent.consume();
				}
			}
			/* 允许数字和‘-’*/
			if (whichField == 2) {

				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_MINUS) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					keyEvent.consume();

				}   
			}
			/* 只允许数字和字母*/
			if (whichField == 3) {

				if (!((c >= '0') && (c <= '9') || ((c >= 'a') && (c <= 'z')))) {
					keyEvent.consume();
				}
			}

		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
