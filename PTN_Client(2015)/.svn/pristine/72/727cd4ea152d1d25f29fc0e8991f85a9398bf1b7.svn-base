package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.PtpPortinfo;


public interface PtpPortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PtpPortinfo record);

    int insertSelective(PtpPortinfo record);

    PtpPortinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PtpPortinfo record);

    int updateByPrimaryKey(PtpPortinfo record);
    public List<PtpPortinfo> queryBySiteId(@Param("siteId")Integer siteId);
}