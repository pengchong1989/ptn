package com.nms.db.bean.system;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class LoginManager extends ViewDataObj {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String startIp;//起始IP  
	private String endIp;//终止Ip
	private String IpDescrible;//Ip描述
	private String username;//用户
	private int userid;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getIpDescrible() {
		return IpDescrible;
	}
	public void setIpDescrible(String ipDescrible) {
		IpDescrible = ipDescrible;
	}

	public LoginManager() {
		super();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("username", this.getUsername());
			getClientProperties().put("startIp", this.getStartIp());
			getClientProperties().put("endIp", this.getEndIp());
			getClientProperties().put("IpDescrible", this.getIpDescrible());
			getClientProperties().put("userid", this.getUserid());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	

}
