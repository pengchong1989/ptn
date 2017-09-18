package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.PtpPortinfo;


public interface PtpPortMapper {
    
    public List<PtpPortinfo> queryBySiteId(@Param("siteId")Integer siteId);
    
    public int insertPtpPort(PtpPortinfo record);
    
    public int deletePtpBySiteId(@Param("siteId")Integer siteId);
}