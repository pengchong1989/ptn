package com.nms.db.dao.ptn.clock;



import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;


public interface FrequencyInfoNeClockMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(FrequencyInfoNeClock record);

    FrequencyInfoNeClock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FrequencyInfoNeClock record);

    int updateByPrimaryKey(FrequencyInfoNeClock record);
    
    /**
	 * function: 查找所有的所有网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
    public FrequencyInfoNeClock select(@Param("siteId")Integer siteId);
    
    public int update(FrequencyInfoNeClock frequencyInfoNeClock);
    
    /**
	 * @param frequencyInfo_neClock
	 * @return 判断是否插入成功 返回的ID号 只要不是0就是插入成功
	 * @throws Exception
	 * 插入数据
	 */
	public int insert(FrequencyInfoNeClock frequencyInfo_neClock);
}