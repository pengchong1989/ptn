package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.SsMacStudy;



public interface MacStudyAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SsMacStudy record);

    int insertSelective(SsMacStudy record);

    SsMacStudy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SsMacStudy record);

    int updateByPrimaryKey(SsMacStudy record);
    
    /**
	 *查询数据库中二层Mac的信息
	 * @param id 数据库主键ID
	 */
	public List<SsMacStudy> selectBySecondMacStudyInfo(@Param("siteId")Integer siteId);
	
	public List<Integer> queryVlan(@Param("siteId")Integer siteId,@Param("portId")Integer portId);
	
	public List<Integer> queryMacId(@Param("siteId")Integer siteId);
}