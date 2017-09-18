package com.nms.db.bean.perform;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.ERunStates;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class PerformanceTaskInfo extends ViewDataObj implements java.io.Serializable{

	private static final long serialVersionUID = 2339084563422043673L;
	private int id;
	private SiteInst siteInst;
	// 性能任务对象数据库id
	private int objectId;
	// 性能任务对象名称
	private String objectName;
	//对象类型
	private EObjectType objectType;
	// 性能任务名称
	private String taskName;
	// 性能任务线程名称
	private String threadName;
	// 监控周期
	private EMonitorCycle monitorCycle;
	//用于标记创建任务的条目数
	private int taskLabel;
	// 创建时间
	private String createTime;
	// 结束时间
	private String endTime;
	// 运行状态
	private ERunStates runstates;
	// 创建者
	private String creater;
	// 描述
	private String describe;
	// 性能类型
	private String perforType;
	//查询板卡的历史性能:网元ID+板卡的盘地址
	private String slotCard;
	
	private int performanceCount =0;//性能个数
	private int performanceBeginCount=0;//性能起始个数
	private int performanceType;//性能数据类型
	private long performanceBeginDataTime;//性能查询时间
	
	private String beforeThreadName;//修改之前的线程名称
	private ERunStates beforeRunstates;//修改之前的运行状态
	
	
	public long getPerformanceBeginDataTime() {
		return performanceBeginDataTime;
	}

	public void setPerformanceBeginDataTime(long performanceBeginDataTime) {
		this.performanceBeginDataTime = performanceBeginDataTime;
	}

	public int getPerformanceType() {
		return performanceType;
	}

	public void setPerformanceType(int performanceType) {
		this.performanceType = performanceType;
	}

	public int getPerformanceCount() {
		return performanceCount;
	}

	public void setPerformanceCount(int performanceCount) {
		this.performanceCount = performanceCount;
	}

	public int getPerformanceBeginCount() {
		return performanceBeginCount;
	}

	public void setPerformanceBeginCount(int performanceBeginCount) {
		this.performanceBeginCount = performanceBeginCount;
	}

	public PerformanceTaskInfo() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public ERunStates getRunstates() {
		return runstates;
	}

	public void setRunstates(ERunStates runstates) {
		this.runstates = runstates;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public EMonitorCycle getMonitorCycle() {
		return monitorCycle;
	}

	public void setMonitorCycle(EMonitorCycle monitorCycle) {
		this.monitorCycle = monitorCycle;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public SiteInst getSiteInst() {
		return siteInst;
	}

	public void setSiteInst(SiteInst siteInst) {
		this.siteInst = siteInst;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public EObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(EObjectType objectType) {
		this.objectType = objectType;
	}

	public String getPerforType() {
		return perforType;
	}

	public void setPerforType(String perforType) {
		this.perforType = perforType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getSlotCard() {
		return slotCard;
	}

	public void setSlotCard(String slotCard) {
		this.slotCard = slotCard;
	}

	public String getBeforeThreadName() {
		return beforeThreadName;
	}

	public void setBeforeThreadName(String beforeThreadName) {
		this.beforeThreadName = beforeThreadName;
	}

	public ERunStates getBeforeRunstates() {
		return beforeRunstates;
	}

	public void setBeforeRunstates(ERunStates beforeRunstates) {
		this.beforeRunstates = beforeRunstates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			this.getClientProperties().put("id", this.getId());
			this.getClientProperties().put("siteName", this.getSiteInst().getCellId() + "");
			this.getClientProperties().put("taskName", this.getTaskName());
			this.getClientProperties().put("taskObj", this.getObjectName());
			if (this.getMonitorCycle() != null) {
				this.getClientProperties().put("taskCycle", this.getMonitorCycle());
			}
			if (this.getCreateTime() != null) {
				this.getClientProperties().put("createTime", this.getCreateTime());
			}
			if (this.getEndTime() != null) {
				this.getClientProperties().put("endTime", this.getEndTime());
			}
			if (this.getRunstates() != null) {
				this.getClientProperties().put("runStates", this.getRunstates());
			}
			this.getClientProperties().put("taskCreater", this.getCreater());
			this.getClientProperties().put("taskDesc", this.getDescribe());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public int getTaskLabel() {
		return taskLabel;
	}

	public void setTaskLabel(int taskLabel) {
		this.taskLabel = taskLabel;
	}
	
	
	
}
