package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.dao.ptn.clock.ClockSourceMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class FrequencyClockManageService_MB extends ObjectService_Mybatis {
	private ClockSourceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public ClockSourceMapper getMapper(){
		return mapper;
	}
	public void setMapper(ClockSourceMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return List<FrequencyInfo_neClock>
	 * @throws Exception
	 */

	public List<ClockSource> select(int siteId) throws Exception {
		List<ClockSource> frequencyClockManageList=null;
		try {
			frequencyClockManageList=new ArrayList<ClockSource>();
			frequencyClockManageList = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return frequencyClockManageList;
	}
}
