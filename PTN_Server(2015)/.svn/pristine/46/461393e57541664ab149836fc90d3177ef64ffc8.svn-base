package com.nms.model.ptn.qos;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.qos.QosMappingAttrMapper;
import com.nms.db.dao.ptn.qos.QosMappingModeMapper;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

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
		QosMappingModeAttrService_MB mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService, this.sqlSession);
		mappingModes = this.mapper.queryByCondition(qosMappingMode);
		for(QosMappingMode mappingMode :mappingModes){
			qosMappingAttr.setPhbId(mappingMode.getId());
			qosMappingAttr.setSiteId(mappingMode.getSiteId());
			mappingAttrs = mappingModeAttrService.queryByCondition(qosMappingAttr);
			mappingMode.setQosMappingAttrList(mappingAttrs);
		}
		return mappingModes;
	}
	
	/**
	 * 更新或新增QosMappingMode
	 * @param qosMappingModeList
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public int saveForCX(QosMappingMode mappingMode) throws SQLException{
		int result = 0;
		int type = 0;
		int direction = 0;
		try {
			type = mappingMode.getQosMappingAttrList().get(0).getModel();
			direction = mappingMode.getQosMappingAttrList().get(0).getDirection();
			//新建
			BusinessidMapper businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
			Businessid businessid = null;
			try {
				//llsp输入
				if(UiUtil.getCodeByValue("EXPTYPE", "0").getId()==type&&UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "llsppmappinginput").get(0);
				}
				//llsp输出
				if(UiUtil.getCodeByValue("EXPTYPE", "0").getId()==type&&UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "llsppmappingoutput").get(0);
				}
				//elsp输入
				if(UiUtil.getCodeByValue("EXPTYPE", "1").getId()==type&&UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "elsppmappinginput").get(0);
				}
				//elsp输出
				if(UiUtil.getCodeByValue("EXPTYPE", "1").getId()==type&&UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "elsppmappingoutput").get(0);
				}
				//vlanpirtocolormapping
				if(QosTemplateTypeEnum.VLANPRI_COLOR.getValue()==mappingMode.getType()){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "vlanpirtocolormapping").get(0);
				}
				//costovlanpirmapping
				if(QosTemplateTypeEnum.COS_VLANPRI.getValue()==mappingMode.getType()){
					//查询businessid
					businessid = businessidMapper.queryBySiteId(mappingMode.getSiteId(), "costovlanpirmapping").get(0);
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			if (businessid == null) {
				throw new BusinessIdException("BusinessId is null");
			}
			// 修改此id为已用状态
			businessid.setIdStatus(1);
			businessidMapper.update(businessid);
			
			mappingMode.setBusinessId(businessid.getIdValue());
			result = this.mapper.insert(mappingMode);
			for(QosMappingAttr qosMappingAttr:mappingMode.getQosMappingAttrList()){
				qosMappingAttr.setPhbId(mappingMode.getId());
			}
			QosMappingModeAttrService_MB mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService, this.sqlSession);
			mappingModeAttrService.saveOrUpdate(mappingMode.getQosMappingAttrList());
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	
	public void restoreData(QosMappingMode qosMappingMode) throws Exception{
		try {
			QosMappingAttrMapper qosMappingAttrMapper = this.sqlSession.getMapper(QosMappingAttrMapper.class);
			BusinessidMapper businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
			//删除模板详细数据
			qosMappingAttrMapper.deleteByQosModelId(qosMappingMode.getId());
			//删除模板
			this.mapper.delete(qosMappingMode.getId());
			// 修改此id为未用状态
			Businessid bId = new Businessid();
			bId.setId(qosMappingMode.getBusinessId());
			bId.setIdStatus(0);
			businessidMapper.update(bId);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	/**
	 * 根据条件删除
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public int deleteForSiteId(int siteId) throws Exception{
		int result = this.mapper.deleteBySiteId(siteId);
		this.sqlSession.commit();
		return result;
	}
	
	/**
	 * 更新或新增QosMappingMode
	 * @param qosMappingModeList
	 * @return
	 * @throws Exception
	 */
	public int save(QosMappingMode mappingMode) throws Exception{
		int result = this.mapper.insert(mappingMode);
		this.sqlSession.commit();
		return result;
	}
	
	/**
	 * 更新或新增QosMappingMode
	 * @param qosMappingModeList
	 * @return
	 * @throws Exception
	 */
	public int saveOrUpdate(List<QosMappingMode> qosMappingModeList) throws Exception{
		int result = 0;
		try {
			QosMappingModeAttrService_MB mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService, this.sqlSession);
			for(QosMappingMode mappingMode : qosMappingModeList){
				if(mappingMode.getId()>0){
					this.mapper.update(mappingMode);
					List<QosMappingAttr> list = mappingMode.getQosMappingAttrList();
					mappingModeAttrService.saveOrUpdate(list);
				}else{
					result = this.mapper.insert(mappingMode);
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	
	public int Update(List<QosMappingMode> qosMappingModeList) throws Exception{
		int result = 0;
		try {
			QosMappingModeAttrService_MB mappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService, this.sqlSession);
			for(QosMappingMode mappingMode : qosMappingModeList){
				mappingModeAttrService.saveOrUpdate(mappingMode.getQosMappingAttrList());
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
}
