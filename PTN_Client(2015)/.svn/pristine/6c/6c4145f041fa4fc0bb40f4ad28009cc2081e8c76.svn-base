package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;

/**
 * 查询光功率
 * @author guoqc
 *
 */
public class SfpPowInfo extends ViewDataObj {
	private static final long serialVersionUID = 6946051248447164832L;
	private String linkName;//段名称
	private	String aPortName;//A端口名称
	private	String zPortName;//Z端口名称
	private String sfpTxPow;//光模块发射功率
	private String sfpRxPow;//光模块接收功率
	
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		getClientProperties().put("linkName", this.getLinkName());
		getClientProperties().put("aPortName", this.getaPortName());
		getClientProperties().put("zPortName", this.getzPortName());
		getClientProperties().put("sfpTxPow", this.getSfpTxPow());
		getClientProperties().put("sfpRxPow", this.getSfpRxPow());
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getaPortName() {
		return aPortName;
	}

	public void setaPortName(String aPortName) {
		this.aPortName = aPortName;
	}

	public String getzPortName() {
		return zPortName;
	}

	public void setzPortName(String zPortName) {
		this.zPortName = zPortName;
	}

	public String getSfpTxPow() {
		return sfpTxPow;
	}

	public void setSfpTxPow(String sfpTxPow) {
		this.sfpTxPow = sfpTxPow;
	}

	public String getSfpRxPow() {
		return sfpRxPow;
	}

	public void setSfpRxPow(String sfpRxPow) {
		this.sfpRxPow = sfpRxPow;
	}
}