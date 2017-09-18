package com.nms.ui.ptn.ne.msp.bean;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

/**
 * msp端口信息bean 显示用
 * 
 * @author kk
 * 
 */
public class MspPortInfo extends ViewDataObj {

	private static final long serialVersionUID = 1L;
	private String portName; // 端口名称
	private String identity; // 主备身份
	private boolean workPort; // 实际工作端口
	private String protectStatus; // 保护状态
	private String k1byte; // 发送K1字节/接受K1字节
	private String k2byte; // 发送K2字节/接受K2字节

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public boolean isWorkPort() {
		return workPort;
	}

	public void setWorkPort(boolean workPort) {
		this.workPort = workPort;
	}

	public String getProtectStatus() {
		return protectStatus;
	}

	public void setProtectStatus(String protectStatus) {
		this.protectStatus = protectStatus;
	}

	public String getK1byte() {
		return k1byte;
	}

	public void setK1byte(String k1byte) {
		this.k1byte = k1byte;
	}

	public String getK2byte() {
		return k2byte;
	}

	public void setK2byte(String k2byte) {
		this.k2byte = k2byte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("portName", this.getPortName());
			getClientProperties().put("identity", this.getIdentity());
			getClientProperties().put("workPort", this.isWorkPort());
			getClientProperties().put("protectStatus", this.getProtectStatus());
			getClientProperties().put("k1byte", this.getK1byte());
			getClientProperties().put("k1byte", this.getK2byte());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

}
