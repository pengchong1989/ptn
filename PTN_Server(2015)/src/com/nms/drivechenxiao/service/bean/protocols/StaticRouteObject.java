package com.nms.drivechenxiao.service.bean.protocols;

public class StaticRouteObject {
	private String name; //名字; prefix
	private String nexthop;//ipaddr ; 0.0.0.0 //下一跳的ip
	private String ifname; //txt ; //静态路由指向接口
	private String distance ; //int32 ;[0,255] //静态路由的距离值
	/**
	 * prefix : a.b.c.d\masklen 即 name=目标网络\掩码位数
	 * 传输保存形式为 AA.BB.CC.DD\MM 对应于0xMMAABBCCDD
	 * **/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNexthop() {
		return nexthop;
	}
	public void setNexthop(String nexthop) {
		this.nexthop = nexthop;
	}
	public String getIfname() {
		return ifname;
	}
	public void setIfname(String ifname) {
		this.ifname = ifname;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
