package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.db.dao.ptn.clock.ExternalClockInterfaceMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ExternalClockInterfaceService_MB extends ObjectService_Mybatis {
	private ExternalClockInterfaceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public ExternalClockInterfaceMapper getMapper(){
		return mapper;
	}
	public void setMapper(ExternalClockInterfaceMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return lineClockInterface
	 * @throws Exception
	 */

	public List<ExternalClockInterface> select(ExternalClockInterface externalClockInterface) throws Exception {
		List<ExternalClockInterface> lists=null;
		try {
			lists=new ArrayList<ExternalClockInterface>();
			lists = this.mapper.select(externalClockInterface);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lists;
	}
}
