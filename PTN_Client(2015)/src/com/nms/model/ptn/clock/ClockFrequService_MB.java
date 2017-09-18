package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.dao.ptn.clock.FrequencyInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ClockFrequService_MB extends ObjectService_Mybatis {
	private FrequencyInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
    public FrequencyInfoMapper getMapper() {
		return mapper;
	}
	public void setMapper(FrequencyInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	
	
	/**
	 * 根据网元Id 查询 
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<FrequencyInfo> query(int siteId) throws Exception {
		List<FrequencyInfo> infoList = null;
		try {
			infoList = new ArrayList<FrequencyInfo>();
			infoList = this.mapper.queryByCondition(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  infoList;
	}
}
