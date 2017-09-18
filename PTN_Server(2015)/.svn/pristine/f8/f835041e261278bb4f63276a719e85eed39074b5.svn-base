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
	
	
	public int insertTimeManageInfo(ExternalClockInterface externalClockInterface) throws Exception {
		if (null == externalClockInterface) {
			throw new Exception("externalClockInterface is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(externalClockInterface);
			information = externalClockInterface.getId();
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

	public int update(ExternalClockInterface externalClockInterface)throws Exception {
		if (null == externalClockInterface) {
			throw new Exception("externalClockInterface is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(externalClockInterface);
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
	
	public void updateActiveStatus(int siteId, int value) throws Exception {
		if (0 == siteId) {
			throw new Exception("siteId is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.updateActiveStatus(siteId,value);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}		
	}
	
	public int delete(ExternalClockInterface externalClockInterface)throws Exception {
		int information = 0;
		try {
			if(externalClockInterface==null){
				throw new Exception("externalClockInterface is null");
			}
				information = this.mapper.delete(externalClockInterface);
				this.sqlSession.commit();
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return information;
	}
}
