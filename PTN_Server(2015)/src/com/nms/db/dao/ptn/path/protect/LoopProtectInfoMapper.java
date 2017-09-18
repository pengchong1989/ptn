package com.nms.db.dao.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;


public interface LoopProtectInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoopProtectInfo record);

    int insertSelective(LoopProtectInfo record);

    LoopProtectInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoopProtectInfo record);

    int updateByPrimaryKey(LoopProtectInfo record);

	public List<LoopProtectInfo> queryByCondition(LoopProtectInfo loopCondition);

	public int update(LoopProtectInfo loopProtectInfo);

	List<LoopProtectInfo> selectAll();

	int query_name(@Param("afterName")String afterName, @Param("beforeName")String beforeName);
	
	/**
	 * 条件查询
	 * @param loopProtectInfo
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<LoopProtectInfo> queryForLoop();

	void deleteByLoopId(int loopId);
	
	public void updateStatus(@Param("siteId")int siteId, @Param("status")int status);
}