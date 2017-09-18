package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.MCN;


public interface MCNMapper {
    public int update(MCN record);
    
    public int insertMcn(MCN record);
    
    public int insert(@Param("neId")String neId,@Param("ip")String ip ,@Param("mtu")Integer mtu,@Param("id")Integer id);
    
    /**
	 * 通过网元ID查询
	 * @param NeID
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<MCN> queryByNeID(String NeID);
	
	public void deleteBySite(@Param("siteId")String siteId);
}