package com.nms.db.dao.ptn;

import java.util.List;

import com.nms.db.bean.ptn.BlackAndwhiteMacInfo;



public interface BlackWhiteMacNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackAndwhiteMacInfo record);

    int insertSelective(BlackAndwhiteMacInfo record);

    BlackAndwhiteMacInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlackAndwhiteMacInfo record);

    int updateByPrimaryKey(BlackAndwhiteMacInfo record);
    
    public List<BlackAndwhiteMacInfo> selectByBlackAndwhiteMacInfo(BlackAndwhiteMacInfo blackAndwhiteMacInfo);
}