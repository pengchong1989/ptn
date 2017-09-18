package com.nms.model.system.code;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.code.Code;
import com.nms.db.dao.system.code.CodeMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;

public class CodeService_MB extends ObjectService_Mybatis {
    private CodeMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public CodeMapper getMapper() {
		return mapper;
	}

	public void setMapper(CodeMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 查询全部code
	 * @return code 集合
	 * @throws Exception
	 */
	public List<Code> selectAll() throws Exception {
		List<Code> codeList = null;
		try {
			Code code = new Code();
			codeList = this.mapper.queryByCondition(code);
			if(codeList != null){
				for (Code c : codeList) {
					if ("en_US".equals(ResourceUtil.language)) {
						c.setCodeName(c.getCodeENName());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return codeList;
	}
	
	/**
	 * 根据条件查询code
	 * @param code 查询条件
	 * @return code集合
	 * @throws Exception
	 */
	public List<Code> selectByCode(Code code) throws Exception {
		List<Code> codeList = null;

		try {
			codeList = new ArrayList<Code>();
			codeList = this.mapper.queryByCode(code);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return codeList;
	}
	
	/**
	 * 新增或修改code对象，通过code.getId()来判断是修改还是新增
	 * 
	 * @param code
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public int saveOrUpdate(Code code) throws Exception {

		if (code == null) {
			throw new Exception("code is null");
		}

		int result = 0;
		try {

			if (code.getId() == 0) {
				result = this.mapper.insert(code);
			} else {
				result = this.mapper.update(code);
			}
           this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 通过主键删除code对象
	 * 
	 * @param id
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {

		int result = 0;

		try {
			result = this.mapper.delete(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;

	}
	
	/**
	 * 查询全部code
	 * 
	 * @return code 集合
	 * @throws Exception
	 */
	public List<Code> select() throws Exception {
		List<Code> codeList = null;

		try {
			Code code = new Code();
			codeList = this.mapper.queryByCondition(code);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return codeList;
	}
}
