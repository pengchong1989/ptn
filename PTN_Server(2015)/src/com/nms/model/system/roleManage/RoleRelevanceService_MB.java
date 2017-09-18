package com.nms.model.system.roleManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.db.bean.system.roleManage.RoleRelevance;
import com.nms.db.dao.system.roleManage.RoleRelevanceMapper;
import com.nms.model.util.ObjectService_Mybatis;
/**
 * 查询  角色、权限关联表
 * @author Administrator
 *
 */
public class RoleRelevanceService_MB extends ObjectService_Mybatis{
    private RoleRelevanceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public RoleRelevanceMapper getMapper() {
		return mapper;
	}

	public void setMapper(RoleRelevanceMapper mapper) {
		this.mapper = mapper;
	}
	List<RoleRelevance> infoList=null;

	/**
	 * 查看
	 */
	public List<RoleRelevance> select(RoleRelevance roleRelevance) throws Exception {
		if (null == roleRelevance) {
			throw new Exception("roleRelevance is null");
		}
		infoList = new ArrayList<RoleRelevance>();
		infoList=this.mapper.select(roleRelevance);
		 
		return infoList;
	}

	/**
	 * 根据   infoId  批量  删除 角色权限
	 */
	public int delete(RoleInfo roleInfo) throws Exception {
		if (null == roleInfo) {
			throw new Exception("roleInfo is null");
		}
		
		int n=this.mapper.delete(roleInfo);
		 this.sqlSession.commit();
		return n;
	}
}
