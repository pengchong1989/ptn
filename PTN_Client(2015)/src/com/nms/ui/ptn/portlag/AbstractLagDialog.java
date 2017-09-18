package com.nms.ui.ptn.portlag;

import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.ui.manager.control.PtnDialog;

public abstract class AbstractLagDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -534180837372049152L;

	public abstract PortLagInfo get();

	public abstract void initData(PortLagInfo portLagInfo);
}
