package com.nms.db.dao.system.loginlog;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.loginlog.UserLock;



public interface UserLockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLock record);

    int insertSelective(UserLock record);

    UserLock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLock record);

    int updateByPrimaryKey(UserLock record);
    
    /**
	 * 查询表user_lock 账户的锁定信息
	 * @param userlock
	 * @param max  max=1,查找账户上一次锁定信息,
	 * @return
	 */
	public List<UserLock> selectLockType(@Param("userLock")UserLock userLock,@Param("max")Integer max);
	
	/**
	 * 更新user_lock表中信息 解锁修改表中数据
	 * @param userlock
	 */
	public int updateUserLock(@Param("userLock")UserLock userLock);
	
	/**
	 * 新增 表user_lock
	 * @param userlock
	 * @return
	 */
	public int insertUserLock(UserLock userLock);
	
	/**
	 * 查看（某个用户） userlock 的锁定信息
	 * 
	 * @param userlock
	 * @return
	 * @throws Exception
	 */
	public List<UserLock> selectUserLock(UserLock userlock);
	
	
	
}