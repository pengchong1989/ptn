package com.nms.db.bean.report;

import com.nms.ui.frame.ViewDataObj;

/**
 * 
 * @author xxx
 *
 */
public class SSPath extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id ;//序号
	private String SiteName = ""; //网元名称
	private String PathType; //路径类型
	private String PathCount; //路径数量
	private String PathUsed; //已使用路径数量
	private String PathUnUsed; //空闲路径数量
	
	@Override
	public void putObjectProperty() {
		this.putClientProperty("id",this.getId());
		this.putClientProperty("SiteName",this.getSiteName());
		this.putClientProperty("PathType",this.getPathType());
		this.putClientProperty("PathCount",this.getPathCount());
		this.putClientProperty("PathUsed",this.getPathUsed());
		this.putClientProperty("PathUnUsed",this.getPathUnUsed());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSiteName() {
		return SiteName;
	}
	public void setSiteName(String siteName) {
		SiteName = siteName;
	}
	public String getPathType() {
		return PathType;
	}
	public void setPathType(String pathType) {
		PathType = pathType;
	}
	public String getPathCount() {
		return PathCount;
	}
	public void setPathCount(String pathCount) {
		PathCount = pathCount;
	}
	public String getPathUsed() {
		return PathUsed;
	}
	public void setPathUsed(String pathUsed) {
		PathUsed = pathUsed;
	}
	public String getPathUnUsed() {
		return PathUnUsed;
	}
	public void setPathUnUsed(String pathUnUsed) {
		PathUnUsed = pathUnUsed;
	}
}
