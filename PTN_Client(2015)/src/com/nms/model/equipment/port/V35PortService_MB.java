package com.nms.model.equipment.port;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.V35PortInfo;
import com.nms.db.dao.equipment.port.V35PortInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class V35PortService_MB extends ObjectService_Mybatis {
	private V35PortInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(V35PortInfoMapper mapper) {
		this.mapper = mapper;
	}

	public V35PortInfoMapper getMapper() {
		return mapper;
	}

	public List<V35PortInfo> selectByCondition(V35PortInfo info) {
		List<V35PortInfo> v35PortInfos = null;		
		try {
			v35PortInfos = this.mapper.queryByCondition(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return v35PortInfos;
	}
	
}
