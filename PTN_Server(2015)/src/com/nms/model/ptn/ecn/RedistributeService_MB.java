package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;
import com.nms.db.dao.ptn.ecn.OspfRedistributeMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class RedistributeService_MB extends ObjectService_Mybatis {
	private OspfRedistributeMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public void setMapper(OspfRedistributeMapper mapper) {
		this.mapper = mapper;
	}

	public List<OspfRedistribute> queryByNeID(int NeID) throws Exception {
		if (NeID == 0) {
			throw new Exception("NeID is null");
		}
		List<OspfRedistribute> ospfRedistributeList = null;
		try {
			ospfRedistributeList = this.mapper.queryByNeID(NeID);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfRedistributeList;
	}

	public int queryName(OspfRedistribute ospfRedistribute) throws Exception {
		if (ospfRedistribute == null) {
			throw new Exception("ospfRedistribute is null");
		}
		int count = 0;
		try {
			count = this.mapper.queryName(ospfRedistribute);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return count;
	}

	/**
	 *创建
	 * 
	 * @param OSPFAREAInfo
	 * @return
	 * @throws Exception
	 */
	public int insert(OspfRedistribute ospfRedistribute) throws Exception {
		if (ospfRedistribute == null) {
			throw new Exception("OSPFRedistribute is null");
		}
		int ospfId = 1;
		try {
			ospfId = this.mapper.insert(ospfRedistribute);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfId;
	}

	/**
	 *修改
	 * 
	 * @param OSPFAREAInfo
	 * @return
	 * @throws Exception
	 */
	public int update(OspfRedistribute ospfRedistribute) throws Exception {
		if (ospfRedistribute == null) {
			throw new Exception("ospfRedistribute is null");
		}
		int ospfId = 0;
		try {
			ospfId = this.mapper.updateByPrimaryKey(ospfRedistribute);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfId;
	}

	/**
	 *删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(OspfRedistribute ospfRedistribute) throws Exception {
		if (ospfRedistribute == null) {
			throw new Exception("id is null");
		}
		int ospfId = 1;
		try {
			ospfId = this.mapper.deleteByNeIdAndType(ospfRedistribute);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfId;
	}

	/**
	 * 修改 激活状态
	 * 
	 * @param OSPFAREAInfo
	 * @return
	 * @throws Exception
	 */
	public int updateStatus(OspfRedistribute ospfRedistribute) throws Exception {
		if (ospfRedistribute == null) {
			throw new Exception("ospfRedistribute is null");
		}
		int ospfId = 0;
		try {
			ospfId = this.mapper.updateStatus(ospfRedistribute);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfId;
	}

	public List<OspfRedistribute> queryByNeIdAndNameRedistributeType(String neID, String redistributeType) throws Exception {

		if (neID == null) {
			throw new Exception("neID is null");
		}
		if (redistributeType == null) {
			throw new Exception("redistributeType is null");
		}
		List<OspfRedistribute> ospfRedistributeList = null;
		try {
			ospfRedistributeList = this.mapper.queryByNeIdAndNameRedistributeType(neID, redistributeType);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return ospfRedistributeList;
	}
}
