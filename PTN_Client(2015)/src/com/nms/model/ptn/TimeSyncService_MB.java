package com.nms.model.ptn;



import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.dao.ptn.PtpPortMapper;
import com.nms.db.dao.ptn.TimeSyncMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class TimeSyncService_MB extends ObjectService_Mybatis{
	
	   private TimeSyncMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public TimeSyncMapper getMapper() {
			return mapper;
		}

		public void setMapper(TimeSyncMapper mapper) {
			this.mapper = mapper;
		}
		private PtpPortMapper ptpPortMapper =null; 

		public List<TimeSyncInfo> select(int siteId) throws Exception{
			List<TimeSyncInfo> timesyncs = null;
			List<PtpPortinfo> ptpPort = null;
			if(siteId == 0){
				throw new Exception("siteId is null");
			}
			try {
				ptpPortMapper =this.sqlSession.getMapper(PtpPortMapper.class);
				timesyncs = new ArrayList<TimeSyncInfo>();
				timesyncs = this.mapper.queryBySiteId(siteId);
				ptpPort = new ArrayList<PtpPortinfo>();
				ptpPort = ptpPortMapper.queryBySiteId(siteId);
				if(ptpPort!=null && ptpPort.size()>0){
					if(timesyncs!=null && timesyncs.size()>0){
						for(int i=0;i<timesyncs.size();i++){
							timesyncs.get(i).setPtpPortlist(ptpPort);
						}
					}
				}
				
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				
			}
			return timesyncs;
		}
	
}
	
