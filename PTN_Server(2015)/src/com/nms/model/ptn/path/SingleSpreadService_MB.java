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
	
	/**
	 * 修改
	 * @param info
	 * @throws Exception
	 */
	public void update(StaticUnicastInfo info) throws Exception {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.update(info);
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
	
	/**
	 * 添加
	 * @return 
	 */
	public int insert(StaticUnicastInfo info) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}
		int result = 0;

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.insert(info);
			result = info.getId();
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
	
	public void delete(List<StaticUnicastInfo> uniInfoList) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(StaticUnicastInfo staticUnicastInfo:uniInfoList){
				this.mapper.delete(staticUnicastInfo.getId());
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
	}
	
	public List<StaticUnicastInfo> querybyCondition(StaticUnicastInfo info) throws Exception {
		List<StaticUnicastInfo> infoList = null;
		try {
			infoList = this.mapper.queryByConditions(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  infoList;
	}
	
	public String selectNameByXcId(int xcid, int siteid) throws Exception{
		return this.mapper.selectNameByXcId(xcid, siteid).get(0);
	}
}
