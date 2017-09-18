package com.nms.model.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.protect.Protect_Corba;
import com.nms.db.dao.ptn.path.protect.ProtectCorbaMapper;
import com.nms.model.util.ObjectService_Mybatis;

/**
 * corba 保护组业务层
 * 
 * @author dzy
 * 
 */
public class ProtectServiceCorba_MB extends ObjectService_Mybatis{

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	private ProtectCorbaMapper protectCorbaMapper;

	
	/**
	 * 通过条件查询
	 * @param mspProtect 条件对象
	 * @return
	 * @throws Exception
	 */
	public List<Protect_Corba> queryProtectByCondition(Protect_Corba protect_Corba) throws Exception {
		return  this.protectCorbaMapper.queryProtectByCondition(protect_Corba);
	}

	public ProtectCorbaMapper getProtectCorbaMapper() {
		return protectCorbaMapper;
	}

	public void setProtectCorbaMapper(ProtectCorbaMapper protectCorbaMapper) {
		this.protectCorbaMapper = protectCorbaMapper;
	}
	
}
