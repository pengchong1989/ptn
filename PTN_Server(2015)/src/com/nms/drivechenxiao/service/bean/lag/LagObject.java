package com.nms.drivechenxiao.service.bean.lag;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.AFObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.BEObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.CSObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.EFObject;
import com.nms.drivechenxiao.service.bean.porteth.uni.UNIObject;

public class LagObject {
	private OamObject oam = new OamObject();
	private NNIObject nni = new NNIObject();
	private UNIObject uni = new UNIObject();
	private CSObject cs3 = new CSObject();
	private CSObject cs6 = new CSObject();
	private CSObject cs7 = new CSObject();
	private BEObject be = new BEObject();
	private AFObject af1 = new AFObject();
	private AFObject af2 = new AFObject();
	private AFObject af3 = new AFObject();
	private AFObject af4 = new AFObject();
	private EFObject ef = new EFObject();
	private LagMember menber = new LagMember();
	private List<AcObject> acList = new ArrayList<AcObject>();

	private String name = "";
	private String iused = ""; //当前输入使用带宽            
	private String oused = ""; //当前输出使用带宽                  
	private String ifname = ""; //接口名                            
	private String ifindex = ""; //接口索引值                        
	private String desc = ""; //接口描述                          
	private String admin = ""; //接口管理状态                      
	private String oper = ""; //接口工作状态                      
	private String ref = ""; //                                  
	private String framelen = ""; //最大帧长                          
	private String arpproto_tocpu = ""; //arp PROTOCOL 提交到CPU控制        
	private String dhcpproto_tocpu = "";//dhcp PROTOCOL 提交到CPU控制       
	private String mac = ""; //本地MAC地址                       
	private String role = ""; //接口角色(uni|nni)         	privat
	private String type = ""; //                                  
	private String isolation = ""; //端口隔离组                        
	private String portac = ""; //                                  
	private String permitpktloop = ""; //是否允许报文环回                  
	private String schmode = ""; //端口队列调度模式                  
	private String psc = ""; //链路聚合报文分发算法              
	private String work = ""; //工作链路聚合成员端口              
	private String active = ""; //                                  
	private String dualid = ""; //                                  
	private String recover = ""; //1to1 恢复式还是非恢复式           

	public String toString(){
		StringBuffer sb = new StringBuffer().append(" name=").append(name)
		.append(" ;iused=").append(iused).append(" ;oused=").append(oused)
		.append(" ;ifname=").append(ifname).append(" ;ifindex=").append(ifindex)
		.append(" ;desc=").append(desc).append(" ;admin=").append(admin)
		.append(" ;oper=").append(oper).append(" ;ref=").append(ref)
		.append(" ;framelen=").append(framelen).append(" ;arpproto_tocpu=").append(arpproto_tocpu)
		.append(" ;dhcpproto_tocpu=").append(dhcpproto_tocpu).append(" ;mac=").append(mac)
		.append(" ;role=").append(role).append(" ;type=").append(type)
		.append(" ;isolation=").append(isolation).append(" ;portac=").append(portac)
		.append(" ;permitpktloop=").append(permitpktloop).append(" ;schmode=").append(schmode)
		.append(" ;psc=").append(psc).append(" ;work=").append(work)
		.append(" ;active=").append(active).append(" ;dualid=").append(dualid)
		.append(" ;recover=").append(recover).append(" ;oam={").append(oam.toString())
		.append("} ; nni={").append(nni.toString()).append("} ; uni=").append(uni)
		.append(" } ; cs3={").append(cs3.toString()).append(" } ; cs6={").append(cs6.toString())
		.append("} ; cs7={").append(cs7.toString()).append(" } ; be={").append(be.toString())
		.append("} ; af1={").append(af1.toString()).append(" } ; af2={").append(af2.toString())
		.append("} ; af3={").append(af3.toString()).append(" } ; af4={").append(af4.toString())
		.append("} ; ef={").append(ef.toString()).append(" }");
		return sb.toString();
	}
	public LagMember getMenber() {
		return menber;
	}

	public void setMenber(LagMember menber) {
		this.menber = menber;
	}

	public OamObject getOam() {
		return oam;
	}

	public void setOam(OamObject oam) {
		this.oam = oam;
	}

	public List<AcObject> getAcList() {
		return acList;
	}

	public void setAcList(List<AcObject> acList) {
		this.acList = acList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIused() {
		return iused;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getFramelen() {
		return framelen;
	}

	public void setFramelen(String framelen) {
		this.framelen = framelen;
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

	public String getSchmode() {
		return schmode;
	}

	public void setSchmode(String schmode) {
		this.schmode = schmode;
	}

	public String getPsc() {
		return psc;
	}

	public void setPsc(String psc) {
		this.psc = psc;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDualid() {
		return dualid;
	}

	public void setDualid(String dualid) {
		this.dualid = dualid;
	}

	public String getRecover() {
		return recover;
	}

	public void setRecover(String recover) {
		this.recover = recover;
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

	public CSObject getCs3() {
		return cs3;
	}

	public void setCs3(CSObject cs3) {
		this.cs3 = cs3;
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

}
