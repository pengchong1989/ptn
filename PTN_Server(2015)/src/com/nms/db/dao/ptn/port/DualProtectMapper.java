package com.nms.db.dao.ptn.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.protect.DualProtect;

public interface DualProtectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DualProtect record);

    int insertSelective(DualProtect record);

    DualProtect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DualProtect record);

    int updateByPrimaryKey(DualProtect record);

	List<DualProtect> queryByCondition(DualProtect dualProtectSel);
	
	public int updateActiveStatus(@Param("siteId")int siteId,@Param("status")int status);
}