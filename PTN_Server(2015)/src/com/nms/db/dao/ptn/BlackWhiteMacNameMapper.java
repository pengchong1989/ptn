package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;



public interface BlackWhiteMacNameMapper {
    public int delete(@Param("id")Integer id);

    public int insert(BlackAndwhiteMacInfo record);

    public int update(BlackAndwhiteMacInfo record);

    public int deleteBySiteId(@Param("siteId")int siteId);
    
    public String selectByVsAndSiteId(@Param("vsId")int vsId,@Param("siteId")int siteId);
    
    public List<BlackAndwhiteMacInfo> selectByBlackAndwhiteMacInfo(BlackAndwhiteMacInfo blackAndwhiteMacInfo);
}