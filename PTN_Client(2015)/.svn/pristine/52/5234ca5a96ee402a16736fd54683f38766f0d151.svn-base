package com.nms.db.dao.ptn.path.eth;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.eth.ElineInfo;

public interface ElineInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ElineInfo record);

    int insertSelective(ElineInfo record);

    ElineInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ElineInfo record);

    int updateByPrimaryKey(ElineInfo record);

	public List<ElineInfo> queryByCondition(ElineInfo eline);

	public List<ElineInfo> selectBySiteId(int siteId);

	List<ElineInfo> querySingleByCondition(ElineInfo elineInfo);

	List<ElineInfo> selectBySiteAndisSingle(int siteId, int i);
	
	public List<ElineInfo> queryNodeBySiteAndServiceId(@Param("siteId")int siteId,@Param("serviceId")int serviceId);

	List<ElineInfo> queryByAcIdAndSiteIdCondition(int acId, int siteId);

	List<ElineInfo> queryByCondition_notjoin(ElineInfo elineinfo);

	List<ElineInfo> queryNodeBySite(int siteId);

	void doSearche_insert(String name, int s1Id, int s2Id);

	void deleteByIds(@Param("integers")List<Integer> integers);

	int query_name(@Param("afterName")String afterName, @Param("beforeName")String beforeName, @Param("siteId")int siteId);

	void updateStatusByType(@Param("siteId")int siteId, @Param("status")int status, @Param("type")int type);

	List<ElineInfo> querySynchro(int siteId, int xcid);

	int isRelatedPW(int pwId);

	int isRelatedAcByVPWS(int acId);

	List<ElineInfo> isRelatedACByVPLS(@Param("acId")int acId);

	public List<ElineInfo> queryAllElineByPwId(@Param("pwIdList")List<Integer> pwIdList);

	List<ElineInfo> selectByacids(@Param("integers")List<Integer> integers);
}