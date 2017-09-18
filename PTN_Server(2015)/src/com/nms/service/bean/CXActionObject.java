package com.nms.service.bean;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.weihu.PowerObject;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.acn.ACNObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.drivechenxiao.service.bean.clock.TodObject;
import com.nms.drivechenxiao.service.bean.lag.LagObject;
import com.nms.drivechenxiao.service.bean.mcn.MCNObject;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;
import com.nms.drivechenxiao.service.bean.oam.OamMipObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFAREAObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFObject;
import com.nms.drivechenxiao.service.bean.ospf.interfaces.OSPFinterfacesObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.drivechenxiao.service.bean.porteth.uni.UNIObject;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.portpdh.ac.PdhAcObject;
import com.nms.drivechenxiao.service.bean.protocols.CardProObject;
import com.nms.drivechenxiao.service.bean.protocols.Cos2vlanpriObject;
import com.nms.drivechenxiao.service.bean.protocols.DualObject;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpPortObject;
import com.nms.drivechenxiao.service.bean.protocols.StaticRouteObject;
import com.nms.drivechenxiao.service.bean.protocols.Vlanpri2cngObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.RingObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.CostoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.ExptocosObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ClrtoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ExptoclrObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.redistribute.RedistributeObject;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.Protection;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.drivechenxiao.service.bean.xc.Route;
import com.nms.drivechenxiao.service.bean.xc.XcObject;

public class CXActionObject {

	private int actionId = 0; // 唯一标识
	private String status = ""; // 状态
	private CXNEObject cxNeObject = new CXNEObject();// 网元对象
	private String type = ""; // 类型 tunnel pw等
	private String action = ""; // 动作 insert update等
	private String oamPath = ""; // oam路径
	private String oamType = ""; // oam类型

	private RingObject ringObject = new RingObject();
	private TodObject todObject = new TodObject();
	private EthPortObject ethPortObject = new EthPortObject();
	private PdhPortObject pdhPortObject = new PdhPortObject();
	private SdhPortObject sdhPortObject = new SdhPortObject();
	private UNIObject uNIObject = new UNIObject();
	private NNIObject nNIObject = new NNIObject();
	private AcObject acObject = new AcObject();
	private PdhAcObject pdhAcObject = new PdhAcObject();
	private SdhAcObject sdhAcObject = new SdhAcObject();
	private TunnelObject tunnelObject = new TunnelObject();
	private PwEthObject pwEthObject = new PwEthObject();
	private PwPdhObject pwPdhObject = new PwPdhObject();
	private PwSdhObject pwSdhObject = new PwSdhObject();
	private ELanObject elanObject = new ELanObject();
	private ELineObject elineObject = new ELineObject();
	private ETreeObject etreeObject = new ETreeObject();
	private CesObject cesObject = new CesObject();
	private QosObject qosObject = new QosObject();	
	
	private AcQosObject acQosObject = new AcQosObject();
	
