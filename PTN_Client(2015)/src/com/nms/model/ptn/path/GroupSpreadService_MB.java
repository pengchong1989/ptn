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
}
