package com.nms.drivechenxiao.analysis.tool;



import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.AnalysisObjectID;
import com.nms.drivechenxiao.service.bean.acn.ACNObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
import com.nms.drivechenxiao.service.bean.almcfg.AlmcfgObject;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.drivechenxiao.service.bean.clock.TodObject;
import com.nms.drivechenxiao.service.bean.cmap.L2Object;
import com.nms.drivechenxiao.service.bean.cmap.L3Object;
import com.nms.drivechenxiao.service.bean.lag.LagObject;
import com.nms.drivechenxiao.service.bean.mcn.MCNObject;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;
import com.nms.drivechenxiao.service.bean.oam.OamTestObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFAREAObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFObject;
import com.nms.drivechenxiao.service.bean.ospf.interfaces.OSPFinterfacesObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
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
import com.nms.drivechenxiao.service.bean.qos.eelsp.EELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.elsp.ELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.llsp.LLSPQosObject;
import com.nms.drivechenxiao.service.bean.redistribute.RedistributeObject;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.drivechenxiao.service.bean.user.UserObject;
import com.nms.drivechenxiao.service.bean.xc.Route;
import com.nms.drivechenxiao.service.bean.xc.XcIFObject;
import com.nms.drivechenxiao.service.bean.xc.XcObject;
import com.nms.drivechenxiao.service.impl.CXMonitorCallbackThread;
/**
 * 解析赋值
 * ***/
public class SetObject {
	Boolean isNull = false;
	int numberTimes = 0;//??
	String oldAttributeName = "";
	String oldoldAttributeName = "";
	byte[] bs = null;

	public SetObject(int numberTimes, String oldAttributeName, String oldoldAttributeName, byte[] bs) {
		this.oldAttributeName = oldAttributeName;
		this.oldoldAttributeName = oldoldAttributeName;
		this.numberTimes = numberTimes;
		this.bs = bs;
	}
	
	/**
	 * 对象赋值
	 * 
	 * @param value
	 * @param attributeName
	 * @param alarmObject
	 */
	public Object setObjectValue(String type, String value, String attributeName, Object object) {
		if ("alarm".equals(type)) {
			object = setAlarmValue(type, value, attributeName, object);
		} else if ("persvr".equals(type)) {
			object = setPersvrValue(type, value, attributeName, object);
		} else if ("slot".equals(type)) {
			object = setSlotValue(type, value, attributeName, object);
		} else if ("ne".equals(type)) {
			object = setNEValue(type, value, attributeName, object);
		} else if ("user".equals(type)) {
			object = setUserValue(type, value, attributeName, object);
		} else if ("ethport".equals(type)) {
			object = setETHPortValue(type, value, attributeName, object);
		} else if ("uni".equals(type)) {
			object = setETHPortUniValue(type, value, attributeName, object);
		} else if ("nni".equals(type)) {
			object = setETHPortNniValue(type, value, attributeName, object);
		} else if ("ethportqos".equals(type)) {
			object = setETHPortQosValue(type, value, attributeName, object);
		} else if ("ospf".equals(type)) {
			object = setOSPFValue(type, value, attributeName, object);
		} else if ("ospfinterfaces".equals(type)) {
			object = setOSPFInterfacesCCNValue(type, value, attributeName, object);
		} else if ("ccn".equals(type)) {
			object = setCCNValue(type, value, attributeName, object);
		} else if ("mcn".equals(type)) {
			object = setMCNValue(type, value, attributeName, object);
		} else if ("ospfarea".equals(type)) {
			object = setOspfAreaValue(type, value, attributeName, object);
		} else if ("ac".equals(type)) {
			object = setAcValue(type, value, attributeName, object);
		} else if ("tunnel".equals(type)) {
			object = setTunnelValue(type, value, attributeName, object);
		} else if ("ethpw".equals(type)) {
			object = setEthPwValue(type, value, attributeName, object);
		} else if ("lsp".equals(type)) {
			object = setLspValue(type, value, attributeName, object);
		} else if ("pdhport".equals(type)) {
			object = setPdhPortValue(type, value, attributeName, object);
		} else if ("xc".equals(type)) {
			object = setXcValue(type, value, attributeName, object);
		} else if ("eline".equals(type)) {
			object = setElineValue(type, value, attributeName, object);
		} else if ("alllsp".equals(type)) {
			object = setAllLspValue(type, value, attributeName, object);
		} else if ("qos".equals(type)) {
			object = setQosValue(type, value, attributeName, object);
		} else if ("pdhpw".equals(type)) {
			object = setPdhPwValue(type, value, attributeName, object);
		} else if ("sdhpw".equals(type)) {
			object = setSdhPwValue(type, value, attributeName, object);
		} else if ("allac".equals(type)) {
			object = setAllAcValue(type, value, attributeName, object);
		} else if ("ces".equals(type)) {
			object = setCesValue(type, value, attributeName, object);
		} else if ("etree".equals(type)) {
			object = setTreeValue(type, value, attributeName, object);
		} else if ("acqos".equals(type)) {
			object = setAcQosValue(type, value, attributeName, object);
		} else if ("elan".equals(type)) {
			object = setElanValue(type, value, attributeName, object);
		} else if ("allethport".equals(type)) {
			object = setAllETHPortValue(type, value, attributeName, object);
		} else if ("redistribute".equals(type)) {
			object = setRedistributeValue(type, value, attributeName, object);
		} else if ("sdhport".equals(type)) {
			object = setSDHPortValue(type, value, attributeName, object);
		} else if ("sdhac".equals(type)) {
			object = setSDHAcValue(type, value, attributeName, object);
		} else if ("lag".equals(type)) {
			object = setLagValue(type, value, attributeName, object);
		}else if ("ACN".equals(type)) {
			object = setAcnValue(type, value, attributeName, object);
		}else if ("route".equals(type)) {
			object = setRouteValue(type, value, attributeName, object);
		}else if ("Ring".equals(type)) {
			object = setRingValue(type, value, attributeName, object);
		}else if ("Clock".equals(type)) {
			object = setClockValue(type, value, attributeName, object);
		}else if ("tod".equals(type)) {
			object = setTodValue(type, value, attributeName, object);
		}else if ("Ptp".equals(type)) {
			object = setPtpValue(type, value, attributeName, object);
		}else if ("Ptpport".equals(type)) {
			object = setPtpPortValue(type, value, attributeName, object);
		}else if ("extclk".equals(type)) {
			object = setExtclkValue(type, value, attributeName, object);
		}else if ("clockport".equals(type)) {
			object = setClockPortValue(type, value, attributeName, object);
		}else if ("cmapl2".equals(type)) {
			object = setcmapl2Value(type, value, attributeName, object);
		}else if ("cmapl3".equals(type)) {
			object = setcmapl3Value(type, value, attributeName, object);
		}else if ("Msp".equals(type)) {
			object = setMspValue(type, value, attributeName, object);
		}else if ("Dual".equals(type)) {
			object = setDualValue(type, value, attributeName, object);
		}else if ("OamEfm".equals(type)) {
			object = setOamEfmValue(type, value, attributeName, object);
		}else if ("Clrtoexp".equals(type)) {
			object = setClrtoexpValue(type, value, attributeName, object);
		}else if ("Costoexp".equals(type)) {
			object = setCostoexpValue(type, value, attributeName, object);
		}else if ("Exptoclr".equals(type)) {
			object = setExptoclrValue(type, value, attributeName, object);
		}else if ("Exptocos".equals(type)) {
			object = setExptocosValue(type, value, attributeName, object);
		}else if ("cos2vlanpri".equals(type)) {
			object = setCos2vlanpriValue(type, value, attributeName, object);
		}else if ("vlanpri2cng".equals(type)) {
			object = setVlanpri2cngValue(type, value, attributeName, object);
		}else if ("staticRoute".equals(type)) {
			object = setStaticRouteValue(type, value, attributeName, object);
		}else if ("almcfg".equals(type)) {
			object = setAlmcfgValue(type, value, attributeName, object);
		}else if ("slotTemp".equals(type)) {
			object = setTempValue(type, value, attributeName, object);
		}else if ("cardPro".equals(type)) {
			object = setCardPro(type, value, attributeName, object);
		}else if ("power".equals(type)) {
			object = setPersvrValue(type, value, attributeName, object);
		}else if ("oamtest".equals(type)) {
			object = setOamTestValue(type, value, attributeName, object);
		}else if("loopback".equals(type)){
			object=setLoopBack(type, value, attributeName, object);
		}else if("protectLog".equals(type)){
			object=setProtectLog(type, value, attributeName, object);
		}else if("hooknotify".equals(type)){
			object=setProtectHookNotify(type, value, attributeName, object);
		}
		return object;
	}
	/**ACN**/
	private Object setAcnValue(String type, String value, String attributeName, Object object) {
		ACNObject acn = null;
		if (object == null) {
			isNull = true;
			acn = new ACNObject();
		} else {
			isNull = false;
			acn = (ACNObject) object;
		}
		acn.setName(oldoldAttributeName);
		if(attributeName.equals("ifname")){
			acn.setIfname(value);
		}
		if(attributeName.equals("ifindex")){
			acn.setIfindex(Integer.valueOf(value).intValue());
		}
		if(attributeName.equals("desc")){
			acn.setDesc(value);
		}
		if(attributeName.equals("admin")){
			acn.setAdmin(value);
		}
		if(attributeName.equals("oper")){
			acn.setOper(value);
		}
		if(attributeName.equals("ref")){
			acn.setRef(Integer.valueOf(value).intValue());
		}
		if(attributeName.equals("peer")){
			acn.setPeer(value);
		}
		if(attributeName.equals("active")){
			acn.setActive(value);
		}
		
		return acn;
	}

