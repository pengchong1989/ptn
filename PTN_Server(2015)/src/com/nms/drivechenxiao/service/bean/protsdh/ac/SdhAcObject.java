package com.nms.drivechenxiao.service.bean.protsdh.ac;

import com.nms.drivechenxiao.service.bean.pwpdh.service.CesServiceObject;

public class SdhAcObject {
	CesServiceObject cesServiceObject = new CesServiceObject();

	private String name = "";// 名称
	private String admin = "";// 接口管理状态
	private String ifname = "";//接口名            
	private String ifindex = "";//接口索引值        
	private String desc = "";//接口描述          
	private String oper = "";//接口工作状态      
	private String perprofile = "";//性能profile名字   
	private String ref = "";//                  
	private String expectj2 = "";//期望接收到J2字节  
	private String sendj2 = "";//发送的J2字节      
	private String receivedj2 = "";//实际接收到的J2字节
	private String checkj2 = "";//是否检查J2字节    
	private String expectv5 = "";//期望接收到V5字节  
	private String sendv5 = "";//发送的V5字节      
	private String receivedv5 = "";//实际接收到的V5字节
	private String checkv5 = "";//是否检查v5字节    
	private String sdhifindex = "";//                  
	private String label = "";//                  
	private String lblhwres = "";//                  
	/**
	 * 修改业务 (eline，etree,elan)
	 *  identify  原来的名称-与name相对应
	 * 判断  端口 是否修改
	 */
	private String identify;
	
	public String toString(){
		return new StringBuffer().append(" name=").append(name)
		.append(" ;admin=").append(admin).append(" ;ifname=").append(ifname)
		.append(" ;ifindex=").append(ifindex).append(" ;desc=").append(desc)
		.append(" ;oper=").append(oper).append(" ;perprofile=").append(perprofile)
		.append(" ;ref=").append(ref).append(" ;expectj2=").append(expectj2)
		.append(" ;sendj2=").append(sendj2).append(" ;receivedj2=").append(receivedj2)
		.append(" ;checkj2=").append(checkj2).append(" ;expectv5=").append(expectv5)
		.append(" ;sendv5=").append(sendv5).append(" ;receivedv5=").append(receivedv5)
		.append(" ;checkv5=").append(checkv5).append(" ;sdhifindex=").append(sdhifindex)
		.append(" ;label=").append(label).append(" ;lblhwres=").append(lblhwres).toString();
	}
	
	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
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

	public String getExpectj2() {
		return expectj2;
	}

	public void setExpectj2(String expectj2) {
		this.expectj2 = expectj2;
	}

	public String getSendj2() {
		return sendj2;
	}

	public void setSendj2(String sendj2) {
		this.sendj2 = sendj2;
	}

	public String getReceivedj2() {
		return receivedj2;
	}

	public void setReceivedj2(String receivedj2) {
		this.receivedj2 = receivedj2;
	}

	public String getCheckj2() {
		return checkj2;
	}

	public void setCheckj2(String checkj2) {
		this.checkj2 = checkj2;
	}

	public String getExpectv5() {
		return expectv5;
	}

	public void setExpectv5(String expectv5) {
		this.expectv5 = expectv5;
	}

	public String getSendv5() {
		return sendv5;
	}

	public void setSendv5(String sendv5) {
		this.sendv5 = sendv5;
	}

	public String getReceivedv5() {
		return receivedv5;
	}

	public void setReceivedv5(String receivedv5) {
		this.receivedv5 = receivedv5;
	}

	public String getCheckv5() {
		return checkv5;
	}

	public void setCheckv5(String checkv5) {
		this.checkv5 = checkv5;
	}

	public String getSdhifindex() {
		return sdhifindex;
	}

	public void setSdhifindex(String sdhifindex) {
		this.sdhifindex = sdhifindex;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLblhwres() {
		return lblhwres;
	}

	public void setLblhwres(String lblhwres) {
		this.lblhwres = lblhwres;
	}

	public CesServiceObject getCesServiceObject() {
		return cesServiceObject;
	}

	public void setCesServiceObject(CesServiceObject cesServiceObject) {
		this.cesServiceObject = cesServiceObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
