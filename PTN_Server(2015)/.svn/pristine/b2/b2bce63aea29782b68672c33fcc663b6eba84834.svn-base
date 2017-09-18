package com.nms.drivechenxiao.service.bean.porteth;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.lag.LAGObject;
import com.nms.drivechenxiao.service.bean.porteth.nbr.NBRObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.AFObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.BEObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.CSObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.EFObject;
import com.nms.drivechenxiao.service.bean.porteth.uni.UNIObject;

public class EthPortObject {

	private NBRObject nbr = new NBRObject();
	private NNIObject nni = new NNIObject();
	private UNIObject uni = new UNIObject();
	private LAGObject lag = new LAGObject();
	private OamObject oam = new OamObject();
	private CSObject cs3 = new CSObject();
	private CSObject cs6 = new CSObject();
	private CSObject cs7 = new CSObject();
	private BEObject be = new BEObject();
	private AFObject af1 = new AFObject();
	private AFObject af2 = new AFObject();
	private AFObject af3 = new AFObject();
	private AFObject af4 = new AFObject();
	private EFObject ef = new EFObject();
	private List<AcObject> acList = new ArrayList<AcObject>();

	private String name = "";// 名称 **
	private String neType = "";// 设备类型 **
	private String iused = ""; // 当前输入使用带宽
	private String oused = ""; // 当前输出使用带宽
	private String ifname = "";// 接口名
	private String ifindex = ""; // 接口索引值
	private String desc = ""; // 接口描述
	private String admin = ""; // 接口管理状态
	private String oper = ""; // 接口工作状态
	private String perprofile = ""; // 性能
	private String als = ""; // ALS使能
	private String alsdelay = ""; // ALS激光器关闭时长
	private String alsshtup = ""; // ALS激光器开启时长
	private String ref = "";
	private String sfptype = ""; // SFP实际类型
	private String sfpvendor = "";// SFP厂家信息
	private String wavelength = "";// 波长
	private String sfpexptype = "";// 期望SFP类型
	private String dnugroup = ""; // DNU组
	private String ssmoutputenable = ""; // SSM输出使能
	private String framelen = "";// 最大帧长
	private String fc = "false"; // 流控
	private String slowproto_tocpu = ""; // 提交到CPU控制
	private String arpproto_tocpu = ""; // 提交到CPU控制
	private String dhcpproto_tocpu = "";// 提交到CPU控制
	private String aspeed = ""; // 接口实际速率，单位mb/s
	private String speed = "";// 端口速率 1: 100m 2: 1g 3: 10g 4: neg100m 5: neg1g
	// 6: 2g5 neg1g 4
	private String mac = ""; // 本地MAC地址
	private String role = "";// 接口角色(uni|nni)**
	private String type = "";
	private String xgwan = ""; // 10G接口的WAN(true),LAN(false)
	private String mirror = "";// 被镜像接口
	private String isolation = ""; // 端口隔离组
	private String portac = "";
	private String permitpktloop = ""; // 是否允许报文环回
	private String ringid = ""; // 环ID
	private String switchport = "";
	private String Stringernal = ""; // 如果为true,代表系统内部接口,这个接口不应该显示在网管界面上,并且用户不能对它进行任何配置
	private String l2aclhwres = "";
	private String l3aclhwres = "";
	private String schmode = "";// 端口队列调度模式
	private String vegc = "";
	private String portType = "";//eth||lag
	private String siteType = "";//端口所属 网元 属性 。700B->cxt100 ;700D->cxt20b ;700E->cxt20a
//	/**
//	 * 修改业务 (eline，etree,elan)
//	 *  identify  原来的名称-与name相对应
//	 * 判断  端口 是否修改
//	 */
	private String identify;
//	/**
//	 * 修改业务 (eline，etree,elan)
//	 *  previous  原来的名称-与portType相对应  eth/lag
//	 * 判断  端口类型 是否修改
//	 */
	private String previous ;
	
	
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String toString1(){
		return "name="+name+" ;mac="+mac;
	}
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" siteType=").append(siteType)
		.append(" ;name=").append(name).append(" ;neType=").append(neType).append(" ;iused=").append(iused).append(" ;oused=").append(oused)
		.append(" ;ifname=").append(ifname).append(" ;ifindex=").append(ifindex)
			.append(" ;desc=").append(desc) 
	.append(" ;admin=").append(admin)
	.append(" ;oper=").append(oper)
	.append(" ;perprofile=").append(perprofile)
	.append(" ;als=").append(als)
	.append(" ;alsdelay=").append(alsdelay)
	.append(" ;alsshtup=").append(alsshtup)
	.append(" ;ref=").append(ref)
	.append(" ;sfptype=").append(sfptype)
	.append(" ;sfpvendor=").append(sfpvendor)
	.append(" ;wavelength=").append(wavelength)
	.append(" ;sfpexptype=").append(sfpexptype)
	.append(" ;dnugroup=").append(dnugroup)
	.append(" ;ssmoutputenable=").append(ssmoutputenable)
	.append(" ;framelen=").append(framelen)
	.append(" ;fc=").append(fc)
	.append(" ;slowproto_tocpu=").append(slowproto_tocpu)
	.append(" ;arpproto_tocpu=").append(arpproto_tocpu)
	.append(" ;dhcpproto_tocpu=").append(dhcpproto_tocpu)
	.append(" ;aspeed=").append(aspeed)
	.append(" ;speed=").append(speed) 
	.append(" ;mac=").append(mac)
	.append(" ;role=").append(role)
	.append(" ;type=").append(type)
	.append(" ;xgwan=").append(xgwan)
	.append(" ;mirror=").append(mirror)
	.append(" ;isolation=").append(isolation)
	.append(" ;portac=").append(portac)
	.append(" ;permitpktloop=").append(permitpktloop)
	.append(" ;ringid=").append(ringid)
	.append(" ;switchport=").append(switchport)
	.append(" ;Stringernal=").append(Stringernal)
	.append(" ;l2aclhwres=").append(l2aclhwres)
	.append(" ;l3aclhwres=").append(l3aclhwres)
	.append(" ;schmode=").append(schmode)
	.append(" ;vegc=").append(vegc)
	.append(" ;portType=").append(portType)
	.append(" ;nbr={").append(nbr.toString()).append("}")
	.append(" ;nni={").append(nni.toString()).append("}")
	.append(" ;uni={").append(uni.toString()).append("}")
	.append(" ;lag={").append(lag.toString()).append("}")
	.append(" ;oam={").append(oam.toString()).append("}")
	.append(" ;cs3={").append(cs3.toString()).append("}")
	.append(" ;cs6={").append(cs6.toString()).append("}")
	.append(" ;cs7={").append(cs7.toString()).append("}")
	.append(" ;be={").append(be.toString()).append("}")
	.append(" ;af1={").append(af1.toString()).append("}")
	.append(" ;af2={").append(af2.toString()).append("}")
	.append(" ;af3={").append(af3.toString()).append("}")
	.append(" ;af4={").append(af4.toString()).append("}")
	.append(" ;ef={").append(ef.toString()).append("}")
		;
		
		return sb.toString();
	}
	
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getPortType() {
		return portType;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public OamObject getOam() {
		return oam;
	}

	public void setOam(OamObject oam) {
		this.oam = oam;
	}

	public CSObject getCs3() {
		return cs3;
	}

	public void setCs3(CSObject cs3) {
		this.cs3 = cs3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeType() {
		return neType;
	}

	public void setNeType(String neType) {
		this.neType = neType;
	}

	public NBRObject getNbr() {
		return nbr;
	}

	public void setNbr(NBRObject nbr) {
		this.nbr = nbr;
	}

	public NNIObject getNni() {
		return nni;
	}

	public void setNni(NNIObject nni) {
		this.nni = nni;
	}

	public UNIObject getUni() {
		return uni;
	}

	public void setUni(UNIObject uni) {
		this.uni = uni;
	}

	public LAGObject getLag() {
		return lag;
	}

	public void setLag(LAGObject lag) {
		this.lag = lag;
	}

	public CSObject getCs6() {
		return cs6;
	}

	public void setCs6(CSObject cs6) {
		this.cs6 = cs6;
	}

	public CSObject getCs7() {
		return cs7;
	}

	public void setCs7(CSObject cs7) {
		this.cs7 = cs7;
	}

	public BEObject getBe() {
		return be;
	}

	public void setBe(BEObject be) {
		this.be = be;
	}

	public AFObject getAf1() {
		return af1;
	}

	public void setAf1(AFObject af1) {
		this.af1 = af1;
	}

	public AFObject getAf2() {
		return af2;
	}

	public void setAf2(AFObject af2) {
		this.af2 = af2;
	}

	public AFObject getAf3() {
		return af3;
	}

	public void setAf3(AFObject af3) {
		this.af3 = af3;
	}

	public AFObject getAf4() {
		return af4;
	}

	public void setAf4(AFObject af4) {
		this.af4 = af4;
	}

	public EFObject getEf() {
		return ef;
	}

	public void setEf(EFObject ef) {
		this.ef = ef;
	}

	public String getIused() {
		return iused;
	}

	public List<AcObject> getAcList() {
		return acList;
	}

	public void setAcList(List<AcObject> acList) {
		this.acList = acList;
	}

	public void setIused(String iused) {
		this.iused = iused;
	}

	public String getOused() {
		return oused;
	}

	public void setOused(String oused) {
		this.oused = oused;
	}

	public String getIfname() {
		return ifname;
	}

	public void setIfname(String ifname) {
		this.ifname = ifname;
	}

	public String getIfindex() {
		return ifindex;
	}

	public void setIfindex(String ifindex) {
		this.ifindex = ifindex;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getPerprofile() {
		return perprofile;
	}

	public void setPerprofile(String perprofile) {
		this.perprofile = perprofile;
	}

	public String getAls() {
		return als;
	}

	public void setAls(String als) {
		this.als = als;
	}

	public String getAlsdelay() {
		return alsdelay;
	}

	public void setAlsdelay(String alsdelay) {
		this.alsdelay = alsdelay;
	}

	public String getAlsshtup() {
		return alsshtup;
	}

	public void setAlsshtup(String alsshtup) {
		this.alsshtup = alsshtup;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getSfptype() {
		return sfptype;
	}

	public void setSfptype(String sfptype) {
		this.sfptype = sfptype;
	}

	public String getSfpvendor() {
		return sfpvendor;
	}

	public void setSfpvendor(String sfpvendor) {
		this.sfpvendor = sfpvendor;
	}

	public String getWavelength() {
		return wavelength;
	}

	public void setWavelength(String wavelength) {
		this.wavelength = wavelength;
	}

	public String getSfpexptype() {
		return sfpexptype;
	}

	public void setSfpexptype(String sfpexptype) {
		this.sfpexptype = sfpexptype;
	}

	public String getDnugroup() {
		return dnugroup;
	}

	public void setDnugroup(String dnugroup) {
		this.dnugroup = dnugroup;
	}

	public String getSsmoutputenable() {
		return ssmoutputenable;
	}

	public void setSsmoutputenable(String ssmoutputenable) {
		this.ssmoutputenable = ssmoutputenable;
	}

	public String getFramelen() {
		return framelen;
	}

	public void setFramelen(String framelen) {
		this.framelen = framelen;
	}

	public String getFc() {
		return fc;
	}

	public void setFc(String fc) {
		this.fc = fc;
	}

	public String getSlowproto_tocpu() {
		return slowproto_tocpu;
	}

	public void setSlowproto_tocpu(String slowproto_tocpu) {
		this.slowproto_tocpu = slowproto_tocpu;
	}

	public String getArpproto_tocpu() {
		return arpproto_tocpu;
	}

	public void setArpproto_tocpu(String arpproto_tocpu) {
		this.arpproto_tocpu = arpproto_tocpu;
	}

	public String getDhcpproto_tocpu() {
		return dhcpproto_tocpu;
	}

	public void setDhcpproto_tocpu(String dhcpproto_tocpu) {
		this.dhcpproto_tocpu = dhcpproto_tocpu;
	}

	public String getAspeed() {
		return aspeed;
	}

	public void setAspeed(String aspeed) {
		this.aspeed = aspeed;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXgwan() {
		return xgwan;
	}

	public void setXgwan(String xgwan) {
		this.xgwan = xgwan;
	}

	public String getMirror() {
		return mirror;
	}

	public void setMirror(String mirror) {
		this.mirror = mirror;
	}

	public String getIsolation() {
		return isolation;
	}

	public void setIsolation(String isolation) {
		this.isolation = isolation;
	}

	public String getPortac() {
		return portac;
	}

	public void setPortac(String portac) {
		this.portac = portac;
	}

	public String getPermitpktloop() {
		return permitpktloop;
	}

	public void setPermitpktloop(String permitpktloop) {
		this.permitpktloop = permitpktloop;
	}

	public String getRingid() {
		return ringid;
	}

	public void setRingid(String ringid) {
		this.ringid = ringid;
	}

	public String getSwitchport() {
		return switchport;
	}

	public void setSwitchport(String switchport) {
		this.switchport = switchport;
	}

	public String getStringernal() {
		return Stringernal;
	}

	public void setStringernal(String stringernal) {
		Stringernal = stringernal;
	}

	public String getL2aclhwres() {
		return l2aclhwres;
	}

	public void setL2aclhwres(String l2aclhwres) {
		this.l2aclhwres = l2aclhwres;
	}

	public String getL3aclhwres() {
		return l3aclhwres;
	}

	public void setL3aclhwres(String l3aclhwres) {
		this.l3aclhwres = l3aclhwres;
	}

	public String getSchmode() {
		return schmode;
	}

	public void setSchmode(String schmode) {
		this.schmode = schmode;
	}

	public String getVegc() {
		return vegc;
	}

	public void setVegc(String vegc) {
		this.vegc = vegc;
	}

}