	private XcObject xcObject = new XcObject();
	private OamObject oamObject = new OamObject();
	private SlotObject slotObject = new SlotObject();
	private PtnNeObject ptnNeObject = new PtnNeObject();
	private List<PwEthObject> pwEthObjectList = new ArrayList<PwEthObject>();
	private List<AlarmObject> alarmObjectList = new ArrayList<AlarmObject>();
	private List<PersvrObject> persvrobjectList = new ArrayList<PersvrObject>();//性能返回
	private SlotObject[] slotObjectArray = new SlotObject[0];
	private OSPFObject OSPFObject = new OSPFObject();
	private OSPFAREAObject oSPFAREAObject = new OSPFAREAObject();
	private RedistributeObject redistributeObject = new RedistributeObject();
	private MCNObject mcnObject = new MCNObject();
	private CCNObject ccnObject = new CCNObject();
	private OSPFinterfacesObject OSPFInterfacesObject = new OSPFinterfacesObject();
	private LagObject lagObject = new LagObject();
	private List<RingObject> ringObjectList = new ArrayList<RingObject>();
	private List<EthPortObject> ethObjectList = new ArrayList<EthPortObject>();
	private List<TunnelObject> tunnelObjectList = new ArrayList<TunnelObject>();
	private List<LSPObject> lspObjectList = new ArrayList<LSPObject>();
	private List<PwPdhObject> pwPdhObjectList = new ArrayList<PwPdhObject>();
	private List<PwSdhObject> pwSdhObjectList = new ArrayList<PwSdhObject>();
	private List<ELineObject> elineObjectList = new ArrayList<ELineObject>();
	private List<CesObject> cesObjectList = new ArrayList<CesObject>();
	private List<XcObject> xcObjectList = new ArrayList<XcObject>();
	private List<AcObject> acObjectList = new ArrayList<AcObject>();
	private List<QosObject> qosObjectList = new ArrayList<QosObject>();
	private List<ETreeObject> etreeObjectList = new ArrayList<ETreeObject>();
	private List<AcQosObject> acQosObjectList = new ArrayList<AcQosObject>();
	private List<ELanObject> elanObjectList = new ArrayList<ELanObject>();
	private List<EthPortObject> ethPortObjectList = new ArrayList<EthPortObject>();
	private List<CCNObject> ccnObjectList = new ArrayList<CCNObject>();
	private List<OSPFAREAObject> ospfAREAObjectList = new ArrayList<OSPFAREAObject>();
	private List<RedistributeObject> redistributeObjectList = new ArrayList<RedistributeObject>();
	private List<PdhPortObject> pdhPortObjectList = new ArrayList<PdhPortObject>();
	private List<MCNObject> mcnObjectList = new ArrayList<MCNObject>();
	private List<OSPFinterfacesObject> ospfInterfacesObjectList = new ArrayList<OSPFinterfacesObject>();
	private List<SdhPortObject> sdhPortObjectList = new ArrayList<SdhPortObject>();
	private List<SdhAcObject> sdhAcObjectList = new ArrayList<SdhAcObject>();
	private Protection protection = new Protection();
	private OamMipObject oamMipObject = new OamMipObject();
	private int slot = 0; // 要查询告警的设备的板卡号
	private String persvrTime = ""; // 查询告警是15分钟还是24小时 m15/h24
	private List<LagObject> lagObjectList = new ArrayList<LagObject>();
	private List<ACNObject> acnObjectList = new ArrayList<ACNObject>(); //acn对象
	private PtpObject ptpObject = new PtpObject();
	private PtpPortObject ptpPortObject = new PtpPortObject();
	private List<PtpPortObject> ptpPortList=new ArrayList<PtpPortObject>();
	private List<PtpObject> ptpObjectList = new ArrayList<PtpObject>(); //时钟
	private ClockObject clockObject=new ClockObject();
	private List<ClockObject> clockObjectList = new ArrayList<ClockObject>(); //时钟	状态
	private ClockPortESObject clockPortESObject=new ClockPortESObject();
	private List<ClockPortESObject> clockPortESObjectList = new ArrayList<ClockPortESObject>(); //时钟	状态
	private ExtclkObject extclkObject=new ExtclkObject();//外接时钟接口
	private List<ExtclkObject> extclkObjectList = new ArrayList<ExtclkObject>();
	private List<TodObject> TodObjectList = new ArrayList<TodObject>();
	private MspObject mspObject=new MspObject();//Msp保护
	private List<MspObject> mapObjectList = new ArrayList<MspObject>();
	private EfmObject efmObject=new EfmObject();
	private List<EfmObject> efmObjectList = new ArrayList<EfmObject>();
	private DualObject dualObject=new DualObject();
	private List<DualObject> dualObjectList = new ArrayList<DualObject>();
	private ClrtoexpObject clrtoexpObject=new ClrtoexpObject();
	private List<ClrtoexpObject> ClrtoexpObjectList = new ArrayList<ClrtoexpObject>();
	private CostoexpObject costoexpObject=new CostoexpObject();
	private List<CostoexpObject> CostoexpObjectList = new ArrayList<CostoexpObject>();
	private ExptoclrObject exptoclrObject=new ExptoclrObject();
	private List<ExptoclrObject> exptoclrObjectList = new ArrayList<ExptoclrObject>();
	private ExptocosObject exptocosObject=new ExptocosObject();
	private List<ExptocosObject> exptocosObjectList = new ArrayList<ExptocosObject>();
	private String persvrFileName = ""; // 查询告警 文件名 m15-201207130730.per ; h24-20120712.per
    private Cos2vlanpriObject cos2vlanpriObject = new Cos2vlanpriObject();
    private List<Cos2vlanpriObject> cos2vlanpriObjectList = new ArrayList<Cos2vlanpriObject>();
    private Vlanpri2cngObject vlanpri2cngObject = new Vlanpri2cngObject();
    private List<Vlanpri2cngObject> vlanpri2cngObjectList = new ArrayList<Vlanpri2cngObject>();
    private StaticRouteObject staticRouteObject=new StaticRouteObject();
    private SlotTempObject slotTempObject=new SlotTempObject();
    private List<SlotTempObject> slotTempList=new ArrayList<SlotTempObject>();    
	private List<PwEthObject> pwUpdate=new ArrayList<PwEthObject>();
	private PowerObject powerObject=new PowerObject();//光功率
	private CardProObject cardProObject=new CardProObject();//板卡保护
	private Route route=new Route();
	private List<Route> routeList=new ArrayList<Route>();
	
	

