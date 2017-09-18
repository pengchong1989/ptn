package com.nms.db.bean.system.roleManage;

import java.util.ArrayList;
import java.util.List;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;

/**
 * 角色信息
 * @author sy
 *
 */
public class RoleInfo extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleName;
	private String remark;
	private String roleEnName;
	private int isAll;
	
	//添加 关联信息
	private List<RoleManage> roleManageList = new ArrayList<RoleManage>();
	
	public List<RoleManage> getRoleManageList() {
		return roleManageList;
	}

	public void setRoleManageList(List<RoleManage> roleManageList) {
		this.roleManageList = roleManageList;
	}

	public RoleInfo() {
		super();
	}

	public RoleInfo(String roleName) {
		super();
		this.roleName = roleName;
	}

	public int getIsAll() {
		return isAll;
	}

	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleEnName() {
		return roleEnName;
	}

	public void setRoleEnName(String roleEnName) {
		this.roleEnName = roleEnName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			if(ResourceUtil.language.equals("zh_CN")){
				getClientProperties().put("roleName", this.getRoleName());
			}else{
				getClientProperties().put("roleName", this.getRoleEnName());
			}
			getClientProperties().put("remark", this.getRemark());

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}

	}

}
