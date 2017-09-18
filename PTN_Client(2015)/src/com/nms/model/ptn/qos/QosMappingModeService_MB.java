package com.nms.model.ptn.qos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.dao.ptn.qos.QosMappingModeMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;

public class QosMappingModeService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private QosMappingModeMapper mapper;

	public QosMappingModeMapper getQosMappingModeMapper() {
		return mapper;
	}

	public void setQosMappingModeMapper(QosMappingModeMapper qosMappingModeMapper) {
		this.mapper = qosMappingModeMapper;
	}
	
	/**
	 * 条件查询qos模板
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingMode> queryByCondition(QosMappingMode qosMappingMode) throws Exception{
		List<QosMappingMode> mappingModes = null;
		List<QosMappingAttr> mappingAttrs = null;
		QosMappingAttr qosMappingAttr = new QosMappingAttr();
		QosMappingModeAttrService_MB mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
		mappingModes = this.mapper.queryByCondition(qosMappingMode);
		for(QosMappingMode mappingMode :mappingModes){
			qosMappingAttr.setPhbId(mappingMode.getId());
			qosMappingAttr.setSiteId(mappingMode.getSiteId());
			mappingAttrs = mappingModeAttrService.queryByCondition(qosMappingAttr);
			mappingMode.setQosMappingAttrList(mappingAttrs);
		}
		return mappingModes;
	}
}
