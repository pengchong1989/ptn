package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.BfdInfo;
import com.nms.drive.service.bean.BfdObject;



public interface BfdInfoMapper {
    
	public int update(BfdInfo info);
	
	public int insert(BfdInfo info);
	
	public int delete(@Param("id")int id);
	
    public List<BfdInfo> selectByCondtion(BfdInfo infos);
    
    public List<BfdObject> queryByCondition(@Param("siteId")int siteId);
    
    public int deleteBySiteId(@Param("siteId")int siteId);
    
  
}