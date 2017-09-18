package com.nms.drivechenxiao.service.bean.protocols;

import java.util.ArrayList;
import java.util.List;

/**
 * 双规保护bean,目录为 ne/protocols/dual/
 * **/
public class DualObject {
	private String name; //名称 ; [1-4]
	private String desc; //描述 ;txt; 0-16
	private String enaps ; //使能aps ; bool ; ; false
	private String type ; //保护类型 ;  1plus1=0(1+1 aps) ,1to1=1(1:1 aps),1plus1_u=2(1+1_unidirectional Aps) ;1to1
	private String wtrtime ; //等待恢复时间 ; int32 ; [0,720] ; 0
	private String apscmd ; //aps倒换命令 ; txt ; 0-32
	private String dualif ; //双规口 ;ifname
	private String iswork ; //是否工作端口 ; bool ;
	private String passtunel ; //双规组的穿通隧道 ; ifname
	private String swmanner ;//倒换方式 ; closeport=0(倒换时关闭端口,以通知对端),efm=1(倒换时发送3ah事件,以通知对端),closeportforever=2(倒换为备用时,始终关闭端口,以通知对端) ; closeport
	private String pwdrop ; //pw drop on slave dual ne for elan ; bool ; ; true 
	private String ref ; // ; u32 ; ; 0x0
	private String sel ; //保护组选择器 ; ifname ;0-16 ;
	private String clearcnt ; //  ; int32 ; ;0
	private String mspid ; //MSPID ; int32 ; ; 0
	private List<String> tunnels = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getEnaps() {
		return enaps;
	}
	public void setEnaps(String enaps) {
		this.enaps = enaps;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWtrtime() {
		return wtrtime;
	}
	public void setWtrtime(String wtrtime) {
		this.wtrtime = wtrtime;
	}
	public String getApscmd() {
		return apscmd;
	}
	public void setApscmd(String apscmd) {
		this.apscmd = apscmd;
	}
	public String getDualif() {
		return dualif;
	}
	public void setDualif(String dualif) {
		this.dualif = dualif;
	}
	public String getIswork() {
		return iswork;
	}
	public void setIswork(String iswork) {
		this.iswork = iswork;
	}
	public String getPasstunel() {
		return passtunel;
	}
	public void setPasstunel(String passtunel) {
		this.passtunel = passtunel;
	}
	public String getSwmanner() {
		return swmanner;
	}
	public void setSwmanner(String swmanner) {
		this.swmanner = swmanner;
	}
	public String getPwdrop() {
		return pwdrop;
	}
	public void setPwdrop(String pwdrop) {
		this.pwdrop = pwdrop;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	public String getClearcnt() {
		return clearcnt;
	}
	public void setClearcnt(String clearcnt) {
		this.clearcnt = clearcnt;
	}
	public String getMspid() {
		return mspid;
	}
	public void setMspid(String mspid) {
		this.mspid = mspid;
	}
	public List<String> getTunnels() {
		return tunnels;
	}
	public void setTunnels(List<String> tunnels) {
		this.tunnels = tunnels;
	}
	
	
	
}
