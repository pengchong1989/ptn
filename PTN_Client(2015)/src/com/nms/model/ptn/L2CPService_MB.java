package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

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
		 * 通过网元查询所有的该网元的数据
		 * @param siteId
		 * @return
		 * @throws Exception
		 */
		public List<L2cpInfo> selectAll(int siteId) throws Exception{
			List<L2cpInfo> L2cpInfoList= new ArrayList<L2cpInfo>();
			if(siteId == 0){
				throw new Exception("siteId is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				L2cpInfoList = this.mapper.queryBySiteId(siteId);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return L2cpInfoList;
		}

}
