package com.nms.db.bean.ptn.path.tunnel;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;

public class Tunnel extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 850906691362747671L;
	private int tunnelId;
	private String tunnelName="";
	private String tunnelType;
	private int tunnelStatus;
	private String jobStatus = "0";
	private int bandwidth;
	private int aSiteId;
	private int zSiteId;
	private int aPortId;
	private int zPortId;
	// private int labelValue;
	private String direction;
	private String showSiteAname;
	private String showSiteZname;
	private String showPortAname;
	private String showPortZname;
	private List<Lsp> lspParticularList;
	private int isReverse;
	private String createTime;
	/**
	 * overTime
	 * Tunnel过滤时，选择时间 -自定义范围 的 结束时间
	 *   数据库中没有此数据
	 */
	private String overTime;
	private String createUser;
	private int protectTunnelId;
	private String protectTunnelName;
	private int protectType;
	private List<PortInst> portInstList = new ArrayList<PortInst>(); // 端口集合 修改端口用
	private List<OamInfo> oamList = new ArrayList<OamInfo>();
	private OamInfo oamInfo = new OamInfo();
	private List<QosInfo> qosList = new ArrayList<QosInfo>();
	private boolean node = false;
	private int isSingle;
	//tunnel修改之前的激活状态，在下发晨晓时 判断用
	private int before_activity;
	private Tunnel protectTunnel;
	private int waittime;
	private int delaytime;
	private int apsenable;
	private boolean isSynchro = false;
	private int rotate_a; 	//a端的保护倒换事件  不入库，下发倒换用
	private int rotate_z;	//z端的保护倒换事件  不入库，下发倒换用
	private int position; // 工作状态 1=工作 0=空闲
	private int protectBack;//1:1保护是否返回（武汉）
	private int aprotectId;//a，z保护id
	private int zprotectId;
	private String sncpIds;//sncp保护id和对应网元id
	
	private String role = "0";//记录角色的id
	private int inBandwidthControl;
	private int outBandwidthControl;
	
	//新增外层vlan
	private int aVlanEnable;//前向外层VLAN使能
	private int aOutVlanValue;//前向外层vlan值
	private int aTp_id;//前向外层TP_ID
	private int zVlanEnable;//后向外层VLAN使能
	private int zOutVlanValue;//后向外层vlan值
	private int zTp_id;//后向外层TP_ID
	
	//新增mac
	private String sourceMac;//源mac
	private String endMac;//目的mac
	
	//8月20日 做单网元过滤时，康凯新增字段
	private int cardId=0;
	
	private String rotateWay;// 倒换准则 SD/SF
	private String rotateLocation;// 倒换位置 A端网元/Z端网元
	private String rotateMode;// 倒换模式 人工倒换/自动倒换
	private int tnpLayer;// TNP层速率 1-100
	private int rotateThreshold;// 自动倒换阈值(%) 1-100
	
	public String getRotateWay() {
		return rotateWay;
	}

	public void setRotateWay(String rotateWay) {
		this.rotateWay = rotateWay;
	}

	public String getRotateLocation() {
		return rotateLocation;
	}

	public void setRotateLocation(String rotateLocation) {
		this.rotateLocation = rotateLocation;
	}

	public String getRotateMode() {
		return rotateMode;
	}

	public void setRotateMode(String rotateMode) {
		this.rotateMode = rotateMode;
	}

	public int getTnpLayer() {
		return tnpLayer;
	}

	public void setTnpLayer(int tnpLayer) {
		this.tnpLayer = tnpLayer;
	}

	public int getRotateThreshold() {
		return rotateThreshold;
	}

	public void setRotateThreshold(int rotateThreshold) {
		this.rotateThreshold = rotateThreshold;
	}

	public int getInBandwidthControl() {
		return inBandwidthControl;
	}

	public void setInBandwidthControl(int inBandwidthControl) {
		this.inBandwidthControl = inBandwidthControl;
	}

	public int getOutBandwidthControl() {
		return outBandwidthControl;
	}

	public void setOutBandwidthControl(int outBandwidthControl) {
		this.outBandwidthControl = outBandwidthControl;
	}

	public OamInfo getOamInfo() {
		return oamInfo;
	}

	public void setOamInfo(OamInfo oamInfo) {
		this.oamInfo = oamInfo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}

	public String getTunnelName() {
		return tunnelName;
	}

	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	public String getTunnelType() {
		return tunnelType;
	}

	public void setTunnelType(String tunnelType) {
		this.tunnelType = tunnelType;
	}

	public int getTunnelStatus() {
		return tunnelStatus;
	}

	public void setTunnelStatus(int tunnelStatus) {
		this.tunnelStatus = tunnelStatus;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getASiteId() {
		return aSiteId;
	}

	public void setASiteId(int siteId) {
		aSiteId = siteId;
	}

	public int getZSiteId() {
		return zSiteId;
	}

	public void setZSiteId(int siteId) {
		zSiteId = siteId;
	}

	public int getAPortId() {
		return aPortId;
	}

	public void setAPortId(int portId) {
		aPortId = portId;
	}

	public int getZPortId() {
		return zPortId;
	}

	public void setZPortId(int portId) {
		zPortId = portId;
	}

	// public int getLabelValue() {
	// return labelValue;
	// }
	//
	// public void setLabelValue(int labelValue) {
	// this.labelValue = labelValue;
	// }

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<Lsp> getLspParticularList() {
		return lspParticularList;
	}

	public void setLspParticularList(List<Lsp> lspParticularList) {
		this.lspParticularList = lspParticularList;
	}

	public String getShowSiteAname() {
		return showSiteAname;
	}

	public void setShowSiteAname(String showSiteAname) {
		this.showSiteAname = showSiteAname;
	}

	public String getShowSiteZname() {
		return showSiteZname;
	}

	public void setShowSiteZname(String showSiteZname) {
		this.showSiteZname = showSiteZname;
	}

	public String getShowPortAname() {
		return showPortAname;
	}

	public void setShowPortAname(String showPortAname) {
		this.showPortAname = showPortAname;
	}

	public String getShowPortZname() {
		return showPortZname;
	}

	public List<QosInfo> getQosList() {
		return qosList;
	}

	public void setQosList(List<QosInfo> qosList) {
		this.qosList = qosList;
	}

	public void setShowPortZname(String showPortZname) {
		this.showPortZname = showPortZname;
	}

	public int getIsReverse() {
		return isReverse;
	}

	public void setIsReverse(int isReverse) {
		this.isReverse = isReverse;
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

	public int getProtectTunnelId() {
		return protectTunnelId;
	}

	public void setProtectTunnelId(int protectTunnelId) {
		this.protectTunnelId = protectTunnelId;
	}

	public String getProtectTunnelName() {
		return protectTunnelName;
	}

	public void setProtectTunnelName(String protectTunnelName) {
		this.protectTunnelName = protectTunnelName;
	}

	public int getProtectType() {
		return protectType;
	}

	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}

	public List<PortInst> getPortInstList() {
		return portInstList;
	}

	public void setPortInstList(List<PortInst> portInstList) {
		this.portInstList = portInstList;
	}

	public List<OamInfo> getOamList() {
		return oamList;
	}

	public void setOamList(List<OamInfo> oamList) {
		this.oamList = oamList;
	}

	public boolean isNode() {
		return node;
	}

	public void setNode(boolean node) {
		this.node = node;
	}

	public int getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(int isSingle) {
		this.isSingle = isSingle;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getBefore_activity() {
		return before_activity;
	}

	public void setBefore_activity(int before_activity) {
		this.before_activity = before_activity;
	}

	public Tunnel getProtectTunnel() {
		return protectTunnel;
	}

	public void setProtectTunnel(Tunnel protectTunnel) {
		this.protectTunnel = protectTunnel;
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

	public boolean isSynchro() {
		return isSynchro;
	}

	public void setSynchro(boolean isSynchro) {
		this.isSynchro = isSynchro;
	}

	public int getRotate_a() {
		return rotate_a;
	}

	public void setRotate_a(int rotate_a) {
		this.rotate_a = rotate_a;
	}

	public int getRotate_z() {
		return rotate_z;
	}

	public void setRotate_z(int rotate_z) {
		this.rotate_z = rotate_z;
	}

	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getTunnelId());
			this.putClientProperty("tunnelName", getTunnelName());
			this.putClientProperty("activeStates", getTunnelStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
			this.putClientProperty("creater", getCreateUser());
			if (!isNode()) {// 端到端对象
				this.putClientProperty("isReversed", getIsReverse() == 0 ? Boolean.FALSE : Boolean.TRUE);
				this.putClientProperty("zsiteName", getShowSiteZname());
				this.putClientProperty("aportName", getShowPortAname());
				this.putClientProperty("asiteName", getShowSiteAname());
				this.putClientProperty("zportName", getShowPortZname());
				this.putClientProperty("direction", ResourceUtil.srcStr(StringKeysTab.TAB_TWOWAY));
				this.putClientProperty("protectTunnelId", getProtectTunnelId());
				this.putClientProperty("protectType", getProtectType() == 0 ? "" : UiUtil.getCodeById(getProtectType()).getCodeName());
				if(this.getTunnelType().equals("0"))
				{
					this.putClientProperty("tunneltype",ResourceUtil.srcStr(StringKeysLbl.LBL_PROTECT));
				}
				else
				{
					this.putClientProperty("tunneltype", UiUtil.getCodeById(Integer.parseInt(this.getTunnelType())).getCodeName());
				}
				
			} else {
				this.putClientProperty("jobStatus", super.getJobStatus(this.getJobStatus()));
				this.putClientProperty("isSingle", this.getIsSingle() == 0 ? ResourceUtil.srcStr(StringKeysObj.OBJ_NO) : ResourceUtil.srcStr(StringKeysObj.OBJ_YES));
				String role = "";
				if (this.getASiteId() == ConstantUtil.siteId) {
					role = "INGRESS";
				} else if (this.getZSiteId() == ConstantUtil.siteId) {
					role = "EGRESS";
				} else {
					role = "XC";
				}
				this.putClientProperty("role", role);
				if ("XC".equals(role)) {
					this.putClientProperty("tunneltype", UiUtil.getCodeByValue("PROTECTTYPE", "1").getCodeName());
				} else {
					this.putClientProperty("tunneltype", UiUtil.getCodeById(Integer.parseInt(this.getTunnelType())).getCodeName());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	public static List<Tunnel> getTunnelList(Tunnel tunnel) {
		TunnelService_MB tunnelService = null;
		List<Tunnel> tunnelList = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelService.select(tunnel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,Tunnel.class);
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
		return tunnelList;

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getProtectBack() {
		return protectBack;
	}

	public void setProtectBack(int protectBack) {
		this.protectBack = protectBack;
	}

	public int getAprotectId() {
		return aprotectId;
	}

	public void setAprotectId(int aprotectId) {
		this.aprotectId = aprotectId;
	}

	public int getZprotectId() {
		return zprotectId;
	}

	public void setZprotectId(int zprotectId) {
		this.zprotectId = zprotectId;
	}

	public String getSncpIds() {
		return sncpIds;
	}

	public void setSncpIds(String sncpIds) {
		this.sncpIds = sncpIds;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public void setRole(String roleCondition) {
		this.role = roleCondition;
	}

	public String getRole() {
		return role;
	}

	public int getaSiteId() {
		return aSiteId;
	}

	public void setaSiteId(int aSiteId) {
		this.aSiteId = aSiteId;
	}

	public int getzSiteId() {
		return zSiteId;
	}

	public void setzSiteId(int zSiteId) {
		this.zSiteId = zSiteId;
	}

	public int getaPortId() {
		return aPortId;
	}

	public void setaPortId(int aPortId) {
		this.aPortId = aPortId;
	}

	public int getzPortId() {
		return zPortId;
	}

	public void setzPortId(int zPortId) {
		this.zPortId = zPortId;
	}

	public String getSourceMac() {
		return sourceMac;
	}

	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}

	public String getEndMac() {
		return endMac;
	}

	public void setEndMac(String endMac) {
		this.endMac = endMac;
	}

	public int getaVlanEnable() {
		return aVlanEnable;
	}

	public void setaVlanEnable(int aVlanEnable) {
		this.aVlanEnable = aVlanEnable;
	}

	public int getaOutVlanValue() {
		return aOutVlanValue;
	}

	public void setaOutVlanValue(int aOutVlanValue) {
		this.aOutVlanValue = aOutVlanValue;
	}

	public int getaTp_id() {
		return aTp_id;
	}

	public void setaTp_id(int aTpId) {
		aTp_id = aTpId;
	}

	public int getzVlanEnable() {
		return zVlanEnable;
	}

	public void setzVlanEnable(int zVlanEnable) {
		this.zVlanEnable = zVlanEnable;
	}

	public int getzOutVlanValue() {
		return zOutVlanValue;
	}

	public void setzOutVlanValue(int zOutVlanValue) {
		this.zOutVlanValue = zOutVlanValue;
	}

	public int getzTp_id() {
		return zTp_id;
	}

	public void setzTp_id(int zTpId) {
		zTp_id = zTpId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	
}
