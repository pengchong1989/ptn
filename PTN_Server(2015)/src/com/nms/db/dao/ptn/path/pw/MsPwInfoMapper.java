package com.nms.db.dao.ptn.path.pw;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.pw.MsPwInfo;

public interface MsPwInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MsPwInfo record);

    MsPwInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsPwInfo record);

    int updateByPrimaryKey(MsPwInfo record);

	void updateFrontTunnelId(int newTunnelId, String tid);

	void updateBackTunnelId(int newTunnelId, String tid);

	public List<MsPwInfo> queryByCondition(MsPwInfo mspwinfoCondition);

	public int insert(MsPwInfo msPwInfo);

	public void update(MsPwInfo msPwInfo);

	List<MsPwInfo> queryByTunnelIds(@Param("tunnelIds")List<Integer> tunnelIds);

	public void delete(int id);
}