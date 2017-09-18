package com.nms.model.system;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.system.UnLoading;
import com.nms.db.dao.ptn.ecn.OSPFinfo_whMapper;
import com.nms.db.dao.ptn.qos.QosMappingTemplateMapper;
import com.nms.db.dao.system.UnloadMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class UnloadService_MB extends ObjectService_Mybatis {
    private UnloadMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;		
	}
	
	public UnloadMapper getMapper() {
		return mapper;
	}

	public void setMapper(UnloadMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 查询全部code
	 * @return code 集合
	 * @throws Exception
	 */
	public List<UnLoading> selectAll() throws Exception {
		List<UnLoading> unLoadList = null;
		try {
			unLoadList = this.mapper.selectAll();			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return unLoadList;
	}
	

	
	/**
	 * 新增或修改code对象，通过code.getId()来判断是修改还是新增
	 * 
	 * @param code
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public int update(UnLoading unload) throws Exception {

		if (unload == null) {
			throw new Exception("code is null");
		}

		int result = 0;
		try {			
			result = this.mapper.update(unload);			
           this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	

}
