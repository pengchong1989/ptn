package com.nms.db.dao.ptn.clock;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.FrequencyInfo;


public interface FrequencyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FrequencyInfo record);

    int insertSelective(FrequencyInfo record);

    FrequencyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FrequencyInfo record);

    int updateByPrimaryKey(FrequencyInfo record);
    
    /**
	 * 查询全部
	 * 
	 * @param condition
	 *            查询条件
	 * @param connection
	 *            数据库连接
	 * @return List<FrequencyInfo>
	 * @throws Exception
	 */
	public List<FrequencyInfo> queryByCondition(@Param("siteId")Integer siteId);
}