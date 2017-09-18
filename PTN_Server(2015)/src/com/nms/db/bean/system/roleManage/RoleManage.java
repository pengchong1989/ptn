package com.nms.db.bean.system.roleManage;

import com.nms.ui.frame.ViewDataObj;

/**
 * 角色管理表
 * @author Administrator
 *
 */
public class RoleManage extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String name_en;
	private String label;
	private int parentId;
	private int isVisible;
	/**
	 * 角色名 
	 *    做   界面显示（因安全权限 显示的不同      以 角色名  确定是否显示     ）
	 */
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**创建   一个 新的实例
	 *    id   
	 *    	主键ID
	 * @param id
	 */
	public RoleManage(int id) {
		super();
		this.id = id;
	}
	public RoleManage(){		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void putObjectProperty() {
		
		
	}
	
}
