package com.nms.db.dao.equipment.slot;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.nms.db.bean.equipment.slot.SlotInst;


public interface SlotInstMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SlotInst record);

    SlotInst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SlotInst record);

    int updateByPrimaryKey(SlotInst record);
    
    public List<SlotInst> queryByCondition(@Param("slotInst")SlotInst slotInst);
    
    public int insert(@Param("slotInst")SlotInst slotInst);
    
    public int update(@Param("slotInst")SlotInst slotInst);
    //槽位信息统计
    public List<SlotInst> queryStatistics();
}