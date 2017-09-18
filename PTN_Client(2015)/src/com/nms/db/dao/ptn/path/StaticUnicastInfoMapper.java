package com.nms.db.dao.ptn.path;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.StaticUnicastInfo;

public interface StaticUnicastInfoMapper {

	public List<StaticUnicastInfo> selectByStaticUniInfo(StaticUnicastInfo info);
	
	public List<String> selectNameByXcId(@Param("xcId")int xcid,@Param("siteId")int siteid);
	
	public List<Integer> querySuId(@Param("siteId")int siteId);
	
	public List<StaticUnicastInfo> queryByCondition(@Param("siteId")int siteId);
}