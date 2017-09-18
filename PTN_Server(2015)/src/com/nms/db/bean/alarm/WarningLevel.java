package com.nms.db.bean.alarm;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.alarm.AlarmTools;


@SuppressWarnings("serial")
public class WarningLevel extends ViewDataObj {
	private int id;
	private String warningname;
	private String warningnote;
	private int warninglevel;
	private int warninglevel_temp;
	private int warningcode;
	private String warningobject;
	private int warningtype;
	private String warningdescribe;
	private String waringeffect;
	private String waringremark;
	private String warningadvice;
	private String warningmayreason;
	private int manufacturer;  //1表示武汉，2表示晨晓
	private String warningEnNote;//英文名称

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWarningname() {
		return warningname;
	}

	public void setWarningname(String warningname) {
		this.warningname = warningname;
	}

	public String getWarningnote() {
		return warningnote;
	}

	public void setWarningnote(String warningnote) {
		this.warningnote = warningnote;
	}

	public int getWarninglevel() {
		return warninglevel;
	}

	public void setWarninglevel(int warninglevel) {
		this.warninglevel = warninglevel;
	}

	public int getWarningcode() {
		return warningcode;
	}

	public void setWarningcode(int warningcode) {
		this.warningcode = warningcode;
	}

	public String getWarningobject() {
		return warningobject;
	}

	public void setWarningobject(String warningobject) {
		this.warningobject = warningobject;
	}

	public int getWarningtype() {
		return warningtype;
	}

	public void setWarningtype(int warningtype) {
		this.warningtype = warningtype;
	}

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getWarningdescribe() {
		return warningdescribe;
	}

	public void setWarningdescribe(String warningdescribe) {
		this.warningdescribe = warningdescribe;
	}

	public String getWaringeffect() {
		return waringeffect;
	}

	public void setWaringeffect(String waringeffect) {
		this.waringeffect = waringeffect;
	}

	public String getWaringremark() {
		return waringremark;
	}

	public void setWaringremark(String waringremark) {
		this.waringremark = waringremark;
	}

	public int getWarninglevel_temp() {
		return warninglevel_temp;
	}

	public void setWarninglevel_temp(int warninglevelTemp) {
		warninglevel_temp = warninglevelTemp;
	}

	

	public String getWarningmayreason() {
		return warningmayreason;
	}

	public void setWarningmayreason(String warningmayreason) {
		this.warningmayreason = warningmayreason;
	}

	public String getWarningadvice() {
		return warningadvice;
	}

	public void setWarningadvice(String warningadvice) {
		this.warningadvice = warningadvice;
	}
	
	@Override
	public void putObjectProperty() {
		AlarmTools alarmTools =new AlarmTools();
		this.putClientProperty("id", getId());
		this.putClientProperty("alarmname", this.getWarningname());
		this.putClientProperty("alarmleavel",  this.convertAlarmLevel(this.getWarninglevel_temp()));
		this.putClientProperty("alarmtype", alarmTools.getAlarmType(this.getWarningtype()));
		this.putClientProperty("influence", this.getWaringeffect());
		
		if(ResourceUtil.language.equals("zh_CN")){
			this.putClientProperty("desc", this.getWarningdescribe());
		}else{
			//英文 显示，未找到字段
		}
		
		this.putClientProperty("remark",  this.getWaringremark());
	}
	
	/**
	 * 把告警代码转成文字
	 * 
	 * @param code
	 *            告警代码
	 * @return 告警字符串
	 */
	public String convertAlarmLevel(int code) {
		String alarmLevelStr = "";

		switch (code) {
		case 0:
			alarmLevelStr ="";
			break;
		case 1:
			alarmLevelStr = ResourceUtil.srcStr(StringKeysLbl.LBL_UnknowAlarm);
			break;
		case 2:
			alarmLevelStr = ResourceUtil.srcStr(StringKeysLbl.LBL_PromptAlarm);
			break;
		case 3:
			alarmLevelStr = ResourceUtil.srcStr(StringKeysLbl.LBL_MinorAlarm);
			break;
		case 4:
			alarmLevelStr = ResourceUtil.srcStr(StringKeysLbl.LBL_MajorAlarm);
			break;
		case 5:
			alarmLevelStr = ResourceUtil.srcStr(StringKeysLbl.LBL_UrgencyAlarm);
			break;
		}
		return alarmLevelStr;
	}

	public String getWarningEnNote() {
		return warningEnNote;
	}

	public void setWarningEnNote(String warningEnNote) {
		this.warningEnNote = warningEnNote;
	}

}
