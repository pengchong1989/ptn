package com.nms.db.dao.ptn.qos;

import java.util.List;
import java.util.Map;

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
	
}