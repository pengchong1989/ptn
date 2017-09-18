package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.dao.ptn.ecn.OSPFInterfaceMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OSPFInterfaceService_MB extends ObjectService_Mybatis {
	private OSPFInterfaceMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OSPFInterfaceMapper mapper) {
		this.mapper = mapper;
	}

	public List<OSPFInterface> queryByNeID(int NeID) throws Exception {
		if (NeID == 0) {
			throw new Exception("NeID is null");
		}
		List<OSPFInterface> ospfInterfaceList = null;
		try {
			ospfInterfaceList = this.mapper.queryByNeID(NeID+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ospfInterfaceList;
	}

	public List<OSPFInterface> queryByCondition(OSPFInterface ospfInterface) throws Exception {
		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}
		List<OSPFInterface> ospfInterfaceList = null;
		try {
			ospfInterfaceList = this.mapper.queryByCondition(ospfInterface);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ospfInterfaceList;
	}
	
	public int updateActiveStatus(int siteId, int value) throws Exception {
		int result=0;
		result=this.mapper.updateActiveStatus(siteId+"", value);
		this.sqlSession.commit();
		return result;
	}
	
	public int delete(OSPFInterface ospfInterface) throws Exception {
		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}
		int result = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			result = this.mapper.deleteById(ospfInterface.getId());
			//离线网元数据下载
			super.dateDownLoad(Integer.parseInt(ospfInterface.getNeId()), ospfInterface.getId(), EServiceType.OSPF.getValue(), EActionType.DELETE.getValue(), ospfInterface.getInterfaceName(),"",0,0,"");
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	public int insert(OSPFInterface ospfInterface) throws Exception {
		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}
		int result = 1;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(ospfInterface);
			result = ospfInterface.getId();
			//离线网元数据下载
			super.dateDownLoad(Integer.parseInt(ospfInterface.getNeId()), result, EServiceType.OSPF.getValue(), EActionType.INSERT.getValue(), ospfInterface.getInterfaceName(),"",0,0,"");
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	public int update(OSPFInterface ospfInterface) throws Exception {
		if (ospfInterface == null) {
			throw new Exception("ospfInterface is null");
		}
		int id = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			id = this.mapper.update(ospfInterface);
			//离线网元数据下载
			super.dateDownLoad(Integer.parseInt(ospfInterface.getNeId()), ospfInterface.getId(), EServiceType.OSPF.getValue(), EActionType.UPDATE.getValue(), ospfInterface.getInterfaceName(),"",0,0,"");
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return id;
	}
	
	public void updateStatus(OSPFInterface OSPFInterface) throws Exception {
		if (OSPFInterface == null) {
			throw new Exception("OSPFInterface is null");
		}
		try {
			 this.mapper.updateStatus(OSPFInterface);
			 this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
}
