package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.MacManagementInfo;


public interface BlackListMacMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MacManagementInfo record);

    int insertSelective(MacManagementInfo record);

    MacManagementInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MacManagementInfo record);

    int updateByPrimaryKey(MacManagementInfo record);
    
    public List<MacManagementInfo> selectBySiteId(@Param("siteId")Integer siteId);
    
    /**
	 * 判断一个网元下mac是否重复
	 * @param macInfo
	 * @param connection
	 * @return
	 * @throws Exception 
	 */
	public List<MacManagementInfo> checkVlanAndMac(MacManagementInfo macInfo);
	
	public List<MacManagementInfo> selectCountBySiteId(@Param("siteId")Integer siteId);
	
	 public MacManagementInfo selectById(@Param("id")Integer id);
}