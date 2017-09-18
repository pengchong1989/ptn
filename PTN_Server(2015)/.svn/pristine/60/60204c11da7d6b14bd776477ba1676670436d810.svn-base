package com.nms.model.system;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.NetWork;
import com.nms.db.dao.system.NetWorkMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class NetService_MB extends ObjectService_Mybatis{
    private NetWorkMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public NetWorkMapper getMapper() {
		return mapper;
	}

	public void setMapper(NetWorkMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 新增
	 * @param netWork
	 * @return
	 */
	public int saveOrUpdate(NetWork netWork){
		int result = 0;
		try {
			if(netWork.getNetWorkId()>0){
				this.mapper.update(netWork);
				result = netWork.getNetWorkId();
				this.sqlSession.commit();
			}else{
				result = this.mapper .insert(netWork);
				this.sqlSession.commit();
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<NetWork> select(){
		List<NetWork> netWorks = null;
		try {
			netWorks = new ArrayList<NetWork>();
			netWorks = this.mapper.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return netWorks;
	}
	
	/**
	 * 根据id查询查询所有
	 * @return
	 */
	public List<NetWork> select(NetWork netWork){
		List<NetWork> netWorks = null;
		try {
			netWorks = new ArrayList<NetWork>();
			netWorks = this.mapper.selectByNetWorkId(netWork);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return netWorks;
	}
	
	/**
	 * 根据id删除
	 * @param netWork
	 * @return
	 */
	public int delete(NetWork netWork){
		int result = 0;
		try {
			result = this.mapper.delete(netWork);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	/**
	 * 批量更新
	 * @param netWorks
	 * @return
	 */
	public void updateBatch(List<NetWork> netWorks){
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(NetWork netWork: netWorks){
				this.mapper.update(netWork);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (SQLException e) {
			try {
				this.sqlSession.getConnection().rollback();
			} catch (SQLException e1) {

			}
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
}
