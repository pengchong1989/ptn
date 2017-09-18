package com.nms.db.bean.system;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class UdaGroup implements Serializable {
	private int id;
	private String groupName;
	private String groupType;
	private int parentId;
	private String parentName;
	private List<UdaAttr> udaAttrList;
	private List<UdaGroup> childUdaGroupList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<UdaAttr> getUdaAttrList() {
		return udaAttrList;
	}

	public void setUdaAttrList(List<UdaAttr> udaAttrList) {
		this.udaAttrList = udaAttrList;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<UdaGroup> getChildUdaGroupList() {
		return childUdaGroupList;
	}

	public void setChildUdaGroupList(List<UdaGroup> childUdaGroupList) {
		this.childUdaGroupList = childUdaGroupList;
	}
}
