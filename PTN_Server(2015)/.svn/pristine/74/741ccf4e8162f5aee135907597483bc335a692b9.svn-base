package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.dao.equipment.port.PortStmMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class PortStmService_MB extends ObjectService_Mybatis {
	private PortStmMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(PortStmMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public PortStmMapper getMapper() {
		return mapper;
	}

	public List<PortStm> queryBySiteid(int siteId) throws Exception{
		List<PortStm> portStms = null;
		try {
			portStms = new ArrayList<PortStm>();
			portStms = mapper.quertyBySite(siteId);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portStms;
	}
	
	public List<PortStm> queryByCondition(PortStm portStm) throws Exception{
		List<PortStm> list = null;
		try {
			list = new ArrayList<PortStm>();
			list = this.mapper.queryByCondition(portStm);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return list;
	}

	public void update(PortStm portStm) throws Exception{
		PortService_MB portService =null;
		PortInst portInst = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			portInst = new PortInst();
			portInst.setPortId(portStm.getPortid());
			portInst.setIsEnabled_code(portStm.getStatus());
			portInst.setJobStatus(portStm.getJobstatus());
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			portService.update(portInst);
			this.mapper.update(portStm);
			//离线网元数据下载
			super.dateDownLoad(portStm.getSiteid(),portStm.getId(), EServiceType.SDH.getValue(), EActionType.UPDATE.getValue());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}

	
	public void save(PortStm portStm) throws Exception{
		try {
			this.mapper.insert(portStm);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public List<PortStm> queryBySiteIdAndPortId(int siteId, int portId) throws Exception{
		List<PortStm> portStms = null;
		try {
			portStms = new ArrayList<PortStm>();
			portStms = this.mapper.queryBySiteIdAndPortId(siteId, portId);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portStms;
	}
}
