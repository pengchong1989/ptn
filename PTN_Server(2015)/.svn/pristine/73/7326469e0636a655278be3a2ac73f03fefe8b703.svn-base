package com.nms.db.dao.system;

import java.util.List;

import com.nms.db.bean.system.NetWork;
import com.nms.db.bean.system.user.UserInst;


public interface NetWorkMapper {
    int deleteByPrimaryKey(Integer networkid);

   

    int insertSelective(NetWork record);

    NetWork selectByPrimaryKey(Integer networkid);

    int updateByPrimaryKeySelective(NetWork record);

    int updateByPrimaryKey(NetWork record);
    
    public List<NetWork> select();

	List<NetWork> queryByUserIdField(UserInst userinst);
	
	/**
	 * 更新
	 * @param netWork
	 * @return
	 */
	public int update(NetWork netWork);
	
	public int insert(NetWork netWork);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<NetWork> selectByNetWorkId(NetWork net);
	
	/**
	 * 更新
	 * @param netWork
	 * @return
	 */
	public int delete(NetWork netWork);
}