package com.nms.db.dao.ptn.qos;

import java.util.List;

import com.nms.db.bean.ptn.qos.QosQueue;


public interface QosQueueMapper {
	public int insert(QosQueue qos);

	public void update(QosQueue qos);

	public List<QosQueue> queryByCondition(QosQueue qos);

	public int deleteByServiceId(QosQueue qos);
	
	public List<QosQueue> queryByPortId(Integer portId, String objType);
}