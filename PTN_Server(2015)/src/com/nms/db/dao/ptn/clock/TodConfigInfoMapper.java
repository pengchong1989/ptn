package com.nms.db.dao.ptn.clock;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.TodConfigInfo;


public interface TodConfigInfoMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(TodConfigInfo record);

    int insertSelective(TodConfigInfo record);

    TodConfigInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TodConfigInfo record);

    int updateByPrimaryKey(TodConfigInfo record);
    
    public int update(TodConfigInfo todConfigInfo);
    
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public TodConfigInfo select(@Param("siteId")Integer siteId);
}