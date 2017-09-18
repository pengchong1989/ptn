package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.EthServiceInfo;



public interface EthServiceInstMapper {
    public int delete(EthServiceInfo ethServiceInfo);

    public int insert(EthServiceInfo record);

    public int update(EthServiceInfo record);
			
	/**
	 * 查询全部
	 * @param siteId	查询条件
	 * @return	List<EthServiceInfo>
	 * @throws Exception   EthServiceInfo ethServiceInfo
	 */
	public List<EthServiceInfo> queryBySiteId(Integer siteId);
	
	public void deleteAllBySite(@Param("siteId")int siteId);
	
	public List<EthServiceInfo> queryOamLinkInfoByCondition(EthServiceInfo ethServiceInst);
}