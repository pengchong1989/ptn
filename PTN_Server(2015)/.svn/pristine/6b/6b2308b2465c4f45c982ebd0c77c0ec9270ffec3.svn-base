package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;


public interface OSPFAREAInfoMapper {
    public int delete(OSPFAREAInfo record);

    public int insert(@Param("osp")OSPFAREAInfo osp);

    public int update(OSPFAREAInfo record);

	public List<OSPFAREAInfo> queryByNeID(String neId);
	
	public int deleteBySiteId(@Param("siteId")String siteId);
	
	public int queryName(@Param("neId")String NeID,@Param("area_range")String area_range);
	
	public int deleteById(@Param("id")Integer id);
	
	public List<OSPFAREAInfo> queryByNeIDAndAreaRange(@Param("neId")String NeID,@Param("area_range")String area_range);
	
	public int updateStatus(OSPFAREAInfo record);
}