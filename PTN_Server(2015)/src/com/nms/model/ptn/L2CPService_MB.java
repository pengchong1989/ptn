package com.nms.model.ptn;


import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.L2cpInfo;
import com.nms.db.dao.ptn.L2cpInstMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class L2CPService_MB extends ObjectService_Mybatis{

	    private L2cpInstMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public L2cpInstMapper getMapper() {
			return mapper;
		}

		public void setMapper(L2cpInstMapper mapper) {
			this.mapper = mapper;
		}
	
		
		
		/**
		 * 保存
		 * @param l2cpInfo
		 * @throws Exception
		 */
		public void save(L2cpInfo l2cpInfo) throws Exception{
			if(l2cpInfo == null){
				throw new Exception("l2cpInfo is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.insert(l2cpInfo);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}
		
		//跟新数据
		public void update(L2cpInfo l2cpInfo)throws Exception{
			if(l2cpInfo == null){
				throw new Exception("l2cpInfo is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.update(l2cpInfo);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}

		/*
		 * 根据网元ID删除数据
		 * 
		 */
		public void delete(int siteId)throws Exception{
			if(siteId == 0){
				throw new Exception("siteId is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.delete(siteId);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}
}
