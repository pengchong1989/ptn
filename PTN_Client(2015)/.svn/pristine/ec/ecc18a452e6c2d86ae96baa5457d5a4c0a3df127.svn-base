package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
/**
 * @author guoqc
 */
public class SmartFan extends ViewDataObj{
	private static final long serialVersionUID = -164156013729720252L;
	private int id;
	private int siteId;//网元id
	private int workType;//模式: 0/1 = 自选模式/智能模式
	private String workTypeLog;//log日志使用
	private int gearLevel;//0/1/2/3=智能选择/档位1/档位2/档位3 自选模式下可选档位1/2/3, 智能模式下只有智能选择
	private String gearLevelLog;//log日志使用
	
	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", getId());
		this.putClientProperty("workType", getWorkType() == 1 ? "智能模式" : "自选模式");
		this.putClientProperty("gearLevel", this.getLevel());
	}

	private String getLevel() {
		if(this.getGearLevel() == 0){
			return "智能选择";
		}else if(this.getGearLevel() == 1){
			return "档位1";
		}else if(this.getGearLevel() == 2){
			return "档位2";
		}else{
			return "档位3";
		}
	}
	
	public String getWorkTypeLog() {
		return workTypeLog;
	}

	public void setWorkTypeLog(String workTypeLog) {
		this.workTypeLog = workTypeLog;
	}

	public String getGearLevelLog() {
		return gearLevelLog;
	}

	public void setGearLevelLog(String gearLevelLog) {
		this.gearLevelLog = gearLevelLog;
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

	public int getWorkType() {
		return workType;
	}

	public void setWorkType(int workType) {
		this.workType = workType;
	}

	public int getGearLevel() {
		return gearLevel;
	}

	public void setGearLevel(int gearLevel) {
		this.gearLevel = gearLevel;
	}
}