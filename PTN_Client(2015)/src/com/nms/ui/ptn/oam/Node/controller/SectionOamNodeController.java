package com.nms.ui.ptn.oam.Node.controller;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.oam.Node.view.SectionOamNodeDialog;

public class SectionOamNodeController {

	private SectionOamNodeDialog view;

	public SectionOamNodeController(SectionOamNodeDialog view) {
		this.view = view;
		addListener();
	}

	private void addListener() {
		view.getMegGradeField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkMEL()) {
					return;
				}
			}
		});
		view.getMegGradeField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkMEL()) {
					return;
				}
			}
		});
		view.getCycleTlvLengthField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (view.getCyclyeEnableCheckBox().isSelected()) {
					if (checkTVLLength()) {
						return;
					}
				}
			}
		});
		view.getCycleTlvLengthField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (view.getCyclyeEnableCheckBox().isSelected()) {
					if (checkTVLLength()) {
						return;
					}
				}
			}
		});
		view.getCycleTLVField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				if (view.getCyclyeEnableCheckBox().isSelected()) {
					if (checkTVLData()) {
						return;
					}
				}
			}
		});
		view.getCycleTLVField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (view.getCyclyeEnableCheckBox().isSelected()) {
					if (checkTVLData()) {
						return;
					}
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
		
		view.getCycleTlvLengthField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkCycleTlvLength()) {
					return;
				}
			}
		});
		view.getCycleTlvLengthField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkCycleTlvLength()) {
					return;
				}
			}
		});
		
		view.getCycleTLVField().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent me) {
				if (checkCycleTLV()) {
					return;
				}
			}
		});
		view.getCycleTLVField().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (checkCycleTLV()) {
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
	 * 环回tlv内容
	 * 范围：0-255
	 * @return
	 */
	public boolean checkCycleTLV() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getCycleTLVField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getCycleTLVField().getText()) || value > 255 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_CYCYLE_TLVCONTENT));
			view.getCycleTLVField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getCycleTLVField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	/**
	 * 环回tlv长度
	 * 范围：1-65535
	 * @return
	 */
	public boolean checkCycleTlvLength() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getCycleTlvLengthField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getCycleTlvLengthField().getText()) || value > 65535 || value < 1) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_CYCYLE_TLVLENGTH));
			view.getCycleTlvLengthField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getCycleTlvLengthField().setBorder(new LineBorder(Color.gray));
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
			value = Integer.valueOf(view.getMegGradeField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getMegGradeField().getText()) || value > 7 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_MELOUTOFLIMIT));
			view.getMegGradeField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getMegGradeField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	public boolean checkTVLLength() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getCycleTlvLengthField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getCycleTlvLengthField().getText()) || value > 65535 || value < 1) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_1_65535));
			view.getCycleTlvLengthField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getCycleTlvLengthField().setBorder(new LineBorder(Color.gray));
		view.getConfirm().setEnabled(true);
		view.getCancel().setEnabled(true);
		return false;
	}

	public boolean checkTVLData() {
		int value = -1;
		try {
			value = Integer.valueOf(view.getCycleTLVField().getText());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		if ("".equals(view.getCycleTLVField().getText()) || value > 255 || value < 0) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_0_255));
			view.getCycleTLVField().setBorder(new LineBorder(Color.RED));
			view.getConfirm().setEnabled(false);
			view.getCancel().setEnabled(false);
			return true;
		}
		view.getVertifyLabel().setText("    ");
		view.getCycleTLVField().setBorder(new LineBorder(Color.gray));
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
		if ("".equals(view.getTstTLVLengthField().getText()) || value > 65535 || value < 29) {
			view.getVertifyLabel().setText(ResourceUtil.srcStr(StringKeysTip.TIP_LIMIT_28_65536));
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