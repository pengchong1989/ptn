package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.dao.ptn.clock.LineClockInterfaceMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
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
	public List<LineClockInterface> select(LineClockInterface lineClockInterface) {
		List<LineClockInterface> lists=null;
		try {
			lists=new ArrayList<LineClockInterface>();
			lists = this.mapper.selectByCondtion(lineClockInterface);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lists;
	}
	
	public int update(LineClockInterface lineClockInterface)throws Exception {
		if (null == lineClockInterface) {
			throw new Exception("lineClockInterface is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(lineClockInterface);
			//离线网元数据下载
			super.dateDownLoad(lineClockInterface.getSiteId(),lineClockInterface.getId(), EServiceType.CLOCKPORTCONFIG.getValue(), EActionType.UPDATE.getValue());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return isOK;
	}
	
	public int insert(LineClockInterface lineClockInterface) throws Exception {
		if (null == lineClockInterface) {
			throw new Exception("lineClockInterface is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			information = this.mapper.insert(lineClockInterface);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return information;
	}
	
	
}
