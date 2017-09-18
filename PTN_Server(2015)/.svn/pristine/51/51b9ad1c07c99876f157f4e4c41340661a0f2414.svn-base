package com.nms.db.dao.ptn.qos;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.qos.QosTemplateInfo;

public interface QosTemplateInfoMapper {
	/**
	 * 根据模板名称查询
	 * @param templateName
	 * @return
	 */
	public List<QosTemplateInfo> queryByCondition(String templateName);

	/**
	 * 根据主键id查询
	 * @param idList
	 * @return
	 */
	public List<QosTemplateInfo> queryByIdList(@Param("idList")List<Integer> idList);

	public List<QosTemplateInfo> select();

	/**
	 * 根据名称和类型删除
	 * @param templateName
	 * @param qosType
	 * @return
	 */
	public int delete(QosTemplateInfo condition);

	public void update(QosTemplateInfo qosTemplate);

	public void insert(QosTemplateInfo qosTemplate);
}