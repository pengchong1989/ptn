package com.nms.db.dao.ptn.port;

import java.util.List;

import com.nms.db.bean.ptn.port.PortLagInfo;


public interface PortLagInfoMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(PortLagInfo record);

    int insertSelective(PortLagInfo record);

    PortLagInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortLagInfo record);

    int updateByPrimaryKey(PortLagInfo record);

    public int update(PortLagInfo lagInfo);

	public List<PortLagInfo> queryByCondition(PortLagInfo portLagInfo);

	int selectCountByNeId(int neId);
}