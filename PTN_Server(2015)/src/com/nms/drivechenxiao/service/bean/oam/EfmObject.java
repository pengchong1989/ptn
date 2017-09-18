package com.nms.drivechenxiao.service.bean.oam;
/**oam管理里面的 eth 链路 oam管理里面的 对应节点
 * **/
public class EfmObject {
	private boolean isOAM = false; //是否是oam
	private String portname; //端口名
	private String enable ; //使能 ; bool ; ; false
	private String errsymbperiod ; //错误信号周期 ; u32;[125000000,4294967295];0x7735940
	private String errsymbthreshold ;//错误信号门限 ;u52;[1,7500000000] ; 1
	private String errfrmperiod ; //错误帧周期 ;int32 ;[1,60] ; 1
	private String errfrmthreshold ;//错误帧门限 ;u32 ;[1,4294967295]; 0x1
	private String errfrmperiodperiond ; //错误帧周期 的周期;int32 ;[14880000,892800000] ;14880000
	private String errfrmperiondthreshold ;//错误帧周期的门限 ;u32 ;[1,4294967295]; 0x1
	private String efffrmsecondsperiod ;//误帧秒周期 ;int32 ;[10,900]; 60
	private String workmode ;//工作模式 ; passive=0,active=1 ;active=1
	private String errfrmsecondsthreshold ;//误帧秒门限 ;int32 ;[1,900] ;1
	private String lpbtimeout ; //回环超时时间 ; int32 ;[2,5] ;3
	private String unidir ; //是否支持单向收发 ; bool; ;false
	private String rmtloopback ; //是否支持远端环回 ;bool ; ;false
	private String linkevent ;//是否支持解释链路事件协议包 ;bool ; ;true
	private String varretr ;//是否支持响应变量查询协议包 ;bool ; ;false
	private String maxoampdu ; //oam最大包长 ;int32 ;[64,1522]; 1518
	private String oui ; //组织唯一标识 ;int32 ;[0,16777215];0
	private String vsi ;//厂商相关信息 ; u32 ; ;0x0
	private String lclmuxaction ;//本端mux状态 ; fwd=0,discard=1 ;fwd=0
	private String lclparseraction ;//本端parser状态 ;fwd=0,loopback=1,discard=2 ;fwd=0
	private String rmtmuxaction ; //远端ux状态 ;  fwd=0,discard=1 ;fwd=0
	private String rmtparseraction ;//远端parser状态 ;fwd=0,loopback=1,discard=2 ;fwd=0
	private String rmtworkmode ; //远端工作模式; passive=0,active=1 ;active=1
	private String validoammtu ;//生效的oammtu ;int32 ; ; 0
	private String discoverystat ;//oam发现状态 ;fault=0,actsendlcl=1,passivewait=2,sendlclrmt=3,sendlclrmtok=4,sendany=5,sendextinfo=6,sendextinfoack=7,sendanynoext=8 ;fault=0
	private String lpbstat ;//环回状态 ;nolpb=0,waitlpbrsp=1,lpb=2,rmtlpb=3,waitnolpbrsp=4;
	private String frequecy ;//发包周期 ; 100ms=0,1s=1 ;1
	private String losttime ;//linkfail 周期 ; int32;[2,5] ;5
	
	
	public boolean getisOAM() {
		return isOAM;
	}
	public void setisOAM(boolean isOAM) {
		this.isOAM = isOAM;
	}
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getErrsymbperiod() {
		return errsymbperiod;
	}
	public void setErrsymbperiod(String errsymbperiod) {
		this.errsymbperiod = errsymbperiod;
	}
	public String getErrsymbthreshold() {
		return errsymbthreshold;
	}
	public void setErrsymbthreshold(String errsymbthreshold) {
		this.errsymbthreshold = errsymbthreshold;
	}
	public String getErrfrmperiod() {
		return errfrmperiod;
	}
	public void setErrfrmperiod(String errfrmperiod) {
		this.errfrmperiod = errfrmperiod;
	}
	public String getErrfrmthreshold() {
		return errfrmthreshold;
	}
	public void setErrfrmthreshold(String errfrmthreshold) {
		this.errfrmthreshold = errfrmthreshold;
	}
	public String getErrfrmperiodperiond() {
		return errfrmperiodperiond;
	}
	public void setErrfrmperiodperiond(String errfrmperiodperiond) {
		this.errfrmperiodperiond = errfrmperiodperiond;
	}
	public String getErrfrmperiondthreshold() {
		return errfrmperiondthreshold;
	}
	public void setErrfrmperiondthreshold(String errfrmperiondthreshold) {
		this.errfrmperiondthreshold = errfrmperiondthreshold;
	}
	public String getEfffrmsecondsperiod() {
		return efffrmsecondsperiod;
	}
	public void setEfffrmsecondsperiod(String efffrmsecondsperiod) {
		this.efffrmsecondsperiod = efffrmsecondsperiod;
	}
	public String getWorkmode() {
		return workmode;
	}
	public void setWorkmode(String workmode) {
		this.workmode = workmode;
	}
	public String getErrfrmsecondsthreshold() {
		return errfrmsecondsthreshold;
	}
	public void setErrfrmsecondsthreshold(String errfrmsecondsthreshold) {
		this.errfrmsecondsthreshold = errfrmsecondsthreshold;
	}
	public String getLpbtimeout() {
		return lpbtimeout;
	}
	public void setLpbtimeout(String lpbtimeout) {
		this.lpbtimeout = lpbtimeout;
	}
	public String getUnidir() {
		return unidir;
	}
	public void setUnidir(String unidir) {
		this.unidir = unidir;
	}
	public String getRmtloopback() {
		return rmtloopback;
	}
	public void setRmtloopback(String rmtloopback) {
		this.rmtloopback = rmtloopback;
	}
	public String getLinkevent() {
		return linkevent;
	}
	public void setLinkevent(String linkevent) {
		this.linkevent = linkevent;
	}
	public String getVarretr() {
		return varretr;
	}
	public void setVarretr(String varretr) {
		this.varretr = varretr;
	}
	public String getMaxoampdu() {
		return maxoampdu;
	}
	public void setMaxoampdu(String maxoampdu) {
		this.maxoampdu = maxoampdu;
	}
	public String getOui() {
		return oui;
	}
	public void setOui(String oui) {
		this.oui = oui;
	}
	public String getVsi() {
		return vsi;
	}
	public void setVsi(String vsi) {
		this.vsi = vsi;
	}
	public String getLclmuxaction() {
		return lclmuxaction;
	}
	public void setLclmuxaction(String lclmuxaction) {
		this.lclmuxaction = lclmuxaction;
	}
	public String getLclparseraction() {
		return lclparseraction;
	}
	public void setLclparseraction(String lclparseraction) {
		this.lclparseraction = lclparseraction;
	}
	public String getRmtmuxaction() {
		return rmtmuxaction;
	}
	public void setRmtmuxaction(String rmtmuxaction) {
		this.rmtmuxaction = rmtmuxaction;
	}
	public String getRmtparseraction() {
		return rmtparseraction;
	}
	public void setRmtparseraction(String rmtparseraction) {
		this.rmtparseraction = rmtparseraction;
	}
	public String getRmtworkmode() {
		return rmtworkmode;
	}
	public void setRmtworkmode(String rmtworkmode) {
		this.rmtworkmode = rmtworkmode;
	}
	public String getValidoammtu() {
		return validoammtu;
	}
	public void setValidoammtu(String validoammtu) {
		this.validoammtu = validoammtu;
	}
	public String getDiscoverystat() {
		return discoverystat;
	}
	public void setDiscoverystat(String discoverystat) {
		this.discoverystat = discoverystat;
	}
	public String getLpbstat() {
		return lpbstat;
	}
	public void setLpbstat(String lpbstat) {
		this.lpbstat = lpbstat;
	}
	public String getFrequecy() {
		return frequecy;
	}
	public void setFrequecy(String frequecy) {
		this.frequecy = frequecy;
	}
	public String getLosttime() {
		return losttime;
	}
	public void setLosttime(String losttime) {
		this.losttime = losttime;
	}

	
}
