package com.nms.model.ptn.qos;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.dao.ptn.qos.MappingRelationMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class MappingRelationService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private MappingRelationMapper mappingRelationMapper;

	public MappingRelationMapper getMappingRelationMapper() {
		return mappingRelationMapper;
	}

	public void setMappingRelationMapper(MappingRelationMapper mappingRelationMapper) {
		this.mappingRelationMapper = mappingRelationMapper;
	}
}
