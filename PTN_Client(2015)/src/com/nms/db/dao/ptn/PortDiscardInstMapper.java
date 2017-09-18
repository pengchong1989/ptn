package com.nms.db.dao.ptn;

import java.util.List;

import com.nms.db.bean.equipment.port.PortDiscardInfo;



public interface PortDiscardInstMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PortDiscardInfo record);

    int insertSelective(PortDiscardInfo record);

    PortDiscardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortDiscardInfo record);

    int updateByPrimaryKey(PortDiscardInfo record);

	List<PortDiscardInfo> queryByCondition(int siteId);
}