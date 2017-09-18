package com.nms.model.ptn.clock;

import org.apache.ibatis.session.SqlSession;

import com.nms.model.util.ObjectService_Mybatis;

public class ClockSourceService_Corba_MB extends ObjectService_Mybatis {
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
}
