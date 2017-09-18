package com.nms.model.ptn.qos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.dao.ptn.qos.QosMappingAttrMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class QosMappingModeAttrService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private QosMappingAttrMapper mapper;

	public QosMappingAttrMapper getQosMappingAttrMapper() {
		return mapper;
	}

	public void setQosMappingAttrMapper(QosMappingAttrMapper qosMappingAttrMapper) {
		this.mapper = qosMappingAttrMapper;
	}

	/**
	 * 更新或新增QosMappingMode
	 * @param qosMappingModeList
	 * @return
	 * @throws Exception
	 */
	public int saveOrUpdate(List<QosMappingAttr> qosMappingAttrList) throws Exception{
		int result = 0;
		try {
			for(QosMappingAttr qosMappingAttr : qosMappingAttrList){
				if(qosMappingAttr.getId()>0){
					this.mapper.update(qosMappingAttr);					
				}else{
					result = this.mapper.insert(qosMappingAttr);					
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	/**
	 * 条件查询qos模板
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingAttr> queryByCondition(QosMappingAttr qosMappingAttr) throws Exception{
		return this.mapper.queryByCondition(qosMappingAttr);
	}
	
	/**
	 * 条件查询qos模板
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingAttr> isExit(QosMappingAttr qosMappingAttr) throws Exception{
		return this.mapper.queryByCondition(qosMappingAttr);
	}
}
