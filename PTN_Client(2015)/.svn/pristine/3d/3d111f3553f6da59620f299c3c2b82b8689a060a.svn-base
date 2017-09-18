package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;
import com.nms.db.dao.ptn.ecn.OspfRedistributeMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class RedistributeService_MB extends ObjectService_Mybatis {
	private OspfRedistributeMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OspfRedistributeMapper mapper) {
		this.mapper = mapper;
	}

	public List<OspfRedistribute> queryByNeID(int NeID) throws Exception {
		if (NeID == 0) {
			throw new Exception("NeID is null");
		}
		List<OspfRedistribute> ospfRedistributeList = null;
		try {
			ospfRedistributeList = this.mapper.queryByNeID(NeID);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ospfRedistributeList;
	}
}
