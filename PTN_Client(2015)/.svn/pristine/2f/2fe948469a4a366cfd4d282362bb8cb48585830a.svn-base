package com.nms.model.system.code;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.dao.system.code.CodeGroupMapper;
import com.nms.db.dao.system.code.CodeMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class CodeGroupService_MB extends ObjectService_Mybatis{

    private CodeGroupMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public CodeGroupMapper getMapper() {
		return mapper;
	}

	public void setMapper(CodeGroupMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<CodeGroup> select(CodeGroup codegroup) throws Exception{
		List<CodeGroup> codegroupList = new ArrayList<CodeGroup>();
		codegroupList=this.mapper.queryByCondition(codegroup);
		CodeMapper codeMapper = null; 
		if (null != codegroupList && codegroupList.size() != 0) {
			for (int i = 0; i < codegroupList.size(); i++) {
				Code code = new Code();
				codeMapper=this.sqlSession.getMapper(CodeMapper.class);
				code.setId(codegroupList.get(i).getId());
				List<Code> codeList =new ArrayList<Code>();
				codeList= codeMapper.queryByCondition(code);
				codegroupList.get(i).setCodeList(codeList);
			}
		}
		return codegroupList;
	}
	
	public List<CodeGroup> select() throws Exception {
		List<CodeGroup> codegroupList = new ArrayList<CodeGroup>();
		codegroupList=this.mapper.queryByConditionall();
		CodeMapper codeMapper = null; 
		if (null != codegroupList && codegroupList.size() != 0) {
			for (int i = 0; i < codegroupList.size(); i++) {
				Code code = new Code();
				codeMapper=this.sqlSession.getMapper(CodeMapper.class);
				code.setCodeGroupId(codegroupList.get(i).getId());
				List<Code> codeList =new ArrayList<Code>();
				codeList = codeMapper.queryByCondition(code);
				codegroupList.get(i).setCodeList(codeList);
			}
		}
		return codegroupList;
	}
	
	public int saveOrUpdate(CodeGroup codegroup) throws Exception {

		if (codegroup == null) {
			throw new Exception("codegroup is null");
		}
		int result = 0;
		try {
			if (codegroup.getId() == 0) {
				result = this.mapper.insert(codegroup);
			} else {
				result = this.mapper.update(codegroup);
			}
          this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	public List<CodeGroup> selectCodeByCodeGroup() throws Exception {
		List<CodeGroup> codegroupList = new ArrayList<CodeGroup>();
		codegroupList=this.mapper.queryByConditionall();
		if (null != codegroupList && codegroupList.size() != 0) {
			for (int i = 0; i < codegroupList.size(); i++) {
				Code code = new Code();
				CodeMapper codeMapper = this.sqlSession.getMapper(CodeMapper.class);				
				code.setCodeGroupId(codegroupList.get(i).getId());				
				List<Code> codeList = new ArrayList<Code>();
				codeList=codeMapper.queryByCode(code);
				codegroupList.get(i).setCodeList(codeList);
			}
		}
		return codegroupList;
	}
	
	public void delete(CodeGroup codegroup) throws Exception {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.delete(codegroup.getId());
			
			Code code=new Code();
			code.setCodeGroupId(codegroup.getId());
			CodeMapper codeMapper = this.sqlSession.getMapper(CodeMapper.class);
			codeMapper.delete(code.getId());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
}