	public List<Route> getRouteList() {
		return routeList;
	}
	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public CardProObject getCardProObject() {
		return cardProObject;
	}
	public void setCardProObject(CardProObject cardProObject) {
		this.cardProObject = cardProObject;
	}
	public PowerObject getPowerObject() {
		return powerObject;
	}
	public void setPowerObject(PowerObject powerObject) {
		this.powerObject = powerObject;
	}
	public List<PwEthObject> getPwUpdate() {
		return pwUpdate;
	}
	public void setPwUpdate(List<PwEthObject> pwUpdate) {
		this.pwUpdate = pwUpdate;
	}
	public List<SlotTempObject> getSlotTempList() {
		return slotTempList;
	}
	public void setSlotTempList(List<SlotTempObject> slotTempList) {
		this.slotTempList = slotTempList;
	}
	public SlotTempObject getSlotTempObject() {
		return slotTempObject;
	}
	public void setSlotTempObject(SlotTempObject slotTempObject) {
		this.slotTempObject = slotTempObject;
	}
	public StaticRouteObject getStaticRouteObject() {
		return staticRouteObject;
	}
	public void setStaticRouteObject(StaticRouteObject staticRouteObject) {
		this.staticRouteObject = staticRouteObject;
	}
	public String getPersvrFileName() {
		return persvrFileName;
	}
	public void setPersvrFileName(String persvrFileName) {
		this.persvrFileName = persvrFileName;
	}
	public ExptocosObject getExptocosObject() {
		return exptocosObject;
	}
	public void setExptocosObject(ExptocosObject exptocosObject) {
		this.exptocosObject = exptocosObject;
	}
	public ExptoclrObject getExptoclrObject() {
		return exptoclrObject;
	}
	
	public CostoexpObject getCostoexpObject() {
		return costoexpObject;
	}
	
