package com.nms.model.ptn;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.MacManagementInfo;
import com.nms.db.dao.ptn.BlackListMacMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class MacManageService_MB extends ObjectService_Mybatis {
    private BlackListMacMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public BlackListMacMapper getMapper() {
		return mapper;
	}

	public void setMapper(BlackListMacMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 根据网元id查询对应的mac
	 * @param siteId
	 * @return
	 * @throws SQLException
	 */
	public List<MacManagementInfo> selectBySiteId(int siteId) throws SQLException {
		List<MacManagementInfo> macManagementInfoList = new ArrayList<MacManagementInfo>();
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			macManagementInfoList =  this.mapper.selectBySiteId(siteId);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return macManagementInfoList;
	}

	public boolean checkVlanAndMac(MacManagementInfo macInfo) throws SQLException {
		boolean flag = false;
		List<MacManagementInfo> macInfoList = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);			
		    macInfoList=new ArrayList<MacManagementInfo>();
			macInfoList=this.mapper.checkVlanAndMac(macInfo);
			if(macInfoList!=null && macInfoList.size()>0){
			   flag = true;
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			macInfoList=null;
		}
		return flag;
	}
	
	public boolean selectCountBySiteId(int siteId) throws SQLException {
		boolean flag = false;
		List<MacManagementInfo> macInfoList = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);		
		    macInfoList=new ArrayList<MacManagementInfo>();
			macInfoList=this.mapper.selectCountBySiteId(siteId);
			if(macInfoList!=null && macInfoList.size()>=50){
				flag = true;
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			macInfoList=null;
		}
		return flag;
	}
	
	public MacManagementInfo selectById(int id) throws SQLException {
		MacManagementInfo macInfoList = null;
		try {		
		    macInfoList=new MacManagementInfo();
			macInfoList=this.mapper.selectById(id);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return macInfoList;
	}
}
