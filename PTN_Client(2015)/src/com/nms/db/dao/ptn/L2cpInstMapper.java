package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.L2cpInfo;


public interface L2cpInstMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(L2cpInfo record);

    int insertSelective(L2cpInfo record);

    L2cpInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(L2cpInfo record);

    int updateByPrimaryKey(L2cpInfo record);
    

	/**
	 * 通过网元ID来获取全部的L2CP数据
	 * @param siteId 网元Id
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<L2cpInfo> queryBySiteId(@Param("siteId")int siteId);
}