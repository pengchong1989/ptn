package com.nms.drivechenxiao.service.bean.acn;

public class ACNObject {
	private String ifname ;
	private int ifindex=0;
	private String desc="ptn";
	private String admin = "up"; //down=0 ; up=1 ; test=2 
	private String oper ="up";//admindown=4 ; // 比特0,代表tsf;比特1,代表linkdown;比特2,代表admindown;比特3,代表notPresent;比特5,代表datalinkdown;所有bit为0代表up
	private int ref ;
	private String peer;
	private String active;
	private OSPF ospf;
	private String[] datalink;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getDatalink() {
		return datalink;
	}
	public void setDatalink(String[] datalink) {
		this.datalink = datalink;
	}
	public OSPF getOspf() {
		return ospf;
	}
	public void setOspf(OSPF ospf) {
		this.ospf = ospf;
	}
	public String getIfname() {
		return ifname;
	}
	public void setIfname(String ifname) {
		this.ifname = ifname;
	}
	public int getIfindex() {
		return ifindex;
	}
	public void setIfindex(int ifindex) {
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
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getPeer() {
		return peer;
	}
	public void setPeer(String peer) {
		this.peer = peer;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}
