package com.nms.model.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.dao.ptn.path.protect.MspProtectMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class MspProtectService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private MspProtectMapper MspProtectMapper;

	public MspProtectMapper getMspProtectMapper() {
		return MspProtectMapper;
	}

	public void setMspProtectMapper(MspProtectMapper MspProtectMapper) {
		this.MspProtectMapper = MspProtectMapper;
	}
	

	public List<MspProtect> selectBySite(int siteId) {
		// 设置查询条件
		MspProtect mspProtect = new MspProtect();
		mspProtect.setSiteId(siteId);
		return this.MspProtectMapper.query(mspProtect);
	}
}