	private Object setLagValue(String type, String value, String attributeName, Object object) {
		LagObject lagObject = null;
		if (object == null) {
			isNull = true;
			lagObject = new LagObject();
		} else {
			isNull = false;
			lagObject = (LagObject) object;
		}

		lagObject.setName(oldoldAttributeName);
		if (attributeName.equals("admin")) {
			lagObject.setAdmin(value);
		} else if (attributeName.equals("oper")) {
			lagObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if (attributeName.equals("ifname")) {
			lagObject.setIfname(value);
		} else if (attributeName.equals("role")) {
			lagObject.setRole(value);
		} else if (attributeName.equals("mac")) {
			lagObject.setMac(value);
		} else if (attributeName.equals("framelen")) {
			lagObject.setFramelen(value);
		} else if (attributeName.equals("permitpktloop")) {
			lagObject.setPermitpktloop(value);
		} else if ("psc".equals(attributeName)) {
			lagObject.setPsc(value);
		} else if ("recover".equals(attributeName)) {
			lagObject.setRecover(value);
		} else if ("permitpktloop".equals(attributeName)) {
			lagObject.setPermitpktloop(value);
		} else if ("framelen".equals(attributeName)) {
			lagObject.setFramelen(value);
		}else if ("active".equals(attributeName)) {
			lagObject.setActive(value);
		}else if ("arpproto_tocpu".equals(attributeName)) {
			lagObject.setArpproto_tocpu(value);
		}else if ("desc".equals(attributeName)) {
			lagObject.setDesc(value);
		}else if ("dhcpproto_tocpu".equals(attributeName)) {
			lagObject.setDhcpproto_tocpu(value);
		}else if ("dualid".equals(attributeName)) {
			lagObject.setDualid(value);
		}else if ("ifindex".equals(attributeName)) {
			lagObject.setIfindex(value);
		}else if ("isolation".equals(attributeName)) {
			lagObject.setIsolation(value);
		}else if ("iused".equals(attributeName)) {
			lagObject.setIused(value);
		}else if ("oused".equals(attributeName)) {
			lagObject.setOused(value);
		}else if ("portac".equals(attributeName)) {
			lagObject.setPortac(value);
		}else if ("ref".equals(attributeName)) {
			lagObject.setRef(value);
		}else if ("schmode".equals(attributeName)) {
			lagObject.setSchmode(value);
		}else if ("type".equals(attributeName)) {
			lagObject.setType(value);
		}else if ("work".equals(attributeName)) {
			lagObject.setWork(value);
		}else if ("nni".equals(oldAttributeName)) {
			if ("llspexptoclr".equals(attributeName)) {
				lagObject.getNni().setLlspexptoclr(value);
			} else if ("eelspcostoexp".equals(attributeName)) {
				lagObject.getNni().setEelspcostoexp(value);
			} else if ("eelspexptocos".equals(attributeName)) {
				lagObject.getNni().setEelspexptocos(value);
			}else if ("egbwlimit".equals(attributeName)) {
				lagObject.getNni().setEgbwlimit(value);
			}else if ("elspcostoexp".equals(attributeName)) {
				lagObject.getNni().setElspcostoexp(value);
			}else if ("elspexptocos".equals(attributeName)) {
				lagObject.getNni().setElspexptocos(value);
			}else if ("inbwlimit".equals(attributeName)) {
				lagObject.getNni().setInbwlimit(value);
			}else if ("llspclrtoexp".equals(attributeName)) {
				lagObject.getNni().setLlspclrtoexp(value);
			}else if ("ccnEnableString".equals(attributeName)) {
				lagObject.getNni().setCcnEnableString(value);
			}else if ("pvid".equals(attributeName)) {
				lagObject.getNni().setPvid(value);
			}else if (isNull) {
				return null;
			}
		} else if ("uni".equals(oldAttributeName)) {
			if ("pvid".equals(attributeName)) {
				lagObject.getUni().setPvid(value);
			} else if ("pvpri".equals(attributeName)) {
				lagObject.getUni().setPvpri(value);
			} else if ("encap".equals(attributeName)) {
				lagObject.getUni().setEncap(value);
			} else if ("sdtpid".equals(attributeName)) {
				lagObject.getUni().setSdtpid(value);
			} else if ("tpid".equals(attributeName)) {
				lagObject.getUni().setTpid(value);
			} else if ("vlanmode".equals(attributeName)) {
				lagObject.getUni().setVlanmode(value);
			} else if ("bcastlimit".equals(attributeName)) {
				lagObject.getUni().setBcastlimit(value);
			} else if ("dlflimit".equals(attributeName)) {
				lagObject.getUni().setDlflimit(value);
			} else if ("mcastlimit".equals(attributeName)) {
				lagObject.getUni().setMcastlimit(value);
			} else if ("inbwlimit".equals(attributeName)) {
				lagObject.getUni().setInbwlimit(value);
			} else if ("egbwlimit".equals(attributeName)) {
				lagObject.getUni().setEgbwlimit(value);
			} else if ("acl".equals(attributeName)) {
				lagObject.getUni().setAcl(value);
			}else if ("cos2vlanpri".equals(attributeName)) {
				lagObject.getUni().setCos2vlanpri(value);
			}else if ("iclrmode".equals(attributeName)) {
				lagObject.getUni().setIclrmode(value);
			}else if ("lptstatby".equals(attributeName)) {
				lagObject.getUni().setLptstatby(value);
			}else if ("lptstat".equals(attributeName)) {
				lagObject.getUni().setLptstat(value);
			}else if ("oclrmode".equals(attributeName)) {
				lagObject.getUni().setOclrmode(value);
			}else if ("vlanpri2cng".equals(attributeName)) {
				lagObject.getUni().setVlanpri2cng(value);
			}else if (isNull) {
				return null;
			}
		} else if ("cs7".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getCs7().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getCs7().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getCs7().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getCs7().setGreenwredend(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getCs7().setUsed(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getCs7().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("cs6".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getCs6().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getCs6().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getCs6().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getCs6().setGreenwredend(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getCs6().setUsed(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getCs6().setWreden(value);
			}  else if (isNull) {
				return null;
			}
		} else if ("cs3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getCs3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getCs3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getCs3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getCs3().setGreenwredend(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getCs3().setUsed(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getCs3().setWreden(value);
			}  else if (isNull) {
				return null;
			}
		}else if ("ef".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getEf().setCir(value);
			} else if ("used".equals(attributeName)) {
				lagObject.getEf().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				lagObject.getEf().setWreden(value);
			}else if ("greendroprate".equals(attributeName)) {
				lagObject.getEf().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getEf().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getEf().setGreenwredend(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af4".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getAf4().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getAf4().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getAf4().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getAf4().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				lagObject.getAf4().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				lagObject.getAf4().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				lagObject.getAf4().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				lagObject.getAf4().setMaxbw(value);
			}else if ("weight".equals(attributeName)) {
				lagObject.getAf4().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getAf4().setWreden(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getAf4().setUsed(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getAf3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getAf3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getAf3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getAf3().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				lagObject.getAf3().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				lagObject.getAf3().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				lagObject.getAf3().setYellowwredend(value);
			} else if ("maxbw".equals(attributeName)) {
				lagObject.getAf3().setMaxbw(value);
			}else if ("weight".equals(attributeName)) {
				lagObject.getAf3().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getAf3().setWreden(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getAf3().setUsed(value);
			}else if (isNull) {
				return null;
			}
		} else if ("af2".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getAf2().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getAf2().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getAf2().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getAf2().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				lagObject.getAf2().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				lagObject.getAf2().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				lagObject.getAf2().setYellowwredend(value);
			} else if ("maxbw".equals(attributeName)) {
				lagObject.getAf2().setMaxbw(value);
			}else if ("weight".equals(attributeName)) {
				lagObject.getAf2().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getAf2().setWreden(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getAf2().setUsed(value);
			}else if (isNull) {
				return null;
			}
		} else if ("af1".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				lagObject.getAf1().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				lagObject.getAf1().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				lagObject.getAf1().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				lagObject.getAf1().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				lagObject.getAf1().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				lagObject.getAf1().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				lagObject.getAf1().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				lagObject.getAf1().setMaxbw(value);
			}else if ("weight".equals(attributeName)) {
				lagObject.getAf1().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				lagObject.getAf1().setWreden(value);
			}else if ("used".equals(attributeName)) {
				lagObject.getAf1().setUsed(value);
			} else if (isNull) {
				return null;
			}
		} else if ("oam".equals(oldAttributeName)) {
			if ("cvintvl".equals(attributeName)) {
				lagObject.getOam().setCvintvl(value);
			} else if ("encsf".equals(attributeName)) {
				lagObject.getOam().setEncsf(value);
			}else if ("endm".equals(attributeName)) {
				lagObject.getOam().setEndm(value);
			}else if ("enlm".equals(attributeName)) {
				lagObject.getOam().setEnlm(value);
			}else if ("hwres".equals(attributeName)) {
				lagObject.getOam().setHwres(value);
			}else if ("iflck".equals(attributeName)) {
				lagObject.getOam().setIflck(value);
			}else if ("lpbtimeout".equals(attributeName)) {
				lagObject.getOam().setLpbtimeout(value);
			}else if ("lvl".equals(attributeName)) {
				lagObject.getOam().setLvl(value);
			}else if ("megid".equals(attributeName)) {
				lagObject.getOam().setMegid(value);
			}else if ("mepid".equals(attributeName)) {
				lagObject.getOam().setMepid(value);
			}else if ("peer".equals(attributeName)) {
				lagObject.getOam().setPeer(value);
			}else if (isNull) {
				return null;
			}
		}else if ("be".equals(oldAttributeName)) {
			if ("yellowdroprate".equals(attributeName)) {
				lagObject.getBe().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				lagObject.getBe().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				lagObject.getBe().setYellowwredend(value);
			} else if ("weight".equals(attributeName)) {
				lagObject.getBe().setWeight(value);
			} else if ("wreden".equals(attributeName)) {
				lagObject.getBe().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("member".equals(oldAttributeName)) {
			lagObject.getMenber().getPortName().add(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).substring(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).indexOf("/") + 1));
		} else if (isNull) {
			return null;
		}
		return lagObject;
	}   

	private Object setSDHAcValue(String type, String value, String attributeName, Object object) {
		SdhAcObject sdhAcObject = null;
		if (object == null) {
			isNull = true;
			sdhAcObject = new SdhAcObject();
		} else {
			isNull = false;
			sdhAcObject = (SdhAcObject) object;
		}
		if (oldoldAttributeName.indexOf("stm1") != -1) {
			if (oldAttributeName.matches("([0-9]\\.){4}[0-9]")) {
				sdhAcObject.setName(oldAttributeName);
				if ("admin".equals(attributeName)) {
					sdhAcObject.setAdmin(value);
				} else if ("ifname".equals(attributeName)) {
					sdhAcObject.setIfname(value.substring(0, value.lastIndexOf("/")));
				} else if ("oper".equals(attributeName)) {
					sdhAcObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
				} else if ("expectj2".equals(attributeName)) {
					sdhAcObject.setExpectj2(value);
				} else if ("sendj2".equals(attributeName)) {
					sdhAcObject.setSendj2(value);
				} else if ("receivedj2".equals(attributeName)) {
					sdhAcObject.setReceivedj2(value);
				} else if ("checkj2".equals(attributeName)) {
					sdhAcObject.setCheckj2(value);
				} else if ("expectv5".equals(attributeName)) {
					sdhAcObject.setExpectv5(value);
				} else if ("sendv5".equals(attributeName)) {
					sdhAcObject.setSendv5(value);
				} else if ("receivedv5".equals(attributeName)) {
					sdhAcObject.setReceivedv5(value);
				} else if ("checkv5".equals(attributeName)) {
					sdhAcObject.setCheckv5(value);
				}else if ("name".equals(attributeName)) {
					sdhAcObject.setName(value);
				}else if ("sdhifindex".equals(attributeName)) {
					sdhAcObject.setSdhifindex(value);
				}else if ("label".equals(attributeName)) {
					sdhAcObject.setLabel(value);
				}else if ("lblhwres".equals(attributeName)) {
					sdhAcObject.setLblhwres(value);
				}
			} else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}

		return sdhAcObject;
	}

	private Object setSDHPortValue(String type, String value, String attributeName, Object object) {
		SdhPortObject sdhPortObject = null;
		if (object == null) {
			isNull = true;
			sdhPortObject = new SdhPortObject();
		} else {
			isNull = false;
			sdhPortObject = (SdhPortObject) object;
		}

		if (oldoldAttributeName.indexOf("stm1") != -1) {
			sdhPortObject.setName(oldoldAttributeName);
			if ("admin".equals(attributeName)) {
				sdhPortObject.setAdmin(value);
			} else if ("oper".equals(attributeName)) {
				sdhPortObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
			} else if ("sfptype".equals(attributeName)) {
				sdhPortObject.setSfptype(value);
			} else if ("sfpvendor".equals(attributeName)) {
				sdhPortObject.setSfpvendor(value);
			} else if ("wavelength".equals(attributeName)) {
				sdhPortObject.setWavelength(value);
			} else if ("sfpexptype".equals(attributeName)) {
				sdhPortObject.setSfpexptype(value);
			}else if ("ifname".equals(attributeName)) {
				sdhPortObject.setIfname(value);
			}else if ("ifindex".equals(attributeName)) {
				sdhPortObject.setIfindex(value);
			}else if ("desc".equals(attributeName)) {
				sdhPortObject.setDesc(value);
			}else if ("perprofile".equals(attributeName)) {
				sdhPortObject.setPerprofile(value);
			}else if ("als".equals(attributeName)) {
				sdhPortObject.setAls(value);
			}else if ("alsdelay".equals(attributeName)) {
				sdhPortObject.setAlsdelay(value);
			}else if ("alsshtup".equals(attributeName)) {
				sdhPortObject.setAlsshtup(value);
			}else if ("ref".equals(attributeName)) {
				sdhPortObject.setRef(value);
			}else if ("dnugroup".equals(attributeName)) {
				sdhPortObject.setDnugroup(value);
			}else if ("ssmoutputenable".equals(attributeName)) {
				sdhPortObject.setSsmoutputenable(value);
			}else if ("type".equals(attributeName)) {
				sdhPortObject.setType(value);//SDH接口类型
			}else if ("j0mode".equals(attributeName)) {
				sdhPortObject.setJ0mode(value);
			}else if ("expectj0".equals(attributeName)) {
				sdhPortObject.setExpectj0(value);
			}else if ("sendj0".equals(attributeName)) {
				sdhPortObject.setSendj0(value);
			}else if ("checkj0".equals(attributeName)) {
				sdhPortObject.setCheckj0(value);
			}else if ("loopback".equals(attributeName)) {
				sdhPortObject.setLoopback(value);
			}else if ("regsdthr".equals(attributeName)) {
				sdhPortObject.setRegsdthr(value);
			}else if ("regsfthr".equals(attributeName)) {
				sdhPortObject.setRegsfthr(value);
			}else if ("mssdthr".equals(attributeName)) {
				sdhPortObject.setMssdthr(value);
			}else if ("mssfthr".equals(attributeName)) {
				sdhPortObject.setMssfthr(value);
			}else if ("mspid".equals(attributeName)) {
				sdhPortObject.setMspid(value);
			}else if ("j1mode".equals(attributeName)) {
				sdhPortObject.setJ1mode(value);
			}else if ("expectj1".equals(attributeName)) {
				sdhPortObject.setExpectj1(value);
			}else if ("sendj1".equals(attributeName)) {
				sdhPortObject.setSendj1(value);
			}else if ("receivedj1".equals(attributeName)) {
				sdhPortObject.setReceivedj1(value);
			}else if ("checkj1".equals(attributeName)) {
				sdhPortObject.setCheckj1(value);
			}else if ("expectc2".equals(attributeName)) {
				sdhPortObject.setExpectc2(value);
			}else if ("sendc2".equals(attributeName)) {
				sdhPortObject.setSendc2(value);
			}else if ("checkc2".equals(attributeName)) {
				sdhPortObject.setCheckc2(value);
			}else if ("receivedc2".equals(attributeName)) {
				sdhPortObject.setReceivedc2(value);
			}else if ("hpsdthr".equals(attributeName)) {
				sdhPortObject.setHpsdthr(value);
			}else if ("hpsfthr".equals(attributeName)) {
				sdhPortObject.setHpsfthr(value);
			}else if ("switchport".equals(attributeName)) {
				sdhPortObject.setSwitchport(value);
			}
		}else if ("ac".equals(oldoldAttributeName)) {
			//ac 
			SdhAcObject sdhac = new SdhAcObject();
			if ("ifname".equals(attributeName)) {
				sdhac.setIfname(value);
			}else if ("ifindex".equals(attributeName)) {
				sdhac.setIfindex(value);
			}else if ("desc".equals(attributeName)) {
				sdhac.setDesc(value);
			}else if ("admin".equals(attributeName)) {
				sdhac.setAdmin(value);
			}else if ("oper".equals(attributeName)) {
//				sdhac.setOper(value); 
				sdhac.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))) );
			}else if ("perprofile".equals(attributeName)) {
				sdhac.setPerprofile(value);
			}else if ("ref".equals(attributeName)) {
				sdhac.setRef(value);
			}else if ("expectj2".equals(attributeName)) {
				sdhac.setExpectj2(value);
			}else if ("sendj2".equals(attributeName)) {
				sdhac.setSendj2(value);
			}else if ("receivedj2".equals(attributeName)) {
				sdhac.setReceivedj2(value);
			}else if ("expectv5".equals(attributeName)) {
				sdhac.setExpectv5(value);
			}else if ("sendv5".equals(attributeName)) {
				sdhac.setSendv5(value);
			}else if ("receivedv5".equals(attributeName)) {
				sdhac.setReceivedv5(value);
			}else if ("checkv5".equals(attributeName)) {
				sdhac.setCheckv5(value);
			}else if ("sdhifindex".equals(attributeName)) {
				sdhac.setSdhifindex(value);
			}else if ("label".equals(attributeName)) {
				sdhac.setLabel(value);
			}else if ("lblhwres".equals(attributeName)) {
				sdhac.setLblhwres(value);
			}else if (isNull) {
				return null;
			}
			sdhPortObject.getSdhAcList().add(sdhac);
		} else if (isNull) {
			return null;
		}

		return sdhPortObject;
	}

	private Object setRedistributeValue(String type, String value, String attributeName, Object object) {
		RedistributeObject redistributeObject = null;
		if (object == null) {
			isNull = true;
			redistributeObject = new RedistributeObject();
		} else {
			isNull = false;
			redistributeObject = (RedistributeObject) object;
		}

		if ("default".equals(oldoldAttributeName)) {
			redistributeObject.setRedistribute_type("default");
			if ("type".equals(attributeName)) {
				redistributeObject.setType(value);
			} 
			else if ("metrictype".equals(attributeName)) {
				redistributeObject.setMetrictype(value);
			} else if ("metric".equals(attributeName)) {
				redistributeObject.setMetric(Integer.valueOf(value));
			} else if ("enable".equals(attributeName)) {
				redistributeObject.setEnable(value.equals("true")?1:0);
			}
			else if (isNull) {
				return null;
			}
		} else if ("mcn".equals(oldoldAttributeName)) {
			redistributeObject.setRedistribute_type("mcn");
			if ("metrictype".equals(attributeName)) {
				redistributeObject.setMetrictype(value);
			} else if ("metric".equals(attributeName)) {
				redistributeObject.setMetric(Integer.valueOf(value));
			}else if ("enable".equals(attributeName)) {
				redistributeObject.setEnable(value.equals("true")?1:0);
			}
		} else if ("static".equals(oldoldAttributeName)) {
			redistributeObject.setRedistribute_type("static");
			if ("metrictype".equals(attributeName)) {
				redistributeObject.setMetrictype(value);
			} else if ("metric".equals(attributeName)) {
				redistributeObject.setMetric(Integer.valueOf(value));
			}else if ("enable".equals(attributeName)) {
				redistributeObject.setEnable(value.equals("true")?1:0);
			}
		} else if (isNull) {
			return null;
		}
		return redistributeObject;
	}

	private Object setAllETHPortValue(String type, String value, String attributeName, Object object) {
		EthPortObject ethPortObject = null;
		if (object == null) {
			isNull = true;
			ethPortObject = new EthPortObject();
		} else {
			isNull = false;
			ethPortObject = (EthPortObject) object;
		}

		ethPortObject.setName(oldoldAttributeName);
		if (attributeName.equals("admin")) {
			ethPortObject.setAdmin(value);
		} else if (attributeName.equals("mac")&&( !"nbr".equals(oldAttributeName) )) {
			if(ethPortObject.getMac().equals(""))ethPortObject.setMac(value);
//System.out.println("`````name="+ethPortObject.getName()+" ;Mac="+ethPortObject.getMac());			
		}else if (attributeName.equals("oper")) {
			ethPortObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(Integer.valueOf(value)))));
		} else if (attributeName.equals("ifname")) {
			ethPortObject.setIfname(value);
		} else if (attributeName.equals("role")) {
			ethPortObject.setRole(value);
		} else if (attributeName.equals("aspeed")) {
			ethPortObject.setAspeed(value);
		} else if (attributeName.equals("speed")) {
			ethPortObject.setSpeed(value);
		}  else if (attributeName.equals("framelen")) {
			ethPortObject.setFramelen(value);
		} else if (attributeName.equals("fc")) {
			ethPortObject.setFc(value);
		} else if (attributeName.equals("wavelength")) {
			ethPortObject.setWavelength(value);
		} else if (attributeName.equals("sfptype")) {
			ethPortObject.setSfptype(value);
		} else if (attributeName.equals("sfpvendor")) {
			ethPortObject.setSfpvendor(value);
		} else if (attributeName.equals("xgwan")) {
			ethPortObject.setXgwan(value);
		} else if (attributeName.equals("permitpktloop")) {
			ethPortObject.setPermitpktloop(value);
		} else if (attributeName.equals("sfpexptype")) {
			ethPortObject.setSfpexptype(value);
		}
// add by 20130814
		else if (attributeName.equals("desc")) {
			ethPortObject.setDesc(value);
		}else if (attributeName.equals("ifindex")) {
			ethPortObject.setIfindex(value);
		}else if (attributeName.equals("perprofile")) {
			ethPortObject.setPerprofile(value);
		}else if (attributeName.equals("ref")) {
			ethPortObject.setRef(value);
		}else if (attributeName.equals("als")) {
			ethPortObject.setAls(value);
		}else if (attributeName.equals("alsdelay")) {
			ethPortObject.setAlsdelay(value);
		}else if (attributeName.equals("alsshtup")) {
			ethPortObject.setAlsshtup(value);
		}else if (attributeName.equals("arpproto_tocpu")) {
			ethPortObject.setArpproto_tocpu(value);
		}else if (attributeName.equals("dnugroup")) {
			ethPortObject.setDnugroup(value);
		}else if (attributeName.equals("isolation")) {
			ethPortObject.setIsolation(value);
		}else if (attributeName.equals("iused")) {
			ethPortObject.setIused(value);
		}else if (attributeName.equals("l2aclhwres")) {
			ethPortObject.setL2aclhwres(value);
		}else if (attributeName.equals("l3aclhwres")) {
			ethPortObject.setL3aclhwres(value);
		}else if (attributeName.equals("mirror")) {
			ethPortObject.setMirror(value);
		}else if (attributeName.equals("neType")) {
			ethPortObject.setNeType(value);
		}else if (attributeName.equals("oused")) {
			ethPortObject.setOused(value);
		}else if (attributeName.equals("perprofile")) {
			ethPortObject.setPerprofile(value);
		}else if (attributeName.equals("portType")) {
			ethPortObject.setPortType(value);
		}else if (attributeName.equals("ref")) {
			ethPortObject.setRef(value);
		}else if (attributeName.equals("ringid")) {
			ethPortObject.setRingid(value);
		}else if (attributeName.equals("schmode")) {
			ethPortObject.setSchmode(value);
		}else if (attributeName.equals("sfpvendor")) {
			ethPortObject.setSfpvendor(value);
		}else if (attributeName.equals("slowproto_tocpu")) {
			ethPortObject.setSlowproto_tocpu(value);
		}else if (attributeName.equals("ssmoutputenable")) {
			ethPortObject.setSsmoutputenable(value);
		}else if (attributeName.equals("stringernal")) {
			ethPortObject.setStringernal(value);
		}else if (attributeName.equals("switchport")) {
			ethPortObject.setSwitchport(value);
		}else if (attributeName.equals("type")) {
			ethPortObject.setType(value);
		}else if (attributeName.equals("vegc")) {
			ethPortObject.setVegc(value);
		}
		else if ("nni".equals(oldAttributeName)) {
			if ("llspexptoclr".equals(attributeName)) {
				ethPortObject.getNni().setLlspexptoclr(value);
			} else if ("eelspcostoexp".equals(attributeName)) {
				ethPortObject.getNni().setEelspcostoexp(value);
			} else if ("eelspexptocos".equals(attributeName)) {
				ethPortObject.getNni().setEelspexptocos(value);
			}else if ("egbwlimit".equals(attributeName)) {
				ethPortObject.getNni().setEgbwlimit(value);
			}else if ("elspcostoexp".equals(attributeName)) {
				ethPortObject.getNni().setElspcostoexp(value);
			}else if ("elspexptocos".equals(attributeName)) {
				ethPortObject.getNni().setElspexptocos(value);
			}else if ("inbwlimit".equals(attributeName)) {
				ethPortObject.getNni().setInbwlimit(value);
			}else if ("llspclrtoexp".equals(attributeName)) {
				ethPortObject.getNni().setLlspclrtoexp(value);
			}else if ("pvid".equals(attributeName)) {
				ethPortObject.getNni().setPvid(value);
			}else if (isNull) {
				return null;
			}
		}else if ("oam".equals(oldAttributeName)) {
			if ("cvintvl".equals(attributeName)) {
				ethPortObject.getOam().setCvintvl(value);
			} else if ("encsf".equals(attributeName)) {
				ethPortObject.getOam().setEncsf(value);
			}else if ("endm".equals(attributeName)) {
				ethPortObject.getOam().setEndm(value);
			}else if ("enlm".equals(attributeName)) {
				ethPortObject.getOam().setEnlm(value);
			}else if ("hwres".equals(attributeName)) {
				ethPortObject.getOam().setHwres(value);
			}else if ("iflck".equals(attributeName)) {
				ethPortObject.getOam().setIflck(value);
			}else if ("lpbtimeout".equals(attributeName)) {
				ethPortObject.getOam().setLpbtimeout(value);
			}else if ("lvl".equals(attributeName)) {
				ethPortObject.getOam().setLvl(value);
			}else if ("megid".equals(attributeName)) {
				ethPortObject.getOam().setMegid(value);
			}else if ("peer".equals(attributeName)) {
				ethPortObject.getOam().setPeer(value);
			}else if (isNull) {
				return null;
			}
		}	
		
//end	l2aclhwres	
		else if ("tpoam".equals(oldAttributeName)) {
			if ("lvl".equals(attributeName))
				ethPortObject.getOam().setLvl(value);
			else if ("peer".equals(attributeName))
				ethPortObject.getOam().setPeer(value);
			else if ("megid".equals(attributeName))
				ethPortObject.getOam().setMegid(value);
			else if ("hwres".equals(attributeName))
				ethPortObject.getOam().setHwres(value);
			else if ("mepid".equals(attributeName))
				ethPortObject.getOam().setMepid(value);
			else if ("enlm".equals(attributeName))
				ethPortObject.getOam().setEnlm(value);
			else if ("endm".equals(attributeName))
				ethPortObject.getOam().setEndm(value);
			else if ("cvintvl".equals(attributeName))
				ethPortObject.getOam().setCvintvl(value);
			else if ("lpbtimeout".equals(attributeName))
				ethPortObject.getOam().setLpbtimeout(value);
			else if ("iflck".equals(attributeName))
				ethPortObject.getOam().setIflck(value);
			else if (isNull)
				return null;
		} else if ("lag".equals(oldAttributeName)) {
			if ("lagid".equals(attributeName)) {
				ethPortObject.getLag().setLagid(value);
			} else if ("name".equals(attributeName)) {
				ethPortObject.getLag().setName(value);
			} else if ("status".equals(attributeName)) {
				ethPortObject.getLag().setStatus(value);
			}else if ("wtrtime".equals(attributeName)) {
				ethPortObject.getLag().setWtrtime(value);
			} else if (isNull) {
				return null;
			}
		} else if ("uni".equals(oldAttributeName)) {
			if ("oclrmode".equals(attributeName)) {
				ethPortObject.getUni().setOclrmode(value);
			} else if ("iclrmode".equals(attributeName)) {
				ethPortObject.getUni().setIclrmode(value);
			} else if ("cos2vlanpri".equals(attributeName)) {
				ethPortObject.getUni().setCos2vlanpri(value);
			} else if ("acl".equals(attributeName)) {
				ethPortObject.getUni().setAcl(value);
			}else if ("bcastlimit".equals(attributeName)) {
				ethPortObject.getUni().setBcastlimit(value);
			}else if ("dlflimit".equals(attributeName)) {
				ethPortObject.getUni().setDlflimit(value);
			}else if ("egbwlimit".equals(attributeName)) {
				ethPortObject.getUni().setEgbwlimit(value);
			}else if ("encap".equals(attributeName)) {
				ethPortObject.getUni().setEncap(value);
			}else if ("lptstat".equals(attributeName)) {
				ethPortObject.getUni().setLptstat(value);
			}else if ("inbwlimit".equals(attributeName)) {
				ethPortObject.getUni().setInbwlimit(value);
			}else if ("lptstatby".equals(attributeName)) {
				ethPortObject.getUni().setLptstatby(value);
			}else if ("mcastlimit".equals(attributeName)) {
				ethPortObject.getUni().setMcastlimit(value);
			}else if ("pvid".equals(attributeName)) {
				ethPortObject.getUni().setPvid(value);
			}else if ("pvpri".equals(attributeName)) {
				ethPortObject.getUni().setPvpri(value);
			}else if ("sdtpid".equals(attributeName)) {
				ethPortObject.getUni().setSdtpid(value);
			}else if ("tpid".equals(attributeName)) {
				ethPortObject.getUni().setTpid(value);
			}else if ("vlanmode".equals(attributeName)) {
				ethPortObject.getUni().setVlanmode(value);
			}else if ("vlanpri2cng".equals(attributeName)) {
				ethPortObject.getUni().setVlanpri2cng(value);
			}else if (isNull) {
				return null;
			}
		}else if ("cs3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs3().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs3().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs3().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("cs7".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs7().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs7().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs7().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs7().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs7().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs7().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("cs6".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs6().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs6().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs6().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs6().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs6().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs6().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("ef".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getEf().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getEf().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getEf().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getEf().setGreenwredend(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getEf().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getEf().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af4".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf4().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf4().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf4().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf4().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf4().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf4().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf4().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf4().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf4().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf4().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf4().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf3().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf3().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf3().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf3().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf3().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf3().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf3().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf3().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af2".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf2().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf2().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf2().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf2().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf2().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf2().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf2().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf2().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf2().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf2().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf2().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af1".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf1().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf1().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf1().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf1().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf1().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf1().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf1().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf1().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf1().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf1().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf1().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("be".equals(oldAttributeName)) {
			if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getBe().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getBe().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getBe().setYellowwredend(value);
			} else if ("weight".equals(attributeName)) {
				ethPortObject.getBe().setWeight(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getBe().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("nbr".equals(oldAttributeName)) {
//System.out.println("++++----++++ alleth.nbr."+attributeName+" . value="+value);		
			if ("stat".equals(attributeName)) {
				ethPortObject.getNbr().setStat(value);
			} else if ("smac".equals(attributeName)) {
				ethPortObject.getNbr().setSmac(value);
			} else if ("mac".equals(attributeName)) {			
				ethPortObject.getNbr().setMac(value);
			}else if ("ccn".equals(attributeName)) {
				ethPortObject.getNbr().setCcn(value);
			}else if ("ifidx".equals(attributeName)) {
				ethPortObject.getNbr().setIfidx(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).substring(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).indexOf("/") + 1));
			} else if ("neid".equals(attributeName)) {
				ethPortObject.getNbr().setNeid(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if (isNull) {
				return null;
			}
		} else if ("tpoam".equals(oldAttributeName)) {
			if ("stat".equals(attributeName)) {
				ethPortObject.getNbr().setStat(value);
			} else if ("mac".equals(attributeName)) {
				ethPortObject.getNbr().setMac(value);
			} else if ("ifidx".equals(attributeName)) {
				ethPortObject.getNbr().setIfidx(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).substring(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).indexOf("/") + 1));
			} else if ("neid".equals(attributeName)) {
				ethPortObject.getNbr().setNeid(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}
		return ethPortObject;
	}

	private Object setElanValue(String type, String value, String attributeName, Object object) {
		ELanObject elanObject = null;
		if (object == null) {
			isNull = true;
			elanObject = new ELanObject();
		} else {
			isNull = false;
			elanObject = (ELanObject) object;
		}
		elanObject.setName(oldoldAttributeName);
		if ("oper".equals(attributeName)) {
			elanObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("admin".equals(attributeName)) {
			elanObject.setAdmin(value);
		} else if ("portlist".equals(oldAttributeName)) {
			String s = "";
			if (CoderUtils.isNum(value)) {
				s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(attributeName)));
			} else {
				s = value;
			}

			if (s.indexOf("pw") != -1) {

				elanObject.getPortList().getPwList().add(s);
			} else {
				elanObject.getPortList().setAc(s);
			}
		} else if (isNull) {
			return null;
		}
		return elanObject;
	}

	private Object setAcQosValue(String type, String value, String attributeName, Object object) {
		AcQosObject acQosObject = null;
		if (object == null) {
			isNull = true;
			acQosObject = new AcQosObject();
		} else {
			isNull = false;
			acQosObject = (AcQosObject) object;
		}

		if ("default".equals(oldAttributeName)) {
			acQosObject.setName(oldoldAttributeName);
			if ("cos".equals(attributeName)) {
				acQosObject.setCos(value);
			} else if ("cir".equals(attributeName)) {
				acQosObject.setCir(value);
			} else if ("cbs".equals(attributeName)) {
				acQosObject.setCbs(value);
			} else if ("eir".equals(attributeName)) {
				acQosObject.setEir(value);
			} else if ("ebs".equals(attributeName)) {
				acQosObject.setEbs(value);
			} else if ("pir".equals(attributeName)) {
				acQosObject.setPir(value);
			} else if ("seq".equals(attributeName)) {
				acQosObject.setSeq(value);
			} else if (isNull) {
				return null;
			}
		}else if (oldAttributeName.startsWith("l2")||"0".equals(oldAttributeName)||"1".equals(oldAttributeName)||"2".equals(oldAttributeName)||"3".equals(oldAttributeName)||"4".equals(oldAttributeName)||"5".equals(oldAttributeName)||"6".equals(oldAttributeName)||"7".equals(oldAttributeName) ) {
			if(!acQosObject.getXFAcQosListM().containsKey(oldAttributeName)){//
				AcQosObject xfAcQos = new AcQosObject();
				xfAcQos.setName(oldAttributeName);
				if ("cos".equals(attributeName)) {
					xfAcQos.setCos(value);
				} else if ("cir".equals(attributeName)) {
					xfAcQos.setCir(value);
				} else if ("cbs".equals(attributeName)) {
					xfAcQos.setCbs(value);
				} else if ("eir".equals(attributeName)) {
					xfAcQos.setEir(value);
				} else if ("ebs".equals(attributeName)) {
					xfAcQos.setEbs(value);
				} else if ("pir".equals(attributeName)) {
					xfAcQos.setPir(value);
				} else if ("seq".equals(attributeName)) {
					xfAcQos.setSeq(value);
				} 
				acQosObject.getXFAcQosListM().put(oldAttributeName, xfAcQos);
				
			}else{
//				acQosObject.getXFAcQosListM().get(oldAttributeName);
				if ("cos".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setCos(value);
				} else if ("cir".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setCir(value);
				} else if ("cbs".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setCbs(value);
				} else if ("eir".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setEir(value);
				} else if ("ebs".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setEbs(value);
				} else if ("pir".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setPir(value);
				} else if ("seq".equals(attributeName)) {
					acQosObject.getXFAcQosListM().get(oldAttributeName).setSeq(value);
				} 
			}			
		}else if (isNull) {
			return null;
		}
		return acQosObject;
	}

	private Object setTreeValue(String type, String value, String attributeName, Object object) {
		ETreeObject etreeObject = null;
		if (object == null) {
			isNull = true;
			etreeObject = new ETreeObject();
		} else {
			isNull = false;
			etreeObject = (ETreeObject) object;
		}
		etreeObject.setName(oldoldAttributeName);
		if ("oper".equals(attributeName)) {
			etreeObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("admin".equals(attributeName)) {
			etreeObject.setAdmin(value);
		} else if ("root".equals(attributeName)) {
			if (CoderUtils.isNum(value)) {
				etreeObject.setRoot(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
			} else {
				etreeObject.setRoot(value);
			}
		} else if ("portlist".equals(oldAttributeName)) {
			String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(attributeName)));
			if (s.indexOf("pw") != -1) {
				etreeObject.getPortList().getPwList().add(s);
			} else {
				etreeObject.getPortList().setAc(value);
			}
		} else if (isNull) {
			return null;
		}
		return etreeObject;
	}

	private Object setCesValue(String type, String value, String attributeName, Object object) {
		CesObject cesObject = null;
		if (object == null) {
			isNull = true;
			cesObject = new CesObject();
		} else {
			isNull = false;
			cesObject = (CesObject) object;
		}

		cesObject.setName(oldoldAttributeName);
		if ("oper".equals(attributeName)) {
			cesObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("admin".equals(attributeName)) {
			cesObject.setAdmin(value);
		} else if ("portlist".equals(oldAttributeName)) {
			String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(attributeName)));
			if (s.indexOf("pw") != -1) {
				cesObject.getPortList().setPw(s);
			} else {
				cesObject.getPortList().setAc(s);
			}
		} else if (isNull) {
			return null;
		}
		return cesObject;
	}

