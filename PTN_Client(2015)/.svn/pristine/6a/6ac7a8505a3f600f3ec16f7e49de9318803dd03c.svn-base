package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.PortConfigInfo;


public interface PortConfigInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PortConfigInfo record);

    int insertSelective(PortConfigInfo record);

    PortConfigInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortConfigInfo record);

    int updateByPrimaryKey(PortConfigInfo record);
    
    /**
	 * function: 查找所有的所有网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<PortConfigInfo> select(@Param("siteId")Integer siteId);
}