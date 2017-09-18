package com.nms.db.bean.system.roleManage;

import com.nms.ui.frame.ViewDataObj;

/**
 * 角色，权限关联信息
 * @author sy
 *
 */
public class RoleRelevance  extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int infoId;//关联角色ID
	private int manageId;//关联 权限ID
	//做界面显示，数据库中无此数据
	private String roleName;//角色名
	//做界面显示，数据库中无此数据
	private int parentId;
	//做权限验证使用，数据库中无此数据
	private int label;//标签验证： 权限集合中有  此  label 
	public RoleRelevance(){
		
	}
	
	public RoleRelevance(int infoId) {
		super();
		this.infoId = infoId;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public int getManageId() {
		return manageId;
	}
	public void setManageId(int manageId) {
		this.manageId = manageId;
	}

	@Override
	public void putObjectProperty() {
		
		
	}
	
}
