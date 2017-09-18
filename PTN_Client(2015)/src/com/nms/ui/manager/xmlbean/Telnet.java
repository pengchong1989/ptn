package com.nms.ui.manager.xmlbean;

/**
 * 登陆配置XMLbean
 * 
 * 项目名称：WuHanPTN2012 类名称：LoginConfig 类描述： 创建人：kk 创建时间：2013-6-25 下午04:22:18 修改人：kk 修改时间：2013-6-25 下午04:22:18 修改备注：
 * 
 * @version
 * 
 */
public class Telnet {

	private String serviceIp;
	private String username;
	private String password;
	private String neIp;
	private String nePassword;
	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the neIp
	 */
	public String getNeIp() {
		return neIp;
	}

	/**
	 * @param neIp the neIp to set
	 */
	public void setNeIp(String neIp) {
		this.neIp = neIp;
	}

	/**
	 * @return the nePassword
	 */
	public String getNePassword() {
		return nePassword;
	}

	/**
	 * @param nePassword the nePassword to set
	 */
	public void setNePassword(String nePassword) {
		this.nePassword = nePassword;
	}

}
