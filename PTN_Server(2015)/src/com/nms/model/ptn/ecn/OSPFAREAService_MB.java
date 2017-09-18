package com.nms.model.ptn.ecn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.db.dao.ptn.ecn.OSPFAREAInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OSPFAREAService_MB extends ObjectService_Mybatis {
	private OSPFAREAInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OSPFAREAInfoMapper mapper) {
		this.mapper = mapper;
	}

	public List<OSPFAREAInfo> queryByNeID(int neId) throws Exception {
		if (neId == 0) {
			throw new Exception("NeID is null");
		}
		List<OSPFAREAInfo> oSPFAREAInfoList = null;
		try {
			oSPFAREAInfoList = this.mapper.queryByNeID(neId+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return oSPFAREAInfoList;
	}

	public int insert(OSPFAREAInfo OSPFAREAInfo) throws Exception {
		if (OSPFAREAInfo == null) {
			throw new Exception("OSPFInfo is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(OSPFAREAInfo);
			ospfId =OSPFAREAInfo.getId(); 
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
	public int queryName(String NeID, String area_range) throws Exception {
		if (NeID == null) {
			throw new Exception("NeID is null");
		}
		if (area_range == null) {
			throw new Exception("area_range is null");
		}
		int count = 0;
		try {
			count = this.mapper.queryName(NeID, area_range);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return count;
	}
	
	public int delete(int id) throws Exception {
		if (id == 0) {
			throw new Exception("id is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.deleteById(id);
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
	
	public int update(OSPFAREAInfo OSPFAREAInfo) throws Exception {
		if (OSPFAREAInfo == null) {
			throw new Exception("OSPFInfo is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.update(OSPFAREAInfo);
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
	
	public List<OSPFAREAInfo> queryByNeIDAndAreaRange(String NeID, String area_range) throws Exception {
		if (NeID == null) {
			throw new Exception("NeID is null");
		}
		if (area_range == null) {
			throw new Exception("area_range is null");
		}
		List<OSPFAREAInfo> oSPFAREAInfoList = null;
		try {
			oSPFAREAInfoList = this.mapper.queryByNeIDAndAreaRange(NeID, area_range);
			if(oSPFAREAInfoList==null){
				oSPFAREAInfoList =new ArrayList<OSPFAREAInfo>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return oSPFAREAInfoList;
	}
	
	public int delete(OSPFAREAInfo info) throws Exception {
		if (info==null) {
			throw new Exception("id is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.delete(info);
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
	
	public int updateStatus(OSPFAREAInfo OSPFAREAInfo) throws Exception {
		if (OSPFAREAInfo == null) {
			throw new Exception("OSPFInfo is null");
		}
		int ospfId = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.updateStatus(OSPFAREAInfo);
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
