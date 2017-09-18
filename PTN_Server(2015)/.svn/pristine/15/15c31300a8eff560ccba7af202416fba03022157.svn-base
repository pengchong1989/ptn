package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.bean.ptn.clock.ClockSource_Corba;


public interface ClockSourceMapper {
    
    public int update(ClockSource clockSource);
    
    /**
	 * function: 查找所有的所有网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public List<ClockSource> select(@Param("siteId")Integer siteId);
    
    /**
	 * function: 条件查询
	 * @param conn 数据库连接
	 * @return 
	 * @throws Exception
	 */
	public List<ClockSource> selectByCondtion(ClockSource clockSourceSearch);
	
	/**添加数据
	 * @param ClockSource
	 * @return 判断是否插入成功 返回的ID号 只要不是0就是插入成功
	 * @throws Exception
	 */
	public int insertSystemModel(ClockSource clockSource);
	
	/**添加数据
	 * @param ClockSource
	 * @return 判断是否插入成功 返回的ID号 只要不是0就是插入成功
	 * @throws Exception
	 */
	public int insertExportModel(ClockSource clockSource);
	
	public int delete(ClockSource clockSource);
	
	public int updateActiveStatus(@Param("siteId")int siteId,@Param("activeStatus")int activeStatus);
	
	public List<ClockSource_Corba> queryCorbaByCondition(ClockSource_Corba condition);
    
}