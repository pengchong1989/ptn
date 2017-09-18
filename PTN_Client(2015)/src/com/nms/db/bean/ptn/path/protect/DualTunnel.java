package com.nms.db.bean.ptn.path.protect;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 双规保护 tunnel bean
 * @author dzy
 *
 */
@SuppressWarnings("serial")
public class DualTunnel extends ViewDataObj{
	private String tunnelName;
	private int role;
	private int protectType;
	@Override
	public void putObjectProperty() {
		try{
			this.putClientProperty("tunnelName", this.getTunnelName());
			this.putClientProperty("protectType", UiUtil.getCodeById(this.getProtectType()).getCodeName());
			this.putClientProperty("role", UiUtil.getCodeById(this.getRole()).getCodeName());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public String getTunnelName() {
		return tunnelName;
	}
	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

}
