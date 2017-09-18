package com.nms.db.dao.ptn.ecn;

import java.util.List;

import com.nms.db.bean.ptn.ecn.OSPFInterface;


public interface OSPFInterfaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OSPFInterface record);

    int insertSelective(OSPFInterface record);

    OSPFInterface selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OSPFInterface record);

    int updateByPrimaryKey(OSPFInterface record);

	public List<OSPFInterface> queryByNeID(String neID);

	public List<OSPFInterface> queryByCondition(OSPFInterface ospfInterface);
}