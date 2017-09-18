package com.nms.db.dao.ptn.clock;

import java.util.List;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;


public interface ExternalClockInterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExternalClockInterface record);

    int insertSelective(ExternalClockInterface record);

    ExternalClockInterface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExternalClockInterface record);

    int updateByPrimaryKey(ExternalClockInterface record);
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<ExternalClockInterface> select(ExternalClockInterface externalClockInterfaceObject);
}