package com.nms.model.ptn.path;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;
import com.nms.db.dao.ptn.path.GroupSpreadInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class GroupSpreadService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private GroupSpreadInfoMapper GroupSpreadInfoMapper;

	public GroupSpreadInfoMapper getGroupSpreadInfoMapper() {
		return GroupSpreadInfoMapper;
	}

	public void setGroupSpreadInfoMapper(GroupSpreadInfoMapper GroupSpreadInfoMapper) {
		this.GroupSpreadInfoMapper = GroupSpreadInfoMapper;
	}
	
	/**
	 * 根据网元Id 查询 
	 * @param siteId
	 * @return FrequencyInfo List
	 * @throws Exception
	 */
	public List<GroupSpreadInfo> query(GroupSpreadInfo groupInfo) throws Exception {
		List<GroupSpreadInfo> infoList = new ArrayList<GroupSpreadInfo>();
		try {
			infoList = this.GroupSpreadInfoMapper.queryByCondition(groupInfo);
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
	public void update(GroupSpreadInfo info) throws Exception {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.GroupSpreadInfoMapper.update(info);
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
	
	
	public int insert(GroupSpreadInfo info) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}

		int result = 0;

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			result = this.GroupSpreadInfoMapper.insert(info);
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
	
	public void delete(List<GroupSpreadInfo> infoList) throws Exception {

		if (this.sqlSession.getConnection() == null) {
			System.out.println("connection is null");
		}

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(GroupSpreadInfo info :infoList){
				this.GroupSpreadInfoMapper.delete(info.getId());
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
}
