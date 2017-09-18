package com.nms.db.bean.ptn.clock;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class FrequencySource extends ViewDataObj  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6182259059979191189L;
	private int clockName;//时钟源名称
	private int sourceStatus;//输入源状态
	private int sourceQL;//输入源ql值
	private int outSourceQl;//输出源值
	private String clockNameString;
	public String getClockNameString() {
		return clockNameString;
	}
	public void setClockNameString(String clockNameString) {
		this.clockNameString = clockNameString;
	}
	public int getClockName() {
		return clockName;
	}
	public void setClockName(int clockName) {
		this.clockName = clockName;
	}
	public int getSourceStatus() {
		return sourceStatus;
	}
	public void setSourceStatus(int sourceStatus) {
		this.sourceStatus = sourceStatus;
	}
	public int getSourceQL() {
		return sourceQL;
	}
	public void setSourceQL(int sourceQL) {
		this.sourceQL = sourceQL;
	}
	public int getOutSourceQl() {
		return outSourceQl;
	}
	public void setOutSourceQl(int outSourceQl) {
		this.outSourceQl = outSourceQl;
	}
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("name", getClockNameString());
			this.putClientProperty("sourceStatus", (UiUtil.getCodeByValue("SOURCESTATUS", this.getSourceStatus()+"")).getCodeName());
			this.putClientProperty("inQL", (UiUtil.getCodeByValue("LOCKSOURCES1", this.getSourceQL()+"")).getCodeName());
			this.putClientProperty("outQL", (UiUtil.getCodeByValue("LOCKSOURCES1", this.getOutSourceQl()+"")).getCodeName());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
}
