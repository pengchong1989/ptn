package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ARPInfo;

public interface ARPMapper {

	public List<ARPInfo> queryBySiteId(int siteId);

	public int query_nameBySingle(@Param("afterName")String afterName,@Param("beforeName")String beforeName,@Param("siteId")int siteId);

}
