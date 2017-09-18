package com.nms.db.dao.ptn.clock;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.clock.TimeManageInfo;


public interface TimeManageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(TimeManageInfo timeManageInfo);

    int insertSelective(TimeManageInfo record);

    TimeManageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeManageInfo record);

    int updateByPrimaryKey(TimeManageInfo record);
    
    /**
	 * function: 查找单个的网元时钟的状态和属性
	 * @param conn 数据库连接
	 * @return 所有网元时钟的状态和属性 集合
	 * @throws Exception
	 */
   public TimeManageInfo select(@Param("siteId")int siteId);
   
   /**
	 * 
	 * @param timeManageInfo 实体
	 * @param conn 数据库连接
	 * @return 1成功，0不成功
	 * @throws Exception
	 * function:根新数据
	 */
    public int update(TimeManageInfo timeManageInfo);
    
    public List<TimeManageInfo> selectByCondtion(TimeManageInfo condition);
}