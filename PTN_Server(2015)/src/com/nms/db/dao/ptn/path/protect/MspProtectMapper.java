package com.nms.db.dao.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.protect.MspProtect;

public interface MspProtectMapper {
    public int delete(MspProtect record);
    
    public int deleteBySiteId(@Param("siteId ")Integer siteId);

    public int insert(MspProtect record);

    public int update(MspProtect record);

	public List<MspProtect> query(MspProtect mspProtect);
	
	public int updateActiveStatus(@Param("siteId ")int siteId,@Param("status ")int status);
}