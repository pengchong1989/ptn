package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.dao.ptn.ecn.MCNMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class MCNService_MB extends ObjectService_Mybatis {
	private MCNMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(MCNMapper mapper) {
		this.mapper = mapper;
	}
	

	/**
	 * 添加MCN
	 * @param neId
	 * @return
	 * @throws Exception
	 */
	public int insertMcn(String neId,String ip,int mtu ) throws Exception {
		if (neId == null) {
			throw new Exception("neId is 0");
		}
		int information = 0;
		try {
			int id=0;
			information =this.mapper.insert(neId,ip,mtu,id);			 
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return information;
	}
	
	/**
	 * 通过网元ID查询
	 * @param NeID
	 * 			网元ID
	 * @return
	 * @throws Exception
	 */
	public List<MCN> queryByNeID(String NeID) throws Exception {
		if (NeID == null) {
			throw new Exception("NeID is null");
		}
		List<MCN> mcnList = null;
		try {
			mcnList = this.mapper.queryByNeID(NeID);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return mcnList;
	}
	
	/**
	 * 
	* queryBySiteIdAndIp通过siteid查询mcn 	
	* @author wangwf	
	* @param   siteid,ip	
	* @return 	
	* @Exception 异常对象	
	 */
	public List<MCN> queryBySiteId(int siteid) throws Exception {
		
		List<MCN> mcnList = null;
		try {
			mcnList = this.mapper.queryByNeID(siteid+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return mcnList;
	}

	public int update(MCN mcn) throws Exception {
		if (mcn == null) {
			throw new Exception("mcn is null");
		}
		int ospfId = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ospfId = this.mapper.update(mcn);
			super.dateDownLoad(Integer.parseInt(mcn.getNeId()), Integer.parseInt(mcn.getNeId()), EServiceType.MCN.getValue(), EActionType.UPDATE.getValue());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ospfId;
	}
	
	public int insert(MCN mcn) throws Exception {
		if (null == mcn) {
			throw new Exception("mcn is null");
		}
		int information = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insertMcn(mcn);
			information = mcn.getId();
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
