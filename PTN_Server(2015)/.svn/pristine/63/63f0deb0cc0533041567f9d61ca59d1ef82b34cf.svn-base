package com.nms.db.bean.system;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;


public class SiteLock extends ViewDataObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int siteId;
	private String lockType;
	private int lockStatus=0;		//锁住状态 0已锁 1未锁
	private String lockUser;
	private String lockDate;
	private String clearUser;
	private String clearDate;
	private int isForceClear=0;	//是否为强制解锁 0不是 1是
	
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
	public String getLockType() {
		return lockType;
	}
	public void setLockType(String lockType) {
		this.lockType = lockType;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	public String getLockUser() {
		return lockUser;
	}
	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}
	public String getLockDate() {
		return lockDate;
	}
	public void setLockDate(String lockDate) {
		this.lockDate = lockDate;
	}
	public String getClearUser() {
		return clearUser;
	}
	public void setClearUser(String clearUser) {
		this.clearUser = clearUser;
	}
	public String getClearDate() {
		return clearDate;
	}
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}
	public int getIsForceClear() {
		return isForceClear;
	}
	public void setIsForceClear(int isForceClear) {
		this.isForceClear = isForceClear;
	}
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("siteId", getSiteId());
			this.putClientProperty("lockType", getLockType());
			this.putClientProperty("lockStatus", getLockStatus()==0 ? "已锁":"未锁");
			this.putClientProperty("lockUser", getLockUser());
			this.putClientProperty("lockDate", getLockDate());
			this.putClientProperty("clearUser", getClearUser());
			this.putClientProperty("clearDate", getClearDate());
			this.putClientProperty("isForceClear", getIsForceClear());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
