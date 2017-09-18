package com.nms.drivechenxiao.service.impl.bean;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.weihu.PowerObject;
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
import com.nms.drivechenxiao.service.bean.protocols.CardProObject;
import com.nms.drivechenxiao.service.bean.protocols.Cos2vlanpriObject;
import com.nms.drivechenxiao.service.bean.protocols.DualObject;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
import com.nms.drivechenxiao.service.bean.protocols.ProtectLogObject;
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
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.drivechenxiao.service.bean.xc.XcObject;

public class CXPtnDataObject {

	private String status = "";// 返回状态
	private String operType = "";
	private String objType = "";
	private List<AlarmObject> alarmObjectList = new ArrayList<AlarmObject>();
	private List<PersvrObject> persvrObjectList = new ArrayList<PersvrObject>();
	private SlotObject[] slotObjects = new SlotObject[0];
	private PtnNeObject ptnNeObject = new PtnNeObject();
	private List<TunnelObject> tunnelObjectList = new ArrayList<TunnelObject>();
	private List<LSPObject> lspObjectList = new ArrayList<LSPObject>();
	private List<XcObject> xcObject = new ArrayList<XcObject>();
	private List<PwEthObject> pwEthObjectList = new ArrayList<PwEthObject>();
	private List<PwPdhObject> pwPdhObjectList = new ArrayList<PwPdhObject>();
	private List<PwSdhObject> pwSdhObjectList = new ArrayList<PwSdhObject>();
	private List<QosObject> qosObjectList = new ArrayList<QosObject>();
	private List<ELineObject> elineObjectList = new ArrayList<ELineObject>();
	private List<AcObject> acObjectList = new ArrayList<AcObject>();
	private List<CesObject> cesObjectList = new ArrayList<CesObject>();
	private List<ETreeObject> etreeObjectList = new ArrayList<ETreeObject>();
	private List<AcQosObject> acQosObjectList = new ArrayList<AcQosObject>();
	private List<ELanObject> elanObjectList = new ArrayList<ELanObject>();
	private List<CCNObject> ccnObjectList = new ArrayList<CCNObject>();
	private UNIObject uniObject = new UNIObject();
	private NNIObject nniObject = new NNIObject();
	private List<EthPortObject> ethPortObjectList = new ArrayList<EthPortObject>();
	private OSPFObject OSPFObject = new OSPFObject();
	private List<OSPFAREAObject> ospfAREAObjectList = new ArrayList<OSPFAREAObject>();
	private List<RedistributeObject> redistributeObjectList = new ArrayList<RedistributeObject>();
	private List<PdhPortObject> pdhPortObjectList = new ArrayList<PdhPortObject>();
	private List<OSPFinterfacesObject> ospfInterfacesObjectList = new ArrayList<OSPFinterfacesObject>();
	private List<MCNObject> mcnObjectList = new ArrayList<MCNObject>();
	private List<SdhPortObject> sdhPortObjectList = new ArrayList<SdhPortObject>();
	private List<SdhAcObject> sdhAcObjectList = new ArrayList<SdhAcObject>();
	private List<LagObject> lagObjectList=new ArrayList<LagObject>();
	private List<ACNObject> acnObjectList = new ArrayList<ACNObject>(); //acn对象
	private List<RingObject> ringObjectList = new ArrayList<RingObject>();
	private List<PtpObject> ptpObjectList=new ArrayList<PtpObject>();
	private List<PtpPortObject> ptpPortObjectList=new ArrayList<PtpPortObject>();
	private List<ClockObject> clockObjectList=new ArrayList<ClockObject>();
	private List<ClockPortESObject> clockPortObjectList=new ArrayList<ClockPortESObject>();
	private List<TodObject> todObjectList = new ArrayList<TodObject>();
	private List<ExtclkObject> extclkObjectList = new ArrayList<ExtclkObject>();
	private List<MspObject> mspObjectList = new ArrayList<MspObject>();
	private List<EfmObject> efmObjectList = new ArrayList<EfmObject>();
	private List<DualObject> dualObjectList = new ArrayList<DualObject>();
	private List<ClrtoexpObject> clrtoexpObjectList = new ArrayList<ClrtoexpObject>();//Clrtoexp
	private List<CostoexpObject> costoexpObjectList = new ArrayList<CostoexpObject>();//Costoexp
	private List<ExptoclrObject> exptoclrObjectList = new ArrayList<ExptoclrObject>();
	private List<ExptocosObject> exptocosObjectList = new ArrayList<ExptocosObject>();
	private List<Cos2vlanpriObject> cos2vlanpriObjectList = new ArrayList<Cos2vlanpriObject>();
	private List<Vlanpri2cngObject> vlanpri2cngObjectList = new ArrayList<Vlanpri2cngObject>();
	private List<StaticRouteObject> staticRouteObjectList = new ArrayList<StaticRouteObject>();
	private List<SlotTempObject> slotTempObjectList=new ArrayList<SlotTempObject>();//温度管理
	private List<PowerObject> powerObjectList=new ArrayList<PowerObject>();//光功率
	private List<CardProObject> cardProObjectList=new ArrayList<CardProObject>();//板卡保护
	private List<ProtectLogObject> protectLogObject=new ArrayList<ProtectLogObject>();//保护倒换事件
	private List<Object> objectList=new ArrayList<Object>();//TMD环回： pdh,sdh,extclk
	
	
	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

