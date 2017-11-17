package com.nms.db.dao.equipment.card;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.card.CardInst;


public interface CardInstMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(@Param("cardInst")CardInst cardInst);

    int insertSelective(CardInst record);

    CardInst selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardInst record);

    int updateByPrimaryKey(CardInst record);
    
    public List<CardInst> queryByCondition(@Param("cardInst")CardInst cardInst);
    
    public int update(@Param("cardInst")CardInst cardInst);
    //E1类型的Card
    public List<Integer> querryBySiteIdAndType(@Param("siteId")Integer siteId);
    //主控卡或703
    public List<Integer> querrysBySiteIdAndType(@Param("siteId")Integer siteId);
    
    public int delete(@Param("id")Integer id);
    
    public int deleteBySiteId(@Param("siteId")int siteId);

	List<CardInst> select_north();
}