package com.nms.model.ptn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.SsMacStudy;
import com.nms.db.dao.equipment.port.PortInstMapper;
import com.nms.db.dao.ptn.MacStudyAddressMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class SecondMacStudyService_MB extends ObjectService_Mybatis {
	    private MacStudyAddressMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public MacStudyAddressMapper getMapper() {
			return mapper;
		}

		public void setMapper(MacStudyAddressMapper mapper) {
			this.mapper = mapper;
		}
	
		/**
		 * 根据网元id查询对应的二层mac地址
		 * @param siteId
		 * @return
		 * @throws SQLException
		 */
		public List<SsMacStudy> selectBySecondMacStudyInfo(int siteId) throws SQLException {
			List<SsMacStudy> ssMacStudyInfoList = new ArrayList<SsMacStudy>();
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				ssMacStudyInfoList = this.mapper.selectBySecondMacStudyInfo(siteId);
				if(ssMacStudyInfoList!=null && ssMacStudyInfoList.size()!=0){
					for(int i = 0; i< ssMacStudyInfoList.size(); i++){
				    	String []macs= ssMacStudyInfoList.get(i).getMacAddress().split("\\|");
					    for(int j = 0; j< macs.length; j++){
					    	ssMacStudyInfoList.get(i).getMacAddressList().add(macs[j]);
					    }
				    }
				}else{
					ssMacStudyInfoList=new ArrayList<SsMacStudy>();
				}
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return ssMacStudyInfoList;
		}
		
				
		/**
		 * 新增
		 * @param AclInfo
		 * @throws Exception
		 */
		public int save(SsMacStudy ssMacStudy) throws Exception{
			
			if(ssMacStudy == null){
				throw new Exception("ssMacStudy is null");
			}
			int result = 0;
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.insert(ssMacStudy);
				result = ssMacStudy.getId();
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
		

		public void update(SsMacStudy ssMacStudy) throws Exception {
			if(ssMacStudy == null){	
				throw new Exception("ssMacStudy is null");		  
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.update(ssMacStudy);
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

		public void delete(SsMacStudy ssMacStudy) throws Exception{
			if(ssMacStudy == null){
				throw new Exception("ssMacStudy is null");
			}
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.delete(ssMacStudy);
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
		 * 查询
		 * 
		 * @param oamEthernetInfo
		 * @return
		 * @throws Exception
		 */
		public List<SsMacStudy> selectBySsMacStudyAddressInfo(SsMacStudy ssMacStudy) throws Exception {
			if (ssMacStudy == null) {
				throw new Exception("ssMacStudy is null");
			}
			List<SsMacStudy> ccnList = null;
			try {
				ccnList = this.mapper.queryMacInfoByCondition(ssMacStudy);
				if(ccnList!=null && ccnList.size()!=0){
					for(int i = 0; i< ccnList.size(); i++){
				    	String []macs= ccnList.get(i).getMacAddress().split("\\|");
					    for(int j = 0; j< macs.length; j++){
						   ccnList.get(i).getMacAddressList().add(macs[j]);
					    }
				    }
				}else{
					ccnList=new ArrayList<SsMacStudy>();
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return ccnList;
		}
		
		public List<SsMacStudy> select(int siteId) throws Exception {
			List<SsMacStudy> suIds = null;
			PortInstMapper portMapper= null;
			try {	
				portMapper =this.sqlSession.getMapper(PortInstMapper.class);
				suIds = this.mapper.selectBySecondMacStudyInfo(siteId);
				if(suIds!=null && suIds.size()!=0){
					for(int i = 0; i< suIds.size(); i++){
				    	String []macs= suIds.get(i).getMacAddress().split("\\|");
					    for(int j = 0; j< macs.length; j++){
					    	suIds.get(i).getMacAddressList().add(macs[j]);
					    }
					   int number= portMapper.selectBySiteIdAndPortId(siteId,suIds.get(i).getPortId());
					   suIds.get(i).setPortId(number);
				    }
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				portMapper=null;
			}
			return  suIds;
		}
		
		/**
		 * 根据网元Id删除数据
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
		
		/**
		 * 根据网元Id,portid 查询vlan
		 * @param siteId,portId
		 * @return FrequencyInfo List
		 * @throws Exception
		 */
		public int selectPortIdBySiteId(int siteId,int portId) throws Exception {
			int port =0;			
			PortInstMapper portMapper= null;
			try {
				portMapper =this.sqlSession.getMapper(PortInstMapper.class);
				port = portMapper.selectBySiteIdAndNumber(siteId,portId);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return  port;
		}
}
