package com.nms.db.bean.ptn.clock;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class LineClockInterface extends ViewDataObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1157462850428051390L;
	private int id;//ID
	private int siteId;//关联的ID组件
	private int port;//端口
	private String rate;//速率
	private int ssmSendingEnabled;//SSM发送使能
	private String dnuGroup;//DNU组
	/**
	 * portName   页面显示   
	 * 数据库中无此数据
	 */
	private String portName;
	
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public int getSsmSendingEnabled() {
		return ssmSendingEnabled;
	}
	public void setSsmSendingEnabled(int ssmSendingEnabled) {
		this.ssmSendingEnabled = ssmSendingEnabled;
	}
	public String getDnuGroup() {
		return dnuGroup;
	}
	public void setDnuGroup(String dnuGroup) {
		this.dnuGroup = dnuGroup;
	}
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id",this.getId());
			this.putClientProperty("siteId",this.getSiteId());
//			this.putClientProperty("port", UiUtil.getCodeById(this.getPort()).getCodeName());	
	
			this.putClientProperty("port", this.getPortName());		
			this.putClientProperty("rate", UiUtil.getCodeByValue("portSetRateGE",this.getRate()).getCodeName());		
			this.putClientProperty("ssmSendingEnabled",this.getSsmSendingEnabled()==0?ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO) : ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED));
			this.putClientProperty("dnuGroup", getDnuGroup());		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}		
	}

}
