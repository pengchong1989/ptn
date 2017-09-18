package com.nms.ui.ptn.systemconfig.dialog.bsUpdate;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class SoftwareType extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4723685837602035661L;
	private int siteid;//网元Id
	private String siteName;
	private int type;//type值
	private String result;//升级结果
	private int hour;
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		try {
			this.putClientProperty("siteid", this.getSiteid());
			this.putClientProperty("siteName", this.getSiteName());
			this.putClientProperty("result", this.getResult());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
}
