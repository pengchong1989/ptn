package com.nms.model.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.SiteLock;
import com.nms.db.dao.system.SiteLockMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.DateUtil;
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
	
	/**
	 * 批量解锁
	 * @param siteIdList
	 * @throws Exception 
	 */
	public void updateClear(List<Integer> siteIdList) throws Exception{
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(int siteId : siteIdList){
				this.mapper.updateSiteLock("admin",siteId, DateUtil.getDate(DateUtil.FULLTIME));
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
	
	
	/**
	 * 批量插入
	 * @param siteIdList
	 * @throws Exception 
	 */
	public void save(List<Integer> siteIdList,String lockType) throws Exception{
		SiteLock siteLock=null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(int siteId : siteIdList){
				siteLock=new SiteLock();
				siteLock.setSiteId(siteId);
				siteLock.setLockDate(DateUtil.getDate(DateUtil.FULLTIME));
				siteLock.setLockType(lockType);
				//TODO user
				siteLock.setLockUser("admin");
				this.mapper.insert(siteLock);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
			siteLock=null;
		}
	}
	
	/**
	 * 暂时去掉是否被锁，以后完善
	 * @param siteId
	 * @return false 没锁  true有锁
	 * @throws Exception 
	 */
	public boolean isLock(int siteId) throws Exception{
		return false;
	}
}
