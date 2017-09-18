package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.ClockSource_Corba;
import com.nms.db.dao.ptn.clock.ClockSourceMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class ClockSourceService_Corba_MB extends ObjectService_Mybatis {
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private ClockSourceMapper mapper=null;
	/**
	 * 通过条件查询 保护组
	 * @param clockSource_Corba
	 * @return
	 * @throws Exception
	 */
	public List<ClockSource_Corba> queryByCondition(ClockSource_Corba clockSource_Corba) throws Exception{
		if(null == clockSource_Corba)
		clockSource_Corba = new ClockSource_Corba();
		mapper=this.sqlSession.getMapper(ClockSourceMapper.class);
		List<ClockSource_Corba> cobarList=new ArrayList<ClockSource_Corba>();
		cobarList=this.mapper.queryCorbaByCondition(clockSource_Corba);
		return cobarList;
	}
}
