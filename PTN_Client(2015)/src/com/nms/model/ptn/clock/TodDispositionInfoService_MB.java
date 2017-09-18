package com.nms.model.ptn.clock;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.TodConfigInfo;
import com.nms.db.dao.ptn.clock.TodConfigInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class TodDispositionInfoService_MB extends ObjectService_Mybatis {
	private TodConfigInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
    public TodConfigInfoMapper getMapper(){
    	return mapper;
    }
	public void setMapper(TodConfigInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 删除相关的数据
	 * 
	 * @param todConfigInfo
	 *            实体
	 * @return int 1:成功，0不成功
	 * @throws Exception
	 */
	public int update(TodConfigInfo todConfigInfo)throws Exception {
		if (null == todConfigInfo) {
			throw new Exception("todConfigInfo is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(todConfigInfo);
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
	 * 查询相关信息
	 * 
	 * @return List<FrequencyInfo_neClock>
	 * @throws Exception
	 */

	public TodConfigInfo select(int siteId) throws Exception {
		TodConfigInfo todConfigInfo=null;
		try {
			todConfigInfo = new TodConfigInfo();
			todConfigInfo = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return todConfigInfo;
	}

	
}
