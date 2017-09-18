package com.nms.ui.ptn.ne.tunnel.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.ne.tunnel.view.QosAddAfterTunnelDialog;
import com.nms.ui.ptn.ne.tunnel.view.TunnelAddDialog;

public class QosAddAfterTunnelDialogController {
	private QosAddAfterTunnelDialog view;

	public QosAddAfterTunnelDialogController(QosAddAfterTunnelDialog view) {
		this.view = view;
		addListener();
	}

	private void addListener() {
		view.getReturnButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final TunnelAddDialog dialog = new TunnelAddDialog(view.getTunnel(),null);
				dialog.setSize(new Dimension(650, 400));
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()),
						UiUtil.getWindowHeight(dialog.getHeight()));
				dialog.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent arg0) {
						dialog.dispose();
					}

				});
				view.dispose();
				dialog.setVisible(true);
			}
		});
		view.getConfirm().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					try {
						DispatchUtil dispatch=new DispatchUtil(RmiKeys.RMI_TUNNEL);
						if(view.getTunnel().getTunnelId()>0){
						dispatch.excuteUpdate(view.getTunnel());
						}else{
							List<Tunnel> tunnelList = new ArrayList<Tunnel>();
							tunnelList.add(view.getTunnel());
							dispatch.excuteInsert(tunnelList);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				
				view.dispose();
			}
		});
		view.getCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.dispose();
			}
		});
	}
}
