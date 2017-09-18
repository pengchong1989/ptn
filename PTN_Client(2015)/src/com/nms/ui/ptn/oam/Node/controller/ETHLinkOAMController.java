package com.nms.ui.ptn.oam.Node.controller;

import com.nms.ui.frame.AbstractController;
import com.nms.ui.ptn.oam.Node.view.dialog.ETHLinkOamNodeDialog;

public class ETHLinkOAMController extends AbstractController {
	private ETHLinkOamNodeDialog view;

	public ETHLinkOAMController(ETHLinkOamNodeDialog view) {
		this.view = view;
	}

	public ETHLinkOamNodeDialog getView() {
		return view;
	}

	public void setView(ETHLinkOamNodeDialog view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
		 

	}

	@Override
	public void openCreateDialog() throws Exception {

	}

}
