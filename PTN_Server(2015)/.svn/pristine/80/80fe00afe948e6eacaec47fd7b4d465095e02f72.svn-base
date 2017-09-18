package com.nms.db.dao.ptn.oam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.oam.OamLinkInfo;

public interface OamLinkInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OamLinkInfo record);

    int insertSelective(OamLinkInfo record);

    OamLinkInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OamLinkInfo record);

    public int updateByPrimaryKey(OamLinkInfo record);
    
    public OamLinkInfo queryOamLinkInfoByCondition(OamLinkInfo record);
    
    public void delete(OamLinkInfo record);
    
    /**
     * 查询网元下所有linkoam
     * @param record
     * @return
     */
    public List<OamLinkInfo> queryOamLinkByType(OamLinkInfo record);

	void updateEquipExitStatusForEthLinkOam(int siteId, int status);
	
	public int deleteBySiteId(@Param("siteId")int siteId);
}