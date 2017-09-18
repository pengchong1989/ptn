package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.TimeSyncInfo;



public interface TimeSyncMapper {
    public int deleteBySiteId(@Param("siteId")Integer siteId);
    
    public int insert(TimeSyncInfo record);

    public int update(TimeSyncInfo record);
    
    public List<TimeSyncInfo> queryBySiteId(@Param("siteId")Integer siteId);
}