package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.db.dao.ptn.clock.PortConfigInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class PortDispositionInfoService_MB extends ObjectService_Mybatis {
	private PortConfigInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public PortConfigInfoMapper getMapper(){
		return mapper;
	}
	public void setMapper(PortConfigInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return List<PortConfigInfo>
	 * @throws Exception
	 */

	public List<PortConfigInfo> select(int siteId) throws Exception {
		List<PortConfigInfo> listPortDispositionInfo=null;
		try {
			listPortDispositionInfo=new ArrayList<PortConfigInfo>();
			listPortDispositionInfo = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return listPortDispositionInfo;
	}

}
