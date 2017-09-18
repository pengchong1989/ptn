package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.dao.equipment.port.PortStmMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class PortStmService_MB extends ObjectService_Mybatis {
	private PortStmMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(PortStmMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public PortStmMapper getMapper() {
		return mapper;
	}

	public List<PortStm> queryBySiteid(int siteId) throws Exception{
		List<PortStm> portStms = null;
		try {
			portStms = new ArrayList<PortStm>();
			portStms = mapper.quertyBySite(siteId);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portStms;
	}
}
