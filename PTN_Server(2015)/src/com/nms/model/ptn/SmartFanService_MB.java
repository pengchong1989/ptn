package com.nms.model.ptn;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.SmartFan;
import com.nms.db.dao.ptn.SmartFanMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class SmartFanService_MB extends ObjectService_Mybatis {
	    private SmartFanMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public SmartFanMapper getMapper() {
			return mapper;
		}

		public void setMapper(SmartFanMapper mapper) {
			this.mapper = mapper;
		}

		public void save(SmartFan fan) throws Exception{
			if(fan == null){
				throw new Exception("smartFan is null");
			}
			this.mapper.save(fan);
			this.sqlSession.commit();
		}
		
		public void update(SmartFan fan) throws Exception{
			if(fan == null){
				throw new Exception("smartFan is null");
			}
			this.mapper.update(fan);
			this.sqlSession.commit();
		}
		
		public List<SmartFan> selectAll(int siteId) throws Exception{
			if (siteId == 0) {
				throw new Exception("siteid is 0");
			}
			return this.mapper.selectAll(siteId);
		}
}