	public ClrtoexpObject getClrtoexpObject() {
		return clrtoexpObject;
	}
	public void setClrtoexpObject(ClrtoexpObject clrtoexpObject) {
		this.clrtoexpObject = clrtoexpObject;
	}
	public DualObject getDualObject() {
		return dualObject;
	}
	public void setDualObject(DualObject dualObject) {
		this.dualObject = dualObject;
	}
	public EfmObject getEfmObject() {
		return efmObject;
	}
	public void setEfmObject(EfmObject efmObject) {
		this.efmObject = efmObject;
	}
	public MspObject getMspObject() {
		return mspObject;
	}
	public void setMspObject(MspObject mspObject) {
		this.mspObject = mspObject;
	}
	public List<MspObject> getMapObjectList() {
		return mapObjectList;
	}
	public void setMapObjectList(List<MspObject> mapObjectList) {
		this.mapObjectList = mapObjectList;
	}
	public List<PtpPortObject> getPtpPortList() {
		return ptpPortList;
	}
	public void setPtpPortList(List<PtpPortObject> ptpPortList) {
		this.ptpPortList = ptpPortList;
	}
	public PtpPortObject getPtpPortObject() {
		return ptpPortObject;
	}
	public void setPtpPortObject(PtpPortObject ptpPortObject) {
		this.ptpPortObject = ptpPortObject;
	}
	public PtpObject getPtpObject() {
		return ptpObject;
		}
	public List<TodObject> getTodObjectList() {
		return TodObjectList;
	}

	public void setTodObjectList(List<TodObject> todObjectList) {
		TodObjectList = todObjectList;
	}

	public List<ExtclkObject> getExtclkObjectList() {
		return extclkObjectList;
	}

	public void setExtclkObjectList(List<ExtclkObject> extclkObjectList) {
		this.extclkObjectList = extclkObjectList;
	}

	public TodObject getTodObject() {
		return todObject;
	}

	public void setPtpObject(PtpObject ptpObject) {
		this.ptpObject = ptpObject;
	}

	public List<PtpObject> getPtpObjectList() {
		return ptpObjectList;
	}
	public ExtclkObject getExtclkObject() {
		return extclkObject;
	}

	public void setPtpObjectList(List<PtpObject> ptpObjectList) {
		this.ptpObjectList = ptpObjectList;
	}
	public void setExtclkObject(ExtclkObject extclkObject) {
		this.extclkObject = extclkObject;
	}

	public ClockObject getClockObject() {
		return clockObject;
	}

	public void setClockObject(ClockObject clockObject) {
		this.clockObject = clockObject;
	}

	public List<ClockObject> getClockObjectList() {
		return clockObjectList;
	}

	public void setClockObjectList(List<ClockObject> clockObjectList) {
		this.clockObjectList = clockObjectList;
	}

	public void setTodObject(TodObject todObject) {
		this.todObject = todObject;
	}

	public ExtclkObject getExtlkObject() {
		return extclkObject;
	}

	public void setExtlkObject(ExtclkObject extlkObject) {
		this.extclkObject = extlkObject;
	}

	

	public RingObject getRingObject() {
		return ringObject;
	}

	public void setRingObject(RingObject ringObject) {
		this.ringObject = ringObject;
	}

	public List<RingObject> getRingObjectList() {
		return ringObjectList;
	}

	public void setRingObjectList(List<RingObject> ringObjectList) {
		this.ringObjectList = ringObjectList;
	}

	public OamMipObject getOamMipObject() {
		return oamMipObject;
	}

	public void setOamMipObject(OamMipObject oamMipObject) {
		this.oamMipObject = oamMipObject;
	}

	public Protection getProtection() {
		return protection;
	}

	public void setProtection(Protection protection) {
		this.protection = protection;
	}

	public String getOamType() {
		return oamType;
	}

	public void setOamType(String oamType) {
		this.oamType = oamType;
	}

	public String getOamPath() {
		return oamPath;
	}

	public void setOamPath(String oamPath) {
		this.oamPath = oamPath;
	}

	public List<CCNObject> getCcnObjectList() {
		return ccnObjectList;
	}

	public void setCcnObjectList(List<CCNObject> ccnObjectList) {
		this.ccnObjectList = ccnObjectList;
	}

