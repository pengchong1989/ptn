package com.nms.model.ptn.ecn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.CCN;
import com.nms.db.dao.ptn.ecn.CCNMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class CCNService_MB extends ObjectService_Mybatis {
	private CCNMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(CCNMapper mapper) {
		this.mapper = mapper;
	}


	public List<CCN> queryByNeInAndName(String name, int neId) throws Exception {
		if (name == null) {
			throw new Exception("name is null");
		}
		try {
			return this.mapper.selectByNeIdAndName(name, neId+"");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<CCN> queryByNeID(String NeID) throws Exception {
		if (NeID == null) {
			throw new Exception("NeID is null");
		}
		List<CCN> ccnList = null;
		try {
			ccnList = this.mapper.queryByNeID(NeID);
			if(ccnList==null){
				ccnList=new ArrayList<CCN>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ccnList;
	}
	
	public int delete(CCN ccn) throws Exception {
		if (ccn == null) {
			throw new Exception("ccn is null");
		}
		int ccnId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ccnId = this.mapper.deleteById(ccn.getId());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ccnId;
	}
	
	
	
	public int insert(CCN ccn) throws Exception {
		if (ccn == null) {
			throw new Exception("ccn is null");
		}
		int ccnId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(ccn);
			ccnId = ccn.getId();
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ccnId;
	}
	
	public int update(CCN ccn) throws Exception {
		if (ccn == null) {
			throw new Exception("ccn is null");
		}
		int id = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			id = this.mapper.update(ccn);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return id;
	}
	
	public int updateStatus(CCN ccn) throws Exception {
		if (ccn == null) {
			throw new Exception("ccn is null");
		}
		int id = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			id = this.mapper.updateStatus(ccn);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return id;
	}
}
