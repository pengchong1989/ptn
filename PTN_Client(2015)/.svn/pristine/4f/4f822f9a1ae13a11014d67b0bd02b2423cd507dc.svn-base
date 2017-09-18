package com.nms.ui.ptn.business.testoam.controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.testoam.dialog.PwOamBusinessDialog;

public class PwOamBusinessController {
	private PwOamBusinessDialog view;

	public PwOamBusinessController(PwOamBusinessDialog view) {
		this.view = view;
		addListener();
	}

	private void addListener() {

		view.getLoopTlvLengthField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (view.getLoopEnableCheckBox().isSelected()) {
					if (checkTVLLength()) {
						return;
					}
				}
			}
		});
		view.getLoopTlvLengthField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (view.getLoopEnableCheckBox().isSelected()) {
					if (checkTVLLength()) {
						return;
					}
				}
			}
		});

		view.getLoopTLVInfoField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (view.getLoopEnableCheckBox().isSelected()) {
					if (checkTVLData()) {
						return;
					}
				}
			}
		});
		view.getLoopTLVInfoField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkTVLData()) {
					return;
				}
			}
		});

		view.getTstTLVLengthField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (view.getTstEnableCheckBox().isSelected()) {
					if (checkTSTTLVLength()) {
						return;
					}
				}
			}
		});
		view.getTstTLVLengthField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (view.getTstEnableCheckBox().isSelected()) {
					if (checkTSTTLVLength()) {
						return;
					}
				}
			}
		});
		
		view.getMelField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkMEL()) {
					return;
				}
			}
		});
		view.getMelField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkMEL()) {
					return;
				}
			}
		});
		
		view.getLocalField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkLocalId()) {
					return;
				}
			}
		});
		view.getLocalField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkLocalId()) {
					return;
				}
			}
		});
		
		view.getRemoteField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkremoteId()) {
					return;
				}
			}
		});
		view.getRemoteField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkremoteId()) {
					return;
				}
			}
		});

		view.getLbTTLField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkLbTTL()) {
					return;
				}
			}
		});
		view.getLbTTLField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkLbTTL()) {
					return;
				}
			}
		});
	}
	
	/**
	 * lbTTL
	 * 范围：1-255
	 * @return
	 */
	public boolean checkLbTTL() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getLbTTLField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getLbTTLField().getText()) || value > 255 || value < 1) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LB_TTL));
			view.getLbTTLField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getLbTTLField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}
	
	/**
	 * 验证源mepId
	 * 范围 0-8191
	 * @return
	 */
	public boolean checkLocalId() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getLocalField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getLocalField().getText()) || value > 8191 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_MEPID));
			view.getLocalField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getLocalField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}
	
	/**
	 * 验证目的mepId
	 * 范围 0-8191
	 * @return
	 */
	public boolean checkremoteId() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getRemoteField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getRemoteField().getText()) || value > 8191 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_MEPID));
			view.getRemoteField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getRemoteField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	/**
	 * 验证mel等级0-7
	 */
	public boolean checkMEL() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getMelField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getMelField().getText()) || value > 7 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_MELOUTOFLIMIT));
			view.getMelField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getMelField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	public boolean checkTVLLength() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getLoopTlvLengthField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getLoopTlvLengthField().getText()) || value > 65535 || value < 1) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_1_65535));
			view.getLoopTlvLengthField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getLoopTlvLengthField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	public boolean checkTVLData() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getLoopTLVInfoField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getLoopTLVInfoField().getText()) || value > 255 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_0_255));
			view.getLoopTLVInfoField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getLoopTLVInfoField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	public boolean checkTSTTLVLength() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getTstTLVLengthField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getTstTLVLengthField().getText()) || value > 65535 || value < 21) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_20_65536));
			view.getTstTLVLengthField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getTstTLVLengthField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}
}
