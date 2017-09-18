package com.nms.db.dao.equipment.port;

import java.util.List;

import com.nms.db.bean.equipment.port.V35PortInfo;


public interface V35PortInfoMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(V35PortInfo record);

    public int update(V35PortInfo record);

	public List<V35PortInfo> queryByCondition(V35PortInfo info);
}