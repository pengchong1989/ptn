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
	
	public void save(MacManagementInfo macInfo) throws Exception {
		if(macInfo == null){
			throw new Exception("macInfo is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
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
	

	public void update(MacManagementInfo macInfo) throws SQLException {
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
	 * 多选删除
	 * 虚拟网元操作
	 */
	public void delete(List<MacManagementInfo> list) throws SQLException {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			if(list != null){
				for (MacManagementInfo mac : list) {
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
	
	public void deleteById(int id) throws SQLException {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.delete(id);
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
	
	public MacManagementInfo selectById(int id) throws SQLException {
		MacManagementInfo macManagementInfo = null;
		try {
			macManagementInfo =this.mapper.selectById(id);
			if(macManagementInfo==null){
				macManagementInfo=new MacManagementInfo();
			}
		} catch (Exception e) {			
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return macManagementInfo;
	}
}
