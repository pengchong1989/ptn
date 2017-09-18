package com.nms.model.ptn.clock;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.dao.ptn.clock.TimeManageInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class TimeManageInfoService_MB extends ObjectService_Mybatis {
	private TimeManageInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public TimeManageInfoMapper getMapper(){
		return mapper;
	}
	
	public void setMapper(TimeManageInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return List<TimeManageInfo>
	 * @throws Exception
	 */

	public TimeManageInfo select(int siteId) throws Exception {
		TimeManageInfo timeManageInfo = null;
		try {
			timeManageInfo = new TimeManageInfo();
			timeManageInfo = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return timeManageInfo;
	}
	
	/**
	 * 更新相关的数据
	 * 
	 * @param timeManageInfo
	 *            实体
	 * @return int 1:成功，0不成功
	 * @throws Exception
	 */
	public int update(TimeManageInfo timeManageInfo)throws Exception {
		if (null == timeManageInfo) {
			throw new Exception("timeManageInfo is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(timeManageInfo);
			//离线网元数据下载
			super.dateDownLoad(timeManageInfo.getSiteId(),timeManageInfo.getId(), EServiceType.PTPCONFIG.getValue(), EActionType.UPDATE.getValue());
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
	 * @param timeManageInfo 实体
	 * @return 只要不是0就是插入成功
	 * @throws Exception
	 */
	public int insertTimeManageInfo(TimeManageInfo timeManageInfo) throws Exception {
		if (null == timeManageInfo) {
			throw new Exception("frequencyInfo_neClock is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);		 
			this.mapper.insert(timeManageInfo);
			information =timeManageInfo.getId();
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
