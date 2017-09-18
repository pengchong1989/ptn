package com.nms.service.impl.dispatch.rmi.bean;

import java.io.Serializable;

/**
 * 服务器参数bean
 * @author kk
 *
 */
public class ServiceBean implements Serializable{
	
	private int maxConnection;	//最大连接数
	private int maxSiteNumner;	//最大网元数
	private String dueDate;		//到期时间
	private int connectionResult; 	//连接结果 1=成功   2=超过最大连接数  3=无许可
	
	
	public int getMaxConnection() {
		return maxConnection;
	}
	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}
	public int getMaxSiteNumner() {
		return maxSiteNumner;
	}
	public void setMaxSiteNumner(int maxSiteNumner) {
		this.maxSiteNumner = maxSiteNumner;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public int getConnectionResult() {
		return connectionResult;
	}
	public void setConnectionResult(int connectionResult) {
		this.connectionResult = connectionResult;
	}
	
}
