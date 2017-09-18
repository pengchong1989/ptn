package com.nms.model.ptn.clock;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.db.dao.ptn.clock.PortConfigInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
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

	public List<PortConfigInfo> select(PortConfigInfo portConfigInfo) {
		List<PortConfigInfo> listPortDispositionInfo=null;
		try {
			listPortDispositionInfo=new ArrayList<PortConfigInfo>();
			listPortDispositionInfo = this.mapper.selectByCondtion(portConfigInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return listPortDispositionInfo;
		
	}
	
	public int insertPortDispositionInfo(PortConfigInfo portConfigInfo) throws Exception {
		if (null == portConfigInfo) {
			throw new Exception("portConfigInfo is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			information = this.mapper.insert(portConfigInfo);
			//离线网元数据下载
			super.dateDownLoad(portConfigInfo.getSiteId(),information, EServiceType.CLOCKPORTCONFIG.getValue(), EActionType.INSERT.getValue());
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
	
	public int delete(List<PortConfigInfo> PortConfigInfoList)throws Exception {

		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(PortConfigInfo PortConfigInfo:PortConfigInfoList){
				information = this.mapper.delete(PortConfigInfo);
				//离线网元数据下载
				super.dateDownLoad(PortConfigInfo.getSiteId(),PortConfigInfo.getId(), EServiceType.CLOCKPORTCONFIG.getValue(), EActionType.UPDATE.getValue(),"","",PortConfigInfo.getPort(),0,"");
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

	public int update(PortConfigInfo portConfigInfo)throws Exception {
		if (null == portConfigInfo) {
			throw new Exception("portConfigInfo is null");
		}
		int isOK = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			isOK = this.mapper.update(portConfigInfo);
			//离线网元数据下载
			super.dateDownLoad(portConfigInfo.getSiteId(),portConfigInfo.getId(), EServiceType.CLOCKPORTCONFIG.getValue(), EActionType.UPDATE.getValue());
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
	
}
