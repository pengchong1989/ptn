package com.nms.db.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.UdaGroup;



public interface UdaGroupMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(UdaGroup udaGroup);

    int insertSelective(UdaGroup record);

    UdaGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdaGroup record);

    int updateByPrimaryKey(UdaGroup record);
    
    /**
     * 根据条件查询UdaGroup
     * @param udagroupCondition 查询条件
     * @param connection 数据库连接
     * @return UdaGroup集合
     * @throws Exception
     */
 	public List<UdaGroup> queryByCondition(UdaGroup udagroupCondition);
 	
 	public int update(UdaGroup udaGroup);
 	
 	public int delete(@Param("id")Integer id);
}