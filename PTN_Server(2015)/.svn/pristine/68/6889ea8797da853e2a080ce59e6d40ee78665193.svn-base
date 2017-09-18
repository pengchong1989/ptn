package com.nms.model.path;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.path.SetNameRule;
import com.nms.db.dao.path.SetNameRuleMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class NameRuleService_MB extends ObjectService_Mybatis {
	private SetNameRuleMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(SetNameRuleMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<SetNameRule> select(SetNameRule namerule) throws Exception {
		List<SetNameRule> nameruleList = null;
		try {
			nameruleList = new ArrayList<SetNameRule>();
			nameruleList = this.mapper.queryByidCondition(namerule);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return nameruleList;
	}
	
	public int saveOrUpdate(SetNameRule nameRule) throws Exception {
		int rule = 0;
		if (nameRule == null) {
			throw new Exception("nameRule is null");
		}
		if (nameRule.getId() == 0) {
			rule = this.mapper.insert(nameRule);

		} else {
			rule = this.mapper.update(nameRule);
		}
         this.sqlSession.commit();
		return rule;
	}
	
	public void delete(int id) throws Exception {
		try {
			this.mapper.delete(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	/**
	 * 
	 * @param afterName 修改前名称
	 * @param beforeName 修改后名称
	 * @param siteId  网元ID
	 * @return
	 */
	public boolean nameRepetition(String afterName, String beforeName, int siteId) {
		int result = 0;
		try {
			result = this.mapper.queryname(afterName, beforeName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(0== result){
			return false;
		}else{
			return true;
		}
	}
}
