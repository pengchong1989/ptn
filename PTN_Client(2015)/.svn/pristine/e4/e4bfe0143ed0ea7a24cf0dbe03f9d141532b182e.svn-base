package com.nms.db.dao.equipment.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.PortStmTimeslot;


public interface PortStmTimeslotMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PortStmTimeslot record);

    PortStmTimeslot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortStmTimeslot record);

    int updateByPrimaryKey(PortStmTimeslot record);
    
    public int insert(@Param("portStm")PortStmTimeslot portStm);

	List<PortStmTimeslot> quertyByPortId(int portId);
	
	public List<PortStmTimeslot> quertyBySite(@Param("siteId")int siteId);

	void setUsed(int getaAcId, int isused);
}