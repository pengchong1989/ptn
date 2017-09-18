package com.nms.model.ptn.ecn;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.dao.ptn.ecn.MCNMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class MCNService_MB extends ObjectService_Mybatis {
	private MCNMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(MCNMapper mapper) {
		this.mapper = mapper;
	}
	

	/**
	 * 添加MCN
	 * @param neId
	 * @return
	 * @throws Exception
	 */
	public int insertMcn(String neId,String ip,int mtu ) throws Exception {
		if (neId ==null) {
			throw new Exception("neId is 0");
		}
		int information = 0;
		try {
			information = this.mapper.insert(neId,ip,mtu,0);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return information;
	}
}
