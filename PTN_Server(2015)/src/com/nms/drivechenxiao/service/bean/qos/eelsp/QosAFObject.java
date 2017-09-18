package com.nms.drivechenxiao.service.bean.qos.eelsp;

public class QosAFObject {
	private String icir = ""; // 输入承诺信息速率 **
	private String icbs = ""; // 输入承诺突发长度
	private String ieir = ""; // 输入额外速率 **
	private String iebs = ""; // 输入额外突发长度
	private String ocir = ""; // 输出承诺信息速率 **
	private String ocbs = ""; // 输出承诺突发长度
	private String oeir = ""; // 输出额外速率 **
	private String oebs = ""; // 输出额外突发长度
	private String ipir = ""; // 输入最大峰值速率
	private String opir = ""; // 输出最大峰值速率

	public String getIcir() {
		return icir;
	}

	public void setIcir(String icir) {
		this.icir = icir;
	}

	public String getIcbs() {
		return icbs;
	}

	public void setIcbs(String icbs) {
		this.icbs = icbs;
	}

	public String getIeir() {
		return ieir;
	}

	public void setIeir(String ieir) {
		this.ieir = ieir;
	}

	public String getIebs() {
		return iebs;
	}

	public void setIebs(String iebs) {
		this.iebs = iebs;
	}

	public String getOcir() {
		return ocir;
	}

	public void setOcir(String ocir) {
		this.ocir = ocir;
	}

	public String getOcbs() {
		return ocbs;
	}

	public void setOcbs(String ocbs) {
		this.ocbs = ocbs;
	}

	public String getOeir() {
		return oeir;
	}

	public void setOeir(String oeir) {
		this.oeir = oeir;
	}

	public String getOebs() {
		return oebs;
	}

	public void setOebs(String oebs) {
		this.oebs = oebs;
	}

	public String getIpir() {
		return ipir;
	}

	public void setIpir(String ipir) {
		this.ipir = ipir;
	}

	public String getOpir() {
		return opir;
	}

	public void setOpir(String opir) {
		this.opir = opir;
	}

}
