package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.dao.ptn.clock.FrequencyInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ClockFrequService_MB extends ObjectService_Mybatis {
	private FrequencyInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
    public FrequencyInfoMapper getMapper() {
		return mapper;
	}
	public void setMapper(FrequencyInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	/**
	 * 根据网元Id 查询 
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<FrequencyInfo> query(int siteId) throws Exception {
		List<FrequencyInfo> infoList = null;
		try {
			infoList = new ArrayList<FrequencyInfo>();
			infoList = this.mapper.queryByCondition(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  infoList;
	}
	
	/**
	 * 修改 时钟
	 * @param info
	 * @throws Exception
	 */
	public void update(FrequencyInfo info) throws Exception {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.update(info);
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
	
	/**
	 * 添加
	 * @return 
	 */
	public int insert(FrequencyInfo info) throws Exception {
		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
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
	
	public void delete(int siteId) throws Exception {
		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.delete(siteId);
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
