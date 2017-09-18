package com.nms.model.ptn.qos;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.dao.ptn.qos.QosMappingTemplateMapper;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class QosMappingTemplateService_MB extends ObjectService_Mybatis{
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private QosMappingTemplateMapper qosMappingTemplateMapper;

	
	public QosMappingTemplateMapper getQosMappingTemplateMapper() {
		return qosMappingTemplateMapper;
	}

	public void setQosMappingTemplateMapper(QosMappingTemplateMapper qosMappingTemplateMapper) {
		this.qosMappingTemplateMapper = qosMappingTemplateMapper;
	}
	
	/**
	 * 条件查询qos模板
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingAttr> queryByCondition(QosMappingAttr qosMappingAttr) throws Exception{
		List<QosMappingAttr> qosMappingAttrList = new ArrayList<QosMappingAttr>();
		qosMappingAttrList = qosMappingTemplateMapper.queryByCondition(qosMappingAttr);
		return qosMappingAttrList;
	}
	
	/**
	 * 条件查询qos模板
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingMode> refresh(QosMappingAttr condition) throws Exception{
		List<QosMappingAttr> qosMappingAttrList = new ArrayList<QosMappingAttr>();
		qosMappingAttrList = this.qosMappingTemplateMapper.refresh(condition);
		QosMappingMode qosMappingMode;
		List<QosMappingMode> qosMappingModeList = new ArrayList<QosMappingMode>();
		if(qosMappingAttrList!=null && qosMappingAttrList.size()>0){
			for(QosMappingAttr qosMappingAttr:qosMappingAttrList){
				qosMappingMode = new QosMappingMode();
				qosMappingMode.setName(qosMappingAttr.getName());
				qosMappingMode.setType(qosMappingAttr.getMappingType());
				qosMappingMode.setTypeName(QosTemplateTypeEnum.from(qosMappingAttr.getMappingType()).toString());
				qosMappingAttr.setId(0);
				qosMappingMode.setQosMappingAttrList(this.getExpInfoDate(qosMappingAttr));
				qosMappingModeList.add(qosMappingMode);
			}
		}
		return qosMappingModeList;
	}
	
	/**
	 * 核对数据
	 * @param qosMappingMode
	 * @return
	 * @throws Exception
	 */
	public List<QosMappingAttr> getExpInfoDate(QosMappingAttr qosMappingAttr) throws Exception{
		List<QosMappingAttr> qosMappingAttrList = new ArrayList<QosMappingAttr>();
		qosMappingAttrList = this.qosMappingTemplateMapper.getExpInfoDate(qosMappingAttr);
		return qosMappingAttrList;
	}
	
	/**
	 * 根据id批量删除
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public int delete(int groupid) throws Exception{
		int result=0;
		result=this.qosMappingTemplateMapper.delete(groupid);
		return	result;
	}
	
	/**
	 * 更新或新增QosMappingMode
	 * @param qosMappingModeList
	 * @return
	 * @throws Exception
	 */
	public int saveOrUpdate(List<QosMappingAttr> qosMappingAttrList)throws Exception{
		
		int groupid = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			int count = 0;
			List<QosMappingAttr> qosMappingAttrSel ;
			QosMappingAttr qosMappingAttrBusi = qosMappingAttrList.get(0);
			if(qosMappingAttrBusi.getGroupid()>0){
				groupid = qosMappingAttrBusi.getGroupid();
				for(QosMappingAttr qosMappingAttr : qosMappingAttrList){
					this.qosMappingTemplateMapper.update(qosMappingAttr);
				}
			}else{
				for(QosMappingAttr qosMappingAttr:qosMappingAttrList){
					qosMappingAttrSel = this.queryByCondition(qosMappingAttr);
					if(1==qosMappingAttrSel.size()){
						count++;
					}
				}
				if(count!=qosMappingAttrList.size()){
					groupid = this.qosMappingTemplateMapper.queryMaxGroupId()+1;
					for(QosMappingAttr qosMappingAttr : qosMappingAttrList){
						qosMappingAttr.setGroupid(groupid);
						this.qosMappingTemplateMapper.insert(qosMappingAttr);
					}
				}
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e, getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return groupid;
	}
	
	/**
	 * 名字是否重复
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public boolean nameRepetition(String afterName, String beforeName, int type) {
		int result = 0;
		try {
			result = this.qosMappingTemplateMapper.query_name(afterName, beforeName, type);
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
