package com.nms.db.dao.equipment.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.Port2LayerAttr;


public interface Port2LayerAttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Port2LayerAttr record);

    Port2LayerAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Port2LayerAttr record);

    int updateByPrimaryKey(Port2LayerAttr record);
    
    public int insert(@Param("port2Layer")Port2LayerAttr port2LayerAttr);
    
    public int update(@Param("port2Layer")Port2LayerAttr port2LayerAttr);

	List<Port2LayerAttr> selectByCondition(Port2LayerAttr condition);
}