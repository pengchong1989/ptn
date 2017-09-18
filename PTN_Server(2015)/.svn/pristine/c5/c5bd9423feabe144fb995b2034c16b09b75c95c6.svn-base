package com.nms.db.dao.equipment.port;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.nms.db.bean.equipment.port.PortAttr;

public interface PortAttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(PortAttr record);

    PortAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortAttr record);

    int updateByPrimaryKey(PortAttr record);

	public List<PortAttr> queryByCondition(PortAttr portAttr);

	public void update(PortAttr portAttr);
	
	public int insert(PortAttr portAttr);
	
	public void deleteBySite(@Param("siteId")int siteId);
	
	public int deleteByPortId(@Param("portId")int portId);
}