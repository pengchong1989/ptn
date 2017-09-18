package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.AllConfigInfo;


public interface AllConfigInfoMapper {
    public int delete(@Param("siteId")Integer siteId);

    public int insert(AllConfigInfo record);

    int insertSelective(AllConfigInfo record);

    AllConfigInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AllConfigInfo record);

    int updateByPrimaryKey(AllConfigInfo record);

	public List<AllConfigInfo> queryBySiteId(int siteId);

	public void update(AllConfigInfo wholeConfigInfo);
}