	public List<EthPortObject> getEthObjectList() {
		return ethObjectList;
	}

	public void setEthObjectList(List<EthPortObject> ethObjectList) {
		this.ethObjectList = ethObjectList;
	}

	public LagObject getLagObject() {
		return lagObject;
	}

	public void setLagObject(LagObject lagObject) {
		this.lagObject = lagObject;
	}

	public OSPFinterfacesObject getOSPFInterfacesObject() {
		return OSPFInterfacesObject;
	}

	public void setOSPFInterfacesObject(OSPFinterfacesObject interfacesObject) {
		OSPFInterfacesObject = interfacesObject;
	}

	public CCNObject getCcnObject() {
		return ccnObject;
	}

	public void setCcnObject(CCNObject ccnObject) {
		this.ccnObject = ccnObject;
	}

	public MCNObject getMcnObject() {
		return mcnObject;
	}

	public void setMcnObject(MCNObject mcnObject) {
		this.mcnObject = mcnObject;
	}

	public OSPFAREAObject getOSPFAREAObject() {
		return oSPFAREAObject;
	}

	public void setOSPFAREAObject(OSPFAREAObject object) {
		oSPFAREAObject = object;
	}

	public OSPFObject getOSPFObject() {
		return OSPFObject;
	}

	public void setOSPFObject(OSPFObject object) {
		OSPFObject = object;
	}

	public PtnNeObject getPtnNeObject() {
		return ptnNeObject;
	}

	public void setPtnNeObject(PtnNeObject ptnNeObject) {
		this.ptnNeObject = ptnNeObject;
	}

	public PdhAcObject getPdhAcObject() {
		return pdhAcObject;
	}

	public void setPdhAcObject(PdhAcObject pdhAcObject) {
		this.pdhAcObject = pdhAcObject;
	}

	public CesObject getCesObject() {
		return cesObject;
	}

	public void setCesObject(CesObject cesObject) {
		this.cesObject = cesObject;
	}

	public PwPdhObject getPwPdhObject() {
		return pwPdhObject;
	}

	public void setPwPdhObject(PwPdhObject pwPdhObject) {
		this.pwPdhObject = pwPdhObject;
	}

	public PwSdhObject getPwSdhObject() {
		return pwSdhObject;
	}

	public void setPwSdhObject(PwSdhObject pwSdhObject) {
		this.pwSdhObject = pwSdhObject;
	}

	public SdhAcObject getSdhAcObject() {
		return sdhAcObject;
	}

	public void setSdhAcObject(SdhAcObject sdhAcObject) {
		this.sdhAcObject = sdhAcObject;
	}

	public SdhPortObject getSdhPortObject() {
		return sdhPortObject;
	}

	public void setSdhPortObject(SdhPortObject sdhPortObject) {
		this.sdhPortObject = sdhPortObject;
	}

	public PdhPortObject getPdhPortObject() {
		return pdhPortObject;
	}

