package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.OSPFinfo_wh;


public interface OSPFinfo_whMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OSPFinfo_wh record);

    int insertSelective(OSPFinfo_wh record);

    OSPFinfo_wh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OSPFinfo_wh record);

    int updateByPrimaryKey(OSPFinfo_wh record);
    
    List<OSPFinfo_wh> selectBysiteId(@Param("siteId")int siteId);
    
    int deleteBySiteId(@Param("siteId")int siteId);
}