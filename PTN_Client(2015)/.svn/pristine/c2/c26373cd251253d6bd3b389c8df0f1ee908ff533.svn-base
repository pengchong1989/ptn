package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.AclInfo;



public interface AclInstMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(AclInfo acInfo);

    int insertSelective(AclInfo record);

    AclInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AclInfo record);

    int updateByPrimaryKey(AclInfo record);

	public List<AclInfo> queryBySiteId(int siteId);

	public void update(AclInfo aclInfo);
	
	public AclInfo queryById(@Param("id")int id);
}