package com.nms.drivechenxiao.service.bean.pweth;

import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ELanServiceObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ETreeServiceObject;
import com.nms.drivechenxiao.service.bean.pweth.service.ElineServiceObject;

public class PwEthObject {

	private ElineServiceObject eline = new ElineServiceObject();
	private ELanServiceObject elan = new ELanServiceObject();
	private ETreeServiceObject etree = new ETreeServiceObject();
	private OamObject oam = new OamObject();

	private String name = "";// 名称
	private String ifname = "";// 接口名
	private String ifindex = "";// 接口索引值
	private String desc = "";// 接口描述
	private String admin = "";// 接口管理状态
	private String oper = "";// 接口工作状态
	private String perprofile = "";// 性能profile名字
	private String ref = "";
	private String peer = "";// PW 对端网元ID **
	private String type = "";
	private String owner = "";// PW创建者
	private String inlabel = "";// PW 输入标签 **
	private String outlabel = "";// PW 输出标签 **
	private String carrierif = "";// PW 承载层接口 Tunnel **
	private String action = "";// VLAN动作
	private String sdvlan = "";// 区分业务的VLAN ID
	private String sdvlanpri = "";// 区分业务的VLAN 优先级
	private String tpid = "";// 区分业务的VLAN TPID
	private String qos = "";// 以太网PW的QoS 类型(elsp|eelsp|llsp) **
	private String lspqostype = "";
	private String qoshwres = "";
	private String lblhwres = "";
	private String lptstat = "";// lpt接口状态
	private boolean deleteQos;
	private boolean isCreateQos=false;	//创建tunnel时，是否创建qos true创建  false不创建
	/**
	 * 修改业务 (eline，etree,elan)
	 * identify  原来的名称-与name相对应
	 * 判断   pw 是否修改
	 */
	private String identify="";


	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public boolean isDeleteQos() {
		return deleteQos;
	}

	public void setDeleteQos(boolean deleteQos) {
		this.deleteQos = deleteQos;
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

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getInlabel() {
		return inlabel;
	}

	public void setInlabel(String inlabel) {
		this.inlabel = inlabel;
	}

	public String getOutlabel() {
		return outlabel;
	}

	public void setOutlabel(String outlabel) {
		this.outlabel = outlabel;
	}

	public String getCarrierif() {
		return carrierif;
	}

	public void setCarrierif(String carrierif) {
		this.carrierif = carrierif;
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

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public String getLspqostype() {
		return lspqostype;
	}

	public void setLspqostype(String lspqostype) {
		this.lspqostype = lspqostype;
	}

	public String getQoshwres() {
		return qoshwres;
	}

	public void setQoshwres(String qoshwres) {
		this.qoshwres = qoshwres;
	}

	public String getLblhwres() {
		return lblhwres;
	}

	public void setLblhwres(String lblhwres) {
		this.lblhwres = lblhwres;
	}

	public String getLptstat() {
		return lptstat;
	}

	public void setLptstat(String lptstat) {
		this.lptstat = lptstat;
	}

	public boolean isCreateQos() {
		return isCreateQos;
	}

	public void setCreateQos(boolean isCreateQos) {
		this.isCreateQos = isCreateQos;
	}
}
