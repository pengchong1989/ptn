package com.nms.ui.ptn.ne.pw.controller;

import com.nms.ui.ptn.ne.pw.view.QosAddAfterPwDialog;

public class QosAddAfterPwDialogContorller {
	private QosAddAfterPwDialog view;

	public QosAddAfterPwDialogContorller(QosAddAfterPwDialog view) {
		this.view = view;
		addListener();
	}

	private void addListener() {
//
//		view.getReturnButton().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				final PwAddDialog dialog = new PwAddDialog(null, true, view.getPwInfo());
//				dialog.setSize(new Dimension(650, 400));
//				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//
//					@Override
//					public void windowClosing(WindowEvent arg0) {
//						dialog.dispose();
//					}
//
//				});
//				view.dispose();
//				dialog.setVisible(true);
//			}
//		});
//		view.getCancel().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				view.dispose();
//
//			}
//		});
//		view.getConfirm().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				PwDispatch pwdipatch = new PwDispatch();
//				try {
//					if (view.getPwInfo().getPwId() > 0)
//						pwdipatch.excutionUpdate(view.getPwInfo());
//					else
//						pwdipatch.excutionInsert(view.getPwInfo());
//				} catch (Exception e) {
//					ExceptionManage.dispose(e,this.getClass());
//				}
//				view.dispose();
//			}
//		});
	}

}
