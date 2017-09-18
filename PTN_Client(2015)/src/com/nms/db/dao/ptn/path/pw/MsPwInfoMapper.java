package com.nms.db.dao.ptn.path.pw;

import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.path.pw.MsPwInfo;

public interface MsPwInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(MsPwInfo record);

    MsPwInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsPwInfo record);

    int updateByPrimaryKey(MsPwInfo record);

	void updateFrontTunnelId(Map<String, Object> map);

	void updateBackTunnelId(Map<String, Object> map);

	public List<MsPwInfo> queryByCondition(MsPwInfo mspwinfoCondition);

	public int insert(MsPwInfo msPwInfo);

	public void update(MsPwInfo msPwInfo);
}