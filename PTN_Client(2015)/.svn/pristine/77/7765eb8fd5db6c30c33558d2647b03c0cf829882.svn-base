package com.nms.db.bean.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class Segment extends ViewDataObj {

	private static final long serialVersionUID = -1213233315027033332L;
	private String Segment="Segment";//log日志显示时使用
	private int id;
	private String NAME;
	private int BANDWIDTH;
	private int TYPE;
	private int ASITEID;
	private int ZSITEID;
	private int APORTID;
	private int ZPORTID;
	private String CREATUSER;
	private String CREATTIME;
	private String ShowSiteAname;
	private String ShowSiteZname;
	private String ShowPortAname;
	private String ShowPortZname;
	private int aSlotNumber;
	private int zSlotNumber;
	private String speedSegment;//用于显示速率	对应code
	private String OAM="OAM";//log日志显示时使用
	private List<OamInfo> oamList = new ArrayList<OamInfo>();
	private String Qosqueue="Qosqueue";//log日志显示时使用
	private Map<Integer, List<QosQueue>> qosMap = new HashMap<Integer, List<QosQueue>>();
	private List<QosQueue> aqosqueue = new ArrayList<QosQueue>();
	private List<QosQueue> zqosqueue = new ArrayList<QosQueue>();	
	private PortInst aPortInst = new PortInst();
	private PortInst zPortInst = new PortInst();
	private PortInst oldaPortInst = new PortInst();
	private PortInst oldzPortInst = new PortInst();
	
	private String Port="Port";
    private CommonBean aCommonBean;
    private CommonBean zCommonBean;
	
	
	
	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public CommonBean getzCommonBean() {
		return zCommonBean;
	}

	public void setzCommonBean(CommonBean zCommonBean) {
		this.zCommonBean = zCommonBean;
	}

	public CommonBean getaCommonBean() {
		return aCommonBean;
	}

	public void setaCommonBean(CommonBean aCommonBean) {
		this.aCommonBean = aCommonBean;
	}

	public PortInst getoldzPortInst() {
		return oldzPortInst;
	}

	public void setoldzPortInst(PortInst oldzPortInst) {
		this.oldzPortInst = oldzPortInst;
	}

	public PortInst getoldaPortInst() {
		return oldaPortInst;
	}

	public void setoldaPortInst(PortInst oldaPortInst) {
		this.oldaPortInst = oldaPortInst;
	}
	
	
	public PortInst getzPortInst() {
		return zPortInst;
	}

	public void setzPortInst(PortInst zPortInst) {
		this.zPortInst = zPortInst;
	}

	public PortInst getaPortInst() {
		return aPortInst;
	}

	public void setaPortInst(PortInst aPortInst) {
		this.aPortInst = aPortInst;
	}

	public String getSegment() {
		return Segment;
	}

	public void setSegment(String segment) {
		Segment = segment;
	}
	
	public String getQosqueue() {
		return Qosqueue;
	}

	public void setQosqueue(String Qos) {
		Qosqueue = Qos;
	}
	
	public String getOAM() {
		return OAM;
	}

	public void setOAM(String oam) {
		OAM = oam;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getBANDWIDTH() {
		return BANDWIDTH;
	}

	public void setBANDWIDTH(int bANDWIDTH) {
		BANDWIDTH = bANDWIDTH;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}

	public int getASITEID() {
		return ASITEID;
	}

	public void setASITEID(int aSITEID) {
		ASITEID = aSITEID;
	}

	public int getZSITEID() {
		return ZSITEID;
	}

	public void setZSITEID(int zSITEID) {
		ZSITEID = zSITEID;
	}

	public int getAPORTID() {
		return APORTID;
	}

	public void setAPORTID(int aPORTID) {
		APORTID = aPORTID;
	}

	public int getZPORTID() {
		return ZPORTID;
	}

	public void setZPORTID(int zPORTID) {
		ZPORTID = zPORTID;
	}

	public String getCREATUSER() {
		return CREATUSER;
	}

	public void setCREATUSER(String cREATUSER) {
		CREATUSER = cREATUSER;
	}

	public String getCREATTIME() {
		return CREATTIME;
	}

	public void setCREATTIME(String cREATTIME) {
		CREATTIME = cREATTIME;
	}

	public String getShowSiteAname() {
		return ShowSiteAname;
	}

	public void setShowSiteAname(String showSiteAname) {
		ShowSiteAname = showSiteAname;
	}

	public String getShowSiteZname() {
		return ShowSiteZname;
	}

	public void setShowSiteZname(String showSiteZname) {
		ShowSiteZname = showSiteZname;
	}

	public String getShowPortAname() {
		return ShowPortAname;
	}

	public void setShowPortAname(String showPortAname) {
		ShowPortAname = showPortAname;
	}

	public String getShowPortZname() {
		return ShowPortZname;
	}

	public void setShowPortZname(String showPortZname) {
		ShowPortZname = showPortZname;
	}

	public List<OamInfo> getOamList() {
		return oamList;
	}

	public void setOamList(List<OamInfo> oamList) {
		this.oamList = oamList;
	}

	public Map<Integer, List<QosQueue>> getQosMap() {
		return qosMap;
	}

	public void setQosMap(Map<Integer, List<QosQueue>> qosMap) {
		this.qosMap = qosMap;
	}

	public List<QosQueue> getAqosqueue() {
		return aqosqueue;
	}

	public void setAqosqueue(List<QosQueue> aqosqueue) {
		this.aqosqueue = aqosqueue;
	}

	public List<QosQueue> getZqosqueue() {
		return zqosqueue;
	}

	public void setZqosqueue(List<QosQueue> zqosqueue) {
		this.zqosqueue = zqosqueue;
	}

	public int getaSlotNumber() {
		return aSlotNumber;
	}

	public void setaSlotNumber(int aSlotNumber) {
		this.aSlotNumber = aSlotNumber;
	}

	public int getzSlotNumber() {
		return zSlotNumber;
	}

	public void setzSlotNumber(int zSlotNumber) {
		this.zSlotNumber = zSlotNumber;
	}

	public String getSpeedSegment() {
		return speedSegment;
	}

	public void setSpeedSegment(String speedSegment) {
		this.speedSegment = speedSegment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("name", getNAME());
			getClientProperties().put("bandwidth", getBANDWIDTH());
			if(null!=this.getSpeedSegment() && !"".equals(this.getSpeedSegment())){
				getClientProperties().put("speedSegment", UiUtil.getCodeById(Integer.parseInt(this.getSpeedSegment())).getCodeName());
			}
			getClientProperties().put("asiteName", getShowSiteAname());
			getClientProperties().put("zsiteName", getShowSiteZname());
			getClientProperties().put("aportName", getShowPortAname());
			getClientProperties().put("zportName", getShowPortZname());
			getClientProperties().put("createUser", getCREATUSER());
			getClientProperties().put("createTimer", getCREATTIME());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

}
