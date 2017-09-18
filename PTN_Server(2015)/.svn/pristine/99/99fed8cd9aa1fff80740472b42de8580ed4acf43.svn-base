package com.nms.model.ptn.qos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.dao.ptn.qos.QosQueueMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class QosQueueService_MB extends ObjectService_Mybatis {
	private QosQueueMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public QosQueueMapper getQosInfoMapper() {
		return mapper;
	}

	public void setQosQueueMapper(QosQueueMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 根据对象ID和类型查询模板
	 */
	public List<QosQueue> queryByCondition(QosQueue qos) throws Exception {
		List<QosQueue> qosQueueList = null;
		try {
			qosQueueList = new ArrayList<QosQueue>();
			qosQueueList = this.mapper.queryByCondition(qos);
		} catch (Exception e) {
			throw e;
		}
		return qosQueueList;
	}

	public int saveOrUpdate(List<QosQueue> qosQueueList) throws Exception {
		if (qosQueueList == null || qosQueueList.size() == 0) { 
			throw new Exception("qosQueueList is null");
		}
		int result = 0;
		try {
			int aObjId = 0;
			int zObjId = 0;
			boolean aFlag = false;
			boolean zFlag = false;
			String objType = "";
			if (qosQueueList.size() != 0) {
				aObjId = qosQueueList.get(0).getObjId();
				objType = qosQueueList.get(0).getObjType();
				for (QosQueue qos : qosQueueList) {
					if(qos.getObjId() != aObjId){
						zObjId = qos.getObjId();
						break;
					}
				}
				aFlag = this.checkServiceIdExsits(aObjId, objType);
				zFlag = this.checkServiceIdExsits(zObjId, objType);
				for (QosQueue qosQueue : qosQueueList) {
					if(qosQueue.getObjId() == aObjId){
						this.operate(aFlag, qosQueue);
					}else if(qosQueue.getObjId() == zObjId){
						this.operate(zFlag, qosQueue);
					}
					++result;
				}
				this.sqlSession.commit();
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	private boolean checkServiceIdExsits(int objId, String objType) throws Exception {
		List<QosQueue> qosList = null;
		try {
			qosList = new ArrayList<QosQueue>();
			QosQueue qosCondition = new QosQueue();
			qosCondition.setObjType(objType);
			qosCondition.setObjId(objId);
			qosList = this.mapper.queryByCondition(qosCondition);
			if (qosList != null && qosList.size() != 0) {
				return true;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return false;
	}

	private void operate(boolean flag, QosQueue qosQueue) throws Exception {
		if(flag){
			this.mapper.update(qosQueue);
		}else{
			this.mapper.insert(qosQueue);
		}
	}

	/**
	 * 根据业务ID和类型查询模板
	 */
	public Map<Integer, List<QosQueue>> queryByPortId(QosQueue qos) throws Exception {
		Map<Integer, List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueueList = null;
		List<QosQueue> infoList = null;
		try {
			qosMap = new HashMap<Integer, List<QosQueue>>();
			qosQueueList = this.mapper.queryByCondition(qos);
			for (QosQueue qosQueue : qosQueueList) {
				if (qosMap.get(qosQueue.getSiteId()) == null) {
					infoList = new ArrayList<QosQueue>();
					for (QosQueue info : qosQueueList) {
						if (info.getSiteId() == qosQueue.getSiteId()) {
							infoList.add(info);
						}

					}
					qosMap.put(qosQueue.getSiteId(), infoList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return qosMap;
	}

	/**
	 * 根据业务ID和类型删除，批量删除
	 */
	public int deleteByServiceId(QosQueue qos) throws Exception {
		int result = 0;
		try {
			result = this.mapper.deleteByServiceId(qos);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 通过id集合获取
	 * @param objIds id集合
	 * @return
	 */
	public List<QosQueue> queryByIdWithUNI(int id) {
		List<QosQueue> qosQueueList = null;
		try {
			qosQueueList = new ArrayList<QosQueue>();
			qosQueueList = this.mapper.queryByIdWithUNI(id);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return qosQueueList;
	}

	/**
	 * 根据对象ID和类型删除,单个删除
	 */
	public int delete(QosQueue qos) throws Exception {
		int result = 0;
		try {
			result = this.mapper.delete(qos);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return result;
	}
}
