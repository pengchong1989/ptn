package com.nms.db.dao.system.roleManage;

import java.util.List;

import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.db.bean.system.roleManage.RoleManage;



public interface RoleManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleManage record);

    int insertSelective(RoleManage record);

    RoleManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleManage record);

    int updateByPrimaryKey(RoleManage record);
    
    /**
	 * 根據  角色 ID 查詢 關聯表
	 * 			在 菜單權限結果集中
	 * 			取得    此角色的 權限信息
	 * @param roleInfo
	 * @return
	 */
	public List<RoleManage> byRoleInfoSelect(RoleInfo roleInfo);
	
	/**
	 * 查询权限表
	 * @param roleroleManage
	 * @return
	 */
	public List<RoleManage> select(RoleManage roleManage);
}