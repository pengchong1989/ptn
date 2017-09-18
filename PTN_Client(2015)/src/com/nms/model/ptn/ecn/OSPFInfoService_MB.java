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
}
