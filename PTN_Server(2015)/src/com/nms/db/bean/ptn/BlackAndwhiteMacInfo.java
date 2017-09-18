package com.nms.db.bean.ptn;

import java.io.Serializable;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 
 * function:添加MAC地址的黑白名单 Bean 对象
 * @author zhangkun
 */
public class BlackAndwhiteMacInfo extends ViewDataObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1128615361825912436L;
	private int id; //数据库主键ID
	private String vplsServiceName;//elan业务名称
	private int elanBussinessId ; //elan业务ID
	private int siteId;//设备id
	private int nameList;//名单模式：1/2=黑名单/白名单模式 
	private String mac;//（最多50个）（默认00-00-00-00-00-00）
	
	
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


	public int getNameList() {
		return nameList;
	}


	public void setNameList(int nameList) {
		this.nameList = nameList;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getVplsServiceName() {
		return vplsServiceName;
	}


	public void setVplsServiceName(String vplsServiceName) {
		this.vplsServiceName = vplsServiceName;
	}

	public int getElanBussinessId() {
		return elanBussinessId;
	}


	public void setElanBussinessId(int elanBussinessId) {
		this.elanBussinessId = elanBussinessId;
	}


	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("serviceName", getVplsServiceName());
			this.putClientProperty("nameList",UiUtil.getCodeById(this.getNameList()).getCodeName());
			this.putClientProperty("mac", getMac());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	
}
