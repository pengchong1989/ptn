package com.nms.model.ptn.path;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.StaticUnicastInfo;
import com.nms.db.dao.ptn.path.StaticUnicastInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class SingleSpreadService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private StaticUnicastInfoMapper mapper;

	public StaticUnicastInfoMapper getStaticUnicastInfoMapper() {
		return mapper;
	}

	public void setStaticUnicastInfoMapper(StaticUnicastInfoMapper staticUnicastInfoMapper) {
		this.mapper = staticUnicastInfoMapper;
	}

	public List<StaticUnicastInfo> selectByStaticUniInfo(StaticUnicastInfo info) {
		List<StaticUnicastInfo> infoList = null;
		try {
			infoList = this.mapper.selectByStaticUniInfo(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return infoList;
	}
	public String selectNameByXcId(int xcid, int siteid) throws Exception{
		return this.mapper.selectNameByXcId(xcid, siteid).get(0);
	}
	
	/**
	 * 根据网元Id 查询 单播条目数，以此来分配SUID
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<Integer> querySuId(int siteId) throws Exception {
		List<Integer> suIds = null;
		try {
			suIds = new ArrayList<Integer>();
			suIds = this.mapper.querySuId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  suIds;
	}
	
	/**
	 * 根据网元Id 查询 
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<StaticUnicastInfo> query(int siteId) throws Exception {
		List<StaticUnicastInfo> infoList = null;
		try {
			infoList = new ArrayList<StaticUnicastInfo>();
			infoList = this.mapper.queryByCondition(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  infoList;
	}
}
