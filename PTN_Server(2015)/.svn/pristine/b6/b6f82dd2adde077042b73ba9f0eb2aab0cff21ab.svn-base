package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.dao.equipment.port.PortStmTimeslotMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class PortStmTimeslotService_MB extends ObjectService_Mybatis {
	private PortStmTimeslotMapper mapper;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public PortStmTimeslotMapper getMapper() {
		return mapper;
	}

	public void setMapper(PortStmTimeslotMapper mapper) {
		this.mapper = mapper;
	}
	
	public PortStmTimeslot selectById(int id) throws Exception{
		PortStmTimeslot portStmTimeslot=null;
		try {
			portStmTimeslot = this.mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portStmTimeslot;
	}
	
    public List<PortStmTimeslot> selectbyportId(int portId) throws Exception{
   	 List<PortStmTimeslot> list=null;
		try {
			list = this.mapper.quertyByPortId(portId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}	
		return list;
	}
    
	public List<PortStmTimeslot> select(int siteId) throws Exception{
		List<PortStmTimeslot> list=null;
		try {
			list= new ArrayList<PortStmTimeslot>();
			list = this.mapper.quertyBySite(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return list;
	}


 	public void update(PortStmTimeslot portStmTimeslot) throws Exception{
		try {
			this.mapper.update(portStmTimeslot);
			this.sqlSession.commit();
			//离线网元数据下载
			super.dateDownLoad(portStmTimeslot.getSiteId(),portStmTimeslot.getId(), EServiceType.SDHSLOTTIME.getValue(), EActionType.UPDATE.getValue());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
 	
 	public void updateActiveStatus(int siteId, int status) throws Exception {

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.updateStatus(siteId, status);
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
 	
 	public void insert(PortStmTimeslot portStmTimeslot) throws Exception{
		try {
			this.mapper.insert(portStmTimeslot);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
 	
 	public List<PortStmTimeslot> selectBySiteIdAndNumberAndName(int siteId, int portId, String name) throws Exception{
		List<PortStmTimeslot> list=null;
		try {
			list = this.mapper.selectBySiteIdAndNumberAndName(siteId, portId, name);
			if(list==null){
				list=new ArrayList<PortStmTimeslot>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return list;
	}
 	
 	public List<PortStmTimeslot> selectBySiteIdAndPortIdAndName(int siteId, int portId, String name) throws Exception{
		List<PortStmTimeslot> list=null;
		try {
			list = this.mapper.selectBySiteIdAndPortIdAndName(siteId, portId, name);
			if(list==null){
				list=new ArrayList<PortStmTimeslot>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return list;
	}

		

}
