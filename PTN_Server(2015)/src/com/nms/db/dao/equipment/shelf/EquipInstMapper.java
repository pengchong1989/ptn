package com.nms.db.dao.equipment.shelf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.shelf.EquipInst;


public interface EquipInstMapper {
    int deleteByPrimaryKey(Integer id);

    

    int insertSelective(EquipInst record);

    EquipInst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EquipInst record);

    int updateByPrimaryKey(EquipInst record);
    
    public int insert(@Param("equipInst")EquipInst equipInst);
    public List<EquipInst> queryByCondition(@Param("equipInst")EquipInst equipInst);
    
    public int deleteBySiteId(@Param("siteId")int siteId);
    
}