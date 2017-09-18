package com.nms.db.bean.ptn.clock;
/**
 * 时钟源borba BEAN
 * @author dzy
 *
 */
public class ClockSource_Corba {
	
	private int id;//ID
	private String identifying;//唯一标识
	private int siteId;//网元ID
	private String name;//名称
	private int status;//时钟状态1=使用，0=未使用
	private int type;//类型（2=内部时钟源、0=外部时钟源、1=线路时钟等,3=保持(仅武汉)，4=自动(仅武汉)） 
	private int model;//工作模式（1=自动、2=自由振荡、3=保持）
	private int mainReferType;//主用参考源类型
	private int priorityLevel;//优先级
	private int ssmEnable;//ssm使能  1=使能 0=未使能
	private int jobStatus;//工作状态
	private int QualityLevel;//0=未知，1=G_811时钟，2=G_812转接时钟，3=G_812本地时钟 ，4=G_813时钟(SETS) ，5=不可用作同步
	public int getQualityLevel() {
		return QualityLevel;
	}
	public void setQualityLevel(int qualityLevel) {
		QualityLevel = qualityLevel;
	}
	private String siteName;//网元名称
	private String addition;//附加信息
	
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getMainReferType() {
		return mainReferType;
	}
	public void setMainReferType(int mainReferType) {
		this.mainReferType = mainReferType;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPriorityLevel() {
		return priorityLevel;
	}
	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}
	public int getSsmEnable() {
		return ssmEnable;
	}
	public void setSsmEnable(int ssmEnable) {
		this.ssmEnable = ssmEnable;
	}
	public int getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
