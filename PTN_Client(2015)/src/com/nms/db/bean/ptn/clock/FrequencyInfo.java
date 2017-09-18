package com.nms.db.bean.ptn.clock;

import java.io.Serializable;
import java.util.List;
import com.nms.db.bean.ptn.CommonBean;




public class FrequencyInfo implements Serializable{

	private static final long serialVersionUID = 6864054827146242229L;
	private int id;
	private int siteId;
	private String siteName;
	private int smOuter;// SM门限外时钟
	private int smSystem;// SM门限系统时钟
	private int clockWorkMode; // 时钟工作模式
	private int qlEnable;// QL使能选择式
	private String clockPRIList;// 时钟优先级排列
	
	private int outerClockInSelect;// 外时钟输入选择
	private int outerClockOutSelect1;// 外时钟输出使能选择1
	private int outerClockOutSelect2;// 外时钟输出使能选择2
	private String outSelectList;// 输出时钟选择			
	private String clockInQLValueList;// 输入源QL值GE1
	private int qlIn;// QL使用SA选择输入源SA选择
	private int qlOut;// QL使用SA选择输出源SA选择
	private String clockOutQLValueList;// 输出源的QL值GE1
	private int clockInResumeTime;// 时钟输入源等待恢复时间
	private String waitResumeTime; // 等待恢复时间开关
	private String ClockPri="ClockPri";
	private List<CommonBean> clockList;
	private String OuterClockSelect="OuterClockSelect";		
	private List<CommonBean> outerClockList;
	private String InQL="InQL";
	private List<CommonBean> QLlist;	
	private String OuterQL="OuterQL";
	private List<CommonBean> OuterQlList;	
	private String WaitForReciveryTimeSwitch="WaitForReciveryTimeSwitch";
	private List<CommonBean> waitResumeTimeList;
	private int clockRarate=0;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getWaitResume() {
		return WaitForReciveryTimeSwitch;
	}

	public void setWaitResume(String WaitForReciveryTimeSwitch) {
		this.WaitForReciveryTimeSwitch = WaitForReciveryTimeSwitch;
	}

	public String getOuterQL() {
		return OuterQL;
	}

	public void setOuterQL(String outerQL) {
		OuterQL = outerQL;
	}

	public String getInQL() {
		return InQL;
	}

	public void setInQL(String QL) {
		InQL = QL;
	}

	public String getOuterClockSelect() {
		return OuterClockSelect;
	}

	public void setOuterClockSelect(String OuterClockSelect) {
		this.OuterClockSelect = OuterClockSelect;
	}

	public String getClockPri() {
		return ClockPri;
	}

	public void setClockPri(String ClockPri) {
		this.ClockPri = ClockPri;
	}

	public List<CommonBean> getOuterQlList() {
		return OuterQlList;
	}

	public void setOuterQlList(List<CommonBean> outerQlList) {
		OuterQlList = outerQlList;
	}

	public List<CommonBean> getQLlist() {
		return QLlist;
	}

	public void setQLlist(List<CommonBean> qLlist) {
		QLlist = qLlist;
	}

	public List<CommonBean> getOuterClockList() {
		return outerClockList;
	}

	public void setOuterClockList(List<CommonBean> outerClockList) {
		this.outerClockList = outerClockList;
	}

	public List<CommonBean> getClockList() {
		return clockList;
	}

	public void setClockList(List<CommonBean> clockList) {
		this.clockList = clockList;
	}

	public List<CommonBean> getWaitResumeTimeList() {
		return waitResumeTimeList;
	}

	public void setWaitResumeTimeList(List<CommonBean> waitResumeTimeList) {
		this.waitResumeTimeList = waitResumeTimeList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getClockWorkMode() {
		return clockWorkMode;
	}

	public void setClockWorkMode(int clockWorkMode) {
		this.clockWorkMode = clockWorkMode;
	}

	public int getQlEnable() {
		return qlEnable;
	}

	public void setQlEnable(int qlEnable) {
		this.qlEnable = qlEnable;
	}

	public String getClockPRIList() {
		return clockPRIList;
	}

	public void setClockPRIList(String clockPRIList) {
		this.clockPRIList = clockPRIList;
	}

	public int getOuterClockInSelect() {
		return outerClockInSelect;
	}

	public void setOuterClockInSelect(int outerClockInSelect) {
		this.outerClockInSelect = outerClockInSelect;
	}

	public int getOuterClockOutSelect1() {
		return outerClockOutSelect1;
	}

	public void setOuterClockOutSelect1(int outerClockOutSelect1) {
		this.outerClockOutSelect1 = outerClockOutSelect1;
	}

	public int getOuterClockOutSelect2() {
		return outerClockOutSelect2;
	}

	public void setOuterClockOutSelect2(int outerClockOutSelect2) {
		this.outerClockOutSelect2 = outerClockOutSelect2;
	}

	public String getOutSelectList() {
		return outSelectList;
	}

	public void setOutSelectList(String outSelectList) {
		this.outSelectList = outSelectList;
	}

	public int getSmOuter() {
		return smOuter;
	}

	public void setSmOuter(int smOuter) {
		this.smOuter = smOuter;
	}

	public int getSmSystem() {
		return smSystem;
	}

	public void setSmSystem(int smSystem) {
		this.smSystem = smSystem;
	}

	public int getQlIn() {
		return qlIn;
	}

	public void setQlIn(int qlIn) {
		this.qlIn = qlIn;
	}

	public int getQlOut() {
		return qlOut;
	}

	public void setQlOut(int qlOut) {
		this.qlOut = qlOut;
	}

	public String getClockInQLValueList() {
		return clockInQLValueList;
	}

	public void setClockInQLValueList(String clockInQLValueList) {
		this.clockInQLValueList = clockInQLValueList;
	}

	public String getClockOutQLValueList() {
		return clockOutQLValueList;
	}

	public void setClockOutQLValueList(String clockOutQLValueList) {
		this.clockOutQLValueList = clockOutQLValueList;
	}

	public int getClockInResumeTime() {
		return clockInResumeTime;
	}

	public void setClockInResumeTime(int clockInResumeTime) {
		this.clockInResumeTime = clockInResumeTime;
	}

	public String getWaitResumeTime() {
		return waitResumeTime;
	}

	public void setWaitResumeTime(String waitResumeTime) {
		this.waitResumeTime = waitResumeTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getClockRarate() {
		return clockRarate;
	}

	public void setClockRarate(int clockRarate) {
		this.clockRarate = clockRarate;
	}
	
}
