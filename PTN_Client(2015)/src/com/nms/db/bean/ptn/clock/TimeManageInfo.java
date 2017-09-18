package com.nms.db.bean.ptn.clock;

import com.nms.ui.frame.ViewDataObj;

/**
 * 
 * @author zhangkun
 *时钟管理/网元PTP配值信息
 */

public class TimeManageInfo extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5911968909664538793L;
	private int id;//自增、主键
	private int siteId;//关联网元表主键
	private int model;//模式
	private int ClockType;//时钟类型
	private int clockPrecision;//时钟精度
	private int clockVariance;//时钟方差
	private int priorOne;//优先级1
	private int priorTwo;//优先级2
	private int manufacturerOUI;//厂商OUI
	private int clockRegion;//时钟域
	private int clockRegionOne;//透传时钟域1
	private int clockRegionTwoJbox;//是否選中
	private int clockRegionTwo;//透传时钟域2
	private int clockRegionThreeJbox;//是否選中3
	private int clockRegionThree;//透传时钟域3
	private int clockRegionFourJbox;//是否選中4
	private int clockRegionFour;//透传时钟域4
	private int clockRegionDelay;//透传时钟延时机制
	private int followModel;//跟随模式
	private int todsendTime;//Tod发送时间类型
	
	private String timeID;//时钟ID
	private String timeType;//时钟类型
	private String ftimeID;//父时钟ID
	private String ftimePort;//父时钟端口
	private String leapNumber;//跳数
	private String systemVarianceValue;//1588和系统时间差值
	private String todState;//当前TOD状态
	private String zTimeID;//祖时钟ID
	private String zTimeTpye;//祖时钟类型
	private String zTimePrecision;//祖时钟精度
	private String zTimeVariance;//祖时钟方差
	private String zTimePriorOne;//祖时钟优先级1
	private String zTimePriorTwo;//祖时钟优先级2
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
	
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getClockType() {
		return ClockType;
	}
	public void setClockType(int clockType) {
		ClockType = clockType;
	}
	public int getClockPrecision() {
		return clockPrecision;
	}
	public void setClockPrecision(int clockPrecision) {
		this.clockPrecision = clockPrecision;
	}
	public int getClockVariance() {
		return clockVariance;
	}
	public void setClockVariance(int clockVariance) {
		this.clockVariance = clockVariance;
	}
	public int getPriorOne() {
		return priorOne;
	}
	public void setPriorOne(int priorOne) {
		this.priorOne = priorOne;
	}
	public int getPriorTwo() {
		return priorTwo;
	}
	public void setPriorTwo(int priorTwo) {
		this.priorTwo = priorTwo;
	}
	public int getManufacturerOUI() {
		return manufacturerOUI;
	}
	public void setManufacturerOUI(int manufacturerOUI) {
		this.manufacturerOUI = manufacturerOUI;
	}
	public int getClockRegion() {
		return clockRegion;
	}
	public void setClockRegion(int clockRegion) {
		this.clockRegion = clockRegion;
	}
	public int getClockRegionOne() {
		return clockRegionOne;
	}
	public void setClockRegionOne(int clockRegionOne) {
		this.clockRegionOne = clockRegionOne;
	}
	public int getClockRegionTwo() {
		return clockRegionTwo;
	}
	public void setClockRegionTwo(int clockRegionTwo) {
		this.clockRegionTwo = clockRegionTwo;
	}
	public int getClockRegionThree() {
		return clockRegionThree;
	}
	public void setClockRegionThree(int clockRegionThree) {
		this.clockRegionThree = clockRegionThree;
	}
	public int getClockRegionFour() {
		return clockRegionFour;
	}
	public void setClockRegionFour(int clockRegionFour) {
		this.clockRegionFour = clockRegionFour;
	}
	public int getClockRegionDelay() {
		return clockRegionDelay;
	}
	public void setClockRegionDelay(int clockRegionDelay) {
		this.clockRegionDelay = clockRegionDelay;
	}
	public int getFollowModel() {
		return followModel;
	}
	public void setFollowModel(int followModel) {
		this.followModel = followModel;
	}
	public int getTodsendTime() {
		return todsendTime;
	}
	public void setTodsendTime(int todsendTime) {
		this.todsendTime = todsendTime;
	}
	public String getTimeID() {
		return timeID;
	}
	public void setTimeID(String timeID) {
		this.timeID = timeID;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getFtimeID() {
		return ftimeID;
	}
	public void setFtimeID(String ftimeID) {
		this.ftimeID = ftimeID;
	}
	public String getFtimePort() {
		return ftimePort;
	}
	public void setFtimePort(String ftimePort) {
		this.ftimePort = ftimePort;
	}
	public String getLeapNumber() {
		return leapNumber;
	}
	public void setLeapNumber(String leapNumber) {
		this.leapNumber = leapNumber;
	}
	public String getSystemVarianceValue() {
		return systemVarianceValue;
	}
	public void setSystemVarianceValue(String systemVarianceValue) {
		this.systemVarianceValue = systemVarianceValue;
	}
	public String getTodState() {
		return todState;
	}
	public void setTodState(String todState) {
		this.todState = todState;
	}
	public String getzTimeID() {
		return zTimeID;
	}
	public void setzTimeID(String zTimeID) {
		this.zTimeID = zTimeID;
	}
	public String getzTimeTpye() {
		return zTimeTpye;
	}
	public void setzTimeTpye(String zTimeTpye) {
		this.zTimeTpye = zTimeTpye;
	}
	public String getzTimePrecision() {
		return zTimePrecision;
	}
	public void setzTimePrecision(String zTimePrecision) {
		this.zTimePrecision = zTimePrecision;
	}
	public String getzTimeVariance() {
		return zTimeVariance;
	}
	public void setzTimeVariance(String zTimeVariance) {
		this.zTimeVariance = zTimeVariance;
	}
	public String getzTimePriorOne() {
		return zTimePriorOne;
	}
	public void setzTimePriorOne(String zTimePriorOne) {
		this.zTimePriorOne = zTimePriorOne;
	}
	public String getzTimePriorTwo() {
		return zTimePriorTwo;
	}
	public void setzTimePriorTwo(String zTimePriorTwo) {
		this.zTimePriorTwo = zTimePriorTwo;
	}
	public int getClockRegionTwoJbox() {
		return clockRegionTwoJbox;
	}
	public void setClockRegionTwoJbox(int clockRegionTwoJbox) {
		this.clockRegionTwoJbox = clockRegionTwoJbox;
	}
	public int getClockRegionThreeJbox() {
		return clockRegionThreeJbox;
	}
	public void setClockRegionThreeJbox(int clockRegionThreeJbox) {
		this.clockRegionThreeJbox = clockRegionThreeJbox;
	}
	public int getClockRegionFourJbox() {
		return clockRegionFourJbox;
	}
	public void setClockRegionFourJbox(int clockRegionFourJbox) {
		this.clockRegionFourJbox = clockRegionFourJbox;
	}
	@Override
	public void putObjectProperty() {
		
	}
	
}
