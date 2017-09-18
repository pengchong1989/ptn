package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.BfdInfo;



public interface BfdInfoMapper {
    
    public List<Integer> queryBfdId(@Param("siteId")int siteId);
    
    public List<Integer> queryMySid(@Param("siteId")int siteId);
    
    public List<Integer> queryPeerSid(@Param("siteId")int siteId);
    
    public List<BfdInfo> selectByCondtion(BfdInfo infos);
    
    public List<Integer> queryPwIds(@Param("siteId")int siteId,@Param("type")int type);
    
    public List<Integer> queryLspIds(@Param("siteId")int siteId,@Param("type")int type);
}