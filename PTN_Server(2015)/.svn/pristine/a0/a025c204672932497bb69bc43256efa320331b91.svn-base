package com.nms.db.dao.system.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.user.UserInst;



public interface UserInstMapper {
      
    public List<UserInst> selectUserList(UserInst userInst);

	public List<UserInst> querByroleId(UserInst userinst);
	
	/**
	 * 通过主键删除userInst 
	 * @param User_Id
	 * @return 删除的记录数
	 */
	public int deleteByUserId(@Param("userId")Integer User_Id);
	
	public List<UserInst> queryByuserid(UserInst userInst);
	
	/**
	 * 新增 userinst(用户表)
	 * @param userinst
	 * @return 执行成功插入的记录数
	 */
	public int insert(UserInst userinst);
	/**
	 * 修改userInst(用户表)
	 * @param userInst
	 * @return 修改的记录数
	 */
	public int update(UserInst userInst);
}