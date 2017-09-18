package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.EthLoopInfo;



public interface EthLoopMapper {

    public int insert(EthLoopInfo record);

    public int update(EthLoopInfo record);
    
    /**
	 * 查询全部
	 * @param condition	查询条件
	 * @param connection  数据库连接
	 * @return	List<EthLoopInfo>
	 * @throws Exception
	 */
	public List<EthLoopInfo> queryBySiteId(@Param("siteId")Integer siteId);
	
	public int deleteBySiteId(@Param("siteId")Integer siteId);
}