package com.nms.db.bean.ptn.qos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * qos与业务关联表
 * 
 * @author kk
 * 
 */
@SuppressWarnings("serial")
public class QosRelevance implements Serializable {

	private int id;
	private String objType; // 业务类型，tunnel、pw、ac
	private int objId; // 业务主键
	private int siteId; // 网元ID
	private int qosGroupId; // 关联qosinfo表中的groupId
	private boolean isRepeat;//true/false = 有重复的(不删除)/没有重复的(可以删除)

	private List<QosInfo> qosInfoList = new ArrayList<QosInfo>(); // 关联的qos集合
	private Object object;

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getQosGroupId() {
		return qosGroupId;
	}

	public void setQosGroupId(int qosGroupId) {
		this.qosGroupId = qosGroupId;
	}

	public List<QosInfo> getQosInfoList() {
		return qosInfoList;
	}

	public void setQosInfoList(List<QosInfo> qosInfoList) {
		this.qosInfoList = qosInfoList;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
