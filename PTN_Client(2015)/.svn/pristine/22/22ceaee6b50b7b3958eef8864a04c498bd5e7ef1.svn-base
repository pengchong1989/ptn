package com.nms.db.bean.ptn.path.pw;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EPwType;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;

public class PwInfo extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1318487589378511002L;
	private String Pw="Pw";//log日志显示时使用
	private int pwId;
	private String pwName ="";
	private String tunnelName;
	private String showaSiteName;
	private String showzSiteName;
	private EPwType type;
	private String businessType;
	private int payload;//负载净荷
	private int pwStatus;
	private int tunnelId;
	private int InlabelValue;
	private int OutlabelValue;
	private int aSiteId;
	private int zSiteId;
	private String direction;
	private String createTime;
	private String createUser;
	private int apwServiceId;//A 端 业务ID
	private int zpwServiceId;//Z 端 业务ID
	private int relatedServiceId;
	private int relatedServiceType;
	private int aPortConfigId;
	private int zPortConfigId;
	private int qosModel;
	private String QoS="QoS";//log日志显示时使用
	private List<QosInfo> qosList = new ArrayList<QosInfo>();
	private String OAM="OAM";//log日志显示时使用
	private List<OamInfo> oamList = new ArrayList<OamInfo>();
	private List<PortInst> portInstList = new ArrayList<PortInst>();
	private boolean node = false;
	private int backInlabelValue;
	private int backOutlabelValue;
	/**
	 * 0 网络层PW
	 * 1  单站
	 */
	private int isSingle;
	private int before_activity;
	private String aoppositeId;
	private String zoppositeId;
	private String jobStatus;
	private PwNniInfo aPwNniInfo;
	private PwNniInfo zPwNniInfo;
	private String PHB2EXP="PHB2EXP";//log日志显示时使用
	private int expStrategy;//策略:0/1=指配EXP/基于PHB到TMC|TMP EXP映射表
	private int expAssignment;//指配EXP:0-7
	private int phbToExpId = 1;//PHB2EXP_ID:1-15
	private int phbStrategy;//策略:0/1=指配PHB/基于TMP EXP 到PHB映射表
	private int phbAssignment;//指配PHB:0/1/2/3/4/5/6/7=BE/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private int expTophbId = 1;//EXP 2 PHB _ID:1-15
	private int aVlanEnable;//a外层VLAN使能
	private int aOutVlanValue;//a外层vlan值
	private String aSourceMac;//a端源mac
	private String atargetMac;//a端目的mac
	private int zVlanEnable;//z外层VLAN使能
	private int zOutVlanValue;//z外层vlan值
	private String zSourceMac;//z端源mac
	private String ztargetMac;//z端目的mac
	private String MSPw="MSPw";//log日志显示时使用
	private List<MsPwInfo> msPwInfos;

	private int inLblMinValue ;//pw过滤条件中的输入最小值
	private int inLblMaxValue ;//pw过滤条件中的输入最大值
	private int outLblMinValue ;//pw过滤条件中的输出最小值
	private int outLblMaxValue ;//pw过滤条件中的输出最大值
	private int portId;

	private int atp_id;//外层TP_ID
	private int ztp_id;//外层TP_ID
	private int role;
	
	public String getPwInfo() {
		return Pw;
	}

	public void setPwInfo(String pwInfo) {
		Pw = pwInfo;
	}

	public String getQoS() {
		return QoS;
	}

	public void setQoS(String qoS) {
		QoS = qoS;
	}

	public String getOAM() {
		return OAM;
	}

	public void setOAM(String oAM) {
		OAM = oAM;
	}

	public String getPHB2EXP() {
		return PHB2EXP;
	}

	public void setPHB2EXP(String pHB2EXP) {
		PHB2EXP = pHB2EXP;
	}

	public String getMSPwInfo() {
		return MSPw;
	}

	public void setMSPwInfo(String mSPwInfo) {
		MSPw = mSPwInfo;
	}

	public int getQosModel() {
		return qosModel;
	}

	public void setQosModel(int qosModel) {
		this.qosModel = qosModel;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getAtp_id() {
		return atp_id;
	}

	public void setAtp_id(int atpId) {
		atp_id = atpId;
	}

	public int getZtp_id() {
		return ztp_id;
	}

	public void setZtp_id(int ztpId) {
		ztp_id = ztpId;
	}

	public PwNniInfo getaPwNniInfo() {
		return aPwNniInfo;
	}

	public void setaPwNniInfo(PwNniInfo aPwNniInfo) {
		this.aPwNniInfo = aPwNniInfo;
	}

	public PwNniInfo getzPwNniInfo() {
		return zPwNniInfo;
	}

	public void setzPwNniInfo(PwNniInfo zPwNniInfo) {
		this.zPwNniInfo = zPwNniInfo;
	}

	public String getTunnelName() {
		return tunnelName;
	}

	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}

	public int getPwId() {
		return pwId;
	}

	public void setPwId(int pwId) {
		this.pwId = pwId;
	}

	public String getPwName() {
		return pwName;
	}

	public void setPwName(String pwName) {
		this.pwName = pwName;
	}

	public int getPwStatus() {
		return pwStatus;
	}

	public void setPwStatus(int pwStatus) {
		this.pwStatus = pwStatus;
	}

	public int getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}

	public int getInlabelValue() {
		return InlabelValue;
	}

	public void setInlabelValue(int inlabelValue) {
		InlabelValue = inlabelValue;
	}

	public int getOutlabelValue() {
		return OutlabelValue;
	}

	public void setOutlabelValue(int outlabelValue) {
		OutlabelValue = outlabelValue;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public String getShowaSiteName() {
		return showaSiteName;
	}

	public void setShowaSiteName(String showaSiteName) {
		this.showaSiteName = showaSiteName;
	}

	public String getShowzSiteName() {
		return showzSiteName;
	}

	public void setShowzSiteName(String showzSiteName) {
		this.showzSiteName = showzSiteName;
	}

	public List<QosInfo> getQosList() {
		return qosList;
	}

	public void setQosList(List<QosInfo> qosList) {
		this.qosList = qosList;
	}

	public List<PortInst> getPortInstList() {
		return portInstList;
	}

	public void setPortInstList(List<PortInst> portInstList) {
		this.portInstList = portInstList;
	}

	public int getApwServiceId() {
		return apwServiceId;
	}

	public void setApwServiceId(int apwServiceId) {
		this.apwServiceId = apwServiceId;
	}

	public int getZpwServiceId() {
		return zpwServiceId;
	}

	public void setZpwServiceId(int zpwServiceId) {
		this.zpwServiceId = zpwServiceId;
	}

	public int getRelatedServiceId() {
		return relatedServiceId;
	}

	public void setRelatedServiceId(int relatedServiceId) {
		this.relatedServiceId = relatedServiceId;
	}

	public int getRelatedServiceType() {
		return relatedServiceType;
	}

	public void setRelatedServiceType(int relatedServiceType) {
		this.relatedServiceType = relatedServiceType;
	}

	public int getaPortConfigId() {
		return aPortConfigId;
	}

	public void setaPortConfigId(int aPortConfigId) {
		this.aPortConfigId = aPortConfigId;
	}

	public int getzPortConfigId() {
		return zPortConfigId;
	}

	public void setzPortConfigId(int zPortConfigId) {
		this.zPortConfigId = zPortConfigId;
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

	public EPwType getType() {
		return type;
	}

	public void setType(EPwType type) {
		this.type = type;
	}

	public int getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(int isSingle) {
		this.isSingle = isSingle;
	}

	public String getAoppositeId() {
		return aoppositeId;
	}

	public void setAoppositeId(String aoppositeId) {
		this.aoppositeId = aoppositeId;
	}

	public String getZoppositeId() {
		return zoppositeId;
	}

	public void setZoppositeId(String zoppositeId) {
		this.zoppositeId = zoppositeId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getPwId());
			this.putClientProperty("pwName", getPwName());
			this.putClientProperty("activeStates", getPwStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("type", this.getType());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
			this.putClientProperty("creater", getCreateUser());
			this.putClientProperty("jobStatus", super.getJobStatus(this.getJobStatus()));
			if (!isNode()) {// 端到端对象
				this.putClientProperty("serviceType", (UiUtil.getCodeByValue("BUSINESSTYPE", getBusinessType())).getCodeName());
//				this.putClientProperty("serviceType", (UiUtil.getCodeByValue("BUSINESSTYPE", getBusinessType())).getCodeName());
				this.putClientProperty("type", getType());
				this.putClientProperty("inLabelValue", this.getInlabelValue());
				this.putClientProperty("outLabelValue", this.getOutlabelValue());
				this.putClientProperty("zsiteName", getShowzSiteName());
				// this.putClientProperty("aportConfigName", getPortConfigName(getaPortConfigId()));
				this.putClientProperty("asiteName", getShowaSiteName());
				this.putClientProperty("direction", ResourceUtil.srcStr(StringKeysTab.TAB_TWOWAY));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	public int getBefore_activity() {
		return before_activity;
	}

	public void setBefore_activity(int before_activity) {
		this.before_activity = before_activity;
	}

	public int getExpStrategy() {
		return expStrategy;
	}

	public void setExpStrategy(int expStrategy) {
		this.expStrategy = expStrategy;
	}

	public int getExpAssignment() {
		return expAssignment;
	}

	public void setExpAssignment(int expAssignment) {
		this.expAssignment = expAssignment;
	}

	public int getPhbToExpId() {
		return phbToExpId;
	}

	public void setPhbToExpId(int phbToExpId) {
		this.phbToExpId = phbToExpId;
	}

	public int getPhbStrategy() {
		return phbStrategy;
	}

	public void setPhbStrategy(int phbStrategy) {
		this.phbStrategy = phbStrategy;
	}

	public int getPhbAssignment() {
		return phbAssignment;
	}

	public void setPhbAssignment(int phbAssignment) {
		this.phbAssignment = phbAssignment;
	}

	public int getExpTophbId() {
		return expTophbId;
	}

	public void setExpTophbId(int expTophbId) {
		this.expTophbId = expTophbId;
	}

	public int getPayload() {
		return payload;
	}

	public void setPayload(int payload) {
		this.payload = payload;
	}

	public String getaSourceMac() {
		return aSourceMac;
	}

	public void setaSourceMac(String aSourceMac) {
		this.aSourceMac = aSourceMac;
	}

	public String getAtargetMac() {
		return atargetMac;
	}

	public void setAtargetMac(String atargetMac) {
		this.atargetMac = atargetMac;
	}

	public String getzSourceMac() {
		return zSourceMac;
	}

	public void setzSourceMac(String zSourceMac) {
		this.zSourceMac = zSourceMac;
	}

	public String getZtargetMac() {
		return ztargetMac;
	}

	public void setZtargetMac(String ztargetMac) {
		this.ztargetMac = ztargetMac;
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

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public List<MsPwInfo> getMsPwInfos() {
		return msPwInfos;
	}

	public void setMsPwInfos(List<MsPwInfo> msPwInfos) {
		this.msPwInfos = msPwInfos;
	}

	public int getBackInlabel() {
		return backInlabelValue;
	}

	public void setBackInlabel(int backInlabel) {
		this.backInlabelValue = backInlabel;
	}

	public int getBackOutlabel() {
		return backOutlabelValue;
	}

	public void setBackOutlabel(int backOutlabel) {
		this.backOutlabelValue = backOutlabel;
	}

	public void setOutLblMaxValue(int outLblMaxValue) {
		this.outLblMaxValue = outLblMaxValue;
	}

	public int getOutLblMaxValue() {
		return outLblMaxValue;
	}

	public void setOutLblMinValue(int outLblMinValue) {
		this.outLblMinValue = outLblMinValue;
	}

	public int getOutLblMinValue() {
		return outLblMinValue;
	}

	public void setInLblMaxValue(int inLblMaxValue) {
		this.inLblMaxValue = inLblMaxValue;
	}

	public int getInLblMaxValue() {
		return inLblMaxValue;
	}

	public void setInLblMinValue(int inLblMinValue) {
		this.inLblMinValue = inLblMinValue;
	}

	public int getInLblMinValue() {
		return inLblMinValue;
	}
	
	public int getPortId()
	{
		return portId;
	}

	public void setPortId(int portId)
	{
		this.portId = portId;
	}
}
