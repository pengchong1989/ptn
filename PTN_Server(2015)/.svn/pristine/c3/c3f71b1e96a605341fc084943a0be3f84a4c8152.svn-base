package com.nms.db.dao.path;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.path.SetNameRule;


public interface SetNameRuleMapper {
    public int delete(@Param("id")Integer id);

    public int insert(@Param("nameRule")SetNameRule nameRule);
  
    public List<SetNameRule> queryByidCondition(@Param("nameRule")SetNameRule nameRule);
    
    public int update(@Param("nameRule")SetNameRule nameRule);

    public int queryname(@Param("afterName")String afterName,@Param("beforeName")String beforeName);
}