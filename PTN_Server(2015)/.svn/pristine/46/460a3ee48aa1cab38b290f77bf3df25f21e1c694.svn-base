package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ARPInfo;
import com.nms.db.dao.ptn.ARPMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ARPInfoService_MB extends ObjectService_Mybatis {
	private ARPMapper mapper = null;
		
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(ARPMapper mapper) {
		this.mapper = mapper;
	}

	public List<ARPInfo> queryBySiteId(int siteId) {
		List<ARPInfo> arpList = new ArrayList<ARPInfo>();
		try {
			arpList = this.mapper.queryBySiteId(siteId);
			if(arpList == null){
				return new ArrayList<ARPInfo>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return arpList;
	}

	public void insert(ARPInfo arpInfo) {
		this.mapper.insert(arpInfo);
		this.sqlSession.commit();
	}

	public void delete(List<ARPInfo> list) {
		for (ARPInfo arpInfo : list) {
			this.mapper.delete(arpInfo.getId());
		}
		this.sqlSession.commit();
	}

	public void update(ARPInfo arpInfo) {
		this.mapper.update(arpInfo);
		this.sqlSession.commit();
	}

	public ARPInfo queryById(int id) {
		return this.mapper.queryById(id);
	}

	public void deleteBySiteId(int siteId) {
		this.mapper.deleteBySiteId(siteId);
	}

	public ARPInfo select_synchro(int siteId, int pwProtectId) {
		return this.mapper.select_synchro(siteId, pwProtectId);
	}
}
