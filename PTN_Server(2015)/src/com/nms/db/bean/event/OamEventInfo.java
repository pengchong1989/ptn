package com.nms.db.bean.event;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class OamEventInfo extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6332440125898325867L;
	private int id;
	private int siteId;
	private String source;//事件源
	private String eventName;//事件名
	private int eventStatus;//事件状态
	private String time;//上报时间
	private String siteName;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("siteId", getSiteName());
		this.putClientProperty("port", getSource());
		this.putClientProperty("eventName", getEventName());
		this.putClientProperty("eventStatus", getEventStatus()==1?ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE):ResourceUtil.srcStr(StringKeysLbl.LBL_DISAPPEAR));
		this.putClientProperty("time", getTime());
	}
}
