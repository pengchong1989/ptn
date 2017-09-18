package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.dao.ptn.ecn.OSPFInterfaceMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OSPFInterfaceService_MB extends ObjectService_Mybatis {
	private OSPFInterfaceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OSPFInterfaceMapper mapper) {
		this.mapper = mapper;
	}

	public List<OSPFInterface> queryByNeID(int NeID) throws Exception {
		if (NeID == 0) {
			throw new Exception("NeID is null");
		}
		List<OSPFInterface> ospfInterfaceList = null;
		try {
			ospfInterfaceList = this.mapper.queryByNeID(NeID+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ospfInterfaceList;
	}

	public List<OSPFInterface> queryByCondition(OSPFInterface ospfInterface) throws Exception {
		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}
		List<OSPFInterface> ospfInterfaceList = null;
		try {
			ospfInterfaceList = this.mapper.queryByCondition(ospfInterface);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ospfInterfaceList;
	}
}
