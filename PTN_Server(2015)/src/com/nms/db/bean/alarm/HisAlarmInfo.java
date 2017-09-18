package com.nms.db.bean.alarm;

import com.nms.ui.ptn.alarm.AlarmTools;

public class HisAlarmInfo extends AlarmInfo{

	private static final long serialVersionUID = -5370629344231254340L;
	private String siteName;
	//槽位的数据库id
	private int slotId;
	private String ackUser;
	private WarningLevel warningLevel;
	private String alarmDesc;
	private String commonts;
	private int warningLevel_temp;
	private String isCleared;
	private String happenedtime;//insert方法最后赋值需要
	private String confirmtime;//insert方法最后赋值需要
	private String cleanTime;//insert方法最后赋值需要
	private int isClear;//insert方法最后赋值需要
	
	public int getIsClear() {
		return isClear;
	}

	public void setIsClear(int isClear) {
		this.isClear = isClear;
	}

	public String getCleanTime() {
		return cleanTime;
	}

	public void setCleanTime(String cleanTime) {
		this.cleanTime = cleanTime;
	}

	public String getConfirmtime() {
		return confirmtime;
	}

	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}

	public String getHappenedtime() {
		return happenedtime;
	}

	public void setHappenedtime(String happenedtime) {
		this.happenedtime = happenedtime;
	}

	public HisAlarmInfo() {	}
	
	public String getIsCleared()
	{
		return isCleared;
	}

	public void setIsCleared(String isCleared)
	{
		this.isCleared = isCleared;
	}

	public void setAckUser(String ackUser) {
		this.ackUser = ackUser;
	}

	public WarningLevel getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(WarningLevel warningLevel) {
		this.warningLevel = warningLevel;
	}

	public String getAlarmDesc() {
		return alarmDesc;
	}

	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
	}
	
	public String getAckUser() {
		return ackUser;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCommonts()
	{
		return commonts;
	}

	public void setCommonts(String commonts)
	{
		this.commonts = commonts;
	}
	
	public int getWarningLevel_temp() {
		return warningLevel_temp;
	}

	public void setWarningLevel_temp(int warningLevelTemp) {
		warningLevel_temp = warningLevelTemp;
	}

	public void putClientProperty() {
		this.putClientProperty("siteName", getSiteName());
		this.putClientProperty("alarmSource", getObjectType()+"/"+this.getObjectName());
		this.putClientProperty("alarmDesc", this.getAlarmDesc());
		this.putClientProperty("remarks", this.getCommonts());
		this.putClientProperty("ackUser", this.getAckUser());
		if(this.getWarningLevel() != null){
			AlarmTools alarmTools=new AlarmTools();
			this.putClientProperty("warningTypes",alarmTools.getAlarmType(getWarningLevel().getWarningtype()));
			this.putClientProperty("warningNotes", this.getWarningLevel().getWarningname());
		}
		this.putClientProperty("clearedTime", this.getClearedTime());
	}
}
