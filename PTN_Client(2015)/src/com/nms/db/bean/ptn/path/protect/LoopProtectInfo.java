package com.nms.db.bean.ptn.path.protect;

import java.util.List;

import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.ui.frame.ViewDataObj;

public class LoopProtectInfo extends ViewDataObj {
//	保护类型:00/01/02=无保护 /Ring Wrapping_V1 /Ring Wrapping_V2
//	西向槽位:255/10/11/14/15/…/19H=无/Slot10/ Slot11/Slot14/ Slot15/…/ Slot19（界面不显示）
//	西向端口:0/1/…/4=无/WAN1/…/WAN4
//	东向槽位:255/10/11/14/15/…/19H=无/Slot10/ Slot11/Slot14/ Slot15/…/ Slot19（界面不显示）
//	东向端口:0/1…/4=无/WAN1/…/WAN4
//	环网节点总数:00-FFH=0-255
//	本站节点ID:00-FFH=0-255
//	逻辑环ID:00-FFH =0-255
//	拖延时间:00-FFH =0-255
//	返回类型:00/01=返回型/不返回型
//	环网ID:1-2
//	目的节点ID :00-FFH =0-255
//	西向LSP ID：00-200H=0-512（V3用，网管暂不处理）
//	东向LSP ID：00-200H=0-512（V3用，网管暂不处理）
//	备用,00
	private static final long serialVersionUID = -7817578769116448514L;
	private int id;
	private String name;
	private int protectType = 2;//保护类型
	private int westSlot;//西向槽位
	private int westPort;//西向端口
	private int eastSlot;//东向槽位
	private int eastPort;//东向端口
	private int loopNodeNumber;//环网节点总数
	private int nodeId;//本站节点id
	private int logicId;//逻辑id
	private int waittime;//等待恢复时间
	private int delaytime;//拖延时间
	private int apsenable;//aps使能
	private int backType;//是否返回
	private int loopId;//环网id
	private int targetNodeId;//目的节点id
	private int westLspId;//西向lspId
	private int eastLspId;//东向lspId
	private int siteId;
	private Segment westSegment;
	private String createTime;
	private String createUser;
	private int isSingle;
	private String position;
	private int activeStatus;  //1 激活 ，0未激活
	private int westNodeId;//西向节点Id
	private int eastNodeId;//东向节点Id
	private int loopBusinessId;//业务Id
	private List<CommonBean> loopPathNameList;//路径名称集合，log日志显示使用

	public List<CommonBean> getLoopPathNameList() {
		return loopPathNameList;
	}

	public void setLoopPathNameList(List<CommonBean> loopPathNameList) {
		this.loopPathNameList = loopPathNameList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}


	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public Segment getWestSegment() {
		return westSegment;
	}


	public void setWestSegment(Segment westSegment) {
		this.westSegment = westSegment;
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}


	public int getSiteId() {
		return siteId;
	}


	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getProtectType() {
		return protectType;
	}


	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}


	public int getWestSlot() {
		return westSlot;
	}


	public void setWestSlot(int westSlot) {
		this.westSlot = westSlot;
	}


	public int getWestPort() {
		return westPort;
	}


	public void setWestPort(int westPort) {
		this.westPort = westPort;
	}


	public int getEastSlot() {
		return eastSlot;
	}


	public void setEastSlot(int eastSlot) {
		this.eastSlot = eastSlot;
	}


	public int getEastPort() {
		return eastPort;
	}


	public void setEastPort(int eastPort) {
		this.eastPort = eastPort;
	}


	public int getLoopNodeNumber() {
		return loopNodeNumber;
	}


	public void setLoopNodeNumber(int loopNodeNumber) {
		this.loopNodeNumber = loopNodeNumber;
	}


	public int getNodeId() {
		return nodeId;
	}


	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}


	public int getLogicId() {
		return logicId;
	}


	public void setLogicId(int logicId) {
		this.logicId = logicId;
	}


	public int getWaittime() {
		return waittime;
	}


	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}


	public int getDelaytime() {
		return delaytime;
	}


	public void setDelaytime(int delaytime) {
		this.delaytime = delaytime;
	}


	public int getApsenable() {
		return apsenable;
	}


	public void setApsenable(int apsenable) {
		this.apsenable = apsenable;
	}


	public int getBackType() {
		return backType;
	}


	public void setBackType(int backType) {
		this.backType = backType;
	}


	public int getLoopId() {
		return loopId;
	}


	public void setLoopId(int loopId) {
		this.loopId = loopId;
	}


	public int getTargetNodeId() {
		return targetNodeId;
	}


	public void setTargetNodeId(int targetNodeId) {
		this.targetNodeId = targetNodeId;
	}

	public int getWestLspId() {
		return westLspId;
	}


	public void setWestLspId(int westLspId) {
		this.westLspId = westLspId;
	}


	public int getEastLspId() {
		return eastLspId;
	}


	public void setEastLspId(int eastLspId) {
		this.eastLspId = eastLspId;
	}


	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", getId());
		this.putClientProperty("name", getName());
		this.putClientProperty("apsenable", getApsenable()==1?"使能":"不使能");
		this.putClientProperty("waittime", getWaittime());
		this.putClientProperty("delaytime", getDelaytime());
		this.putClientProperty("backType", getBackType()==1?"返回":"不返回");
		this.putClientProperty("createTime", getCreateTime());
		this.putClientProperty("createUser", getCreateUser());
		this.putClientProperty("nodeId", getNodeId());
		this.putClientProperty("activeStatus", getActiveStatus()==1?true:false);
	}


	public int getIsSingle() {
		return isSingle;
	}


	public void setIsSingle(int isSingle) {
		this.isSingle = isSingle;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public int getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	public int getWestNodeId() {
		return westNodeId;
	}


	public void setWestNodeId(int westNodeId) {
		this.westNodeId = westNodeId;
	}


	public int getEastNodeId() {
		return eastNodeId;
	}


	public void setEastNodeId(int eastNodeId) {
		this.eastNodeId = eastNodeId;
	}


	public int getLoopBusinessId() {
		return loopBusinessId;
	}


	public void setLoopBusinessId(int loopBusinessId) {
		this.loopBusinessId = loopBusinessId;
	}
}
