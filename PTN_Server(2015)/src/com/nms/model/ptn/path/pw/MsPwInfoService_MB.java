package com.nms.model.ptn.path.pw;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.dao.ptn.path.pw.MsPwInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class MsPwInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private MsPwInfoMapper mapper;

	public MsPwInfoMapper getMsPwInfoMapper() {
		return mapper;
	}

	public void setMsPwInfoMapper(MsPwInfoMapper MsPwInfoMapper) {
		this.mapper = MsPwInfoMapper;
	}

	public List<MsPwInfo> selectBySiteId(int siteId) {
		List<MsPwInfo> msPwInfos = new ArrayList<MsPwInfo>();
		try {
			MsPwInfo mspwinfoCondition = new MsPwInfo();
			mspwinfoCondition.setSiteId(siteId);
			msPwInfos = this.mapper.queryByCondition(mspwinfoCondition);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return msPwInfos;
	}

	public List<MsPwInfo> select(MsPwInfo msPwInfo) {
		List<MsPwInfo> msPwInfos = null;
		try {
			msPwInfos = new ArrayList<MsPwInfo>();
			msPwInfos = this.mapper.queryByCondition(msPwInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return msPwInfos;
	}
	
	public int insert(MsPwInfo msPwInfo){
		int result = 0;
		try {
			result = mapper.insert(msPwInfo);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询
	 * @param msPwInfo
	 * @return
	 */
	public void update(MsPwInfo msPwInfo){
		try {
			mapper.update(msPwInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 查询
	 * @param msPwInfo
	 * @return
	 */
	public List<MsPwInfo> selectByTunnelIds(List<Integer> tunnelIds){
		List<MsPwInfo> msPwInfos = null;
		try {
			msPwInfos = mapper.queryByTunnelIds(tunnelIds);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return msPwInfos;
	}
}
