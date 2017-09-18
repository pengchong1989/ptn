package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

public class ETreeObject {

	private int vplsId = 0;// VS ID
	private int enable = 1;// 使能 0/(1)=关/(开)
	private int macCount = 0;// MAC地址表容量(4)
	private int eTreeUNIObjCount = 1;// (LAN口成员列表)成员端口数
	private int eTreeNNIObjCount = 0;// (仿真LAN口组)成员端口数

	private List<ETreeUNIObject> eTreeUNIObjectList = new ArrayList<ETreeUNIObject>();// (LAN口成员列表)成员端口数
	private List<ETreeNNIObject> eTreeNNIObjectList = new ArrayList<ETreeNNIObject>();// (仿真LAN口组)成员端口数
	
	private int type =1;//1/2 etree/elan
	private int role = 0; //角色 0:根;1:叶子
	
	public List<ETreeUNIObject> getETreeUNIObjectList() {
		return eTreeUNIObjectList;
	}

	public void setETreeUNIObjectList(List<ETreeUNIObject> treeUNIObjectList) {
		eTreeUNIObjectList = treeUNIObjectList;
	}

	public List<ETreeNNIObject> getETreeNNIObjectList() {
		return eTreeNNIObjectList;
	}

	public void setETreeNNIObjectList(List<ETreeNNIObject> treeNNIObjectList) {
		eTreeNNIObjectList = treeNNIObjectList;
	}

	public int getVplsId() {
		return vplsId;
	}

	public void setVplsId(int vplsId) {
		this.vplsId = vplsId;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getMacCount() {
		return macCount;
	}

	public void setMacCount(int macCount) {
		this.macCount = macCount;
	}

	public int getETreeUNIObjCount() {
		return eTreeUNIObjCount;
	}

	public void setETreeUNIObjCount(int treeUNIObjCount) {
		eTreeUNIObjCount = treeUNIObjCount;
	}

	public int getETreeNNIObjCount() {
		return eTreeNNIObjCount;
	}

	public void setETreeNNIObjCount(int treeNNIObjCount) {
		eTreeNNIObjCount = treeNNIObjCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
