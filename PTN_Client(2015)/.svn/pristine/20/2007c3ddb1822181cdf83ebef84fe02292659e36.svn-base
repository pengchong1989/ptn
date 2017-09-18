package com.nms.model.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.protect.ProtectRorateInfo;
import com.nms.db.dao.ptn.path.protect.ProtectRorateInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ProtectRorateInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private ProtectRorateInfoMapper protectRorateInfoMapper;

	public ProtectRorateInfoMapper getProtectRorateInfoMapper() {
		return protectRorateInfoMapper;
	}

	public void setProtectRorateInfoMapper(ProtectRorateInfoMapper protectRorateInfoMapper) {
		this.protectRorateInfoMapper = protectRorateInfoMapper;
	}

	public List<ProtectRorateInfo> queryByProtectRorateInfo(ProtectRorateInfo protectRorateInfo)throws Exception{
		List<ProtectRorateInfo> protectInfoList = null;
		try {
			protectInfoList = protectRorateInfoMapper.queryByCondition(protectRorateInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return protectInfoList;
	}
	
	
}
