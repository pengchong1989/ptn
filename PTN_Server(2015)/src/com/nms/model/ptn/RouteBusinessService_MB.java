package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.RouteBusiness;
import com.nms.db.dao.ptn.RouteBusinessMapper;
import com.nms.model.util.ObjectService_Mybatis;
/**
 * 查询路由业务业务层
 * @author dzy
 *
 */
public class RouteBusinessService_MB extends ObjectService_Mybatis{
	 private RouteBusinessMapper mapper = null;
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public RouteBusinessMapper getMapper() {
		return mapper;
	}

	public void setMapper(RouteBusinessMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 通过网元名称查询路由业务信息
	 * @param siteName 网元名称
	 * @param connection 数据库连接
	 * @return
	 * @throws Exception
	 */
	public List<RouteBusiness> queryBySiteName(String siteName) throws Exception {
		List<RouteBusiness> routeBusinessList= new ArrayList<RouteBusiness>();
		routeBusinessList=this.mapper.queryBySiteName(siteName);
		return routeBusinessList;
	}
	
	/**
	 * 通过网元名称查询经过路由业务信息
	 * @param siteId 网元id
	 * @param connection 数据库连接
	 * @return
	 * @throws Exception
	 */
	public List<RouteBusiness> queryBySiteName_XC(int siteId) throws Exception {
		List<RouteBusiness> routeBusinessList= new ArrayList<RouteBusiness>();
		routeBusinessList=this.mapper.queryBySiteName_XC(siteId);
		return routeBusinessList;
	}
}
