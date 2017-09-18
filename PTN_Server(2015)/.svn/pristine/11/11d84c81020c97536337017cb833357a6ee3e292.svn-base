package com.nms.model.ptn.qos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.qos.QosTemplateInfo;
import com.nms.db.dao.ptn.qos.QosTemplateInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class QosTemplateService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private QosTemplateInfoMapper mapper;

	public QosTemplateInfoMapper getQosTemplateInfoMapper() {
		return mapper;
	}

	public void setQosTemplateInfoMapper(QosTemplateInfoMapper qosTemplateInfoMapper) {
		this.mapper = qosTemplateInfoMapper;
	}

	/**
	 * 保存或更新模板
	 */
	public int saveOrUpdate(List<QosTemplateInfo> qosTemplateInfoList)
			throws Exception {

		if (qosTemplateInfoList == null || qosTemplateInfoList.size() == 0) {
			throw new Exception("qosTemplateInfoList is null");
		}
		String templateName = null;
		String qosType = null;
		int result = 0;
		List<Integer> idList = new ArrayList<Integer>();

		try {
			templateName = qosTemplateInfoList.get(0).getTemplateName();
			qosType = qosTemplateInfoList.get(0).getQosType();
			for (QosTemplateInfo qosTemplate : qosTemplateInfoList) {
				if(qosTemplate.getId() != 0)
					idList.add(qosTemplate.getId());
			}
			if (!checkTemplateName(templateName, "")) {
				if (!checkIdListExsits(idList)) {
					for (QosTemplateInfo qosTemplate : qosTemplateInfoList) {
						this.mapper.insert(qosTemplate);
						result ++ ;
					}
				} else {
					for (QosTemplateInfo qosTemplate : qosTemplateInfoList) {
						this.mapper.update(qosTemplate);
						result ++ ;
					}
				}
			} else {
				QosTemplateInfo condition = new QosTemplateInfo();
				condition.setTemplateName(templateName);
				condition.setQosType(qosType);
				this.mapper.delete(condition);
				for (QosTemplateInfo qosTemplate : qosTemplateInfoList) {
					this.mapper.update(qosTemplate);
					result ++ ;
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 根据模板名和类型删除
	 */
	public int delete(String templateName, String qosType) throws Exception {
		int result = 0;
		try {
			QosTemplateInfo condition = new QosTemplateInfo();
			condition.setTemplateName(templateName);
			condition.setQosType(qosType);
			result = this.mapper.delete(condition);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 查询所有模板
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List> selectAll() throws Exception {
		Map<String, List> templateInfoMap = new HashMap<String, List>();
		List<QosTemplateInfo> templateInfoList = new ArrayList<QosTemplateInfo>();
		List<QosTemplateInfo> templateInfoAllList = new ArrayList<QosTemplateInfo>();
		try {
			templateInfoAllList = this.mapper.select();
			for (QosTemplateInfo qosTemp : templateInfoAllList) {
				String tempName = qosTemp.getTemplateName();
				if (templateInfoMap.get(tempName) == null) {
					templateInfoList = new ArrayList<QosTemplateInfo>();
					for (QosTemplateInfo qos : templateInfoAllList) {
						if (qos.getTemplateName() == tempName) {
							templateInfoList.add(qos);
						}
					}
					templateInfoMap.put(tempName, templateInfoList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return templateInfoMap;
	}

	/**
	 * 根据模板名和类型查询模板
	 */
	public List<QosTemplateInfo> queryByCondition(String templateName) throws Exception {
		List<QosTemplateInfo> templateInfoList = null;
		try {
			templateInfoList = this.mapper.queryByCondition(templateName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return templateInfoList;
	}

	/** 判断id是否存在
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	private boolean checkIdListExsits(List<Integer> idList) throws Exception {
		List<QosTemplateInfo> list = null;
		try {
			if( idList != null && idList.size() != 0)
			list = this.mapper.queryByIdList(idList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断模板名是否有冲突
	 */
	public boolean checkTemplateName(String templateName, String qosType) throws Exception {
		List<QosTemplateInfo> list = null;
		try {
			list = this.mapper.queryByCondition(templateName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (list != null && list.size() != 0) {
			return true;
		}
		return false;
	}
}
