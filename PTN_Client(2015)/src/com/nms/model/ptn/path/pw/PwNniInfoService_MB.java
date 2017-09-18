package com.nms.model.ptn.path.pw;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.dao.ptn.path.pw.PwNniInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class PwNniInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private PwNniInfoMapper mapper;

	public PwNniInfoMapper getPwNniInfoMapper() {
		return mapper;
	}

	public void setPwNniInfoMapper(PwNniInfoMapper PwNniInfoMapper) {
		this.mapper = PwNniInfoMapper;
	}

	public List<PwNniInfo> select(PwNniInfo pwinfo) {
		List<PwNniInfo> pwinfoList = null;
		try {
			pwinfoList = this.mapper.queryByCondition(pwinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pwinfoList;
	}

	public int delete(int id) {
		int result = 0;
		try {
			result = this.mapper.delete(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	public int saveOrUpdate(PwNniInfo pwinfo) throws Exception {
		if (pwinfo == null) {
			throw new Exception("pwinfo is null");
		}
		int result = 0;
		try {
			if (pwinfo.getId() == 0) {		
				result = this.mapper.insert(pwinfo);			
			} else {
				result = this.mapper.update(pwinfo);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

}
