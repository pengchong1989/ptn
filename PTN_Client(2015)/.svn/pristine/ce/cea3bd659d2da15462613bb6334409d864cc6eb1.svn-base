package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.EthServiceInfo;



public interface EthServiceInstMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EthServiceInfo record);

    int insertSelective(EthServiceInfo record);

    EthServiceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EthServiceInfo record);

    int updateByPrimaryKey(EthServiceInfo record);

	public List<EthServiceInfo> queryBySiteIdAndPortLine(@Param("siteId")Integer siteId,@Param("portLine")String portLine,@Param("num")int num);
	
	/**
	 * 查询全部数量
	 * @return	List<EthServiceInfo>
	 * @throws Exception   EthServiceInfo ethServiceInfo
	 */
	public int findAllCount(@Param("siteId")Integer siteId);
	
	/**
	 * 查询全部
	 * @return	List<EthServiceInfo>
	 * @throws Exception   EthServiceInfo ethServiceInfo
	 */
	public List<EthServiceInfo> queryByCondition(@Param("vlans")List<Integer> vlans,@Param("siteId")Integer siteId);
	
	/**
	 * 查询全部
	 * @param siteId	查询条件
	 * @return	List<EthServiceInfo>
	 * @throws Exception   EthServiceInfo ethServiceInfo
	 */
	public List<EthServiceInfo> queryBySiteId(Integer siteId);
	
	public EthServiceInfo queryById(@Param("id")Integer id);
}