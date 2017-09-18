package com.nms.model.system;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.SiteLock;
import com.nms.db.dao.system.SiteLockMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class SiteLockService_MB extends ObjectService_Mybatis {

    private SiteLockMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public SiteLockMapper getMapper() {
		return mapper;
	}

	public void setMapper(SiteLockMapper mapper) {
		this.mapper = mapper;
	}

	
	
	

	
	

	/**
	 * 查询单个网元下被锁的信息
	 * @param siteInst
	 * @return 一条siteLock
	 * @throws Exception
	 */
	public List<SiteLock> selectSiteLock(SiteInst siteInst)throws Exception {
		List<SiteLock> siteLocks = null;
		try {
			siteLocks = new ArrayList<SiteLock>();
			siteLocks = this.mapper.queryLockBySiteId(siteInst.getSite_Inst_Id());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return siteLocks;
	}
	
}
