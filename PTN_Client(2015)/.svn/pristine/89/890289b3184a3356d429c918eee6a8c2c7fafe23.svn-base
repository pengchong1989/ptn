package com.nms.model.ptn.port;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.protect.DualRelevance;
import com.nms.db.dao.ptn.port.DualRelevanceMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class DualRelevanceService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private DualRelevanceMapper dualRelevanceMapper;

	public DualRelevanceMapper getDualRelevanceMapper() {
		return dualRelevanceMapper;
	}

	public void setDualRelevanceMapper(DualRelevanceMapper dualRelevanceMapper) {
		this.dualRelevanceMapper = dualRelevanceMapper;
	}

	/**
	 * 通过网元ID查询
	 * @param siteId 网元ID
	 * @return
	 */
	public List<DualRelevance> queryByCondition(DualRelevance condition) {
		DualRelevance dualRelevance = condition;
		List<DualRelevance> dualProtectList = null;
		try {
			dualProtectList =  this.dualRelevanceMapper.queryByCondition(dualRelevance);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			dualRelevance = null;	
		}
		return dualProtectList;
	}
}
