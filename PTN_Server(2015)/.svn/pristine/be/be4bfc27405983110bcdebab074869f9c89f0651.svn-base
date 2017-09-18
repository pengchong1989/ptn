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
				ptpPortMapper=null;
			}
			return timesyncs;
		}
	
		public void update(TimeSyncInfo timesync, List<TimeSyncInfo> timesyns) throws Exception{
			if(this.mapper == null){
				throw new Exception("mapper is null");
			}
			List<PtpPortinfo> ptpPortList = null;
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				ptpPortMapper =this.sqlSession.getMapper(PtpPortMapper.class);
				ptpPortList = timesync.getPtpPortlist();
				this.mapper.update(timesync);
				this.ptpPortMapper.deletePtpBySiteId(timesync.getSiteId());
				if(ptpPortList != null && ptpPortList.size()>0){
					
				    	 for(PtpPortinfo ptpPortInfo:ptpPortList)
						   {
							   PtpPortinfo info = new PtpPortinfo();
							   info.setIndexId(ptpPortInfo.getIndexId());
							   info.setPortNum(ptpPortInfo.getPortNum());
							   info.setPortId(ptpPortInfo.getPortId());
							   info.setWorkModel(ptpPortInfo.getWorkModel());
							   info.setLine(ptpPortInfo.getLine());
							   info.setLineCpn(ptpPortInfo.getLineCpn());
							   info.setDelayMeca(ptpPortInfo.getDelayMeca());
							   info.setMessageMode(ptpPortInfo.getMessageMode());
							   info.setSiteId(timesync.getSiteId());
							   ptpPortMapper.insertPtpPort(info);					
						  }
				    	
				   
				}
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
				ptpPortMapper=null;
			}
		}
		public void save(TimeSyncInfo timesync) throws Exception{
			if(this.mapper == null){
				throw new Exception("mapper is null");
			}
			List<PtpPortinfo> ptpPortList = null;
			
			try {
				
				this.sqlSession.getConnection().setAutoCommit(false);
				ptpPortMapper =this.sqlSession.getMapper(PtpPortMapper.class);
				this.mapper.insert(timesync);
				ptpPortList = timesync.getPtpPortlist();
				if(ptpPortList != null && ptpPortList.size()>0){
					for(PtpPortinfo ptpPortInfo:ptpPortList)
					{
						PtpPortinfo info = new PtpPortinfo();
						info.setIndexId(ptpPortInfo.getIndexId());
						info.setPortNum(ptpPortInfo.getPortNum());
						info.setPortId(ptpPortInfo.getPortId());
						info.setWorkModel(ptpPortInfo.getWorkModel());
						info.setLine(ptpPortInfo.getLine());
						info.setLineCpn(ptpPortInfo.getLineCpn());
						info.setDelayMeca(ptpPortInfo.getDelayMeca());
						info.setMessageMode(ptpPortInfo.getMessageMode());
						info.setSiteId(timesync.getSiteId());
						this.ptpPortMapper.insertPtpPort(info);
						
					}
					
				}			
				
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				this.sqlSession.getConnection().setAutoCommit(true);
				ptpPortList = null;
				
			}
		}
		
		
		public void deleteBySiteId(int siteId) throws Exception{		
			if(siteId == 0){
				throw new Exception("siteId is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				ptpPortMapper =this.sqlSession.getMapper(PtpPortMapper.class);
				this.mapper.deleteBySiteId(siteId);
				this.ptpPortMapper.deletePtpBySiteId(siteId);
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
	
