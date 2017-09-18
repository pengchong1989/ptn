package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;


public interface ExternalClockInterfaceMapper {
    public int delete(ExternalClockInterface record);
	
    public int insert(ExternalClockInterface record);

    public int update(ExternalClockInterface record);
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */  
    public List<ExternalClockInterface> select(ExternalClockInterface externalClockInterfaceObject);
    
    public void updateActiveStatus(@Param("siteId")int siteId,@Param("value")int value);
}