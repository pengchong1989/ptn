package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.CCN;


public interface CCNMapper {

    public int insert(CCN record);
 
    public int update(CCN record);

	public List<CCN> queryByNeID(@Param("siteId")String neID);
	
	public int deleteBySiteId(@Param("siteId")String siteId);
	
	public List<CCN> selectByNeIdAndName(@Param("name")String name,@Param("siteId")String neID);
	
	public int updateStatus(CCN record);
	
	public int deleteById(@Param("id")int id);
}