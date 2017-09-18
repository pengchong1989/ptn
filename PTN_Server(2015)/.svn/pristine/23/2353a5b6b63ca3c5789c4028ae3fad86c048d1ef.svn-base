package com.nms.model.ptn.ecn;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OSPFInfo;
import com.nms.db.dao.ptn.ecn.OSPFInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OSPFInfoService_MB extends ObjectService_Mybatis {
	private OSPFInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OSPFInfoMapper mapper) {
		this.mapper = mapper;
	}

	public OSPFInfo queryByNeID(int NeID) throws Exception {
		if (NeID == 0) {
			throw new Exception("NeID is null");
		}
		OSPFInfo OSPFInfo = null;
		try {
			OSPFInfo = this.mapper.queryByNeID(NeID+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return OSPFInfo;
	}
	
	public int insert(OSPFInfo OSPFInfo) throws Exception {
		if (OSPFInfo == null) {
			throw new Exception("OSPFInfo is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(OSPFInfo);
			ospfId =Integer.parseInt(OSPFInfo.getId());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ospfId;
	}
	
	public int delete(String id) throws Exception {
		if (id == null) {
			throw new Exception("id is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.deleteById(Integer.parseInt(id));
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ospfId;
	}
	
	/**
	 *修改
	 * 
	 * @param OSPFInfo
	 * @return
	 * @throws Exception
	 */
	public int update(OSPFInfo OSPFInfo) throws Exception {
		if (OSPFInfo == null) {
			throw new Exception("OSPFInfo is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.update(OSPFInfo);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
				}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ospfId;
	}
}
