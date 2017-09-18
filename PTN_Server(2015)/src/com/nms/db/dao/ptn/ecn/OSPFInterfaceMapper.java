package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.OSPFInterface;


public interface OSPFInterfaceMapper {
   
    public int insert(OSPFInterface record);

    public int update(OSPFInterface record);

	public List<OSPFInterface> queryByNeID(String neID);

	public List<OSPFInterface> queryByCondition(OSPFInterface ospfInterface);
	
	public int deleteById(@Param("id")Integer id);
	
	public int deleteBySiteId(@Param("siteid")String siteid);
	
	public int updateActiveStatus(@Param("siteid")String siteId,@Param("value")int value);
	
	public int updateStatus(OSPFInterface ospfInterface);
}