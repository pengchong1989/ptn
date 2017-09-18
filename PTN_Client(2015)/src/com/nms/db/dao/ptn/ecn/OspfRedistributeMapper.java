package com.nms.db.dao.ptn.ecn;

import java.util.List;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;


public interface OspfRedistributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OspfRedistribute record);

    int insertSelective(OspfRedistribute record);

    OspfRedistribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OspfRedistribute record);

    int updateByPrimaryKey(OspfRedistribute record);

	public List<OspfRedistribute> queryByNeID(int neID);
}