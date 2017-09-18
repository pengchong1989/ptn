package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.LineClockInterface;


public interface LineClockInterfaceMapper {
   
    public int insert(LineClockInterface record);

    
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<LineClockInterface> select(@Param("siteId")Integer siteId);
    
    /**
	 * 条件查询
	 * @param conn
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<LineClockInterface> selectByCondtion(LineClockInterface condition);
	
	public int update(LineClockInterface lineClockInterface);
}