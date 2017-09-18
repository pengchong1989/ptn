package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.ptn.BfdInfo;
import com.nms.db.dao.ptn.BfdInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class BfdInfoService_MB extends ObjectService_Mybatis{
   private BfdInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public BfdInfoMapper getMapper() {
		return mapper;
	}
	
	public void setMapper(BfdInfoMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 根据网元Id 查询 bfdid，以此来分配bfdid
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> queryBfdId(int siteId) throws Exception {
		List<Integer> bfdIds = null;
		try {
			bfdIds = new ArrayList<Integer>();
			bfdIds = this.mapper.queryBfdId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  bfdIds;
	}
	
	/**
	 * 根据网元Id 查询 mysid，以此来分配mysid
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> queryMySid(int siteId) throws Exception {
		List<Integer> mySidIds = null;
		try {
			mySidIds = new ArrayList<Integer>();
			mySidIds = this.mapper.queryMySid(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  mySidIds;
	}
	
	/**
	 * 根据网元Id 查询peerSid条目数，以此来分配mysid
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> queryPeerSid(int siteId) throws Exception {
		List<Integer> peerSidIds = null;
		try {
			peerSidIds = new ArrayList<Integer>();
			peerSidIds = this.mapper.queryPeerSid(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  peerSidIds;
	}

	public List<BfdInfo> selectByCondition(BfdInfo info) throws Exception {
		List<BfdInfo> bfd = null;
		try {
			bfd = new ArrayList<BfdInfo>();
			bfd = this.mapper.selectByCondtion(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  bfd;
	}
	
	
	/**
	 * 根据网元Id 查询pwId条目数
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> queryPwIds(int siteId,int type) throws Exception {
		List<Integer> pwIds=null;
		try {
			pwIds = new ArrayList<Integer>();
			pwIds = this.mapper.queryPwIds(siteId,type);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  pwIds;
	}
	
	/**
	 * 根据网元Id 查询pwId条目数
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> queryLspIds(int siteId,int type) throws Exception {
		List<Integer> lspIds=null;
		try {
			lspIds = new ArrayList<Integer>();
			lspIds = this.mapper.queryLspIds(siteId,type);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  lspIds;
	}
}
