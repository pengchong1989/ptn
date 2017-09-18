package com.nms.db.bean.client;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 客户 Bean 类
 * @author dzy
 *
 */
public class Client  extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7236232683210912348L;
	private int id; 
	private String name; //客户名称 
	private String adress; //地址
	private String linkMan;	//联系人
	private String phoneNumber; //联系电话
	private String grade; //等级
	private String area; //区域
	private String remark; //备注

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("name", getName());
			getClientProperties().put("adress", getAdress());
			getClientProperties().put("linkMan", getLinkMan());
			getClientProperties().put("phoneNumber", getPhoneNumber());
			getClientProperties().put("grade", UiUtil.getCodeById(Integer.parseInt(getGrade())).getCodeName());
			getClientProperties().put("area", getArea());
			getClientProperties().put("remark", getRemark());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