	public List<ProtectLogObject> getProtectLogObject() {
		return protectLogObject;
	}

	public void setProtectLogObject(List<ProtectLogObject> protectLogObject) {
		this.protectLogObject = protectLogObject;
	}

	public List<CardProObject> getCardProObjectList() {
		return cardProObjectList;
	}

	public void setCardProObjectList(List<CardProObject> cardProObjectList) {
		this.cardProObjectList = cardProObjectList;
	}

	public List<PowerObject> getPowerObjectList() {
		return powerObjectList;
	}

	public void setPowerObjectList(List<PowerObject> powerObjectList) {
		this.powerObjectList = powerObjectList;
	}

	public List<SlotTempObject> getSlotTempObjectList() {
		return slotTempObjectList;
	}

	public void setSlotTempObjectList(List<SlotTempObject> slotTempObjectList) {
		this.slotTempObjectList = slotTempObjectList;
	}

	public List<StaticRouteObject> getStaticRouteObjectList() {
		return staticRouteObjectList;
	}

	public void setStaticRouteObjectList(
			List<StaticRouteObject> staticRouteObjectList) {
		this.staticRouteObjectList = staticRouteObjectList;
	}

	public List<Vlanpri2cngObject> getVlanpri2cngObjectList() {
		return vlanpri2cngObjectList;
	}

	public void setVlanpri2cngObjectList(
			List<Vlanpri2cngObject> vlanpri2cngObjectList) {
		this.vlanpri2cngObjectList = vlanpri2cngObjectList;
	}

	public List<Cos2vlanpriObject> getCos2vlanpriObjectList() {
		return cos2vlanpriObjectList;
	}

	public void setCos2vlanpriObjectList(
			List<Cos2vlanpriObject> cos2vlanpriObjectList) {
		this.cos2vlanpriObjectList = cos2vlanpriObjectList;
	}

	public List<ExptocosObject> getExptocosObjectList() {
		return exptocosObjectList;
	}

	public void setExptocosObjectList(List<ExptocosObject> exptocosObjectList) {
		this.exptocosObjectList = exptocosObjectList;
	}

	public List<ExptoclrObject> getExptoclrObjectList() {
		return exptoclrObjectList;
	}

	public void setExptoclrObjectList(List<ExptoclrObject> exptoclrObjectList) {
		this.exptoclrObjectList = exptoclrObjectList;
	}

	public List<CostoexpObject> getCostoexpObjectList() {
		return costoexpObjectList;
	}

	public void setCostoexpObjectList(List<CostoexpObject> costoexpObjectList) {
		this.costoexpObjectList = costoexpObjectList;
	}

	public List<ClrtoexpObject> getClrtoexpObjectList() {
		return clrtoexpObjectList;
	}

	public void setClrtoexpObjectList(List<ClrtoexpObject> clrtoexpObjectList) {
		this.clrtoexpObjectList = clrtoexpObjectList;
	}

	public List<DualObject> getDualObjectList() {
		return dualObjectList;
	}

	public void setDualObjectList(List<DualObject> dualObjectList) {
		this.dualObjectList = dualObjectList;
	}

	public List<EfmObject> getEfmObjectList() {
		return efmObjectList;
	}

	public void setEfmObjectList(List<EfmObject> efmObjectList) {
		this.efmObjectList = efmObjectList;
	}

	public List<MspObject> getMspObjectList() {
		return mspObjectList;
	}

	public void setMspObjectList(List<MspObject> mspObjectList) {
		this.mspObjectList = mspObjectList;
	}

	public List<PtpPortObject> getPtpPortObjectList() {
		return ptpPortObjectList;
	}

	public void setPtpPortObjectList(List<PtpPortObject> ptpPortObjectList) {
		this.ptpPortObjectList = ptpPortObjectList;
	}

	public List<ClockPortESObject> getClockPortObjectList() {
		return clockPortObjectList;
	}

	public void setClockPortObjectList(List<ClockPortESObject> clockPortObjectList) {
		this.clockPortObjectList = clockPortObjectList;
	}

	public List<ExtclkObject> getExtclkObjectList() {
		return extclkObjectList;
	}

	public void setExtclkObjectList(List<ExtclkObject> extclkObjectList) {
		this.extclkObjectList = extclkObjectList;
	}

