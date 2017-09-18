package com.nms.db.dao.system;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.WorkIps;

public interface WorkIpsMapper {
    public int delete(Integer id);

    public int insert(WorkIps workIps);

    public int update(WorkIps workIps);
    
    public List<WorkIps> queryByCondition(@Param("workIps")WorkIps workIps);
}