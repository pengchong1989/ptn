package com.nms.model.system;


import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.WorkIps;
import com.nms.db.dao.system.WorkIpsMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class WorkIpsService_MB extends ObjectService_Mybatis {
	private WorkIpsMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public WorkIpsMapper getMapper() {
		return mapper;
	}

	public void setMapper(WorkIpsMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 新增或修改workIps对象，通过workIps()来判断是修改还是新增
	 * 
	 * @param workIps
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public int saveOrUpdate(WorkIps workIps) throws Exception {

		if (workIps == null) {
			throw new Exception("workIps is null");
		}

		int result = 0;
		try {

			if (workIps.getId() == 0) {
				result = this.mapper.insert(workIps);
			} else {
				result = this.mapper.update(workIps);
			}
            this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 通过主键删除workips对象
	 * 
	 * @param id
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {

		int result = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			result = this.mapper.delete(id);
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

}
