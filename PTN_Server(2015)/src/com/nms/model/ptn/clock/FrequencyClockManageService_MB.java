package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.dao.ptn.clock.ClockSourceMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class FrequencyClockManageService_MB extends ObjectService_Mybatis {
	private ClockSourceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public ClockSourceMapper getMapper(){
		return mapper;
	}
	public void setMapper(ClockSourceMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询相关信息
	 * 
	 * @return List<FrequencyInfo_neClock>
	 * @throws Exception
	 */

	public List<ClockSource> select(int siteId) throws Exception {
		List<ClockSource> frequencyClockManageList=null;
		try {
			frequencyClockManageList=new ArrayList<ClockSource>();
			frequencyClockManageList = this.mapper.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return frequencyClockManageList;
	}
	
	/**
	 * 条件查询
	 * 
	 * @return List<FrequencyInfo_neClock>
	 * @throws Exception
	 */

	public List<ClockSource> select(ClockSource clockSource) throws Exception {
		List<ClockSource> frequencyClockManageList=null;
		try {
			frequencyClockManageList=new ArrayList<ClockSource>();
			frequencyClockManageList = this.mapper.selectByCondtion(clockSource);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return frequencyClockManageList;
	}
	
	/**
	 *添加数据
	 *
	 * @param clockSource 实体
	 * @return 只要不是0就是插入成功
	 * @throws Exception
	 */
	public int insert(ClockSource clockSource) throws Exception {
		if (null == clockSource) {
			throw new Exception("clockSource is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			clockSource.setId(UiUtil.getCodeByValue("clockType", "0").getId());
			information = this.mapper.insertSystemModel(clockSource);
			clockSource.setId(UiUtil.getCodeByValue("clockType", "1").getId());
			clockSource.setExternalOrder("lock");
			information = this.mapper.insertExportModel(clockSource);
			//离线网元数据下载
			super.dateDownLoad(clockSource.getSiteId(),information, EServiceType.CLOCKSOURCE.getValue(), EActionType.INSERT.getValue());
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
	
	/**
	 * function:删除相关联数据
	 * @param clockSource
	 * @throws Exception
	 */
	public int delete(List<ClockSource> clockSourceList)throws Exception {
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(ClockSource clockSource: clockSourceList){
				information = this.mapper.delete(clockSource);
				//离线网元数据下载
				super.dateDownLoad(clockSource.getSiteId(),clockSource.getId(), EServiceType.CLOCKSOURCE.getValue(), EActionType.DELETE.getValue(),"",null,clockSource.getPort(),0,null);
			}
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
	
	
	public int update(ClockSource clockSource)throws Exception {
		if (null == clockSource) {
			throw new Exception("clockSource is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(clockSource);
			//离线网元数据下载
			if(0!=clockSource.getSiteId()){
				super.dateDownLoad(clockSource.getSiteId(),clockSource.getId(), EServiceType.CLOCKSOURCE.getValue(), EActionType.UPDATE.getValue());
			}
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

	public void updateActiveStatus(int siteId, int value) {
		try {
			this.mapper.updateActiveStatus(siteId,value);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

}
