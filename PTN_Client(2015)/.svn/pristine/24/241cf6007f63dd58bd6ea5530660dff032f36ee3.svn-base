package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.ClockSource;


public interface ClockSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClockSource record);

    int insertSelective(ClockSource record);

    ClockSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClockSource record);

    int updateByPrimaryKey(ClockSource record);
    
    /**
	 * function: 查找所有的所有网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<ClockSource> select(@Param("siteId")Integer siteId);
    
}