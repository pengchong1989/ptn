package com.nms.db.bean.perform;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;

public class CurrPerformCountInfo extends ViewDataObj implements Serializable {
	private static final long serialVersionUID = -7615425194028047793L;
	
	private int id;
	private String siteName;//网元名称
	private String objectName;//监控对象名称
	private String time;//查询时间
	private String receiveByte_before="0";
	private String sendByte_before="0";
	private String receiveByte="0";//收字节(KB)
	private String sendByte="0";//发字节(KB)
	private String inBandWidthUtil;//入带宽利用率(%)
	private String outBandWidthUtil;//出带宽利用率(%)
	
	public String getReceiveByte_before() {
		return receiveByte_before;
	}

	public void setReceiveByte_before(String receiveByteBefore) {
		receiveByte_before = receiveByteBefore;
	}

	public String getSendByte_before() {
		return sendByte_before;
	}

	public void setSendByte_before(String sendByteBefore) {
		sendByte_before = sendByteBefore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
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

	public String getReceiveByte() {
		return receiveByte;
	}

	public void setReceiveByte(String receiveByte) {
		this.receiveByte = receiveByte;
	}

	public String getSendByte() {
		return sendByte;
	}

	public void setSendByte(String sendByte) {
		this.sendByte = sendByte;
	}

	public String getInBandWidthUtil() {
		return inBandWidthUtil;
	}

	public void setInBandWidthUtil(String inBandWidthUtil) {
		this.inBandWidthUtil = inBandWidthUtil;
	}

	public String getOutBandWidthUtil() {
		return outBandWidthUtil;
	}

	public void setOutBandWidthUtil(String outBandWidthUtil) {
		this.outBandWidthUtil = outBandWidthUtil;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		this.getClientProperties().put("id", this.getId());
		this.getClientProperties().put("siteName", this.getSiteName());
		this.getClientProperties().put("time", this.getTime());
		this.getClientProperties().put("objectName", this.getObjectName());
		this.getClientProperties().put("receiveByte", this.getReceiveByte());
		this.getClientProperties().put("sendByte", this.getSendByte());
		this.getClientProperties().put("inBandwidthUtil", this.getInBandWidthUtil());
		this.getClientProperties().put("outBandwidthUtil", this.getOutBandWidthUtil());
	}
}
