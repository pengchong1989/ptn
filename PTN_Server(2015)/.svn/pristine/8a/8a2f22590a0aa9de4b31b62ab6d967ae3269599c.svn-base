package com.nms.db.dao.ptn.qos;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.qos.QosMappingAttr;

public interface QosMappingTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(QosMappingAttr record);

    int insertSelective(QosMappingAttr record);

    QosMappingAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QosMappingAttr record);

    int updateByPrimaryKey(QosMappingAttr record);

	List<QosMappingAttr> queryByCondition(QosMappingAttr qosMappingAttr);
	
	/**
	 * 通过条件查询
	 * 
	 * @param qosMappingMode
	 *            查询条件
	 * @param connection
	 *            数据库连接
	 * @return List<ElineInfo>集合
	 * @throws Exception
	 */
	public List<QosMappingAttr> refresh(QosMappingAttr condition);
	
	/**
	 * 核对数据
	 * 
	 * @param qosMappingMode
	 *            查询条件
	 * @param connection
	 *            数据库连接
	 * @return List<ElineInfo>集合
	 * @throws Exception
	 */
	public List<QosMappingAttr> getExpInfoDate(QosMappingAttr qosMappingAttr);
	
	public int delete(@Param("groupId")Integer groupId);
	
	public int update(QosMappingAttr record);
	
	/**
	 * 获取最大的groupId 新建用
	 * 
	 * @param connection
	 *            数据库连接
	 * @return 最大的groupid
	 * @throws Exception
	 */
	public int queryMaxGroupId();
	
	public int query_name(@Param("afterName")String afterName, @Param("beforeName")String beforeName,@Param("type")int type);
}