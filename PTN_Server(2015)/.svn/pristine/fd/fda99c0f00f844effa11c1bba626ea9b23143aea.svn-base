package com.nms.db.dao.ptn.oam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.oam.OamEthernetInfo;

public interface OamEthernetInfoMapper {
    public int insert(OamEthernetInfo record);

	public List<OamEthernetInfo> queryOamLinkInfoByConditionSide(OamEthernetInfo oamEthernetInfo);
	
	public int deleteBySiteId(@Param("siteId")int siteId);

	public List<OamEthernetInfo> queryOamLinkInfoByCondition(OamEthernetInfo oamEthernetInfo);

	public int delete(OamEthernetInfo oamEthernetInfo);

	public List<Integer> count(OamEthernetInfo oamInfo);

	public int update(OamEthernetInfo oamEthernetInfo);
}