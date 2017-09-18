package com.nms.db.dao.ptn.clock;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.PortConfigInfo;


public interface PortConfigInfoMapper {
	public int delete(PortConfigInfo portConfigInfo);

    public int insert(PortConfigInfo record);

    public int update(PortConfigInfo record);
    
    /**
	 * function: 查找所有的所有网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<PortConfigInfo> select(@Param("siteId")Integer siteId);
    
    public List<PortConfigInfo> selectByCondtion(PortConfigInfo condition);
}