package com.nms.drivechenxiao.service.bean.porteth.uni;

public class UNIObject {
	private String encap = "";// 以太网封装
	private String vlanmode = "";// 以太网VLAN模式
	private String tpid = "";// OUTER VLAN的TPID
	private String pvid = "";// 缺省VLANID
	private String pvpri = "";// 缺省VLAN优先级
	private String sdtpid = "";// 运营商VLAN的TPID
	private String acl = "";// 接口访问控制列表
	private String oclrmode = "";// ETHAC出去的颜色模式(色盲(blind)/信任)) **
	private String iclrmode = "";// ETHAC进来的颜色模式(信任CFI/信任VLAN优先级) **
	private String vlanpri2cng = "";// 输入VLANPRI优先级到颜色的映射
	private String cos2vlanpri = "";// 细分ETHAC出去的流  **
	private String inbwlimit = "";// 入口带宽限制(单位:kbps,步长为64kpbs)
	private String egbwlimit = "";// 出口带宽限制(单位:kbps,步长为64kpbs )
	private String bcastlimit = "";// 广播报文抑制
	private String mcastlimit = "";// 组播报文抑制
	private String dlflimit = "";// 未知单播报文抑制
	private String lptstat = "";// lpt接口状态
	private String lptstatby = "";// lpt状态通知CE的方式
	
	public String toString(){
		StringBuffer s=new StringBuffer().append(" iclrmode=").append(iclrmode)
						.append(" ; oclrmode=").append(oclrmode)
						.append(" ;cos2vlanpri=").append(cos2vlanpri)
						.append(" ;vlanmode=").append(vlanmode)
						.append(" ;tpid =").append(tpid)
						.append(" ;sdtpid=").append(sdtpid)
						.append(" ; encap=").append(encap)
						.append(" ;bcastlimit=").append(bcastlimit)
						.append(" ;egbwlimit=").append(egbwlimit)
						.append(" ; mcastlimit=").append(mcastlimit)
						
						.append(" ;");
		return s.toString();
	}
	public String getEncap() {
		return encap;
	}

	public void setEncap(String encap) {
		this.encap=encap;
	}

	public String getVlanmode() {
		return vlanmode;
	}

	public void setVlanmode(String vlanmode) {
		this.vlanmode = vlanmode;
	}

	public String getTpid() {
		return tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

	public String getPvid() {
		return pvid;
	}

	public void setPvid(String pvid) {
		this.pvid = pvid;
	}

	public String getPvpri() {
		return pvpri;
	}

	public void setPvpri(String pvpri) {
		this.pvpri = pvpri;
	}

	public String getSdtpid() {
		return sdtpid;
	}

	public void setSdtpid(String sdtpid) {
		this.sdtpid = sdtpid;
	}

	public String getAcl() {
		return acl;
	}

	public void setAcl(String acl) {
		this.acl = acl;
	}

	public String getOclrmode() {
		return oclrmode;
	}

	public void setOclrmode(String oclrmode) {
		this.oclrmode = oclrmode;
	}

	public String getIclrmode() {
		return iclrmode;
	}

	public void setIclrmode(String iclrmode) {
		this.iclrmode = iclrmode;
	}

	public String getVlanpri2cng() {
		return vlanpri2cng;
	}

	public void setVlanpri2cng(String vlanpri2cng) {
		this.vlanpri2cng = vlanpri2cng;
	}

	public String getCos2vlanpri() {
		return cos2vlanpri;
	}

	public void setCos2vlanpri(String cos2vlanpri) {
		this.cos2vlanpri = cos2vlanpri;
	}

	public String getInbwlimit() {
		return inbwlimit;
	}

	public void setInbwlimit(String inbwlimit) {
		this.inbwlimit = inbwlimit;
	}

	public String getEgbwlimit() {
		return egbwlimit;
	}

	public void setEgbwlimit(String egbwlimit) {
		this.egbwlimit = egbwlimit;
	}

	public String getBcastlimit() {
		return bcastlimit;
	}

	public void setBcastlimit(String bcastlimit) {
		if(null==bcastlimit||"".equals(bcastlimit)){
			this.bcastlimit = "-1";
		}else{
			this.bcastlimit = bcastlimit;
		}
	}

	public String getMcastlimit() {
		return mcastlimit;
	}

	public void setMcastlimit(String mcastlimit) {
		if(null==mcastlimit||"".equals(mcastlimit)){
			this.mcastlimit = "-1";
		}else{
			this.mcastlimit = mcastlimit;
		}
	}

	public String getDlflimit() {
		return dlflimit;
	}

	public void setDlflimit(String dlflimit) {
		if(null==dlflimit||"".equals(dlflimit)){
			this.dlflimit="-1";
		}else{
			this.dlflimit = dlflimit;
		}
	}

	public String getLptstat() {
		return lptstat;
	}

	public void setLptstat(String lptstat) {
		this.lptstat = lptstat;
	}

	public String getLptstatby() {
		return lptstatby;
	}

	public void setLptstatby(String lptstatby) {
		this.lptstatby = lptstatby;
	}

}
