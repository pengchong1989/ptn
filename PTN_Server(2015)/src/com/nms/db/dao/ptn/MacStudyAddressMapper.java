package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.SsMacStudy;



public interface MacStudyAddressMapper {
    public int delete(SsMacStudy record);

    public int insert(SsMacStudy record);

    public void update(SsMacStudy record);
   
    /**
	 *查询数据库中二层Mac的信息
	 * @param id 数据库主键ID
	 */
	public List<SsMacStudy> selectBySecondMacStudyInfo(@Param("siteId")Integer siteId);
	
	public List<SsMacStudy> queryMacInfoByCondition(SsMacStudy info);
		
	public void deleteBySiteId(@Param("siteId")int siteId);
	
		
}