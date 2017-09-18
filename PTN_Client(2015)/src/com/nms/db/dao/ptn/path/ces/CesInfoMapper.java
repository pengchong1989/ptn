package com.nms.db.dao.ptn.path.ces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.ces.CesInfo;

public interface CesInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CesInfo record);

    int insertSelective(CesInfo record);

    CesInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CesInfo record);

    int updateByPrimaryKey(CesInfo record);

	List<CesInfo> filterQuery(CesInfo cesInfo);

	List<CesInfo> queryNodeBySite(int siteId);

	List<CesInfo> filterSingle(@Param("cesInfo")CesInfo cesInfo, @Param("siteId")int siteId);
	
	/**
	 * 通过条件查询
	 * 
	 * @param cesinfoCondition
	 *            查询条件
	 * @return List<PwInfo>集合
	 */
	public List<CesInfo> queryByIdCondition(CesInfo cesinfoCondition);

	void doSearche_insert(String name, int s1Id, int s2Id);

	void deleteByIds(@Param("integers")List<Integer> integers);
	
	
	/**
	 * 通过条件查询
	 * 
	 * @param cesinfoCondition
	 *            查询条件
	 * @return List<PwInfo>集合
	 */
	public List<CesInfo> queryByCondition(CesInfo cesinfoCondition);

	int query_name(@Param("afterName")String afterName, @Param("beforeName")String beforeName, @Param("siteId")int siteId);
	
	List<CesInfo> querySynchro(int siteId, int xcid);
	
	/**
	 * 通过条件查询
	 * 
	 * @param cesinfoCondition
	 *            查询条件
	 * @param connection
	 *            数据库连接
	 * @return List<PwInfo>集合
	 * @throws Exception
	 */
	public List<CesInfo> queryByCondition_nojoin(CesInfo cesinfoCondition);

	public List<CesInfo> queryByPwId(@Param("pwIdList")List<Integer> pwIdList);

	List<CesInfo> selectBysiteIdandE1id(int siteID, int e1Id);
	
}