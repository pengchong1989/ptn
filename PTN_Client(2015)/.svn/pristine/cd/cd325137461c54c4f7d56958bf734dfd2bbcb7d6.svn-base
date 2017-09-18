package com.nms.model.ptn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.SsMacStudy;
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
		 * 根据网元Id,portid 查询vlan
		 * @param siteId,portId
		 * @return FrequencyInfo List
		 * @throws Exception
		 */
		public List<Integer> queryVlan(int siteId,int portId) throws Exception {
			List<Integer> Vlans = null;
			try {
				Vlans = new ArrayList<Integer>();
				Vlans = this.mapper.queryVlan(siteId,portId);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return  Vlans;
		}
	
		/**
		 * 根据网元Id 查询 mac条目数，以此来分配macID
		 * @param siteId
		 * @return FrequencyInfo List
		 * @throws Exception
		 */
		public List<Integer> queryMacId(int siteId) throws Exception {
			List<Integer> macIds = null;
			try {
				macIds =  new ArrayList<Integer>();
				macIds = this.mapper.queryMacId(siteId);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return  macIds;
		}		
}
