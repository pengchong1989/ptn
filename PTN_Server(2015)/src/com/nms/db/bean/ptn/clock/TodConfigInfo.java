package com.nms.db.bean.ptn.clock;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.UiUtil;
/**
 * @author zhangkun 
 *function:时间管理中TOD配置Bean
 */
public class TodConfigInfo extends ViewDataObj {

	private static final long serialVersionUID = -5480947999164867042L;

	
	private int id;// 自增、主键
	private int siteId;// 关联网元表主键
	private String port;// 端口
	private int interfaceType;// 接口类型
	private String physicalStatus;// 物理状态
	private String logicalStatus;// 逻辑状态
	private String priority1;// 优先级1
	private String clockType;// 时钟类型
	private String clockAccuracy;// 时钟精度
	private String clockVariance;// 时钟方差
	private String priority2;// 优先级2

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

	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(int interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}

	public String getLogicalStatus() {
		return logicalStatus;
	}

	public void setLogicalStatus(String logicalStatus) {
		this.logicalStatus = logicalStatus;
	}

	public String getPriority1() {
		return priority1;
	}

	public void setPriority1(String priority1) {
		this.priority1 = priority1;
	}

	public String getClockType() {
		return clockType;
	}

	public void setClockType(String clockType) {
		this.clockType = clockType;
	}

	public String getClockAccuracy() {
		return clockAccuracy;
	}

	public void setClockAccuracy(String clockAccuracy) {
		this.clockAccuracy = clockAccuracy;
	}

	public String getClockVariance() {
		return clockVariance;
	}

	public void setClockVariance(String clockVariance) {
		this.clockVariance = clockVariance;
	}

	public String getPriority2() {
		return priority2;
	}

	public void setPriority2(String priority2) {
		this.priority2 = priority2;
	}

	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("port", getPort());
			this.putClientProperty("interfaceType", UiUtil.getCodeById(this.getInterfaceType()).getCodeName());
//			this.putClientProperty("interfaceType", this.getInterfaceType());
			this.putClientProperty("PhysicalStatus", this.getPhysicalStatus());
			this.putClientProperty("LogicalStatus", this.getLogicalStatus());
			this.putClientProperty("Priority1", this.getPriority1());
			this.putClientProperty("ClockType", this.getClockType());
			this.putClientProperty("ClockAccuracy", this.getClockAccuracy());
			this.putClientProperty("ClockVariance", this.getClockVariance());
			this.putClientProperty("Priority2", this.getPriority2());
		} catch (Exception e) {
		}

	}

}
