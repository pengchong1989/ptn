package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.SmartFan;


public interface SmartFanMapper {
    
    public int save(@Param("fan")SmartFan fan);
    
    public int update(@Param("fan")SmartFan fan);
    
    public List<SmartFan> selectAll(@Param("siteId")Integer siteId);
    
    public void deleteBySiteId(@Param("siteId")int siteId);
    
}