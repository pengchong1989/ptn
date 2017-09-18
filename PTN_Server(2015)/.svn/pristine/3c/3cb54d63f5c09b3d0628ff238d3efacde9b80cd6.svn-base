package com.nms.db.dao.ptn.ecn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.ecn.OspfRedistribute;


public interface OspfRedistributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OspfRedistribute record);

    int insertSelective(OspfRedistribute record);

    OspfRedistribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OspfRedistribute record);

    int updateByPrimaryKey(OspfRedistribute record);

	public List<OspfRedistribute> queryByNeID(int neID);
	
	public int deleteByNeId(@Param("siteId")int siteId);

	int queryName(OspfRedistribute ospfRedistribute);

	int deleteByNeIdAndType(OspfRedistribute ospfRedistribute);

	int updateStatus(OspfRedistribute ospfRedistribute);

	List<OspfRedistribute> queryByNeIdAndNameRedistributeType(String neID, String redistributeType);
}