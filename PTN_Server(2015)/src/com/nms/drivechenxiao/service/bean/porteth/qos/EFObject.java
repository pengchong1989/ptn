package com.nms.drivechenxiao.service.bean.porteth.qos;

public class EFObject {
	private String cir = ""; // 承诺信息速率 **
	private String used = ""; // 当前使用带宽
	private String greenwredstart = ""; // 绿色业务WRED丢弃低门限,单位为KB
	private String greenwredend = ""; // 绿色业务WRED丢弃高门限,单位为KB
	private String greendroprate = ""; // 绿色业务WRED 丢弃概率
	private String wreden = ""; // 使能WREAD
	
	public String toString(){
		return new StringBuffer().append(" cir=").append(cir)
		.append(" ;used=").append(used).append(" ;greenwredstart=").append(greenwredstart)
		.append(" ;greenwredend=").append(greenwredend).append(" ;greendroprate=").append(greendroprate)
		.append(" ;wreden=").append(wreden).toString();
	}

	public String getCir() {
		return cir;
	}

	public void setCir(String cir) {
		this.cir = cir;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getGreenwredstart() {
		return greenwredstart;
	}

	public void setGreenwredstart(String greenwredstart) {
		this.greenwredstart = greenwredstart;
	}

	public String getGreenwredend() {
		return greenwredend;
	}

	public void setGreenwredend(String greenwredend) {
		this.greenwredend = greenwredend;
	}

	public String getGreendroprate() {
		return greendroprate;
	}

	public void setGreendroprate(String greendroprate) {
		this.greendroprate = greendroprate;
	}

	public String getWreden() {
		return wreden;
	}

	public void setWreden(String wreden) {
		this.wreden = wreden;
	}

}
