package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.MacLearningInfo;


public interface MacLearningLimitMapper {
    public int delete(@Param("id")Integer id);

    public int insert(MacLearningInfo record);

    public int update(MacLearningInfo record);
      
    public List<MacLearningInfo> selectBySiteId(@Param("siteId")Integer siteId);
    
    public MacLearningInfo selectById(@Param("id")int id);
    
    public void deleteByPortId(@Param("siteId")int portId);
    
    public int deleteBySiteId(@Param("siteId")int siteId);
}