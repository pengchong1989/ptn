package com.nms.db.dao.system.roleManage;

import java.util.List;

import com.nms.db.bean.system.roleManage.RoleInfo;



public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(RoleInfo roleInfo);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
    /**
   	 * 根据条件  
   	 * 查找   数据库
   	 * @param roleInfo
   	 * @return
   	 */
   	public List<RoleInfo> select(RoleInfo roleInfo);
   	
   	/**
	 * 删除
	 * @param roleInfo
	 * @return
	 */
	public int delete(RoleInfo roleInfo);
	
	/**
	 * 更新 时  ，不可重名
	 * 根据条件  
	 * 查找   数据库
	 * @param roleInfo
	 * @return
	 */
	public List<RoleInfo> selectNoName(RoleInfo roleInfo);
	
	/**
	 * 更新
	 * @param roleInfo
	 * @return 
	 */
	public int update(RoleInfo roleInfo);
}