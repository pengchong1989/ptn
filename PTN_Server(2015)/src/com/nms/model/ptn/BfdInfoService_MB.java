package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.BfdInfo;
import com.nms.db.dao.ptn.BfdInfoMapper;
import com.nms.drive.service.bean.BfdObject;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class BfdInfoService_MB extends ObjectService_Mybatis{
   private BfdInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public BfdInfoMapper getMapper() {
		return mapper;
	}
	
	public void setMapper(BfdInfoMapper mapper) {
		this.mapper = mapper;
	}

	
	
	/**
	 * 修改
	 * @param info
	 * @throws Exception
	 */
	public void update(BfdInfo info) throws Exception {
		try {
			this.getSqlSession().getConnection().setAutoCommit(false);
			this.mapper.update(info);
			if(!this.getSqlSession().getConnection().getAutoCommit()){
				this.getSqlSession().getConnection().commit();
			}
		} catch (Exception e) {
			this.getSqlSession().getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.getSqlSession().getConnection().setAutoCommit(true);
		}

	}

	/**
	 * 添加
	 * @return 
	 */
	public int insert(BfdInfo info) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			throw new Exception("connection is null");
		}
		int result = 0;

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(info);
			result = info.getId();
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	public void delete(List<BfdInfo> bfdInfoList) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(BfdInfo bfdInfo:bfdInfoList){
				this.mapper.delete(bfdInfo.getId());
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
	

	public List<BfdInfo> selectByCondition(BfdInfo info) throws Exception {
		List<BfdInfo> bfd = null;
		try {
			bfd = new ArrayList<BfdInfo>();
			bfd = this.mapper.selectByCondtion(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  bfd;
	}
	
	/**
	 * 根据网元Id 查询 
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<BfdObject> selectBySiteId(int siteId) throws Exception {
		List<BfdObject> infoList = null;
		try {
			infoList = this.mapper.queryByCondition(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  infoList;
	}
	
	public void deleteBySiteId(int siteId) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
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
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
}
