package com.nms.db.dao.ptn.clock;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.LineClockInterface;


public interface LineClockInterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LineClockInterface record);

    int insertSelective(LineClockInterface record);

    LineClockInterface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LineClockInterface record);

    int updateByPrimaryKey(LineClockInterface record);
    
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<LineClockInterface> select(@Param("siteId")Integer siteId);
}