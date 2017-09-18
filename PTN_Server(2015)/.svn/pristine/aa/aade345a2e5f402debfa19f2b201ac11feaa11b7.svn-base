package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.RouteBusiness;

public interface RouteBusinessMapper {
   
	/**
	 * 通过网元名称查询路由业务信息
	 * @param siteName  网元名称
	 * @param connection 数据库连接
	 * @return 查询得到的结果集
	 */
	public List<RouteBusiness> queryBySiteName(@Param("siteName")String siteName);
	
	/**
	 * 通过网元名称查询经过路由业务信息
	 * @param siteName  网元名称
	 * @return 查询得到的结果集
	 */
	public List<RouteBusiness> queryBySiteName_XC(@Param("siteId")int siteId);
	
}