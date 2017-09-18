package com.nms.drivechenxiao.service.bean.perprofile;

public class EthPerObject {
	private String name;
	private String type;//ETH
	private String tp64;//发送包数长度64字节
	private String tp65to127;//
	private String tp128to255;
	private String tp256to511;
	private String tp512to1023;
	private String tp1024to1518;
	private String tpoversize;
	private String rp64;//接收包数据长度64字节
	private String rp65to127;
	private String rp128to255;
	private String rp256to511;
	private String rp512to1023;
	private String rp1024to1518;
	private String rpoversize;//接收包长度大于1518
	private String tpuc;//发送单播包数
	private String tpmc;//发送组播包数
	private String tpbc;//发送广播包数
	private String rpuc;//接收单播包数
	private String rpmc;
	private String rpbc;
	private String tpok;//发送的好包数
	private String tpall;//发送字节总数
	private String rpok;//接收好包数
	private String roall;//接收总字节数
	private String rpbad;//接收怀包数
	private String tpbad;
	private String fcserr;//校验错误数
	private String dropratio;//丢包率
	private String latency;//时延
	private String jitter;//时延变化
	private String iop;//光接受功率
	private String oop;//光发送功率
	private String lb;//激光器偏置电流
	private String lt;//激光器温度
	private String ref;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTp64() {
		return tp64;
	}
	public void setTp64(String tp64) {
		this.tp64 = tp64;
	}
	public String getTp65to127() {
		return tp65to127;
	}
	public void setTp65to127(String tp65to127) {
		this.tp65to127 = tp65to127;
	}
	public String getTp128to255() {
		return tp128to255;
	}
	public void setTp128to255(String tp128to255) {
		this.tp128to255 = tp128to255;
	}
	public String getTp256to511() {
		return tp256to511;
	}
	public void setTp256to511(String tp256to511) {
		this.tp256to511 = tp256to511;
	}
	public String getTp512to1023() {
		return tp512to1023;
	}
	public void setTp512to1023(String tp512to1023) {
		this.tp512to1023 = tp512to1023;
	}
	public String getTp1024to1518() {
		return tp1024to1518;
	}
	public void setTp1024to1518(String tp1024to1518) {
		this.tp1024to1518 = tp1024to1518;
	}
	public String getTpoversize() {
		return tpoversize;
	}
	public void setTpoversize(String tpoversize) {
		this.tpoversize = tpoversize;
	}
	public String getRp64() {
		return rp64;
	}
	public void setRp64(String rp64) {
		this.rp64 = rp64;
	}
	public String getRp65to127() {
		return rp65to127;
	}
	public void setRp65to127(String rp65to127) {
		this.rp65to127 = rp65to127;
	}
	public String getRp128to255() {
		return rp128to255;
	}
	public void setRp128to255(String rp128to255) {
		this.rp128to255 = rp128to255;
	}
	public String getRp256to511() {
		return rp256to511;
	}
	public void setRp256to511(String rp256to511) {
		this.rp256to511 = rp256to511;
	}
	public String getRp512to1023() {
		return rp512to1023;
	}
	public void setRp512to1023(String rp512to1023) {
		this.rp512to1023 = rp512to1023;
	}
	public String getRp1024to1518() {
		return rp1024to1518;
	}
	public void setRp1024to1518(String rp1024to1518) {
		this.rp1024to1518 = rp1024to1518;
	}
	public String getRpoversize() {
		return rpoversize;
	}
	public void setRpoversize(String rpoversize) {
		this.rpoversize = rpoversize;
	}
	public String getTpuc() {
		return tpuc;
	}
	public void setTpuc(String tpuc) {
		this.tpuc = tpuc;
	}
	public String getTpmc() {
		return tpmc;
	}
	public void setTpmc(String tpmc) {
		this.tpmc = tpmc;
	}
	public String getTpbc() {
		return tpbc;
	}
	public void setTpbc(String tpbc) {
		this.tpbc = tpbc;
	}
	public String getRpuc() {
		return rpuc;
	}
	public void setRpuc(String rpuc) {
		this.rpuc = rpuc;
	}
	public String getRpmc() {
		return rpmc;
	}
	public void setRpmc(String rpmc) {
		this.rpmc = rpmc;
	}
	public String getRpbc() {
		return rpbc;
	}
	public void setRpbc(String rpbc) {
		this.rpbc = rpbc;
	}
	public String getTpok() {
		return tpok;
	}
	public void setTpok(String tpok) {
		this.tpok = tpok;
	}
	public String getTpall() {
		return tpall;
	}
	public void setTpall(String tpall) {
		this.tpall = tpall;
	}
	public String getRpok() {
		return rpok;
	}
	public void setRpok(String rpok) {
		this.rpok = rpok;
	}
	public String getRoall() {
		return roall;
	}
	public void setRoall(String roall) {
		this.roall = roall;
	}
	public String getRpbad() {
		return rpbad;
	}
	public void setRpbad(String rpbad) {
		this.rpbad = rpbad;
	}
	public String getTpbad() {
		return tpbad;
	}
	public void setTpbad(String tpbad) {
		this.tpbad = tpbad;
	}
	public String getFcserr() {
		return fcserr;
	}
	public void setFcserr(String fcserr) {
		this.fcserr = fcserr;
	}
	public String getDropratio() {
		return dropratio;
	}
	public void setDropratio(String dropratio) {
		this.dropratio = dropratio;
	}
	public String getLatency() {
		return latency;
	}
	public void setLatency(String latency) {
		this.latency = latency;
	}
	public String getJitter() {
		return jitter;
	}
	public void setJitter(String jitter) {
		this.jitter = jitter;
	}
	public String getIop() {
		return iop;
	}
	public void setIop(String iop) {
		this.iop = iop;
	}
	public String getOop() {
		return oop;
	}
	public void setOop(String oop) {
		this.oop = oop;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getLt() {
		return lt;
	}
	public void setLt(String lt) {
		this.lt = lt;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	
	
}
