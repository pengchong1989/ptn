package com.nms.drivechenxiao.service.bean.clock;


//interfaces/tod
//时钟源配置/状态
public class TodObject {
	private String name ; //名称
	private String ifname ; //接口名 
	private String index ; //接口索引值 ; int
	private String desc ; //接口描述 ; txt 1-16
	private String admin ; //接口管理状态  ;down=0,up=1,test=2
	private String oper ; //接口工作状态  //CoderUtils.analysisOper(CoderUtils.intTo6Binary(Integer.valueOf(value)))
	private String ref ; //u32
	private String priority1 ; //优先级1 ; int [0,255]
	private String clockclass ; //时钟类型 ; int [0,255]
	private String clockaccuracy ; //时钟精度 ; int [0,255]
	private String variance ; //时钟方差 ; int [0,65535]
	private String priority2 ; //优先级2 ; int
	private String mode ; //tod接口类型：输入，输出 ; in=0,out=1
	private String timeoffset ;//时间偏移补偿 ;int [0,1000000000]
	private String isdebug ; //是否使用tod
	private String scspri ; //scs优先级 ;int32 [0,255]
	private String scsmanual ; //scs人工倒换 ;bool
	private String scsforce ; //scs强制倒换 ； bool
	private String scslockout ; //scs闭锁 ； bool
	private String forcelevel ; //强制等级 ;  prc=1,ssua=2,ssub=3,sec=4,dnu=5,inv=6,auto=255
	private String PhysicalState ;//物理状态 ; fail=0,normal=1,wtr=2,na=3
	private String SCSLogicalState ; //scs逻辑状态 fail=0,normal=1,wtr=2,na=3
	private String SCSQL ; //cs质量等级  prc=1,ssua=2,ssub=3,sec=4,dnu=5,inv=6,auto=255
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIfname() {
		return ifname;
	}
	public void setIfname(String ifname) {
		this.ifname = ifname;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
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
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getPriority1() {
		return priority1;
	}
	public void setPriority1(String priority1) {
		this.priority1 = priority1;
	}
	public String getClockclass() {
		return clockclass;
	}
	public void setClockclass(String clockclass) {
		this.clockclass = clockclass;
	}
	public String getClockaccuracy() {
		return clockaccuracy;
	}
	public void setClockaccuracy(String clockaccuracy) {
		this.clockaccuracy = clockaccuracy;
	}
	public String getVariance() {
		return variance;
	}
	public void setVariance(String variance) {
		this.variance = variance;
	}
	public String getPriority2() {
		return priority2;
	}
	public void setPriority2(String priority2) {
		this.priority2 = priority2;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTimeoffset() {
		return timeoffset;
	}
	public void setTimeoffset(String timeoffset) {
		this.timeoffset = timeoffset;
	}
	public String getIsdebug() {
		return isdebug;
	}
	public void setIsdebug(String isdebug) {
		this.isdebug = isdebug;
	}
	public String getScspri() {
		return scspri;
	}
	public void setScspri(String scspri) {
		this.scspri = scspri;
	}
	public String getScsmanual() {
		return scsmanual;
	}
	public void setScsmanual(String scsmanual) {
		this.scsmanual = scsmanual;
	}
	public String getScsforce() {
		return scsforce;
	}
	public void setScsforce(String scsforce) {
		this.scsforce = scsforce;
	}
	public String getScslockout() {
		return scslockout;
	}
	public void setScslockout(String scslockout) {
		this.scslockout = scslockout;
	}
	public String getForcelevel() {
		return forcelevel;
	}
	public void setForcelevel(String forcelevel) {
		this.forcelevel = forcelevel;
	}
	public String getPhysicalState() {
		return PhysicalState;
	}
	public void setPhysicalState(String physicalState) {
		PhysicalState = physicalState;
	}
	public String getSCSLogicalState() {
		return SCSLogicalState;
	}
	public void setSCSLogicalState(String logicalState) {
		SCSLogicalState = logicalState;
	}
	public String getSCSQL() {
		return SCSQL;
	}
	public void setSCSQL(String scsql) {
		SCSQL = scsql;
	}
	
	
	
	
	
}
