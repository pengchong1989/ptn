package com.nms.db.bean.perform;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;

public class PathPerformCountInfo extends ViewDataObj implements Serializable {
	private static final long serialVersionUID = -7615425194028047793L;
	
	private int id;
	private String siteName;//网元名称
	private String objectName;//监控对象名称
	private String time;//查询时间
	private String packlosr_near="0.0";//近端丢包率
	private String packlosr_far="0.0";//远端丢包率
	private String pmpackdelay_s="0.0";//时延秒
	private String pmpackdelay_ns="0.0";//时延纳秒
	private String rx_cv="0.0";//接收cv包统计
	private String tx_cv="0.0";//发送cv包统计
	
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		this.getClientProperties().put("id", this.getId());
		this.getClientProperties().put("siteName", this.getSiteName());
		this.getClientProperties().put("time", this.getTime());
		this.getClientProperties().put("objectName", this.getObjectName());
		this.getClientProperties().put("packlosr_near", this.getPacklosr_near());
		this.getClientProperties().put("packlosr_far", this.getPacklosr_far());
		this.getClientProperties().put("pmpackdelay_s", this.getPmpackdelay_s());
		this.getClientProperties().put("pmpackdelay_ns", this.getPmpackdelay_ns());
		this.getClientProperties().put("rx_cv", this.getRx_cv());
		this.getClientProperties().put("tx_cv", this.getTx_cv());
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPacklosr_near() {
		return packlosr_near;
	}

	public void setPacklosr_near(String packlosrNear) {
		packlosr_near = packlosrNear;
	}

	public String getPacklosr_far() {
		return packlosr_far;
	}

	public void setPacklosr_far(String packlosrFar) {
		packlosr_far = packlosrFar;
	}

	public String getPmpackdelay_s() {
		return pmpackdelay_s;
	}

	public void setPmpackdelay_s(String pmpackdelayS) {
		pmpackdelay_s = pmpackdelayS;
	}

	public String getPmpackdelay_ns() {
		return pmpackdelay_ns;
	}

	public void setPmpackdelay_ns(String pmpackdelayNs) {
		pmpackdelay_ns = pmpackdelayNs;
	}

	public String getRx_cv() {
		return rx_cv;
	}

	public void setRx_cv(String rxCv) {
		rx_cv = rxCv;
	}

	public String getTx_cv() {
		return tx_cv;
	}

	public void setTx_cv(String txCv) {
		tx_cv = txCv;
	}
}
