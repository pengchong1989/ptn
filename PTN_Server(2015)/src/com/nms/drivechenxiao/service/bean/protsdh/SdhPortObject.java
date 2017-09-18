package com.nms.drivechenxiao.service.bean.protsdh;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;

public class SdhPortObject {
	private String name = "";// 名称 **
	private String admin = "";// 使能 **
	private String ifname = "";//接口名              
	private String ifindex = "";//接口索引值          
	private String desc = "";//接口描述            
	private String oper = "";//接口工作状态        
	private String perprofile = "";//性能profile名字     
	private String als = "";//ALS使能             
	private String alsdelay = "";//ALS激光器关闭时长   
	private String alsshtup = "";//ALS激光器开启时长   
	private String ref = "";//                    
	private String sfptype = "";//SFP实际类型         
	private String sfpvendor = "";//SFP厂家信息         
	private String wavelength = "";//波长                
	private String sfpexptype = "";//期望SFP类型         
	private String dnugroup = "";//DNU组               
	private String ssmoutputenable = "";//SSM输出使能         
	private String type = "";//SDH接口类型         
	private String j0mode = "";//J0字节模式          
	private String expectj0 = "";//期望接收的J0字节    
	private String sendj0 = "";//发送的J0字节        
	private String receivedj0 = "";//实际接收的J0字节    
	private String checkj0 = "";//是否检查J0字节      
	private String loopback = "";// 环回模式：0 normae（正常-解环回） ;1 internal(数据流向设备内环回)；2 external(数据流向设备外环回)                   
	private String regsdthr = "";//再生段信号裂化门限  
	private String regsfthr = "";//再生段信号失效门限  
	private String mssdthr = "";//复用段信号裂化门限  
	private String mssfthr = "";//复用段信号失效门限  
	private String mspid = "";//复用段保护组ID      
	private String j1mode = "";//J1字节模式          
	private String expectj1 = "";//期望接收的J1字节    
	private String sendj1 = "";//发送的J1字节        
	private String receivedj1 = "";//实际接收的J1字节    
	private String checkj1 = "";//是否检查J1字节      
	private String expectc2 = "";//期望接收的C2字节    
	private String sendc2 = "";//发送的C2字节        
	private String checkc2 = "";//是否检查C2字节      
	private String receivedc2 = "";//实际接收的C2字节    
	private String hpsdthr = "";//高阶通道信号劣化门限
	private String hpsfthr = "";//高阶通道信号失效门限
	private String switchport = "";//                    
	private List<SdhAcObject> sdhAcList = new ArrayList<SdhAcObject>();
	/**
	 * 修改业务 (ces)
	 *  identify  原来的名称-与name相对应
	 * 判断  端口 是否修改
	 */
	private String identify;

	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String toString(){
		return new StringBuffer().append(" name=").append(name)
		.append(" ;admin=").append(admin).append(" ;ifname=").append(ifname)
		.append(" ;ifindex=").append(ifindex).append(" ;desc=").append(desc)
		.append(" ;oper=").append(oper).append(" ;perprofile=").append(perprofile)
		.append(" ;als=").append(als).append(" ;ref=").append(ref)
		.append(" ;sfptype=").append(sfptype).append(" ;sfpvendor=").append(sfpvendor)
		.append(" ;wavelength=").append(wavelength).append(" ;sfpexptype=").append(sfpexptype)
		.append(" ;dnugroup=").append(dnugroup).append(" ;type").append(type)
		.append(" ;sdhAcList.size() = ").append(sdhAcList.size()).toString();
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

	public String getAls() {
		return als;
	}

	public void setAls(String als) {
		this.als = als;
	}

	public String getAlsdelay() {
		return alsdelay;
	}

	public void setAlsdelay(String alsdelay) {
		this.alsdelay = alsdelay;
	}

	public String getAlsshtup() {
		return alsshtup;
	}

	public void setAlsshtup(String alsshtup) {
		this.alsshtup = alsshtup;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getSfptype() {
		return sfptype;
	}

	public void setSfptype(String sfptype) {
		this.sfptype = sfptype;
	}

	public String getSfpvendor() {
		return sfpvendor;
	}

	public void setSfpvendor(String sfpvendor) {
		this.sfpvendor = sfpvendor;
	}

	public String getWavelength() {
		return wavelength;
	}

	public void setWavelength(String wavelength) {
		this.wavelength = wavelength;
	}

	public String getSfpexptype() {
		return sfpexptype;
	}

	public void setSfpexptype(String sfpexptype) {
		this.sfpexptype = sfpexptype;
	}

	public String getDnugroup() {
		return dnugroup;
	}

	public void setDnugroup(String dnugroup) {
		this.dnugroup = dnugroup;
	}

	public String getSsmoutputenable() {
		return ssmoutputenable;
	}

	public void setSsmoutputenable(String ssmoutputenable) {
		this.ssmoutputenable = ssmoutputenable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJ0mode() {
		return j0mode;
	}

	public void setJ0mode(String j0mode) {
		this.j0mode = j0mode;
	}

	public String getExpectj0() {
		return expectj0;
	}

	public void setExpectj0(String expectj0) {
		this.expectj0 = expectj0;
	}

	public String getSendj0() {
		return sendj0;
	}

	public void setSendj0(String sendj0) {
		this.sendj0 = sendj0;
	}

	public String getReceivedj0() {
		return receivedj0;
	}

	public void setReceivedj0(String receivedj0) {
		this.receivedj0 = receivedj0;
	}

	public String getCheckj0() {
		return checkj0;
	}

	public void setCheckj0(String checkj0) {
		this.checkj0 = checkj0;
	}

	public String getLoopback() {
		return loopback;
	}

	public void setLoopback(String loopback) {
		this.loopback = loopback;
	}

	public String getRegsdthr() {
		return regsdthr;
	}

	public void setRegsdthr(String regsdthr) {
		this.regsdthr = regsdthr;
	}

	public String getRegsfthr() {
		return regsfthr;
	}

	public void setRegsfthr(String regsfthr) {
		this.regsfthr = regsfthr;
	}

	public String getMssdthr() {
		return mssdthr;
	}

	public void setMssdthr(String mssdthr) {
		this.mssdthr = mssdthr;
	}

	public String getMssfthr() {
		return mssfthr;
	}

	public void setMssfthr(String mssfthr) {
		this.mssfthr = mssfthr;
	}

	public String getMspid() {
		return mspid;
	}

	public void setMspid(String mspid) {
		this.mspid = mspid;
	}

	public String getJ1mode() {
		return j1mode;
	}

	public void setJ1mode(String j1mode) {
		this.j1mode = j1mode;
	}

	public String getExpectj1() {
		return expectj1;
	}

	public void setExpectj1(String expectj1) {
		this.expectj1 = expectj1;
	}

	public String getSendj1() {
		return sendj1;
	}

	public void setSendj1(String sendj1) {
		this.sendj1 = sendj1;
	}

	public String getReceivedj1() {
		return receivedj1;
	}

	public void setReceivedj1(String receivedj1) {
		this.receivedj1 = receivedj1;
	}

	public String getCheckj1() {
		return checkj1;
	}

	public void setCheckj1(String checkj1) {
		this.checkj1 = checkj1;
	}

	public String getExpectc2() {
		return expectc2;
	}

	public void setExpectc2(String expectc2) {
		this.expectc2 = expectc2;
	}

	public String getSendc2() {
		return sendc2;
	}

	public void setSendc2(String sendc2) {
		this.sendc2 = sendc2;
	}

	public String getCheckc2() {
		return checkc2;
	}

	public void setCheckc2(String checkc2) {
		this.checkc2 = checkc2;
	}

	public String getReceivedc2() {
		return receivedc2;
	}

	public void setReceivedc2(String receivedc2) {
		this.receivedc2 = receivedc2;
	}

	public String getHpsdthr() {
		return hpsdthr;
	}

	public void setHpsdthr(String hpsdthr) {
		this.hpsdthr = hpsdthr;
	}

	public String getHpsfthr() {
		return hpsfthr;
	}

	public void setHpsfthr(String hpsfthr) {
		this.hpsfthr = hpsfthr;
	}

	public String getSwitchport() {
		return switchport;
	}

	public void setSwitchport(String switchport) {
		this.switchport = switchport;
	}

	public List<SdhAcObject> getSdhAcList() {
		return sdhAcList;
	}

	public void setSdhAcList(List<SdhAcObject> sdhAcList) {
		this.sdhAcList = sdhAcList;
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