	private Object setAllAcValue(String type, String value, String attributeName, Object object) {
		AcObject acObject = null;
		if (object == null) {
			isNull = true;
			acObject = new AcObject();
		} else {
			isNull = false;
			acObject = (AcObject) object;
		}
		if (CoderUtils.isNum(oldoldAttributeName)) {
			acObject.setName(oldoldAttributeName);
			if ("oam".equals(oldAttributeName)) {
				if ("lvl".equals(attributeName))
					acObject.getOam().setLvl(value);
				else if ("peer".equals(attributeName))
					acObject.getOam().setPeer(value);
				else if ("megid".equals(attributeName))
					acObject.getOam().setMegid(value);
				else if ("hwres".equals(attributeName))
					acObject.getOam().setHwres(value);
				else if ("mepid".equals(attributeName))
					acObject.getOam().setMepid(value);
				else if ("enlm".equals(attributeName))
					acObject.getOam().setEnlm(value);
				else if ("endm".equals(attributeName))
					acObject.getOam().setEndm(value);
				else if ("cvintvl".equals(attributeName))
					acObject.getOam().setCvintvl(value);
				else if ("lpbtimeout".equals(attributeName))
					acObject.getOam().setLpbtimeout(value);
				else if ("iflck".equals(attributeName))
					acObject.getOam().setIflck(value);
				else if (isNull)
					return null;
			} else if ("admin".equals(attributeName)) {
				acObject.setAdmin(value);
			} else if ("oper".equals(attributeName)) {
				acObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
			} else if ("mode".equals(attributeName)) {
				acObject.setMode(value);
			} else if ("spvlan".equals(attributeName)) {
				acObject.setSpvlan(value);
			} else if ("cevlan".equals(attributeName)) {
				acObject.setCevlan(value);
			} else if ("dualid".equals(attributeName)) {
				acObject.setDualid(value);
			} else if ("qos".equals(attributeName)) {
				acObject.setQos(value);
			}else if ("action".equals(attributeName)) {
				acObject.setAction(value);
			} else if ("desc".equals(attributeName)) {
				acObject.setDesc(value);
			} else if ("ethacout".equals(attributeName)) {
				acObject.setEthacout(value);
			} else if ("ethifindex".equals(attributeName)) {
				acObject.setEthifindex(value);
			} else if ("hwres".equals(attributeName)) {
				acObject.setHwres(value);
			}else if ("hwresref".equals(attributeName)) {
				acObject.setHwresref(value);
			}else if ("ifindex".equals(attributeName)) {
				acObject.setIfindex(value);
			}else if ("ifname".equals(attributeName)) {
				acObject.setIfname(value);
			}else if ("l3iifhwres".equals(attributeName)) {
				acObject.setL3iifhwres(value);
			}else if ("qoshwres".equals(attributeName)) {
				acObject.setQoshwres(value);
			}else if ("ref".equals(attributeName)) {
				acObject.setRef(value);
			}else if ("sdvlan".equals(attributeName)) {
				acObject.setSdvlan(value);
			}else if ("sdvlancfi".equals(attributeName)) {
				acObject.setSdvlancfi(value);
			} else if ("sdvlanpri".equals(attributeName)) {
				acObject.setSdvlanpri(value);
			}else if ("spvlan".equals(attributeName)) {
				acObject.setSpvlan(value);
			}else if ("vthwres".equals(attributeName)) {
				acObject.setVthwres(value);
			}else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}
		return acObject;
	}

