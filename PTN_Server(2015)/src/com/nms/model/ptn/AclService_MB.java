package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.AclInfo;
import com.nms.db.dao.ptn.AclInstMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class AclService_MB extends ObjectService_Mybatis{
	
    private AclInstMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public AclInstMapper getMapper() {
		return mapper;
	}

	public void setMapper(AclInstMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 批量创建
	 * @param aclInfos
	 * @return
	 * @throws Exception
	 */
	public int batchSave(List<AclInfo> aclInfos) throws Exception{
		int result = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(AclInfo aclInfo : aclInfos){				 
				this.mapper.insert(aclInfo);
				result =aclInfo.getId();
				aclInfo.setId(result);
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
		return result;
	}
	
	

	/**
	 * 查询该网元下所有信息
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public List<AclInfo> select(int siteId) throws Exception{
		List<AclInfo> AclInfoList = null;
		if(siteId == 0){
			throw new Exception("siteId is null");
		}
		try {
			AclInfoList = this.mapper.queryBySiteId(siteId);
			if(AclInfoList==null){
				AclInfoList = new ArrayList<AclInfo>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return AclInfoList;
	}
	
	/**
	 * 更新
	 * @param wholeConfigInfo
	 * @throws Exception
	 */
	public void update(AclInfo aclInfo) throws Exception{
		if(aclInfo == null){
			throw new Exception("aclInfo is null");
		}
		try {
			this.mapper.update(aclInfo);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 新增
	 * @param AclInfo
	 * @throws Exception
	 */
	public int save(AclInfo aclInfo) throws Exception{
		
		if(aclInfo == null){
			throw new Exception("AclInfo is null");
		}
		int result = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(aclInfo);
			result = aclInfo.getId();
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	/**
	 *删除
	 * @param wholeConfigInfo
	 * @throws Exception
	 */
	public void delete(AclInfo aclInfo) throws Exception{
		if(aclInfo == null){
			throw new Exception("aclInfo is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.delete(aclInfo);
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
	 *通过siteId删除
	 * @param wholeConfigInfo
	 * @throws Exception
	 */
	public void deleteBySiteId(int siteId) throws Exception{
		if(siteId == 0){
			throw new Exception("siteId is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.deleteBySiteId(siteId);
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
}