	public void setPdhPortObject(PdhPortObject pdhPortObject) {
		this.pdhPortObject = pdhPortObject;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public String getPersvrTime() {
		return persvrTime;
	}

	public void setPersvrTime(String persvrTime) {
		this.persvrTime = persvrTime;
	}

	public OamObject getOamObject() {
		return oamObject;
	}

	public void setOamObject(OamObject oamObject) {
		this.oamObject = oamObject;
	}

	public XcObject getXcObject() {
		return xcObject;
	}

	public void setXcObject(XcObject xcObject) {
		this.xcObject = xcObject;
	}

	public AcQosObject getAcQosObject() {
		return acQosObject;
	}

	public void setAcQosObject(AcQosObject acQosObject) {
		this.acQosObject = acQosObject;
	}

	public UNIObject getUNIObject() {
		return uNIObject;
	}

	public void setUNIObject(UNIObject object) {
		uNIObject = object;
	}

	public NNIObject getNNIObject() {
		return nNIObject;
	}

	public void setNNIObject(NNIObject object) {
		nNIObject = object;
	}

	public AcObject getAcObject() {
		return acObject;
	}

	public void setAcObject(AcObject acObject) {
		this.acObject = acObject;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public EthPortObject getEthPortObject() {
		return ethPortObject;
	}

	public void setEthPortObject(EthPortObject ethPortObject) {
		this.ethPortObject = ethPortObject;
	}

	public PwEthObject getPwEthObject() {
		return pwEthObject;
	}

	public void setPwEthObject(PwEthObject pwEthObject) {
		this.pwEthObject = pwEthObject;
	}

	public QosObject getQosObject() {
		return qosObject;
	}

	public void setQosObject(QosObject qosObject) {
		this.qosObject = qosObject;
	}

	public TunnelObject getTunnelObject() {
		return tunnelObject;
	}

	public void setTunnelObject(TunnelObject tunnelObject) {
		this.tunnelObject = tunnelObject;
	}

	public ELanObject getElanObject() {
		return elanObject;
	}

	public void setElanObject(ELanObject elanObject) {
		this.elanObject = elanObject;
	}

	public ELineObject getElineObject() {
		return elineObject;
	}

	public void setElineObject(ELineObject elineObject) {
		this.elineObject = elineObject;
	}

	public ETreeObject getEtreeObject() {
		return etreeObject;
	}

	public void setEtreeObject(ETreeObject etreeObject) {
		this.etreeObject = etreeObject;
	}

	public CXNEObject getCxNeObject() {
		return cxNeObject;
	}

	public void setCxNeObject(CXNEObject cxNeObject) {
		this.cxNeObject = cxNeObject;
	}

	public List<PwEthObject> getPwEthObjectList() {
		return pwEthObjectList;
	}

	public void setPwEthObjectList(List<PwEthObject> pwEthObjectList) {
		this.pwEthObjectList = pwEthObjectList;
	}

	public List<AlarmObject> getAlarmObjectList() {
		return alarmObjectList;
	}

	public void setAlarmObjectList(List<AlarmObject> alarmObjectList) {
		this.alarmObjectList = alarmObjectList;
	}

	public List<PersvrObject> getPersvrobjectList() {
		return persvrobjectList;
	}

	public void setPersvrobjectList(List<PersvrObject> persvrobjectList) {
		this.persvrobjectList = persvrobjectList;
	}

	public SlotObject getSlotObject() {
		return slotObject;
	}

	public void setSlotObject(SlotObject slotObject) {
		this.slotObject = slotObject;
	}

	public SlotObject[] getSlotObjectArray() {
		return slotObjectArray;
	}

	public void setSlotObjectArray(SlotObject[] slotObjectArray) {
		this.slotObjectArray = slotObjectArray;
	}

	public RedistributeObject getRedistributeObject() {
		return redistributeObject;
	}

	public void setRedistributeObject(RedistributeObject redistributeObject) {
		this.redistributeObject = redistributeObject;
	}

	public List<TunnelObject> getTunnelObjectList() {
		return tunnelObjectList;
	}

	public void setTunnelObjectList(List<TunnelObject> tunnelObjectList) {
		this.tunnelObjectList = tunnelObjectList;
	}

	public List<LSPObject> getLspObjectList() {
		return lspObjectList;
	}

	public void setLspObjectList(List<LSPObject> lspObjectList) {
		this.lspObjectList = lspObjectList;
	}

	public List<PwPdhObject> getPwPdhObjectList() {
		return pwPdhObjectList;
	}

	public void setPwPdhObjectList(List<PwPdhObject> pwPdhObjectList) {
		this.pwPdhObjectList = pwPdhObjectList;
	}

	public List<PwSdhObject> getPwSdhObjectList() {
		return pwSdhObjectList;
	}

	public void setPwSdhObjectList(List<PwSdhObject> pwSdhObjectList) {
		this.pwSdhObjectList = pwSdhObjectList;
	}

	public List<ELineObject> getElineObjectList() {
		return elineObjectList;
	}

	public void setElineObjectList(List<ELineObject> elineObjectList) {
		this.elineObjectList = elineObjectList;
	}

	public List<CesObject> getCesObjectList() {
		return cesObjectList;
	}

	public void setCesObjectList(List<CesObject> cesObjectList) {
		this.cesObjectList = cesObjectList;
	}

	public List<XcObject> getXcObjectList() {
		return xcObjectList;
	}

	public void setXcObjectList(List<XcObject> xcObjectList) {
		this.xcObjectList = xcObjectList;
	}

	public List<AcObject> getAcObjectList() {
		return acObjectList;
	}

	public void setAcObjectList(List<AcObject> acObjectList) {
		this.acObjectList = acObjectList;
	}

	public List<QosObject> getQosObjectList() {
		return qosObjectList;
	}

	public void setQosObjectList(List<QosObject> qosObjectList) {
		this.qosObjectList = qosObjectList;
	}

	public List<ETreeObject> getEtreeObjectList() {
		return etreeObjectList;
	}

	public void setEtreeObjectList(List<ETreeObject> etreeObjectList) {
		this.etreeObjectList = etreeObjectList;
	}

	public List<AcQosObject> getAcQosObjectList() {
		return acQosObjectList;
	}

	public void setAcQosObjectList(List<AcQosObject> acQosObjectList) {
		this.acQosObjectList = acQosObjectList;
	}

	public List<ELanObject> getElanObjectList() {
		return elanObjectList;
	}

	public void setElanObjectList(List<ELanObject> elanObjectList) {
		this.elanObjectList = elanObjectList;
	}

	public List<EthPortObject> getEthPortObjectList() {
		return ethPortObjectList;
	}

	public void setEthPortObjectList(List<EthPortObject> ethPortObjectList) {
		this.ethPortObjectList = ethPortObjectList;
	}

	public List<OSPFAREAObject> getOspfAREAObjectList() {
		return ospfAREAObjectList;
	}

	public void setOspfAREAObjectList(List<OSPFAREAObject> ospfAREAObjectList) {
		this.ospfAREAObjectList = ospfAREAObjectList;
	}

	public List<RedistributeObject> getRedistributeObjectList() {
		return redistributeObjectList;
	}

	public void setRedistributeObjectList(List<RedistributeObject> redistributeObjectList) {
		this.redistributeObjectList = redistributeObjectList;
	}

	public List<PdhPortObject> getPdhPortObjectList() {
		return pdhPortObjectList;
	}

	public void setPdhPortObjectList(List<PdhPortObject> pdhPortObjectList) {
		this.pdhPortObjectList = pdhPortObjectList;
	}

	public List<MCNObject> getMcnObjectList() {
		return mcnObjectList;
	}

	public void setMcnObjectList(List<MCNObject> mcnObjectList) {
		this.mcnObjectList = mcnObjectList;
	}

	public List<OSPFinterfacesObject> getOspfInterfacesObjectList() {
		return ospfInterfacesObjectList;
	}

	public void setOspfInterfacesObjectList(List<OSPFinterfacesObject> ospfInterfacesObjectList) {
		this.ospfInterfacesObjectList = ospfInterfacesObjectList;
	}

	public List<SdhPortObject> getSdhPortObjectList() {
		return sdhPortObjectList;
	}

	public void setSdhPortObjectList(List<SdhPortObject> sdhPortObjectList) {
		this.sdhPortObjectList = sdhPortObjectList;
	}

	public List<SdhAcObject> getSdhAcObjectList() {
		return sdhAcObjectList;
	}

	public void setSdhAcObjectList(List<SdhAcObject> sdhAcObjectList) {
		this.sdhAcObjectList = sdhAcObjectList;
	}

	public List<LagObject> getLagObjectList() {
		return lagObjectList;
	}

	public void setLagObjectList(List<LagObject> lagObjectList) {
		this.lagObjectList = lagObjectList;
	}

	public List<ACNObject> getAcnObjectList() {
		return acnObjectList;
	}

	public void setAcnObjectList(List<ACNObject> acnObjectList) {
		this.acnObjectList = acnObjectList;
	}
	public ClockPortESObject getClockPortESObject() {
		return clockPortESObject;
	}
	public void setClockPortESObject(ClockPortESObject clockPortESObject) {
		this.clockPortESObject = clockPortESObject;
	}
	public List<ClockPortESObject> getClockPortESObjectList() {
		return clockPortESObjectList;
	}
	public void setClockPortESObjectList(
			List<ClockPortESObject> clockPortESObjectList) {
		this.clockPortESObjectList = clockPortESObjectList;
	}
	public List<EfmObject> getEfmObjectList() {
		return efmObjectList;
	}
	public void setEfmObjectList(List<EfmObject> efmObjectList) {
		this.efmObjectList = efmObjectList;
	}
	public List<DualObject> getDualObjectList() {
		return dualObjectList;
	}
	public void setDualObjectList(List<DualObject> dualObjectList) {
		this.dualObjectList = dualObjectList;
	}
	public void setCostoexpObject(CostoexpObject costoexpObject) {
		this.costoexpObject = costoexpObject;
	}
	public void setExptoclrObject(ExptoclrObject exptoclrObject) {
		this.exptoclrObject = exptoclrObject;
	}
	public List<ClrtoexpObject> getClrtoexpObjectList() {
		return ClrtoexpObjectList;
	}
	public void setClrtoexpObjectList(List<ClrtoexpObject> clrtoexpObjectList) {
		ClrtoexpObjectList = clrtoexpObjectList;
	}
	public List<CostoexpObject> getCostoexpObjectList() {
		return CostoexpObjectList;
	}
	public void setCostoexpObjectList(List<CostoexpObject> costoexpObjectList) {
		CostoexpObjectList = costoexpObjectList;
	}
	public List<ExptoclrObject> getExptoclrObjectList() {
		return exptoclrObjectList;
	}
	public void setExptoclrObjectList(List<ExptoclrObject> exptoclrObjectList) {
		this.exptoclrObjectList = exptoclrObjectList;
	}
	public List<ExptocosObject> getExptocosObjectList() {
		return exptocosObjectList;
	}
	public void setExptocosObjectList(List<ExptocosObject> exptocosObjectList) {
		this.exptocosObjectList = exptocosObjectList;
	}
	public Cos2vlanpriObject getCos2vlanpriObject() {
		return cos2vlanpriObject;
	}
	public void setCos2vlanpriObject(Cos2vlanpriObject cos2vlanpriObject) {
		this.cos2vlanpriObject = cos2vlanpriObject;
	}
	public List<Cos2vlanpriObject> getCos2vlanpriObjectList() {
		return cos2vlanpriObjectList;
	}
	public void setCos2vlanpriObjectList(
			List<Cos2vlanpriObject> cos2vlanpriObjectList) {
		this.cos2vlanpriObjectList = cos2vlanpriObjectList;
	}
	public Vlanpri2cngObject getVlanpri2cngObject() {
		return vlanpri2cngObject;
	}
	public void setVlanpri2cngObject(Vlanpri2cngObject vlanpri2cngObject) {
		this.vlanpri2cngObject = vlanpri2cngObject;
	}
	public List<Vlanpri2cngObject> getVlanpri2cngObjectList() {
		return vlanpri2cngObjectList;
	}
	public void setVlanpri2cngObjectList(
			List<Vlanpri2cngObject> vlanpri2cngObjectList) {
		this.vlanpri2cngObjectList = vlanpri2cngObjectList;
	}

}
