package com.nms.db.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.UdaAttr;



public interface UdaAttrMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(UdaAttr udaAttr);

    int insertSelective(UdaAttr record);

    UdaAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UdaAttr record);

    int updateByPrimaryKey(UdaAttr record);
    
    /**
     * 根据条件查询udaattr对象
     * @param udaattrCondition  查询条件
     * @param connection  数据库连接
     * @return  UdaAttr集合
     * @throws Exception
     */
	public List<UdaAttr> queryByCondition(UdaAttr udaattrCondition);
	
	public int update(UdaAttr udaAttr);
	
	public int delete(@Param("id")Integer id);
}