package com.nms.db.dao.equipment.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.PortStm;


public interface PortStmMapper {
    int deleteByPrimaryKey(Integer id);

    PortStm selectByPrimaryKey(Integer id);
  
    public int insert(@Param("portStm")PortStm portStm);

	List<PortStm> quertyBySite(int siteId);
	
	public List<PortStm> queryByCondition(PortStm condition);
	
	public void deleteBySite(@Param("siteId")int siteId);
	
	public int update(PortStm record);
	
	public List<PortStm> queryBySiteIdAndPortId(@Param("siteId")int siteId, @Param("portId")int portId);
}