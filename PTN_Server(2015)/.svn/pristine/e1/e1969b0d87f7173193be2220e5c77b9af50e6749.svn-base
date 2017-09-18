package com.nms.db.dao.equipment.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.E1Info;


public interface E1InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(E1Info record);

    E1Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(E1Info record);

    int updateByPrimaryKey(@Param("e1Info")E1Info e1Info);
    
    public int insert(@Param("e1Info")E1Info record);

	List<E1Info> queryByCondition(E1Info e1Info);
	
	public List<E1Info> queryBySiteId(@Param("siteId")int siteId);
	
	public void deleteBySite(@Param("siteId")int siteId);
	
	public void delete(E1Info e1Info);
}