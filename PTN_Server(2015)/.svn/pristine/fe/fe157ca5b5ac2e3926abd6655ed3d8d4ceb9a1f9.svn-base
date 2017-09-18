package com.nms.drivechenxiao.service.bean.porteth.ac;

import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ELanServiceObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ETreeServiceObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ElineServiceObject;

public class AcObject {

	private ElineServiceObject eline = new ElineServiceObject();
	private ELanServiceObject elan = new ELanServiceObject();
	private ETreeServiceObject etree = new ETreeServiceObject();
	private OamObject oam = new OamObject();

	private String name = "";// 名称 **
	private String ethacout = "";
	private String ifname = "";// 接口名 **
	private String ifindex = "";// 接口索引值
	private String desc = "";// 接口描述
	private String admin = "";// 接口管理状态
	private String oper = "";// 接口工作状态
	private String perprofile = "";// 性能profile名字
	private String ref = "";//	
	private String l3iifhwres = "";//
	private String mode = "";// ETHAC 模式 端口模式值'port' vlan模式值'port_plus_spvlan'
	private String spvlan = "";// 运营商VLANID **
	private String cevlan = "";// 客户VLANID
	private String vthwres = "";//	
	private String action = "";// 当报文从AC出去时VLAN动作
	private String sdvlan = "";// 业务区分的VLANID
	private String sdvlanpri = "";// 业务区分的VLAN 优先级
	private String sdvlancfi = "";// 业务区分的VLAN CFI
	private String ethifindex = "";//		
	private String qoshwres = "";//	
	private String qos = "";// pmap名 **
	private String hwres = "";//
	private String hwresref = "";//	
	private String dualid = "";// 双归保护组ID
	private boolean deleteQos;
	private boolean isCreateQos=false;	//创建tunnel时，是否创建qos true创建  false不创建
	/**
	 * 修改业务 (eline，etree,elan)
	 *  identify  原来的名称-与name相对应
	 * 判断   ac是否修改
	 */
	private String identify="";

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String toString(){
		StringBuffer sb=new StringBuffer().append(" name=").append(name)
		.append(" ;ethacout=").append(ethacout).append(" ;ifname=").append(ifname)
		.append(" ;ifindex=").append(ifindex).append(" ;desc=").append(desc)
		.append(" ;admin=").append(admin).append(" ;oper=").append(oper)
		.append(" ;perprofile=").append(perprofile).append(" ;ref=").append(ref)
		.append(" :l3iifhwres=").append(l3iifhwres).append(" ;mode=").append(mode)
		.append(" ;spvlan=").append(spvlan).append(" ;cevlan=").append(cevlan)
		.append(" ;vthwres=").append(vthwres).append(" :action=").append(action)
		.append(" ;sdvlan=").append(sdvlan).append(" ;sdvlanpri=").append(sdvlanpri)
		.append(" ;sdvlancfi=").append(sdvlancfi).append(" ;ethifindex=").append(ethifindex)
		.append(" ;qoshwres=").append(qoshwres).append(" ;qos=").append(qos)
		.append(" ;hwres=").append(hwres).append(" ;hwresref=").append(hwresref)
		.append(" ;dualid=").append(dualid).append(" ;deleteQos=").append(deleteQos)
		.append(" ;eline={").append(eline.toString()).append("} ;elan={").append(elan.toString())
		.append("} ;etree={").append(etree.toString()).append(" } ;oam={").append(oam.toString()).append("} ");
		return sb.toString();
	}
	

	public boolean isDeleteQos() {
		return deleteQos;
	}

	public void setDeleteQos(boolean deleteQos) {
		this.deleteQos = deleteQos;
	}

	public String getEthacout() {
		return ethacout;
	}

	public void setEthacout(String ethacout) {
		this.ethacout = ethacout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElineServiceObject getEline() {
		return eline;
	}

	public void setEline(ElineServiceObject eline) {
		this.eline = eline;
	}

	public ELanServiceObject getElan() {
		return elan;
	}

	public void setElan(ELanServiceObject elan) {
		this.elan = elan;
	}

	public ETreeServiceObject getEtree() {
		return etree;
	}

	public void setEtree(ETreeServiceObject etree) {
		this.etree = etree;
	}

	public OamObject getOam() {
		return oam;
	}

	public void setOam(OamObject oam) {
		this.oam = oam;
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

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getL3iifhwres() {
		return l3iifhwres;
	}

	public void setL3iifhwres(String l3iifhwres) {
		this.l3iifhwres = l3iifhwres;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSpvlan() {
		return spvlan;
	}

	public void setSpvlan(String spvlan) {
		this.spvlan = spvlan;
	}

	public String getCevlan() {
		return cevlan;
	}

	public void setCevlan(String cevlan) {
		this.cevlan = cevlan;
	}

	public String getVthwres() {
		return vthwres;
	}

	public void setVthwres(String vthwres) {
		this.vthwres = vthwres;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSdvlan() {
		return sdvlan;
	}

	public void setSdvlan(String sdvlan) {
		this.sdvlan = sdvlan;
	}

	public String getSdvlanpri() {
		return sdvlanpri;
	}

	public void setSdvlanpri(String sdvlanpri) {
		this.sdvlanpri = sdvlanpri;
	}

	public String getSdvlancfi() {
		return sdvlancfi;
	}

	public void setSdvlancfi(String sdvlancfi) {
		this.sdvlancfi = sdvlancfi;
	}

	public String getEthifindex() {
		return ethifindex;
	}

	public void setEthifindex(String ethifindex) {
		this.ethifindex = ethifindex;
	}

	public String getQoshwres() {
		return qoshwres;
	}

	public void setQoshwres(String qoshwres) {
		this.qoshwres = qoshwres;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public String getHwres() {
		return hwres;
	}

	public void setHwres(String hwres) {
		this.hwres = hwres;
	}

	public String getHwresref() {
		return hwresref;
	}

	public void setHwresref(String hwresref) {
		this.hwresref = hwresref;
	}

	public String getDualid() {
		return dualid;
	}

	public void setDualid(String dualid) {
		this.dualid = dualid;
	}

	public boolean isCreateQos() {
		return isCreateQos;
	}

	public void setCreateQos(boolean isCreateQos) {
		this.isCreateQos = isCreateQos;
	}

}
