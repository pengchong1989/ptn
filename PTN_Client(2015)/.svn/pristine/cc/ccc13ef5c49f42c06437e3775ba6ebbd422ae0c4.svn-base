package com.nms.ui.ptn.ne.tunnel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nms.ui.ptn.ne.tunnel.view.TunnelAddDialog;

public class TunnelAddDialogController {
	private TunnelAddDialog view;

	public TunnelAddDialogController(TunnelAddDialog view) {
		this.view = view;
		addListener();
	}

	private void addListener() {
		view.getCancel().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				view.dispose();
			
			}
		});
		
	}
}
