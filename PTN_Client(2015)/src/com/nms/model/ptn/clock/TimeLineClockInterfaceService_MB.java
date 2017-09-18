package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.dao.ptn.clock.LineClockInterfaceMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class TimeLineClockInterfaceService_MB extends ObjectService_Mybatis {
	private LineClockInterfaceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public LineClockInterfaceMapper getMapper(){
		return mapper;
	}
	public void setMapper(LineClockInterfaceMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return lineClockInterface
	 * @throws Exception
	 */

	public List<LineClockInterface> select(int siteId) throws Exception {
		List<LineClockInterface> lists=null;
		try {
			lists=new ArrayList<LineClockInterface>();
			lists = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lists;
	}

}