	public List<TodObject> getTodObjectList() {
		return todObjectList;
	}

	public void setTodObjectList(List<TodObject> todObjectList) {
		this.todObjectList = todObjectList;
	}

	public List<ClockObject> getClockObjectList() {
		return clockObjectList;
	}

	public void setClockObjectList(List<ClockObject> clockObjectList) {
		this.clockObjectList = clockObjectList;
	}

	public List<PtpObject> getPtpObjectList() {
		return ptpObjectList;
	}

	public void setPtpObjectList(List<PtpObject> ptpObjectList) {
		this.ptpObjectList = ptpObjectList;
	}

	public List<RingObject> getRingObjectList() {
		return ringObjectList;
	}

	public void setRingObjectList(List<RingObject> ringObjectList) {
		this.ringObjectList = ringObjectList;
	}

	public List<ACNObject> getAcnObjectList() {
		return acnObjectList;
	}

	public void setAcnObjectList(List<ACNObject> acnObjectList) {
		this.acnObjectList = acnObjectList;
	}

	public List<OSPFinterfacesObject> getOspfInterfacesObjectList() {
		return ospfInterfacesObjectList;
	}

	public void setOspfInterfacesObjectList(List<OSPFinterfacesObject> ospfInterfacesObjectList) {
		this.ospfInterfacesObjectList = ospfInterfacesObjectList;
	}

	public List<MCNObject> getMcnObjectList() {
		return mcnObjectList;
	}

	public void setMcnObjectList(List<MCNObject> mcnObjectList) {
		this.mcnObjectList = mcnObjectList;
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

	public List<CCNObject> getCcnObjectList() {
		return ccnObjectList;
	}

	public void setCcnObjectList(List<CCNObject> ccnObjectList) {
		this.ccnObjectList = ccnObjectList;
	}

	public PtnNeObject getPtnNeObject() {
		return ptnNeObject;
	}

	public void setPtnNeObject(PtnNeObject ptnNeObject) {
		this.ptnNeObject = ptnNeObject;
	}

	public SlotObject[] getSlotObjects() {
		return slotObjects;
	}

	public void setSlotObjects(SlotObject[] slotObjects) {
		this.slotObjects = slotObjects;
	}

	public List<PersvrObject> getPersvrObjectList() {
		return persvrObjectList;
	}

	public void setPersvrObjectList(List<PersvrObject> persvrObjectList) {
		this.persvrObjectList = persvrObjectList;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AlarmObject> getAlarmObjectList() {
		return alarmObjectList;
	}

	public void setAlarmObjectList(List<AlarmObject> alarmObjectList) {
		this.alarmObjectList = alarmObjectList;
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

	public List<XcObject> getXcObject() {
		return xcObject;
	}

	public void setXcObject(List<XcObject> xcObject) {
		this.xcObject = xcObject;
	}

	public List<PwEthObject> getPwEthObjectList() {
		return pwEthObjectList;
	}

	public void setPwEthObjectList(List<PwEthObject> pwEthObjectList) {
		this.pwEthObjectList = pwEthObjectList;
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

	public List<QosObject> getQosObjectList() {
		return qosObjectList;
	}

	public void setQosObjectList(List<QosObject> qosObjectList) {
		this.qosObjectList = qosObjectList;
	}

	public List<ELineObject> getElineObjectList() {
		return elineObjectList;
	}

	public void setElineObjectList(List<ELineObject> elineObjectList) {
		this.elineObjectList = elineObjectList;
	}

	public List<AcObject> getAcObjectList() {
		return acObjectList;
	}

	public void setAcObjectList(List<AcObject> acObjectList) {
		this.acObjectList = acObjectList;
	}

	public List<CesObject> getCesObjectList() {
		return cesObjectList;
	}

	public void setCesObjectList(List<CesObject> cesObjectList) {
		this.cesObjectList = cesObjectList;
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

	public UNIObject getUniObject() {
		return uniObject;
	}

	public void setUniObject(UNIObject uniObject) {
		this.uniObject = uniObject;
	}

	public NNIObject getNniObject() {
		return nniObject;
	}

	public void setNniObject(NNIObject nniObject) {
		this.nniObject = nniObject;
	}

	public List<EthPortObject> getEthPortObjectList() {
		return ethPortObjectList;
	}

	public void setEthPortObjectList(List<EthPortObject> ethPortObjectList) {
		this.ethPortObjectList = ethPortObjectList;
	}

	public OSPFObject getOSPFObject() {
		return OSPFObject;
	}

	public void setOSPFObject(OSPFObject object) {
		OSPFObject = object;
	}

	public List<PdhPortObject> getPdhPortObjectList() {
		return pdhPortObjectList;
	}

	public void setPdhPortObjectList(List<PdhPortObject> pdhPortObjectList) {
		this.pdhPortObjectList = pdhPortObjectList;
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

}
