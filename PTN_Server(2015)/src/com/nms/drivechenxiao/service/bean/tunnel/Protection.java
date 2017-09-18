package com.nms.drivechenxiao.service.bean.tunnel;

public class Protection {
	private String desc = "";// 描述
	private String enaps = "";// 使能aps
	private String type = "";// 保护类型
	private String wtrtime = "";// 等待恢复时间
	private String holdofftime = "";// 拖延时间
	private String sdthreshold = "";// 是否使能sd告警触发倒换
	private String apscmd = "";// 外部倒换命令
	private String worklsp = "";
	private String prtlsp = "";
	private String apsstat = "";// 保护状态
	private String sel = "";// 保护组选择器
	private String clearcnt = "";
	private String arrayidx = "";// 数组下标索引

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEnaps() {
		return enaps;
	}

	public void setEnaps(String enaps) {
		this.enaps = enaps;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWtrtime() {
		return wtrtime;
	}

	public void setWtrtime(String wtrtime) {
		this.wtrtime = wtrtime;
	}

	public String getHoldofftime() {
		return holdofftime;
	}

	public void setHoldofftime(String holdofftime) {
		this.holdofftime = holdofftime;
	}

	public String getSdthreshold() {
		return sdthreshold;
	}

	public void setSdthreshold(String sdthreshold) {
		this.sdthreshold = sdthreshold;
	}

	public String getApscmd() {
		return apscmd;
	}

	public void setApscmd(String apscmd) {
		this.apscmd = apscmd;
	}

	public String getWorklsp() {
		return worklsp;
	}

	public void setWorklsp(String worklsp) {
		this.worklsp = worklsp;
	}

	public String getPrtlsp() {
		return prtlsp;
	}

	public void setPrtlsp(String prtlsp) {
		this.prtlsp = prtlsp;
	}

	public String getApsstat() {
		return apsstat;
	}

	public void setApsstat(String apsstat) {
		this.apsstat = apsstat;
	}

	public String getSel() {
		return sel;
	}

	public void setSel(String sel) {
		this.sel = sel;
	}

	public String getClearcnt() {
		return clearcnt;
	}

	public void setClearcnt(String clearcnt) {
		this.clearcnt = clearcnt;
	}

	public String getArrayidx() {
		return arrayidx;
	}

	public void setArrayidx(String arrayidx) {
		this.arrayidx = arrayidx;
	}

}
