package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.AclInfo;



public interface AclInstMapper {
    public int delete(AclInfo acInfo);
    
    public int deleteBySiteId(@Param("siteId")Integer siteId);

    public int insert(AclInfo acInfo);
  
	public List<AclInfo> queryBySiteId(@Param("siteId")int siteId);

	public void update(AclInfo aclInfo);
}