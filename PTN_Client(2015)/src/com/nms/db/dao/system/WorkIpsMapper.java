package com.nms.db.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.WorkIps;



public interface WorkIpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkIps record);

    int insertSelective(WorkIps record);

    WorkIps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkIps record);

    int updateByPrimaryKey(WorkIps record);
    
    public List<WorkIps> queryByCondition(@Param("workIps")WorkIps workIps);
}