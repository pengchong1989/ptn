package com.nms.drive.service.bean;

public class SmartFanObject {
	private int id;//条目id
	private int siteId;//网元id
	private int workType;//模式: 0/1 = 自选模式/智能模式
	private int gearLevel;//0/1/2/3=智能选择/档位1/档位2/档位3 自选模式下可选档位1/2/3, 智能模式下只有智能选择

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
