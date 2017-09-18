package com.nms.model.ptn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.MacLearningInfo;
import com.nms.db.dao.ptn.MacLearningLimitMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class MacLearningService_MB extends ObjectService_Mybatis {
	    private MacLearningLimitMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public MacLearningLimitMapper getMapper() {
			return mapper;
		}

		public void setMapper(MacLearningLimitMapper mapper) {
			this.mapper = mapper;
		}
		
				
		/**
		 * 根据网元id查询对应的mac
		 * @param siteId
		 * @return
		 * @throws SQLException
		 */
		public List<MacLearningInfo> selectBySiteId(int siteId) throws SQLException {
			this.sqlSession.getConnection().setAutoCommit(false);
			List<MacLearningInfo> macLearningInfoList = new ArrayList<MacLearningInfo>();
			try {
				macLearningInfoList = this.mapper.selectBySiteId(siteId);
				
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return macLearningInfoList;
		}
		
		
		
		public void save(MacLearningInfo macInfo) throws Exception {
			if(macInfo == null){
				throw new Exception("macInfo is null");
			}
			this.sqlSession.getConnection().setAutoCommit(false);
			try {
				this.mapper.insert(macInfo);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}

		public void update(MacLearningInfo macInfo) throws SQLException {
			this.sqlSession.getConnection().setAutoCommit(false);
			try {
				this.mapper.update(macInfo);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}

		/**
		 * 多选删除
		 * 虚拟网元操作
		 */
		public void delete(List<MacLearningInfo> list) throws SQLException {
			this.sqlSession.getConnection().setAutoCommit(false);
			try {
				if(list != null){
					for (MacLearningInfo mac : list) {
						this.mapper.delete(mac.getId());
					}
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
			
		}
		
		public MacLearningInfo selectById(int id) throws SQLException {
			MacLearningInfo macLearningInfo = new MacLearningInfo();
			try {
				macLearningInfo = this.mapper.selectById(id);	
				if(macLearningInfo==null){
					macLearningInfo=new MacLearningInfo();
				}
			} catch (Exception e) {				
				ExceptionManage.dispose(e, this.getClass());
			}
			return macLearningInfo;
		}
		
		public void deleteByPortId(int portId) throws SQLException {
			this.sqlSession.getConnection().setAutoCommit(false);
			try {
				this.mapper.deleteByPortId(portId);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			
		}
}
