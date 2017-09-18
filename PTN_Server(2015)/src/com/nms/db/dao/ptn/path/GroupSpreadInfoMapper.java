package com.nms.db.dao.ptn.path;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;

public interface GroupSpreadInfoMapper {
    public int delete(@Param("id")Integer id);

    public int insert(GroupSpreadInfo record);
  
    public int update(GroupSpreadInfo record);
    
    public List<GroupSpreadInfo> queryByCondition(GroupSpreadInfo groupInfo);
}