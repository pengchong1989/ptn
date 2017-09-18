package com.nms.db.dao.ptn.ecn;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.MCN;


public interface MCNMapper {
    int deleteByPrimaryKey(Integer id);

    

    int insertSelective(MCN record);

    MCN selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MCN record);

    int updateByPrimaryKey(MCN record);
    public int insert(@Param("neId")String neId,@Param("ip")String ip ,@Param("mtu")Integer mtu,@Param("id")Integer id);
}