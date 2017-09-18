package com.nms.db.dao.equipment.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.PortStmTimeslot;


public interface PortStmTimeslotMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PortStmTimeslot record);

    PortStmTimeslot selectByPrimaryKey(Integer id);

    public int update(PortStmTimeslot record);
    
    public int insert(@Param("portStm")PortStmTimeslot portStm);

	List<PortStmTimeslot> quertyByPortId(int portId);
	
	public List<PortStmTimeslot> quertyBySite(@Param("siteId")int siteId);

	void setUsed(int getaAcId, int isused);

	public void deleteBySite(@Param("siteId")int siteId);
	
	public int updateStatus(@Param("siteId")int siteId,@Param("status")int status);
	
	public List<PortStmTimeslot> selectBySiteIdAndNumberAndName(@Param("siteId")int siteId,@Param("portId")int portId,@Param("name")String name);
	
	public List<PortStmTimeslot> selectBySiteIdAndPortIdAndName(@Param("siteId")int siteId,@Param("portId")int portId,@Param("name")String name);
}