package com.nms.model.ptn;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;
import com.nms.db.dao.ptn.BlackWhiteMacNameMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

/**
 * function:操作黑白MAC名单的数据库的Service层
 * @author ZK
 */
public class BlackWhiteMacService_MB extends ObjectService_Mybatis{
	    private BlackWhiteMacNameMapper mapper = null;
	 	
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public BlackWhiteMacNameMapper getMapper() {
			return mapper;
		}

		public void setMapper(BlackWhiteMacNameMapper mapper) {
			this.mapper = mapper;
		}
	
	
		/**
		 * 查询
		 * @param siteId 网元ID
		 * @return  List<MacManagementInfo> 黑白MAC名单对象集合
		 * @throws Exception
		 */
		public List<BlackAndwhiteMacInfo> selectByBlackAndwhiteMacInfo(BlackAndwhiteMacInfo blackAndwhiteMacInfo) throws SQLException {
			List<BlackAndwhiteMacInfo> blackAndwhiteMacInfoList = new ArrayList<BlackAndwhiteMacInfo>();
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				blackAndwhiteMacInfoList = this.mapper.selectByBlackAndwhiteMacInfo(blackAndwhiteMacInfo);
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return blackAndwhiteMacInfoList;
		}
		
		/**
		 * 保存
		 * @param macInfo 黑白MAC名单对象
		 * @throws Exception
		 */
		public int save(BlackAndwhiteMacInfo macInfo) throws Exception {
			if(macInfo == null){
				throw new Exception("macInfo is null");
			}
			int result = 0;
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.insert(macInfo);
				result = macInfo.getId();
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return result;
		}

		/**
		 * 更新
		 * @param macInfo 黑白MAC名单对象
		 * @throws Exception
		 */
		
		public void update(BlackAndwhiteMacInfo macInfo) throws SQLException {
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
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
		 * 删除
		 * @param macInfo 黑白MAC名单对象
		 * @throws Exception
		 */
		public void delete(BlackAndwhiteMacInfo blackAndwhiteMacInfo) throws SQLException {
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.delete(blackAndwhiteMacInfo.getId());
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
		 * 删除
		 * @param 根据siteIdz 黑白MAC名单对象
		 * @throws Exception
		 */
		public void deleteBySiteId(int siteId) throws SQLException {
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.deleteBySiteId(siteId);
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
		
		public String selectByVsAndSiteId(int vsId,int siteId) throws SQLException {
			String name=null;
			try {		
				name=this.mapper.selectByVsAndSiteId(vsId,siteId);				
			} catch (Exception e) {
			
				ExceptionManage.dispose(e, this.getClass());
			} 
			return name;
		}
}
