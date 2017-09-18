package com.nms.db.dao.ptn.qos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.qos.QosInfo;

public interface QosInfoMapper {
    /**
	 * 根据条件查询
	 * @param siteId
	 * @param groupId
	 * @return
	 */   
    public List<QosInfo> queryByCondition(QosInfo qosInfo);

	public int queryMaxGroupId();

	public void insert(QosInfo qosInfo);

	public List<QosInfo> selectByCondition(Map<String, Object> map);

	public void deleteByGroupId(int qosGroupId);

	public void updateElsp(QosInfo qosInfo);

	public void update(QosInfo qosInfo);

	public void updateStatus(@Param("siteId")int siteId, @Param("type")String type, @Param("groupIds")String groupIds, @Param("status")int status);
	
	public void delete(int siteId);
}