package com.nms.db.dao.ptn.ecn;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.OSPFInfo;


public interface OSPFInfoMapper {

    public int insert(@Param("osp")OSPFInfo record);
    
    public int update(OSPFInfo record);

	public OSPFInfo queryByNeID(String id);
	
	public int deleteById(@Param("id")Integer id);
	
	public int deleteBySiteId(@Param("siteId")String id);
}