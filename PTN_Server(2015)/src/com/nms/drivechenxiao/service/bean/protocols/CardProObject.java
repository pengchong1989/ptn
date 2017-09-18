package com.nms.drivechenxiao.service.bean.protocols;

/**
 * 板卡保护
 * @author sy
 *
 */
public class CardProObject {
	
	private String neType;//网元 类型（cxt500,cxt100,）
	private String name;//板卡保护名称，（读不出来，需要解析时赋值）--不可更改
	private String card1 ;// 配置工作板卡------不可更改
	private String card2;// 配置保护板卡--不可更改
	private String cfgword;//工作板卡--不可更改
	private String apscmd;// 倒换命令      0： 无状态 1:强制到工作，2：强制到保护 3：人工到工作 4：人工到保护
	private String sel;//保护组选择器,表示当前的工作板卡
	
	
	public String getNeType() {
		return neType;
	}
	public void setNeType(String neType) {
		this.neType = neType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard1() {
		return card1;
	}
	public void setCard1(String card1) {
		this.card1 = card1;
	}
	public String getCard2() {
		return card2;
	}
	public void setCard2(String card2) {
		this.card2 = card2;
	}
	public String getCfgword() {
		return cfgword;
	}
	public void setCfgword(String cfgword) {
		this.cfgword = cfgword;
	}
	public String getApscmd() {
		return apscmd;
	}
	public void setApscmd(String apscmd) {
		this.apscmd = apscmd;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	
}

