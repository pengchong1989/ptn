package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.dao.equipment.port.E1InfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class E1InfoService_MB extends ObjectService_Mybatis {
	private E1InfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	
	public E1InfoMapper getMapper() {
		return mapper;
	}

	public void setMapper(E1InfoMapper mapper) {
		this.mapper = mapper;
	}

	public List<E1Info> selectByCondition(E1Info e1Info) throws Exception {
		List<E1Info> e1InfoList = null;		
		try {
			e1InfoList = mapper.queryByCondition(e1Info);
			if(e1InfoList==null){
				e1InfoList= new ArrayList<E1Info>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return e1InfoList;
	}
	
	/**
	 * 通过网元id查找
	 * @param siteId
	 * @return
	 */
	public List<E1Info> selectBySiteId(int siteId){
		List<E1Info> e1InfoList = null;		
		try {
			e1InfoList = this.mapper.queryBySiteId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return e1InfoList;
	}
	
	public void update(E1Info e1Info) throws Exception{
		PortService_MB portService =null;
		PortInst portInst = null;
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			portInst = new PortInst();
			portInst.setPortId(e1Info.getPortId());
			if(siteService.getManufacturer(e1Info.getSiteId()) == EManufacturer.WUHAN.getValue()){
				portInst.setIsEnabled_code(e1Info.getLegEnable());
			}else{
				portInst.setIsEnabled_code(e1Info.getPortInst().getIsEnabled_code());
			}
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			portService.update(portInst);
			this.mapper.updateByPrimaryKey(e1Info);
			//离线网元数据下载
			super.dateDownLoad(e1Info.getSiteId(),e1Info.getId(), EServiceType.PDH.getValue(), EActionType.UPDATE.getValue(), e1Info.getPortId()+"",null,0,0,null);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
		}
	}
	
	public int saveOrUpdate(E1Info e1Info) throws Exception {
		if(e1Info == null ) {
			throw new Exception("E1InfoList is null");
		}
		
		int result = 0;
		try {
			//下发E1
			if(e1Info.getId() > 0) {
				mapper.updateByPrimaryKey(e1Info);
			} else {
				result = mapper.insert(e1Info);
			}
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	
	
}
