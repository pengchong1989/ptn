package com.nms.db.dao.ptn.port;

import java.util.List;

import com.nms.db.bean.ptn.path.protect.DualRelevance;

public interface DualRelevanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DualRelevance record);

    int insertSelective(DualRelevance record);

    DualRelevance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DualRelevance record);

    int updateByPrimaryKey(DualRelevance record);

	List<DualRelevance> queryByCondition(DualRelevance dualRelevance);
}