	private Object setSdhPwValue(String type, String value, String attributeName, Object object) {
		PwSdhObject pwSdhObject = null;
		if (object == null) {
			isNull = true;
			pwSdhObject = new PwSdhObject();
		} else {
			isNull = false;
			pwSdhObject = (PwSdhObject) object;
		}
		pwSdhObject.setName(oldoldAttributeName);
		if ("oam".equals(oldAttributeName)) {
			if ("lvl".equals(attributeName))
				pwSdhObject.getOam().setLvl(value);
			else if ("peer".equals(attributeName))
				pwSdhObject.getOam().setPeer(value);
			else if ("megid".equals(attributeName))
				pwSdhObject.getOam().setMegid(value);
			else if ("hwres".equals(attributeName))
				pwSdhObject.getOam().setHwres(value);
			else if ("mepid".equals(attributeName))
				pwSdhObject.getOam().setMepid(value);
			else if ("enlm".equals(attributeName))
				pwSdhObject.getOam().setEnlm(value);
			else if ("endm".equals(attributeName))
				pwSdhObject.getOam().setEndm(value);
			else if ("cvintvl".equals(attributeName))
				pwSdhObject.getOam().setCvintvl(value);
			else if ("lpbtimeout".equals(attributeName))
				pwSdhObject.getOam().setLpbtimeout(value);
			else if ("iflck".equals(attributeName))
				pwSdhObject.getOam().setIflck(value);
			else if (isNull)
				return null;
		} else if ("admin".equals(attributeName)) {
			pwSdhObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			pwSdhObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("carrierif".equals(attributeName)) {
			String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
			pwSdhObject.setCarrierif(s.substring(s.indexOf("/") + 1));
		} else if ("peer".equals(attributeName)) {
			pwSdhObject.setPeer(CoderUtils.longToIpAddress(Long.valueOf(value)));
		} else if ("inlabel".equals(attributeName)) {
			pwSdhObject.setInlabel(value);
		} else if ("outlabel".equals(attributeName)) {
			pwSdhObject.setOutlabel(value);
		} else if ("payload".equals(attributeName)) {
			pwSdhObject.setPayload(value);
		}else if ("cos".equals(attributeName)) {
			pwSdhObject.setCos(value);
		} else if (isNull) {
			return null;
		}
		return pwSdhObject;
	}

	private Object setPdhPwValue(String type, String value, String attributeName, Object object) {
		PwPdhObject pwPdhObject = null;
		if (object == null) {
			isNull = true;
			pwPdhObject = new PwPdhObject();
		} else {
			isNull = false;
			pwPdhObject = (PwPdhObject) object;
		}
		pwPdhObject.setName(oldoldAttributeName);
		if ("oam".equals(oldAttributeName)) {
			if ("lvl".equals(attributeName))
				pwPdhObject.getOam().setLvl(value);
			else if ("peer".equals(attributeName))
				pwPdhObject.getOam().setPeer(value);
			else if ("megid".equals(attributeName))
				pwPdhObject.getOam().setMegid(value);
			else if ("hwres".equals(attributeName))
				pwPdhObject.getOam().setHwres(value);
			else if ("mepid".equals(attributeName))
				pwPdhObject.getOam().setMepid(value);
			else if ("enlm".equals(attributeName))
				pwPdhObject.getOam().setEnlm(value);
			else if ("endm".equals(attributeName))
				pwPdhObject.getOam().setEndm(value);
			else if ("cvintvl".equals(attributeName))
				pwPdhObject.getOam().setCvintvl(value);
			else if ("lpbtimeout".equals(attributeName))
				pwPdhObject.getOam().setLpbtimeout(value);
			else if ("iflck".equals(attributeName))
				pwPdhObject.getOam().setIflck(value);
			else if (isNull)
				return null;
		} else if ("admin".equals(attributeName)) {
			pwPdhObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			pwPdhObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("carrierif".equals(attributeName)) {
			String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
			pwPdhObject.setCarrierif(s.substring(s.indexOf("/") + 1));
		} else if ("peer".equals(attributeName)) {
			pwPdhObject.setPeer(CoderUtils.longToIpAddress(Long.valueOf(value)));
		} else if ("inlabel".equals(attributeName)) {
			pwPdhObject.setInlabel(value);
		} else if ("outlabel".equals(attributeName)) {
			pwPdhObject.setOutlabel(value);
		}else if ("payload".equals(attributeName)) {
			pwPdhObject.setPayload(value);
		} else if ("cos".equals(attributeName)) {
			pwPdhObject.setCos(value);
		} else if (isNull) {
			return null;
		}
		return pwPdhObject;
	}

	private Object setQosValue(String type, String value, String attributeName, Object object) {
		QosObject qosObject = null;
		if (object == null) {
			isNull = true;
			if (oldoldAttributeName.indexOf("llsp") != -1) {
				qosObject = new LLSPQosObject("LLSP");
			} else if (oldoldAttributeName.indexOf("elsp") != -1) {
				qosObject = new ELSPQosObject("ELSP");
			} else if (oldoldAttributeName.indexOf("eelsp") != -1) {
				qosObject = new EELSPQosObject("EELSP");
			}
		} else {
			isNull = false;
			if (oldoldAttributeName.indexOf("llsp") != -1) {
				qosObject = (LLSPQosObject) object;
			} else if (oldoldAttributeName.indexOf("elsp") != -1) {
				qosObject = (ELSPQosObject) object;
			} else if (oldoldAttributeName.indexOf("eelsp") != -1) {
				qosObject = (EELSPQosObject) object;
			}
		}

		if (oldoldAttributeName.indexOf("llsp") != -1) {
			((LLSPQosObject) qosObject).setName(oldoldAttributeName);
			if ("cos".equals(attributeName)) {
				((LLSPQosObject) qosObject).setCos(value);
			} else if ("icir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setIcir(value);
			} else if ("icbs".equals(attributeName)) {
				((LLSPQosObject) qosObject).setIcbs(value);
			} else if ("ieir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setIeir(value);
			} else if ("iebs".equals(attributeName)) {
				((LLSPQosObject) qosObject).setIebs(value);
			} else if ("ocir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setOcir(value);
			} else if ("ocbs".equals(attributeName)) {
				((LLSPQosObject) qosObject).setOcbs(value);
			} else if ("oeir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setOeir(value);
			} else if ("oebs".equals(attributeName)) {
				((LLSPQosObject) qosObject).setOebs(value);
			} else if ("ipir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setIpir(value);
			} else if ("opir".equals(attributeName)) {
				((LLSPQosObject) qosObject).setOpir(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldoldAttributeName.indexOf("elsp") != -1) {
			((ELSPQosObject) qosObject).setName(oldoldAttributeName);
			if ("icbs".equals(attributeName)) {
				((ELSPQosObject) qosObject).setName(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldoldAttributeName.indexOf("eelsp") != -1) {
			((EELSPQosObject) qosObject).setName(oldoldAttributeName);
			if ("name".equals(attributeName)) {
				((EELSPQosObject) qosObject).setName(value);
			} else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}

		return qosObject;
	}

	private Object setElineValue(String type, String value, String attributeName, Object object) {
		ELineObject elinePortObject = null;
		if (object == null) {
			isNull = true;
			elinePortObject = new ELineObject();
		} else {
			isNull = false;
			elinePortObject = (ELineObject) object;
		}

		elinePortObject.setName(oldoldAttributeName);
		if ("oper".equals(attributeName)) {
			elinePortObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("admin".equals(attributeName)) {
			elinePortObject.setAdmin(value);
		} else if ("portlist".equals(oldAttributeName)) {
			if (!elinePortObject.getPortList().isStatus()) {
				elinePortObject.getPortList().setAc(value);
				elinePortObject.getPortList().setStatus(true);
			} else {
				elinePortObject.getPortList().setPw(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
			}
		} else if (isNull) {
			return null;
		}
		return elinePortObject;
	}

	private Object setXcValue(String type, String value, String attributeName, Object object) {
		XcObject xcObject = null;
		if (object == null) {
			isNull = true;
			xcObject = new XcObject();
			XcIFObject[] xcIFObjectList = new XcIFObject[2];
			xcIFObjectList[0] = new XcIFObject();
			xcIFObjectList[1] = new XcIFObject();
			xcObject.setXcIFObjects(xcIFObjectList);
		} else {
			isNull = false;
			xcObject = (XcObject) object;
		}
		xcObject.setName(oldoldAttributeName);
		if ("1".equals(oldAttributeName)) {
			if ("inlabel".equals(attributeName)) {
				xcObject.getXcIFObjects()[0].setInlabel(value);
			} else if ("outlabel".equals(attributeName)) {
				xcObject.getXcIFObjects()[0].setOutlabel(value);
			} else if ("carrierif".equals(attributeName)) {
				String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
				xcObject.getXcIFObjects()[0].setCarrierif(s.substring(s.indexOf("/") + 1));
			} else if (isNull) {
				return null;
			}
		} else if ("2".equals(oldAttributeName)) {
			if ("inlabel".equals(attributeName)) {
				xcObject.getXcIFObjects()[1].setInlabel(value);
			} else if ("outlabel".equals(attributeName)) {
				xcObject.getXcIFObjects()[1].setOutlabel(value);
			} else if ("carrierif".equals(attributeName)) {
				String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
				xcObject.getXcIFObjects()[1].setCarrierif(s.substring(s.indexOf("/") + 1));
			} else if (isNull) {
				return null;
			}
		} else if ("oam".equals(oldAttributeName)) {
			if ("megid".equals(attributeName))
				xcObject.getOamMipObject().setMegid(value);
			else if ("lvl".equals(attributeName))
				xcObject.getOamMipObject().setLvl(value);
			else if ("hwres".equals(attributeName))
				xcObject.getOamMipObject().setHwres(value);
			else if (isNull)
				return null;
		} else if ("mipid".equals(attributeName)) {
			xcObject.getOamMipObject().setMipid(value);
		} else if ("mepids".equals(oldAttributeName)) {
			if ("1".equals(attributeName))
				xcObject.getOamMipObject().setAMepId(value);
			else if ("2".equals(attributeName))
				xcObject.getOamMipObject().setZMepId(value);
			else if (isNull)
				return null;
		} else {
			if ("admin".equals(attributeName)) {
				xcObject.setAdmin(value);
			} else if ("oper".equals(attributeName)) {
				xcObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
			} else if ("role".equals(attributeName)) {
				xcObject.setRole(value);
			} else if ("qos".equals(attributeName)) {
				xcObject.setQos(value);
			} else if ("ingress".equals(attributeName)) {
				xcObject.setIngress(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if ("egress".equals(attributeName)) {
				xcObject.setEgress(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if (isNull) {
				return null;
			}
		}
		return xcObject;
	}

	private Object setPdhPortValue(String type, String value, String attributeName, Object object) {
		PdhPortObject pdhPortObject = null;
		if (object == null) {
			isNull = true;
			pdhPortObject = new PdhPortObject();
		} else {
			isNull = false;
			pdhPortObject = (PdhPortObject) object;
		}

		pdhPortObject.setName(oldoldAttributeName);
		if ("admin".equals(attributeName)) {
			pdhPortObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			pdhPortObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("linecode".equals(attributeName)) {
			pdhPortObject.setLinecode(value);
		} else if ("termination".equals(attributeName)) {
			pdhPortObject.setTermination(value);
		} else if ("desc".equals(attributeName)) {
			pdhPortObject.setDesc(value);
		}else if ("dnugroup".equals(attributeName)) {
			pdhPortObject.setDnugroup(value);
		}else if ("ifindex".equals(attributeName)) {
			pdhPortObject.setIfindex(value);
		}else if ("ifname".equals(attributeName)) {
			pdhPortObject.setIfname(value);
		}else if ("label".equals(attributeName)) {
			pdhPortObject.setLabel(value);
		}else if ("lblhwres".equals(attributeName)) {
			pdhPortObject.setLblhwres(value);
		}else if ("loopback".equals(attributeName)) {
			pdhPortObject.setLoopback(value);
		}else if ("mode".equals(attributeName)) {
			pdhPortObject.setMode(value);
		}else if ("perprofile".equals(attributeName)) {
			pdhPortObject.setPerprofile(value);
		}else if ("ref".equals(attributeName)) {
			pdhPortObject.setRef(value);
		}else if ("switchport".equals(attributeName)) {
			pdhPortObject.setSwitchport(value);
		}else if (isNull) {
			return null;
		}
		return pdhPortObject;
	}

	private Object setLspValue(String type, String value, String attributeName, Object object) {
		LSPObject lspObject = null;
		if (object == null) {
			isNull = true;
			lspObject = new LSPObject();
		} else {
			isNull = false;
			lspObject = (LSPObject) object;
		}
//System.out.println("setLspValue . attributeName="+attributeName+" ;oldoldAttributeName="+oldoldAttributeName+" ;attributeName="+attributeName+" ;value="+value);		
		lspObject.setName(oldoldAttributeName);
		if ("admin".equals(attributeName)) {
			lspObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			lspObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("inlabel".equals(attributeName)) {
			lspObject.setInlabel(value);
		} else if ("outlabel".equals(attributeName)) {
			lspObject.setOutlabel(value);
		} else if ("peer".equals(attributeName)) {
			lspObject.setPeer(value);
		} else if ("sdthr".equals(attributeName)) {
			lspObject.setSdthr(value);
		} else if ("carrierif".equals(attributeName)) {
			lspObject.setCarrierif(value);
		} else if (isNull) {
			return null;
		}
		return lspObject;
	}

	private Object setAllLspValue(String type, String value, String attributeName, Object object) {
		LSPObject lspObject = null;
		if (object == null) {
			isNull = true;
			lspObject = new LSPObject();
		} else {
			isNull = false;
			lspObject = (LSPObject) object;
		}
		if (!"".equals(oldoldAttributeName)) {
			lspObject.setName(oldoldAttributeName);
			if ("oam".equals(oldAttributeName)) {
				if ("lvl".equals(attributeName))
					lspObject.getOam().setLvl(value);
				else if ("peer".equals(attributeName))
					lspObject.getOam().setPeer(value);
				else if ("megid".equals(attributeName))
					lspObject.getOam().setMegid(value);
				else if ("hwres".equals(attributeName))
					lspObject.getOam().setHwres(value);
				else if ("mepid".equals(attributeName))
					lspObject.getOam().setMepid(value);
				else if ("enlm".equals(attributeName))
					lspObject.getOam().setEnlm(value);
				else if ("endm".equals(attributeName))
					lspObject.getOam().setEndm(value);
				else if ("cvintvl".equals(attributeName))
					lspObject.getOam().setCvintvl(value);
				else if ("lpbtimeout".equals(attributeName))
					lspObject.getOam().setLpbtimeout(value);
				else if ("iflck".equals(attributeName))
					lspObject.getOam().setIflck(value);
				else if (isNull)
					return null;
			} else if ("admin".equals(attributeName)) {
				lspObject.setAdmin(value);
			} else if ("oper".equals(attributeName)) {
				lspObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
			} else if ("inlabel".equals(attributeName)) {
				lspObject.setInlabel(value);
			} else if ("outlabel".equals(attributeName)) {
				lspObject.setOutlabel(value);
			} else if ("peer".equals(attributeName)) {
				lspObject.setPeer(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if ("sdthr".equals(attributeName)) {
				lspObject.setSdthr(value);
			} else if ("carrierif".equals(attributeName)) {
				String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
				lspObject.setCarrierif(s.substring(s.indexOf("/") + 1));
			} else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}

		return lspObject;
	}

	private Object setEthPwValue(String type, String value, String attributeName, Object object) {
		PwEthObject pwEthObject = null;
		if (object == null) {
			isNull = true;
			pwEthObject = new PwEthObject();
		} else {
			isNull = false;
			pwEthObject = (PwEthObject) object;
		}

		pwEthObject.setName(oldoldAttributeName);
		if ("oam".equals(oldAttributeName)) {
			if ("lvl".equals(attributeName))
				pwEthObject.getOam().setLvl(value);
			else if ("peer".equals(attributeName))
				pwEthObject.getOam().setPeer(value);
			else if ("megid".equals(attributeName))
				pwEthObject.getOam().setMegid(value);
			else if ("hwres".equals(attributeName))
				pwEthObject.getOam().setHwres(value);
			else if ("mepid".equals(attributeName))
				pwEthObject.getOam().setMepid(value);
			else if ("enlm".equals(attributeName))
				pwEthObject.getOam().setEnlm(value);
			else if ("endm".equals(attributeName))
				pwEthObject.getOam().setEndm(value);
			else if ("cvintvl".equals(attributeName))
				pwEthObject.getOam().setCvintvl(value);
			else if ("lpbtimeout".equals(attributeName))
				pwEthObject.getOam().setLpbtimeout(value);
			else if ("iflck".equals(attributeName))
				pwEthObject.getOam().setIflck(value);
			else if (isNull)
				return null;
		} else if ("admin".equals(attributeName)) {
			pwEthObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			pwEthObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("carrierif".equals(attributeName)) {
			String s = AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value)));
			pwEthObject.setCarrierif(s.substring(s.indexOf("/") + 1));
		} else if ("peer".equals(attributeName)) {
			pwEthObject.setPeer(CoderUtils.longToIpAddress(Long.valueOf(value)));
		} else if ("inlabel".equals(attributeName)) {
			pwEthObject.setInlabel(value);
		} else if ("outlabel".equals(attributeName)) {
			pwEthObject.setOutlabel(value);
		} else if ("qos".equals(attributeName)) {
			pwEthObject.setQos(value);
		}else if ("desc".equals(attributeName)) {
			pwEthObject.setDesc(value);
		} else if ("ifindex".equals(attributeName)) {
			pwEthObject.setIfindex(value);
		}else if ("ifname".equals(attributeName)) {
			pwEthObject.setIfname(value);
		}else if ("lblhwres".equals(attributeName)) {
			pwEthObject.setLblhwres(value);
		}else if ("lptstat".equals(attributeName)) {
			pwEthObject.setLptstat(value);
		}else if ("lspqostype".equals(attributeName)) {
			pwEthObject.setLspqostype(value);
		}else if ("owner".equals(attributeName)) {
			pwEthObject.setOwner(value);
		}else if ("perprofile".equals(attributeName)) {
			pwEthObject.setPerprofile(value);
		}else if ("qoshwres".equals(attributeName)) {
			pwEthObject.setQoshwres(value);
		}else if ("ref".equals(attributeName)) {
			pwEthObject.setRef(value);
		}else if ("sdvlan".equals(attributeName)) {
			pwEthObject.setSdvlan(value);
		}else if ("sdvlanpri".equals(attributeName)) {
			pwEthObject.setSdvlanpri(value);
		}else if ("tpid".equals(attributeName)) {
			pwEthObject.setTpid(value);
		}else if ("type".equals(attributeName)) {
			pwEthObject.setType(value);
		}else if (isNull) {
			return null;
		}
		return pwEthObject;
	}

	private Object setTunnelValue(String type, String value, String attributeName, Object object) {
		TunnelObject tunnelObject = null;
		if (object == null) {
			isNull = true;
			tunnelObject = new TunnelObject();
		} else {
			isNull = false;
			tunnelObject = (TunnelObject) object;
		}
//System.out.println("setTunnelValue ."+numberTimes+" ;type="+type+" ;oldAttributeName="+oldAttributeName+" ; oldoldAttributeName="+oldoldAttributeName+" ; attributeName="+attributeName+" ; value="+value);		
		if ("".equals(oldAttributeName)) {
			tunnelObject.setName(oldoldAttributeName);
			if ("admin".equals(attributeName)) {
				tunnelObject.setAdmin(value);
			} else if ("oper".equals(attributeName)) {
				tunnelObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
			} else if ("role".equals(attributeName)) {
				tunnelObject.setRole(value);
			} else if ("lspqostype".equals(attributeName)) {
				tunnelObject.setLspqostype(value);
			} else if ("desc".equals(attributeName)) {
				tunnelObject.setDesc(value);
			} else if ("peerid".equals(attributeName)) {
				tunnelObject.setPeerid(value);
			} else if ("ref".equals(attributeName)) {
				tunnelObject.setRef(value);
			} else if ("lspw".equals(attributeName)) {
//				tunnelObject.setLspw(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
	// add by 20130814 stones
				tunnelObject.setLspw(value);
			} else if ("qos".equals(attributeName)) {
				tunnelObject.setQos(value);
			} else if (isNull) {
				return null;
			}
		}else if ("protection".equals(oldAttributeName)) {
			if ("enaps".equals(attributeName)) {
				tunnelObject.getProtection().setEnaps(value);
			}else if ("type".equals(attributeName)) {
				tunnelObject.getProtection().setType(value);
			}else if ("wtrtime".equals(attributeName)) {
				tunnelObject.getProtection().setWtrtime(value);
			}else if ("holdofftime".equals(attributeName)) {
				tunnelObject.getProtection().setHoldofftime(value);
			}else if ("sdthreshold".equals(attributeName)) {
				tunnelObject.getProtection().setSdthreshold(value);
			}else if ("worklsp".equals(attributeName)) {
				tunnelObject.getProtection().setWorklsp(value);
			}else if ("prtlsp".equals(attributeName)) {
				tunnelObject.getProtection().setPrtlsp(value);
			}else if ("apsstat".equals(attributeName)) {
				tunnelObject.getProtection().setApsstat(value);
			}else if ("sel".equals(attributeName)) {
				tunnelObject.getProtection().setSel(value);
			}else if ("arrayidx".equals(attributeName)) {
				tunnelObject.getProtection().setArrayidx(value);
			}else if ("apscmd".equals(attributeName)) {
				tunnelObject.getProtection().setApscmd(value);
			}
		} else if (isNull) {
			return null;
		}
		return tunnelObject;
	}

	private Object setAcValue(String type, String value, String attributeName, Object object) {
		AcObject acObject = null;
		if (object == null) {
			isNull = true;
			acObject = new AcObject();
		} else {
			isNull = false;
			acObject = (AcObject) object;
		}

		if ("ifname".equals(attributeName)) {
			acObject.setName(value);
		} else if ("admin".equals(attributeName)) {
			acObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			acObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("mode".equals(attributeName)) {
			acObject.setMode(value);
		} else if ("spvlan".equals(attributeName)) {
			acObject.setSpvlan(value);
		} else if ("cevlan".equals(attributeName)) {
			acObject.setCevlan(value);
		} else if ("dualid".equals(attributeName)) {
			acObject.setDualid(value);
		} else if ("qos".equals(attributeName)) {
			acObject.setQos(value);
		}else if ("action".equals(attributeName)) {
			acObject.setAction(value);
		} else if ("desc".equals(attributeName)) {
			acObject.setDesc(value);
		} else if ("ethacout".equals(attributeName)) {
			acObject.setEthacout(value);
		} else if ("ethifindex".equals(attributeName)) {
			acObject.setEthifindex(value);
		} else if ("hwres".equals(attributeName)) {
			acObject.setHwres(value);
		}else if ("hwresref".equals(attributeName)) {
			acObject.setHwresref(value);
		}else if ("ifindex".equals(attributeName)) {
			acObject.setIfindex(value);
		}else if ("ifname".equals(attributeName)) {
			acObject.setIfname(value);
		}else if ("l3iifhwres".equals(attributeName)) {
			acObject.setL3iifhwres(value);
		}else if ("qoshwres".equals(attributeName)) {
			acObject.setQoshwres(value);
		}else if ("ref".equals(attributeName)) {
			acObject.setRef(value);
		}else if ("sdvlan".equals(attributeName)) {
			acObject.setSdvlan(value);
		}else if ("sdvlancfi".equals(attributeName)) {
			acObject.setSdvlancfi(value);
		} else if ("sdvlanpri".equals(attributeName)) {
			acObject.setSdvlanpri(value);
		}else if ("spvlan".equals(attributeName)) {
			acObject.setSpvlan(value);
		}else if ("vthwres".equals(attributeName)) {
			acObject.setVthwres(value);
		} else if (isNull) {
			return null;
		}
		return acObject;
	}

	private Object setOspfAreaValue(String type, String value, String attributeName, Object object) {
		OSPFAREAObject OSPFAREAObject = null;
		if (object == null) {
			isNull = true;
			OSPFAREAObject = new OSPFAREAObject();
		} else {
			isNull = false;
			OSPFAREAObject = (OSPFAREAObject) object;
		}		
		if(oldoldAttributeName.contains("area.")){
			String[] neId=oldoldAttributeName.split("\\.");
			if(neId.length==2){
				OSPFAREAObject.setNeId(neId[1]);
			}
			if(oldAttributeName.equals("type")){
				if(attributeName.equals("type")){//stub
					OSPFAREAObject.setType(value);
				}else if(attributeName.equals("nosummaries")){//true
					OSPFAREAObject.setNosummaries(value);
				}else if(attributeName.equals("defmetric")){//12
					OSPFAREAObject.setDefmetric(value);
				} 
			}
			
		}
		else if (isNull) {
			return null;
		}
//		if (oldoldAttributeName.indexOf("area") != -1) {
//			OSPFAREAObject.setDefmetric(oldoldAttributeName.substring(oldoldAttributeName.indexOf(".") + 1));
//		} else if (isNull) {
//			return null;
//		}

		return OSPFAREAObject;
	}

	private Object setMCNValue(String type, String value, String attributeName, Object object) {
		MCNObject mcnObject = null;
		if (object == null) {
			isNull = true;
			mcnObject = new MCNObject();
		} else {
			isNull = false;
			mcnObject = (MCNObject) object;
		}

		if ("ip".equals(attributeName)) {
			mcnObject.setIp(CoderUtils.longToIpPrefix(Long.valueOf(value)));
//System.out.println("setObject.line2059 . ip.value="+value);			
//			                CoderUtils.longToIpAddress(Long.valueOf(value))
		} else if ("mtu".equals(attributeName)) {
			mcnObject.setMtu(value);
		} else if (isNull) {
			return null;
		}
		return mcnObject;
	}

	private Object setCCNValue(String type, String value, String attributeName, Object object) {
		CCNObject ccnObject = null;
		if (object == null) {
			isNull = true;
			ccnObject = new CCNObject();
		} else {
			isNull = false;
			ccnObject = (CCNObject) object;
		}

		ccnObject.setName(oldoldAttributeName);
		if ("admin".equals(attributeName)) {
			ccnObject.setAdmin(value);
		} else if ("oper".equals(attributeName)) {
			ccnObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if ("ip".equals(attributeName)) {
			//			[66, 56, -56, 1, 2, 2, 0, 0]
			ccnObject.setIp(CoderUtils.longToIpAndMaskAddress(Long.valueOf(value)));
		} else if ("peer".equals(attributeName)) {
			ccnObject.setPeer(CoderUtils.longToIpAddress(Long.valueOf(value)));
		} else if ("mtu".equals(attributeName)) {
			ccnObject.setMtu(value);
		} else if ("datalink".equals(oldAttributeName)) {
			ccnObject.setDatalink(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
		} else if (isNull) {
			return null;
		}
		return ccnObject;
	}

	private Object setOSPFInterfacesCCNValue(String type, String value, String attributeName, Object object) {
		OSPFinterfacesObject OSPFinterfacesObject = null;
		if (object == null) {
			isNull = true;
			OSPFinterfacesObject = new OSPFinterfacesObject();
		} else {
			isNull = false;
			OSPFinterfacesObject = (OSPFinterfacesObject) object;
		}

		if (oldoldAttributeName != null || !oldoldAttributeName.equals("")) {
			OSPFinterfacesObject.setName(oldoldAttributeName);
		}
		if ("area".equals(attributeName)) {
			OSPFinterfacesObject.setArea(value);
		} else if ("hello_interval".equals(attributeName)) {
			OSPFinterfacesObject.setHello_interval(value);
		} else if ("dead_interval".equals(attributeName)) {
			OSPFinterfacesObject.setDead_interval(value);
		} else if ("retransmit_interval".equals(attributeName)) {
			OSPFinterfacesObject.setRetransmit_interval(value);
		} else if ("transmit_delay".equals(attributeName)) {
			OSPFinterfacesObject.setTransmit_delay(value);
		} else if ("type".equals(attributeName)) {
			OSPFinterfacesObject.setType(value);
		} else if ("passive".equals(attributeName)) {
			OSPFinterfacesObject.setPassive(value);
		} else if ("cost".equals(attributeName)) {
			OSPFinterfacesObject.setCost(value);
		} else if ("prioriy".equals(attributeName)) {
			OSPFinterfacesObject.setPrioriy(value);
		} else if (isNull) {
			return null;
		}
		return OSPFinterfacesObject;
	}

	private Object setOSPFValue(String type, String value, String attributeName, Object object) {
		OSPFObject ospfObject = null;
		if (object == null) {
			isNull = true;
			ospfObject = new OSPFObject();
		} else {
			isNull = false;
			ospfObject = (OSPFObject) object;
		}

		if (attributeName.equals("abr")) {
			ospfObject.setAbr(value);
		} else if ("rt_id_area".equals(attributeName)) {
			ospfObject.setRt_id_area(value);
		} else if (attributeName.equals("compatiblerfc1583")) {
			ospfObject.setCompatiblerfc1583(value);
		} else if (attributeName.equals("defmetric")) {
			ospfObject.setDefmetric(value);
		} else if ("distance".equals(attributeName)) {
			ospfObject.setDistance(value);
		} else if ("refbandwidth".equals(attributeName)) {
			ospfObject.setRefbandwidth(value);
		} else if ("refresh_time".equals(attributeName)) {
			ospfObject.setRefresh_time(value);
		} else if ("spf_delay".equals(attributeName)) {
			ospfObject.setSpf_delay(value);
		} else if ("spf_holdtime".equals(attributeName)) {
			ospfObject.setSpf_holdtime(value);
		} else if ("spf_maxholdtime".equals(attributeName)) {
			ospfObject.setSpf_maxholdtime(value);
		} else if ("version".equals(attributeName)) {
			ospfObject.setVersion(value);
		} else if (isNull) {
			return null;
		}
		return ospfObject;
	}

	private Object setAlarmValue(String type, String value, String attributeName, Object object) {
		AlarmObject alarmObject = null;
		if (object == null) {
			isNull = true;
			alarmObject = new AlarmObject();
		} else {
			isNull = false;
			alarmObject = (AlarmObject) object;
		}
		if (attributeName.equals("aid") || attributeName.equals("2")) {
//System.out.println("~~~~~~aid= "+value);
//System.out.println("~~~~~~aid= "+CXMonitorCallbackThread.getPropertiesValue(CXMonitorCallbackThread.alarmid_en, value));			
			alarmObject.setCode(value);
			int val = new Integer(value).intValue();
			if(val>4096 && val < 8192){
				alarmObject.setPerid("1");
				alarmObject.setPerVal((val-4096)+"");
			}else if(val>8192 && val < 12288){
				alarmObject.setPerid("2");
				alarmObject.setPerVal((val-8192)+"");
			}else if(val>12288 && val < 16384){
				alarmObject.setPerid("3");
				alarmObject.setPerVal((val-12288)+"");
			}else if(val>16384 ){
				alarmObject.setPerid("4");
				alarmObject.setPerVal((val-16384)+"");
			}
			alarmObject.setAlmId(CXMonitorCallbackThread.getPropertiesValue(CXMonitorCallbackThread.alarmid_en, value));
		} else if (attributeName.equals("lvl") || attributeName.equals("3")) {
			alarmObject.setLevel(value);
		} else if (attributeName.equals("oid") || attributeName.equals("1")) {
//System.out.println("~~~~~~oid= "+AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));				
			alarmObject.setObjId(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
		} else if (attributeName.equals("stime") || attributeName.equals("4")) {
//System.out.println("~~~~~~stime= "+CoderUtils.longToDate(Long.valueOf(value)));			
			alarmObject.setTime((value));
		} else if (isNull) {
			return null;
		}
		return alarmObject;
	}

	private Object setPersvrValue(String type, String value, String attributeName, Object object) {
		PersvrObject persvrObject = null;
		if (object == null) {
			isNull = true;
			persvrObject = new PersvrObject();
		} else {
			isNull = false;
			persvrObject = (PersvrObject) object;
		}

		if (attributeName.equals("pid")) {
			persvrObject.setPerid(value);
		} else if (attributeName.equals("val")) {
			persvrObject.setValue(value);
		} else if (attributeName.equals("oid") || attributeName.equals("1")) {
			persvrObject.setObjId(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))));
		} else if (isNull) {
			return null;
		}
		return persvrObject;
	}

	private Object setSlotValue(String type, String value, String attributeName, Object object) {
		SlotObject slotObject = null;
		if (object == null) {
			isNull = true;
			slotObject = new SlotObject();
		} else {
			isNull = false;
			slotObject = (SlotObject) object;
		}

		if (attributeName.equals("type")) {
			slotObject.setType(CXMonitorCallbackThread.getPropertiesValue(CXMonitorCallbackThread.ecxtcard, value));
		}else if(attributeName.equals("online")){
			slotObject.setOnline(value);
		}
		else if (isNull) {
			return null;
		}
		return slotObject;
	}
	private Object setRouteValue(String type, String value, String attributeName, Object object) {
		Route route= null;
		if (object == null) {
			isNull = true;
			route = new Route();
		} else {
			isNull = false;
			route = (Route) object;
		}
		route.setName(oldoldAttributeName);
		if (attributeName.equals("nexthop")) {
			route.setNexthop(value);
		} else if (attributeName.equals("ifname")) {
			route.setIfname(value);
		}else if (attributeName.equals("type")) {
			route.setType(value);
		}else if (attributeName.equals("metric")) {
			route.setMetric(value);
		} else if (isNull) {
			return null;
		}
		return route;
	}

	private Object setNEValue(String type, String value, String attributeName, Object object) {
		PtnNeObject ptnNeObject = null;
		if (object == null) {
			isNull = true;
			ptnNeObject = new PtnNeObject();
		} else {
			isNull = false;
			ptnNeObject = (PtnNeObject) object;
		}

		if (attributeName.equals("type")) {
			ptnNeObject.setType(value);
		} else if (attributeName.equals("id")) {
//			ptnNeObject.setId(value_);
			ptnNeObject.setId(CoderUtils.longToIpAddress(Long.valueOf(value))); //兼容 13 1a 2种类型传输
		} else if (attributeName.equals("ver")) {
			ptnNeObject.setVer(value);
		} else if (attributeName.equals("oui")) {
			ptnNeObject.setOui(value);
		} else if (attributeName.equals("icccode")) {
			ptnNeObject.setIcccode(value);
		} else if (attributeName.equals("tpoamchntype")) {
			ptnNeObject.setTpoamchntype(value);
		} else if (attributeName.equals("desc")) {
			ptnNeObject.setDesc(value);
		} else if (attributeName.equals("timezone")) {
			ptnNeObject.setTimezone(value);
		} else if (attributeName.equals("topalmlvl")) {
			ptnNeObject.setTopalmlvl(value);
		} else if (isNull) {
			return null;
		}
		return ptnNeObject;
	}

	private Object setUserValue(String type, String value, String attributeName, Object object) {
		UserObject userObject = null;
		if (object == null) {
			isNull = true;
			userObject = new UserObject();
		} else {
			isNull = false;
			userObject = (UserObject) object;
		}

		if (attributeName.equals("name")) {
			userObject.setName(value);
		} else if (oldAttributeName.equals("group")) {
			userObject.setPower(attributeName);
		} else if (attributeName.equals("idle")) {
			userObject.setIdle(value);
		} else if (attributeName.equals("password")) {
			userObject.setPassWord(value);
		} else if (attributeName.equals("lock")) {
			userObject.setLock(value);
		} else if (isNull) {
			return null;
		}
		return userObject;
	}

	private Object setETHPortValue(String type, String value, String attributeName, Object object) {
		EthPortObject ethPortObject = null;
		if (object == null) {
			isNull = true;
			ethPortObject = new EthPortObject();
		} else {
			isNull = false;
			ethPortObject = (EthPortObject) object;
		}

		if (attributeName.equals("admin")) {
			ethPortObject.setAdmin(value);
		} else if (attributeName.equals("oper")) {
			ethPortObject.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if (attributeName.equals("ifname")) {
			ethPortObject.setIfname(value);
		} else if (attributeName.equals("role")) {
			ethPortObject.setRole(value);
		} else if (attributeName.equals("aspeed")) {
			ethPortObject.setAspeed(value);
		} else if (attributeName.equals("speed")) {
			ethPortObject.setSpeed(value);
		} else if (attributeName.equals("mac")) {
			if(ethPortObject.getMac().equals(""))ethPortObject.setMac(value);
		} else if (attributeName.equals("framelen")) {
			ethPortObject.setFramelen(value);
		} else if (attributeName.equals("fc")) {
			ethPortObject.setFc(value);
		} else if (attributeName.equals("wavelength")) {
			ethPortObject.setWavelength(value);
		} else if (attributeName.equals("sfptype")) {
			ethPortObject.setSfptype(value);
		} else if (attributeName.equals("xgwan")) {
			ethPortObject.setXgwan(value);
		} else if (attributeName.equals("permitpktloop")) {
			ethPortObject.setPermitpktloop(value);
		} else if (attributeName.equals("sfpexptype")) {
			ethPortObject.setSfpexptype(value);
		}else if (attributeName.equals("desc")) {
			ethPortObject.setDesc(value);
		}else if (attributeName.equals("ifindex")) {
			ethPortObject.setIfindex(value);
		}else if (attributeName.equals("perprofile")) {
			ethPortObject.setPerprofile(value);
		}else if (attributeName.equals("ref")) {
			ethPortObject.setRef(value);
		}else if (attributeName.equals("als")) {
			ethPortObject.setAls(value);
		}else if (attributeName.equals("alsdelay")) {
			ethPortObject.setAlsdelay(value);
		}else if (attributeName.equals("alsshtup")) {
			ethPortObject.setAlsshtup(value);
		}else if (attributeName.equals("arpproto_tocpu")) {
			ethPortObject.setArpproto_tocpu(value);
		}else if (attributeName.equals("dnugroup")) {
			ethPortObject.setDnugroup(value);
		}else if (attributeName.equals("isolation")) {
			ethPortObject.setIsolation(value);
		}else if (attributeName.equals("iused")) {
			ethPortObject.setIused(value);
		}else if (attributeName.equals("l2aclhwres")) {
			ethPortObject.setL2aclhwres(value);
		}else if (attributeName.equals("l3aclhwres")) {
			ethPortObject.setL3aclhwres(value);
		}else if (attributeName.equals("mirror")) {
			ethPortObject.setMirror(value);
		}else if (attributeName.equals("neType")) {
			ethPortObject.setNeType(value);
		}else if (attributeName.equals("oused")) {
			ethPortObject.setOused(value);
		}else if (attributeName.equals("perprofile")) {
			ethPortObject.setPerprofile(value);
		}else if (attributeName.equals("portType")) {
			ethPortObject.setPortType(value);
		}else if (attributeName.equals("ref")) {
			ethPortObject.setRef(value);
		}else if (attributeName.equals("ringid")) {
			ethPortObject.setRingid(value);
		}else if (attributeName.equals("schmode")) {
			ethPortObject.setSchmode(value);
		}else if (attributeName.equals("sfpvendor")) {
			ethPortObject.setSfpvendor(value);
		}else if (attributeName.equals("slowproto_tocpu")) {
			ethPortObject.setSlowproto_tocpu(value);
		}else if (attributeName.equals("ssmoutputenable")) {
			ethPortObject.setSsmoutputenable(value);
		}else if (attributeName.equals("stringernal")) {
			ethPortObject.setStringernal(value);
		}else if (attributeName.equals("switchport")) {
			ethPortObject.setSwitchport(value);
		}else if (attributeName.equals("type")) {
			ethPortObject.setType(value);
		}else if (attributeName.equals("vegc")) {
			ethPortObject.setVegc(value);
		}else if ("nni".equals(oldAttributeName)) {
			if ("llspexptoclr".equals(attributeName)) {
				ethPortObject.getNni().setLlspexptoclr(value);
			} else if ("eelspcostoexp".equals(attributeName)) {
				ethPortObject.getNni().setEelspcostoexp(value);
			} else if ("eelspexptocos".equals(attributeName)) {
				ethPortObject.getNni().setEelspexptocos(value);
			}else if ("egbwlimit".equals(attributeName)) {
				ethPortObject.getNni().setEgbwlimit(value);
			}else if ("elspcostoexp".equals(attributeName)) {
				ethPortObject.getNni().setElspcostoexp(value);
			}else if ("elspexptocos".equals(attributeName)) {
				ethPortObject.getNni().setElspexptocos(value);
			}else if ("inbwlimit".equals(attributeName)) {
				ethPortObject.getNni().setInbwlimit(value);
			}else if ("llspclrtoexp".equals(attributeName)) {
				ethPortObject.getNni().setLlspclrtoexp(value);
			}else if ("pvid".equals(attributeName)) {
				ethPortObject.getNni().setPvid(value);
			}else if (isNull) {
				return null;
			}
		}else if ("oam".equals(oldAttributeName)) {
			if ("cvintvl".equals(attributeName)) {
				ethPortObject.getOam().setCvintvl(value);
			} else if ("encsf".equals(attributeName)) {
				ethPortObject.getOam().setEncsf(value);
			}else if ("endm".equals(attributeName)) {
				ethPortObject.getOam().setEndm(value);
			}else if ("enlm".equals(attributeName)) {
				ethPortObject.getOam().setEnlm(value);
			}else if ("hwres".equals(attributeName)) {
				ethPortObject.getOam().setHwres(value);
			}else if ("iflck".equals(attributeName)) {
				ethPortObject.getOam().setIflck(value);
			}else if ("lpbtimeout".equals(attributeName)) {
				ethPortObject.getOam().setLpbtimeout(value);
			}else if ("lvl".equals(attributeName)) {
				ethPortObject.getOam().setLvl(value);
			}else if ("megid".equals(attributeName)) {
				ethPortObject.getOam().setMegid(value);
			}else if ("peer".equals(attributeName)) {
				ethPortObject.getOam().setPeer(value);
			}else if (isNull) {
				return null;
			}
		}else if ("tpoam".equals(oldAttributeName)) {
			if ("lvl".equals(attributeName))
				ethPortObject.getOam().setLvl(value);
			else if ("peer".equals(attributeName))
				ethPortObject.getOam().setPeer(value);
			else if ("megid".equals(attributeName))
				ethPortObject.getOam().setMegid(value);
			else if ("hwres".equals(attributeName))
				ethPortObject.getOam().setHwres(value);
			else if ("mepid".equals(attributeName))
				ethPortObject.getOam().setMepid(value);
			else if ("enlm".equals(attributeName))
				ethPortObject.getOam().setEnlm(value);
			else if ("endm".equals(attributeName))
				ethPortObject.getOam().setEndm(value);
			else if ("cvintvl".equals(attributeName))
				ethPortObject.getOam().setCvintvl(value);
			else if ("lpbtimeout".equals(attributeName))
				ethPortObject.getOam().setLpbtimeout(value);
			else if ("iflck".equals(attributeName))
				ethPortObject.getOam().setIflck(value);
			else if (isNull)
				return null;
		} else if ("lag".equals(oldAttributeName)) {
			if ("lagid".equals(attributeName)) {
				ethPortObject.getLag().setLagid(value);
			} else if ("name".equals(attributeName)) {
				ethPortObject.getLag().setName(value);
			} else if ("status".equals(attributeName)) {
				ethPortObject.getLag().setStatus(value);
			}else if ("wtrtime".equals(attributeName)) {
				ethPortObject.getLag().setWtrtime(value);
			} else if (isNull) {
				return null;
			}
		} else if ("uni".equals(oldAttributeName)) {
			if ("oclrmode".equals(attributeName)) {
				ethPortObject.getUni().setOclrmode(value);
			} else if ("iclrmode".equals(attributeName)) {
				ethPortObject.getUni().setIclrmode(value);
			} else if ("cos2vlanpri".equals(attributeName)) {
				ethPortObject.getUni().setCos2vlanpri(value);
			} else if ("acl".equals(attributeName)) {
				ethPortObject.getUni().setAcl(value);
			}else if ("bcastlimit".equals(attributeName)) {
				ethPortObject.getUni().setBcastlimit(value);
			}else if ("dlflimit".equals(attributeName)) {
				ethPortObject.getUni().setDlflimit(value);
			}else if ("egbwlimit".equals(attributeName)) {
				ethPortObject.getUni().setEgbwlimit(value);
			}else if ("encap".equals(attributeName)) {
				ethPortObject.getUni().setEncap(value);
			}else if ("lptstat".equals(attributeName)) {
				ethPortObject.getUni().setLptstat(value);
			}else if ("inbwlimit".equals(attributeName)) {
				ethPortObject.getUni().setInbwlimit(value);
			}else if ("lptstatby".equals(attributeName)) {
				ethPortObject.getUni().setLptstatby(value);
			}else if ("mcastlimit".equals(attributeName)) {
				ethPortObject.getUni().setMcastlimit(value);
			}else if ("pvid".equals(attributeName)) {
				ethPortObject.getUni().setPvid(value);
			}else if ("pvpri".equals(attributeName)) {
				ethPortObject.getUni().setPvpri(value);
			}else if ("sdtpid".equals(attributeName)) {
				ethPortObject.getUni().setSdtpid(value);
			}else if ("tpid".equals(attributeName)) {
				ethPortObject.getUni().setTpid(value);
			}else if ("vlanmode".equals(attributeName)) {
				ethPortObject.getUni().setVlanmode(value);
			}else if ("vlanpri2cng".equals(attributeName)) {
				ethPortObject.getUni().setVlanpri2cng(value);
			}else if (isNull) {
				return null;
			}
		}else if ("cs3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs3().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs3().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs3().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("cs7".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs7().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs7().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs7().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs7().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs7().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs7().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("cs6".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getCs6().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getCs6().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getCs6().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getCs6().setGreenwredend(value);
			} else if ("used".equals(attributeName)) {
				ethPortObject.getCs6().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getCs6().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("ef".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getEf().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getEf().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getEf().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getEf().setGreenwredend(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getEf().setUsed(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getEf().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af4".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf4().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf4().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf4().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf4().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf4().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf4().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf4().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf4().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf4().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf4().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf4().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af3".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf3().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf3().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf3().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf3().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf3().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf3().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf3().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf3().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf3().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf3().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf3().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af2".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf2().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf2().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf2().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf2().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf2().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf2().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf2().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf2().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf2().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf2().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf2().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("af1".equals(oldAttributeName)) {
			if ("cir".equals(attributeName)) {
				ethPortObject.getAf1().setCir(value);
			} else if ("greendroprate".equals(attributeName)) {
				ethPortObject.getAf1().setGreendroprate(value);
			} else if ("greenwredstart".equals(attributeName)) {
				ethPortObject.getAf1().setGreenwredstart(value);
			} else if ("greenwredend".equals(attributeName)) {
				ethPortObject.getAf1().setGreenwredend(value);
			} else if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getAf1().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getAf1().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getAf1().setYellowwredend(value);
			}else if ("maxbw".equals(attributeName)) {
				ethPortObject.getAf1().setMaxbw(value);
			}else if ("used".equals(attributeName)) {
				ethPortObject.getAf1().setUsed(value);
			}else if ("weight".equals(attributeName)) {
				ethPortObject.getAf1().setWeight(value);
			}else if ("wreden".equals(attributeName)) {
				ethPortObject.getAf1().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("be".equals(oldAttributeName)) {
			if ("yellowdroprate".equals(attributeName)) {
				ethPortObject.getBe().setYellowdroprate(value);
			} else if ("yellowwredstart".equals(attributeName)) {
				ethPortObject.getBe().setYellowwredstart(value);
			} else if ("yellowwredend".equals(attributeName)) {
				ethPortObject.getBe().setYellowwredend(value);
			} else if ("weight".equals(attributeName)) {
				ethPortObject.getBe().setWeight(value);
			} else if ("wreden".equals(attributeName)) {
				ethPortObject.getBe().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if ("nbr".equals(oldAttributeName)) {
			if ("stat".equals(attributeName)) {
				ethPortObject.getNbr().setStat(value);
			} else if ("mac".equals(attributeName)) {
				ethPortObject.getNbr().setMac(value);
			} else if ("ifidx".equals(attributeName)) {
				ethPortObject.getNbr().setIfidx(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).substring(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).indexOf("/") + 1));
			} else if ("neid".equals(attributeName)) {
				ethPortObject.getNbr().setNeid(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if (isNull) {
				return null;
			}
		} else if ("tpoam".equals(oldAttributeName)) {
			if ("stat".equals(attributeName)) {
				ethPortObject.getNbr().setStat(value);
			} else if ("mac".equals(attributeName)) {
				ethPortObject.getNbr().setMac(value);
			} else if ("ifidx".equals(attributeName)) {
				ethPortObject.getNbr().setIfidx(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).substring(AnalysisObjectID.analysisObjectID(CoderUtils.intToBytes(Integer.valueOf(value))).indexOf("/") + 1));
			} else if ("neid".equals(attributeName)) {
				ethPortObject.getNbr().setNeid(CoderUtils.longToIpAddress(Long.valueOf(value)));
			} else if (isNull) {
				return null;
			}
		} else if (isNull) {
			return null;
		}
		return ethPortObject;
	}

	private Object setETHPortUniValue(String type, String value, String attributeName, Object object) {
		EthPortObject ethPortObject = null;
		if (object == null) {
			isNull = true;
			ethPortObject = new EthPortObject();
		} else {
			isNull = false;
			ethPortObject = (EthPortObject) object;
		}

		if (attributeName.equals("encap")) {
			ethPortObject.getUni().setEncap(value);
		} else if (attributeName.equals("vlanmode")) {
			ethPortObject.getUni().setVlanmode(value);
		} else if (attributeName.equals("tpid")) {
			ethPortObject.getUni().setTpid(value);
		} else if (attributeName.equals("pvid")) {
			ethPortObject.getUni().setPvid(value);
		} else if (attributeName.equals("pvpri")) {
			ethPortObject.getUni().setPvpri(value);
		} else if (attributeName.equals("sdtpid")) {
			ethPortObject.getUni().setSdtpid(value);
		} else if (attributeName.equals("acl")) {
			ethPortObject.getUni().setAcl(value);
		}else if (attributeName.equals("oclrmode")) {
			ethPortObject.getUni().setOclrmode(value);
		}else if (attributeName.equals("iclrmode")) {
			ethPortObject.getUni().setIclrmode(value);
		}else if (attributeName.equals("vlanpri2cng")) {
			ethPortObject.getUni().setVlanpri2cng(value);
		}else if (attributeName.equals("cos2vlanpri")) {
			ethPortObject.getUni().setCos2vlanpri(value);
		}else if (attributeName.equals("inbwlimit")) {
			ethPortObject.getUni().setInbwlimit(value);
		}else if (attributeName.equals("egbwlimit")) {
			ethPortObject.getUni().setEgbwlimit(value);
		}else if (attributeName.equals("bcastlimit")) {
			ethPortObject.getUni().setBcastlimit(value);
		}else if (attributeName.equals("mcastlimit")) {
			ethPortObject.getUni().setMcastlimit(value);
		}else if (attributeName.equals("dlflimit")) {
			ethPortObject.getUni().setDlflimit(value);
		}else if (attributeName.equals("lptstat")) {
			ethPortObject.getUni().setLptstat(value);
		}else if (attributeName.equals("lptstatby")) {
			ethPortObject.getUni().setLptstatby(value);
		}else if (isNull) {
			return null;
		}
		return ethPortObject;
	}

	private Object setETHPortNniValue(String type, String value, String attributeName, Object object) {
		EthPortObject ethPortObject = null;
		if (object == null) {
			isNull = true;
			ethPortObject = new EthPortObject();
		} else {
			isNull = false;
			ethPortObject = (EthPortObject) object;
		}

		if (attributeName.equals("smac")) {
			ethPortObject.getNbr().setSmac(value);
		} else if (attributeName.equals("stat")) {
			ethPortObject.getNbr().setStat(value);
		} else if (attributeName.equals("mac")) {
			ethPortObject.getNbr().setMac(value);
		} else if (attributeName.equals("ifidx")) {
			ethPortObject.getNbr().setIfidx(value);
		} else if (attributeName.equals("neid")) {
			ethPortObject.getNbr().setNeid(value);
		} else if (attributeName.equals("ccn")) {
			ethPortObject.getNbr().setCcn(value);
		}else if (attributeName.equals("llspexptoclr")) {
			ethPortObject.getNni().setLlspexptoclr(value);
		}else if (attributeName.equals("llspclrtoexp")) {
			ethPortObject.getNni().setLlspclrtoexp(value);
		}else if (attributeName.equals("eelspexptocos")) {
			ethPortObject.getNni().setEelspexptocos(value);
		}else if (attributeName.equals("eelspcostoexp")) {
			ethPortObject.getNni().setEelspcostoexp(value);
		}else if (attributeName.equals("elspexptocos")) {
			ethPortObject.getNni().setElspexptocos(value);
		}else if (attributeName.equals("elspcostoexp")) {
			ethPortObject.getNni().setElspcostoexp(value);
		}else if (attributeName.equals("inbwlimit")) {
			ethPortObject.getNni().setInbwlimit(value);
		}else if (attributeName.equals("egbwlimit")) {
			ethPortObject.getNni().setEgbwlimit(value);
		}else if (attributeName.equals("pvid")) {
			ethPortObject.getNni().setPvid(value);
		}else if (attributeName.equals("ccnEnableString")) {
			ethPortObject.getNni().setCcnEnableString(value);
		}else if (isNull) {
			return null;
		}
		return ethPortObject;
	}

	private Object setETHPortQosValue(String type, String value, String attributeName, Object object) {
		EthPortObject ethPortObject = null;
		if (object == null) {
			isNull = true;
			ethPortObject = new EthPortObject();
		} else {
			isNull = false;
			ethPortObject = (EthPortObject) object;
		}

		if (oldAttributeName.equals("af1")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getAf1().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getAf1().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getAf1().setGreenwredend(value);
			} else if (attributeName.equals("yellowwredstart")) {
				ethPortObject.getAf1().setYellowwredstart(value);
			} else if (attributeName.equals("yellowwredend")) {
				ethPortObject.getAf1().setYellowwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getAf1().setUsed(value);
			} else if (attributeName.equals("weight")) {
				ethPortObject.getAf1().setWeight(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getAf1().setGreendroprate(value);
			} else if (attributeName.equals("yellowdroprate")) {
				ethPortObject.getAf1().setYellowdroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getAf1().setWreden(value);
			} else if (attributeName.equals("maxbw")) {
				ethPortObject.getAf1().setMaxbw(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("af2")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getAf2().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getAf2().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getAf2().setGreenwredend(value);
			} else if (attributeName.equals("yellowwredstart")) {
				ethPortObject.getAf2().setYellowwredstart(value);
			} else if (attributeName.equals("yellowwredend")) {
				ethPortObject.getAf2().setYellowwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getAf2().setUsed(value);
			} else if (attributeName.equals("weight")) {
				ethPortObject.getAf2().setWeight(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getAf2().setGreendroprate(value);
			} else if (attributeName.equals("yellowdroprate")) {
				ethPortObject.getAf2().setYellowdroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getAf2().setWreden(value);
			} else if (attributeName.equals("maxbw")) {
				ethPortObject.getAf2().setMaxbw(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("af3")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getAf3().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getAf3().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getAf3().setGreenwredend(value);
			} else if (attributeName.equals("yellowwredstart")) {
				ethPortObject.getAf3().setYellowwredstart(value);
			} else if (attributeName.equals("yellowwredend")) {
				ethPortObject.getAf3().setYellowwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getAf3().setUsed(value);
			} else if (attributeName.equals("weight")) {
				ethPortObject.getAf3().setWeight(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getAf3().setGreendroprate(value);
			} else if (attributeName.equals("yellowdroprate")) {
				ethPortObject.getAf3().setYellowdroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getAf3().setWreden(value);
			} else if (attributeName.equals("maxbw")) {
				ethPortObject.getAf3().setMaxbw(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("af4")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getAf4().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getAf4().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getAf4().setGreenwredend(value);
			} else if (attributeName.equals("yellowwredstart")) {
				ethPortObject.getAf4().setYellowwredstart(value);
			} else if (attributeName.equals("yellowwredend")) {
				ethPortObject.getAf4().setYellowwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getAf4().setUsed(value);
			} else if (attributeName.equals("weight")) {
				ethPortObject.getAf4().setWeight(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getAf4().setGreendroprate(value);
			} else if (attributeName.equals("yellowdroprate")) {
				ethPortObject.getAf4().setYellowdroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getAf4().setWreden(value);
			} else if (attributeName.equals("maxbw")) {
				ethPortObject.getAf4().setMaxbw(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("cs6")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getCs6().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getCs6().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getCs6().setGreenwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getCs6().setUsed(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getCs6().setGreendroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getCs6().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("cs7")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getCs7().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getCs7().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getCs7().setGreenwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getCs7().setUsed(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getCs7().setGreendroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getCs7().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("ef")) {
			if (attributeName.equals("cir")) {
				ethPortObject.getEf().setCir(value);
			} else if (attributeName.equals("greenwredstart")) {
				ethPortObject.getEf().setGreenwredstart(value);
			} else if (attributeName.equals("greenwredend")) {
				ethPortObject.getEf().setGreenwredend(value);
			} else if (attributeName.equals("used")) {
				ethPortObject.getEf().setUsed(value);
			} else if (attributeName.equals("greendroprate")) {
				ethPortObject.getEf().setGreendroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getEf().setWreden(value);
			} else if (isNull) {
				return null;
			}
		} else if (oldAttributeName.equals("be")) {
			if (attributeName.equals("yellowwredstart")) {
				ethPortObject.getBe().setYellowwredstart(value);
			} else if (attributeName.equals("yellowwredend")) {
				ethPortObject.getBe().setYellowwredend(value);
			} else if (attributeName.equals("weight")) {
				ethPortObject.getBe().setWeight(value);
			} else if (attributeName.equals("yellowdroprate")) {
				ethPortObject.getBe().setYellowdroprate(value);
			} else if (attributeName.equals("wreden")) {
				ethPortObject.getBe().setWreden(value);
			} else if (isNull) {
				return null;
			}
		}
		return ethPortObject;
	}
	private Object setRingValue(String type, String value, String attributeName, Object object) {
		RingObject ring= null;
		if (object == null) {
			isNull = true;
			ring = new RingObject();
		} else {
			isNull = false;
			ring = (RingObject) object;
		}
		ring.setName(oldoldAttributeName);
		if (attributeName.equals("enaps")) {
			ring.setEnaps(value);
		} else if (attributeName.equals("wtrtime")) {
			ring.setWtrtime(value);
		}else if (attributeName.equals("holdofftime")) {
			ring.setHoldofftime(value);
		}else if (attributeName.equals("nodeid")) {
			ring.setNodeid(value);
		}else if (attributeName.equals("westnbrid")) {
			ring.setWestnbrid(value);
		}else if (attributeName.equals("eastnbrid")) {
			ring.setEastnbrid(value);
		}else if (attributeName.equals("westport")) {
			ring.setWestport(value);
		}else if (attributeName.equals("eastport")) {
			ring.setEastport(value);
		}else if (attributeName.equals("apscmd")) {
			ring.setApscmd(value);
		}else if (attributeName.equals("stat")) {
			ring.setStat(value);
		}else if (attributeName.equals("westlastrxpdu")) {
			ring.setWestlastrxpdu(value);
		}else if (attributeName.equals("eastlastrxpdu")) {
			ring.setEastlastrxpdu(value);
		}else if (attributeName.equals("westlasttxpdu")) {
			ring.setWestlasttxpdu(value);
		}else if (attributeName.equals("eastlasttxpdu")) {
			ring.setEastlasttxpdu(value);
		} else if (isNull) {
			return null;
		}
		return ring;
	}
	private Object setClockValue(String type, String value, String attributeName, Object object) {
		ClockObject clock= null;
		if (object == null) {
			isNull = true;
			clock = new ClockObject();
		} else {
			isNull = false;
			clock = (ClockObject) object;
		}
//		clock.setName(oldoldAttributeName);
		if (attributeName.equals("ssmmode")) {
			clock.setSsmmode(value);
		}else if (attributeName.equals("scswtr")) {
			clock.setScswtr(value);
		}else if (attributeName.equals("ecswtr")) {
			clock.setEcswtr(value);
		} else if (attributeName.equals("freerunlevel")) {
			clock.setFreerunlevel(value);
		} else if (attributeName.equals("unknownlevel")) {
			clock.setUnknownlevel(value);
		} else if (attributeName.equals("operationmode")) {
			clock.setOperationmode(value);
		} else if (attributeName.equals("extclkdrvmode")) {
			clock.setExtclkdrvmode(value);
		} else if (attributeName.equals("squelchmin")) {
			clock.setSquelchmin(value);
		} else if (attributeName.equals("SCSWorkState")) {
			clock.setSCSWorkState(value);
		} else if (attributeName.equals("ECSWorkState")) {
			clock.setECSWorkState(value);
		} else if (attributeName.equals("SCSSelectSource")) {
			clock.setSCSSelectSource(value);
		} else if (attributeName.equals("ECSSelectSource")) {
			clock.setECSSelectSource(value);
		}  else if (oldAttributeName.equals("1588v2")) {
			if (attributeName.equals("scspri")) {
				clock.getIeee1588().setScspri(value);
			} else if (attributeName.equals("scsmanual")) {
				clock.getIeee1588().setScsmanual(value);
			}else if (attributeName.equals("scsforce")) {
				clock.getIeee1588().setScsforce(value);
			}else if (attributeName.equals("scslockout")) {
				clock.getIeee1588().setScslockout(value);
			}else if (attributeName.equals("forcelevel")) {
				clock.getIeee1588().setForcelevel(value);
			}else if (attributeName.equals("PhysicalState")) {
				clock.getIeee1588().setPhysicalState(value);
			}else if (attributeName.equals("SCSLogicalState")) {
				clock.getIeee1588().setSCSLogicalState(value);
			}else if (attributeName.equals("SCSQL")) {
				clock.getIeee1588().setSCSQL(value);
			}else if (isNull) {
				return null;
			}
		}else if (isNull) {
			return null;
		}
		return clock;
	}
	
	private Object setTodValue(String type, String value, String attributeName, Object object) {
		TodObject tod= null;
		if (object == null) {
			isNull = true;
			tod = new TodObject();
		} else {
			isNull = false;
			tod = (TodObject) object;
		}
		tod.setName(oldoldAttributeName);
		if (attributeName.equals("ifname")) {
			tod.setIfname(value);
		}else if (attributeName.equals("index")) {
			tod.setIndex(value);
		}else if (attributeName.equals("desc")) {
			tod.setIndex(value);
		} else if (attributeName.equals("admin")) {
			tod.setAdmin(value);
		} else if (attributeName.equals("oper")) {
			tod.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		} else if (attributeName.equals("ref")) {
			tod.setRef(value);
		} else if (attributeName.equals("priority1")) {
			tod.setPriority1(value);
		} else if (attributeName.equals("clockclass")) {
			tod.setClockclass(value);
		} else if (attributeName.equals("clockaccuracy")) {
			tod.setClockaccuracy(value);
		} else if (attributeName.equals("variance")) {
			tod.setVariance(value);
		} else if (attributeName.equals("priority2")) {
			tod.setPriority2(value);
		} else if (attributeName.equals("mode")) {
			tod.setMode(value);
		} else if (attributeName.equals("timeoffset")) {
			tod.setTimeoffset(value);
		} else if (attributeName.equals("isdebug")) {
			tod.setIsdebug(value);
		} else if (attributeName.equals("scspri")) {
			tod.setScspri(value);
		} else if (attributeName.equals("scsmanual")) {
			tod.setScsmanual(value);
		} else if (attributeName.equals("scsforce")) {
			tod.setScsforce(value);
		} else if (attributeName.equals("scslockout")) {
			tod.setScslockout(value);
		} else if (attributeName.equals("forcelevel")) {
			tod.setForcelevel(value);
		} else if (attributeName.equals("PhysicalState")) {
			tod.setPhysicalState(value);
		} else if (attributeName.equals("SCSLogicalState")) {
			tod.setSCSLogicalState(value);
		} else if (attributeName.equals("SCSQL")) {
			tod.setSCSQL(value);
		} 
		else if (isNull) {
			return null;
		}
		return tod;
	}	
	private Object setPtpValue(String type, String value, String attributeName, Object object) {
		PtpObject ptp= null;
		if (object == null) {
			isNull = true;
			ptp = new PtpObject();
		} else {
			isNull = false;
			ptp = (PtpObject) object;
		}
//		tod.setName(oldoldAttributeName);
		if (attributeName.equals("clkid")) {
			ptp.setClkid(value);
		}else if (attributeName.equals("priority1")) {
			ptp.setPriority1(value);
		}else if (attributeName.equals("clockclass")) {
			ptp.setClockclass(value);
		}else if (attributeName.equals("clockaccuracy")) {
			ptp.setClockaccuracy(value);
		}else if (attributeName.equals("variance")) {
			ptp.setVariance(value);
		}else if (attributeName.equals("priority2")) {
			ptp.setPriority2(value);
		}else if (attributeName.equals("bmcenable")) {
			ptp.setBmcenable(value);
		}else if (attributeName.equals("domain")) {
			ptp.setDomain(value);
		}else if (attributeName.equals("tcdomain1")) {
			ptp.setTcdomain1(value);
		}else if (attributeName.equals("tcdomain2")) {
			ptp.setTcdomain2(value);
		}else if (attributeName.equals("tcdomain2enable")) {
			ptp.setTcdomain2enable(value);
		}else if (attributeName.equals("tcdomain3")) {
			ptp.setTcdomain3(value);
		}else if (attributeName.equals("tcdomain3enable")) {
			ptp.setTcdomain3enable(value);
		}else if (attributeName.equals("tcdomain4")) {
			ptp.setTcdomain4(value);
		}else if (attributeName.equals("tcdomain4enable")) {
			ptp.setTcdomain4enable(value);
		}else if (attributeName.equals("tcdelaymechanism")) {
			ptp.setTcdelaymechanism(value);
		}else if (attributeName.equals("slaveonly")) {
			ptp.setSlaveonly(value);
		}else if (attributeName.equals("meanpathdelay")) {
			ptp.setMeanpathdelay(value);
		}else if (attributeName.equals("offsetfrommaster")) {
			ptp.setOffsetfrommaster(value);
		}else if (attributeName.equals("timesoffset")) {
			ptp.setTimesoffset(value);
		}else if (attributeName.equals("currenttodsel")) {
			ptp.setCurrenttodsel(value);
		}else if (attributeName.equals("prntid")) {
			ptp.setPrntid(value);
		}else if (attributeName.equals("gmclkid")) {
			ptp.setGmclkid(value);
		}else if (attributeName.equals("gmprio1")) {
			ptp.setGmprio1(value);
		}else if (attributeName.equals("gmclockclass")) {
			ptp.setGmclockclass(value);
		}else if (attributeName.equals("gmclockaccuracy")) {
			ptp.setGmclockaccuracy(value);
		}else if (attributeName.equals("gmvariance")) {
			ptp.setGmvariance(value);
		}else if (attributeName.equals("gmprio2")) {
			ptp.setGmprio2(value);
		}else if (attributeName.equals("stepsremoved")) {
			ptp.setStepsremoved(value);
		}else if (attributeName.equals("clocktype")) {
			ptp.setClocktype(value);
		}else if (attributeName.equals("todtxtype")) {
			ptp.setTodtxtype(value);
		}else if (attributeName.equals("filterenable")) {
			ptp.setFilterenable(value);
		}
		else if (isNull) {
			return null;
		}
		return ptp;
	}	
	private Object setExtclkValue(String type, String value, String attributeName, Object object) {
		ExtclkObject extclk= null;
		if (object == null) {
			isNull = true;
			extclk = new ExtclkObject();
		} else {
			isNull = false;
			extclk = (ExtclkObject) object;
		}
		extclk.setName(oldoldAttributeName);
		if (attributeName.equals("ifname")) {
			extclk.setIfname(value);
		}else if (attributeName.equals("scspri")) {
			extclk.setScspri(value);
		}else if (attributeName.equals("ifindex")) {
			extclk.setIfindex(value);
		}else if (attributeName.equals("mode")) {
			extclk.setMode(value);
		}else if (attributeName.equals("desc")) {
			extclk.setDesc(value);
		}else if (attributeName.equals("admin")) {
			extclk.setAdmin(value);
		}else if (attributeName.equals("oper")) {
			extclk.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		}else if (attributeName.equals("ref")) {
			extclk.setRef(value);
		}else if (attributeName.equals("scsmanual")) {
			extclk.setScsmanual(value);
		}else if (attributeName.equals("scsforce")) {
			extclk.setScsforce(value);
		}else if (attributeName.equals("scslockout")) {
			extclk.setScslockout(value);
		}else if (attributeName.equals("forcelevel")) {
			extclk.setForcelevel(value);
		}else if (attributeName.equals("ssmslot")) {
			extclk.setSsmslot(value);
		}else if (attributeName.equals("ssmoutputenable")) {
			extclk.setSsmoutputenable(value);
		}else if (attributeName.equals("impedance")) {
			extclk.setImpedance(value);
		}else if (attributeName.equals("extclkmode")) {
			extclk.setExtclkmode(value);
		}else if (attributeName.equals("PhysicalState")) {
			extclk.setPhysicalState(value);
		}else if (attributeName.equals("SCSLogicalState")) {
			extclk.setSCSLogicalState(value);
		}else if (attributeName.equals("loopback")) {
			extclk.setLoopback(value);
		}else if (attributeName.equals("SSMCode")) {
			extclk.setSSMCode(value);
		}else if (attributeName.equals("SCSQL")) {
			extclk.setSCSQL(value);
		}
		else if (isNull) {
			return null;
		}
		return extclk;
	}	
	private Object setClockPortValue(String type, String value, String attributeName, Object object) {
		ClockPortESObject clockport= null;
		if (object == null) {
			isNull = true;
			clockport = new ClockPortESObject();
		} else {
			isNull = false;
			clockport = (ClockPortESObject) object;
		}
		clockport.setPortname(oldoldAttributeName);
		if (attributeName.equals("admin")) {
			clockport.setAdmin(value);
		}else if (attributeName.equals("oper")) {
			clockport.setOper(CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value))));
		}else if (attributeName.equals("dnugroup")) {
			clockport.setDnugroup(value);
		}else if (attributeName.equals("ssmoutputenable")) {
			clockport.setSsmoutputenable(value);
		}else if (attributeName.equals("internal")) {
			clockport.setInternal(value);
		}else if (attributeName.equals("speed")) {
			clockport.setSpeed(value);
		}else if ("clock".equals(oldAttributeName)) {
			if(!clockport.getIsClock())clockport.setIsClock(true);
			if (attributeName.equals("scspri")) {
				clockport.setScspri(value);
			}else if (attributeName.equals("ecspri")) {
				clockport.setEcspri(value);
			}else if (attributeName.equals("scslockout")) {
				clockport.setScslockout(value);
			}else if (attributeName.equals("ecslockout")) {
				clockport.setEcslockout(value);
			}else if (attributeName.equals("forcelevel")) {
				clockport.setForcelevel(value);
			}else if (attributeName.equals("PhysicalState")) {
				clockport.setPhysicalState(value);
			}else if (attributeName.equals("SCSLogicalState")) {
				clockport.setSCSLogicalState(value);
			}else if (attributeName.equals("CSLogicalState")) {
				clockport.setCSLogicalState(value);
			}else if (attributeName.equals("SSMCode")) {
				clockport.setSSMCode(value);
			}else if (attributeName.equals("SCSQL")) {
				clockport.setSCSQL(value);
			}else if (attributeName.equals("ECSQL")) {
				clockport.setECSQL(value);
			}else if (attributeName.equals("recoverymode")) {
				clockport.setRecoverModel(value);
			}else if (isNull) {
				return null;
			}
		}
		else if (isNull) {
			return null;
		}
		return clockport;
	}	
	private Object setPtpPortValue(String type, String value, String attributeName, Object object) {
		PtpPortObject ptpport= null;
		if (object == null) {
			isNull = true;
			ptpport = new PtpPortObject();
		} else {
			isNull = false;
			ptpport = (PtpPortObject) object;
		}
		ptpport.setPortname(oldoldAttributeName);
		if (attributeName.equals("admin")) {
			ptpport.setPortadmin(value);
		}else if ("ptp".equals(oldAttributeName)) {
			if(!ptpport.getIsPtp())ptpport.setIsptp(true);
			if (attributeName.equals("porttype")) {
				ptpport.setPorttype(value);
			}else if (attributeName.equals("delaymechanism")) {
				ptpport.setDelaymechanism(value);
			}else if (attributeName.equals("anncintv")) {
				ptpport.setAnncintv(value);
			}else if (attributeName.equals("annctmo")) {
				ptpport.setAnnctmo(value);
			}else if (attributeName.equals("syncintv")) {
				ptpport.setSyncintv(value);
			}else if (attributeName.equals("delreqintv")) {
				ptpport.setDelreqintv(value);
			}else if (attributeName.equals("pdelreqintv")) {
				ptpport.setPdelreqintv(value);
			}else if (attributeName.equals("delayoffset")) {
				ptpport.setDelayoffset(value);
			}else if (attributeName.equals("testdelayoffset")) {
				ptpport.setTestdelayoffset(value);
			}else if (attributeName.equals("enable")) {
				ptpport.setEnable(value);
			}else if (attributeName.equals("vlanid")) {
				ptpport.setVlanid(value);
			}else if (attributeName.equals("operationmode")) {
				ptpport.setOperationmode(value);
			}else if (attributeName.equals("portstate")) {
				ptpport.setPortstate(value);
			}else if (attributeName.equals("ref")) {
				ptpport.setRef(value);
			}else if (attributeName.equals("two_step")) {
				ptpport.setTwo_step(value);
			}else if (isNull) {
				return null;
			}
		}
		else if (isNull) {
			return null;
		}
		return ptpport;
	}	
	private Object setcmapl2Value(String type, String value, String attributeName, Object object) {
		L2Object l2= null;
		if (object == null) {
			isNull = true;
			l2 = new L2Object();
		} else {
			isNull = false;
			l2 = (L2Object) object;
		}
		l2.setName(oldoldAttributeName);
		if (attributeName.equals("spvlan")) {
			l2.setSpvlan(value);
		}else if (attributeName.equals("spvlanmask")) {
			l2.setSpvlanmask(value);
		}else if (attributeName.equals("cevlan")) {
			l2.setCevlan(value);
		}else if (attributeName.equals("cevlanmask")) {
			l2.setCevlanmask(value);
		}else if (attributeName.equals("spvlanpri")) {
			l2.setSpvlanpri(value);
		}else if (attributeName.equals("spvlanprimask")) {
			l2.setSpvlanprimask(value);
		}else if (attributeName.equals("cevlanpri")) {
			l2.setCevlanpri(value);
		}else if (attributeName.equals("cevlanprimask")) {
			l2.setCevlanprimask(value);
		}else if (attributeName.equals("type")) {
			l2.setType(value);
		}else if (attributeName.equals("ref")) {
			l2.setRef(value);
		}else if (attributeName.equals("ethtype")) {
			l2.setEthtype(value);
		}else if (attributeName.equals("ethtypemask")) {
			l2.setEthtypemask(value);
		}else if (attributeName.equals("dstmac")) {
			l2.setDstmac(value);
		}else if (attributeName.equals("dstmacmask")) {
			l2.setDstmacmask(value);
		}else if (attributeName.equals("srcmac")) {
			l2.setSrcmac(value);
		}else if (attributeName.equals("srcmacmask")) {
			l2.setSrcmacmask(value);
		}
		else if (isNull) {
			return null;
		}
		return l2;
	}		
	private Object setcmapl3Value(String type, String value, String attributeName, Object object) {
		L3Object l3= null;
		if (object == null) {
			isNull = true;
			l3 = new L3Object();
		} else {
			isNull = false;
			l3 = (L3Object) object;
		}
		l3.setName(oldoldAttributeName);
		if (attributeName.equals("spvlan")) {
			l3.setSpvlan(value);
		}else if (attributeName.equals("spvlanmask")) {
			l3.setSpvlanmask(value);
		}else if (attributeName.equals("cevlan")) {
			l3.setCevlan(value);
		}else if (attributeName.equals("cevlanmask")) {
			l3.setCevlanmask(value);
		}else if (attributeName.equals("spvlanpri")) {
			l3.setSpvlanpri(value);
		}else if (attributeName.equals("spvlanprimask")) {
			l3.setSpvlanprimask(value);
		}else if (attributeName.equals("cevlanpri")) {
			l3.setCevlanpri(value);
		}else if (attributeName.equals("cevlanprimask")) {
			l3.setCevlanprimask(value);
		}else if (attributeName.equals("type")) {
			l3.setType(value);
		}else if (attributeName.equals("dstip")) {
			l3.setDstip(value);
		}else if (attributeName.equals("dstipmask")) {
			l3.setDstipmask(value);
		}else if (attributeName.equals("srcip")) {
			l3.setSrcip(value);
		}else if (attributeName.equals("srcipmask")) {
			l3.setSrcipmask(value);
		}else if (attributeName.equals("pid")) {
			l3.setPid(value);
		}else if (attributeName.equals("pidmask")) {
			l3.setPidmask(value);
		}else if (attributeName.equals("dscp")) {
			l3.setDscp(value);
		}else if (attributeName.equals("ref")) {
			l3.setRef(value);
		}else if (attributeName.equals("dscpmask")) {
			l3.setDscpmask(value);
		}else if (attributeName.equals("dstport")) {
			l3.setDstport(value);
		}else if (attributeName.equals("dstportmask")) {
			l3.setDstportmask(value);
		}else if (attributeName.equals("srcport")) {
			l3.setSrcport(value);
		}else if (attributeName.equals("srcportmask")) {
			l3.setSrcportmask(value);
		}
		else if (isNull) {
			return null;
		}
		return l3;
	}	
	private Object setMspValue(String type, String value, String attributeName, Object object) {
		MspObject ms= null;
		if (object == null) {
			isNull = true;
			ms = new MspObject();
		} else {
			isNull = false;
			ms = (MspObject) object;
		}
		ms.setName(oldoldAttributeName);
		if (attributeName.equals("desc")) {
			ms.setDesc(value);
		}else if (attributeName.equals("enaps")) {
			ms.setEnaps(value);
		}else if (attributeName.equals("type")) {
			ms.setType(value);
		}else if (attributeName.equals("wtrtime")) {
			ms.setWtrtime(value);
		}else if (attributeName.equals("holdofftime")) {
			ms.setHoldofftime(value);
		}else if (attributeName.equals("sdthreshold")) {
			ms.setSdthreshold(value);
		}else if (attributeName.equals("apscmd")) {
			ms.setApscmd(value);
		}else if (attributeName.equals("sfhighpri")) {
			ms.setSfhighpri(value);
		}else if (attributeName.equals("sdhighpri")) {
			ms.setSdhighpri(value);
		}else if (attributeName.equals("prtport")) {
			ms.setPrtport(value);
		}else if (attributeName.equals("workport")) {
			ms.setWorkport(value);
		}else if (attributeName.equals("apsstat")) {
			ms.setApsstat(value);
		}else if (attributeName.equals("sel")) {
			ms.setSel(value);
		}else if (attributeName.equals("clearcnt")) {
			ms.setClearcnt(value);
		}else if (attributeName.equals("lastxmitkbyte")) {
			ms.setLastxmitkbyte(value);
		}else if (attributeName.equals("lastrecvkbyte")) {
			ms.setLastrecvkbyte(value);
		}else if (attributeName.equals("dualid")) {
			ms.setDualid(value);
		}
		else if (isNull) {
			return null;
		}
		return ms;
	}	
	private Object setClrtoexpValue(String type, String value, String attributeName, Object object) {
		ClrtoexpObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new ClrtoexpObject();
		} else {
			isNull = false;
			obj = (ClrtoexpObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("yellow")) {
			obj.setYellow(value);
		}else if (attributeName.equals("green")) {
			obj.setGreen(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	private Object setOamEfmValue(String type, String value, String attributeName, Object object) {
		EfmObject efm= null;
		if (object == null) {
			isNull = true;
			efm = new EfmObject();
		} else {
			isNull = false;
			efm = (EfmObject) object;
		}
		efm.setPortname(oldoldAttributeName);
		if ("efm".equals(oldAttributeName)) {
			if(!efm.getisOAM())efm.setisOAM(true);
			if (attributeName.equals("enable")) {
				efm.setEnable(value);
			}else if (attributeName.equals("errsymbperiod")) {
				efm.setErrsymbperiod(value);
			}else if (attributeName.equals("errsymbthreshold")) {
				efm.setErrsymbthreshold(value);
			}else if (attributeName.equals("errfrmperiod")) {
				efm.setErrfrmperiod(value);
			}else if (attributeName.equals("errfrmthreshold")) {
				efm.setErrfrmthreshold(value);
			}else if (attributeName.equals("errfrmperiodperiond")) {
				efm.setErrfrmperiodperiond(value);
			}else if (attributeName.equals("errfrmperiondthreshold")) {
				efm.setErrfrmperiondthreshold(value);
			}else if (attributeName.equals("efffrmsecondsperiod")) {
				efm.setEfffrmsecondsperiod(value);
			}else if (attributeName.equals("workmode")) {
				efm.setWorkmode(value);
			}else if (attributeName.equals("errfrmsecondsthreshold")) {
				efm.setErrfrmsecondsthreshold(value);
			}else if (attributeName.equals("lpbtimeout")) {
				efm.setLpbtimeout(value);
			}else if (attributeName.equals("unidir")) {
				efm.setUnidir(value);
			}else if (attributeName.equals("rmtloopback")) {
				efm.setRmtloopback(value);
			}else if (attributeName.equals("linkevent")) {
				efm.setLinkevent(value);
			}else if (attributeName.equals("varretr")) {
				efm.setVarretr(value);
			}else if (attributeName.equals("maxoampdu")) {
				efm.setMaxoampdu(value);
			}else if (attributeName.equals("oui")) {
				efm.setOui(value);
			}else if (attributeName.equals("vsi")) {
				efm.setVsi(value);
			}else if (attributeName.equals("lclmuxaction")) {
				efm.setLclmuxaction(value);
			}else if (attributeName.equals("lclparseraction")) {
				efm.setLclparseraction(value);
			}else if (attributeName.equals("rmtmuxaction")) {
				efm.setRmtmuxaction(value);
			}else if (attributeName.equals("rmtparseraction")) {
				efm.setRmtparseraction(value);
			}else if (attributeName.equals("rmtworkmode")) {
				efm.setRmtworkmode(value);
			}else if (attributeName.equals("validoammtu")) {
				efm.setValidoammtu(value);
			}else if (attributeName.equals("discoverystat")) {
				efm.setDiscoverystat(value);
			}else if (attributeName.equals("lpbstat")) {
				efm.setLpbstat(value);
			}else if (attributeName.equals("frequecy")) {
				efm.setFrequecy(value);
			}else if (attributeName.equals("losttime")) {
				efm.setLosttime(value);
			}
			else if (isNull) {
				return null;
			}
		}
		else if (isNull) {
			return null;
		}
		return efm;
	}	
	private Object setDualValue(String type, String value, String attributeName, Object object) {
		DualObject dual= null;
		if (object == null) {
			isNull = true;
			dual = new DualObject();
		} else {
			isNull = false;
			dual = (DualObject) object;
		}
		dual.setName(oldoldAttributeName);
		if (attributeName.equals("desc")) {
			dual.setDesc(value);
		}else if (attributeName.equals("enaps")) {
			dual.setEnaps(value);
		}else if (attributeName.equals("type")) {
			dual.setType(value);
		}else if (attributeName.equals("wtrtime")) {
			dual.setWtrtime(value);
		}else if (attributeName.equals("apscmd")) {
			dual.setApscmd(value);
		}else if (attributeName.equals("dualif")) {
			dual.setDualif(value);
		}else if (attributeName.equals("iswork")) {
			dual.setIswork(value);
		}else if (attributeName.equals("passtunel")) {
			dual.setPasstunel(value);
		}else if (attributeName.equals("swmanner")) {
			dual.setSwmanner(value);
		}else if (attributeName.equals("pwdrop")) {
			dual.setPwdrop(value);
		}else if (attributeName.equals("ref")) {
			dual.setRef(value);
		}else if (attributeName.equals("sel")) {
			dual.setSel(value);
		}else if (attributeName.equals("clearcnt")) {
			dual.setClearcnt(value);
		}else if (attributeName.equals("mspid")) {
			dual.setMspid(value);
		}
		else if (isNull) {
			return null;
		}
		return dual;
	}
	
	private Object setCostoexpValue(String type, String value, String attributeName, Object object) {
		CostoexpObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new CostoexpObject();
		} else {
			isNull = false;
			obj = (CostoexpObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("be")) {
			obj.setBe(value);
		}else if (attributeName.equals("af1")) {
			obj.setAf1(value);
		}else if (attributeName.equals("af2")) {
			obj.setAf2(value);
		}else if (attributeName.equals("af3")) {
			obj.setAf3(value);
		}else if (attributeName.equals("af4")) {
			obj.setAf4(value);
		}else if (attributeName.equals("ef")) {
			obj.setEf(value);
		}else if (attributeName.equals("cs6")) {
			obj.setCs6(value);
		}else if (attributeName.equals("cs7")) {
			obj.setCs7(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	private Object setExptoclrValue(String type, String value, String attributeName, Object object) {
		ExptoclrObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new ExptoclrObject();
		} else {
			isNull = false;
			obj = (ExptoclrObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("exp0")) {
			obj.setExp0(value);
		}else if (attributeName.equals("exp1")) {
			obj.setExp1(value);
		}else if (attributeName.equals("exp2")) {
			obj.setExp2(value);
		}else if (attributeName.equals("exp3")) {
			obj.setExp3(value);
		}else if (attributeName.equals("exp4")) {
			obj.setExp4(value);
		}else if (attributeName.equals("exp5")) {
			obj.setExp5(value);
		}else if (attributeName.equals("exp6")) {
			obj.setExp6(value);
		}else if (attributeName.equals("exp7")) {
			obj.setExp7(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	private Object setExptocosValue(String type, String value, String attributeName, Object object) {
		ExptocosObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new ExptocosObject();
		} else {
			isNull = false;
			obj = (ExptocosObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("exp0")) {
			obj.setExp0(value);
		}else if (attributeName.equals("exp1")) {
			obj.setExp1(value);
		}else if (attributeName.equals("exp2")) {
			obj.setExp2(value);
		}else if (attributeName.equals("exp3")) {
			obj.setExp3(value);
		}else if (attributeName.equals("exp4")) {
			obj.setExp4(value);
		}else if (attributeName.equals("exp5")) {
			obj.setExp5(value);
		}else if (attributeName.equals("exp6")) {
			obj.setExp6(value);
		}else if (attributeName.equals("exp7")) {
			obj.setExp7(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	

	private Object setCos2vlanpriValue(String type, String value, String attributeName, Object object) {
		Cos2vlanpriObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new Cos2vlanpriObject();
		} else {
			isNull = false;
			obj = (Cos2vlanpriObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("be")) {
			obj.setBe(value);
		}else if (attributeName.equals("af1")) {
			obj.setAf1(value);
		}else if (attributeName.equals("af2")) {
			obj.setAf2(value);
		}else if (attributeName.equals("af3")) {
			obj.setAf3(value);
		}else if (attributeName.equals("af4")) {
			obj.setAf4(value);
		}else if (attributeName.equals("ef")) {
			obj.setEf(value);
		}else if (attributeName.equals("cs6")) {
			obj.setCs6(value);
		}else if (attributeName.equals("cs7")) {
			obj.setCs7(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	
	private Object setVlanpri2cngValue(String type, String value, String attributeName, Object object) {
		Vlanpri2cngObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new Vlanpri2cngObject();
		} else {
			isNull = false;
			obj = (Vlanpri2cngObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("vlanpri0")) {
			obj.setVlanpri0(value);
		}else if (attributeName.equals("vlanpri1")) {
			obj.setVlanpri1(value);
		}else if (attributeName.equals("vlanpri2")) {
			obj.setVlanpri2(value);
		}else if (attributeName.equals("vlanpri3")) {
			obj.setVlanpri3(value);
		}else if (attributeName.equals("vlanpri4")) {
			obj.setVlanpri4(value);
		}else if (attributeName.equals("vlanpri5")) {
			obj.setVlanpri5(value);
		}else if (attributeName.equals("vlanpri6")) {
			obj.setVlanpri6(value);
		}else if (attributeName.equals("vlanpri7")) {
			obj.setVlanpri7(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	private Object setStaticRouteValue(String type, String value, String attributeName, Object object) {
		StaticRouteObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new StaticRouteObject();
		} else {
			isNull = false;
			obj = (StaticRouteObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("nexthop")) {
			obj.setNexthop(value);
		}else if (attributeName.equals("ifname")) {
			obj.setIfname(value);
		}else if (attributeName.equals("distance")) {
			obj.setDistance(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	private Object setAlmcfgValue(String type, String value, String attributeName, Object object) {
		AlmcfgObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new AlmcfgObject();
		} else {
			isNull = false;
			obj = (AlmcfgObject) object;
		}
//		obj.setName(oldoldAttributeName);
		if (attributeName.equals("SYSLTI")) {
			obj.setSYSLTI(value);
		}else if (attributeName.equals("SYSLTO")) {
			obj.setSYSLTO(value);
		}else if (attributeName.equals("SYNC_LOS")) {
			obj.setSYNC_LOS(value);
		}else if (attributeName.equals("SYNC_LOF")) {
			obj.setSYNC_LOF(value);
		}else if (attributeName.equals("SYNC_AIS")) {
			obj.setSYNC_AIS(value);
		}else if (attributeName.equals("SYNC_BAD")) {
			obj.setSYNC_BAD(value);
		}else if (attributeName.equals("SYNC_SSM_MisMatch")) {
			obj.setSYNC_SSM_MisMatch(value);
		}else if (attributeName.equals("PTP_LOT")) {
			obj.setPTP_LOT(value);
		}else if (attributeName.equals("PTP_LOS")) {
			obj.setPTP_LOS(value);
		}else if (attributeName.equals("PowerFail")) {
			obj.setPowerFail(value);
		}else if (attributeName.equals("HighTemp")) {
			obj.setHighTemp(value);
		}else if (attributeName.equals("LowTemp")) {
			obj.setLowTemp(value);
		}else if (attributeName.equals("FAN_FAIL")) {
			obj.setFAN_FAIL(value);
		}else if (attributeName.equals("PKG_REMOVED")) {
			obj.setPKG_REMOVED(value);
		}else if (attributeName.equals("PKG_FAIL")) {
			obj.setPKG_FAIL(value);
		}else if (attributeName.equals("PKG_TYPE")) {
			obj.setPKG_TYPE(value);
		}else if (attributeName.equals("LOS")) {
			obj.setLOS(value);
		}else if (attributeName.equals("AIS")) {
			obj.setAIS(value);
		}else if (attributeName.equals("RDI")) {
			obj.setRDI(value);
		}else if (attributeName.equals("LOC")) {
			obj.setLOC(value);
		}else if (attributeName.equals("LCK")) {
			obj.setLCK(value);
		}else if (attributeName.equals("Mismerge")) {
			obj.setMismerge(value);
		}else if (attributeName.equals("UnexpMEP")) {
			obj.setUnexpMEP(value);
		}else if (attributeName.equals("UnexpPrd")) {
			obj.setUnexpPrd(value);
		}else if (attributeName.equals("UnexpMel")) {
			obj.setUnexpMel(value);
		}else if (attributeName.equals("CSF")) {
			obj.setCSF(value);
		}else if (attributeName.equals("APSMismatch")) {
			obj.setAPSMismatch(value);
		}else if (attributeName.equals("LOF")) {
			obj.setLOF(value);
		}else if (attributeName.equals("OOF")) {
			obj.setOOF(value);
		}else if (attributeName.equals("RS_TIM")) {
			obj.setRS_TIM(value);
		}else if (attributeName.equals("RS_EXC")) {
			obj.setRS_EXC(value);
		}else if (attributeName.equals("RS_DEG")) {
			obj.setRS_DEG(value);
		}else if (attributeName.equals("MS_AIS")) {
			obj.setMS_AIS(value);
		}else if (attributeName.equals("MS_RDI")) {
			obj.setMS_RDI(value);
		}else if (attributeName.equals("MS_EXC")) {
			obj.setMS_EXC(value);
		}else if (attributeName.equals("AU_LOP")) {
			obj.setAU_LOP(value);
		}else if (attributeName.equals("MS_DEG")) {
			obj.setMS_DEG(value);
		}else if (attributeName.equals("AU_AIS")) {
			obj.setAU_AIS(value);
		}else if (attributeName.equals("HP_TIM")) {
			obj.setHP_TIM(value);
		}else if (attributeName.equals("HP_UNEQ")) {
			obj.setHP_UNEQ(value);
		}else if (attributeName.equals("HP_RDI")) {
			obj.setHP_RDI(value);
		}else if (attributeName.equals("HP_EXC")) {
			obj.setHP_EXC(value);
		}else if (attributeName.equals("HP_DEG")) {
			obj.setHP_DEG(value);
		}else if (attributeName.equals("TU_LOP")) {
			obj.setTU_LOP(value);
		}else if (attributeName.equals("TU_LOM")) {
			obj.setTU_LOM(value);
		}else if (attributeName.equals("TU_AIS")) {
			obj.setTU_AIS(value);
		}else if (attributeName.equals("LP_TIM")) {
			obj.setLP_TIM(value);
		}else if (attributeName.equals("LP_UNEQ")) {
			obj.setLP_UNEQ(value);
		}else if (attributeName.equals("LP_PLMF")) {
			obj.setLP_PLMF(value);
		}else if (attributeName.equals("LP_RDI")) {
			obj.setLP_RDI(value);
		}else if (attributeName.equals("LP_EXC")) {
			obj.setLP_EXC(value);
		}else if (attributeName.equals("LP_DEG")) {
			obj.setLP_DEG(value);
		}else if (attributeName.equals("KByteMisMatch")) {
			obj.setKByteMisMatch(value);
		}else if (attributeName.equals("LsrOffline")) {
			obj.setLsrOffline(value);
		}else if (attributeName.equals("LsrMisMatch")) {
			obj.setLsrMisMatch(value);
		}else if (attributeName.equals("ELinkFail")) {
			obj.setELinkFail(value);
		}else if (attributeName.equals("LOPS")) {
			obj.setLOPS(value);
		}else if (attributeName.equals("CES_AIS")) {
			obj.setCES_AIS(value);
		}else if (attributeName.equals("CES_RDI")) {
			obj.setCES_RDI(value);
		}else if (attributeName.equals("P_AIS")) {
			obj.setP_AIS(value);
		}else if (attributeName.equals("TSF")) {
			obj.setTSF(value);
		}else if (attributeName.equals("BUSERROR")) {
			obj.setBUSERROR(value);
		}else if (attributeName.equals("OUTSIDEALM1")) {
			obj.setOUTSIDEALM1(value);
		}else if (attributeName.equals("OUTSIDEALM2")) {
			obj.setOUTSIDEALM2(value);
		}else if (attributeName.equals("OUTSIDEALM3")) {
			obj.setOUTSIDEALM3(value);
		}else if (attributeName.equals("OUTSIDEALM4")) {
			obj.setOUTSIDEALM4(value);
		}else if (attributeName.equals("OUTSIDEALM5")) {
			obj.setOUTSIDEALM5(value);
		}else if (attributeName.equals("OUTSIDEALM6")) {
			obj.setOUTSIDEALM6(value);
		}else if (attributeName.equals("OUTSIDEALM7")) {
			obj.setOUTSIDEALM7(value);
		}else if (attributeName.equals("OUTSIDEALM8")) {
			obj.setOUTSIDEALM8(value);
		}else if (attributeName.equals("ARP_REACH_MAX")) {
			obj.setARP_REACH_MAX(value);
		}else if (attributeName.equals("ONU_MISMATCH")) {
			obj.setONU_MISMATCH(value);
		}else if (attributeName.equals("SWPKG_MISMATCH")) {
			obj.setSWPKG_MISMATCH(value);
		}else if (attributeName.equals("XCON")) {
			obj.setXCON(value);
		}else if (attributeName.equals("ERROR_CCM")) {
			obj.setERROR_CCM(value);
		}else if (attributeName.equals("SomeRDI")) {
			obj.setSomeRDI(value);
		}else if (attributeName.equals("SomeRmepCCM")) {
			obj.setSomeRmepCCM(value);
		}else if (attributeName.equals("IOP_15L")) {
			obj.setIOP_15L(value);
		}else if (attributeName.equals("IOP_24L")) {
			obj.setIOP_24L(value);
		}else if (attributeName.equals("OOP_24L")) {
			obj.setOOP_24L(value);
		}else if (attributeName.equals("OOP_15L")) {
			obj.setOOP_15L(value);
		}else if (attributeName.equals("LB_15L")) {
			obj.setLB_15L(value);
		}else if (attributeName.equals("LB_24L")) {
			obj.setLB_24L(value);
		}else if (attributeName.equals("LT_15L")) {
			obj.setLT_15L(value);
		}else if (attributeName.equals("LT_24L")) {
			obj.setLT_24L(value);
		}else if (attributeName.equals("LV_15L")) {
			obj.setLV_15L(value);
		}else if (attributeName.equals("LV_24L")) {
			obj.setLV_24L(value);
		}else if (attributeName.equals("TPALL_24H")) {
			obj.setTPALL_24H(value);
		}else if (attributeName.equals("TPALL_15H")) {
			obj.setTPALL_15H(value);
		}else if (attributeName.equals("TOALL_15H")) {
			obj.setTOALL_15H(value);
		}else if (attributeName.equals("TOALL_24H")) {
			obj.setTOALL_24H(value);
		}else if (attributeName.equals("RPALL_24H")) {
			obj.setRPALL_24H(value);
		}else if (attributeName.equals("RPALL_15H")) {
			obj.setRPALL_15H(value);
		}else if (attributeName.equals("DROPRATIO_24H")) {
			obj.setDROPRATIO_24H(value);
		}else if (attributeName.equals("DROPRATIO_15H")) {
			obj.setDROPRATIO_15H(value);
		}else if (attributeName.equals("LATENCY_15H")) {
			obj.setLATENCY_15H(value);
		}else if (attributeName.equals("LATENCY_24H")) {
			obj.setLATENCY_24H(value);
		}else if (attributeName.equals("JITTER_15H")) {
			obj.setJITTER_15H(value);
		}else if (attributeName.equals("JITTER_24H")) {
			obj.setJITTER_24H(value);
		}else if (attributeName.equals("TP64_24H")) {
			obj.setTP64_24H(value);
		}else if (attributeName.equals("TP64_15H")) {
			obj.setTP64_15H(value);
		}else if (attributeName.equals("TP65TO127_15H")) {
			obj.setTP65TO127_15H(value);
		}else if (attributeName.equals("TP65TO127_24H")) {
			obj.setTP65TO127_24H(value);
		}else if (attributeName.equals("TP128TO255_15H")) {
			obj.setTP128TO255_15H(value);
		}else if (attributeName.equals("TO128TO255_24H")) {
			obj.setTO128TO255_24H(value);
		}else if (attributeName.equals("TP256TO511_15H")) {
			obj.setTP256TO511_15H(value);
		}else if (attributeName.equals("TP256_511_24H")) {
			obj.setTP256_511_24H(value);
		}else if (attributeName.equals("TP512TO1023_15H")) {
			obj.setTP512TO1023_15H(value);
		}else if (attributeName.equals("TP512TO1023_24H")) {
			obj.setTP512TO1023_24H(value);
		}else if (attributeName.equals("TP1024TO1518_15H")) {
			obj.setTP1024TO1518_15H(value);
		}else if (attributeName.equals("TP1024TO1518_24H")) {
			obj.setTP1024TO1518_24H(value);
		}else if (attributeName.equals("TPOVERSIZE_15H")) {
			obj.setTPOVERSIZE_15H(value);
		}else if (attributeName.equals("TPOVERSIZE_24H")) {
			obj.setTPOVERSIZE_24H(value);
		}else if (attributeName.equals("RP64_24H")) {
			obj.setRP64_24H(value);
		}else if (attributeName.equals("RP64_15H")) {
			obj.setRP64_15H(value);
		}else if (attributeName.equals("RP65TO127_15H")) {
			obj.setRP65TO127_15H(value);
		}else if (attributeName.equals("RP65TO127_24H")) {
			obj.setRP65TO127_24H(value);
		}else if (attributeName.equals("RP128TO255_15H")) {
			obj.setRP128TO255_15H(value);
		}else if (attributeName.equals("RP128TO255_24H")) {
			obj.setRP128TO255_24H(value);
		}else if (attributeName.equals("RP256TO511_15H")) {
			obj.setRP256TO511_15H(value);
		}else if (attributeName.equals("RP256TO511_24H")) {
			obj.setRP256TO511_24H(value);
		}else if (attributeName.equals("RP512TO1023_15H")) {
			obj.setRP512TO1023_15H(value);
		}else if (attributeName.equals("RP512TO1023_24H")) {
			obj.setRP512TO1023_24H(value);
		}else if (attributeName.equals("RP1024TO1518_15H")) {
			obj.setRP1024TO1518_15H(value);
		}else if (attributeName.equals("RP1024TO1518_24H")) {
			obj.setRP1024TO1518_24H(value);
		}else if (attributeName.equals("RPOVERSIZE_24H")) {
			obj.setRPOVERSIZE_24H(value);
		}else if (attributeName.equals("RPOVERSIZE_15H")) {
			obj.setRPOVERSIZE_15H(value);
		}else if (attributeName.equals("TPUC_15H")) {
			obj.setTPUC_15H(value);
		}else if (attributeName.equals("TPUC_24H")) {
			obj.setTPUC_24H(value);
		}else if (attributeName.equals("TPMC_15H")) {
			obj.setTPMC_15H(value);
		}else if (attributeName.equals("TPMC_24H")) {
			obj.setTPMC_24H(value);
		}else if (attributeName.equals("TPBC_15H")) {
			obj.setTPBC_15H(value);
		}else if (attributeName.equals("TPBC_24H")) {
			obj.setTPBC_24H(value);
		}else if (attributeName.equals("RPUC_15H")) {
			obj.setRPUC_15H(value);
		}else if (attributeName.equals("RPUC_24H")) {
			obj.setRPUC_24H(value);
		}else if (attributeName.equals("RPMC_15H")) {
			obj.setRPMC_15H(value);
		}else if (attributeName.equals("RPMC_24H")) {
			obj.setRPMC_24H(value);
		}else if (attributeName.equals("RPBC_15H")) {
			obj.setRPBC_15H(value);
		}else if (attributeName.equals("RPBC_24H")) {
			obj.setRPBC_24H(value);
		}else if (attributeName.equals("TPOK_15H")) {
			obj.setTPOK_15H(value);
		}else if (attributeName.equals("TPOK_24H")) {
			obj.setTPOK_24H(value);
		}else if (attributeName.equals("TOOK_15H")) {
			obj.setTOOK_15H(value);
		}else if (attributeName.equals("TOOK_24H")) {
			obj.setTOOK_24H(value);
		}else if (attributeName.equals("RPOK_15H")) {
			obj.setRPOK_15H(value);
		}else if (attributeName.equals("RPOK_24H")) {
			obj.setRPOK_24H(value);
		}else if (attributeName.equals("ROOK_15H")) {
			obj.setROOK_15H(value);
		}else if (attributeName.equals("ROOK_24H")) {
			obj.setROOK_24H(value);
		}else if (attributeName.equals("FCSERR_15H")) {
			obj.setFCSERR_15H(value);
		}else if (attributeName.equals("FCSERR_24H")) {
			obj.setFCSERR_24H(value);
		}else if (attributeName.equals("LPES_15H")) {
			obj.setLPES_15H(value);
		}else if (attributeName.equals("LPES_24H")) {
			obj.setLPES_24H(value);
		}else if (attributeName.equals("LPSES_15H")) {
			obj.setLPSES_15H(value);
		}else if (attributeName.equals("LPSES_24H")) {
			obj.setLPSES_24H(value);
		}else if (attributeName.equals("LPBBE_15H")) {
			obj.setLPBBE_15H(value);
		}else if (attributeName.equals("LPBBE_24H")) {
			obj.setLPBBE_24H(value);
		}else if (attributeName.equals("LPUAS_15H")) {
			obj.setLPUAS_15H(value);
		}else if (attributeName.equals("LPUAS_24H")) {
			obj.setLPUAS_24H(value);
		}else if (attributeName.equals("RSES_15H")) {
			obj.setRSES_15H(value);
		}else if (attributeName.equals("RSES_24H")) {
			obj.setRSES_24H(value);
		}else if (attributeName.equals("RSSES_15H")) {
			obj.setRSSES_15H(value);
		}else if (attributeName.equals("RSSES_24H")) {
			obj.setRSSES_24H(value);
		}else if (attributeName.equals("RSBBE_15H")) {
			obj.setRSBBE_15H(value);
		}else if (attributeName.equals("RSBBE_24H")) {
			obj.setRSBBE_24H(value);
		}else if (attributeName.equals("RSUAS_15H")) {
			obj.setRSUAS_15H(value);
		}else if (attributeName.equals("RSUAS_24H")) {
			obj.setRSUAS_24H(value);
		}else if (attributeName.equals("MSES_15H")) {
			obj.setMSES_15H(value);
		}else if (attributeName.equals("MSES_24H")) {
			obj.setMSES_24H(value);
		}else if (attributeName.equals("MSSES_15H")) {
			obj.setMSSES_15H(value);
		}else if (attributeName.equals("MSSES_24H")) {
			obj.setMSSES_24H(value);
		}else if (attributeName.equals("MSBBE_15H")) {
			obj.setMSBBE_15H(value);
		}else if (attributeName.equals("MSBBE_24H")) {
			obj.setMSBBE_24H(value);
		}else if (attributeName.equals("MSUAS_15H")) {
			obj.setMSUAS_15H(value);
		}else if (attributeName.equals("MSUAS_24H")) {
			obj.setMSUAS_24H(value);
		}else if (attributeName.equals("HPES_15H")) {
			obj.setHPES_15H(value);
		}else if (attributeName.equals("HPES_24H")) {
			obj.setHPES_24H(value);
		}else if (attributeName.equals("HPBBE_15H")) {
			obj.setHPBBE_15H(value);
		}else if (attributeName.equals("HPBBE_24H")) {
			obj.setHPBBE_24H(value);
		}else if (attributeName.equals("HPUAS_15H")) {
			obj.setHPUAS_15H(value);
		}else if (attributeName.equals("HPUAS_24H")) {
			obj.setHPUAS_24H(value);
		}else if (attributeName.equals("IOP_15H")) {
			obj.setIOP_15H(value);
		}else if (attributeName.equals("IOP_24H")) {
			obj.setIOP_24H(value);
		}else if (attributeName.equals("OOP_15H")) {
			obj.setOOP_15H(value);
		}else if (attributeName.equals("OOP_24H")) {
			obj.setOOP_24H(value);
		}else if (attributeName.equals("LB_15H")) {
			obj.setLB_15H(value);
		}else if (attributeName.equals("LB_24H")) {
			obj.setLB_24H(value);
		}else if (attributeName.equals("LT_15H")) {
			obj.setLT_15H(value);
		}else if (attributeName.equals("LT_24H")) {
			obj.setLT_24H(value);
		}else if (attributeName.equals("PWTDMMISSINGPKIS_15H")) {
			obj.setPWTDMMISSINGPKIS_15H(value);
		}else if (attributeName.equals("PWTDMMISSINGPKIS_24H")) {
			obj.setPWTDMMISSINGPKIS_24H(value);
		}else if (attributeName.equals("PWTDMJTRBFRUNDERRUNS_15H")) {
			obj.setPWTDMJTRBFRUNDERRUNS_15H(value);
		}else if (attributeName.equals("PWTDMJTRBFRUNDERRUNS_24H")) {
			obj.setPWTDMJTRBFRUNDERRUNS_24H(value);
		}else if (attributeName.equals("PWTDMMALFORMEDPKTS_15H")) {
			obj.setPWTDMMALFORMEDPKTS_15H(value);
		}else if (attributeName.equals("PWTDMMALFORMEDPKTS_24H")) {
			obj.setPWTDMMALFORMEDPKTS_24H(value);
		}else if (attributeName.equals("PWTDMJTRBFROVERRUNS_15H")) {
			obj.setPWTDMJTRBFROVERRUNS_15H(value);
		}else if (attributeName.equals("PWTDMJTRBFROVERRUNS_24H")) {
			obj.setPWTDMJTRBFROVERRUNS_24H(value);
		}else if (attributeName.equals("PDHCV_15H")) {
			obj.setPDHCV_15H(value);
		}else if (attributeName.equals("PDHCV_24H")) {
			obj.setPDHCV_24H(value);
		}else if (attributeName.equals("MPCP_15H")) {
			obj.setMPCP_15H(value);
		}else if (attributeName.equals("MPCP_24H")) {
			obj.setMPCP_24H(value);
		}else if (attributeName.equals("OAMPDU_15H")) {
			obj.setOAMPDU_15H(value);
		}else if (attributeName.equals("OAMPDU_24H")) {
			obj.setOAMPDU_24H(value);
		}else if (attributeName.equals("RPKT63_15H")) {
			obj.setRPKT63_15H(value);
		}else if (attributeName.equals("RPKT63_24H")) {
			obj.setRPKT63_24H(value);
		}else if (attributeName.equals("RPKT64TO127_15H")) {
			obj.setRPKT64TO127_15H(value);
		}else if (attributeName.equals("RPKT64TO127_24H")) {
			obj.setRPKT64TO127_24H(value);
		}else if (attributeName.equals("RPKT128TO255_15H")) {
			obj.setRPKT128TO255_15H(value);
		}else if (attributeName.equals("RPKT128TO255_24H")) {
			obj.setRPKT128TO255_24H(value);
		}else if (attributeName.equals("RPKT256TO511_15H")) {
			obj.setRPKT256TO511_15H(value);
		}else if (attributeName.equals("RPKT256TO511_24H")) {
			obj.setRPKT256TO511_24H(value);
		}else if (attributeName.equals("RPKT512TO1023_15H")) {
			obj.setRPKT512TO1023_15H(value);
		}else if (attributeName.equals("RPKT512TO1023_24H")) {
			obj.setRPKT512TO1023_24H(value);
		}else if (attributeName.equals("RPKT1024TO1535_15H")) {
			obj.setRPKT1024TO1535_15H(value);
		}else if (attributeName.equals("RPKT1024TO1535_24H")) {
			obj.setRPKT1024TO1535_24H(value);
		}else if (attributeName.equals("RPKTFCSERR_15H")) {
			obj.setRPKTFCSERR_15H(value);
		}else if (attributeName.equals("RPKTFCSERR_24H")) {
			obj.setRPKTFCSERR_24H(value);
		}else if (attributeName.equals("RPKTSUPERLEN_15H")) {
			obj.setRPKTSUPERLEN_15H(value);
		}else if (attributeName.equals("RPKTSUPERLEN_24H")) {
			obj.setRPKTSUPERLEN_24H(value);
		}else if (attributeName.equals("RPKTLLIDERR_15H")) {
			obj.setRPKTLLIDERR_15H(value);
		}else if (attributeName.equals("RPKTLLIDERR_24H")) {
			obj.setRPKTLLIDERR_24H(value);
		}else if (attributeName.equals("RBYTEOK_15H")) {
			obj.setRBYTEOK_15H(value);
		}else if (attributeName.equals("RBYTEOK_24H")) {
			obj.setRBYTEOK_24H(value);
		}else if (attributeName.equals("RPKTKNUC_15H")) {
			obj.setRPKTKNUC_15H(value);
		}else if (attributeName.equals("RPKTKNUC_24H")) {
			obj.setRPKTKNUC_24H(value);
		}else if (attributeName.equals("RPKTKNMC_15H")) {
			obj.setRPKTKNMC_15H(value);
		}else if (attributeName.equals("RPKTKNMC_24H")) {
			obj.setRPKTKNMC_24H(value);
		}else if (attributeName.equals("RPKTBC_15H")) {
			obj.setRPKTBC_15H(value);
		}else if (attributeName.equals("RPKTBC_24H")) {
			obj.setRPKTBC_24H(value);
		}else if (attributeName.equals("RPKTNOVLAN_15H")) {
			obj.setRPKTNOVLAN_15H(value);
		}else if (attributeName.equals("RPKTNOVLAN_24H")) {
			obj.setRPKTNOVLAN_24H(value);
		}else if (attributeName.equals("RPKTNOMAC_15H")) {
			obj.setRPKTNOMAC_15H(value);
		}else if (attributeName.equals("RPKTNOMAC_24H")) {
			obj.setRPKTNOMAC_24H(value);
		}else if (attributeName.equals("RPKTNOP2P_15H")) {
			obj.setRPKTNOP2P_15H(value);
		}else if (attributeName.equals("RPKTNOP2P_24H")) {
			obj.setRPKTNOP2P_24H(value);
		}else if (attributeName.equals("RPKTNOACL_15H")) {
			obj.setRPKTNOACL_15H(value);
		}else if (attributeName.equals("RPKTNOACL_24H")) {
			obj.setRPKTNOACL_24H(value);
		}else if (attributeName.equals("RPKTNOPOL_15H")) {
			obj.setRPKTNOPOL_15H(value);
		}else if (attributeName.equals("RPKTNOPOL_24H")) {
			obj.setRPKTNOPOL_24H(value);
		}else if (attributeName.equals("RPKTNODYWRED_15H")) {
			obj.setRPKTNODYWRED_15H(value);
		}else if (attributeName.equals("RPKTNODYWRED_24H")) {
			obj.setRPKTNODYWRED_24H(value);
		}else if (attributeName.equals("RPKTNOWRED_15H")) {
			obj.setRPKTNOWRED_15H(value);
		}else if (attributeName.equals("RPKTNOWRED_24H")) {
			obj.setRPKTNOWRED_24H(value);
		}else if (attributeName.equals("RBYTENOPOL_15H")) {
			obj.setRBYTENOPOL_15H(value);
		}else if (attributeName.equals("RBYTENOPOL_24H")) {
			obj.setRBYTENOPOL_24H(value);
		}else if (attributeName.equals("TPKT63_15H")) {
			obj.setTPKT63_15H(value);
		}else if (attributeName.equals("TPKT63_24H")) {
			obj.setTPKT63_24H(value);
		}else if (attributeName.equals("TPKT64TO127_15H")) {
			obj.setTPKT64TO127_15H(value);
		}else if (attributeName.equals("TPKT64TO127_24H")) {
			obj.setTPKT64TO127_24H(value);
		}else if (attributeName.equals("TPKT128TO255_15H")) {
			obj.setTPKT128TO255_15H(value);
		}else if (attributeName.equals("TPKT128TO255_24H")) {
			obj.setTPKT128TO255_24H(value);
		}else if (attributeName.equals("TPKT256TO511_15H")) {
			obj.setTPKT256TO511_15H(value);
		}else if (attributeName.equals("TPKT256TO511_24H")) {
			obj.setTPKT256TO511_24H(value);
		}else if (attributeName.equals("TPKT512TO1023_15H")) {
			obj.setTPKT512TO1023_15H(value);
		}else if (attributeName.equals("TPKT512TO1023_24H")) {
			obj.setTPKT512TO1023_24H(value);
		}else if (attributeName.equals("TPKT1024TO1535_15H")) {
			obj.setTPKT1024TO1535_15H(value);
		}else if (attributeName.equals("TPKT1024TO1535_24H")) {
			obj.setTPKT1024TO1535_24H(value);
		}else if (attributeName.equals("TBYTEOK_15H")) {
			obj.setTBYTEOK_15H(value);
		}else if (attributeName.equals("TBYTEOK_24H")) {
			obj.setTBYTEOK_24H(value);
		}else if (attributeName.equals("TPKTMC_15H")) {
			obj.setTPKTMC_15H(value);
		}else if (attributeName.equals("TPKTMC_24H")) {
			obj.setTPKTMC_24H(value);
		}else if (attributeName.equals("TPKTUC_15H")) {
			obj.setTPKTUC_15H(value);
		}else if (attributeName.equals("TPKTUC_24H")) {
			obj.setTPKTUC_24H(value);
		}else if (attributeName.equals("TPMPCPDIS_15H")) {
			obj.setTPMPCPDIS_15H(value);
		}else if (attributeName.equals("TPMPCPDIS_24H")) {
			obj.setTPMPCPDIS_24H(value);
		}else if (attributeName.equals("TPMPCPREG_15H")) {
			obj.setTPMPCPREG_15H(value);
		}else if (attributeName.equals("TPMPCPREG_24H")) {
			obj.setTPMPCPREG_24H(value);
		}else if (attributeName.equals("TPMPCPREREG_15H")) {
			obj.setTPMPCPREREG_15H(value);
		}else if (attributeName.equals("TPMPCPREREG_24H")) {
			obj.setTPMPCPREREG_24H(value);
		}else if (attributeName.equals("RPMPCPDIS_15H")) {
			obj.setRPMPCPDIS_15H(value);
		}else if (attributeName.equals("RPMPCPDIS_24H")) {
			obj.setRPMPCPDIS_24H(value);
		}else if (attributeName.equals("RPMPCPREG_15H")) {
			obj.setRPMPCPREG_15H(value);
		}else if (attributeName.equals("RPMPCPREG_24H")) {
			obj.setRPMPCPREG_24H(value);
		}else if (attributeName.equals("RPMPCPDEREG_15H")) {
			obj.setRPMPCPDEREG_15H(value);
		}else if (attributeName.equals("RPMPCPDEREG_24H")) {
			obj.setRPMPCPDEREG_24H(value);
		}else if (attributeName.equals("LV_15H")) {
			obj.setLV_15H(value);
		}else if (attributeName.equals("LV_24H")) {
			obj.setLV_24H(value);
		}else if (attributeName.equals("RPDISCARD_15H")) {
			obj.setRPDISCARD_15H(value);
		}else if (attributeName.equals("RPDISCARD_24H")) {
			obj.setRPDISCARD_24H(value);
		}else if (attributeName.equals("RBYTEDISCARD_15H")) {
			obj.setRBYTEDISCARD_15H(value);
		}else if (attributeName.equals("RBYTENISCARD_24H")) {
			obj.setRBYTENISCARD_24H(value);
		}else if (attributeName.equals("RPOAM_15H")) {
			obj.setRPOAM_15H(value);
		}else if (attributeName.equals("RPOAM_24H")) {
			obj.setRPOAM_24H(value);
		}else if (attributeName.equals("TPNORMALGATE_15H")) {
			obj.setTPNORMALGATE_15H(value);
		}else if (attributeName.equals("TPNORMALGATE_24H")) {
			obj.setTPNORMALGATE_24H(value);
		}else if (attributeName.equals("RPREPORT_15H")) {
			obj.setRPREPORT_15H(value);
		}else if (attributeName.equals("RPREPORT_24H")) {
			obj.setRPREPORT_24H(value);
		}else if (attributeName.equals("TPBAD_15H")) {
			obj.setTPBAD_15H(value);
		}else if (attributeName.equals("TPBAD_24H")) {
			obj.setTPBAD_24H(value);
		}else if (attributeName.equals("RPBAD_15H")) {
			obj.setRPBAD_15H(value);
		}else if (attributeName.equals("RPBAD_24H")) {
			obj.setRPBAD_24H(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}
	/**
	 * 温度管理
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setTempValue(String type, String value, String attributeName, Object object) {
		SlotTempObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new SlotTempObject();
		} else {
			isNull = false;
			obj = (SlotTempObject) object;
		}
		obj.setName(oldoldAttributeName);
		if (attributeName.equals("temphighthr")) {
			obj.setTemphighthr(value);
		}else if (attributeName.equals("templowthr")) {
			obj.setTemplowthr(value);
		}else if (attributeName.equals("logcalcardtype")) {
			//obj.setExp2(value);
		}else if (attributeName.equals("phycardtype")) {
			//obj.setExp3(value);
		}else if (attributeName.equals("curtemp")) {
			obj.setCurTemp(value);
		}else if (attributeName.equals("hishightemp")) {
			obj.setHisHighTemp(value);
		}else if (attributeName.equals("hislowtemp")) {
			obj.setHisLowTemp(value);
		}else if (attributeName.equals("online")) {
			obj.setOnline(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	/**
	 *板卡保护
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setCardPro(String type, String value, String attributeName, Object object) {
		CardProObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new CardProObject();
		} else {
			isNull = false;
			obj = (CardProObject) object;
		}
		if(oldoldAttributeName.equals("1")){
			obj.setName("主控单板保护组");
		}else if(oldoldAttributeName.equals("2")){
			obj.setName("交叉和时钟保护组");
		}else if(oldoldAttributeName.equals("3")){
			obj.setName("PDH单板保护组1");
		}else if(oldoldAttributeName.equals("4")){
			obj.setName("PDH单板保护组2");
		}else {
			obj.setName("");
		}
		if(attributeName.equals("apscmd")){
			obj.setApscmd(value);
		}else if(attributeName.equals("card1")){
			obj.setCard1(value);
		}else if(attributeName.equals("card2")){
			obj.setCard2(value);
		}else if(attributeName.equals("sel")){
			obj.setSel(value);
		}
		else if (isNull) {
			return null;
		}
		return obj;
	}	
	/**
	 * oam测试
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setOamTestValue(String type, String value, String attributeName, Object object) {
		OamTestObject oamTestObject = null;
		if (object == null) {
			isNull = true;
			oamTestObject = new OamTestObject();
		} else {
			isNull = false;
			oamTestObject = (OamTestObject) object;
		}
		if(attributeName.equals("1")){
			oamTestObject.setResult(value);
		}
		//oamTestObject.setResult(attributeName);
		return oamTestObject;
	}
	/**
	 * TMD环回
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setLoopBack(String type, String value, String attributeName, Object object) {
		PdhPortObject pdhPortObject=null;
		SdhPortObject sdhPortObject=null;
		ExtclkObject extclkObject=null;
		CardProObject obj= null;
		if (object == null) {
			isNull = true;
			if(oldoldAttributeName.contains("stm1.")){//sdh
				sdhPortObject=new SdhPortObject();
			}else if(oldoldAttributeName.contains("e1.")){//pdh
				pdhPortObject=new PdhPortObject();
			}else{
				extclkObject=new ExtclkObject();
			}
		} else {
			isNull = false;
			if(oldoldAttributeName.contains("stm1.")){//sdh
				sdhPortObject=(SdhPortObject) object;
			}else if(oldoldAttributeName.contains("e1.")){//pdh
				pdhPortObject=(PdhPortObject) object;
			}else{
				extclkObject=(ExtclkObject) object;
			}
		}
		if(pdhPortObject !=null){
			pdhPortObject.setName(oldoldAttributeName);
			if(attributeName.equals("admin")){
				pdhPortObject.setAdmin(value);
			}else if(attributeName.equals("loopback")){
				pdhPortObject.setLoopback(value);
			}else if(attributeName.equals("oper")){
				pdhPortObject.setOper(value);
			}
			return pdhPortObject;
		}else if(sdhPortObject!=null){
			sdhPortObject.setName(oldoldAttributeName);
			if(attributeName.equals("admin")){
				sdhPortObject.setAdmin(value);
			}else if(attributeName.equals("loopback")){
				sdhPortObject.setLoopback(value);
			}else if(attributeName.equals("oper")){
				sdhPortObject.setOper(value);
			}
			return sdhPortObject;
		}else if(extclkObject!=null){
			extclkObject.setName(oldoldAttributeName);
			if(attributeName.equals("admin")){
				extclkObject.setAdmin(value);
			}else if(attributeName.equals("loopback")){
				extclkObject.setLoopback(value);
			}else if(attributeName.equals("oper")){
				extclkObject.setOper(value);
			}
			return extclkObject;
		}else if(isNull){
			return null;
		}
		return null;
	}
	/**
	 * 查询-保护倒换事件
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setProtectLog(String type, String value, String attributeName, Object object) {
		ProtectLogObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new ProtectLogObject();
		} else {
			isNull = false;
			obj = (ProtectLogObject) object;
		}
		if(!oldAttributeName.equals("colnames")&&!oldAttributeName.equals("coltypes")){		
			if(attributeName.equals("1")){
				obj.setType(value);
			}else if(attributeName.equals("2")){
				obj.setObj(obj.getType()+"/"+value);
			}else if(attributeName.equals("3")){
				obj.setCarse(obj.transProtectLog(value));
			}else if(attributeName.equals("4")){
				obj.setDesc(obj.transProtectLog(value));
			}else if(attributeName.equals("5")){
				obj.setTime(value);
			}
		}
		return obj;
	}
	/**
	 * 上报-保护倒换事件
	 * @param type
	 * @param value
	 * @param attributeName
	 * @param object
	 * @return
	 */
	private Object setProtectHookNotify(String type, String value, String attributeName, Object object) {
		ProtectLogObject obj= null;
		if (object == null) {
			isNull = true;
			obj = new ProtectLogObject();
		} else {
			isNull = false;
			obj = (ProtectLogObject) object;
		}
		if(!oldAttributeName.equals("colnames")&&!oldAttributeName.equals("coltypes")){		
			if(attributeName.equals("5")){
				obj.setType(value);
			}else if(attributeName.equals("1")){
				if(obj.getType().equals("lsp")){
					obj.setObj("tunnel/"+value);
				}else{
					obj.setObj(obj.getType()+"/"+value);
				}
			}else if(attributeName.equals("2")){
				obj.setCarse(obj.transProtectLog(value));
			}else if(attributeName.equals("3")){
				obj.setDesc(obj.transProtectLog(value));
			}else if(attributeName.equals("4")){
				obj.setTime(value);
			}
		}
		return obj;
	}	
}
