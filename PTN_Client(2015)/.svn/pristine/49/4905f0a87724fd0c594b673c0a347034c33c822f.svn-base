package com.nms.model.ptn.clock;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.dao.ptn.clock.FrequencyInfoNeClockMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class FrequencyInfoNeClockService_MB extends ObjectService_Mybatis {
	private FrequencyInfoNeClockMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public FrequencyInfoNeClockMapper getMapper(){
		return mapper;
	}
	public void setMapper(FrequencyInfoNeClockMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return List<FrequencyInfo_neClock>
	 * @throws Exception
	 */

	public FrequencyInfoNeClock select(int siteId) throws Exception {
		FrequencyInfoNeClock FrequencyInfo_neClockList = null;
		try {
			FrequencyInfo_neClockList = new FrequencyInfoNeClock();
			FrequencyInfo_neClockList = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return FrequencyInfo_neClockList;
	}
	
	/**
	 * 删除相关的数据
	 * 
	 * @param frequencyInfo_neClock
	 *            实体
	 * @return int 1:成功，0不成功
	 * @throws Exception
	 */
	public int update(FrequencyInfoNeClock frequencyInfo_neClock)throws Exception {
		if (null == frequencyInfo_neClock) {
			throw new Exception("frequencyInfo_neClock is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(frequencyInfo_neClock);
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

	/**
	 *添加数据
	 *
	 * @param frequencyInfo_neClock 实体
	 * @return 只要不是0就是插入成功
	 * @throws Exception
	 */
	public int insertFrequencyInfo_neClock(FrequencyInfoNeClock frequencyInfo_neClock) throws Exception {
		if (null == frequencyInfo_neClock) {
			throw new Exception("frequencyInfo_neClock is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);			
			this.mapper.insert(frequencyInfo_neClock);
			information =frequencyInfo_neClock.getId();
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
